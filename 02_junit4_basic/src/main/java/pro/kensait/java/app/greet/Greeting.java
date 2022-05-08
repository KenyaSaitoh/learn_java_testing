package pro.kensait.java.app.greet;

public class Greeting {
    public String sayHello(String personName) {
        return "Hello! 私は" + personName + "です";
    }

    public String sayGoodbye(String personName) {
        return "Goodbye! 私は" + personName + "です";
    }

    public String sayGoodMorning(String personName) {
        return "Good Morning! 私は" + personName + "です";
    }
}