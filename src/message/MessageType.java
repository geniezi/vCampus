package message;
//接口中定义一些常量
//不同的常量值表示不同消息类型

/**
 * 消息类型
 *
 * @author Shuheng_Gu
 * @date 2022/09/03
 */
public interface MessageType {

    /**
     * 港口
     */
    int PORT = 8081;                     //端口号
    /**
     * 信息登录成功
     */
    String MESSAGE_LOGIN_SUCCEED = "1";  //登录成功
    /**
     * 信息登录失败
     */
    String MESSAGE_LOGIN_FAIL = "2";     //登录失败
    /**
     * 学生登录信息
     */
    String MESSAGE_STUDENT_LOGIN = "3";  //学生登录
    /**
     * 信息老师登录
     */
    String MESSAGE_TEACHER_LOGIN = "4";  //教师登录
    /**
     * 信息管理员登录
     */
    String MESSAGE_ADMIN_LOGIN = "5";    //管理员登录

    /**
     * 消息学生注册
     */
    String MESSAGE_STUDENT_REGISTER = "10";             //学生注册
    /**
     * 学生信息注册成功
     */
    String MESSAGE_STUDENT_REGISTER_SUCCEED = "11";     //学生注册成功
    /**
     * 消息学生注册失败
     */
    String MESSAGE_STUDENT_REGISTER_FAILED = "12";     //学生注册失败
    /**
     * 信息老师注册
     */
    String MESSAGE_TEACHER_REGISTER = "13";             //老师注册
    /**
     * 信息老师注册成功
     */
    String MESSAGE_TEACHER_REGISTER_SUCCEED = "14";     //老师注册成功
    /**
     * 信息老师注册失败
     */
    String MESSAGE_TEACHER_REGISTER_FAILED = "15";     //老师注册失败

    /**
     * 发现某些
     */
    String TO_FIND_CERTAIN="16";
    /**
     * 发现某些
     */
    String HAVE_FIND_CERTAIN="17";                     //找到要修改密码的用户
    /**
     * 找不到某些
     */
    String NOT_FIND_CERTAIN="18";                      //没有找到要修改密码的用户
    /**
     * 重置密码
     */
    String RESET_PASSWORD="19";                        //开始重置密码
    /**
     * 重置密码失败
     */
    String RESET_PASSWORD_FAILED = "20";               //重置密码失败
    /**
     * 重置密码成功
     */
    String RESET_PASSWORD_SUCCEED = "9";              //重置密码成功

    /**
     * 消息客户端退出
     */
    String MESSAGE_CLIENT_EXIT = "0";     //客户端退出

    //图书馆

