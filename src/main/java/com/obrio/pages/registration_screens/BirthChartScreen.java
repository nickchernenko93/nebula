package com.obrio.pages.registration_screens;

import com.obrio.elements.Button;
import com.obrio.elements.IButton;
import com.obrio.pages.BaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class BirthChartScreen extends BaseScreen {

    @AndroidFindBy(id = "genesis.nebula:id/toolbarBackTitleText")
    private WebElement birthChartTitle;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/primaryButton\")")
    private WebElement nextButton;

    private IButton nextButton() {
        return new Button(nextButton, "'Next' button");
    }

    @Override
    protected void waitUntilScreenIsLoaded(WebElement element) {
        super.waitUntilScreenIsLoaded(birthChartTitle);
    }

    public boolean isBirthChartPageOpened() {
        return birthChartTitle.isDisplayed();
    }

    public DateOfBirthScreen clickNextButtonAndOpenDateOfBirthScreen() {
        nextButton().click();
        return new DateOfBirthScreen();
    }
}
