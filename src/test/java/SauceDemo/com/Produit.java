package SauceDemo.com;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class Produit {
    WebDriver driver;
    String url = "https://www.saucedemo.com/";
    @BeforeMethod
    public void setUpTest() {
        /*if (Browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (Browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (Browser.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }*/
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
    }

    @Test
    public void Verifier_produits_trier() {
        String username= "standard_user";
        String password ="secret_sauce";
        By login = By.xpath("//input[@id='user-name']");
        By PWD_area = By.xpath("//input[@id='password']");
        By btn_login = By.xpath("//*[@id=\"login-button\"]");

        driver.findElement(login).clear();

        driver.findElement(PWD_area).clear();

        driver.findElement(btn_login).sendKeys(username);

        driver.findElement(PWD_area).sendKeys(password);

        driver.findElement(login).click();

        List<WebElement> elements = driver.findElements(By.xpath("//a/div[@class='inventory_item_name ']"));
        // Créer un tableau pour stocker les textes
        String[] tab = new String[elements.size()];

        // Récupérer le texte de chaque élément et le stocker dans le tableau
        for (int i = 0; i < elements.size(); i++) {
            tab[i] = elements.get(i).getText();
        }String[] actualTextTab = Arrays.copyOf(tab, tab.length);


        Arrays.sort(tab);


        Assert.assertTrue(Arrays.equals(actualTextTab, tab), "Les tableaux ne sont pas identiques après le tri.");

    }
    @AfterMethod
    public void TearDownTest() {

        driver.quit();

    }




}
