package Gas;
import java.util.concurrent.Semaphore;

public class FuelStation implements FuelStationInterf{

    private Semaphore semaphore;

    public FuelStation() {
        this.semaphore = new Semaphore(gasPools.length);
    }

    private static GasPool gasPools [] = {
            new GasPool(),
            new GasPool(),
            new GasPool()

    };

    public synchronized float doRefuel(float amount){
        try {
            semaphore.acquire();
            for (int i = 0; i < gasPools.length; i++) {
                if(gasPools[i].isStatus()&&gasPools[i].getBalance()>0&&gasPools[i].getBalance()-amount>=0){
                    System.out.println(gasPools[i].getIndexGas()+": "+gasPools[i].getBalance()+"/"+GasPool.getStock());
                    float a = gasPools[i].request(amount);
                    semaphore.release();
                    return a;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }

        return 0f;

    }
    public void getIndex(){
        for (int i = 0; i < gasPools.length; i++) {
            System.out.println(gasPools[i].getIndexGas());
        }
    }
    public boolean checkFreeAllGasPool(){
        int a=0;
        for (int j = 0; j < gasPools.length; j++) {
            if (gasPools[j].getBalance() == 0){
                a++;
            }
        }
        return gasPools.length==a;
    }

    public boolean checkFuel(float autoFuel){
        for (int j = 0; j < gasPools.length; j++) {
            if (gasPools[j].getBalance() >= autoFuel){
                return true;
            }
        }
        return false;
    }
    public void getInfo(){
        semaphore.availablePermits();
        System.out.println("___________________________________________________");
        System.out.println("___________________________________________________");
        System.out.println("___________________________________________________");
        for (int i = 0; i < gasPools.length; i++) {
            System.out.println(
                    "_________________"+gasPools[i].getIndexGas()+
                            ":"+gasPools[i].getBalance()+
                            "/"+GasPool.getStock()+"______________"
            );
        }
        System.out.println("___________________________________________________");
        System.out.println("___________________________________________________");
        System.out.println("___________________________________________________");
        semaphore.release();
    }
}
