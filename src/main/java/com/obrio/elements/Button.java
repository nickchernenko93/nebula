package com.obrio.elements;

import org.openqa.selenium.WebElement;

public class Button extends AbstractUIElements implements IButton {

    public Button(WebElement element) {
        super(element);
    }

    public Button(WebElement element, String name) {
        super(element, name);
    }

    @Override
    public WebElement getWrappedElement() {
        return element;
    }
}
