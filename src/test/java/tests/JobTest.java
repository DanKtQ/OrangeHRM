package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JobTest extends TestBase {

    // Create a new employee. Edit the Job section and set Sub Unit to Quality Assurance.
    // Then go to Employee List and make a search after Sub Unit dropdown button = Quality Assurance. Check that the new employee which was created is found. Delete the employee.
    @Test
    public void checkPersonalDetailsOfANewEmployee() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("Admin", "admin123");

        DashboardPage dashboardPage = new DashboardPage(driver);
        waitForPageToLoad("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index", 10);
        dashboardPage.switchToPimPage();
        waitForPageToLoad("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList", 10);

        PimPage pimPage = new PimPage(driver);
        pimPage.switchToAddEmployee();
        waitForPageToLoad("https://opensource-demo.orangehrmlive.com/web/index.php/pim/addEmployee", 10);

        AddEmployeePage addEmployee = new AddEmployeePage(driver);
        addEmployee.fillingAs("Frey", "Sindri", "Alvis", "220891");
        waitForElementToBeClickable(By.xpath("//button[contains(@class,'orangehrm-left-space') and text()=' Save ']"), 10);
        addEmployee.setPressSaveButton();

        EmployeePersonalDetailsPage personalDetails = new EmployeePersonalDetailsPage(driver);
        personalDetails.switchToJobSection();

        assertThat(driver.getCurrentUrl(), containsString("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewJobDetails/"));
        waitForElementToBeVisible(By.xpath("//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']"), 10);

        JobPage jobPage = new JobPage(driver);
        jobPage.clickOnSubUnitField();

        // Wait for options to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'oxd-input-group')][.//label[text()='Sub Unit']]//div[contains(@class, 'oxd-select-wrapper')]")));

        // Click "Quality Assurance" from the dropdown list
        WebElement qualityAssuranceOption = driver.findElement(By.xpath("//div[contains(@class, 'oxd-input-group')][.//label[text()='Sub Unit']]//div[contains(@class, 'oxd-select-wrapper')]//span[text()='Quality Assurance']"));
        qualityAssuranceOption.click();

        jobPage.pressSaveButton();

        waitForElementToBeVisible(By.xpath("//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']"), 10);

        personalDetails.switchToEmployeeSection();
        waitForPageToLoad("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList", 10);

        pimPage.setSubUnitDropDownButton();

        // Wait for options to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'oxd-input-group')][.//label[text()='Sub Unit']]//div[contains(@class, 'oxd-select-wrapper')]")));

        // Click "Quality Assurance" from the dropdown list
        WebElement qualityAssuranceOption1 = driver.findElement(By.xpath("//div[contains(@class, 'oxd-input-group')][.//label[text()='Sub Unit']]//div[contains(@class, 'oxd-select-wrapper')]//span[text()='Quality Assurance']"));
        qualityAssuranceOption1.click();

        Thread.sleep(3000);

        pimPage.pressSearchButton();

        waitForElementToBeVisible(By.xpath("//div[@role='row'][.//div[text()='Frey Sindri'] and .//div[text()='Quality Assurance']]"), 10);
        assertEquals("Frey Sindri", pimPage.getFirstMiddleNameCell());
        assertEquals("Quality Assurance", pimPage.getSubUnitCell());

        waitForElementToBeClickable(By.xpath("(//div[@class='oxd-table-cell oxd-padding-cell' and @role='cell'])[1]"), 10);
        pimPage.pressEmployeeResultCheckbox();

        waitForElementToBeClickable(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-horizontal-margin']"), 10);
        pimPage.deleteSelected();

        waitForElementToBeClickable(By.xpath("//button[contains(@class, 'oxd-button') and contains(@class, 'oxd-button--label-danger') and text()[normalize-space()='Yes, Delete']]"), 10);
        pimPage.yesDeletePopupButton();

        Thread.sleep(3000);
    }

    // Create a new employee. Edit the Job section and select Terminate Employment. Choose termination date and termination reason.
    // Verify that the employee contract was ended with selected date. Delete the employee.
    @Test
    public void terminateEmployeeContract() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("Admin", "admin123");

        DashboardPage dashboardPage = new DashboardPage(driver);
        waitForPageToLoad("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index", 10);
        dashboardPage.switchToPimPage();
        waitForPageToLoad("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList", 10);

        PimPage pimPage = new PimPage(driver);
        pimPage.switchToAddEmployee();
        waitForPageToLoad("https://opensource-demo.orangehrmlive.com/web/index.php/pim/addEmployee", 10);

        AddEmployeePage addEmployee = new AddEmployeePage(driver);
        addEmployee.fillingAs("Lara", "Giano", "Jove", "220893");
        waitForElementToBeClickable(By.xpath("//button[contains(@class,'orangehrm-left-space') and text()=' Save ']"), 10);
        addEmployee.setPressSaveButton();

        EmployeePersonalDetailsPage personalDetails = new EmployeePersonalDetailsPage(driver);
        personalDetails.switchToJobSection();

        assertThat(driver.getCurrentUrl(), containsString("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewJobDetails/"));
        waitForElementToBeVisible(By.xpath("//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']"), 10);

        JobPage jobPage = new JobPage(driver);
        jobPage.pressTerminateEmployment();

        waitForElementToBeVisible(By.xpath("//div[@class='oxd-sheet oxd-sheet--rounded oxd-sheet--white oxd-dialog-sheet oxd-dialog-sheet--shadow oxd-dialog-sheet--gutters orangehrm-dialog-modal']"), 10);

        // Locate the date input field inside the dropdown
        WebElement dateInput = driver.findElement(By.xpath("(//div[@data-v-4a95a2e0 and @class='oxd-date-input'])[2]//input"));
        dateInput.sendKeys("2025-03-25");

        jobPage.selectTerminationReason();
        // Wait for options to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-v-957b4417]//label[text()='Termination Reason']/following::div[@data-v-13cf171c and contains(@class, 'oxd-select-wrapper')][1]")));

        // Click "Resigned - Self Proposed" from the dropdown list
        WebElement resignSelfProposedOption = driver.findElement(By.xpath("//div[@data-v-957b4417]//label[text()='Termination Reason']/following::div[@data-v-13cf171c and contains(@class, 'oxd-select-wrapper')][1]//span[text()='Resigned - Self Proposed']"));
        resignSelfProposedOption.click();
        Thread.sleep(2000);

        jobPage.pressTerminateEmplymentSaveButton();
        waitForElementToBeVisible(By.xpath("//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']"), 10);

        waitForElementToBeVisible(By.xpath("//p[@class='oxd-text oxd-text--p orangehrm-terminate-date']"), 10);
        assertEquals("Terminated on: 2025-25-03", jobPage.verifyTerminationDate());
        assertEquals("(Past Employee)", jobPage.pastEmployeeInformation());

        personalDetails.switchToEmployeeSection();
        waitForPageToLoad("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList", 10);
        waitForElementToBeVisible(By.xpath("//div[@class='oxd-table-filter']"), 10);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'oxd-input-group')][.//label[text()='Include']]//div[contains(@class, 'oxd-select-wrapper')]")));
        // Click "Current and Past Employees" from the dropdown list
        pimPage.selectIncludeButton();
        WebElement includeButtonElement = driver.findElement(By.xpath("//div[contains(@class, 'oxd-input-group')][.//label[text()='Include']]//div[contains(@class, 'oxd-select-wrapper')]//span[text()='Current and Past Employees']"));
        includeButtonElement.click();

        pimPage.setSearchForEmployeeId("220893");
        waitForElementToBeClickable(By.xpath("//button[@type='submit' and contains(@class, 'oxd-button') and contains(@class, 'oxd-button--secondary') and text()=' Search ']"), 10);
        Thread.sleep(3000);
        pimPage.pressSearchButton();

        waitForElementToBeVisible(By.xpath("//div[@class='oxd-table-cell oxd-padding-cell' and @role='cell']/div[text()='220893']"), 10);
        assertEquals("220893", pimPage.getEmployeeIdSearchResult());

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
