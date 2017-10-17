package PO53.KuznecovAA.wdad.learn.xml;

import PO53.KuznecovAA.wdad.data.managers.PreferencesManager;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

/**
 * Created by U_LIVT35408 on 21.09.2017.
 */
public class TestXmlTask {
    public static void main(String[] args) throws Exception {

        XmlTask test = new XmlTask();
        User user = new User("Aloy");
        String t = "TEXT";
        test.updateNode(user,"День первый","овый текст");
        test.setPrivileges("День второй",user,3);
       //System.out.println(test.getNoteText(user,t));
        PreferencesManager testPF = PreferencesManager.getInstance();
            testPF.setClassprovider("Проверка");
            testPF.setCreateregistry("Проверка");
            testPF.setPolicypath("Проверка");
            testPF.setRegistryaddress("Проверка");
            testPF.setRegistryport(1111);
            testPF.setUsecodebaseonly("Проверка");
            testPF.saveTransformXml();


    }

    }

