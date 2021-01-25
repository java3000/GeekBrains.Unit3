public abstract class Vehicle {

    public float fuelVolume;
    public float fuelConsumption;
    public long VIN;

    public Vehicle(float fuelVolume, float fuelConsumption) {
        this.fuelVolume = fuelVolume;
        this.fuelConsumption = fuelConsumption;
        this.VIN = System.nanoTime();

        //отключено, чтобы не флудило, мы его все равно не меняем.
        //ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        //ses.scheduleWithFixedDelay(this::checkFuel, 0, 3, TimeUnit.SECONDS);
    }

    public void checkFuel() {
        System.out.printf("у автомобиля %d уровень топлива %f %n", this.VIN, this.fuelVolume);
    };

}
