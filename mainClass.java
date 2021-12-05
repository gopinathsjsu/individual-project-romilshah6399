import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.plaf.basic.BasicComboBoxUI.ItemHandler;
import java.io.BufferedReader;  
import java.io.FileReader;
import java.io.FileWriter;  
import java.io.IOException;  
import java.io.File;  



class mainClass
{
    public static void main(String args[])
    {
        Order order = new Order("Order:");
        HashMap<String,HashMap<String,HashMap<String,Float>>> inventory = new HashMap<>();
        HashMap<String,Float> invalid=new HashMap<>();
        order.getInventory(inventory,"Dataset - Sheet1.csv");
        String cardNumber = order.getInput(inventory,invalid,"Input3 - Sheet1.csv");
        if(invalid.size()!=0) order.printInvalidEntries(invalid);
        else order.generateOrderCheckOut(cardNumber);
        
    }
}

