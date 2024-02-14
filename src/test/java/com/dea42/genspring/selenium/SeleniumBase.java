/**
 * 
 */
package com.dea42.genspring.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.web.server.LocalServerPort;

import com.dea42.genspring.UnitBase;
import com.dea42.genspring.utils.Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;

/**
 * Title: SeleniumBase <br>
 * Description: Base class for Selenium tests. <br>
 * Copyright: Copyright (c) 2001-2024<br>
 * Company: RMRR<br>
 *
 * @author Gened by GenSpring version 0.7.2<br>
 * @version 0.7.2<br>
 */
@Slf4j
public class SeleniumBase extends UnitBase {

	@LocalServerPort
	protected int port;
	protected String base;
	protected String context = "genspring";
	protected WebDriver driver;
	protected int timeOutInSeconds = 30;
	protected boolean useLocal = false;
	// time to pause at the start of each command.
	protected long speedDelay = 1;
	protected ResourceBundle bundle;

	@RegisterExtension
	public ScreenShotRule screenshotRule = new ScreenShotRule();
	
	private TestInfo testInfo;

	public SeleniumBase() {
		bundle = ResourceBundle.getBundle("test");
		String baseModule = Utils.getProp(bundle, "genSpring.module");
		context = Utils.getProp(bundle, "genSpring.artifactId", baseModule);

	}

	/**
	 * called at the start of each "command". Set speedDelay above to the
	 * milliseconds pause you want between commands or 0 for none.
	 */
	protected void speedControl() {
		if (speedDelay > 0) {
			try {
				Thread.sleep(speedDelay);
			} catch (InterruptedException e) {
				// ignored
			}
		}
	}

	protected WebElement getBy(By selector, WebElement parent) {
		speedControl();
		WebElement element = null;
		try {
			if (parent == null)
				element = driver.findElement(selector);
			else
				element = parent.findElement(selector);
		} catch (Exception e) {
			log.info("Element " + selector + " not found");
		}
		return element;
	}

	/**
	 * Open url in browser and wait for page to finish loading
	 * 
	 * @param url
	 */
	protected void open(String url) {
		try {
			speedControl();
			driver.get(url);
			driver.manage().window().maximize();
			waitForPageLoaded();
		} catch (Exception e) {
			// Debug breakpoint
			throw e;
		}
	}

	protected String getSrc() {
		speedControl();
		String rtn = null;
		try {
			rtn = driver.getPageSource();
		} catch (Exception e) {
			log.warn("failed getting page source", e);
		}

		return rtn;
	}

	protected String getHTML() {
		speedControl();
		String rtn = null;
		try {
			rtn = driver.findElement(By.tagName("html")).getAttribute("innerHTML");
		} catch (Exception e) {
			log.warn("failed getting page source", e);
		}

		return rtn;
	}

	/**
	 * Checks the nav system
	 */
	public void checkHeader() throws Exception {
		speedControl();
		List<String> names = getMenuLinks("guiMenu", "header.gui", false);
		assertTrue(names.size() > 0, "Check for more than 0 GUI menu items");
//		boolean login = true;
		checkCommonLinks(names.get(0), true);
//		if (login)
//			login = false;
		names = getMenuLinks("guiMenu", "header.gui", false);
		for (String name : names) {
			checkCommonLinks(name, false);
//			if (login)
//				login = false;
		}

		List<String> refs = getMenuLinks("restMenu", "header.restApi", true);
		assertTrue(names.size() > 0, "Check for more than 0 REST API menu items");
		for (String ref : refs) {
			open(ref);
			sourceContains("<id>1</id>", false);
		}

	}

	/**
	 * Check to see if expected is in page source
	 * 
	 * @param expected
	 * @param doesNotContain if true checks that expected is NOT in source
	 */
	protected void sourceContains(String expected, boolean doesNotContain) {
		String src = getSrc();
		assertNotNull(src, "checking page source not null");
		if (src.contains("??")) {
			fail("'??' was found in page source:" + src + " of:" + driver.getCurrentUrl());
		}
		if (doesNotContain) {
			if (src.contains(expected)) {
				fail("'" + expected + "' was found in page source:" + src + " of:" + driver.getCurrentUrl());
			}
		} else {
			if (!src.contains(expected)) {
				fail("'" + expected + "' was not found in page " + lang + " source:" + src + " of:"
						+ driver.getCurrentUrl());
			}
		}

	}

