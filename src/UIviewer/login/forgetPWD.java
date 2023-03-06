package UIviewer.login;
import ClientToServer.ClientToServer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 忘记pwd
 *
 * @author Chen_GuanZhi
 * @date 2022/09/03
 */
public class forgetPWD {

    /**
     * 忘记pwdui
     */
    public static void forgetPWDUI() {
        JFrame jf = new JFrame("forgetPWD");
        //找回密码
        JLabel l2 = new JLabel("      找回密码");
        l2.setBounds(200, 10, 290, 80);
        Font font = new Font("楷体", Font.BOLD, 26);
        l2.setFont(font);
        //l1.setForeground(new Color(111,222,0));
        jf.getContentPane().add(l2);

        JLabel l5 = new JLabel("      请验证身份信息!");
        l5.setBounds(200, 100, 290, 80);
        Font font1 = new Font("楷体", Font.BOLD, 20);
        l5.setFont(font1);
        //l1.setForeground(new Color(111,222,0));
        jf.getContentPane().add(l5);

        JLabel l3 = new JLabel("一卡通:");
        l3.setFont(new Font("宋体", Font.BOLD, 15));
        l3.setBounds(220, 200, 250, 25);
        jf.add(l3);
        JTextField textField3=new JTextField();
        textField3.setFont(new Font("宋体", Font.BOLD, 12));
        textField3.setBounds(295, 200, 125, 25);
        jf.add(textField3);
        textField3.setColumns(10);

        JLabel l4 = new JLabel("邮箱:");
        l4.setFont(new Font("宋体", Font.BOLD, 15));
        l4.setBounds(220, 240, 250, 25);
        jf.add(l4);
        JTextField mail=new JTextField();
        mail.setFont(new Font("宋体", Font.BOLD, 12));
        mail.setBounds(295, 240, 125, 25);
        jf.add(mail);
        mail.setColumns(10);

        JLabel l6=new JLabel("新密码:");
        l6.setFont(new Font("宋体", Font.BOLD, 15));
        l6.setBounds(220, 280, 250, 25);
        jf.add(l6);
        JPasswordField pwd1=new JPasswordField();
        pwd1.setBounds(295,280,125,25);
        jf.add(pwd1);
        pwd1.setColumns(10);

        JLabel l7=new JLabel("确定密码:");
        l7.setFont(new Font("宋体", Font.BOLD, 15));
        l7.setBounds(220, 320, 250, 25);
        jf.add(l7);
        JPasswordField pwd2=new JPasswordField();
        pwd2.setBounds(295,320,125,25);
        jf.add(pwd2);
        pwd1.setColumns(10);

        JLabel l9 = new JLabel("身份：");
        l9.setFont(new Font("宋体", Font.BOLD, 15));
        l9.setBounds(220, 360, 250, 25);
        jf.add(l9);
        JComboBox jc1 =new JComboBox();
        jc1.setBounds(295,360,125,25);//使用绝对布局，自定义大小
        jc1.addItem("--请选择--");
        jc1.addItem("学生");
        jc1.addItem("教师");
        jf.add(jc1);

        JButton b1=new JButton("验证");
        b1.setBounds(360,410,100,30);
        b1.setBackground(new Color(250,250,210));
        b1.setFocusPainted(false);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                try {
                    String card=textField3.getText();
                    String mail1=mail.getText();
                    String passwd1=String.valueOf(pwd1.getPassword());
                    String passwd2=String.valueOf(pwd2.getPassword());
                    String identity=(String)jc1.getSelectedItem();
                    if(ClientToServer.forgetpwd(card,mail1,identity)){   //如果验证正确
                        if(passwd1.equals(passwd2)) {
                            ClientToServer.resetPwd(card, passwd1, identity);
                            JOptionPane.showMessageDialog(null,"修改密码成功！");
                            jf.dispose();
                        }else
                            JOptionPane.showMessageDialog(null,"两次输入的新密码不同！");
                    } else   //验证错误
                        JOptionPane.showMessageDialog(null,"验证错误，请重新尝试！");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        jf.add(b1);

        //中间面板
        JPanel p3 = new JPanel();
        p3.setBounds(180, 0, 310, 620);
        p3.setBackground(new Color(255,228,181,200));
        jf.getContentPane().add(p3);

        //随机背景图片
        JLabel lblBackground = new JLabel(); // 创建一个标签组件对象
        ImageIcon icon = new ImageIcon("src/image/bg13.jpg"); // 创建背景图片对象
        lblBackground.setIcon(icon); // 设置标签组件要显示的图标
        lblBackground.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight()); // 设置组件的显示位置及大小
        jf.getContentPane().add(lblBackground);

        jf.setBounds(0,0,690,620);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setVisible(true);
    }
}
