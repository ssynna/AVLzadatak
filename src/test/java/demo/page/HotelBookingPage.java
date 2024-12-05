package demo.page;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static demo.helpers.Helper.*;
import static demo.helpers.Helper.DropdownSelectAndCheckSelected;
import static demo.setup.BaseSetup.driver;
import static org.junit.jupiter.api.Assertions.*;

public class HotelBookingPage {

    private static String url = "https://tinyurl.com/qa-avl-2024";
    private static String title = "Hotel Booking Form";
    private static String logoImg = "https://www.jotform.com/uploads/ugurg/form_files/hotel-banner.63885da4032201.67905415.png";
    private static String formId = "243331793966366";
    private static String headerId = "header_14";
    private static String subheaderId = "subHeader_14";
    private static String labelTextXpath = "//label[contains(@class,'form-label')]";
    private static List<String> expectedLabels = List.of(
            "Name",
            "E-mail*",
            "Room Type",
            "Number of Guests*",
            "Arrival Date & Time\n" +
                    "*",
            "Departure Date*",
            "Free Pickup?*",
            "Flight Number*",
            "Special Requests*"
    );

    private static String sublabelTextXpath = "//label[contains(@class,'form-sub-label')]";
    private static List<String> expectedSublabels = List.of(
            "Prefix",
            "First Name",
            "Middle Name",
            "Last Name",
            "Suffix",
            "example@example.com",
            "Date",
            "Month",
            "Day",
            "Year"
    );

    private static String freePickupYesXpath = "//span[@class='form-radio-item'][1]";
    private static String freePickupNoXpath = "//span[@class='form-radio-item'][2]";
    private static String dropdownXpath = "//select";
    private static String inputXpath = "//input";
    private static String nonDisplayed = "//*[@style='display:none']";
    private static String hidden = "//*[@type='hidden']";
    private static String submitBtnXpath = "//button[@type='submit']";
    private static String topScreenErrorBarXpath = "//div[@class='error-navigation-container']";
    private static String topScreenErrorBarBtnXpath = "//button[@class='error-navigation-next-button']";
    private static String nonDisplayedTopErrorBarBtn = "//button[@style='display: none;']";
    private static String topErrorBarAlertMessage = "//div[@class='error-navigation-container']//*[@class='error-navigation-message']";
    private static String topErrorBarAlertNumber = "//div[@class='error-navigation-container']//*[@class='error-navigation-message']//strong";
    private static String inputBoxAlerts = "//div[@role='alert']"; // All 6
    private static String inputBoxVisibleAlerts = "(//div[@role='alert'])[position() <= 5]"; // Only 5 that are really visible
    private static String prefix = "//select[@data-component='prefix']";
    private static String firstName = "//input[@data-component='first']";
    private static String middleName = "//input[@data-component='middle']";
    private static String lastName = "//input[@data-component='last']";
    private static String sufix = "//input[@data-component='suffix']";
    private static String email = "//input[@data-component='email']";
    private static String confirmEmail = "//input[@data-component='emailConfirmation']";
    private static String roomTypeDropdown = "//select[@data-component='dropdown']";
    private static String numberOfGuests = "//input[@data-component='number']";
    private static String arivalDateAndTime = "//input[@data-format]";
    private static String departureMonth = "//select[@data-component='birthdate-month']";
    private static String departureDay = "//select[@data-component='birthdate-day']";
    private static String departureYear = "//select[@data-component='birthdate-year']";
    private static String flightNumber = "//input[@data-component='textbox']";
    private static String specialRequests = "//textarea[@data-component='textarea']";

    public static void NavigationToPageAndLoadingTime(){
        long startTime = System.currentTimeMillis(); // Start time
        driver.get(url);

        // Check loading time
        long endTime = System.currentTimeMillis();
        long loadTime = endTime - startTime;

        System.out.println("The page loaded in " + loadTime + " ms.");

        // Check if less than a minute
        assertTrue(loadTime <= 60000, "The page took too long to load!");
    }
    public static void CheckPageTitle(){
        String pageTitle = driver.getTitle();
        System.out.println("Title: " + pageTitle);

        Assertions.assertEquals(driver.getTitle(),title);
        Sleep(0); //wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public static void CheckIfPageLogoIsDisplayed(){
        var isDisplayed = driver.findElement(By.xpath(String.format("//img[@src='%s']", logoImg))).isDisplayed();
        assertTrue(isDisplayed);
    }

    public static void CheckIfPageFormIsDisplayed(){
        var isDisplayed = driver.findElement(By.id(formId)).isDisplayed();
        assertTrue(isDisplayed);
    }

    public static void CheckHeaderAndSubheader(){
        WebElement headerElement = driver.findElement(By.id(headerId));
        WebElement subheaderElement = driver.findElement(By.id(subheaderId));

        String headerActualText = headerElement.getText();
        String subheaderActualText = subheaderElement.getText();

        String headerExpectedText = "Hotel Booking";
        String subheaderExpectedText = "Experience something new every moment";

        Assertions.assertEquals(headerExpectedText,headerActualText);
        Assertions.assertEquals(subheaderExpectedText,subheaderActualText);
    }

