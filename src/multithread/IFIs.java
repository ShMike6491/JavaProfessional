package multithread;

public class IFIs {
    private Object mon1 = new Object();
    private Object mon2 = new Object();

    public void scan(int pages, String name) {
        if(pages < 0) return;
        synchronized (mon1) {
            for (int i = 1; i < pages+1; i++) {
                System.out.println("Отсканировано " + i + " страниц (" + name + ")" );
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void print(int pages, String name) {
        if(pages < 0) return;
        synchronized (mon2) {
            for (int i = 1; i < pages+1; i++) {
                System.out.println("Отпечатано " + i + " страниц (" + name + ")");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
