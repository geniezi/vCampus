package UIviewer.SelectCourse;
import UIhandler.Currirulum.Client_curriculum;
import UIviewer.login.functionChoose;
import net.coobird.thumbnailator.Thumbnails;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


import static UIhandler.Currirulum.Client_curriculum.RequireallCourse;

/**
 * selcourse导演
 *
 * @author Chen_GuanZhi
 * @date 2022/09/03
 */
public class Selcourse_director extends JPanel {
    Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
    int width=(int ) screensize.getWidth(); //得到宽度
    int height=(int ) screensize.getHeight();//获得高度
    double width_r=(double)(width)/1273;
    double height_r=(double)(height)/790;

    public static CardLayout cardLayout=new CardLayout();
    public static JPanel panel=new JPanel();

    /**
     * selcourse导演
     */
    public Selcourse_director()
    {
        setBounds(0,0,(int)(1273*width_r),(int)(790*height_r));
        setLayout(null);
        panel.setBounds(0,(int)(100*height_r),(int)(1273*width_r),(int)(790*height_r));
        add(panel);
        panel.setLayout(cardLayout);

        try {
            RequireallCourse();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        Scheduling f2=new Scheduling();
        panel.add(f2,"f2");

        Check_Course f1=new Check_Course();
        panel.add(f1,"f1");

        /*CurrentCourse_Man f3=new CurrentCourse_Man();
        panel.add(f3,"f3");*/


        Font myfont = new Font("微软雅黑 ", Font.BOLD, 18);


        // 按钮2
        JButton btnNewButton_2 = new JButton("排课");
        btnNewButton_2.setBounds((int)(150*width_r), (int)(50*height_r), (int)(200*width_r), (int)(50*height_r));
        btnNewButton_2.setFont(myfont);
        btnNewButton_2.setFocusPainted(false);
        btnNewButton_2.setBackground(new Color(245, 222, 179));
        btnNewButton_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    RequireallCourse();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        add(btnNewButton_2);


        //按钮4
        JButton btnNewButton_4 = new JButton("审核课程");
        btnNewButton_4.setBounds((int)(550*width_r), (int)(50*height_r), (int)(200*width_r), (int)(50*height_r));
        btnNewButton_4.setFont(myfont);
        btnNewButton_4.setFocusPainted(false);
        btnNewButton_4.setBackground(new Color(250, 240, 230));
        btnNewButton_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel,"f1");
                try {
                    Client_curriculum.Require_all_application();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        add(btnNewButton_4);

        //按钮5
        JButton btnNewButton_5 = new JButton("退出");
        btnNewButton_5.setBounds((int)(950*width_r), (int)(50*height_r), (int)(200*width_r), (int)(50*height_r));
        btnNewButton_5.setFont(myfont);
        btnNewButton_5.setFocusPainted(false);
        btnNewButton_5.setBackground(new Color(245, 222, 179));

        btnNewButton_5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                functionChoose.jf.setContentPane(functionChoose.fc_panel);
                functionChoose.jf.setTitle("functionChoose");
                setVisible(false);
            }
        });
        add(btnNewButton_5);


        //东南大学标志图片
        JLabel l15 = new JLabel();
        ImageIcon icon6 = new ImageIcon("src/image/background2.jpg");
        int icon6_width= 1273;
        int icon6_height=790;
        try {
            Thumbnails.of(new File("src/image/background2.jpg"))
                    .size((int)(icon6_width*width_r), (int)(icon6_height*height_r))
                    .keepAspectRatio(false)
                    .toFile(new File("src/image/background2_min.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        l15.setIcon(new ImageIcon("src/image/background2_min.jpg"));
        l15.setBounds(0, 0, (int)(1273*width_r), (int)(790*height_r));
        add(l15);
        setVisible(true);
    }

}
