package pl.kowalczyk.day3;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WiresPath {
    boolean[][] AreaSize(List<String> listOfString) {
        String sumOfList = listOfString.get(0) + listOfString.get(1);
        Pattern pattern = Pattern.compile("(\\D)(\\d{1,3})");
        Matcher matcher = pattern.matcher(sumOfList);

        while (matcher.find()) {

            if (matcher.group(1).equals("U")) {

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
}
