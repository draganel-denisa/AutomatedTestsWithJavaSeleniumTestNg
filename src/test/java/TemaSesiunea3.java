import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;


public class TemaSesiunea3 {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testVisibility() {
        driver.get("https://apps.qualiadept.eu/yumi-form");
        WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        name.sendKeys("Denisa Draganel");

        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        email.sendKeys("draganel_denisa@yahoo.com");

        WebElement mobile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mobile")));
        mobile.sendKeys("0716623653");

        WebElement gender = wait.until(ExpectedConditions.elementToBeClickable(By.id("gender-female")));
        gender.click();

        WebElement hobby = wait.until(ExpectedConditions.elementToBeClickable(By.id("hobby-reading")));
        hobby.click();

        WebElement currentAddress = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("currentAddress")));
        currentAddress.sendKeys("Tilburg, Netherlands");

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn.btn-primary.px-4")));
        submitButton.click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        Assert.assertEquals(alertText, "Form submitted! Check the console for the data.");
        alert.accept();
    }


    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
