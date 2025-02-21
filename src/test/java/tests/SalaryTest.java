package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SalaryTest extends TestBase {

    // Create a new employee. Assign a salary component. Go back to Employee List. Search for the newly created employee. Verify the salary component.
    // Delete the newly created employee.
    @Test
    public void assignSalaryComponent() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("Admin", "admin123");

        DashboardPage dashboardPage = new DashboardPage(driver);
        assertEquals("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index", dashboardPage.getPageUrl());
        dashboardPage.switchToPimPage();
        assertEquals("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList", dashboardPage.getPageUrl());

        PimPage pimPage = new PimPage(driver);
        pimPage.switchToAddEmployee();
        waitForPageToLoad("https://opensource-demo.orangehrmlive.com/web/index.php/pim/addEmployee", 10);

        AddEmployeePage addEmployee = new AddEmployeePage(driver);
        addEmployee.fillingAs("Loki", "Njord", "Heimir", "220892");
        waitForElementToBeClickable(By.xpath("//button[contains(@class,'orangehrm-left-space') and text()=' Save ']"), 10);
        addEmployee.setPressSaveButton();

        EmployeePersonalDetailsPage personalDetails = new EmployeePersonalDetailsPage(driver);
        personalDetails.switchToSalarySection();

        waitForElementToBeVisible(By.xpath("//div[@class='orangehrm-edit-employee-content']"), 10);

        SalaryPage salaryPage = new SalaryPage(driver);
        salaryPage.setAddSalaryComponentsButton();

        waitForElementToBeVisible(By.xpath("//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']"), 10);

        salaryPage.setSalaryComponentField("Basic Salary");

        salaryPage.setPayGradeField();
        // Wait for options to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Pay Grade']/following::div[contains(@class, 'oxd-select-wrapper')][1]")));
        // Click "Grade 5" from the dropdown list
        WebElement grade5 = driver.findElement(By.xpath("//label[text()='Pay Grade']/following::div[contains(@class, 'oxd-select-wrapper')][1]//span[text()='Grade 5']"));
        grade5.click();

        Thread.sleep(2000);

        salaryPage.setPayFrequencyField();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Pay Frequency']/following::div[contains(@class, 'oxd-select-wrapper')][1]")));
        // Click "Monthly" from the dropdown list
        WebElement monthly = driver.findElement(By.xpath("//label[text()='Pay Frequency']/following::div[contains(@class, 'oxd-select-wrapper')][1]//span[text()='Monthly']"));
        monthly.click();

        salaryPage.setCurrencyField();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Currency']/following::div[contains(@class, 'oxd-select-wrapper')][1]")));
        // Click "United States Dollar" from the dropdown list
        WebElement freqeuency = driver.findElement(By.xpath("//label[text()='Currency']/following::div[contains(@class, 'oxd-select-wrapper')][1]//span[text()='United States Dollar']"));
        freqeuency.click();

        salaryPage.setAmount("20000");

        salaryPage.setSaveButton();
        waitForElementToBeVisible(By.xpath("//span[@class='oxd-text oxd-text--span' and text()='(1) Record Found']"), 10);

        personalDetails.switchToEmployeeSection();
        waitForPageToLoad("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList", 10);

        pimPage.setSearchForEmployeeId("220892");
        waitForElementToBeClickable(By.xpath("//button[@type='submit' and contains(@class, 'oxd-button') and contains(@class, 'oxd-button--secondary') and text()=' Search ']"), 10);
        Thread.sleep(3000);
        pimPage.pressSearchButton();

        waitForElementToBeVisible(By.xpath("//div[@class='oxd-table-cell oxd-padding-cell' and @role='cell']/div[text()='220892']"), 10);
        assertEquals("220892", pimPage.getEmployeeIdSearchResult());

        pimPage.setEmployeeIdSearchResult();

        personalDetails.switchToSalarySection();
        waitForElementToBeVisible(By.xpath("//div[@class='orangehrm-edit-employee-content']"), 10);
        assertEquals("Basic Salary", salaryPage.getSalaryComponentCell());
        assertEquals("20000", salaryPage.getAmountCell());
        assertEquals("United States Dollar", salaryPage.getCurrencyCell());
        assertEquals("Monthly", salaryPage.getPayFrequencyCell());

        Thread.sleep(2000);

        personalDetails.switchToEmployeeSection();
        waitForPageToLoad("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList", 10);

        pimPage.setSearchForEmployeeId("220892");
        waitForElementToBeClickable(By.xpath("//button[@type='submit' and contains(@class, 'oxd-button') and contains(@class, 'oxd-button--secondary') and text()=' Search ']"), 10);
        Thread.sleep(3000);
        pimPage.pressSearchButton();

        waitForElementToBeVisible(By.xpath("//div[@class='oxd-table-cell oxd-padding-cell' and @role='cell']/div[text()='220892']"), 10);
        assertEquals("220892", pimPage.getEmployeeIdSearchResult());

        waitForElementToBeClickable(By.xpath("(//div[@class='oxd-table-cell oxd-padding-cell' and @role='cell'])[1]"), 10);
        pimPage.pressEmployeeResultCheckbox();

        waitForElementToBeClickable(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-horizontal-margin']"), 10);
        pimPage.deleteSelected();

        waitForElementToBeClickable(By.xpath("//button[contains(@class, 'oxd-button') and contains(@class, 'oxd-button--label-danger') and text()[normalize-space()='Yes, Delete']]"), 10);
        pimPage.yesDeletePopupButton();

        waitForElementToBeVisible(By.xpath("//span[@class='oxd-text oxd-text--span' and text()='No Records Found']"), 10);
        assertEquals("No Records Found", pimPage.noRecordsFoundMessage());
    }
}
