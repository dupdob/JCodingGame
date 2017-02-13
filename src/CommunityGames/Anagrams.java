package CommunityGames;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Anagrams {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String phrase = in.nextLine();

        ArrayList<Integer> positions = new ArrayList<>();

        int lastSpace = -1;
        for (int i = 0; i < phrase.length(); i++) {
            if (phrase.charAt(i)== ' ') {
                positions.add(i-lastSpace-1);
                lastSpace = i;
            }
        }
        positions.add(phrase.length()-lastSpace-1);
        Collections.reverse(positions);

        phrase = phrase.replaceAll(" ", "");

        char[] cars= phrase.toCharArray();
        int id;
        ArrayList<Character> storedChar = new ArrayList<>();

        for (int i = 0; i < cars.length; i++) {
            if (cars[i] % 4 == 0){
                storedChar.add(cars[i]);
            }
        }
        id = storedChar.size()-1;
        for (int i = 0; i < cars.length; i++) {
            if (cars[i] % 4 == 0){
                cars[i] = storedChar.get(id);
                id  = (id+1)%storedChar.size();
            }
        }
        storedChar.clear();

        for (int i = 0; i < cars.length; i++) {
            if ( cars[i] % 3 == 1) {
                storedChar.add(cars[i]);
            }
        }
        id = 1;
        for (int i = 0; i < cars.length; i++) {
            if (cars[i] % 3 == 1) {
                cars[i] = storedChar.get(id);
                id  = (id+1)%storedChar.size();
            }
        }
        storedChar.clear();

        for (int i = 0; i < cars.length; i++) {
            if (cars[i] % 2 == 0) {
                storedChar.add(cars[i]);
            }
        }
        id = storedChar.size();
        for (int i = 0; i < cars.length; i++) {
            if (cars[i] % 2 == 0){
                id--;
                cars[i] = storedChar.get(id);
            }
        }
        storedChar.clear();
        phrase = new String(cars);
        StringBuilder builder= new StringBuilder();
        for (int i = 0; i < positions.size(); i++) {
            if (builder.length()>0){
                builder.append(' ');
            }
            builder.append(phrase.substring(0, positions.get(i)));
            phrase = phrase.substring(positions.get(i));
        }
        phrase = builder.toString();
        System.out.println(phrase);
    }
}