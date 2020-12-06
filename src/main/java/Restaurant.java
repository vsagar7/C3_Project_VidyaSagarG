import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public int orderCost=0;

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        //return true;
        //DELETE ABOVE STATEMENT AND WRITE CODE HERE
        return  (getCurrentTime().isBefore(closingTime) || getCurrentTime().equals(closingTime)) &&
                (getCurrentTime().isAfter(openingTime) || getCurrentTime().equals(openingTime));
        //return (getCurrentTime().compareTo(closingTime) < 0 || getCurrentTime().compareTo(closingTime)==0) && (getCurrentTime().compareTo(openingTime) > 0 || getCurrentTime().compareTo(openingTime)==0);
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        //return null;
        //DELETE ABOVE RETURN STATEMENT AND WRITE CODE HERE
        return menu;
    }

     Item findItemByName(String itemName) throws itemNotFoundException{
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        throw new itemNotFoundException(itemName);
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

    //

    public int userOrderCost(String... userItems) throws itemNotFoundException {

        if(userItems.length==0)
            return 0;
        else {
            for (String itemName : userItems) {
                Item item = findItemByName(itemName);
                orderCost+=item.getPrice();
            }
            return orderCost;
        }
    }

}
