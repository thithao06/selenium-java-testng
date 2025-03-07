package webdriver;

import graphql.schema.impl.StronglyConnectedComponentsTopologicallySorted;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class topic_11_Default_Dropdown {
    WebDriver webDriver;

    @BeforeClass
    public void beforeClass(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-data-dir=C:/Users/thao.tran/AppData/Local/Google/Chrome/User Data/");
        options.addArguments("--profile-directory=Profile 10");
        options.addArguments("--disable-geolocation");
        webDriver = new ChromeDriver(options);
        webDriver.manage().window().maximize();
    }

    @Test
    public void TC_01_Nopcommerce() throws InterruptedException {
        String firstName = "Tracy";
        String lastName = "Holf";
        String day = "6";
        String month = "December";
        String year = "1997";
        String email = "tracyholf" + new Random().nextInt(9999) + "@gmail.com";
        String companyName = "Amazon";
        String password = "12345678";

        webDriver.get("https://demo.nopcommerce.com/register");
        webDriver.findElement(By.cssSelector("span.female input")).click();
        webDriver.findElement(By.cssSelector("input#FirstName")).sendKeys(firstName);
        webDriver.findElement(By.cssSelector("input#LastName")).sendKeys(lastName);
        new Select(webDriver.findElement(By.xpath("//select[@name='DateOfBirthDay']"))).selectByVisibleText(day);
        new Select(webDriver.findElement(By.xpath("//select[@name='DateOfBirthMonth']"))).selectByVisibleText(month);
        new Select(webDriver.findElement(By.xpath("//select[@name='DateOfBirthYear']"))).selectByVisibleText(year);
        webDriver.findElement(By.cssSelector("input#Email")).sendKeys(email);
        webDriver.findElement(By.cssSelector("input#Company")).sendKeys(companyName);
        webDriver.findElement(By.cssSelector("input#Password")).sendKeys(password);
        webDriver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys(password);
        webDriver.findElement(By.xpath("//button[@id='register-button']")).click();

        Thread.sleep(2000);
        Assert.assertEquals(webDriver.findElement(By.cssSelector("div.result")).getText(),"Your registration completed");
        webDriver.findElement(By.xpath("//a[@class='ico-account']")).click();
        Assert.assertTrue(webDriver.findElement(By.cssSelector("span.female input")).isSelected());
        Assert.assertEquals(webDriver.findElement(By.cssSelector("input#FirstName")).getAttribute("value"),firstName);
        Assert.assertEquals(webDriver.findElement(By.cssSelector("input#LastName")).getAttribute("value"),lastName);
        Assert.assertEquals(new Select(webDriver.findElement(By.xpath("//select[@name='DateOfBirthDay']"))).getFirstSelectedOption().getText(),day);
        Assert.assertEquals(new Select(webDriver.findElement(By.xpath("//select[@name='DateOfBirthMonth']"))).getFirstSelectedOption().getText(),month);
        Assert.assertEquals(new Select(webDriver.findElement(By.xpath("//select[@name='DateOfBirthYear']"))).getFirstSelectedOption().getText(),year);
        Assert.assertEquals(webDriver.findElement(By.cssSelector("input#Email")).getAttribute("value"),email);
        Assert.assertEquals(webDriver.findElement(By.cssSelector("input#Company")).getAttribute("value"),companyName);
    }

    @Test
    public void TC_02_Rode() throws InterruptedException {
        webDriver.get("https://rode.com/en/support/where-to-buy");
        Assert.assertFalse(new Select(webDriver.findElement(By.cssSelector("select#country"))).isMultiple());

        new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("select#country")));
        new Select(webDriver.findElement(By.cssSelector("select#country"))).selectByVisibleText("Vietnam");
        webDriver.findElement(By.cssSelector("input#map_search_query")).sendKeys("HO CHI MINH");
        webDriver.findElement(By.xpath("//button[text()='Search']")).click();
        Thread.sleep(3000);
        List<WebElement> dealers = webDriver.findElements(By.cssSelector("div.d-flex.flex-wrap h4"));
        Assert.assertEquals(dealers.size(), 16);
        for (WebElement dealer : dealers) {
            System.out.println(dealer.getText());
        }

    }

    @AfterClass
    public void afterClass(){
        webDriver.quit();
    }
}
