package com.obrio.pages.settings_screens;

import com.obrio.elements.Button;
import com.obrio.elements.IButton;
import com.obrio.pages.BaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.NoSuchElementException;
import java.util.function.Supplier;

import static com.obrio.drivers.DriverManager.getDriverInstance;
import static com.obrio.utils.ElementUtils.isElementShown;

public class SettingsScreen extends BaseScreen {

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/toolbarCloseTitleText\")")
    private WebElement settingsScreenLabel;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/container\")")
    private WebElement informationPopUpContainer;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/positiveButton\")")
    private WebElement okButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/toolbarBackButton\")")
    private WebElement backButton;

    public IButton okButton() {
        return new Button(okButton, "'Ok' button");
    }

    public IButton backButton() {
        return new Button(backButton, "'Back' button");
    }

    public void closeInformationPopUpIfItAppears() {
        try {
            if (isElementShown(informationPopUpContainer)) {
                okButton().click();
            }
        } catch (NoSuchElementException ignored) {

        }
    }

    @Override
    protected void waitUntilScreenIsLoaded(WebElement element) {
        super.waitUntilScreenIsLoaded(settingsScreenLabel);
    }

    public <T> T openNeededSettingScreen(String settingName, Supplier<T> pageSupplier) {
        String locator = String.format("//android.widget.TextView[@text=\"%s\"]", settingName);
        getDriverInstance().findElement(By.xpath(locator)).click();
        closeInformationPopUpIfItAppears();
        return pageSupplier.get();
    }

    public <T> T closeSettingAndOpenNeededSettingScreen(String settingName, Supplier<T> pageSupplier) {
        waitUtils().tryWaitUntil(() -> backButton().isDisplayed());
        backButton().click();
        waitUtils().tryWaitUntil(() -> isElementShown(settingsScreenLabel));
        return openNeededSettingScreen(settingName, pageSupplier);
    }
}
