import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class KareliEkran extends JFrame {

    public KareliEkran() {

    }
    public void kareOlustur(int sayi, int labirent[][],JPanel jp[][]){
        setTitle("Kareli Ekran");
        setSize(50 * sayi, 50 * sayi);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(sayi,sayi));
        doldur(sayi,labirent,jp);
        setVisible(true);
    }


    public void doldur(int sayi,int geciciMatris[][],JPanel jp[][]){
        for (int i = 0; i < sayi ; i++) {
            for (int j = 0; j <sayi ; j++) {
                jp[i][j] = new JPanel();

                if(geciciMatris[i][j] != 0){
                    jp[i][j].setBackground(Color.darkGray);


                }
                else{
                    jp[i][j].setBackground(Color.gray);


                }



                add(jp[i][j]);
            }

        }
    }

    public static void main(String[] args) throws IOException {
        izgara iz = new izgara(izgara.fileName); //sayac alınması için
        KareliEkran ekran = new KareliEkran();
        int labirent[][] = new int[izgara.sayac][izgara.sayac];
        izgara.dosyaOku(izgara.fileName,labirent);
        labirent = izgara.matrisKopyala(labirent);

        JPanel jp[][] = new JPanel[izgara.sayac+2][izgara.sayac+2];
        System.out.println(labirent.length);
        ekran.kareOlustur(izgara.sayac+2,labirent,jp);






    }

}



