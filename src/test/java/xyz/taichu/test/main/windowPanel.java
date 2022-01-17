package xyz.taichu.test.main;

import xyz.taichu.model.entity.MyWindows;
import xyz.taichu.service.intf.Thread.DrawWindows;
import xyz.taichu.service.intf.Thread.StartClient;
import xyz.taichu.service.intf.factory.XmlClass;

import javax.swing.*;
import java.io.IOException;

/**
 * 使用本类启动，
 * 将格外画多一个窗口可以查看主图片的处理情况
 *
 * @Author: 老文
 * @Date: 2021/7/13 3:50
 */
public class windowPanel
{
    public static void main(String[] args) throws IOException
    {
        MyWindows windowsConfig = (MyWindows) XmlClass.eifUserConfig.getBean("MyWindowsConfig");

        StartClient p = new StartClient();
        Thread clientThread = new Thread(p);
        DrawWindows d = new DrawWindows();
        Thread panelThread = new Thread(d);

        JFrame frame = new JFrame();
        frame.add(d);
        frame.setSize(windowsConfig.getWindowLong(), windowsConfig.getWindowWide());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelThread.start();
        clientThread.start();
    }
}
