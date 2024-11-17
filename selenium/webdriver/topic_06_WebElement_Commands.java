package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_06_WebElement_Commands {
    WebDriver webDriver;

    @BeforeClass
    public void beforeClass(){
        webDriver = new ChromeDriver();
    }

    @Test
    public void TC_01_RunOnChrome(){
        webDriver.get("");
    }

    @AfterClass
    public void afterClass(){
        webDriver.quit();
    }
}
