package cart2;
import java.util.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Cart {

    //Members
    private String customerName;
    public static List<String> users = new LinkedList<>();
    private List<String> cart = new LinkedList<>();
    private boolean loggedIn = false;

    //constructor
    public Cart(){}
    public Cart(String customerName){this.customerName = customerName;users.add(customerName);}

    // Getters and setters
    public String getCustomerName() {return customerName;}
    public void setCustomerName(String customerName) {this.customerName = customerName;users.add(0, customerName);}

    public List<String> getCart() {return cart;}
    public void setCart(List<String> cart) {this.cart = cart;}
        
    public boolean isLoggedIn() {return loggedIn;}
    public void setLoggedIn(boolean loggedIn) {this.loggedIn = loggedIn;}

    //methods
    //add items 
    public void add(String input_arr){
        String[] items = input_arr.split(",", 0);
        for (int i = 0; i<items.length;i++){
            if(cart.contains(items[i].toLowerCase().trim())){
                System.out.printf("you have %s in your cart\n",items[i].toLowerCase().trim());
                continue;
            } else{
                cart.add(items[i].toLowerCase().trim());
                System.out.printf("%s added to cart\n",items[i].toLowerCase().trim());
              }
        }
    }

    //delete items
    public void delete(String input_arr){
        String[] junk = input_arr.split(",", 0);

        for (int i = 0,j=0; i<junk.length;i++,j++){
            if(Integer.parseInt(junk[i].trim())-j  > cart.size()){
                System.out.println("Incorrect item index\n");
            } else{
                System.out.printf("%s removed from cart\n",cart.get(Integer.parseInt(junk[i])-1-j));
                cart.remove(Integer.parseInt(junk[i])-1-j);
            }
        }        
    }

    // new delete method - using name of items to delete instead of index
    public void newDelete(String input_arr){                                                  // takes items as string and returns nothing
        String temp = "";                                                                       // temporary temp variable to store output as string
        String[] junk = input_arr.trim().split(",", 0);                            // split items list by "," delimiter to store items in an string array junk[]
        List<String> junkName = new LinkedList<>();                                             // create new string array 
        boolean isEmpty = true;
        for (int i = 0;i< junk.length;i++ ){
            if ((Integer.parseInt(junk[i].trim()))>cart.size()){
                temp = temp+ "\nIncorrect item index\n";
                continue;
            } else{
                junkName.add(cart.get(Integer.parseInt(junk[i].trim())-1));                     // store item name in junkName list
                isEmpty = false;
            }            
        }
                
        if(!isEmpty){
            for (String item:junkName){                                                             // for loop to remove all items
                int removeThisItem = cart.indexOf(item);                                            // locate item index via name of item
                temp = temp + cart.get(removeThisItem)+ " removed from cart\n";                      
                cart.remove(removeThisItem);                                                        // using .remove method to delete the item and pass response to temp string
            }
        }

        System.out.println(temp);                                                                            // return compiled responses in the string temp
    }   

    
    // clears shopping cart
    public void clear(){
        this.cart.removeAll(cart);
        System.out.println("you cart has been cleared");
    }

    // list items
    public void list(){
        if (cart.isEmpty()){
            System.out.println("You have nothing in your cart\n");
        } else{        
            System.out.println("You cart contains the following items");        
            for (int i = 0; i < cart.size();i++){
            System.out.printf("%d. %s\n",i+1,cart.get(i));
            }
        }
    }

    // NEW METHODS FOR OPENING/CLOSING/CREATING FILES
    public void userLogin(String user, String folder) throws FileNotFoundException, IOException{        
        // create a path to the .txt file location
        user = user.toLowerCase().trim();
        Path fileLocation = Paths.get(folder+"\\"+user+".txt");
        File customerFile = fileLocation.toFile();
        
        if(customerFile.exists()){
            FileReader fr = new FileReader(customerFile);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();

            br.close();
            fr.close();
            
            String[] nameItemSplit = line.trim().split(" ", 2);
            
            // incase an empty cart was saved
            if(nameItemSplit.length<2){
                this.customerName = nameItemSplit[0];
                this.loggedIn = true;           
                list();
            } else{

                String[] itemSplit = nameItemSplit[1].split(",", 0);

                List<String> fileItemList = new LinkedList<>();
                for (String item: itemSplit){fileItemList.add(item.trim());}

                // initiallize variables with info from txt file
                this.customerName = nameItemSplit[0];
                this.cart=fileItemList;
                this.loggedIn = true;           
                list();
            }
            
        } else{
            this.customerName = user;
            this.loggedIn = true;
            System.out.println("New cart created, welcome "+this.customerName);
            list();            
        }
    }

    public void saveCart(String folder) throws IOException{

        if(this.loggedIn){
            FileWriter writer = new FileWriter(folder+"\\"+this.customerName+".txt");
        
            String itemString = "";

            for(int i=0; i <cart.size()-1;i++){
                itemString += cart.get(i) + ", ";
            }
            itemString += cart.get(cart.size()-1);
            
            writer.write(this.customerName+" ");
            writer.write(itemString);

            writer.flush();
            writer.close();

            System.out.println("Your cart has been saved");

        } else{
            System.out.println("Please login in first before saving");
            }           
    }

    public static void userList(String folder){
        File folderPath = new File(folder+"\\");
        File[] listFiles = folderPath.listFiles();
        
        for(int i =0; i<listFiles.length;i++){
            String fileName = listFiles[i].getName();
            System.out.printf("%d. %s\n",i+1,fileName.substring(0,fileName.length()-4));
        }
    }   

}
