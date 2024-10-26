package com.obrio.pages;

import com.obrio.elements.Button;
import com.obrio.elements.IButton;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    @AndroidFindBy(id = "genesis.nebula:id/gradient")
    private WebElement nebulaLabel;

    @AndroidFindBy(id = "genesis.nebula:id/startJobsTypeButton")
    private WebElement getStartedButton;

    public IButton getStartedButton() {
        return new Button(getStartedButton, "'Get Started' button");
    }

    @Override
    protected void waitUntilPageIsLoaded(WebElement element) {
        super.waitUntilPageIsLoaded(nebulaLabel);
    }

    public boolean isHomePageOpened(){
        return nebulaLabel.isDisplayed();
    }

    public GoalsPage clickGetStartedButtonAndOpenGoalsPage() {
        getStartedButton().click();
        return new GoalsPage();
    }
}
