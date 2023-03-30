package billgenerator;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BillGenerator {

    private static final BlockingQueue<User> fetchPlanBlockingQueue = new ArrayBlockingQueue<>(CONSTANTS.MAX_QUEUE_LENGTH);
    private static final BlockingQueue<User> deliveryBillBlockingQueue = new ArrayBlockingQueue<>(CONSTANTS.MAX_QUEUE_LENGTH);
    private static final BlockingQueue<Integer> userIds = CONSTANTS.GET_USER_IDS();

    public void startApplication() {

        int index;

        Thread[] fetchThreads = new Thread[CONSTANTS.THREAD_COPIES];
        Thread[] calculateThreads = new Thread[CONSTANTS.THREAD_COPIES];
        Thread[] deliveryThreads = new Thread[CONSTANTS.THREAD_COPIES];

        // instantiation threads.
        for (index=0; index < CONSTANTS.THREAD_COPIES ; index++) {

            fetchThreads[index] = new Thread(new FetchPlan());
            fetchThreads[index].setName("Fetching-Thread-"+(index+1));

            calculateThreads[index] = new Thread(new CalculatingBill());
            calculateThreads[index].setName("Calculating-Thread-"+(index+1));

            deliveryThreads[index] = new Thread(new BillDelivery());
            deliveryThreads[index].setName("Delivering-Thread-"+(index+1));

        }

        // starting threads.
        for (index=0; index<CONSTANTS.THREAD_COPIES; index++){

            fetchThreads[index].start();
            calculateThreads[index].start();
            deliveryThreads[index].start();

        }

    }

    public static int takeId() {

        try {
            return userIds.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return 0;

    }

    public static User takeRequest() {

        try {
            return fetchPlanBlockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static void addRequest(User user) {

        try {
            fetchPlanBlockingQueue.put(user);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void addToDelivery(User user) {

        try {
            deliveryBillBlockingQueue.put(user);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static User takeDelivery() {

        try {
            return deliveryBillBlockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;

    }

}
