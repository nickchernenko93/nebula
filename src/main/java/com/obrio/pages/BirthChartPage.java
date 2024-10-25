package com.obrio.pages;

import com.obrio.elements.Button;
import com.obrio.elements.IButton;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class BirthChartPage extends BasePage {

    @AndroidFindBy(id = "genesis.nebula:id/toolbarBackTitleText")
    private WebElement birthChartTitle;

    @AndroidFindBy(id = "new UiSelector().resourceId(\"genesis.nebula:id/primaryButton\")")
    private WebElement nextButton;

    private IButton nextButton() {
        return new Button(nextButton, "'Next' button");
    }


    @Override
    protected void waitUntilPageIsLoaded(WebElement element) {
        super.waitUntilPageIsLoaded(birthChartTitle);
    }

    public boolean isBirthChartPageOpened() {
        return birthChartTitle.isDisplayed();
    }

    public DateOfBirthPage clickNextButton() {
        nextButton().click();
        return new DateOfBirthPage();
    }
}
