package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    @FindBy(xpath = "//input[@name='username']")
    private WebElement userNameInput;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--main orangehrm-login-button']")
    private WebElement loginButton;

    @FindBy(xpath = "//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")
    private WebElement errorMessage;

    //constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //method for login
    public void loginAs(String userName, String password) {
        userNameInput.sendKeys(userName);
        passwordInput.sendKeys(password);
        loginButton.click();
    }

    //method to retrieve the error message for invalid credentials
    public String getErrorMessage() {

        return errorMessage.getText();
    }
}
