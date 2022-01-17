package xyz.taichu.controller.Invoker;

import xyz.taichu.service.impl.Command.Command;

/**
 * 该方法选择调用方法
 *
 * @author GoLw
 */
public class InvokerController
{
    private Command imgCommand = null;
    private Command MouseCommand = null;
    private Command KeyboardCommand = null;

    public void setImgCommand(Command imgCommand)
    {
        this.imgCommand = imgCommand;
    }

    public void setMouseCommand(Command mouseCommand)
    {
        MouseCommand = mouseCommand;
    }

    public void setKeyboardCommand(Command keyboardCommand)
    {
        KeyboardCommand = keyboardCommand;
    }

    /*执行比对图片*/
    public void CompareImg()
    {
        imgCommand.execute();
    }

    /*执行鼠标命令*/
    public void MouseCommand()
    {
        MouseCommand.execute();
    }

    /*执行键盘命令*/
    public void KeyboardCommand()
    {
        KeyboardCommand.execute();
    }
}
