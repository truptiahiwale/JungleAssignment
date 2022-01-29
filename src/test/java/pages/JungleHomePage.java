package pages;

import java.util.List;

import org.jsoup.select.Evaluator.IsEmpty;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;

public class JungleHomePage extends BasePage {

	private By menuList = By.xpath("//*[@id='nav-menu']/li/a");
	private By bannerHeadline = By.xpath("//h1[@class='banner-text__headline']");

	private By stepTitleEle = By.xpath("//*[@class='steps__box'][2]/div/h3");
	private By categoryList = By.xpath("//h3[@class='items__title']");
	private By emailTextBox = By.name("email");
	private By signUpBtn = By.xpath("//button[text()='Sign up']");
	private By welcomMsg = By.xpath("//*[@id='subscribeForm']/div");
	private By bannerDetail = By.xpath("//div[@class='banner-text__desc']");
	private By sloganTitle = By.xpath("//*[@class='slogan__title']");
	private By sloganDetailedText = By.xpath("//div[@class='slogan']/div");
	private By getStartedLink = By.xpath("//a[text()='Get Started']");
	private By bannerTextLink = By.xpath("//div[@class='banner-text__link']/a/span");
	private String bannerHeadlineText;
	private String stepTitle;
	private String stepDetailText;
	private String fAQTitle;
	private String fAQDetailText;
	private String bannerDetailText;
	private String sloganTitleText;

	public String[] getMenuList() {

		List<WebElement> listOfItems = getDriver().findElements(menuList);
		String[] eleText = new String[listOfItems.size()];

		System.out.println("list size " + listOfItems.size());
		for (int i = 0; i < listOfItems.size(); i++) {

			eleText[i] = listOfItems.get(i).getText();

		}
		return eleText;

	}

	public String getBannerHeadLine() {
		bannerHeadlineText = getText(bannerHeadline);

		return bannerHeadlineText;
	}

	public String getBannerDetailText() {
		bannerDetailText = getText(bannerDetail);

		return bannerDetailText;
	}

	public String getSloganTitleText() {
		sloganTitleText = getText(sloganTitle);

		return sloganTitleText;
	}

	public String getSloganTitleDetails() {

		return getText(sloganDetailedText);
	}
	

	public String getStepsHeader() {

		return getDriver().findElement(By.xpath("//*[@id='steps']/div/h2")).getText();
	}
	
	public String getCategorysHeader() {

		return getDriver().findElement(By.xpath("//*[@id='market']/div/h2")).getText();
	}
	public String getFAQHeader() {

		return getDriver().findElement(By.xpath("//*[@id='faq']/div/h2")).getText();
	}

	public String[] getStepDetails(int stepNum)

	{

		WebElement stepTitleEle = getDriver().findElement(By.xpath("//*[@class='steps__box'][" + stepNum + "]/div/h3"));
		stepTitle = stepTitleEle.getText();

		WebElement stepDetailsEle = getDriver()
				.findElement(By.xpath("//*[@class='steps__box'][" + stepNum + "]//div[@class='steps__desc']"));
		stepDetailText = stepDetailsEle.getText();

		String[] stepDetails = { stepTitle, stepDetailText };

		return stepDetails;
	}

	public String[] getCategoryList() {

		List<WebElement> listOfItems = getDriver().findElements(categoryList);
		String[] eleText = new String[listOfItems.size()];

		System.out.println("list size " + listOfItems.size());
		for (int i = 0; i < listOfItems.size(); i++) {

			eleText[i] = listOfItems.get(i).getText();

		}
		return eleText;

	}

	public String[] getFAQDetails(int num)

	{
		
		WebElement fAQTitleEle = getDriver().findElement(By.xpath("//*[@id='faq']//ul/li[" + num + "]/h3/span"));
		fAQTitle = fAQTitleEle.getText();

		WebElement fAQDetailsEle = getDriver().findElement(By.xpath("//*[@id='faq']//ul/li[" + num + "]/div"));
		fAQDetailText = fAQDetailsEle.getAttribute("innerText");

		String[] stepDetails = { fAQTitle, fAQDetailText};

		return stepDetails;

	}

	public void enterEmail(String emailID) {

		setText(emailTextBox, emailID);

	}

	public void clickSignUpBtn() {

		waitForElementToBeClikable(getDriver().findElement(signUpBtn), 10);

		click(signUpBtn);

	}

	public String getWelcomMsg() {
		waitForElementToBeVisible(getDriver().findElement(welcomMsg), 10);
		String msg = getText(welcomMsg);
		return msg;
	}

	public String signUp(String emailID) {
		String msg = "";
		try {
			enterEmail(emailID);
			clickSignUpBtn();
			msg = getWelcomMsg();

		} catch (ElementClickInterceptedException e) {
			System.out.println("Sign up button not clickable");

		}

		return msg;
	}
	
	public boolean verifyGetStartedLinkPresent()
	{
		
		return getDriver().findElement(getStartedLink).isDisplayed();
	}
	
	public String getBannerTextLink()
	{
		
		return getDriver().findElement(bannerTextLink).getText();
	}
	
	
	
}
