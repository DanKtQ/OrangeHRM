package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PimPage {

    WebDriver driver;

    @FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--secondary']")
    private WebElement clickAddNewEmployee;

    @FindBy(xpath = "//div[contains(@class, 'oxd-input-group')][.//label[text()='Employee Id']]//input[contains(@class, 'oxd-input--active')]")
    private WebElement searchForEmployeeId;

    @FindBy(xpath = "//button[@type='submit' and contains(@class, 'oxd-button') and contains(@class, 'oxd-button--secondary') and text()=' Search ']")
    private WebElement clickSearchButton;

    @FindBy(xpath = "(//div[@class='oxd-table-cell oxd-padding-cell' and @role='cell'])[1]")
    private WebElement employeeSearchResultCheckbox;

    @FindBy(xpath = "(//div[@class='oxd-table-cell oxd-padding-cell' and @role='cell'])[2]")
    private WebElement employeeIdCellResult;

    @FindBy(xpath = "(//div[@class='oxd-table-cell oxd-padding-cell' and @role='cell'])[3]")
    private WebElement firstAndMiddleNameCell;

    @FindBy(xpath = "(//div[@class='oxd-table-cell oxd-padding-cell' and @role='cell'])[7]")
    private WebElement subUnitCell;

    @FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-horizontal-margin']")
    private WebElement deleteSelectedButton;

    @FindBy(xpath = "//button[contains(@class, 'oxd-button') and contains(@class, 'oxd-button--label-danger') and text()[normalize-space()='Yes, Delete']]")
    private WebElement yesDeletePopupButton;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span' and text()='No Records Found']")
    private WebElement noRecordsFoundMessage;

    @FindBy(xpath = "//div[contains(@class, 'oxd-input-group')][.//label[text()='Sub Unit']]//div[contains(@class, 'oxd-select-wrapper')]")
    private WebElement subUnitDropDownButton;

    @FindBy(xpath = "//div[@class='orangehrm-container']")
    private WebElement recordsSection;

    @FindBy(xpath = "//div[contains(@class, 'oxd-input-group')][.//label[text()='Include']]//div[contains(@class, 'oxd-select-wrapper')]")
    private WebElement includeDropdownButton;

    //Constructor
    public PimPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // WebDriverWait Helpers
    public void waitForElementToBeVisible(By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForElementToBeClickable(By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForPageToLoad(String expectedUrl, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
    }

    //method for switching to Add Employee section
    public void switchToAddEmployee() {
        clickAddNewEmployee.click();
        waitForPageToLoad("https://opensource-demo.orangehrmlive.com/web/index.php/pim/addEmployee", 10);
    }

    //method to search for an employee Id
    public void setSearchForEmployeeId(String employeeId) {
        waitForElementToBeClickable(By.xpath("//button[@type='submit' and contains(@class, 'oxd-button') and contains(@class, 'oxd-button--secondary') and text()=' Search ']"), 10);
        searchForEmployeeId.sendKeys(employeeId);
    }

    //method to click search button
    public void pressSearchButton() {
        waitForElementToBeClickable(By.xpath("//button[@type='submit' and contains(@class, 'oxd-button') and contains(@class, 'oxd-button--secondary') and text()=' Search ']"), 10);
        clickSearchButton.click();
    }

    //method to retrieve the employeeId cell
    public String getEmployeeIdSearchResult() {
        waitForElementToBeVisible(By.xpath("(//div[@class='oxd-table-cell oxd-padding-cell' and @role='cell'])[2]"), 10);
        return employeeIdCellResult.getText();
    }

    //method to select the employeeId cell
    public void setEmployeeIdSearchResult() {
        employeeIdCellResult.click();
    }

    //method to retrive the employee first & middle name cell
    public String getFirstMiddleNameCell() {
        waitForElementToBeVisible(By.xpath("(//div[@class='oxd-table-cell oxd-padding-cell' and @role='cell'])[3]"), 10);
        return firstAndMiddleNameCell.getText();
    }

    //method to retrive the employee Sub Unit cell
    public String getSubUnitCell() {
        waitForElementToBeVisible(By.xpath("(//div[@class='oxd-table-cell oxd-padding-cell' and @role='cell'])[7]"), 10);
        return subUnitCell.getText();
    }

    //method for pressing on the checkbox for the employee search result
    public void pressEmployeeResultCheckbox() {
        waitForElementToBeClickable(By.xpath("(//div[@class='oxd-table-cell oxd-padding-cell' and @role='cell'])[1]"), 10);
        employeeSearchResultCheckbox.click();
    }

    //method to delete an employee
    public void deleteSelected() {
        waitForElementToBeClickable(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-horizontal-margin']"), 10);
        deleteSelectedButton.click();
    }

    //method to press Yes, Delete on the pop-up button
    public void yesDeletePopupButton() {
        waitForElementToBeClickable(By.xpath("//button[contains(@class, 'oxd-button') and contains(@class, 'oxd-button--label-danger') and text()[normalize-space()='Yes, Delete']]"), 10);
        yesDeletePopupButton.click();
    }

    //method to retrieve the message "No records Found"
    public String noRecordsFoundMessage() {
        waitForElementToBeVisible(By.xpath("//span[@class='oxd-text oxd-text--span' and text()='No Records Found']"), 10);
        return noRecordsFoundMessage.getText();
    }

    //method to click on Sub Unit dropdown button
    public void setSubUnitDropDownButton() {
        subUnitDropDownButton.click();
        // Wait for options to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'oxd-input-group')][.//label[text()='Sub Unit']]//div[contains(@class, 'oxd-select-wrapper')]")));

        // Click "Quality Assurance" from the dropdown list
        WebElement qualityAssuranceOption = driver.findElement(By.xpath("//div[contains(@class, 'oxd-input-group')][.//label[text()='Sub Unit']]//div[contains(@class, 'oxd-select-wrapper')]//span[text()='Quality Assurance']"));
        qualityAssuranceOption.click();

    }

    //method to select Include button
    public void selectIncludeButton() {
        // Wait for options to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'oxd-input-group')][.//label[text()='Include']]//div[contains(@class, 'oxd-select-wrapper')]")));
        includeDropdownButton.click();
        // Click "Current and Past Employees" from the dropdown list
        WebElement includeButtonElement = driver.findElement(By.xpath("//div[contains(@class, 'oxd-input-group')][.//label[text()='Include']]//div[contains(@class, 'oxd-select-wrapper')]//span[text()='Current and Past Employees']"));
        includeButtonElement.click();
    }
}
