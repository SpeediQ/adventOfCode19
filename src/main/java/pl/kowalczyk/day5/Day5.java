package pl.kowalczyk.day5;

import java.util.List;
import java.util.Scanner;
import pl.kowalczyk.FileUtil;

public class Day5 {
    public Day5() {
        Scanner scanner = new Scanner(System.in);
        List<String> listOfStrings = FileUtil.loadFile("files/day5.txt");
        String[] listOfString = ((String)listOfStrings.get(0)).split(",");
        Integer[] listOfInteger = new Integer[listOfString.length];

        for(int i = 0; i < listOfString.length; ++i) {
            listOfInteger[i] = Integer.parseInt(listOfString[i]);
        }

        Integer[] programList = (Integer[])listOfInteger.clone();
        int counter = 0;
        boolean run = true;
        int[] params = new int[3];
        boolean[] isPositionMode = new boolean[3];

        while(run) {
            String instruction = String.valueOf(programList[counter]);

            int opcode;
            for(opcode = instruction.length(); opcode < 5; ++opcode) {
                instruction = "0" + instruction;
            }

            opcode = Integer.parseInt(instruction.substring(instruction.length() - 2));
            isPositionMode[0] = instruction.charAt(2) == '0';
            isPositionMode[1] = instruction.charAt(1) == '0';
            isPositionMode[2] = true;
            switch(opcode) {
                case 1:
                    params[0] = checkTheMode(isPositionMode[0], programList, counter + 1);
                    params[1] = checkTheMode(isPositionMode[1], programList, counter + 2);
                    params[2] = programList[counter + 3];
                    programList[params[2]] = params[0] + params[1];
                    counter += 4;
                    break;
                case 2:
                    params[0] = checkTheMode(isPositionMode[0], programList, counter + 1);
                    params[1] = checkTheMode(isPositionMode[1], programList, counter + 2);
                    params[2] = programList[counter + 3];
                    programList[params[2]] = params[0] * params[1];
                    counter += 4;
                    break;
                case 3:
                    System.out.print("Input: ");
                    int value = scanner.nextInt();
                    params[0] = programList[counter + 1];
                    programList[params[0]] = value;
                    counter += 2;
                    break;
                case 4:
                    params[0] = checkTheMode(isPositionMode[0], programList, counter + 1);
                    System.out.println(params[0]);
                    counter += 2;
                    break;
                case 5:
                    params[0] = checkTheMode(isPositionMode[0], programList, counter + 1);
                    params[1] = checkTheMode(isPositionMode[1], programList, counter + 2);
                    if (params[0] != 0) {
                        counter = params[1];
                    } else {
                        counter += 3;
                    }
                    break;
                case 6:
                    params[0] = checkTheMode(isPositionMode[0], programList, counter + 1);
                    params[1] = checkTheMode(isPositionMode[1], programList, counter + 2);
                    if (params[0] == 0) {
                        counter = params[1];
                    } else {
                        counter += 3;
                    }
                    break;
                case 7:
                    params[0] = checkTheMode(isPositionMode[0], programList, counter + 1);
                    params[1] = checkTheMode(isPositionMode[1], programList, counter + 2);
                    params[2] = programList[counter + 3];
                    if (params[0] < params[1]) {
                        programList[params[2]] = 1;
                    } else {
                        programList[params[2]] = 0;
                    }

                    counter += 4;
                    break;
                case 8:
                    params[0] = checkTheMode(isPositionMode[0], programList, counter + 1);
                    params[1] = checkTheMode(isPositionMode[1], programList, counter + 2);
                    params[2] = programList[counter + 3];
                    if (params[0] == params[1]) {
                        programList[params[2]] = 1;
                    } else {
                        programList[params[2]] = 0;
                    }

                    counter += 4;
                    break;
                default:
                    run = false;
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        new Day5();
    }

    private static int checkTheMode(boolean positionMode, Integer[] param, int counter) {
        return positionMode ? param[param[counter]] : param[counter];
    }
}
