package com.obrio.pages;

import com.obrio.elements.Button;
import com.obrio.elements.IButton;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class HomeScreen extends BaseScreen {

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

    public boolean isHomePageOpened(){
        return nebulaLabel.isDisplayed();
    }

    public GoalsScreen clickGetStartedButtonAndOpenGoalsScreen() {
        getStartedButton().click();
        return new GoalsScreen();
    }
}
