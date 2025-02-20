package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddEmployeePage {

    WebDriver driver;

    @FindBy(xpath = "//input[@name='firstName']")
    private WebElement enterFirstName;

    @FindBy(xpath = "//input[@name='middleName']")
    private WebElement enterMiddleName;

    @FindBy(xpath = "//input[@name='lastName']")
    private WebElement enterLastName;

    @FindBy(xpath = "//div[contains(@class, 'oxd-input-group')][.//label[text()='Employee Id']]//input")
    private WebElement enterAnEmployeeId;

    @FindBy(xpath = "//button[contains(@class,'orangehrm-left-space') and text()=' Save ']")
    private WebElement pressSaveButton;

    //Constructor
    public AddEmployeePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //method for filling the fields
    public void fillingAs(String firstName, String middleName, String lastName, String employeeId) {
        enterFirstName.sendKeys(firstName);
        enterMiddleName.sendKeys(middleName);
        enterLastName.sendKeys(lastName);
        enterAnEmployeeId.sendKeys(Keys.CONTROL + "a"); // Select all text
        enterAnEmployeeId.sendKeys(Keys.BACK_SPACE); // Delete selected text
        enterAnEmployeeId.sendKeys(employeeId);
    }

    //method for pressing Save button
    public void setPressSaveButton() {
        pressSaveButton.click();
    }

}
