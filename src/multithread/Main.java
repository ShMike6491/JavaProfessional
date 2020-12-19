package multithread;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
//        Q q = new Q();
//        new ABC(q, 'A');
//        new ABC(q, 'B');
//        new ABC(q, 'C');

        IFIs printer = new IFIs();

        new Thread(() -> {
            printer.print(2, Thread.currentThread().getName());
        }).start();
        new Thread(() -> {
            printer.scan(3, Thread.currentThread().getName());
        }).start();
        new Thread(() -> {
            printer.print(5, Thread.currentThread().getName());
        }).start();
        new Thread(() -> {
            printer.scan(2, Thread.currentThread().getName());
        }).start();
    }
}

class Q {
    private boolean setA = false;
    private boolean setB = true;
    private boolean setC = true;

    private File file = null;
    private FileWriter writer = null;


    synchronized void put(char letter) throws InterruptedException {
        switch(letter) {
            case 'A':
                while(setA) {wait();}
                setA = true;
                setB = false;
                break;
            case 'B':
                while(setB){wait();}
                setB = true;
                setC = false;
                break;
            case 'C':
                while(setC){wait();}
                setC = true;
                setA = false;
                break;
            default:
                wait();
        }
        System.out.print(letter);
        notifyAll();
    }
    synchronized void writeLines (String msg) throws InterruptedException, IOException {
        file = new File("src/multithread/text.txt");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        try {
            writer = new FileWriter(file, true);
            writer.write(msg + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
        Thread.sleep(200);
    }
}

class ABC implements Runnable {
    Q q;
    char x;

    ABC(Q q, char letter) {
        this.q = q;
        x = letter;
        new Thread(this, letter + "").start();
    }

    @Override
    public void run() {
//        taskOne();
        taskTwo();
    }

    /**
     * синхронизированная запись в файл
     */
    private void taskTwo() {
        for (int i = 0; i < 10; i++) {
            try {
                q.writeLines(String.format("Поток %c делает запись %d", x, i));
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * синхронизация потоков (wait, notify, notifyall)
     */
    private void taskOne() {
        for (int i = 0; i < 5; i++) {
            try {
                q.put(x);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}