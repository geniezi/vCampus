package UIviewer.login;
import ClientToServer.ClientToServer;
import User.Student;
import User.Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 注册
 *
 * @author Chen_GuanZhi
 * @date 2022/09/03
 */
public class register {

    /**
     * 注册界面
     */
    public static void registerUI() {
        JFrame jf = new JFrame("register");
        //东南大学logo
        JLabel l1 = new JLabel(); // 创建一个标签组件对象
        ImageIcon icon1 = new ImageIcon("src/image/logo-mini.png"); // 创建背景图片对象
        l1.setIcon(icon1); // 设置标签组件要显示的图标
        l1.setBounds(0, 10, 210,65); // 设置组件的显示位置及大小
        jf.getContentPane().add(l1);
        //注册信息
        JLabel l2 = new JLabel("       注册信息");
        l2.setBounds(200, 10, 290, 80);
        Font font = new Font("楷体", Font.BOLD, 26);
        l2.setFont(font);
        //l1.setForeground(new Color(111,222,0));
        jf.getContentPane().add(l2);

        //账号密码
        JLabel l3 = new JLabel("一卡通:");
        l3.setFont(new Font("宋体", Font.BOLD, 15));
        l3.setBounds(220, 100, 250, 25);
        jf.add(l3);
        JTextField textField3=new JTextField();
        textField3.setFont(new Font("宋体", Font.BOLD, 12));
        textField3.setBounds(295, 100, 125, 25);
        jf.add(textField3);
        textField3.setColumns(10);

        JLabel l4 = new JLabel("密码:");
        l4.setFont(new Font("宋体", Font.BOLD, 15));
        l4.setBounds(220, 140, 250, 25);
        jf.add(l4);
        JPasswordField pwd=new JPasswordField();
        pwd.setFont(new Font("宋体", Font.BOLD, 12));
        pwd.setBounds(295, 140, 125, 25);
        jf.add(pwd);
        pwd.setColumns(10);

        JLabel l5 = new JLabel("姓名:");
        l5.setFont(new Font("宋体", Font.BOLD, 15));
        l5.setBounds(220, 180, 250, 25);
        jf.add(l5);
        JTextField textField5=new JTextField();
        textField5.setFont(new Font("宋体", Font.BOLD, 12));
        textField5.setBounds(295, 180, 125, 25);
        jf.add(textField5);
        textField5.setColumns(10);

        JLabel l6 = new JLabel("学/工号:");
        l6.setFont(new Font("宋体", Font.BOLD, 15));
        l6.setBounds(220, 220, 250, 25);
        jf.add(l6);
        JTextField textField6=new JTextField();
        textField6.setFont(new Font("宋体", Font.BOLD, 12));
        textField6.setBounds(295, 220, 125, 25);
        jf.add(textField6);
        textField6.setColumns(10);

        JLabel l7 = new JLabel("年龄:");
        l7.setFont(new Font("宋体", Font.BOLD, 15));
        l7.setBounds(220, 260, 250, 25);
        jf.add(l7);
        JTextField textField7=new JTextField();
        textField7.setFont(new Font("宋体", Font.BOLD, 12));
        textField7.setBounds(295, 260, 125, 25);
        jf.add(textField7);
        textField7.setColumns(10);

        JLabel l10 = new JLabel("邮箱:");
        l10.setFont(new Font("宋体", Font.BOLD, 15));
        l10.setBounds(220, 300, 250, 25);
        jf.add(l10);
        JTextField textField10=new JTextField();
        textField10.setFont(new Font("宋体", Font.BOLD, 12));
        textField10.setBounds(295, 300, 125, 25);
        jf.add(textField10);
        textField10.setColumns(10);

        JLabel l8 = new JLabel("性别：");
        l8.setFont(new Font("宋体", Font.BOLD, 15));
        l8.setBounds(220, 340, 250, 25);
        jf.add(l8);
        JComboBox jc =new JComboBox();
        jc.setBounds(295,340,125,25);//使用绝对布局，自定义大小
        jc.addItem("--请选择--");
        jc.addItem("男");
        jc.addItem("女");
        jf.add(jc);

        JLabel l9 = new JLabel("身份：");
        l9.setFont(new Font("宋体", Font.BOLD, 15));
        l9.setBounds(220, 380, 250, 25);
        jf.add(l9);
        JComboBox jc1 =new JComboBox();
        jc1.setBounds(295,380,125,25);//使用绝对布局，自定义大小
        jc1.addItem("--请选择--");
        jc1.addItem("学生");
        jc1.addItem("教师");
        jf.add(jc1);

        JButton b1=new JButton("确定注册");
        b1.setBounds(360,450,100,30);
        b1.setBackground(new Color(250,250,210));
        b1.setFocusPainted(false);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                try {
                    String idcard=textField3.getText();
                    String passwd=null;
                    if(pwd.getPassword().length>0)
                        passwd=String.valueOf(pwd.getPassword());
                    String name=textField5.getText();
                    String xuehao=textField6.getText();
                    int age=0;
                    if(textField7.getText().length()!=0)
                        age=Integer.parseInt(textField7.getText());
                    String mail=textField10.getText();
                    String gender=(String)jc.getSelectedItem();
                    String identity=(String)jc1.getSelectedItem();
                    if(idcard==null||name==null||passwd==null||xuehao==null||age==0||(mail.length()==0)||gender==null||identity==null){
                        JOptionPane.showMessageDialog(jf, "信息填写不完整，请重新填写!");
                    }
                    else if(identity=="学生") {
                        Student s = new Student();
                        s.setStudent_idcard(idcard);
                        s.setStudent_pwd(passwd);
                        s.setStudent_name(name);
                        s.setStudent_id(xuehao);
                        s.setStudent_age(age);
                        s.setStudent_email(mail);
                        s.setStudent_gender(gender);
                        if(ClientToServer.registerStudent(s)) {
                        //    System.out.println("学生"+name+"注册成功！");
                            JOptionPane.showMessageDialog(jf, "注册成功!");
                            jf.dispose();
                        }
                    }
                    else if(identity=="教师") {
                        Teacher t = new Teacher();
                        t.setTeacher_idcard(idcard);
                        t.setTeacher_pwd(passwd);
                        t.setTeacher_name(name);
                        t.setTeacher_id(xuehao);
                        t.setTeacher_gender(gender);
                        t.setTeacher_email(mail);
                        t.setTeacher_age(age);
                        if(ClientToServer.registerTeacher(t)) {
                            System.out.println("教师"+name+"注册成功！");
                            JOptionPane.showMessageDialog(jf, "注册成功!");
                            jf.dispose();
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        jf.add(b1);


        //中间面板
        JPanel p3 = new JPanel();
        p3.setBounds(200, 0, 290, 620);
        p3.setBackground(new Color(255,228,181,150));
        jf.getContentPane().add(p3);

        //随机背景图片
        JLabel lblBackground = new JLabel(); // 创建一个标签组件对象
        ImageIcon icon = new ImageIcon("src/image/bg16.jpg"); // 创建背景图片对象
        lblBackground.setIcon(icon); // 设置标签组件要显示的图标
        lblBackground.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight()); // 设置组件的显示位置及大小
        jf.getContentPane().add(lblBackground);

        jf.setBounds(0,0,690,620);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setVisible(true);
    }

}
