package com.obrio.pages.settings_screens;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class ProfileSettingScreen extends GeneralSettingScreen {

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/toolbarBackTitleText\")")
    private WebElement myProfileTitle;

    @Override
    protected void waitUntilScreenIsLoaded(WebElement element) {
        super.waitUntilScreenIsLoaded(myProfileTitle);
    }
}
