// package com.ekarya.Models;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import java.io.File;
// import java.util.Arrays;
// import java.util.List;

// import static org.junit.jupiter.api.Assertions.*;

// public class PropertyTest {

//     private Property property;
//     private File mainImage;
//     private List<File> additionalImages;

//     @BeforeEach
//     public void setUp() {
//         mainImage = new File("main.jpg");
//         additionalImages = Arrays.asList(new File("img1.jpg"), new File("img2.jpg"));

//         property = new Property();
//         property.setId("P123");
//         property.setTitle("Cozy Apartment");
//         property.setLocation("New York");
//         property.setDescription("A lovely apartment in the city center.");
//         property.setGuests(2);
//         property.setBedrooms(1);
//         property.setBeds(1);
//         property.setBathrooms(1);
//         property.setPrice(120.5);
//         property.setLandlord_id(101);
//         property.setMainImage(mainImage);
//         property.setAdditionalImages(additionalImages);
//     }

//     @Test
//     public void testGetters() {
//         assertEquals("P123", property.getId());
//         assertEquals("Cozy Apartment", property.getTitle());
//         assertEquals("New York", property.getLocation());
//         assertEquals("A lovely apartment in the city center.", property.getDescription());
//         assertEquals(2, property.getGuests());
//         assertEquals(1, property.getBedrooms());
//         assertEquals(1, property.getBeds());
//         assertEquals(1, property.getBathrooms());
//         assertEquals(120.5, property.getPrice());
//         assertEquals(101, property.getLandlord_id());
//         assertEquals(mainImage, property.getMainImage());
//         assertEquals(additionalImages, property.getAdditionalImages());
//     }

//     @Test
//     public void testToStringContainsKeyFields() {
//         String output = property.toString();
//         assertTrue(output.contains("Cozy Apartment"));
//         assertTrue(output.contains("New York"));
//         assertTrue(output.contains("main.jpg"));
//         assertTrue(output.contains("2")); // guests count
//         assertTrue(output.contains("120.5")); // price
//     }

//     @Test
//     public void testConstructorInitialization() {
//         Property newProp = new Property(
//                 "ID1", "Modern Loft", "Los Angeles", "Trendy and modern space.",
//                 4, 2, 2, 2, 300.0, 202
//         );

//         assertEquals("Modern Loft", newProp.getTitle());
//         assertEquals("Los Angeles", newProp.getLocation());
//         assertEquals(4, newProp.getGuests());
//         assertEquals(300.0, newProp.getPrice());
//     }
// }


// package com.ekarya.Models;

// import org.junit.jupiter.api.Test;

// import java.io.File;
// import java.util.Arrays;
// import java.util.List;

// import static org.junit.jupiter.api.Assertions.*;

// public class PropertyTest {

//     @Test
//     void testGetAdditionalImages() {
//         Property property = new Property();
//         List<File> images = Arrays.asList(new File("img1.jpg"), new File("img2.jpg"));
//         property.setAdditionalImages(images);
//         assertEquals(images, property.getAdditionalImages());
//     }

//     @Test
//     void testGetBathrooms() {
//         Property property = new Property();
//         property.setBathrooms(2);
//         assertEquals(2, property.getBathrooms());
//     }

//     @Test
//     void testGetBedrooms() {
//         Property property = new Property();
//         property.setBedrooms(3);
//         assertEquals(3, property.getBedrooms());
//     }

//     @Test
//     void testGetBeds() {
//         Property property = new Property();
//         property.setBeds(4);
//         assertEquals(4, property.getBeds());
//     }

//     @Test
//     void testGetDescription() {
//         Property property = new Property();
//         property.setDescription("Nice view");
//         assertEquals("Nice view", property.getDescription());
//     }

//     @Test
//     void testGetGuests() {
//         Property property = new Property();
//         property.setGuests(5);
//         assertEquals(5, property.getGuests());
//     }

//     @Test
//     void testGetLocation() {
//         Property property = new Property();
//         property.setLocation("San Francisco");
//         assertEquals("San Francisco", property.getLocation());
//     }

//     @Test
//     void testGetMainImage() {
//         Property property = new Property();
//         File mainImage = new File("main.jpg");
//         property.setMainImage(mainImage);
//         assertEquals(mainImage, property.getMainImage());
//     }

