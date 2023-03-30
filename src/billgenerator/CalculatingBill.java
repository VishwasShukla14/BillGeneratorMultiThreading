package billgenerator;

public class CalculatingBill implements Runnable {

    @Override
    public void run() {

        while (true) {
            User user = BillGenerator.takeRequest();
            user = calculateBill(user);
            BillGenerator.addToDelivery(user);
        }

    }

    private User calculateBill(User user) {
        System.out.println("Calculating the total bill for user " + user.getId() + " by " + Thread.currentThread().getName());
        user.setPlan(user.getPlan() + CONSTANTS.TAX_AMOUNT);
        return user;
    }

}
