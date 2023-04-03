package com.example.demo;

import com.example.demo.entity.HobbyEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.entity.UserHobbyEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@Component
public class DataImportRunner implements ApplicationRunner {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ObjectMapper objectMapper;

    private static final Logger logger = Logger.getLogger(DataImportRunner.class.getName());

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        File usersFile = new File("src/main/resources/data/users.json");
        File hobbiesFile = new File( "src/main/resources/data/hobbies.json");
        File usersHobbiesFile = new File("src/main/resources/data/userHobby.json");
        loadData(usersFile,new TypeReference<List<UserEntity>>(){});
        loadData(hobbiesFile,new TypeReference<List<HobbyEntity>>(){});
        loadData(usersHobbiesFile, new TypeReference<List<UserHobbyEntity>>(){});
    }

    private <T> void loadData(File file, TypeReference<List<T>> typeReference) {
        if (file.exists()) {
            try {
                List<T> data = objectMapper.readValue(file, typeReference);
                for (var d : data) {
                    entityManager.persist(d);
                }
            } catch (IOException e) {
                logger.info(e.getMessage());
            }
        } else {
            logger.info("Json file is missing from the given directory! Path: " + file.getPath());
        }
    }
}
