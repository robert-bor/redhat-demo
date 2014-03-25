package org.javaswift.redhatdemo;

import java.util.ArrayList;
import java.util.Collection;

public class ContainerModel {

    String name;

    Collection<ObjectModel> objects = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void add(ObjectModel objectModel) {
        objects.add(objectModel);
    }

    public Collection<ObjectModel> getObjects() {
        return this.objects;
    }

}
