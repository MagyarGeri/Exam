import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.Arrays;

public class PortioTest extends BaseTest{


    @Test
    @Order(1)
    public void acceptTest(){
        Page page = new Page(driver);
        page.navigate();
        page.clickOnAccept();
        Assertions.assertFalse(page.popup());
    }

    @Test
    @Order(2)
    public void xTest(){
        Page page = new Page(driver);
        page.navigate();
        page.clickOnX();
        Assertions.assertFalse(page.popup());
    }

    @Test
    @Order(3)
    public void regMeTest(){

        Page page = new Page(driver);
        page.navigate();
        page.clickOnAccept();
        page.registerMe(name, password, email, description);
        Assertions.assertTrue(page.registrationCheck());

    }

    @Test
    @Order(4)
    public void loginMeTest(){
        Page page = new Page(driver);
        page.navigate();
        page.clickOnAccept();
        page.registerMe(name, password, email, description);
        Assertions.assertTrue(page.registrationCheck());
        page.loginMe(name, password);
        Assertions.assertTrue(page.loginCheck());
    }

    @Test
    @Order(5)
    public void logoutTest(){
        Page page = new Page(driver);
        page.navigate();
        page.clickOnAccept();
        page.registerMe(name, password, email, description);
        Assertions.assertTrue(page.registrationCheck());
        page.loginMe(name, password);
        Assertions.assertTrue(page.loginCheck());
        page.logout();
        Assertions.assertTrue(page.logoutChecker());
    }

    @Test
    @Order(6)
    public  void  profileNewDataTest(){
        Page page = new Page(driver);
        page.navigate();
        page.clickOnAccept();
        page.registerMe(name, password, email, description);
        page.loginMe(name, password);
        page.profileNewData(modifiedName, bio, phoneNumber);

        String expected = "Profile Edited!";
        String actual = page.profileNewDataChecker();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Order(7)
    public void deleteProfileTest(){
        Page page = new Page(driver);
        page.navigate();
        page.clickOnAccept();
        page.registerMe(name, password, email, description);
        page.loginMe(name, password);
        page.deleteProfile();
        Assertions.assertTrue(page.deleteProfileChecker());

    }

    @Test
    @Order(8)
    public void articlesListTest() throws InterruptedException {
        Page page = new Page(driver);
        page.navigate();
        page.clickOnAccept();
        page.registerMe(name, password, email, description);
        page.loginMe(name, password);
        page.articklesList();
        Assertions.assertEquals(expectedBlogTitleList, page.articleListText());
    }
}
