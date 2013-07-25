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
    private StringBuffer finalstr;

    public void convertToCSV(String path, Object imageSrc) throws IOException {
        
        String str = new String(imageSrc.toString());
        String slash = str.substring((imageSrc.toString().length())-1 );
        System.out.println(slash);
        
        if (!slash.equals("/")) {
            finalstr = new StringBuffer(str);
            finalstr.append("/");
        }
        
        CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
        CSVWriter writer = new CSVWriter(new FileWriter("./shopifyConverter_result.csv"), ',');
        
        String[] nextLine;
        boolean isfirstRow = true;
        while ((nextLine = reader.readNext()) != null) {
            if(isfirstRow) {
                String[] headerLine = { "Handle", "Title", "Body (HTML)", "Vendor", "Type", "Tags", "Published", "Option1 Name", "Option1 Value", "Option2 Name", "Option2 Value", "Option3 Name", "Option3 Value", "Variant SKU", "Variant Grams", "Variant Inventory Tracker", "Variant Inventory Qty", "Variant Inventory Policy", "Variant Fulfillment Service", "Variant Price", "Variant Compare At Price", "Variant Requires Shipping", "Variant Taxable", "Variant Barcode", "Image Src" };
                writer.writeNext(headerLine);
                isfirstRow = false;
            } else {
                String[] entries = { nextLine[40], nextLine[2], nextLine[3] + "\r\n\r\nAdditional Notes: \r\n" + nextLine[36], nextLine[12], "Not Specified", nextLine[13], "", "", "Title", nextLine[21], "", "", "", "", nextLine[1], "", "", nextLine[28], "", "", nextLine[38], "", "", "", finalstr + nextLine[37] };
                writer.writeNext(entries);
            }
        }
        writer.close();
    }
    
}
