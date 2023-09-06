import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
    private Product product;

    @BeforeEach
    public void setUp() {
        // Create a sample Product object for testing
        product = new Product("SampleProduct", "Sample Description", "12345", 10.99);
    }

    @Test
    public void testGetName() {
        assertEquals("SampleProduct", product.getName());
    }

    @Test
    public void testGetDescription() {
        assertEquals("Sample Description", product.getDescription());
    }

    @Test
    public void testGetID() {
        assertEquals("12345", product.getID());
    }

    @Test
    public void testGetCost() {
        assertEquals(10.99, product.getCost(), 0.001); // 0.001 is the delta for double comparison
    }

    @Test
    public void testSetName() {
        product.setName("NewName");
        assertEquals("NewName", product.getName());
    }

    @Test
    public void testSetDescription() {
        product.setDescription("New Description");
        assertEquals("New Description", product.getDescription());
    }

    @Test
    public void testSetID() {
        product.setID("54321");
        assertEquals("54321", product.getID());
    }

    @Test
    public void testSetCost() {
        product.setCost(19.99);
        assertEquals(19.99, product.getCost(), 0.001);
    }

    @Test
    public void testToString() {
        String expected = "Product{name='SampleProduct', description='Sample Description', ID='12345', cost=10.99}";
        assertEquals(expected, product.toString());
    }
}
