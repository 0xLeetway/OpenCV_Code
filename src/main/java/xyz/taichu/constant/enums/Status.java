package xyz.taichu.constant.enums;

import xyz.taichu.constant.enums.impl.EnumDescription;

/**
 * 本次枚举，从前期来说是有点多余的，
 * 但是相信我，后期的扩展，这个将是状态码的最佳存放地
 *
 * @Author: 老文
 * @Date: 2021/7/15 10:06
 */
public enum Status implements EnumDescription
{
    /**
     * imgComparison：图片比对状态码
     */
    imgComparison(000, "图片比对"),
    /**
     * useDevice：用户使用什么设备使用键盘还是鼠标
     */
    useDevice(100, "选择使用设备");
    private int code;
    private String description;

    Status(int code, String description)
    {
        this.code = code;
        this.description = description;
    }

    @Override
    public int getCode()
    {
        return code;
    }

    @Override
    public String getDescription()
    {
        return description;
    }
}
