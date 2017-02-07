package CommunityGames;

import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class InertiaRide {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int inertia = in.nextInt();
        int W = in.nextInt();
        int H = in.nextInt();
        char[] ride = new char[W];
        in.nextLine();
        for (int i = 0; i < H; i++) {
            String row = in.nextLine();
            for (int j = 0; j < row.length(); j++) {
                switch (row.charAt(j)){
                    case '/':
                    case '\\':
                    case '_':
                        ride[j] = row.charAt(j);
                        break;
                }
            }
        }
        int currentPos=0;
        while (inertia!=0 || ride[currentPos]!='_'){
            switch (ride[currentPos]){
                case '/':
                    inertia-= inertia>=0 ? 10 : 9;
                    break;
                case '\\':
                    inertia+= inertia>=0 ? 9 : 10;
                    break;
                case '_':
                    inertia-= inertia>0 ? 1 : -1;
                    break;
            }
            if (inertia<0){
                if (currentPos==0)
                    break;
                currentPos--;
            } else if (inertia>0){
                if (currentPos==W-1)
                    break;
                currentPos++;
            }
            System.err.format("%d (%d)%n", currentPos, inertia);
        }
        System.out.println(currentPos);
    }
}