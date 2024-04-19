import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class KareliEkran extends JFrame  {
   static JFrame frame;
   static Random random = new Random();

    static int labirentt[][];
    static JPanel jp[][];
    public KareliEkran() throws IOException {
        frame = new JFrame();
        izgara iz = new izgara(izgara.fileName); //sayac alınması için
        izgara.dosyaOku(izgara.fileName);
        labirentt = izgara.matrisKopyala();
        kareOlustur(labirentt.length, labirentt,jp);

    }
        public static void ekraniGoster(){
            for (int i = 0; i < jp.length ; i++) {
                for (int j = 0; j < jp.length; j++) {
                    frame.add(jp[i][j]);
                }
            }
        }
    public static void kareOlustur(int sayi, int labirent[][], JPanel jp[][]) {
        frame.setTitle("Problem 1 Labirent");
        frame.setResizable(true);
        frame.setBounds(0,0,1000,900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(sayi,sayi));
        doldur(sayi, labirent, jp);
        frame.setVisible(true);
    }
    public static void BaslangicBelirle(int sayi){
    LabirentBFS.SatirBas = random.nextInt(sayi);
    LabirentBFS.SutunBas= random.nextInt(sayi);
    if(labirentt[LabirentBFS.SatirBas][LabirentBFS.SutunBas] != 0){
        while(labirentt[LabirentBFS.SatirBas][LabirentBFS.SutunBas] !=0){
            LabirentBFS.SatirBas = random.nextInt(sayi);
            LabirentBFS.SutunBas= random.nextInt(sayi);
        }
    }
    jp[LabirentBFS.SatirBas][LabirentBFS.SutunBas].setBackground(Color.black);
    }
    public static void BitisBelirle(int sayi){
        LabirentBFS.SatirBit = random.nextInt(sayi);
        LabirentBFS.SutunBit= random.nextInt(sayi);
        if(labirentt[LabirentBFS.SatirBit][LabirentBFS.SutunBit] != 0){
            while(labirentt[LabirentBFS.SatirBit][LabirentBFS.SutunBit] !=0){
                LabirentBFS.SatirBit = random.nextInt(sayi);
                LabirentBFS.SutunBit= random.nextInt(sayi);
            }
        }
        jp[LabirentBFS.SatirBit][LabirentBFS.SutunBit].setBackground(Color.MAGENTA);
    }
    public static void doldur(int sayi, int geciciMatris[][], JPanel jp[][]) {
        for (int i = 0; i < sayi; i++) {
            for (int j = 0; j < sayi; j++) {
                    jp[i][j] = new JPanel();
                    if (geciciMatris[i][j] != 0) {
                    jp[i][j].setBackground(Color.darkGray);

                } else {
                    jp[i][j].setBackground(Color.gray);
                }
                jp[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                frame.add(jp[i][j]);
            }
        }
    }
    public static void main(String[] args) throws IOException {

    }


}
