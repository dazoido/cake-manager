package com.waracle.cakemgr;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

import java.util.List;

import com.waracle.cakemgr.data.model.CakeModel;
import com.waracle.cakemgr.data.repository.CakeRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CakeRepositoryTests {
    
    @Autowired
    private CakeRepository cakeRepository;

    @Test
    public void whenFindingAllCakes_thenCorrect() {

        // When
        cakeRepository.deleteAll();
        cakeRepository.save(new CakeModel("Cake 1", "Vanilla Cake", "vanilla_cake.jpg"));
        cakeRepository.save(new CakeModel("Cake 2", "Lemon Cake", "lemon_cake.jpg"));

        // Then
        assertThat(cakeRepository.findAll().size(), is(2));
    }
}
