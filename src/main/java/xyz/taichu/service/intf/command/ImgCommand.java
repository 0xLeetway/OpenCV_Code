package xyz.taichu.service.intf.command;

import xyz.taichu.service.impl.Command.Command;
import xyz.taichu.service.intf.receive.ReciveAction;

/**
 * 通过调用这个接口，
 * 调用图片比对命令
 *
 * @author 老文
 */
public class ImgCommand implements Command
{
    private ReciveAction reciveAction;

    public ImgCommand(ReciveAction reciveAction)
    {
        this.reciveAction = reciveAction;
    }

    @Override
    public void execute()
    {
        reciveAction.imgAction();
    }
}
