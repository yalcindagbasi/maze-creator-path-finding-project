
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Timer;


public class newAnaEkran {

    static JButton sureEkrani= new JButton();
    JButton problembirButton = new JButton();
    JButton problemikiButton = new JButton();
    JButton baslaButonu=new JButton();
    JButton bitirButonu=new JButton();
    JButton urlButonu=new JButton();
    static JButton degerGoster = new JButton();

    static ArrayList<int[]> enKisaYol = new ArrayList<>();
    ExecutorService executor = Executors.newSingleThreadExecutor();


    public void oyunCalistir (){
        problembirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame menu = new JFrame("OYUN MENÜSÜ");
                menu.setLayout(new GridLayout(1,5));
                baslaButonu.setBackground(Color.green);
                menu.setVisible(true);
                menu.setBounds(900,40,1000,300);
                menu.add(baslaButonu,BorderLayout.CENTER);
                baslaButonu.setText("OYUNA BAŞLA");
                baslaButonu.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        executor.execute(new Runnable() {
                            public void run() {
                                try {
                                    
                                    sureEkrani.setVisible(false);
                                    LabirentBFS.sayac = 0;
                                    oyun oyun = new oyun();
                                    sureEkrani.setVisible(true);
                                } catch (InterruptedException ex) {
                                    throw new RuntimeException(ex);
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }

                            }

                        });
                    }
                });

                bitirButonu.setBackground(Color.yellow);
                bitirButonu.setText("HIZLI BİTİR");
                menu.add(bitirButonu,BorderLayout.CENTER);
                bitirButonu.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        enKisaYol.clear();
                        LabirentBFS.speed = 0;


                        try {Thread.sleep(400);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }

                        try {
                            enKisaYol = LabirentBFS.BFS(KareliEkran.labirentt,oyun.geciciBitis,oyun.geciciBaslangic);
                            LabirentBFS.bitisBoya();
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                       // System.out.println(enKisaYol.size());

                    }
                });
            menu.add(urlButonu,BorderLayout.CENTER);
            urlButonu.setBackground(Color.gray);
            urlButonu.setText("URL DEĞİŞTİR");

            urlButonu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(izgara.fileName.indexOf(izgara.urlBir) != -1){
                        izgara.fileName = izgara.normalHal+izgara.urliki;
                    }
                    else{
                        izgara.fileName = izgara.normalHal + izgara.urlBir;
                    }
                }
            });
            degerGoster.setBackground(Color.CYAN);
            menu.add(degerGoster,BorderLayout.CENTER);

                sureEkrani.setBackground(Color.pink);
                menu.add(sureEkrani,BorderLayout.CENTER);

            }


        });


        problemikiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Arayuz arayuz = new Arayuz();
            }
        });



    }



    public newAnaEkran (){
        JFrame anaEkran = new JFrame("ANA EKRAN");
        anaEkran.setBounds(500,50,500,800);
        anaEkran.setVisible(true);
        anaEkran.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        anaEkran.setLayout(null);
        anaEkran.setBackground(Color.black);
        problembirButton.setText("PROBLEM 1 ");
        problembirButton.setBounds(150,200,200,100);
        anaEkran.getContentPane().add(problembirButton);
        problemikiButton.setText("PROBLEM 2 ");
        problemikiButton.setBounds(150,400,200,100);
        anaEkran.getContentPane().add(problemikiButton);
        problemikiButton.setBackground(Color.pink);
        problembirButton.setBackground(Color.pink);



    }

    public static void main(String[] args) throws InterruptedException, IOException {

    newAnaEkran anaEkran1 = new newAnaEkran();
    anaEkran1.oyunCalistir();

}

}
