package pl.kowalczyk;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day2 {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Day2: Part1: " + modifiedList(fileToList()).get(0));
        System.out.println("Day2: Part2: " + nextTry(fileToList()));

    }

    private static List<Integer> fileToList() throws FileNotFoundException {
        List<Integer> integerList = null;
        File file = new File("day2.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String stringNumber = sc.nextLine();
            integerList = Arrays.stream(stringNumber.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        }

        return integerList;
    }

    private static List<Integer> modifiedList(List<Integer> inList) {
        List<Integer> outList = copyList(inList);
        for (int i = 0; i <= outList.size() / 4; i++) {
            outList.set(1, 12);
            outList.set(2, 2);
            int key = outList.get((4 * i));
            int in1Index = outList.get(1 + (4 * i));
            int in2Index = outList.get(2 + (4 * i));
            int outIndex = outList.get(3 + (4 * i));
            if (key == 1) {
                int outValue = outList.get(in1Index) + outList.get(in2Index);
                outList.set(outIndex, outValue);
            }
            if (key == 2) {
                int outValue = outList.get(in1Index) * outList.get(in2Index);
                outList.set(outIndex, outValue);
            }
            if (key == 99) {
                return outList;
            }
        }
        return outList;
    }

    private static int nextTry(List<Integer> inList) {

        List<Integer> copyOfInList = copyList(inList);




        int wantedValue = 19690720;
        int valueNoun = 0;
        int valueVerb = 0;


        copyOfInList.set(1, valueNoun);
        copyOfInList.set(2, valueVerb);

        while (valueVerb < 99 && !(modifiedList(copyOfInList)).get(0).equals(wantedValue)) {
            try {
                valueVerb++;
                copyOfInList.set(2, valueVerb);
                if (!(modifiedList(copyOfInList)).get(0).equals(wantedValue)) {
                    for (valueNoun = 0; valueNoun <= 99; valueNoun++) {
                        copyOfInList.set(1, valueNoun);
                        if ((modifiedList(copyOfInList)).get(0).equals(wantedValue)) {
                            break;
                        }
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("błąd");
            }
        }


        return 100 * valueNoun + valueVerb;
    }

    private static List<Integer> copyList(List<Integer> oldList) {
        return new ArrayList<>(oldList);
    }


}
