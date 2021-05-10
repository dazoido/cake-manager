package com.waracle.cakemgr.controller;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.*;

import com.waracle.cakemgr.data.model.CakeModel;
import com.waracle.cakemgr.data.repository.CakeRepository;
import com.waracle.cakemgr.exception.BadRequestException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CakeDataControllerTests {
    
    @InjectMocks
    public CakeDataController cakeDataController;

    @Mock
    public CakeRepository cakeRepository;

    @Test
    public void whenAddCake_thenSavesCake() throws Exception {

        // Given
        CakeModel cake = new CakeModel("Cake 1", "Vanilla Cake", "vanilla_cake.jpg");

        // When
        when(cakeRepository.existsByTitle(any(String.class))).thenReturn(false);
        when(cakeRepository.save(any())).thenReturn(cake);
        CakeModel savedCake = cakeDataController.add(cake);

        // Then
        assertThat("SavedCake object is not null", savedCake == null);
        assertThat("Cake details are saved correctly", cake.getTitle().equals(savedCake.getTitle()));
    }

    @Test()
    public void whenAddCakeWithDuplicateTitle_thenExceptionThrown() throws Exception {

        // Given
        CakeModel cake = new CakeModel("Cake 1", "Vanilla Cake", "vanilla_cake.jpg");

        // When
        when(cakeRepository.existsByTitle(any(String.class))).thenReturn(true);

        Exception exception = assertThrows(BadRequestException.class, ()-> {
            cakeDataController.add(cake);
        });
        
        // Then
        String expectedMessage = "Cake already exists with given title[" + cake.getTitle() + "]";
        String actualMessage = exception.getMessage();

        assertThat("Exception with expected message is thrown", actualMessage.equals(expectedMessage));
    }
}