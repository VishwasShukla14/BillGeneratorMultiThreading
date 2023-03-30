package billgenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class CONSTANTS {

    public static final int MAX_QUEUE_LENGTH = 10;
    public static final int TAX_AMOUNT = 199;
    public static final int THREAD_COPIES = 5;

    private static final int MAX_USERS = 100;

    private CONSTANTS(){}

    public static BlockingQueue<Integer> GET_USER_IDS(){
        BlockingQueue<Integer> userIds = new ArrayBlockingQueue<>(MAX_USERS);

        for(int id=1;id<=MAX_USERS;id++){
            userIds.add(id);
        }

        return userIds;
    }

    public static Map<String,Integer> GET_PLAN(){
        Map<String,Integer> plan = new HashMap<>();
        plan.put("PLAN1",450);
        plan.put("PLAN2",250);
        plan.put("PLAN3",4000);
        plan.put("PLAN4",129);
        plan.put("PLAN5",999);
        plan.put("PLAN6",559);
        return plan;
    }

}
