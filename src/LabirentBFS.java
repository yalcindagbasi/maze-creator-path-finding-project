import java.awt.*;
import java.io.IOException;
import java.util.Queue;
import java.util.*;

public class LabirentBFS {

    static int SatirBas;
    static int SutunBas;
    static int SatirBit;
    static int SutunBit;
    static int sayac = 0;
    static int speed = 150;
    int time;
    static ArrayList<int[]> gezilenYerler = new ArrayList<>();

    public LabirentBFS() throws IOException {
        KareliEkran ekran = new KareliEkran();
        for (int i = 0; i < KareliEkran.jp.length; i++) {
            for (int j = 0; j < KareliEkran.jp.length; j++) {
                KareliEkran.jp[i][j].setVisible(false);
            }
        }
    }


    public static boolean bitisGorunduMu(int baslangicNoktasi[], int hedefNoktasi[]) {
        int x = hedefNoktasi[0];
        int y = hedefNoktasi[1];
        return KareliEkran.jp[x][y].isVisible();
    }

    public static void bitiseGit(int baslangicNoktasi[], int hedefNoktasi[]) throws InterruptedException {
        if (hedefNoktasi[0] == baslangicNoktasi[0] + 1 && hedefNoktasi[1] == baslangicNoktasi[1]) {
            baslangicNoktasi[0] = baslangicNoktasi[0] + 1;
            KareliEkran.jp[baslangicNoktasi[0]][baslangicNoktasi[1]].setBackground(Color.blue);
        }
        if (hedefNoktasi[0] == baslangicNoktasi[0] - 1 && hedefNoktasi[1] == baslangicNoktasi[1]) {
            baslangicNoktasi[0] = baslangicNoktasi[0] - 1;
            KareliEkran.jp[baslangicNoktasi[0]][baslangicNoktasi[1]].setBackground(Color.blue);
        }
        if (hedefNoktasi[0] == baslangicNoktasi[0] && hedefNoktasi[1] == baslangicNoktasi[1] + 1) {
            baslangicNoktasi[1] = baslangicNoktasi[1] + 1;
            KareliEkran.jp[baslangicNoktasi[0]][baslangicNoktasi[1]].setBackground(Color.blue);
        }
        if (hedefNoktasi[0] == baslangicNoktasi[0] && hedefNoktasi[1] == baslangicNoktasi[1] - 1) {
            baslangicNoktasi[1] = baslangicNoktasi[1] - 1;
            KareliEkran.jp[baslangicNoktasi[0]][baslangicNoktasi[1]].setBackground(Color.blue);
        }
    }

    public static int[] enYakinNoktaBul(int baslangicNoktasi[]) {
        int index = -1;
        Set<int[]> liste = new LinkedHashSet<>();
        for (int[] gecici : gezilenYerler) {
            if (KareliEkran.jp[gecici[0]][gecici[1]].getBackground().equals(Color.gray)) {
                liste.add(gecici);
            }
        }
        double kisaUzunluk = Double.MAX_VALUE;
        double[] enkisaUzunluk = new double[liste.size()];
        int i = 0;
        for (int[] gecici : liste) {
            double uzunluk = Math.sqrt(Math.pow(gecici[1] - baslangicNoktasi[1], 2) + Math.pow(gecici[0] - baslangicNoktasi[0], 2));
            enkisaUzunluk[i] = uzunluk;
            if (uzunluk < kisaUzunluk) {
                kisaUzunluk = uzunluk;
                index = i;
            }
            i++;
        }
        return (int[]) liste.toArray()[index];
    }

    public static ArrayList<int[]> BFS(int[][] labirent, int[] hedefDugumu, int[] baslangicDugumu) throws InterruptedException {
        ArrayList<int[]> komsuYollar = new ArrayList<>();
        Queue<int[]> kuyruk = new LinkedList<>();
        Map<String, int[]> oncekiKonum = new HashMap<>();
        kuyruk.offer(baslangicDugumu);
        oncekiKonum.put(Arrays.toString(baslangicDugumu), null); // Başlangıç düğümünün önceki konumu yok

        while (!kuyruk.isEmpty()) {
            int[] gecici = kuyruk.poll();
            if (Arrays.equals(gecici, hedefDugumu)) {
                int[] konum = gecici;


                while (konum != null) {
                    newAnaEkran.enKisaYol.add(konum);
                    Thread.sleep(speed);
                    boya(konum);
                    System.out.println(Arrays.toString(konum) + "boyandı");
                    etrafiniBoya(konum);
                    konum = oncekiKonum.get(Arrays.toString(konum));
                    sayac++;


                }
                return newAnaEkran.enKisaYol;
            }

            int x = gecici[0];
            int y = gecici[1];
            int[][] komsular = {{x - 1, y}, {x + 1, y}, {x, y - 1}, {x, y + 1}};
            for (int[] komsu : komsular) {
                if (komsu[0] < 0 || komsu[0] >= labirent.length || komsu[1] < 0 || komsu[1] >= labirent[0].length || labirent[komsu[0]][komsu[1]] != 0 || oncekiKonum.containsKey(Arrays.toString(komsu))) {
                    continue;
                }
                komsuYollar.add(komsu);
                kuyruk.offer(komsu);
                oncekiKonum.put(Arrays.toString(komsu), gecici);

            }
        }


        return null;
    }

