package com.luv2code.junitdemo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

class ConditionalTest {
    @Test
    @Disabled("Don't run until JIRA #123 is resolved")
    void basicTest() {
        // execution method and perform asserts
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void testForWindowsOnly() {
        // execution method and perform asserts
    }

    @Test
    @EnabledOnOs(OS.MAC)
    void testForMacOnly() {
        // execution method and perform asserts
    }

    @Test
    @EnabledOnOs({OS.MAC, OS.WINDOWS})
    void testForMacAndWindowsOnly() {
        // execution method and perform asserts
    }

    @Test
    @EnabledOnOs(OS.LINUX)
    void testForLinuxOnly() {
        // execution method and perform asserts
    }

    @Test
    @EnabledOnJre(JRE.JAVA_17)
    void testForJava17Only() {
        // execution method and perform asserts
    }

    @Test
    @EnabledOnJre(JRE.JAVA_11)
    void testForJava11Only() {
        // execution method and perform asserts
    }

    @Test
    @EnabledForJreRange(min = JRE.JAVA_13, max = JRE.JAVA_18)
    void testForJavaRange() {
        // execution method and perform asserts
    }

    @Test
    @EnabledForJreRange(min = JRE.JAVA_11)
    void testForJavaRangeMin() {
        // execution method and perform asserts
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "LUV2CODE_ENV", matches = "DEV")
    void testForDevEnvironmentOnly() {
        // execution method and perform asserts
    }

    @Test
    @EnabledIfSystemProperty(named = "LUV2CODE_SYS_PROP", matches = "CI_CD_DEPLOY")
    void testForSystemPropertyOnly() {
        // execution method and perform asserts
    }
}