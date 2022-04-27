package com.aj2814.homeAutomation.services.deviceRegistry.entity;


import com.aj2814.homeAutomation.services.deviceRegistry.view.Room;
import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "DEVICES")
@TypeDef(name = "json", typeClass = JsonType.class)
public class DeviceEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer identifier;
    private String name;
    private String type;
    private String kind;
    private String controllerName;
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Room room;

    public DeviceEntity() {
    }

    public DeviceEntity(Integer identifier, String name, String type, String kind, String controllerName, Room room) {
        this.identifier = identifier;
        this.name = name;
        this.type = type;
        this.kind = kind;
        this.controllerName = controllerName;
        this.room = room;
    }

    public Integer getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getKind() {
        return kind;
    }

    public String getControllerName() {
        return controllerName;
    }

    public Room getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return "id: " + identifier
                + ", name: " + name
                + ", type:" + type
                + ", kind: " + kind
                + ", controller: " + controllerName
                + ", room: " + room.toString();
    }

}
