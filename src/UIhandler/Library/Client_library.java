package UIhandler.Library;

import ClientToServer.ManageClientToServerThread;
import DAO.Library.Book_admin;
import DAO.Library.Book_borrower;
import DAO.Library.Punishment;
import UIviewer.Library.*;
import UIviewer.login.functionChoose;
import message.Message;
import message.MessageType;
import utils.MyObjectOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import utils.MyObjectInputStream;
import utils.MyObjectOutputStream;

import javax.swing.*;

import static UIviewer.Library.readLib.cardLayout;
import static UIviewer.Library.readLib.panel;

/**
 * 客户端库
 *
 * @author Chen_GuanZhi
 * @date 2022/09/03
 */
public class Client_library {
    static String id;
    public static String lastsearch;
    public static MyObjectOutputStream oos=null;

    /**
     * 得到id
     *
     * @return {@link String}
     */
    public static String getId() {
        return id;
    }

    /**
     * 设置oos
     *
     * @param mos socket.outpustream
     */
    public static void setOos(MyObjectOutputStream mos){
        oos=mos;
    }

    /**
     * 组id
     *
     * @param id id
     */
    public static void setId(String id) {
        Client_library.id = id;
    }


    /**
     * requireshow所有书
     *
     * @throws IOException ioexception
     *///管理员查看所有图书的请求和处理
    public static void RequireshowAllBooks()throws IOException{
        AllBooks.tableDate=null;
        Message message=new Message();
        message.setType(MessageType.MESSAGE_LIBRARY_ADMIN_LIST);
        oos.writeObject(message);
    }

    /**
     * 显示所有图书
     *
     * @param books 书
     * @throws IOException ioexception
     */
    public static void showAllBooks(ArrayList<Book_admin>books)throws IOException{
        int n= books.size();
       // System.out.println(n);
        AllBooks.tableDate=new String[n][11];
        for(int i=0;i<n;i++)
        {
            Book_admin book=(Book_admin) books.get(i);
            AllBooks.tableDate[i][0]=book.getID();
            AllBooks.tableDate[i][1]=book.getName();
            AllBooks.tableDate[i][2]=book.getAuthor();
            AllBooks.tableDate[i][3]=book.getPublisher();
            AllBooks.tableDate[i][4]=book.getCountry();
            AllBooks.tableDate[i][5]=String.valueOf(book.getPrice());
            if(book.getAvailable()==1) {
                AllBooks.tableDate[i][6]="可借";
            }
            else{
                AllBooks.tableDate[i][6]="借出";
            }
            AllBooks.tableDate[i][7]=book.getDate_borrow();
            AllBooks.tableDate[i][8]=book.getBorrow_to();
            AllBooks.tableDate[i][9]=book.getDate_expire();
            AllBooks.tableDate[i][10]=book.getPlace();
        }
        AllBooks fa=new AllBooks();
        UIviewer.Library.adminLib.panel.add(fa,"fa");
        UIviewer.Library.adminLib.cardLayout.show(UIviewer.Library.adminLib.panel,"fa");
    }

    /**
     * 需要添加书
     *
     * @param b b
     * @throws IOException ioexception
     *///增加书籍的请求和处理
    public static void RequireAddBook(Book_admin b)throws IOException{
        Message message=new Message();
        message.setData(b);
        message.setType(MessageType.MESSAGE_LIBRARY_ADMIN_ADD);
        oos.writeObject(message);
    }

    /**
     * 需要删除书
     *
     * @param deleteID 删除id
     * @throws IOException ioexception
     *///删除书籍的请求和处理
    public static void RequireDeleteBook(String deleteID) throws IOException {
        Message message=new Message();
        message.setData(deleteID);
        message.setType(MessageType.MESSAGE_LIBRARY_ADMIN_DELETE);
        oos.writeObject(message);
    }

    /**
     * 需要新惩罚
     *
     * @param p p
     * @throws IOException ioexception
     *///管理员新开一个罚单
    public static void RequireNewPunishment(Punishment p)throws IOException{
        applyTicket.myPunish=null;
        Message message=new Message();
        message.setData(p);
        message.setType(MessageType.MESSAGE_LIBRARY_ADMIN_GIVE_TICKET);
        oos.writeObject(message);
    }

