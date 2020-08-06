package main.logic;

import main.model.Merchant;
import main.model.Transaction;
import main.model.User;

import java.util.ArrayList;
import java.util.List;

import static main.logic.MerchantManagementService.MERCHANT_NOT_FOUND;
import static main.logic.UserManagementService.USER_NOT_FOUND;

/**
 * A service with functionalities to manage transactions between user and merchant.
 */
public class TransactionManagementService {
    public static final String INVALID_TRANSACTION = "Invalid Transaction! Transaction amount exceed credit limit!";

    private static List<Transaction> transactions = new ArrayList<>();

    private UserManagementService userManagementService = new UserManagementService();
    private MerchantManagementService merchantManagementService = new MerchantManagementService();

    /**
     * Check if the transaction is valid or not.
     *
     * @param remainingCreditLimit
     * @param transactionAmount
     * @return true if remainingCreditLimit exceeds transactionAmount, false otherwise.
     */
    private boolean isValidTransaction(double remainingCreditLimit, double transactionAmount){
        return transactionAmount <= remainingCreditLimit;
    }

    /**
     * Process transaction and updates related fields from user and transaction.
     *
     * @param userName
     * @param merchantName
     * @param transactionAmount
     * @throws Exception when user or merchant is not found, transaction is invalid.
     */
    public void processTransaction(String userName, String merchantName, double transactionAmount) throws Exception {
        User user = userManagementService.getUserFromUserName(userName);
        if(user == null)
            throw new Exception(USER_NOT_FOUND);

        Merchant merchant = merchantManagementService.getMerchantFromName(merchantName);
        if(merchant == null)
            throw new Exception(MERCHANT_NOT_FOUND);

        if(!isValidTransaction(user.getRemainingCreditLimit(), transactionAmount))
            throw new Exception(INVALID_TRANSACTION);

        //update remainingCreditLimit
        user.updateRemainingCreditLimit(transactionAmount);

        //update dueAmount
        user.updateDueAmount(transactionAmount);

        Transaction transaction = new Transaction(userName, merchantName, transactionAmount);

        //update discount
        double discountAmount = transactionAmount * merchant.getDiscountPercentage();
        transaction.setDiscountAmount(discountAmount);

        //update actualPayableAmount
        double actualPayableAmount = transactionAmount - discountAmount;
        transaction.setActualPaybleAmount(actualPayableAmount);

        //save the transaction
        transactions.add(transaction);
    }

    /**
     * Get overall discount received from given merchant.
     *
     * @param merchantName
     * @return overall discount.
     * @throws Exception when merchant is not found.
     */
    public double getOverallDiscountFromMerchant(String merchantName) throws Exception {
        Merchant merchant = merchantManagementService.getMerchantFromName(merchantName);
        if(merchant == null)
            throw new Exception(MERCHANT_NOT_FOUND);

        double totalDiscount = 0;
        for(Transaction transaction : transactions){
            if(transaction.getMerchantName().equals(merchantName))
                totalDiscount += transaction.getDiscountAmount();
        }
        return totalDiscount;
    }

    public List<Transaction> getTransactions(){
        return transactions;
    }
}
