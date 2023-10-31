package com.digitalmedia.users.repository;

import com.digitalmedia.users.dto.NewUserDto;
import com.digitalmedia.users.model.User;
import com.digitalmedia.users.model.UserType;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Repository;

import javax.ws.rs.core.Response;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class KeycloakRepository implements UserRepository {

    private final Keycloak keycloakClient;
    private String reino = "eventos";

    private User toUser(UserRepresentation userRepresentation) {
        return User.builder()
                .id(userRepresentation.getId())
                .username(userRepresentation.getUsername())
                .email(userRepresentation.getEmail())
                .firstName(userRepresentation.getFirstName())
                .type(UserType.valueOf(userRepresentation.getGroups().get(0)))
                .build();
    }

    public User findById(String id){
        UserRepresentation userRepresentation = keycloakClient.realm(reino).users().get(id).toRepresentation();
        User user = toUser(userRepresentation);

        return user;
    }


    public String addUser(NewUserDto user){
        RealmResource realmResource = keycloakClient.realm(reino);
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();

        // create new user with permissions
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(user.getUsername());
        userRepresentation.setFirstName(user.getName());
        userRepresentation.setLastName(user.getSurname());
        userRepresentation.setEmail(user.getEmail());
        userRepresentation.setGroups(List.of(user.getType().toString()));
        userRepresentation.setEnabled(true);

        // Establecer password y dejarla como no temporal
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(user.getPassword());
        credentialRepresentation.setTemporary(false);

        userRepresentation.setCredentials(List.of(credentialRepresentation));

        realmResource.users().create(userRepresentation);

        return "created";
    }

    @Override
    public void delete(String id) {
        keycloakClient.realm(reino).users().delete(id);
    }
}
