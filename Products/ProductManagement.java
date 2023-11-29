package Products;

import Customers.Customer;
import Customers.RewardsCustomer;
import Helpers.Cls;
import Helpers.Round;
import Main.Main;
import Retail_Operations.CashRegister;
import Retail_Operations.Employee;

import java.util.*;

import Helpers.ObjectJson;
import Transactions.*;

import java.io.File;

import static Main.Main.execute;

public abstract class ProductManagement {
    /**
     * This method is the main menu for the product management menu.
     * @param employee The employee that is currently logged in.
     */
    public static void productManagement(Employee employee){
        Cls.cls();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the product management menu");
        System.out.println("Please select an option");
        System.out.println("1. Add a product");
        System.out.println("2. Remove a product");
        System.out.println("3. Edit a product");
        System.out.println("4. List all products");
        System.out.println("5. Return to main menu");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice){
            case 1:
                Cls.cls();
                System.out.println("Please enter the name of the product");
                String case1Name = scanner.nextLine();
                Product case1Product = getProduct(case1Name);
                if (case1Product != null){
                    Cls.cls();
                    System.out.println("Product already exists");
                    System.out.println(case1Product);
                    System.out.println("Press enter to try again");
                    scanner.nextLine();
                    execute(() -> productManagement(employee));
                    break;
                }
                System.out.println("Please enter the price of the product");
                double newPrice = scanner.nextDouble();
                scanner.nextLine();
                System.out.println("Please enter the description of the product");
                String newDescription = scanner.nextLine();
                System.out.println("Please enter the id of the product");
                String newId = scanner.nextLine();
                Product newProduct = new Product(case1Name, newPrice, newDescription, newId);
                ObjectJson.objectToJson(newProduct);
                Cls.cls();
                System.out.println("Product added successfully");
                System.out.println("Product details:");
                System.out.println(newProduct);
                System.out.println();
                System.out.println("Press enter to return to the product management menu");
                scanner.nextLine();
                execute(() -> productManagement(employee));
                break;
            case 2:
                Cls.cls();
                System.out.println("Please enter the name of the product");
                String case2Name = scanner.nextLine();
                Product case2Product = getProduct(case2Name);
                if (case2Product == null){
                    Cls.cls();
                    System.out.println("Product does not exist");
                    System.out.println("Press enter to try again");
                    scanner.nextLine();
                    execute(() -> productManagement(employee));
                    break;
                }
                System.out.println(case2Product);
                System.out.println("Are you sure you want to delete this product?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice == 1){
                    ObjectJson.deleteObject(case2Product.getJsonId(), Product.class);
                    Cls.cls();
                    System.out.println("Product deleted successfully");
                }else {
                    Cls.cls();
                    System.out.println("Product not deleted");
                }
                System.out.println("Press enter to return to the product management menu");
                scanner.nextLine();
                execute(() -> productManagement(employee));
                break;
            case 3:
                Cls.cls();
                System.out.println("Please enter the name of the product");
                String case3Name = scanner.nextLine();
                Product case3product = getProduct(case3Name);
                if (case3product == null){
                    Cls.cls();
                    System.out.println("Product does not exist");
                    System.out.println("Press enter to try again");
                    scanner.nextLine();
                    execute(() -> productManagement(employee));
                    break;
                }
                Cls.cls();
                System.out.println(case3product);
                System.out.println("Please select an option");
                System.out.println("1. Edit name");
                System.out.println("2. Edit price");
                System.out.println("3. Edit description");
                System.out.println("4. Edit id");
                System.out.println("5. Return to product management menu");
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice){
                    case 1:
                        System.out.println("Please enter the new name");
                        String editName = scanner.nextLine();
                        case3product.setName(editName);
                        ObjectJson.objectToJson(case3product);
                        Cls.cls();
                        System.out.println("Product edited successfully");
                        System.out.println("Product details:");
                        System.out.println(case3product);
                        System.out.println();
                        System.out.println("Press enter to return to the product management menu");
                        scanner.nextLine();
                        execute(() -> productManagement(employee));
                        break;
                    case 2:
                        System.out.println("Please enter the new price");
                        double editPrice = scanner.nextDouble();
                        scanner.nextLine();
                        case3product.setPrice(editPrice);
                        ObjectJson.objectToJson(case3product);
                        Cls.cls();
                        System.out.println("Product edited successfully");
                        System.out.println("Product details:");
                        System.out.println(case3product);
                        System.out.println();
                        System.out.println("Press enter to return to the product management menu");
                        scanner.nextLine();
                        execute(() -> productManagement(employee));
                        break;
                    case 3:
                        System.out.println("Please enter the new description");
                        String editDescription = scanner.nextLine();
                        case3product.setDescription(editDescription);
                        ObjectJson.objectToJson(case3product);
                        Cls.cls();
                        System.out.println("Product edited successfully");
                        System.out.println("Product details:");
                        System.out.println(case3product);
                        System.out.println();
                        System.out.println("Press enter to return to the product management menu");
                        scanner.nextLine();
                        execute(() -> productManagement(employee));
                        break;
                    case 4:
                        System.out.println("Please enter the new id");
                        String editId = scanner.nextLine();
                        scanner.nextLine();
                        case3product.setId(editId);
                        ObjectJson.objectToJson(case3product);
                        Cls.cls();
                        System.out.println("Product edited successfully");
                        System.out.println("Product details:");
                        System.out.println(case3product);
                        System.out.println();
                        System.out.println("Press enter to return to the product management menu");
                        scanner.nextLine();
                        execute(() -> productManagement(employee));
                        break;
                    case 5:
                        execute(() -> productManagement(employee));
                        break;
                    default:
                        System.out.println("Invalid choice");
                        System.out.println("Press enter to return to the product management menu");
                        scanner.nextLine();
                        execute(() -> productManagement(employee));
                        break;
                }

