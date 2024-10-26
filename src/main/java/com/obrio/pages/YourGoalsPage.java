package com.obrio.pages;

import com.obrio.elements.Button;
import com.obrio.elements.IButton;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class YourGoalsPage extends BasePage {

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/onboardingGoalsPersonalTitleText\")")
    private WebElement gotItLabel;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/primaryButton\")")
    private WebElement nextButton;

    private IButton nextButton(){
        return new Button(nextButton, "'Next' button");
    }

    @Override
    protected void waitUntilPageIsLoaded(WebElement element) {
        super.waitUntilPageIsLoaded(gotItLabel);
    }

    public boolean isYourGoalPageOpened(){
        return gotItLabel.isDisplayed();
    }

    public BirthChartPage clickNextButton(){
        nextButton().click();
        return new BirthChartPage();
    }
}
