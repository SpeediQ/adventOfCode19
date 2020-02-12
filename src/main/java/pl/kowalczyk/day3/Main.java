package pl.kowalczyk.day3;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ReadFile readFile = new ReadFile();
        readFile.sizeOfMap_DRPoint(ReadFile.read());
    }

}
