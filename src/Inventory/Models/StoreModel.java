package Inventory.Models;

public class StoreModel {
    private int storeId;
    private String storeName;
    private int quantity;

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getStoreId() {
        return storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
    public StoreModel(int storeId, String storeName, int quantity){
        this.storeId=storeId;
        this.storeName=storeName;
        this.quantity=quantity;
    }
    public StoreModel(){}
}
