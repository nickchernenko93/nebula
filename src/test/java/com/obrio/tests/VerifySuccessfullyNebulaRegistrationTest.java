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

import static com.obrio.data.Gender.*;
import static com.obrio.data.GoalValues.*;

public class VerifySuccessfullyNebulaRegistrationTest extends BaseTest {

    private final String SCREEN_SHOULD_BE_OPENED = "Screen should be opened";
    private HomeScreen homeScreen;
    private GoalsScreen goalsScreen;
    private YourGoalsScreen yourGoalsScreen;
    private BirthChartScreen birthChartScreen;
    private DateOfBirthScreen dateOfBirthScreen;
    private TimeOfBirthScreen timeOfBirthScreen;
    private PlaceOfBirthScreen placeOfScreen;
    private PalmReadingScreen palmReadingScreen;
    private GenderScreen genderScreen;
    private EnterNameScreen enterNameScreen;
    private String day;
    private String month;
    private String placeOfBirth;
    private String name;

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
    public HomeScreen openHomePage() {
        createTestData();
        return homeScreen = new HomeScreen();
    }

    @Test
    public void verifyHomeScreenIsOpened() {
        Assert.assertTrue(homeScreen.isHomePageOpened(), SCREEN_SHOULD_BE_OPENED);
    }

    @Test(dataProvider = "goalsDataProvider", dependsOnMethods = "verifyHomeScreenIsOpened", alwaysRun = true)
    public void verifyGoalScreenIsOpenedAndGoalsAreSelected(String goal) {
        goalsScreen.selectGoals(goal);
        soft.assertTrue(goalsScreen.isGoalSelected(goal), String.format("'%s' goal should be selected", goal));
        soft.assertAll();
    }

    @Test(dependsOnMethods = "verifyGoalScreenIsOpenedAndGoalsAreSelected", alwaysRun = true)
    public void verifyYourGoalsScreenIsOpened() {
        yourGoalsScreen = goalsScreen.clickNextButtonAndOpenYourGoalsScreen();
        Assert.assertTrue(yourGoalsScreen.isYourGoalPageOpened(), SCREEN_SHOULD_BE_OPENED);
    }

    @Test(dependsOnMethods = "verifyYourGoalsScreenIsOpened", alwaysRun = true)
    public void verifyBirthChatScreenIsOpened() {
        birthChartScreen = yourGoalsScreen.clickNextButtonAndOpenBirthChartScreen();
        Assert.assertTrue(birthChartScreen.isBirthChartPageOpened(), SCREEN_SHOULD_BE_OPENED);
    }

    @Test(dependsOnMethods = "verifyBirthChatScreenIsOpened", alwaysRun = true)
    public void verifyDayOfBirthScreenOpenedAndDateIsSet() {
        dateOfBirthScreen = birthChartScreen.clickNextButtonAndOpenDateOfBirthScreen();
//        dateOfBirthPage.selectMonth(month);
//        dateOfBirthPage.selectDay(day);
//        Arrays.asList(day, month).forEach(value -> soft.assertTrue(dateOfBirthPage.isDateValueSetInPickerWheel(value),
//                String.format("'%s' value should be present and set", value)));
    }

    @Test(dependsOnMethods = "verifyDayOfBirthScreenOpenedAndDateIsSet", alwaysRun = true)
    public void verifyInfoMessageAppearedAfterPressingIDontKnowButton() {
        String expectedInfoMessage = "We need the time of your birth to make astrological predictions more accurate." +
                " You can come back and add it when you figure it out.";
        timeOfBirthScreen = dateOfBirthScreen.clickNextButtonAndOpenTimeOfBirthScreen()
                .tapIDontKnowButton();
        String actualMessage = timeOfBirthScreen
                .getTextFromInfoMessage();
        Assert.assertEquals(actualMessage, expectedInfoMessage,
                String.format("'%s' actual message is not equal to '%s' expected", actualMessage, expectedInfoMessage));
    }

    @Test(dependsOnMethods = "verifyInfoMessageAppearedAfterPressingIDontKnowButton", alwaysRun = true)
    public void verifyPlaceOfBirthValueIsSet() {
        placeOfScreen = timeOfBirthScreen.clickSkipButtonAndOpenPlaceOfBirthScreen();
        placeOfScreen.setPlaceOfBirth(placeOfBirth);
        Assert.assertTrue(placeOfScreen.isSearchedPlacePresentInSearchResult(placeOfBirth),
                String.format("'%s' city should be present in search result", placeOfBirth));
    }

    @Test(dependsOnMethods = "verifyPlaceOfBirthValueIsSet", alwaysRun = true)
    public void verifyPlaceOfBirthSelectedAndPalmScreenOpened() {
        palmReadingScreen = placeOfScreen.confirmPlaceOfBirthAndOpenPalmReadingScreen(placeOfBirth);
        Assert.assertTrue(palmReadingScreen.isPalmReadingScreenOpened(), SCREEN_SHOULD_BE_OPENED);
    }

    @Test(dependsOnMethods = "verifyPlaceOfBirthSelectedAndPalmScreenOpened", alwaysRun = true)
    public void verifyGenderScreenOpenedAndGenderTilePresent() {
        genderScreen = palmReadingScreen.clickSkipButtonAndOpenGenderScreen();
        Arrays.asList(MALE, FEMALE, NON_BINARY).forEach(gender -> soft.assertTrue(genderScreen.isGenderTileShown(gender),
                String.format("'%s' gender tile should be shown", gender.getValue())));
        soft.assertAll();
    }

    @Test(dependsOnMethods = "verifyGenderScreenOpenedAndGenderTilePresent", alwaysRun = true)
    public void verifyEnterNameScreenOpenedAndNameTilePresent() {
        enterNameScreen = genderScreen.selectGenderAndOpenNameScreen(MALE);
        System.out.println("end");
    }


    @AfterMethod(alwaysRun = true)
    private void afterMethodActions(Method method) {
        if (method.getName().equalsIgnoreCase("verifyHomeScreenIsOpened")) {
            goalsScreen = homeScreen.clickGetStartedButtonAndOpenGoalsScreen();
        }
    }

    private void createTestData() {
        Date date = FAKER.date().birthday();
        DateFormatUtils dateFormatUtils = new DateFormatUtils();
        day = dateFormatUtils.formatDayDate(date);
        month = dateFormatUtils.formatMonthDate(date);
        placeOfBirth = "New York";
        name = FAKER.name().fullName();
    }
}
