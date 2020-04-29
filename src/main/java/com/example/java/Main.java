package com.example.java;

import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Tasks tasks = new Tasks();
        //3 - 2, 5 - 2, 4 - 3, 10 - 3, 1 - 1, 0 - 1
        int[] array = {3, 5, 4, 3, 10, 1, 10, 4, 5, 4, 10, 0};
        List<Tasks.ValueEntriesPair> valueEntries = tasks.sortByAmountOfEntries(array);
        System.out.println(valueEntries);
        System.out.println(tasks.checkExpressionCorrection("(gffd]"));
    }
}
