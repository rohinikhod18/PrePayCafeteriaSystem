package com.kpit.cps.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserRoleTest {

    @Test
     public void  testGettersAndSetters(){
         UserRole userRole=new UserRole();
         userRole.setId(1L);
        userRole.setRoleName("Admin");
        userRole.setIsDesigned("Y");
        userRole.setCreatedBy(1);
        userRole.setCreatedOn("2024-01-01");
        userRole.setUpdatedBy(1);
        userRole.setUpdatedOn("2024-01-01");

        assertEquals(1L, userRole.getId());
        assertEquals("Admin", userRole.getRoleName());
        assertEquals("Y", userRole.getIsDesigned());
        assertEquals(1, userRole.getCreatedBy());
        assertEquals("2024-01-01", userRole.getCreatedOn());
        assertEquals("2024-01-01", userRole.getUpdatedOn());
        assertEquals(1, userRole.getUpdatedBy());

    }

}
