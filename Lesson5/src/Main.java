import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    //вроде все, кроме этого так или иначе сделал
    //* Транспортные средства после заправки возвращаются на дорогу и продолжают свое движение
    public static void main(String[] args) {
        FuelStaion fs = new FuelStaion();

        ExecutorService es = Executors.newFixedThreadPool(10);

        es.execute(() -> fs.enter( new Car()));
        es.execute(() -> fs.enter( new Car()));
        es.execute(() -> fs.enter( new Car()));
        es.execute(() -> fs.enter( new Truck()));
        es.execute(() -> fs.enter( new Bus()));
        es.execute(() -> fs.enter( new Car()));
        es.execute(() -> fs.enter( new Bus()));

        es.shutdown();
    }
}
