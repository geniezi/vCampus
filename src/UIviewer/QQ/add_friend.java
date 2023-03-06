package UIviewer.QQ;

import ClientToServer.ClientToServer;
import UIhandler.QICQ.Client_qicq;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import ClientToServer.myInfo;

/**
 * 添加朋友
 *
 * @author Zhangyang_Ge
 * @date 2022/09/03
 */
public class add_friend {
    static JFrame jf;

    /**
     * 添加朋友
     */
    public static void add_friend() {
        jf = new JFrame("添加好友");
        jf.setLayout(null);
        jf.setBackground(Color.white);


        JLabel l1 = new JLabel("一卡通:");
        l1.setFont(new Font("宋体", Font.BOLD, 15));
        l1.setBounds(20, 20, 250, 25);
        jf.add(l1);
        JTextField idCard=new JTextField();
        idCard.setFont(new Font("宋体", Font.BOLD, 12));
        idCard.setBounds(95, 20, 125, 25);
        jf.add(idCard);
        idCard.setColumns(10);

        JLabel l2 = new JLabel("分组:");
        l2.setFont(new Font("宋体", Font.BOLD, 15));
        l2.setBounds(20, 60, 250, 25);
        jf.add(l2);
        JTextField group=new JTextField();
        group.setFont(new Font("宋体", Font.BOLD, 12));
        group.setBounds(95, 60, 125, 25);
        jf.add(group);
        group.setColumns(10);

        JLabel l3 = new JLabel("备注:");
        l3.setFont(new Font("宋体", Font.BOLD, 15));
        l3.setBounds(20, 100, 250, 25);
        jf.add(l3);
        JTextField nickname=new JTextField();
        nickname.setFont(new Font("宋体", Font.BOLD, 12));
        nickname.setBounds(95, 100, 125, 25);
        jf.add(nickname);
        nickname.setColumns(10);

        JButton b1=new JButton("申请添加");
        b1.setFocusPainted(false);
        b1.setBounds(270,58,100,30);
        b1.setForeground(Color.white);
        b1.setBackground(new Color(30,111,255));
        b1.setFocusPainted(false);
        jf.add(b1);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Client_qicq.add_friend(myInfo.getId(),group.getText(),idCard.getText(),nickname.getText());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        jf.getContentPane().setBackground(Color.white);
        jf.setBounds(0,0,400,195);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setVisible(true);
    }

    /**
     * 添加好友成功
     */
    public static void add_friend_succeed() {
        jf.dispose();
    }

    /**
     * 添加好友失败
     */
    public static void add_friend_fail() {
        JOptionPane.showMessageDialog(null, "未查找到该用户", "WARNING!", JOptionPane.PLAIN_MESSAGE);
    }
}
