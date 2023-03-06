package UIviewer.QQ;

import DAO.QICQ.Friend;
import UIhandler.QICQ.Client_qicq;
import UIviewer.login.functionChoose;
import UIviewer.status_manage.RoundJButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 主面板
 *
 * @author Zhangyang_Ge
 * @date 2022/09/03
 */
public class main_panel {
    public static JPanel mjp;
    public static JButton close_button;
    public static chat_panel cpn;
    public HashMap<String, ArrayList<Friend>> friend;
    static button_panel buttonPanel;

    /**
     * 主面板
     *
     * @param width  宽度
     * @param height 高度
     * @param type   类型
     * @throws IOException ioexception
     */
    public main_panel(int width, int height,int type) throws IOException {
        double width_r=(double)(width)/1920;
        double height_r=(double)(height)/1080;
        mjp=new JPanel();
        //设置屏幕大小、背景颜色
        mjp.setBounds(0,0,width,height);
        mjp.setBackground(new Color(255,255,255));
        //设置绝对布局
        mjp.setLayout(null);

        if(type!=3){
            //侧边按钮面板
            buttonPanel=new button_panel();
            buttonPanel.setBounds((int)(width_r*1920/3+0),(int)((height-135*height_r)),(int)(170*width_r),(int)(400*height_r));
            mjp.add(buttonPanel);
            //添加好友按钮
            JButton addFriend= new RoundJButton();
            addFriend.setFocusPainted(false);
            addFriend.setText("   添加好友   ");
            addFriend.setBounds(0,(int)(0*height_r),(int)(170*width_r),(int)(50*height_r));
            addFriend.setBackground(new Color(30,111,255));
            addFriend.setForeground(Color.white);
            addFriend.setFont(new Font("微软雅黑",Font.PLAIN,(int)(22*width_r)));
            addFriend.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    add_friend.add_friend();
                }
            });
            buttonPanel.add(addFriend);
            //返回功能选择模块
            functionChoose.back_from_student_status=new RoundJButton();
            functionChoose.back_from_student_status.setFocusPainted(false);
            functionChoose.back_from_student_status.setText("返回功能选择");
            functionChoose.back_from_student_status.setBounds(0,(int)(50*height_r),(int)(170*width_r),(int)(50*height_r));
            functionChoose.back_from_student_status.setBackground(new Color(96,190,41));
            functionChoose.back_from_student_status.setForeground(new Color(255,255,255));
            functionChoose.back_from_student_status.setFont(new Font("微软雅黑",Font.PLAIN,(int)(22*width_r)));
            buttonPanel.add(functionChoose.back_from_student_status);
            functionChoose.back_from_student_status.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    functionChoose.jf.remove(functionChoose.jf.getContentPane());
                    functionChoose.jf.setContentPane(functionChoose.fc_panel);
                    functionChoose.jf.setTitle("functionChoose");
                }
            });
        }
        else{
            //侧边按钮面板
            buttonPanel=new button_panel();
            buttonPanel.setBounds((int)(width_r*1920/3+0),(int)((height-80*height_r)),(int)(170*width_r),(int)(400*height_r));
            mjp.add(buttonPanel);
            //返回功能选择模块
            functionChoose.back_from_student_status=new RoundJButton();
            functionChoose.back_from_student_status.setFocusPainted(false);
            functionChoose.back_from_student_status.setText("返回功能选择");
            functionChoose.back_from_student_status.setBounds(0,(int)(0*height_r),(int)(170*width_r),(int)(50*height_r));
            functionChoose.back_from_student_status.setBackground(new Color(96,190,41));
            functionChoose.back_from_student_status.setForeground(new Color(255,255,255));
            functionChoose.back_from_student_status.setFont(new Font("微软雅黑",Font.PLAIN,(int)(22*width_r)));
            buttonPanel.add(functionChoose.back_from_student_status);
            functionChoose.back_from_student_status.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    functionChoose.jf.remove(functionChoose.jf.getContentPane());
                    functionChoose.jf.setContentPane(functionChoose.fc_panel);
                    functionChoose.jf.setTitle("functionChoose");
                }
            });
        }
        //好友列表
        Client_qicq.Require_friend_list();
        friend_list friend_list_panel=new friend_list(1920/3,1080,width_r,height_r,0,0);
        mjp.add(friend_list_panel.jPanel);
        friend_list_panel.jPanel.setVisible(true);
        functionChoose.back_from_student_status.setVisible(false);
        functionChoose.back_from_student_status.setVisible(true);
        mjp.updateUI();
    }
}
