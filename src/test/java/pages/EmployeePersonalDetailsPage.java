package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    //constructor
    public EmployeePersonalDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //method to retrieve the page title
    public String getPageTitle() {

        return personalDetails.getText();
    }

    //method to retrieve the employee name
    public String getEmployeeName() {
        return employeeName.getText();
    }

    //method to retrieve the employee name element
    public WebElement getEmployeeNameElement() {
        return driver.findElement(By.xpath("//h6[@class='oxd-text oxd-text--h6 --strong']"));
    }

    //method to retrieve first name
    public String getFirstName() {
        return firstName.getAttribute("value");
    }

    //method to retrieve middle name
    public String getMiddleName() {
        return middleName.getAttribute("value");
    }

    //method to retrieve last name
    public String getLastName() {
        return lastName.getAttribute("value");
    }

    //method to retrieve employee id
    public String getEmployeeId() {
        return employeeId.getAttribute("value");
    }

    //method for switching to Employee section
    public void switchToEmployeeSection() {
        buttonEmployeeList.click();
    }

}
