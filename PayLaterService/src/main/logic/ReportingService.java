package main.logic;

import main.model.User;

import java.util.List;
import java.util.Map;

/**
 * A service for reporting different matrices.
 */
public class ReportingService {
    private UserManagementService userManagementService = new UserManagementService();
    private MerchantManagementService merchantManagementService = new MerchantManagementService();
    private static TransactionManagementService transactionManagementService = new TransactionManagementService();

    public List<Double> getOverallDuesForUser(String userName) throws Exception {
        return userManagementService.getOverallDuesForUser(userName);
    }

    public Map<String, List<Double>> getOverallDuesForAllUsers() {
        return userManagementService.getOverallDuesForAllUsers();
    }

    public List<User> getUsersWithExhaustedCreditLimit() {
        return userManagementService.getUsersWithExhaustedCreditLimit();
    }

    public double getOverallDiscountFromMerchant(String merchantName) throws Exception {
        return transactionManagementService.getOverallDiscountFromMerchant(merchantName);
    }
}