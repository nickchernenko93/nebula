package com.obrio.pages;

import com.obrio.drivers.DriverManager;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.obrio.utils.SwipeUtils.*;

public class DateOfBirthPage extends BasePage {

    private static final String DATE_PICKER_WHEEL = "//android.widget.EditText[@resource-id=\"android:id/numberpicker_input\" and @text=\"%s\"]";

    @AndroidFindBy(id = "genesis.nebula:id/onboardingBirthDateImage")
    private WebElement birthDateLabel;

    @Override
    protected void waitUntilPageIsLoaded(WebElement element) {
        super.waitUntilPageIsLoaded(birthDateLabel);
    }

    public void selectMonth(String month) {
        swipeToMonthElement(By.xpath(String.format(DATE_PICKER_WHEEL, month)), 12);
    }

    public void selectDay(String day) {
        swipeToDayElement(By.xpath(String.format(DATE_PICKER_WHEEL, day)), 31);
    }

    // TODO: 26.10.2024 need select year in other way, to many values could be set
    public void selectYear(String year) {
        swipeToYearElement(By.xpath(String.format(DATE_PICKER_WHEEL, year)), 2);
    }

    public boolean isDateValueSetInPickerWheel(String value) {
        return DriverManager.getDriverInstance().findElement(By.xpath(String.format(DATE_PICKER_WHEEL, value))).isDisplayed();
    }
}
