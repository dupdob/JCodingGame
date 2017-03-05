package CommunityGames;

import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class KolaKoski {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int A = in.nextInt();
        int B = in.nextInt();
        int count=0;
        int max;
        int[] id={A,B};
        StringBuilder result = new StringBuilder(N);

        while (result.length()<=N) {
            if (count>=result.length()) {
                max = id[count%2];
            } else {
                max = result.charAt(count)-'0';
            }
            result.append(fillString(max, (char) (id[count%2]+'0')));
            count++;
        }
        System.out.println(result.substring(0, N));
    }

    public static String fillString(int count,char c) {
        StringBuilder sb = new StringBuilder( count );
        for( int i=0; i<count; i++ ) {
            sb.append( c );
        }
        return sb.toString();
    }
}