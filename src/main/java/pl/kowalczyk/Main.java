package pl.kowalczyk;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(sumOfFuelRequiredFirstGo(ListOfMass()));
        System.out.println(sumOfFuelRequiredSecondGo(ListOfMass()));
    }

    private static int fuelRequired(int mass) {
        return (mass / 3) - 2;
    }

    private static List<Integer> ListOfMass() throws FileNotFoundException {
        File file = new File("SumMassOfSpacecraft.txtt");
        List<Integer> massOfEachPart = new ArrayList<>();

        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            massOfEachPart.add(Integer.parseInt(sc.nextLine()));
        }

        return massOfEachPart;
    }

    private static int sumOfFuelRequiredFirstGo(List<Integer> list) {
        int sum = 0;
        for (int value : list) {
            sum += fuelRequired(value);
        }
        return sum;
    }

    private static int sumOfFuelRequiredSecondGo(List<Integer> list) {
        int sum = 0;
        for (int value : list) {
            int addValue = 0;
            int restOfPart = fuelRequired(value);
            int sumOfPart = 0;
            while (fuelRequired(restOfPart) > 0) {
                addValue += restOfPart;
                restOfPart = fuelRequired(restOfPart);
            }
            sumOfPart = addValue + restOfPart;
            // Sum of all Part
            sum += sumOfPart;
        }
        return sum;
    }
}
