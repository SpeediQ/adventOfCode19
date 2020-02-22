package pl.kowalczyk;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileUtil {
    public static List<String> loadFile(String location) {
        File file = new File(location);
        List<String> listOfStrings = new ArrayList<>();

        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (scanner != null) {
            while (scanner.hasNextLine()) {
                listOfStrings.add(scanner.nextLine());
            }
        }
        return listOfStrings;
    }

    public static List<Integer> conversionFromStringToInteger(List<String> listOfStrings){
        List<Integer> listOfIntegers = new ArrayList<>();
        for (String string : listOfStrings) {
            listOfIntegers.add(Integer.parseInt(string));
        }
        return listOfIntegers;
    }
}
