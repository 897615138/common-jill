package jill.common.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.File;

/**
 * @author Jill W
 * @date 2020/12/28
 */
public class XmlUtil {

    private static final SAXReader saxReader = new SAXReader();

    public static Document getDocument(String filename) {
        try {
            File resource = FilesUtil.getResource("text.json");
            return saxReader.read(resource);
        } catch (DocumentException e) {
            e.printStackTrace();
            return null;
        }
    }
}
