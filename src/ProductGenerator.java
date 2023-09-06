import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductGenerator {
    public static void main(String[] args) {
        ArrayList<Product> products = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + File.separator + "ProductTestData.txt");
        System.out.println("Working Directory: " + workingDirectory.getPath());

        boolean done = false;

        String productRec = "";
        String ID = "";
        String productName = "";
        String productDes = "";
        double cost = 0;

        do {
            ID = SafeInput.getNonZeroLenString(in, "Enter the ID [6 digits]");
            productName = SafeInput.getNonZeroLenString(in, "Enter product name");
            productDes = SafeInput.getNonZeroLenString(in, "Enter description");
            cost = SafeInput.getRangedDouble(in, "Enter product cost", 100.0, 9999.0);

            Product product = new Product(productName, productDes, ID, cost);
            products.add(product);

            done = SafeInput.getYNConfirm(in, "Are you done?");
        }while(!done);

        try
        {
            // Typical java pattern of inherited classes
            // we wrap a BufferedWriter around a lower level BufferedOutputStream
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            // Finally can write the file LOL!

            for (Product product : products) {
                String csvDataRecord = product.toCSVDataRecord();
                writer.write(csvDataRecord, 0, csvDataRecord.length());
                writer.newLine();
            }

            writer.close(); // must close the file to seal it and flush buffer
            System.out.println("Data file written!");
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred while writing to the file.");
        }
    }
}