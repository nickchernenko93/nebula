package com.obrio.tests;

import com.obrio.pages.*;
import com.obrio.utils.DateFormatUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

import static com.obrio.data.GoalValues.*;

public class VerifySuccessfullyNebulaRegistrationTest extends BaseTest {

    private HomePage homePage;
    private GoalsPage goalsPage;
    private YourGoalsPage yourGoalsPage;
    private BirthChartPage birthChartPage;
    private DateOfBirthPage dateOfBirthPage;
    private final String PAGE_SHOULD_BE_OPENED = "Page should be opened";
    private String day;
    private String month;

    @DataProvider(name = "goalsDataProvider")
    public Object[][] goalsDataProvider() {
        return new Object[][]{
                {UNDERSTAND_MYSELF.getValue()},
                {FIND_PERFECT_MATCH.getValue()},
                {RECEIVE_DAILY_INSIGHT_AND_TIPS.getValue()},
                {IMPROVE_RELATIONSHIP.getValue()},
        };
    }

    @BeforeClass(alwaysRun = true)
    public HomePage openHomePage() {
        createTestData();
        return homePage = new HomePage();
    }

    @Test
    public void verifyHomePageIsOpened() {
        Assert.assertTrue(homePage.isHomePageOpened(), PAGE_SHOULD_BE_OPENED);
    }

    @Test(dataProvider = "goalsDataProvider", dependsOnMethods = "verifyHomePageIsOpened", alwaysRun = true)
    public void verifyGoalPageIsOpenedAndGoalsAreSelected(String goal) {
        goalsPage.selectGoals(goal);
        soft.assertTrue(goalsPage.isGoalSelected(goal), String.format("'%s' goal should be selected", goal));
        soft.assertAll();
    }

    @Test(dependsOnMethods = "verifyGoalPageIsOpenedAndGoalsAreSelected", alwaysRun = true)
    public void verifyYourGoalsPageIsOpened() {
        yourGoalsPage = goalsPage.clickNextButton();
        Assert.assertTrue(yourGoalsPage.isYourGoalPageOpened(), PAGE_SHOULD_BE_OPENED);
    }

    @Test(dependsOnMethods = "verifyYourGoalsPageIsOpened", alwaysRun = true)
    public void verifyBirthChatPageIsOpened() {
        birthChartPage = yourGoalsPage.clickNextButton();
        Assert.assertTrue(birthChartPage.isBirthChartPageOpened(), PAGE_SHOULD_BE_OPENED);
    }

    @Test(dependsOnMethods = "verifyBirthChatPageIsOpened", alwaysRun = true)
    public void verifyDayOfBirthPageOpened() {
        dateOfBirthPage = birthChartPage.clickNextButton();
        dateOfBirthPage.selectMonth(month);
        dateOfBirthPage.selectDay(day);
        Arrays.asList(day, month).forEach(value -> soft.assertTrue(dateOfBirthPage.isDateValueSetInPickerWheel(value),
                String.format("'%s' value should be present and set", value)));
    }


    @AfterMethod(alwaysRun = true)
    private void afterMethodActions(Method method) {
        if (method.getName().equalsIgnoreCase("verifyHomePageIsOpened")) {
            goalsPage = homePage.openGoalsPage();
        }
    }

    private void createTestData() {
        Date date = FAKER.date().birthday();
        DateFormatUtils dateFormatUtils = new DateFormatUtils();
        day = dateFormatUtils.formatDayDate(date);
        month = dateFormatUtils.formatMonthDate(date);
    }
}
