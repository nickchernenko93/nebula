package com.obrio.pages;

import com.obrio.elements.Button;
import com.obrio.elements.IButton;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import static com.obrio.utils.ElementUtils.isElementShown;

public class TimeOfBirthScreen extends BaseScreen {

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/onboardingSkipButton\")")
    private WebElement iDontKnowButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/negativeButton\")")
    private WebElement skipButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/toolbarBackTitleText\")")
    private WebElement timeOfBirthLabel;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/message\")")
    private WebElement infoMessage;


    @Override
    protected void waitUntilScreenIsLoaded(WebElement element) {
        super.waitUntilScreenIsLoaded(timeOfBirthLabel);
    }

    public IButton iDontKnowButton() {
        return new Button(iDontKnowButton, "'I don't know' button");
    }

    public IButton skipButton() {
        return new Button(skipButton, "'Skip' button");
    }

    public TimeOfBirthScreen tapIDontKnowButton() {
        iDontKnowButton().click();
        return this;
    }

    public String getTextFromInfoMessage() {
        elementUtils().tryWaitUntil(() -> isElementShown(infoMessage), 2);
        return infoMessage.getText();
    }

    public PlaceOfBirthScreen clickSkipButtonAndOpenPlaceOfBirthScreen() {
        skipButton().click();
        return new PlaceOfBirthScreen();
    }
}
