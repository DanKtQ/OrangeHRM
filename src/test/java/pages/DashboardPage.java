package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {

    WebDriver driver;

    @FindBy(xpath = "//span[contains(@class, 'oxd-main-menu-item--name') and text()='PIM']")
    private WebElement clickPimPage;

    //method to retrieve the page url
    public String getPageUrl() {

        return driver.getCurrentUrl();
    }

    //Constructor
    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //method for switching to PIM page
    public void switchToPimPage() {
        clickPimPage.click();
    }
}
