package main;

import main.logic.MerchantManagementService;
import main.logic.ReportingService;
import main.logic.TransactionManagementService;
import main.logic.UserManagementService;
import main.model.User;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

    public class Driver {
        private static final String NEW_USER = "new user";
        private static final String NEW_MERCHANT = "new merchant";
        private static final String NEW_TXN = "new txn";
        private static final String UPDATE_MERCHANT = "update merchant";
        private static final String PAYBACK = "payback";
        private static final String REPORT_DISCOUNT = "report discount";
        private static final String REPORT_DUES = "report dues";
        private static final String REPORT_USERS_AT_CREDIT_LIMIT = "report users-at-credit-limit";
        private static final String REPORT_TOTAL_DUES = "report total-dues";

        private static TransactionManagementService transactionManagementService = new TransactionManagementService();
        private static MerchantManagementService merchantManagementService = new MerchantManagementService();
        private static UserManagementService userManagementService = new UserManagementService();
        private static ReportingService reportingService = new ReportingService();

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(
                    "To create new user, enter 'new user'\n"
                            + "To create new merchant, enter 'new merchant'\n"
                            + "To create new transaction, enter 'new txn'\n"
                            + "To update existing merchant, enter 'update merchant'\n"
                            + "To payback due, enter 'payback'\n"
                            + "To report discount, enter 'report discount'\n"
                            +"To report overall dues for a user, enter 'report dues'\n"
                            + "To report users at credit limit, enter 'report users-at-credit-limit'\n"
                            + "To report overall dues, enter 'report total-dues'\n"
            );
            System.out.println("What you have in mind?");

            do{
                String input = scanner.nextLine();
                switch(input){
                    case NEW_USER:
                        handleUserCreation(scanner);
                        break;

                    case NEW_MERCHANT:
                        handleMerchantCreation(scanner);
                        break;

                    case NEW_TXN:
                        handleTransactionCreation(scanner);
                        break;

                    case UPDATE_MERCHANT:
                        handleMerchantDiscountUpdate(scanner);
                        break;

                    case PAYBACK:
                        try {
                            handleUserPayback(scanner);
                        } catch (Exception exception) {
                            System.out.println(exception.getMessage());
                        }
                        break;

                    case REPORT_DISCOUNT:
                        handleReportOverallMerchantDiscount(scanner);
                        break;

                    case REPORT_DUES:
                        handleReportOverallDues(scanner);
                        break;

                    case REPORT_USERS_AT_CREDIT_LIMIT:
                        handleReportUsersAtCreditLimit(scanner);
                        break;

                    case REPORT_TOTAL_DUES:
                        handleReportOverallDuesForAllUsers(scanner);
                        break;

                    default:
                        System.out.println("\nWhat you have in mind?");
                        break;
                }
            }while (true);
        }

        private static void handleUserCreation(Scanner scanner){
            System.out.println("Enter User Info: name, email, credit limit:");
            userManagementService.saveNewUser(scanner.next(), scanner.next(), scanner.nextDouble());
            System.out.println(userManagementService.getAllUsers());
        }

        private static void handleMerchantCreation(Scanner scanner){
            System.out.println("Enter Merchant Info: name, percent-discount:");
            merchantManagementService.saveNewMerchant(scanner.next(),scanner.nextDouble());
            System.out.println(merchantManagementService.getAllMerchants());
        }

        private static void handleTransactionCreation(Scanner scanner){
            System.out.println("Enter Transaction Info: user name, merchant name, transaction amount:");
            String userName = scanner.next();
            try{
                transactionManagementService.processTransaction(userName, scanner.next(), scanner.nextDouble());
            }catch (Exception exception){
                System.out.println(exception.getMessage());
            }
            System.out.println("Current status:");
            System.out.println(transactionManagementService.getTransactions());
            System.out.println(userManagementService.getUserFromUserName(userName));
        }

        private static void handleMerchantDiscountUpdate(Scanner scanner){
            System.out.println("Enter Merchant Info: name, updated percent-discount:");
            String merchantName = scanner.next();
            try{
                merchantManagementService.updateDiscountPercentForMerchant(merchantName, scanner.nextDouble()/100);
            }catch (Exception exception){
                System.out.println(exception.getMessage());
            }
            System.out.println("Current status : " + merchantManagementService.getMerchantFromName(merchantName));
        }

        private static void handleUserPayback(Scanner scanner) throws Exception {
            System.out.println("Enter user name:");
            String userName = scanner.next();
            double dueAmount = 0;

            dueAmount = userManagementService.getDueAmountForUser(userName);

            System.out.println("Current due amount: "+ dueAmount);
            System.out.println("Enter due amount you want to settle:");
            double paybackAmount = scanner.nextDouble();
            userManagementService.paybackDueAmount(userName, paybackAmount);

            System.out.println("Current status: "+ userManagementService.getUserFromUserName(userName));
        }

        private static void handleReportOverallMerchantDiscount(Scanner scanner){
            double totalDiscount = 0;
            System.out.println("Enter merchant name: ");
            try {
                totalDiscount = reportingService.getOverallDiscountFromMerchant(scanner.next());
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
            System.out.println("Total accumulated discount for the merchant:" + totalDiscount);
        }

        private static void handleReportOverallDues(Scanner scanner){
            System.out.println("Enter user name to get dues:");
            String userName2 = scanner.next();

            List<Double> userDueAmounts = null;
            try{
                userDueAmounts = reportingService.getOverallDuesForUser(userName2);
            }catch (Exception exception){
                System.out.println(exception.getMessage());
            }

            System.out.println("The due amount for user "+ userName2 + "so far ");
            System.out.println(userDueAmounts);
        }

        private static void handleReportUsersAtCreditLimit(Scanner scanner){
            List<User> usersWithExhaustedCreditLimit = reportingService.getUsersWithExhaustedCreditLimit();
            System.out.println("Users who have reached their credit limit:");
            System.out.println(usersWithExhaustedCreditLimit);
        }

        private static void handleReportOverallDuesForAllUsers(Scanner scanner){
            Map<String, List<Double>> totalDueAmount = reportingService.getOverallDuesForAllUsers();
            System.out.println("Total Dues For All Users: " );
            totalDueAmount.forEach((String name, List<Double> dues) -> {
                System.out.println(name);
                System.out.println(dues);
            });
        }
    }

