import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.*;
import java.io.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PortioTest extends BaseTest{

    @Test
    @Order(1)
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Adatkezelési nyilatkozat használata")
    @Description("Adatkezelési nyilatkozat elfogadása")
    public void acceptTest(){

        TermsAndConditionsPage tc = new TermsAndConditionsPage(driver);
        tc.navigate();
        tc.clickOnAccept();
        Assertions.assertFalse(tc.popup());
    }

    @Test
    @Order(2)
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Adatkezelési nyilatkozat használata")
    @Description("Adatkezelési nyilatkozat elutasítása")
    public void xTest(){

        TermsAndConditionsPage tc = new TermsAndConditionsPage(driver);
        tc.navigate();
        tc.clickOnX();
        Assertions.assertFalse(tc.popup());
    }

    @Test
    @Order(3)
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Regisztráció")
    @Description("Felhasználó regisztrálása az oldalra")
    public void regMeTest(){

        TermsAndConditionsPage tc = new TermsAndConditionsPage(driver);
        RegistrationPage reg = new RegistrationPage(driver);
        tc.navigate();
        tc.clickOnAccept();
        reg.registerMe(name, password, email, description);
        Assertions.assertTrue(reg.registrationCheck());
    }

    @Test
    @Order(11)
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Regisztráció")
    @Description("Felhasználó regisztrálása az oldalra hibás email címmel")
    public void regValidationTest(){

        TermsAndConditionsPage tc = new TermsAndConditionsPage(driver);
        RegistrationPage reg = new RegistrationPage(driver);
        tc.navigate();
        tc.clickOnAccept();
        reg.registerMe(name, password, emailwithoutAT, description);
        Assertions.assertFalse(reg.registrationCheck());
    }



    @Test
    @Order(4)
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Bejelentkezés")
    @Description("Felhasználó Belépése az oldalra regisztráció után")
    public void loginMeTest(){

        TermsAndConditionsPage tc = new TermsAndConditionsPage(driver);
        RegistrationPage reg = new RegistrationPage(driver);
        LoginPage login = new LoginPage(driver);
        tc.navigate();
        tc.clickOnAccept();
        reg.registerMe(name, password, email, description);
        Assertions.assertTrue(reg.registrationCheck());
        login.loginMe(name, password);
        Assertions.assertTrue(login.loginCheck());
    }

    @Test
    @Order(5)
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Kijelentkezése")
    @Description("Felhasználó Kilépése a fiókból")
    public void logoutTest(){

        TermsAndConditionsPage tc = new TermsAndConditionsPage(driver);
        RegistrationPage reg = new RegistrationPage(driver);
        LoginPage login = new LoginPage(driver);
        LogoutPage logout = new LogoutPage(driver);
        tc.navigate();
        tc.clickOnAccept();
        reg.registerMe(name, password, email, description);
        Assertions.assertTrue(reg.registrationCheck());
        login.loginMe(name, password);
        Assertions.assertTrue(login.loginCheck());
        logout.logout();
        Assertions.assertTrue(logout.logoutChecker());
    }

    @Test
    @Order(6)
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Új adat bevitel")
    @Description("Felhasználó profiljába Új adat bevitel")
    public  void  profileNewDataTest(){

        TermsAndConditionsPage tc = new TermsAndConditionsPage(driver);
        RegistrationPage reg = new RegistrationPage(driver);
        LoginPage login = new LoginPage(driver);
        ProfilePage profile = new ProfilePage(driver);
        tc.navigate();
        tc.clickOnAccept();
        reg.registerMe(name, password, email, description);
        Assertions.assertTrue(reg.registrationCheck());
        login.loginMe(name, password);
        Assertions.assertTrue(login.loginCheck());
        profile.profileNewData(modifiedName, bio, phoneNumber);

        String expected = "Profile Edited!";
        String actual = profile.profileNewDataChecker();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Order(7)
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Adat vagy adatok törlése")
    @Description("Profil törlése")
    public void deleteProfileTest(){

        TermsAndConditionsPage tc = new TermsAndConditionsPage(driver);
        RegistrationPage reg = new RegistrationPage(driver);
        LoginPage login = new LoginPage(driver);
        ProfilePage profile = new ProfilePage(driver);
        tc.navigate();
        tc.clickOnAccept();
        reg.registerMe(name, password, email, description);
        Assertions.assertTrue(reg.registrationCheck());
        login.loginMe(name, password);
        Assertions.assertTrue(login.loginCheck());
        profile.deleteProfile();
        Assertions.assertTrue(profile.deleteProfileChecker());
    }

    @Test
    @Order(8)
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Több oldalas lista bejárása")
    @Description("Több oldalas lista bejárása, blog címek listázása az oldalakon")
    public void articlesListTest() throws InterruptedException {

        TermsAndConditionsPage tc = new TermsAndConditionsPage(driver);
        RegistrationPage reg = new RegistrationPage(driver);
        LoginPage login = new LoginPage(driver);
        BlogPage blog = new BlogPage(driver);
        tc.navigate();
        tc.clickOnAccept();
        reg.registerMe(name, password, email, description);
        Assertions.assertTrue(reg.registrationCheck());
        login.loginMe(name, password);
        Assertions.assertTrue(login.loginCheck());
        blog.articklesList();
        Assertions.assertEquals(expectedBlogTitleList, blog.articleListText());
    }

    @Test
    @Order(9)
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Adatok lementése felületről")
    @Description("Blog bejegyzés bekeezdésének lementése fájlba")
    public void saveBlogTextTest() throws InterruptedException {

        TermsAndConditionsPage tc = new TermsAndConditionsPage(driver);
        RegistrationPage reg = new RegistrationPage(driver);
        LoginPage login = new LoginPage(driver);
        BlogPage blog = new BlogPage(driver);
        tc.navigate();
        tc.clickOnAccept();
        reg.registerMe(name, password, email, description);
        Assertions.assertTrue(reg.registrationCheck());
        login.loginMe(name, password);
        Assertions.assertTrue(login.loginCheck());
        blog.articklesList();
        String res = blog.saveBlogText();
        blog.write(res, "saved.txt");
        String actual = blog.reader("saved.txt");
        String expected = blog.reader("expectedData.txt");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Order(10)
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Ismételt és sorozatos adatbevitel adatforrásból ")
    @Description("Ismételt és sorozatos felhasználók regisztrálása adatforrásból ")
    public void repeatedUserRegTest() throws IOException {

        TermsAndConditionsPage tc = new TermsAndConditionsPage(driver);
        RegistrationPage reg = new RegistrationPage(driver);
        tc.navigate();
        tc.clickOnAccept();
        Assertions.assertFalse(tc.popup());
        reg.regTabclicker();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("usersToRegister.txt"));
            String line = reader.readLine();
            while (line != null) {
                String[] arr = line.split(";");
                reg.repeatedUserReg(arr[0],arr[1],arr[2],arr[3]);
                boolean result = reg.registrationCheck();
                Assertions.assertTrue(result);
                line = reader.readLine();
                tc.navigate();
                reg.regTabclicker();
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

