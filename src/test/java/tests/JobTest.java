package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.*;

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
        dashboardPage.switchToPimPage();

        PimPage pimPage = new PimPage(driver);
        pimPage.switchToAddEmployee();

        AddEmployeePage addEmployee = new AddEmployeePage(driver);
        addEmployee.fillingAs("Frey", "Sindri", "Alvis", "220891");
        addEmployee.setPressSaveButton();

        EmployeePersonalDetailsPage personalDetails = new EmployeePersonalDetailsPage(driver);
        personalDetails.switchToJobSection();

        assertThat(driver.getCurrentUrl(), containsString("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewJobDetails/"));

        JobPage jobPage = new JobPage(driver);
        jobPage.clickOnSubUnitField();
        jobPage.pressSaveButton();

        personalDetails.switchToEmployeeSection();
        pimPage.setSubUnitDropDownButton();

        Thread.sleep(3000);

        pimPage.pressSearchButton();
        assertEquals("Frey Sindri", pimPage.getFirstMiddleNameCell());
        assertEquals("Quality Assurance", pimPage.getSubUnitCell());

        pimPage.pressEmployeeResultCheckbox();
        pimPage.deleteSelected();
        pimPage.yesDeletePopupButton();

        Thread.sleep(2000);
    }

    // Create a new employee. Edit the Job section and select Terminate Employment. Choose termination date and termination reason.
    // Verify that the employee contract was ended with selected date. Delete the employee.
    @Test
    public void terminateEmployeeContract() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("Admin", "admin123");

        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.switchToPimPage();

        PimPage pimPage = new PimPage(driver);
        pimPage.switchToAddEmployee();

        AddEmployeePage addEmployee = new AddEmployeePage(driver);
        addEmployee.fillingAs("Lara", "Giano", "Jove", "220893");
        addEmployee.setPressSaveButton();

        EmployeePersonalDetailsPage personalDetails = new EmployeePersonalDetailsPage(driver);
        personalDetails.switchToJobSection();

        assertThat(driver.getCurrentUrl(), containsString("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewJobDetails/"));

        JobPage jobPage = new JobPage(driver);
        jobPage.pressTerminateEmployment();

        // Locate the date input field inside the dropdown
        WebElement dateInput = driver.findElement(By.xpath("(//div[@data-v-4a95a2e0 and @class='oxd-date-input'])[2]//input"));
        dateInput.sendKeys("2025-03-25");
        jobPage.selectTerminationReason();

        Thread.sleep(2000);

        jobPage.pressTerminateEmplymentSaveButton();

        assertEquals("Terminated on: 2025-25-03", jobPage.verifyTerminationDate());
        assertEquals("(Past Employee)", jobPage.pastEmployeeInformation());

        personalDetails.switchToEmployeeSection();

        pimPage.selectIncludeButton();
        pimPage.setSearchForEmployeeId("220893");
        Thread.sleep(3000);
        pimPage.pressSearchButton();

        assertEquals("220893", pimPage.getEmployeeIdSearchResult());

        pimPage.pressEmployeeResultCheckbox();
        pimPage.deleteSelected();
        pimPage.yesDeletePopupButton();

        assertEquals("No Records Found", pimPage.noRecordsFoundMessage());

    }
}
