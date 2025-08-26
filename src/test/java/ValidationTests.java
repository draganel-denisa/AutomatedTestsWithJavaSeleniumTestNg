import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidationTests extends BaseTest {

    // Metodă ajutătoare privată pentru a nu repeta codul de navigare
    private void navigateToRegisterPage() {
        driver.get(baseUrl + "register.php");
    }

    @Test(groups = {"regression", "validation"}, description = "Verifică eroarea la un username cu lungime invalidă")
    public void testUsernameLengthValidation() {
        // STRATEGIA: Acest test validează regula de business din Account.php:
        // if(strlen($un) < 2 || strlen($un) > 25)
        navigateToRegisterPage();

        // Cazul 1: Prea scurt
        driver.findElement(By.name("firstName")).sendKeys("George");
        driver.findElement(By.name("lastName")).sendKeys("Datcu");
        // aici este campul/field-ul testat!!!
        driver.findElement(By.name("username")).sendKeys("a");
        driver.findElement(By.name("email")).sendKeys("abcde@example.com");
        driver.findElement(By.name("email2")).sendKeys("abcde@example.com");
        driver.findElement(By.name("password")).sendKeys("abcde123");
        driver.findElement(By.name("password2")).sendKeys("abcde123");
        driver.findElement(By.name("submitButton")).click();

        // Aserțiune - verificăm textul exact din Constants.php
        String expectedError = "Your username must be between 2 and 25 characters";
//        String actualError = driver.findElement(By.xpath("//span[@class='errorMessage']")).getText();


        WebElement errorMessageElement =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class, 'errorMessage')]")));
        Assert.assertTrue(errorMessageElement.getText().contains(expectedError), "Eroarea pentru username prea scurt nu a apărut.");

        // Cazul 2: Prea lung (ne reimprospătăm pagina pentru a avea un test curat)
        navigateToRegisterPage();
        driver.findElement(By.name("firstName")).sendKeys("George");
        driver.findElement(By.name("lastName")).sendKeys("Datcu");
        // aici este campul/field-ul testat!!!
        driver.findElement(By.name("username")).sendKeys("unusernamefoartefoartelungcarearepeste25decaractere");
        driver.findElement(By.name("email")).sendKeys("abcde@example.com");
        driver.findElement(By.name("email2")).sendKeys("abcde@example.com");
        driver.findElement(By.name("password")).sendKeys("abcde123");
        driver.findElement(By.name("password2")).sendKeys("abcde123");
        driver.findElement(By.name("submitButton")).click();

//        Am experimentat test flakiness
//        actualError = driver.findElement(By.xpath("//span[@class='errorMessage']")).getText();
//        Assert.assertTrue(actualError.contains(expectedError), "Eroarea pentru username prea lung nu a apărut.");

        // Tratarea instabilitatii testului prin ExplictWaits & ExpectedConditions!
        WebElement errorMessageElement2 =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class, 'errorMessage')]")));
        Assert.assertTrue(errorMessageElement2.getText().contains(expectedError), "Eroarea pentru username prea scurt nu a apărut.");

    }


    @Test(groups = {"regression", "validation"}, description = "Verifică eroarea la un format de email invalid")
    public void testEmailFormatValidation() {
        // STRATEGIA: Acest test validează regula de business din Account.php:
        // if(!filter_var($em, FILTER_VALIDATE_EMAIL))
        navigateToRegisterPage();

        driver.findElement(By.name("firstName")).sendKeys("George");
        driver.findElement(By.name("lastName")).sendKeys("Datcu");
        driver.findElement(By.name("username")).sendKeys("uservalid");
        // aici este campul/field-ul testat!!!
        driver.findElement(By.name("email")).sendKeys("george.datcu@hotmail.com");
        driver.findElement(By.name("email2")).sendKeys("george.datcu@hotmail.com");
        driver.findElement(By.name("password")).sendKeys("abcde123");
        driver.findElement(By.name("password2")).sendKeys("abcde123");
        driver.findElement(By.name("submitButton")).click();

        String expectedError = "Email already in use";
//        String actualError = driver.findElement(By.xpath("//span[@class='errorMessage']")).getText();
//        Assert.assertTrue(actualError.contains(expectedError), "Eroarea pentru format de email invalid nu a apărut.");

        WebElement actualError =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class, 'errorMessage')]")));
        Assert.assertTrue(actualError.getText().contains(expectedError), "Eroarea pentru format de email invalid nu a apărut." +
                "Eroarea asteptata era: " + expectedError + ", dar de fapt am primit: " + actualError.getText());
    }
}

