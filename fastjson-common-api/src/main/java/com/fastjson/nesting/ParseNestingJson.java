package com.fastjson.nesting;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;

import java.util.Map;

/***
 * todo: 9_5Lmd5biI5YWE5ZSv5LiA5Y6f5Yib5paH56ug77yM56aB5q2i6L2s6L29_9  2023/2/9 17:27 9 九师兄
 *   fastjson字符串json转对象（父类转子类）
 *    https://blog.csdn.net/qq_21383435/article/details/94730091
 */
public class ParseNestingJson {

    /**
     * json 转对象
     * json转对象：ClassHome(className=九三班, woman=Woman(beauty=非常漂亮), man=Man(house=价值200万的房子))
     */
    public void parseObjectTest() {
        String source = "{\n" +
                "\t\"className\": \"九三班\",\n" +
                "\t\"woman\": {\n" +
                "\t\t\"beauty\": \"非常漂亮\",\n" +
                "\t\t\"name\": \"谢静静\",\n" +
                "\t\t\"age\": \"20\",\n" +
                "\t\t\"type\": \"WOMAN\"\n" +
                "\t},\n" +
                "\t\"man\": {\n" +
                "\t\t\"house\": \"价值200万的房子\",\n" +
                "\t\t\"name\": \"谢静静\",\n" +
                "\t\t\"age\": \"20\",\n" +
                "\t\t\"type\": \"MAN\"\n" +
                "\t}\n" +
                "}";

        ClassHome slassHome = JSONObject.parseObject(source, ClassHome.class);
        System.out.println("json转对象：" + slassHome);
    }

    /**
     * json 转对象
     * <p>
     * woman Woman(beauty=非常漂亮)
     * <p>
     * 父类转之类失败
     * <p>
     * java.lang.ClassCastException: com.lcc.fastjson.Person cannot be cast to com.lcc.fastjson.Woman
     */
    public void parseObjectTest2() {
        String source = "{\n" +
                "\t\"className\": \"九三班\",\n" +
                "\t\"woman\": {\n" +
                "\t\t\"beauty\": \"非常漂亮\",\n" +
                "\t\t\"name\": \"谢静静\",\n" +
                "\t\t\"age\": \"20\",\n" +
                "\t\t\"type\": \"WOMAN\"\n" +
                "\t},\n" +
                "\t\"man\": {\n" +
                "\t\t\"house\": \"价值200万的房子\",\n" +
                "\t\t\"name\": \"谢静静\",\n" +
                "\t\t\"age\": \"20\",\n" +
                "\t\t\"type\": \"MAN\"\n" +
                "\t}\n" +
                "}";

        ClassHome slassHome = JSONObject.parseObject(source, ClassHome.class);
        System.out.println("json转对象：" + slassHome);
        // 不对啊  我的数据呢
        Woman woman = (Woman) slassHome.getWoman();
        System.out.println("woman" + woman);
    }


    /**
     * 特定的序列化
     */
    public void parseObjectTest3() {
        ParserConfig globalInstance = ParserConfig.getGlobalInstance();
        globalInstance.putDeserializer(Person.class, new PersonDeserializer());

        String source = "{\n" +
                "\t\"className\": \"九三班\",\n" +
                "\t\"woman\": {\n" +
                "\t\t\"beauty\": \"非常漂亮\",\n" +
                "\t\t\"name\": \"谢静静\",\n" +
                "\t\t\"age\": \"20\",\n" +
                "\t\t\"type\": \"WOMAN\"\n" +
                "\t},\n" +
                "\t\"man\": {\n" +
                "\t\t\"house\": \"价值200万的房子\",\n" +
                "\t\t\"name\": \"谢静静\",\n" +
                "\t\t\"age\": \"20\",\n" +
                "\t\t\"type\": \"MAN\"\n" +
                "\t}\n" +
                "}";

        ClassHome slassHome = JSONObject.parseObject(source, ClassHome.class);
        System.out.println("json转对象：" + slassHome);
        // 不对啊  我的数据呢
        Woman woman = (Woman) slassHome.getWoman();
        // woman Woman(beauty=非常漂亮)
        System.out.println("woman:" + woman);

        Person person = slassHome.getMan();
        System.out.println(person);

        Man man = (Man) person;
        System.out.println(man.getHouse());


    }

}
