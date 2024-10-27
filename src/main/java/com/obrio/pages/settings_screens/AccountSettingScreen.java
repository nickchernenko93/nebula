package com.obrio.pages.settings_screens;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class AccountSettingScreen extends GeneralSettingScreen {

    @Override
    protected void waitUntilScreenIsLoaded(WebElement element) {
        super.waitUntilScreenIsLoaded(accountSettingTitle);
    }

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/toolbarBackTitleText\")")
    private WebElement accountSettingTitle;
}
