package UIviewer.SelectCourse;
import DAO.Curriculum.Course;
import DAO.Library.Book_borrower;
import UIhandler.Currirulum.Client_curriculum;
import UIhandler.Library.Client_library;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

/**
 * 检查过程
 *
 * @author 28468
 * @date 2022/09/03
 */
public class Check_Course extends JPanel{
    /**
     * checkcourse
     */
    public static volatile String[][] checkcourse=null;
    /**
     * 拉
     */
    Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
    /**
     * 宽度
     */
    int width=(int ) screensize.getWidth(); //得到宽度
    /**
     * 高度
     */
    int height=(int ) screensize.getHeight();//获得高度
    /**
     * 宽度r
     */
    double width_r=(double)(width)/1273;
    /**
     * 高r
     */
    double height_r=(double)(height)/790;

    /**
     * 检查过程
     */
    public Check_Course()
    {
        setLayout(null);
        String[] tableTitle={"课程编号","课程名","任课老师","课程学分","课程学时","通过","退回"};
        DefaultTableModel dtm=new DefaultTableModel(checkcourse,tableTitle);
        JTable table_want=new JTable(dtm)
        {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table_want.setFont(new Font("宋体",Font.BOLD,20));
        table_want.getColumnModel().getColumn(0).setPreferredWidth(150);
        table_want.getColumnModel().getColumn(1).setPreferredWidth(190);
        table_want.getColumnModel().getColumn(2).setPreferredWidth(160);
        table_want.getColumnModel().getColumn(3).setPreferredWidth(170);
        table_want.getColumnModel().getColumn(4).setPreferredWidth(170);
        table_want.getColumnModel().getColumn(5).setPreferredWidth(150);
        table_want.getColumnModel().getColumn(6).setPreferredWidth(150);
        table_want.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(table_want.getSelectedColumn()==5){
                    Course addCourse=new Course();
                    addCourse.setId((String) table_want.getValueAt(table_want.getSelectedRow(),0));
                    addCourse.setName((String) table_want.getValueAt(table_want.getSelectedRow(),1));
                    addCourse.setTeacher((String) table_want.getValueAt(table_want.getSelectedRow(),2));
                    addCourse.setPoint(Double.valueOf((String) table_want.getValueAt(table_want.getSelectedRow(),3)));
                    addCourse.setHour(Integer.valueOf((String) table_want.getValueAt(table_want.getSelectedRow(),4)));
                    try {
                        Client_curriculum.Require_AgreeAddCourse(addCourse);
                        Client_curriculum.Require_all_application();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                if(table_want.getSelectedColumn()==6){
                   String reason=JOptionPane.showInputDialog(null,"请输入拒绝原因:");
                    try {
                        Client_curriculum.Require_RefuseAddCourse((String) table_want.getValueAt(table_want.getSelectedRow(),0),reason);
                        Client_curriculum.Require_all_application();
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
                try {
                    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
                        @Override
                        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                            if (column!=8&&column!=7) {
                                setBackground(Color.white);
                            }else {
                                setBackground(new Color(255,255,255));
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

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        table_want.setRowHeight(50);
        //支持滚动
        JScrollPane jsp=new JScrollPane(table_want);
        jsp.setBounds(0,0,(int)(1280*width_r),(int)(580*height_r));
        add(jsp);

        try {
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    if (column!=5&&column!=6) {
                        setBackground(Color.white);
                    }else if(column==5)
                    {
                        setBackground(new Color(60,179,113));
                    }
                    else {
                        setBackground(new Color(164, 1, 1));
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
