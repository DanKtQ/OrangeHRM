package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JobPage {

    WebDriver driver;

    @FindBy(xpath = "//div[contains(@class, 'oxd-input-group')][.//label[text()='Sub Unit']]//div[contains(@class, 'oxd-select-wrapper')]")
    private WebElement clickSubUnitField;

    @FindBy(xpath = "//button[@type='submit' and @class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']")
    private WebElement clickSaveButton;

    //Constructor
    public JobPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //method for pressing on the SubUnit dropdown button
    public void clickOnSubUnitField() {
        clickSubUnitField.click();
    }

    //method for pressing Save button
    public void pressSaveButton() {
        clickSaveButton.click();
    }
}
