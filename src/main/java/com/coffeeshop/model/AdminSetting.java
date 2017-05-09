package com.coffeeshop.model;

import javax.persistence.*;

/**
 * Created by Amirhossein on 5/7/2017.
 */
@Entity
@Table
public class AdminSetting
{

    private long adminSettingId;
    private int trackNumber;
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
