package com.coffeeshop.bean;

import com.coffeeshop.database.KitchenDaoImp;
import com.coffeeshop.database.KitchenOrderTempDao;
import com.coffeeshop.database.KitchenOrderTempDaoImp;
import com.coffeeshop.model.KitchenOrderTemp;
import com.coffeeshop.wrapper.KitchenOrderTempWrapper;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Amirhossein on 5/4/2017.
 */
@ManagedBean
@SessionScoped
public class KitchenBean implements Serializable{

    private KitchenOrderTempDao kitchenOrderTempDaoImp;
    private List<KitchenOrderTemp> kitchenOrderTemps;
    private List<KitchenOrderTempWrapper> kitchenOrderTempsWrapper;
    private HashMap<String, String> urlParam;
    private long kitchenId;

    @PostConstruct
    public void init() {
        kitchenOrderTempDaoImp = new KitchenOrderTempDaoImp();
        kitchenId = 1; //getUrlParam();
        kitchenOrderTemps = kitchenOrderTempDaoImp.getKitchenOrderTempByKitchenId(kitchenId);
        for (int i = 0; i < kitchenOrderTemps.size(); i++){
            KitchenOrderTempWrapper kitchenOrderTempWrapper = new KitchenOrderTempWrapper(kitchenOrderTemps.get(i));
            kitchenOrderTempsWrapper.add(kitchenOrderTempWrapper);
        }

    }

    public long getUrlParam(){
        urlParam = (HashMap<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        return  Long.parseLong(urlParam.get("kitchenId"));
    }

    public KitchenOrderTempDao getKitchenOrderTempDaoImp() {
        return kitchenOrderTempDaoImp;
    }

    public void setKitchenOrderTempDaoImp(KitchenOrderTempDao kitchenOrderTempDaoImp) {
        this.kitchenOrderTempDaoImp = kitchenOrderTempDaoImp;
    }

    public List<KitchenOrderTemp> getKitchenOrderTemps() {
        return kitchenOrderTemps;
    }

    public void setKitchenOrderTemps(List<KitchenOrderTemp> kitchenOrderTemps) {
        this.kitchenOrderTemps = kitchenOrderTemps;
    }

    public List<KitchenOrderTempWrapper> getKitchenOrderTempsWrapper() {
        return kitchenOrderTempsWrapper;
    }

    public void setKitchenOrderTempsWrapper(List<KitchenOrderTempWrapper> kitchenOrderTempsWrapper) {
        this.kitchenOrderTempsWrapper = kitchenOrderTempsWrapper;
    }

    public void setUrlParam(HashMap<String, String> urlParam) {
        this.urlParam = urlParam;
    }

    public long getKitchenId() {
        return kitchenId;
    }

    public void setKitchenId(long kitchenId) {
        this.kitchenId = kitchenId;
    }
}
