package com.obrio.pages.registration_screens;

import com.obrio.data.registration.RelationshipStatuses;
import com.obrio.pages.BaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.obrio.drivers.DriverManager.getDriverInstance;

public class RelationshipStatusScreen extends BaseScreen {

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/toolbarBackTitleText\")")
    private WebElement relationshipScreenTitle;

    @Override
    protected void waitUntilScreenIsLoaded(WebElement element) {
        super.waitUntilScreenIsLoaded(relationshipScreenTitle);
    }

    public boolean isRelationshipScreenOpened() {
        return relationshipScreenTitle.isDisplayed();
    }

    public void selectRelationshipStatus(RelationshipStatuses status) {
        String locator = String.format("//android.widget.TextView[@resource-id=\"genesis.nebula:id/answerSquareButtonTitle\" " +
                "and @text=\"%s\"]//ancestor::android.widget.FrameLayout[@resource-id=\"genesis.nebula:id/optionButton\"]", status.getValue());
        getDriverInstance().findElement(By.xpath(locator)).click();
    }

    public InterestsScreen selectRelationshipStatusAndOpenInterestsScreen(RelationshipStatuses status) {
        selectRelationshipStatus(status);
        return new InterestsScreen();
    }
}
