package xyz.taichu.service.impl.Image;

import org.opencv.core.Mat;

import java.io.File;

/**
 * 该接口实现类
 * 需要完成图片的比对方法
 *
 * @Author: 老文
 * @Date: 2021/7/15 19:47
 */
public interface ImageComparison
{
    /**
     * 比对图片方法
     *
     * @param source 我们主图片
     * @param file   匹配图块
     * @return 返回处理好的图片
     */
    Mat findImg(Mat source, File file);
}
