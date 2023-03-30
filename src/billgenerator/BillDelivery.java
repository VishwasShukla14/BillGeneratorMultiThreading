package billgenerator;

public class BillDelivery implements Runnable {

    @Override
    public void run() {

        while (true) {

            User user = BillGenerator.takeDelivery();
            printUserBill(user);

        }
    }


    private void printUserBill(User user) {
        System.out.println("\n************************** The generated bill for user " + user.getId() + " is " + user.getPlan() + " delivered by " + Thread.currentThread().getName() + " **************************\n");
    }

}
