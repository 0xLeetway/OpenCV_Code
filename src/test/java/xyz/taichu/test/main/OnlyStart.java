package xyz.taichu.test.main;

import xyz.taichu.service.intf.Thread.StartClient;

/**
 * 这个启动类仅仅启动一个主线程运行程序
 *
 * @Author: 老文
 * @Date: 2021/7/15 15:16
 */
public class OnlyStart
{
    public static void main(String[] args)
    {
        StartClient p = new StartClient();
        Thread clientThread = new Thread(p);
        clientThread.start();
    }
}
