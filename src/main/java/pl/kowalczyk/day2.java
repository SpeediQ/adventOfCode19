package pl.kowalczyk;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class day2 {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(fileToList());
        System.out.println(modifiedList(fileToList()));
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
    private static List<Integer> nounAndVerbList(List<Integer> oldList){
        List<Integer> newStep2List = oldList;
        modifiedList(newStep2List);

        return null;
    }

    private static List<Integer> modifiedList(List<Integer> oldList) {
        List<Integer> newList = oldList;
        newList.set(1,12);
        newList.set(2,2);
        for (int i = 0; i <= newList.size() / 4; i++) {
            int key = newList.get(0 + (4 * i));
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

}
