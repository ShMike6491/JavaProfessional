package reflection;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        try {
            TestSuite.start(Test1.class, Test2.class);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}

class Test1 {
    private int num;
    public Test1() {
        num = 1;
    }
    @BeforeSuite
    private void test0() {
        System.out.println("Before test from test1");
    }
    @Test(priority = 4)
    protected void test1() {
        System.out.println(4);
    }
    @AfterSuite
    public void test2() {
        System.out.println("After test from test1");
    }
    @Test(priority = 3)
    public void test3() {
        System.out.println(3);
    }
    @Test
    private void test4() {
        System.out.println(5);
    }
    @Test(priority = 9)
    private void test5() {
        System.out.println(9);
    }
}

class Test2 {
    @BeforeSuite
    private void test0() {
        System.out.println("Before test from test2");
    }
    @Test(priority = 1)
    protected void test1() {
        System.out.println(1);
    }
    @AfterSuite
    public void test2() {
        System.out.println("After test from test2");
    }
    @Test(priority = 3)
    public void test3() {
        System.out.println(3);
    }
    @Test
    private void test4() {
        System.out.println(5);
    }
    @Test(priority = 1)
    private void test5() {
        System.out.println(1);
    }
}
