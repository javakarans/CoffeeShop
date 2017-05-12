package com.coffeeshop.bean;

import com.coffeeshop.database.KitchenDaoImp;
import com.coffeeshop.model.*;
import com.coffeeshop.model.Kitchen;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class AddKitchenBean {
    private KitchenDaoImp kitchenDaoImp;
    private com.coffeeshop.model.Kitchen kitchen;
    private List<com.coffeeshop.model.Kitchen> kitchenList;
    private List<String> printerNameList;

    @PostConstruct
    public void init()
    {
        checkAdminIsLogin();
        kitchenDaoImp = new KitchenDaoImp();
        kitchenList = kitchenDaoImp.getAllKitchen();
        kitchen = new Kitchen();
        printerNameList = new ArrayList<String>();
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        System.out.println(printServices.length);
        for (PrintService printer : printServices) {
                printerNameList.add(printer.getName());
        }
    }

    public String getAuthority(){
        return (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("authority");
    }

    public void checkAdminIsLogin(){
        String authority = getAuthority();
        System.out.println(authority);
        if(authority==null){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/admin/AdminLogin.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addKitchen()
    {
        boolean result = kitchenDaoImp.createKitchen(kitchen);
        if (result){
            //show message
            kitchenList.add(kitchen);

        }
        else
        {
            //show message
        }
        kitchen = new Kitchen();
    }

    public void removeKitchen(Kitchen kitchen)
    {
        boolean result = kitchenDaoImp.deleteKitchen(kitchen);
        if (result) {
            kitchenList.remove(kitchen);
            //show message
        }
        else {
            //show message
        }
    }

    public com.coffeeshop.model.Kitchen getKitchen() {
        return kitchen;
    }

    public void setKitchen(Kitchen kitchen) {
        this.kitchen = kitchen;
    }

    public List<Kitchen> getKitchenList() {
        return kitchenList;
    }

    public void setKitchenList(List<Kitchen> kitchenList) {
        this.kitchenList = kitchenList;
    }

    public List<String> getPrinterNameList() {
        return printerNameList;
    }

    public void setPrinterNameList(List<String> printerNameList) {
        this.printerNameList = printerNameList;
    }
}
