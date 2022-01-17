package xyz.taichu.service.intf.receive;

import com.sun.jna.platform.win32.User32;
import org.opencv.core.Mat;
import xyz.taichu.config.StoreImgInfo;
import xyz.taichu.model.entity.MyWindows;
import xyz.taichu.model.entity.Scene;
import xyz.taichu.model.entity.SceneSupervise;
import xyz.taichu.service.impl.receive.ReciveData;
import xyz.taichu.service.intf.Image.ImageContrast;
import xyz.taichu.service.intf.Image.WindowsBackstageScreenshot;
import xyz.taichu.service.intf.factory.XmlClass;
import xyz.taichu.utils.Image.MatToBufferImage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static java.awt.event.InputEvent.*;

/**
 * @author GoLw
 */
public class ReciveAction implements ReciveData
{

    static Integer allImg;
    SceneSupervise sceneSupervise = (SceneSupervise) XmlClass.eifUserConfig.getBean("SceneSupervise");
    XmlClass xmlClass = new XmlClass();
    ImageContrast imageContrast = new ImageContrast();
    Mat mainWindowsImg = null;
    Mat processMainWindowsImg = null;
    /**
     * 根据XML中的参数实例化，
     * MyWindows，sceneSupervise，
     * xmlClass：实例化XML配置类，生成Scene情景类，并装入到Secene集合中
     * imageContrast：实例化ImageContrast，图像比对
     * Mat类（OpenCV用来存储图片信息的类）
     * mainWindowsImg：存储主窗口的图片
     * processMainWindowsImg：存储我们要比对的图块信息
     * allImg:存储一个情景的图片总数，因为在StatusImg要引用所以直接定义为静态
     * Robot:JDK中用来实现自动化控制的一个包
     */
    MyWindows winConfig = (MyWindows) XmlClass.eifUserConfig.getBean("MyWindowsConfig");
    Robot robot;

    {
        try
        {
            robot = new Robot();
        }
        catch (AWTException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void imgAction()
    {
        /**
         * 初始化XML的配置
         */
        xmlClass.createXmlClass();
        List<Scene> scenes = XmlClass.sceneList;


        //将情景集合中的一个个取出
        Scene scene = scenes.get(StoreImgInfo.flag);
        StoreImgInfo.imgOrder = scene.getImgOrder();
        StoreImgInfo.useDevice = scene.getUseDevice();
        /* System.out.println("从中获得的鼠标键值："+scene.getMouseKey());*/
        StoreImgInfo.mouseKey = scene.getMouseKey();
        /*System.out.println("从XML中得到的键值：" + scene.getKeyCode());*/
        StoreImgInfo.keyCode = scene.getKeyCode();
        /*System.out.println("获取之后的mousKey："+StoreImgInfo.mouseKey);*/
        StoreImgInfo.pressDelay = scene.getPressDelay();
        /*System.out.println("获取之后的pressDelay："+StoreImgInfo.pressDelay);*/
        allImg = scene.getImgNum();
        File[] files = new File[allImg];
        System.out.printf("allimg："+allImg );
        //转成IO流
        files[0] = new File(scene.getImg1());
        System.out.printf("scene.getImg2():" + (scene.getImg2() == "null"));
        if (allImg>1)
        {
            files[1] = new File(scene.getImg2());
            System.out.println(files[1]);
        }
        if (allImg>2)
        {
            files[2] = new File(scene.getImg3());
            System.out.println(files[2]);
        }
        for (int i = 0; i < allImg; i++)
        {
            /* 如果没有这个文件那么就会停止循环 */
            /* System.out.println(files[i].exists());*/
            if (files[i] == null)
            {
                System.out.println("文件是空，直接跳出循环");
                break;
            }
            System.out.println("图片名：" + files[i].getName());
            /*如果每一次图片比对之前，我们进行一次窗口调整*/
            User32.INSTANCE.MoveWindow(MyWindows.windowHandle, winConfig.getLeftUpX(), winConfig.getLeftUpY(), winConfig.getWindowLong(), winConfig.getWindowWide(), true);

            StoreImgInfo.current = i + 1;
            /*完成情景对象录入，进行结果比对*/
            try
            {
                mainWindowsImg = WindowsBackstageScreenshot.scanningProcess(MyWindows.windowHandle, winConfig.getWindowLong(), winConfig.getWindowWide());
                processMainWindowsImg = imageContrast.findImg(mainWindowsImg, files[i]);
                //将从后台截取的图片传给画笔线程
                MyWindows.mainImage = MatToBufferImage.matToBufferImag(processMainWindowsImg);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            /*将临时计数图片变量清空*/
            if (i == (allImg - 1))
            {
                StatusImg.imgNum = 0;
            }
        }
        StoreImgInfo.flag++;
        if (StoreImgInfo.flag == sceneSupervise.getScaneToal())
        {
            StoreImgInfo.flag = 0;
        }
        //  System.out.println("执行了比对图片的信息");
    }

    @Override
    public void mouseAction()
    {
         System.out.println(StoreImgInfo.winX+","+StoreImgInfo.winY);
        robot.mouseMove(StoreImgInfo.winX + winConfig.getLeftUpX(), StoreImgInfo.winY + winConfig.getLeftUpY());
        /*System.out.println("按键编码：" + StoreImgInfo.mouseKey);*/
        System.out.println("这里移动了鼠标");
        switch (StoreImgInfo.mouseKey)
        {
            case 1:
                /*System.out.println("使用鼠标左键");*/
                robot.mousePress(BUTTON1_DOWN_MASK);
                robot.delay(StoreImgInfo.pressDelay);
                robot.mouseRelease(BUTTON1_DOWN_MASK);
                break;
            case 2:
                /*System.out.println("使用鼠标中键");*/
                robot.mousePress(BUTTON2_DOWN_MASK);
                robot.delay(StoreImgInfo.pressDelay);
                robot.mouseRelease(BUTTON2_DOWN_MASK);
                break;
            case 3:
                /*System.out.println("使用鼠标右键");*/
                robot.mousePress(BUTTON3_DOWN_MASK);
                robot.delay(StoreImgInfo.pressDelay);
                robot.mouseRelease(BUTTON3_DOWN_MASK);
                break;
            default:
                System.out.println("请输入正确格式");
        }


    }

    @Override
    public void keyboardAction()
    {
        robot.keyPress(StoreImgInfo.keyCode);
        robot.delay(StoreImgInfo.pressDelay);
        robot.keyRelease(StoreImgInfo.keyCode);
    }
}
