package com.ibm.academia.apirest.repositories;

import com.ibm.academia.apirest.datos.DatosDummy;
import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.models.entities.Aula;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AulaRepositoryTest {

    @Autowired
    private AulaRepository repository;

    @BeforeEach
    void setUp() {
        repository.save(DatosDummy.aula01());
        repository.save(DatosDummy.aula02());
        repository.save(DatosDummy.aula03());
    }

    @AfterEach
    void dispose() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("It should select the first element")
    void createMethodShouldPersistAula() {
        Optional<Aula> result = repository.findById(1);

        if (result.isEmpty()) {
            throw new NotFoundException("No se encontró el primer elemento");
        }

        assertThat(result.get().getId() == 1).isTrue();
    }

}