    /**
     * 图书馆信息输入
     */
    String MESSAGE_LIBRARY_ENTER="21";   //提取用户信息 无data
    /**
     * 消息库输入受潮湿腐烂
     */
    String MESSAGE_LIBRARY_ENTER_RET="21.1";//返回提取的用户信息 data类型Info
    /**
     * 图书馆信息管理列表
     */
    String MESSAGE_LIBRARY_ADMIN_LIST="22"; //管理员查看图书馆内书籍 无data
    /**
     * 图书馆信息管理列表仓促
     */
    String MESSAGE_LIBRARY_ADMIN_LIST_RET="22.1";  //返回管理员看到的图书 data类型Hashset<Book_admin>
    /**
     * 图书馆信息管理查询
     */
    String MESSAGE_LIBRARY_ADMIN_QUERY="22.2"; //管理员查询图书馆内书籍 data类型String
    /**
     * 图书馆信息管理查询受潮湿腐烂
     */
    String MESSAGE_LIBRARY_ADMIN_QUERY_RET="22.3";  //返回管理员查到的图书 data类型Hashset<Book_admin>
    /**
     * 消息库列表我书
     */
    String MESSAGE_LIBRARY_LIST_MY_BOOK="22.5"; //查询用户借阅的书籍 无data
    /**
     * 消息我书ret库列表
     */
    String MESSAGE_LIBRARY_LIST_MY_BOOK_RET="22.4"; //返回用户借阅的书籍  data类型Hashset<Book_borrower>
    /**
     * 消息库管理员处理
     */
    String MESSAGE_LIBRARY_ADMIN_HANDLE="23";  //管理员处理罚单 data类型
    /**
     * 消息库管理员添加
     */
    String MESSAGE_LIBRARY_ADMIN_ADD="23.1";  //管理员添加图书馆内书籍 data类型Book_admin
    /**
     * 消息库管理员删除
     */
    String MESSAGE_LIBRARY_ADMIN_DELETE="23.2"; //管理员删除图书馆内书籍 data类型String，图书id
    /**
     * 消息库管理员删除成功
     */
    String MESSAGE_LIBRARY_ADMIN_DELETE_SUCCEED="23.3";
    /**
     * 消息库管理员删除失败
     */
    String MESSAGE_LIBRARY_ADMIN_DELETE_FAIL="23.4";
    /**
     * 消息库列表我票
     */
    String MESSAGE_LIBRARY_LIST_MY_TICKET="24";  //用户请求自己的罚单 无data
    /**
     * 消息我票ret库列表
     */
    String MESSAGE_LIBRARY_LIST_MY_TICKET_RET="24.1"; //返回用户看自己的罚单 data类型Hashset<Punishment>
    /**
     * 图书馆信息管理列表门票
     */
    String MESSAGE_LIBRARY_ADMIN_LIST_TICKETS="25"; //管理员看到用户的请求 无data
    /**
     * 消息票ret图书馆管理员列表
     */
    String MESSAGE_LIBRARY_ADMIN_LIST_TICKETS_RET="25.1"; //返回管理员看到的用户的请求 data类型Hashset<Punishment>
    /**
     * 消息图书馆借
     */
    String MESSAGE_LIBRARY_BORROW="26";   //借书请求，data类型Book_borrower
    /**
     * 消息图书馆借成功
     */
    String MESSAGE_LIBRARY_BORROW_SUCCEED="26.1"; //借书成功 无data
    /**
     * 消息图书馆借太多失败
     */
    String MESSAGE_LIBRARY_BORROW_FAIL_TOO_MANY="26.2";  //借书失败，超过五本的限制 无data
    /**
     * 消息图书馆借失败返回第一个
     */
    String MESSAGE_LIBRARY_BORROW_FAIL_RETURN_FIRST="26.3"; //借书失败，应该先还书 无data
    /**
     * 消息库扩展
     */
    String MESSAGE_LIBRARY_EXTEND="26.4";  //用户请求延长借阅 data类型Book_borrower
    /**
     * 消息库扩展成功
     */
    String MESSAGE_LIBRARY_EXTEND_SUCCEED="26.5"; //延长借阅成功 无data
    /**
     * 消息库扩展失败
     */
    String MESSAGE_LIBRARY_EXTEND_FAIL="26.6"; //延长借阅失败，已经延长过一次 无data
    /**
     * 消息库ret
     */
    String MESSAGE_LIBRARY_RET="27"; //用户还书，data类型Book_borrower
    /**
     * 消息库ret成功
     */
    String MESSAGE_LIBRARY_RET_SUCCEED="27.1" ; //用户还书成功 无data
    /**
     * 消息库ret晚
     */
    String MESSAGE_LIBRARY_RET_LATE="27.2";//用户还书迟了 无data
    /**
     * 图书馆信息查询
     */
    String MESSAGE_LIBRARY_QUERY="28";   //查询书籍，data类型String
    /**
     * 消息库查询ret
     */
    String MESSAGE_LIBRARY_QUERY_RET="28.1"; //返回查询的书籍 data类型Hashset<Book_borrower>
    /**
     * 图书馆信息管理给票
     */
    String MESSAGE_LIBRARY_ADMIN_GIVE_TICKET="29";  //用户提出申请 data类型Punishment
    /**
     * 消息库支付
     */
    String MESSAGE_LIBRARY_PAY="30";  //用户罚钱 data类型Punishment
    /**
     * 消息库支付成功
     */
    String MESSAGE_LIBRARY_PAY_SUCCEED="30.1"; //用户交罚款成功
    /**
     * 消息库支付失败
     */
    String MESSAGE_LIBRARY_PAY_FAIL="30.2";//用户交罚款失败，钱不够


