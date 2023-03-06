package UIviewer.Library;
import DAO.Library.Book_borrower;
import DAO.Library.Punishment;
import UIhandler.Library.Client_library;
import net.coobird.thumbnailator.Thumbnails;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

/**
 * 申请机票
 *
 * @author Chen_GuanZhi
 * @date 2022/09/03
 */
public class applyTicket extends JPanel {
    public static volatile String[][] myPunish=null;
    Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
    int width=(int ) screensize.getWidth(); //得到宽度
    int height=(int ) screensize.getHeight();//获得高度
    double width_r=(double)(width)/1273;
    double height_r=(double)(height)/784;

    /**
     * 申请机票
     */
    public applyTicket(){
        setLayout(null);
        String[] tableTitle = {"罚单编号","罚单金额","书籍编号","罚单备注","缴费"};
        //数据
        DefaultTableModel dtm = new DefaultTableModel(myPunish, tableTitle);
        JTable table_want = new JTable(dtm)
        {
            public boolean isCellEditable(int row, int column) {
                return false;}
        };
        table_want.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(table_want.getSelectedColumn()==4){
                    Punishment punishment=new Punishment();
                    try {
                        String id= (String) table_want.getValueAt(table_want.getSelectedRow(),0);
                        double p= Double.parseDouble((String)table_want.getValueAt(table_want.getSelectedRow(),1));
                        punishment.setCustomer_iD(Client_library.getId());
                        punishment.setPunishmentID(id);
                        punishment.setPrice(p);
                   //     System.out.println(id);
                        Client_library.reqirePay(punishment);
                    } catch (IOException ex) {
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


        JPanel p11=new JPanel();
        p11.setBounds(0,0, (int) (1280*width_r), (int) (650*height_r));
        JLabel pic1 = new JLabel();
        ImageIcon icon1 = new ImageIcon("src/image/main4.jpg");
        int icon1_width= 1300;
        int icon1_height=650;
        try {
            Thumbnails.of(new File("src/image/main4.jpg"))
                    .size((int)(icon1_width*width_r), (int)(icon1_height*height_r))
                    .toFile(new File("src/image/main4_min.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        pic1.setIcon(new ImageIcon("src/image/main4_min.jpg"));
        pic1.setBounds(0,0 , (int) (1300*width_r), (int) (650*height_r));
        p11.add(pic1);
        add(p11);

        //调整美化
        table_want.setFont(new Font("宋体",Font.BOLD, (int) (16*width_r)));
        try {
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    if (column!=4) {
                        setBackground(Color.white);
                    }else {
                        setBackground(new Color(60,179,113));
                        //setForeground(new Color(255,255,255));
                        //setFont(new Font("微软雅黑",Font.BOLD,18));
                    }
                    return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                }
            };

            for (int i = 0; i < table_want.getColumnCount(); i++)
            {
                table_want.getColumn(table_want.getColumnName(i)).setCellRenderer(tcr);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}