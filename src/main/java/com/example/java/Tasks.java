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
        StringBuilder regex = new StringBuilder("(([^\\(\\[\\]\\)]*\\([^\\(\\[\\]\\)]*\\)[^\\(\\[\\]\\)]*)|([^\\(\\[\\]\\)]*\\[[^\\(\\[\\]\\)]*\\][^\\(\\[\\]\\)]*))+");
        Pattern pattern = Pattern.compile(regex.toString());
        Matcher matcher = pattern.matcher(expression);
        List<String> previousRegexes = new ArrayList();
        while (matcher.find()) {
            if (matcher.matches()) {
                return true;
            }
            previousRegexes.add(regex.toString());
            List<String> regexes = combineAll(previousRegexes);
            for (String reg: regexes) {
                if (expression.matches(reg)) {
                    return true;
                }
            }
            StringBuilder newRegex = new StringBuilder("(((("+betweenBrackets+"\\("+betweenBrackets+"(");
            for (int i = 0; i < regexes.size(); i++) {
                String reg = regexes.get(i);
                newRegex.append("("+reg+")");
                if (i<regexes.size()-1) {
                    newRegex.append("|");
                }
            }
            newRegex.append(")"+betweenBrackets+"\\)"+betweenBrackets+")))|((("+betweenBrackets+"\\["+betweenBrackets+"(");
            for (int i = 0; i < regexes.size(); i++) {
                String reg = regexes.get(i);
                newRegex.append(reg);
                if (i<regexes.size()-1) {
                    newRegex.append("|");
                }
            }
            newRegex.append(")"+betweenBrackets+"\\]"+betweenBrackets+")))+)");
            for (int i = 0; i < regexes.size(); i++) {
                String reg = regexes.get(i);
                newRegex.append("(");
                newRegex.append(reg);
                newRegex.append(")?");
            }
            regex = newRegex;
            pattern = Pattern.compile(regex.toString());
            matcher = pattern.matcher(expression);
        }
        return false;
    }

    private List<String> combineAll(List<String> list) {
        if (list.size() == 0) {
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        if (list.size() == 1) {
            result.add(list.get(0));
        } else {
            for (int i = 1; i<=list.size(); i++) {
               if (i==1) {
                   for (int j = 0; j < list.size(); j++) {
                       result.add(list.get(j));
                   }
                   continue;
               }
               for (int j = i - 1; j <= list.size()-1; j++) {
                    for (int k = j + 1; k <= list.size(); k++) {
                        StringBuilder combination = new StringBuilder("");
                        for (int l = j - i + 1; l <= j; l++) {
                            combination.append((list.get(l)));
                        }
                        result.add(combination.toString());
                        if (k < list.size()) {
                            String buffer = list.get(j);
                            list.set(j, list.get(k));
                            list.set(k, buffer);
                        }
                    }
               }
            }
        }
        return result;
    }

    public int transformRightZeroToOne(int number) {
        String revertedStringNumber = new StringBuilder(Integer.toString(number)).reverse().toString();
        StringBuilder stringResult = new StringBuilder(revertedStringNumber.replaceFirst("0", "1"));
        return Integer.parseInt(stringResult.reverse().toString());
    }
}
