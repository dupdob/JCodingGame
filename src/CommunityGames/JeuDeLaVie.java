package CommunityGames;

import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class JeuDeLaVie {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int width = in.nextInt();
        int height = in.nextInt();
        char[][] start = new char[height][width];
        for (int i = 0; i < height; i++) {
            String line = in.next();
            for (int j = 0; j < width; j++) {
                start[i][j] = line.charAt(j);
            }
        }
        char[][] end = new char[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // we count
                int cell=0;
                for (int k = Math.max(0, i-1); k <= Math.min(height-1, i+1); k++) {
                    for (int l = Math.max(0, j-1); l <= Math.min(width-1, j+1); l++) {
                        if (i==k && j==l){
                            continue;
                        }
                        if (start[k][l]=='1'){
                            cell++;
                        }
                    }
                }
                System.err.format("%d.%d=>%d%n", i, j, cell);
                if (start[i][j]=='0') {
                    end[i][j] = cell==3 ? '1' : '0';
                } else {
                    end[i][j]=(cell>=2 && cell<=3)?'1':'0';
                }
            }
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(end[i][j]);
            }
            System.out.println();
        }
    }
}