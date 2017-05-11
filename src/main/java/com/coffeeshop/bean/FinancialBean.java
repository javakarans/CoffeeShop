package com.coffeeshop.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.Date;

/**
 * Created by Amirhossein on 5/11/2017.
 */
@ManagedBean
@ViewScoped
public class FinancialBean {

    private Date today;
    private Date from;
    private Date to;

    @PostConstruct
    public void init(){
        today = new Date();
        from = new Date();
        to = new Date();
    }

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }
}
