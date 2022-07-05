import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlogPage {

    WebDriver driver;

    public BlogPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By blogButton = By.xpath("//*[@href=\"https://lennertamas.github.io/portio/#blog\"]");
    private final By seeAllPostsButton = By.xpath("//a[@class=\"btn btn-outline-secondary  btn-zoom m-3\"]");
    private final By nextPageButtonArrow = By.xpath("//a[@rel=\"next\"]");
    private final By articleTitle = By.xpath(".//h5[@class=\"mb-0\"]");
    private final By blogs = By.xpath("//*[@class=\"section blog-page\"]/div/div/div");

    private final By articleTab = By.xpath("//a[@href=\"https://lennertamas.github.io/portio/blog/designer-conference-at-florida-2020/\"]");
    private final By blogContent = By.xpath("//*[@class=\"singleBlog__content\"]/blockquote");

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
            FileWriter output = new FileWriter(fileName);
            output.write(text);
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
}