    /**
     * 消息课程列出所有
     *///选课
    String MESSAGE_CURRICULUM_LIST_ALL="31";  //请求所有的课程 无data
    /**
     * 消息课程列出所有仓促
     */
    String MESSAGE_CURRICULUM_LIST_ALL_RET="31.1"; //返回所有课程 data类型Arraylist<Course>
    /**
     * 我消息课程列表
     */
    String MESSAGE_CURRICULUM_LIST_MINE="31.5";  //展示用户所选课程 无data
    /**
     * 消息我ret课程列表
     */
    String MESSAGE_CURRICULUM_LIST_MINE_RET="31.6"; //返回用户所选课程 data类型Arraylist<Course>
    /**
     * 消息课程选择
     */
    String MESSAGE_CURRICULUM_CHOOSE="32"; //请求选课 data类型Course
    /**
     * 消息课程选择成功
     */
    String MESSAGE_CURRICULUM_CHOOSE_SUCCEED="32.1"; //选课成功 无data
    /**
     * 消息课程选择冲突
     */
    String MESSAGE_CURRICULUM_CHOOSE_CONFLICT="32.2";//选课失败 课程冲突 无data
    /**
     * 消息课程选择全
     */
    String MESSAGE_CURRICULUM_CHOOSE_FULL="32.3";//选课失败 课程已满 无data
    /**
     * 消息课程申请
     */
    String MESSAGE_CURRICULUM_APPLY="33";  //教师申请 data类型Opencourse
    /**
     * 消息课程申请成功
     */
    String MESSAGE_CURRICULUM_APPLY_SUCCEED="33.1"; //教师申请成功 无data
    /**
     * 消息课程申请失败
     */
    String MESSAGE_CURRICULUM_APPLY_FAIL="33.2";//教师申请失败 已经有这门课程 无data
    /**
     * 课程信息列表应用程序
     */
    String MESSAGE_CURRICULUM_LIST_APPLICATION="33.3"; //教师看到自己的申请，无data
    /**
     * 课程信息列表应用程序仓促
     */
    String MESSAGE_CURRICULUM_LIST_APPLICATION_RET="33.4"; //返回教师看到自己的申请，data类型Arraylist<Opencourse>
    /**
     * 消息课程应用程序批准
     */
    String MESSAGE_CURRICULUM_APPLICATION_APPROVE="33.5";  //同意加课，Message包getter里写凯克课程id，data是课程Course类型
    /**
     * 消息课程应用程序拒绝
     */
    String MESSAGE_CURRICULUM_APPLICATION_REFUSE="33.6";  //拒绝加课，Message包getter里写开课课程id，data里写原因String类型
    /**
     * 课程信息展示斯图
     */
    String MESSAGE_CURRICULUM_SHOW_STU="34";  //请求展示这门课的学生 data类型String课程id
    /**
     * 课程信息展示stu仓促
     */
    String MESSAGE_CURRICULUM_SHOW_STU_RET="34.1"; //返回这门课的学生 data类型Arraylist<Student>
    /**
     * 课程信息显示进度
     */
    String MESSAGE_CURRICULUM_SHOW_SCHEDULE="35";  //请求展示课表
    /**
     * 课程信息展示进度受潮湿腐烂
     */
    String MESSAGE_CURRICULUM_SHOW_SCHEDULE_RET="35.1"; //返回课表 data类型String[17][6][14]
    /**
     * 课程信息删除
     */
    String MESSAGE_CURRICULUM_DELETE="36"; //删除课 data类型String课程id
    /**
     * 课程列表管理应用程序消息
     */
    String MESSAGE_CURRICULUM_LIST_ADMIN_APPLICATION="33.7";  //管理员展示申请
    /**
     * 课程信息列表管理应用程序受潮湿腐烂
     */
    String MESSAGE_CURRICULUM_LIST_ADMIN_APPLICATION_RET="33.8"; //返回管理员处的申请 data类型Arraylist<Opencourse>
    /**
     * 课程信息查询
     */
    String MESSAGE_CURRICULUM_QUERY="37"; //课程查询 data String(名称，教师，课程号)
    /**
     * 课程信息查询ret
     */
    String MESSAGE_CURRICULUM_QUERY_RET="37.1"; //返回课程查询的结果 data类型Arraylist<Course>
    /**
     * 课程信息管理安排
     */
    String MESSAGE_CURRICULUM_ADMIN_ARRANGEMENT="38";//管理员排课，安排教室和时间 Data类型Course
    /**
     * 消息课程下降
     */
    String MESSAGE_CURRICULUM_DROP="39";
    /**
     * 消息课程我选择
     */
    String MESSAGE_CURRICULUM_MY_CHOOSING="40";
    /**
     * 消息课程我选择后悔
     */
    String MESSAGE_CURRICULUM_MY_CHOOSING_RET="40.5";
    /**
     * 消息课程老师课程列表
     */
    String MESSAGE_CURRICULUM_LIST_TEACHER_COURSE="40.6";
    /**
     * 老师课程ret消息课程列表
     */
    String MESSAGE_CURRICULUM_LIST_TEACHER_COURSE_RET="40.8";
    /**
     * 消息课程老师安排
     */
    String MESSAGE_CURRICULUM_TEACHER_SCHEDULE="40.9";

