package com.obrio.tests;

import com.obrio.pages.GoalsPage;
import com.obrio.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static com.obrio.data.GoalValues.FIND_PERFECT_MATCH;
import static com.obrio.data.GoalValues.UNDERSTAND_MYSELF;

public class VerifySuccessfullyNebulaRegistrationTest extends  BaseTest{

    private HomePage homePage;
    private GoalsPage goalsPage;

//    @DataProvider(name = "goalsDataProvider")
//    public Object[][] goalsDataProvider() {
//        return new Object[][]{
//                {UNDERSTAND_MYSELF.getValue()},
//                {FIND_PERFECT_MATCH.getValue()},
//        };
//    }

    @BeforeClass(alwaysRun = true)
    public HomePage openHomePage() {
        return homePage = new HomePage();
    }

    @Test
    public void verifyHomePageIsOpened() {
        Assert.assertTrue(homePage.isHomePageOpened(), "Home page should be opened");
    }

    @Test(dependsOnMethods = "verifyHomePageIsOpened", alwaysRun = true)
    public void verifyGoalPageIsOpenedAndGoalsSelected() {
        List<String> goals = Arrays.asList(UNDERSTAND_MYSELF.getValue(), FIND_PERFECT_MATCH.getValue());
        goalsPage = homePage.openGoalsPage();
        goals.forEach(goal -> goalsPage.selectGoals(goal));
        goals.forEach(goal -> soft.assertTrue(goalsPage.isGoalSelected(goal)));
        soft.assertAll();
    }
}
