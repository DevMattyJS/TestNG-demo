package demo_testng;

import org.testng.annotations.*;

public class ListBoxes {

    @BeforeClass
    public void beforeClass() {
        System.out.println("Execute before class: List Box");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Execute before each test method.");
    }

    @Test(groups = "smoke")
    public void bootstrapListBox() {
        System.out.println("Test method 3: Bootstrap list box");
    }

    @Test(groups = {"regression", "smoke"})
    public void jQueryListBox() {
        System.out.println("Test method 4: JQuery list box");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Execute after each test method.");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("Execute after class: List Boxes");
    }

    @BeforeGroups
    public void beforeGroups() {
        System.out.println("Execute before each group");
    }

    @AfterGroups
    public void afterGroups() {
        System.out.println("Executes after each group");
    }
}
