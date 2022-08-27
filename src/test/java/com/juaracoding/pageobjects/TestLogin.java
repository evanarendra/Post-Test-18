package com.juaracoding.pageobjects;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.juaracoding.pageobjects.drivers.DriveriSingleton;
import com.juaracoding.pageobjects.pages.LoginPage;

public class TestLogin {
	
	public static WebDriver driver;
	private LoginPage loginPage;
	
	@BeforeClass
	public void setUp() {
		DriveriSingleton.getInstance("Chrome");
		driver = DriveriSingleton.getDriver();
		String url = "https://opensource-demo.orangehrmlive.com";
//		String url = "https://shop.demoqa.com/my-account/";
		driver.get(url);
	}
	
	@BeforeMethod
	public void pageObject() {
		loginPage = new LoginPage();
	}
	
	@Test
	public void testInvalidLogin() {
		loginPage.login("Admin", "admin1234");
		assertEquals(loginPage.msgInvalid(), "Invalid credentials");
	}
	
	@Test
	public void testValidLogin() {
		loginPage.login("Admin", "admin123");
		assertEquals(loginPage.getTxtDashboard(), "Dashboard");
	}
	
	@AfterClass 
	public void closeBrowser() {
		delay(3);
		driver.quit();
	}
	
	static void delay(int detik) {
		try {
			Thread.sleep(1000*detik);
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
