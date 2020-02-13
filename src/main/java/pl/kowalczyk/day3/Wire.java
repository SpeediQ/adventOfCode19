package pl.kowalczyk.day3;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Wire {
    Point head = new Point(0, 0);
    List<Point> body = new ArrayList<>();

    @Override
    public String toString() {
        return "Wire{" +
                "head=" + head +
                ", body=" + body +
                '}';
    }

    public Point getHead() {
        return head;
    }

    public void setHead(Point head) {
        this.head = head;
    }



    public void wirePath(String path) {
        Pattern pattern = Pattern.compile("(\\D)(\\d{1,3})");
        Matcher matcher = pattern.matcher(path);
        while (matcher.find()) {

            if (matcher.group(1).equals("U")) {
                int value = Integer.parseInt(matcher.group(2));
                for (int i = 1; i <= value; i++) {
                    body.add(new Point(head.getX(), head.getY() + i));
                }
                head.setY(head.getY() + value);
            }
            if (matcher.group(1).equals("D")) {
                int value = Integer.parseInt(matcher.group(2));
                for (int i = 1; i <= value; i++) {
                    body.add(new Point(head.getX(), head.getY() - i));
                }
                head.setY(head.getY() - value);
            }
            if (matcher.group(1).equals("R")) {
                int value = Integer.parseInt(matcher.group(2));
                for (int i = 1; i <= value; i++) {
                    body.add(new Point(head.getX() + i, head.getY()));
                }
                head.setX(head.getX() + value);
            }
            if (matcher.group(1).equals("L")) {
                int value = Integer.parseInt(matcher.group(2));
                for (int i = 1; i <= value; i++) {
                    body.add(new Point(head.getX() - i, head.getY()));
                }
                head.setX(head.getX() - value);
            }

        }

    }
}
