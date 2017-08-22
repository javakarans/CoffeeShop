package com.coffeeshop.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Amirhossein on 5/7/2017.
 */
@Entity
@Table
public class AdminSetting implements Serializable
{

    private long adminSettingId;
    private int trackNumber;
    private String receiptNumber;
    private String receiptAddress;
    private String firstPhoneNumber;
    private String secondPhoneNumber;
    private String receiptTitle;
    private String casherPrinterName;
    private String toucherPrinterName;
    private String gmailUser;
    private String gmailPassWord;
    private String receiverEmail;
    private String branchNanme;
    //for background
    private String imageUrl;
//    private String password;
    //for other admins
//    private int role;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    public long getAdminSettingId() {
        return adminSettingId;
    }

    public void setAdminSettingId(long adminSettingId) {
        this.adminSettingId = adminSettingId;
    }

    @Column
    public int getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }

    @Column
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Column
    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    @Column
    public String getReceiptAddress() {
        return receiptAddress;
    }

    public void setReceiptAddress(String receiptAddress) {
        this.receiptAddress = receiptAddress;
    }

    @Column
    public String getFirstPhoneNumber() {
        return firstPhoneNumber;
    }

    public void setFirstPhoneNumber(String firstPhoneNumber) {
        this.firstPhoneNumber = firstPhoneNumber;
    }

    @Column
    public String getSecondPhoneNumber() {
        return secondPhoneNumber;
    }

    public void setSecondPhoneNumber(String secondPhoneNumber) {
        this.secondPhoneNumber = secondPhoneNumber;
    }

    @Column
    public String getReceiptTitle() {
        return receiptTitle;
    }

    public void setReceiptTitle(String receiptTitle) {
        this.receiptTitle = receiptTitle;
    }

    @Column
    public String getCasherPrinterName() {
        return casherPrinterName;
    }

    public void setCasherPrinterName(String casherPrinterName) {
        this.casherPrinterName = casherPrinterName;
    }

    @Column
    public String getToucherPrinterName() {
        return toucherPrinterName;
    }

    public void setToucherPrinterName(String toucherPrinterName) {
        this.toucherPrinterName = toucherPrinterName;
    }

    public String getGmailUser() {
        return gmailUser;
    }

    public void setGmailUser(String gmailUser) {
        this.gmailUser = gmailUser;
    }

    public String getGmailPassWord() {
        return gmailPassWord;
    }

    public void setGmailPassWord(String gmailPassWord) {
        this.gmailPassWord = gmailPassWord;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getBranchNanme() {
        return branchNanme;
    }

    public void setBranchNanme(String branchNanme) {
        this.branchNanme = branchNanme;
    }

    //    @Column
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    @Column
//    public int getRole() {
//        return role;
//    }
//
//    public void setRole(int role) {
//        this.role = role;
//    }


}
