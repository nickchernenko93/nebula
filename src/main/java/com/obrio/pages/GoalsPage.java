package com.obrio.pages;

import com.obrio.elements.Button;
import com.obrio.elements.IButton;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.obrio.drivers.DriverManager.getDriverInstance;
import static com.obrio.utils.WaitUtils.getWebDriverWait;

public class GoalsPage extends BasePage {

    private static final String GOALS_LOCATOR = "//android.widget.TextView[contains(@text, \"%s\")]" +
            "//following-sibling::android.widget.RadioButton[@resource-id=\"genesis.nebula:id/optionRadiobutton\"]";

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/onboardingQuestionTitleText\")")
    private WebElement whatAreYourGoalsLabel;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"genesis.nebula:id/primaryButton\")")
    private WebElement nextButton;

    private IButton nextButton(){
        return new Button(nextButton, "'Next' Button");
    }

    public void selectGoals(String... goal) {
        getDriverInstance().findElement(By.xpath(String.format(GOALS_LOCATOR, goal))).click();
    }

    public boolean isGoalSelected(String... goal) {
        return getWebDriverWait(1).until(
                ExpectedConditions.elementToBeSelected(By.xpath(String.format(GOALS_LOCATOR, (Object[]) goal))));
    }

    public YourGoalsPage clickNextButton(){
        nextButton().click();
        return new YourGoalsPage();
    }

    @Override
    protected void waitUntilPageIsLoaded(WebElement element) {
        super.waitUntilPageIsLoaded(whatAreYourGoalsLabel);
    }
}
