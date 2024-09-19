package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_02_Locator {
    WebDriver webDriver;

    @BeforeClass
    public void beforeClass(){
        webDriver = new ChromeDriver();
        webDriver.get("https://alada.vn/tai-khoan/dang-ky.html");
    }

    @Test
    // ID
    public void TC_01_ID(){
        webDriver.findElement(By.id("txtFirstname"));
        webDriver.findElement(By.id("txtEmail"));
        webDriver.findElement(By.id("txtCEmail"));
        webDriver.findElement(By.id("txtPassword"));
        webDriver.findElement(By.id("txtCPassword"));
        webDriver.findElement(By.id("txtPhone"));
        webDriver.findElement(By.id("chkRight"));
        webDriver.findElement(By.id("btndknfooter"));
    }

    @Test
    // Class Name
    public void TC_02_ClassName(){
        webDriver.findElement(By.className("field_btn"));
    }

    @Test
    // Name
    public void TC_03_Name(){
        webDriver.findElement(By.name("txtFirstname"));
        webDriver.findElement(By.name("txtEmail"));
        webDriver.findElement(By.name("txtCEmail"));
        webDriver.findElement(By.name("txtPassword"));
        webDriver.findElement(By.name("txtCPassword"));
        webDriver.findElement(By.name("txtPhone"));
        webDriver.findElement(By.name("chkRight"));
    }

    @Test
    // Link
    public void TC_04_Link(){
        webDriver.findElement(By.linkText("Giới thiệu"));
        webDriver.findElement(By.linkText("Liên hệ"));
        webDriver.findElement(By.linkText("Chính sách bảo mật"));
        webDriver.findElement(By.linkText("Điều khoản dịch vụ"));
        webDriver.findElement(By.linkText("Quy chế hoạt động"));
        webDriver.findElement(By.linkText("Hướng dẫn sử dụng"));
        webDriver.findElement(By.linkText("Câu hỏi thường gặp"));
        webDriver.findElement(By.linkText("Hướng dẫn thanh toán"));
        webDriver.findElement(By.linkText("Chính sách hoàn trả học phí"));
    }

    @Test
    // Partial Link
    public void TC_05_PartialLink(){
        webDriver.findElement(By.partialLinkText("Giới thiệu"));
        webDriver.findElement(By.partialLinkText("Liên hệ"));
        webDriver.findElement(By.partialLinkText("bảo mật"));
        webDriver.findElement(By.partialLinkText("Điều khoản"));
        webDriver.findElement(By.partialLinkText("hoạt động"));
        webDriver.findElement(By.partialLinkText("sử dụng"));
        webDriver.findElement(By.partialLinkText("Câu hỏi"));
        webDriver.findElement(By.partialLinkText("thanh toán"));
        webDriver.findElement(By.partialLinkText("hoàn trả học phí"));
    }

    @Test
    // Tag Name
    public void TC_06_TagName(){
        webDriver.findElements(By.tagName("input"));
    }

    @Test
    // CSS Selector
    public void TC_07_CSS(){
        // CSS with ID
        webDriver.findElement(By.cssSelector("#txtFirstname"));
        webDriver.findElement(By.cssSelector("#txtEmail"));
        webDriver.findElement(By.cssSelector("#txtCEmail"));
        webDriver.findElement(By.cssSelector("#txtPassword"));
        webDriver.findElement(By.cssSelector("#txtCPassword"));
        webDriver.findElement(By.cssSelector("#txtPhone"));
        webDriver.findElement(By.cssSelector("#chkRight"));
        webDriver.findElement(By.cssSelector("#btndknfooter"));

        // CSS with name
        webDriver.findElement(By.cssSelector("input[name='txtFirstname']"));
        webDriver.findElement(By.cssSelector("input[name='txtEmail']"));
        webDriver.findElement(By.cssSelector("input[name='txtCEmail']"));
        webDriver.findElement(By.cssSelector("input[name='txtPassword']"));
        webDriver.findElement(By.cssSelector("input[name='txtCPassword']"));
        webDriver.findElement(By.cssSelector("input[name='txtPhone']"));
        webDriver.findElement(By.cssSelector("input[name='chkRight']"));

        // CSS with class
        webDriver.findElement(By.cssSelector(".field_btn .btn_pink_sm"));

        // CSS with Link
        webDriver.findElement(By.cssSelector(".fsubmenu a[href='https://alada.vn/huong-dan-su-dung.html']"));
        webDriver.findElement(By.cssSelector("a[href='https://alada.vn/cau-hoi-thuong-gap.html']"));
        webDriver.findElement(By.cssSelector("a[href='https://alada.vn/huong-dan-thanh-toan.html']"));

        // CSS with Partial Link
        webDriver.findElement(By.cssSelector("a[href*='chinh-sach-hoan-tra-hoc-phi']"));
        webDriver.findElement(By.cssSelector("a[href*='dieu-khoan-dich-vu']"));
        webDriver.findElement(By.cssSelector("a[href$='/huong-dan-thanh-toan.html']"));
    }

    @Test
    // XPath
    public void TC_08_XPath(){
        // Xpath and ID
        webDriver.findElement(By.xpath("//input[@id='txtFirstname']"));
        webDriver.findElement(By.xpath("//input[@id='txtEmail']"));
        webDriver.findElement(By.xpath("//input[@id='txtCEmail']"));
        webDriver.findElement(By.xpath("//input[@id='txtPassword']"));
        webDriver.findElement(By.xpath("//input[@id='txtCPassword']"));
        webDriver.findElement(By.xpath("//input[@id='txtPhone']"));

        // Xpath and Class
        webDriver.findElement(By.xpath("//div[@class='field_btn']"));

        // Xpath and Name
        webDriver.findElement(By.xpath("//input[@name='txtFirstname']"));
        webDriver.findElement(By.xpath("//input[@name='txtEmail']"));
        webDriver.findElement(By.xpath("//input[@name='txtCEmail']"));
        webDriver.findElement(By.xpath("//input[@name='txtPassword']"));
        webDriver.findElement(By.xpath("//input[@name='txtCPassword']"));
        webDriver.findElement(By.xpath("//input[@name='txtPhone']"));

        // Xpath and Text
        webDriver.findElement(By.xpath("//button[text()='ĐĂNG KÝ NGAY']"));
        webDriver.findElement(By.xpath("//a[text()='Chính sách bảo mật']"));

        // Xpath and Link
        webDriver.findElement(By.xpath("//a[@href='https://alada.vn/cau-hoi-thuong-gap.html']"));
        webDriver.findElement(By.xpath("//a[@href='https://alada.vn/chinh-sach-hoan-tra-hoc-phi.html']"));

        // Xpath and Partial Link
        webDriver.findElement(By.xpath("//ul[@class='fmenu']//a[contains(@href,'lien-he')]"));
        webDriver.findElement(By.xpath("//div[@class='form frmRegister']//a[contains(@href,'dieu-khoan-dich-vu')]"));
    }

    @AfterClass
    public void afterClass(){
        webDriver.quit();
    }
}
