package UIviewer.Shopping;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import java.util.Date;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import UIhandler.Library.Client_library;

/**
 * 所有商品
 *
 * @author Chen_GuanZhi
 * @date 2022/09/03
 */
public class AllGoods extends JPanel {
    public static String[][] tableDate;

    /**
     * 得到表日期
     *
     * @return {@link String[][]}
     */
    public static String[][] getTableDate() {
        return tableDate;
    }

    /**
     * 设置表日期
     *
     * @param tableDate 表日期
     */
    public static void setTableDate(String[][] tableDate) {
        AllGoods.tableDate = tableDate;
    }

    Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
    int width=(int ) screensize.getWidth(); //得到宽度
    int height=(int ) screensize.getHeight();//获得高度
    double width_r=(double)(width)/1273;
    double height_r=(double)(height)/784;

    /**
     * 所有商品
     */
    public AllGoods(){
        setLayout(null);
        String[] tableTitle = {"商品编号","商品名称","商品价格","商品剩余数量","商品种类"};
        //数据
        DefaultTableModel dtm = new DefaultTableModel(tableDate, tableTitle);
        //System.out.println("tableDate.length="+tableDate.length);
        JTable table_want = new JTable(dtm)
        {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table_want.setRowHeight((int) (30*height_r));
        Font myfont1 = new Font("宋体", Font.PLAIN, (int) (14*width_r));
        table_want.setFont(myfont1);

        //支持滚动
        JScrollPane jsp = new JScrollPane();
        jsp.setViewportView(table_want);
        jsp.setBounds(0,0, (int) (1280*width_r), (int) (620*height_r));
        jsp.setBackground(new Color(255, 240, 245, 80));
        add(jsp);

        JPanel p1 = new JPanel();
        p1.setBounds(0, 0, (int) (1280*width_r), (int) (650*height_r));
        p1.setBackground(new Color(255, 240, 245, 10));
        add(p1);

        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setOpaque(false); //将渲染器设置为透明
        table_want.setDefaultRenderer(Object.class,render);

        table_want.setFont(new Font("宋体",Font.BOLD, (int) (16*width_r)));
        try {
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

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