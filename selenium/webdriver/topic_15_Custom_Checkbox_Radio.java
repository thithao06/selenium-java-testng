package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class topic_15_Custom_Checkbox_Radio {
    WebDriver webDriver;

    @BeforeClass
    public void beforeClass(){
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
    }

    @Test
    public void TC_01_Ubuntu(){
        webDriver.get("https://login.ubuntu.com/");
//        webDriver.findElement(By.cssSelector("label.new-user")).click();
        By radioElement = By.cssSelector("input#id_new_user");
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", webDriver.findElement(radioElement));
        Assert.assertTrue(webDriver.findElement(radioElement).isSelected());

        By chkElement = By.xpath("//input[@type='checkbox']");
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", webDriver.findElement(chkElement));
        Assert.assertTrue(webDriver.findElement(chkElement).isSelected());
    }

    @Test
    public void TC_02_GoogleForm() throws InterruptedException {
        webDriver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
        Thread.sleep(3000);

        By canThoRadio = By.xpath("//div[@aria-label='Cần Thơ']");
//        Assert.assertTrue(webDriver.findElement(By.xpath("//div[(@aria-label='Cần Thơ')and(@aria-checked='false')]")).isDisplayed());
        webDriver.findElement(canThoRadio).click();
        Assert.assertEquals(webDriver.findElement(canThoRadio).getAttribute("aria-checked"),"true");

        // Select all checkboxes
        List<WebElement> allChks = webDriver.findElements(By.cssSelector("div[role='checkbox']"));
        for (WebElement item : allChks){
            if (!item.getAttribute("aria-checked").equals("true")){
                item.click();
            }
        }

        for (WebElement item : allChks){
            Assert.assertEquals(item.getAttribute("aria-checked"),"true");
        }
    }

    @AfterClass
    public void afterClass(){
        webDriver.quit();
    }
}
