package CommunityGames;

import java.util.Scanner;
/*
A sandpile is a square matrix of natural numbers between 0 and 3, representing how many grains of sand there is on each square
        To add two sandpiles, just start by adding the two matrices element by element. Except the matrix you generate might not be a sandpile, if one of its element is higher than 3 you must transform this matrix into a sanpile, and this is how it is done :
        - If a square has 4 grains of sand or more, it "loses" four and distributes it to its four neighbors (if the square touches an edge, the grain of sand is lost)
        - Keep doing that to all the squares with 4 grains or more until all the squares have 3 grains or less

        Example :
        000   000   000    010
        020 + 020 = 040 -> 101
        000   000   000    010
        */
class Sandpile {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        int [][] pile = new int[n][n];
        for (int i = 0; i < n; i++) {
            String row = in.nextLine();
            for (int j = 0; j < n; j++) {
                pile[i][j]=(row.charAt(j)-'0');
            }
        }
        for (int i = 0; i < n; i++) {
            String row = in.nextLine();
            for (int j = 0; j < n; j++) {
                pile[i][j]+=(row.charAt(j)-'0');
            }
        }
        boolean needscan;
        do {
            needscan = false;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (pile[i][j] > 3) {
                        // need to break the pile
                        needscan = true;
                        pile[i][j] -= 4;
                        if (i > 0) {
                            pile[i - 1][j]++;
                        }
                        if (i < n - 1) {
                            pile[i + 1][j]++;
                        }
                        if (j > 0) {
                            pile[i][j - 1]++;
                        }
                        if (j < n - 1) {
                            pile[i][j + 1]++;
                        }
                    }
                }
            }

        } while (needscan);

        for(int i=0; i<n; i++) {
            for (int j = 0; j <n; j++) {
                System.out.print((char)(pile[i][j]+'0'));
            }
            System.out.println();
        }
    }
}