package Inventory.Models;

public class ItemModel {
    private int itemId;
    private String itemName;
    private String itemStatus;

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public String getItemStatus() {
        return itemStatus;
    }
    public ItemModel(int itemId, String itemName, String itemStatus){
        this.itemId=itemId;
        this.itemStatus=itemStatus;
        this.itemName=itemName;
    }
    public ItemModel(){}
}
