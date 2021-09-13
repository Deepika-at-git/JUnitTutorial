package com.tutorials;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import jdk.jfr.Enabled;
import org.junit.Rule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import org.junit.rules.ExpectedException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("When running MathUtilsTest")
public class MathUtilsTest {

    MathUtils obj;
    TestInfo testInfo;
    TestReporter testReporter;

    @BeforeAll
     void beforeAllInit(){
        System.out.println("This need to run before all");
    }

    @AfterAll
     void afterAllInit(){
        System.out.println("End execution");
    }

    @BeforeEach
    void init(TestInfo testInfo, TestReporter testReporter){
        this.testInfo=testInfo;
        this.testReporter=testReporter;
        obj = new MathUtils();
        testReporter.publishEntry("Running " + testInfo.getDisplayName() + " with tags " + testInfo.getTags() );
    }

    @AfterEach
    void cleanup(){
        System.out.println("Cleaning up...");
    }

    @Tag("Math")
    @Nested
    @DisplayName("Displays Add methods")
    class AddTest{

        @Test
        @DisplayName("Tests two positive integers")
        void test_add_with_positive_integers(){

            assertEquals(3,obj.add(1,2),() -> "should return the correct sum");
        }

        @Test
        @DisplayName("Tests two negative integers")
        void test_add_with_negative_integers(){
            assertEquals(-4,obj.add(-2,-2),() ->"should return the correct sum");
        }

    }

    @Tag("Math")
    @Test
    @DisplayName("Multiply method")
    void test_multiply_two_integers(){

        assertAll(
                () -> assertEquals(4,obj.multiply(2,2)),
                () -> assertEquals(0,obj.multiply(0,2)),
                () -> assertEquals(4,obj.multiply(-2,-2))
        );
    }

    @Tag("Math")
    @Test
    @EnabledOnOs(OS.LINUX)
    public void test_divide_with_positive_integers(){
        assertThrows(ArithmeticException.class, () -> obj.divide(1,0));
    }

    @Tag("Circle")
    @Test
    @RepeatedTest(4)
    public void test_area_of_circle(){
//        boolean isServerUp=true;
//        assumeTrue(isServerUp);
        assertEquals(12.566370614359172,obj.areaOfCircle(2),() -> "To compute area of circle");
    }

    @Test
    @Disabled
    @DisplayName("TDD method...this should be skipped")
     void testDisable(){
        fail("This method should not be executed");
    }

}
