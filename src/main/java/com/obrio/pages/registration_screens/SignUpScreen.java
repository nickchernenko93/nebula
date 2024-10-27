package com.obrio.pages.registration_screens;

import com.obrio.elements.Button;
import com.obrio.elements.IButton;
import com.obrio.elements.Input;
import com.obrio.pages.BaseScreen;
import com.obrio.pages.HomeScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class SignUpScreen extends BaseScreen {

    @Override
    protected void waitUntilScreenIsLoaded(WebElement element) {
        super.waitUntilScreenIsLoaded(signUpButton);
    }

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"SIGN UP\")")
    private WebElement signUpButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(0)")
    private WebElement emailField;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(1)")
    private WebElement passwordField;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(2)")
    private WebElement confirmPasswordField;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/title\")")
    private WebElement errorMessagePopUp;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/positiveButton\")")
    private WebElement okButton;

    private Input emailInput() {
        return new Input(emailField, "'Email' input");
    }

    private Input passwordInput() {
        return new Input(passwordField, "'Password' input");
    }

    private Input confirmPasswordInput() {
        return new Input(confirmPasswordField, "'Confirm password' input");
    }

    private IButton okButton() {
        return new Button(okButton, "'Ok' button");
    }

    public boolean isErrorMessageShown() {
        try {
            waitUtils().tryWaitUntil(() -> errorMessagePopUp.isDisplayed(), 3);
            return errorMessagePopUp.isDisplayed();
        } catch (Exception ignored) {
            return false;
        }
    }

    public HomeScreen fillInDataForRegistrationAndExpectHomeScreen(String email, String password) {
        boolean errorHandler = false;
        emailInput().type(email);
        passwordInput().type(password);
        confirmPasswordInput().type(password);
        signUpButton.click();
        if (isErrorMessageShown()) {
            try {
                okButton().click();
                errorHandler = true;
            } catch (Exception ignored) {
            }
        }
        if (errorHandler) {
            return null;
        }
        return new HomeScreen();
    }

}
