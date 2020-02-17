package pl.kowalczyk.day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Path {

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

    public void creatingWireSegments(List<Section> sectionList, String path) {
        Pattern pattern = Pattern.compile("(\\D)(\\d{1,4})");
        Matcher matcher = pattern.matcher(path);

        while (matcher.find()) {
            if (matcher.group(1).equals("U")) {
                int valueFromFile = Integer.parseInt(matcher.group(2));
                if (sectionList.isEmpty()) {
                    sectionList.add(new Section(0, 0, 0, valueFromFile));
                } else {
                    int earlier_X = sectionList.get(sectionList.size() - 1).getX2();
                    int earlier_Y = sectionList.get(sectionList.size() - 1).getY2();
                    Section newSection = new Section(earlier_X, earlier_Y, earlier_X, earlier_Y + valueFromFile);
                    sectionList.add(newSection);
                }
            }
            if (matcher.group(1).equals("D")) {
                int valueFromFile = Integer.parseInt(matcher.group(2));
                if (sectionList.isEmpty()) {
                    sectionList.add(new Section(0, 0, 0, -valueFromFile));
                } else {
                    int earlier_X = sectionList.get(sectionList.size() - 1).getX2();
                    int earlier_Y = sectionList.get(sectionList.size() - 1).getY2();
                    Section newSection = new Section(earlier_X, earlier_Y, earlier_X, earlier_Y - valueFromFile);
                    sectionList.add(newSection);
                }
            }
            if (matcher.group(1).equals("R")) {
                int valueFromFile = Integer.parseInt(matcher.group(2));
                if (sectionList.isEmpty()) {
                    sectionList.add(new Section(0, 0, valueFromFile, 0));
                } else {
                    int earlier_X = sectionList.get(sectionList.size() - 1).getX2();
                    int earlier_Y = sectionList.get(sectionList.size() - 1).getY2();
                    Section newSection = new Section(earlier_X, earlier_Y, earlier_X + valueFromFile, earlier_Y);
                    sectionList.add(newSection);
                }
            }
            if (matcher.group(1).equals("L")) {
                int valueFromFile = Integer.parseInt(matcher.group(2));
                if (sectionList.isEmpty()) {
                    sectionList.add(new Section(0, 0, -valueFromFile, 0));
                } else {
                    int earlier_X = sectionList.get(sectionList.size() - 1).getX2();
                    int earlier_Y = sectionList.get(sectionList.size() - 1).getY2();
                    Section newSection = new Section(earlier_X, earlier_Y, earlier_X - valueFromFile, earlier_Y);
                    sectionList.add(newSection);
                }
            }
        }
    }



}
