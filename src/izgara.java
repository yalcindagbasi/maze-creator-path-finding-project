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
     static    String urlBir = "url1.txt";
       static  String urliki = "url2.txt";
      static String normalHal = "http://bilgisayar.kocaeli.edu.tr/prolab2/";
    static String fileName = "http://bilgisayar.kocaeli.edu.tr/prolab2/"+urliki;
    public izgara(String file) throws IOException {
        URL url = new URL(file);
        String line;

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        while((line=br.readLine())!= null){
            sayac++;
        }
        br.close();
        KareliEkran.labirentt = new int[sayac][sayac];
        KareliEkran.jp = new JPanel[sayac+2][sayac+2];
    }

    public static void dosyaOku(String filename) throws IOException {

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
                    KareliEkran.labirentt[satir][sutun] = Character.getNumericValue(c);
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

    public static int[][] matrisKopyala(){
        int yeniBoyut=KareliEkran.labirentt.length+2; //etrafına duvar için 2 satır 2 sütün çekilmesi için yaptık.
        System.out.println(KareliEkran.labirentt.length);
        int geciciMatris [][] = new int[yeniBoyut][yeniBoyut];
        for (int i = 0; i < KareliEkran.labirentt.length; i++) {
            for (int j = 0; j < KareliEkran.labirentt[i].length; j++) {
                geciciMatris[i+1][j+1] =KareliEkran.labirentt[i][j];
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

}




