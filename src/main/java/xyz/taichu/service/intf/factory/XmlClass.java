package xyz.taichu.service.intf.factory;

import xyz.taichu.model.entity.Scene;
import xyz.taichu.model.entity.SceneSupervise;
import xyz.taichu.utils.factory.ClassPathXmlApplicationContext;

import java.util.LinkedList;
import java.util.List;

/**
 * 创建的XMl表中的对象
 *
 * @author 老文
 */
public class XmlClass
{
    public static ClassPathXmlApplicationContext eifUserConfig = new ClassPathXmlApplicationContext("EIfUserConfig.xml");
    public static List<Scene> sceneList = new LinkedList<Scene>();
    SceneSupervise supervise = (SceneSupervise) eifUserConfig.getBean("SceneSupervise");
    Scene scene = null;

    public void createXmlClass()
    {
        int sceneToal = supervise.getScaneToal();
        for (int i = 1; i <= sceneToal; i++)
        {
            scene = (Scene) eifUserConfig.getBean("Scene" + i);

            sceneList.add(scene);
        }
    }


    public ClassPathXmlApplicationContext getEifUserConfig()
    {
        return eifUserConfig;
    }

    public List<Scene> getSceneList()
    {
        return sceneList;
    }
}
