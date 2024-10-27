package com.obrio.pages;

import com.obrio.elements.Button;
import com.obrio.elements.IButton;
import com.obrio.elements.Input;
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
        elementUtils().tryWaitUntil(() -> errorMessagePopUp.isDisplayed(), 3);
        return errorMessagePopUp.isDisplayed();
    }

    public HoroscopeScreen fillInDataForRegistrationAndExpectHoroscopeScreen(String email, String password) {
        emailInput().type(email);
        passwordInput().type(password);
        confirmPasswordInput().type(password);
        signUpButton.click();
        if (isErrorMessageShown()) {
            okButton().click();
        }
        return new HoroscopeScreen();
    }

}
