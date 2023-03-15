import org.apache.poi.ss.formula.functions.T;

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


    JButton problembirButton = new JButton();
    JButton problemikiButton = new JButton();
    JButton baslaButonu=new JButton();
    JButton bitirButonu=new JButton();
    JButton urlButonu=new JButton();
    static JLabel degerGoster = new JLabel();

    static ArrayList<int[]> enKisaYol = new ArrayList<>();
    ExecutorService executor = Executors.newSingleThreadExecutor();
    Timer timer;

    public void oyunCalistir (){
        problembirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame menu = new JFrame();
                menu.setLayout(new GridLayout(1,4));
                baslaButonu.setBackground(Color.green);
                menu.setVisible(true);
                menu.setBounds(1300,40,600,300);
                menu.add(baslaButonu);
                baslaButonu.setText("OYUNA BAŞLA");
                baslaButonu.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        executor.execute(new Runnable() {
                            public void run() {
                                try {
                                    LabirentBFS.sayac = 0;
                                    oyun oyun = new oyun();
                                } catch (InterruptedException ex) {
                                    throw new RuntimeException(ex);
                                }
                                System.out.println(LabirentBFS.sayac);
                            }

                        });
                    }
                });

                bitirButonu.setBackground(Color.yellow);
                bitirButonu.setText("HIZLI BİTİR");
                menu.add(bitirButonu);
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
            menu.add(urlButonu);
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
            menu.add(degerGoster);

            //degerGoster.setText("ADIM SAYISI : " + LabirentBFS.sayac + "");

            }



        });


        problemikiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Arayuz arayuz = new Arayuz();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
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
