package com.obrio.pages;

import com.obrio.elements.Button;
import com.obrio.elements.IButton;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import static com.obrio.utils.ElementUtils.isElementShown;

public class HoroscopeRemainderScreen extends BaseScreen {

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/onboardingDailyPushAnimation\")")
    private WebElement bellImage;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/onboardingSkipButton\")")
    private WebElement skipButton;

    public IButton skipButton() {
        return new Button(skipButton, "'Skip' button");
    }

    @Override
    protected void waitUntilScreenIsLoaded(WebElement element) {
        super.waitUntilScreenIsLoaded(bellImage);
    }

    public boolean isBellImageShown(){
        return isElementShown(bellImage);
    }

    public SocialMediaScreen clickSkipButtonAndOpenSocialMediaScreen(){
        elementUtils().tryWaitUntil(() -> skipButton().isDisplayed());
        skipButton().click();
        return new SocialMediaScreen();
    }

}
