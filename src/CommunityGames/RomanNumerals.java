package CommunityGames;

import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class RomanNumerals {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String rom1 = in.next();
        String rom2 = in.next();

        int first = scanRoman(rom1);
        int second = scanRoman(rom2);
        System.err.format("First number %d (%s)%n", first, rom1);
        System.err.format("Second number %d (%s)%n", second, rom2);

        int total = first+second;
        System.out.println(toRoman(total));
    }

    private static String toRoman(int total) {
        StringBuilder builder = new StringBuilder(10);
        while (total>999){
            builder.append('M');
            total-=1000;
        }
        if (total>=900){
            builder.append("CM");
            total-=900;
        }
        else if (total>=500){
            builder.append('D');
            total-=500;
        }
        else if (total>=400){
            builder.append("CD");
            total-=400;
        }
        while (total>=100){
            builder.append('C');
            total-=100;
        }
        if (total>=90){
            builder.append("XC");
            total-=90;
        }
        else if (total>=50){
            builder.append('L');
            total-=50;
        }
        else if (total>=40) {
            builder.append("XL");
            total-=40;
        }
        while (total>=10){
            builder.append('X');
            total-=10;
        }
        if (total==9){
            builder.append("IX");
            total-=9;
        } else if (total>=5) {
            builder.append('V');
            total -= 5;
        } else if (total==4) {
            builder.append("IV");
            total-=4;
        }
        while (total>0){
            builder.append('I');
            total-=1;
        }

        return builder.toString();
    }

    private static int scanRoman(String rom1) {
        int ret=0;
        while (!rom1.isEmpty()){
            if (rom1.startsWith("IV")){
                ret+=4;
                rom1 = rom1.substring(2);
            } else if (rom1.startsWith("IX")){
                ret+=9;
                rom1 = rom1.substring(2);
            } else if (rom1.startsWith("XL")){
                ret+=40;
                rom1 = rom1.substring(2);
            } else if (rom1.startsWith("XC")){
                ret+=90;
                rom1 = rom1.substring(2);
            } else if (rom1.startsWith("CD")){
                ret+=400;
                rom1 = rom1.substring(2);
            } else if (rom1.startsWith("CM")){
                ret+=900;
                rom1 = rom1.substring(2);
            } else{
                switch (rom1.charAt(0)){
                    case 'I':
                        ret+=1;
                        break;
                    case 'V':
                        ret+=5;
                        break;
                    case 'X':
                        ret+=10;
                        break;
                    case 'L':
                        ret+=50;
                        break;
                    case 'C':
                        ret+=100;
                        break;
                    case 'D':
                        ret+=500;
                        break;
                    case 'M':
                        ret+=1000;
                        break;
                }
                rom1 = rom1.substring(1);
            }

        }
        return ret;
    }
}