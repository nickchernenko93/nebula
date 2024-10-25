package com.obrio.elements;

import org.openqa.selenium.WrapsElement;

public interface UIElements extends WrapsElement {

    void click();
    boolean isDisplayed();
    boolean isEnabled();
    String getText();
}
