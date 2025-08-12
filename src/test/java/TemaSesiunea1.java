import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TemaSesiunea1 {
    private WebDriver driver;

    @BeforeMethod
    private void setup() {
        System.out.println("Rulam metoda de setup...");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

// Test 1

    @Test
    public void testTitluEmag() {
        System.out.println("Rulam testul pentru titlul Emag...");
        driver.get("https://www.emag.ro");
        String titluActual = driver.getTitle();
        String titluAsteptat = "eMAG.ro - Căutarea nu se oprește niciodată";

        Assert.assertEquals(titluActual, titluAsteptat, "Titlul paginii Emag nu este corect!");
    }

// Test 2

    @Test
    public void testUrlImdb() {
        System.out.println("Rulam testul de navigare...");
        driver.navigate().to("https://www.imdb.com/");
        String urlActual = driver.getCurrentUrl();

        Assert.assertTrue(urlActual.contains("imdb.com"), "URL-ul nu conține 'imdb.com'");
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("Rulam metoda de teardown...");
        // Acest cod se va executa DUPA FIECARE metoda @Test
        if (driver != null) {
            driver.quit();
        }

    }
}

