import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;

// Această clasă servește ca șablon fundamental pentru TOATE clasele noastre de test.
// Orice clasă de test va moșteni ('extends') BaseTest.
public class BaseTest {

    // 'protected' înseamnă că aceste variabile sunt vizibile și utilizabile
    // în interiorul acestei clase și în orice clasă copil (ex: AuthenticationTests).
    // Le facem 'protected' în loc de 'private' pentru a le putea folosi direct în teste.
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected String baseUrl = "https://apps.qualiadept.eu/hapiflix/\"";

    // Adnotarea @BeforeMethod îi spune lui TestNG să execute această metodă
    // înainte de FIECARE metodă marcată cu @Test.
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        // Aici este SINGURUL loc din întregul proiect unde inițializăm un driver.
        // Dacă mâine vrem să folosim Firefox, modificăm DOAR această linie.
        driver = new ChromeDriver();

        // Maximizarea ferestrei este o bună practică pentru a asigura că toate
        // elementele sunt vizibile și pentru a testa aplicația într-o configurație comună.
        driver.manage().window().maximize();

        // Inițializăm un 'Explicit Wait'. Acest obiect 'wait' va fi folosit în teste
        // pentru a aștepta în mod inteligent ca anumite condiții să fie îndeplinite
        // (ex: un element să devină vizibil), înainte de a interacționa cu el.
        // Asta previne erorile de tip 'NoSuchElementException' cauzate de timpii de încărcare ai paginii.
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Adnotarea @AfterMethod îi spune lui TestNG să execute această metodă
    // după FIECARE metodă marcată cu @Test, indiferent dacă a trecut sau a eșuat.
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        // Verificăm dacă driver-ul nu este null, pentru a preveni o NullPointerException
        // în cazul în care inițializarea din setUp() a eșuat.
        if (driver != null) {
            // driver.quit() este esențial. Nu folosi driver.close().
            // .close() închide doar fereastra/tab-ul curent.
            // .quit() închide TOATE ferestrele asociate sesiunii, termină procesul
            // chromedriver.exe din memorie și curăță toate resursele.
            // Neutilizarea lui .quit() duce la acumularea de procese "zombie" care consumă RAM.
            driver.quit();
        }
    }
}