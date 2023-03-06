package UIviewer.SelectCourse;

import ClientToServer.myInfo;
import UIhandler.Currirulum.Client_curriculum;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 我coursetable
 *
 * @author Chen_GuanZhi
 * @date 2022/09/03
 */
public class My_Coursetable extends JPanel{
    Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
    int width=(int ) screensize.getWidth(); //得到宽度
    int height=(int ) screensize.getHeight();//获得高度
    double width_r=(double)(width)/1273;
    double height_r=(double)(height)/790;
    public static volatile  String[][][] tableDate=new String[16][13][6];
    TableColumn column;

    /**
     * 我coursetable
     *
     * @param week 周
     */
    public My_Coursetable(int week)
    {
        setLayout(null);
        JTable table;
        JScrollPane scrollPane;
        DefaultTableModel tableModel;
        String weekS=String.valueOf(week);
        JLabel l1 = new JLabel("第"+weekS+"周课表");
        l1.setBounds((int) (1075*width_r), (int) (300*height_r), (int) (300*width_r), (int) (75*height_r));
        Font font = new Font("楷体", Font.BOLD, (int) (25*width_r));
        l1.setFont(font);
        add(l1);
        for (int i=0;i<16;i++){
            My_Coursetable.tableDate[i][0][0]="第一节";
            My_Coursetable.tableDate[i][1][0]="第二节";
            My_Coursetable.tableDate[i][2][0]="第三节";
            My_Coursetable.tableDate[i][3][0]="第四节";
            My_Coursetable.tableDate[i][4][0]="第五节";
            My_Coursetable.tableDate[i][5][0]="第六节";
            My_Coursetable.tableDate[i][6][0]="第七节";
            My_Coursetable.tableDate[i][7][0]="第八节";
            My_Coursetable.tableDate[i][8][0]="第九节";
            My_Coursetable.tableDate[i][9][0]="第十节";
            My_Coursetable.tableDate[i][10][0]="第十一节";
            My_Coursetable.tableDate[i][11][0]="第十二节";
            My_Coursetable.tableDate[i][12][0]="第十三节";
        }

        String[] columnNames={"节数","星期一","星期二","星期三","星期四","星期五"};
        tableModel=new DefaultTableModel(tableDate[week-1],columnNames);
        table=new JTable(tableModel){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        //scrollPane.setViewportView(table);
        scrollPane=new JScrollPane(table);
        table.setRowHeight(48);//设置行宽

        //table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        for(int i=0;i<6;i++)
        {
            column=table.getColumnModel().getColumn(i);
            if(i==0)
            {
                column.setPreferredWidth(85);
            }
            else {
                column.setPreferredWidth(300);
            }

        }
        JComboBox jc=new JComboBox();
        jc.addItem("--请选择--");
        jc.addItem("第一周");
        jc.addItem("第二周");
        jc.addItem("第三周");
        jc.addItem("第四周");
        jc.addItem("第五周");
        jc.addItem("第六周");
        jc.addItem("第七周");
        jc.addItem("第八周");
        jc.addItem("第九周");
        jc.addItem("第十周");
        jc.addItem("第十一周");
        jc.addItem("第十二周");
        jc.addItem("第十三周");
        jc.addItem("第十四周");
        jc.addItem("第十五周");
        jc.addItem("第十六周");
        jc.setBounds((int)(1075*width_r),(int)(75*height_r),(int)(100*width_r),(int)(40*height_r));
        jc.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED){
                    //选择的下拉框选项
                    if(e.getItem()=="第一周")
                    {
                        if(myInfo.getType()==1) {
                            My_Coursetable table = new My_Coursetable(1);
                            Selcourse.panel.add(table, "schedule");
                            Selcourse.cardLayout.show(Selcourse.panel, "schedule");
                        }else if(myInfo.getType()==2){
                            My_Coursetable table = new My_Coursetable(1);
                            Selcourse_teacher.panel.add(table, "schedule");
                            Selcourse_teacher.cardLayout.show(Selcourse_teacher.panel, "schedule");
                        }
                    }
                    if(e.getItem()=="第二周")
                    {

                        if(myInfo.getType()==1) {
                            My_Coursetable table = new My_Coursetable(2);
                            Selcourse.panel.add(table, "schedule");
                            Selcourse.cardLayout.show(Selcourse.panel, "schedule");
                        }else if(myInfo.getType()==2){
                            My_Coursetable table = new My_Coursetable(2);
                            Selcourse_teacher.panel.add(table, "schedule");
                            Selcourse_teacher.cardLayout.show(Selcourse_teacher.panel, "schedule");
                        }
                    }
                    if(e.getItem()=="第三周")
                    {
                        if(myInfo.getType()==1) {
                            My_Coursetable table = new My_Coursetable(3);
                            Selcourse.panel.add(table, "schedule");
                            Selcourse.cardLayout.show(Selcourse.panel, "schedule");
                        }else if(myInfo.getType()==2){
                            My_Coursetable table = new My_Coursetable(3);
                            Selcourse_teacher.panel.add(table, "schedule");
                            Selcourse_teacher.cardLayout.show(Selcourse_teacher.panel, "schedule");
                        }
                    }
                    if(e.getItem()=="第四周")
                    {
                        if(myInfo.getType()==1) {
                            My_Coursetable table = new My_Coursetable(4);
                            Selcourse.panel.add(table, "schedule");
                            Selcourse.cardLayout.show(Selcourse.panel, "schedule");
                        }else if(myInfo.getType()==2){
                            My_Coursetable table = new My_Coursetable(4);
                            Selcourse_teacher.panel.add(table, "schedule");
                            Selcourse_teacher.cardLayout.show(Selcourse_teacher.panel, "schedule");
                        }
                    }
                    if(e.getItem()=="第五周")
                    {
                        if(myInfo.getType()==1) {
                            My_Coursetable table = new My_Coursetable(5);
                            Selcourse.panel.add(table, "schedule");
                            Selcourse.cardLayout.show(Selcourse.panel, "schedule");
                        }else if(myInfo.getType()==2){
                            My_Coursetable table = new My_Coursetable(5);
                            Selcourse_teacher.panel.add(table, "schedule");
                            Selcourse_teacher.cardLayout.show(Selcourse_teacher.panel, "schedule");
                        }
                    }
                    if(e.getItem()=="第六周")
                    {
                        if(myInfo.getType()==1) {
                            My_Coursetable table = new My_Coursetable(6);
                            Selcourse.panel.add(table, "schedule");
                            Selcourse.cardLayout.show(Selcourse.panel, "schedule");
                        }else if(myInfo.getType()==2){
                            My_Coursetable table = new My_Coursetable(6);
                            Selcourse_teacher.panel.add(table, "schedule");
                            Selcourse_teacher.cardLayout.show(Selcourse_teacher.panel, "schedule");
                        }
                    }
                    if(e.getItem()=="第七周")
                    {
                        if(myInfo.getType()==1) {
                            My_Coursetable table = new My_Coursetable(7);
                            Selcourse.panel.add(table, "schedule");
                            Selcourse.cardLayout.show(Selcourse.panel, "schedule");
                        }else if(myInfo.getType()==2){
                            My_Coursetable table = new My_Coursetable(7);
                            Selcourse_teacher.panel.add(table, "schedule");
                            Selcourse_teacher.cardLayout.show(Selcourse_teacher.panel, "schedule");
                        }
                    }
                    if(e.getItem()=="第八周")
                    {
                        if(myInfo.getType()==1) {
                            My_Coursetable table = new My_Coursetable(8);
                            Selcourse.panel.add(table, "schedule");
                            Selcourse.cardLayout.show(Selcourse.panel, "schedule");
                        }else if(myInfo.getType()==2){
                            My_Coursetable table = new My_Coursetable(8);
                            Selcourse_teacher.panel.add(table, "schedule");
                            Selcourse_teacher.cardLayout.show(Selcourse_teacher.panel, "schedule");
                        }
                    }
                    if(e.getItem()=="第九周")
                    {
                        if(myInfo.getType()==1) {
                            My_Coursetable table = new My_Coursetable(9);
                            Selcourse.panel.add(table, "schedule");
                            Selcourse.cardLayout.show(Selcourse.panel, "schedule");
                        }else if(myInfo.getType()==2){
                            My_Coursetable table = new My_Coursetable(9);
                            Selcourse_teacher.panel.add(table, "schedule");
                            Selcourse_teacher.cardLayout.show(Selcourse_teacher.panel, "schedule");
                        }
                    }
                    if(e.getItem()=="第十周")
                    {
                        if(myInfo.getType()==1) {
                            My_Coursetable table = new My_Coursetable(10);
                            Selcourse.panel.add(table, "schedule");
                            Selcourse.cardLayout.show(Selcourse.panel, "schedule");
                        }else if(myInfo.getType()==2){
                            My_Coursetable table = new My_Coursetable(10);
                            Selcourse_teacher.panel.add(table, "schedule");
                            Selcourse_teacher.cardLayout.show(Selcourse_teacher.panel, "schedule");
                        }
                    }
                    if(e.getItem()=="第十一周")
                    {
                        if(myInfo.getType()==1) {
                            My_Coursetable table = new My_Coursetable(11);
                            Selcourse.panel.add(table, "schedule");
                            Selcourse.cardLayout.show(Selcourse.panel, "schedule");
                        }else if(myInfo.getType()==2){
                            My_Coursetable table = new My_Coursetable(11);
                            Selcourse_teacher.panel.add(table, "schedule");
                            Selcourse_teacher.cardLayout.show(Selcourse_teacher.panel, "schedule");
                        }
                    }
                    if(e.getItem()=="第十二周")
                    {
                        if(myInfo.getType()==1) {
                            My_Coursetable table = new My_Coursetable(12);
                            Selcourse.panel.add(table, "schedule");
                            Selcourse.cardLayout.show(Selcourse.panel, "schedule");
                        }else if(myInfo.getType()==2){
                            My_Coursetable table = new My_Coursetable(12);
                            Selcourse_teacher.panel.add(table, "schedule");
                            Selcourse_teacher.cardLayout.show(Selcourse_teacher.panel, "schedule");
                        }
                    }
                    if(e.getItem()=="第十三周")
                    {
                        if(myInfo.getType()==1) {
                            My_Coursetable table = new My_Coursetable(13);
                            Selcourse.panel.add(table, "schedule");
                            Selcourse.cardLayout.show(Selcourse.panel, "schedule");
                        }else if(myInfo.getType()==2){
                            My_Coursetable table = new My_Coursetable(13);
                            Selcourse_teacher.panel.add(table, "schedule");
                            Selcourse_teacher.cardLayout.show(Selcourse_teacher.panel, "schedule");
                        }
                    }
                    if(e.getItem()=="第十四周")
                    {
                        if(myInfo.getType()==1) {
                            My_Coursetable table = new My_Coursetable(14);
                            Selcourse.panel.add(table, "schedule");
                            Selcourse.cardLayout.show(Selcourse.panel, "schedule");
                        }else if(myInfo.getType()==2){
                            My_Coursetable table = new My_Coursetable(14);
                            Selcourse_teacher.panel.add(table, "schedule");
                            Selcourse_teacher.cardLayout.show(Selcourse_teacher.panel, "schedule");
                        }
                    }
                    if(e.getItem()=="第十五周")
                    {
                        if(myInfo.getType()==1) {
                            My_Coursetable table = new My_Coursetable(15);
                            Selcourse.panel.add(table, "schedule");
                            Selcourse.cardLayout.show(Selcourse.panel, "schedule");
                        }else if(myInfo.getType()==2){
                            My_Coursetable table = new My_Coursetable(15);
                            Selcourse_teacher.panel.add(table, "schedule");
                            Selcourse_teacher.cardLayout.show(Selcourse_teacher.panel, "schedule");
                        }
                    }
                    if(e.getItem()=="第十六周")
                    {
                        if(myInfo.getType()==1) {
                            My_Coursetable table = new My_Coursetable(16);
                            Selcourse.panel.add(table, "schedule");
                            Selcourse.cardLayout.show(Selcourse.panel, "schedule");
                        }else if(myInfo.getType()==2){
                            My_Coursetable table = new My_Coursetable(16);
                            Selcourse_teacher.panel.add(table, "schedule");
                            Selcourse_teacher.cardLayout.show(Selcourse_teacher.panel, "schedule");
                        }
                    }

                }
            }
        });
        add(jc);

        //add(scrollPane,BorderLayout.CENTER);
        scrollPane.setBounds(0,0,(int)(1000*width_r),(int)(1000*height_r));
        add(scrollPane);
        setVisible(true);
    }

}
