package pl.kowalczyk.day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Main main = new Main();
        Path path = new Path();
        Wire wire1 = new Wire();
        Wire wire2 = new Wire();
        String wire1Path = path.readFromFile().get(0);
        String wire2Path = path.readFromFile().get(1);
        List<Cross> listOfCrosses = new ArrayList<>();

        path.creatingWireSegments(wire1.getSectionList(), wire1Path);
        path.creatingWireSegments(wire2.getSectionList(), wire2Path);

        List<Section> unmodifiedSectionList_wire1 = new ArrayList<>(wire1.getSectionList());
        List<Section> unmodifiedSectionList_wire2 = new ArrayList<>(wire2.getSectionList());


        listOfCrosses = main.doCrossList(wire1, wire2, listOfCrosses);
        List<Point> listOfCrosses_Points = main.PointsFromCross(listOfCrosses);


        System.out.println("Punkty przecięcia: " + listOfCrosses_Points);
        System.out.println("Odległość do najbliższego przecięcia od punktu startowego: " + main.nearestPoint(listOfCrosses_Points));
        System.out.println("Lista przecięć: " + listOfCrosses);


        List<Integer> stepsList_wire1 = main.valueOfSteps(wire1Path);
        List<Integer> stepsList_wire2 = main.valueOfSteps(wire2Path);


        System.out.println(unmodifiedSectionList_wire1);

        int intersectionClosest = main.findIntersectionClosest(listOfCrosses, stepsList_wire1, stepsList_wire2, unmodifiedSectionList_wire1, unmodifiedSectionList_wire2);
        System.out.println(intersectionClosest);

    }

    public int findIntersectionClosest(List<Cross> crosslist, List<Integer> listOfInteger_Wire1, List<Integer> listOfInteger_Wire2, List<Section> sectionList_Wire1, List<Section> sectionList_Wire2) {
        int sum = 9999999;
        for (Cross cross : crosslist) {
            int sectionNumber_wire1 = cross.getSectionNumber_wire1();
            int sectionNumber_wire2 = cross.getSectionNumber_wire2();
            Point crossPoint = cross.getCrossPoint();
            int partOfTheSum_wire1 = 0;
            int partOfTheSum_wire2 = 0;
            for (int i = 0; i < sectionNumber_wire1 ; i++) {
                partOfTheSum_wire1 += listOfInteger_Wire1.get(i);
            }
            for (int i = 0; i < sectionNumber_wire2 ; i++) {
                partOfTheSum_wire2 += listOfInteger_Wire2.get(i);
            }
            int x1_Wire1 = sectionList_Wire1.get(sectionNumber_wire1).getX1();
            int y1_Wire1 = sectionList_Wire1.get(sectionNumber_wire1).getY1();
            int x1_Wire2 = sectionList_Wire2.get(sectionNumber_wire2).getX1();
            int y1_Wire2 = sectionList_Wire2.get(sectionNumber_wire2).getY1();

            int xCross = cross.getCrossPoint().x;
            int yCross = cross.getCrossPoint().y;

            int sum_Wire1 = 0;
            int sum_Wire2 = 0;

            if (isVertical(sectionList_Wire1.get(sectionNumber_wire1))) {
                sum_Wire1 = partOfTheSum_wire1 + Math.abs(yCross - y1_Wire1);
                sum_Wire2 = partOfTheSum_wire2 + Math.abs(xCross - x1_Wire2);
            } else {
                sum_Wire1 = partOfTheSum_wire1 + Math.abs(xCross - x1_Wire1);
                sum_Wire2 = partOfTheSum_wire2 + Math.abs(yCross - y1_Wire2);
            }
            int sumTemp = sum_Wire1 + sum_Wire2;
            if (sumTemp < sum) {
                sum = sumTemp;
            }
        }


        return sum;
    }


    public List<Cross> doCrossList(Wire wire1, Wire wire2, List<Cross> listOfCrosses) {


        for (int i = 1; i < wire1.getSectionList().size(); i++) {
            for (int j = 1; j < wire2.getSectionList().size(); j++) {

                Section section_Wire1 = wire1.getSectionList().get(i);
                Section section_Wire2 = wire2.getSectionList().get(j);
                Section verifiedSection_Wire1 = smallerCoordinatesToStartingPoint(section_Wire1);
                Section verifiedSection_Wire2 = smallerCoordinatesToStartingPoint(section_Wire2);


                if (isVertical(verifiedSection_Wire1) != isVertical(verifiedSection_Wire2)) {


                    if (isCrossing(verifiedSection_Wire1, verifiedSection_Wire2)) {

                        if (isVertical(verifiedSection_Wire1)) {
                            listOfCrosses.add(new Cross(i, j, new Point(verifiedSection_Wire1.getX1(), verifiedSection_Wire2.getY2())));
                        } else {
                            listOfCrosses.add(new Cross(i, j, new Point(verifiedSection_Wire2.getX1(), verifiedSection_Wire1.getY2())));
                        }
                    }
                }

            }

        }

        return listOfCrosses;
    }

    public static boolean isVertical(Section section) {
        boolean vertical = false;
        if (section.getX1() == section.getX2()) {
            vertical = true;
        }
        return vertical;
    }

    public static Section smallerCoordinatesToStartingPoint(Section section) {


        int tempValue = 0;
        if (isVertical(section)) {
            if (section.getY2() < section.getY1()) {
                tempValue = section.getY2();
                section.setY2(section.getY1());
                section.setY1(tempValue);
            }
        } else {
            if (section.getX2() < section.getX1()) {
                tempValue = section.getX2();
                section.setX2(section.getX1());
                section.setX1(tempValue);
            }
        }
        return section;
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

    public List<Point> PointsFromCross(List<Cross> crossList) {
        List<Point> listOfCrosses_Points = new ArrayList<>();

        for (Cross cross : crossList) {
            listOfCrosses_Points.add(cross.getCrossPoint());
        }
        return listOfCrosses_Points;
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