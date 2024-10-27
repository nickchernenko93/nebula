package com.obrio.pages;

import com.obrio.elements.Button;
import com.obrio.elements.IButton;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.obrio.drivers.DriverManager.getDriverInstance;

public class SocialMediaScreen extends BaseScreen {

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/onboardingQuestionTitleText\")")
    private WebElement socialMediaQuestionLabel;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/onboardingSkipButton\")")
    private WebElement skipButton;

    public IButton skipButton() {
        return new Button(skipButton, "'Skip' button");
    }

    @Override
    protected void waitUntilScreenIsLoaded(WebElement element) {
        super.waitUntilScreenIsLoaded(socialMediaQuestionLabel);
    }

    public boolean isButtonEnabled(String buttonName) {
        String locator = String.format("//android.widget.Button[@text='%s']", buttonName);
        WebElement element = getDriverInstance().findElement(By.xpath(locator));
        return element.isEnabled();
    }

    public ReachYourGoalScreen clickSkipButtonAndOpenReachYourGoalScreen() {
        skipButton().click();
        return new ReachYourGoalScreen();
    }
}
