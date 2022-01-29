package test;

import pages.BasePage;

import pages.JungleHomePage;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.CustomSoftAssert;

public class JungleHomePageTest extends BasePage {
	public static CustomSoftAssert softAssert = new CustomSoftAssert();
	JungleHomePage jungleHomePage = new JungleHomePage();

	WebDriver driver1;

	@Test(priority = 0, description = "verify header list")
	public void verifyMenuLinkedDisplayed() {

		String[] expectedMenuLinks = { "Explore", "Market", "How It Works", "Community" };
		String[] actualMenuLinks = jungleHomePage.getMenuList();

		softAssert.assertEquals(actualMenuLinks, expectedMenuLinks);

		softAssert.assertAll();

	}

	@Test(priority = 1, description = "verify step section title and detail message ")
	public void verifyStepsSection() {

		// get step 1
		String[] step1 = jungleHomePage.getStepDetails(1);

		// Verify Step 1 header
		softAssert.assertEquals(step1[0], "Set up your wallet");
		// Verify Step 1 detail message
		softAssert.assertEquals(step1[1],
				"Setting up a wallet on Jungle NFT is simple and seamless. Click the wallet icon on the top right corner to get started.");

		// Get Step 2
		String[] step2 = jungleHomePage.getStepDetails(2);

		// Verify Step 2 header
		softAssert.assertEquals(step2[0], "Build your collection");
		// Verify Step 2 detail message
		softAssert.assertEquals(step2[1],
				"Whether you’re starting from scratch or want to add more to your collection, set up your profile and start adding info.");

		// Get step 3
		String[] step3 = jungleHomePage.getStepDetails(3);

		// Verify Step 3 header
		softAssert.assertEquals(step3[0], "Add your own NFTs");
		// Verify Step 3 detail message
		softAssert.assertEquals(step3[1],
				"Upload your digital art (that includes image, audio, video, and 3D art), and add a title and description to get going.");

		// Get Step 4
		String[] step4 = jungleHomePage.getStepDetails(4);

		// Verify Step 4 header
		softAssert.assertEquals(step4[0], "List NFTs for sale");
		// Verify Step 4 detail message
		softAssert.assertEquals(step4[1],
				"Choose between a variety of methods, from auctions to listings, and connect with interested buyers on the Jungle NFT Marketplace.");

		softAssert.assertAll();

	}

	@Test(priority = 2, description = "Verify Category List present ")
	public void verifyCategoryList() {

		String[] expectedCategories = { "Art", "Music", "Domain Names", "Virtual Worlds", "Trading Cards",
				"Collectibles", "Sports", "Utility", "All NFTs" };
		String[] actualCategories = jungleHomePage.getCategoryList();

		softAssert.assertEquals(actualCategories, expectedCategories);

		softAssert.assertAll();

	}

	@Test(priority = 3, description = "Verify FAQ section ")
	public void verifyFAQSection() {

		// get Question 1
		String[] q1 = jungleHomePage.getFAQDetails(1);

		// Verify Question 1 details
		softAssert.assertEquals(q1[0], "What is Jungle NFT?");
		// Verify detail answer
		softAssert.assertEquals(q1[1],
				"The Jungle NFT Marketplace is a space where creators and collectors can meet to discover, buy, and sell unique and innovative digital art and crypto collectibles.");

		// get Question 2 details
		String[] q2 = jungleHomePage.getFAQDetails(2);

		// Verify Question 2 header
		softAssert.assertEquals(q2[0], "What makes Jungle NFT different?");
		// Verify detail answer
		softAssert.assertEquals(q2[1],
				"Great question. We believe purchasing NFTs should be simple - that means straightforward transactions, easily discoverable art, and community-driven design. Jungle NFT is for everyone - not just early adopters or crypto experts.");

		// get Question 3 details
		String[] q3 = jungleHomePage.getFAQDetails(3);

		// Verify Question 3 header
		softAssert.assertEquals(q3[0], "When will you launch?");
		// Verify detail answer
		softAssert.assertEquals(q3[1],
				"We’re launching very soon! Watch this space for more information on Jungle NFT.");

		softAssert.assertAll();

	}

//	@Test(priority = 4, description = "Verify login functionlity  ")
//	public void verifyLoginFunctionlity() {
//
//		softAssert.assertEquals(jungleHomePage.signUp("trupti_sa5@yahoo.co.in"), "Thank you, You are in!");
//
//	}

	@Test(priority = 5, description = "Verify Banner head line displayed")
	public void verifyBannerHeadLine() {

		softAssert.assertEquals(jungleHomePage.getBannerHeadLine(), "Buy, Sell & Create Digital Art");
		softAssert.assertAll();

	}

	@Test(priority = 6, description = "Verify Banner detailed text displayed  ")
	public void verifyBannerDetailedText() {

		softAssert.assertEquals(jungleHomePage.getBannerDetailText(),
				"The Jungle NFT Marketplace is a space where creators and collectors can meet to discover, buy, and sell unique and innovative digital art and crypto collectibles.");
		softAssert.assertAll();

	}

	@Test(priority = 7, description = "Verify Slogan Title displayed  ")
	public void verifySloganTitle() {

		softAssert.assertEquals(jungleHomePage.getSloganTitleText(), "Never miss a drop!");
		softAssert.assertAll();

	}

	@Test(priority = 8, description = "Verify Slogan details displayed  ")
	public void verifySloganDetails() {

		softAssert.assertEquals(jungleHomePage.getSloganTitleDetails(),
				"Join our mailing list to stay tuned with Jungle NFT news, drops, and other releases.");
		softAssert.assertAll();

	}

	@Test(priority = 9, description = "Verify section headers displayed  ")
	public void verifySectionHeaders() {

		softAssert.assertEquals(jungleHomePage.getStepsHeader(), "How it works");
		softAssert.assertEquals(jungleHomePage.getCategorysHeader(), "Browse by category");
		softAssert.assertEquals(jungleHomePage.getFAQHeader(), "Frequently asked questions");

		softAssert.assertAll();

	}

	@Test(priority = 10, description = "Verify Get started link is present  ")
	public void verifyGetStartedLink() {

		softAssert.assertEquals(jungleHomePage.verifyGetStartedLinkPresent(), true);

		softAssert.assertAll();

	}

	@Test(priority = 11, description = "Verify Get started link is present  ")
	public void verifyBannerTextLink() {

		softAssert.assertEquals(jungleHomePage.getBannerTextLink(), "Learn more about Jungle NFT");

		softAssert.assertAll();

	}

	@BeforeClass
	public void openDriver() {
		setDriver(driver1);
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
