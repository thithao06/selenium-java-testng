package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_04_Register {
    WebDriver webDriver;

    @BeforeClass
    public void beforeClass(){
        webDriver = new ChromeDriver();
        webDriver.get("https://alada.vn/tai-khoan/dang-ky.html");
    }

    @Test
    public void TC_01_Register_Empty_Data(){
        webDriver.findElement(By.xpath("//button[@type='submit']")).click();
        Assert.assertEquals(webDriver.findElement(By.id("txtFirstname-error")).getText(),"Vui lòng nhập họ tên");
        Assert.assertEquals(webDriver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email");
        Assert.assertEquals(webDriver.findElement(By.id("txtCEmail-error")).getText(),"Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(webDriver.findElement(By.id("txtPassword-error")).getText(),"Vui lòng nhập mật khẩu");
        Assert.assertEquals(webDriver.findElement(By.id("txtCPassword-error")).getText(),"Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(webDriver.findElement(By.id("txtPhone-error")).getText(),"Vui lòng nhập số điện thoại.");
    }

    @Test
    public void TC_02_Register_Invalid_Email(){
        webDriver.findElement(By.id("txtFirstname")).sendKeys("Nguyen Van A");
        webDriver.findElement(By.id("txtEmail")).sendKeys("abc");
        webDriver.findElement(By.id("txtCEmail")).sendKeys("abd");
        webDriver.findElement(By.id("txtPassword")).sendKeys("12345678");
        webDriver.findElement(By.id("txtCPassword")).sendKeys("12345678");
        webDriver.findElement(By.id("txtPhone")).sendKeys("0987654321");
        webDriver.findElement(By.xpath("//button[@type='submit']")).click();
        Assert.assertEquals(webDriver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email hợp lệ");
        Assert.assertEquals(webDriver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");
    }

    @Test
    public void TC_03_Register_Incorrect_Confirm_Email(){
        webDriver.findElement(By.id("txtFirstname")).sendKeys("Nguyen Van A");
        webDriver.findElement(By.id("txtEmail")).sendKeys("nguyenvana@gmail.com");
        webDriver.findElement(By.id("txtCEmail")).sendKeys("nguyenvana@gmail.vn");
        webDriver.findElement(By.id("txtPassword")).sendKeys("12345678");
        webDriver.findElement(By.id("txtCPassword")).sendKeys("12345678");
        webDriver.findElement(By.id("txtPhone")).sendKeys("0987654321");
        webDriver.findElement(By.xpath("//button[@type='submit']")).click();
        Assert.assertEquals(webDriver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");
    }

    @Test
    public void TC_04_Register_Password_Less_Than_6_Characters(){
        webDriver.findElement(By.id("txtFirstname")).sendKeys("Nguyen Van A");
        webDriver.findElement(By.id("txtEmail")).sendKeys("nguyenvana@gmail.com");
        webDriver.findElement(By.id("txtCEmail")).sendKeys("nguyenvana@gmail.com");
        webDriver.findElement(By.id("txtPassword")).sendKeys("12345");
        webDriver.findElement(By.id("txtCPassword")).sendKeys("12345");
        webDriver.findElement(By.id("txtPhone")).sendKeys("0987654321");
        webDriver.findElement(By.xpath("//button[@type='submit']")).click();
        Assert.assertEquals(webDriver.findElement(By.id("txtPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
        Assert.assertEquals(webDriver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
    }

    @Test
    public void TC_05_Register_Incorrect_Confirm_Password(){
        webDriver.findElement(By.id("txtFirstname")).sendKeys("Nguyen Van A");
        webDriver.findElement(By.id("txtEmail")).sendKeys("nguyenvana@gmail.com");
        webDriver.findElement(By.id("txtCEmail")).sendKeys("nguyenvana@gmail.com");
        webDriver.findElement(By.id("txtPassword")).sendKeys("12345678");
        webDriver.findElement(By.id("txtCPassword")).sendKeys("12345");
        webDriver.findElement(By.id("txtPhone")).sendKeys("0987654321");
        webDriver.findElement(By.xpath("//button[@type='submit']")).click();
        Assert.assertEquals(webDriver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
    }

    @Test
    public void TC_06_Register_Incorrect_Phone_Number(){
        // Less than 10 digits
        webDriver.findElement(By.id("txtFirstname")).sendKeys("Nguyen Van A");
        webDriver.findElement(By.id("txtEmail")).sendKeys("nguyenvana@gmail.com");
        webDriver.findElement(By.id("txtCEmail")).sendKeys("nguyenvana@gmail.com");
        webDriver.findElement(By.id("txtPassword")).sendKeys("12345678");
        webDriver.findElement(By.id("txtCPassword")).sendKeys("12345678");
        webDriver.findElement(By.id("txtPhone")).sendKeys("098765432");
        webDriver.findElement(By.xpath("//button[@type='submit']")).click();
        Assert.assertEquals(webDriver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số.");

        // More than 11 digits
        webDriver.findElement(By.id("txtFirstname")).clear();
        webDriver.findElement(By.id("txtFirstname")).sendKeys("Nguyen Van A");
        webDriver.findElement(By.id("txtEmail")).clear();
        webDriver.findElement(By.id("txtEmail")).sendKeys("nguyenvana@gmail.com");
        webDriver.findElement(By.id("txtCEmail")).clear();
        webDriver.findElement(By.id("txtCEmail")).sendKeys("nguyenvana@gmail.com");
        webDriver.findElement(By.id("txtPassword")).clear();
        webDriver.findElement(By.id("txtPassword")).sendKeys("12345678");
        webDriver.findElement(By.id("txtCPassword")).clear();
        webDriver.findElement(By.id("txtCPassword")).sendKeys("12345678");
        webDriver.findElement(By.id("txtPhone")).clear();
        webDriver.findElement(By.id("txtPhone")).sendKeys("098765432112");
        webDriver.findElement(By.xpath("//button[@type='submit']")).click();
        Assert.assertEquals(webDriver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số.");

        // Not start with 0
        webDriver.findElement(By.id("txtFirstname")).clear();
        webDriver.findElement(By.id("txtFirstname")).sendKeys("Nguyen Van A");
        webDriver.findElement(By.id("txtEmail")).clear();
        webDriver.findElement(By.id("txtEmail")).sendKeys("nguyenvana@gmail.com");
        webDriver.findElement(By.id("txtCEmail")).clear();
        webDriver.findElement(By.id("txtCEmail")).sendKeys("nguyenvana@gmail.com");
        webDriver.findElement(By.id("txtPassword")).clear();
        webDriver.findElement(By.id("txtPassword")).sendKeys("12345678");
        webDriver.findElement(By.id("txtCPassword")).clear();
        webDriver.findElement(By.id("txtCPassword")).sendKeys("12345678");
        webDriver.findElement(By.id("txtPhone")).clear();
        webDriver.findElement(By.id("txtPhone")).sendKeys("98765432112");
        webDriver.findElement(By.xpath("//button[@type='submit']")).click();
        Assert.assertEquals(webDriver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
    }

    @AfterClass
    public void afterClass(){
        webDriver.quit();
    }
}
