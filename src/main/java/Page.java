import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Page{

    WebDriver driver;

    public Page(WebDriver driver) {
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

    private final By loginPopupWindow = By.id("login");
    private final By loginTab = By.xpath("//*[@id=\"register\"]//button[@onclick=\"showLogin()\"]");
    private final By usernameField = By.id("email");
    private final By loginPasswordField = By.id("password");
    private final By loginButton = By.xpath("//*[@class=\"formGroup\"]//*[@onclick=\"myFunction()\"]");
    private final By landingPageLink = By.xpath("//a[@href=\"landing.html\"]");

    public void loginMe(String name, String password){

        driver.findElement(loginTab).click();
        driver.findElement(usernameField).sendKeys(name);
        driver.findElement(loginPasswordField).sendKeys(password);
        driver.findElement(loginButton).click();


    }

    public  boolean loginCheck(){
        return driver.findElement(landingPageLink).isDisplayed();
    }



    private final By logoutButton = By.id("logout-link");


    public void logout(){

        driver.findElement(logoutButton).click();
    }

    public boolean logoutChecker(){

        return driver.findElement(loginButton).isDisplayed();
    }

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

    private final By blogButton = By.xpath("//*[@href=\"https://lennertamas.github.io/portio/#blog\"]");
    private final By seeAllPostsButton = By.xpath("//a[@class=\"btn btn-outline-secondary  btn-zoom m-3\"]");
    private final By nextPageButtonArrow = By.xpath("//a[@rel=\"next\"]");
    private final By articleTitle = By.xpath(".//h5[@class=\"mb-0\"]");
    private final By blogs = By.xpath("//*[@class=\"section blog-page\"]/div/div/div");

    public void articklesList() throws InterruptedException {
        driver.findElement(blogButton).click();
        Thread.sleep(1000);
        driver.findElement(seeAllPostsButton).click();

    }

    public List<String> articleListText(){

        List<String> blogTitleList = new ArrayList<>();

        List<WebElement> elements = driver.findElements(blogs);
        for (WebElement element : elements) {
            String title = element.findElement(articleTitle).getText();
            blogTitleList.add(title);
        }

        driver.findElement(nextPageButtonArrow).click();

        List<WebElement> elements2 = driver.findElements(blogs);
        for (WebElement element : elements2) {
            String title = element.findElement(articleTitle).getText();
            blogTitleList.add(title);
        }
        System.out.println(blogTitleList);
        return blogTitleList;
    }

    private final By articleTab = By.xpath("//a[@href=\"https://lennertamas.github.io/portio/blog/designer-conference-at-florida-2020/\"]");
    private final By blogContent = By.xpath("//*[@class=\"singleBlog__content\"]/blockquote");
    private final By paragraphs = By.xpath("//*[@class=\"singleBlog__content\"]/ul/li");


    public String saveBlogText() {

        driver.findElement(articleTab).click();
        List<WebElement> lis = driver.findElements(blogContent);
        String itemList = null;

        for (int i = 0; i < lis.size(); i++) {
            WebElement li = lis.get(i);
            itemList = li.getText();
        }
        return itemList;
    }


    public void write(String text, String fileName) {


        try {
            // Creates a FileWriter
            FileWriter output = new FileWriter(fileName);

            // Writes the string to the file
            output.write(text);

            // Closes the writer
            output.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public String reader(String filename) {
        String res = "";
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                return data;
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return res;
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

    public void fieldClearer(){
        driver.findElement(userNameField).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        driver.findElement(userNameField).sendKeys(Keys.chord(Keys.DELETE));
        driver.findElement(passwordField).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        driver.findElement(passwordField).sendKeys(Keys.chord(Keys.DELETE));
        driver.findElement(emailField).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        driver.findElement(emailField).sendKeys(Keys.chord(Keys.DELETE));
        driver.findElement(descriptionField).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        driver.findElement(descriptionField).sendKeys(Keys.chord(Keys.DELETE));


    }
}
