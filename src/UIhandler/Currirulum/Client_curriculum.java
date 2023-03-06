package UIhandler.Currirulum;

import ClientToServer.myInfo;
import DAO.Curriculum.Course;
import DAO.Curriculum.Opencourse;
import UIviewer.SelectCourse.Check_Coustatus;
import UIviewer.SelectCourse.Choosing_Course;
import UIviewer.SelectCourse.Search_result;
import User.Student;
import message.Message;
import message.MessageType;
import utils.MyObjectOutputStream;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import static UIviewer.SelectCourse.Selcourse.cardLayout;

import static UIviewer.SelectCourse.Selcourse.panel;
import UIviewer.SelectCourse.*;

/**
 * 客户课程
 *
 * @author Chen_GuanZhi
 * @date 2022/09/03
 */
public class Client_curriculum {

    String id;
    public static String lastconsult;
    static MyObjectOutputStream oos=null;

    /**
     * 用户id
     *
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 安排
     *
     * @param c c
     * @throws IOException ioexception
     */
    public static void arrange(Course c) throws IOException {
        Message message=new Message();
        message.setData(c);
        message.setType(MessageType.MESSAGE_CURRICULUM_ADMIN_ARRANGEMENT);
        oos.writeObject(message);
    }

    /**
     * 设置oos
     *
     * @param mos 输出流
     * @throws IOException ioexception
     */
    public static void setOos(MyObjectOutputStream mos) throws IOException {
        oos=mos;
    }

    /**
     * 需要选择
     *
     * @param curri 课程
     * @throws IOException ioexception
     */
    public static void requireToChoose(Course curri)throws IOException{
            Message message=new Message();
            message.setData(curri);
            message.setType(MessageType.MESSAGE_CURRICULUM_CHOOSE);
            oos.writeObject(message);
    }

    /**
     * 需要我选择
     *
     * @throws IOException ioexception
     */
    public static void RequireMyChoice() throws IOException {
        Message message=new Message();
        message.setData(myInfo.getId());
        message.setType(MessageType.MESSAGE_CURRICULUM_LIST_MINE);
        oos.writeObject(message);
    }

    /**
     * 显示我选择
     *
     * @param courses 课程
     */
    public static void showMyChoice(ArrayList<Course>courses){
        int n=courses.size();
        ConsultCourse_Chosen.consultCourse_chosen=new String[n][6];
        Iterator b=courses.iterator();
        int count=0;
        while(b.hasNext())
        {
            Course curri=(Course) b.next();
            ConsultCourse_Chosen.consultCourse_chosen[count][0]=curri.getId();
            ConsultCourse_Chosen.consultCourse_chosen[count][1]=curri.getName();
            ConsultCourse_Chosen.consultCourse_chosen[count][2]=curri.getTimestring();
            ConsultCourse_Chosen.consultCourse_chosen[count][3]=String.valueOf(curri.getPoint());
            ConsultCourse_Chosen.consultCourse_chosen[count][4]=curri.getTeacher();
            ConsultCourse_Chosen.consultCourse_chosen[count][5]=curri.getClassroom();
            count++;
        }
        ConsultCourse_Chosen chosen=new ConsultCourse_Chosen();
        Selcourse.panel.add(chosen,"chosen");
        Selcourse.cardLayout.show(Selcourse.panel,"chosen");
    }

    /**
     * 给老师选择
     *
     * @param courses 课程
     */
    public static void showTeacherChoice(ArrayList<Course>courses){
        int n=courses.size();
        ConsultCourse_stuInfo.consult_stu=new String[n][6];
        Iterator b=courses.iterator();
        int count=0;
        while(b.hasNext())
        {
            Course curri=(Course) b.next();
            ConsultCourse_stuInfo.consult_stu[count][0]=curri.getId();
            ConsultCourse_stuInfo.consult_stu[count][1]=curri.getName();
            ConsultCourse_stuInfo.consult_stu[count][2]=String.valueOf(curri.getSize());
            ConsultCourse_stuInfo.consult_stu[count][4]=curri.getTimestring();
            ConsultCourse_stuInfo.consult_stu[count][3]=curri.getClassroom();
            ConsultCourse_stuInfo.consult_stu[count][5]="              查询";
            count++;
        }
        ConsultCourse_stuInfo tea_course=new ConsultCourse_stuInfo();
        Selcourse_teacher.panel.add(tea_course,"tea_course");
        Selcourse_teacher.cardLayout.show(Selcourse_teacher.panel,"tea_course");
    }

