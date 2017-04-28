package com.coffeeshop.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Mohammad on 4/28/2017.
 */
@Entity
@Table
public class Kitchen implements Serializable {

    private long kitchenId;
    private String name;
    private String staticIp;
    private String macAddress;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true , nullable = false)
    public long getKitchenId() {
        return kitchenId;
    }

    public void setKitchenId(long kitchenId) {
        this.kitchenId = kitchenId;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public String getStaticIp() {
        return staticIp;
    }

    public void setStaticIp(String staticIp) {
        this.staticIp = staticIp;
    }

    @Column
    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
}
