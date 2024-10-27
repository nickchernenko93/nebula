package com.obrio.pages.settings_screens;

import com.obrio.data.ui.fields.SettingsFields;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.obrio.drivers.DriverManager.getDriverInstance;

public class GeneralSettingScreen extends SettingsScreen {

    public String getValueFromField(SettingsFields fieldName) {
        String locator = String.format("//android.widget.TextView[@text='%s']//following-sibling::android.widget.TextView", fieldName.getValue());
        WebElement element = getDriverInstance().findElement(By.xpath(locator));
        waitUtils().tryWaitUntil(element::isEnabled);
        return element.getText();
    }
}
