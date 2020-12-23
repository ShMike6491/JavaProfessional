package multithread_II;

import java.util.concurrent.*;

public class MainClass {
    public static final int CARS_COUNT = 4;
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(CARS_COUNT);
        Semaphore semaphore = new Semaphore(CARS_COUNT/2);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(CARS_COUNT, () ->
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!"));
        CountDownLatch countDownLatch = new CountDownLatch(CARS_COUNT);

        Race race = new Race(new Road(60), new Tunnel(semaphore), new Road(40));
        Car[] cars = new Car[CARS_COUNT];

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        for (int i = 0; i < cars.length; i++)
            executorService.submit(new Car(race, 20 + (int) (Math.random() * 10), cyclicBarrier, countDownLatch));

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        executorService.shutdown();
    }
}

