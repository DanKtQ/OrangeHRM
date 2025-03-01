package tests;

import org.junit.jupiter.api.Test;
import pages.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeePersonalDetailsTest extends TestBase {

    // Login into the site. Go to PIM section and create a new employee. Verify employee details.
    // Go back to Employee List, search by ID and delete the newly created employee record.
    @Test
    public void checkPersonalDetailsOfANewEmployee() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("Admin", "admin123");

        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.switchToPimPage();

        PimPage pimPage = new PimPage(driver);
        pimPage.switchToAddEmployee();

        AddEmployeePage addEmployee = new AddEmployeePage(driver);
        addEmployee.fillingAs("Gandalf", "Jarl", "Balder", "220890");
        addEmployee.setPressSaveButton();

        EmployeePersonalDetailsPage personalDetails = new EmployeePersonalDetailsPage(driver);
        assertEquals("Personal Details", personalDetails.getPageTitle());
        assertEquals("Gandalf Balder", personalDetails.getEmployeeName());
        assertEquals("Gandalf", personalDetails.getFirstName());
        assertEquals("Jarl", personalDetails.getMiddleName());
        assertEquals("Balder", personalDetails.getLastName());
        assertEquals("220890", personalDetails.getEmployeeId());

        personalDetails.switchToEmployeeSection();

        pimPage.setSearchForEmployeeId("220890");
        Thread.sleep(3000);
        pimPage.pressSearchButton();

        assertEquals("220890", pimPage.getEmployeeIdSearchResult());

        pimPage.pressEmployeeResultCheckbox();
        pimPage.deleteSelected();
        pimPage.yesDeletePopupButton();

        assertEquals("No Records Found", pimPage.noRecordsFoundMessage());

    }
}
