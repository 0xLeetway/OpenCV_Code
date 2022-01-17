package xyz.taichu.test.xml;

import xyz.taichu.model.entity.Scene;
import xyz.taichu.service.intf.factory.XmlClass;

import java.util.Iterator;

public class xmlTest
{
    public static void main(String[] args)
    {

        XmlClass xmlClass = new XmlClass();
        xmlClass.createXmlClass();
        Iterator<Scene> sceneIterator = xmlClass.getSceneList().iterator();
        int i = 0;
        while (sceneIterator.hasNext())
        {
            Scene scene = xmlClass.getSceneList().get(i);
            System.out.println(scene);
            sceneIterator.next();
            if (sceneIterator.hasNext() == false)
            {
                i = 0;
            }
            System.out.println("下一个还有没有？" + sceneIterator.hasNext());
            i++;
        }
    }
}
