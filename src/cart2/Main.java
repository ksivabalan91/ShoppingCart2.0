package cart2;

import java.io.Console;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main{
    public static void main(String[] args) throws FileNotFoundException,IOException{

        System.out.println("\nWelcome to your shopping cart\n"+
        "You may input the following commands:\n"+
        "1. login\t - Retrieve your saved cart or create new cart\n"+
        "2. add\t\t - add items to your cart, add multiple items to your cart with ','\n"+
        "3. delete/remove - remove items from your cart, remove multiple item from your cart with ','\n"+
        "4. list\t\t - list all items in your cart\n"+
        "5. clear\t - remvoes all items from your cart\n"+
        "6. save\t\t - save your shopping cart for future use\n"+
        "7. exit\t\t - exits the shopping cart app\n"+
        "8. users\t - lists all users with saved shopping carts\n");     
        Console cons = System.console();
        Boolean exit = true;
        Cart customer = new Cart(null);
        String dataDirectory = args[0];

        while(exit){
            String input = cons.readLine("> ");
            String[] input_arr = input.toLowerCase().trim().split(" ", 2);

            
            switch(input_arr[0].trim()){
                //shopping cart methods
                case "add": customer.add(input_arr[1]); break;
                case "delete","remove":  customer.delete(input_arr[1]);break;
                case "clear": customer.clear();break;
                case "list": customer.list();break;
                // loadind and saving databases
                case "login": customer.userLogin(input_arr[1], dataDirectory); break;
                case "save": customer.saveCart(dataDirectory);break;
                case "users": Cart.userList(dataDirectory);break;
                
                case "exit":
                    exit = false;
                    System.out.println("Thank you for shopping\n");
                    break;
                default:
                    System.out.println("Input valid commands: 'add','delete','list','exit'\n");
            }
        }
    }
}