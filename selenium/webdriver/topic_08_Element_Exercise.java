package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_08_Element_Exercise {
    WebDriver webDriver;

    @BeforeClass
    public void beforeClass(){

        webDriver = new ChromeDriver();
    }

    @Test
    public void TC_01_RunOnChrome(){

        webDriver.get("https://automationfc.github.io/basic-form/index.html");
        Assert.assertTrue(webDriver.findElement(By.cssSelector("input#mail")).isDisplayed());
        Assert.assertTrue(webDriver.findElement(By.cssSelector("input#under_18")).isDisplayed());
        Assert.assertTrue(webDriver.findElement(By.cssSelector("textarea#edu")).isDisplayed());
        Assert.assertFalse(webDriver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed());

    }

    @AfterClass
    public void afterClass(){

        webDriver.quit();
    }
}
