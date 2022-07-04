import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaseTest {

    protected WebDriver driver;

    //TESZTADATOK
    String name = "Magyar Gergő";
    String password = "123456";
    String email = "geri.magyar93@gmail.com";
    String description = "teszt";

    String modifiedName = "Gergő Magyar";
    String bio = "A vizsgaremek remek vizsga";
    String phoneNumber = "123456789";

    //ArrayList<String> expectedBlogTitleList = new ArrayList<String>(Arrays.asList("Markdown Formatting Demo", "Designer Conference at Florida 2020", "Benjamin Franklins thoughts about new designers", "Designers thoughts about mobile UI", "How to become acreative designer", "New designers limitations", "Things you must know as a designer", "World's Most Famous App Developers and Designers", "You must know this before becoming a designer"));
    List<String> expectedBlogTitleList = Arrays.asList("Markdown Formatting Demo", "Designer Conference at Florida 2020", "Benjamin Franklins thoughts about new designers", "Designers thoughts about mobile UI", "How to become acreative designer", "New designers limitations", "Things you must know as a designer", "World's Most Famous App Developers and Designers", "You must know this before becoming a designer");




    @BeforeEach
    public void Setup() {

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("start-maximized", "--incognito");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @AfterEach
    public void Dispose(){
        driver.quit();
    }
}
