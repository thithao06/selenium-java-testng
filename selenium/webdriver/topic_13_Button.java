package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class topic_13_Button {
    WebDriver webDriver;

    @BeforeClass
    public void beforeClass(){

        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
    }

    @Test
    public void TC_01_Button(){

        webDriver.get("https://www.fahasa.com/customer/account/create");
        webDriver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
        By loginButton = By.cssSelector("button.fhs-btn-login");
        Assert.assertFalse(webDriver.findElement(loginButton).isEnabled());
        Assert.assertEquals(Color.fromString(webDriver.findElement(loginButton).
                getCssValue("background-color")).
                asHex().toUpperCase(),"#000000");

        webDriver.findElement(By.cssSelector("input#login_username")).sendKeys("thao@gmail.com");
        webDriver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");
        Assert.assertTrue(webDriver.findElement(loginButton).isEnabled());
        Assert.assertEquals(Color.fromString(webDriver.findElement(loginButton).
                getCssValue("background-color")).
                asHex().toUpperCase(), "#C92127");


//        By registerButton = By.cssSelector("button.fhs-btn-register");
//
//        // cho implement k dc phep click
//        new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(registerButton)));
//
//        // kiem tra button disable
//        Assert.assertFalse(webDriver.findElement(registerButton).isEnabled());
//
//        // Verify text of button
//        Assert.assertEquals(webDriver.findElement(registerButton).getText(), "Đăng ký");
//
//        // Background color
//
//        Assert.assertEquals(Color.fromString(webDriver.findElement(registerButton).
//                getCssValue("background-color")).
//                asHex().toUpperCase(), "#000000");
    }

    @AfterClass
    public void afterClass(){
        webDriver.quit();
    }
}
