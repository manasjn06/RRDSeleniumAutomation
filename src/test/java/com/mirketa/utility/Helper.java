package com.mirketa.utility;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.google.common.io.Files;

public class Helper {
	public static String captureScreenshot(WebDriver driver, String screenshotName) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String destination = "C:\\Automation\\DevWorkspacce\\rrd\\Screenshot\\" + screenshotName + System.currentTimeMillis() + ".png";
		try {
			Files.copy(src, new File(destination));
		} catch (IOException e) {
			System.out.println("Failed to capture screenshot " + e.getMessage());
		}

		return destination;

	}

}
