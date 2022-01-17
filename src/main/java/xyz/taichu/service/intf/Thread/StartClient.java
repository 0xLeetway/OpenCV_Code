package xyz.taichu.service.intf.Thread;

import xyz.taichu.constant.enums.Status;
import xyz.taichu.controller.Client.ClientControler;
import xyz.taichu.controller.impl.Client;

import javax.swing.*;
import java.awt.*;

/**
 * 一个线程启动类，
 * mian方法运行该线程之后，可以直接运行客户端
 *
 * @author 老文
 */
public class StartClient extends JPanel implements Runnable
{
    Client client = new ClientControler();

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                client.determine(Status.imgComparison);
            }
            catch (AWTException e)
            {
                e.printStackTrace();
            }
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

}

