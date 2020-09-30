/**
 * 
 */
package com.dea42.genspring.selenium;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

/**
 * Basic smoke test to check app works.
 * 
 * @author GenSpring
 *
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class SmokeIT extends SeleniumBase {

	/**
	 * Do basic login and check pages are reachable in deployed app mode
	 * 
	 * @throws Exception
	 */
	@Test
	public void smokeTest() throws Exception {
		checkSite();
	}
}