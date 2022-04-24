
# **Device Registry**

The Device-Registry Service keeps track of the different devices managed by the system and enables
new devices to be registered, deleted or modified

## Usage
All usages of the deviceEntity registry and exposed methods and endpoints
### List all devices
**Definition**

`GET /devices`

**Response**

- 200: success


```json
[
    {
        "id": "id1",
        "name": "Device 1",
        "type": "hs100",
        "kind": "switch",
        "controller_name": "controller-1",
        "room": {
            "id": "bedroom1",
            "name": "Master Bedroom"
        }
    },
    {
        "id": "id2",
        "name": "Device 2",
        "type": "huelight",
        "kind": "lamp",
        "controller_name": "controller-2",
        "room": {
            "id": "kitchen",
            "name": "Kitchen"
        }
    }
]
```

### Register a new deviceEntity
**Definition**

`POST /devices`

**Arguments**

- `"id":string` a globally unique ID for this deviceEntity
- `"name":string` a friendly name for the deviceEntity
- `"type":string` the type of the deviceEntity as understood by the client e.g. hs100
- `"kind":string` the kind of deviceEntity - mapped to type e.g. lamp
- `"room_id":string` the globally unique ID of the room
- `"controller_name":string` the name of the deviceEntity's controller
- `"attributes":object` arbitrary controller-specific information about the deviceEntity
- `"state_providers":array` names of external services that provide state

If the ID already exists, the existing deviceEntity will be overwritten. This is baked in with the Spring JPA
functionality

**Response**

- 400: unknown room
- 201: created successfully

Returns the new deviceEntity if successful.

```json
{
    "id": "id1",
    "name": "Device 1",
    "type": "hs100",
    "kind": "switch",
    "controller_name": "controller-2",
    "room": {
        "id": "bedroom1",
        "name": "Master Bedroom"
    }
}
```

### Lookup deviceEntity details
**Definition**

`GET /deviceEntity/<id>`

**Response**

- 404: deviceEntity not found
- 200: success

```json
{
    "id": "id1",
    "name": "Device 1",
    "type": "hs100",
    "kind": "switch",
    "controller_name": "controller-1",
    "room": {
        "id": "bedroom2",
        "name": "Spare Bedroom"
    }
}
```

### Delete a deviceEntity
**Definition**

`DELETE /deviceEntity/<id>`

**Response**

- 404: deviceEntity not found
- 204: success

### List rooms
**Definition**

`GET /rooms`

**Response**

- 200: success

```json
[
    {
        "id": "bedroom2",
        "name": "Spare Bedroom",
        "devices": [
            {
                "id": "lamp1",
                "name": "Lamp",
                "type": "huelight",
                "kind": "lamp",
                "controller_name": "controller-1"
            }
        ]
    },
    {
        "id": "living_room",
        "name": "Living Room",
        "devices": [
            {
                "id": "tv2",
                "name": "TV",
                "type": "samsung48",
                "kind": "tv",
                "controller_name": "controller-2"
            }
        ]
    }
]
```

### Register new room
**Definition**

`POST /rooms`

**Arguments**

- `"id":string` a globally unique id for the room
- `"name":string` a friendly name for the room

If the id already exists, the existing room will be overwritten.
Devices belonging to an existing room will not be modified.

**Response**

- 201: created successfully

Returns the new room is created successfully.

```json
{
    "id": "bedroom2",
    "name": "Spare Bedroom",
    "devices": []
}
```

### Lookup room details
**Definition**
`GET /room/<id>`

**Response**

- 404: room not found
- 200: success

```json
{
    "id": "bedroom2",
    "name": "Spare Bedroom",
    "devices": [
        {
            "id": "id1",
            "name": "Device 1",
            "type": "hs100",
            "kind": "switch",
            "controller_name": "controller-1"
        }
    ]
}
```

### Delete a room
**Definition**

`DELETE /rooms/<id>`

**Response**

- 404: room not found
- 204: success