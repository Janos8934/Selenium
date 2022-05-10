package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
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
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        //wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".content-update-box .btn")));
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

    @Test
    public void test5(){
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, "http://testerhub-demo-app-staging.uat.ding.hu/");
    }

    @Test
    public void test6(){
        driver.get("http://testerhub-demo-app-staging.uat.ding.hu/home/login");
        WebElement element = driver.findElement(By.id("login-email"));
        element.sendKeys("szabo.janos@testerlab.io");
        Assert.assertEquals(element.getAttribute("value"), "szabo.janos@testerlab.io");
        element.clear();
        Assert.assertEquals(element.getAttribute("value"), "");
    }

    @Test
    public void test7(){
        driver.findElement(By.cssSelector(".btn-sign-in")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://testerhub-demo-app-staging.uat.ding.hu/home/login");
        driver.navigate().back();
        Assert.assertEquals(driver.getCurrentUrl(), "http://testerhub-demo-app-staging.uat.ding.hu/");
        driver.navigate().forward();
        Assert.assertEquals(driver.getCurrentUrl(), "http://testerhub-demo-app-staging.uat.ding.hu/home/login");
        driver.navigate().refresh();
        Assert.assertEquals(driver.getCurrentUrl(), "http://testerhub-demo-app-staging.uat.ding.hu/home/login");
    }

    @Test
    public void test8(){
        String title = driver.getTitle();
        Assert.assertEquals(title, "Home | Academy");
    }

    @Test
    public void test9(){
        driver.get("https://janosszabo.hu/selenium");
        boolean button = driver.findElement(By.id("disabledButton")).isEnabled();
        Assert.assertFalse(button);
    }

    @Test
    public void test10(){
        driver.get("https://janosszabo.hu/selenium");
        boolean hideButton = driver.findElement(By.id("hiddenButton")).isDisplayed();
        Assert.assertTrue(hideButton);
        driver.findElement(By.name("actionButton")).click();
        hideButton = driver.findElement(By.id("hiddenButton")).isDisplayed();
        Assert.assertFalse(hideButton);
    }

    @Test
    public void test11() throws InterruptedException {
        driver.get("https://janosszabo.hu/selenium");
        driver.findElement(By.cssSelector(".alert")).click();
        System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
        Thread.sleep(2000);
    }

}
