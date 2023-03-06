package UIviewer.Shopping;

/*NewButton类，继承JButton类重写用于绘制按钮形状的函数*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * 新按钮
 *
 * @author Chen_GuanZhi
 * @date 2022/09/03
 */
public class NewButton extends JButton
{
    private String s;
    private int sizeX;
    private int sizeY;

    /**
     * 新按钮
     *
     * @param s 年代
     */
    public NewButton(String s)    //传递图片引用
    {
        super(s);
        setContentAreaFilled(false);
    }

    /**
     * 油漆组件
     *
     * @param g g
     */
    protected void paintComponent(Graphics g)    //绘制按钮内容
    {
        g.setColor(new Color(249, 70, 29));
        g.fillRoundRect(0,0,getSize().width-1,getSize().height-1,20,20);        //绘制一个圆角矩形getSize()为获取组件的大小
        //g.drawImage(img, 0,0,50, 40, null);      //除了形状外还可以为按钮绘制一个图片来美化按钮
        super.paintComponent(g);	//使用父类函数绘制一个焦点框
    }

    /**
     * 油漆边界
     *
     * @param g g
     */
    protected void paintBorder(Graphics g)   //绘制按钮边框
    {
        g.drawRoundRect(0,0,getSize().width-1,getSize().height-1,20,20);
    }
}
