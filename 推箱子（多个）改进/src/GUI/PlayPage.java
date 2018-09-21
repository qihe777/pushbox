package GUI;

import COMMAND.FindCommand;

import LOGIC.DataStatic;
import STRUCT.MyPoint;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//地图需要时时更新
public class PlayPage extends JFrame implements ActionListener,KeyListener {
    private JButton btHelp,btSelect;
    private FindCommand findCommand;
    private JLayeredPane fenceng;
    private ImagePanel jp1;
    private int picSize;
    private MapPanel jp2;
    private JPanel jp3;
    PlayPage(){
         //数据初始化
         fenceng = new JLayeredPane();
         DataStatic.fenceng= fenceng;
         jp1=new ImagePanel();
         jp2=new MapPanel();
         jp3 = new JPanel();
         picSize=DataStatic.picSize;
        int picNum = 20;
        int changdu=picSize* picNum;
         btHelp=new JButton("帮助");
         btSelect=new JButton("选择关卡");
        //容器初始化jp1是背景，jp2是地图，jp3添加按钮菜单

        jp1.setBounds(0,0,changdu+changdu/2, changdu);
        jp2.setBounds(0,0,changdu,changdu);
        jp3.setBounds(changdu,0,changdu/2,changdu);

        this.setLayout(null);
        jp1.setLayout(null);
        jp2.setLayout(null);
        jp3.setLayout(null);
        //jp3背景透明
        jp3.setBackground(null);
        jp3.setOpaque(false);
        add(1);
         btHelp.addActionListener(this);
         btSelect.addActionListener(this);
        btHelp.setBounds(20,200,80,40);
        btSelect.setBounds(20,300,80,40);
        jp3.add(btHelp);
        jp3.add(btSelect);
        this.setLayeredPane(fenceng);

        this.addKeyListener(this);
        this.setTitle("Playing");
        this.setResizable(false);
        this.setSize(changdu+changdu/2, changdu);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置点击关闭按钮后的动作
        this.setLocationRelativeTo(null);//居中显示
        this.setVisible(true);
        this.requestFocus();
    }
    private void add(int rank){//删除fenceng中的全部元素，并添加新的元素。
        DataStatic.boxList.clear();
        DataStatic.zhongdian.clear();
        DataStatic.allSitu.clear();
        DataStatic.failPoint.clear();
        DataStatic.boxLabelMap.clear();
        btHelp.setEnabled(true);
        fenceng.removeAll();
        fenceng.repaint();
        jp1.repaint();
        jp2.repaint();
        fenceng.add(jp1,new Integer(0));//P1为最底层
        fenceng.add(jp2,new Integer(1));//p2为倒数第二层
        fenceng.add(jp3,new Integer(1));
        findCommand=new FindCommand();
        char[][] map = findCommand.getMap(rank);
        DataStatic.map= map;
        PeopleLabel peopleLabel = new PeopleLabel();
        DataStatic.peopleLabel= peopleLabel;
        //初始化数据
        MyPoint people = DataStatic.valueToPoint(DataStatic.peopleValue);
        int zuo = (10 - DataStatic.chang / 2) * picSize;
        int shang = (10 - DataStatic.kuan / 2) * picSize;
        DataStatic.zuo= zuo;
        DataStatic.shang= shang;
        for(int i=0;i<DataStatic.boxNum;i++){//创建箱子标签
            JLabel boxLabel=new JLabel(new ImageIcon("lib/imgs/box.png"));
            DataStatic.boxLabelMap.put(DataStatic.boxList.get(i),boxLabel);
        }

        peopleLabel.setBounds(zuo +people.x*picSize-10, shang +people.y*picSize-52,60,80);
        for(int i=0;i<DataStatic.boxNum;i++){
            MyPoint box=DataStatic.boxList.get(i);
            JLabel boxLabel=DataStatic.boxLabelMap.get(box);
            boxLabel.setBounds(zuo +box.x*picSize, shang +box.y*picSize-10,35,45);
            fenceng.add(boxLabel,new Integer(10+box.y*2));
        }

        //全部在 DEFAULT_LAYER层画，label从10开始，z_order=10+行数
        fenceng.add(peopleLabel,new Integer(10+people.y*2));
        //在分层中添加墙和终点的Jlabel，并且为其设置Z_order,在 DEFAULT_LAYER层
        for(int i = 0; i<DataStatic.kuan; i++){
            for(int j=0;j<DataStatic.chang;j++){
                switch (map[i][j]){
                    case '#'://墙
                        JLabel qiang=new JLabel(new ImageIcon("lib/imgs/wall.png"));
                        qiang.setBounds(zuo +j*picSize, shang +i*picSize-10,35,45);
                        fenceng.add(qiang,new Integer(10+i*2));
                        break;
                    case '.'://终点
                        JLabel zhongdian=new JLabel(new ImageIcon("lib/imgs/yelloball.png"));
                        zhongdian.setBounds(zuo +j*picSize, shang +i*picSize,35,35);
                        fenceng.add(zhongdian,new Integer(10+i*2));
                }
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //this.requestFocus();
        if(e.getSource()==btHelp){
            //因为焦点在按钮上，所以点击后不能操作键盘了
            new Thread(new Runnable() {//耗时操作新建线程完成
                @Override
                public void run() {
                    JOptionPane.showMessageDialog(null,"请耐心等待","帮助",JOptionPane.INFORMATION_MESSAGE);
                }
            }).start();
            btHelp.setEnabled(false);
            new Thread(new Runnable() {//耗时操作新建线程完成
                @Override
                public void run() {
                    findCommand.help(DataStatic.valueToPoint(DataStatic.peopleValue));
                }
            }).start();
        }
        else if(e.getSource()==btSelect){
            String xx=JOptionPane.showInputDialog("请选择关卡，本程序未添加错误处理");
            add(Integer.parseInt(xx));
            this.requestFocus();
        }
     }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                findCommand.move(e.getKeyCode());
            }
        }).start();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }



}
