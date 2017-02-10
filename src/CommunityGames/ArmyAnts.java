package CommunityGames;

import java.io.Console;
import java.util.Scanner;

/**
 It is a well known fact that ants always move in a straight line. However, not many people know what happens when two groups of ants meet face to face in a passage which is too narrow for any two ants to evade laterally each other. One theory suggests that they start jumping over each other.

 From the moment the groups meet, every second, ants which are facing an ant moving in opposite direction jump (or gets jumped over, depending on the deal they made) over it.

 For example, given two groups, say ABC and DEF.
 When they meet it's like this: CBADEF
 Since they can't bypass each other, they start jumping:
 After 1 second, it becomes CBDAEF
 After 2 seconds, it becomes CDBEAF
 After 3 seconds, it becomes DCEBFA
 After 4 seconds, it becomes DECFBA
 And finally, it becomes DEFCBA

 From this point on, groups have jumped over each other and any seconds after this, the state remains the same.


 Your job is to determine the order of ants in the passage after T seconds.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N1 = in.nextInt();
        int N2 = in.nextInt();
        String S1 = in.next();
        String S2 = in.next();

        int T = in.nextInt();
        char[] result = new char[N1+N2];
        for (int i = 0; i < result.length; i++) {
            result[i]=' ';
        }
        System.err.format("(%d) %s%n", N1, S1);
        System.err.format("(%d) %s%n", N2, S2);
        System.err.format("%d%n", T);
        for (int i = 0; i < N1; i++) {
            int y = Math.max(T-i, 0);
            int x = Math.max(N2-y+i, i);
            result[N1+N2-1-x] = S1.charAt(i);
        }

        for (int i = 0; i < N2; i++) {
            int y = Math.max(T-i, 0);
            int x = Math.max(N1-y+i, i);
            result[x] = S2.charAt(i);
        }

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(new String(result));
    }
}