package com.example.Gestion_Acceso.services.impl;
import com.example.Gestion_Acceso.models.UserAllowed;
import com.example.Gestion_Acceso.services.UserAllowedService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
@Service
public class UserAllowedServiceImpl implements UserAllowedService {

    @Value("${user.allowed.file.path}")
    private String jsonFilePath;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ResourceLoader resourceLoader;


    private List<UserAllowed> readUsersFromFile() throws IOException {

        Resource resource = resourceLoader.getResource(jsonFilePath);
        if (resource.exists()) {
            try (InputStream inputStream = resource.getInputStream()) {
                String content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

                JsonNode rootNode = objectMapper.readTree(content);

                JsonNode usersNode = rootNode.get("users_allowed");

                if (usersNode != null && usersNode.isArray()) {
                    CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, UserAllowed.class);
                    List<UserAllowed> users = objectMapper.convertValue(usersNode, listType);

                    return users;
                }
            } catch (Exception e) {

                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }
    public List<UserAllowed> getAllUsers() throws IOException {
        return readUsersFromFile();
    }
}