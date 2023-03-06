package UIviewer.status_manage;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

/**
 * jtext字段提示监听器
 *
 * @author Zhangyang_Ge
 * @date 2022/09/03
 */
public class JTextFieldHintListener implements FocusListener {
    private String hintText;
    private JTextField textField;

    /**
     * jtext字段提示监听器
     *
     * @param jTextField j文本字段
     * @param hintText   提示文本
     */
    public JTextFieldHintListener(JTextField jTextField,String hintText) {
        this.textField = jTextField;
        this.hintText = hintText;
        jTextField.setText(hintText);  //默认直接显示
        jTextField.setForeground(Color.GRAY);
    }

    /**
     * 关注了
     *
     * @param e e
     */
    @Override
    public void focusGained(FocusEvent e) {
        //获取焦点时，清空提示内容
        String temp = textField.getText();
        if(temp.equals(hintText)) {
            textField.setText("");
            textField.setForeground(Color.BLACK);
        }

    }

    /**
     * 集中了
     *
     * @param e e
     */
    @Override
    public void focusLost(FocusEvent e) {
        //失去焦点时，没有输入内容，显示提示内容
        String temp = textField.getText();
        if(temp.equals("")) {
            textField.setForeground(Color.GRAY);
            textField.setText(hintText);
        }

    }

}