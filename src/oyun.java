import java.io.IOException;

public class oyun {

    static int geciciBaslangic[] = new int[2];
    static int geciciBitis[] = new int[2];
    public oyun() throws InterruptedException {
        LabirentBFS.sifirla();
        try {
            LabirentBFS lab = new LabirentBFS();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        LabirentBFS.baslangicBitisBelirle();

        int[] baslangicDugumu = {LabirentBFS.SatirBas, LabirentBFS.SutunBas};
        int[] hedefDugumu = {LabirentBFS.SatirBit, LabirentBFS.SutunBit};
        System.arraycopy(baslangicDugumu,0,geciciBaslangic,0,baslangicDugumu.length);
        System.arraycopy(hedefDugumu,0,geciciBitis,0,baslangicDugumu.length);
        LabirentBFS.baslangicBoya();
        try {
            LabirentBFS.rastgeleDolas(baslangicDugumu,hedefDugumu);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        newAnaEkran.enKisaYol = LabirentBFS.BFS(KareliEkran.labirentt,geciciBitis,geciciBaslangic);
        LabirentBFS.bitisBoya();
        LabirentBFS.tumEkraniGoster();

    }
}
