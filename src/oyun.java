import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class oyun {
    static int geciciBaslangic[] = new int[2];
    static int geciciBitis[] = new int[2];

    public oyun() throws InterruptedException, IOException {
        long startTime = System.currentTimeMillis();
        File dosya = new File("En Kısa Yol Koordinati.txt");
        if(!dosya.exists()){
            dosya.createNewFile();
        }
        FileWriter dosyaYaz = new FileWriter(dosya,false);
        BufferedWriter buff = new BufferedWriter(dosyaYaz);
        LabirentBFS.sifirla();
        try {
            LabirentBFS lab = new LabirentBFS();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        LabirentBFS.baslangicBitisBelirle();

        int[] baslangicDugumu = {LabirentBFS.SatirBas, LabirentBFS.SutunBas};
        int[] hedefDugumu = {LabirentBFS.SatirBit, LabirentBFS.SutunBit};
        System.arraycopy(baslangicDugumu, 0, geciciBaslangic, 0, baslangicDugumu.length);
        System.arraycopy(hedefDugumu, 0, geciciBitis, 0, baslangicDugumu.length);
        LabirentBFS.baslangicBoya();
        try {
            LabirentBFS.rastgeleDolas(baslangicDugumu, hedefDugumu);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        newAnaEkran.enKisaYol = LabirentBFS.BFS(KareliEkran.labirentt, geciciBitis, geciciBaslangic);
        LabirentBFS.bitisBoya();
        LabirentBFS.tumEkraniGoster();
        double endTime = System.currentTimeMillis();
        double total = endTime - startTime;
        newAnaEkran.sureEkrani.setText("GEÇEN SÜRE : " + (total / 1000) + " saniye ");
        System.out.println(newAnaEkran.enKisaYol.size());
        buff.write("Başlangıç Koordinatı : [" + LabirentBFS.SatirBas +" " + LabirentBFS.SutunBas+ "]\n");
        buff.write("Hedef Koordinatı : [" + LabirentBFS.SatirBit + " " + LabirentBFS.SutunBit+"] \n");
        buff.write("EN KISA YOL KOORDİNATLARI\n");
        buff.write("[" + LabirentBFS.SatirBas+"," + " " + LabirentBFS.SutunBas+ "] \n");
        for (int i = newAnaEkran.enKisaYol.size()-1; i >-1 ; i--) {
                int gecici[] = newAnaEkran.enKisaYol.get(i);
                if(KareliEkran.jp[gecici[0]][gecici[1]].getBackground().equals(Color.pink)){
                   buff.write(Arrays.toString(newAnaEkran.enKisaYol.get(i)) + "\n");
                }
        }
        buff.write("[" + LabirentBFS.SatirBit+"," + " " + LabirentBFS.SutunBit+ "] \n");

        buff.close();
    }


}
