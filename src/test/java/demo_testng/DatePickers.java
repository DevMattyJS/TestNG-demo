package demo_testng;

import org.testng.annotations.*;

public class DatePickers {
    // Class to check execution flow of testNG methods (based on annotations)

    // We can assign each test method to one or more groups
    // we can set up which groups of tests we want to run in testng.xml file
    @Test(groups = {"smoke", "e2e"})
    public void bootstrapDatePicker() {
        System.out.println("Test method 1: Bootstrap date picker");
    }

    @Test(groups = "e2e")
    public void jQueryDatePicker() {
        System.out.println("Test method 2: JQuery date picker");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Execute before each test method.");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Execute after each test method.");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("Execute before class: Date Pickers");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("Execute after class: Date Pickers");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("Execute before each test (defined in testng.xml file)");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("Execute after each test (defined in testng.xml file)");
    }

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Execute before the suite (defined in testng.xml file)");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("Execute after the suite (defined in testng.xml file)");
    }

}
