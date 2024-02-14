/**
 * 
 */
package com.dea42.genspring.selenium;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Basic smoke test to check app works.
 * 
 * @author GenSpring
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SmokeTest extends SeleniumBase {

	/**
	 * Test test framework is working
	 * 
	 * @throws IOException
	 */
	@Test
	void getSearchPage() throws IOException {
		open("https://www.google.com");
		WebElement element = driver.findElement(By.name("q"));
		assertNotNull(element, "Testing driver works");
		genfilesMd();
	}

	/**
	 * Do basic login and check pages are reachable in Spring Boot standalone mode
	 * 
	 * @throws Exception
	 */
	@Test
	void smokeTest() throws Exception {
		checkSite();
	}
}