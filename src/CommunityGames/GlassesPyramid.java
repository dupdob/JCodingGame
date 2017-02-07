package CommunityGames;

import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class GlassesPyramid {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        ArrayList<String> output = new ArrayList<>();
        int line =1;
        while (N>=line) {
            // offset the top of the pyramid
            for (int i = 0; i < output.size(); i++) {
                output.set(i, "   "+output.get(i)+"   ");
            }
            ArrayList<String> nextLine = new ArrayList<>(4);
            for (int i = 0; i < 4; i++) {
                nextLine.add("");
            }
            for (int j = 0; j < line-1; j++) {
                nextLine.set(0, nextLine.get(0)+" ***  ");
                nextLine.set(1, nextLine.get(1)+" * *  ");
                nextLine.set(2, nextLine.get(2)+" * *  ");
                nextLine.set(3, nextLine.get(3)+"***** ");
            }
            nextLine.set(0, nextLine.get(0)+" *** ");
            nextLine.set(1, nextLine.get(1)+" * * ");
            nextLine.set(2, nextLine.get(2)+" * * ");
            nextLine.set(3, nextLine.get(3)+"*****");
            output.addAll(nextLine);
            N-=line;
            line++;
        }

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");
        for (int i = 0; i < output.size(); i++) {
            System.out.println(output.get(i));
        }
    }
}