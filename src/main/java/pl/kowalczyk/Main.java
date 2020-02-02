package pl.kowalczyk;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println(sumOfFuelRequired());
    }

    private static int fuelRequired(int mass) {
        return (mass / 3) - 2;
    }

    private static int sumOfFuelRequired() {
        File file = new File("SumMassOfSpacecraft.txt");
        int sum = 0;
        int value;
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                value = fuelRequired(Integer.parseInt(sc.nextLine()));
                sum += value;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Brak PLIKU SumMassOfSpacecraft.txt !");
            e.printStackTrace();
        }
        return sum;
    }
}
