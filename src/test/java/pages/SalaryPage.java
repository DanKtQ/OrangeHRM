package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SalaryPage {

    WebDriver driver;

    @FindBy(xpath = "//div[h6[text()='Assigned Salary Components']]//button[contains(@class, 'oxd-button') and contains(@class, 'oxd-button--text') and text()[normalize-space()='Add']]")
    private WebElement addSalaryComponentsButton;

    @FindBy(xpath = "//label[text()='Salary Component']/following::input[1]")
    private WebElement salaryComponentField;

    @FindBy(xpath = "//label[text()='Pay Grade']/following::div[contains(@class, 'oxd-select-wrapper')][1]")
    private WebElement payGradeField;

    @FindBy(xpath = "//label[text()='Pay Frequency']/following::div[contains(@class, 'oxd-select-wrapper')][1]")
    private WebElement payFrequencyField;

    @FindBy(xpath = "//label[text()='Currency']/following::div[contains(@class, 'oxd-select-wrapper')][1]")
    private WebElement currencyField;

    @FindBy(xpath = "//label[text()='Amount']/following::input[1]")
    private WebElement amountField;

    @FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']")
    private WebElement saveButton;

    @FindBy(xpath = "//div[@role='row']//div[@role='cell'][2]")
    private WebElement salaryComponentCell;

    @FindBy(xpath = "//div[@role='row']//div[@role='cell'][3]")
    private WebElement amountCell;

    @FindBy(xpath = "//div[@role='row']//div[@role='cell'][4]")
    private WebElement currencyCell;

    @FindBy(xpath = "//div[@role='row']//div[@role='cell'][5]")
    private WebElement payFrequencyCell;

    //Constructor
    public SalaryPage(WebDriver driver) {
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

    //method to click on the salary component add button
    public void setAddSalaryComponentsButton() {
        addSalaryComponentsButton.click();
        waitForElementToBeVisible(By.xpath("//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']"), 10);
    }

    //method to set a salary component
    public void setSalaryComponentField(String salaryComponent) {
        salaryComponentField.sendKeys(salaryComponent);
    }

    //method to select a pay grade
    public void setPayGradeField() {
        payGradeField.click();
        // Wait for options to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Pay Grade']/following::div[contains(@class, 'oxd-select-wrapper')][1]")));
        // Click "Grade 5" from the dropdown list
        WebElement grade5 = driver.findElement(By.xpath("//label[text()='Pay Grade']/following::div[contains(@class, 'oxd-select-wrapper')][1]//span[text()='Grade 5']"));
        grade5.click();
    }

    //method to select a pay frequency
    public void setPayFrequencyField() {
        payFrequencyField.click();
        // Wait for options to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Pay Frequency']/following::div[contains(@class, 'oxd-select-wrapper')][1]")));
        // Click "Monthly" from the dropdown list
        WebElement monthly = driver.findElement(By.xpath("//label[text()='Pay Frequency']/following::div[contains(@class, 'oxd-select-wrapper')][1]//span[text()='Monthly']"));
        monthly.click();
    }

    //method to select a currency
    public void setCurrencyField() {
        currencyField.click();
        // Wait for options to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Currency']/following::div[contains(@class, 'oxd-select-wrapper')][1]")));
        // Click "United States Dollar" from the dropdown list
        WebElement freqeuency = driver.findElement(By.xpath("//label[text()='Currency']/following::div[contains(@class, 'oxd-select-wrapper')][1]//span[text()='United States Dollar']"));
        freqeuency.click();
    }

    //method to set an amount
    public void setAmount(String amount) {
        amountField.sendKeys(amount);
    }

    //method to press save button
    public void setSaveButton() {
        saveButton.click();
        waitForElementToBeVisible(By.xpath("//span[@class='oxd-text oxd-text--span' and text()='(1) Record Found']"), 10);
    }

    //method to retrieve salaryComponentCell
    public String getSalaryComponentCell() {
        return salaryComponentCell.getText();
    }

    //method to retrieve amount cell
    public String getAmountCell() {
        return amountCell.getText();
    }

    //method to retrieve currency cell
    public String getCurrencyCell() {
        return currencyCell.getText();
    }

    //method to retrieve pay frequency cell
    public String getPayFrequencyCell() {
        return payFrequencyCell.getText();
    }
}
