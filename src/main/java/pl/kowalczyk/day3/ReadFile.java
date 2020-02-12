package pl.kowalczyk.day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadFile {
    public Point sizeOfMap_DRPoint(List<String> listOfString) {
        String sumOfList = listOfString.get(0) + listOfString.get(1);
        Pattern pattern = Pattern.compile("(\\D)(\\d{1,3})");
        Matcher matcher = pattern.matcher(sumOfList);
        int mapV=0, mapH = 0;
        int maxU = 0, maxD = 0, maxR = 0, maxL = 0;
        while (matcher.find()) {

            if (matcher.group(1).equals("U") && Integer.parseInt(matcher.group(2)) > maxU) {
                maxU = Integer.parseInt(matcher.group(2));
            }
            if (matcher.group(1).equals("D") && Integer.parseInt(matcher.group(2)) > maxD) {
                maxD = Integer.parseInt(matcher.group(2));
            }
            if (matcher.group(1).equals("R") && Integer.parseInt(matcher.group(2)) > maxR) {
                maxR = Integer.parseInt(matcher.group(2));
            }
            if (matcher.group(1).equals("L") && Integer.parseInt(matcher.group(2)) > maxL) {
                maxL = Integer.parseInt(matcher.group(2));
            }


            mapH = maxL + maxR + 1;
            mapV = maxU + maxD + 1;

        }
        System.out.println("Max U= " + maxU);
        System.out.println("Max D= " + maxD);
        System.out.println("Max R= " + maxR);
        System.out.println("Max L= " + maxL);
        return new Point(mapH, mapV);

    }


    private static List<String> read() throws FileNotFoundException {
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
