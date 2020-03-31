package pl.kowalczyk.day6;

import jdk.swing.interop.SwingInterOpUtils;
import pl.kowalczyk.FileUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day6 {
    public static void main(String[] args) {
        List<String> listOfStrings = FileUtil.loadFileToList("files/day6.txt");

        String firstObject = null;

        for (int i = 0; i < listOfStrings.size(); i++) {
            if (listOfStrings.get(i).substring(0, 3).contains("COM")) {
                firstObject = listOfStrings.get(i);
                break;
            }
        }

        System.out.println("Start from: " + firstObject + ", index: " + listOfStrings.indexOf(firstObject));
        boolean run = true;
        List<String> tempList = new ArrayList<>();
        int value = 1;
        int sum = 1;
        String object = firstObject;
        tempList.add(object);
        System.out.println(object);
        int i = 0;
        for (String string : listOfStrings) {
//            tempString = part2(object);
            System.out.print(part2(object) + "||" + part1(string) + "\n");


            if (part2(object).equals(part1(string)) && object != firstObject) {
                System.out.println("dodac: " + i);
                value++;
                sum += value;
                tempList.add(string);
                object = string;
            } else {
                System.out.println("odjąc: " + i);

                value--;
                object = tempList.get(tempList.size() - 1);

//                listOfStrings.remove(string);
            }
            i++;
        }
        System.out.println(listOfStrings);
        System.out.println(tempList);


    }

    private static String part1(String string) {
        return string.substring(0, 3);
    }

    private static String part2(String string) {
        return string.substring(4, string.length());
    }

}
