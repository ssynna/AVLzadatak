package demo.tests;

import demo.setup.BaseSetup;
import org.junit.jupiter.api.*;

import static demo.page.HotelBookingPage.*;
import static demo.page.HotelBookingPage.CheckHeaderAndSubheader;
import static org.junit.jupiter.api.Assertions.assertAll;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Without this the @Order can't work
public class TestList extends BaseSetup {

    //Test Scenarios:
    @Test
    @Order(1)
    //@DisplayName("Hotel Main page")
    void HotelMainPage() {
        NavigationToPageAndLoadingTime();
        CheckPageTitle();
        CheckIfPageLogoIsDisplayed();
        CheckIfPageFormIsDisplayed();
    }

    @Test
    @Order(2)
    void Elements(){
        assertAll("Elements tests",
                () -> CheckHeaderAndSubheader(),
                () -> CheckLabelText(),
                () -> CheckSublabelText(), // Error: Finds 14 sub-labels instead od 10 (hidden elements)
                () -> CheckNumberOfAllVisibleDropdowns(),
                () -> CheckAllVisibleInputBoxes(), // Shows 26 instead of 11 inputs (hidden elements)
                () -> CheckForAllNonDisplayedElements(), // Non-displayed elements on the page: 2
                () -> CheckForAllHiddenElements() // Hidden elements on the page: 11
        );
    }

    @Test
    @Order(3)
    void EmptyValidationSubmit(){
        assertAll("Empty validation submit tests",
                () -> TopScreenErrorBar(),
                () -> TopScreenErrorBarBtn(),
                () -> CheckForAllNonDisplayedElementsOnTopScreenErrorBar(), // Non-displayed elements on the page: 1 (hidden btn)
                () -> ErrorBarAlertMsgCheck(),
                () -> CompareNumberOfErrorsWithAlertNumber(), // Non-displayed or hidden elements on the page: 6 and it should be: 5
                () -> ErrorBarBackgroundColorCheck(),
                () -> InputBoxAlertBackgroundColorCheck()
        );
    }

    @Test
    @Order(4)
    void FullName(){
        PrefixSelectAndCheck();
        FirstNameInsertAndCheck();
        MiddleNameInsertAndCheck();
        LastNameInsertAndCheck();
        SufixInsertAndCheck();
    }

    @Test
    @Order(5)
    void EMail(){
        EmailInsertAndCheck();
        ConfirmEmailInsertAndCheck();
    }


    @Test
    @Order(6)
    void RoomType(){
        RoomTypeSelectAndCheck();
    }

    @Test
    @Order(7)
    void NumberOfGuests(){
        NumberOfGuestsInsertAndCheck();
    }

    @Test
    @Order(8)
    void ArrivalDateAndTime(){
        ArivalDateAndTimeInsertAndCheck();
    }

    @Test
    @Order(9)
    void DepartureDate(){
        DepartureDateInsertAndCheck();
    }

    @Test
    @Order(10)
    void FreePickup(){
        FreePickupInsertAndCheck();
    }

    @Test
    @Order(11)
    void FlightNumber(){
        FlightNumberInsertAndCheck();
    }

    @Test
    @Order(12)
    void SpecialRequests(){
        SpecialRequestsInsertAndCheck();
    }

    @Test
    @Order(13)
    void SubmitForm(){
        ClickOnSubmit();
        CheckIfSubmitted();
    }
}