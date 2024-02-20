package SauceDemo.com;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class Connexion {

    WebDriver driver;
    String URL = "https://www.saucedemo.com/";

    //@Parameters("Browser")
    @BeforeMethod
    public void setUpTest() {
        /*if (Browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (Browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (Browser.equals("edge")) */
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
    }

    @Test
    public void LoginTest_nominal() throws InterruptedException {
        By login = By.xpath("//input[@id='user-name']");

        By Area_pswrd = By.xpath("//input[@id='password']");

        By page = By.xpath("//span[@class='title']");

        By btn_login = By.xpath("//*[@id=\"login-button\"]");

        List<String> usernames = Arrays.asList("standard_user", "problem_user", "performance_glitch_user", "error_user", "visual_user");
        String password = "secret_sauce";

        By menu = By.xpath("//button[@id=\'react-burger-menu-btn\']");

        By lnk_logout = By.xpath("//a[@id='logout_sidebar_link']");

        for (String username : usernames) {
            driver.findElement(login).clear();
            driver.findElement(Area_pswrd).clear();
            driver.findElement(login).sendKeys(username);
            driver.findElement(Area_pswrd).sendKeys(password);
            driver.findElement(btn_login).click();
            Assert.assertTrue(driver.findElement(page).isDisplayed());
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(menu));
            driver.findElement(menu).click();
            wait.until(ExpectedConditions.elementToBeClickable(lnk_logout));
            driver.findElement(lnk_logout).click();
        }


    }

    @Test
    public void LoginTest_invalides() {
        By login = By.xpath("//input[@id='user-name']");

        By Area_pswrd = By.xpath("//input[@id='password']");

        By page = By.xpath("//span[@class='title']");

        By btn_login = By.xpath("//*[@id=\"login-button\"]");

        By menu = By.xpath("//button[@id=\'react-burger-menu-btn\']");

        By lnk_logout = By.xpath("//a[@id='logout_sidebar_link']");
        By msg_err = By.xpath("//h3[@data-test=\'error\']");

        List<String> usrnme = Arrays.asList("otmani", "abdellah", "visual_user", "error_user", "visual_user");
        List<String> PWD = Arrays.asList("secret_sauce", "erreur", "sauce", "sauce", "abddellah", "otmani");

        for (String username : usrnme) {
            driver.findElement(login).clear();
            driver.findElement(Area_pswrd).clear();
            driver.findElement(login).sendKeys(username);
            for (String password : PWD) {
                driver.findElement(Area_pswrd).sendKeys(password);
                driver.findElement(btn_login).click();
                driver.findElement(Area_pswrd).clear();
            };
            Assert.assertTrue(driver.findElement(msg_err).isDisplayed());

        }
    }
    @AfterMethod
    public void TearDownTest() {

        driver.quit();

    }
}