	/**
	 * Check to see if expected is in page text after waitFor becomes available
	 * 
	 * @param expected
	 * @param doesNotContain if true checks that expected is NOT in source
	 * @param waitFor        See waitForElement(By)
	 */
	protected void htmlContains(String expected, boolean doesNotContain, By waitFor) {
		if (waitFor != null)
			waitForElement(waitFor);

		String src = getHTML();
		assertNotNull(src, "checking page source not null");
		if (src.contains("??")) {
			fail("'??' was found in page source:" + src + " of:" + driver.getCurrentUrl());
		}
		if (doesNotContain) {
			if (src.contains(expected)) {
				fail("'" + expected + "' was found in page source:" + src + " of:" + driver.getCurrentUrl());
			}
		} else {
			if (!src.contains(expected)) {
				fail("'" + expected + "' was not found in page source:" + src + " of:" + driver.getCurrentUrl());
			}
		}

	}

	/**
	 * Open a url on Spring Boot app and wait for page to load
	 * 
	 * @param url without this.base bit as in /login
	 */
	protected void openTest(String url) {
		open(base + url);
	}

	/**
	 * Wait for the element to appear then click it. Same as waitThenClick(By, null)
	 * 
	 * @param selector
	 * @return
	 */
	protected WebElement waitThenClick(By selector) {
		return waitThenClick(selector, null);
	}

	/**
	 * wait for the element to appear then click it.
	 * 
	 * @param selector
	 * @param parent   element to search from or null to search page
	 * @return
	 */
	protected WebElement waitThenClick(By selector, WebElement parent) {
		waitForElement(selector);
		log.debug("clicking:" + selector);
		return clickBy(selector, parent);

	}

	/**
	 * Click a link based on its text. wrapper for clickBy(By.linkText(text),
	 * parent)
	 * 
	 * @param text
	 * @param parent
	 * @return
	 */
	protected WebElement clickLinkByText(String text, WebElement parent) {
		return clickBy(By.linkText(text), parent);
	}

	/**
	 * 
	 * @param selector
	 * @param parent
	 * @return
	 */
	protected WebElement clickBy(By selector, WebElement parent) {
		speedControl();
		WebElement element = getBy(selector, parent);
		assertNotNull(element, "Getting element" + selector);
		element.click();
		log.debug("Clicked:" + selector);
		return element;
	}

	protected String getAttribute(WebElement element, String attrName) {
		speedControl();
		String rtn = null;
		try {
			rtn = element.getAttribute(attrName);
		} catch (Exception e) {
			log.warn("Failed to get attribue " + attrName + " from " + element, e);
		}

		return rtn;
	}

	protected WebElement type(By selector, String text, boolean clearFirst, WebElement parent) {
		speedControl();
		WebElement element = getBy(selector, parent);
		assertNotNull(element, "Getting element by:" + selector);

		String b4text = "";

		if (clearFirst) {
			element.clear();
		} else {
			b4text = element.getText();
		}

		element.sendKeys(text);
		log.debug("Clicked link:" + text);

		String actual = getAttribute(element, "value");
		assertEquals(b4text + text, actual, "Checking element contains what we typed");

		return element;
	}

	public void waitForPageLoaded() {
		speedControl();
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		try {
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			wait.until(expectation);
		} catch (Throwable error) {
			fail("Timeout waiting for Page Load Request to complete.");
		}
	}

