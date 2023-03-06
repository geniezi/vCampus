package UIviewer.SelectCourse;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * 我学生
 *
 * @author Chen_GuanZhi
 * @date 2022/09/03
 */
public class My_students extends JPanel{
    Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
    int width=(int ) screensize.getWidth(); //得到宽度
    int height=(int ) screensize.getHeight();//获得高度
    double width_r=(double)(width)/1273;
    double height_r=(double)(height)/790;
    public static volatile String[][] students=null;

    /**
     * 我学生
     */
    public My_students() {

        setLayout(null);
        String[] tableTitle2={"学生学号","学生姓名"};
        DefaultTableModel dtm2=new DefaultTableModel(students,tableTitle2);
        JTable table_want2=new JTable(dtm2);
        JScrollPane jsp2=new JScrollPane(table_want2){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jsp2.setBounds(0,(int)(260*height_r),(int)(1280*width_r),(int)(280*height_r));
        add(jsp2);
        table_want2.setRowHeight(70);

    }
}
