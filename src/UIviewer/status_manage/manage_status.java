package UIviewer.status_manage;

import ClientToServer.ClientToServer;
import UIhandler.StatusManagement.Client_status;
import UIviewer.login.functionChoose;
import net.coobird.thumbnailator.Thumbnails;

import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * 管理状态
 *
 * @author Zhangyang_Ge
 * @date 2022/09/03
 */
public class manage_status{
    static JButton jb_back_to_search;
    static public JPanel search_panel;
    static public JPanel status_jpanel;
    static public JPanel manage_panel;
    public static final JTextField search_text=new RoundJTextField(15);

    /**
     * 管理状态
     *
     * @param width  宽度
     * @param height 高度
     * @throws Exception 异常
     */
    public manage_status(int width, int height) throws Exception {
        double width_r=(double)(width)/1920;
        double height_r=(double)(height)/1080;
        manage_panel=new JPanel();
        //设置屏幕大小、背景颜色
        manage_panel.setBounds(0,0,width,height);
        manage_panel.setBackground(new Color(255,255,255));
        //设置绝对布局
        manage_panel.setLayout(null);


        //SEU logo
        JLabel logo = new JLabel();
        int icon1_width=160;
        int icon1_height=50;
        try {
            Thumbnails.of(new File("src/image/student_manage_logo.png"))
                    .size((int)(icon1_width*width_r), (int)(icon1_height*width_r))
                    .toFile(new File("src/image/student_manage_logo_min.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logo.setIcon(new ImageIcon("src/image/student_manage_logo_min.png"));
        manage_panel.add(logo);
        logo.setBounds((int)(30*width_r),(int)(5*height_r),(int)(icon1_width*width_r),(int)(icon1_height*height_r));
        //标题
        JLabel title=new JLabel("学生基本信息管理");
        title.setBounds((int)((40+icon1_width)*width_r), (int)(3*height_r), (int)(300*width_r), (int)(icon1_height*height_r));
        Font title_font = new Font("微软雅黑", Font.BOLD, (int)(31*width_r));
        title.setFont(title_font);
        title.setForeground(new Color(255,255,255));
        manage_panel.add(title);




        //搜索面板
        search_panel=new JPanel();
        search_panel.setBackground(new Color(255,255,255));
        search_panel.setBorder(BorderFactory.createEtchedBorder());
        search_panel.setLayout(null);//设置绝对布局
        search_panel.setBounds((int)((60+icon1_width)*width_r),(int)((47+icon1_height)*height_r), (int)(width-2*(60+icon1_width)*width_r),(int)((1080-70-icon1_height)*height_r));
        manage_panel.add(search_panel);
        //图标
        JLabel logo2=new JLabel();
        int icon2_width=320;
        int icon2_height=100;
        try {
            Thumbnails.of(new File("src/image/student_manage_logo.png"))
                    .size((int)(icon2_width*width_r), (int)(icon2_height*width_r))
                    .toFile(new File("src/image/student_manage_logo_mini.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logo2.setIcon(new ImageIcon("src/image/student_manage_logo_mini.png"));
        search_panel.add(logo2);
        logo2.setBounds((int)((width-2*(60+icon1_width)*width_r)/2-icon2_width/2*width_r),(int)(200*height_r),(int)(icon2_width*width_r),(int)(icon2_height*height_r));
        //搜索框

        search_text.setBounds((int)(300*width_r),(int)((250+icon2_height)*height_r),(int)((width-2*(60+icon1_width)*width_r-2*300*width_r)),(int)(100*height_r));
        search_text.setFont(new Font("微软雅黑",Font.PLAIN,(int)(35*width_r)));
        search_text.setHorizontalAlignment(JTextField.CENTER);
        search_text.addFocusListener(new JTextFieldHintListener(search_text, "请输入要查询学生的一卡通号"));
        search_panel.add(search_text);
        //搜索按钮
        JButton search_button=new RoundJButton();
        search_button.setText("查询");
        search_button.setBounds((int)(550*width_r),(int)((400+icon2_height)*height_r),(int)((width-2*(60+icon1_width)*width_r-2*550*width_r)),(int)(100*height_r));
        search_button.setBackground(new Color(96,190,41));
        search_button.setForeground(new Color(255,255,255));
        search_button.setFont(new Font("微软雅黑",Font.PLAIN,(int)(39*width_r)));
        search_panel.add(search_button);
        search_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //学生信息视图
                try {
                    Client_status.requireInfo(search_text.getText());
                  //  search_text.setHorizontalAlignment(JTextField.CENTER);
                    //search_text.addFocusListener(new JTextFieldHintListener(search_text, "请输入要查询学生的一卡通号"));
                    /*status_jpanel = student_status.status_panel(width_r,height_r,width-2*(60+icon1_width)*width_r,(1080-70-icon1_height)*height_r,search_text.getText());
                    status_jpanel.setBounds((int)((60+icon1_width)*width_r),(int)((47+icon1_height)*height_r), (int)(width-2*(60+icon1_width)*width_r),(int)((1080-70-icon1_height)*height_r));
                    add(status_jpanel,0);
                    search_panel.setVisible(false);
                    status_jpanel.setVisible(true);*/
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        //返回功能选择模块
        functionChoose.back_from_student_status =new RoundJButton();
        functionChoose.back_from_student_status.setText("返回功能选择");
        functionChoose.back_from_student_status.setBounds((int)(550*width_r),(int)((550+icon2_height)*height_r),(int)((width-2*(60+icon1_width)*width_r-2*550*width_r)),(int)(100*height_r));
        functionChoose.back_from_student_status.setBackground(new Color(199,84,80));
        functionChoose.back_from_student_status.setForeground(new Color(255,255,255));
        functionChoose.back_from_student_status.setFont(new Font("微软雅黑",Font.PLAIN,(int)(39*width_r)));
        search_panel.add(functionChoose.back_from_student_status);
        functionChoose.back_from_student_status.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              //  Client_status.resetS();
                functionChoose.jf.remove(manage_status.manage_panel);
                functionChoose.jf.setContentPane(functionChoose.fc_panel);
            }
        });

        //信息面板上透明条
        JPanel white_front_slice=new JPanel();
        white_front_slice.setBackground(new Color(124,136,204));
        white_front_slice.setBounds((int)((90+icon1_width)*width_r),(int)((7+icon1_height)*height_r), (int)(width-2*(90+icon1_width)*width_r),(int)(40*height_r));
        manage_panel.add(white_front_slice);
        white_front_slice.setLayout(null);//设置绝对布局
        //文字
        JLabel front_title=new JLabel("学生基本信息管理");
        front_title.setBounds((int)(20*width_r), (int)(0*height_r), (int)(300*width_r), (int)(40*height_r));
        Font front_title_font = new Font("微软雅黑", Font.PLAIN, (int)(19*width_r));
        front_title.setFont(front_title_font);
        front_title.setForeground(new Color(240,241,249));
        white_front_slice.add(front_title);


        //上方蓝色背景
        JPanel blue_back=new JPanel();
        blue_back.setBackground(new Color(63,81,181));
        blue_back.setBounds(0,0,width,height*2/5);
        manage_panel.add(blue_back);
    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame("manage_status");
//        Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
//        int width=(int ) screensize.getWidth(); //得到宽度
//        int height=(int ) screensize.getHeight();//获得高度
//        frame.setBounds(0,0,width,height);
//        frame.setContentPane(new manage_status(ucs,width,height));
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
//    }
}
