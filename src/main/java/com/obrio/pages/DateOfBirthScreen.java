package com.obrio.pages;

import com.obrio.drivers.DriverManager;
import com.obrio.elements.Button;
import com.obrio.elements.IButton;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.obrio.utils.SwipeUtils.*;

public class DateOfBirthScreen extends BaseScreen {

    private static final String DATE_PICKER_WHEEL = "//android.widget.EditText[@resource-id=\"android:id/numberpicker_input\" and @text=\"%s\"]";

    @AndroidFindBy(id = "genesis.nebula:id/onboardingBirthDateImage")
    private WebElement birthDateLabel;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/primaryButton\")")
    private WebElement nextButton;

    private IButton nextButton() {
        return new Button(nextButton, "'Next' button");
    }

    @Override
    protected void waitUntilScreenIsLoaded(WebElement element) {
        super.waitUntilScreenIsLoaded(birthDateLabel);
    }

    public void selectMonth(String month) {
        swipeToMonthElement(By.xpath(String.format(DATE_PICKER_WHEEL, month)), 12);
    }

    public void selectDay(String day) {
        swipeToDayElement(By.xpath(String.format(DATE_PICKER_WHEEL, day)), 31);
    }

    // TODO: 26.10.2024 need select year through keyboard input because to many values present
    public void selectYear(String year) {
        swipeToYearElement(By.xpath(String.format(DATE_PICKER_WHEEL, year)), 2);
    }

    public boolean isDateValueSetInPickerWheel(String value) {
        return DriverManager.getDriverInstance().findElement(By.xpath(String.format(DATE_PICKER_WHEEL, value))).isDisplayed();
    }

    public TimeOfBirthScreen clickNextButtonAndOpenTimeOfBirthScreen() {
        nextButton().click();
        return new TimeOfBirthScreen();
    }
}
