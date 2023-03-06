package UIviewer.Shopping;
import DAO.Shop.Product;
import UIhandler.Library.Client_library;
import UIhandler.Shop.Client_shop;

import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import ClientToServer.myInfo;
import UIviewer.login.functionChoose;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static UIhandler.Shop.Client_shop.checktypeProduct;
import static UIviewer.Shopping.shopCustomer.cardLayout;
import static UIviewer.Shopping.shopCustomer.panel;
import static UIviewer.login.forgetPWD.forgetPWDUI;

/**
 * 购物大厅
 *
 * @author Chen_GuanZhi
 * @date 2022/09/03
 */
public class ShoppingHall extends JPanel {
    Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
    int width=(int ) screensize.getWidth(); //得到宽度
    int height=(int ) screensize.getHeight();//获得高度
    double width_r=(double)(width)/1273;
    double height_r=(double)(height)/784;

    public static Object[][] data;

    /**
     * 获取数据
     *
     * @return {@link Object[][]}
     */
    public static Object[][] getData() {
        return data;
    }

    /**
     * 集数据
     *
     * @param data 数据
     */
    public static void setData(Object[][] data) {
        ShoppingHall.data = data;
    }

    public static String[][] shoptable;

    /**
     * resetshoptable
     */
    public static void resetshoptable(){
        shoptable=null;
    }

    /**
     * 得到shoptable
     *
     * @return {@link String[][]}
     */
    public static String[][] getShoptable() {
        return shoptable;
    }

    /**
     * 设置shoptable
     *
     * @param shoptable shoptable
     */
    public static void setShoptable(String[][] shoptable) {
        ShoppingHall.shoptable = shoptable;
    }

