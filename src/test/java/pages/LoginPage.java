package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    private final String EMAIL_FLD="login-email";
    private final String PASSWORD_FLD="password";
    private final String LOGIN_BTN=".login-form .btn";
    private final String PROFILE_ICON=".user-box";

    public WebElement setEmailFld(){
        return driver.findElement(By.id(EMAIL_FLD));
    }

    public void setPasswordFld(String password){
        driver.findElement(By.name(PASSWORD_FLD)).sendKeys(password);
    }

    public void clickLoginBtn(){
        driver.findElement(By.cssSelector(LOGIN_BTN)).click();
    }

    public boolean profileIconIsExist(){
       return driver.findElements(By.cssSelector(PROFILE_ICON)).size()==1? true: false;
    }

}
