package main.logic;

import main.model.Merchant;

import java.util.ArrayList;
import java.util.List;

/**
 * A service which offers functions to manage merchants.
 */
public class MerchantManagementService {
    public static final String MERCHANT_NOT_FOUND = "Merchant not found!";
    private static List<Merchant> merchants = new ArrayList<>();

    /**
     * Create a new merchant and save in list of users.
     *
     * @param merchantName merchant's name.
     * @param discountPercentage percentage of discount it offers.
     */
    public void saveNewMerchant(String merchantName, double discountPercentage){
        Merchant merchant = new Merchant(merchantName, discountPercentage);
        merchants.add(merchant);
    }

    /**
     * Get a merchant from the name.
     *
     * @param merchantName merchant's name.
     * @return merchant object if exist, null otherwise.
     */
    public Merchant getMerchantFromName(String merchantName){
        for(Merchant merchant : merchants){
            if(merchant.getMerchantName().equals(merchantName)){
                return merchant;
            }
        }
        return null;
    }

    /**
     * Get a of all the merchants.
     * @return list of merchants
     */
    public List<Merchant> getAllMerchants(){
        return merchants;
    }

    /**
     * Update discount percentage for given user.
     *
     * @param merchantName merchant's name.
     * @param discountPercentage discount percentage the merchant offers.
     * @throws Exception when merchant is not found.
     */
    public void updateDiscountPercentForMerchant(String merchantName, double discountPercentage) throws Exception {
        Merchant merchant = getMerchantFromName(merchantName);
        if(merchant != null)
            merchant.setDiscountPercentage(discountPercentage);
        else
            throw new Exception(MERCHANT_NOT_FOUND);
    }
}