    /**
     * 购物大厅
     */
    public ShoppingHall() {
        setLayout(null);

        setBackground(new Color(251, 244, 242));
        //文字
        JLabel l19 = new JLabel("       商品分类");
        l19.setBounds((int) (40*width_r), (int) (0*height_r), (int) (300*width_r), (int) (80*height_r));
        l19.setForeground(new Color(255,69,0));
        Font font5 = new Font("楷体", Font.BOLD, (int) (24*width_r));
        l19.setFont(font5);
        add(l19);

        JTextField textField=new MyTextField(20);
        textField.setFont(new Font("微软雅黑", Font.BOLD, (int) (18*width_r)));
        textField.setBounds((int) (460*width_r), (int) (30*height_r), (int) (430*width_r), (int) (40*height_r));
        add(textField);
        textField.setColumns((int) (10*height_r));

        JButton b11=new NewButton("检索");
        b11.setBounds((int) (980*width_r), (int) (30*height_r), (int) (80*width_r), (int) (40*height_r));
        Font myfont = new Font("楷体", Font.BOLD, (int) (20*width_r));
        b11.setFont(myfont);
        b11.setBackground(new Color(255,127,80));
        b11.setForeground(new Color(255,255,255));
        b11.setFocusPainted(false);
        b11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String searchInfo=textField.getText();
                try {
                    List<Product> t = Client_shop.checkProduct(searchInfo);
                    if(t!=null){
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
                    }else {
                       // System.out.println("kong");
                        setShoptable(null);
                    }
                    ShoppingHall f112=new ShoppingHall();
                    panel.add(f112,"f112");
                    cardLayout.show(panel, "f112");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        add(b11);

        Font myfont1 = new Font("楷体 ", Font.BOLD, (int) (18*width_r));

        JButton btnNewButton_1 = new JButton("零食");
        btnNewButton_1.setBounds((int) (20*width_r), (int) (150*height_r), (int) (80*width_r), (int) (30*height_r));
        btnNewButton_1.setFont(myfont1);
        btnNewButton_1.setForeground(new Color(255,255,255));
        btnNewButton_1.setBackground(new Color(254,178,148));
        btnNewButton_1.setBorder(BorderFactory.createLineBorder(new Color(254,178,148)));
        btnNewButton_1.setFocusPainted(false);
        btnNewButton_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                btnNewButton_1.setContentAreaFilled(false);//设置按钮透明
                btnNewButton_1.setBackground(new Color(255,160,122));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                btnNewButton_1.setContentAreaFilled(true);//设置按钮透明
                btnNewButton_1.setBackground(new Color(254,178,148));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                btnNewButton_1.setFont(new Font("楷体",Font.BOLD, (int) (20*width_r)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                btnNewButton_1.setFont(new Font("楷体 ", Font.PLAIN, (int) (18*width_r)));
            }
        });
        btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Product> t = Client_shop.checktypeProduct("零食");
                    if(t!=null) {
                        String[][] temp = new String[t.size()][];
                        for (int i = 0; i < t.size(); i++) {
                            String[] tt = new String[7];
                            tt[0] = String.valueOf(t.get(i).getProduct_id());
                            tt[1] = t.get(i).getProduct_name();
                            tt[2] = String.valueOf(t.get(i).getProduct_price());
                            tt[3] = String.valueOf(t.get(i).getProduct_currentNumbers());
                            tt[4] = "1";
                            tt[5] = "加入购物车";
                            tt[6] = "购买";
                            temp[i] = tt;
                        }
                        setShoptable(temp);
                    }else {
                        setShoptable(null);
                    }
                    ShoppingHall f11=new ShoppingHall();
                    panel.add(f11,"f11");
                    cardLayout.show(panel, "f11");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                } }
        });
        add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("饼干");
        btnNewButton_2.setBounds((int) (120*width_r), (int) (150*height_r), (int) (80*width_r), (int) (30*height_r));
        btnNewButton_2.setFont(myfont1);
        btnNewButton_2.setForeground(new Color(255,255,255));
        btnNewButton_2.setBackground(new Color(254,178,148));
        btnNewButton_2.setBorder(BorderFactory.createLineBorder(new Color(254,178,148)));
        btnNewButton_2.setFocusPainted(false);
        btnNewButton_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                btnNewButton_2.setContentAreaFilled(false);//设置按钮透明
                btnNewButton_2.setBackground(new Color(255,160,122));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                btnNewButton_2.setContentAreaFilled(true);//设置按钮透明
                btnNewButton_2.setBackground(new Color(254,178,148));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                btnNewButton_2.setFont(new Font("楷体",Font.BOLD, (int) (20*width_r)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                btnNewButton_2.setFont(new Font("楷体 ", Font.PLAIN, (int) (18*width_r)));
            }
        });
        btnNewButton_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Product> t = Client_shop.checktypeProduct("饼干");
                    if(t!=null) {
                        String[][] temp = new String[t.size()][];
                        for (int i = 0; i < t.size(); i++) {
                            String[] tt = new String[7];
                            tt[0] = String.valueOf(t.get(i).getProduct_id());
                            tt[1] = t.get(i).getProduct_name();
                            tt[2] = String.valueOf(t.get(i).getProduct_price());
                            tt[3] = String.valueOf(t.get(i).getProduct_currentNumbers());
                            tt[4] = "1";
                            tt[5] = "加入购物车";
                            tt[6] = "购买";
                            temp[i] = tt;
                        }
                        setShoptable(temp);
                    }else {
                        setShoptable(null);
                    }
                    ShoppingHall f11=new ShoppingHall();
                    panel.add(f11,"f11");
                    cardLayout.show(panel, "f11");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        add(btnNewButton_2);

        JButton btnNewButton_3 = new JButton("茶");
        btnNewButton_3.setBounds((int) (225*width_r), (int) (150*height_r), (int) (80*width_r), (int) (30*height_r));
        btnNewButton_3.setFont(myfont1);
        btnNewButton_3.setForeground(new Color(255,255,255));
        btnNewButton_3.setBackground(new Color(254,178,148));
        btnNewButton_3.setBorder(BorderFactory.createLineBorder(new Color(254,178,148)));
        btnNewButton_3.setFocusPainted(false);
        btnNewButton_3.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                btnNewButton_3.setContentAreaFilled(false);//设置按钮透明
                btnNewButton_3.setBackground(new Color(255,160,122));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                btnNewButton_3.setContentAreaFilled(true);//设置按钮透明
                btnNewButton_3.setBackground(new Color(254,178,148));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                btnNewButton_3.setFont(new Font("楷体",Font.BOLD, (int) (20*width_r)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                btnNewButton_3.setFont(new Font("楷体 ", Font.PLAIN, (int) (18*width_r)));
            }
        });
        btnNewButton_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Product> t = Client_shop.checktypeProduct("茶");
                    if(t!=null) {
                        String[][] temp = new String[t.size()][];
                        for (int i = 0; i < t.size(); i++) {
                            String[] tt = new String[7];
                            tt[0] = String.valueOf(t.get(i).getProduct_id());
                            tt[1] = t.get(i).getProduct_name();
                            tt[2] = String.valueOf(t.get(i).getProduct_price());
                            tt[3] = String.valueOf(t.get(i).getProduct_currentNumbers());
                            tt[4] = "1";
                            tt[5] = "加入购物车";
                            tt[6] = "购买";
                            temp[i] = tt;
                        }
                        setShoptable(temp);
                    }else {
                        setShoptable(null);
                    }
                    ShoppingHall f11=new ShoppingHall();
                    panel.add(f11,"f11");
                    cardLayout.show(panel, "f11");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                } }
        });
        add(btnNewButton_3);

        JButton btnNewButton_4 = new JButton("美妆");
        btnNewButton_4.setBounds((int) (20*width_r), (int) (220*height_r), (int) (80*width_r), (int) (30*height_r));
        btnNewButton_4.setFont(myfont1);
        btnNewButton_4.setForeground(new Color(255,255,255));
        btnNewButton_4.setBackground(new Color(254,178,148));
        btnNewButton_4.setBorder(BorderFactory.createLineBorder(new Color(254,178,148)));
        btnNewButton_4.setFocusPainted(false);
        btnNewButton_4.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                btnNewButton_4.setContentAreaFilled(false);//设置按钮透明
                btnNewButton_4.setBackground(new Color(255,160,122));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                btnNewButton_4.setContentAreaFilled(true);//设置按钮透明
                btnNewButton_4.setBackground(new Color(254,178,148));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                btnNewButton_4.setFont(new Font("楷体",Font.BOLD, (int) (20*width_r)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                btnNewButton_4.setFont(new Font("楷体 ", Font.PLAIN, (int) (18*width_r)));
            }
        });
        btnNewButton_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Product> t = Client_shop.checktypeProduct("美妆");
                    if(t!=null) {
                        String[][] temp = new String[t.size()][];
                        for (int i = 0; i < t.size(); i++) {
                            String[] tt = new String[7];
                            tt[0] = String.valueOf(t.get(i).getProduct_id());
                            tt[1] = t.get(i).getProduct_name();
                            tt[2] = String.valueOf(t.get(i).getProduct_price());
                            tt[3] = String.valueOf(t.get(i).getProduct_currentNumbers());
                            tt[4] = "1";
                            tt[5] = "加入购物车";
                            tt[6] = "购买";
                            temp[i] = tt;
                        }
                        setShoptable(temp);
                    }else {
                        setShoptable(null);
                    }
                    ShoppingHall f11=new ShoppingHall();
                    panel.add(f11,"f11");
                    cardLayout.show(panel, "f11");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                } }
        });
        add(btnNewButton_4);


        JButton btnNewButton_5 = new JButton("饰品");
        btnNewButton_5.setBounds((int) (120*width_r), (int) (220*height_r), (int) (80*width_r), (int) (30*height_r));
        btnNewButton_5.setFont(myfont1);
        btnNewButton_5.setForeground(new Color(255,255,255));
        btnNewButton_5.setBackground(new Color(254,178,148));
        btnNewButton_5.setBorder(BorderFactory.createLineBorder(new Color(254,178,148)));
        btnNewButton_5.setFocusPainted(false);
        btnNewButton_5.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                btnNewButton_5.setContentAreaFilled(false);//设置按钮透明
                btnNewButton_5.setBackground(new Color(255,160,122));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                btnNewButton_5.setContentAreaFilled(true);//设置按钮透明
                btnNewButton_5.setBackground(new Color(254,178,148));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                btnNewButton_5.setFont(new Font("楷体",Font.BOLD, (int) (20*width_r)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                btnNewButton_5.setFont(new Font("楷体 ", Font.PLAIN, (int) (18*width_r)));
            }
        });
        btnNewButton_5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Product> t = Client_shop.checktypeProduct("饰品");
                    if(t!=null) {
                        String[][] temp = new String[t.size()][];
                        for (int i = 0; i < t.size(); i++) {
                            String[] tt = new String[7];
                            tt[0] = String.valueOf(t.get(i).getProduct_id());
                            tt[1] = t.get(i).getProduct_name();
                            tt[2] = String.valueOf(t.get(i).getProduct_price());
                            tt[3] = String.valueOf(t.get(i).getProduct_currentNumbers());
                            tt[4] = "1";
                            tt[5] = "加入购物车";
                            tt[6] = "购买";
                            temp[i] = tt;
                        }
                        setShoptable(temp);
                    }else {
                        setShoptable(null);
                    }
                    ShoppingHall f11=new ShoppingHall();
                    panel.add(f11,"f11");
                    cardLayout.show(panel, "f11");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }}
        });
        add(btnNewButton_5);

        JButton btnNewButton_6 = new JButton("洗护");
        btnNewButton_6.setBounds((int) (225*width_r), (int) (220*height_r), (int) (80*width_r), (int) (30*height_r));
        btnNewButton_6.setFont(myfont1);
        btnNewButton_6.setForeground(new Color(255,255,255));
        btnNewButton_6.setBackground(new Color(254,178,148));
        btnNewButton_6.setBorder(BorderFactory.createLineBorder(new Color(254,178,148)));
        btnNewButton_6.setFocusPainted(false);
        btnNewButton_6.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                btnNewButton_6.setContentAreaFilled(false);//设置按钮透明
                btnNewButton_6.setBackground(new Color(255,160,122));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                btnNewButton_6.setContentAreaFilled(true);//设置按钮透明
                btnNewButton_6.setBackground(new Color(254,178,148));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                btnNewButton_6.setFont(new Font("楷体",Font.BOLD, (int) (20*width_r)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                btnNewButton_6.setFont(new Font("楷体 ", Font.PLAIN, (int) (18*width_r)));
            }
        });
        btnNewButton_6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Product> t = Client_shop.checktypeProduct("洗护");
                    if(t!=null) {
                        String[][] temp = new String[t.size()][];
                        for (int i = 0; i < t.size(); i++) {
                            String[] tt = new String[7];
                            tt[0] = String.valueOf(t.get(i).getProduct_id());
                            tt[1] = t.get(i).getProduct_name();
                            tt[2] = String.valueOf(t.get(i).getProduct_price());
                            tt[3] = String.valueOf(t.get(i).getProduct_currentNumbers());
                            tt[4] = "1";
                            tt[5] = "加入购物车";
                            tt[6] = "购买";
                            temp[i] = tt;
                        }
                        setShoptable(temp);
                    }else {
                        setShoptable(null);
                    }
                    ShoppingHall f11=new ShoppingHall();
                    panel.add(f11,"f11");
                    cardLayout.show(panel, "f11");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                } }
        });
        add(btnNewButton_6);

        JButton btnNewButton_7 = new JButton("男装");
        btnNewButton_7.setBounds((int) (20*width_r), (int) (290*height_r), (int) (80*width_r), (int) (30*height_r));
        btnNewButton_7.setFont(myfont1);
        btnNewButton_7.setForeground(new Color(255,255,255));
        btnNewButton_7.setBackground(new Color(254,178,148));
        btnNewButton_7.setBorder(BorderFactory.createLineBorder(new Color(254,178,148)));
        btnNewButton_7.setFocusPainted(false);
        btnNewButton_7.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                btnNewButton_7.setContentAreaFilled(false);//设置按钮透明
                btnNewButton_7.setBackground(new Color(255,160,122));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                btnNewButton_7.setContentAreaFilled(true);//设置按钮透明
                btnNewButton_7.setBackground(new Color(254,178,148));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                btnNewButton_7.setFont(new Font("楷体",Font.BOLD, (int) (20*width_r)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                btnNewButton_7.setFont(new Font("楷体 ", Font.PLAIN, (int) (18*width_r)));
            }
        });
        btnNewButton_7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Product> t = Client_shop.checktypeProduct("男装");
                    if(t!=null) {
                        String[][] temp = new String[t.size()][];
                        for (int i = 0; i < t.size(); i++) {
                            String[] tt = new String[7];
                            tt[0] = String.valueOf(t.get(i).getProduct_id());
                            tt[1] = t.get(i).getProduct_name();
                            tt[2] = String.valueOf(t.get(i).getProduct_price());
                            tt[3] = String.valueOf(t.get(i).getProduct_currentNumbers());
                            tt[4] = "1";
                            tt[5] = "加入购物车";
                            tt[6] = "购买";
                            temp[i] = tt;
                        }
                        setShoptable(temp);
                    }else {
                        setShoptable(null);
                    }
                    ShoppingHall f11=new ShoppingHall();
                    panel.add(f11,"f11");
                    cardLayout.show(panel, "f11");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                } }
        });
        add(btnNewButton_7);

        JButton btnNewButton_8 = new JButton("运动");
        btnNewButton_8.setBounds((int) (120*width_r), (int) (290*height_r), (int) (80*width_r), (int) (30*height_r));
        btnNewButton_8.setFont(myfont1);
        btnNewButton_8.setForeground(new Color(255,255,255));
        btnNewButton_8.setBackground(new Color(254,178,148));
        btnNewButton_8.setBorder(BorderFactory.createLineBorder(new Color(254,178,148)));
        btnNewButton_8.setFocusPainted(false);
        btnNewButton_8.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                btnNewButton_8.setContentAreaFilled(false);//设置按钮透明
                btnNewButton_8.setBackground(new Color(255,160,122));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                btnNewButton_8.setContentAreaFilled(true);//设置按钮透明
                btnNewButton_8.setBackground(new Color(254,178,148));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                btnNewButton_8.setFont(new Font("楷体",Font.BOLD, (int) (20*width_r)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                btnNewButton_8.setFont(new Font("楷体 ", Font.PLAIN, (int) (18*width_r)));
            }
        });
        btnNewButton_8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Product> t = Client_shop.checktypeProduct("运动");
                    if(t!=null) {
                        String[][] temp = new String[t.size()][];
                        for (int i = 0; i < t.size(); i++) {
                            String[] tt = new String[7];
                            tt[0] = String.valueOf(t.get(i).getProduct_id());
                            tt[1] = t.get(i).getProduct_name();
                            tt[2] = String.valueOf(t.get(i).getProduct_price());
                            tt[3] = String.valueOf(t.get(i).getProduct_currentNumbers());
                            tt[4] = "1";
                            tt[5] = "加入购物车";
                            tt[6] = "购买";
                            temp[i] = tt;
                        }
                        setShoptable(temp);
                    }else {
                        setShoptable(null);
                    }
                    ShoppingHall f11=new ShoppingHall();
                    panel.add(f11,"f11");
                    cardLayout.show(panel, "f11");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                } }
        });
        add(btnNewButton_8);

        JButton btnNewButton_9 = new JButton("百货");
        btnNewButton_9.setBounds((int) (225*width_r), (int) (290*height_r), (int) (80*width_r), (int) (30*height_r));
        btnNewButton_9.setFont(myfont1);
        btnNewButton_9.setForeground(new Color(255,255,255));
        btnNewButton_9.setBackground(new Color(254,178,148));
        btnNewButton_9.setBorder(BorderFactory.createLineBorder(new Color(254,178,148)));
        btnNewButton_9.setFocusPainted(false);
        btnNewButton_9.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                btnNewButton_9.setContentAreaFilled(false);//设置按钮透明
                btnNewButton_9.setBackground(new Color(255,160,122));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                btnNewButton_9.setContentAreaFilled(true);//设置按钮透明
                btnNewButton_9.setBackground(new Color(254,178,148));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                btnNewButton_9.setFont(new Font("楷体",Font.BOLD, (int) (20*width_r)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                btnNewButton_9.setFont(new Font("楷体 ", Font.PLAIN, (int) (18*width_r)));
            }
        });
        btnNewButton_9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Product> t = Client_shop.checktypeProduct("百货");
                    if(t!=null) {
                        String[][] temp = new String[t.size()][];
                        for (int i = 0; i < t.size(); i++) {
                            String[] tt = new String[7];
                            tt[0] = String.valueOf(t.get(i).getProduct_id());
                            tt[1] = t.get(i).getProduct_name();
                            tt[2] = String.valueOf(t.get(i).getProduct_price());
                            tt[3] = String.valueOf(t.get(i).getProduct_currentNumbers());
                            tt[4] = "1";
                            tt[5] = "加入购物车";
                            tt[6] = "购买";
                            temp[i] = tt;
                        }
                        setShoptable(temp);
                    }else {
                        setShoptable(null);
                    }
                    ShoppingHall f11=new ShoppingHall();
                    panel.add(f11,"f11");
                    cardLayout.show(panel, "f11");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                } }
        });
        add(btnNewButton_9);

        JButton btnNewButton_10 = new JButton("手机");
        btnNewButton_10.setBounds((int) (20*width_r), (int) (360*height_r), (int) (80*width_r), (int) (30*height_r));
        btnNewButton_10.setFont(myfont1);
        btnNewButton_10.setForeground(new Color(255,255,255));
        btnNewButton_10.setBackground(new Color(254,178,148));
        btnNewButton_10.setBorder(BorderFactory.createLineBorder(new Color(254,178,148)));
        btnNewButton_10.setFocusPainted(false);
        btnNewButton_10.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                btnNewButton_10.setContentAreaFilled(false);//设置按钮透明
                btnNewButton_10.setBackground(new Color(255,160,122));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                btnNewButton_10.setContentAreaFilled(true);//设置按钮透明
                btnNewButton_10.setBackground(new Color(254,178,148));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                btnNewButton_10.setFont(new Font("楷体",Font.BOLD, (int) (20*width_r)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                btnNewButton_10.setFont(new Font("楷体 ", Font.PLAIN, (int) (18*width_r)));
            }
        });
        btnNewButton_10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Product> t = Client_shop.checktypeProduct("手机");
                    if(t!=null) {
                        String[][] temp = new String[t.size()][];
                        for (int i = 0; i < t.size(); i++) {
                            String[] tt = new String[7];
                            tt[0] = String.valueOf(t.get(i).getProduct_id());
                            tt[1] = t.get(i).getProduct_name();
                            tt[2] = String.valueOf(t.get(i).getProduct_price());
                            tt[3] = String.valueOf(t.get(i).getProduct_currentNumbers());
                            tt[4] = "1";
                            tt[5] = "加入购物车";
                            tt[6] = "购买";
                            temp[i] = tt;
                        }
                        setShoptable(temp);
                    }else {
                        setShoptable(null);
                    }
                    ShoppingHall f11=new ShoppingHall();
                    panel.add(f11,"f11");
                    cardLayout.show(panel, "f11");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }}
        });
        add(btnNewButton_10);

        JButton btnNewButton_11 = new JButton("数码");
        btnNewButton_11.setBounds((int) (120*width_r), (int) (360*height_r), (int) (80*width_r), (int) (30*height_r));
        btnNewButton_11.setFont(myfont1);
        btnNewButton_11.setForeground(new Color(255,255,255));
        btnNewButton_11.setBackground(new Color(254,178,148));
        btnNewButton_11.setBorder(BorderFactory.createLineBorder(new Color(254,178,148)));
        btnNewButton_11.setFocusPainted(false);
        btnNewButton_11.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                btnNewButton_11.setContentAreaFilled(false);//设置按钮透明
                btnNewButton_11.setBackground(new Color(255,160,122));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                btnNewButton_11.setContentAreaFilled(true);//设置按钮透明
                btnNewButton_11.setBackground(new Color(254,178,148));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                btnNewButton_11.setFont(new Font("楷体",Font.BOLD, (int) (20*width_r)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                btnNewButton_11.setFont(new Font("楷体 ", Font.PLAIN, (int) (18*width_r)));
            }
        });
        btnNewButton_11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Product> t = Client_shop.checktypeProduct("数码");
                    if(t!=null) {
                        String[][] temp = new String[t.size()][];
                        for (int i = 0; i < t.size(); i++) {
                            String[] tt = new String[7];
                            tt[0] = String.valueOf(t.get(i).getProduct_id());
                            tt[1] = t.get(i).getProduct_name();
                            tt[2] = String.valueOf(t.get(i).getProduct_price());
                            tt[3] = String.valueOf(t.get(i).getProduct_currentNumbers());
                            tt[4] = "1";
                            tt[5] = "加入购物车";
                            tt[6] = "购买";
                            temp[i] = tt;
                        }
                        setShoptable(temp);
                    }else {
                        setShoptable(null);
                    }
                    ShoppingHall f11=new ShoppingHall();
                    panel.add(f11,"f11");
                    cardLayout.show(panel, "f11");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }}
        });
        add(btnNewButton_11);

        JButton btnNewButton_12 = new JButton("礼品");
        btnNewButton_12.setBounds((int) (225*width_r), (int) (360*height_r), (int) (80*width_r), (int) (30*height_r));
        btnNewButton_12.setFont(myfont1);
        btnNewButton_12.setForeground(new Color(255,255,255));
        btnNewButton_12.setBackground(new Color(254,178,148));
        btnNewButton_12.setBorder(BorderFactory.createLineBorder(new Color(254,178,148)));
        btnNewButton_12.setFocusPainted(false);
        btnNewButton_12.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                btnNewButton_12.setContentAreaFilled(false);//设置按钮透明
                btnNewButton_12.setBackground(new Color(255,160,122));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                btnNewButton_12.setContentAreaFilled(true);//设置按钮透明
                btnNewButton_12.setBackground(new Color(254,178,148));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                btnNewButton_12.setFont(new Font("楷体",Font.BOLD, (int) (20*width_r)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                btnNewButton_12.setFont(new Font("楷体 ", Font.PLAIN, (int) (18*width_r)));
            }
        });
        btnNewButton_12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Product> t = Client_shop.checktypeProduct("礼品");
                    if(t!=null) {
                        String[][] temp = new String[t.size()][];
                        for (int i = 0; i < t.size(); i++) {
                            String[] tt = new String[7];
                            tt[0] = String.valueOf(t.get(i).getProduct_id());
                            tt[1] = t.get(i).getProduct_name();
                            tt[2] = String.valueOf(t.get(i).getProduct_price());
                            tt[3] = String.valueOf(t.get(i).getProduct_currentNumbers());
                            tt[4] = "1";
                            tt[5] = "加入购物车";
                            tt[6] = "购买";
                            temp[i] = tt;
                        }
                        setShoptable(temp);
                    }else {
                        setShoptable(null);
                    }
                    ShoppingHall f11=new ShoppingHall();
                    panel.add(f11,"f11");
                    cardLayout.show(panel, "f11");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }}
        });
        add(btnNewButton_12);

        JButton btnNewButton_13 = new JButton("家装");
        btnNewButton_13.setBounds((int) (20*width_r), (int) (430*height_r), (int) (80*width_r), (int) (30*height_r));
        btnNewButton_13.setFont(myfont1);
        btnNewButton_13.setForeground(new Color(255,255,255));
        btnNewButton_13.setBackground(new Color(254,178,148));
        btnNewButton_13.setBorder(BorderFactory.createLineBorder(new Color(254,178,148)));
        btnNewButton_13.setFocusPainted(false);
        btnNewButton_13.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                btnNewButton_13.setContentAreaFilled(false);//设置按钮透明
                btnNewButton_13.setBackground(new Color(255,160,122));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                btnNewButton_13.setContentAreaFilled(true);//设置按钮透明
                btnNewButton_13.setBackground(new Color(254,178,148));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                btnNewButton_13.setFont(new Font("楷体",Font.BOLD, (int) (20*width_r)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                btnNewButton_13.setFont(new Font("楷体 ", Font.PLAIN, (int) (18*width_r)));
            }
        });
        btnNewButton_13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Product> t = Client_shop.checktypeProduct("家装");
                    if(t!=null) {
                        String[][] temp = new String[t.size()][];
                        for (int i = 0; i < t.size(); i++) {
                            String[] tt = new String[7];
                            tt[0] = String.valueOf(t.get(i).getProduct_id());
                            tt[1] = t.get(i).getProduct_name();
                            tt[2] = String.valueOf(t.get(i).getProduct_price());
                            tt[3] = String.valueOf(t.get(i).getProduct_currentNumbers());
                            tt[4] = "1";
                            tt[5] = "加入购物车";
                            tt[6] = "购买";
                            temp[i] = tt;
                        }
                        setShoptable(temp);
                    }else {
                        setShoptable(null);
                    }
                    ShoppingHall f11=new ShoppingHall();
                    panel.add(f11,"f11");
                    cardLayout.show(panel, "f11");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                } }
        });
        add(btnNewButton_13);

        JButton btnNewButton_14 = new JButton("电器");
        btnNewButton_14.setBounds((int) (120*width_r), (int) (430*height_r), (int) (80*width_r), (int) (30*height_r));
        btnNewButton_14.setFont(myfont1);
        btnNewButton_14.setForeground(new Color(255,255,255));
        btnNewButton_14.setBackground(new Color(254,178,148));
        btnNewButton_14.setBorder(BorderFactory.createLineBorder(new Color(254,178,148)));
        btnNewButton_14.setFocusPainted(false);
        btnNewButton_14.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                btnNewButton_14.setContentAreaFilled(false);//设置按钮透明
                btnNewButton_14.setBackground(new Color(255,160,122));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                btnNewButton_14.setContentAreaFilled(true);//设置按钮透明
                btnNewButton_14.setBackground(new Color(254,178,148));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                btnNewButton_14.setFont(new Font("楷体",Font.BOLD, (int) (20*width_r)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                btnNewButton_14.setFont(new Font("楷体 ", Font.PLAIN, (int) (18*width_r)));
            }
        });
        btnNewButton_14.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Product> t = Client_shop.checktypeProduct("电器");
                    if(t!=null) {
                        String[][] temp = new String[t.size()][];
                        for (int i = 0; i < t.size(); i++) {
                            String[] tt = new String[7];
                            tt[0] = String.valueOf(t.get(i).getProduct_id());
                            tt[1] = t.get(i).getProduct_name();
                            tt[2] = String.valueOf(t.get(i).getProduct_price());
                            tt[3] = String.valueOf(t.get(i).getProduct_currentNumbers());
                            tt[4] = "1";
                            tt[5] = "加入购物车";
                            tt[6] = "购买";
                            temp[i] = tt;
                        }
                        setShoptable(temp);
                    }else {
                        setShoptable(null);
                    }
                    ShoppingHall f11=new ShoppingHall();
                    panel.add(f11,"f11");
                    cardLayout.show(panel, "f11");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }}
        });
        add(btnNewButton_14);

        JButton btnNewButton_15 = new JButton("厨具");
        btnNewButton_15.setBounds((int) (225*width_r), (int) (430*height_r), (int) (80*width_r), (int) (30*height_r));
        btnNewButton_15.setFont(myfont1);
        btnNewButton_15.setForeground(new Color(255,255,255));
        btnNewButton_15.setBackground(new Color(254,178,148));
        btnNewButton_15.setBorder(BorderFactory.createLineBorder(new Color(254,178,148)));
        btnNewButton_15.setFocusPainted(false);
        btnNewButton_15.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                btnNewButton_15.setContentAreaFilled(false);//设置按钮透明
                btnNewButton_15.setBackground(new Color(255,160,122));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                btnNewButton_15.setContentAreaFilled(true);//设置按钮透明
                btnNewButton_15.setBackground(new Color(254,178,148));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                btnNewButton_15.setFont(new Font("楷体",Font.BOLD, (int) (20*width_r)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                btnNewButton_15.setFont(new Font("楷体 ", Font.PLAIN, (int) (18*width_r)));
            }
        });
        btnNewButton_15.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Product> t = Client_shop.checktypeProduct("厨具");
                    if(t!=null) {
                        String[][] temp = new String[t.size()][];
                        for (int i = 0; i < t.size(); i++) {
                            String[] tt = new String[7];
                            tt[0] = String.valueOf(t.get(i).getProduct_id());
                            tt[1] = t.get(i).getProduct_name();
                            tt[2] = String.valueOf(t.get(i).getProduct_price());
                            tt[3] = String.valueOf(t.get(i).getProduct_currentNumbers());
                            tt[4] = "1";
                            tt[5] = "加入购物车";
                            tt[6] = "购买";
                            temp[i] = tt;
                        }
                        setShoptable(temp);
                    }else {
                        setShoptable(null);
                    }
                    ShoppingHall f11=new ShoppingHall();
                    panel.add(f11,"f11");
                    cardLayout.show(panel, "f11");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                } }
        });
        add(btnNewButton_15);

        JButton btnNewButton_16 = new JButton("医药");
        btnNewButton_16.setBounds((int) (20*width_r), (int) (500*height_r), (int) (80*width_r), (int) (30*height_r));
        btnNewButton_16.setFont(myfont1);
        btnNewButton_16.setForeground(new Color(255,255,255));
        btnNewButton_16.setBackground(new Color(254,178,148));
        btnNewButton_16.setBorder(BorderFactory.createLineBorder(new Color(254,178,148)));
        btnNewButton_16.setFocusPainted(false);
        btnNewButton_16.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                btnNewButton_16.setContentAreaFilled(false);//设置按钮透明
                btnNewButton_16.setBackground(new Color(255,160,122));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                btnNewButton_16.setContentAreaFilled(true);//设置按钮透明
                btnNewButton_16.setBackground(new Color(254,178,148));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                btnNewButton_16.setFont(new Font("楷体",Font.BOLD, (int) (20*width_r)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                btnNewButton_16.setFont(new Font("楷体 ", Font.PLAIN, (int) (18*width_r)));
            }
        });
        btnNewButton_16.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Product> t = Client_shop.checktypeProduct("医药");
                    if(t!=null) {
                        String[][] temp = new String[t.size()][];
                        for (int i = 0; i < t.size(); i++) {
                            String[] tt = new String[7];
                            tt[0] = String.valueOf(t.get(i).getProduct_id());
                            tt[1] = t.get(i).getProduct_name();
                            tt[2] = String.valueOf(t.get(i).getProduct_price());
                            tt[3] = String.valueOf(t.get(i).getProduct_currentNumbers());
                            tt[4] = "1";
                            tt[5] = "加入购物车";
                            tt[6] = "购买";
                            temp[i] = tt;
                        }
                        setShoptable(temp);
                    }else {
                        setShoptable(null);
                    }
                    ShoppingHall f11=new ShoppingHall();
                    panel.add(f11,"f11");
                    cardLayout.show(panel, "f11");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                } }
        });
        add(btnNewButton_16);

        JButton btnNewButton_17 = new JButton("保健");
        btnNewButton_17.setBounds((int) (120*width_r), (int) (500*height_r), (int) (80*width_r), (int) (30*height_r));
        btnNewButton_17.setFont(myfont1);
        btnNewButton_17.setForeground(new Color(255,255,255));
        btnNewButton_17.setBackground(new Color(254,178,148));
        btnNewButton_17.setBorder(BorderFactory.createLineBorder(new Color(254,178,148)));
        btnNewButton_17.setFocusPainted(false);
        btnNewButton_17.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                btnNewButton_17.setContentAreaFilled(false);//设置按钮透明
                btnNewButton_17.setBackground(new Color(255,160,122));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                btnNewButton_17.setContentAreaFilled(true);//设置按钮透明
                btnNewButton_17.setBackground(new Color(254,178,148));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                btnNewButton_17.setFont(new Font("楷体",Font.BOLD, (int) (20*width_r)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                btnNewButton_17.setFont(new Font("楷体 ", Font.PLAIN, (int) (18*width_r)));
            }
        });
        btnNewButton_17.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Product> t = Client_shop.checktypeProduct("保健");
                    if(t!=null) {
                        String[][] temp = new String[t.size()][];
                        for (int i = 0; i < t.size(); i++) {
                            String[] tt = new String[7];
                            tt[0] = String.valueOf(t.get(i).getProduct_id());
                            tt[1] = t.get(i).getProduct_name();
                            tt[2] = String.valueOf(t.get(i).getProduct_price());
                            tt[3] = String.valueOf(t.get(i).getProduct_currentNumbers());
                            tt[4] = "1";
                            tt[5] = "加入购物车";
                            tt[6] = "购买";
                            temp[i] = tt;
                        }
                        setShoptable(temp);
                    }else {
                        setShoptable(null);
                    }
                    ShoppingHall f11=new ShoppingHall();
                    panel.add(f11,"f11");
                    cardLayout.show(panel, "f11");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                } }
        });
        add(btnNewButton_17);

        JButton btnNewButton_18 = new JButton("生鲜");
        btnNewButton_18.setBounds((int) (225*width_r), (int) (500*height_r), (int) (80*width_r), (int) (30*height_r));
        btnNewButton_18.setFont(myfont1);
        btnNewButton_18.setForeground(new Color(255,255,255));
        btnNewButton_18.setBackground(new Color(254,178,148));
        btnNewButton_18.setBorder(BorderFactory.createLineBorder(new Color(254,178,148)));
        btnNewButton_18.setFocusPainted(false);
        btnNewButton_18.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                btnNewButton_18.setContentAreaFilled(false);//设置按钮透明
                btnNewButton_18.setBackground(new Color(255,160,122));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                btnNewButton_18.setContentAreaFilled(true);//设置按钮透明
                btnNewButton_18.setBackground(new Color(254,178,148));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                btnNewButton_18.setFont(new Font("楷体",Font.BOLD, (int) (20*width_r)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                btnNewButton_18.setFont(new Font("楷体 ", Font.PLAIN, (int) (18*width_r)));
            }
        });
        btnNewButton_18.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Product> t = Client_shop.checktypeProduct("生鲜");
                    if(t!=null) {
                        String[][] temp = new String[t.size()][];
                        for (int i = 0; i < t.size(); i++) {
                            String[] tt = new String[7];
                            tt[0] = String.valueOf(t.get(i).getProduct_id());
                            tt[1] = t.get(i).getProduct_name();
                            tt[2] = String.valueOf(t.get(i).getProduct_price());
                            tt[3] = String.valueOf(t.get(i).getProduct_currentNumbers());
                            tt[4] = "1";
                            tt[5] = "加入购物车";
                            tt[6] = "购买";
                            temp[i] = tt;
                        }
                        setShoptable(temp);
                    }else {
                        setShoptable(null);
                    }
                    ShoppingHall f11=new ShoppingHall();
                    panel.add(f11,"f11");
                    cardLayout.show(panel, "f11");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                } }
        });
        add(btnNewButton_18);

        //左侧面板
        JPanel p1=new JPanel();
        p1 = new JPanel();
        p1.setBounds(0, 0, (int) (340*width_r), (int) (780*height_r));
        //p1.setBackground(null);
        p1.setBackground(new Color(255,160,122, 200));
        //panel1.BackColor = Color.FromArgb(80, 255, 0, 0);
        //p1.setOpaque(false);
        add(p1);


        String[] tableTitle = {"商品编号","商品名称","价格","剩余数量","购买数量","加入购物车","购买"};
        //数据

