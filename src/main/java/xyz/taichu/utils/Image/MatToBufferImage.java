package xyz.taichu.utils.Image;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Mat和BufferImage互转
 *
 * @author 老文
 */
public class MatToBufferImage
{

    /**
     * Mat转换成BufferedImage
     *
     * @param matrix        要转换的Mat
     * @param fileExtension 格式为 ".jpg", ".png", etc
     * @return
     */
    public static BufferedImage Mat2BufImg(Mat matrix, String fileExtension)
    {
        // convert the matrix into a matrix of bytes appropriate for
        // this file extension
        MatOfByte mob = new MatOfByte();
        Imgcodecs.imencode(fileExtension, matrix, mob);
        // convert the "matrix of bytes" into a byte array
        byte[] byteArray = mob.toArray();
        BufferedImage bufImage = null;
        try
        {
            InputStream in = new ByteArrayInputStream(byteArray);
            bufImage = ImageIO.read(in);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return bufImage;
    }

    public static BufferedImage matToBufferImag(Mat mat)
    {
        return (BufferedImage) HighGui.toBufferedImage(mat);
    }

    /*BufferedImage转Mat方法*/
    public static Mat BufferedImage2Mat(BufferedImage image) throws IOException
    {
        /*字节数组输出流，这里的流不需要关闭，因为本身就是字节，用完后会直接回收*/
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "bmp", byteArrayOutputStream);
        byteArrayOutputStream.flush();
        return Imgcodecs.imdecode(new MatOfByte(byteArrayOutputStream.toByteArray()), Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);
    }
}