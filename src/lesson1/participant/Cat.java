package lesson1.participant;

public class Cat implements Participant {
    private final String name;

    public Cat(String name) {
        this.name = name;
    }

    @Override
    public int run() {
        return 10;
    }

    @Override
    public int jump() {
        return 1;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                '}';
    }
}
