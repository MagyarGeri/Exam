import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {

    WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By registerTab = By.id("register-form-button");
    private final By userNameField = By.id("register-username");
    private final By passwordField = By.id("register-password");
    private final By emailField = By.id("register-email");
    private final By descriptionField = By.id("register-description");
    private final By registerButton = By.xpath("//*[@class=\"formGroup\"]//*[@onclick=\"registerUser()\"]");
    private final By registerAlert = By.id("register-alert");

    public void  registerMe(String name, String password, String email, String description){

        driver.findElement(registerTab).click();
        driver.findElement(userNameField).sendKeys(name);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(descriptionField).sendKeys(description);
        driver.findElement(registerButton).click();
    }

    public boolean registrationCheck(){

        return driver.findElement(registerAlert).isDisplayed();
    }

    public void regTabclicker(){

        driver.findElement(registerTab).click();
    }

    public void repeatedUserReg(String name, String password, String email, String description){

        driver.findElement(userNameField).sendKeys(name);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(descriptionField).sendKeys(description);
        driver.findElement(registerButton).click();
    }
}
