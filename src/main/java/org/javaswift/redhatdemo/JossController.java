package org.javaswift.redhatdemo;

import org.javaswift.joss.client.factory.AccountFactory;
import org.javaswift.joss.client.factory.AuthenticationMethod;
import org.javaswift.joss.model.Account;
import org.javaswift.joss.model.Container;
import org.javaswift.joss.model.StoredObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/")
public class JossController {

    private Account account;

    private JossController() {
        reauthenticate();
    }

    @RequestMapping(value = "/add-container", method = POST)
    public void addContainer(@RequestParam String containerName) {
        Container container = account.getContainer(containerName);
        container.create();
        container.makePublic();
    }

    @RequestMapping(value = "/delete-container", method = DELETE)
    public void deleteContainer(@RequestParam String containerName) {
        Container container = account.getContainer(containerName);
        container.delete();
    }

    @RequestMapping(value = "/delete-object", method = DELETE)
    public void deleteObject(@RequestParam String containerName, @RequestParam String objectName) {
        Container container = account.getContainer(containerName);
        StoredObject object = container.getObject(objectName);
        object.delete();
    }

    @RequestMapping(value = "/upload", method = POST)
    public void upload(@RequestParam MultipartFile file, @RequestParam String containerName) throws IOException {
        Container container = account.getContainer(containerName);
        StoredObject object = container.getObject(file.getOriginalFilename());
        object.uploadObject(file.getInputStream());
    }

    @RequestMapping(value = "/reauthenticate", method = POST)
    public void reauthenticate() {
        account = new AccountFactory()
                .setUsername("")
                .setPassword("")
                .setAuthUrl("")
                .setAuthenticationMethod(AuthenticationMethod.BASIC)
                .createAccount();
    }

    @RequestMapping(value = "/containers", method = GET)
    public Collection<ContainerModel> findAll() {

        Collection<ContainerModel> containers = new ArrayList<>();
        for (Container container : account.list()) {

            ContainerModel containerModel = new ContainerModel();
            containerModel.setName(container.getName());
            for (StoredObject object : container.list()) {

                ObjectModel objectModel = new ObjectModel();
                objectModel.setName(object.getName());
                objectModel.setLastModified(object.getLastModified());
                objectModel.setUrl(object.getPublicURL());
                containerModel.add(objectModel);
            }
            containers.add(containerModel);
        }
        return containers;
    }

}
