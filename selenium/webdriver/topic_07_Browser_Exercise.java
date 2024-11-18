package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_07_Browser_Exercise {
    WebDriver webDriver;

    @BeforeClass
    public void beforeClass(){
        webDriver = new ChromeDriver();
    }

    @Test
    public void TC_01_verifyUrl(){
        webDriver.get("https://live.techpanda.org/");
        webDriver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']")).click();
        Assert.assertEquals( webDriver.getCurrentUrl(),"https://live.techpanda.org/index.php/customer/account/login/");
        webDriver.findElement(By.xpath("//span[text()='Create an Account']")).click();
        Assert.assertEquals(webDriver.getCurrentUrl(),"https://live.techpanda.org/index.php/customer/account/create/");
    }

    @Test
    public void TC_02_verifyTitle(){
        webDriver.get("https://live.techpanda.org/");
        webDriver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']")).click();
        Assert.assertEquals( webDriver.getTitle(),"Customer Login");
        webDriver.findElement(By.xpath("//span[text()='Create an Account']")).click();
        Assert.assertEquals(webDriver.getTitle(),"Create New Customer Account");
    }

    @Test
    public void TC_03_navigationFunction(){
        webDriver.get("https://live.techpanda.org/");
        webDriver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']")).click();
        webDriver.findElement(By.xpath("//span[text()='Create an Account']")).click();
        Assert.assertEquals(webDriver.getCurrentUrl(),"https://live.techpanda.org/index.php/customer/account/create/");
        webDriver.navigate().back();
        Assert.assertEquals( webDriver.getCurrentUrl(),"https://live.techpanda.org/index.php/customer/account/login/");
        webDriver.navigate().forward();
        Assert.assertEquals(webDriver.getTitle(),"Create New Customer Account");
    }

    @Test
    public void TC_04_getPageSource(){
        webDriver.get("https://live.techpanda.org/");
        webDriver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']")).click();
        Assert.assertTrue(webDriver.getPageSource().contains("Login or Create an Account"));
        webDriver.findElement(By.xpath("//span[text()='Create an Account']")).click();
        Assert.assertTrue(webDriver.getPageSource().contains("Create an Account"));
    }

    @AfterClass
    public void afterClass(){

        webDriver.quit();
    }
}
