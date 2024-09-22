package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_03_Relative_Location {
    WebDriver webDriver;

    @BeforeClass
    public void beforeClass(){
        webDriver = new ChromeDriver();
        webDriver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
    }

    @Test
    public void TC_01_RunOnChrome(){
        // Radio Male
        webDriver.findElement(By.xpath("//input[@id='gender-male']"));
        // Radio Female
        webDriver.findElement(By.xpath("//input[@id='gender-female']"));
        //Textbox FirstName
        webDriver.findElement(By.xpath("//input[@name='FirstName']"));
        //Textbox LastName
        webDriver.findElement(By.xpath("//input[@name='LastName']"));
        //Dropdown Day
        webDriver.findElement(By.xpath("//select[@name='DateOfBirthDay']"));
        //Dropdown Month
        webDriver.findElement(By.xpath("//select[@name='DateOfBirthMonth']"));
        //Dropdown Year
        webDriver.findElement(By.xpath("//select[@name='DateOfBirthYear']"));
        // Textbox Email
        webDriver.findElement(By.xpath("//input[@id='Email']"));
        // Textbox Company
        webDriver.findElement(By.xpath("//input[@id='Company']"));
        // Checkbox Newsletter
        webDriver.findElement(By.xpath("//input[@id='Newsletter']"));
        // Textbox Password
        webDriver.findElement(By.xpath("//input[@id='Password']"));
        // Textbox Confirm Password
        webDriver.findElement(By.xpath("//input[@id='ConfirmPassword']"));
        // Button Register
        webDriver.findElement(By.xpath("//button[@id='register-button']"));
    }

    @AfterClass
    public void afterClass(){
        webDriver.quit();
    }
}
