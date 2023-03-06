package UIviewer.SelectCourse;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 检查coustatus
 *
 * @author Chen_GuanZhi
 * @date 2022/09/03
 */
public class Check_Coustatus extends JPanel{
    Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
    int width=(int ) screensize.getWidth(); //得到宽度
    int height=(int ) screensize.getHeight();//获得高度
    double width_r=(double)(width)/1273;
    double height_r=(double)(height)/790;
    public static volatile String[][] checkcourse_status=null;

    /**
     * 检查coustatus
     */
    public Check_Coustatus()
    {
        setLayout(null);
        String[] tableTitle={"申报课程编号","课程名","课程学时","状态","审核信息"};
        DefaultTableModel dtm=new DefaultTableModel(checkcourse_status,tableTitle);
        JTable table_want=new JTable(dtm){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table_want.setFont(new Font("楷体",Font.BOLD, (int) (18*width_r)));
        JScrollPane jsp=new JScrollPane(table_want);
        jsp.setBounds(0,0,(int)(1280*width_r),(int)(520*height_r));
        add(jsp);
        table_want.setRowHeight(40);


    }
}
