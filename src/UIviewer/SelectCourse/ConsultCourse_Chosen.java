package UIviewer.SelectCourse;

import UIhandler.Currirulum.Client_curriculum;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

/**
 * 咨询课程选择
 *
 * @author Chen_GuanZhi
 * @date 2022/09/03
 */
public class ConsultCourse_Chosen extends JPanel {
    Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
    int width=(int ) screensize.getWidth(); //得到宽度
    int height=(int ) screensize.getHeight();//获得高度
    double width_r=(double)(width)/1273;
    double height_r=(double)(height)/790;
    public static volatile String[][] consultCourse_chosen=null;

    /**
     * 咨询课程选择
     */
    public ConsultCourse_Chosen()
    {
        setLayout(null);
        JPanel p11=new JPanel();
        p11.setBounds(0,0,(int)(1273*width_r),(int)(790*height_r));


        String[] tableTitle={"课程编号","课程名","时间","学分","任课老师","地点"};
        DefaultTableModel dtm=new DefaultTableModel(consultCourse_chosen,tableTitle);
        JTable table_want=new JTable(dtm){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table_want.setFont(new Font("宋体",Font.BOLD,20));
        table_want.getColumnModel().getColumn(0).setPreferredWidth(100);
        table_want.getColumnModel().getColumn(1).setPreferredWidth(250);
        table_want.getColumnModel().getColumn(2).setPreferredWidth(450);
        table_want.getColumnModel().getColumn(3).setPreferredWidth(80);
        table_want.getColumnModel().getColumn(4).setPreferredWidth(120);
        table_want.getColumnModel().getColumn(5).setPreferredWidth(110);

        table_want.setRowHeight(50);
        //支持滚动
        JScrollPane jsp=new JScrollPane(table_want);
        jsp.setBounds(0,0,(int)(1280*width_r),(int)(480*height_r));
        add(jsp);
        JButton del_button = new JButton("退选课程");
        del_button.setBounds((int)(500*width_r), (int)(525*height_r), (int)(200*width_r), (int)(40*height_r));
        add(del_button);
        Font myfont2 = new Font("微软雅黑", Font.PLAIN, 18);
        del_button.setFont(myfont2);
        del_button.setBackground(new Color(248, 248, 255));
        del_button.setContentAreaFilled(true);//设置按钮透明
        del_button.setVisible(false);
        del_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String id= (String) table_want.getValueAt(table_want.getSelectedRow(),0);
                try {
                    Client_curriculum.DropCourse(id);
                    Client_curriculum.RequireMyChoice();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        table_want.addMouseListener(new MouseListener() {
            int last_clicked=-1;
            @Override
            public void mouseClicked(MouseEvent e) {

                if(table_want.getSelectedColumn()==last_clicked){
                    last_clicked=-1;
                    table_want.clearSelection();
                    del_button.setVisible(false);
                }
                else {
                    last_clicked=table_want.getSelectedColumn();
                    del_button.setVisible(true);
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
        add(p11);


    }

}
