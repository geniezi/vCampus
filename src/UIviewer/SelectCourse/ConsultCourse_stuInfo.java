package UIviewer.SelectCourse;

import DAO.Curriculum.Course;
import UIhandler.Currirulum.Client_curriculum;

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
 * @author Chen_GuanZhi
 * @date 2022/09/03
 */
public class ConsultCourse_stuInfo extends JPanel {
    Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
    int width=(int ) screensize.getWidth(); //得到宽度
    int height=(int ) screensize.getHeight();//获得高度
    double width_r=(double)(width)/1273;
    double height_r=(double)(height)/790;
    public static volatile String[][] students=null;
    public static volatile String[][] consult_stu=null;

    /**
     * 咨询课程斯图信息
     */
    public ConsultCourse_stuInfo()
    {
        setLayout(null);

        String[] tableTitle={"课程编号","课程名","课程容量","上课教室","上课时间",""};
        DefaultTableModel dtm=new DefaultTableModel(consult_stu,tableTitle);
        JTable table_want=new JTable(dtm){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JScrollPane jsp=new JScrollPane(table_want);
        jsp.setBounds(0,0,(int)(1280*width_r),(int)(300*height_r));
        add(jsp);
        table_want.setRowHeight(40);
        table_want.getColumnModel().getColumn(0).setPreferredWidth(100);
        table_want.getColumnModel().getColumn(1).setPreferredWidth(150);
        table_want.getColumnModel().getColumn(2).setPreferredWidth(60);
        table_want.getColumnModel().getColumn(3).setPreferredWidth(150);
        table_want.getColumnModel().getColumn(4).setPreferredWidth(450);
        table_want.getColumnModel().getColumn(5).setPreferredWidth(120);
        try {
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    if (column!=5) {
                        setBackground(Color.white);
                    }else {
                        setBackground(new Color(54, 124, 255,100));
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
                if(table_want.getSelectedColumn()==5){
                    try{
                        System.out.println("Clicked");
                        String id=(String)table_want.getValueAt(table_want.getSelectedRow(),0);
                        Client_curriculum.Require_show_my_students(id);
                    }catch(IOException ex)
                    {
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

        String[] tableTitle2={"学生学号","学生姓名"};
        DefaultTableModel dtm2=new DefaultTableModel(students,tableTitle2);
        JTable table_want2=new JTable(dtm2){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JScrollPane jsp2=new JScrollPane(table_want2){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jsp2.setBounds(0,(int)(320*height_r),(int)(1280*width_r),(int)(280*height_r));
        add(jsp2);
        table_want2.setRowHeight(50);
        table_want.setFont(new Font("微软雅黑",Font.BOLD, (int) (14*width_r)));
        table_want2.setFont(new Font("楷体",Font.BOLD, (int) (14*width_r)));
    }

}
