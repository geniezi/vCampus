package UIviewer.Shopping;
import ClientToServer.ClientToServer;
import DAO.Shop.Product;
import UIhandler.Library.Client_library;
import UIhandler.Shop.Client_shop;
import UIviewer.login.functionChoose;
import net.coobird.thumbnailator.Thumbnails;
import UIviewer.Shopping.AddDeleteGoods;
import UIviewer.Shopping.AllGoods;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import ClientToServer.myInfo;

import static UIviewer.Shopping.ShoppingHall.setShoptable;

/**
 * 店管理
 *
 * @author Chen_GuanZhi
 * @date 2022/09/03
 */
public class shopAdmin extends JPanel {
    Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
    int width=(int ) screensize.getWidth(); //得到宽度
    int height=(int ) screensize.getHeight();//获得高度
    double width_r=(double)(width)/1273;
    double height_r=(double)(height)/784;
    static String name;
    public static JPanel panel = new JPanel();

    /**
     * 得到名字
     *
     * @param a 一个
     */
    static void getName(String a)
    {
        name=a;
    }
    public static CardLayout cardLayout=new CardLayout();

    /**
     * 店管理
     *
     * @throws Exception 异常
     */
    public shopAdmin() throws Exception {
        String name=myInfo.getName();
        getName(name);
        setBounds(0,0, (int) (1273*width_r), (int) (790*height_r));
        setLayout(null);
        panel.setBounds(0, (int) (150*height_r), (int) (1273*width_r), (int) (790*height_r));
        add(panel);
//		给主要显示面板添加布局方式
        panel.setLayout(cardLayout);
//		创建相应面板类的对象

        JLabel logo = new JLabel();
        ImageIcon icon = new ImageIcon("src/image/780.png");
        int icon1_width= 600;
        int icon1_height=100;
        try {
            Thumbnails.of(new File("src/image/780.png"))
                    .size((int)(icon1_width*width_r), (int)(icon1_height*height_r))
                    .toFile(new File("src/image/780_min.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logo.setIcon(new ImageIcon("src/image/780_min.png"));
        logo.setBounds((int) (0*width_r), (int) (0*height_r), (int) (600*width_r), (int) (100*height_r));
        add(logo);

        List<Product> t = Client_shop.returnAllProduct();
        String[][] temp = new String[t.size()][];
        for(int i =0;i<t.size();i++){
            String[] tt =new String[5];
            tt[0]=String.valueOf(t.get(i).getProduct_id());
            tt[1]=t.get(i).getProduct_name();
            tt[2]=String.valueOf(t.get(i).getProduct_price());
            tt[3]=String.valueOf(t.get(i).getProduct_currentNumbers());
            tt[4]=t.get(i).getProduct_type();
            temp[i]=tt;
        }
        //System.out.println("temp="+temp.length);
        Client_shop.setId(String.valueOf(myInfo.getType()));
        Client_shop.setIdcard(myInfo.getId());
        //functionChoose.jf.setContentPane(new shopAdmin());
        AllGoods.setTableDate(temp);
        AllGoods f11=new AllGoods();
        panel.add(f11,"f11");
        //cardLayout.show(panel,"f11");


        //文字
        JLabel l19 = new JLabel("『东南大学天猫校园商店管理系统』");
        l19.setBounds((int) (220*width_r), (int) (15*height_r), (int) (700*width_r), (int) (80*height_r));
        l19.setForeground(new Color(248, 248, 255));
        Font font5 = new Font("楷体", Font.BOLD, (int) (30*width_r));
        l19.setFont(font5);
        add(l19);
        //文字
        JLabel l1 = new JLabel("你好！"+name);
        l1.setBounds((int) (1100*width_r), (int) (30*height_r), (int) (200*width_r), (int) (55*height_r));
        l1.setForeground(new Color(248, 248, 255));
        Font font = new Font("楷体", Font.BOLD, (int) (20*width_r));
        l1.setFont(font);
        add(l1);

        //上方面板
        JPanel p1 = new JPanel();
        p1.setBounds(0, 0, (int) (1280*width_r), (int) (100*height_r));
        p1.setBackground(new Color(255,127,80));
        add(p1);

        //按钮
        JButton b1=new JButton("查看所有商品");
        b1.setBounds((int) (100*width_r), (int) (100*height_r), (int) (250*width_r), (int) (50*height_r));
        Font myfont1 = new Font("微软雅黑", Font.BOLD, (int) (18*width_r));
        b1.setFont(myfont1);
        b1.setContentAreaFilled(false);//设置按钮透明
        b1.setFocusPainted(false);
        b1.setForeground(new Color(248, 248, 255));
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Product> t = null;
                AllGoods.setTableDate(null);
                try {
                    t = Client_shop.returnAllProduct();
                    String[][] temp = new String[t.size()][];
                    for(int i =0;i<t.size();i++){
                        String[] tt =new String[5];
                        tt[0]=String.valueOf(t.get(i).getProduct_id());
                        tt[1]=t.get(i).getProduct_name();
                        tt[2]=String.valueOf(t.get(i).getProduct_price());
                        tt[3]=String.valueOf(t.get(i).getProduct_currentNumbers());
                        tt[4]=t.get(i).getProduct_type();
                        temp[i]=tt;
                    }
                   // System.out.println("temp.length="+temp.length);
                    AllGoods.setTableDate(temp);
                    AllGoods f11=new AllGoods();
                    panel.add(f11,"f11");
                    cardLayout.show(panel,"f11");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        add(b1);

        JButton b2=new JButton("录入/删除商品");
        b2.setBounds((int) (505*width_r), (int) (100*height_r), (int) (250*width_r), (int) (50*height_r));
        b2.setFont(myfont1);
        b2.setContentAreaFilled(false);//设置按钮透明
        b2.setFocusPainted(false);
        b2.setForeground(new Color(248, 248, 255));
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                AddDeleteGoods f12=new AddDeleteGoods();
                panel.add(f12,"f12");
                cardLayout.show(panel,"f12");
            }
        });
        add(b2);



        JButton b4=new JButton("退出商店管理");
        b4.setBounds((int) (910*width_r), (int) (100*height_r), (int) (250*width_r), (int) (50*height_r));
        b4.setFont(myfont1);
        b4.setContentAreaFilled(false);//设置按钮透明
        b4.setFocusPainted(false);
        b4.setForeground(new Color(248, 248, 255));
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                functionChoose.jf.setContentPane(functionChoose.fc_panel);
                functionChoose.jf.setTitle("functionChoose");
                //cardLayout.show(panel, "f1");
            }
        });
        add(b4);

        //按钮面板
        JPanel p2 = new JPanel();
        p2.setBounds(0, (int) (100*height_r), (int) (1280*width_r), (int) (50*height_r));
        p2.setBackground(new Color(255,160,122));
        add(p2);

    }
}