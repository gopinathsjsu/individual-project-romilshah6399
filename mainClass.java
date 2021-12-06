import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

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
        HashSet<String> cardNumbers = new HashSet<>();
        order.renderCard(cardNumbers,"Cards - Sheet1.csv");
        HashMap<String,Float> invalid=new HashMap<>();
        order.getInventory(inventory,"Dataset - Sheet1.csv");
        order.getInput(inventory,invalid,"Input2 - Sheet1.csv",cardNumbers,"Cards - Sheet1.csv");
        if(invalid.size()!=0) order.printInvalidEntries(invalid);
        else order.generateOrderCheckOut();
        
    }
}

