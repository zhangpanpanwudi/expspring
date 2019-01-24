package com.wonders.spring.xml;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * @author: zph
 * @data: 2018/12/12 21:52
 */
public class ExpApplicationContext {

    private String xmlPath;

    public ExpApplicationContext(String xmlPath) {
        this.xmlPath = xmlPath;
    }


    public Object getBean(String beanId) throws Exception {
        //1、读取xml配置文件
        List<Element> elements = readXMl();
        if(elements==null||elements.isEmpty()){
            throw new Exception();
        }
        //2、判断是否有对应的bean
        String classPath = getInstence(elements,beanId);
        if(StringUtils.isEmpty(classPath)){
            throw new Exception("没有配置classpath属性");
        }
        //3、反射创建对象
        return newInstence(classPath);
    }

    public String getInstence(List<Element> elements,String beanId) throws Exception {
        for(Element element:elements){
            String id = element.attributeValue("id");
            if(StringUtils.isEmpty(id)){
                throw  new Exception("没有配置id属性");
            }
            if(beanId.equals(id)){
                String xmlClass = element.attributeValue("class");
                return xmlClass;
            }
        }
        return null;
    }

    public Object newInstence(String classPath) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> forName = Class.forName(classPath);
        Object object = forName.newInstance();
        return object;
    }

    public List<Element> readXMl() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document read = saxReader.read(getClassPath(xmlPath));
        Element rootElement = read.getRootElement();
        List<Element> elements = rootElement.elements();
        if(elements==null||elements.isEmpty()){
            return null;
        }
        return elements;
    }

    public InputStream getClassPath(String xmlPath){
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(xmlPath);
        return resourceAsStream;
    }
}
