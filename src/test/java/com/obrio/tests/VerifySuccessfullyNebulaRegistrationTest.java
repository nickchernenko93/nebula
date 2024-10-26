package com.obrio.tests;

import com.obrio.pages.*;
import com.obrio.utils.DateFormatUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Date;

import static com.obrio.data.GoalValues.*;

public class VerifySuccessfullyNebulaRegistrationTest extends BaseTest {

    private HomePage homePage;
    private GoalsPage goalsPage;
    private YourGoalsPage yourGoalsPage;
    private BirthChartPage birthChartPage;
    private DateOfBirthPage dateOfBirthPage;
    private TimeOfBirthPage timeOfBirthPage;
    private PlaceOfBirthPage placeOfBirthPage;
    private PalmReadingPage palmReadingPage;
    private final String SCREEN_SHOULD_BE_OPENED = "Screen should be opened";
    private String day;
    private String month;
    private String placeOfBirth;

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
        Assert.assertTrue(homePage.isHomePageOpened(), SCREEN_SHOULD_BE_OPENED);
    }

    @Test(dataProvider = "goalsDataProvider", dependsOnMethods = "verifyHomePageIsOpened", alwaysRun = true)
    public void verifyGoalPageIsOpenedAndGoalsAreSelected(String goal) {
        goalsPage.selectGoals(goal);
        soft.assertTrue(goalsPage.isGoalSelected(goal), String.format("'%s' goal should be selected", goal));
        soft.assertAll();
    }

    @Test(dependsOnMethods = "verifyGoalPageIsOpenedAndGoalsAreSelected", alwaysRun = true)
    public void verifyYourGoalsPageIsOpened() {
        yourGoalsPage = goalsPage.clickNextButtonAndOpenYourGoalsPage();
        Assert.assertTrue(yourGoalsPage.isYourGoalPageOpened(), SCREEN_SHOULD_BE_OPENED);
    }

    @Test(dependsOnMethods = "verifyYourGoalsPageIsOpened", alwaysRun = true)
    public void verifyBirthChatPageIsOpened() {
        birthChartPage = yourGoalsPage.clickNextButtonAndOpenBirthChartPage();
        Assert.assertTrue(birthChartPage.isBirthChartPageOpened(), SCREEN_SHOULD_BE_OPENED);
    }

    @Test(dependsOnMethods = "verifyBirthChatPageIsOpened", alwaysRun = true)
    public void verifyDayOfBirthPageOpenedAndDateIsSet() {
        dateOfBirthPage = birthChartPage.clickNextButtonAndOpenDateOfBirthPage();
//        dateOfBirthPage.selectMonth(month);
//        dateOfBirthPage.selectDay(day);
//        Arrays.asList(day, month).forEach(value -> soft.assertTrue(dateOfBirthPage.isDateValueSetInPickerWheel(value),
//                String.format("'%s' value should be present and set", value)));
    }

    @Test(dependsOnMethods = "verifyDayOfBirthPageOpenedAndDateIsSet", alwaysRun = true)
    public void verifyInfoMessageAppearedAfterPressingIDontKnowButton() {
        String expectedInfoMessage = "We need the time of your birth to make astrological predictions more accurate. You can come back and add it when you figure it out.";
        timeOfBirthPage = dateOfBirthPage.clickNextButtonAndOpenTimeOfBirthPage()
                .tapIDontKnowButton();
        String actualMessage = timeOfBirthPage
                .getTextFromInfoMessage();
        Assert.assertEquals(actualMessage, expectedInfoMessage,
                String.format("'%s' actual message is not equal to '%s' expected", actualMessage, expectedInfoMessage));
    }

    @Test(dependsOnMethods = "verifyInfoMessageAppearedAfterPressingIDontKnowButton", alwaysRun = true)
    public void verifyPlaceOfBirthValueIsSet(){
        placeOfBirthPage = timeOfBirthPage.clickSkipButtonAndOpenPlaceOfBirthPage();
        placeOfBirthPage.setPlaceOfBirth(placeOfBirth);
        Assert.assertTrue(placeOfBirthPage.isSearchedPlacePresentInSearchResult(placeOfBirth),
                String.format("'%s' city should be present in search result", placeOfBirth));
    }

    @Test
    public void verifyPlaceOfBirthSelectedAndPalmScreenOpened(){
        palmReadingPage = placeOfBirthPage.confirmPlaceOfBirthAndOpenPalmReadingScreen(placeOfBirth);
        System.out.println("end");
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethodActions(Method method) {
        if (method.getName().equalsIgnoreCase("verifyHomePageIsOpened")) {
            goalsPage = homePage.clickGetStartedButtonAndOpenGoalsPage();
        }
    }

    private void createTestData() {
        Date date = FAKER.date().birthday();
        DateFormatUtils dateFormatUtils = new DateFormatUtils();
        day = dateFormatUtils.formatDayDate(date);
        month = dateFormatUtils.formatMonthDate(date);
        placeOfBirth = "New York";
    }
}
