package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//利用LayeredPane为Swing控件增加了深度,允许组建在需要的时候互相重叠
//    由低到高的层次是:Default,Palette,Modal,PopUp,Drag
public class MainFrame extends JFrame implements ActionListener {
    private JLayeredPane layeredPane;//用于分层
    private JPanel jp;
    private JLabel jl;
    private JButton btStart,btHelp;
    MainFrame(){
        layeredPane=new JLayeredPane();
        ImageIcon image = new ImageIcon("lib/imgs/主页.jpg");
        jp=new JPanel();
        jp.setBounds(0,50, image.getIconWidth(), image.getIconHeight());
        jl=new JLabel(image);
        jp.add(jl);
        btHelp=new JButton("帮助");
        btStart=new JButton("开始");
        btStart.addActionListener(this);
        btStart.setBounds(600,180,100,40);
        btHelp.setBounds(600,380,100,40);
        //将jp放到最底层。
        layeredPane.add(jp,JLayeredPane.DEFAULT_LAYER);
        //将bt放到高一层的地方
        layeredPane.add(btStart,JLayeredPane.MODAL_LAYER);
        layeredPane.add(btHelp,JLayeredPane.MODAL_LAYER);
        this.setLayout(null);
        this.setTitle("推箱子");
        this.setResizable(false);
        this.setLayeredPane(layeredPane);
        this.setSize(800,590);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置点击关闭按钮后的动作
        this.setLocationRelativeTo(null);//居中显示
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btStart){
            this.setVisible(false);
            //new PlayPage();
        }

    }
}