    /**
     * 需要我书
     *
     * @throws IOException ioexception
     *///用户查看自己图书
    public static void RequireMyBooks()throws IOException{
        myBook.myBook=null;
        Message message=new Message();
        message.setType(MessageType.MESSAGE_LIBRARY_LIST_MY_BOOK);
        oos.writeObject(message);
    }

    /**
     * 给我书
     *
     * @param books 书
     * @throws IOException          ioexception
     * @throws InterruptedException 中断异常
     */
    public static void showMyBooks(ArrayList<Book_borrower>books) throws IOException, InterruptedException {
        int n= books.size();
        myBook.myBook=new String[n][10];
       // System.out.println(n);
       for(int i=0;i<n;i++)
        {
            Book_borrower book=(Book_borrower) books.get(i);
            myBook.myBook[i][0]=book.getId();
            myBook.myBook[i][1]=book.getName();
            myBook.myBook[i][2]=book.getAuthor();
            myBook.myBook[i][3]=book.getPublisher();
            myBook.myBook[i][4]=book.getCountry();
            myBook.myBook[i][5]=book.getDate_borrow();
            myBook.myBook[i][6]=book.getDate_expire();
            myBook.myBook[i][7]="        归还";
            myBook.myBook[i][8]="        续借";
        }
        myBook f2=new myBook();
        panel.add(f2,"f2");
        cardLayout.show(panel, "f2");
    }

    /**
     * 需要我惩罚
     *
     * @throws IOException ioexception
     *///查看自己的罚单
    public static void RequireMyPunishments()throws IOException{
        applyTicket.myPunish=null;
        Message message=new Message();
        message.setType(MessageType.MESSAGE_LIBRARY_LIST_MY_TICKET);
        oos.writeObject(message);
    }


    /**
     * 给我惩罚
     *
     * @param myPunishments 我惩罚
     * @throws IOException ioexception
     */
    public static void showMyPunishments(ArrayList<Punishment>myPunishments)throws IOException{
        int n= myPunishments.size();
       // System.out.println(n);
        applyTicket.myPunish=new String[n][5];
        for(int i=0;i<n;i++)
        {
            Punishment punishment=myPunishments.get(i);
            applyTicket.myPunish[i][0]=punishment.getPunishmentID();
            applyTicket.myPunish[i][1]=String.valueOf(punishment.getPrice());
            applyTicket.myPunish[i][2]=punishment.getBook_id();
            applyTicket.myPunish[i][3]=punishment.getNotice();
            applyTicket.myPunish[i][4]="            缴费";
        }
        applyTicket f3= new applyTicket();
        panel.add(f3,"f3");
        cardLayout.show(panel, "f3");
    }

    /**
     * 需要搜索结果
     *
     * @param searchInfo 搜索信息
     * @throws IOException ioexception
     *///搜索书籍
    public static void RequireSearchResult(String searchInfo)throws IOException{
        lastsearch=searchInfo;
        searchResult.searchresult=null;
        Message message=new Message();
        message.setData(searchInfo);
        message.setType(MessageType.MESSAGE_LIBRARY_QUERY);
        oos.writeObject(message);
     //   System.out.println("search  "+searchInfo);
    }

