package com.obrio.pages;

import com.obrio.elements.Input;
import com.obrio.utils.WebElementUtils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.obrio.drivers.DriverManager.getDriverInstance;
import static com.obrio.utils.WaitUtils.getWebDriverWait;
import static com.obrio.utils.WebElementUtils.isElementEnable;

public class PlaceOfBirthPage extends BasePage {

    private static final String SEARCHED_VALUE_LOCATOR = "//android.widget.TextView[contains(@text, '%s')]/parent::android.view.ViewGroup";

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/onboardingBirthPlaceEditText\")")
    private WebElement placeOfBirthInput;

    @Override
    protected void waitUntilPageIsLoaded(WebElement element) {
        super.waitUntilPageIsLoaded(placeOfBirthInput);
    }

    public Input placeOfBirthInput() {
        return new Input(placeOfBirthInput, "'Place of birth' input");
    }

    public PlaceOfBirthPage setPlaceOfBirth(String placeOfBirth) {
        placeOfBirthInput().click();
        WebElementUtils.waitUntil(() -> isElementEnable(placeOfBirthInput), 3);
        placeOfBirthInput().sendKeys(placeOfBirth);
        placeOfBirthInput().click();
        return this;
    }

    public PalmReadingPage confirmPlaceOfBirthAndOpenPalmReadingScreen(String placeOfBirth) {
        String locator = String.format(SEARCHED_VALUE_LOCATOR, placeOfBirth);
        getWebDriverWait(5).until(
                ExpectedConditions.elementToBeClickable(By.xpath(locator))).click();
        return new PalmReadingPage();
    }

    public boolean isSearchedPlacePresentInSearchResult(String placeOfBirth) {
        return getDriverInstance().findElement(By.xpath(String.format(
                SEARCHED_VALUE_LOCATOR, placeOfBirth))).isDisplayed();
    }
}
