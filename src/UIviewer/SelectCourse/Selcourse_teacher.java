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
import java.nio.channels.spi.AbstractInterruptibleChannel;

/**
 * selcourse老师
 *
 * @author 28468
 * @date 2022/09/03
 */
public class Selcourse_teacher extends JPanel {
    /**
     * 拉
     */
    Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
    /**
     * 宽度
     */
    int width=(int ) screensize.getWidth(); //得到宽度
    /**
     * 高度
     */
    int height=(int ) screensize.getHeight();//获得高度
    /**
     * 宽度r
     */
    double width_r=(double)(width)/1273;
    /**
     * 高r
     */
    double height_r=(double)(height)/790;
    /**
     * 卡布局
     */
    public static CardLayout cardLayout=new CardLayout();
    /**
     * 面板
     */
    public static JPanel panel=new JPanel();
    /**
     * 卡layout1
     */
    public static CardLayout cardLayout1=new CardLayout();
    /**
     * panel1
     */
    public static JPanel panel1=new JPanel();

    /**
     * selcourse老师
     */
    public Selcourse_teacher()
    {
        setBounds(0,0,(int)(1273*width_r),(int)(790*height_r));
        setLayout(null);
        panel.setBounds(0,(int)(100*height_r),(int)(1273*width_r),(int)(790*height_r));
        panel1.setBounds(0,(int)(200*height_r),(int)(1273*width_r),(int)(500*height_r));
        add(panel);
        add(panel1);
        panel1.setLayout(cardLayout1);
        panel.setLayout(cardLayout);
        ConsultCourse_Info f1=new ConsultCourse_Info();
        panel.add(f1,"f1");

        ConsultCourse_stuInfo f2=new ConsultCourse_stuInfo();
        panel.add(f2,"f2");

        Check_Coustatus f3=new Check_Coustatus();
        panel.add(f3,"f3");

        Declare_Course f4=new Declare_Course();
        panel.add(f4,"f4");

        My_Coursetable f5=new My_Coursetable(1);
        panel.add(f5,"f5");

        //六个按钮
        // 按钮1
        JButton btnNewButton_1 = new JButton("查询课程信息");
        btnNewButton_1.setBounds((int)(95*width_r), (int)(50*height_r), (int)(160*width_r), (int)(50*height_r));
        Font myfont = new Font("微软雅黑 ", Font.BOLD, 18);
        btnNewButton_1.setFont(myfont);
        btnNewButton_1.setFocusPainted(false);
        btnNewButton_1.setBackground(new Color(220, 220, 220));
        btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel,"f1");
            }
        });
        add(btnNewButton_1);

        // 按钮2
        JButton btnNewButton_2 = new JButton("查询选课情况");
        btnNewButton_2.setBounds((int)(275*width_r), (int)(50*height_r), (int)(160*width_r), (int)(50*height_r));
        btnNewButton_2.setFont(myfont);
        btnNewButton_2.setFocusPainted(false);
        btnNewButton_2.setBackground(new Color(250, 255, 240));
        btnNewButton_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Client_curriculum.requireTeacherCourse();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        add(btnNewButton_2);

        // 按钮3
        JButton btnNewButton_3 = new JButton("申报课程状态");
        btnNewButton_3.setBounds((int)(455*width_r), (int)(50*height_r), (int)(160*width_r), (int)(50*height_r));
        btnNewButton_3.setFont(myfont);
        btnNewButton_3.setFocusPainted(false);
        btnNewButton_3.setBackground(new Color(250, 240, 230));
        btnNewButton_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel,"f3");
                try {
                    Client_curriculum.Require_my_apply();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        add(btnNewButton_3);

        //按钮4
        JButton btnNewButton_4 = new JButton("申报课程");
        btnNewButton_4.setBounds((int)(635*width_r), (int)(50*height_r), (int)(160*width_r), (int)(50*height_r));
        btnNewButton_4.setFont(myfont);
        btnNewButton_4.setFocusPainted(false);
        btnNewButton_4.setBackground(new Color(220, 220, 220));
        btnNewButton_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel,"f4");
            }
        });
        add(btnNewButton_4);

        //按钮5
        JButton btnNewButton_5 = new JButton("我的课表");
        btnNewButton_5.setBounds((int)(815*width_r), (int)(50*height_r), (int)(160*width_r), (int)(50*height_r));
        btnNewButton_5.setFont(myfont);
        btnNewButton_5.setFocusPainted(false);
        btnNewButton_5.setBackground(new Color(250, 255, 240));
        btnNewButton_5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Client_curriculum.RequireTeaSchedule();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        add(btnNewButton_5);

        //按钮6
        JButton btnNewButton_6 = new JButton("退出");
        btnNewButton_6.setBounds((int)(995*width_r), (int)(50*height_r), (int)(160*width_r), (int)(50*height_r));
        btnNewButton_6.setFont(myfont);
        btnNewButton_6.setFocusPainted(false);
        btnNewButton_6.setBackground(new Color(250, 240, 230));
        btnNewButton_6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                functionChoose.jf.setContentPane(functionChoose.fc_panel);
                functionChoose.jf.setTitle("functionChoose");
                setVisible(false);
            }

        });
        add(btnNewButton_6);

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
