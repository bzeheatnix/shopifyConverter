import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import java.io.*;

/**
Copyright 2005 Bytecode Pty Ltd.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
public class CSVR {    
    
//    public void captureColumnNumber(String path) throws IOException {
//        System.out.println(handle + ", " + title + ", " + body + ", " + additionalInfo + ", " + type + ", " + option1 + ", " + sku + ", " + qty + ", " + price + ", " + image);
//    }

    public void convertToCSV(String path, Object imageSrc) throws IOException {
        // if the url doesn't have a "/" at the end, we append it.
        if (!imageSrc.equals("")) {
            String str = new String(imageSrc.toString());
            String slash = str.substring((imageSrc.toString().length())-1 );
        
            if (!slash.equals("/")) {
                finalstr = new StringBuffer(str);
                finalstr.append("/");
                src = finalstr.toString();
            }
        }
        
        // We set up the reader.
        CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
        
        // We read and set the column number to use from the csv.
        nextLine = reader.readNext();
        for ( int i = 0; i < nextLine.length ; i ++ ) {
            
            if (nextLine[i].equals("Handle Type")) {
                nextLine[i] = "Handle";
                handle = i;
            } else if (nextLine[i].equals("Model #")) {
                nextLine[i] = "Title";
                title = i;
            } else if (nextLine[i].equals("Description")) {
                nextLine[i] = "Body (HTML)";
                body = i;
            } else if (nextLine[i].equals("Additional Info")) {
                additionalInfo = i;
            } else if (nextLine[i].equals("ProdType")) {
                nextLine[i] = "Type";
                type = i;
            } else if (nextLine[i].equals("Options")) {
                nextLine[i] = "Option1 Value";
                option1 = i;
            } else if (nextLine[i].equals("Part #")) {
                nextLine[i] = "Variant SKU";
                sku = i;
            } else if (nextLine[i].equals("Qty per pallet")) {
                nextLine[i] = "Variant Inventory Qty";
                qty = i;
            } else if (nextLine[i].equals("List Price")) {
                nextLine[i] = "Variant Price";
                price = i;
            } else if (nextLine[i].equals("Photo")) {
                nextLine[i] = "Image Src";
                image = i;
            }        
        }
        
        CSVReader reader2 = new CSVReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
        // We set up the writer.
        CSVWriter writer = new CSVWriter(new FileWriter("./shopifyConverter_result.csv"), ',');
        boolean isfirstRow = true; // if it's the first row we set up the headers.
        
        while ((nextLine2 = reader2.readNext()) != null) {
            if(isfirstRow) {
                String[] headerLine = { "Handle", "Title", "Body (HTML)", "Vendor", "Type", "Tags", "Published", "Option1 Name", "Option1 Value", "Option2 Name", "Option2 Value", "Option3 Name", "Option3 Value", "Variant SKU", "Variant Grams", "Variant Inventory Tracker", "Variant Inventory Qty", "Variant Inventory Policy", "Variant Fulfillment Service", "Variant Price", "Variant Compare At Price", "Variant Requires Shipping", "Variant Taxable", "Variant Barcode", "Image Src" };
                writer.writeNext(headerLine);
                isfirstRow = false;
            } else { // We capture the columns with the numbers setted before.
                String[] entries = { nextLine2[handle], nextLine2[title], nextLine2[body] + "\r\n\r\nAdditional Notes: \r\n" + nextLine2[additionalInfo], "Not Specified", nextLine2[type], "", "", "Title", nextLine2[option1], "", "", "", "", nextLine2[sku], "", "", nextLine2[qty], "", "", nextLine2[price], "", "", "", "", src + nextLine2[image] };
                writer.writeNext(entries);
            }
        }
        writer.close();
    }
 
//    public static void main(String[] args) throws IOException {
//        CSVR csvr = new CSVR();
//        csvr.captureColumnNumber("/Users/bzeheatnix/Desktop/products.csv");
//    }
    
    private String[] nextLine;
    private String[] nextLine2;
    private StringBuffer finalstr;
    private String src = "";
    private int handle = 0;
    private int title = 0;
    private int body = 0;
    private int additionalInfo = 0;
    private int type = 0;
    private int option1 = 0;
    private int sku = 0;
    private int qty = 0;
    private int price = 0;
    private int image = 0;
}
