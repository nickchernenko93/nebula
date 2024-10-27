package com.obrio.utils;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import static com.obrio.utils.WaitUtils.getWebDriverWait;

public class ElementUtils {

    public static boolean isElementShown(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException ignored) {
            return false;
        }
    }

    public static boolean isElementEnable(WebElement element) {
        try {
            return element.isEnabled();
        } catch (NoSuchElementException | StaleElementReferenceException ignored) {
            return false;
        }
    }


    public static boolean isClickable(WebElement element) {
        try {
            getWebDriverWait();
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }
}
