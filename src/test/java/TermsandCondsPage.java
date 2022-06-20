import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TermsandCondsPage {

    WebDriver driver;

    public TermsandCondsPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By acceptButton = By.id("terms-and-conditions-button");

    private final By xButton = By.xpath("//*[@class=\"CloseIcon\"]");

    private final By popupWindow = By.xpath("//*[@class=\"popup\"]");

    private String url = "https://lennertamas.github.io/portio/";

    public void navigate(){

        driver.get(url);

    }

    public void clickOnAccept(){

        driver.findElement(acceptButton).click();
    }

    public void clickOnX(){

        driver.findElement(xButton).click();
    }

    public boolean popup(){

        return driver.findElement(popupWindow).isDisplayed();

    }




}
