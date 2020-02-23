package pl.kowalczyk.day4;

import pl.kowalczyk.FileUtil;

import java.util.*;
import java.util.stream.Collectors;

public class Day4 {

    public static void main(String[] args) {
        List<String> strings = FileUtil.loadFile("files/day4.txt");
        String[] split = strings.get(0).split("-");

        final int minValue = Integer.parseInt(split[0]);
        final int maxValue = Integer.parseInt(split[1]);

        List<String> listOfValues = createListOfValues(minValue, maxValue);
        List<String> correctList = correctList_String(listOfValues);
        List<String> revisedList = part2(correctList);


        System.out.println("Answer 4.1: " + correctList.size());
        System.out.println("Answer 4.2: " + revisedList.size());

    }


    public static List<String> createListOfValues(int min, int max) {
        List<String> listOfValues_String = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            listOfValues_String.add(String.valueOf(i));
        }
        return listOfValues_String;
    }

    private static List<String> part2(List<String> listOfValues_String) {
        List<String> correctList = new ArrayList<>();
        for (String string : listOfValues_String) {
            if (partOfALargerArray(string)) {
                correctList.add(string);
            }
        }
        return correctList;
    }

    private static List<String> correctList_String(List<String> listOfValues_String) {
        List<String> correctList = new ArrayList<>();
        for (String string : listOfValues_String) {
            if (doesItHaveAPair(string) && risingValues(string)) {
                correctList.add(string);
            }
        }
        return correctList;
    }


    private static boolean doesItHaveAPair(String string) {
        boolean isCorrect = false;
        for (int i = 1; i < string.length(); i++) {
            if (string.charAt(i - 1) == string.charAt(i)) {
                isCorrect = true;
                break;
            }
        }
        return isCorrect;
    }

    private static boolean risingValues(String string) {
        boolean isCorrect = true;
        for (int i = 1; i < string.length(); i++) {
            if (string.charAt(i - 1) > string.charAt(i)) {
                isCorrect = false;
                break;
            }
        }
        return isCorrect;
    }

    private static boolean partOfALargerArray(String string) {
        boolean isTrue = false;

        for (int i = 0; i < string.length()-1; i++) {
            String valueMinus="";
            String value = String.valueOf(string.charAt(i));
            String valuePlus = String.valueOf(string.charAt(i+1));
            String valuePlusPlus="";
            if (i > 0) {
                valueMinus = String.valueOf(string.charAt(i-1));
            }
            if (i <= 3){
                valuePlusPlus = String.valueOf(string.charAt(i+2));
            }

            if (value.equals(valuePlus) && !value.equals(valueMinus) && !value.equals(valuePlusPlus)){
                isTrue = true;
            }

//            if (i == 0) {
//                valueMinus = "";
//            } else {
//                valueMinus = String.valueOf(string.charAt(i-1));
//            }
//            if (i == 4){
//                valuePlusPlus = "";
//            } else {
//                valuePlusPlus = String.valueOf(string.charAt(i+2));
//            }
//
//            if (value.equals(valuePlus) && !value.equals(valueMinus) && !value.equals(valuePlusPlus)){
//                isTrue = true;
//            }

        }
        return isTrue;

    }


}
//589