//        //将图片Icon对象放入表格数据数组
//        Object[][] data = new Object[][] {
//                {new ImageIcon("E://Vcampus//src//image//1.jpg"), "Text 2","111","222"}
//        };

        TableModel dtm = new DefaultTableModel(shoptable, tableTitle);
//            @Override   //核心步骤：重写getColumnClass方法
//            public Class<?> getColumnClass(int columnIndex) {
//                return getValueAt(0, columnIndex).getClass();
//                //return (columnIndex == 0) ? Icon.class : Object.class;
//            }
//        };
        JTable table_want = new JTable(dtm){
            public boolean isCellEditable(int row, int column) {
                if(column==4)
                {
                    return true;
                }
                else {
                    return false;
                }
            }
        };

        //将表格数据数组放入表格模型,并重写getColumnClass方法
        table_want.setModel(dtm);

        //调整美化
        table_want.setFont(new Font("宋体",Font.BOLD, (int) (16*width_r)));

        try {
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    if (column!=5&&column!=6) {
                        setBackground(Color.white);
                    }else {
                        setBackground(new Color(250,128,114,100));
                        //setForeground(new Color(255,255,255));
                        //setFont(new Font("微软雅黑",Font.BOLD,18));
                    }
                    return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                }
            };
            //居中
            tcr.setHorizontalAlignment(JLabel.CENTER);
            table_want.setDefaultRenderer(Object.class, tcr);
            for (int i = 0; i < table_want.getColumnCount(); i++)
            {
                table_want.getColumn(table_want.getColumnName(i)).setCellRenderer(tcr);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

            table_want.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (table_want.getSelectedColumn() == 5) {
                        //购物车
                        int id= Integer.parseInt((String) table_want.getValueAt(table_want.getSelectedRow(),0));
                        int Num = Integer.parseInt((String) table_want.getValueAt(table_want.getSelectedRow(),4));
                        try {
                            Client_shop.addToShopCar(myInfo.getId(),id,Num);
                            JOptionPane.showMessageDialog(null,"添加购物车成功！");
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    if (table_want.getSelectedColumn() == 6) {

                        String id= (String) table_want.getValueAt(table_want.getSelectedRow(),0);
                        double money= Double.parseDouble((String) table_want.getValueAt(table_want.getSelectedRow(),2));
                        int Num = Integer.parseInt((String) table_want.getValueAt(table_want.getSelectedRow(),4));
                        try {
                            double temp1;
                            if(myInfo.getType()==1){
                                temp1=Client_shop.getMoney(myInfo.getId());
                            }
                            else{
                                temp1=Client_shop.getMoney_Teacher(myInfo.getId());
                            }
                            if(temp1>=(money*Num)){
                                if(myInfo.getType()==1){
                                    if(Client_shop.buyProduct(myInfo.getId(),id,Num,Client_shop.getMoney(myInfo.getId())-money*Num))
                                        JOptionPane.showMessageDialog(null,"购买成功！");
                                }
                                else {
                                    if(Client_shop.buyProduct_Teacher(myInfo.getId(),id,Num,Client_shop.getMoney_Teacher(myInfo.getId())-money*Num))
                                        JOptionPane.showMessageDialog(null,"购买成功！");
                                }
                                List<Product> t = Client_shop.returnAllProduct();
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

                                Client_shop.setId(String.valueOf(myInfo.getType()));
                                Client_shop.setIdcard(myInfo.getId());
                                functionChoose.jf.setContentPane(new shopCustomer());
                                ShoppingHall f11=new ShoppingHall();
                                panel.add(f11,"f11");
                                cardLayout.show(panel,"f11");
                                functionChoose.jf.setTitle("shopCustomer");
                            }else {
                                JOptionPane.showMessageDialog(null,"余额不足！");
                            }
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }

            @Override
            public void mousePressed(MouseEvent e) {


            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

                }
            });
            //支持滚动
            JScrollPane jsp = new JScrollPane(table_want);
            jsp.setBounds((int) (340*width_r), (int) (100*height_r), (int) (940*width_r), (int) (460*height_r));
            add(jsp);
            table_want.setRowHeight(30);

        setVisible(true);
        }
}

