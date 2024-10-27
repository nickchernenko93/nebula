package com.obrio.pages;

import com.obrio.elements.Button;
import com.obrio.elements.IButton;
import com.obrio.utils.ElementUtils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import static com.obrio.utils.ElementUtils.isElementShown;

public class CreateAccountScreen extends BaseScreen {

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/onboardingSignupTitleText\")")
    private WebElement createAccountTitle;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/authGoogleButtonText\")")
    private WebElement createAccountViaGoogleButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/authEmailButton\")")
    private WebElement createAccountViaEmailButton;

    public IButton createAccountViaGoogleButton() {
        return new Button(createAccountViaGoogleButton, "'Create account via Google' button");
    }

    public IButton createAccountViaEmailButton() {
        return new Button(createAccountViaEmailButton, "'Create account via Email' button");
    }

    public boolean isCreateAccountViaEmailButtonShown() {
        return isElementShown(createAccountViaEmailButton().getWrappedElement());
    }

    public boolean isCreateAccountViaGoogleButtonShown() {
        return isElementShown(createAccountViaEmailButton().getWrappedElement());
    }

    public SignUpScreen clickCreateAccountViaEmailButtonAndOpenSignUpScreen(){
        createAccountViaEmailButton().click();
        return new SignUpScreen();
    }

    @Override
    protected void waitUntilScreenIsLoaded(WebElement element) {
        super.waitUntilScreenIsLoaded(createAccountTitle);
    }
}
