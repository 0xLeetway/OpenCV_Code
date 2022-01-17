package xyz.taichu.service.intf.command;

import xyz.taichu.service.impl.Command.Command;
import xyz.taichu.service.intf.receive.ReciveAction;

/**
 * 通过链接键盘命令和键盘行为
 *
 * @author 老文
 */
public class KeyboardCommand implements Command
{
    private ReciveAction reciveAction = null;

    public KeyboardCommand(ReciveAction reciveAction)
    {
        this.reciveAction = reciveAction;
    }

    @Override
    public void execute()
    {
        reciveAction.keyboardAction();
    }
}
