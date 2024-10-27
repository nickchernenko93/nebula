package com.obrio.pages.registration_screens;

import com.obrio.elements.Button;
import com.obrio.elements.IButton;
import com.obrio.pages.BaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.obrio.drivers.DriverManager.getDriverInstance;

public class MotivationScreen extends BaseScreen {

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/toolbarBackTitleText\")")
    private WebElement motivationTitle;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/primaryButton\")")
    private WebElement nextButton;

    public IButton nextButton() {
        return new Button(nextButton, "'Next' button");
    }

    @Override
    protected void waitUntilScreenIsLoaded(WebElement element) {
        super.waitUntilScreenIsLoaded(motivationTitle);

    }

    public MotivationScreen clickNextButton() {
        waitUtils().tryWaitUntil(() -> nextButton().isDisplayed());
        nextButton.click();
        return this;
    }

    public HoroscopeRemainderScreen clickNextButtonAndOpenHoroscopeRemainderScreen() {
        waitUtils().tryWaitUntil(() -> nextButton().isDisplayed());
        nextButton().click();
        return new HoroscopeRemainderScreen();
    }

    public MotivationScreen selectOption(String option) {
        String locator = String.format("//android.widget.Button[@text='%s']", option);
        waitUtils().tryWaitUntil(getDriverInstance().findElement(By.xpath(locator))::isDisplayed);
        WebElement element = getDriverInstance().findElement(By.xpath(locator));
        element.click();
        return this;
    }

    public boolean isMotivationScreenOpened() {
        return motivationTitle.isDisplayed();
    }

}
