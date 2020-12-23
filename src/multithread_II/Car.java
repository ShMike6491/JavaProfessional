package multithread_II;

import java.util.concurrent.*;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private volatile static boolean WINNER;

    static {
        CARS_COUNT = 0;
        WINNER = false;
    }

    private Race race;
    private int speed;
    private String name;
    private CyclicBarrier cyclicBarrier;
    private CountDownLatch countDownLatch;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CyclicBarrier cb, CountDownLatch cdl) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        cyclicBarrier = cb;
        countDownLatch = cdl;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            cyclicBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }

        if(!WINNER) {
            WINNER = true;
            System.out.println(this.name + " - !WINNER!");
        }

        countDownLatch.countDown();
    }
}
