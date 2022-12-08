package com.luv2code.junitdemo;

import java.util.List;

public class DemoUtils {
    // equals and not equals
    public int add(int a, int b) {
        return a + b;
    }

    // null and not null
    public Object checkNull(Object obj) {
        return obj;
    }

    // same and not same
    private final String academy = "Luv2Code Academy";

    public String getAcademy() {
        return academy;
    }

    @SuppressWarnings("FieldCanBeLocal")
    private final String academyDuplicate = academy;

    public String getAcademyDuplicate() {
        return academyDuplicate;
    }

    // true and false
    public Boolean isGreater(int n1, int n2) {
        return n1 > n2;
    }

    // array equals
    private final String[] firstThreeLettersOfAlphabet = {"A", "B", "C"};

    public String[] getFirstThreeLettersOfAlphabet() {
        return firstThreeLettersOfAlphabet;
    }

    // iterable equals, lines match
    private final List<String> academyInList = List.of("luv", "2", "code");

    public List<String> getAcademyInList() {
        return academyInList;
    }

    // throws and does not throw
    public String throwException(int a) throws Exception {
        if (a < 0) {
            throw new Exception("Value should be greater than or equal to 0");
        }
        return "Value is greater than or equal to 0";
    }

    // timeout
    public void checkTimeout() throws InterruptedException {
        System.out.println("I am going to sleep");
        Thread.sleep(2000);
        System.out.println("Sleeping over");
    }

    // not covered method
    @SuppressWarnings("unused")
    public int multiply(int a, int b) {
        return a * b;
    }
}