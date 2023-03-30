package billgenerator;

import java.util.Map;
import java.util.Random;


public class FetchPlan implements Runnable {

    private final Map<String, Integer> plan;
    private final Random random = new Random();

    public FetchPlan() {
        this.plan = CONSTANTS.GET_PLAN();
    }

    @Override
    public void run() {

        while (true) {
            BillGenerator.addRequest(fetchBill(BillGenerator.takeId()));
        }

    }

    private User fetchBill(int idCount) {
        User user = new User();
        user.setId(idCount);
        int planNumber = random.nextInt(plan.size());
        String planKey = "PLAN" + (planNumber + 1);
        int planAmount = plan.get(planKey);
        user.setPlan(planAmount);
        System.out.println("Plan fetched for user " + (user.getId()) + "  by " + Thread.currentThread().getName());
        return user;
    }

}