    /**
     * 显示搜索结果
     *
     * @param books 书
     * @throws IOException ioexception
     */
    public static void showSearchResult(ArrayList<Book_borrower> books)throws IOException{
        int n= books.size();
      //  System.out.println(n);
        searchResult.searchresult=new String[n][9];
        Iterator b= books.iterator();
        int count=0;
        while(b.hasNext())
        {
            Book_borrower book=(Book_borrower) b.next();
            searchResult.searchresult[count][0]=book.getId();
            searchResult.searchresult[count][1]=book.getName();
            searchResult.searchresult[count][2]=book.getAuthor();
            searchResult.searchresult[count][3]=book.getPublisher();
            searchResult.searchresult[count][4]=book.getCountry();
            if(book.getAvailable()==1)
            {
                searchResult.searchresult[count][5]="可借";
            }
            else {
                searchResult.searchresult[count][5]="已借出";
            }
            searchResult.searchresult[count][6]=book.getDate_expire();
            searchResult.searchresult[count][7]=book.getPlace();
            if(book.getAvailable()==1) {
                searchResult.searchresult[count][8] = "     借阅";
            }
            else{
                searchResult.searchresult[count][8] = "";
            }
            count++;
        }
        while(searchResult.searchresult==null);
        searchResult search=new searchResult();
        panel.add(search,"search");
        cardLayout.show(panel,"search");
    }

    /**
     * reqire返回
     *
     * @param rBook r书
     * @throws IOException ioexception
     */
    public static void reqireReturn(Book_borrower rBook)throws IOException{
        Message message=new Message();
        message.setData(rBook);
        message.setType(MessageType.MESSAGE_LIBRARY_RET);
        oos.writeObject(message);
    }

    /**
     * reqire扩展
     *
     * @param eBook e书
     * @throws IOException ioexception
     */
    public static void reqireExtend(Book_borrower eBook)throws IOException{
        Message message=new Message();
        message.setData(eBook);
        message.setType(MessageType.MESSAGE_LIBRARY_EXTEND);
        oos.writeObject(message);
    }

    /**
     * reqire借
     *
     * @param bBook b书
     * @throws IOException ioexception
     */
    public static void reqireBorrow(Book_borrower bBook)throws IOException{
        Message message=new Message();
        message.setData(bBook);
        message.setType(MessageType.MESSAGE_LIBRARY_BORROW);
        oos.writeObject(message);
    }

    /**
     * reqire支付
     *
     * @param punishment 惩罚
     * @throws IOException ioexception
     */
    public static void reqirePay(Punishment punishment)throws IOException{
        Message message=new Message();
        message.setData(punishment);
        message.setType(MessageType.MESSAGE_LIBRARY_PAY);
        oos.writeObject(message);
    }

    /**
     * 管理员输入
     *
     * @throws IOException ioexception
     */
    public static void admin_enter()throws IOException{
        Message message=new Message();
        message.setType(MessageType.MESSAGE_LIBRARY_ENTER);
        oos.writeObject(message);
    }

    /**
     * 管理员输入结果
     *
     * @param books 书
     * @throws IOException ioexception
     */
    public static void admin_enter_result(ArrayList<Book_admin>books) throws IOException {
        int n= books.size();
      //  System.out.println(n);
        AllBooks.tableDate=new String[n][11];
        for(int i=0;i<n;i++)
        {
            Book_admin book=books.get(i);
            AllBooks.tableDate[i][0]=book.getID();
            AllBooks.tableDate[i][1]=book.getName();
            AllBooks.tableDate[i][2]=book.getAuthor();
            AllBooks.tableDate[i][3]=book.getPublisher();
            AllBooks.tableDate[i][4]=book.getCountry();
            AllBooks.tableDate[i][5]=String.valueOf(book.getPrice());
            if(book.getAvailable()==1) {
                AllBooks.tableDate[i][6]="可借";
            }
            else{
                AllBooks.tableDate[i][6]="借出";
            }
            AllBooks.tableDate[i][7]=book.getDate_borrow();
            AllBooks.tableDate[i][8]=book.getBorrow_to();
            AllBooks.tableDate[i][9]=book.getDate_expire();
            AllBooks.tableDate[i][10]=book.getPlace();
        }
        //AllBooks fa=new AllBooks();
        //UIviewer.Library.adminLib.panel.add(fa,"fa");
        //UIviewer.Library.adminLib.cardLayout.show(UIviewer.Library.adminLib.panel,"fa");
        Client_library.RequireshowAllBooks();
        functionChoose.jf.setContentPane(new adminLib());
        functionChoose.jf.setTitle("adminLib");
        functionChoose.jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        functionChoose.jf.setVisible(true);
    }
}


