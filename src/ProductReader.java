import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;

public class ProductReader {
    public static ArrayList<Product> readFromFile() {
        ArrayList<Product> products = new ArrayList<>();
        JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.dir")));
        fileChooser.setDialogTitle("Select Product Data File");

        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] fields = line.split(",");
                    if (fields.length == 4) {
                        String name = fields[0].trim();
                        String description = fields[1].trim();
                        String ID = fields[2].trim();
                        double cost = Double.parseDouble(fields[3].trim());

                        Product product = new Product(name, description, ID, cost);
                        products.add(product);
                    } else {
                        System.out.println("Invalid data format: " + line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No file selected.");
        }

        return products;
    }

    public static void main(String[] args) {
        ArrayList<Product> products = readFromFile();

        // Now you have the ArrayList of Product objects for further processing
        for (Product product : products) {
            System.out.println(product);
        }
    }
}
