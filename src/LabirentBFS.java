    import javax.swing.*;
    import java.awt.*;
    import java.io.IOException;
    import java.util.*;

    public class LabirentBFS {
        JPanel jbaslangic = new JPanel();
        JPanel jbitis = new JPanel();
        static int SatirBas;
        static int SutunBas;
        static int SatirBit;
        static int SutunBit;
        static int sayac=0;
        static int speed =50;
      //   static ArrayList<int[]> enKisaYol = new ArrayList<>();
          public LabirentBFS() throws IOException {
            KareliEkran ekran = new KareliEkran();
            for (int i = 0; i <KareliEkran.jp.length ; i++) {
                for (int j = 0; j <KareliEkran.jp.length ; j++) {
                KareliEkran.jp[i][j].setVisible(false);
                }
            }
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

                    System.out.println(konum[0]+ " " + konum[1]);
                   //  int[][] goruntu = {{SatirGoruntu - 1, SutunGoruntu}, {SatirGoruntu + 1, SutunGoruntu}, {SatirGoruntu, SutunGoruntu - 1}, {SatirGoruntu, SutunGoruntu + 1}};
                    ;
                    while (konum != null) {
                       // Thread.sleep(speed);
                        newAnaEkran.enKisaYol.add(konum);
                                boya(konum);
                                etrafiniBoya(konum);
                            konum = oncekiKonum.get(Arrays.toString(konum));
                            sayac++;


                    }
                   // Collections.reverse(enKisaYol); //sırayı tersine çeviriyor
                    return newAnaEkran.enKisaYol;
                }


                int x = gecici[0];
                int y = gecici[1];
                int[][] komşular = {{x - 1, y}, {x + 1, y}, {x, y - 1}, {x, y + 1}};
                for (int[] komsu : komşular) {
                    if (komsu[0] < 0 || komsu[0] >= labirent.length ||
                            komsu[1] < 0 || komsu[1] >= labirent[0].length ||
                            labirent[komsu[0]][komsu[1]] != 0 ||
                            oncekiKonum.containsKey(Arrays.toString(komsu))) {
                        continue;
                    }
                    komsuYollar.add(komsu);
                    kuyruk.offer(komsu);
                    oncekiKonum.put(Arrays.toString(komsu), gecici);
                //   KareliEkran.jp[gecici[0]][gecici[1]].setBackground(Color.cyan);
                   etrafiniBoya(komsu);

                }
            }



            return null;
        }
        public static void enkisaYoluBoya(){
            for (int i = 0; i <newAnaEkran.enKisaYol.size() ; i++) {
                int gecici[] = new int[2];
                    gecici = newAnaEkran.enKisaYol.get(i);
                    KareliEkran.jp[gecici[0]][gecici[1]].setBackground(Color.pink);
            }

        }
        public static boolean bitisGorunduMu(int baslangicNoktasi[],int hedefNoktasi[]){
              int x = hedefNoktasi[0];
              int y = hedefNoktasi[1];
              if(KareliEkran.jp[x][y].isVisible()){
                  return true;
              }
              return false;
        }
        public static void bitiseGit(int baslangicNoktasi[],int hedefNoktasi[]) throws InterruptedException {
              if(hedefNoktasi[0] == baslangicNoktasi[0]+1 && hedefNoktasi[1] == baslangicNoktasi[1] ){
                  baslangicNoktasi[0] = baslangicNoktasi[0] +1;
                  KareliEkran.jp[baslangicNoktasi[0]][baslangicNoktasi[1]].setBackground(Color.blue);
              }
            if(hedefNoktasi[0] == baslangicNoktasi[0]-1 && hedefNoktasi[1] == baslangicNoktasi[1] ){
                baslangicNoktasi[0] = baslangicNoktasi[0] -1;
                KareliEkran.jp[baslangicNoktasi[0]][baslangicNoktasi[1]].setBackground(Color.blue);
            }
            if(hedefNoktasi[0] == baslangicNoktasi[0] && hedefNoktasi[1] == baslangicNoktasi[1]+1 ){
                baslangicNoktasi[1] = baslangicNoktasi[1] +1;
                KareliEkran.jp[baslangicNoktasi[0]][baslangicNoktasi[1]].setBackground(Color.blue);
            }
            if(hedefNoktasi[0] == baslangicNoktasi[0] && hedefNoktasi[1] == baslangicNoktasi[1]-1 ){
                baslangicNoktasi[1] = baslangicNoktasi[1] -1;
                KareliEkran.jp[baslangicNoktasi[0]][baslangicNoktasi[1]].setBackground(Color.blue);
            }
          }
        public static void rastgeleDolas(int baslangicNoktasi[],int bitisNoktasi[]) throws InterruptedException {
            Random rand = new Random();
            while (!Arrays.equals(baslangicNoktasi,bitisNoktasi)){
                Thread.sleep(speed);
                int x = baslangicNoktasi[0];
                int y = baslangicNoktasi[1];
                int rastgeleSayi = rand.nextInt(4);
                    if(bitisGorunduMu(baslangicNoktasi,bitisNoktasi)){
                        bitiseGit(baslangicNoktasi,bitisNoktasi);
                    }
              switch (rastgeleSayi){
                  case 0 : {
                      if(KareliEkran.labirentt[x+1][y] ==0){
                          baslangicNoktasi[0] = x+1;
                          baslangicNoktasi[1] = y;
                          KareliEkran.jp[baslangicNoktasi[0]][baslangicNoktasi[1]].setBackground(Color.blue);
                          etrafiniBoya(baslangicNoktasi);
                          rastgeleDolas(baslangicNoktasi,bitisNoktasi);

                      }
                      break;
                  }
                  case 1 : {
                      if(KareliEkran.labirentt[x-1][y] ==0){
                          baslangicNoktasi[0] = x-1;
                          baslangicNoktasi[1] = y;
                          KareliEkran.jp[baslangicNoktasi[0]][baslangicNoktasi[1]].setBackground(Color.blue);
                          etrafiniBoya(baslangicNoktasi);
                          rastgeleDolas(baslangicNoktasi,bitisNoktasi);

                      }
                      break;
                  }
                  case 2 : {

                      if(KareliEkran.labirentt[x][y+1] ==0){
                          baslangicNoktasi[0] = x;
                          baslangicNoktasi[1] = y+1;
                          KareliEkran.jp[baslangicNoktasi[0]][baslangicNoktasi[1]].setBackground(Color.blue);
                          etrafiniBoya(baslangicNoktasi);
                          rastgeleDolas(baslangicNoktasi,bitisNoktasi);
                      }
                      break;

                  }
                  case 3 : {
                      if(KareliEkran.labirentt[x][y-1] ==0){
                          baslangicNoktasi[0] = x;
                          baslangicNoktasi[1] = y-1;
                          KareliEkran.jp[baslangicNoktasi[0]][baslangicNoktasi[1]].setBackground(Color.blue);
                         etrafiniBoya(baslangicNoktasi);
                          rastgeleDolas(baslangicNoktasi,bitisNoktasi);
                      }
                      break;
                  }
              }

            }
        }
        public static void etrafiniBoya(int konum[]){
            KareliEkran.jp[konum[0]-1][konum[1]].setVisible(true);
            KareliEkran.jp[konum[0]+1][konum[1]].setVisible(true);
            KareliEkran.jp[konum[0]][konum[1]-1].setVisible(true);
            KareliEkran.jp[konum[0]][konum[1]+1].setVisible(true);
        }
        public static void baslangicBoya(){
            KareliEkran.jp[SatirBas][SutunBas].setBackground(Color.ORANGE);
            KareliEkran.jp[SatirBas][SutunBas].setVisible(false);
            KareliEkran.jp[SatirBit][SutunBit].setBackground(Color.white);
            KareliEkran.jp[SatirBit][SutunBit].setVisible(false);

        }
        public static void baslangicBitisBelirle(){
            KareliEkran.BaslangicBelirle(izgara.sayac);
            KareliEkran.BitisBelirle(izgara.sayac);
        }
        public static void bitisBoya(){
            KareliEkran.jp[SatirBas][SutunBas].setBackground(Color.ORANGE);
            KareliEkran.jp[SatirBit][SutunBit].setBackground(Color.ORANGE);
        }
        public static void boya(int konum[]){
            KareliEkran.jp[konum[0]][konum[1]].setBackground(Color.pink);
        }
        public static void tumEkraniGoster(){
            for (int i = 0; i <KareliEkran.jp.length ; i++) {
                for (int j = 0; j <KareliEkran.jp.length ; j++) {
                    if(!KareliEkran.jp[i][j].isVisible()) {
                        KareliEkran.jp[i][j].setVisible(true);
                    }


                }
            }
        }
        public static void sifirla() {

              SatirBas=0;
              SatirBit=0;
              SutunBas=0;
              SutunBit =0;
           KareliEkran.labirentt = null;
           KareliEkran.jp = null;
           izgara.sayac = 0;
           izgara.satir=0;
           izgara.sutun=0;
           izgara.sayack = 0;
            KareliEkran.frame =null;
            LabirentBFS.speed=400;
          }

        public static void main(String[] args) throws IOException, InterruptedException {

             LabirentBFS lab = new LabirentBFS();
             baslangicBitisBelirle();
             int[] baslangicDugumu = {SatirBas, SutunBas};
             int[] hedefDugumu = {SatirBit, SutunBit};
            int geciciBaslangic[] = new int[baslangicDugumu.length];
            int geciciBitis[] = new int[hedefDugumu.length];
            System.arraycopy(baslangicDugumu,0,geciciBaslangic,0,baslangicDugumu.length);
            System.arraycopy(hedefDugumu,0,geciciBitis,0,baslangicDugumu.length);
             baslangicBoya();
            rastgeleDolas(baslangicDugumu,hedefDugumu);
            System.out.println(baslangicDugumu[0]+ " "+ baslangicDugumu[1]);
             newAnaEkran.enKisaYol = BFS(KareliEkran.labirentt, geciciBitis, geciciBaslangic);
            System.out.println(newAnaEkran.enKisaYol.size());
             bitisBoya();
             Thread.sleep(1500);
             tumEkraniGoster();

         }


        }