    /**
     * requireall课程
     *
     * @throws IOException ioexception
     *///显示所有的课程进行选择
    public static void RequireallCourse()throws IOException{
        Message message=new Message();
        message.setType(MessageType.MESSAGE_CURRICULUM_LIST_ALL);
        oos.writeObject(message);
    }

    /**
     * 显示choosable
     *
     * @param allchoosing allchoosing
     * @throws IOException ioexception
     */
    public static void show_choosable(ArrayList<Course>allchoosing)throws IOException{
        int n=allchoosing.size();
        Choosing_Course.selectcourse=new String[n][7];
        Iterator b=allchoosing.iterator();
        int count=0;
        while(b.hasNext())
        {
            Course course=(Course) b.next();
            Choosing_Course.selectcourse[count][0]=course.getId();
            Choosing_Course.selectcourse[count][1]=course.getName();
            Choosing_Course.selectcourse[count][2]=course.getTimestring();
            Choosing_Course.selectcourse[count][3]=course.getTeacher();
            Choosing_Course.selectcourse[count][4]=String.valueOf(course.getSize());
            Choosing_Course.selectcourse[count][5]=course.getClassroom();
            Choosing_Course.selectcourse[count][6]="        选择";
            count++;
        }
        Choosing_Course choosing=new Choosing_Course();
        Selcourse.panel.add(choosing,"show_choosable");
        Selcourse.cardLayout.show(Selcourse.panel,"show_choosable");
    }

    /**
     * 管理所有课程
     *
     * @param allchoosing allchoosing
     * @throws IOException ioexception
     */
    public static void admin_all_course(ArrayList<Course>allchoosing)throws IOException{
        int n=allchoosing.size();
        Scheduling.courses=new String[n][7];
        Iterator b=allchoosing.iterator();
        int count=0;
        while(b.hasNext())
        {
            Course course=(Course) b.next();
            Scheduling.courses[count][0]=course.getId();
            Scheduling.courses[count][1]=course.getName();
            Scheduling.courses[count][2]=course.getTeacher();
            Scheduling.courses[count][3]=String.valueOf(course.getHour());
            Scheduling.courses[count][5]=String.valueOf(course.getSize());
            Scheduling.courses[count][4]=course.getTimestring();
            Scheduling.courses[count][6]=course.getClassroom();
            count++;
        }
        Scheduling sch=new Scheduling();
        Selcourse_director.panel.add(sch,"scheduling");
        Selcourse_director.cardLayout.show(Selcourse_director.panel,"scheduling");
    }

    /**
     * 需要咨询结果
     *
     * @param consultInfo 咨询信息
     * @throws IOException ioexception
     */
    public static void RequireConsultResult(String consultInfo)throws IOException{
        lastconsult=consultInfo;
        Message message=new Message();
        message.setData(consultInfo);
        message.setType(MessageType.MESSAGE_CURRICULUM_QUERY);
        oos.writeObject(message);
    }

    /**
     * 显示咨询结果
     *
     * @param course 课程
     * @throws IOException ioexception
     */
    public static void showConsultResult(ArrayList<Course> course)throws IOException{
        int n=course.size();
        Search_result.search_result=new String[n][6];
        Iterator b=course.iterator();
        int count=0;
        while(b.hasNext())
        {
            Course curri=(Course) b.next();
            Search_result.search_result[count][0]=curri.getId();
            Search_result.search_result[count][1]=curri.getName();
            Search_result.search_result[count][2]=curri.getTimestring();
            Search_result.search_result[count][3]=String.valueOf(curri.getPoint());
            Search_result.search_result[count][4]=curri.getTeacher();
            Search_result.search_result[count][5]=curri.getClassroom();
            count++;
        }
        Search_result search=new Search_result();
        if(myInfo.getType()==1)
        {panel.add(search,"search");
            cardLayout.show(panel,"search");}
        if(myInfo.getType()==2)
        {Selcourse_teacher.panel.add(search,"search");
            Selcourse_teacher.cardLayout.show(Selcourse_teacher.panel,"search");}
    }

    /**
     * 需要给我学生
     *
     * @param Id id
     * @throws IOException ioexception
     */
    public static void Require_show_my_students(String Id)throws IOException{
    //    System.out.println("require");
        Message message=new Message();
        message.setData(Id);
        message.setType(MessageType.MESSAGE_CURRICULUM_SHOW_STU);
        oos.writeObject(message);

    }

