package com.coffeeshop.wrapper;


import com.coffeeshop.database.FoodDaoImp;
import com.coffeeshop.database.FoodOrderDaoImp;
import com.coffeeshop.database.OrderDetailDaoImp;
import com.coffeeshop.model.Food;
import com.coffeeshop.model.FoodOrder;
import com.coffeeshop.model.KitchenOrderTemp;
import com.coffeeshop.model.OrderDetail;

public class KitchenOrderTempWrapper {
    private long kitchenOrderTempWrapperId;
    private long foodOrderId;
    private long foodId;
    private long kitchenId;
    private long trackNumber;
    private String foodName;
    private long foodQuantity;
    private String foodStatus;
    private long orderId;

    public KitchenOrderTempWrapper()
    {

    }

    public KitchenOrderTempWrapper(KitchenOrderTemp kitchenOrderTemp)
    {
        this.kitchenOrderTempWrapperId = kitchenOrderTemp.getKitchenOrderId();
        this.kitchenId = kitchenOrderTemp.getKitchenId();
        this.foodOrderId =  kitchenOrderTemp.getFoodOrderId();
        this.trackNumber = kitchenOrderTemp.getTrackNumber();
        fill();
    }

    private void fill()
    {
        FoodOrderDaoImp foodOrderDaoImp = new FoodOrderDaoImp();
        FoodDaoImp foodDaoImp = new FoodDaoImp();
        OrderDetailDaoImp orderDetailDaoImp = new OrderDetailDaoImp();
        FoodOrder foodOrder = foodOrderDaoImp.getFoodOrderById(this.foodOrderId);
        this.foodQuantity = foodOrder.getQuantity();
        Food food = foodDaoImp.getFoodByFoodId(foodOrder.getFoodId());
        this.foodName = food.getName();
        OrderDetail orderDetail = orderDetailDaoImp.getOrderDetailById(foodOrder.getOrderId()).get(0);
    }

    public long getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(long trackNumber) {
        this.trackNumber = trackNumber;
    }

    public long getKitchenOrderTempWrapperId() {
        return kitchenOrderTempWrapperId;
    }

    public void setKitchenOrderTempWrapperId(long kitchenOrderTempWrapperId) {
        this.kitchenOrderTempWrapperId = kitchenOrderTempWrapperId;
    }

    public long getFoodOrderId() {
        return foodOrderId;
    }

    public void setFoodOrderId(long foodOrderId) {
        this.foodOrderId = foodOrderId;
    }

    public long getKitchenId() {
        return kitchenId;
    }

    public void setKitchenId(long kitchenId) {
        this.kitchenId = kitchenId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public long getFoodId() {
        return foodId;
    }

    public void setFoodId(long foodId) {
        this.foodId = foodId;
    }

    public long getFoodQuantity() {
        return foodQuantity;
    }

    public void setFoodQuantity(long foodQuantity) {
        this.foodQuantity = foodQuantity;
    }

    public String getFoodStatus() {
        return foodStatus;
    }

    public void setFoodStatus(String foodStatus) {
        this.foodStatus = foodStatus;
    }
}
