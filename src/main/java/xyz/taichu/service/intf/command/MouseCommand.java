package xyz.taichu.service.intf.command;

import xyz.taichu.service.impl.Command.Command;
import xyz.taichu.service.intf.receive.ReciveAction;

/**
 * 通过链接鼠标命令和鼠标行为
 *
 * @author 老文
 */
public class MouseCommand implements Command
{
    private ReciveAction reciveAction = null;

    public MouseCommand(ReciveAction reciveAction)
    {
        this.reciveAction = reciveAction;
    }

    @Override
    public void execute()
    {
        reciveAction.mouseAction();
    }
}
