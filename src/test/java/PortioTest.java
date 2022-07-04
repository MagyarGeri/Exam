import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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

    @Test
    @Order(9)
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
    public void repeatedUserRegTest() throws IOException {
        Page page = new Page(driver);
        page.navigate();
        page.clickOnAccept();
        page.regTabclicker();

        try {
            Scanner scanner = new Scanner(new File("usersToRegister.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] arr = line.split(";");

                page.repeatedUserReg(arr[0],arr[1],arr[2],arr[3]);
                page.fieldClearer();

                System.out.println(arr);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }




        /*BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("usersToRegister.txt"));

            String line = reader.readLine();
            String[] arr = line.split(";");

            while (line != null) {
                page.repeatedUserReg(arr[0],arr[1],arr[2],arr[3]);

                System.out.println(arr.length);
                Assertions.assertTrue(page.registrationCheck());

                line = reader.readLine();
                page.fieldClearer();





            }
            page.navigate();
            page.regTabclicker();
            reader.close();
        }
        } catch (IOException e) {
            e.printStackTrace();
        }*/




    }

    }

