package tests;

import org.junit.jupiter.api.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.PimPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PimTest extends TestBase {
    @Test
    public void accessPimPage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("Admin", "admin123");

        DashboardPage dashboardPage = new DashboardPage(driver);
        assertEquals("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index", dashboardPage.getPageUrl());
        dashboardPage.switchToPimPage();
        assertEquals("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList", dashboardPage.getPageUrl());

        PimPage pimPage = new PimPage(driver);
        pimPage.switchToAddEmployee();
        assertEquals("https://opensource-demo.orangehrmlive.com/web/index.php/pim/addEmployee", dashboardPage.getPageUrl());

    }
}
