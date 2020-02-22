package pl.kowalczyk.day2;

import pl.kowalczyk.FileUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day2 {
    public static void main(String[] args) throws FileNotFoundException {
        Day2 part1 = new Day2();
        Day2 part2 = new Day2();
        List<String> listOfStrings = FileUtil.loadFile("files/day2.txt");
        List<Integer> listOfIntegers = conversionToIntegerList(listOfStrings);
        int valueOfFirstposition = part1.modifiedList(listOfIntegers).get(0);
        int result = part2.valueSearch(listOfIntegers);
        System.out.println("Answer 2.1: " + valueOfFirstposition);
        System.out.println("Answer 2.2: " + result);


    }

    private static List<Integer> conversionToIntegerList(List<String> listOfStrings) {
        List<Integer> integerList = null;
        String allInOne = listOfStrings.get(0);

        integerList = Arrays.stream(allInOne.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        return integerList;
    }


    private List<Integer> modifiedList(List<Integer> inList) {

        inList.set(1, 12);
        inList.set(2, 2);
        return modificationKey(inList);
    }

    private static List<Integer> modificationKey(List<Integer> outList) {
        List<Integer> newList = new ArrayList<>(outList);
        for (int i = 0; i <= outList.size() / 4; i++) {
            int key = newList.get((4 * i));
            int in1Index = newList.get(1 + (4 * i));
            int in2Index = newList.get(2 + (4 * i));
            int outIndex = newList.get(3 + (4 * i));
            if (key == 1) {
                int outValue = newList.get(in1Index) + newList.get(in2Index);
                newList.set(outIndex, outValue);
            }
            if (key == 2) {
                int outValue = newList.get(in1Index) * newList.get(in2Index);
                newList.set(outIndex, outValue);
            }
            if (key == 99) {
                return newList;
            }
        }
        return newList;
    }

    private int valueSearch(List<Integer> copyOfInList) {
        int wantedValue = 19690720;
        int valueNoun = 0;
        int valueVerb = 0;
        copyOfInList.set(1, valueNoun);
        copyOfInList.set(2, valueVerb);
        while (valueVerb < 99 && !(modificationKey(copyOfInList)).get(0).equals(wantedValue)) {
            try {
                valueVerb++;
                copyOfInList.set(2, valueVerb);
                if (!(modificationKey(copyOfInList)).get(0).equals(wantedValue)) {
                    for (valueNoun = 0; valueNoun <= 99; valueNoun++) {
                        copyOfInList.set(1, valueNoun);
                        if ((modificationKey(copyOfInList)).get(0).equals(wantedValue)) {
                            break;
                        }
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }
        return 100 * valueNoun + valueVerb;
    }


}
