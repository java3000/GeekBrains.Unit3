import java.util.Queue;
import java.util.concurrent.*;

public class FuelStaion {
    private static final int MAX_CARS = 3;
    public static final int FUILING_TIME = 5; //secs
    private Semaphore semaphore;
    private Queue<Vehicle> queue;

    public FuelStaion() {
        this.semaphore = new Semaphore(MAX_CARS);
        this.queue = new ConcurrentLinkedQueue<>();

        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleWithFixedDelay(this::check, 0, 1, TimeUnit.MILLISECONDS);
    }

    public void enter(Vehicle v){
        queue.offer(v);
        System.out.printf("машина %d встала в очередь на заправку %n", v.VIN);
    }

    public void check(){
        if(!queue.isEmpty()){
            for (int i = 0; i< queue.size(); i++) {
                Vehicle v = queue.poll();
                System.out.printf("машина %d поехала заправляться %n", v.VIN);
                fill(v);
            }
        }
    }

    public void fill(Vehicle v){
        try {
            semaphore.acquire();
            System.out.println("заправляется авто № " + v.VIN);
            Thread.sleep(FUILING_TIME * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("заправилось авто № " + v.VIN);
            semaphore.release();
        }
    }
}
