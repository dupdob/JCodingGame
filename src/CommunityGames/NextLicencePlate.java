package CommunityGames;

import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class NextLicencePlate {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String x = in.nextLine();
        int n = in.nextInt();

        String[] parts = x.split("-");
        char [] letters = (parts[0] + parts[2]).toCharArray();
        int integerPart = Integer.parseInt(parts[1]) + n;

        if (integerPart>=1000) {
            int extraShift = integerPart/999;
            // overflow
            integerPart=integerPart % 999 ;

            for (int j = 0; j < extraShift; j++) {
                for(int i=3; i >=0; i--) {
                    if (letters[i] != 'Z') {
                        letters[i] = (char)(letters[i]+1);
                        break;
                    } else  {
                        letters[i] = 'A';
                    }
                }
            }
        }
        parts[0] = new String(letters, 0, 2);
        parts[2] = new String(letters, 2, 2);
        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.format("%s-%03d-%s%n", parts[0], integerPart, parts[2]);
    }
}