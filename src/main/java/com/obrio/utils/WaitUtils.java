package com.obrio.utils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

import static com.obrio.drivers.DriverManager.getDriverInstance;

public class WaitUtils {
public static final int DEFAULT_TIMEOUT_TO_WAIT = 2;
    public static FluentWait<AndroidDriver> getWebDriverWait(int timeInSeconds) {
        return new FluentWait<>(getDriverInstance()).withTimeout(Duration.ofSeconds(timeInSeconds))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementNotInteractableException.class);
    }

    public static FluentWait<AndroidDriver> getWebDriverWait() {
        return getWebDriverWait(DEFAULT_TIMEOUT_TO_WAIT);
    }
}