    /**
     * 应用
     *
     * @param course 课程
     * @throws IOException ioexception
     */
    public static void apply(Opencourse course) throws IOException{
        Check_Coustatus.checkcourse_status=null;
        Message message=new Message();
        message.setData(course);
        message.setType(MessageType.MESSAGE_CURRICULUM_APPLY);
        oos.writeObject(message);
    }

    /**
     * 给我学生
     *
     * @param students 学生
     */
    public static void show_my_students(ArrayList<Student>students){
        int n=students.size();
        ConsultCourse_stuInfo.students=new String[n][2];
        Iterator b=students.iterator();
        int count=0;
        while(b.hasNext())
        {
            Student s=(Student) b.next();
            ConsultCourse_stuInfo.students[count][0]=s.getStudent_id();
            ConsultCourse_stuInfo.students[count][1]=s.getStudent_name();
            count++;
        }
        ConsultCourse_stuInfo stuInfo=new ConsultCourse_stuInfo();
        //My_students stuInfo=new My_students();
        Selcourse_teacher.panel.add(stuInfo,"stuInfo");
        Selcourse_teacher.cardLayout.show(Selcourse_teacher.panel,"stuInfo");
    }

    /**
     * 结果显示应用
     *
     * @param opencourses opencourses
     */
    public static void showApplyResult(ArrayList<Opencourse>opencourses) {
        int n=opencourses.size();
        Check_Coustatus.checkcourse_status=new String[n][5];
        Iterator b=opencourses.iterator();
        int count=0;
        while(b.hasNext())
        {
            Opencourse curri=(Opencourse) b.next();
            Check_Coustatus.checkcourse_status[count][0]=curri.getId();
            Check_Coustatus.checkcourse_status[count][1]=curri.getName();
            Check_Coustatus.checkcourse_status[count][2]=String.valueOf(curri.getHour());
            if(curri.getStatus()==0){
                Check_Coustatus.checkcourse_status[count][3]="审核中...";
                Check_Coustatus.checkcourse_status[count][4]="审核中...";
            } else if (curri.getStatus()==1) {
                Check_Coustatus.checkcourse_status[count][3]="已退回";
                Check_Coustatus.checkcourse_status[count][4]=curri.getResult();
            }
            else{
                Check_Coustatus.checkcourse_status[count][3]="已通过";
                Check_Coustatus.checkcourse_status[count][4]="审核通过";
            }
            count++;
        }
        Check_Coustatus cc =new Check_Coustatus();
        Selcourse_teacher.panel.add(cc,"apply");
        Selcourse_teacher.cardLayout.show(Selcourse_teacher.panel,"apply");

    }

    /**
     * 我需要申请
     *
     * @throws IOException ioexception
     */
    public static void Require_my_apply() throws IOException {

        Message message=new Message();
        message.setData(myInfo.getId());
        message.setType(MessageType.MESSAGE_CURRICULUM_LIST_APPLICATION);
        oos.writeObject(message);
    }

    /**
     * 显示我日程安排
     *
     * @param schedule 时间表
     */
    public static void show_my_schedule(String [][][]schedule){
        My_Coursetable.tableDate=new String[17][13][6];
        for(int i=0;i<16;i++)
        {
            for(int j=0;j<13;j++){
                for(int k=1;k<=5;k++){
                    My_Coursetable.tableDate[i][j][k]=schedule[i+1][k][j+1];
                  //  if(i==0) System.out.print(My_Coursetable.tableDate[i][j][k]);
                }
               // if(i==0) System.out.println();
            }
        }
        My_Coursetable table=new My_Coursetable(1);
        Selcourse.panel.add(table,"schedule");
        Selcourse.cardLayout.show(Selcourse.panel,"schedule");
    }

    /**
     * 需要安排
     *
     * @throws IOException ioexception
     */
    public static void RequireSchedule() throws IOException {
        Message message=new Message();
        message.setData(myInfo.getId());
        message.setType(MessageType.MESSAGE_CURRICULUM_SHOW_SCHEDULE);
        oos.writeObject(message);
    }

    /**
     * 需要茶时间表
     *
     * @throws IOException ioexception
     */
    public static void RequireTeaSchedule() throws IOException {
        Message message=new Message();
        message.setData(myInfo.getId());
        message.setType(MessageType.MESSAGE_CURRICULUM_TEACHER_SCHEDULE);
        oos.writeObject(message);
    }

