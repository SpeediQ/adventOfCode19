package pl.kowalczyk.day1;

import pl.kowalczyk.FileUtil;

import java.io.FileNotFoundException;
import java.util.List;

public class Day1 {
    public static void main(String[] args) throws FileNotFoundException {
        Day1 firstGo = new Day1();
        Day1 secondGo = new Day1();
        List<Integer> listOfMass = FileUtil.conversionFromStringToInteger(FileUtil.loadFileToList("files/day1.txt"));
        int sumOfTheFuelRequirements = firstGo.sumOfFuelRequiredFirstGo(listOfMass);
        int sumOfTheFuelRequirements_WithMassOfTheAddedFuel = secondGo.sumOfFuelRequiredSecondGo(listOfMass);

        System.out.println("Answer 1.1: " + sumOfTheFuelRequirements);
        System.out.println("Answer 1.2: " + sumOfTheFuelRequirements_WithMassOfTheAddedFuel);
    }

    private static int fuelRequired(int mass) {
        return (mass / 3) - 2;
    }


    private int sumOfFuelRequiredFirstGo(List<Integer> list) {
        int sum = 0;
        for (int value : list) {
            sum += fuelRequired(value);
        }
        return sum;
    }

    private int sumOfFuelRequiredSecondGo(List<Integer> list) {
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