    /**
     * 消息qicq添加朋友
     *///站内通信
    String MESSAGE_QICQ_ADD_FRIEND="41";
    /**
     * 消息qicq添加好友失败无法找到用户
     */
    String MESSAGE_QICQ_ADD_FRIEND_FAIL_CANNOT_FIND_USER="41.2";
    /**
     * 消息qicq添加好友成功
     */
    String MESSAGE_QICQ_ADD_FRIEND_SUCCEED="41.5";
    /**
     * 消息qicq发送味精
     *///    String MESSAGE_QICQ_ADD_FRIEND_START="41.3";
    String MESSAGE_QICQ_SEND_MSG="42";
    /**
     * 消息qicq发送文件
     */
    String MESSAGE_QICQ_SEND_FILE="42.5";
    /**
     * 消息qicq得到通知
     */
    String MESSAGE_QICQ_GET_ANNOUNCEMENT="43";
    /**
     * 消息qicq声明受潮湿腐烂
     */
    String MESSAGE_QICQ_GET_ANNOUNCEMENT_RET="43.1";
    /**
     * 消息qicq得到消息
     */
    String MESSAGE_QICQ_GET_MESSAGE="43.3";
    /**
     * 消息qicq得到消息仓促
     */
    String MESSAGE_QICQ_GET_MESSAGE_RET="43.4";
    /**
     * 消息qicq添加公告
     */
    String MESSAGE_QICQ_ADD_ANNOUNCEMENT="43.2";
    /**
     * 消息qicq列表应用程序
     */
    String MESSAGE_QICQ_LIST_APPLICATION="44";  //请求展示出我发送的好友申请
    /**
     * 消息qicq列表应用程序仓促
     */
    String MESSAGE_QICQ_LIST_APPLICATION_RET="44.1";
    /**
     * 消息qicq列表应用程序处理
     */
    String MESSAGE_QICQ_LIST_APPLICATION_HANDLE="44.2";  //请求展示出我收到的好友申请
    /**
     * 消息qicq列表应用程序处理受潮湿腐烂
     */
    String MESSAGE_QICQ_LIST_APPLICATION_HANDLE_RET="44.3";
    /**
     * 消息qicq朋友列表
     */
    String MESSAGE_QICQ_LIST_FRIENDS="45";
    /**
     * 消息qicq朋友ret列表
     */
    String MESSAGE_QICQ_LIST_FRIENDS_RET="45.1";
    /**
     * 消息qicq接受新朋友
     */
    String MESSAGE_QICQ_ACCEPT_NEW_FRIEND="46";
    /**
     * 消息qicq否认新朋友
     */
    String MESSAGE_QICQ_DENY_NEW_FRIEND="46.5";
    /**
     * 消息qicq recerive消息
     */
    String MESSAGE_QICQ_RECERIVE_MESSAGE="47";
    /**
     * 消息qicq recerive文件
     */
    String MESSAGE_QICQ_RECERIVE_FILE="47.5";
    /**
     * 消息qicq朋友在线
     */
    String MESSAGE_QICQ_FRIEND_ONLINE="48";
    /**
     * 消息qicq朋友在线仓促
     */
    String MESSAGE_QICQ_FRIEND_ONLINE_RET="48.5";
    /**
     * 离线消息qicq朋友
     */
    String MESSAGE_QICQ_FRIEND_OFFLINE="48.2";
    /**
     * 离线消息qicq朋友后悔
     */
    String MESSAGE_QICQ_FRIEND_OFFLINE_RET="48.6";
    /**
     * 消息qicq修改
     */
    String MESSAGE_QICQ_MODIFY="49";
    /**
     * 消息qicq修改受潮湿腐烂
     */
    String MESSAGE_QICQ_MODIFY_RET="49.5";

/*    String RETURN_STUDENT_INFO="100";           //得到学籍信息
    String RETURN_STUDENT_INFO_SUCCEED="101";   //得到学籍信息成功
    String RETURN_STUDENT_INFO_FAILED="102";    //得到学籍信息失败

    String RETURN_PHOTO="103";          //得到图片
    String RETURN_PHOTO_SUCCEED="104";  //得到图片成功
    String RETURN_PHOTO_FAILED="105";   //得到图片失败

    String ADMIN_RETURN_STUDENT_INFO="100.1";           //管理员得到学籍信息
    String ADMIN_RETURN_STUDENT_INFO_SUCCEED="101.1";   //管理员得到学籍信息成功
    String ADMIN_RETURN_STUDENT_INFO_FAILED="102.1";    //管理员得到学籍信息失败

    String RENEW_STUDENT_INFO="100.11";           //RENEW学籍信息
    String RENEW_STUDENT_INFO_SUCCEED="101.11";   //RENEW学籍信息成功
    String RENEW_STUDENT_INFO_FAILED="102.11";    //RENEW学籍信息失败*/


