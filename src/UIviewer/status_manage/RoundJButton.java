package UIviewer.status_manage;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * 一轮jbutton
 *
 * @author Zhangyang_Ge
 * @date 2022/09/03
 */
public class RoundJButton extends JButton {
    private Shape shape;

    /**
     * 一轮jbutton
     */
    public RoundJButton() {
        setOpaque(false); // As suggested by @AVD in comment.
    }

    /**
     * 油漆组件
     *
     * @param g g
     */
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        super.paintComponent(g);
    }

    /**
     * 油漆边界
     *
     * @param g g
     */
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
    }

    /**
     * 包含
     *
     * @param x x
     * @param y y
     * @return boolean
     */
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        }
        return shape.contains(x, y);
    }
}