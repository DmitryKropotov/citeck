package com.example.java;

import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Tasks tasks = new Tasks();
        //0 - 1, 1 - 1, 3 - 2, 5 - 2, 4 - 3, 10 - 3
        int[] array = {3, 5, 4, 3, 10, 1, 10, 4, 5, 4, 10, 0};
        List<Tasks.ValueEntriesPair> valueEntries = tasks.sortByAmountOfEntries(array);
        System.out.println(valueEntries);

        System.out.println(tasks.checkExpressionCorrection("([(dc)hj]jhj)"));//true
        System.out.println(tasks.checkExpressionCorrection("([](cdxcz)[cxzc]()fwe)([]df(dsd)f)"));//true
        System.out.println(tasks.checkExpressionCorrection("vd"));//false
        System.out.println(tasks.checkExpressionCorrection("[[]df[]]()"));//true
        System.out.println(tasks.checkExpressionCorrection("[[]df[]]([d])"));//true
        System.out.println(tasks.checkExpressionCorrection("[[]df[]()dsdf]"));//true
        System.out.println(tasks.checkExpressionCorrection("[[]df[]()dsdf](())"));//true
        System.out.println(tasks.checkExpressionCorrection("([ff]rr)()"));//true
        System.out.println(tasks.checkExpressionCorrection("([[]df[]]())"));//true
        System.out.println(tasks.checkExpressionCorrection("(p)([[]df[]]())"));//true
        System.out.println(tasks.checkExpressionCorrection("()fed"));//true
        System.out.println(tasks.checkExpressionCorrection("dsf()]"));//false

        System.out.println(tasks.transformRightZeroToOne(12309303));
    }
}
