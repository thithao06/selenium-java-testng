package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_18_Fixed_Popup {
    WebDriver webDriver;

    @BeforeClass
    public void beforeClass(){
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
    }

    @Test
    public void TC_01_Ngoaingu24h() throws InterruptedException {
        webDriver.get("https://ngoaingu24h.vn/");
        webDriver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();

        Thread.sleep(2000);

        By loginDialog = By.cssSelector("div#custom-dialog div[role='dialog']");
        Assert.assertTrue(webDriver.findElement(loginDialog).isDisplayed());
        webDriver.findElement(By.cssSelector("div[role='dialog'] input[autocomplete='username']")).sendKeys("automationfc");
        webDriver.findElement(By.cssSelector("div[role='dialog'] input[autocomplete='new-password']")).sendKeys("automationfc");
        webDriver.findElement(By.xpath("//div[@role='dialog']//button[text()='Đăng nhập']")).click();

        Thread.sleep(1000);

        Assert.assertEquals(webDriver.findElement(By.cssSelector("div#notistack-snackbar")).getText(), "Bạn đã nhập sai tài khoản hoặc mật khẩu!");
        webDriver.findElement(By.cssSelector("div[role='dialog'] button.close-btn")).click();

        Thread.sleep(2000);
        Assert.assertEquals(webDriver.findElements(loginDialog).size(), 0);

    }

    @Test
    public void TC_02_Tiki() throws InterruptedException {
        webDriver.get("https://tiki.vn/");
        Thread.sleep(5000);
        webDriver.findElement(By.xpath("//div//span[text()='Tài khoản']")).click();
        Thread.sleep(2000);
        By loginDialog = By.cssSelector("div[role='dialog']");
        Assert.assertTrue(webDriver.findElement(loginDialog).isDisplayed());
        webDriver.findElement(By.cssSelector("p.login-with-email")).click();
        webDriver.findElement(By.xpath("//div[@role='dialog']//button[text()='Đăng nhập']")).click();

        Assert.assertEquals(webDriver.findElement(By.xpath("//input[@type='email']/parent::div/following::span[1]")).getText(),
                "Email không được để trống");
        Assert.assertEquals(webDriver.findElement(By.xpath("//input[@type='password']/parent::div/following::span[1]")).getText(),
                "Mật khẩu không được để trống");

        webDriver.findElement(By.cssSelector("button.btn-close")).click();
        Assert.assertEquals(webDriver.findElements(loginDialog).size(),0);

    }

    @AfterClass
    public void afterClass(){
        webDriver.quit();
    }
}