    public static void CheckLabelText(){
        // Scroll down
        ScrollDown();

        // Click on element
        ClickByXpath(freePickupYesXpath);

        // Wait for the element visibility
        WaitForElementByXpath(freePickupYesXpath,60);  //Sleep(1);

        List<WebElement> labelElements = driver.findElements(By.xpath(labelTextXpath));
        List<String> actualLabels = new ArrayList<>();

        // Run through every label and print it out
        for (WebElement label : labelElements) {
            String text = label.getText().trim();
            actualLabels.add(text);
            //System.out.println("Found label: " + text);
        }

        // Check if every expected label exists between actual labels
        for (String expectedLabel : expectedLabels) {
            assertTrue(actualLabels.contains(expectedLabel),
                    "Label not found: " + expectedLabel);
        }

        // Check number of labels
        int labelNo = labelElements.size();
        Assertions.assertFalse(labelNo != 9, "Labels don't display correctly!");
    }

    public static void CheckSublabelText(){

        List<WebElement> labelElements = driver.findElements(By.xpath(sublabelTextXpath));
        List<String> actualLabels = new ArrayList<>();

        for (WebElement label : labelElements) {
            String text = label.getText().trim();
            actualLabels.add(text);
            //System.out.println("Found sublabel: " + text);
        }

        for (String expectedLabel : expectedSublabels) {
            assertTrue(actualLabels.contains(expectedLabel),
                    "Label not found: " + expectedLabel);
        }

        // Check number of sublabels
        int labelNo = labelElements.size();
        Assertions.assertFalse(labelNo != 10, "Labels don't display correctly!"); // Finds 14 instead od 10 (hidden elements)
    }

    public static void CheckNumberOfAllVisibleDropdowns(){
        List<WebElement> dropdownElements = driver.findElements(By.xpath(dropdownXpath));
        int dropdownNo = dropdownElements.size();
        Assertions.assertFalse(dropdownNo != 5, "The number of visible dropdown boxes is not displayed correctly!");
    }

    public static void CheckAllVisibleInputBoxes(){
        ClickByXpath(freePickupYesXpath);

        List<WebElement> inputBoxElements = driver.findElements(By.xpath(inputXpath));
        int inputNo = inputBoxElements.size();
        Assertions.assertFalse(inputNo != 11, "The number of visible dropdown boxes is not displayed correctly!");
    }

    public static void CheckForAllNonDisplayedElements(){
        List<WebElement> nonDisplayedElements = driver.findElements(By.xpath(nonDisplayed));
        int inputNo = nonDisplayedElements.size();
        if (inputNo > 0){
            System.out.println("Non-displayed elements on the page: " + inputNo);
        }
        Assertions.assertFalse(inputNo > 0, "There must not be non-displayed elements on the page!");
    }

    public static void CheckForAllHiddenElements(){
        List<WebElement> hiddenElements = driver.findElements(By.xpath(hidden));
        int inputNo = hiddenElements.size();
        if (inputNo > 0){
            System.out.println("Hidden elements on the page: " + inputNo);

            for (WebElement element : hiddenElements) {
                String elementId = element.getAttribute("id");
                String elementName = element.getAttribute("name");

                // Checking and printing of the appropriate element
                if (elementId != null && !elementId.isEmpty()) {
                    System.out.println("Non-displayed element ID: " + elementId);
                } else if (elementName != null && !elementName.isEmpty()) {
                    System.out.println("Non-displayed element Name: " + elementName);
                } else {
                    System.out.println("Non-displayed element has no ID or Name attribute.");
                }
            }
            Assertions.assertFalse(inputNo > 0, "There must not be hidden elements on the page!");
        }
    }

    public static void TopScreenErrorBar(){
        ScrollDown();
        ClickByXpath(submitBtnXpath);

        WaitForElementByXpath(topScreenErrorBarXpath,60);
    }

    public static void TopScreenErrorBarBtn(){
        // Check if btn. displayed
        var isDisplayed = driver.findElement(By.xpath(topScreenErrorBarBtnXpath)).isDisplayed();
        assertTrue(isDisplayed);
    }

    public static void CheckForAllNonDisplayedElementsOnTopScreenErrorBar(){
        List<WebElement> nonDisplayedBtnElements = driver.findElements(By.xpath(nonDisplayedTopErrorBarBtn));
        int inputNo = nonDisplayedBtnElements.size();
        if (inputNo > 0){
            System.out.println("Non-displayed elements on the page: " + inputNo);
        }
        Assertions.assertFalse(inputNo > 0, "There must not be non-displayed elements on the top bar!");
    }

    public static void ErrorBarAlertMsgCheck(){

        WebElement element = driver.findElement(By.xpath(topErrorBarAlertMessage));
        String text = element.getText();
        //System.out.println("Text inside of the element: " + text);

        Assertions.assertFalse(text.isEmpty(), "No alert text present!");
    }

