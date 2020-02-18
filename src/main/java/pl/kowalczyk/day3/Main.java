package pl.kowalczyk.day3;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Main main = new Main();
        Path path = new Path();
        Wire wire1 = new Wire();
        Wire wire2 = new Wire();
        List<Cross> listOfCross = new ArrayList<>();
        List<Section> sectionsOfWire1 = path.creatingWireSegments(wire1.getSectionList(), path.readFromFile().get(0));
        List<Section> sectionsOfWire2 = path.creatingWireSegments(wire2.getSectionList(), path.readFromFile().get(1));
        List<Point> crossList = main.doCrossList(wire1, wire2, listOfCross);
        System.out.println("tutaj" + listOfCross);
        System.out.println("Answer 3/1: " + main.nearestPoint(crossList));
        System.out.println("Lista punktów przecięcia: " + crossList);
        List<Integer> integerList_Wire1 = main.valueOfSteps(path.readFromFile().get(0));
        List<Integer> integerList_Wire2 = main.valueOfSteps(path.readFromFile().get(1));

        System.out.println(sectionsOfWire1);
        System.out.println(sectionsOfWire2);

        List<Integer> listOfSteps = main.doSTH(crossList, listOfCross, sectionsOfWire1, sectionsOfWire2, integerList_Wire1, integerList_Wire2);
        Collections.sort(listOfSteps);
        System.out.println(listOfSteps.get(0));

    }


    public List<pl.kowalczyk.day3.Point> doCrossList(Wire wire1, Wire wire2, List<Cross> listOfCross) {

        List<Point> crossList = new ArrayList<>();
        for (int i = 1; i < wire1.getSectionList().size(); i++) {
            for (int j = 1; j < wire2.getSectionList().size(); j++) {

                Section section_Wire1 = wire1.getSectionList().get(i);
                Section section_Wire2 = wire2.getSectionList().get(j);
                Section verifiedSection_Wire1 = smallerCoordinatesToStartingPoint(section_Wire1);
                Section verifiedSection_Wire2 = smallerCoordinatesToStartingPoint(section_Wire2);


                if (isVertical(verifiedSection_Wire1) != isVertical(verifiedSection_Wire2)) {


                    if (isCrossing(verifiedSection_Wire1, verifiedSection_Wire2)) {

                        if (isVertical(verifiedSection_Wire1)) {
                            crossList.add(new pl.kowalczyk.day3.Point(verifiedSection_Wire1.getX1(), verifiedSection_Wire2.getY2()));
                            listOfCross.add(new Cross(i, j));
                        } else {
                            crossList.add(new pl.kowalczyk.day3.Point(verifiedSection_Wire2.getX1(), verifiedSection_Wire1.getY2()));
                            listOfCross.add(new Cross(i, j));
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
        Section tempSection = new Section(section.getX1(), section.getY1(), section.getX2(), section.getY2());
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
            if (sum > sumTemp) {
                sum = sumTemp;
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

    public static boolean positiveNumber(int a) {
        boolean positive = false;
        if (a > 0) {
            positive = true;
        }
        return positive;
    }

    public List<Integer> FinishIT(List<Point> cross_Point, List<Cross> numberOfCrossedSection, List<Section> listOfSection_Wire1, List<Section> listOfSection_Wire2, List<Integer> valueSteps_Wire1, List<Integer> valueSteps_Wire2) {
        Point tempPoint_Wire1 = new Point(0, 0);
        Point tempPoint_Wire2 = new Point(0, 0);
        Point crossPoint = new Point(0, 0);
        List<Integer> listvalues = new ArrayList<>();
        for (int i = 0; i < numberOfCrossedSection.size(); i++) {
            int sectionNumber_wire1 = numberOfCrossedSection.get(i).getSectionNumber_wire1();
            int sectionNumber_wire2 = numberOfCrossedSection.get(i).getSectionNumber_wire2();
            tempPoint_Wire1.setX(listOfSection_Wire1.get(sectionNumber_wire1).getX1());
            tempPoint_Wire1.setY(listOfSection_Wire1.get(sectionNumber_wire1).getY1());
            tempPoint_Wire2.setX(listOfSection_Wire2.get(sectionNumber_wire2).getX1());
            tempPoint_Wire2.setY(listOfSection_Wire2.get(sectionNumber_wire2).getY1());

            crossPoint.setX(cross_Point.get(i).getX());
            crossPoint.setY(cross_Point.get(i).getY());

            int lastSteps_Wire1 = 0;
            int lastSteps_Wire2 = 0;

            if (isVertical(listOfSection_Wire1.get(sectionNumber_wire1))) {
                if (positiveNumber(cross_Point.get(i).getY()) != positiveNumber(tempPoint_Wire1.getY())) {
                    lastSteps_Wire1 = Math.abs(cross_Point.get(i).getY()) + Math.abs(tempPoint_Wire1.getY());

                } else {
                    lastSteps_Wire1 = Math.abs(cross_Point.get(i).getY()) - Math.abs(tempPoint_Wire1.getY());
                }
            } else {
                if (positiveNumber(cross_Point.get(i).getX()) != positiveNumber(tempPoint_Wire1.getX())) {
                    lastSteps_Wire1 = Math.abs(cross_Point.get(i).getX()) + Math.abs(tempPoint_Wire1.getX());
                } else {
                    lastSteps_Wire1 = Math.abs(cross_Point.get(i).getX()) - Math.abs(tempPoint_Wire1.getX());
                }
            }
            if (isVertical(listOfSection_Wire2.get(sectionNumber_wire2))) {
                if (positiveNumber(cross_Point.get(i).getY()) != positiveNumber(tempPoint_Wire2.getY())) {
                    lastSteps_Wire2 = Math.abs(cross_Point.get(i).getY()) + Math.abs(tempPoint_Wire2.getY());
                } else {
                    lastSteps_Wire2 = Math.abs(cross_Point.get(i).getY()) - Math.abs(tempPoint_Wire2.getY());
                }
            } else {
                if (positiveNumber(cross_Point.get(i).getX()) != positiveNumber(tempPoint_Wire2.getX())) {
                    lastSteps_Wire2 = Math.abs(cross_Point.get(i).getX()) + Math.abs(tempPoint_Wire2.getX());
                } else {
                    lastSteps_Wire2 = Math.abs(cross_Point.get(i).getX()) - Math.abs(tempPoint_Wire2.getX());
                }
            }
            int sumValue_wire1 = 0;
            int sumValue_wire2 = 0;
            for (int i1 = 0; i1 < sectionNumber_wire1; i1++) {
                sumValue_wire1 += valueSteps_Wire1.get(i1);
            }
            for (int i2 = 0; i2 < sectionNumber_wire2; i2++) {
                sumValue_wire2 += valueSteps_Wire2.get(i2);
            }

            int sum = Math.abs(lastSteps_Wire1) + Math.abs(lastSteps_Wire2) + sumValue_wire1 + sumValue_wire2;
            listvalues.add(sum);

        }
        return listvalues;

    }


}
