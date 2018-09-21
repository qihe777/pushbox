package GUI;

import LOGIC.DataStatic;
import javax.swing.*;
import java.awt.*;


public class MapPanel extends JPanel  {//画背景绿色方块

    @Override
    public void paint(Graphics g) {
        //super.paint(g);//调用父类方法
        int picSize = 35;
        int zuo = (10 - DataStatic.chang / 2) * picSize;
        int shang = (10 - DataStatic.kuan / 2) * picSize;
        for(int i=0;i<DataStatic.kuan;i++){
            for(int j=0;j<DataStatic.chang;j++){
                int x= zuo +j* picSize;
                int y= shang +i* picSize;
                //路是平铺的
                ImageIcon image2=new ImageIcon("lib/imgs/block.gif");
                //image2.setImage(image2.getImage().getScaledInstance(picSize,picSize,Image.SCALE_DEFAULT));//重新设置图片大小
                g.drawImage(image2.getImage(), x, y, picSize,
                        picSize, this);// 图片会自动缩放
            }
        }
    }

    /*@Override
    public void paintComponent(Graphics g) {
        //super.paintComponent(g);//保留元组件
        //根据map长度来确定左下角位置
        int picSize = 35;
        int zuo = (10 - DataStatic.chang / 2) * picSize;
        int shang = (10 - DataStatic.kuan / 2) * picSize;
        for(int i=0;i<DataStatic.kuan;i++){
            for(int j=0;j<DataStatic.chang;j++){
                int x= zuo +j* picSize;
                int y= shang +i* picSize;
                //路是平铺的
                ImageIcon image2=new ImageIcon("lib/imgs/block.gif");
                //image2.setImage(image2.getImage().getScaledInstance(picSize,picSize,Image.SCALE_DEFAULT));//重新设置图片大小
                g.drawImage(image2.getImage(), x, y, picSize,
                        picSize, this);// 图片会自动缩放
            }
        }
    }*/
}
