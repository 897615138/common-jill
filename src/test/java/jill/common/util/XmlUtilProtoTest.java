package jill.common.util;

import junit.framework.TestCase;
import org.dom4j.Document;
import org.dom4j.Element;


/**
 * @author Jill W
 * @date 2020/12/28
 */
public class XmlUtilProtoTest extends TestCase {

    public void testGetDocument() {
        Document document = XmlUtil.getDocument("text.json");
        assertNotNull(document);
        Element notes = document.getRootElement();
//        System.out.println(notes);
//        List<Element> note = notes.elements("note");
//        note.forEach(el->{
//            Element toText = el.element("to");
//            Element fromText = el.element("from");
//            Element headingText = el.element("heading");
//            Element bodyText = el.element("body");
//            System.out.println(toText);
//        });
    }
}