package xyz.taichu.service.intf.Image;

import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinGDI;
import com.sun.jna.platform.win32.WinNT;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class WindowsBackstageScreenshot
{
    /**
     * 本方法会根据游戏界面句柄扫描进程窗口,窗口尺寸并且返回窗口截图(进程不可以最小化)
     *
     * @param hwnd        - 游戏界面句柄
     * @param game_width  - 窗口宽度
     * @param game_height - 窗口高度
     * @return - 返回窗口截图
     */
    public static Mat scanningProcess(WinDef.HWND hwnd, int game_width, int game_height) throws IOException
    {

        // 检索游戏窗口区域的显示设备上下文环境的句柄,以后在GDI函数中使用该句柄来在设备上下文环境中绘图
        WinDef.HDC gameDC = GDI32.INSTANCE.GetDC(hwnd);
        // 创建与指定的设备环境相关的设备兼容的位图
        WinDef.HBITMAP outputBitmap = GDI32.INSTANCE.CreateCompatibleBitmap(gameDC, game_width, game_height);
        try
        {
            // 创建一个与指定设备兼容的内存设备上下文环境
            WinDef.HDC blitDC = GDI32.INSTANCE.CreateCompatibleDC(gameDC);
            try
            {
                // 选择一对象到指定的设备上下文环境中
                WinNT.HANDLE oldBitmap = GDI32.INSTANCE.SelectObject(blitDC, outputBitmap);
                try
                {
                    // 对指定的源设备环境区域中的像素进行位块(bit_block)转换
                    GDI32.INSTANCE.BitBlt(blitDC, 0, 0, game_width, game_height, gameDC, 0, 0, GDI32.SRCCOPY);
                }
                finally
                {
                    GDI32.INSTANCE.SelectObject(blitDC, oldBitmap);
                }
                // 位图信息头,大小固定40字节
                WinGDI.BITMAPINFO bi = new WinGDI.BITMAPINFO(40);
                bi.bmiHeader.biSize = 40;
                // 函数获取指定兼容位图的位,然后将其作一个DIB—设备无关位图使用的指定格式复制到一个缓冲区中
                boolean ok = GDI32.INSTANCE.GetDIBits(blitDC, outputBitmap, 0, game_height, (byte[]) null, bi, WinGDI.DIB_RGB_COLORS);
                if (ok)
                {
                    WinGDI.BITMAPINFOHEADER bih = bi.bmiHeader;
                    bih.biHeight = -Math.abs(bih.biHeight);
                    bi.bmiHeader.biCompression = 0;
                    BufferedImage img = bufferedImageFromBitmap(blitDC, outputBitmap, bi);
                    /*将获得截取获得Buffered的图片转成Mat类型*/
                    Mat mat = WindowsBackstageScreenshot.BufferedImage2Mat(img);
                    return mat;
                }
            }
            finally
            {
                GDI32.INSTANCE.DeleteObject(blitDC);
            }
        }
        finally
        {
            GDI32.INSTANCE.DeleteObject(outputBitmap);
        }

        return null;

    }

    // 依赖方法scanningProcess
    private static BufferedImage bufferedImageFromBitmap(WinDef.HDC blitDC, WinDef.HBITMAP outputBitmap, WinGDI.BITMAPINFO bi) throws IOException
    {
        WinGDI.BITMAPINFOHEADER bih = bi.bmiHeader;
        int height = Math.abs(bih.biHeight);
        final ColorModel cm;
        final DataBuffer buffer;
        final WritableRaster raster;
        int strideBits = (bih.biWidth * bih.biBitCount);
        int strideBytesAligned = (((strideBits - 1) | 0x1F) + 1) >> 3;
        final int strideElementsAligned;
        switch (bih.biBitCount)
        {
            case 16:
                strideElementsAligned = strideBytesAligned / 2;
                cm = new DirectColorModel(16, 0x7C00, 0x3E0, 0x1F);
                buffer = new DataBufferUShort(strideElementsAligned * height);
                raster = Raster.createPackedRaster(buffer, bih.biWidth, height, strideElementsAligned, ((DirectColorModel) cm).getMasks(), null);
                break;
            case 32:
                strideElementsAligned = strideBytesAligned / 4;
                cm = new DirectColorModel(32, 0xFF0000, 0xFF00, 0xFF);
                buffer = new DataBufferInt(strideElementsAligned * height);
                raster = Raster.createPackedRaster(buffer, bih.biWidth, height, strideElementsAligned, ((DirectColorModel) cm).getMasks(), null);
                break;
            default:
                throw new IllegalArgumentException("检测到不支持的图片位数: " + bih.biBitCount);
        }
        final boolean ok;
        switch (buffer.getDataType())
        {
            case DataBuffer.TYPE_INT:
            {
                int[] pixels = ((DataBufferInt) buffer).getData();
                ok = GDI32.INSTANCE.GetDIBits(blitDC, outputBitmap, 0, raster.getHeight(), pixels, bi, 0);
            }
            break;
            case DataBuffer.TYPE_USHORT:
            {
                short[] pixels = ((DataBufferUShort) buffer).getData();
                ok = GDI32.INSTANCE.GetDIBits(blitDC, outputBitmap, 0, raster.getHeight(), pixels, bi, 0);
            }
            break;
            default:
                throw new AssertionError("检测到不支持的缓冲元素类型: " + buffer.getDataType());
        }
        if (ok)
        {
            BufferedImage bufferedImage = new BufferedImage(cm, raster, false, null);
            return bufferedImage;
        }
        else
        {
            return null;
        }
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
