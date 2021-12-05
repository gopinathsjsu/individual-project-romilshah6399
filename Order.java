import java.util.*;
import java.io.*;

class Order implements Item
{
    ArrayList<Item> components = new ArrayList<>();
    String description;


    Order(String d)
    {
        description =d;
    }


    void generateOrderCheckOut(String cardNumber)
    {
        try
        {   
            FileWriter cart = new FileWriter("cart.csv");
            float total=0;
            cart.write(cardNumber+"\n");
            for(Item p:components)
            {
                cart.write(p.getItemName()+","+p.getItemPrice()+"\n");
                total+=p.getItemPrice();
            }
            cart.write(String.valueOf(total));
            cart.close();
        }         
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }



    public void print_description()
    {
        System.out.println(this.description);
    }



    public void addItem(Item i)
    {
        components.add(i);
    }
  

    void printInvalidEntries(HashMap<String,Float> invalid)
    {
        try
        {
            FileWriter file = new FileWriter("incorrectEntries.txt");
            file.write("Incorrect correct quantities\n");
            for(Map.Entry<String,Float> entry:invalid.entrySet() ) file.write(entry.getKey()+":"+entry.getValue()+"\n");
            file.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }


    String getInput(HashMap<String,HashMap<String,HashMap<String,Float>>> inventory,HashMap<String,Float> invalid,String address)
    {
        String line = "",cardNumber=""; 
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(address));    
            cardNumber = br.readLine();
            while ((line = br.readLine()) != null) 
            {  
                String[] inp = line.split(",");
                String item = inp[0];
                float quantity = Float.parseFloat(inp[1]);
                cardNumber = inp[2];
                float available_quantity=-1.0f,price=0f;
                for(String s:inventory.keySet() )
                {
                    if(inventory.get(s).containsKey(item))  
                    {
                        available_quantity=inventory.get(s).get(item).get("quantity");
                        price=inventory.get(s).get(item).get("price");
                        if(available_quantity==-1 || available_quantity<quantity  || quantity>getCap(item))
                         invalid.put(item,quantity);
                        else 
                        {
                            
                            Product p = new Product(item);
                            p.price=quantity*price;
                            // System.out.print(p.description+"--"+p.price);
                            this.addItem(p);
                        }
                        break;
                    } 
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return cardNumber;
    }

    float getCap(String item)
    {
        switch(item)
        {
            case "Luxury": return 3.0f;
            case "Essential": return 5.0f;
            case "Misc": return 6.0f;
        }
        return 10.0f;
    }

    void getInventory(HashMap<String,HashMap<String,HashMap<String,Float>>> inventory,String address)
    {
        String line = "";
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(address)); 
            br.readLine();
            // Rendering data from CSV file to hashmap
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] item = line.split(",");    // use comma as separator

                if (item[0].equals("Essential") || item[0].equals("Luxury") || item[0].equals("Misc"))
                {

                    HashMap<String, Float> quantityPrice = new HashMap<String, Float>() 
                    {{
                        put("quantity", Float.parseFloat(item[2]));
                        put("price", Float.parseFloat(item[3]));
                    }};
                    HashMap<String, HashMap<String, Float>> itemType = new HashMap<>();
                    itemType.put(item[1], quantityPrice);


                    if (inventory.containsKey(item[0])) 
                    {
                        HashMap<String, HashMap<String, Float>> temp;
                        temp = inventory.get(item[0]);
                        temp.put(item[1], quantityPrice);
                    }
                    else inventory.put(item[0], itemType);
                }
            
            }  
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }


    public String getItemName()
    {
        return "";
    }


    public float getItemPrice()
    {
        return 0;
    }
    
}


