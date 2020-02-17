package pl.kowalczyk.day3;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Wire {
    public List<Section> sectionList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wire wire = (Wire) o;
        return Objects.equals(sectionList, wire.sectionList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sectionList);
    }

    @Override
    public String toString() {
        return "Wire{" +
                "sectionList=" + sectionList +
                '}';
    }

    public List<Section> getSectionList() {
        return sectionList;
    }
}


class Section {
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    Section(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    @Override
    public String toString() {
        return "Section{" +
                "x1=" + x1 +
                ", y1=" + y1 +
                ", x2=" + x2 +
                ", y2=" + y2 +
                '}';
    }


}
class Point{
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
class Cross{
    int sectionNumber_wire1;
    int sectionNumber_wire2;
    Point crossPoint;

    public Cross(int sectionNumber_wire1, int sectionNumber_wire2, Point crossPoint) {
        this.sectionNumber_wire1 = sectionNumber_wire1;
        this.sectionNumber_wire2 = sectionNumber_wire2;
        this.crossPoint = crossPoint;
    }

    public int getSectionNumber_wire1() {
        return sectionNumber_wire1;
    }

    public void setSectionNumber_wire1(int sectionNumber_wire1) {
        this.sectionNumber_wire1 = sectionNumber_wire1;
    }

    public int getSectionNumber_wire2() {
        return sectionNumber_wire2;
    }

    public void setSectionNumber_wire2(int sectionNumber_wire2) {
        this.sectionNumber_wire2 = sectionNumber_wire2;
    }

    public Point getCrossPoint() {
        return crossPoint;
    }

    public void setCrossPoint(Point crossPoint) {
        this.crossPoint = crossPoint;
    }

    @Override
    public String toString() {
        return "Cross{" +
                "sectionNumber_wire1=" + sectionNumber_wire1 +
                ", sectionNumber_wire2=" + sectionNumber_wire2 +
                ", crossPoint=" + crossPoint +
                '}';
    }
}
