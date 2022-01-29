package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BasePage {
	private static WebDriver driver;

	public String browser;
	public String baseUrl;
	public Properties properties;

	private void loadProperties() {
		FileInputStream fis = null;

		try {
			properties = new Properties();
			fis = new FileInputStream("src\\main\\java\\config\\config.properties");
			properties.load(fis);

			browser = properties.getProperty("browser");
			baseUrl = properties.getProperty("baseUrl");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void openBrowser() {
		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();

			setDriver(new ChromeDriver());
		}
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		getDriver().manage().window().maximize();
	}

	public void closeBrowser() {
		getDriver().quit();
	}

	public void closeWindow() {
		getDriver().close();
	}

	public String getCurrentURL() {
		return getDriver().getCurrentUrl();
	}

	public Boolean goToHomepage() {
		try {
			loadProperties();
			openBrowser();
			getDriver().get(baseUrl);
		} catch (Exception e) {
			System.out.println("Unable to navigate to the homepage");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void setText(By locator, String text) {
		getDriver().findElement(locator).clear();
		getDriver().findElement(locator).sendKeys(text);
		tab(locator);
	}

	public void tab(By locator) {
		getDriver().findElement(locator).sendKeys(Keys.TAB);
	}

	public String getText(By locator) {
		String displayedText = getDriver().findElement(locator).getText();
		if (displayedText.isEmpty()) {
			return getDriver().findElement(locator).getAttribute("value");
		} else {
			return displayedText;
		}
	}

	public void click(By locator) {
		getDriver().findElement(locator).click();
	}

	public void goBack() {
		getDriver().navigate().back();
	}

	public void goToUrl(String url) {
		getDriver().get(url);
	}

	public String getPageTitle() {
		return getDriver().getTitle();
	}

	public boolean waitForPageTitle(String title) {
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.titleContains(title));
	}

	public void waitForElementText(By locator, String text) {
		// This is an explicit wait
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
		wait.until(ExpectedConditions.textToBe(locator, text));

		// This is a FluentWait. It does the same as the above wait, but it is more
		// customizable
//        Wait<WebDriver> wait = new FluentWait<>(driver)
//                .withTimeout(Duration.ofSeconds(3))
//                .pollingEvery(Duration.ofMillis(250))
//                .ignoring(NoSuchElementException.class);
//        wait.until(ExpectedConditions.textToBe(locator, text));
	}

	public void waitForElementToBeVisible(WebElement webElement, int sec) {
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(sec));
		wait.until(ExpectedConditions.visibilityOf(webElement));

	}

	public void waitForElementToBeClikable(WebElement webElement, int sec) {
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(sec));
		wait.until(ExpectedConditions.elementToBeClickable(webElement));

	}

	
	public void waitForPageLoad(String title, int sec) {
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(sec));

		wait.until(ExpectedConditions.titleContains(title));
	}

	public void hoverOverElement(By locator) {
		WebElement element = getDriver().findElement(locator);
		Actions actions = new Actions(getDriver());
		actions.moveToElement(element).perform();
	}

	/**
	 * Takes screenshot of whole page and uses the current date/time as the file
	 * name
	 */
	public void takeScreenshot() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH-mm-ss-SSS");
		LocalDateTime dateTime = LocalDateTime.now();
		takeScreenshot(dateTime.format(formatter));
	}

	/**
	 * Takes screenshot of whole page
	 *
	 * @param screenshotName The screenshot file name
	 */
	public void takeScreenshot(String screenshotName) {
		TakesScreenshot screenshot = (TakesScreenshot) getDriver();
		File file = screenshot.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file, new File("./failed_tests/" + screenshotName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Takes screenshot of single WebElement
	 */
	public void takeElementScreenshot(By locator) {
		WebElement element = getDriver().findElement(locator);
		File file = element.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file, new File("./screenshot.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the driver
	 */
	public WebDriver getDriver() {
		return driver;
	}

	/**
	 * @param driver the driver to set
	 */
	public void setDriver(WebDriver driver) {
		BasePage.driver = driver;
	}

}
