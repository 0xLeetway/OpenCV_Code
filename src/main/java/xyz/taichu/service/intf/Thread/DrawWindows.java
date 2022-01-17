package xyz.taichu.service.intf.Thread;

import xyz.taichu.model.entity.MyWindows;
import xyz.taichu.service.intf.factory.XmlClass;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: 老文
 * @Date: 2021/7/15 11:17
 */
public class DrawWindows extends JPanel implements Runnable
{
    MyWindows winConfig = (MyWindows) XmlClass.eifUserConfig.getBean("MyWindowsConfig");

    //画图窗口
    @Override
    public void paint(Graphics arg0)
    {
        super.paint(arg0);
        //将图片释放到回话窗口中
        arg0.drawImage(MyWindows.mainImage, winConfig.getLeftUpX(), winConfig.getLeftUpY(), winConfig.getWindowLong(), winConfig.getWindowWide(), this);
    }

    @Override
    public void run()
    {
        while (true)
        {
            /*重画*/
            this.repaint();
            try
            {
                Thread.sleep(50);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
