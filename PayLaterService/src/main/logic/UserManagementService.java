package main.logic;

import main.model.Transaction;
import main.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A service with functionalities to manage users.
 */
public class UserManagementService {
    public static final String USER_NOT_FOUND = "User not found!";
    public static final String PAYBACK_EXCEEDS_DUE = "Payback amount can't be greater than due amount!";

    private static TransactionManagementService transactionManagementService = new TransactionManagementService();

    private static List<User> users = new ArrayList<>();

    /**
     * Create a new user and save in list of users.
     *
     * @param userName
     * @param userEmail
     * @param originalCreditLimit
     */
    public void saveNewUser(String userName, String userEmail, double originalCreditLimit){
        User user = new User(userName, userEmail, originalCreditLimit);
        users.add(user);
    }

    /**
     * Get a user from his name.
     *
     * @param userName
     * @return user
     */
    public User getUserFromUserName(String userName){
        for(User user : users){
            if(user.getUserName().equals(userName))
                return user;
        }
        return null;
    }

    public double getDueAmountForUser(String userName) throws Exception {
        User user = getUserFromUserName(userName);
        if(user == null)
            throw new Exception(USER_NOT_FOUND);
        return user.getDueAmount();
    }

    /**
     * Get all the users.
     *
     * @return list of all the users
     */
    public List<User> getAllUsers(){
        return users;
    }

    /**
     * Payback partial or full due amount for given user.
     *
     * @param userName
     * @param paybackAmount
     * @throws Exception when payback amount exceeds due amount and user does not exist.
     */
    public void paybackDueAmount(String userName, double paybackAmount) throws Exception {
        User user = getUserFromUserName(userName);
        if(user == null)
            throw new Exception(USER_NOT_FOUND);

            double dueAmount = user.getDueAmount();
            if(paybackAmount > dueAmount){
                throw new Exception(PAYBACK_EXCEEDS_DUE);
            }
            //reduce due amount
            dueAmount -= paybackAmount;
            user.setDueAmount(dueAmount);

            //increase remainingCreditLimit
            double remainingCreditLimit = user.getRemainingCreditLimit();
            remainingCreditLimit += paybackAmount;
            user.setRemainingCreditLimit(remainingCreditLimit);

    }

    /**
     * Get list of overall dues for given user so far.
     *
     * @param userName
     * @return list of overall dues for given user so far.
     * @throws Exception when user does not exist.
     */
    public List<Double> getOverallDuesForUser(String userName) throws Exception {
        User user = getUserFromUserName(userName);
        if(user == null)
            throw new Exception(USER_NOT_FOUND);

        List<Double> totalDues = new ArrayList<>();
        for( Transaction transaction : transactionManagementService.getTransactions()){
            if(transaction.getUserName().equals(userName))
                totalDues.add(transaction.getTransactionAmount());
        }
        return totalDues;
    }

    /**
     * Get all the users and their overall dues so far.
     *
     * @return a mapping of user's name and his overall dues so far.
     */
    public Map<String, List<Double>> getOverallDuesForAllUsers(){
        Map<String, List<Double>> userNameTotalDuesMap = new HashMap<>();
        for(User user : users){
            List<Double> dueAmounts = new ArrayList<>();
            String userName = user.getUserName();
            for( Transaction transaction : transactionManagementService.getTransactions()){
                if(transaction.getUserName().equals(userName)){
                    dueAmounts.add(transaction.getTransactionAmount());
                }
            }
            userNameTotalDuesMap.put(userName, dueAmounts);
        }
        return userNameTotalDuesMap;
    }

    /**
     * Get a list of users with no remaining credit limit.
     *
     * @return a list of users.
     */
    public List<User> getUsersWithExhaustedCreditLimit(){
        List<User> usersWithExhaustedCreditLimit = new ArrayList<>();
        for(User user : users){
            if(user.getRemainingCreditLimit() == 0){
                usersWithExhaustedCreditLimit.add(user);
            }
        }
        return usersWithExhaustedCreditLimit;
    }
}
