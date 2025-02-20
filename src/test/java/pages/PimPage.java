package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PimPage {

    WebDriver driver;

    @FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--secondary']")
    private WebElement clickAddNewEmployee;

    @FindBy(xpath = "//div[contains(@class, 'oxd-input-group')][.//label[text()='Employee Id']]//input[contains(@class, 'oxd-input--active')]")
    private WebElement searchForEmployeeId;

    @FindBy(xpath = "//button[contains(@class,'orangehrm-left-space')]")
    private WebElement clickSearchButton;

    @FindBy(xpath = "//div[@class='oxd-table-cell oxd-padding-cell' and @role='cell']/div[text()='220890']")
    private WebElement employeeIdSearchResult;

    @FindBy(xpath = "//span[contains(@class, 'oxd-checkbox-input') and contains(@class, 'oxd-checkbox-input--active')]")
    private WebElement employeeSearchResultCheckbox;

    @FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-horizontal-margin']")
    private WebElement deleteSelectedButton;

    @FindBy(xpath = "//button[contains(@class, 'oxd-button') and contains(@class, 'oxd-button--label-danger') and text()[normalize-space()='Yes, Delete']]")
    private WebElement yesDeletePopupButton;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span' and text()='No Records Found']")
    private WebElement noRecordsFoundMessage;

    //Constructor
    public PimPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //method for switching to Add Employee section
    public void switchToAddEmployee() {
        clickAddNewEmployee.click();
    }

    //method to search for an employee Id
    public void setSearchForEmployeeId(String employeeId) {
        searchForEmployeeId.sendKeys(employeeId);
    }

    //method to click search button
    public void pressSearchButton() {
        clickSearchButton.click();
    }

    //method to identify the employeeId search result
    public String getEmployeeIdSearchResult() {
        return employeeIdSearchResult.getText();
    }

    //method for pressing on the checkbox for the employee search result
    public void pressEmployeeResultCheckbox() {
        employeeSearchResultCheckbox.click();
    }

    //method to delete an employee
    public void deleteSelected() {
        deleteSelectedButton.click();
    }

    //method to press Yes, Delete on the pop-up button
    public void yesDeletePopupButton() {
        yesDeletePopupButton.click();
    }

    //method to retrieve the message "No records Found"
    public String noRecordsFoundMessage() {
        return noRecordsFoundMessage.getText();
    }
}
