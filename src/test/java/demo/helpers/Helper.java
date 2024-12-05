package demo.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static demo.setup.BaseSetup.driver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Helper {
    public static void Sleep(int sec){
        int mills = sec * 1000;
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void ScrollDown(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public static void ScrollUp() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0)");
    }

    public static void ClickByXpath(String xpath){
        WebElement element = driver.findElement(By.xpath(xpath));
        element.click();
    }

    public static void WaitForElementByXpath(String xpath, int maxSec){
        // Initialisation od WebDriverWait with wait duration
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(maxSec));

        // Wait until the element is visible
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    public static void WaitForElementNotToBeVisibleByXpath(String xpath, int maxSec){
        // Initialisation od WebDriverWait with wait duration
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(maxSec));

        // Wait until the element is visible
        Boolean element = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
    }

    public static void RefreshPage(){
        driver.navigate().refresh();
    }

    public static void InsertAndCheckText(String xpath, String input){
        WebElement inputBoxElement = driver.findElement(By.xpath(xpath));

        String inputText = input;
        inputBoxElement.sendKeys(inputText);

        // Check if text has been inserted
        String enteredText = inputBoxElement.getAttribute("value");
        assertNotNull(enteredText);
        assertEquals(inputText, enteredText);

        System.out.println("Text in the box is: " + enteredText);
    }

    public static void DropdownSelectAndCheckSelected(String xpath, String selectByText){
        WebElement dropdownElement = driver.findElement(By.xpath(xpath));

        Select dropdown = new Select(dropdownElement);

        dropdown.selectByValue(selectByText);

        // Check if option is selected
        WebElement selectedOption = dropdown.getFirstSelectedOption();
        assertNotNull(selectedOption);
        System.out.println("Selected option is : " + selectedOption.getText());
    }

    public static void DropdownSelectByIndexAndCheckSelected(String xpath, int selectByIndex){
        WebElement dropdownElement = driver.findElement(By.xpath(xpath));

        Select dropdown = new Select(dropdownElement);

        dropdown.selectByIndex(selectByIndex);

        // Check if option is selected
        WebElement selectedOption = dropdown.getFirstSelectedOption();
        assertNotNull(selectedOption);
        System.out.println("Selected option is : " + selectedOption.getText());
    }

    public static void ClearInput(String xpath){
        WebElement findElement = driver.findElement(By.xpath(xpath));
        findElement.clear();
    }

    public static void SendEnterKey(String elementXpath){
        WebElement findElement = driver.findElement(By.xpath(elementXpath));
        findElement.sendKeys(Keys.ENTER);
    }

    public static void SendEscKey(String elementXpath){
        WebElement findElement = driver.findElement(By.xpath(elementXpath));
        findElement.sendKeys(Keys.ESCAPE);
    }

    public static void RadioBtn(String xpath){
        WebElement findElement = driver.findElement(By.xpath(xpath));
        findElement.click();
    }
}