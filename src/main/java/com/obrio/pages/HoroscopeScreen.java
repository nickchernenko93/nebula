package com.obrio.pages;

import com.obrio.elements.Button;
import com.obrio.elements.IButton;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class HoroscopeScreen extends BaseScreen {

    @AndroidFindBy(uiAutomator = "//android.widget.TextView[@resource-id=\"genesis.nebula:id/uploadResultProgressViewTitleText\"]")
    private WebElement uploadResultProgressViewElement;

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

    @Override
    protected void waitUntilScreenIsLoaded(WebElement element) {
        elementUtils().tryWaitUntilElementIsNotDisplayed(() -> uploadResultProgressViewElement.isDisplayed());
        elementUtils().tryWaitUntil(() -> nebulaPremiumPopUp.isDisplayed());
        if (nebulaPremiumPopUp.isDisplayed()) {
            closeButton().click();
        }
        if (zodiacLabel.isDisplayed()) {
            closeButton().click();
        }
        super.waitUntilScreenIsLoaded(characterImage);
    }


}
