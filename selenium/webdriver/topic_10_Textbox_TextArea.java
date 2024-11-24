package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class topic_10_Textbox_TextArea {
    WebDriver webDriver;

    @BeforeClass
    public void beforeClass(){
        FirefoxDriver driver = new FirefoxDriver();
        webDriver.manage().window().maximize();
    }

    @Test
    public void TC_01_TechPanda() throws InterruptedException {
        String firstName = "Tracy";
        String lastName = "Holf";
        String fullName = firstName + " " + lastName;
        String emailAddress = "tracy.holf" + new Random().nextInt(9999) + "@gmail.vn";
        String password = "tracy!23";

        webDriver.get("http://live.techpanda.org/");
        webDriver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']")).click();
        webDriver.findElement(By.xpath("//span[text()='Create an Account']")).click();
        Thread.sleep(5000);

        webDriver.findElement(By.cssSelector("input[name='firstname']")).sendKeys(firstName);
        webDriver.findElement(By.cssSelector("input[name='lastname']")).sendKeys(lastName);
        webDriver.findElement(By.cssSelector("input#email_address")).sendKeys(emailAddress);
        webDriver.findElement(By.cssSelector("input[name='password']")).sendKeys(password);
        webDriver.findElement(By.cssSelector("input[name='confirmation']")).sendKeys(password);
        webDriver.findElement(By.xpath("//span[text()='Register']")).click();

        Thread.sleep(5000);
//        webDriver.switchTo().alert().accept();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("li.success-msg li span")).getText(),"Thank you for registering with Main Website Store.");

        String contactInformation = webDriver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(contactInformation.contains(fullName));
        Assert.assertTrue(contactInformation.contains(emailAddress));

        webDriver.findElement(By.xpath("//a[text()='Mobile']")).click();
        Thread.sleep(3000);

        webDriver.findElement(By.xpath("//a[text()='Samsung Galaxy']")).click();
        webDriver.findElement(By.xpath("//a[text()='Add Your Review']")).click();
        Thread.sleep(3000);

        webDriver.findElement(By.xpath("//input[@id='Quality 1_1']")).click();
        webDriver.findElement(By.xpath("//label[text()='Let us know your thoughts']/following-sibling::div/textarea")).sendKeys("Good application\nEasy to navigate.");
        webDriver.findElement(By.xpath("//label[text()='Summary of Your Review']/following-sibling::div/input")).sendKeys("Best Iphone");
        webDriver.findElement(By.xpath("//span[text()='Submit Review']")).click();
        Thread.sleep(3000);
        Assert.assertEquals(webDriver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),"Your review has been accepted for moderation.");
    }

    @Test
    public void TC_02_OrangeHRM() throws InterruptedException {
        webDriver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        webDriver.manage().window().maximize();
        Thread.sleep(5000);

        String firstName = "Tracy";
        String lastName = "Holf";
        String username = "Tracy Holf" + new Random().nextInt(9999);
        String password = "Tracy1!@#";
        String migrationNumber = "111-222-333-444";
        String migrationComment = "testing\nonly";


        webDriver.findElement(By.cssSelector("input[name='username']")).sendKeys("Admin");
        webDriver.findElement(By.cssSelector("input[name='password']")).sendKeys("admin123");
        webDriver.findElement(By.cssSelector("button[type='submit']")).click();
        Thread.sleep(4000);

        webDriver.findElement(By.xpath("//span[text()='PIM']")).click();
        Thread.sleep(4000);

        webDriver.findElement(By.xpath("//a[text()='Add Employee']")).click();
        Thread.sleep(2000);

        webDriver.findElement(By.cssSelector("input[name='firstName']")).sendKeys(firstName);
        webDriver.findElement(By.cssSelector("input[name='lastName']")).sendKeys(lastName);
        String employeeID = webDriver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value");

        webDriver.findElement(By.xpath("//input[@type='checkbox']/following-sibling::span")).click();
        webDriver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys(username);
        webDriver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys(password);
        webDriver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys(password);
        webDriver.findElement(By.cssSelector("button[type='submit']")).click();
        Thread.sleep(10000);

        Assert.assertEquals(webDriver.findElement(By.cssSelector("input[name='firstName']")).getAttribute("value"),firstName);
        Assert.assertEquals(webDriver.findElement(By.cssSelector("input[name='lastName']")).getAttribute("value"),lastName);
        Assert.assertEquals(webDriver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"),employeeID);

        webDriver.findElement(By.xpath("//a[text()='Immigration']")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button")).click();
        webDriver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(migrationNumber);
        webDriver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).sendKeys(migrationComment);
        webDriver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
        Thread.sleep(5000);

        webDriver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
        Thread.sleep(2000);
        Assert.assertEquals(webDriver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"),migrationNumber);
        Assert.assertEquals(webDriver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"),migrationComment);

        webDriver.findElement(By.cssSelector("span.oxd-userdropdown-tab")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//a[text()='Logout']")).click();
        Thread.sleep(3000);

        webDriver.findElement(By.cssSelector("input[name='username']")).sendKeys(username);
        webDriver.findElement(By.cssSelector("input[name='password']")).sendKeys(password);
        webDriver.findElement(By.cssSelector("button[type='submit']")).click();
        Thread.sleep(4000);

        webDriver.findElement(By.xpath("//span[text()='My Info']")).click();
        Thread.sleep(3000);
        Assert.assertEquals(webDriver.findElement(By.cssSelector("input[name='firstName']")).getAttribute("value"),firstName);
        Assert.assertEquals(webDriver.findElement(By.cssSelector("input[name='lastName']")).getAttribute("value"),lastName);
        Assert.assertEquals(webDriver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"),employeeID);
        Assert.assertFalse(webDriver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).isEnabled());

        webDriver.findElement(By.xpath("//a[text()='Immigration']")).click();
        Thread.sleep(3000);

        webDriver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
        Thread.sleep(3000);

        Assert.assertEquals(webDriver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"),migrationNumber);
        Assert.assertEquals(webDriver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"),migrationComment);
    }

    @AfterClass
    public void afterClass(){
        webDriver.quit();
    }
}
