package com.obrio.pages.registration_screens;

import com.obrio.elements.Button;
import com.obrio.elements.IButton;
import com.obrio.pages.BaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class ReviewsScreen extends BaseScreen {

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/primaryButton\")")
    private WebElement nextButton;

    public IButton nextButton() {
        return new Button(nextButton, "'Next' button");
    }

    public CreateAccountScreen clickNextButtonAndOpenCreateAccountScreen() {
        nextButton.click();
        return new CreateAccountScreen();
    }

    @Override
    protected void waitUntilScreenIsLoaded(WebElement element) {
        super.waitUntilScreenIsLoaded(element);
    }
}
