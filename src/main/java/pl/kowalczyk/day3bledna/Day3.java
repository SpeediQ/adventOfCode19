package pl.kowalczyk.day3bledna;

import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(ReadFile.read());
        System.out.println("Wielkość planszy to: "+2*totalSize()+" x "+2*totalSize());
        patter();

    }


    static void patter() throws FileNotFoundException {
        String s0 = ReadFile.read().get(0);
        String s1 = ReadFile.read().get(1);

        Pattern pattern = Pattern.compile("(\\D)(\\d{1,3})");
        Matcher matcher = pattern.matcher(s0);


        while (matcher.find()) {
            if (matcher.group(1).equals("R")) {
                System.out.println(matcher.group(2));
            }
        }

    }
// poprawić warunki jak już program będzie działał
    static int totalSize() throws FileNotFoundException {
        String s0 = ReadFile.read().get(0);
        String s1 = ReadFile.read().get(1);

        Pattern pattern = Pattern.compile("(\\D)(\\d{1,3})");
        Matcher matcher = pattern.matcher(s0);
        Matcher matcher1 = pattern.matcher(s1);
        int maxValue;
        int maxValue1 = 0;
        int maxValue2 = 0;
        while (matcher.find()) {
            if (Integer.parseInt(matcher.group(2)) > maxValue1) {
                maxValue1 = Integer.parseInt(matcher.group(2));
            }
        }
        while (matcher1.find()) {
            if (Integer.parseInt(matcher1.group(2)) > maxValue2) {
                maxValue2 = Integer.parseInt(matcher1.group(2));
            }
        }
        maxValue = maxValue1;
        if (maxValue1 < maxValue2) {
            maxValue = maxValue2;
        }
        return maxValue;
    }

}
