package xyz.taichu.controller.Client;

import com.sun.jna.platform.win32.User32;
import org.opencv.core.Core;
import xyz.taichu.config.StoreImgInfo;
import xyz.taichu.constant.enums.Status;
import xyz.taichu.controller.Invoker.InvokerController;
import xyz.taichu.controller.impl.Client;
import xyz.taichu.model.entity.MyWindows;
import xyz.taichu.service.intf.command.ImgCommand;
import xyz.taichu.service.intf.command.KeyboardCommand;
import xyz.taichu.service.intf.command.MouseCommand;
import xyz.taichu.service.intf.factory.XmlClass;
import xyz.taichu.service.intf.receive.ReciveAction;


/**
 * 客户端控制类
 * 从这里发送指令给InvokerController类
 * 本类控制，InvokerController发出什么命令
 *
 * @Author: 老文
 * @Date: 2021/7/15 9:51
 */
public class ClientControler implements Client
{
    InvokerController inController = new InvokerController();
    MyWindows winConfig = (MyWindows) XmlClass.eifUserConfig.getBean("MyWindowsConfig");

    @Override
    public void determine(Status status)
    {
        MyWindows.windowHandle = User32.INSTANCE.FindWindow(winConfig.getIpClassName(), winConfig.IpWindowName);
        switch (status)
        {
            case imgComparison:
                /*加载openCV核心类*/
                System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
                inController.setImgCommand(new ImgCommand(new ReciveAction()));
                inController.CompareImg();
                System.out.println(status.getDescription());
                break;
            case useDevice:
                if (StoreImgInfo.useDevice == 0)
                {
                    inController.setMouseCommand(new MouseCommand(new ReciveAction()));
                    inController.MouseCommand();
                }
                else if (StoreImgInfo.useDevice == 1)
                {
                    inController.setKeyboardCommand(new KeyboardCommand(new ReciveAction()));
                    inController.KeyboardCommand();
                }
                break;
            default:
                break;
        }
    }
}