    public static void CompareNumberOfErrorsWithAlertNumber(){
        ScrollDown();
        ClickByXpath(submitBtnXpath);

        // Collect number of errors and parse
        int errNo = Integer.parseInt(driver.findElement(By.xpath(topErrorBarAlertNumber)).getText().trim());

        // Compare with the number of alerts beneath the input boxes
        List<WebElement> alerts = driver.findElements(By.xpath(inputBoxAlerts));
        int inputNo = alerts.size();
        if (inputNo != errNo){
            System.out.println("Non-displayed or hidden elements on the page: " + inputNo + " and it should be: " + errNo);
        }
        Assertions.assertFalse(inputNo != errNo, "There must not be non-displayed or hidden elements on the page!");
    }

    public static void ErrorBarBackgroundColorCheck(){
        WebElement element = driver.findElement(By.xpath(topScreenErrorBarXpath));
        String backgroundColor = element.getCssValue("background-color");
        //System.out.println(backgroundColor);

        Assertions.assertEquals("rgba(220, 38, 38, 1)",backgroundColor);
    }

    public static void InputBoxAlertBackgroundColorCheck(){
        WebElement element = driver.findElement(By.xpath(inputBoxVisibleAlerts));
        String backgroundColor = element.getCssValue("background-color");
        //System.out.println(backgroundColor);

        Assertions.assertEquals("rgba(242, 58, 60, 1)",backgroundColor);
    }

    public static void PrefixSelectAndCheck(){
        RefreshPage();
        Sleep(1);

        ScrollUp();

        WebElement dropdownElement = driver.findElement(By.xpath(prefix));

        Select dropdown = new Select(dropdownElement);

        dropdown.selectByValue("Mr.");

        // Check if option is selected
        WebElement selectedOption = dropdown.getFirstSelectedOption();
        assertNotNull(selectedOption);
        System.out.println("Selected option is : " + selectedOption.getText());
    }

    public static void FirstNameInsertAndCheck(){
        WebElement inputBoxElement = driver.findElement(By.xpath(firstName));

        String inputText = "Samuel";
        inputBoxElement.sendKeys(inputText);

        // Check if text is inserted
        String enteredText = inputBoxElement.getAttribute("value");
        assertNotNull(enteredText);
        assertEquals(inputText, enteredText);

        System.out.println("Text in input box is: " + enteredText);
    }

    public static void MiddleNameInsertAndCheck(){
        InsertAndCheckText(middleName,"L.");
    }

    public static void LastNameInsertAndCheck(){
        InsertAndCheckText(lastName,"Jackson");
    }

    public static void SufixInsertAndCheck(){
        InsertAndCheckText(sufix,"jr");
    }

    public static void EmailInsertAndCheck(){
        InsertAndCheckText(email,"slj@gmail.com");
    }

    public static void ConfirmEmailInsertAndCheck(){
        InsertAndCheckText(confirmEmail,"slj@gmail.com");
    }

    public static void RoomTypeSelectAndCheck(){
        DropdownSelectAndCheckSelected(roomTypeDropdown,"Standard Room (1 to 2 People)");
    }

    public static void NumberOfGuestsInsertAndCheck(){
        InsertAndCheckText(numberOfGuests,"2");
    }

    public static void ArivalDateAndTimeInsertAndCheck(){
        ClearInput(arivalDateAndTime);
        InsertAndCheckText(arivalDateAndTime,"2024-12-25");
        SendEscKey(arivalDateAndTime);
    }

    public static void DepartureDateInsertAndCheck(){
        ScrollDown();

        DropdownSelectByIndexAndCheckSelected(departureMonth,12);
        DropdownSelectByIndexAndCheckSelected(departureDay,31);
        DropdownSelectAndCheckSelected(departureYear,"2024");

        SendEscKey(departureYear);
    }

    public static void FreePickupInsertAndCheck(){
        RadioBtn(freePickupNoXpath);
        Sleep(1);
        RadioBtn(freePickupYesXpath);
        WaitForElementByXpath(flightNumber,10);
    }

    public static void FlightNumberInsertAndCheck(){
        InsertAndCheckText(flightNumber,"1234567891012345");
        SendEscKey(flightNumber);
    }

    public static void SpecialRequestsInsertAndCheck(){
        InsertAndCheckText(specialRequests,"One two three four five six seven eight nine ten");
        SendEscKey(specialRequests);
    }

    public static void ClickOnSubmit(){
        WebElement submitBtn = driver.findElement(By.xpath(submitBtnXpath));
        submitBtn.click();
    }

    public static void CheckIfSubmitted(){
        String getMsg = "//*[contains(text(), 'Your submission has been received.')]";

        WaitForElementNotToBeVisibleByXpath(submitBtnXpath,10);
        WaitForElementByXpath(getMsg,10);

        WebElement submitMsg = driver.findElement(By.xpath(getMsg));
        Assertions.assertTrue(submitMsg.isDisplayed(),"The form has not been successfully submitted!");
    }
}