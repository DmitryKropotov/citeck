package com.example.java;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        StringBuilder regex = new StringBuilder("\\([^\\(\\[\\)\\]]*\\[[^\\(\\[\\)\\]]*\\][^\\(\\[\\)\\]]*\\)");
        Pattern pattern = Pattern.compile(regex.toString());
        Matcher matcher = pattern.matcher(expression);
        while (matcher.matches()) {
            if (matcher.groupCount() == 0) {
                return false;
            }
            regex.insert(0, "\\(");
            regex.append("\\[" + regex + "\\]" + regex + "\\)");
            //regex = "\\(" + regex + "\\[" + regex + "\\]" + regex + "\\)";
        }
        return true;
    }
}
