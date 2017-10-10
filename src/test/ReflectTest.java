package test;

/**
 * Created by lian on 2017/1/5.
 */
public class ReflectTest {
    public static void main(String[] args) throws Exception {

    Person person = new Person("jack", 25);

    // test get private value
    System.out.println("jack's name:" + ReflectionUtil.getValue(person, "name"));
    System.out.println("jack's age:" + ReflectionUtil.getValue(person, "age"));

    // test set private value
    ReflectionUtil.setValue(person, "name", "jason");
    ReflectionUtil.setValue(person, "age", 10);
    System.out.println("jack's name:" + ReflectionUtil.getValue(person, "name"));
    System.out.println("jack's age:" + ReflectionUtil.getValue(person, "age"));

    // test call private method
    String result = (String) ReflectionUtil.callMethod(person, "getInfo", new Class[] { String.class, int.class },
            new Object[] { "I hava ", 4 });
    System.out.println("result: " + result);
    }
}
class Person {

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private String getInfo(String str, int num) {
        return str + num + " apples";
    }

}