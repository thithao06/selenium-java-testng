package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Scanner;

public class topic_19_Shadow_DOM {
    WebDriver webDriver;

    @BeforeClass
    public void beforeClass(){
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();

    }
    @Test
    public void TC_01_GitHub(){
        webDriver.get("https://automationfc.github.io/shadow-dom/");
        String element = webDriver.findElement(By.xpath("//a[@href='scroll.html']")).getText();
        System.out.println(element);

        WebElement shadowHost= webDriver.findElement(By.cssSelector("div#shadow_host"));
        SearchContext firstShadow = shadowHost.getShadowRoot();

        System.out.println(firstShadow.findElement(By.cssSelector("span#shadow_content span")).getText());
        System.out.println(firstShadow.findElement(By.cssSelector("a[href='scroll.html']")).getText());
        firstShadow.findElement(By.cssSelector("input[type='text']")).sendKeys("Selenium");
        firstShadow.findElement(By.cssSelector("input[type='checkbox']")).click();

        WebElement nestedShadowHost = firstShadow.findElement(By.cssSelector("div#nested_shadow_host"));
        SearchContext secondShadow = nestedShadowHost.getShadowRoot();
        System.out.println(secondShadow.findElement(By.cssSelector("div#nested_shadow_content div")).getText());

    }

    @Test
    public void TC_01_Book() throws InterruptedException {
        webDriver.get("https://books-pwakit.appspot.com/");

        WebElement shadowHost = webDriver.findElement(By.cssSelector("book-app[apptitle='BOOKS']"));
        SearchContext firstShadow = shadowHost.getShadowRoot();

        firstShadow.findElement(By.cssSelector("app-header app-toolbar book-input-decorator input")).sendKeys("Harry Potter");

        WebElement shadowInput = firstShadow.findElement(By.cssSelector("app-header[effects='waterfall'] app-toolbar.toolbar-bottom book-input-decorator"));
        SearchContext secondShadow = shadowInput.getShadowRoot();
        secondShadow.findElement(By.cssSelector("div.icon")).click();

        Thread.sleep(5000);
        WebElement shadowBookExplore = firstShadow.findElement(By.cssSelector("main.main-content>book-explore"));
        SearchContext thirdShadow = shadowBookExplore.getShadowRoot();

        List<WebElement> shadowElements = thirdShadow.findElements(By.cssSelector("section ul li book-item"));
        for (WebElement item : shadowElements) {
            SearchContext fourthShadow = item.getShadowRoot();
            System.out.println(fourthShadow.findElement(By.cssSelector("a div.info h2.title")).getText());
        }
    }

    @AfterClass
    public void afterClass(){
        webDriver.quit();
    }
}
