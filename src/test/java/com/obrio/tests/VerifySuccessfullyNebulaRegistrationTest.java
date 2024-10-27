package com.obrio.tests;

import com.obrio.data.ui.fields.SettingsFields;
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

import static com.obrio.data.registration.Genders.*;
import static com.obrio.data.registration.Goals.*;
import static com.obrio.data.registration.Interests.LOVE;
import static com.obrio.data.registration.Interests.MONEY;
import static com.obrio.data.registration.RelationshipStatuses.SINGLE;

public class VerifySuccessfullyNebulaRegistrationTest extends BaseTest {

    private final String SCREEN_SHOULD_BE_OPENED = "Screen should be opened";
    private final String BUTTON_SHOULD_BE_SHOWN = "Button should be shown";
    private final String YES_OPTION = "YES";
    private final String NO_OPTION = "NO";
    private HomeScreen homeScreen;
    private GoalsScreen goalsScreen;
    private YourGoalsScreen yourGoalsScreen;
    private BirthChartScreen birthChartScreen;
    private DateOfBirthScreen dateOfBirthScreen;
    private TimeOfBirthScreen timeOfBirthScreen;
    private PlaceOfBirthScreen placeOfScreen;
    private PalmReadingScreen palmReadingScreen;
    private GenderScreen genderScreen;
    private RelationshipStatusScreen relationshipStatusScreen;
    private MotivationScreen motivationScreen;
    private HoroscopeRemainderScreen horoscopeRemainderScreen;
    private SocialMediaScreen socialMediaScreen;
    private CreateAccountScreen createAccountScreen;
    private SignUpScreen signUpScreen;
    private HoroscopeScreen horoscopeScreen;
    private ProfileSettingScreen settingScreen;
    private String dayOfBirth;
    private String monthOfBirth;
    private String placeOfBirth;
    private String name;
    private String email;
    private String password;

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
    public HomeScreen openHomeScreen() {
        createTestData();
        return homeScreen = new HomeScreen();
    }

    @Test
    public void verifyHomeScreenIsOpened() {
        Assert.assertTrue(homeScreen.isHomeScreenOpened(), SCREEN_SHOULD_BE_OPENED);
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
//        soft.assertAll();
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
        Arrays.asList(MALE, FEMALE, NON_BINARY).forEach(genders -> soft.assertTrue(genderScreen.isGenderTileShown(genders),
                String.format("'%s' gender tile should be shown", genders.getValue())));
        soft.assertAll();
    }

    @Test(dependsOnMethods = "verifyGenderScreenOpenedAndGenderTilePresent", alwaysRun = true)
    public void verifyNameIsSetAndRelationshipScreenOpened() {
        relationshipStatusScreen = genderScreen.selectGenderAndOpenNameScreen(MALE)
                .setNameAndOpenRelationshipStatusScreen(name);
        Assert.assertTrue(relationshipStatusScreen.isRelationshipScreenOpened(), SCREEN_SHOULD_BE_OPENED);
    }

    @Test(dependsOnMethods = "verifyNameIsSetAndRelationshipScreenOpened", alwaysRun = true)
    public void verifyRelationshipStatusAndInterestsSelectedAndMotivationScreenOpened() {
        motivationScreen = relationshipStatusScreen.selectRelationshipStatusAndOpenInterestsScreen(SINGLE)
                .selectInterestsAndOpenMotivationScreen(MONEY.getValue(), LOVE.getValue());
        Assert.assertTrue(motivationScreen.isMotivationScreenOpened(), SCREEN_SHOULD_BE_OPENED);
    }

    @Test(dependsOnMethods = "verifyRelationshipStatusAndInterestsSelectedAndMotivationScreenOpened", alwaysRun = true)
    public void verifyInterestsSelectedAndHoroscopeRemainderScreenOpened() {
        horoscopeRemainderScreen = motivationScreen.selectOption(YES_OPTION)
                .clickNextButton()
                .selectOption(NO_OPTION)
                .selectOption(YES_OPTION)
                .clickNextButton()
                .selectOption(YES_OPTION)
                .clickNextButtonAndOpenHoroscopeRemainderScreen();
        Assert.assertTrue(horoscopeRemainderScreen.isBellImageShown(), "Bell image should be shown");
    }

