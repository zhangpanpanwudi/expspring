springAOP及属性注入（实现思路）
========================
<br>1、扫描包，得到所有的类（代码固定，晚上有很多，可封装为util类）
<br>2、判断是否有bean注解，有的话初始化bean 加入concurrentHashmap中，
 <br>       其中key为bean名称。value为bean对象
<br>3、初始化所有的bean后，实现属性注入，
 <br>以上三步都是在容器初始化的时候完成的，
 通过容器获取bean时，只需要从concurrentHashmap中取值就可以了
