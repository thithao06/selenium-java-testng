package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class topic_20_IFrame {
    WebDriver webDriver;

    @BeforeClass
    public void beforeClass(){
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        webDriver.manage().window().maximize();
    }

    @Test
    public void TC_01_ToiDiCodeDao(){
        webDriver.get("https://toidicodedao.com/");

        WebElement facebookIframe = webDriver.findElement(By.cssSelector("div#page aside#facebook-likebox-3 iframe"));

        Assert.assertTrue(facebookIframe.isDisplayed());

        webDriver.switchTo().frame(facebookIframe);

        System.out.println(webDriver.findElement(By.cssSelector("div._1drq")).getText());

    }

    @Test
    public void TC_02_Map() {
        webDriver.get("https://www.embedgooglemap.net/");
        webDriver.switchTo().frame(webDriver.findElement(By.cssSelector("div.gmap_canvas iframe")));
        String address = webDriver.findElement(By.cssSelector("div.address")).getText();
        System.out.println(address);
    }

    @Test
    public void TC_03_FormSite() {
        webDriver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
        webDriver.findElement(By.cssSelector("div#imageTemplateContainer img")).click();

        webDriver.switchTo().frame(webDriver.findElement(By.cssSelector("div#formTemplateContainer iframe")));

        new Select(webDriver.findElement(By.cssSelector("select#RESULT_RadioButton-2"))).selectByVisibleText("Sophomore");
        new Select(webDriver.findElement(By.cssSelector("select#RESULT_RadioButton-3"))).selectByVisibleText("South Dorm");
        WebElement radioElement = webDriver.findElement(By.xpath("//label[text()='Female']/preceding-sibling::input"));
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].click();", radioElement);

        webDriver.findElement(By.cssSelector("input[type='submit']")).click();

        webDriver.switchTo().defaultContent();

        WebElement dialogCloseBtn = webDriver.findElement(By.cssSelector("div[aria-label='Cookie Consent Banner'] button[class*='close']"));

        if (dialogCloseBtn.isDisplayed()){
            dialogCloseBtn.click();
        }
        webDriver.findElement(By.cssSelector("nav[class*='desktop-floater'] a[title='Log in']")).click();
        webDriver.findElement(By.cssSelector("button#login")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("div#message-error")).getText(), "Username and password are both required.");
    }

        @AfterClass
    public void afterClass(){
        webDriver.quit();
    }
}
