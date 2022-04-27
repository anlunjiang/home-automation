package com.aj2814.homeAutomation.services.deviceRegistry.entity;


import com.vladmihalcea.hibernate.type.array.IntArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;

@Entity
@Table(name = "ROOMS")
@TypeDef(name = "intArray", typeClass = IntArrayType.class)
public class RoomEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int identifier;
    private String name;
    @Type(type = "intArray")
    @Column(columnDefinition = "intArray")
    private int[] devices;

    public RoomEntity() {
    }

    public RoomEntity(int identifier, String name, int[] devices) {
        this.identifier = identifier;
        this.name = name;
        this.devices = devices;
    }

    public int getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public int[] getDevices() {
        return devices;
    }

    public void setDevices(int[] devices) {
        this.devices = devices;
    }

    @Override
    public String toString() {
        return "id: " + identifier + " name: " + name + " devices: " + Arrays.toString(devices);
    }
}
