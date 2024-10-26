package com.obrio.utils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.obrio.drivers.DriverManager.getDriverInstance;

public class WaitUtils {

    public static FluentWait<AndroidDriver> getWebDriverWait(int timeInSeconds) {
        return new FluentWait<>(getDriverInstance()).withTimeout(Duration.ofSeconds(timeInSeconds))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementNotInteractableException.class);
    }
}