    /**
     * 返回所有产品
     */
    String RETURN_ALL_PRODUCT="106";          //请求得到所有商品
    /**
     * 返回所有产品成功
     */
    String RETURN_ALL_PRODUCT_SUCCEED="107";  //成功
    /**
     * 返回所有产品失败
     */
    String RETURN_ALL_PRODUCT_FAILED="108";   //失败

    /**
     * 找到产品
     */
    String FIND_PRODUCT="106.5";          //请求模糊查找商品
    /**
     * 发现产品成功
     */
    String FIND_PRODUCT_SUCCEED="110.5";        //成功
    /**
     * 发现产品成功零
     */
    String FIND_PRODUCT_SUCCEED_ZERO="110.0";  //成功且为0
    /**
     * 发现产品失败
     */
    String FIND_PRODUCT_FAILED="111.5";         //失败

    /**
     * 删除产品
     */
    String DELETE_PRODUCT="109";          //请求删除特定商品
    /**
     * 删除产品成功
     */
    String DELETE_PRODUCT_SUCCEED="110";  //成功
    /**
     * 删除产品失败
     */
    String DELETE_PRODUCT_FAILED="111";   //失败


    /**
     * 找到类型产品
     */
    String FIND_TYPE_PRODUCT="112";             //查找商品根据分类
    /**
     * 找到类型产品成功
     */
    String FIND_TYPE_PRODUCT_SUCCEED="113";     //成功
    /**
     * 找到类型产品失败
     */
    String FIND_TYPE_PRODUCT_FAILED="114";      //成功且为0

    /**
     * 添加产品
     */
    String ADD_PRODUCT="115";             //add商品
    /**
     * 添加产品成功
     */
    String ADD_PRODUCT_SUCCEED="116";     //成功
    /**
     * 添加产品失败
     */
    String ADD_PRODUCT_FAILED="117";      //失败

    /**
     * 检查购买产品
     */
    String CHECK_BUYED_PRODUCT="118";
    /**
     * 检查购买产品成功
     */
    String CHECK_BUYED_PRODUCT_SUCCEED="119";     //成功
    /**
     * 检查购买产品失败
     */
    String CHECK_BUYED_PRODUCT_FAILED="120";      //失败


