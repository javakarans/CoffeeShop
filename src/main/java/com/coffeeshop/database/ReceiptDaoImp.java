package com.coffeeshop.database;

import com.coffeeshop.model.Receipt;

import java.util.List;

/**
 * Created by Amirhossein on 4/28/2017.
 */
public class ReceiptDaoImp implements ReceiptDao{

    private SQLService sqlService;

    public ReceiptDaoImp(){
        sqlService = new SQLService();
    }

    public boolean createReceipt(Receipt receipt) {
        return sqlService.create(receipt);
    }

    public boolean updateReceipt(Receipt receipt) {
        return sqlService.update(receipt);
    }

    public boolean deleteReceipt(Receipt receipt) {
        return sqlService.delete(receipt);
    }

    public List<Receipt> getAllReceipts() {
        List receipts = sqlService.getAllObjects(new Receipt());
        return receipts;
    }

    public List<Receipt> getReceiptsByReceiptId(String receiptId) {
        List receipts = sqlService.getObjectsBySpecialColumn(new Receipt(), "receiptId", receiptId);
        return receipts;
    }
}
