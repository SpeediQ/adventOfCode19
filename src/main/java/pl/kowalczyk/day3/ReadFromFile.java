package pl.kowalczyk.day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadFromFile {

    public List<String> readFromFile() throws FileNotFoundException {
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
