package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.html5.WebStorage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class SetDriver {

    public static WebDriver driver;

    @BeforeSuite
    public void setDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1920,1080));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterSuite
    public void closeDriver(){
        driver.close();
        driver.quit();
    }

    @BeforeMethod
    public void getPage(){
        driver.get("http://testerhub-demo-app-staging.uat.ding.hu/");
    }

    @AfterMethod
    public void clearStorage(){
        driver.manage().deleteAllCookies();
        WebStorage webStorage = (WebStorage) driver;
        LocalStorage localStorage = webStorage.getLocalStorage();
        SessionStorage sessionStorage = webStorage.getSessionStorage();
        localStorage.clear();
        sessionStorage.clear();
    }

}
