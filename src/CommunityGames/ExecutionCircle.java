package CommunityGames;

import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class ExecutionCircle {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        long N = in.nextLong();
        long S = in.nextLong();
        in.nextLine();
        String D = in.nextLine();
        System.err.format("guests %d, init %d, %s,%n", N, S, D);
        int move = D.equals("LEFT") ? 1 : -1;

        long offset = 2;
        long winner = 0;
        long temp = N;
        while (temp!=0) {
            if (temp % 2 == 1) {
                winner=(winner+offset)%N;
                System.err.format("add %d (for %d)%n", offset, temp);
            }
            temp/=2;
            offset*=2;
        }
        System.out.print((S+winner*move+N) % N);
    }
}