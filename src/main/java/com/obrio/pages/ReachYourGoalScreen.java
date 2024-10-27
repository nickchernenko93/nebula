package com.obrio.pages;

import com.obrio.elements.Button;
import com.obrio.elements.IButton;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class ReachYourGoalScreen extends BaseScreen {

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/primaryButton\")")
    private WebElement nextButton;

    public IButton nextButton() {
        return new Button(nextButton, "'Next' button");
    }

    public ReviewsScreen clickNextButtonAndOpenReviewsScreen() {
        nextButton.click();
        return new ReviewsScreen();
    }

    @Override
    protected void waitUntilScreenIsLoaded(WebElement element) {
        super.waitUntilScreenIsLoaded(element);
    }
}
