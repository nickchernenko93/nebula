package com.obrio.pages;

import com.obrio.elements.Button;
import com.obrio.elements.IButton;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.obrio.drivers.DriverManager.getDriverInstance;

public class InterestsScreen extends BaseScreen {

    @AndroidFindBy(uiAutomator = "genesis.nebula:id/onboardingInterestsTitleText")
    private WebElement interestTitleLabel;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/primaryButton\")")
    private WebElement nextButton;

    public IButton nextButton() {
        return new Button(nextButton, "'Next' button");
    }

    @Override
    protected void waitUntilScreenIsLoaded(WebElement element) {
        super.waitUntilScreenIsLoaded(interestTitleLabel);
    }

    protected void selectInterest(String interest) {
        String locator = String.format("//android.widget.TextView[@text=\"%s\"]/parent::android.widget.FrameLayout", interest);
        elementUtils().tryWaitUntil(() -> getDriverInstance().findElement(By.xpath(locator)).isEnabled());
        getDriverInstance().findElement(By.xpath(locator)).click();
    }

    public MotivationScreen selectInterestsAndOpenMotivationScreen(String... interests) {
        for (String interest : interests) {
            selectInterest(interest);
        }
        nextButton().click();
        return new MotivationScreen();
    }
}
