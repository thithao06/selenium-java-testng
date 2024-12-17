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

public class topic_14_Checkbox_Radio {
    WebDriver webDriver;

    @BeforeClass
    public void beforeClass(){
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
    }

    @Test
    public void TC_01_Checkbox_Kendo() throws InterruptedException {
        webDriver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        Thread.sleep(3000);
        WebElement element = webDriver.findElement(By.cssSelector("div#demo-runner"));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(2000);

        WebElement dualConditioning = webDriver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input"));
        if (!dualConditioning.isSelected()){
            dualConditioning.click();
        }
        Assert.assertTrue(dualConditioning.isSelected());

        if (dualConditioning.isSelected()) {
            dualConditioning.click();
        }
        Assert.assertFalse(dualConditioning.isSelected());
    }

    @Test
    public void TC_02_Radio_Kendo() throws InterruptedException {
        webDriver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        WebElement element = webDriver.findElement(By.cssSelector("div#demo-runner"));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(3000);

        WebElement petrol147 = webDriver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input"));
        if (!petrol147.isSelected()){
            petrol147.click();
        }
        Assert.assertTrue(petrol147.isSelected());
    }

    @Test
    public void TC_03_Radio_Angular() {
        webDriver.get("https://material.angular.io/components/radio/examples");
        WebElement summerRadio = webDriver.findElement(By.xpath("//input[@value='Summer']"));
        if (!summerRadio.isSelected()) {
            summerRadio.click();
        }
        Assert.assertTrue(summerRadio.isSelected());
    }
    @Test
    public void TC_04_Checkbox_Angular(){
        webDriver.get("https://material.angular.io/components/checkbox/examples");
        WebElement checkedCbx = webDriver.findElement(By.xpath("//label[text()='Checked']/preceding-sibling::div/input"));
        WebElement indeterminateCbx = webDriver.findElement(By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input"));

        // check
        if(!checkedCbx.isSelected()){
            checkedCbx.click();
        }
        Assert.assertTrue(checkedCbx.isSelected());

        if(!indeterminateCbx.isSelected()){
            indeterminateCbx.click();
        }
        Assert.assertTrue(indeterminateCbx.isSelected());

        // uncheck
        if(checkedCbx.isSelected()){
            checkedCbx.click();
        }
        Assert.assertFalse(checkedCbx.isSelected());

        if(indeterminateCbx.isSelected()){
            indeterminateCbx.click();
        }
        Assert.assertFalse(indeterminateCbx.isSelected());
    }

    @Test
    public void TC_05_Select_All_Checkboxes(){
        webDriver.get("https://automationfc.github.io/multiple-fields/");
        WebElement element = webDriver.findElement(By.xpath("//li[@data-type='control_checkbox']"));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);

        // Get list of checkboxes
        List<WebElement> allCheckbox = webDriver.findElements(By.cssSelector("span.form-checkbox-item input"));

        // Check all and verify
        for (WebElement checkboxItem : allCheckbox){
            if(!checkboxItem.isSelected()){
                checkboxItem.click();
            }
            Assert.assertTrue(checkboxItem.isSelected());
        }

        // Uncheck all and verify
        for (WebElement checkboxItem : allCheckbox){
            if(checkboxItem.isSelected()){
                checkboxItem.click();
            }
            Assert.assertFalse(checkboxItem.isSelected());
        }

        // Check with condition
        for (WebElement checkboxItem : allCheckbox){
            if (checkboxItem.getAttribute("value").equals("Heart Attack") && !checkboxItem.isSelected()){
                checkboxItem.click();
            }
        }

        // Verify
        for (WebElement checkboxItem : allCheckbox){
            if (checkboxItem.getAttribute("value").equals("Heart Attack")){
               Assert.assertTrue(checkboxItem.isSelected());
            }
        }
    }

    @AfterClass
    public void afterClass(){
        webDriver.quit();
    }
}