	public WebElement waitForElement(By selector) {
		speedControl();
		WebElement element = null;
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				try {
					WebElement element = driver.findElement(selector);
					return (element != null);
				} catch (Exception e) {
					return false;
				}
			}
		};
		try {
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			wait.until(expectation);
			element = driver.findElement(selector);
		} catch (Throwable error) {
			fail("Timeout waiting for Page Load after click of " + selector + " to complete.");
		}

		return element;
	}

	/**
	 * load web driver and set base part of URL to local app
	 */
	@BeforeEach
	public void setUp(TestInfo testInfo) throws Exception {
		this.testInfo = testInfo;
// If downloading and running an exe makes you nervous (and it probably should) set useLocal to true and update path below.
		if (useLocal) {
			System.setProperty("webdriver.gecko.driver", "C:\\webdriver\\geckodriver-v0.11.1-win64\\geckodriver.exe");
			DesiredCapabilities dc = DesiredCapabilities.firefox();
			dc.setCapability("marionette", true);
		} else {
			WebDriverManager.firefoxdriver().setup();
		}
		FirefoxOptions opts = new FirefoxOptions();
		opts.addArguments("--headless");
		driver = new FirefoxDriver(opts);
		if (this.getClass().getName().endsWith("IT"))
			this.base = "http://localhost:8089/" + context;
		else
			this.base = "http://localhost:" + port;
	}

