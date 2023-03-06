package UIviewer.SelectCourse;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Robot;
import java.util.Date;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import DAO.Curriculum.Course;
import UIhandler.Currirulum.Client_curriculum;
import java.util.Timer;

/**
 * 选择课程
 *
 * @author Chen_GuanZhi
 * @date 2022/09/03
 */
public class Choosing_Course extends JPanel {

        Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
        int width=(int ) screensize.getWidth(); //得到宽度
        int height=(int ) screensize.getHeight();//获得高度
        double width_r=(double)(width)/1273;
        double height_r=(double)(height)/790;
        public static  volatile String [][] selectcourse=null;

        /**
         * 选择课程
         */
        public Choosing_Course()
        {
        //小头像
            setLayout(null);

        String[] tableTitle={"课程编号","课程名","时间","任课老师","课容量","地点","选择"};
        DefaultTableModel dtm=new DefaultTableModel(selectcourse,tableTitle);
        JTable table_want=new JTable(dtm){
                public boolean isCellEditable(int row, int column) {
                        return false;
                }
        };
                table_want.setFont(new Font("宋体",Font.BOLD,16));

                table_want.getColumnModel().getColumn(0).setPreferredWidth(70);
                table_want.getColumnModel().getColumn(1).setPreferredWidth(180);
                table_want.getColumnModel().getColumn(2).setPreferredWidth(450);
                table_want.getColumnModel().getColumn(3).setPreferredWidth(100);
                table_want.getColumnModel().getColumn(4).setPreferredWidth(50);
                table_want.getColumnModel().getColumn(5).setPreferredWidth(150);
                table_want.getColumnModel().getColumn(5).setPreferredWidth(80);

                //支持滚动
        JScrollPane jsp=new JScrollPane(table_want);
        jsp.setBounds(0,0,(int)(1280*width_r),(int)(590*height_r));

        add(jsp);

        //调整美化
                try {
                        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
                                @Override
                                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                                        if (column!=6) {
                                                setBackground(Color.white);
                                        }else {
                                                setBackground(new Color(43, 88, 255));
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

        table_want.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                        if(table_want.getSelectedColumn()==6){
                                Course choose=new Course();
                                try
                                {
                                    String id=(String)table_want.getValueAt(table_want.getSelectedRow(),0);
                                    choose.setId(id);
                                    choose.setSize(Integer.valueOf((String)table_want.getValueAt(table_want.getSelectedRow(),4)));
                                    choose.setTimestring((String)table_want.getValueAt(table_want.getSelectedRow(),2));
                                    Client_curriculum.requireToChoose(choose);
                                    Client_curriculum.Require_suitable();
                                }catch(IOException ex){
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
        table_want.setRowHeight(50);
                JScrollBar scrollBar = new JScrollBar(JScrollBar.VERTICAL) {
                        @Override
                        public boolean isVisible() {
                                return true;
                        }
                };
                jsp.setVerticalScrollBar(scrollBar);
                jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
                scrollBar.setUnitIncrement(30);

    }

}
