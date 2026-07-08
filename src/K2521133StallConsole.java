import java.util.Scanner;

// The stall screen. It runs the menu, walks the customer through building an order,
// and drives tracking, payment and feedback through the service.
public class K2521133StallConsole {

    private final K2521133StallService service = new K2521133StallService();
    private final K2521133InputReader input;

    public K2521133StallConsole(Scanner scanner) {
        this.input = new K2521133InputReader(scanner);
    }

    public void run() {
        System.out.println("=======================================");
        System.out.println("     Custom Ice Cream Stall System");
        System.out.println("=======================================");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = input.readInt("Select an option: ", 0, 6);
            System.out.println();
            switch (choice) {
                case 1 -> placeOrder();
                case 2 -> trackOrder();
                case 3 -> payOrder();
                case 4 -> giveFeedback();
                case 5 -> viewOrders();
                case 6 -> sendPromotion();
                case 0 -> running = false;
            }
            System.out.println();
        }
        System.out.println("Stall closed. Enjoy your ice cream.");
    }

    private void printMenu() {
        System.out.println("1. Place order");
        System.out.println("2. Track order");
        System.out.println("3. Pay for order");
        System.out.println("4. Give feedback");
        System.out.println("5. View orders");
        System.out.println("6. Send promotion to all customers");
        System.out.println("0. Exit");
    }

    private void placeOrder() {
        System.out.println("--- Place order ---");
        String name = input.readText("Customer name: ");
        K2521133ServingMethod serving = pickServing();
        K2521133Order order = service.createOrder(name, serving);

        boolean addMore = true;
        while (addMore) {
            K2521133IceCream iceCream = buildIceCream();
            int quantity = input.readInt("Quantity: ", 1, 50);
            order.addItem(new K2521133OrderItem(iceCream, quantity));
            System.out.printf("Added: %s x%d (LKR %,.2f each)%n",
                    iceCream.getDescription(), quantity, iceCream.getCost());
            addMore = input.readYesNo("Add another ice cream?");
        }

        System.out.println();
        System.out.println("Order " + order.getId() + " placed for " + name + ".");
        System.out.printf("Items: %d, total so far: LKR %,.2f%n",
                order.getItems().size(), order.getTotal());
    }

    // Uses the Builder to collect the choices and the Decorator to price them.
    private K2521133IceCream buildIceCream() {
        K2521133IceCreamBuilder builder = new K2521133IceCreamBuilder();
        builder.setBase(pickBase());
        builder.setFlavor(pickFlavor());

        while (true) {
            int choice = input.readInt(
                    "Add topping: 1 Nuts  2 Choco chips  3 Fruit  0 Done: ", 0, 3);
            if (choice == 0) {
                break;
            }
            builder.addTopping(K2521133Topping.values()[choice - 1]);
        }

        while (true) {
            int choice = input.readInt(
                    "Add sauce: 1 Chocolate  2 Caramel  3 Strawberry  0 Done: ", 0, 3);
            if (choice == 0) {
                break;
            }
            builder.addSauce(K2521133Sauce.values()[choice - 1]);
        }

        while (true) {
            int choice = input.readInt(
                    "Add extra: 1 Sprinkles  2 Whipped cream  3 Wafer  0 Done: ", 0, 3);
            if (choice == 0) {
                break;
            }
            builder.addExtra(K2521133Extra.values()[choice - 1]);
        }

        if (input.readYesNo("Add packaging?")) {
            int choice = input.readInt("Packaging: 1 Standard (50)  2 Gift box (120): ", 1, 2);
            if (choice == 1) {
                builder.withPackaging("Standard box", 50.00);
            } else {
                builder.withPackaging("Gift box", 120.00);
            }
        }

        return builder.build();
    }

    private void trackOrder() {
        System.out.println("--- Track order ---");
        K2521133Order order = requireOrder();
        if (order == null) {
            return;
        }
        System.out.println("Current status: " + order.getStatusLabel());
        if (order.getStatusLabel().equals("Delivered")) {
            System.out.println("This order is complete.");
            return;
        }
        if (input.readYesNo("Move to the next status?")) {
            service.advance(order.getId());
        }
    }

    private void payOrder() {
        System.out.println("--- Pay for order ---");
        K2521133Order order = requireOrder();
        if (order == null) {
            return;
        }
        System.out.printf("Amount due: LKR %,.2f%n", order.getTotal());

        K2521133DiscountStrategy discount = pickDiscount();
        K2521133PaymentStrategy payment = pickPayment();
        try {
            String receipt = service.pay(order.getId(), discount, payment);
            System.out.println(receipt);
        } catch (IllegalStateException e) {
            System.out.println("Could not pay: " + e.getMessage());
        }
    }

    private void giveFeedback() {
        System.out.println("--- Give feedback ---");
        K2521133Order order = requireOrder();
        if (order == null) {
            return;
        }
        int rating = input.readInt("Rating (1 to 5): ", 1, 5);
        String comment = input.readText("Comment: ");
        service.recordFeedback(order.getId(), rating, comment);
        System.out.println("Thank you for your feedback.");
    }

    private void viewOrders() {
        System.out.println("--- Orders ---");
        if (service.allOrders().isEmpty()) {
            System.out.println("No orders yet.");
            return;
        }
        for (K2521133Order order : service.allOrders()) {
            System.out.printf("%s  %-10s  %-9s  LKR %,.2f  %s%n",
                    order.getId(), order.getCustomerName(), order.getStatusLabel(),
                    order.getTotal(), order.isPaid() ? "paid" : "unpaid");
            for (K2521133OrderItem item : order.getItems()) {
                System.out.printf("     - %s x%d%n",
                        item.getIceCream().getDescription(), item.getQuantity());
            }
            if (order.getFeedback() != null) {
                System.out.println("     feedback: " + order.getFeedback());
            }
        }
    }

    private void sendPromotion() {
        System.out.println("--- Send promotion ---");
        if (service.allOrders().isEmpty()) {
            System.out.println("No customers to notify yet.");
            return;
        }
        String message = input.readText("Promotion message: ");
        service.broadcastPromotion(message);
    }

    private K2521133Order requireOrder() {
        String id = input.readText("Order id: ");
        K2521133Order order = service.find(id);
        if (order == null) {
            System.out.println("No order found with id " + id + ".");
        }
        return order;
    }

    private K2521133Base pickBase() {
        int choice = input.readInt("Base: 1 Cone (150)  2 Cup (120): ", 1, 2);
        return K2521133Base.values()[choice - 1];
    }

    private K2521133Flavor pickFlavor() {
        int choice = input.readInt(
                "Flavor: 1 Vanilla (200)  2 Chocolate (250)  3 Strawberry (220)  4 Mango (240): ", 1, 4);
        return K2521133Flavor.values()[choice - 1];
    }

    private K2521133ServingMethod pickServing() {
        int choice = input.readInt("Serving: 1 Pickup  2 Delivery: ", 1, 2);
        return K2521133ServingMethod.values()[choice - 1];
    }

    private K2521133DiscountStrategy pickDiscount() {
        int choice = input.readInt("Promotion: 1 None  2 Ten percent  3 Twenty percent: ", 1, 3);
        return switch (choice) {
            case 2 -> new K2521133PercentageDiscount(10);
            case 3 -> new K2521133PercentageDiscount(20);
            default -> new K2521133NoDiscount();
        };
    }

    private K2521133PaymentStrategy pickPayment() {
        int choice = input.readInt("Pay with: 1 Card  2 Wallet  3 QR code: ", 1, 3);
        return switch (choice) {
            case 1 -> new K2521133CardPayment(input.readText("Card number: "));
            case 2 -> new K2521133WalletPayment(input.readText("Wallet id: "));
            default -> new K2521133QrPayment();
        };
    }
}
