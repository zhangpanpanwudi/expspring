package com.wonders.hotswap;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author: zph
 * @data: 2018/12/09 11:20
 */
public class MyClassloader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            //1、获取文件名称
            String fileName = name.substring(name.lastIndexOf(",")+1)+".class";
            //2、读取文件流
            InputStream is = this.getClass().getResourceAsStream(fileName);
            //3、读取字节
            byte[] bytes = new byte[is.available()];
            //4、将读取的byte数组给jvmclass对象

            return defineClass(name,bytes,0,bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
    }
}
