package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import pages.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeePersonalDetailsTest extends TestBase {

    //Login into the site. Go to PIM section and create a new employee. Go back to Employee List and delete the newly created employee record.
    @Test
    public void checkPersonalDetailsOfANewEmployee() {
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
        addEmployee.fillingAs("Gandalf", "Jarl", "Balder", "220890");
        waitForElementToBeClickable(By.xpath("//button[contains(@class,'orangehrm-left-space') and text()=' Save ']"), 10);
        addEmployee.setPressSaveButton();

        EmployeePersonalDetailsPage personalDetails = new EmployeePersonalDetailsPage(driver);
        waitForElementToBeVisible(By.xpath("//h6[contains(@class, 'orangehrm-main-title') and text()='Personal Details']"), 10);
        assertEquals("Personal Details", personalDetails.getPageTitle());

        waitForElementToBeVisible(By.xpath("//div[@class='orangehrm-custom-fields']"), 10);

        waitForElementToBeVisible(By.xpath("//h6[@class='oxd-text oxd-text--h6 --strong']"), 10);
        assertEquals("Gandalf Balder", personalDetails.getEmployeeName());

        waitForElementToBeVisible(By.xpath("//input[@name='firstName']"), 10);
        assertEquals("Gandalf", personalDetails.getFirstName());
        assertEquals("Jarl", personalDetails.getMiddleName());
        assertEquals("Balder", personalDetails.getLastName());
        assertEquals("220890", personalDetails.getEmployeeId());

        personalDetails.switchToEmployeeSection();
        waitForPageToLoad("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList", 10);

        pimPage.setSearchForEmployeeId("220890");
        waitForElementToBeClickable(By.xpath("//button[contains(@class,'orangehrm-left-space')]"), 10);
        pimPage.pressSearchButton();

        waitForElementToBeVisible(By.xpath("//div[@class='oxd-table-cell oxd-padding-cell' and @role='cell']/div[text()='220890']"), 10);
        assertEquals("220890", pimPage.getEmployeeIdSearchResult());

        waitForElementToBeClickable(By.xpath("//span[contains(@class, 'oxd-checkbox-input') and contains(@class, 'oxd-checkbox-input--active')]"), 10);
        pimPage.pressEmployeeResultCheckbox();

        waitForElementToBeClickable(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-horizontal-margin']"), 10);
        pimPage.deleteSelected();

        waitForElementToBeClickable(By.xpath("//button[contains(@class, 'oxd-button') and contains(@class, 'oxd-button--label-danger') and text()[normalize-space()='Yes, Delete']]"), 10);
        pimPage.yesDeletePopupButton();

        waitForElementToBeVisible(By.xpath("//span[@class='oxd-text oxd-text--span' and text()='No Records Found']"), 10);
        assertEquals("No Records Found", pimPage.noRecordsFoundMessage());

    }
}
