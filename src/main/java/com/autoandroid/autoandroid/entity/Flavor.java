package com.autoandroid.autoandroid.entity;

public class Flavor {

    private String name;
    private String type;
    private String key;
    private String value;

    public Flavor(String name, String type, String key, String value) {
        this.name = name;
        this.type = type;
        this.key = key;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
