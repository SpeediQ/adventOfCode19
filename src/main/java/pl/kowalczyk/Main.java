package pl.kowalczyk;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println(sumOfFuelRequiredFirstGo(ListOfMass()));
        System.out.println(sumOfFuelRequiredSecondGo(ListOfMass()));
    }

    private static int fuelRequired(int mass) {
        return (mass / 3) - 2;
    }
    private static List<Integer> ListOfMass() {
        File file = new File("SumMassOfSpacecraft.txtt");
        List<Integer> massOfEachPart = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                massOfEachPart.add(Integer.parseInt(sc.nextLine()));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Brak PLIKU SumMassOfSpacecraft.txt !");
            e.printStackTrace();
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