                break;
            case 4:
                Cls.cls();
                System.out.println("List of all products:");
                for (Product product : listAllProducts()) {
                    System.out.println(product);
                }
                System.out.println("Press enter to return to the product management menu");
                scanner.nextLine();
                Cls.cls();
                execute(() -> productManagement(employee));
                break;
            case 5:
                execute(() -> Main.employeeMenu(employee));
                break;
            default:
                System.out.println("Invalid choice");
                execute(() -> productManagement(employee));
                break;
        }
    }

    /**
     * This method lists all products in the Products folder.
     * @return An ArrayList of all products.
     */
    public static ArrayList<Product> listAllProducts() {
        File[] files = ObjectJson.listFiles(Product.class);
        ArrayList<Product> products = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                try {
                    Product product = ObjectJson.objectFromJson(file.getName(), Product.class);
                    products.add(product);
                } catch (Exception e) {
                    System.err.println("Error reading JSON file: " + e.getMessage());
                }
            }
        }
        return products;
    }

    /**
     * This method is the main menu for the cashier terminal.
     * @param employee The employee that is currently logged in.
     */
    public static void cashierTerminal(Employee employee) {
        CashRegister cashRegister = new CashRegister(10000, employee);
        Scanner scanner = new Scanner(System.in);
        Payment payment = null;
        Map<Integer, String> paymentTypes = new HashMap<>();
        TransactionList productCart = null;
        Discount discount = null;
        boolean checkOutComplete = false;
        while (!checkOutComplete) {
            Cls.cls();
            paymentTypes.put(1, "Cash");
            paymentTypes.put(2, "Credit");
            paymentTypes.put(3, "Debit");
            System.out.println("Welcome to the cashier terminal");
            System.out.println("Please select an option");
            System.out.println("1. Add products to the transaction");
            System.out.println("2. Remove a product");
            System.out.println("3. Add a discount");
            System.out.println("4. Complete the transaction");
            System.out.println("5. Return to main menu");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    TransactionList tempList = new TransactionList();
                    boolean finished = false;
                    while (!finished) {
                        Cls.cls();
                        Product selectedProduct = null;
                        while (selectedProduct == null) {
                            System.out.println("Please enter the name of the product. Enter -L to list all products.");
                            String addProduct = scanner.nextLine();
                            if (addProduct.equalsIgnoreCase("-L")) {
                                Cls.cls();
                                System.out.println("List of all products:");
                                listAllProducts().forEach(System.out::println);
                                System.out.println();
                                continue;
                            }
                            selectedProduct = getProduct(addProduct);
                            if (selectedProduct == null) {
                                Cls.cls();
                                System.out.println("Product does not exist");
                                System.out.println();
                            }
                        }
                        System.out.println(selectedProduct);
                        System.out.println();
                        System.out.println("Enter the quantity");
                        int quantity = scanner.nextInt();
                        scanner.nextLine();
                        for (int i = 0; i < quantity; i++) {
                            tempList.addTransaction(new Transaction(selectedProduct));
                        }
                        Cls.cls();
                        System.out.println("Product added successfully");
                        System.out.println("Current cart:");
                        System.out.println(tempList);
                        System.out.println();
                        System.out.println("Would you like to add another product?");
                        System.out.println("1. Yes");
                        System.out.println("2. No");
                        String addAnother = scanner.nextLine();
                        if (addAnother.equals("1") || addAnother.equalsIgnoreCase("y")) {
                            continue;
                        } else if (addAnother.equals("2") || addAnother.equalsIgnoreCase("n")) {
                            productCart = tempList;
                            finished = true;
                        } else {
                            System.out.println("Invalid choice");
                            System.out.println("Press enter to try again");
                            scanner.nextLine();
                        }
                    }
                    break;
                case 2:
                    Cls.cls();
                    if (productCart == null || productCart.getTransactions().isEmpty()) {
                        System.out.println("Cart is empty");
                        System.out.println("Press enter to return to the cashier terminal");
                        scanner.nextLine();
                        break;
                    }
                    System.out.println(productCart);
                    System.out.println("Please enter the name of the product");
                    String case2Name = scanner.nextLine();
                    Product case2Product = getProduct(case2Name);
                    if (case2Product == null) {
                        Cls.cls();
                        System.out.println("Product is not in the cart or the product does not exist");
                        System.out.println("Press enter to try again");
                        scanner.nextLine();
                        break;
                    }
                    int i = 0;
                    for (Transaction transaction : productCart.getTransactions()) {
                        if (transaction.getProduct().getName().equalsIgnoreCase(case2Product.getName())) {
                            i++;
                        }
                        if (i == 0) {
                            Cls.cls();
                            System.out.println("Product is not in the cart or the product does not exist");
                            System.out.println("Press enter to try again");
                            scanner.nextLine();
                            break;
                        }
                    }
                    boolean stillRemoving = true;
                    while(stillRemoving) {
                        Cls.cls();
                        System.out.println("There are " + i + " " + case2Product.getName() + " in the cart");
                        System.out.println("How many would you like to remove?");
                        int removeQuantity = scanner.nextInt();
                        scanner.nextLine();
                        if (removeQuantity > i) {
                            Cls.cls();
                            System.out.println("There are not that that many " + case2Product.getName() + " in the cart");
                            System.out.println("Press enter to try again");
                            scanner.nextLine();
                            continue;
                        }
                        Cls.cls();
                        System.out.println("Are you sure you want to delete " + removeQuantity + " " + case2Product.getName() + " from the cart?");
                        System.out.println("1. Yes");
                        System.out.println("2. No");
                        choice = scanner.nextInt();
                        scanner.nextLine();
                        if (choice == 1) {
                            for (int j = 0; j < removeQuantity; j++) {
                                productCart.removeTransaction(new Transaction(case2Product));
                            }
                            Cls.cls();
                            System.out.println("The products were removed successfully");
                            System.out.println("Current cart:");
                        } else {
                            Cls.cls();
                            System.out.println("Products not removed");
                        }
                        System.out.println(productCart);
                        stillRemoving = false;
                    }
                    Cls.cls();
                    System.out.println("Press enter to return to the cashier terminal");
                    scanner.nextLine();
                    break;
                case 3:
                    Cls.cls();
                    System.out.println("Please enter the discount amount. If the discount is a percentage, enter the percentage with a % sign. (eg. 10%)");
                    String discountAmount = scanner.nextLine();
                    if (discountAmount.contains("%")) {
                        discountAmount = discountAmount.replace("%", "");
                        double discountPercent = Double.parseDouble(discountAmount) / 100;
                        discount = new Discount(discountPercent, 0);
                        }else {
                        double discountAmountDouble = Double.parseDouble(discountAmount);
                        discount = new Discount(0, discountAmountDouble);
                        }
                        payment = new Payment(0, 0, "", discount, false);
                        Cls.cls();
                    System.out.println("Discount added successfully");
                    if (discount.getDiscountAmount() > 0) {
                        System.out.println("Discount amount: $" + discount.getDiscountAmount());
                    }
                    if (discount.getDiscountPercent() > 0) {
                        System.out.println("Discount percent: " + discount.getDiscountPercent() * 100 + "%");
                    }
                    System.out.println("Press enter to return to the cashier terminal");
                    scanner.nextLine();
                    break;
                case 4:
                    if (productCart == null || productCart.getTransactions().isEmpty()) {
                        Cls.cls();
                        System.out.println("Cart is empty");
                        System.out.println("Press enter to return to the cashier terminal");
                        scanner.nextLine();
                        break;
                    }
                    System.out.println("Enter the customer's name");
                    String customerName = scanner.nextLine();
                    Customer customer = null;
                    for (File file : Objects.requireNonNull(ObjectJson.listFiles(Customer.class))) {
                        Customer search = ObjectJson.objectFromJson(file.getName(), Customer.class);
                        if (search == null) {
                            System.out.println("Error reading JSON file");
                        }else {
                            if (search.getName().equals(customerName)) {
                                if (file.getName().contains("RewardsCustomer")) {
                                    customer = ObjectJson.objectFromJson(file.getName(), RewardsCustomer.class);
                                } else {
                                    customer = ObjectJson.objectFromJson(file.getName(), Customer.class);
                                }
                            }
                        }
                    }
                    if (customer == null) {
                        System.out.println("Customer does not exist");
                        System.out.println("Creating new customer");
                        System.out.println("Enter the customer's phone number");
                        String customerPhoneNumber = scanner.nextLine();
                        customer = new Customer(customerName, customerPhoneNumber);
                        ObjectJson.objectToJson(customer);
                        System.out.println("Customer created successfully");
                    }
                    Cls.cls();
                    System.out.println("Customer found");
                    System.out.println(customer);
                    if ((!(customer instanceof RewardsCustomer))) {
                        System.out.println("Would the customer like to create a rewards account?");
                        System.out.println("1. Yes");
                        System.out.println("2. No");
                        int createRewards = scanner.nextInt();
                        scanner.nextLine();
                        if (createRewards == 1) {
                            Cls.cls();
                            System.out.println("Creating rewards account");
                            System.out.println("Enter the customer's email");
                            String customerEmail = scanner.nextLine();
                            customer = RewardsCustomer.upgradeCustomerToRewards(customer, customerEmail, 250);
                            ObjectJson.deleteObject(customer.getJsonId(), Customer.class);
                            ObjectJson.objectToJson(customer);
                            Cls.cls();
                            System.out.println("Rewards account created successfully");
                            System.out.println(customer);
                        }
                    }
                    Cls.cls();
                    System.out.println(productCart);
                    double subTotal = Round.round(productCart.getSubTotal());
                    double total = Round.round(Payment.totalWithTax(subTotal));
                    double tax = Round.round(productCart.getSubTotal() * Payment.getDefaultTax());
                    System.out.println("Tax: $" + tax);
                    System.out.println("Sub Total after tax: $" + total);
                    System.out.println();
                    if (customer instanceof RewardsCustomer rewardsCustomer) {
                        double rewardPoints = rewardsCustomer.getLoyaltyAccount().getPoints();
                        System.out.println("Points Available: " + rewardPoints);
                        System.out.println();
                        if (rewardsCustomer.getLoyaltyAccount().getPoints() >= 100) {
                            System.out.println("Customer has enough points for a discount. View discount options?");
                            System.out.println("1. Yes");
                            System.out.println("2. No");
                            int applyDiscount = scanner.nextInt();
                            scanner.nextLine();
                            if (applyDiscount == 1) {
                                Cls.cls();
                                System.out.println("Discount tiers available:");
                                rewardsCustomer.getLoyaltyAccount().getDiscountTiers(total);
                                System.out.println();
                                System.out.println("Total: " + total);
                                System.out.println("Would the customer like to use their points for a discount?");
                                System.out.println("1. Yes");
                                System.out.println("2. No");
                                int usePoints = scanner.nextInt();
                                scanner.nextLine();
                                if (usePoints == 1) {
                                    Cls.cls();
                                    int tiersAvailable = rewardsCustomer.getLoyaltyAccount().getDiscountTiers(total);
                                    System.out.println("Enter the tier number the customer would like to use");
                                    int tier = scanner.nextInt();
                                    scanner.nextLine();
                                    while (tier > tiersAvailable || tier < 1 || total < tier * 10) {
                                        Cls.cls();
                                        if (tier > tiersAvailable || tier < 1) {
                                            System.out.println("Invalid tier number");
                                            System.out.println();
                                        }
                                        if (total < tier * 10) {
                                            System.out.println("Discount is greater than the total");
                                            System.out.println();
                                        }
                                        System.out.println("Discount tiers available:");
                                        rewardsCustomer.getLoyaltyAccount().getDiscountTiers(total);
                                        System.out.println();
                                        System.out.println("Total: " + total);
                                        System.out.println("Enter the tier number the customer would like to use");
                                        tier = scanner.nextInt();
                                        scanner.nextLine();
                                    }
                                    if(discount == null) {
                                       discount = new Discount(rewardsCustomer.getLoyaltyAccount().getRewardsDiscountPercent(), tier * 10);
                                    }else{
                                        double currentDiscount = discount.getDiscountAmount();
                                        discount.setDiscountAmount(currentDiscount + (tier * 10));
                                    }
                                    double discounted = total - discount.getDiscountAmount();
                                    double discountedTotal = Round.round(discounted - (discounted * discount.getDiscountPercent()));
                                    Cls.cls();
                                    System.out.println(productCart);
                                    System.out.println("Discount Selected");
                                    System.out.println();
                                    System.out.println("Total: $" + total);
                                    System.out.println("Points Discount: $" + discount.getDiscountAmount());
                                    System.out.println("Loyalty Discount: " + discount.getDiscountPercent() * 100 + "%");
                                    System.out.println("Discounted total: $" + discountedTotal);
                                    System.out.println();
                                    System.out.println("Would the customer like to apply the discount?");
                                    System.out.println("1. Yes");
                                    System.out.println("2. No");
                                    int apply = scanner.nextInt();
                                    scanner.nextLine();
                                    Cls.cls();
                                    if (apply == 1) {
                                        payment = new Payment(total, 0, "", discount, false);
                                        payment.applyDiscount(total, discount, rewardsCustomer);
                                        System.out.println("Discount applied successfully");
                                        System.out.println("Discounted total: $" + payment.getAmountDue());
                                        System.out.println("Saved Today: $" + payment.getSavedToday());
                                        System.out.println("Points used: " + payment.getPointsUsed());
                                        System.out.println("Points earned: " + payment.getPointsEarned());
                                        System.out.println("New Point Balance: " + rewardsCustomer.getLoyaltyAccount().getPoints());
                                        System.out.println();
                                    } else {
                                        System.out.println("Discount not applied");
                                    }
                                    System.out.println("Press enter to continue");
                                    scanner.nextLine();
                                }
                            }
                        }
                        if (payment != null && payment.getAmountDue() == 0) {
                            double currentDiscountPercent = discount.getDiscountPercent();
                            discount.setDiscountPercent(currentDiscountPercent + rewardsCustomer.getLoyaltyAccount().getRewardsDiscountPercent());
                            payment.applyDiscount(total, discount, rewardsCustomer);
                        }
                    }else{
                        if (discount != null) {
                           payment.setDiscount(discount);
                           payment.applyDiscount(total, discount, customer);
                        }
                    }
                    Cls.cls();
                    System.out.println("How would the customer like to pay?");
                    System.out.println("1. Cash");
                    System.out.println("2. Credit");
                    System.out.println("3. Debit");
                    int paymentChoice = scanner.nextInt();
                    scanner.nextLine();
                    String paymentType = paymentTypes.get(paymentChoice);
                    if (payment == null) {
                        payment = new Payment(total, total, paymentType, false);
                    } else {
                        payment.setPaymentType(paymentType);
                    }
                    if (paymentType.equals("Cash")) {
                        System.out.println("Sub Total: $" + subTotal);
                        System.out.println("Tax: $" + tax);
                        if(payment.getDiscount() != null) {
                            if(payment.getDiscount().getDiscountAmount() > 0) {
                                System.out.println("Discount: $" + payment.getDiscount().getDiscountAmount());
                            }
                            if(payment.getDiscount().getDiscountPercent() > 0) {
                                System.out.println("Discount: " + payment.getDiscount().getDiscountPercent() * 100 + "%");
                            }
                        }
                        System.out.println("Total: $" + payment.getAmountDue());
                        System.out.println();
                        System.out.println("Enter the cash given");
                        payment.setAmountPaid(scanner.nextDouble());
                        scanner.nextLine();
                    } else {
                        payment.setAmountPaid(payment.getAmountDue());
                    }
                    System.out.println("Change due: $" + Round.round(payment.getChangeDue()));
                    System.out.println("Press enter to complete the transaction");
                    scanner.nextLine();
                    Cls.cls();
                    System.out.println("Confirm Transaction");
                    System.out.println(customer.getName());
                    System.out.println();
                    System.out.println("Cart:");
                    System.out.println(productCart);
                    System.out.println();
                    System.out.println("Subtotal: $" + subTotal);
                    System.out.println("Tax: $" + tax);
                    if (payment.getDiscount() != null && customer instanceof RewardsCustomer) {
                        System.out.println("Points Used: " + payment.getPointsUsed());
                        System.out.println("Points Earned: " + payment.getPointsEarned());
                        System.out.println("Rewards Discount: " + payment.getDiscount().getDiscountPercent() * 100 + "%");
                        System.out.println("Discount Amount: $" + payment.getDiscount().getDiscountAmount());
                        System.out.println("Saved Today: $" + payment.getSavedToday());
                    }else if(payment.getDiscount() != null){
                        if(payment.getDiscount().getDiscountAmount() > 0) {
                            System.out.println("Dollar Discount: $" + payment.getDiscount().getDiscountAmount());
                        }
                        if(payment.getDiscount().getDiscountPercent() > 0){
                            System.out.println("Percent Discount: " + payment.getDiscount().getDiscountPercent() * 100 + "% of the total");
                        }
                        System.out.println("Saved Today: $" + payment.getSavedToday());
                    }
                    System.out.println("---------------------------");
                    System.out.println("Total: $" + payment.getAmountDue());
                    System.out.println("Amount paid: $" + payment.getAmountPaid() + " - " + payment.getPaymentType());
                    System.out.println("Change Given: $" + payment.getChangeDue());
                    System.out.println("Would you like to complete the transaction?");
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    int complete = scanner.nextInt();
                    scanner.nextLine();
                    if (complete == 1) {
                        Cls.cls();
                        Receipt receipt = null;
                        if (customer instanceof RewardsCustomer rewardsCustomer) {
                            receipt = new DiscountReceipt(customer.getName(), payment, cashRegister.getCashRegisterNumber(), productCart, payment.getDiscount(), rewardsCustomer.getLoyaltyAccount().getPoints());
                        } else if (payment.getDiscount() != null) {
                            receipt = new DiscountReceipt(customer.getName(), payment, cashRegister.getCashRegisterNumber(), productCart, payment.getDiscount());
                        }else {
                            receipt = new Receipt(customer.getName(), payment, cashRegister.getCashRegisterNumber(), productCart.getTransactions());
                        }
                        payment.completePayment(cashRegister, receipt);
                        customer.addReceipt(receipt);
                        if(customer instanceof RewardsCustomer rewardsCustomer){
                            System.out.println("Points Earned: " + payment.getPointsEarned());
                            System.out.println("New Points Balance: "  + rewardsCustomer.getLoyaltyAccount().getPoints());
                        }
                        customer.updateSaveFile();
                        Cls.cls();
                        System.out.println("Transaction completed successfully");
                        System.out.println("Receipt:");
                        System.out.println(receipt);
                        checkOutComplete = true;
                    } else {
                        checkOutComplete = true;
                        Cls.cls();
                        System.out.println("Check out canceled");
                        execute(() -> cashierTerminal(employee));
                    }
                    System.out.println("Press enter to return to the cashier terminal");
                    scanner.nextLine();
                    execute(() -> cashierTerminal(employee));
                    break;
                case 5:
                    execute(() -> Main.employeeMenu(employee));
                    break;
                default:
                    System.out.println("Invalid choice");
                    System.out.println("Press enter to try again");
                    scanner.nextLine();
                    execute(() -> cashierTerminal(employee));
                    break;
            }
        }
    }

    /**
     * This method reurns a product object based on the name of the product.
     * @param name The name of the product.
     * @return The product object.
     */
    public static Product getProduct(String name){
        ArrayList<Product> products = listAllProducts();
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)){
                return product;
            }
        }
        return null;
    }
}
