package tests;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class SimpleTestClass {
    @BeforeSuite
    void beforeSuite(){
        System.out.println("beforeSuite");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("beforeTest");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("beforeClass");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("beforeMethod");
    }

    @AfterSuite
    void afterSuite(){
        System.out.println("afterSuite");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("afterTest");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("afterClass");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("afterMethod");
    }

    @Test
    public void test1(){
        System.out.println("test1");
        boolean a=true;
        boolean b=false;
        Assert.assertTrue(a);
        Assert.assertFalse(b);
        Assert.assertEquals("alma","alma");
    }

    @Test
    public void test2(){
        System.out.println("test2");
        String a = "alma";
        Assert.assertEquals("szilva", a, "Test failed");
        System.out.println("End of test2");
    }

    @Test
    public void test3(){
        System.out.println("test3");
        String a = "alma";
        if(a.equals("alma")){
            throw new SkipException("Skipped");
        }
    }

    @Test
    public void test4(){
        SoftAssert soft= new SoftAssert();
        soft.assertEquals("xyz","abc");
        soft.assertEquals("szilva","alma");
        soft.assertAll();
    }

}
