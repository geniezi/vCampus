package UIviewer.Library;
import DAO.Library.Punishment;
import UIhandler.Library.Client_library;
import UIviewer.login.functionChoose;
import net.coobird.thumbnailator.Thumbnails;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * 给票
 *
 * @author Chen_GuanZhi
 * @date 2022/09/03
 */
public class giveTicket extends JPanel {

    Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
    int width=(int ) screensize.getWidth(); //得到宽度
    int height=(int ) screensize.getHeight();//获得高度
    double width_r=(double)(width)/1273;
    double height_r=(double)(height)/784;

    /**
     * 给票
     */
    public giveTicket(){
        setLayout(null);

        //录入信息
        JLabel l = new JLabel("    罚单信息");
        l.setBounds((int) (520*width_r), (int) (10*height_r), (int) (290*width_r), (int) (80*height_r));
        Font font = new Font("楷体", Font.BOLD, (int) (26*width_r));
        l.setFont(font);
        //l1.setForeground(new Color(111,222,0));
        add(l);

        JLabel l1 = new JLabel("罚单编号:");
        l1.setFont(new Font("宋体", Font.BOLD, (int) (15*width_r)));
        l1.setBounds((int) (500*width_r), (int) (160*height_r), (int) (250*width_r), (int) (25*height_r));
        add(l1);
        JTextField textField1=new JTextField();
        textField1.setFont(new Font("宋体", Font.BOLD, (int) (12*width_r)));
        textField1.setBounds((int) (575*width_r), (int) (160*height_r), (int) (150*width_r), (int) (25*height_r));
        add(textField1);
        textField1.setColumns((int) (10*height_r));

        JLabel l2 = new JLabel("罚款金额:");
        l2.setFont(new Font("宋体", Font.BOLD, (int) (15*width_r)));
        l2.setBounds((int) (500*width_r), (int) (200*height_r), (int) (250*width_r), (int) (25*height_r));
        add(l2);
        JTextField textField2=new JTextField();
        textField2.setFont(new Font("宋体", Font.BOLD, (int) (12*width_r)));
        textField2.setBounds((int) (575*width_r), (int) (200*height_r), (int) (150*width_r), (int) (25*height_r));
        add(textField2);
        textField2.setColumns((int) (10*height_r));

        JLabel l3 = new JLabel("罚款用户:");
        l3.setFont(new Font("宋体", Font.BOLD, (int) (15*width_r)));
        l3.setBounds((int) (500*width_r), (int) (240*height_r), (int) (250*width_r), (int) (25*height_r));
        add(l3);
        JTextField textField3=new JTextField();
        textField3.setFont(new Font("宋体", Font.BOLD, (int) (12*width_r)));
        textField3.setBounds((int) (575*width_r), (int) (240*height_r), (int) (150*width_r), (int) (25*height_r));
        add(textField3);
        textField3.setColumns((int) (10*height_r));

        JLabel l4 = new JLabel("书籍编号:");
        l4.setFont(new Font("宋体", Font.BOLD, (int) (15*width_r)));
        l4.setBounds((int) (500*width_r), (int) (280*height_r), (int) (250*width_r), (int) (25*height_r));
        add(l4);
        JTextField textField4=new JTextField();
        textField4.setFont(new Font("宋体", Font.BOLD, (int) (12*width_r)));
        textField4.setBounds((int) (575*width_r), (int) (280*height_r), (int) (150*width_r), (int) (25*height_r));
        add(textField4);
        textField4.setColumns((int) (10*height_r));

        JLabel l5 = new JLabel("罚款备注:");
        l5.setFont(new Font("宋体", Font.BOLD, (int) (15*width_r)));
        l5.setBounds((int) (500*width_r), (int) (320*height_r), (int) (250*width_r), (int) (25*height_r));
        add(l5);
        JTextField textField5=new JTextField();
        textField5.setFont(new Font("宋体", Font.BOLD, (int) (12*width_r)));
        textField5.setBounds((int) (575*width_r), (int) (320*height_r), (int) (150*width_r), (int) (25*height_r));
        add(textField5);
        textField5.setColumns((int) (10*width_r));

        JButton b1=new JButton("确定提交");
        b1.setFont(new Font("楷体", Font.BOLD, (int) (18*width_r)));
        b1.setBounds((int) (575*width_r), (int) (400*height_r), (int) (120*width_r), (int) (50*height_r));
        b1.setBackground(new Color(250,250,210));
        add(b1);
        b1.setFocusPainted(false);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                try {
                    Punishment p=new Punishment();
                    p.setPunishmentID(textField1.getText());
                    p.setPrice(Double.valueOf(textField2.getText()));
                    p.setCustomer_iD(textField3.getText());
                    p.setBook_id(textField4.getText());
                    p.setNotice(textField5.getText());
                    p.setStatus(0);
                    Client_library.RequireNewPunishment(p);
                    JOptionPane.showMessageDialog(null,"罚单提交成功！");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
                textField5.setText("");

            }
        });
        //中间面板
        JPanel p3 = new JPanel();
        p3.setBounds((int) (480*width_r), 0, (int) (290*width_r), (int) (620*height_r));
        p3.setBackground(new Color(255,228,181,150));
        add(p3);

        JPanel p12=new JPanel();
        p12.setBounds((int) (300*width_r),0, (int) (685*width_r), (int) (660*height_r));
        JLabel pic2 = new JLabel();
        ImageIcon icon2 = new ImageIcon("src/image/bg13.jpg");
        int icon2_width= 685;
        int icon2_height=660;
        try {
            Thumbnails.of(new File("src/image/bg13.jpg"))
                    .size((int)(icon2_width*width_r), (int)(icon2_height*height_r))
                    .keepAspectRatio(false)
                    .toFile(new File("src/image/bg13_min.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        pic2.setIcon(new ImageIcon("src/image/bg13_min.jpg"));
        pic2.setBounds((int) (300*width_r),0 , (int) (685*width_r), (int) (660*height_r));
        p12.add(pic2);
        add(p12);

        JPanel p11=new JPanel();
        p11.setBounds(0,0, (int) (1300*width_r), (int) (650*height_r));
        JLabel pic1 = new JLabel();
        ImageIcon icon1 = new ImageIcon("src/image/main3.jpg");
        int icon3_width= 1300;
        int icon3_height=650;
        try {
            Thumbnails.of(new File("src/image/main3.jpg"))
                    .size((int)(icon3_width*width_r), (int)(icon3_height*height_r))
                    .keepAspectRatio(false)
                    .toFile(new File("src/image/main3_min.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        pic1.setIcon(new ImageIcon("src/image/main3_min.jpg"));
        pic1.setBounds(0,0 , (int) (1300*width_r), (int) (650*height_r));
        p11.add(pic1);
        add(p11);

    }
}