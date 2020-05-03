package com.example.java;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tasks {

    public List<ValueEntriesPair> sortByAmountOfEntries(int[] array) {
        HashMap<Integer, Integer> valueEntriesMap = new HashMap();
        for (int i = 0; i < array.length; i++) {
            if (!valueEntriesMap.containsKey(array[i])) {
                valueEntriesMap.put(array[i], 1);
            } else {
                valueEntriesMap.put(array[i], valueEntriesMap.get(array[i]) + 1);
            }
        }
        List<ValueEntriesPair> valueEntriesPairs = new ArrayList();
        valueEntriesMap.forEach((value, entries)->valueEntriesPairs.add(new ValueEntriesPair(value, entries)));
        valueEntriesPairs.sort((valueEntriesPair1, valueEntriesPair2) -> {
                if(valueEntriesPair1.entries > valueEntriesPair2.entries) {
                    return 1;
                } else if (valueEntriesPair1.entries == valueEntriesPair2.entries) {
                    return 0;
                } else {
                    return -1;
                }
        });
        return valueEntriesPairs;
    }

    @AllArgsConstructor
    @Data
    public class ValueEntriesPair {
        int value;
        int entries;
    }

    public boolean checkExpressionCorrection(String expression) {
        String betweenBrackets = "[^\\(\\[\\]\\)]*";
        StringBuilder regex = new StringBuilder("("+betweenBrackets+"(\\("+betweenBrackets+/*"[\\["+betweenBrackets+"\\]]?"+betweenBrackets+*/"\\)"+betweenBrackets+")|");
        regex.append("("+betweenBrackets+"\\["+betweenBrackets+/*"(\\("+betweenBrackets+"\\))?"+betweenBrackets+*/"\\])"+betweenBrackets+")");
        Pattern pattern = Pattern.compile(regex.toString());
        Matcher matcher = pattern.matcher(expression);
        while (matcher.find()) {
            /*int count = matcher.groupCount();
            System.out.println("groupCount is " + count);
            for(int i = 0; i<count; i++) {
                System.out.println(matcher.group(i));
                System.out.println(matcher.start(i));
                System.out.println(matcher.end(i));
            }*/
            if (matcher.matches()) {
                return true;
            }
            String previousRegex = regex.toString();
            regex.insert(0, "("+betweenBrackets+"(\\(");
            regex.append(/*"[\\[" + previousRegex + "\\]]?" + previousRegex +*/ "\\)"+betweenBrackets+")|");
            regex.append("("+betweenBrackets+"\\[" /*+ previousRegex + "(\\(" + previousRegex + "\\))?"*/ + previousRegex + "\\])"+betweenBrackets+")");
            pattern = Pattern.compile(regex.toString());
            matcher = pattern.matcher(expression);
        }
        return false;
    }

    public int transformRightZeroToOne(int number) {
        String revertedStringNumber = new StringBuilder(Integer.toString(number)).reverse().toString();
        StringBuilder stringResult = new StringBuilder(revertedStringNumber.replaceFirst("0", "1"));
        return Integer.parseInt(stringResult.reverse().toString());
    }
}
