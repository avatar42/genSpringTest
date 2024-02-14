/**
 * 
 */
package com.dea42.genspring.selenium;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Basic smoke test to check app works.
 * 
 * @author GenSpring
 *
 */
@ExtendWith(SpringExtension.class)
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