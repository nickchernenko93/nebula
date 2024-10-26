package com.obrio.elements;

import org.openqa.selenium.WebElement;

public class Input extends AbstractUIElements implements IInput {
    public Input(WebElement element, String elementName) {
        super(element, elementName);
    }

    public Input(WebElement element) {
        super(element);
    }

    @Override
    public WebElement getWrappedElement() {
        return super.getElement();
    }

    @Override
    public void sendKeys(String text) {
        getWrappedElement().sendKeys(text);
    }
}
