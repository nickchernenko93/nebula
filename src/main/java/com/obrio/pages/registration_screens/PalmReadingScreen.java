package com.obrio.pages.registration_screens;

import com.obrio.elements.Button;
import com.obrio.elements.IButton;
import com.obrio.pages.BaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class PalmReadingScreen extends BaseScreen {

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/toolbarBackTitleText\")")
    private WebElement palmReadingTitle;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/onboardingSkipButton\")")
    private WebElement skipButton;

    public IButton skipButton() {
        return new Button(skipButton, "'Skip' button");
    }

    @Override
    protected void waitUntilScreenIsLoaded(WebElement element) {
        super.waitUntilScreenIsLoaded(palmReadingTitle);
    }

    public boolean isPalmReadingScreenOpened() {
        return palmReadingTitle.isDisplayed();
    }

    public GenderScreen clickSkipButtonAndOpenGenderScreen() {
        skipButton().click();
        return new GenderScreen();
    }
}
