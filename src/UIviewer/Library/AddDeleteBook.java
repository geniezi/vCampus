package UIviewer.Library;
import DAO.Library.Book_admin;
import UIhandler.Library.Client_library;
import net.coobird.thumbnailator.Thumbnails;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * 添加删除本
 *
 * @author Chen_GuanZhi
 * @date 2022/09/03
 */
public class AddDeleteBook extends JPanel {
    public static String[] addinfo=new String[7];
    Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
    int width=(int ) screensize.getWidth(); //得到宽度
    int height=(int ) screensize.getHeight();//获得高度
    double width_r=(double)(width)/1273;
    double height_r=(double)(height)/784;
    public static String deleteID;

    /**
     * 添加删除本
     */
    public AddDeleteBook(){
        setLayout(null);

        //录入信息
        JLabel l = new JLabel("   录入书籍");
        l.setBounds((int) (200*width_r), (int) (10*height_r), (int) (290*width_r), (int) (80*height_r));
        Font font = new Font("楷体", Font.BOLD, (int) (26*width_r));
        l.setFont(font);
        //l1.setForeground(new Color(111,222,0));
        add(l);
        //删除书籍
        JLabel l0 = new JLabel("  删除书籍");
        l0.setBounds((int) (860*width_r), (int) (180*height_r), (int) (290*width_r), (int) (80*height_r));
        l0.setFont(font);
        //l1.setForeground(new Color(111,222,0));
        add(l0);


        //账号密码
        JLabel l1 = new JLabel("书籍编号:");
        l1.setFont(new Font("宋体", Font.BOLD, (int) (15*width_r)));
        l1.setBounds((int) (160*width_r), (int) (120*height_r), (int) (250*width_r), (int) (25*height_r));
        add(l1);
        JTextField textField1=new JTextField();
        textField1.setFont(new Font("宋体", Font.BOLD, (int) (12*width_r)));
        textField1.setBounds((int) (235*width_r), (int) (120*height_r), (int) (125*width_r), (int) (25*height_r));
        add(textField1);
        textField1.setColumns(10);

        JLabel l2 = new JLabel("书名:");
        l2.setFont(new Font("宋体", Font.BOLD, (int) (15*width_r)));
        l2.setBounds((int) (160*width_r), (int) (160*height_r), (int) (250*width_r), (int) (25*height_r));
        add(l2);
        JTextField textField2=new JTextField();
        textField2.setFont(new Font("宋体", Font.BOLD, (int) (12*width_r)));
        textField2.setBounds((int) (235*width_r), (int) (160*height_r), (int) (125*width_r), (int) (25*height_r));
        add(textField2);
        textField2.setColumns(10);

        JLabel l3 = new JLabel("作者:");
        l3.setFont(new Font("宋体", Font.BOLD, (int) (15*width_r)));
        l3.setBounds((int) (160*width_r), (int) (200*height_r), (int) (250*width_r), (int) (25*height_r));
        add(l3);
        JTextField textField3=new JTextField();
        textField3.setFont(new Font("宋体", Font.BOLD, (int) (12*width_r)));
        textField3.setBounds((int) (235*width_r), (int) (200*height_r), (int) (125*width_r), (int) (25*height_r));
        add(textField3);
        textField3.setColumns(10);

        JLabel l4 = new JLabel("价格:");
        l4.setFont(new Font("宋体", Font.BOLD, (int) (15*width_r)));
        l4.setBounds((int) (160*width_r), (int) (240*height_r), (int) (250*width_r), (int) (25*height_r));
        add(l4);
        JTextField textField4=new JTextField();
        textField4.setFont(new Font("宋体", Font.BOLD, (int) (12*width_r)));
        textField4.setBounds((int) (235*width_r), (int) (240*height_r), (int) (125*width_r), (int) (25*height_r));
        add(textField4);
        textField4.setColumns(10);

        JLabel l5 = new JLabel("国家:");
        l5.setFont(new Font("宋体", Font.BOLD, (int) (15*width_r)));
        l5.setBounds((int) (160*width_r), (int) (280*height_r), (int) (250*width_r), (int) (25*height_r));
        add(l5);
        JTextField textField5=new JTextField();
        textField5.setFont(new Font("宋体", Font.BOLD, (int) (12*width_r)));
        textField5.setBounds((int) (235*width_r), (int) (280*height_r), (int) (125*width_r), (int) (25*height_r));
        add(textField5);
        textField5.setColumns(10);

        JLabel l6 = new JLabel("馆藏地:");
        l6.setFont(new Font("宋体", Font.BOLD, (int) (15*width_r)));
        l6.setBounds((int) (160*width_r), (int) (320*height_r), (int) (250*width_r), (int) (25*height_r));
        add(l6);
        JTextField textField6=new JTextField();
        textField6.setFont(new Font("宋体", Font.BOLD, (int) (12*width_r)));
        textField6.setBounds((int) (235*width_r), (int) (320*height_r), (int) (125*width_r), (int) (25*height_r));
        add(textField6);
        textField6.setColumns(10);

        JLabel l7 = new JLabel("出版社:");
        l7.setFont(new Font("宋体", Font.BOLD, (int) (15*width_r)));
        l7.setBounds((int) (160*width_r), (int) (360*height_r), (int) (250*width_r), (int) (25*height_r));
        add(l7);
        JTextField textField7=new JTextField();
        textField7.setFont(new Font("宋体", Font.BOLD, (int) (12*width_r)));
        textField7.setBounds((int) (235*width_r), (int) (360*height_r), (int) (125*width_r), (int) (25*height_r));
        add(textField7);
        textField7.setColumns(10);

        JButton b1=new JButton("确定录入");
        b1.setFont(new Font("楷体", Font.BOLD, (int) (18*width_r)));
        b1.setBounds((int) (235*width_r), (int) (420*height_r), (int) (120*width_r), (int) (50*height_r));
        b1.setBackground(new Color(255,160,122));
        b1.setFocusPainted(false);
        add(b1);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                addinfo[0]=textField1.getText();
                addinfo[1]=textField2.getText();
                addinfo[2]=textField3.getText();
                addinfo[3]=textField4.getText();
                addinfo[4]=textField5.getText();
                addinfo[5]=textField6.getText();
                addinfo[6]=textField7.getText();
                try {
                    Book_admin book=new Book_admin();
                    book.setID(AddDeleteBook.addinfo[0]);
                    book.setName(AddDeleteBook.addinfo[1]);
                    book.setAuthor(AddDeleteBook.addinfo[2]);
                    book.setPrice(Double.valueOf(AddDeleteBook.addinfo[3]));
                    book.setCountry(AddDeleteBook.addinfo[4]);
                    book.setPlace(AddDeleteBook.addinfo[5]);
                    book.setPublisher(AddDeleteBook.addinfo[6]);
                    book.setAvailable(1);
                    Client_library.RequireAddBook(book);
                    JOptionPane.showMessageDialog(null,"图书录入成功！");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
                textField5.setText("");
                textField6.setText("");
                textField7.setText("");


            }
        });

        //中间面板
        JPanel p = new JPanel();
        p.setBounds((int) (150*width_r), (int) (3*height_r), (int) (290*width_r), (int) (630*height_r));
        p.setBackground(new Color(233,150,122,150));
        add(p);

        JLabel l8 = new JLabel("书籍编号:");
        l8.setFont(new Font("宋体", Font.BOLD, (int) (15*width_r)));
        l8.setBounds((int) (810*width_r), (int) (280*height_r), (int) (250*width_r), (int) (25*height_r));
        add(l8);
        JTextField textField8=new JTextField();
        textField8.setFont(new Font("宋体", Font.BOLD, (int) (12*width_r)));
        textField8.setBounds((int) (885*width_r), (int) (280*height_r), (int) (125*width_r), (int) (25*height_r));
        add(textField8);
        textField8.setColumns(10);

        JButton b2=new JButton("确定删除");
        b2.setFont(new Font("楷体", Font.BOLD, (int) (18*width_r)));
        b2.setBounds((int) (885*width_r), (int) (350*height_r), (int) (120*width_r), (int) (50*height_r));
        b2.setBackground(new Color(255,160,122));
        add(b2);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                deleteID=textField8.getText();
                try {
                    Client_library.RequireDeleteBook(deleteID);
                   // JOptionPane.showMessageDialog(null,"图书删除成功！");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                textField8.setText("");
            }
        });
        b2.setFocusPainted(false);

        //中间面板
        JPanel p0 = new JPanel();
        p0.setBounds((int) (800*width_r), (int) (180*height_r), (int) (290*width_r), (int) (250*height_r));
        p0.setBackground(new Color(233,150,122,150));
        add(p0);

        JPanel p11=new JPanel();
        p11.setBounds(0,0, (int) (635*width_r), (int) (650*height_r));
        JLabel pic1 = new JLabel();
        ImageIcon icon1 = new ImageIcon("src/image/bg17.jpg");
        int icon1_width= 635;
        int icon1_height=650;
        try {
            Thumbnails.of(new File("src/image/bg17.jpg"))
                    .size((int)(icon1_width*width_r), (int)(icon1_height*height_r))
                    .keepAspectRatio(false)
                    .toFile(new File("src/image/bg17_min.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        pic1.setIcon(new ImageIcon("src/image/bg17_min.jpg"));
        pic1.setBounds(0,0 , (int) (635*width_r), (int) (650*height_r));
        p11.add(pic1);
        add(p11);

        JPanel p12=new JPanel();
        p12.setBounds((int) (630*width_r),0, (int) (660*width_r), (int) (650*height_r));
        JLabel pic2 = new JLabel();
        ImageIcon icon2 = new ImageIcon("src/image/bg17.jpg");
        int icon2_width= 660;
        int icon2_height=650;
        try {
            Thumbnails.of(new File("src/image/bg17.jpg"))
                    .size((int)(icon2_width*width_r), (int)(icon2_height*height_r))
                    .keepAspectRatio(false)
                    .toFile(new File("src/image/bg17_min.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        pic2.setIcon(new ImageIcon("src/image/bg17_min.jpg"));
        pic2.setBounds((int) (620*width_r),0, (int) (660*width_r), (int) (650*height_r));
        p12.add(pic2);
        add(p12);
    }
}