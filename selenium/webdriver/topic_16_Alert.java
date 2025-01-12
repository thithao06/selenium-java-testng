package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v131.network.Network;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.devtools.v131.network.model.Headers;


import java.time.Duration;
import java.util.*;

public class topic_16_Alert {
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
    public void TC_01_AcceptAlert(){
        webDriver.get("https://automationfc.github.io/basic-form/index.html");
        webDriver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();

        // wait 1 alert duoc xuat hien trong HTML
        // ham nay da bao gom ham switch to alert
        Alert alert = new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(alert.getText(), "I am a JS Alert");
        alert.accept();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");
    }

    @Test
    public void TC_02_ConfirmAlert(){
        webDriver.get("https://automationfc.github.io/basic-form/index.html");
        webDriver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

        Alert alert = new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(alert.getText(), "I am a JS Confirm");
        alert.dismiss();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");
    }

    @Test
    public void TC_03_PromptAlert(){
        webDriver.get("https://automationfc.github.io/basic-form/index.html");
        webDriver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

        Alert alert = new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());

        String name = "automationfc";

        Assert.assertEquals(alert.getText(), "I am a JS prompt");
        alert.sendKeys(name);
        alert.accept();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("p#result")).getText(), "You entered: " + name);
    }

    @Test
    public void TC_04_Authen_Alert(){
        String username = "admin";
        String password = "admin";
        // Truyen truc tiep vao URL
        // webDriver.get("https://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth");

        webDriver.get("https://the-internet.herokuapp.com/");

        String authenLink = authenLinkSplit(webDriver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href")
                                            , username
                                            , password);
        webDriver.get(authenLink);

    }

    @Test
    public void TC_05_Authen_Alert_DevTool(){
        // Get DevTool object
        DevTools devTools = ((HasDevTools) webDriver).getDevTools();

        // Start new session
        devTools.createSession();

        // Enable Network domain of DevTool
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // Encode Username and Password
        Map<String, Object> headers = new HashMap<String,Object>();
        String basicAuthen = "Basic " + new String(new Base64().encode(String.format("%s:%s", "admin", "admin").getBytes()));
        headers.put("Authorizations", basicAuthen);

        // Set to header
        devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));

        webDriver.get("https://the-internet.herokuapp.com/");

    }

    public String authenLinkSplit(String authenLink, String firstText, String secondText){
        String[] partialLink = authenLink.split("//");
        return partialLink[0] + "//" + firstText + ":" + secondText + "@" + partialLink[1];
    }



    @AfterClass
    public void afterClass(){
        webDriver.quit();
    }
}
