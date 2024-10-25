package com.obrio.pages;

import com.obrio.drivers.DriverManager;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public abstract class BasePage {
    protected BasePage() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getDriverInstance()), this);
    }

    protected void waitUntilPageIsLoaded(WebElement element) {
        WebDriver driver = DriverManager.getDriverInstance();

        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        wait.until(webdriver -> {
            if (element.isDisplayed()) {
                return element;
            }
            return null;
        });
    }
}


