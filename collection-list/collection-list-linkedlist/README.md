#LinkedList源码分析
###1、为什么要记录first节点和last节点?<br>
     first查询时开始、last添加元素时的位置
###2、删除注意事项
    删除头元素时，头元素改为头元素的下一个，同理，删除最后一个时，尾元素改为尾元素的上一个
###3、指定位置新增方法注意事项
    如果是头部，更新first,