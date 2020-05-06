import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class RestaurantSerializator {
	
    String filename = "file.ser"; 
      
    public void serialize(List <MenuItem> menu) { 
    	try
    	{    
    		FileOutputStream file = new FileOutputStream(filename); 
    		ObjectOutputStream out = new ObjectOutputStream(file); 
    		
    		out.writeObject(menu); 
    		
    		out.close(); 
    		file.close(); 

    	}catch(IOException ex) 
    	{ 
        ex.printStackTrace(); 
    	} 
    }
    
    @SuppressWarnings("unchecked")
	public List <MenuItem> deserialize(){
    	List <MenuItem> menu = null; 
    	  
        // Deserialization 
        try
        {    
            // Reading the object from a file 
            FileInputStream file = new FileInputStream(filename); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            // Method for deserialization of object 
            menu = (List <MenuItem>)in.readObject(); 
              
            in.close(); 
            file.close(); 
              
        } 
          
        catch(IOException ex) 
        { 
            ex.printStackTrace();
        } 
          
        catch(ClassNotFoundException ex) 
        { 
            ex.printStackTrace();
        } 
        
        return menu;
    }
    
}
