package UIviewer;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import ClientToServer.ClientToServer;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.SystemColor;
import java.io.*;

/**
 * @author : [Tongwei_L]
 * @version : [v1.0]
 * @description : [该类启动]
 * @createTime : [2022.08.14 21:14]
 */

public class LoginFrame extends JFrame {

	private ClientToServer ucs = new ClientToServer();
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	public LoginFrame() {
		setTitle("登录界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(800, 800, 400, 300);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("用户名:");
		lblNewLabel.setIcon(new ImageIcon(LoginFrame.class.getResource("/image/用户名.png")));
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 15));
		lblNewLabel.setBounds(57, 31, 76, 26);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("密   码:");
		lblNewLabel_1.setIcon(new ImageIcon(LoginFrame.class.getResource("/image/密码.png")));
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		lblNewLabel_1.setBounds(57, 73, 88, 26);
		contentPane.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setFont(new Font("微软雅黑", Font.BOLD, 12));
		textField.setBounds(144, 34, 123, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("微软雅黑", Font.BOLD, 12));
		passwordField.setBounds(144, 78, 123, 21);
		contentPane.add(passwordField);

		JButton btnNewButton = new JButton("登录");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					confirmButton(e);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnNewButton.setBackground(new Color(211, 211, 211));
		btnNewButton.setBounds(194, 133, 74, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton1 = new JButton("注册");
		btnNewButton1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					confirmButton(e);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnNewButton1.setBackground(new Color(211, 211, 211));
		btnNewButton1.setBounds(110, 133, 74, 23);
		contentPane.add(btnNewButton1);

		//定义单选按钮

		JRadioButton Button1=new JRadioButton("老师",false);
		Button1.setBounds(110, 166, 74, 23);
		contentPane.add(Button1);
//		JRadioButton Button2=new JRadioButton("学生",false);
//		Button2.setBounds(130, 166, 74, 23);
//		contentPane.add(Button2);
//		JRadioButton Button3=new JRadioButton("管理员",false);
//		Button3.setBounds(13, 166, 74, 23);
//		contentPane.add(Button3);

	}

	protected void confirmButton(ActionEvent e) throws Exception {
		String username = this.textField.getText();
		String password = String.valueOf(this.passwordField.getPassword());
		System.out.println(username+"  "+password);
		if(ucs.checkStudent(username,password)) {
			this.dispose();
		}else{
			JOptionPane.showMessageDialog(this, "用户名或密码错误,请重试!");
		}
	}

	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
