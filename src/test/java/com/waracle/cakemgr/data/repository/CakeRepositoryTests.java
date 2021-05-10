package com.waracle.cakemgr.data.repository;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

import com.waracle.cakemgr.data.model.CakeModel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CakeRepositoryTests {
    
    @Autowired
    private CakeRepository cakeRepository;

    @Test
    public void whenFindingAllCakes_thenCorrect() {

        // Given
        CakeModel cake1 = new CakeModel("Cake 1", "Vanilla Cake", "vanilla_cake.jpg");
        CakeModel cake2 = new CakeModel("Cake 2", "Lemon Cake", "lemon_cake.jpg");

        // When
        cakeRepository.deleteAll();
        cakeRepository.save(cake1);
        cakeRepository.save(cake2);

        // Then
        assertThat(cakeRepository.findAll().size(), is(2));
    }

    @Test
    public void whenDuplicateTitle_thenExistsIsTrue() {

        // Given
        CakeModel cake1 = new CakeModel("Cake 1", "Vanilla Cake", "vanilla_cake.jpg");

        // When
        cakeRepository.deleteAll();
        cakeRepository.save(cake1);

        // Then
        assertThat(cakeRepository.existsByTitle("Cake 1"), is(true));
    }
}
