package xyz.taichu.service.intf.receive;

import xyz.taichu.config.StoreImgInfo;
import xyz.taichu.constant.enums.Status;
import xyz.taichu.controller.Client.ClientControler;

import java.io.File;

/**
 * 本类是通过匹配度结果，
 * 将操作传给客户端控制层
 * 客户端控制层，将根据本次请求的结果，来发出命令
 *
 * @Author: 老文
 * @Date: 2021/7/13 10:41
 */
public class StatusImg
{
    /**
     * 用来储存情景下图片的总数有多少？
     */
    static int imgNum = 0;
    ClientControler client = new ClientControler();

    public void matchNum(Double matchingDegree, File file, double winX, double winY)
    {
        //匹配度判断
        if (matchingDegree > 0.8)
        {
            /*   System.out.println("匹配度大于0.8");*/
            imgNum++;
            if (StoreImgInfo.imgOrder == StoreImgInfo.current)
            {
                /* System.out.println("传入当前图片X,y:("+winX+","+winY+")");*/
                StoreImgInfo.winX = (int) winX;
                StoreImgInfo.winY = (int) winY;
            }
            if (imgNum == ReciveAction.allImg)
            {
                /*  System.out.println("图片遍历，情景比对完成，调用设备编码："+Status.useDevice);*/
                client.determine(Status.useDevice);
                /*调用设备之后，储存的图片清零*/
                imgNum = 0;
            }
        }
        else
        {
            System.out.println("本次传入的图片匹配度太低了");
        }

    }
}
