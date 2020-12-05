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

@ExtendWith(MockitoExtension.class)
class RestaurantTest {

    Restaurant restaurant;

    //REFACTOR ALL THE REPEATED LINES OF CODE
    private Restaurant getRestaurantObject() {

        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        return new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
    }
    private void addMenu() {
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        Restaurant restaurant = getRestaurantObject();

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
        Restaurant restaurant = getRestaurantObject();

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

        restaurant = getRestaurantObject();
        addMenu();


        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }


    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        restaurant = getRestaurantObject();
        addMenu();

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }



    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        restaurant = getRestaurantObject();
        addMenu();

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


}