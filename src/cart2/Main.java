package cart2;

import java.io.Console;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main{
    public static void main(String[] args) throws FileNotFoundException,IOException{

        System.out.println("Welcome to your shopping cart\n");     
        Console cons = System.console();
        Boolean exit = true;
        Cart customer = new Cart(null);

        while(exit){
            String input = cons.readLine("> ");
            String[] input_arr = input.trim().split(" ", 2);

            
            switch(input_arr[0].toLowerCase().trim()){
                case "login": customer.userLogin(input_arr[1], args[0]); break;
                // case "login": customer.setCustomerName(input_arr[1]);customer.setLoggedIn(true); break;
                case "add": customer.add(input_arr[1]); break;
                case "delete","remove":  customer.delete(input_arr[1]);break;
                case "list": customer.list();break;
                case "save": customer.saveCart(args[0]);break;
                case "users": System.out.println(Cart.users);break;
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