import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Random;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Arayuz extends JFrame {
    JFrame anaFrame= new JFrame();
    static JPanel[][] cell = new JPanel[100][100];
    JTextField mazeSize;
    JButton baslaButonu;
    JButton bitirButonu;
    int heightFrame=1000;
    int widthFrame=1400;
    int speed=50;
    boolean firstTime=false;
    int satir=30;
    int sutun=30;
    int boyut=20;
    boolean bittiMi=false;
    boolean[][] ziyaretEdilen = new boolean[100][100];
    Random rand = new Random();
    Arayuz() throws InterruptedException {
        anaFrame.setLayout(null);
        anaFrame.setBounds(0,0,widthFrame+20,heightFrame+50);
        //gridOlustur();
        anaFrame.setResizable(true);
        anaFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        anaFrame.setVisible(true);
        mazeSize= new JTextField();
        baslaButonu= new JButton();
        bitirButonu= new JButton();

        anaFrame.getContentPane().add(mazeSize);
        anaFrame.getContentPane().add(baslaButonu);
        anaFrame.getContentPane().add(bitirButonu);
        mazeSize.setBounds(1050,50,300,50);
        baslaButonu.setBounds(1050,150,300,100);
        bitirButonu.setBounds(1050,300,300,100);
        baslaButonu.setText("BASLA");
        bitirButonu.setText("Hemen Bitir");
        baslaButonu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Arrays.fill(button,null);
                baslaButonu.setEnabled(false);

                if(firstTime)
                    for (int i = 0; i < boyut; i++) {
                        for (int j = 0; j < boyut; j++) {
                            anaFrame.remove(cell[i][j]);
                            cell[i][j]=null;
                        }
                    }
                firstTime=true;
                boyut=Integer.parseInt(mazeSize.getText());
                speed=700/boyut;
                (new Arayuz.MysteryWorker()).execute();

            }
        });
        bitirButonu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                speed=0;

            }
        });

    }
    void deneme(){
        int maz[][]= MazeCreator.getMaze();
        for (int i = 0; i < sutun+1; i++) {
            for (int j = 0; j < satir+1; j++) {
                cell[i][j]=new JPanel();
                cell[i][j].setBounds(i*(widthFrame-400)/(sutun+1),j*heightFrame/(satir+1),(widthFrame-400)/(sutun+1)+1,heightFrame/(satir+1)+1);

                anaFrame.getContentPane().add(cell[i][j]);
                if(maz[i][j]==1)
                    cell[i][j].setBackground(Color.darkGray);
                else
                    cell[i][j].setBackground(Color.gray);
            }

        }
    }
    class MysteryWorker extends SwingWorker<String, Object> {

        @Override
        public String doInBackground() {


            MazeCreator.main(boyut,boyut);
            satir=MazeCreator.getWidth();
            sutun=MazeCreator.getHeight();
            deneme();
            bittiMi=false;
            try {
                yolBul(1,1);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            baslaButonu.setEnabled(true);
            return null;
        }

        @Override
        protected void done() {
        }
    }
    void yolBul(int satirkonum,int sutunkonum) throws InterruptedException {
        int maz[][]= MazeCreator.getMaze();
        if(bittiMi)
            return;
        maz[satirkonum][sutunkonum]=1;
        if((satirkonum==satir-1 && sutunkonum==sutun-1)||(satirkonum==satir-2 && sutunkonum==sutun-2)){
            bittiMi=true;
            cell[satirkonum][sutunkonum].setBackground(Color.blue);
            return;
        }


        Thread.sleep(speed);
        cell[satirkonum][sutunkonum].setBackground(Color.blue);
        if( sutunkonum!=sutun-1 && maz[satirkonum][sutunkonum+1]!=1)
            yolBul(satirkonum,sutunkonum+1);
        if( satirkonum!=satir-1 && maz[satirkonum+1][sutunkonum]!=1)
            yolBul(satirkonum+1,sutunkonum);
        if( satirkonum!=0 && maz[satirkonum-1][sutunkonum]!=1)
            yolBul(satirkonum-1,sutunkonum);
        if( sutunkonum!=0 && maz[satirkonum][sutunkonum-1]!=1)
            yolBul(satirkonum,sutunkonum-1);
        if(bittiMi)
            return;
        cell[satirkonum][sutunkonum].setBackground(Color.red);
            Thread.sleep(speed);

    }
    public static void main(String[] args) throws InterruptedException  {
        Arayuz arayuz= new Arayuz();

    }

}