    /**
     * 展示茶时间表
     *
     * @param schedule 时间表
     */
    public static void show_tea_schedule(String [][][]schedule){
        My_Coursetable.tableDate=new String[17][13][6];
        for(int i=0;i<16;i++)
        {
            for(int j=0;j<13;j++){
                for(int k=1;k<=5;k++){
                    My_Coursetable.tableDate[i][j][k]=schedule[i+1][k][j+1];
                //    if(i==0) System.out.print(My_Coursetable.tableDate[i][j][k]);
                }
              //   if(i==0) System.out.println();
            }
        }
     //   System.out.println("showing teacher's schedule");
        My_Coursetable table=new My_Coursetable(1);
        Selcourse_teacher.panel.add(table,"schedule");
        Selcourse_teacher.cardLayout.show(Selcourse_teacher.panel,"schedule");
    }

    /**
     * 显示所有应用程序
     *
     * @param opencourses opencourses
     */
    public static void show_all_application(ArrayList<Opencourse>opencourses){
        int n=opencourses.size();
        Check_Course.checkcourse=new String[n][7];
        Iterator b=opencourses.iterator();
        int count=0;
        while(b.hasNext())
        {
            Opencourse curri=(Opencourse) b.next();
            Check_Course.checkcourse[count][0]=curri.getId();
            Check_Course.checkcourse[count][1]=curri.getName();
            Check_Course.checkcourse[count][2]=curri.getTeacher();
            Check_Course.checkcourse[count][3]=String.valueOf(curri.getPoint());
            Check_Course.checkcourse[count][4]=String.valueOf(curri.getHour());
            Check_Course.checkcourse[count][5]="        通过";
            Check_Course.checkcourse[count][6]="        退回";
            count++;
        }
        Check_Course f =new Check_Course();
        Selcourse_director.panel.add(f,"application");
        Selcourse_director.cardLayout.show(Selcourse_director.panel,"application");

    }

    /**
     * 要求所有应用程序
     *
     * @throws IOException ioexception
     */
    public static void Require_all_application() throws IOException {
        Message message=new Message();
        message.setType(MessageType.MESSAGE_CURRICULUM_LIST_ADMIN_APPLICATION);
        oos.writeObject(message);
    }

    /**
     * 需要删除课程
     *
     * @param deleteID 删除id
     * @throws IOException ioexception
     */
    public static void Require_deleteCourse(String deleteID) throws IOException {
        Message message=new Message();
        message.setData(deleteID);
        message.setType(MessageType.MESSAGE_CURRICULUM_DELETE);
        oos.writeObject(message);
    }

    /**
     * 需要同意添加课程
     *
     * @param c c
     * @throws IOException ioexception
     */
    public static void Require_AgreeAddCourse(Course c) throws IOException {
        Message message=new Message();
        message.setData(c);
        message.setType(MessageType.MESSAGE_CURRICULUM_APPLICATION_APPROVE);
        oos.writeObject(message);
    }

    /**
     * 需要拒绝添加课程
     *
     * @param id     id
     * @param reason 原因
     * @throws IOException ioexception
     */
    public static void Require_RefuseAddCourse(String id,String reason) throws IOException {
        Message message=new Message();
        message.setGetter(id);
        message.setData(reason);
        message.setType(MessageType.MESSAGE_CURRICULUM_APPLICATION_REFUSE);
        oos.writeObject(message);
    }

    /**
     * 减少课程
     *
     * @param id id
     * @throws IOException ioexception
     */
    public static void DropCourse(String id) throws IOException {
        Message message=new Message();
        message.setData(id);
        message.setType(MessageType.MESSAGE_CURRICULUM_DROP);
        oos.writeObject(message);
    }

    /**
     * 需要合适
     *
     * @throws IOException ioexception
     */
    public static void Require_suitable()throws IOException{
        Message message=new Message();
        message.setType(MessageType.MESSAGE_CURRICULUM_MY_CHOOSING);
        oos.writeObject(message);
    }

    /**
     * 需要老师课程
     *
     * @throws IOException ioexception
     */
    public static void requireTeacherCourse() throws IOException{
        Message message=new Message();
        message.setType(MessageType.MESSAGE_CURRICULUM_LIST_TEACHER_COURSE);
        oos.writeObject(message);
    }
}
