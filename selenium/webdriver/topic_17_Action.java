package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.swing.*;
import java.time.Duration;

public class topic_17_Action {
    WebDriver webDriver;
    Actions action;

    @BeforeClass
    public void beforeClass(){
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--user-data-dir=C:/Users/thao.tran/AppData/Local/Microsoft/Edge/User Data/");
        options.addArguments("--profile-directory=Profile 1");
        options.addArguments("--disable-geolocation");
        webDriver = new EdgeDriver(options);
        webDriver.manage().window().maximize();

        action = new Actions(webDriver);
    }

    @Test
    public void TC_01_JQuery(){
        webDriver.get("https://automationfc.github.io/jquery-tooltip/");

        action.moveToElement(webDriver.findElement(By.cssSelector("input#age")))
                .pause(Duration.ofSeconds(3))
                .perform();

        Assert.assertEquals(webDriver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
    }

    @Test
    public void TC_02_Myntra(){
        webDriver.get("https://www.myntra.com/");

        action.moveToElement(webDriver.findElement(By.xpath("//div[@class='desktop-navLinks']//a[text()='Kids']")))
                .pause(Duration.ofSeconds(3))
                .perform();

        webDriver.findElement(By.xpath("//a[text()='Home & Bath']")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span.breadcrumbs-crumb")).getText(), "Kids Home Bath");
    }

    @Test
    public void TC_03_Fahasa() throws InterruptedException {
        webDriver.get("https://www.fahasa.com/");

        action.moveToElement(webDriver.findElement(By.cssSelector("span.icon_menu")))
                .pause(Duration.ofSeconds(3))
                .perform();

        action.moveToElement(webDriver.findElement(By.xpath("//a[@title='FOREIGN BOOKS']")))
                .perform();

        action.click(webDriver.findElement(By.xpath("//div[contains(@class,'fhs_menu_content')]//a[text()='Fantasy']"))).perform();
        Thread.sleep(3000);
        Assert.assertEquals(webDriver.findElement(By.cssSelector("ol.breadcrumb strong")).getText(), "FANTASY");
    }

    @AfterClass
    public void afterClass(){
        webDriver.quit();
    }
}
