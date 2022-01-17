package xyz.taichu.model.entity;


import com.sun.jna.platform.win32.WinDef;

import java.awt.image.BufferedImage;

/**
 * 这里保存了一些关于窗口的坐标
 * windowName----窗口名字
 * windowHandle---窗口句柄
 * windowLong-----窗口长度
 * windowWide-----窗口宽
 * LeftUpX--------左上角坐标
 * rightUpY-------右上角坐标
 *
 * @author 老文
 */
public class MyWindows
{
    public static BufferedImage mainImage = null;
    public String IpClassName;
    public String IpWindowName;
    public static WinDef.HWND windowHandle = null;

    private int windowLong;
    private int windowWide;
    private int LeftUpX;
    private int leftUpY;

    public static BufferedImage getMainImage()
    {
        return mainImage;
    }

    public static void setMainImage(BufferedImage mainImage)
    {
        MyWindows.mainImage = mainImage;
    }

    public String getIpClassName()
    {
        return IpClassName;
    }

    public void setIpClassName(String ipClassName)
    {
        IpClassName = ipClassName;
    }

    public String getIpWindowName()
    {
        return IpWindowName;
    }

    public void setIpWindowName(String ipWindowName)
    {
        IpWindowName = ipWindowName;
    }

    public int getWindowLong()
    {
        return windowLong;
    }

    public void setWindowLong(int windowLong)
    {
        this.windowLong = windowLong;
    }

    public int getWindowWide()
    {
        return windowWide;
    }

    public void setWindowWide(int windowWide)
    {
        this.windowWide = windowWide;
    }

    public int getLeftUpX()
    {
        return LeftUpX;
    }

    public void setLeftUpX(int leftUpX)
    {
        LeftUpX = leftUpX;
    }

    public int getLeftUpY()
    {
        return leftUpY;
    }

    public void setLeftUpY(int leftUpY)
    {
        this.leftUpY = leftUpY;
    }
}
