package com.obrio.pages;

import com.obrio.elements.Button;
import com.obrio.elements.IButton;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import static com.obrio.utils.WebElementUtils.isElementShown;

public class TimeOfBirthPage extends BasePage {

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/onboardingSkipButton\")")
    private WebElement iDontKnowButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/negativeButton\")")
    private WebElement skipButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/toolbarBackTitleText\")")
    private WebElement timeOfBirthLabel;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/message\")")
    private WebElement infoMessage;


    @Override
    protected void waitUntilPageIsLoaded(WebElement element) {
        super.waitUntilPageIsLoaded(timeOfBirthLabel);
    }

    public IButton iDontKnowButton() {
        return new Button(iDontKnowButton, "'I don't know' button");
    }

    public IButton skipButton() {
        return new Button(skipButton, "'Skip' button");
    }

    public TimeOfBirthPage tapIDontKnowButton() {
        iDontKnowButton().click();
        return this;
    }

    public String getTextFromInfoMessage() {
        webElementUtils().tryWaitUntil(() -> isElementShown(infoMessage), 2);
        return infoMessage.getText();
    }

    public PlaceOfBirthPage clickSkipButtonAndOpenPlaceOfBirthPage() {
        skipButton().click();
        return new PlaceOfBirthPage();
    }
}
