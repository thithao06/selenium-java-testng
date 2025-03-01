package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class topic_19_Random_Popup {
    WebDriver webDriver;

    @BeforeClass
    public void beforeClass(){
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
    }

    @Test
    public void TC_01_Dehieu_In_Dom() throws InterruptedException {
        webDriver.get("https://dehieu.vn/");
        Thread.sleep(2000);
        By popup = By.cssSelector("div.modal-content");
        if (webDriver.findElement(popup).isDisplayed()){
           webDriver.findElement(By.cssSelector("div.modal-content button.close")).click();
        }
        webDriver.findElement(By.xpath("//a[text()=' Đăng nhập']")).click();
        Thread.sleep(2000);
        Assert.assertTrue(webDriver.findElement(By.cssSelector("div.b-login")).isDisplayed());
    }

    @Test
    public void TC_02_KMPlayer() throws InterruptedException {
        webDriver.get("https://www.kmplayer.com/home");
        Thread.sleep(5000);
        By popup = By.cssSelector("div.pop-container");
        if (webDriver.findElement(popup).isDisplayed()){
            webDriver.findElement(By.cssSelector("div.close")).click();
        }

        new Actions(webDriver, Duration.ofSeconds(2)).moveToElement(webDriver.findElement(By.cssSelector("li.pc.pc64x"))).perform();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//li[@class='pc']//a[text()='KMPlayer']")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("div.sub h1")).getText(),"KMPlayer - Video Player for PC");
    }

    @AfterClass
    public void afterClass(){
        webDriver.quit();
    }
}
