package pl.kowalczyk.day3;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Path path = new Path();
        Wire wire1 = new Wire();
        Wire wire2 = new Wire();
        String wire1Path = path.readFromFile().get(0);
        String wire2Path = path.readFromFile().get(1);

        path.creatingWireSegments(wire1.getSectionList(), wire1Path);
        path.creatingWireSegments(wire2.getSectionList(), wire2Path);


        Main main = new Main();
        List<Point> crossList = main.doCrossList(wire1, wire2);
        System.out.println(crossList);

        System.out.println(main.nearestPoint(crossList));


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
            if (sum>sumTemp){
                sum=sumTemp;
            }
        }

        return sum;
    }


}
