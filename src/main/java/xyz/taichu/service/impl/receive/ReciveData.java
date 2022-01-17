package xyz.taichu.service.impl.receive;

/**
 * 这个接口会从数据中解析成为行为
 *
 * @author Lw
 */
public interface ReciveData
{

    /**
     * 图片比对处理
     *
     * @return 如果返回true表示图片全部找到，如果放回false则表示不符合情景
     */
    void imgAction();

    /**
     * 鼠标命令解析
     */
    void mouseAction();

    /**
     * 键盘行为
     */
    void keyboardAction();
}
