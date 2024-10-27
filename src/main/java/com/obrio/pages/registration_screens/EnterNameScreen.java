package com.obrio.pages.registration_screens;


import com.obrio.elements.Button;
import com.obrio.elements.IButton;
import com.obrio.elements.Input;
import com.obrio.pages.BaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class EnterNameScreen extends BaseScreen {

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/toolbarBackTitleText\")")
    private WebElement yourNameScreenTitle;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/rootContainer\")")
    private WebElement nameInputLabel;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\")")
    private WebElement nameInput;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/primaryButton\")")
    private WebElement nextButton;

    public Input nameInput() {
        return new Input(nameInput, "'Name' input");
    }

    public IButton nextButton() {
        return new Button(nextButton, "'Next' button");
    }

    @Override
    protected void waitUntilScreenIsLoaded(WebElement element) {
        super.waitUntilScreenIsLoaded(yourNameScreenTitle);
    }

    public void setName(String name) {
        nameInputLabel.click();
        nameInput().type(name);
    }

    public RelationshipStatusScreen setNameAndOpenRelationshipStatusScreen(String name) {
        setName(name);
        waitUtils().tryWaitUntil(() -> nextButton().isEnabled());
        nextButton().click();
        return new RelationshipStatusScreen();
    }
}
