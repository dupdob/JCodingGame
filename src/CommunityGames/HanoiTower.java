package CommunityGames;

import java.util.*;

/**
 The classic game of Hanoi tower consists of a stack of wooden disks of various, unique size and three axes. At the beginning of the game, all disks are stacked on the left axis, in decreasing size (largest disk at the bottom). The goal of the game is to move the entire stack to the right axis, moving one disk at a time and always placing a disk on an empty stack or a larger disk.

 A trivial algorithm for solving the game is the following:
 - move the smallest disk one axis to the right if the number of disks is even, to the left if the number of disks is odd
 - then make the single other possible move not involving the smallest disk
 - reiterate this process until the stack is fully on the rightmost axis

 You must write a program that implements this algorithm and:
 1. computes the number of steps required to complete the game
 2. displays the state of the game after a given number of turns
 **/
    class HanoiTower {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int T = in.nextInt();

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        final ArrayList<Integer> positions = positions(N, T);
        final Map<Integer, List<Integer>> axes = new HashMap<>();
        for (int i = 0; i < positions.size(); i++) {
            final Integer key = positions.get(i);
            if (!axes.containsKey(key)){
                axes.put(key, new ArrayList<>());
            }
            axes.get(key).add(i);
        }
        // printout the game
        for (int i = 0; i < N; i++) {
            String line = "";
            for (int j = 0; j < 3; j++) {
                String printout;
                final List<Integer> discList = axes.get(j);
                if (discList==null || discList.size()<N-i) {
                    // no disc yey
                    printout="|";
                } else {
                    // get the disc
                    int disc = discList.get(i-(N-discList.size()));
                    printout="#";
                    for (int k = 0; k <=disc; k++) {
                        printout += "##";
                    }
                }
                int length = (printout.length()-1)/2;
                for (int k = 0; k < N-length; k++) {
                    printout = " "+printout;
                    if (j<2){
                        printout +=" ";
                    }
                }
                if (j<2) {
                    printout+= " ";
                }
                line+=printout;
            }
            System.out.println(line);
        }
        System.out.println((int)(Math.pow(2, N)-1));
    }

    private static ArrayList<Integer> positions(int height, int step) {
        ArrayList<Integer> positions;
        if (height == 0) {
            return new ArrayList<>();
        }
        if (step % 2 == 0) {
            positions = positions(height - 1, step / 2);
            positions.add(0, smallestPos(height, step-1));
        } else {
            positions = positions(height - 1, (step-1) / 2);
            positions.add(0,smallestPos(height, step));
        }
        return positions;
    }

    // pins are numbered 0, 1, 2
    private static int smallestPos(int height, int step) {
        if (step <= 0) {
            return 0;
        }
        if (step % 2 == 1) {
            switch (step % 6) {
                case 1:
                    return (height % 2) == 0 ? 1 : 2;
                case 3:
                    return (height % 2) == 0 ? 2 : 1;
                case 5:
                    return 0;
            }
            return 0;
        } else {
            return smallestPos(height, step-1);
        }
    }
}