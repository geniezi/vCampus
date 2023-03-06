package UIviewer.QQ;

import DAO.QICQ.Friend;
import ClientToServer.myInfo;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

/**
 * 朋友列表
 *
 * @author Zhangyang_Ge
 * @date 2022/09/03
 */
public class friend_list{
    public static JPanel jPanel;
    public static JPanel roll_panel;
    private static int width;

    static int number_per_page=7;//一页几个好友,即好友条长度
    private static int height;
    private static double width_r;
    private static double height_r;
    static int count_friend,count_tag;
    static tag_slice[] tag=new tag_slice[10];//标签
    static int[] friend_start_tag=new int[10];//每个标签好友开始数
    static int[] friend_end_tag=new int[10];//每个标签好友结束数
    static friend_slice[] friendslice=new friend_slice[50];//好友
    static JScrollPane scrollPane;

    /**
     * 给朋友
     *
     * @param friend 朋友
     */
    public static void show_Friend(HashMap<String, ArrayList<Friend>> friend) {
        jPanel.removeAll();
        roll_panel=new JPanel();
        roll_panel.setBackground(new Color(255,255,255));
        roll_panel.setSize((int)(width*width_r),(int)((height-200)*height_r));
        roll_panel.setLayout(new BoxLayout(roll_panel, BoxLayout.Y_AXIS));
        scrollPane=new JScrollPane(roll_panel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );//不显示水平滚动条；
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(255,255,255)));
        jPanel.add(scrollPane, BorderLayout.CENTER);
        count_friend=0;
        count_tag=0;


       //System.out.println("shit3");
        //admin
        if(myInfo.getType()!=3){
            tag[count_tag]=new tag_slice(width_r,height_r,"管理员");
            roll_panel.add(tag[count_tag]);
            Friend admin=new Friend();
            admin.setName("公告");
            admin.setOnline(1);
            friend_start_tag[count_tag]=count_friend;
         //   System.out.println("shit4");
            friendslice[count_friend]=new friend_slice(width-1,height/number_per_page,width_r,height_r,admin);
            roll_panel.add(friendslice[count_friend++]);
            friend_end_tag[count_tag]=count_friend;
            int count_tag1=count_tag;
            tag[count_tag1].addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    if(friendslice[friend_start_tag[count_tag1]].isVisible()==true) {
                        for (int i = friend_start_tag[count_tag1]; i < friend_end_tag[count_tag1]; i++) {
                            friendslice[i].setVisible(false);
                        }
                    }
                    else{
                        for (int i = friend_start_tag[count_tag1]; i < friend_end_tag[count_tag1]; i++) {
                            friendslice[i].setVisible(true);
                        }
                    }
                }
            });
            count_tag++;
        }
        for(String Tag: friend.keySet()){
            tag[count_tag]=new tag_slice(width_r,height_r,Tag);
            roll_panel.add(tag[count_tag]);
            ArrayList<Friend> arrayList=friend.get(Tag);
            int num=arrayList.size();


            friend_start_tag[count_tag]=count_friend;
            for(int i=0;i<num;i++){
                friendslice[count_friend]=new friend_slice(width-1,height/number_per_page,width_r,height_r,arrayList.get(i));
                roll_panel.add(friendslice[count_friend++]);
            }
            friend_end_tag[count_tag]=count_friend;
            int count_tag1=count_tag;
            tag[count_tag1].addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    if(friendslice[friend_start_tag[count_tag1]].isVisible()==true) {
                        for (int i = friend_start_tag[count_tag1]; i < friend_end_tag[count_tag1]; i++) {
                            friendslice[i].setVisible(false);
                        }
                    }
                    else{
                        for (int i = friend_start_tag[count_tag1]; i < friend_end_tag[count_tag1]; i++) {
                            friendslice[i].setVisible(true);
                        }
                    }
                }
            });
            count_tag++;
        }
        update();
    }

    /**
     * 更新
     */
    public static void update(){//更新UI界面；
        jPanel.updateUI();
    }

    /**
     * 显示未读
     *
     * @param sender 发送方
     */
    public static void show_unread(String sender){
      //  System.out.println(11111);
        for(int i=1;i<count_friend;i++){
         //   System.out.println(friendslice[i].getFriend().getId());
            if(friendslice[i].getFriend().getId().equals(sender)){
                friendslice[i].setunread(sender);
            }
        }
    }

    /**
     * 朋友列表
     *
     * @param width    宽度
     * @param height   高度
     * @param width_r  宽度r
     * @param height_r 高r
     * @param x        x
     * @param y        y
     */
    friend_list(int width, int height, double width_r, double height_r, int x, int y){
        jPanel=new JPanel();
        this.width=width;
        this.height=height;
        this.width_r=width_r;
        this.height_r=height_r;
        jPanel.setLayout(new BorderLayout());
        jPanel.setBounds((int)(x*width_r),(int)(y*height_r),(int)(width*width_r),(int)(height*height_r));
        jPanel.setBorder(BorderFactory.createLineBorder(new Color(244,244,244)));
        jPanel.setBackground(new Color(255,255,255));
   }

}
