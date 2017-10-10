package test;

import java.lang.reflect.Constructor;

/**
 * Created by lian on 2017/1/5.
 */
public class ConstructorTest {
    public static void main(String[] args) {
        try {
            //获得指定字符串类对象
            Class cla=Class.forName("test.Tests");
            //设置Class对象数组，用于指定构造方法类型
            Class[] cl=new Class[]{int.class,int.class};

            //获得Constructor构造器对象。并指定构造方法类型
            Constructor con=cla.getConstructor(cl);

            //给传入参数赋初值
            Object[] x={new Integer(33),new Integer(67)};

            //得到实例
            Object obj=con.newInstance(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

class Tests{
    public Tests(int x,int y){
        System.out.println(x+"    "+y);
    }
}
