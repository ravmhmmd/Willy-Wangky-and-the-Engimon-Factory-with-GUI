package EngimonWorld;
import java.util.*; 

public class Inventory<T> {
    // constants 
    static int max_Capacity = 20;

    private ArrayList<InventoryItem> itemPlayer;
    private HashMap<String, Integer> mapInventory;

    public Inventory() {
        itemPlayer = new ArrayList<InventoryItem>();
        mapInventory = new HashMap<String, Integer>();
    }

    public int countItem() {
        return itemPlayer.size();
    }

    public T getItem(int index) {
        if (index < 0 || index >= this.itemPlayer.size()){
              return null;
        }
        return (T)this.itemPlayer.get(index);
   }

    public void addItem(InventoryItem item) {
        
        if(itemPlayer.size() < max_Capacity){
            itemPlayer.add(item);
            if (mapInventory.containsKey(item.getNama())){
                mapInventory.put(item.getNama(), mapInventory.get(item.getNama()) + 1);
            } else{
                mapInventory.put(item.getNama(), 1);
            }
            Collections.sort(itemPlayer, Collections.reverseOrder());
        } else{
            System.out.println("Inventory Penuh");
        }
        
    }

    public void removeItem(InventoryItem item) {
        if (item != null && itemPlayer != null && itemPlayer.size() != 0){
            
            if (mapInventory.get(item.getNama()) > 0){
                mapInventory.put(item.getNama(), mapInventory.get(item.getNama()) - 1);
            } else {
                mapInventory.remove(item.getNama());
            }
            itemPlayer.remove(item);
        }
        
    }
    
    public int getQuantity(InventoryItem item) {
        return this.mapInventory.get(item.getNama()).intValue();
    }

    public void printInvenEngimon() {
        System.out.println("<< List Engimon di Inventory >>");
        for (int i = 0; i < itemPlayer.size(); i++){
            System.out.printf("[%d] %s",i+1, itemPlayer.get(i));
        }
    }
    public void printInvenSkill(){
        System.out.println("<< List Skill Item di Inventory >>");
        if (!itemPlayer.isEmpty()){
            for (int i = 0; i < itemPlayer.size(); i++){
                System.out.printf("[%d] %s (%d)\n",i+1, itemPlayer.get(i), this.getQuantity(itemPlayer.get(i)));
            }
        } else {
            System.out.println("Inventory is empty..");
        }
    }

    

}
