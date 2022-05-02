package lesson5;

import java.util.Arrays;

public class HomeWorkThreads {

    private static final int SIZE = 10_000_000;
    private static final int HALF_SIZE = 10_000_000 / 2;

    public static void main(String[] args) throws InterruptedException {
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1.0f);

        firstMethod(arr);
        secondMethod(arr);
    }

    private static void firstMethod(float[] arr) {
        Arrays.fill(arr, 1.0f);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5.0) * Math.cos(0.2f + i / 5.0) * Math.cos(0.4f + i / 2.0));
        }
        System.out.println("Время выполнения первого потока: " + (System.currentTimeMillis() - startTime) + " миллисекунд");
    }
    private static void secondMethod(float[] arr) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        float[] firstHalf = new float[HomeWorkThreads.HALF_SIZE];
        float[] secondHalf = new float[HomeWorkThreads.HALF_SIZE];

        System.arraycopy(arr, 0, firstHalf, 0, HomeWorkThreads.HALF_SIZE);
        System.arraycopy(arr, HomeWorkThreads.HALF_SIZE, secondHalf, 0, HomeWorkThreads.HALF_SIZE);

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < firstHalf.length; i++) {
                firstHalf[i] = (float) (firstHalf[i] * Math.sin(0.2f + i / 5.0) * Math.cos(0.2f + i / 5.0) * Math.cos(0.4f + i / 2.0));
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < secondHalf.length; i++) {
                secondHalf[i] = (float) (secondHalf[i] * Math.sin(0.2f + i / 5.0) * Math.cos(0.2f + i / 5.0) * Math.cos(0.4f + i / 2.0));
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        float[] mergedArray = new float[HomeWorkThreads.SIZE];
        System.arraycopy(firstHalf, 0, mergedArray, 0, HomeWorkThreads.HALF_SIZE);
        System.arraycopy(secondHalf, 0, mergedArray, HomeWorkThreads.HALF_SIZE, HomeWorkThreads.HALF_SIZE);

        System.out.println("Время выполнения второго потока: " + (System.currentTimeMillis() - startTime) + " миллисекунд");
    }
}
