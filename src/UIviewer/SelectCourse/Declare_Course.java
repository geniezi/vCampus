package UIviewer.SelectCourse;

import ClientToServer.myInfo;
import DAO.Curriculum.Opencourse;
import UIhandler.Currirulum.Client_curriculum;
import net.coobird.thumbnailator.Thumbnails;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * 申报课程
 *
 * @author 28468
 * @date 2022/09/03
 */
public class Declare_Course extends JPanel{
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
     * 申报课程
     */
    public Declare_Course()
    {
        setLayout(null);
        JPanel p11=new JPanel();
        p11.setBounds(0,0,(int)(1273*width_r),(int)(784*height_r));

        JLabel lblBackground = new JLabel(); // 创建一个标签组件对象
        ImageIcon icon = new ImageIcon("src/image/logo-mini.png"); // 创建背景图片对象
        lblBackground.setIcon(icon); // 设置标签组件要显示的图标
        lblBackground.setBounds((int)(25*width_r), (int)(40*height_r), (int)(210*width_r), (int)(80*height_r)); // 设置组件的显示位置及大小
        add(lblBackground);


        //文字
        JLabel l1 = new JLabel("  你好！");
        l1.setBounds((int)(30*width_r), (int)(130*height_r), (int)(100*width_r), (int)(75*height_r));
        Font font = new Font("楷体", Font.BOLD, 22);
        l1.setFont(font);
        //l1.setForeground(new Color(111,222,0));
        add(l1);


        //信息面板
        JLabel l4 = new JLabel("      基本信息");
        l4.setBounds((int)(23*width_r), (int)(205*height_r), (int)(250*width_r), (int)(60*height_r));
        Font font2 = new Font("楷体", Font.BOLD, 25);
        l4.setFont(font2);
        l4.setForeground(new Color(94, 38, 18));
        add(l4);

        String name = "1";
        JLabel l2 = new JLabel(" 姓名：" + myInfo.getName());
        l2.setBounds((int)(30*width_r), (int)(300*height_r), (int)(250*width_r), (int)(60*height_r));
        Font font1 = new Font("微软雅黑", Font.PLAIN, 18);
        l2.setFont(font1);
        l2.setForeground(new Color(0, 0, 0));
        add(l2);
        String card = "";
        JLabel l3 = new JLabel(" 卡号：" + myInfo.getId());
        l3.setBounds((int)(30*width_r), (int)(410*height_r), (int)(250*width_r), (int)(60*height_r));
        l3.setFont(font1);
        l3.setForeground(new Color(0, 0, 0));
        add(l3);


        //左侧面板
        JPanel p1 = new JPanel();
        p1.setBounds(0, 0, (int)(310*width_r), (int)(784*height_r));
        p1.setBackground(new Color(135, 206, 250, 180));
        add(p1);


        //文本编辑框（输入课程编号或课程名字）
        JLabel lblNewLabel = new JLabel("申报课程");
        lblNewLabel.setFont(new Font("宋体", Font.BOLD, 45));
        lblNewLabel.setBounds((int)(360*width_r), (int)(45*height_r), (int)(275*width_r), (int)(45*height_r));
        add(lblNewLabel);


        JLabel lblNewLabel2 = new JLabel("课程名:");
        lblNewLabel2.setFont(new Font("宋体", Font.BOLD, 30));
        lblNewLabel2.setBounds((int)(400*width_r), (int)(160*height_r), (int)(275*width_r), (int)(35*height_r));
        add(lblNewLabel2);

        JTextField textField1 = new JTextField();
        textField1.setFont(new Font("宋体", Font.BOLD, 22));
        textField1.setBounds((int)(600*width_r), (int)(160*height_r), (int)(275*width_r), (int)(35*height_r));
        add(textField1);
        textField1.setColumns(10);

        JLabel lblNewLabel3 = new JLabel("课程学分:");
        lblNewLabel3.setFont(new Font("宋体", Font.BOLD, 30));
        lblNewLabel3.setBounds((int)(400*width_r), (int)(275*height_r), (int)(275*width_r), (int)(35*height_r));
        add(lblNewLabel3);

        JTextField textField2 = new JTextField();
        textField2.setFont(new Font("宋体", Font.BOLD, 22));
        textField2.setBounds((int)(600*width_r), (int)(275*height_r), (int)(275*width_r), (int)(35*height_r));
        add(textField2);
        textField2.setColumns(10);

        JLabel lblNewLabel4 = new JLabel("课程学时:");
        lblNewLabel4.setFont(new Font("宋体", Font.BOLD, 30));
        lblNewLabel4.setBounds((int)(400*width_r), (int)(390*height_r), (int)(275*width_r), (int)(35*height_r));
        add(lblNewLabel4);

        JTextField textField3 = new JTextField();
        textField3.setFont(new Font("宋体", Font.BOLD, 22));
        textField3.setBounds((int)(600*width_r), (int)(390*height_r), (int)(275*width_r), (int)(35*height_r));
        add(textField3);
        textField3.setColumns(10);


        JButton btnNewButton_7 = new JButton("申报");
        btnNewButton_7.setBounds((int)(660*width_r), (int)(525*height_r), (int)(150*width_r), (int)(30*height_r));
        Font myfont3 = new Font("微软雅黑", Font.PLAIN, 16);
        btnNewButton_7.setFont(myfont3);
        btnNewButton_7.setBackground(new Color(248, 248, 255));
        //btnNewButton_1.setForeground(new Color(248, 248, 255));
        btnNewButton_7.setContentAreaFilled(true);//设置按钮透明
        //btnNewButton_6.setBorder(null);//取消边框
        add(btnNewButton_7);
        btnNewButton_7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Opencourse opc=new Opencourse();
                    opc.setName(textField1.getText());
                    opc.setPoint(Double.valueOf(textField2.getText()));
                    opc.setHour(Integer.valueOf(textField3.getText()));
                    opc.setTeacher_id(myInfo.getId());
                    opc.setTeacher(myInfo.getName());
                    Client_curriculum.apply(opc);

                }catch(Exception ex){
                    ex.printStackTrace();
                }
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
            }
        });
        //五个按钮
        //右下面板

        //右下面板
        JPanel p3 = new JPanel();
        p3.setBounds((int)(310*width_r), 0, (int)(950*width_r), (int)(685*height_r));
        p3.setBackground(new Color(211, 211, 211, 100));
        add(p3);

        //右侧面板
        JPanel p2 = new JPanel();
        p2.setBounds((int)(310*width_r), 0, (int)(980*width_r), (int)(1000*height_r));
        p2.setBackground(new Color(245, 245, 245, 180));
        add(p2);

        JLabel l15 = new JLabel();
        ImageIcon icon11 = new ImageIcon("src/image/background2.jpg");
        int icon11_width= 1273;
        int icon11_height=790;
        try {
            Thumbnails.of(new File("src/image/background2.jpg"))
                    .size((int)(icon11_width*width_r), (int)(icon11_height*height_r))
                    .keepAspectRatio(false)
                    .toFile(new File("src/image/background2_min.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        l15.setIcon(new ImageIcon("src/image/background2_min.jpg"));
        l15.setBounds(0, 0, (int)(1273*width_r), (int)(790*height_r));
        p11.add(l15);

        setVisible(true);

        add(p11);
    }
}
