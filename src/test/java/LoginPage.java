import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By loginPopupWindow = By.xpath("//*[@id=\"login\"]");
    private final By loginTab = By.xpath("//*[@id=\"register\"]//button[@onclick=\"showLogin()\"]");
    private final By usernameField = By.id("email");
    private final By passwordField = By.id("password");
    private final By loginButton = By.xpath("//*[@class=\"formGroup\"]//*[@onclick=\"myFunction()\"]");
    private final By landingPageLink = By.xpath("//a[@href=\"landing.html\"]");

    public void loginMe(){

        driver.findElement(loginTab).click();
        driver.findElement(usernameField).sendKeys("Magyar Gergő");
        driver.findElement(passwordField).sendKeys("123456");
        driver.findElement(loginButton).click();


    }

    public  boolean loginCheck(){
        return driver.findElement(landingPageLink).isDisplayed();
    }

}
