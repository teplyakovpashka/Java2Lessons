package lesson2;

public class MyArraySizeException extends RuntimeException {
    public MyArraySizeException() {
        super("Неверный размер массива, требуется 4х4");
    }
}
