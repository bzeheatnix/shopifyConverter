
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class CSVReader {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        
            ArrayList<String> content = new ArrayList<String>(); // ArrayList to Store only String objects
            File file = new File("/users/bzeheatnix/shopify_template.csv"); // CSV file created.
        
            // if file doesnt exists, then create it
            if (!file.exists()) {
                    file.createNewFile();
            }
            
            Scanner scanner = new Scanner(new File("/Users/bzeheatnix/original_template.csv")); // CSV file to read.
            scanner.useDelimiter(",");
            while(scanner.hasNext()){
                content.add(scanner.next());
            }
            
            int index = 0;
            
            index = content.indexOf("Part #"); //location of Item object in List
            content.set(index, "Variant SKU");
            
            index = content.indexOf("Description"); //location of Item object in List
            content.set(index, "Body (HTML)");
            
            index = content.indexOf("ProdType"); //location of Item object in List
            content.set(index, "Type");
            
            index = content.indexOf("Handle Type"); //location of Item object in List
            content.set(index, "Handle");
            
            index = content.indexOf("Options"); //location of Item object in List
            content.set(index, "Option1 Value");
            
            index = content.indexOf("Qty per pallet"); //location of Item object in List
            content.set(index, "Variant Inventory Qty");
            
            index = content.indexOf("List Price"); //location of Item object in List
            content.set(index, "Variant Price");
            
            index = content.indexOf("Photo"); //location of Item object in List
            content.set(index, "Image Src");
            
            scanner.close();
            //System.out.println(content);
            
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            
            for ( int i = 0 ; i < content.size() ; i++ ) {
                //System.out.println(content.get(i) + ",");
                if( i == content.size() -1) {
                    bw.write(content.get(i));
                } else {
                    bw.write(content.get(i) + ",");
                }
            }    
            
            bw.close();
   }
 

}