    /**
     * 检查readytobuy产品
     */
    String CHECK_READYTOBUY_PRODUCT="118.1";
    /**
     * 检查readytobuy产品成功
     */
    String CHECK_READYTOBUY_PRODUCT_SUCCEED="118.2";     //成功
    /**
     * 检查readytobuy产品失败
     */
    String CHECK_READYTOBUY_PRODUCT_FAILED="118.3";      //失败


    /**
     * 检查特定产品
     */
    String CHECK_CERTAIN_PRODUCT="118.11";
    /**
     * 检查特定产品成功
     */
    String CHECK_CERTAIN__PRODUCT_SUCCEED="118.21";     //成功
    /**
     * 检查特定产品失败
     */
    String CHECK_CERTAIN__PRODUCT_FAILED="118.31";      //失败

    /**
     * 买某些产品
     */
    String BUY_CERTAIN_PRODUCT="130.1.1";
    /**
     * 买某些产品成功
     */
    String BUY_CERTAIN__PRODUCT_SUCCEED="131.1.1";     //成功
    /**
     * 买某些产品失败
     */
    String BUY_CERTAIN__PRODUCT_FAILED="132.1.1";      //失败

    /**
     * 删除readytpbuy产品
     */
    String DELETE_READYTPBUY_PRODUCT="130.1.1Q";
    /**
     * 删除readytpbuy产品成功
     */
    String DELETE_READYTPBUY_PRODUCT_SUCCEED="131.1.1Q";     //成功
    /**
     * 删除readytpbuy产品失败
     */
    String DELETE_READYTPBUY_PRODUCT_FAILED="132.1.1Q";      //失败

    /**
     * 消息状态斯图输入
     */
    String MESSAGE_STATUS_STU_ENTER="200";
    /**
     * 消息状态斯图输入受潮湿腐烂
     */
    String MESSAGE_STATUS_STU_ENTER_RET="200.5";
    /**
     * 消息状态管理查询
     */
    String MESSAGE_STATUS_ADMIN_QUERY="201.6";
    /**
     * 消息状态管理查询受潮湿腐烂
     */
    String MESSAGE_STATUS_ADMIN_QUERY_RET="201.8";
    /**
     * 消息状态管理查询失败
     */
    String MESSAGE_STATUS_ADMIN_QUERY_FAIL="201.9";
    /**
     * 消息状态确认
     */
    String MESSAGE_STATUS_CONFIRM="202";
    /**
     * 消息状态确认受潮湿腐烂
     */
    String MESSAGE_STATUS_CONFIRM_RET="202.5";

    /**
     * 添加到shopcar
     */
    String ADD_TO_SHOPCAR = "133.1111";
    /**
     * 添加shopcar成功
     */
    String ADD_TO_SHOPCAR_SUCCEED = "133.1111q";
    /**
     * 添加到shopcar失败
     */
    String ADD_TO_SHOPCAR_FAILED = "133.1111s";
    /**
     * 得到钱
     */
    String GET_MONEY = "133.11112";
    /**
     * 得到钱成功
     */
    String GET_MONEY_SUCCEED = "133.1111q2";
    /**
     * 得到钱没有
     */
    String GET_MONEY_FAILED = "133.1111s2";
    /**
     * 得到钱老师
     */
    String GET_MONEY_TEACHER = "133.1111211";
    /**
     * 得到钱老师成功
     */
    String GET_MONEY_TEACHER_SUCCEED = "133.1111q211";
    /**
     * 得到钱老师失败
     */
    String GET_MONEY_TEACHER_FAILED = "133.1111s211";

    /**
     * 买某些产品老师
     */
    String BUY_CERTAIN_PRODUCT_TEACHER = "133.11112111";
    /**
     * 买某些产品老师成功
     */
    String BUY_CERTAIN_PRODUCT_TEACHER_SUCCEED = "133.1111q2111";
    /**
     * 买某些产品老师失败
     */
    String BUY_CERTAIN_PRODUCT_TEACHER_FAILED = "133.1111s2111";


}
