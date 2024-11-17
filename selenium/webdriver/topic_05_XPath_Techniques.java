package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_05_XPath_Techniques {
    WebDriver webDriver;

    @BeforeClass
    public void beforeClass(){
        webDriver = new ChromeDriver();
        webDriver.get("https://www.youtube.com/watch?v=ucVQxPCkIXQ");
    }

    @Test
    public void TC_01_RunOnChrome(){
        webDriver.get("https://www.youtube.com/watch?v=ucVQxPCkIXQ");
    }

    @AfterClass
    public void afterClass(){
        webDriver.quit();
    }
}
