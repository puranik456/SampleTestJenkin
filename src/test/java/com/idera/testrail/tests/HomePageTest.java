package com.idera.testrail.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.testrailmanger.TestRailManager;

import static org.testng.AssertJUnit.assertTrue;


public class HomePageTest {

    public static WebDriver driver;
    protected String testCaseId;    
    @BeforeMethod
    void setup() {
        System.setProperty("webdriver.chrome.driver","C:\\Softwares\\chromedriver-win64_latest\\chromedriver-win64\\chromedriver.exe");
        //WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--start-maximized");
        options.addArguments("--no-proxy-server");
        options.addArguments("disable-infobars"); // Disabling infobars
        options.addArguments("--disable-web-security");
        options.addArguments("--allow-running-insecure-content");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-extensions"); // Disabling extensions
        options.addArguments("--disable-dev-shm-usage"); // Overcome limited resource problems
        options.addArguments("--no-sandbox"); // Bypass OS security model
        driver = new ChromeDriver(options);
        // Navigation: Open a website
        driver.navigate().to("https://www.gurock.com/testrail");
    }

    @Test
    public void verifyTitleOfHomePage() {
        testCaseId="2389";
        assertTrue(driver.getTitle().contains("TestRail"));
    }

    @Test
    void verifyPresenceOfDemoLinkOnHomePage() {
        testCaseId="2390";
        By demoButtonSelector = By.linkText("Get a Demo");
        WebElement demoButton = driver.findElement(demoButtonSelector);
        assertTrue(demoButton.isDisplayed());
    }

    @Test
    void invalidTest() {
        testCaseId="2391";
        assertTrue(driver.getTitle().contains("WRONG TITLE"));
    }

    @AfterTest
    void tearDown() {
        // Quit Driver after each test is completed
        driver.quit();
    }
    
    @AfterMethod
    public void addResultToTestRail(ITestResult result) {
       if(result.getStatus()== ITestResult.SUCCESS) {
           TestRailManager.addResultsForTestCase(testCaseId,TestRailManager.TEST_CASE_PASS_STATUS,"");
       }else if(result.getStatus()== ITestResult.FAILURE) {
           TestRailManager.addResultsForTestCase(testCaseId,TestRailManager.TEST_CASE_FAIL_STATUS , 
              "test got failed..."+ result.getName() + " : FAILED");
       }
        
    }
}
