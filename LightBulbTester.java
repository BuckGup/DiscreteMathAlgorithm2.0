import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.Random;


public class LightBulbTester {

    public static void main(String[] args) {

        int counter = 0;
        Random rand = new Random();
        Scanner userIn = new Scanner(System.in);
        System.out.println("How many switches would you like?"); // asking user for the number of switches
        int numSwitches = userIn.nextInt();
        int[] lightBulbArray = new int[numSwitches];
        String randomSwitchArrayString;                     //declaring variables
        char[] randomSwitchCharArray;
        int randomSwitchToMatch;
        int[] binaryArray = new int[numSwitches];
        char[] binaryChars;
        char[] lightBulbCharGrayCompare;
        char[] lightBulbCharArrayExtended = new char[256];
        double numberOfCombinations = Math.pow(2, numSwitches);

        randomSwitchToMatch = rand.nextInt((int) numberOfCombinations);
        randomSwitchArrayString = printBinaryMode(numSwitches, Integer.toBinaryString(randomSwitchToMatch));
        randomSwitchCharArray = randomSwitchArrayString.toCharArray();

        System.out.println(randomSwitchArrayString);

        if (isOne(randomSwitchCharArray)) {
            System.out.print("The lightbulb is on it took 0 tries!");
            return;
        } else {

            for (int testCase = 1; testCase < numberOfCombinations; testCase++) {
                int indexOfDiff = findIndex(testCase, numSwitches);

                if (randomSwitchCharArray[indexOfDiff] == '1') {
                    randomSwitchCharArray[indexOfDiff] = '0';
                } else {
                    randomSwitchCharArray[indexOfDiff] = '1';

                }

                if (isOne(randomSwitchCharArray)) {
                    System.out.println("The lightbulb is on it took " + testCase + " tries!");
                    return;
                }
            }
        }
    }


//    private static String printBinaryMode(int numSwitches, int intValue) {
//        String tmpStr = Integer.toBinaryString(intValue);
//        String rslts = "";
//
//        for (int i = 0; i < (numSwitches - tmpStr.length()); i++)
//            rslts += '0';
//
//        rslts += tmpStr;
//
//        return rslts;
//    }

    private static String printBinaryMode(int numSwitches, String strValue) {
        String rslts = "";

        for (int i = 0; i < (numSwitches - strValue.length()); i++)
            rslts += '0';

        rslts += strValue;

        return rslts;
    }

    private static int binaryToGray2(int num) {
        int grayValue = num ^ (num >> 1);
        return grayValue;
    }

    private static boolean isOne(char[] lightBulbArray) {

        boolean isOne = true;
        for (int i = 0; i < lightBulbArray.length; i++) {//checks to see if the switches are all on without flipping a switch
            if (lightBulbArray[i] != '1') {
                isOne = false;
            }
        }
        return isOne;
    }


    private static int findIndex(int testCase, int numSwitches) {

        int g1 = binaryToGray2(testCase - 1);
        int g2 = binaryToGray2(testCase);

        String g1String = printBinaryMode(numSwitches, Integer.toBinaryString(g1));
        String g2String = printBinaryMode(numSwitches, Integer.toBinaryString(g2));

        System.out.println(g1String);
        System.out.println(g2String);

        for (int index = 0; index < numSwitches; index++) {
            if (g1String.charAt(index) != g2String.charAt(index)) {
                return index;

            }
        }
        return -1;
    }


}
