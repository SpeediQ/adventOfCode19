package pl.kowalczyk.day3;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Main main = new Main();
        Path path = new Path();
        Wire wire1 = new Wire();
        Wire wire2 = new Wire();
        List<Section> sectionsOfWire1 = path.creatingWireSegments(wire1.getSectionList(), path.readFromFile().get(0));
        List<Section> sectionsOfWire2 = path.creatingWireSegments(wire2.getSectionList(), path.readFromFile().get(1));
        List<Point> crossList = main.doCrossList(wire1, wire2);
        System.out.println("Answer 3/1: "+main.nearestPoint(crossList));
        System.out.println("Lista punktów przecięcia: "+crossList);

        System.out.println(sectionsOfWire1);
        System.out.println(sectionsOfWire2);


    }


    public List<pl.kowalczyk.day3.Point> doCrossList(Wire wire1, Wire wire2) {

        List<Point> crossList = new ArrayList<>();
        for (int i = 0; i < wire1.getSectionList().size(); i++) {
            for (int j = 0; j < wire2.getSectionList().size(); j++) {

                Section section_Wire1 = wire1.getSectionList().get(i);
                Section section_Wire2 = wire2.getSectionList().get(j);
                Section verifiedSection_Wire1 = smallerCoordinatesToStartingPoint(section_Wire1);
                Section verifiedSection_Wire2 = smallerCoordinatesToStartingPoint(section_Wire2);


                if (isVertical(verifiedSection_Wire1) != isVertical(verifiedSection_Wire2)) {


                    if (isCrossing(verifiedSection_Wire1, verifiedSection_Wire2)) {

                        if (isVertical(verifiedSection_Wire1)) {
                            crossList.add(new pl.kowalczyk.day3.Point(verifiedSection_Wire1.getX1(), verifiedSection_Wire2.getY2()));
                        } else {
                            crossList.add(new pl.kowalczyk.day3.Point(verifiedSection_Wire2.getX1(), verifiedSection_Wire1.getY2()));
                        }
                    }
                }

            }

        }

        return crossList;
    }

    public static boolean isVertical(Section section) {
        boolean vertical = false;
        if (section.getX1() == section.getX2()) {
            vertical = true;
        }
        return vertical;
    }

    public static Section smallerCoordinatesToStartingPoint(Section section) {
        Section tempSection = new Section(section.getX1(),section.getY1(),section.getX2(),section.getY2());
        int tempValue = 0;
        if (isVertical(tempSection)) {
            if (tempSection.getY2() < tempSection.getY1()) {
                tempValue = tempSection.getY2();
                tempSection.setY2(tempSection.getY1());
                tempSection.setY1(tempValue);
            }
        } else {
            if (tempSection.getX2() < tempSection.getX1()) {
                tempValue = tempSection.getX2();
                tempSection.setX2(tempSection.getX1());
                tempSection.setX1(tempValue);
            }
        }
        return tempSection;
    }

    public boolean isCrossing(Section section1, Section section2) {
        boolean isCrossed = false;
        if (isVertical(section1)) {
            if (section1.getX1() >= section2.getX1() && section1.getX1() <= section2.getX2() && section2.getY1() >= section1.getY1() && section2.getY1() <= section1.getY2()) {
                isCrossed = true;
            }

        } else {
            if (section1.getY1() >= section2.getY1() && section1.getY1() <= section2.getY2() && section2.getX1() >= section1.getX1() && section2.getX1() <= section1.getX2()) {
                isCrossed = true;
            }
        }
        return isCrossed;
    }

    public int nearestPoint(List<Point> list) {
        int sum = Math.abs(list.get(1).x) + Math.abs(list.get(1).y);
        for (int i = 1; i < list.size(); i++) {
            int x = Math.abs(list.get(i).x);
            int y = Math.abs(list.get(i).y);
            int sumTemp = x + y;
            if (sum>sumTemp){
                sum=sumTemp;
            }
        }

        return sum;
    }
    public List<Integer> valueOfSteps(String pathString) throws FileNotFoundException {
        List<Integer> valueList = new ArrayList<>();
        Pattern pattern = Pattern.compile("(\\D)(\\d{1,4})");
        Matcher matcher = pattern.matcher(pathString);

        while (matcher.find()) {
            valueList.add(Integer.parseInt(matcher.group(2)));
        }
        return valueList;
    }


}
