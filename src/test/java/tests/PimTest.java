package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.DashboardPage;
import pages.LoginPage;
import pages.PimPage;

import java.time.Duration;

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

    //search for employees by using Sub Unit dropdown button = Quality Assurance
    @Test
    public void searchBySubUnit() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("Admin", "admin123");

        DashboardPage dashboardPage = new DashboardPage(driver);
        assertEquals("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index", dashboardPage.getPageUrl());
        dashboardPage.switchToPimPage();
        assertEquals("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList", dashboardPage.getPageUrl());

        PimPage pimPage = new PimPage(driver);
        pimPage.setSubUnitDropDownButton();

        // Wait for options to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'oxd-input-group')][.//label[text()='Sub Unit']]//div[contains(@class, 'oxd-select-wrapper')]")));

        // Click "Quality Assurance" from the dropdown list
        WebElement qualityAssuranceOption = driver.findElement(By.xpath("//div[contains(@class, 'oxd-input-group')][.//label[text()='Sub Unit']]//div[contains(@class, 'oxd-select-wrapper')]//span[text()='Quality Assurance']"));
        qualityAssuranceOption.click();

        pimPage.pressSearchButton();

        waitForElementToBeVisible(By.xpath("//div[@class='oxd-table-cell oxd-padding-cell' and @role='cell']/div[text()='0042']"), 10);
        assertEquals("Quality Assurance", pimPage.getSubUnitCell());
    }
}
