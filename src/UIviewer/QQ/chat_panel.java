package UIviewer.QQ;

import DAO.QICQ.Filetrans;
import DAO.QICQ.Friend;
import UIhandler.QICQ.Client_qicq;
import ClientToServer.myInfo;
import message.Message;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 聊天面板
 *
 * @author Zhangyang_Ge
 * @date 2022/09/03
 */
public class chat_panel extends JPanel {
    public JPanel type_panel;
    private static JScrollPane scrollPane;
    private static Friend friend;
    static double width_r,height_r;
    JTextArea type_field;
    static int width,height,send_button_height,send_button_width;
    static JTextPane jTextPane = new JTextPane();
    static StyledDocument doc = jTextPane.getStyledDocument();
    static JButton  receive_button,close_button,send_button,send_file_button;
    static ArrayList<Filetrans>files=new ArrayList<>();

    /**
     * 插入文本
     *
     * @param text      文本
     * @param colorName 颜色名称
     * @param textSize  文字大小
     * @param textAlign 文本对齐
     */
    public static void insertText(String text, Color colorName, int textSize, int textAlign){
        SimpleAttributeSet set = new SimpleAttributeSet();
        StyleConstants.setForeground(set, colorName);//设置文本颜色
        StyleConstants.setFontSize(set, textSize);//设置文本大小
        StyleConstants.setAlignment(set, textAlign);//设置文本对齐方式
        doc.setParagraphAttributes(jTextPane.getText().length(), doc.getLength() - jTextPane.getText().length(), set, false);
        try {
            doc.insertString(doc.getLength(), text+"\n", set);//插入文本
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送文件
     *
     * @param src      src
     * @param filename 文件名
     */
    public static void send_file(String src,String filename){
        Client_qicq.send_file(src,Client_qicq.getId(),friend.getId(),filename);
        try {
            Client_qicq.get_message(friend.getId());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
      //  System.out.println(friend.getId());
    }

    /**
     * 接收文件
     *
     * @param src      src
     * @param filepath filepath
     * @throws IOException ioexception
     */
    public static void receive_file(Filetrans src,String filepath) throws IOException {
        Client_qicq.receive_file(src,filepath);
        //System.out.println(friend.getId());
    }

    /**
     * 显示消息
     *
     * @param messages 消息
     */
    public static void show_message(ArrayList<Message> messages){
        jTextPane.setText(null);
        //开头空格
        SimpleAttributeSet set = new SimpleAttributeSet();
        StyleConstants.setFontSize(set, 0);//设置文本大小
        StyleConstants.setAlignment(set, 1);//设置文本对齐方式
        doc.setParagraphAttributes(jTextPane.getText().length(), doc.getLength() - jTextPane.getText().length(), set, false);
        try {
            doc.insertString(doc.getLength(), " ", set);//插入文本
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        int num=messages.size();
        for(int i=num-1;i>=0;i--) {
//            System.out.println(messages.get(i).getSendTime());
            if(messages.get(i).isfile==1)
            {
                if(messages.get(i).getSender().equals(myInfo.getId())){
                    insertText(messages.get(i).getSendTime(),new Color(122,122,123),(int)(16*width_r),1);
                    insertText(myInfo.getName()+"(我)"+":",Color.black,(int)(22*width_r),2);
                    Filetrans f=(Filetrans)messages.get(i).getData();
                    insertText((String)"给对方发送文件:  "+f.getName(),new Color(164, 1, 1),(int)(42*width_r),2);
                }
                else{
                    Filetrans f=(Filetrans)messages.get(i).getData();
                    insertText(messages.get(i).getSendTime(),new Color(122,122,123),(int)(16*width_r),1);
                    insertText( friend.getName()+":",new Color(0, 181, 181),(int)(22*width_r),0);
                    insertText((String)(String)"收到对方发送的文件:  "+f.getName(),new Color(164, 1, 1),(int)(42*width_r),0);
                    files.add(f);
                }
            }
            else {
                if(messages.get(i).getSender().equals(myInfo.getId())){
                    insertText(messages.get(i).getSendTime(),new Color(122,122,123),(int)(16*width_r),1);
                    insertText(myInfo.getName()+"(我)"+":",Color.black,(int)(22*width_r),2);
                    insertText((String)(messages.get(i).getData()),Color.black,(int)(42*width_r),2);
                }
                else{
                    insertText(messages.get(i).getSendTime(),new Color(122,122,123),(int)(16*width_r),1);
                    insertText(friend.getName()+":",new Color(0, 181, 181),(int)(22*width_r),0);
                    insertText((String)(messages.get(i).getData()),new Color(0, 181, 181),(int)(42*width_r),0);
                }
            }

        }
        if(files.size()!=0){
            receive_button.setVisible(true);
            close_button.setLocation((int)((width-4*send_button_width-1.5)*width_r),(int)((height/4-send_button_height-30)*height_r));
            receive_button.setFocusPainted(false);
        }
        else {
            receive_button.setVisible(false);
            close_button.setLocation((int)((width-3*send_button_width-1.5)*width_r),(int)((height/4-send_button_height-30)*height_r));
            receive_button.setFocusPainted(false);
        }

        jTextPane.setEditable(false);

        jTextPane.updateUI();
        jTextPane.setCaretPosition(jTextPane.getStyledDocument().getLength());
    }

    /**
     * 聊天面板
     *
     * @param width    宽度
     * @param height   高度
     * @param width_r  宽度r
     * @param height_r 高r
     * @param x        x
     * @param y        y
     * @param friend   朋友
     */
    public chat_panel( int width, int height, double width_r, double height_r, int x, int y, Friend friend){
        main_panel.buttonPanel.setVisible(false);
        this.friend=friend;
        setLayout(null);
        this.width_r=width_r;
        this.height_r=height_r;
        this.width=width;
        this.height=height;
        setBounds((int)(x*width_r),(int)(y*height_r),(int)(width*width_r),(int)(height*height_r));
        setBackground(new Color(224,224,224));
        setBorder(BorderFactory.createLineBorder(new Color(234,234,234)));

        //发消息界面
        type_panel=new JPanel();
        type_panel.setLayout(null);
        type_panel.setBounds(0,(int)(3*height/4*height_r),(int)(width*width_r),(int)(height/4*height_r));
        type_panel.setBorder(BorderFactory.createLineBorder(new Color(234,234,234)));
        type_panel.setBackground(new Color(224,224,224));
        add(type_panel);
        //发送消息按钮
        send_button= new JButton();
        send_button.setFocusPainted(false);
        send_button_height=70;
        send_button_width=140;
        send_button.setBackground(new Color(30,111,255));
        send_button.setFont(new Font("宋体",Font.PLAIN,(int)(25*width_r)));
        send_button.setText("发送");
        send_button.setForeground(new Color(255,255,255));
        send_button.setBounds((int)((width-send_button_width-1)*width_r),(int)((height/4-send_button_height-30)*height_r),(int)(send_button_width*width_r),(int)(send_button_height*height_r));
        type_panel.add(send_button);
        //发送文件按钮
        send_file_button= new JButton();
        send_file_button.setFocusPainted(false);
        send_file_button.setBackground(new Color(30,111,255));
        send_file_button.setFont(new Font("宋体",Font.PLAIN,(int)(25*width_r)));
        send_file_button.setText("文件");
        send_file_button.setForeground(new Color(255,255,255));
        send_file_button.setBounds((int)((width-send_button_width-send_button_width-1)*width_r),(int)((height/4-send_button_height-30)*height_r),(int)(send_button_width*width_r),(int)(send_button_height*height_r));
        type_panel.add(send_file_button);
        send_file_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                file_panel.createWindow(0);
            }
        });
        //关闭该聊天框按钮
        close_button= new JButton();
        close_button.setFocusPainted(false);
        close_button.setBackground(new Color(211,10,11));
        close_button.setFont(new Font("宋体",Font.PLAIN,(int)(25*width_r)));
        close_button.setText("关闭");
        close_button.setForeground(new Color(255,255,255));
        close_button.setBounds((int)((width-3*send_button_width-1.5)*width_r),(int)((height/4-send_button_height-30)*height_r),(int)(send_button_width*width_r),(int)(send_button_height*height_r));
        type_panel.add(close_button);
        close_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                main_panel.buttonPanel.setVisible(true);
            }
        });

        //接受文件按钮
        int receive_button_height=70;
        int receive_button_width=140;
        receive_button= new JButton();
        receive_button.setFocusPainted(false);
        receive_button.setBackground(new Color(10, 211, 87));
        receive_button.setFont(new Font("宋体",Font.PLAIN,(int)(25*width_r)));
        receive_button.setText("接收");
        receive_button.setForeground(new Color(255,255,255));
        receive_button.setBounds((int)((width-3*send_button_width-1.5)*width_r),(int)((height/4-send_button_height-30)*height_r),(int)(send_button_width*width_r),(int)(send_button_height*height_r));
        type_panel.add(receive_button);
        receive_button.setVisible(false);
        receive_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                receive_panel.createWindow(files);
            }
        });
        //输入消息框
        type_field=new JTextArea();
        type_field.setBounds(0,0,(int)(width*width_r),(int)((height/4-1)*height_r));
        type_field.setBorder(BorderFactory.createLineBorder(new Color(224,224,224)));
        type_field.setBackground(new Color(245,246,247));
        type_field.setFont(new Font("宋体",Font.PLAIN,(int)(25*width_r)));
        type_panel.add(type_field);
        //发送信息
        send_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(myInfo.getType()!=3){
                    //发送信息函数
                    Client_qicq.send_message(type_field.getText(), myInfo.getId(),friend.getId());
                    try {
                        Client_qicq.get_message(friend.getId());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else {
                    Client_qicq.add_announcement(myInfo.getId(),type_field.getText());
                    Client_qicq.get_announcement();
                }
                type_field.setText("");

            }
        });

        //显示消息界面
        scrollPane=new JScrollPane(jTextPane);
        scrollPane.setBounds(0,0,(int)(width*width_r),(int)(height/4*3*height_r));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(234,234,234)));
        scrollPane.setBackground(new Color(245,246,247));
        jTextPane.setBackground(new Color(245,246,247));

        JScrollBar scrollBar = new JScrollBar(JScrollBar.VERTICAL) {
            @Override
            public boolean isVisible() {
                return true;
            }
        };
        scrollPane.setVerticalScrollBar(scrollBar);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollBar.setUnitIncrement(30);

        add(scrollPane);
    }

    /**
     * 显示公告
     *
     * @param messages 消息
     */
    public static void show_announcement(ArrayList<Message> messages) {
        jTextPane.setText(null);
        //开头空格
        SimpleAttributeSet set = new SimpleAttributeSet();
        StyleConstants.setFontSize(set, 0);//设置文本大小
        StyleConstants.setAlignment(set, 1);//设置文本对齐方式
        doc.setParagraphAttributes(jTextPane.getText().length(), doc.getLength() - jTextPane.getText().length(), set, false);
        try {
            doc.insertString(doc.getLength(), " ", set);//插入文本
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        insertText("公告",Color.black,(int)(45*width_r*height_r),1);
        int num=messages.size();
        for(int i=num-1;i>=0;i--) {
                insertText(messages.get(i).getSendTime(),new Color(122,122,123),(int)(16*width_r),1);
                insertText((String)(messages.get(i).getData()),Color.black,(int)(42*width_r),1);
        }

        jTextPane.setEditable(false);
       // System.out.println(1);
        jTextPane.updateUI();
        scrollPane.updateUI();

        jTextPane.setCaretPosition(jTextPane.getStyledDocument().getLength());
    }

    /**
     * 得到朋友
     *
     * @return {@link Friend}
     */
    public Friend getFriend() {
        return friend;
    }

    /**
     * 设置关闭
     */
    public void set_Close() {
        close_button.setLocation((int)((width-send_button_width-1)*width_r),(int)((height/4-send_button_height-30)*height_r));
    }
}
