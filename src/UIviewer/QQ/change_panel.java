package UIviewer.QQ;

import ClientToServer.myInfo;
import DAO.QICQ.Friend;
import UIhandler.QICQ.Client_qicq;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * 改变面板
 *
 * @author Zhangyang_Ge
 * @date 2022/09/03
 */
public class change_panel {
    static JFrame jf;

    /**
     * 改变面板
     *
     * @param friend 朋友
     */
    public static void change_panel(Friend friend) {
        jf = new JFrame("修改备注与分组");
        jf.setLayout(null);
        jf.setBackground(Color.white);


        JLabel l1 = new JLabel("备注:");
        l1.setFont(new Font("宋体", Font.BOLD, 15));
        l1.setBounds(20, 20, 250, 25);
        jf.add(l1);
        JTextField nickname=new JTextField();
        nickname.setFont(new Font("宋体", Font.BOLD, 12));
        nickname.setBounds(95, 20, 125, 25);
        jf.add(nickname);
        nickname.setColumns(10);

        JLabel l2 = new JLabel("分组:");
        l2.setFont(new Font("宋体", Font.BOLD, 15));
        l2.setBounds(20, 60, 250, 25);
        jf.add(l2);
        JTextField group=new JTextField();
        group.setFont(new Font("宋体", Font.BOLD, 12));
        group.setBounds(95, 60, 125, 25);
        jf.add(group);
        group.setColumns(10);

        JButton b1=new JButton("修改");
        b1.setFocusPainted(false);
        b1.setBounds(270,38,100,30);
        b1.setForeground(Color.white);
        b1.setBackground(new Color(30,111,255));
        b1.setFocusPainted(false);
        jf.add(b1);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                friend.setName(nickname.getText());
                friend.setType(group.getText());
                Client_qicq.modify(friend);
            }
        });
        jf.getContentPane().setBackground(Color.white);
        jf.setBounds(0,0,400,145);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }

    /**
     * 改变成功
     */
    public static void change_succeed() {
        jf.dispose();
    }
//
//    public static void add_friend_fail() {
//        JOptionPane.showMessageDialog(null, "未查找到该用户", "WARNING!", JOptionPane.PLAIN_MESSAGE);
//    }
}
