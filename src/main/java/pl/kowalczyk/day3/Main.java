package pl.kowalczyk.day3;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Area area = new Area();
        boolean[][] panel = area.AreaSize(area.readFromFile());

        int h0 = area.getMaxL() + 1;
        int v0 = area.getMaxU() + 1;
        Point head = new Point(h0, v0);
        System.out.println(head);


    }

}