//	@After
//	public void teardown() {
//		if (driver != null)
//			driver.quit();
//	}

	public void loginAdmin() {
		speedControl();
		WebElement form = waitForElement(By.id("signinForm"));
		if ("en".equals(lang))
			screenshotRule.docShot("login");
		ResourceBundle bundle = ResourceBundle.getBundle("app");
		String user = Utils.getProp(bundle, "default.adminEmail", null);
		String userpass = Utils.getProp(bundle, "default.adminpass", null);
		sourceContains("<legend>" + getMsg("signin.title") + "</legend>", false);
		type(By.id("email"), user, true, form);
		type(By.id("password"), userpass, true, form);
		clickBy(By.id("signinBtn"), form);

	}

	/**
	 * Run through the links from the home page down to the edit page, check for the
	 * save link and then cancel.
	 * 
	 * @param item        display name of the Entity class (probably the same as the
	 *                    class name).
	 * @param expectLogin if true expect and handle the login challenge.
	 */
	protected void checkCommonLinks(String item, boolean expectLogin) {
		speedControl();
		openTest("/");
		WebElement menu = waitThenClick(By.id("guiMenu"), null);
		waitThenClick(By.linkText(item), menu);
		if (expectLogin) {
			loginAdmin();
			menu = waitThenClick(By.id("guiMenu"), null);
			waitThenClick(By.linkText(item), menu);
		}
		waitForElement(By.id("resultsTable_filter"));
		// H1
		sourceContains(item + " " + getMsg("edit.list"), false);
		// search link
		sourceContains(getMsg("search.advanced") + " " + item, false);
		// new link
		sourceContains(getMsg("edit.new") + " " + item, false);
		// actions buttons
		sourceContains(getMsg("edit.edit"), false);
		sourceContains(getMsg("edit.delete"), false);
		// search box
		sourceContains(getMsg("search.like"), false);
		sourceContains(getMsg("search.search"), false);
		// Pagination
		htmlContains(getMsg("search.previous"), false, By.linkText(getMsg("edit.edit")));
		if ("en".equals(lang))
			screenshotRule.docShot(item + ".list");

		// check new page
		waitThenClick(By.linkText(getMsg("edit.new") + " " + item), null);
		sourceContains(getMsg("edit.new") + " " + item, false);
		assertNotNull(getBy(By.xpath("//button[@value='cancel']"), null), "Checking for cancel button");
		if ("en".equals(lang))
			screenshotRule.docShot(item + ".new");
		waitThenClick(By.xpath("//button[@value='cancel']"), null);

		// should go back to list page
		sourceContains(item + " " + getMsg("edit.list"), false);

		// check edit page by clicking first edit button found
		waitThenClick(By.linkText(getMsg("edit.edit")), null);
		sourceContains(getMsg("edit.edit") + " " + item, false);
		assertNotNull(getBy(By.xpath("//button[@value='save']"), null), "Checking for save button");
		if ("en".equals(lang))
			screenshotRule.docShot(item + ".edit");
		waitThenClick(By.xpath("//button[@value='save']"), null);

		// should go back to list page
		sourceContains(item + " " + getMsg("edit.list"), false);

		// check new page
		waitThenClick(By.linkText(getMsg("search.advanced") + " " + item), null);
		sourceContains(getMsg("search.advanced") + " " + item, false);
		assertNotNull(getBy(By.xpath("//button[@value='reset']"), null), "Checking for reset button");
		if ("en".equals(lang))
			screenshotRule.docShot(item + ".search");
		waitThenClick(By.xpath("//button[@value='reset']"), null);
		assertNotNull(getBy(By.xpath("//button[@value='search']"), null), "Checking for search button");
		waitThenClick(By.xpath("//button[@value='search']"), null);

		// should go back to list page
		sourceContains(item + " " + getMsg("edit.list"), false);

	}

	/**
	 * Get all the menu item links for a menu. TODO: add sub menu support
	 * 
	 * @param menuId
	 * @param menuKey
	 * @param returnHrefs
	 * @return
	 */
	protected List<String> getMenuLinks(String menuId, String menuKey, boolean returnHrefs) {
		openTest("/");
		WebElement menu = waitThenClick(By.id(menuId), null);
		List<WebElement> links = menu.findElements(By.cssSelector("a"));
		List<String> refs = new ArrayList<String>();
		for (WebElement we : links) {
			// skip menu link we clicked to display menu
			String txt = we.getText();
			if (!getMsg(menuKey).equals(txt)) {
				if (returnHrefs) {
					refs.add(getAttribute(we, "href"));
				} else {
					refs.add(txt);
				}
			}
		}

		log.debug(refs.toString());
		return refs;
	}

	protected void genfilesMd() throws IOException {
		Path fileList = Utils.createFile(".", "files.md", true);
		try (BufferedWriter bw = Files.newBufferedWriter(fileList, StandardOpenOption.TRUNCATE_EXISTING)) {
			String dbUrl = Utils.getProp(bundle, "db.url");
			bw.append("DB:" + dbUrl + "<br>");
			bw.newLine();
			bw.append("pom.xml<br>");
			bw.newLine();
			bw.append("README.md<br>");
			bw.newLine();
			bw.append("files.md<br>");
			bw.newLine();
			String root = Utils.getPath(".").toString().replace('\\', '/');
			Path srcPath = Utils.getPath("src");
			Files.walkFileTree(srcPath, new FileVisitor<Path>() {
				@Override
				public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					Path path = file.normalize();
					String pathStr = path.toString().replace('\\', '/').replace(root, ".");
					bw.append(pathStr + "<br>");
					bw.newLine();

					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
					return FileVisitResult.CONTINUE;
				}
			});
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * Open static url and check it contains expected
	 * 
	 * @param url
	 * @param expected
	 */
	protected void checkStatic(String url, String expected) {
		openTest(url);
		sourceContains(expected, false);
	}

	/**
	 * Do basic test of web site and get screen shots for docs.
	 * 
	 * @throws Exception
	 */
	protected void checkSite() throws Exception {
		String staticTop = "/public";
		// check statics
		// check css links work
		checkStatic("/resources/css/site.css", "background-color:");

		// webjars css URLs we use
		checkStatic("/webjars/bootstrap/4.0.0-2/css/bootstrap.min.css", "Bootstrap v4.0.0");
		checkStatic("/webjars/font-awesome/5.11.2/css/all.css", "Font Awesome Free 5.11.2");
		checkStatic("/webjars/tempusdominus-bootstrap-4/5.1.2/css/tempusdominus-bootstrap-4.min.css",
				"Tempus Dominus Bootstrap4 v5.1.2");
		checkStatic("/webjars/datatables/1.10.23/css/jquery.dataTables.min.css", "table.dataTable{width:");

		// check webjars js links work
		checkStatic("/webjars/jquery/3.5.1/jquery.min.js", "jQuery v3.5.1");
		checkStatic("/webjars/popper.js/1.12.9-1/umd/popper.min.js", "Federico Zivolo 2017");
		checkStatic("/webjars/bootstrap/4.0.0-2/js/bootstrap.min.js", "Bootstrap v4.0.0");
		checkStatic("/webjars/momentjs/2.24.0/min/moment.min.js", "invalidMonth:null,invalidFormat");
		checkStatic("/webjars/tempusdominus-bootstrap-4/5.1.2/js/tempusdominus-bootstrap-4.min.js",
				"Tempus Dominus Bootstrap4 v5.1.2");
		checkStatic("/webjars/datatables/1.10.23/js/jquery.dataTables.min.js", "DataTables 1.10.23");

		// favicon.ico
		openTest("/favicon.ico");

		// Check tabs saves as static pages that do not require login
		checkStatic(staticTop + "/optView.html", "resources/sheet.css");
		checkStatic(staticTop + "/Players.html", "resources/sheet.css");
		// Note is used as resources/sheet.css relative to
		// src/main/webapp/public/Players.html
		checkStatic(staticTop + "/resources/sheet.css", "fonts.googleapis.com");

		// check lang changes
		openTest("/");

		List<String> refs = getMenuLinks("langMenu", "lang.change", true);
		List<String> langs = new ArrayList<String>();
		for (String ref : refs) {
			langs.add(ref.substring(ref.length() - 2, ref.length()));
		}
		for (String ref : refs) {
			lang = ref.substring(ref.length() - 2, ref.length());
			open(ref);
			screenshotRule.docShot("home");
			sourceContains("<span>" + getMsg("lang.change") + "</span>", false);
			for (String lang : langs) {
				sourceContains(">" + getMsg("lang." + lang) + "</a>", false);
			}
			// do basic web page checks
			checkHeader();
			openTest("/logout");
		}
	}

	/**
	 * Class for getting shots of windows during tests.
	 * 
	 * @author deabigt
	 *
	 */
	public class ScreenShotRule implements TestWatcher {
		String docShots = "screenshots/";
		String testName = "";
		private long startTime = 0;
		private long endTime = 0;

		public ScreenShotRule() {
			Path ds = Utils.getPath(docShots);
			File p = ds.toFile().getParentFile();
			if (!p.exists()) {
				p.mkdirs();
			}
		}

		/**
		 * Save pic of window for documentation. If lang !="en" then add .lang as mod to
		 * filename.
		 * 
		 * @param shotName
		 */
		public void docShot(String shotName) {
			String mod = "";
			if (!"en".equals(lang))
				mod = "." + lang;
			Path picPath = Utils.getPath(docShots, shotName + mod + ".png");
			takeShot(picPath.toFile());
		}

		/**
		 * Save shot of window to destFile
		 * 
		 * @param destFile
		 */
		public void takeShot(File destFile) {
			try {
				TakesScreenshot takesScreenshot = (TakesScreenshot) driver;

				File scrFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, destFile);
			} catch (IOException ioe) {
				throw new RuntimeException(ioe);
			}

		}

		/**
		 * Sort what to use for testName during the test. And set startTime for metrics
		 */
		public void starting() {
			startTime = System.currentTimeMillis();
			if (testInfo.getDisplayName() != null)
				testName = testInfo.getDisplayName();
			else if (testInfo.getTestMethod() != null)
				testName = testInfo.getTestMethod().get().getName();
			else if (testInfo.getTestClass() != null)
				testName = testInfo.getTestClass().get().getName();
			else
				testName = "unknown";
		}

		@Override
		public void testFailed(ExtensionContext context, Throwable e) {
			endTime = System.currentTimeMillis();
			File destFile = new File("target/" + testName + ".screenShot.png");
			takeShot(destFile);
			log.error("saved screenshot to:" + destFile.getAbsolutePath(), e);
		}

		@Override
		public void testSuccessful(ExtensionContext context) {
			endTime = System.currentTimeMillis();
			// make lint happy
			assertTrue(endTime > startTime);
		}

		public void finished() {
			log.info("Runtime:" + (endTime - startTime) + " milliseconds");
			if (driver != null)
				driver.quit();
		}

		@Override
		public void testAborted(ExtensionContext context, Throwable cause) {
			/* no-op */
		}

		@Override
		public void testDisabled(ExtensionContext context, Optional<String> reason) {
			/* no-op */
		}
	}

}
