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

    @FindBy(xpath = "//button[@title='Terminate Employment']")
    private WebElement terminateEmploymentButton;

    @FindBy(xpath = "(//div[@data-v-4a95a2e0 and @class='oxd-date-input'])[2]")
    private WebElement terminationDate;

    @FindBy(xpath = "//div[@data-v-957b4417]//label[text()='Termination Reason']/following::div[@data-v-13cf171c and contains(@class, 'oxd-select-wrapper')][1]")
    private WebElement terminationReason;

    @FindBy(xpath = "(//div[@role='document']//button[@type='submit' and contains(@class, 'oxd-button--secondary') and text()=' Save '])[1]")
    private WebElement terminateEmploymentSaveButton;

    @FindBy(xpath = "//p[@class='oxd-text oxd-text--p orangehrm-terminate-date']")
    private WebElement checkTerminationDate;

    @FindBy(xpath = "//p[@class='oxd-text oxd-text--p oxd-text--subtitle-2']")
    private WebElement pastEmployeeInfo;

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

    //method for pressing Terminate Employment button
    public void pressTerminateEmployment(){
        terminateEmploymentButton.click();
    }

    //method to select Termination Date
    public void selectTerminationDate(){
        terminationDate.click();
    }

    //method to select Termination Reason
    public void selectTerminationReason(){
        terminationReason.click();
    }

    //method for pressing Save Button
    public void pressTerminateEmplymentSaveButton(){
        terminateEmploymentSaveButton.click();
    }

    //method to verify termination date "Terminated on: 2025-25-03"
    public String verifyTerminationDate(){
        return checkTerminationDate.getText();
    }

    //method to retrive past employee information "(Past Employee)"
    public String pastEmployeeInformation(){
        return pastEmployeeInfo.getText();
    }
}
