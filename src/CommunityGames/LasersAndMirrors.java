package CommunityGames;

import java.util.Scanner;

class LasersAndMirrors {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int U = in.nextInt();
        int V = in.nextInt();
        String[] corners = {"S", "C", "A", "B"};


        final long ppcm = calculePPCM(U, V);
        final int index = (int) (((ppcm/U) %2)*2+(ppcm/V) %2);
        System.out.format("%s %d", corners[index], ppcm);
    }

    public static long calculePPCM(long Nb1, long Nb2) {
        long Produit, Reste, PPCM;

        Produit = Nb1*Nb2;
        Reste   = Nb1%Nb2;
        while(Reste != 0){
            Nb1 = Nb2;
            Nb2 = Reste;
            Reste = Nb1%Nb2;
        }
        PPCM = Produit/Nb2;
        return PPCM;
    } // fin calculePPCM

}