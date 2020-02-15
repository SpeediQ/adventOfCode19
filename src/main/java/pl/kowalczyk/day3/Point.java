package pl.kowalczyk.day3;

public class Point {
    String x;
    String y;
    String marge;

    public Point(String x, String y) {
        this.x = x;
        this.y = y;
        this.marge = this.x + " " + this.y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x='" + x + '\'' +
                ", y='" + y + '\'' +
                ", marge='" + marge + '\'' +
                '}';
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getMarge() {
        return marge;
    }

    public void setMarge(String marge) {
        this.marge = marge;
    }
}
