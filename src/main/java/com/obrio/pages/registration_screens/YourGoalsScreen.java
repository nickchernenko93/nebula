package com.obrio.pages.registration_screens;

import com.obrio.elements.Button;
import com.obrio.elements.IButton;
import com.obrio.pages.BaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class YourGoalsScreen extends BaseScreen {

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/onboardingGoalsPersonalTitleText\")")
    private WebElement gotItLabel;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/primaryButton\")")
    private WebElement nextButton;

    private IButton nextButton() {
        return new Button(nextButton, "'Next' button");
    }

    @Override
    protected void waitUntilScreenIsLoaded(WebElement element) {
        super.waitUntilScreenIsLoaded(gotItLabel);
    }

    public boolean isYourGoalPageOpened() {
        return gotItLabel.isDisplayed();
    }

    public BirthChartScreen clickNextButtonAndOpenBirthChartScreen() {
        nextButton().click();
        return new BirthChartScreen();
    }
}
