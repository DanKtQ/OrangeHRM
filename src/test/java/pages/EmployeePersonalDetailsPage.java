package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EmployeePersonalDetailsPage {

    WebDriver driver;

    @FindBy(xpath = "//h6[contains(@class, 'orangehrm-main-title') and text()='Personal Details']")
    private WebElement personalDetails;

    @FindBy(xpath = "//h6[@class='oxd-text oxd-text--h6 --strong']")
    private WebElement employeeName;

    @FindBy(xpath = "//input[@name='firstName']")
    private WebElement firstName;

    @FindBy(xpath = "//input[@name='middleName']")
    private WebElement middleName;

    @FindBy(xpath = "//input[@name='lastName']")
    private WebElement lastName;

    @FindBy(xpath = "//div[contains(@class, 'oxd-input-group')][.//label[text()='Employee Id']]//input")
    private WebElement employeeId;

    @FindBy(xpath = "//a[@class='oxd-topbar-body-nav-tab-item']")
    private WebElement buttonEmployeeList;

    @FindBy(xpath = "//div[@class='orangehrm-custom-fields']")
    private WebElement customFieldsSection;

    @FindBy(xpath = "//div[@role='tab' and @class='orangehrm-tabs-wrapper']/a[@class='orangehrm-tabs-item' and text()='Salary']")
    private WebElement salarySection;

    @FindBy(xpath = "//div[@role='tab' and contains(@class, 'orangehrm-tabs-wrapper')]/a[@class='orangehrm-tabs-item' and text()='Job']")
    private WebElement jobSection;

    //constructor
    public EmployeePersonalDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitForElementToBeVisible(By.xpath("//h6[contains(@class, 'orangehrm-main-title') and text()='Personal Details']"), 10);
        waitForElementToBeVisible(By.xpath("//div[@class='orangehrm-custom-fields']"), 10);
        waitForElementToBeVisible(By.xpath("//h6[@class='oxd-text oxd-text--h6 --strong']"), 10);
        waitForElementToBeVisible(By.xpath("//input[@name='firstName']"), 10);
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

    //method to retrieve the page title
    public String getPageTitle() {
        waitForElementToBeVisible(By.xpath("//h6[contains(@class, 'orangehrm-main-title') and text()='Personal Details']"), 10);
        return personalDetails.getText();
    }

    //method to retrieve the employee name
    public String getEmployeeName() {
        waitForElementToBeVisible(By.xpath("//h6[@class='oxd-text oxd-text--h6 --strong']"), 10);
        return employeeName.getText();
    }

    //method to retrieve the employee name element
    public WebElement getEmployeeNameElement() {
        return driver.findElement(By.xpath("//h6[@class='oxd-text oxd-text--h6 --strong']"));
    }

    //method to retrieve first name
    public String getFirstName() {
        waitForElementToBeVisible(By.xpath("//input[@name='firstName']"), 10);
        return firstName.getAttribute("value");
    }

    //method to retrieve middle name
    public String getMiddleName() {
        waitForElementToBeVisible(By.xpath("//input[@name='middleName']"), 10);
        return middleName.getAttribute("value");
    }

    //method to retrieve last name
    public String getLastName() {
        waitForElementToBeVisible(By.xpath("//input[@name='lastName']"), 10);
        return lastName.getAttribute("value");
    }

    //method to retrieve employee id
    public String getEmployeeId() {
        waitForElementToBeVisible(By.xpath("//div[contains(@class, 'oxd-input-group')][.//label[text()='Employee Id']]//input"), 10);
        return employeeId.getAttribute("value");
    }

    //method for switching to Employee section
    public void switchToEmployeeSection() {
        buttonEmployeeList.click();
        waitForPageToLoad("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList", 10);
        waitForElementToBeVisible(By.xpath("//div[@class='oxd-table-filter']"), 10);
    }

    //method to access Salary Section
    public void switchToSalarySection() {
        salarySection.click();
        waitForElementToBeVisible(By.xpath("//div[@class='orangehrm-edit-employee-content']"), 10);
    }

    //method to access Job Section
    public void switchToJobSection() {
        jobSection.click();
        waitForElementToBeVisible(By.xpath("//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']"), 10);
    }
}
