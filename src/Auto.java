public abstract class Auto implements Runnable{

    String name;
    abstract void drive();
    abstract void stop();
    abstract float getFuel();
    abstract void doRefuel(float t);

    @Override
    public abstract void run();
}
