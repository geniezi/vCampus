package UIviewer.login;
import ClientToServer.ClientToServer;
import DAO.Shop.Product;
import UIhandler.Library.Client_library;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import UIhandler.Shop.Client_shop;
import UIhandler.StatusManagement.Client_status;
import UIviewer.QQ.main_panel;
import UIviewer.SelectCourse.Selcourse;
import UIviewer.SelectCourse.Selcourse_director;
import UIviewer.SelectCourse.Selcourse_teacher;


import UIhandler.QICQ.Client_qicq;
import UIviewer.Library.AllBooks;
import UIviewer.Library.readLib;
import UIviewer.Library.adminLib;
import UIviewer.Shopping.shopAdmin;
import UIviewer.Shopping.shopCustomer;
import UIviewer.status_manage.manage_status;
import UIviewer.status_manage.student_status;
import net.coobird.thumbnailator.Thumbnails;
import ClientToServer.myInfo;

/**
 * 功能选择
 *
 * @author Chen_GuanZhi
 * @date 2022/09/03
 */
public class functionChoose {

    public static JButton back_from_student_status;
    public static JFrame jf;
    public static JPanel fc_panel;

    /**
     * 功能选择界面
     */
    public static void functionChooseUI() {
        Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
        int width=(int ) screensize.getWidth(); //得到宽度
        int height=(int ) screensize.getHeight();//获得高度
        System.out.println(width);
        System.out.println(height);
        double width_r=(double)(width)/1273;
        double height_r=(double)(height)/784;
        jf = new JFrame("functionChoose");
        jf.setSize(width,height);
        jf.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //jf.setSize(1273,784);
        fc_panel = new JPanel();
        fc_panel.setLayout(null);
        fc_panel.setBounds(0,0, (int) (width*1.2), (int) (height*1.2));
        fc_panel.setBackground(new Color(245,245,245, 180));
        jf.setContentPane(fc_panel);

        //向好友发送上线消息
        try {
            Client_qicq.setId(myInfo.getId());
            Client_qicq.I_am_online();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //小头像
        JLabel touxiang = new JLabel();
        ImageIcon icon = new ImageIcon("src/image/头像.png");
        int icon1_width= 75;
        int icon1_height=75;
        try {
            Thumbnails.of(new File("src/image/头像.png"))
                    .size((int)(icon1_width*width_r), (int)(icon1_height*height_r))
                    .toFile(new File("src/image/头像_min.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        touxiang.setIcon(new ImageIcon("src/image/头像_min.png"));
        touxiang.setBounds((int) (20*width_r), (int) (20*height_r), (int) (75*width_r), (int) (75*height_r));
        fc_panel.add(touxiang);


        //文字
        JLabel l1 = new JLabel("  你好！");
        l1.setBounds((int) (100*width_r), (int) (20*height_r), (int) (200*width_r), (int) (75*height_r));
        Font font = new Font("楷体", Font.BOLD, (int) (22*width_r));
        l1.setFont(font);
        fc_panel.add(l1);

        //信息面板
        JLabel l4 = new JLabel("      基本信息");
        l4.setBounds((int) (23*width_r), (int) (155*height_r), (int) (250*width_r), (int) (60*height_r));
        Font font2 = new Font("楷体", Font.BOLD, (int) (25*width_r));
        l4.setFont(font2);
        l4.setForeground(new Color(30,144,255));
        fc_panel.add(l4);
        String name= myInfo.getName();
        JLabel l2 = new JLabel(" 姓名："+name);
        l2.setBounds((int) (30*width_r), (int) (210*height_r), (int) (250*width_r), (int) (60*height_r));
        Font font1 = new Font("微软雅黑", Font.PLAIN, (int) (18*width_r));
        l2.setFont(font1);
        l2.setForeground(new Color(0,0,0));
        fc_panel.add(l2);
        String card=myInfo.getId();
        JLabel l3 = new JLabel(" 卡号："+card);
        l3.setBounds((int) (30*width_r), (int) (250*height_r), (int) (250*width_r), (int) (60*height_r));
        l3.setFont(font1);
        l3.setForeground(new Color(0,0,0));
        fc_panel.add(l3);
        int iden=myInfo.getType();
        String identify=null;
        if(iden==1)
        {identify="学生";}
        if(iden==2)
        {identify="教师";}
        if(iden==3)
        {identify="管理员";}
        JLabel l21 = new JLabel(" 身份："+identify);
        l21.setBounds((int) (30*width_r), (int) (290*height_r), (int) (250*width_r), (int) (60*height_r));
        l21.setFont(font1);
        l21.setForeground(new Color(0,0,0));
        fc_panel.add(l21);

//文字
        JLabel l41 = new JLabel("大学之道");
        l41.setBounds((int) (60*width_r), (int) (480*height_r), (int) (200*width_r), (int) (75*height_r));
        Font font3 = new Font("楷体", Font.BOLD, (int) (22*width_r));
        l41.setFont(font3);
        fc_panel.add(l41);
        JLabel l42 = new JLabel("在明明德");
        l42.setBounds((int) (60*width_r), (int) (520*height_r), (int) (200*width_r), (int) (75*height_r));
        l42.setFont(font3);
        fc_panel.add(l42);
        JLabel l43 = new JLabel("在亲民");
        l43.setBounds((int) (60*width_r), (int) (560*height_r), (int) (200*width_r), (int) (75*height_r));
        l43.setFont(font3);
        fc_panel.add(l43);
        JLabel l44 = new JLabel("在");
        l44.setBounds((int) (60*width_r), (int) (600*height_r), (int) (40*width_r), (int) (75*height_r));
        l44.setFont(font3);
        fc_panel.add(l44);
        JLabel l45 = new JLabel("『止于至善』");
        l45.setBounds((int) (100*width_r), (int) (630*height_r), (int) (200*width_r), (int) (75*height_r));
        Font font4 = new Font("楷体", Font.BOLD, (int) (30*width_r));
        l45.setFont(font4);
        fc_panel.add(l45);
        JLabel l46 = new JLabel("————校训");
        l46.setBounds((int) (140*width_r), (int) (700*height_r), (int) (200*width_r), (int) (40*height_r));
        l46.setFont(font3);
        fc_panel.add(l46);

        //label背景
        JLabel l11 = new JLabel();
        ImageIcon icon4 = new ImageIcon("src/image/label2.png");
        int icon2_width= 285;
        int icon2_height=330;
        try {
            Thumbnails.of(new File("src/image/label2.png"))
                    .size((int)(icon2_width*width_r), (int)(icon2_height*height_r))
                    .toFile(new File("src/image/label2_min.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        l11.setIcon(new ImageIcon("src/image/label2_min.png"));
        l11.setBounds((int) (15*width_r), (int) (110*height_r), (int) (285*width_r), (int) (330*height_r));
        fc_panel.add(l11);

        //东南大学标志图片
        JLabel logo = new JLabel();
        ImageIcon icon1 = new ImageIcon("src/image/logo-mini.png");
        int icon3_width= 210;
        int icon3_height=65;
        try {
            Thumbnails.of(new File("src/image/logo-mini.png"))
                    .size((int)(icon3_width*width_r), (int)(icon3_height*height_r))
                    .toFile(new File("src/image/logo-mini_min.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logo.setIcon(new ImageIcon("src/image/logo-mini_min.png"));
        logo.setBounds((int) (315*width_r), (int) (5*height_r), (int) (210*width_r), (int) (65*height_r));
        fc_panel.add(logo);


        //右上角图标
        JLabel pic1 = new JLabel();
        ImageIcon icon2 = new ImageIcon("src/image/student.png");
        int icon4_width= 25;
        int icon4_height=25;
        try {
            Thumbnails.of(new File("src/image/student.png"))
                    .size((int)(icon4_width*width_r), (int)(icon4_height*height_r))
                    .toFile(new File("src/image/student_min.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        pic1.setIcon(new ImageIcon("src/image/student_min.png"));
        pic1.setBounds((int) (1100*width_r), (int) (15*height_r), (int) (25*width_r), (int) (25*height_r));
        fc_panel.add(pic1);


        JButton btnNewButton_6 = new JButton("安全退出");
        btnNewButton_6.setBounds((int) (1140*width_r), (int) (15*height_r), (int) (100*width_r), (int) (30*height_r));
        Font myfont2 = new Font("微软雅黑", Font.PLAIN, (int) (12*width_r));
        btnNewButton_6.setFont(myfont2);
        btnNewButton_6.setBackground(new Color(248,248,255));
        btnNewButton_6.setContentAreaFilled(false);//设置按钮透明
        btnNewButton_6.setFocusPainted(false);
        fc_panel.add(btnNewButton_6);

        btnNewButton_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                try {
                    try {
                        Client_qicq.I_am_offline();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    ClientToServer.logout();
                    jf.dispose();
                    LoginFrame.jf.setVisible(true);
                    LoginFrame.passwordField.setText("");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        //横向图片
        JLabel l12 = new JLabel();
        ImageIcon icon5 = new ImageIcon("src/image/banner3.png");
        int icon5_width= 1030;
        int icon5_height=125;
        try {
            Thumbnails.of(new File("src/image/banner3.png"))
                    .size((int)(icon5_width*width_r+20), (int)(icon5_height*height_r))
                    .keepAspectRatio(false)
                    .toFile(new File("src/image/banner3_min.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        l12.setIcon(new ImageIcon("src/image/banner3_min.png"));
        l12.setBounds((int) (310*width_r), (int) (70*height_r), (int) (1020*width_r+20), (int) (125*height_r));
        fc_panel.add(l12);

        //文字
        JLabel l13 = new JLabel("     功能选择 ");
        l13.setBounds((int) (320*width_r), (int) (200*height_r), (int) (200*width_r), (int) (75*height_r));
        Font font13 = new Font("微软雅黑", Font.BOLD, (int) (25*width_r));
        l13.setFont(font13);
        fc_panel.add(l13);

        //左侧面板
        JPanel p1 = new JPanel();
        p1.setBounds(0, 0, (int) (310*width_r), (int) (784*height_r));
        p1.setBackground(new Color(135,206,250, 180));
        fc_panel.add(p1);

        //五个按钮
        //右下面板，学籍管理模块
        JLabel l31 = new JLabel();
        ImageIcon icon31 = new ImageIcon("src/image/icon_72 (1).png");
        int icon6_width= 300;
        int icon6_height=72;
        try {
            Thumbnails.of(new File("src/image/icon_72 (1).png"))
                    .size((int)(icon6_width*width_r), (int)(icon6_height*height_r))
                    .toFile(new File("src/image/icon_72 (1)_min.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        l31.setIcon(new ImageIcon("src/image/icon_72 (1)_min.png"));
        l31.setBounds((int) (449*width_r), (int) (290*height_r), (int) (300*width_r), (int) (72*height_r));
        fc_panel.add(l31);

        JButton btnNewButton_1 = new JButton("学籍管理");
        btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                try {
                    //jf.setBounds(0,0,width,height);
                    jf.remove(fc_panel);
                    Client_status.ini();
                    if(myInfo.getType()==1) {
                        Client_status.stu_enter();
                    }
                    else if(myInfo.getType()==3){
                        jf.setContentPane(new manage_status(width,height).manage_panel);
                        jf.setTitle("admin_status_management");
                    } else {
                        JOptionPane.showMessageDialog(null,"抱歉，您暂无学籍管理权限！");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnNewButton_1.setBounds((int) (335*width_r), (int) (362*height_r), (int) (300*width_r), (int) (128*height_r));
        Font myfont = new Font("微软雅黑", Font.BOLD, (int) (26*width_r));
        btnNewButton_1.setFont(myfont);
        btnNewButton_1.setBackground(new Color(220,220,220));
        btnNewButton_1.setFocusPainted(false);
        fc_panel.add(btnNewButton_1);
        JPanel p21 = new JPanel();
        p21.setBounds((int) (335*width_r), (int) (290*height_r), (int) (300*width_r), (int) (200*height_r));
        p21.setBackground(new Color(248,248,255));
        fc_panel.add(p21);

        JLabel l32 = new JLabel();
        ImageIcon icon32 = new ImageIcon("src/image/icon_72 (3).png");
        int icon7_width= 300;
        int icon7_height=72;
        try {
            Thumbnails.of(new File("src/image/icon_72 (3).png"))
                    .size((int)(icon7_width*width_r), (int)(icon7_height*height_r))
                    .toFile(new File("src/image/icon_72 (3)_min.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        l32.setIcon(new ImageIcon("src/image/icon_72 (3)_min.png"));
        l32.setBounds((int) (754*width_r), (int) (290*height_r), (int) (300*width_r), (int) (72*height_r));
        fc_panel.add(l32);
        JButton btnNewButton_2 = new JButton("选课系统");
        btnNewButton_2.setFocusPainted(false);
        btnNewButton_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                try {
                    jf.remove(fc_panel);
                    if(myInfo.getType()==1)
                    {
                        jf.setContentPane(new Selcourse());
                        jf.setTitle("Selcourse");
                    }
                    else if(myInfo.getType()==2)
                    {
                        jf.setContentPane(new Selcourse_teacher());
                        jf.setTitle("Selcourse_teacher");
                    }
                    else {
                        jf.setContentPane(new Selcourse_director());
                        jf.setTitle("Selcourse_director");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnNewButton_2.setBounds((int) (640*width_r), (int) (362*height_r), (int) (300*width_r), (int) (128*height_r));
        btnNewButton_2.setFont(myfont);
        btnNewButton_2.setBackground(new Color(220,220,220));
        fc_panel.add(btnNewButton_2);

        JPanel p22 = new JPanel();
        p22.setBounds((int) (640*width_r), (int) (290*height_r), (int) (300*width_r), (int) (200*height_r));
        p22.setBackground(new Color(248,248,255));
        fc_panel.add(p22);

        JLabel l33 = new JLabel();
        ImageIcon icon33 = new ImageIcon("src/image/icon_72 (5).png");
        int icon8_width= 300;
        int icon8_height=72;
        try {
            Thumbnails.of(new File("src/image/icon_72 (5).png"))
                    .size((int)(icon8_width*width_r), (int)(icon8_height*height_r))
                    .toFile(new File("src/image/icon_72 (5)_min.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        l33.setIcon(new ImageIcon("src/image/icon_72 (5)_min.png"));
        l33.setBounds((int) (1059*width_r), (int) (290*height_r), (int) (300*width_r), (int) (72*height_r));
        fc_panel.add(l33);

        JButton btnNewButton_3 = new JButton("商店系统");
        btnNewButton_3.setFocusPainted(false);
        btnNewButton_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                try {
                    jf.remove(fc_panel);
                    if(myInfo.getType()!=3)
                    {
                        Client_shop.setId(String.valueOf(myInfo.getType()));
                        Client_shop.setIdcard(myInfo.getId());
                        jf.setContentPane(new shopCustomer());
                        jf.setTitle("shopCustomer");
                    }
                    else
                    {
                        Client_shop.setId(String.valueOf(myInfo.getType()));
                        Client_shop.setIdcard(myInfo.getId());
                        jf.setContentPane(new shopAdmin());
                        jf.setTitle("shopAdmin");
                    }
                    jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    jf.setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnNewButton_3.setBounds((int) (945*width_r), (int) (362*height_r), (int) (300*width_r), (int) (128*height_r));
        btnNewButton_3.setFont(myfont);
        btnNewButton_3.setBackground(new Color(220,220,220));
        //btnNewButton_3.setBorder(null);//取消边框
        //btnNewButton_1.setForeground(new Color(248, 248, 255));
        fc_panel.add(btnNewButton_3);
        JPanel p23 = new JPanel();
        p23.setBounds((int) (945*width_r), (int) (290*height_r), (int) (300*width_r), (int) (200*height_r));
        p23.setBackground(new Color(248,248,255));
        fc_panel.add(p23);

        JLabel l34 = new JLabel();
        ImageIcon icon34 = new ImageIcon("src/image/icon_72 (4).png");
        int icon9_width= 300;
        int icon9_height=72;
        try {
            Thumbnails.of(new File("src/image/icon_72 (4).png"))
                    .size((int)(icon9_width*width_r), (int)(icon9_height*height_r))
                    .toFile(new File("src/image/icon_72 (4)_min.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        l34.setIcon(new ImageIcon("src/image/icon_72 (4)_min.png"));
        l34.setBounds((int) (449*width_r), (int) (500*height_r), (int) (300*width_r), (int) (72*height_r));
        fc_panel.add(l34);
        JButton btnNewButton_4 = new JButton("图书馆系统");
        btnNewButton_4.setFocusPainted(false);
        btnNewButton_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                try {
                    jf.remove(fc_panel);
                    Client_library.setId(myInfo.getId());
                    if(myInfo.getType()!=3)
                    {
                        jf.setContentPane(new readLib());
                        jf.setTitle("readLib");
                        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        jf.setVisible(true);
                    }
                    else
                    {
                        //Client_library.RequireshowAllBooks();
                        Client_library.admin_enter();
                        //jf.setContentPane(new adminLib());
                        //jf.setTitle("adminLib");
                        //jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        //jf.setVisible(true);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnNewButton_4.setBounds((int) (335*width_r), (int) (572*height_r), (int) (300*width_r), (int) (128*height_r));
        btnNewButton_4.setFont(myfont);
        btnNewButton_4.setBackground(new Color(220,220,220));
        //btnNewButton_4.setBorder(null);//取消边框
        //btnNewButton_1.setForeground(new Color(248, 248, 255));
        fc_panel.add(btnNewButton_4);
        JPanel p24 = new JPanel();
        p24.setBounds((int) (335*width_r), (int) (500*height_r), (int) (300*width_r), (int) (200*height_r));
        p24.setBackground(new Color(248,248,255));
        fc_panel.add(p24);

        JLabel l35 = new JLabel();
        ImageIcon icon35 = new ImageIcon("src/image/icon_72 (6).png");
        int icon10_width= 300;
        int icon10_height=72;
        try {
            Thumbnails.of(new File("src/image/icon_72 (6).png"))
                    .size((int)(icon9_width*width_r), (int)(icon9_height*height_r))
                    .toFile(new File("src/image/icon_72 (6)_min.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        l35.setIcon(new ImageIcon("src/image/icon_72 (6)_min.png"));
        l35.setBounds((int) (754*width_r), (int) (500*height_r), (int) (300*width_r), (int) (72*height_r));
        fc_panel.add(l35);
        JButton btnNewButton_5 = new JButton("站内通信");
        btnNewButton_5.setFocusPainted(false);
        btnNewButton_5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                try {
                    Client_qicq.setId(myInfo.getId());
                    if(myInfo.getType()!=3)
                    {
                        jf.setContentPane(new main_panel(width,height,myInfo.getType()).mjp);
                        jf.setTitle("userqq");
                    }
                    else
                    {
                        jf.setContentPane(new main_panel(width,height,myInfo.getType()).mjp);
                        jf.setTitle("adminqq");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnNewButton_5.setBounds((int) (640*width_r), (int) (572*height_r), (int) (300*width_r), (int) (128*height_r));
        btnNewButton_5.setFont(myfont);
        btnNewButton_5.setBackground(new Color(220,220,220));
        //btnNewButton_5.setBorder(null);//取消边框
        //btnNewButton_1.setForeground(new Color(248, 248, 255));
        fc_panel.add(btnNewButton_5);
        JPanel p25 = new JPanel();
        p25.setBounds((int) (640*width_r), (int) (500*height_r), (int) (300*width_r), (int) (200*height_r));
        p25.setBackground(new Color(248,248,255));
        fc_panel.add(p25);

        JButton btnNewButton_7 = new JButton("敬请期待");
        btnNewButton_7.setFocusPainted(false);
        btnNewButton_7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                JOptionPane.showMessageDialog(null,"正在开发中，敬请期待！");
            }
        });
        JLabel l37 = new JLabel();
        ImageIcon icon37 = new ImageIcon("src/image/icon_72 (2).png");
        try {
            Thumbnails.of(new File("src/image/icon_72 (2).png"))
                    .size((int)(icon9_width*width_r), (int)(icon9_height*height_r))
                    .toFile(new File("src/image/icon_72 (2)_min.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        l37.setIcon(new ImageIcon("src/image/icon_72 (2)_min.png"));
        l37.setBounds((int) (1059*width_r), (int) (500*height_r), (int) (300*width_r), (int) (72*height_r));
        fc_panel.add(l37);
        btnNewButton_7.setBounds((int) (945*width_r), (int) (572*height_r), (int) (300*width_r), (int) (128*height_r));
        btnNewButton_7.setFont(myfont);
        btnNewButton_7.setBackground(new Color(220,220,220));
        fc_panel.add(btnNewButton_7);
        JPanel p27 = new JPanel();
        p27.setBounds((int) (945*width_r), (int) (500*height_r), (int) (300*width_r), (int) (200*height_r));
        p27.setBackground(new Color(248,248,255));
        fc_panel.add(p27);

        //右下面板
        JPanel p3 = new JPanel();
        p3.setBounds((int) (326*width_r), (int) (282*height_r), (int) (927*width_r), (int) (430*height_r));
        p3.setBackground(new Color(230,230,230));
        fc_panel.add(p3);

        //右侧面板
        JPanel p2 = new JPanel();
        p2.setBounds((int) (300*width_r), 0, (int) (1000*width_r), (int) (800*height_r));
        p2.setBackground(new Color(245,245,245));
        fc_panel.add(p2);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setVisible(true);
    }
}
