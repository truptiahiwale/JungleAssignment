package test;

import pages.BasePage;

import pages.JungleHomePage;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.CustomSoftAssert;

public class FailTestCaseExample extends BasePage {
	public static CustomSoftAssert softAssert = new CustomSoftAssert();
	JungleHomePage jungleHomePage = new JungleHomePage();
	WebDriver driver2;

	@Test(priority = 12, description = "Verify Get started link is present  ")
	public void verifyBannerTextLink() {

		softAssert.assertEquals(jungleHomePage.getBannerTextLink(), "Learn more about Jungle");

		softAssert.assertAll();

	}

	@BeforeClass
	public void openDriver() {
		setDriver(driver2);
		Assert.assertTrue(goToHomepage(), "An error occurred while navigating to the the homepage");
		System.out.println("Opening home page before method");
		waitForPageTitle(getPageTitle());

	}

	@AfterClass
	public void tearDown() {
		closeWindow();
		closeBrowser();
		System.out.println("Test scenario completed");
	}
}
