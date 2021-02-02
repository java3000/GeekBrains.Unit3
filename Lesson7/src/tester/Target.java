package tester;

public class Target {

    @BeforeSuite
    void BeforeSuite() {
        System.out.println("BeforeSuite");
    }

    @AfterSuite
    void AfterSuite() {
        System.out.println("AfterSuite");
    }

    @Test(priority = 1)
    void test1() {
        System.out.println("test1");
    }

    @Test(priority = 3)
    void test3() {
        System.out.println("test3");
    }

    @Test(priority = 2)
    void test2() {
        System.out.println("test2");
    }
}
