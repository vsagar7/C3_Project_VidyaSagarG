import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class RestaurantTest {

    Restaurant restaurant;

    //REFACTOR ALL THE REPEATED LINES OF CODE
    @BeforeEach
    private void getRestaurantObject() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant= new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        restaurant=org.mockito.Mockito.spy(restaurant);

        LocalTime currentTimes=LocalTime.parse("11:30:00");
        Mockito.when(restaurant.getCurrentTime()).thenReturn(currentTimes);

        Boolean op=restaurant.isRestaurantOpen();

        assertEquals(true,op);
        assertTrue(op);
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        restaurant=org.mockito.Mockito.spy(restaurant);

        LocalTime currentTime=LocalTime.parse("23:00:00");
        Mockito.when(restaurant.getCurrentTime()).thenReturn(currentTime);

        Boolean op=restaurant.isRestaurantOpen();

        assertEquals(false,op);
        assertFalse(op);
    }


    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }


    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }



    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>USER: Selecting items in Menu and getting total cost <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Test
    public void when_user_order_cost_method_is_called_with_items_Sweet_corn_Soup_and_Vegetable_lasagne_from_menu_should_give_order_cost_as_388() throws itemNotFoundException {

        String[] userItems= {"Sweet corn soup","Vegetable lasagne"};

        int orderCost=restaurant.userOrderCost(userItems);

        assertEquals(388,orderCost);

    }

    @Test
    public void when_user_order_cost_method_is_called_with_no_items_method_should_return_0() throws itemNotFoundException {

        String[] userItems= {};

        int orderCost=restaurant.userOrderCost(userItems);

        assertEquals(0,orderCost);

    }

    //<<<<<<<<<<<<<<<<<<<<USER: Selecting items in Menu and getting total cost>>>>>>>>>>>>>>>>>>>>>>>>>>

}