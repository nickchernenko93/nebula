package com.obrio.pages.registration_screens;

import com.obrio.elements.Input;
import com.obrio.pages.BaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.obrio.drivers.DriverManager.getDriverInstance;
import static com.obrio.utils.WaitUtils.getWebDriverWait;

public class PlaceOfBirthScreen extends BaseScreen {

    private static final String SEARCHED_VALUE_LOCATOR = "//android.widget.TextView[contains(@text, '%s')]/parent::android.view.ViewGroup";

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/onboardingBirthPlaceEditText\")")
    private WebElement placeOfBirthInput;

    @Override
    protected void waitUntilScreenIsLoaded(WebElement element) {
        super.waitUntilScreenIsLoaded(placeOfBirthInput);
    }

    public Input placeOfBirthInput() {
        return new Input(placeOfBirthInput, "'Place of birth' input");
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        placeOfBirthInput().click();
        placeOfBirthInput().type(placeOfBirth);
        placeOfBirthInput().click();
    }

    public PalmReadingScreen confirmPlaceOfBirthAndOpenPalmReadingScreen(String placeOfBirth) {
        String locator = String.format(SEARCHED_VALUE_LOCATOR, placeOfBirth);
        getWebDriverWait(5).until(
                ExpectedConditions.elementToBeClickable(By.xpath(locator))).click();
        return new PalmReadingScreen();
    }

    public boolean isSearchedPlacePresentInSearchResult(String placeOfBirth) {
        return getDriverInstance().findElement(By.xpath(String.format(
                SEARCHED_VALUE_LOCATOR, placeOfBirth))).isDisplayed();
    }
}
