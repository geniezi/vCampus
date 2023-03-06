package UIviewer.Shopping;
import ClientToServer.myInfo;
import DAO.Library.Book_borrower;
import DAO.Shop.Product;
import DAO.Shop.ProductPair;
import UIhandler.Library.Client_library;
import UIhandler.Shop.Client_shop;
import UIviewer.login.functionChoose;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static UIviewer.Shopping.ShoppingHall.resetshoptable;
import static UIviewer.Shopping.shopCustomer.cardLayout;
import static UIviewer.Shopping.shopCustomer.panel;

/**
 * 汽车商店
 *
 * @author Chen_GuanZhi
 * @date 2022/09/03
 */
public class shopCar extends JPanel {
    Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
    int width=(int ) screensize.getWidth(); //得到宽度
    int height=(int ) screensize.getHeight();//获得高度
    double width_r=(double)(width)/1273;
    double height_r=(double)(height)/784;
    public static volatile String[][] myBook;

    /**
     * 得到我书
     *
     * @return {@link String[][]}
     */
    public static String[][] getMyBook() {
        return myBook;
    }

    /**
     * 设置我书
     *
     * @param myBook 我书
     */
    public static void setMyBook(String[][] myBook) {
        shopCar.myBook = myBook;
    }

    /**
     * 汽车商店
     */
    public shopCar(){
        setLayout(null);
        String[] tableTitle = {"商品编号","商品名称","商品购买数量","商品价格","购买","删除"};
        //数据
        DefaultTableModel dtm = new DefaultTableModel(myBook, tableTitle);
        JTable table_want = new JTable(dtm){
            public boolean isCellEditable(int row, int column) {
                if(column==2) {
                    return true;
                }else {return false;}
            }
        };
        table_want.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(table_want.getSelectedColumn()==4){
                    //购买功能
                    String id= (String) table_want.getValueAt(table_want.getSelectedRow(),0);
                    int Num = Integer.parseInt((String) table_want.getValueAt(table_want.getSelectedRow(),2));
                    double money= Double.parseDouble((String) table_want.getValueAt(table_want.getSelectedRow(),3));
                    try {
                        double ttt ;
                        if(myInfo.getType()==1){
                            ttt = Client_shop.getMoney(myInfo.getId());
                        }else {
                            ttt = Client_shop.getMoney_Teacher(myInfo.getId());
                        }
                        if(ttt>=(money)) {
                            if (myInfo.getType() == 1) {
                                if (Client_shop.buyProduct(myInfo.getId(), id, Num, Client_shop.getMoney(myInfo.getId()) - money)) {
                                    Client_shop.deleteReadyToBuy(myInfo.getId(), Integer.parseInt(((String) table_want.getValueAt(table_want.getSelectedRow(), 0))), 0);
                                    JOptionPane.showMessageDialog(null, "购买成功！");

                                    resetshoptable();
                                    java.util.List<ProductPair> p = Client_shop.checkReadyToBuy(myInfo.getId());
                                    HashMap<Integer, Integer> all = new HashMap<>();
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
                                                String[] tt = new String[6];
                                                tt[0] = String.valueOf(book.get(i).getProduct_id());
                                                tt[1] = book.get(i).getProduct_name();
                                                tt[2] = String.valueOf(all.get(book.get(i).getProduct_id()));  //数量
                                                tt[3] = String.valueOf(book.get(i).getProduct_price() * all.get(book.get(i).getProduct_id()));
                                                tt[4] = "购买";
                                                tt[5] = "删除";
                                                temp[i] = tt;
                                            }
                                            shopCar.setMyBook(temp);
                                         //   System.out.println(temp.length + "  11111");
                                        }
                                    } else {
                                        shopCar.setMyBook(null);
                                    //    System.out.println("空");
                                    }

                                }

                            } else {
                            if (Client_shop.buyProduct_Teacher(myInfo.getId(), id, Num, Client_shop.getMoney_Teacher(myInfo.getId()) - money)) {
                                Client_shop.deleteReadyToBuy(myInfo.getId(), Integer.parseInt(((String) table_want.getValueAt(table_want.getSelectedRow(), 0))), 0);
                                JOptionPane.showMessageDialog(null, "购买成功！");

                                resetshoptable();
                                java.util.List<ProductPair> p = Client_shop.checkReadyToBuy(myInfo.getId());
                                HashMap<Integer, Integer> all = new HashMap<>();
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
                                            String[] tt = new String[6];
                                            tt[0] = String.valueOf(book.get(i).getProduct_id());
                                            tt[1] = book.get(i).getProduct_name();
                                            tt[2] = String.valueOf(all.get(book.get(i).getProduct_id()));  //数量
                                            tt[3] = String.valueOf(book.get(i).getProduct_price() * all.get(book.get(i).getProduct_id()));
                                            tt[4] = "购买";
                                            tt[5] = "删除";
                                            temp[i] = tt;
                                        }
                                        shopCar.setMyBook(temp);
                                     //   System.out.println(temp.length + "  11111");
                                    }
                                } else {
                                    shopCar.setMyBook(null);
                                //    System.out.println("空");
                                }

                            }
                        }
                            Client_shop.setId(String.valueOf(myInfo.getType()));
                            Client_shop.setIdcard(myInfo.getId());
                            functionChoose.jf.setContentPane(new shopCustomer());
                            shopCar f12=new shopCar();
                            panel.add(f12,"f12");
                            cardLayout.show(panel, "f12");
                        }else {
                            JOptionPane.showMessageDialog(null,"余额不足！");
                        }
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }

                if(table_want.getSelectedColumn()==5){
                    //删除功能
                    int id = Integer.parseInt(((String) table_want.getValueAt(table_want.getSelectedRow(),0)));
                    try {
                        Client_shop.deleteReadyToBuy(myInfo.getId(),id,0);
                        JOptionPane.showMessageDialog(null,"删除成功！");

                        resetshoptable();
                        java.util.List<ProductPair> p = Client_shop.checkReadyToBuy(myInfo.getId());
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
                              //  System.out.println(temp.length+"  11111");
                            }
                        }
                        else {
                            shopCar.setMyBook(null);
                            //System.out.println("空");
                        }

                        shopCar f12=new shopCar();
                        panel.add(f12,"f12");
                        cardLayout.show(panel, "f12");
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
        jsp.setBounds(0,0, (int) (1280*width_r), (int) (680*height_r));
        add(jsp);
        table_want.setRowHeight((int) (30*height_r));

        //调整美化
        table_want.setFont(new Font("宋体",Font.BOLD, (int) (16*width_r)));
        try {
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    if (column!=5&&column!=4) {
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
    }
}