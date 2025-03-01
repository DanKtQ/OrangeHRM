package tests;

import org.junit.jupiter.api.Test;
import pages.*;

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

        AddEmployeePage addEmployee = new AddEmployeePage(driver);
        addEmployee.fillingAs("Loki", "Njord", "Heimir", "220892");
        addEmployee.setPressSaveButton();

        EmployeePersonalDetailsPage personalDetails = new EmployeePersonalDetailsPage(driver);
        personalDetails.switchToSalarySection();

        SalaryPage salaryPage = new SalaryPage(driver);
        salaryPage.setAddSalaryComponentsButton();

        salaryPage.setSalaryComponentField("Basic Salary");
        salaryPage.setPayGradeField();
        Thread.sleep(2000);
        salaryPage.setPayFrequencyField();
        salaryPage.setCurrencyField();
        salaryPage.setAmount("20000");
        salaryPage.setSaveButton();

        personalDetails.switchToEmployeeSection();
        pimPage.setSearchForEmployeeId("220892");
        Thread.sleep(3000);
        pimPage.pressSearchButton();
        assertEquals("220892", pimPage.getEmployeeIdSearchResult());

        pimPage.setEmployeeIdSearchResult();
        personalDetails.switchToSalarySection();
        assertEquals("Basic Salary", salaryPage.getSalaryComponentCell());
        assertEquals("20000", salaryPage.getAmountCell());
        assertEquals("United States Dollar", salaryPage.getCurrencyCell());
        assertEquals("Monthly", salaryPage.getPayFrequencyCell());

        Thread.sleep(2000);

        personalDetails.switchToEmployeeSection();
        pimPage.setSearchForEmployeeId("220892");
        Thread.sleep(3000);
        pimPage.pressSearchButton();

        assertEquals("220892", pimPage.getEmployeeIdSearchResult());

        pimPage.pressEmployeeResultCheckbox();
        pimPage.deleteSelected();
        pimPage.yesDeletePopupButton();

        assertEquals("No Records Found", pimPage.noRecordsFoundMessage());
    }
}
