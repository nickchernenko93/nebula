package com.obrio.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.obrio.drivers.DriverManager.getDriverInstance;

public class SettingScreen extends GeneralSettingsScreen{

    public String getValueFromField(String fieldName) {
        String locator = String.format("//android.widget.TextView[@text='%s']//following-sibling::android.widget.TextView");
        WebElement element = getDriverInstance().findElement(By.xpath(locator));
        elementUtils().tryWaitUntil(element::isEnabled);
        return element.getText();
    }
}
