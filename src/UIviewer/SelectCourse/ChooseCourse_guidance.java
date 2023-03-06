package UIviewer.SelectCourse;
import ClientToServer.myInfo;
import net.coobird.thumbnailator.Thumbnails;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * 选择课程指导
 *
 * @author Chen_GuanZhi
 * @date 2022/09/03
 */
public class ChooseCourse_guidance extends JPanel{
    Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
    int width=(int ) screensize.getWidth(); //得到宽度
    int height=(int ) screensize.getHeight();//获得高度
    double width_r=(double)(width)/1273;
    double height_r=(double)(height)/790;

    /**
     * 选择课程指导
     */
    public ChooseCourse_guidance()
    {
        setLayout(null);
        JPanel p11=new JPanel();
        p11.setBounds(0,0,(int)(1273*width_r),(int)(790*height_r));


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
        JLabel lblNewLabel = new JLabel("选课帮助:");
        lblNewLabel.setFont(new Font("宋体", Font.BOLD, 35));
        lblNewLabel.setBounds((int)(400*width_r), (int)(45*height_r), (int)(275*width_r), (int)(35*height_r));
        add(lblNewLabel);

        //文本编辑框（输入选课帮助）
        JLabel lblNewLabel2 = new JLabel("1、登陆选课系统，学生需根据培养方案进行选课");
        lblNewLabel2.setFont(new Font("宋体", Font.BOLD, 25));
        lblNewLabel2.setBounds((int)(400*width_r), (int)(135*height_r), (int)(605*width_r), (int)(35*height_r));
        add(lblNewLabel2);

        //文本编辑框（输入选课帮助）
        JLabel lblNewLabel3 = new JLabel("2、每学期选择课程的学分和应不低于12学分");
        lblNewLabel3.setFont(new Font("宋体", Font.BOLD, 25));
        lblNewLabel3.setBounds((int)(400*width_r), (int)(205*height_r), (int)(605*width_r), (int)(35*height_r));
        add(lblNewLabel3);

        //文本编辑框（输入选课帮助）
        JLabel lblNewLabel4 = new JLabel("3、教师有权利申报课程，经审核通过后可进行授课");
        lblNewLabel4.setFont(new Font("宋体", Font.BOLD, 25));
        lblNewLabel4.setBounds((int)(400*width_r), (int)(275*height_r), (int)(605*width_r), (int)(35*height_r));
        add(lblNewLabel4);

        //文本编辑框（输入选课帮助）
        JLabel lblNewLabel5 = new JLabel("4、管理员有权增添、删除学生教师信息");
        lblNewLabel5.setFont(new Font("宋体", Font.BOLD, 25));
        lblNewLabel5.setBounds((int)(400*width_r), (int)(345*height_r), (int)(605*width_r), (int)(35*height_r));
        add(lblNewLabel5);

        //文本编辑框（输入选课帮助）
        JLabel lblNewLabel6 = new JLabel("5、学生需要在选课系统开放的时间内完成选课");
        lblNewLabel6.setFont(new Font("宋体", Font.BOLD, 25));
        lblNewLabel6.setBounds((int)(400*width_r), (int)(415*height_r), (int)(605*width_r), (int)(35*height_r));
        add(lblNewLabel6);

        //文本编辑框（输入选课帮助）
        JLabel lblNewLabel7 = new JLabel("6、若对选课系统有疑问，可致电咨询052-84196536");
        lblNewLabel7.setFont(new Font("宋体", Font.BOLD, 25));
        lblNewLabel7.setBounds((int)(400*width_r), (int)(485*height_r), (int)(605*width_r), (int)(35*height_r));
        add(lblNewLabel7);

        //右下面板
        JPanel p3 = new JPanel();
        p3.setBounds((int)(310*width_r), 0, (int)(950*width_r), (int)(685*height_r));
        p3.setBackground(new Color(211, 211, 211, 100));
        add(p3);


        //右侧面板
        JPanel p2 = new JPanel();
        p2.setBounds((int)(310*width_r), 0, (int)(980*width_r), (int)(1000*height_r));  //950
        p2.setBackground(new Color(245, 245, 245, 180));
        add(p2);

        //横向图片
        JLabel l16 = new JLabel();
        ImageIcon icon7 = new ImageIcon("src/image/background2.jpg");
        int icon7_width=1273;
        int icon7_height=790;
        try {
            Thumbnails.of(new File("src/image/background2.jpg"))
                    .size((int)(width), (int)(height))
                    .keepAspectRatio(false)
                    .toFile(new File("src/image/background2_min.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        l16.setIcon(new ImageIcon("src/image/background2_min.jpg"));
        l16.setBounds(0, 0, (int)(1273*width_r), (int)(790*height_r));
        p11.add(l16);
        add(p11);
    }

}
