package UIviewer.Library;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import java.awt.Robot;
import java.util.Date;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import UIhandler.Library.Client_library;

/**
 * 所有书
 *
 * @author Chen_GuanZhi
 * @date 2022/09/03
 */
public class AllBooks extends JPanel {
    public static volatile String[][] tableDate=null;
    Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
    int width=(int ) screensize.getWidth(); //得到宽度
    int height=(int ) screensize.getHeight();//获得高度
    double width_r=(double)(width)/1273;
    double height_r=(double)(height)/784;

    /**
     * 所有书
     */
    public AllBooks(){
     setLayout(null);
        String[] tableTitle = {"书籍编号","书名", "作者","出版社","国家","价格", "是否可借","借出日期","借书人","归还日期","馆藏地"};
        //数据
        DefaultTableModel dtm = new DefaultTableModel(tableDate, tableTitle);
        JTable table_want = new JTable(dtm)
        {
         public boolean isCellEditable(int row, int column) {
          return false;
         }
        };
        //table_want.setOpaque(false);
        table_want.setRowHeight((int) (30*height_r));
        Font myfont1 = new Font("宋体", Font.PLAIN, (int) (14*width_r));
        table_want.setFont(myfont1);
        //table_want.setForeground(new Color(255,255,255));

        //支持滚动
        JScrollPane jsp = new JScrollPane(table_want);
        //jsp.setViewportView(table_want);
        //jsp.getViewport().setOpaque(false);//将JScrollPane设置为透明
        //jsp.setOpaque(false);//将中间的viewport设置为透明
        jsp.setBounds(0,0, (int) (1273*width_r), (int) (620*height_r));
        jsp.setBackground(new Color(255, 240, 245, 80));
        add(jsp);

        JScrollBar scrollBar = new JScrollBar(JScrollBar.VERTICAL) {
            @Override
            public boolean isVisible() {
                return true;
            }
        };
        jsp.setVerticalScrollBar(scrollBar);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollBar.setUnitIncrement(30);

        //左侧面板
        JPanel p1 = new JPanel();
        p1.setBounds(0, 0, (int) (1280*width_r), (int) (650*height_r));
        //p1.setBackground(null);
        p1.setBackground(new Color(255, 240, 245, 10));
        //panel1.BackColor = Color.FromArgb(80, 255, 0, 0);
        //p1.setOpaque(false);
        add(p1);



     DefaultTableCellRenderer render = new DefaultTableCellRenderer();
     render.setOpaque(false); //将渲染器设置为透明
     //将这个渲染器设置到fileTable里。这个设置在没有另外专门对column设置的情况下有效
     //若你对某个column特殊指定了渲染器，则对于这个column，它将不调用render渲染器
     //因此为了保证透明，如果你对column额外指定了渲染器，那么在额外的渲染器里也应该设置透明
     table_want.setDefaultRenderer(Object.class,render);
    }
}