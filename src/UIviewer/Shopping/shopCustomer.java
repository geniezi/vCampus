package UIviewer.Shopping;
import ClientToServer.ClientToServer;
import DAO.Shop.Product;
import DAO.Shop.ProductPair;
import UIhandler.Library.Client_library;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import UIhandler.Shop.Client_shop;
import UIviewer.login.functionChoose;
import net.coobird.thumbnailator.Thumbnails;
import ClientToServer.myInfo;

import static UIviewer.Shopping.ShoppingHall.resetshoptable;
import static UIviewer.Shopping.ShoppingHall.setShoptable;

/**
 * 店客户
 *
 * @author Chen_GuanZhi
 * @date 2022/09/03
 */
public class shopCustomer extends JPanel {
    Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
    int width=(int ) screensize.getWidth(); //得到宽度
    int height=(int ) screensize.getHeight();//获得高度
    double width_r=(double)(width)/1273;
    double height_r=(double)(height)/784;
    public static JPanel panel = new JPanel();;
    static String name;
    public static List<Product> t;
    public static JButton b1=new JButton("主商城");
    public static JButton b2=new JButton("我的购物车");
    public static JButton b3=new JButton("订单记录");

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
     * 刷新
     */
    public void refresh()
    {

    }

    /**
     * 店客户
     *
     * @throws Exception 异常
     */
    public shopCustomer() throws Exception {
        String name=myInfo.getName();
        getName(name);
        double money = 0;
        if(myInfo.getType()==1)
             money=Client_shop.getMoney(myInfo.getId());
        else
             money=Client_shop.getMoney_Teacher(myInfo.getId());

        setBounds(0,0, (int) (1273*width_r), (int) (784*height_r));
        setLayout(null);

        //cardLayout.show(panel, "f1");

        panel.setBounds(0, (int) (150*height_r), (int) (1273*width_r), (int) (634*height_r));
        panel.setBackground(new Color(0,0,0));
        add(panel);
//		给主要显示面板添加布局方式
        panel.setLayout(cardLayout);
//		创建相应面板类的对象

        //商店标志
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


        t = Client_shop.returnAllProduct();
        String[][] temp = new String[t.size()][];
        for(int i =0;i<t.size();i++){
            String[] tt =new String[7];
            tt[0]=String.valueOf(t.get(i).getProduct_id());
            tt[1]=t.get(i).getProduct_name();
            tt[2]=String.valueOf(t.get(i).getProduct_price());
            tt[3]=String.valueOf(t.get(i).getProduct_currentNumbers());
            tt[4]="1";
            tt[5]="加入购物车";
            tt[6]="购买";
            temp[i]=tt;
        }
        ShoppingHall.setShoptable(temp);
        ShoppingHall f1=new ShoppingHall();
        panel.add(f1,"f1");




        //文字
        JLabel l19 = new JLabel("『欢迎光临东南大学天猫校园商店』");
        l19.setBounds((int) (220*width_r), (int) (15*height_r), (int) (700*width_r), (int) (80*height_r));
        l19.setForeground(new Color(248, 248, 255));
        Font font5 = new Font("楷体", Font.BOLD, (int) (30*width_r));
        l19.setFont(font5);
        add(l19);
        //文字
        JLabel l1 = new JLabel("您好！"+name+"。您的余额为"+String.format("%.2f", money)+"元。");
        l1.setBounds((int) (900*width_r), (int) (35*height_r), (int) (420*width_r), (int) (55*height_r));
        l1.setForeground(new Color(248, 248, 255));
        Font font = new Font("楷体", Font.BOLD, (int) (20*width_r));
        l1.setFont(font);
        add(l1);

        //上方面板
        JPanel p1 = new JPanel();
        p1.setBounds(0, 0, (int) (1280*width_r), (int) (100*height_r));
        p1.setBackground(new Color(255,127,80));
        add(p1);

        //主商城
        b1.setBounds((int) (100*width_r), (int) (100*height_r), (int) (250*width_r), (int) (50*height_r));
        Font myfont1 = new Font("微软雅黑", Font.BOLD, (int) (18*width_r));
        b1.setFont(myfont1);
        b1.setContentAreaFilled(false);//设置按钮透明
        b1.setFocusPainted(false);
        b1.setForeground(new Color(248, 248, 255));
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                     t = null;
                try {
                    t = Client_shop.returnAllProduct();
                    String[][] temp = new String[t.size()][];
                    for(int i =0;i<t.size();i++){
                        String[] tt =new String[7];
                        tt[0]=String.valueOf(t.get(i).getProduct_id());
                        tt[1]=t.get(i).getProduct_name();
                        tt[2]=String.valueOf(t.get(i).getProduct_price());
                        tt[3]=String.valueOf(t.get(i).getProduct_currentNumbers());
                        tt[4]="1";
                        tt[5]="加入购物车";
                        tt[6]="购买";
                        temp[i]=tt;
                    }
                    setShoptable(temp);
                    ShoppingHall f11=new ShoppingHall();
                    panel.add(f11,"f11");
                    cardLayout.show(panel, "f11");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        add(b1);

        //我的购物车
        b2.setBounds((int) (370*width_r), (int) (100*height_r), (int) (250*width_r), (int) (50*height_r));
        b2.setFont(myfont1);
        b2.setContentAreaFilled(false);//设置按钮透明
        b2.setFocusPainted(false);
        b2.setForeground(new Color(248, 248, 255));
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                try {
                    resetshoptable();
                    List<ProductPair> p = Client_shop.checkReadyToBuy(myInfo.getId());
                    HashMap<Integer, Integer> all = new HashMap<>();
                    if(p!=null){
                        for (ProductPair productPair : p) {
                            if (all.get(productPair.getId()) != null) {
                                int t = all.get(productPair.getId()) + productPair.getNum();
                                all.put(productPair.getId(), t);
                            } else {
                                all.put(productPair.getId(), productPair.getNum());
                            }
                        }

                        List<Product> book = new ArrayList<>();
                        for (Integer i : all.keySet()) {
                            Product tempt = Client_shop.checkCertainProduct(i);
                            if(tempt!=null)
                                book.add(tempt);
                        }
                        if(book.size()!=0){
                            String[][] temp = new String[book.size()][];
                            for(int i =0;i<book.size();i++){
                                String[] tt =new String[6];
                                tt[0]=String.valueOf(book.get(i).getProduct_id());
                                tt[1]=book.get(i).getProduct_name();
                                tt[2]=String.valueOf(all.get(book.get(i).getProduct_id()));  //数量
                                tt[3]=String.valueOf(book.get(i).getProduct_price()*all.get(book.get(i).getProduct_id()));
                                tt[4]="购买";
                                tt[5]="删除";
                                temp[i]=tt;
                            }
                            shopCar.setMyBook(temp);
                           // System.out.println(temp.length+"  11111");
                        }
                    }
                    else {
                        shopCar.setMyBook(null);
                        //System.out.println("空");
                    }
                    shopCar f2=new shopCar();
                    panel.add(f2,"f2");
                    cardLayout.show(panel,"f2");

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        add(b2);

        //订单记录
        b3.setBounds((int) (640*width_r), (int) (100*height_r), (int) (250*width_r), (int) (50*height_r));
        b3.setFont(myfont1);
        b3.setContentAreaFilled(false);//设置按钮透明
        b3.setFocusPainted(false);
        b3.setForeground(new Color(248, 248, 255));
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                try {
                    resetshoptable();
                    List<ProductPair> p = Client_shop.checkBuyed(myInfo.getId());
                    HashMap<Integer, Integer> all = new HashMap<Integer, Integer>();
                    if (p != null) {
                        for (ProductPair productPair : p) {
                            if (all.get(productPair.getId()) != null) {
                                int t = all.get(productPair.getId()) + productPair.getNum();
                                all.put(productPair.getId(), t);
                            } else {
                                all.put(productPair.getId(), productPair.getNum());
                            }
                        }
                        List<Product> book = new ArrayList<>();
                        for (Integer i : all.keySet()) {
                            Product tempt = Client_shop.checkCertainProduct(i);
                            if (tempt != null)
                                book.add(tempt);
                        }
                        if (book.size() != 0) {
                            String[][] temp = new String[book.size()][];
                            for (int i = 0; i < book.size(); i++) {
                                String[] tt = new String[5];
                                tt[0] = String.valueOf(book.get(i).getProduct_id());
                                tt[1] = book.get(i).getProduct_name();
                                tt[2] = String.valueOf(all.get(book.get(i).getProduct_id()));  //数量
                                tt[3] = String.valueOf(book.get(i).getProduct_price() * all.get(book.get(i).getProduct_id()));
                                temp[i] = tt;
                            }
                            OrderHistory.setCon_bought(temp);
                        }
                    } else {
                        //System.out.println("空");
                    }
                    OrderHistory f3=new OrderHistory();
                    panel.add(f3,"f3");
                    cardLayout.show(panel,"f3");
                }catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        add(b3);

        JButton b4=new JButton("退出商店");
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
                cardLayout.show(panel, "f1");
            }
        });
        add(b4);

        //按钮面板
        JPanel p2 = new JPanel();
        p2.setBounds(0, (int) (100*height_r), (int) (1280*width_r), (int) (50*height_r));
        p2.setBackground(new Color(255,160,122));
        add(p2);

        setVisible(true);
    }

}