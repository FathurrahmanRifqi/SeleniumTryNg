/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */

import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 *
 * @author Pluto 09
 */
public class AUTngTest {
    
    WebDriver driver = null;

    public AUTngTest() {
    }
    

    @BeforeTest
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--headless");
        driver = new ChromeDriver(opt);
    }

    @Test
    public void testCase1() throws Exception{
        driver.get("http://127.0.0.1/wd/hub/index.php");
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("admin123");
        driver.findElement(By.xpath("/html/body/div/form/input[3]")).click();
        
        // verif login
        String veriflogin = driver.findElement(By.xpath("/html/body/div/h2")).getText();
        if(veriflogin.equals("Home Page")){
            System.out.println("TEST CASE 1 PASS:Login success verified");
        }else{
            System.out.println("TEST CASE 1 FAIL:Login failed verified");
        }
        
        this.takeSnapShot(driver,"testcase1.png");
    }
    
    @Test
    public void testCase2() throws Exception{
        driver.get("http://127.0.0.1:80/index.php");
        driver.findElement(By.id("username")).sendKeys("asalasalan");
        driver.findElement(By.id("password")).sendKeys("passwordsalah");
        driver.findElement(By.xpath("/html/body/div/form/input[3]")).click();
        
        // verif login
        String veriflogin = driver.findElement(By.xpath("/html/body/div/div")).getText();
        if(veriflogin.equals("Wrong username or password!")){
            System.out.println("TEST CASE 2 PASS:Notif Alert success verified");
        }else{
            System.out.println("TEST CASE 2 FAIL:Notif Alert failed verified");
        }
        this.takeSnapShot(driver,"testcase2.png");
    }
    
    @Test
    public void testCase3() throws Exception{
        driver.get("http://127.0.0.1:80/AUTng");
        
        driver.findElement(By.xpath("/html/body/div/form/input[3]")).click();
        
        // verif login
        WebElement inputElement = driver.findElement(By.xpath("//*[@id=\"username\"]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;  
        boolean isRequired = (Boolean) js.executeScript("return arguments[0].required;",inputElement);
        if(isRequired )
        {
           //element is required and validation error will popup if the field is empty.
            System.out.println("TEST CASE 3 PASS:Tooltip Alert Required success verified");
        }else{
            System.out.println("TEST CASE 3 FAIL:Tooltip Alert Required failed verified");
        }
        this.takeSnapShot(driver,"testcase3.png");
    }
    
    @AfterTest
    public void tearDown(){
        driver.close();
        driver.quit();
    }
    
    public static void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception{
        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
        File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File(fileWithPath);
        FileUtils.copyFile(srcFile,DestFile);

    }
}
