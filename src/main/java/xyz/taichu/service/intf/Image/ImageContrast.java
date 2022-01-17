package xyz.taichu.service.intf.Image;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import xyz.taichu.service.impl.Image.ImageComparison;
import xyz.taichu.service.intf.receive.StatusImg;

import java.io.File;

/**
 * 从主图片中寻找我们的图块
 *
 * @Author: 老文
 * @Date: 2021/7/15 19:47
 */
public class ImageContrast implements ImageComparison
{
    static int CVTYPE = CvType.CV_8UC4;
    Point matchLoc = null;
    StatusImg statusImg = new StatusImg();
    /**
     * 匹配图片
     *
     * @param source 主图片
     * @param file   要在主图片中寻找的图块
     * @return 返回一个Mat类型的图片
     */
    @Override
    public Mat findImg(Mat source, File file)
    {
        //传入进来的图片，被寻找的部分
        String picture = file.getPath();
        //被匹配的文件
        Mat template = Imgcodecs.imread(picture);
        Mat result = Mat.zeros(source.rows() - template.rows() + 1, source.cols() - template.cols() + 1, CVTYPE);
        //调用模板匹配方法
        int method = Imgproc.TM_CCOEFF_NORMED;
        Imgproc.matchTemplate(source, template, result, method);
        //规格化
        Core.normalize(template, template, 0, 1, Core.NORM_MINMAX, -1);
        //获得最可能点，MinMaxLocResult是其数据格式，包括了最大、最小点的位置x、y
        Core.MinMaxLocResult mlr = Core.minMaxLoc(result);
        //根据匹配算法选择匹配结果
        if (method == Imgproc.TM_SQDIFF || method == Imgproc.TM_SQDIFF_NORMED)
        {
            matchLoc = mlr.minLoc;
            //匹配度
            System.out.println("mlr.max=" + mlr.maxVal);
            /*//在原图上的对应模板可能位置画一个绿色矩形
               System.out.println(matchLoc.x + template.width() + "  " + matchLoc.y + template.height());*/
            Imgproc.rectangle(source, matchLoc, new Point(matchLoc.x + template.width(), matchLoc.y + template.height()), new Scalar(0, 255, 0));
        }
        else
        {
            matchLoc = mlr.maxLoc;
            //匹配度
            System.out.println("mlr.max=" + mlr.maxVal);
           /* //在原图上的对应模板可能位置画一个绿色矩形
             System.out.println(matchLoc.x + template.width() + "  " + matchLoc.y + template.height());*/
            Imgproc.rectangle(source, matchLoc, new Point(matchLoc.x + template.width(), matchLoc.y + template.height()), new Scalar(0, 255, 0));
        }

        statusImg.matchNum(mlr.maxVal, file, matchLoc.x, matchLoc.y);
        template.release();
        return source;
    }

}
