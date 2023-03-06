package DAO.StatusManagement;

import User.Student;

import java.io.Serializable;

/**
 * 用户图片和信息
 * @author Shuheng_Gu
 * @description 用于展示学籍管理模块中的头像和信息
 * @date 2022/09/03
 */
public class ImageAndTable implements Serializable {
    /**
     * 学生
     */
    public Student student;
    /**
     * 图像
     */
    public byte[] image;
}
