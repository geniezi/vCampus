package UIviewer.SelectCourse;

import ClientToServer.myInfo;

import javax.swing.*;
import java.awt.*;

/**
 * 当前课程人
 *
 * @author 28468
 * @date 2022/09/03
 */
public class CurrentCourse_Man extends JPanel {

    /*public CurrentCourse_Man()
    {
        setLayout(null);
        JPanel p18=new JPanel();
        p18.setBounds(0,0,1273,784);

        JLabel l1 = new JLabel("  你好！");
        l1.setBounds(170, 100, 100, 75);
        Font font = new Font("楷体", Font.BOLD, 22);
        l1.setFont(font);
        //l1.setForeground(new Color(111,222,0));
        add(l1);

        //信息面板
        JLabel l4 = new JLabel("      基本信息");
        l4.setBounds(23, 205, 250, 60);
        Font font2 = new Font("楷体", Font.BOLD, 25);
        l4.setFont(font2);
        l4.setForeground(new Color(94, 38, 18));
        add(l4);

        String name = "1";
        JLabel l2 = new JLabel(" 姓名：" + myInfo.getName());
        l2.setBounds(30, 300, 250, 60);
        Font font1 = new Font("微软雅黑", Font.PLAIN, 18);
        l2.setFont(font1);
        l2.setForeground(new Color(0, 0, 0));
        add(l2);
        String card = "";
        JLabel l3 = new JLabel(" 卡号：" + myInfo.getId());
        l3.setBounds(30, 410, 250, 60);
        l3.setFont(font1);
        l3.setForeground(new Color(0, 0, 0));
        add(l3);

        *//*JLabel l12 = new JLabel();
        ImageIcon icon5 = new ImageIcon("src/image/banner3.png");
        l12.setIcon(icon5);
        l12.setBounds(310, 70, 1000, 125);
        add(l12);*//*

        JPanel p1 = new JPanel();
        p1.setBounds(0, 0, 310, 784);
        p1.setBackground(new Color(135, 206, 250, 180));
        add(p1);

        //搜索功能
        JLabel lblNewLabel = new JLabel("课程信息:");
        lblNewLabel.setFont(new Font("宋体", Font.BOLD, 25));
        lblNewLabel.setBounds(370, 115, 275, 35);
        add(lblNewLabel);

        JTextField textField1 = new JTextField();
        textField1.setFont(new Font("宋体", Font.BOLD, 18));
        textField1.setBounds(520, 115, 375, 35);
        add(textField1);
        textField1.setColumns(10);

        JButton btnNewButton_10 = new JButton("确认删除");
        btnNewButton_10.setBounds(980, 115, 100, 40);
        Font myfont9 = new Font("微软雅黑", Font.PLAIN, 12);
        btnNewButton_10.setFont(myfont9);
        btnNewButton_10.setBackground(new Color(248, 248, 255));
        btnNewButton_10.setContentAreaFilled(true);//设置按钮透明
        add(btnNewButton_10);

        //右下面板
        JPanel p3 = new JPanel();
        p3.setBounds(310, 0, 950, 685);
        p3.setBackground(new Color(211, 211, 211, 100));
        add(p3);


        //右侧面板
        JPanel p2 = new JPanel();
        p2.setBounds(310, 0, 950, 1000);
        p2.setBackground(new Color(245, 245, 245, 180));
        add(p2);

        //横向图片
        JLabel l16 = new JLabel();
        ImageIcon icon7 = new ImageIcon("src/image/background2.jpg");
        l16.setIcon(icon7);
        l16.setBounds(0, 0, 1273, 790);
        add(l16);

        add(p18);

    }*/
}
