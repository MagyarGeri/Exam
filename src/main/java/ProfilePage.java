import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {

    WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    private final By loginPopupWindow = By.id("login");
    private final By profileButton = By.id("profile-btn");
    private final By nameModifierfield = By.id("name");
    private final By bioField = By.id("bio");
    private final By phoneNumberField = By.id("phone-number");
    private final By saveProfileButton = By.xpath("//button[@onclick=\"editUser()\"]");
    private final By editAlert = By.id("edit-alert");
    private final By deleteAccountButton = By.xpath("//button[@onclick=\"showRealDeleteAccBtn()\"]");
    private final By realDeleteAccountButton = By.id("delete-account-btn");

    public void profileNewData(String modifiedName, String bio, String phoneNumber){
        driver.findElement(profileButton).click();
        driver.findElement(nameModifierfield).sendKeys(modifiedName);
        driver.findElement(bioField).sendKeys(bio);
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
        driver.findElement(saveProfileButton).click();
    }

    public String profileNewDataChecker(){
        return driver.findElement(editAlert).getText();
    }

    public void deleteProfile(){
        driver.findElement(profileButton).click();
        driver.findElement(deleteAccountButton).click();
        driver.findElement(realDeleteAccountButton).click();

    }

    public boolean deleteProfileChecker(){

        return driver.findElement(loginPopupWindow).isDisplayed();
    }

}