    public static void rastgeleDolas(int[] baslangicNoktasi, int[] bitisNoktasi) throws InterruptedException {

        sayac++;
        if ((bitisNoktasi[0] == baslangicNoktasi[0] && baslangicNoktasi[1] == bitisNoktasi[1] + 1) || (bitisNoktasi[0] == baslangicNoktasi[0] && baslangicNoktasi[1] == bitisNoktasi[1] - 1)) {
            newAnaEkran.degerGoster.setText("HEMEN YANINDA");

        } else if ((bitisNoktasi[1] == baslangicNoktasi[1] && baslangicNoktasi[0] == bitisNoktasi[0] + 1) || (bitisNoktasi[1] == baslangicNoktasi[1] && baslangicNoktasi[0] == bitisNoktasi[0] - 1)) {
            newAnaEkran.degerGoster.setText("HEMEN YANINDA");
        } else if (Arrays.equals(baslangicNoktasi, bitisNoktasi)) {
            newAnaEkran.degerGoster.setText("BAŞLANGIÇ VE HEDEF\nNOKTASI AYNI YERDİR");
        } else {
            while (!Arrays.equals(baslangicNoktasi, bitisNoktasi)) {

                Random rand = new Random();
                Thread.sleep(speed);
                int x = baslangicNoktasi[0];
                int y = baslangicNoktasi[1];
                int rastgeleSayi = 0;
                if (bitisGorunduMu(baslangicNoktasi, bitisNoktasi)) {
                    bitiseGit(baslangicNoktasi, bitisNoktasi);
                    break;
                } else {
                    if (KareliEkran.jp[x - 1][y].getBackground().equals(Color.gray) || KareliEkran.jp[x + 1][y].getBackground().equals(Color.gray) || KareliEkran.jp[x][y - 1].getBackground().equals(Color.gray) || KareliEkran.jp[x][y + 1].getBackground().equals(Color.gray)) {
                        if (KareliEkran.jp[x - 1][y].getBackground().equals(Color.gray)) rastgeleSayi = 1;
                        else if (KareliEkran.jp[x][y - 1].getBackground().equals(Color.gray)) rastgeleSayi = 3;
                        else if (KareliEkran.jp[x + 1][y].getBackground().equals(Color.gray)) rastgeleSayi = 0;
                        else if (KareliEkran.jp[x][y + 1].getBackground().equals(Color.gray)) rastgeleSayi = 2;
                    } else {
                        int[] gecici = enYakinNoktaBul(baslangicNoktasi);
                        ArrayList<int[]> geciciArraylist = new ArrayList<>();
                        geciciArraylist = BFS(KareliEkran.labirentt, gecici, baslangicNoktasi);
                        for (int i = 0; i < geciciArraylist.size(); i++) {
                            int ekranGecici[] = geciciArraylist.get(i);
                            if (KareliEkran.jp[ekranGecici[0]][ekranGecici[1]].getBackground().equals(Color.pink)) {
                                KareliEkran.jp[ekranGecici[0]][ekranGecici[1]].setBackground(Color.blue);
                            }
                        }
                        System.out.println("Başlangıc : " + Arrays.toString(baslangicNoktasi));
                        rastgeleSayi = rand.nextInt(4);

                        if (KareliEkran.jp[baslangicNoktasi[0] - 1][baslangicNoktasi[1]].getBackground().equals(Color.gray) || KareliEkran.jp[baslangicNoktasi[0] + 1][baslangicNoktasi[1]].getBackground().equals(Color.gray) || KareliEkran.jp[baslangicNoktasi[0]][baslangicNoktasi[1] - 1].getBackground().equals(Color.gray) || KareliEkran.jp[baslangicNoktasi[1]][baslangicNoktasi[1] + 1].getBackground().equals(Color.gray)) {

                            if (KareliEkran.jp[baslangicNoktasi[0] - 1][baslangicNoktasi[1]].getBackground().equals(Color.gray))
                                rastgeleSayi = 1;
                            else if (KareliEkran.jp[baslangicNoktasi[0] + 1][baslangicNoktasi[1]].getBackground().equals(Color.gray))
                                rastgeleSayi = 0;

                            else if (KareliEkran.jp[baslangicNoktasi[0]][baslangicNoktasi[1] - 1].getBackground().equals(Color.gray))
                                rastgeleSayi = 3;


                            else if (KareliEkran.jp[baslangicNoktasi[0]][baslangicNoktasi[0] + 1].getBackground().equals(Color.gray))
                                rastgeleSayi = 2;

                        }
                        baslangicNoktasi[0] = gecici[0];
                        baslangicNoktasi[1] = gecici[1];
                        System.out.println("Baslangic Sonra : " + Arrays.toString(baslangicNoktasi));
                    }

                    switch (rastgeleSayi) {
                        case 0 -> {
                            if (KareliEkran.labirentt[baslangicNoktasi[0] + 1][baslangicNoktasi[1]] == 0) {
                                baslangicNoktasi[0] = baslangicNoktasi[0] + 1;
                                EkrandaYerGoster(baslangicNoktasi);

                            }
                        }
                        case 1 -> {
                            if (KareliEkran.labirentt[baslangicNoktasi[0] - 1][baslangicNoktasi[1]] == 0) {
                                baslangicNoktasi[0] = baslangicNoktasi[0] - 1;
                                EkrandaYerGoster(baslangicNoktasi);
                            }
                        }
                        case 2 -> {

                            if (KareliEkran.labirentt[baslangicNoktasi[0]][baslangicNoktasi[1] + 1] == 0) {
                                baslangicNoktasi[1] = baslangicNoktasi[1] + 1;
                                EkrandaYerGoster(baslangicNoktasi);
                            }
                        }
                        case 3 -> {
                            if (KareliEkran.labirentt[baslangicNoktasi[0]][baslangicNoktasi[1] - 1] == 0) {
                                baslangicNoktasi[1] = baslangicNoktasi[1] - 1;
                                EkrandaYerGoster(baslangicNoktasi);


                            }
                        }
                    }
                }
            }
        }
    }

