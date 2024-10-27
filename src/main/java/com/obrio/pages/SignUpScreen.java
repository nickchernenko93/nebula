package com.obrio.pages;

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

    private Input emailInput(){
        return new Input(emailField, "'Email' input");
    }

    private Input passwordInput(){
        return new Input(passwordField, "'Password' input");
    }

    private Input confirmPasswordInput(){
        return new Input(confirmPasswordField, "'Confirm password' input");
    }

    public HoroscopeScreen fillInDataForRegistrationAndExpectHoroscopeScreen(String email, String password) {
        emailInput().type(email);
        passwordInput().type(password);
        confirmPasswordInput().type(password);
        signUpButton.click();
        return new HoroscopeScreen();
    }

}
