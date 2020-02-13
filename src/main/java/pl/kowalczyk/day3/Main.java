package pl.kowalczyk.day3;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private Point head = new Point(0, 0);


    public static void main(String[] args) throws FileNotFoundException {
        ReadFromFile wire = new ReadFromFile();
        Wire wire1 = new Wire();
        Wire wire2 = new Wire();
        String wire1Path = wire.readFromFile().get(0);
        String wire2Path = wire.readFromFile().get(1);
        wire1.wirePath(wire1Path);
        wire2.wirePath(wire2Path);
        List<Point> cross = new ArrayList<>();



//        int valueX_Wire1 = 0;
//        int valueY_Wire1 = 0;
//        int valueX_Wire2 = 0;
//        int valueY_Wire2 = 0;

//        for (int i = 0; i < wire1.body.size(); i++) {
//            for (int j = 0; j < wire2.body.size(); j++) {
//                valueX_Wire1 = wire1.body.get(i).getX();
//                valueY_Wire1 = wire1.body.get(i).getY();
//                valueX_Wire2 = wire2.body.get(i).getX();
//                valueY_Wire2 = wire2.body.get(i).getY();
//                if (valueX_Wire1 == valueX_Wire2 && valueY_Wire1 == valueY_Wire2) {
//                    cross.add(wire1.body.get(i));
//                }
//
//            }
//        }
//        int distance = Math.abs(cross.get(0).getX()) + Math.abs(cross.get(0).getY());
//        for (Point point : cross) {
//            int valueY_TheAbsoluteValue = Math.abs(point.getY());
//            int valueX_TheAbsoluteValue = Math.abs(point.getX());
//            int distanceTemp = valueX_TheAbsoluteValue + valueY_TheAbsoluteValue;
//            if (distanceTemp < distance) {
//                distance = distanceTemp;
//            }
//
//        }
//        System.out.println(distance);
    }


}


