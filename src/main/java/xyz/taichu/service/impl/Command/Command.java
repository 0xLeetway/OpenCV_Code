package xyz.taichu.service.impl.Command;

/**
 * 命令接口，用来居中调度各种命令
 *
 * @author 老文
 */
public interface Command
{
    /**
     * 命令模式对应的执行
     */
    void execute();
}
