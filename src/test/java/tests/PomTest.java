package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

public class PomTest extends SetDriver{

    private LoginPage loginPage;

    @BeforeMethod
    public void createPages(){
        driver.get("http://testerhub-demo-app-staging.uat.ding.hu/home/login");
    }

    @Test
    public void logintTest(){
        loginPage = new LoginPage(driver);
        loginPage.setEmailFld().sendKeys("szabo.janos@testerlab.io");
        loginPage.setPasswordFld("testerlab");
        loginPage.clickLoginBtn();
        Assert.assertTrue(loginPage.profileIconIsExist());
    }

}
