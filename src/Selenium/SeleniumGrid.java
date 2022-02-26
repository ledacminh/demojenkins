package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class SeleniumGrid {
        String projectFolder = System.getProperty("user.dir");
        WebDriver driver;
        Select select;
        String firstName, lastName, email, companyName, password;

        @BeforeClass
        public void beforeClass() {
            System.setProperty("webdriver.chrome.driver", projectFolder + "\\Browser\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.get("https://demo.nopcommerce.com/");
            firstName = "Tony";
            lastName = "Buoi Sang";
            email = "tonybuoisang" + getRandomNumber() + "@hotmail.com";
            companyName = "Tony Buoi Sang";
            password = "tonybuoisang";
        }

        @Test
        public void TC_01_Register() {
            driver.findElement(By.className("ico-register")).click();
            driver.findElement(By.id("gender-male")).click();
            sleepInSecond(2);

            driver.findElement(By.id("FirstName")).sendKeys(firstName);
            driver.findElement(By.id("LastName")).sendKeys(lastName);

            select = new Select(driver.findElement(By.name("DateOfBirthDay")));
            select.selectByVisibleText("10");

            select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
            select.selectByVisibleText("August");

            select = new Select(driver.findElement(By.name("DateOfBirthYear")));
            select.selectByVisibleText("1960");

            driver.findElement(By.id("Email")).sendKeys(email);
            driver.findElement(By.id("Company")).sendKeys(companyName);
            driver.findElement(By.id("Password")).sendKeys(password);
            driver.findElement(By.id("ConfirmPassword")).sendKeys(password);

            driver.findElement(By.id("register-button")).click();
            sleepInSecond(2);

            Assert.assertEquals(driver.findElement(By.xpath("//div[@class='page registration-result-page']//div[@class='result']")).getText(), "Your registration completed");

            driver.findElement(By.className("ico-logout")).click();
            sleepInSecond(2);
        }

        @Test
        public void TC_02_Login() {
            driver.findElement(By.className("ico-login")).click();
            sleepInSecond(2);

            driver.findElement(By.id("Email")).sendKeys(email);
            driver.findElement(By.id("Password")).sendKeys(password);
            driver.findElement(By.cssSelector(".login-button")).click();
            sleepInSecond(2);

            Assert.assertTrue(driver.findElement(By.className("ico-account")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.className("ico-logout")).isDisplayed());
        }

        public int getRandomNumber() {
            Random rand = new Random();
            return rand.nextInt(999999);
        }

        public void sleepInSecond(long time) {
            try {
                Thread.sleep(time * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @AfterClass
        public void afterClass() {
            driver.quit();
        }

}
