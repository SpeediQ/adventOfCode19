package pl.kowalczyk.day3;

import pl.kowalczyk.FileUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day3 {
    private Map<Point, Integer> visitedPoints_Map = new HashMap<>();
    private Map<Point, Integer> numberOfSteps_Map = new HashMap<>();

    public static void main(String[] args) {
        Day3 main = new Day3();
        List<String> listOfSteps_String = FileUtil.loadFile("files/day3.txt");
        String[] input_wire1 = listOfSteps_String.get(0).split(",");
        String[] input_wire2 = listOfSteps_String.get(1).split(",");


        main.visitedPoints(input_wire1);
        int shortestDistance = main.checkingPoints(input_wire2);
        int shortestSteps = main.findShortestWay(input_wire2);
        System.out.println("Answer 3.1: " + shortestDistance);
        System.out.println("Answer 3.2: " + shortestSteps);


    }

    private int findShortestWay(String[] wire2_String) {
        int sum = Integer.MAX_VALUE;
        for (Point point : numberOfSteps_Map.keySet()) {
            int temp = numberOfSteps_Map.get(point) + visitedPoints_Map.get(point);
            if (temp < sum) {
                sum = temp;
            }
        }
        return sum;
    }


    public void visitedPoints(String[] wire1_String) {
        Point currentPoint_wire1 = new Point(0, 0);
        int steps = 0;
        for (String instruction : wire1_String) {
            int distance = Integer.parseInt(instruction.substring(1));
            char direction = instruction.charAt(0);
            for (int i = 1; i <= distance; i++) {
                if (direction == 'U')
                    currentPoint_wire1.y++;
                else if (direction == 'D')
                    currentPoint_wire1.y--;
                else if (direction == 'L')
                    currentPoint_wire1.x--;
                else if (direction == 'R')
                    currentPoint_wire1.x++;

                visitedPoints_Map.put(new Point(currentPoint_wire1), steps + i);
            }
            steps += distance;
        }
    }

    public int checkingPoints(String[] wire2_String) {
        int shortestDistance = Integer.MAX_VALUE;

        Point currentPoint_wire2 = new Point(0, 0);

        int steps = 0;
        for (String instruction : wire2_String) {
            int distance = Integer.parseInt(instruction.substring(1));
            char direction = instruction.charAt(0);
            for (int i = 1; i <= distance; i++) {
                if (direction == 'U')
                    currentPoint_wire2.y++;
                else if (direction == 'D')
                    currentPoint_wire2.y--;
                else if (direction == 'L')
                    currentPoint_wire2.x--;
                else if (direction == 'R')
                    currentPoint_wire2.x++;
                if (visitedPoints_Map.containsKey(currentPoint_wire2)) {
                    numberOfSteps_Map.put(new Point(currentPoint_wire2), steps + i);
                    int shortestDistanceTemp = Math.abs(currentPoint_wire2.x) + Math.abs(currentPoint_wire2.y);
                    if (shortestDistanceTemp < shortestDistance) {
                        shortestDistance = shortestDistanceTemp;
                    }
                }

            }
            steps += distance;
        }
        return shortestDistance;
    }
}
