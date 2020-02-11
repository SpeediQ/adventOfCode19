package pl.kowalczyk.day3;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(wires());


    }

    static List<Wire> wires() throws FileNotFoundException {
        int size = ReadFile.read().size();
        List<String> path1 = null;
        List<String> path2 = null;
        for (int i = 0; i < size; i++) {
            path1 = Arrays.asList(ReadFile.read().get(i).split(","));
            path2 = Arrays.asList(ReadFile.read().get(i).split(","));
        }
        return Arrays.asList(
                new Wire("First Wire", path1),
                new Wire("Second Wire", path2)
        );
    }
    static void patter(){


        Pattern pattern = Pattern.compile("\\D(\\d{1,3})");
        Matcher matcher = pattern.matcher(stringBuffer2);


        while (matcher.find()) {
            System.out.println(matcher);

            lista.add(new Car(matcher.group(1),matcher.group(2),Integer.parseInt(matcher.group(3))));

        }

    }

}
