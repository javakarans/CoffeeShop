package com.coffeeshop.database;

import com.coffeeshop.model.Receipt;

import java.util.List;

/**
 * Created by Amirhossein on 4/28/2017.
 */
public interface ReceiptDao {

    public boolean createReceipt(Receipt receipt);
    public boolean updateReceipt(Receipt receipt);
    public boolean deleteReceipt(Receipt receipt);
    public List<Receipt> getAllReceipts();
    public List<Receipt> getReceiptsByReceiptId(String receiptId);

}
