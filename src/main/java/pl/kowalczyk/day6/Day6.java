package pl.kowalczyk.day6;

import pl.kowalczyk.FileUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day6 {
    public static void main(String[] args) {
        new Day6();
    }

    public Day6() {
        List<String> input = FileUtil.loadFileToList("files/day6.txt");
        Map<String, List<String>> orbits_Map = new HashMap<>();
        List<String> tempList1 = new ArrayList<>();
        List<String> tempList2 = new ArrayList<>();
        for (String string_Input : input) {
            String[] split_Input = string_Input.split("\\)");
            String k_Input = split_Input[0];
            String v_Input = split_Input[1];
            List<String> v_InputList = orbits_Map.get(k_Input);
            if (v_InputList == null) {
                v_InputList = new ArrayList<>();
            }
            v_InputList.add(v_Input);
            orbits_Map.put(k_Input, v_InputList);
        }
        int answer1 = getSumOfOrbits("COM", orbits_Map, 0);
        System.out.println("Answer 6.1: " + answer1);
        int answer2_part1 = previouObject("YOU", "COM", input, 0, tempList1) - 1;
        int answer2_part2 = previouObject("SAN", "COM", input, 0, tempList2) - 1;
        String firstCommonObject = getFirstCommonObject(tempList1, tempList2);
        int partOfSum1 = previouObject("YOU", firstCommonObject, input, 0, tempList1) - 1;
        int partOfSum2 = previouObject("SAN", firstCommonObject, input, 0, tempList1) - 1;
        int answer2 = partOfSum1 + partOfSum2;
        System.out.println("Answer 6.2: " + answer2);


    }

    int getSumOfOrbits(String currentOrbit, Map<String, List<String>> orbits_Map, int depth) {
        int sumOfOrbits = depth;
        if (orbits_Map.containsKey(currentOrbit)) {
            for (String newCurrentOrbit : orbits_Map.get(currentOrbit)) {
                sumOfOrbits += getSumOfOrbits(newCurrentOrbit, orbits_Map, depth + 1);
            }
        }
        return sumOfOrbits;
    }

    int previouObject(String current, String finalString, List<String> inputList, int depth, List<String> tempList) {
        int sumOfSteps = depth;

        String currentTemp = null;
        String nextStepTemp = null;

        if (!finalString.equals(current)) {
            for (String orbits : inputList) {
                currentTemp = orbits.substring(orbits.length() - 3);
                if (currentTemp.equals(current)) {
                    nextStepTemp = orbits.substring(0, 3);
                    tempList.add(currentTemp);
                    sumOfSteps = previouObject(nextStepTemp, finalString, inputList, depth + 1, tempList);

                    break;
                }
            }
        }
        return sumOfSteps;
    }

    String getFirstCommonObject(List<String> list1, List<String> list2) {
        String firstCommonObject = null;

        for (String string : list1) {
            if (list2.contains(string)) {
                firstCommonObject = string;
                break;
            }
        }
        return firstCommonObject;
    }
}
