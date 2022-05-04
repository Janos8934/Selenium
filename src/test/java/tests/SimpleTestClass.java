package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.html5.WebStorage;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class SimpleTestClass {

    WebDriver driver;

    @BeforeSuite
    public void beforeSuite() throws InterruptedException {
        System.out.println("beforeSuite");
        //System.setProperty("webdriver.chrome.driver", "./WebDriver/chromedriver");
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        //options.addArguments("--headless");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1920,1080));
        //driver.manage().window().maximize();
        //driver.manage().window().fullscreen();
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("beforeTest");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("beforeClass");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("beforeMethod");
        driver.get("http://testerhub-demo-app-staging.uat.ding.hu");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("afterSuite");
        driver.close();
        driver.quit();
    }

    @AfterTest
    public void afterTest(){
        System.out.println("afterTest");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("afterClass");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("afterMethod");
        driver.manage().deleteAllCookies();
        WebStorage webStorage = (WebStorage) driver;
        LocalStorage localStorage = webStorage.getLocalStorage();
        SessionStorage sessionStorage = webStorage.getSessionStorage();
        localStorage.clear();
        sessionStorage.clear();
    }

    @Test
    public void test1(){
       /* System.out.println("test1");
        boolean a = true;
        boolean b = false;
        Assert.assertTrue(a);
        Assert.assertFalse(b);
        Assert.assertEquals("alma","alma");*/
        driver.get("http://testerhub-demo-app-staging.uat.ding.hu/home/login");
        driver.findElement(By.id("login-email")).sendKeys("szabo.janos@testerlab.io");
        driver.findElement(By.name("password")).sendKeys("testerlab");
        driver.findElement(By.cssSelector(".content-update-box .btn")).click();
        String welcomeMessage = driver.findElement(By.cssSelector(".toast-message")).getText();
        Assert.assertEquals(welcomeMessage, "Welcome Janos Szabo");
        int a = driver.findElements(By.cssSelector(".user-box")).size();
        Assert.assertEquals(a, 1, "Login failed");
    }

    @Test
    public void test2(){
        System.out.println("test2");
        String a = "alma";
        Assert.assertEquals("szilva", "alma", "Test failed");
        System.out.println("End of test2");
    }

    @Test
    public void test3(){
        System.out.println("test3");
        String a ="alma";
        if(a.equals("alma")){
            throw new SkipException("Skipped");
        }
    }

    @Test
    public void test4(){
        SoftAssert soft = new SoftAssert();
        soft.assertEquals("xyz", "abc");
        soft.assertEquals("szilva", "alma");
        soft.assertAll();
    }

}
