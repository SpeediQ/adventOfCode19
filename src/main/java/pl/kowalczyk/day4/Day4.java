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
        int differentPasswords = correctList.size();


        System.out.println("Answer 4.1: " + differentPasswords);

    }




    public static List<String> createListOfValues(int min, int max) {
        List<String> listOfValues_String = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            listOfValues_String.add(String.valueOf(i));
        }
        return listOfValues_String;
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
        boolean isCorrect = false;
        isCorrect = true;
        for (int i = 1; i < string.length(); i++) {
            if (string.charAt(i - 1) > string.charAt(i)) {
                isCorrect = false;
                break;
            }
        }
        return isCorrect;
    }




}
//589