package com.obrio.pages.settings_screens;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class AccountSettingScreen extends GeneralSettingScreen {

    public AccountSettingScreen() {
        waitUntilScreenLoaded();
    }

    private void waitUntilScreenLoaded() {
        waitUtils().tryWaitUntil(() -> accountSettingTitle.isDisplayed(), 2);
    }

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/toolbarBackTitleText\")")
    private WebElement accountSettingTitle;
}
