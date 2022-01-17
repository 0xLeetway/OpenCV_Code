package xyz.taichu.model.entity;

/**
 * 情景类的实体类，
 * 具体数值，由XML中的数值来实例化
 *
 * @author 老文
 */
public class Scene
{
    private int imgNum;
    private String NameID;
    private String img1;
    private String img2;
    private String img3;

    private int useDevice;

    private int mouseKey;
    private int imgOrder;

    private int keyCode;

    private int pressDelay;

    public int getKeyCode()
    {
        return keyCode;
    }

    public void setKeyCode(int keyCode)
    {
        this.keyCode = keyCode;
    }

    public int getImgNum()
    {
        return imgNum;
    }

    public void setImgNum(int imgNum)
    {
        this.imgNum = imgNum;
    }

    public String getNameID()
    {
        return NameID;
    }

    public void setNameID(String nameID)
    {
        NameID = nameID;
    }

    public String getImg1()
    {
        return img1;
    }

    public void setImg1(String img1)
    {
        this.img1 = img1;
    }

    public String getImg2()
    {
        return img2;
    }

    public void setImg2(String img2)
    {
        this.img2 = img2;
    }

    public String getImg3()
    {
        return img3;
    }

    public void setImg3(String img3)
    {
        this.img3 = img3;
    }

    public int getUseDevice()
    {
        return useDevice;
    }

    public void setUseDevice(int useDevice)
    {
        this.useDevice = useDevice;
    }

    public int getMouseKey()
    {
        return mouseKey;
    }

    public void setMouseKey(int mouseKey)
    {
        this.mouseKey = mouseKey;
    }

    public int getImgOrder()
    {
        return imgOrder;
    }

    public void setImgOrder(int imgOrder)
    {
        this.imgOrder = imgOrder;
    }

    public int getPressDelay()
    {
        return pressDelay;
    }

    public void setPressDelay(int pressDelay)
    {
        this.pressDelay = pressDelay;
    }
}
