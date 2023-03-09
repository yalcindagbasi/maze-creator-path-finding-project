import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.io.IOException;

public class izgara {
    static int sayac=0;
    static int sayack=0;
    static int satir=0;
    static int sutun=0;
    static String fileName = "http://bilgisayar.kocaeli.edu.tr/prolab2/url1.txt";
    public izgara(String file) throws IOException {
        URL url = new URL(file);
        String line;

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        while((line=br.readLine())!= null){
            sayac++;
        }
        br.close();
    }

    public static void dosyaOku(String filename,int labirentt[][]) throws IOException {

        URL url = new URL(filename);
        String line;

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

        while ((line = br.readLine()) != null) {

            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(sayack);
                if(sayack > sayac || sayack == sayac){
                    break;
                }
                else {
                    labirentt[satir][sutun] = Character.getNumericValue(c);
                    sutun++;
                }
                sayack++;
            }
            satir++;
            sutun=0;
            sayack=0;
        }
        br.close();
    }

    public static int[][] matrisKopyala(int labirent[][]){
        int yeniBoyut=labirent.length+2;
        System.out.println(labirent.length);
        int geciciMatris [][] = new int[yeniBoyut][yeniBoyut];
        for (int i = 0; i < labirent.length; i++) {
            for (int j = 0; j < labirent[i].length; j++) {
                geciciMatris[i+1][j+1] =labirent[i][j];
            }
        }
        etrafinaDuvarCek(yeniBoyut,geciciMatris);
        return geciciMatris;
    }
    public static int[][] etrafinaDuvarCek(int yeniboyut,int geciciMatris[][]){
        for (int i = 0; i < yeniboyut; i++) {
            geciciMatris[0][i] = 1;
            geciciMatris[yeniboyut-1][i] = 1;
            geciciMatris[i][0] = 1;
            geciciMatris[i][yeniboyut-1] =1;
        }
    return geciciMatris;
    }
    public static void main(String args[]) throws IOException {
        izgara iz = new izgara(fileName);
        int labirentt[][] = new int[sayac][sayac];
        dosyaOku(fileName,labirentt);
        System.out.println();
        labirentt = matrisKopyala(labirentt);
        for (int i = 0; i < labirentt.length; i++) {
            for (int j = 0; j < labirentt[i].length; j++) {
                System.out.print(labirentt[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println(labirentt.length);
        }

    }




