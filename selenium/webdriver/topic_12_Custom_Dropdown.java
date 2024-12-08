package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class topic_12_Custom_Dropdown {
    WebDriver webDriver;

    @BeforeClass
    public void beforeClass(){
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
    }

    @Test
    public void TC_01_JQuery() throws InterruptedException {
        webDriver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
        selectItemInSelectableDropdown("span#speed-button", "ul#speed-menu div", "Faster");
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(),"Faster");
        selectItemInSelectableDropdown("span#speed-button", "ul#speed-menu div", "Slow");
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(),"Slow");

        selectItemInSelectableDropdown("span#files-button", "ul#files-menu div", "Some other file with a very long option text");
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span#files-button span.ui-selectmenu-text")).getText(),"Some other file with a very long option text");

        selectItemInSelectableDropdown("span#number-button", "ul#number-menu div", "19");
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(),"19");

        selectItemInSelectableDropdown("span#salutation-button", "ul#salutation-menu div", "Mrs.");
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span#salutation-button span.ui-selectmenu-text")).getText(),"Mrs.");
    }

    @Test
    public void TC_02_ReactJS() throws InterruptedException {
        webDriver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        selectItemInSelectableDropdown("div.dropdown", "div.menu div span", "Stevie Feliciano");
        Assert.assertEquals(webDriver.findElement(By.cssSelector("div.divider.text")).getText(), "Stevie Feliciano");

        selectItemInSelectableDropdown("div.dropdown", "div.menu div span", "Justen Kitsune");
        Assert.assertEquals(webDriver.findElement(By.cssSelector("div.divider.text")).getText(), "Justen Kitsune");
    }

    @Test
    public void TC_03_VueJS() throws InterruptedException {
        webDriver.get("https://mikerodham.github.io/vue-dropdowns/");
        selectItemInSelectableDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Second Option");
        Assert.assertEquals(webDriver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");

        selectItemInSelectableDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Third Option");
        Assert.assertEquals(webDriver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");
    }

    @Test
    public void TC_04_React_Semantic() throws InterruptedException {
        webDriver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        Thread.sleep(2000);

        selectItemInEditableDropdown("div.search.selection.dropdown", "div.visible.menu.transition div",
                "Benin", "input.search");
        Assert.assertEquals(webDriver.findElement(By.cssSelector("div.divider.text")).getText(), "Benin");

        selectItemInEditableDropdown("div.search.selection.dropdown", "div.visible.menu.transition div",
                "Bangladesh", "input.search");
        Assert.assertEquals(webDriver.findElement(By.cssSelector("div.divider.text")).getText(), "Bangladesh");
    }

    @Test
    public void TC_05_Huawei() throws InterruptedException {
        webDriver.get("https://id5.cloud.huawei.com/CAS/portal/userRegister/regbyemail.html");
        Thread.sleep(2000);
        selectItemInEditableDropdown("div.hwid-countryDropDown-root div.hwid-dropMenu", "div.hwid-countryDropDown-root ul span",
                "Montenegro","div.hwid-countryDropDown-root input");
        Assert.assertEquals(webDriver.findElement(By.cssSelector("div.hwid-countryDropDown-root span.hwid-select-text")).getText(), "Montenegro");

        selectItemInEditableDropdown("div.hwid-countryDropDown-root div.hwid-dropMenu", "div.hwid-countryDropDown-root ul span",
                "Vietnam","div.hwid-countryDropDown-root input");
        Assert.assertEquals(webDriver.findElement(By.cssSelector("div.hwid-countryDropDown-root span.hwid-select-text")).getText(), "Vietnam");
    }

    private void selectItemInSelectableDropdown(String parentLocator, String childLocator, String itemValue) throws InterruptedException {
        webDriver.findElement(By.cssSelector(parentLocator)).click();
        Thread.sleep(2000);

        new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.
                presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
        List<WebElement> itemsInDropdown = webDriver.findElements(By.cssSelector(childLocator));

        for (WebElement item : itemsInDropdown){
            if (item.getText().equals(itemValue)){
                item.click();
                break;
            }
        }
    }

    private void selectItemInEditableDropdown(String parentLocator, String childLocator, String itemValue, String inputLocator) throws InterruptedException {
        webDriver.findElement(By.cssSelector(parentLocator)).click();
        Thread.sleep(1500);
        webDriver.findElement(By.cssSelector(inputLocator)).clear();
        webDriver.findElement(By.cssSelector(inputLocator)).sendKeys(itemValue);

        new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.
                presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
        List<WebElement> itemsInDropdown = webDriver.findElements(By.cssSelector(childLocator));
        for (WebElement item : itemsInDropdown){
            if(item.getText().equals(itemValue)){
                item.click();
                break;
            }
        }
    }

    @AfterClass
    public void afterClass(){
        webDriver.quit();
    }
}
