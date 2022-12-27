import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class FirstTest {

    WebDriver driver = new ChromeDriver();

    //declaring of variables for tests
    String BASE_URL_MOBILE_PAY = "https://next.privat24.ua/mobile";
    String testPhone = "977010707";
    String testAmount = "100";

    By inputPhoneNumber = By.xpath("//input[@data-qa-node='phone-number']");
    By inputAmount = By.xpath("//input[@data-qa-node='amount']");
    By cardNumber = By.xpath("//input[@data-qa-node='numberdebitSource']");
    By expireDate = By.xpath("//input[@data-qa-node='expiredebitSource']");
    By cardCvv = By.xpath("//input[@data-qa-node='cvvdebitSource']");
    By submitButton = By.xpath("//button[@data-qa-node='submit']");
    By nameFrom = By.xpath(".//input[@data-qa-node='firstNamedebitSource']");
    By surnameFrom = By.xpath(".//input[@data-qa-node='lastNamedebitSource']");

    //for confirmation page check
    By details = By.xpath(".//div[@data-qa-node='details']");
    By amount = By.xpath(".//div[@data-qa-node='amount']");


    @Test
    void mobilePaySmokeTest() throws InterruptedException {

        driver.get(BASE_URL_MOBILE_PAY);

        //waiting for page loading
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.findElement(inputPhoneNumber).sendKeys(testPhone);

        //clear the precompiled field
        driver.findElement(inputAmount).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));

        driver.findElement(inputAmount).sendKeys(testAmount);
        driver.findElement(cardNumber).sendKeys("4004159115449003");
        driver.findElement(expireDate).sendKeys("0125");
        driver.findElement(cardCvv).sendKeys("123");
        driver.findElement(nameFrom).sendKeys("IVAN");
        driver.findElement(surnameFrom).sendKeys("IVANENKO");
        driver.findElement(submitButton).click();


        //check if confirmation page contains correct phone number
        String actualNumber = driver.findElement(details).getText();
        assertTrue(actualNumber.contains(testPhone));

//        other way to check if confirmation page contains correct phone number
//        String actualPhoneNumber = driver.findElement(By.xpath(".//div[@data-qa-node='details']")).getText();
//        Assertions.assertTrue(actualPhoneNumber.contains(testPhone));

        //check if confirmation page contains correct amount
        String actualAmount = driver.findElement(amount).getText();
        assertTrue(actualAmount.contains(testAmount));


        //close driver
        driver.quit();


    }

}
