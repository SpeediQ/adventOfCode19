package pl.kowalczyk.day3bledna;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFile {


    public static List<String> read() throws FileNotFoundException {
        List<String> WiresList = new ArrayList<>();
        File file = new File("day3.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String stringNumber = sc.nextLine();
            WiresList.add(stringNumber);
        }
        return WiresList;
    }
}
