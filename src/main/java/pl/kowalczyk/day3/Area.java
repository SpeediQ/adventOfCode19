package pl.kowalczyk.day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Area {
    private int mapV = 0, mapH = 0;

    public int getMapV() {
        return mapV;
    }

    public int getMapH() {
        return mapH;
    }

    public int getMaxU() {
        return maxU;
    }

    public int getMaxD() {
        return maxD;
    }

    public int getMaxR() {
        return maxR;
    }

    public int getMaxL() {
        return maxL;
    }

    private int maxU = 0, maxD = 0, maxR = 0, maxL = 0;



    boolean[][] AreaSize(List<String> listOfString) {
        String sumOfList = listOfString.get(0) + listOfString.get(1);
        Pattern pattern = Pattern.compile("(\\D)(\\d{1,3})");
        Matcher matcher = pattern.matcher(sumOfList);

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
        }
        mapH = maxL + maxR + 1;
        mapV = maxU + maxD + 1;
        System.out.println("Max U= " + maxU);
        System.out.println("Max D= " + maxD);
        System.out.println("Max R= " + maxR);
        System.out.println("Max L= " + maxL);

        System.out.println("poziom: "+mapH);
        System.out.println("pion: "+mapV);

        return new boolean[mapH][mapV];

    }


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
