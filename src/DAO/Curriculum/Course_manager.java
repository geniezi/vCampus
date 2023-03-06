package DAO.Curriculum;


import ClientToServer.ManageClientToServerThread;
import ServerToClient.ServerToClient;
import User.Student;
import connection.JDBC_Connector;
import message.Message;
import message.MessageType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

/**
 * 课程管理
 *@description 管理选课模块的数据库操作
 * @author Shuheng_Gu
 * @date 2022/09/03
 *///gsh
public class Course_manager {
    /**
     * id
     */
    private String id;
    /**
     * JDBC链接
     */
    private static Connection conn;
    /**
     * 类型
     */
    private int type;

    /**
     * 课程管理构造函数
     *
     * @param id id
     */
    public Course_manager(String id) {
        this.id = id;
        try {
            conn= JDBC_Connector.ConnectMySQL();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 把上课时间从String转为数组，方便设计课表
     *
     * @param s 年代
     * @return {@link int[][][]}
     */
    public int[][][] change(String s){
        int[][][] a = new int[17][6][14];
        int week_l,week_r,day,sec_l,sec_r;
        String ss[]=s.split(",");
        //System.out.println(s);
        for(int i=0;i<ss.length;i++){
            int index=ss[i].indexOf("星期");
            switch (ss[i].substring(index+2, index+3)){
                case "一":
                    day=1;
                    break;
                case "二":
                    day=2;
                    break;
                case "三":
                    day=3;
                    break;
                case "四":
                    day=4;
                    break;
                default:
                    day=5;
                    break;
            }
            int index1=ss[i].indexOf("-");
            week_l=Integer.parseInt(ss[i].substring(0,index1));
            int index2=ss[i].indexOf("周");
            week_r=Integer.parseInt(ss[i].substring(index1+1,index2));

            int index3=ss[i].indexOf("-",index+1);
            sec_l=Integer.parseInt(ss[i].substring(index+4,index3));
            int index4=ss[i].indexOf("节");
            sec_r=Integer.parseInt(ss[i].substring(index3+1,index4));
            //   System.out.println(week_l+"->"+week_r+" "+day+" "+sec_l+"->"+sec_r);
            for(int p=week_l;p<=week_r;p++){
                for(int q=sec_l;q<=sec_r;q++){
                    a[p][day][q]=1;
                }
            }
        }
        return a;
    }

    /**
     * 查询课程
     *
     * @param s 课程名/教师/编号
     * @return {@link ArrayList}<{@link Course}>
     * @throws SQLException sqlexception异常
     */
    public ArrayList<Course> query_courses(String s) throws SQLException {
        ArrayList<Course> courses = new ArrayList<Course>();
        String sql="select * from curriculum where id like ? or name like ? or teacher like ? order by id;";
        String parse="%"+s+"%";
        PreparedStatement st=conn.prepareStatement(sql);
        st.setString(1,parse);
        st.setString(2,parse);
        st.setString(3,parse);
        ResultSet rs=st.executeQuery();
        while (rs.next()){
            Course x=new Course();
            x.setHour(rs.getInt("hour"));
            x.name=rs.getString("name");
            x.teacher=rs.getString("teacher");
            x.classroom=rs.getString("classroom");
            x.point=rs.getDouble("point");
            x.size=rs.getInt("size");
            x.id=rs.getString("id");
            if(rs.getString("time")!=null) x.class_time=change(rs.getString("time"));
            x.setTimestring(rs.getString("time"));
            courses.add(x);
        }
        return courses;
    }

    /**
     * 列出所有课程
     *
     * @return {@link ArrayList}<{@link Course}>
     * @throws SQLException sqlexception异常
     */
    public ArrayList<Course> list_all_courses() throws SQLException {
        ArrayList<Course> courses = new ArrayList<Course>();
        String sql="select * from curriculum order by id;";
        PreparedStatement st=conn.prepareStatement(sql);
        ResultSet rs=st.executeQuery();
        while(rs.next())
        {
            Course x=new Course();
            x.setHour(rs.getInt("hour"));
            x.name=rs.getString("name");
            x.teacher=rs.getString("teacher");
            x.classroom=rs.getString("classroom");
            x.point=rs.getDouble("point");
            x.size=rs.getInt("size");
            x.id=rs.getString("id");
            if(rs.getString("time")!=null) x.class_time=change(rs.getString("time"));
            x.setTimestring(rs.getString("time"));
            courses.add(x);
        }
        rs.close();
        st.close();
        return courses;
    }

    /**
     * 得到我的课程列表
     *
     * @return {@link ArrayList}<{@link Course}>
     * @throws SQLException sqlexception异常
     */
    public ArrayList<Course> list_my_courses() throws SQLException {
        ArrayList<Course> courses = new ArrayList<Course>();
        String sql="select * from elective where stu_id=? order by course_id;";
        PreparedStatement st=conn.prepareStatement(sql);
        st.setString(1,id);
        ResultSet rs=st.executeQuery();
        while(rs.next())
        {
            String course_id=rs.getString("course_id");
            sql="select * from curriculum where id=?;";
            st=conn.prepareStatement(sql);
            st.setString(1,course_id);
            ResultSet rs1=st.executeQuery();
            while(rs1.next()){
                Course x=new Course();
                x.name=rs1.getString("name");
                x.teacher=rs1.getString("teacher");
                x.classroom=rs1.getString("classroom");
                x.point=rs1.getDouble("point");
                x.size=rs1.getInt("size");
                x.id=rs1.getString("id");
                x.setTimestring(rs1.getString("time"));
                if(rs1.getString("time")!=null) x.class_time=change(rs1.getString("time"));
                courses.add(x);
            }
        }
        rs.close();
        st.close();
        return courses;
    }

    /**
     * 老师的课程列表
     *
     * @return {@link ArrayList}<{@link Course}>
     * @throws SQLException sqlexception异常
     */
    public ArrayList<Course>list_tea_course() throws SQLException{
        ArrayList<Course> courses = new ArrayList<Course>();
        String sql="select * from teaching where tea_id=? order by course_id;";
        PreparedStatement st=conn.prepareStatement(sql);
        st.setString(1,id);
        ResultSet rs=st.executeQuery();
        while(rs.next())
        {
            String course_id=rs.getString("course_id");
            sql="select * from curriculum where id=?;";
            st=conn.prepareStatement(sql);
            st.setString(1,course_id);
            ResultSet rs1=st.executeQuery();
            if(rs1.next()){
                Course x=new Course();
                x.setHour(rs1.getInt("hour"));
                x.name=rs1.getString("name");
                x.teacher=rs1.getString("teacher");
                x.classroom=rs1.getString("classroom");
                x.point=rs1.getDouble("point");
                x.size=rs1.getInt("size");
                x.id=rs1.getString("id");
                x.setTimestring(rs1.getString("time"));
                if(rs1.getString("time")!=null) x.class_time=change(rs1.getString("time"));
                courses.add(x);
            }
        }
        rs.close();
        st.close();
        return courses;
    }

    /**
     * 选课
     *
     * @param c 课程
     * @return {@link Message}
     * @throws SQLException sqlexception异常
     */
    public Message choose(Course c) throws SQLException {
        ArrayList<Course> courses = list_my_courses();
        int [][][]a=new int[17][6][14];
        Iterator it = courses.iterator();
        while(it.hasNext()){
            Course cc=(Course) it.next();
            for(int p=1;p<=16;p++){
                for(int q=1;q<=5;q++){
                    for(int r=1;r<=13;r++){
                        a[p][q][r]|=cc.class_time[p][q][r];
                    }
                }
            }
        }
        int confict=0;
        c.class_time=change(c.timestring);
        for(int p=1;p<=16;p++){
            for(int q=1;q<=5;q++){
                for(int r=1;r<=13;r++){
                    if(a[p][q][r]==1&&c.class_time[p][q][r]==1){
                        confict=1;
                        break;
                    }
                }
            }
        }
        Message message=new Message();
        if(confict==1)
        {
            message.setType(MessageType.MESSAGE_CURRICULUM_CHOOSE_CONFLICT);
            return message;
        }
        String sql="select * from curriculum where id=?;";
        PreparedStatement st=conn.prepareStatement(sql);
        st.setString(1,c.id);
        ResultSet rs=st.executeQuery();
        int size=0;
        if (rs.next()) size=rs.getInt("size");
        sql="select count(*) as total from elective where course_id=?;";
        st=conn.prepareStatement(sql);
        st.setString(1,c.id);
        rs=st.executeQuery();
        rs.next();
        if(Integer.valueOf(rs.getString(1))==size){
            message.setType(MessageType.MESSAGE_CURRICULUM_CHOOSE_FULL);
            return message;
        }
        message.setType(MessageType.MESSAGE_CURRICULUM_CHOOSE_SUCCEED);
        sql="insert into elective(course_id,stu_id) values(?,?);";
        st=conn.prepareStatement(sql);
        st.setString(1,c.id);
        st.setString(2,id);
        st.executeUpdate();
        rs.close();
        st.close();
        return message;
    }

    /**
     * 我可以选择的课程列表
     *
     * @return {@link ArrayList}<{@link Course}>
     * @throws SQLException sqlexception异常
     */
    public ArrayList<Course> list_I_can_choose() throws SQLException {
        ArrayList<Course>courses=new ArrayList<>();
        String sql="select * from curriculum order by id;";
        PreparedStatement st=conn.prepareStatement(sql);
        ResultSet rs=st.executeQuery();
        while (rs.next()){
            sql="select * from elective where stu_id=? and course_id=?;";
            st=conn.prepareStatement(sql);
            st.setString(1,id);
            st.setString(2,rs.getString("id"));
            ResultSet rs1=st.executeQuery();
            if(rs1.next()){

            }
            else {
                Course x=new Course();
                x.name=rs.getString("name");
                x.teacher=rs.getString("teacher");
                x.classroom=rs.getString("classroom");
                x.point=rs.getDouble("point");
                x.size=rs.getInt("size");
                x.id=rs.getString("id");
                if(rs.getString("time")!=null) x.class_time=change(rs.getString("time"));
                x.setTimestring(rs.getString("time"));
                courses.add(x);
            }
        }
        rs.close();
        st.close();
        return courses;
    }

    /**
     * 退选课程
     *
     * @param s 课程id
     * @throws SQLException sqlexception异常
     */
    public void drop(String s) throws SQLException {
        String sql="delete from elective where course_id=? and stu_id=?;";
        PreparedStatement st=conn.prepareStatement(sql);
        st.setString(1,s);
        st.setString(2,id);
        st.executeUpdate();
        st.close();
    }

    /**
     * 添加课程
     *
     * @param c 课程
     * @throws SQLException sqlexception异常
     */
    public void add(Course c) throws SQLException {
        String sql="insert into curriculum(name,time,point,teacher,size,id,classroom) values(?,?,?,?,?,?,?,?);";
        PreparedStatement st=conn.prepareStatement(sql);
        st.setString(1,c.name);
        st.setString(2,c.timestring);
        st.setDouble(3,c.point);
        st.setString(4,c.teacher);
        st.setInt(5,c.size);
        st.setString(6,c.id);
        st.setString(7,c.classroom);
        st.executeUpdate();
        st.close();
    }

    /**
     * 删除课程
     *
     * @param s 课程id
     * @throws SQLException sqlexception异常
     */
    public void delete(String s) throws SQLException {
        String sql="delete from curriculum where id=?;";
        PreparedStatement st=conn.prepareStatement(sql);
        st.setString(1,s);
        st.executeUpdate();
        st.close();
    }

    /**
     * 时间表
     *
     * @return {@link String[][][]}
     * @throws SQLException sqlexception异常
     */
    public String[][][] schedule() throws SQLException {
        String [][][]ans=new String[17][6][14];
        ArrayList<Course>courses=list_my_courses();
        Iterator it = courses.iterator();
        while(it.hasNext()){
            Course cc=(Course) it.next();
            for(int p=1;p<=16;p++){
                for(int q=1;q<=5;q++){
                    for(int r=1;r<=13;r++){
                        if(cc.class_time[p][q][r]==1) {
                            ans[p][q][r]=cc.name;
                            //        System.out.println(cc.name);
                        }
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 老师课程表
     *
     * @return {@link String[][][]}
     * @throws SQLException sqlexception异常
     */
    public String[][][] list_tea_schedule()throws SQLException{
        String [][][]ans=new String[17][6][14];
        ArrayList<Course>courses=list_tea_course();
        Iterator it = courses.iterator();
        while(it.hasNext()){
            Course cc=(Course) it.next();
            for(int p=1;p<=16;p++){
                for(int q=1;q<=5;q++){
                    for(int r=1;r<=13;r++){
                        if(cc.class_time[p][q][r]==1) {
                            //        System.out.println(cc.name);
                            ans[p][q][r]=cc.name;
                        }
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 申请开课
     *
     * @param c 开课
     * @return {@link Message}
     * @throws SQLException sqlexception异常
     */
    public Message apply(Opencourse c) throws SQLException {
        String sql="select * from curriculum where name=?;";
        PreparedStatement st=conn.prepareStatement(sql);
        st.setString(1,c.name);
        ResultSet rs=st.executeQuery();
        Message message=new Message();
        if(rs.next()){
            message.setType(MessageType.MESSAGE_CURRICULUM_APPLY_FAIL);
        }
        else{
            message.setType(MessageType.MESSAGE_CURRICULUM_APPLY_SUCCEED);
            String sql1="select count(*) as total from opencourse;";
            PreparedStatement st1= conn.prepareStatement(sql1);
            ResultSet rs1=st1.executeQuery();
            rs1.next();
            String newid=String.valueOf(rs1.getInt("total")+1);
            System.out.println(newid);
            //ServerToClient.add_opencourse(c);
            sql="insert into opencourse(name,teacher_name,teacher_id,point,hour,status,id) values(?,?,?,?,?,0,?);";
            st= conn.prepareStatement(sql);
            st.setString(1,c.getName());
            st.setString(2,c.getTeacher());
            st.setString(3,c.getTeacher_id());
            st.setDouble(4,c.getPoint());
            st.setInt(5,c.getHour());
            st.setString(6,newid);
            st.executeUpdate();
            st1.close();
            rs1.close();
        }
        rs.close();
        st.close();
        return message;
    }

    /**
     * 拒绝开课
     *
     * @param course_id 课程id
     * @param reason    原因
     * @throws IOException  ioexception
     * @throws SQLException sqlexception异常
     */
    public void refuse(String course_id,String reason) throws IOException, SQLException {
        String sql="update opencourse set status=1, comment=? where id=?;";
        PreparedStatement st= conn.prepareStatement(sql);
        st.setString(1,reason);
        st.setString(2,course_id);
        st.executeUpdate();
        st.close();

    }

    /**
     * 批准开课
     *
     * @param c 课程
     * @throws SQLException sqlexception异常
     */
    public void approve(Course c) throws SQLException {
        String sql="update opencourse set status=2, comment=? where id=?;";
        PreparedStatement st= conn.prepareStatement(sql);
        st.setString(1,"同意开课");
        st.setString(2,c.id);
        st.executeUpdate();
        Random r=new Random();
        String new_id="B09"+String.valueOf(r.nextInt(900)+100)+String.valueOf(r.nextInt(900)+100)+"01";
        sql="insert into curriculum(id,name,teacher,hour,point,size,classroom,time) values(?,?,?,?,?,null,null,null);";
        st= conn.prepareStatement(sql);
        st.setString(1,new_id);
        st.setString(2,c.name);
        st.setString(3,c.teacher);
        st.setInt(4,c.getHour());
        st.setDouble(5,c.point);
        st.executeUpdate();
        sql="select teacher_id from opencourse where id=?;";
        st= conn.prepareStatement(sql);
        st.setString(1,c.id);
        ResultSet rs=st.executeQuery();
        rs.next();
        String tid=rs.getString(1);
        sql="insert into teaching(tea_id,course_id) values(?,?);";
        System.out.println(tid+"    "+new_id);
        st=conn.prepareStatement(sql);
        st.setString(1,tid);
        st.setString(2,new_id);
        st.executeUpdate();

        rs.close();
        st.close();
    }

    /**
     * opencourse列表
     *
     * @param id 教师id
     * @return {@link ArrayList}<{@link Opencourse}>
     * @throws SQLException sqlexception异常
     */
    public ArrayList<Opencourse> list_tea_opencourse(String id) throws SQLException{
        ArrayList<Opencourse>opencourses=new ArrayList<>();
        String sql="select * from opencourse where teacher_id=?";
        PreparedStatement st=conn.prepareStatement(sql);
        st.setString(1,id);
        ResultSet rs=st.executeQuery();
        while(rs.next()){
            Opencourse c=new Opencourse();
            c.setId(rs.getString("id"));
            c.setName(rs.getString("name"));
            c.setTeacher(rs.getString("teacher_name"));
            c.setPoint(rs.getDouble("point"));
            c.setHour(rs.getInt("hour"));
            c.status=rs.getInt("status");
            c.result=rs.getString("comment");
            c.setTeacher_id(rs.getString("teacher_id"));
            opencourses.add(c);
        }
        rs.close();
        st.close();
        return opencourses;
    }

    /**
     * 管理员列出开课申请
     *
     * @return {@link Message}
     * @throws SQLException sqlexception异常
     */
    public Message admin_list_application() throws SQLException {
        Message message=new Message();
        message.setType(MessageType.MESSAGE_CURRICULUM_LIST_ADMIN_APPLICATION_RET);
        ArrayList<Opencourse>opencourses=new ArrayList<>();
        String sql="select * from opencourse where status=0";
        PreparedStatement st=conn.prepareStatement(sql);
        ResultSet rs=st.executeQuery();
        while(rs.next()){
            Opencourse c=new Opencourse();
            c.setId(rs.getString("id"));
            c.setName(rs.getString("name"));
            c.setTeacher(rs.getString("teacher_name"));
            c.setPoint(rs.getDouble("point"));
            c.setHour(rs.getInt("hour"));
            c.setTeacher_id(rs.getString("teacher_id"));
            opencourses.add(c);
        }
        st.close();
        rs.close();
        message.setData(opencourses);
        return message;
    }

    /**
     * 得到选课的学生
     *
     * @param s 课程id
     * @return {@link ArrayList}<{@link Student}>
     * @throws SQLException sqlexception异常
     */
    public ArrayList<Student>get_student(String s) throws SQLException {
        ArrayList<Student>res=new ArrayList<Student>();
        String sql="select * from curriculum where id=?;";
        PreparedStatement st=conn.prepareStatement(sql);
        st.setString(1,s);
        ResultSet rs=st.executeQuery();
        if(rs.next()){
            String id=rs.getString("id");
            sql="select * from elective where course_id=? order by stu_id;";
            st=conn.prepareStatement(sql);
            st.setString(1,id);
            rs=st.executeQuery();
            while(rs.next()){
                sql="select * from students where Student_idcard=?;";
                st=conn.prepareStatement(sql);
                st.setString(1,rs.getString("stu_id"));
                ResultSet rs1=st.executeQuery();
                rs1.next();
                Student student = new Student();
                student.setStudent_name(rs1.getString("Student_name"));
                student.setStudent_id(rs1.getString("Student_id"));
                res.add(student);
                rs1.close();
            }
        }
        rs.close();
        st.close();
        return res;
    }

    /**
     * 管理安排课程
     *
     * @param c 课程
     * @throws SQLException sqlexception异常
     */
    public void admin_arrange(Course c) throws SQLException {
        //     System.out.print("    "+c.classroom);
        String sql="update curriculum set classroom=?, time=?, size=? where id=?";
        PreparedStatement st= conn.prepareStatement(sql);
        st.setString(1,c.classroom);
        st.setString(2,c.timestring);
        st.setInt(3,c.size);
        st.setString(4,c.id);
        st.executeUpdate();
        st.close();
    }

    /**
     * 自动排课（待实现）
     *
     * @throws SQLException sqlexception异常
     */
    public void auto_arrage() throws SQLException {
        String sql="select * from curriculum;";
        PreparedStatement st=conn.prepareStatement(sql);
        ResultSet rs= st.executeQuery();

    }

}
