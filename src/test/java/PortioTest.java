import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.*;
import java.io.*;

public class PortioTest extends BaseTest{

    @Test
    @Order(1)
    @Severity(SeverityLevel.NORMAL)
    public void acceptTest(){
        Page page = new Page(driver);
        page.navigate();
        page.clickOnAccept();
        Assertions.assertFalse(page.popup());
    }

    @Test
    @Order(2)
    @Severity(SeverityLevel.NORMAL)
    public void xTest(){
        Page page = new Page(driver);
        page.navigate();
        page.clickOnX();
        Assertions.assertFalse(page.popup());
    }

    @Test
    @Order(3)
    @Severity(SeverityLevel.CRITICAL)
    public void regMeTest(){

        Page page = new Page(driver);
        page.navigate();
        page.clickOnAccept();
        page.registerMe(name, password, email, description);
        Assertions.assertTrue(page.registrationCheck());

    }

    @Test
    @Order(4)
    @Severity(SeverityLevel.CRITICAL)
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
    @Severity(SeverityLevel.CRITICAL)
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
    @Severity(SeverityLevel.MINOR)
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
    @Severity(SeverityLevel.NORMAL)
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
    @Severity(SeverityLevel.MINOR)
    public void articlesListTest() throws InterruptedException {
        Page page = new Page(driver);
        page.navigate();
        page.clickOnAccept();
        page.registerMe(name, password, email, description);
        page.loginMe(name, password);
        page.articklesList();
        Assertions.assertEquals(expectedBlogTitleList, page.articleListText());
    }

    @Test
    @Order(9)
    @Severity(SeverityLevel.MINOR)
    public void saveBlogTextTest() throws InterruptedException {
        Page page = new Page(driver);
        page.navigate();
        page.clickOnAccept();
        page.registerMe(name, password, email, description);
        page.loginMe(name, password);
        page.articklesList();
        String res = page.saveBlogText();
        page.write(res, "saved.txt");
        String actual = page.reader("saved.txt");
        String expected = page.reader("expectedData.txt");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Order(10)
    @Severity(SeverityLevel.CRITICAL)
    public void repeatedUserRegTest() throws IOException {
        Page page = new Page(driver);
        page.navigate();
        page.clickOnAccept();
        page.regTabclicker();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("usersToRegister.txt"));

            String line = reader.readLine();
            while (line != null) {
                String[] arr = line.split(";");
                page.repeatedUserReg(arr[0],arr[1],arr[2],arr[3]);
                boolean result = page.registrationCheck();
                Assertions.assertTrue(result);
                line = reader.readLine();
                page.navigate();
                page.regTabclicker();
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

