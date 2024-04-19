import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Arayuz extends JFrame {
    JFrame anaFrame= new JFrame();
    static JPanel[][] cell = new JPanel[100][100];
    JTextField mazeSize;
    JButton baslaButonu;
    JButton bitirButonu;
    int heightFrame=800;
    int widthFrame=1400;
    int speed=50;
    boolean firstTime=false;
    int satir=30;
    int sutun=30;
    int boyut=20;
    int bloksayaci=0;
    boolean bittiMi=false;

    static long beginTime = 0;
    static long endTime = 0;
    static Label timeLabel = new Label();
    static Label blokLabel = new Label();
    Arayuz() {
        anaFrame.setLayout(null);
        anaFrame.setBounds(0,0,widthFrame+20,heightFrame+50);
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
        timeLabel.setBounds(1050,500,300,100);
        timeLabel.setBackground(Color.pink);
        blokLabel.setBounds(1050,600,300,100);
        blokLabel.setBackground(Color.pink);
        anaFrame.add(timeLabel);
        anaFrame.add(blokLabel);
        beginTime = System.currentTimeMillis();
        baslaButonu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                baslaButonu.setEnabled(false);

                if(firstTime)
                    for (int i = 0; i < boyut+1; i++) {
                        for (int j = 0; j < boyut+1; j++) {
                            anaFrame.remove(cell[i][j]);
                            cell[i][j]=null;
                        }
                    }
                firstTime=true;
                boyut=Integer.parseInt(mazeSize.getText());
                speed=700/boyut;

                (new Worker()).execute();




            }
        });
        bitirButonu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                speed=0;

            }
        });

    }
    void labirentBoya(){
        int[][] labirent = MazeCreator.getMaze();
        for (int i = 0; i < sutun+1; i++) {
            for (int j = 0; j < satir+1; j++) {
                cell[i][j]=new JPanel();
                cell[i][j].setBounds(i*(widthFrame-400)/(sutun+1),j*heightFrame/(satir+1),(widthFrame-400)/(sutun+1)+1,heightFrame/(satir+1)+1);
                anaFrame.getContentPane().add(cell[i][j]);
                if(labirent[i][j]==1)
                    cell[i][j].setBackground(Color.darkGray);
                else
                    cell[i][j].setBackground(Color.gray);
                cell[i][j].setVisible(false);
            }
        }
    }
    class Worker extends SwingWorker<String, Object> {
        @Override
        public String doInBackground() {
            bloksayaci=0;
            beginTime = System.currentTimeMillis();
            MazeCreator.main(boyut,boyut);
            satir=MazeCreator.getWidth();
            sutun=MazeCreator.getHeight();
            labirentBoya();
            bittiMi=false;
            try {
                yolBul(1,1);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            baslaButonu.setEnabled(true);
            Arayuz.endTime = System.currentTimeMillis();
            System.out.println("Çalışma Süresi : " + ((double) (Arayuz.endTime - Arayuz.beginTime)) / 1000 + "Saniye "+"\n" );
            double gecenSure = ((double) (Arayuz.endTime - Arayuz.beginTime)) / 1000;
            Arayuz.timeLabel.setText("Geçen Süre : " + Double.toString((bloksayaci*0.0801232))+ "Saniye");
            Arayuz.blokLabel.setText("Geçilen blok sayısı : " +bloksayaci);
            System.out.println(bloksayaci);
            System.out.println(gecenSure/bloksayaci);
            System.out.println("onluk geçen süre:"+ (bloksayaci*0.0801232));
            for (int i = 0; i < sutun+1; i++) {
                for (int j = 0; j < satir+1; j++) {
                    cell[i][j].setVisible(true);
                }
                }
            return null;
        }
    }
    void yolBul(int satirkonum,int sutunkonum) throws InterruptedException {
        cell[satirkonum+1][sutunkonum-1].setVisible(true);
        cell[satirkonum+1][sutunkonum].setVisible(true);
        cell[satirkonum+1][sutunkonum+1].setVisible(true);
        cell[satirkonum][sutunkonum-1].setVisible(true);
        cell[satirkonum][sutunkonum].setVisible(true);
        cell[satirkonum][sutunkonum+1].setVisible(true);
        cell[satirkonum-1][sutunkonum-1].setVisible(true);
        cell[satirkonum-1][sutunkonum].setVisible(true);
        cell[satirkonum-1][sutunkonum+1].setVisible(true);
        int[][] labirent = MazeCreator.getMaze();
        if(bittiMi)
            return;
        labirent[satirkonum][sutunkonum]=1;
        if((satirkonum==satir-1 && sutunkonum==sutun-1)||(satirkonum==satir-2 && sutunkonum==sutun-2)){
            bittiMi=true;
            cell[satirkonum][sutunkonum].setBackground(Color.blue);
            return;
        }


        Thread.sleep(speed);
        bloksayaci++;
        cell[satirkonum][sutunkonum].setBackground(Color.blue);
        if( sutunkonum!=sutun-1 && labirent[satirkonum][sutunkonum+1]!=1)
            yolBul(satirkonum,sutunkonum+1);
        if( satirkonum!=satir-1 && labirent[satirkonum+1][sutunkonum]!=1)
            yolBul(satirkonum+1,sutunkonum);
        if(labirent[satirkonum-1][sutunkonum]!=1)
            yolBul(satirkonum-1,sutunkonum);
        if(labirent[satirkonum][sutunkonum-1]!=1)
            yolBul(satirkonum,sutunkonum-1);
        if(bittiMi)
            return;
        bloksayaci--;
        cell[satirkonum][sutunkonum].setBackground(Color.red);
        Thread.sleep(speed);

    }
    public static void main(String[] args) {
        new Arayuz();


    }
}
