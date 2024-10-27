package com.obrio.pages.registration_screens;

import com.obrio.elements.Button;
import com.obrio.elements.IButton;
import com.obrio.pages.BaseScreen;
import com.obrio.utils.ElementUtils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class RegistrationHomeScreen extends BaseScreen {

    @AndroidFindBy(id = "genesis.nebula:id/gradient")
    private WebElement nebulaLabel;

    @AndroidFindBy(id = "genesis.nebula:id/startJobsTypeButton")
    private WebElement getStartedButton;

    public IButton getStartedButton() {
        return new Button(getStartedButton, "'Get Started' button");
    }

    @Override
    protected void waitUntilScreenIsLoaded(WebElement element) {
        super.waitUntilScreenIsLoaded(nebulaLabel);
    }

    public boolean isRegistrationHomeScreenOpened() {
        return ElementUtils.isElementShown(nebulaLabel);
    }

    public GoalsScreen clickGetStartedButtonAndOpenGoalsScreen() {
        getStartedButton().click();
        return new GoalsScreen();
    }
}
