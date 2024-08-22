package com.kpit.cps.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MeenuCategoryTest {

    @Test
    public void testGettersAndSetters() {
        MenuCategory menuCategory = new MenuCategory();
        menuCategory.setId(1L);
        menuCategory.setName("Beverages");
        menuCategory.setDescription("Drinks and refreshments");

        assertEquals(1, menuCategory.getId());
        assertEquals("Beverages", menuCategory.getName());
        assertEquals("Drinks and refreshments", menuCategory.getDescription());
    }


}
