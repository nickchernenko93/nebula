package com.obrio.pages.registration_screens;

import com.obrio.data.registration.Genders;
import com.obrio.pages.BaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.obrio.drivers.DriverManager.getDriverInstance;
import static com.obrio.utils.ElementUtils.isElementShown;

public class GenderScreen extends BaseScreen {

    private static final String GENDER_TILE_LOCATOR = "//android.widget.TextView[@resource-id=\"genesis.nebula:id/answerSquareButtonTitle\" " +
            "and @text=\"%s\"]//ancestor::android.widget.FrameLayout[@resource-id=\"genesis.nebula:id/optionButton\"]";

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/toolbarBackTitleText\")")
    private WebElement genderTitle;

    @Override
    protected void waitUntilScreenIsLoaded(WebElement element) {
        super.waitUntilScreenIsLoaded(genderTitle);
    }

    public boolean isGenderTileShown(Genders genders) {
        String locator = String.format(GENDER_TILE_LOCATOR, genders.getValue());
        WebElement tile = getDriverInstance().findElement(By.xpath(locator));
        return isElementShown(tile);
    }

    public EnterNameScreen selectGenderAndOpenNameScreen(Genders genders) {
        String locator = String.format(GENDER_TILE_LOCATOR, genders.getValue());
        getDriverInstance().findElement(By.xpath(locator)).click();
        return new EnterNameScreen();
    }
}