//     @Test
//     void testGetPrice() {
//         Property property = new Property();
//         property.setPrice(199.99);
//         assertEquals(199.99, property.getPrice());
//     }

//     @Test
//     void testGetTitle() {
//         Property property = new Property();
//         property.setTitle("Modern Flat");
//         assertEquals("Modern Flat", property.getTitle());
//     }

//     @Test
//     void testSetAdditionalImages() {
//         Property property = new Property();
//         List<File> images = Arrays.asList(new File("img1.jpg"));
//         property.setAdditionalImages(images);
//         assertEquals(images, property.getAdditionalImages());
//     }

//     @Test
//     void testSetBathrooms() {
//         Property property = new Property();
//         property.setBathrooms(1);
//         assertEquals(1, property.getBathrooms());
//     }

//     @Test
//     void testSetBedrooms() {
//         Property property = new Property();
//         property.setBedrooms(2);
//         assertEquals(2, property.getBedrooms());
//     }

//     @Test
//     void testSetBeds() {
//         Property property = new Property();
//         property.setBeds(2);
//         assertEquals(2, property.getBeds());
//     }

//     @Test
//     void testSetDescription() {
//         Property property = new Property();
//         property.setDescription("Beachfront villa");
//         assertEquals("Beachfront villa", property.getDescription());
//     }

//     @Test
//     void testSetGuests() {
//         Property property = new Property();
//         property.setGuests(6);
//         assertEquals(6, property.getGuests());
//     }

//     @Test
//     void testSetLocation() {
//         Property property = new Property();
//         property.setLocation("Tokyo");
//         assertEquals("Tokyo", property.getLocation());
//     }

//     @Test
//     void testSetMainImage() {
//         Property property = new Property();
//         File image = new File("main.jpg");
//         property.setMainImage(image);
//         assertEquals(image, property.getMainImage());
//     }

//     @Test
//     void testSetPrice() {
//         Property property = new Property();
//         property.setPrice(350.00);
//         assertEquals(350.00, property.getPrice());
//     }

//     @Test
//     void testSetTitle() {
//         Property property = new Property();
//         property.setTitle("Stylish Loft");
//         assertEquals("Stylish Loft", property.getTitle());
//     }

//     @Test
//     void testToString() {
//         Property property = new Property();
//         property.setTitle("Charming Cabin");
//         property.setLocation("Colorado");
//         property.setDescription("Perfect for a snowy retreat.");
//         property.setGuests(4);
//         property.setBedrooms(2);
//         property.setBeds(2);
//         property.setBathrooms(1);
//         property.setPrice(175.50);
//         property.setMainImage(new File("cabin.jpg"));
//         property.setAdditionalImages(Arrays.asList(new File("a.jpg"), new File("b.jpg")));

//         String output = property.toString();
//         assertTrue(output.contains("Charming Cabin"));
//         assertTrue(output.contains("Colorado"));
//         assertTrue(output.contains("cabin.jpg"));
//         assertTrue(output.contains("2")); // for bedrooms/beds/additionalImages count
//         assertTrue(output.contains("175.5"));
//     }
// }
package com.ekarya.Models;

import org.junit.jupiter.api.Test;

public class PropertyTest {
    @Test
    void testGetAdditionalImages() {

    }

    @Test
    void testGetBathrooms() {

    }

    @Test
    void testGetBedrooms() {

    }

    @Test
    void testGetBeds() {

    }

    @Test
    void testGetDescription() {

    }

    @Test
    void testGetGuests() {

    }

    @Test
    void testGetLocation() {

    }

    @Test
    void testGetMainImage() {

    }

    @Test
    void testGetPrice() {

    }

    @Test
    void testGetTitle() {

    }

    @Test
    void testSetAdditionalImages() {

    }

    @Test
    void testSetBathrooms() {

    }

    @Test
    void testSetBedrooms() {

    }

    @Test
    void testSetBeds() {

    }

    @Test
    void testSetDescription() {

    }

    @Test
    void testSetGuests() {

    }

    @Test
    void testSetLocation() {

    }

    @Test
    void testSetMainImage() {

    }

    @Test
    void testSetPrice() {

    }

    @Test
    void testSetTitle() {

    }

    @Test
    void testToString() {

    }
}
