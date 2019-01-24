package com.wonders;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

/**
 * @author: zph
 * @data: 2018/12/12 21:01
 */
public class XmlUtils {

    public static void main(String[] args) throws DocumentException {
        XmlUtils xmlUtils = new XmlUtils();
        xmlUtils.test01();
    }

    public void test01() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document read = saxReader.read(getClassPath("s.xml"));

        Element rootElement = read.getRootElement();

        getNode(rootElement);

    }

    public InputStream getClassPath(String xmlPath){
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(xmlPath);
        return resourceAsStream;
    }

    public void getNode(Element element){
        System.out.println("########################");
        System.out.println("获取当前名称："+element.getName());
        List<Attribute> attributes = element.attributes();
        for(Attribute attribute:attributes){
            System.out.println("属性："+attribute.getName()+",属性值："+attribute.getText());
        }

        String textTrim = element.getTextTrim();
        if(StringUtils.isNotEmpty(textTrim)){
            System.out.println("value:"+textTrim);
        }
        Iterator<Element> iterator = element.elementIterator();
        while(iterator.hasNext()){
            Element next = iterator.next();
            getNode(next);
        }
    }

}
