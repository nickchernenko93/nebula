package com.obrio.pages;

import com.obrio.elements.Button;
import com.obrio.elements.IButton;
import com.obrio.pages.settings_screens.SettingsScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class HomeScreen extends BaseScreen {

    public HomeScreen() {
        waitUntilLoaded();
    }

    @AndroidFindBy(uiAutomator = "//android.widget.TextView[@resource-id=\"genesis.nebula:id/uploadResultProgressViewTitleText\"]")
    private WebElement uploadResultProgressViewElement;

    @AndroidFindBy(uiAutomator = "android.widget.ProgressBar")
    private WebElement progressBarElement;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/premiumHeaderElevate\")")
    private WebElement nebulaPremiumPopUp;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/zodiacBg\")")
    private WebElement zodiacLabel;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/premiumCloseButton\")")
    private WebElement closeButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/character\")")
    private WebElement characterImage;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/toolbar_settings\")")
    private WebElement settingsButton;


    public IButton closeButton() {
        return new Button(closeButton, "'Close' button");
    }

    public IButton settingsButton() {
        return new Button(settingsButton, "'Settings' button");
    }

    public SettingsScreen openSettings() {
        settingsButton().click();
        return new SettingsScreen();
    }

    public boolean isHoroscopeScreenOpened() {
        return characterImage.isDisplayed();
    }

    protected void waitUntilLoaded() {
        waitUtils().tryWaitUntilElementIsNotDisplayed(() -> progressBarElement.isDisplayed());
        waitUtils().tryWaitUntilElementIsNotDisplayed(() -> uploadResultProgressViewElement.isDisplayed());
        waitUtils().tryWaitUntil(() -> nebulaPremiumPopUp.isDisplayed());
        if (nebulaPremiumPopUp.isDisplayed()) {
            closeButton().click();
        }
        if (zodiacLabel.isDisplayed()) {
            closeButton().click();
        }
    }


}
