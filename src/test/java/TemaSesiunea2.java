import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class TemaSesiunea2 {
    private WebDriver driver;

    @BeforeMethod
    private void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testLoginCuSucces() {
        driver.get("https://the-internet.herokuapp.com/login");
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

        usernameField.sendKeys("tomsmith");
        passwordField.sendKeys("SuperSecretPassword!");
        loginButton.click();

        Assert.assertTrue(driver.getCurrentUrl().contains("/secure"));

        WebElement successMessage = driver.findElement(By.id("flash"));
        Assert.assertTrue(successMessage.getText().contains("You logged into a secure area!"));

    }

    @Test
    public void testLoginEsuat() {
        driver.get("https://the-internet.herokuapp.com/login");

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

        usernameField.sendKeys("tomsmith");
        passwordField.sendKeys("parolaGresita");
        loginButton.click();

        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));

        WebElement errorMessage = driver.findElement(By.id("flash"));
        Assert.assertTrue(errorMessage.getText().contains("Your username is invalid!"));
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
