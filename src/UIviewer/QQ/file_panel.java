package UIviewer.QQ;

import java.awt.BorderLayout;

import java.awt.FlowLayout;

import java.awt.LayoutManager;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.io.File;

import javax.swing.*;

/**
 * 文件面板
 *
 * @author Zhangyang_Ge
 * @date 2022/09/03
 */
public class file_panel {
    static File file;

    /**
     * 主要
     *
     * @param args arg游戏
     */
    public static void main(String[] args) {

        //createWindow();

    }

    /**
     * 创建窗口
     *
     * @param type 类型
     */
    public static void createWindow(int type) {
        JFrame frame;
        if(type==0)  {frame= new JFrame("发送文件");}
        else {frame = new JFrame("接收文件");}

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createUI(frame,type);

        frame.setSize(360, 120);

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

    }

    /**
     * 创建用户界面
     *
     * @param frame 框架
     * @param type  类型
     */
    private static void createUI(final JFrame frame,int type) {

        JPanel panel = new JPanel();
        JPanel panel1=new JPanel();

        LayoutManager layout = new FlowLayout();

        panel.setLayout(layout);
        panel1.setLayout(layout);
        JButton button = new JButton("浏览本地文件目录");
        button.setFocusPainted(false);
        JButton button1=new JButton("发送");;
        button1.setFocusPainted(false);
        if(type==1) button1.setText("接收");

        JButton button2=new JButton("取消");
        button2.setFocusPainted(false);

        final JLabel label = new JLabel();

        button.addActionListener(new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent e) {

                JFileChooser fileChooser = new JFileChooser();

                fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

                int option = fileChooser.showOpenDialog(frame);

                if (option == JFileChooser.APPROVE_OPTION) {

                    file = fileChooser.getSelectedFile();

                    label.setText("选择文件或目录: " + file.getName());

                } else {

                    label.setText("打开命令取消");

                }

            }

        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(label.getText().equals("打开命令取消")){
                    JOptionPane.showMessageDialog(null, "请选择文件！", "WARNING!", JOptionPane.PLAIN_MESSAGE);
                }
                else{
                    chat_panel.send_file(file.getAbsolutePath(),file.getName());
                    frame.dispose();
                }
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        panel.add(button);

        panel.add(label);
        panel1.add(button1);
        panel1.add(button2);

        frame.getContentPane().add(panel, BorderLayout.CENTER);

        frame.getContentPane().add(panel1,BorderLayout.SOUTH);

    }

}