import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class PortioTest {

    WebDriver driver;

    @BeforeEach
    public void Setup() {

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        //options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("start-maximized", "--incognito");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }




    @Test
    @Order(1)
    public void acceptTest(){

        TermsandCondsPage termsandCondsPage = new TermsandCondsPage(driver);
        termsandCondsPage.navigate();
        termsandCondsPage.clickOnAccept();
        Assertions.assertFalse(termsandCondsPage.popup());
    }

    @Test
    @Order(2)
    public void xTest(){

        TermsandCondsPage termsandCondsPage = new TermsandCondsPage(driver);
        termsandCondsPage.navigate();
        termsandCondsPage.clickOnX();
        Assertions.assertFalse(termsandCondsPage.popup());
    }

    @Test
    @Order(3)
    public void regMeTest(){

        RegistrationPage registrationPage = new RegistrationPage(driver);
        acceptTest();
        registrationPage.registerMe();
        Assertions.assertTrue(registrationPage.registrationCheck());

    }

    @Test
    @Order(4)
    public void loginMeTest(){
        RegistrationPage registrationPage = new RegistrationPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        acceptTest();
        registrationPage.registerMe();
        Assertions.assertTrue(registrationPage.registrationCheck());
        loginPage.loginMe();
        Assertions.assertTrue(loginPage.loginCheck());
    }

    @Test
    @Order(5)
    public void logoutTest(){
        LogoutPage logoutPage = new LogoutPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        LoginPage loginPage = new LoginPage(driver);


        acceptTest();
        registrationPage.registerMe();
        Assertions.assertTrue(registrationPage.registrationCheck());
        loginPage.loginMe();
        Assertions.assertTrue(loginPage.loginCheck());
        logoutPage.logout();
        Assertions.assertTrue(logoutPage.logoutChecker());
    }

    /*@AfterEach
    public void Dispose(){
        driver.quit();
    }*/




}
