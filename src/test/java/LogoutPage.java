import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutPage {

    WebDriver driver;

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By logoutButton = By.id("logout-link");
    private final By loginButton = By.xpath("//*[@class=\"formGroup\"]//*[@onclick=\"myFunction()\"]");

    public void logout(){

        driver.findElement(logoutButton).click();
    }

    public boolean logoutChecker(){

        return driver.findElement(loginButton).isDisplayed();
    }
}
