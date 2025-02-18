package tests;

import org.junit.jupiter.api.Test;
import pages.DashboardPage;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest extends TestBase {

    @Test
    public void canLoginWithValidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("Admin", "admin123");

        DashboardPage dashboardPage = new DashboardPage(driver);
        assertEquals("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index", dashboardPage.getPageUrl());
    }

    @Test
    public void cannotLoginDueToInvalidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("Admin", "4");

        assertEquals("Invalid credentials", loginPage.getErrorMessage());
    }

}