    private static void EkrandaYerGoster(int[] baslangicNoktasi) throws InterruptedException {

        KareliEkran.jp[baslangicNoktasi[0]][baslangicNoktasi[1]].setBackground(Color.red);
        Thread.sleep(speed);
        KareliEkran.jp[baslangicNoktasi[0]][baslangicNoktasi[1]].setBackground(Color.blue);

        sayac++;
        newAnaEkran.degerGoster.setText("\tADIM SAYISI : " + sayac + "");
        etrafiniBoya(baslangicNoktasi);
    }


    public static void etrafiniBoya(int konum[]) {
        KareliEkran.jp[konum[0] - 1][konum[1]].setVisible(true);
        gezilenYerler.add(new int[]{konum[0] - 1, konum[1]});
        KareliEkran.jp[konum[0] + 1][konum[1]].setVisible(true);
        gezilenYerler.add(new int[]{konum[0] + 1, konum[1]});
        KareliEkran.jp[konum[0]][konum[1] - 1].setVisible(true);
        gezilenYerler.add(new int[]{konum[0], konum[1] - 1});
        KareliEkran.jp[konum[0]][konum[1] + 1].setVisible(true);
        gezilenYerler.add(new int[]{konum[0], konum[1] + 1});

    }

    public static void baslangicBoya() {
        KareliEkran.jp[SatirBas][SutunBas].setBackground(Color.BLACK);
        KareliEkran.jp[SatirBas][SutunBas].setVisible(false);
    }

    public static void baslangicBitisBelirle() {
        KareliEkran.BaslangicBelirle(izgara.sayac);
        KareliEkran.BitisBelirle(izgara.sayac);
    }

    public static void bitisBoya() {
        KareliEkran.jp[SatirBas][SutunBas].setBackground(Color.black);
        KareliEkran.jp[SatirBit][SutunBit].setBackground(Color.magenta);
    }

    public static void boya(int konum[]) {
        KareliEkran.jp[konum[0]][konum[1]].setBackground(Color.pink);
    }

    public static void tumEkraniGoster() {
        for (int i = 0; i < KareliEkran.jp.length; i++) {
            for (int j = 0; j < KareliEkran.jp.length; j++) {
                if (!KareliEkran.jp[i][j].isVisible()) {
                    KareliEkran.jp[i][j].setVisible(true);
                }


            }
        }
    }

    public static void sifirla() {

        SatirBas = 0;
        SatirBit = 0;
        SutunBas = 0;
        SutunBit = 0;
        KareliEkran.labirentt = null;
        KareliEkran.jp = null;
        izgara.sayac = 0;
        izgara.satir = 0;
        izgara.sutun = 0;
        izgara.sayack = 0;
        KareliEkran.frame = null;
        LabirentBFS.speed = 150;
        LabirentBFS.sayac = 0;
    }

    public static void main(String[] args) throws IOException, InterruptedException {


    }


}