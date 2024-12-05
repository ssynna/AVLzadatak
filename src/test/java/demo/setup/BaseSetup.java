package demo.setup;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class BaseSetup {

    // Basic configuration:
    public static WebDriver driver = new EdgeDriver();

    @BeforeAll
    static void SetUp() {
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        System.out.println("WebDriver initialized.");
    }

    @AfterAll
    static void Close() {
        if (driver != null) {
            driver.close();
            driver.quit();
            System.out.println("WebDriver terminated.");
        }
    }

    /*
    Info:
    Junit 4 to 5
    @Before annotation is now @BeforeEach
    @After annotation is now @AfterEach
    @BeforeClass annotation is now @BeforeAll
    @AfterClass annotation is now @AfterAll
    @Ignore annotation is now @Disabled
     */
}