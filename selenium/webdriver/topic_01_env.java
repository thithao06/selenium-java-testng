package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class topic_01_env {
    WebDriver webDriver;

    @Test
    public void TC_01_RunOnChrome(){
        webDriver = new ChromeDriver();
        webDriver.get("https://www.youtube.com/watch?v=ucVQxPCkIXQ");
        webDriver.quit();
    }
}