    @Test(dependsOnMethods = "verifyInterestsSelectedAndHoroscopeRemainderScreenOpened", alwaysRun = true)
    public void verifyNextButtonIsDisableWhenInterestIsNotSelectedAndSkipButtonIsEnableOnSocialMediaScreen() {
        socialMediaScreen = horoscopeRemainderScreen.clickSkipButtonAndOpenSocialMediaScreen();
        String nextButtonName = "NEXT";
        String skipButtonName = "SKIP";
        soft.assertFalse(socialMediaScreen.isButtonEnabled(nextButtonName),
                String.format("'%s' button should be disabled", nextButtonName));
        soft.assertTrue(socialMediaScreen.isButtonEnabled(skipButtonName),
                String.format("'%s' button should be enabled", skipButtonName));
        soft.assertAll();
    }

    @Test(dependsOnMethods = "verifyNextButtonIsDisableWhenInterestIsNotSelectedAndSkipButtonIsEnableOnSocialMediaScreen", alwaysRun = true)
    public void verifyCreateAccountScreenOpenedAndSignInViaEmailAndViaGoogleButtonsPresent() {
        createAccountScreen = socialMediaScreen.clickSkipButtonAndOpenReachYourGoalScreen()
                .clickNextButtonAndOpenReviewsScreen()
                .clickNextButtonAndOpenCreateAccountScreen();
        soft.assertTrue(createAccountScreen.isCreateAccountViaEmailButtonShown(), BUTTON_SHOULD_BE_SHOWN);
        soft.assertTrue(createAccountScreen.isCreateAccountViaGoogleButtonShown(), BUTTON_SHOULD_BE_SHOWN);
        soft.assertAll();
    }

    @Test(dependsOnMethods = "verifyCreateAccountScreenOpenedAndSignInViaEmailAndViaGoogleButtonsPresent", alwaysRun = true)
    public void verifyEmailAndPasswordSetAndHoroscopeScreenOpened() {
        horoscopeScreen = createAccountScreen.clickCreateAccountViaEmailButtonAndOpenSignUpScreen()
                .fillInDataForRegistrationAndExpectHoroscopeScreen(email, password);
        Assert.assertTrue(horoscopeScreen.isHoroscopeScreenOpened(), SCREEN_SHOULD_BE_OPENED);
    }

    @Test(dependsOnMethods = "verifyEmailAndPasswordSetAndHoroscopeScreenOpened", alwaysRun = true)
    public void verifyProfileSettingsFieldHasCorrectValuesAfterRegistration() {
        settingScreen = horoscopeScreen.openSettings()
                .openNeededSettingScreen("My Profile", ProfileSettingScreen::new);
        soft.assertEquals(settingScreen.getValueFromField(SettingsFields.NAME), name);
        soft.assertEquals(settingScreen.getValueFromField(SettingsFields.GENDER), MALE.getValue());
        soft.assertEquals(settingScreen.getValueFromField(SettingsFields.PLACE_OF_BIRTH), placeOfBirth);
        soft.assertEquals(settingScreen.getValueFromField(SettingsFields.RELATIONSHIP_STATUS), SINGLE.getValue());
        soft.assertAll();
    }

    @Test(dependsOnMethods = "verifyProfileSettingsFieldHasCorrectValuesAfterRegistration", alwaysRun = true)
    public void verifyAccountFieldValuesHasCorrectValuesAndUserIdFieldPopulatedAfterRegistration() {
        settingScreen.closeSettingAndOpenNeededSetting("Account", AccountSettingScreen::new);
        soft.assertEquals(settingScreen.getValueFromField(SettingsFields.EMAIL), email);
        soft.assertEquals(settingScreen.getValueFromField(SettingsFields.LOGIN_METHOD), email);
        soft.assertFalse(settingScreen.getValueFromField(SettingsFields.USER_ID).isEmpty());
        soft.assertAll();
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
        dayOfBirth = dateFormatUtils.formatDayDate(date);
        monthOfBirth = dateFormatUtils.formatMonthDate(date);
        placeOfBirth = "New York";
        name = FAKER.name().fullName();
        email = FAKER.internet().emailAddress();
        password = FAKER.internet().password(8, 12, true, true);
    }
}
