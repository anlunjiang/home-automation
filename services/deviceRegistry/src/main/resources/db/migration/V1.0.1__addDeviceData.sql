insert into DEVICES(IDENTIFIER, NAME, TYPE, KIND, CONTROLLER_NAME, ROOM)
values
    (1, 'hue bulb 1', 'b1', 'bulb', 'raspberry_pi', '{"id": 1, "name": "kitchen"}' format json),
    (2, 'thermostat 1', 't1', 'thermo', 'server', '{"id": 2, "name": "living room"}' format json);

insert into ROOMS(IDENTIFIER, NAME ,DEVICES)
values (1, 'kitchen', ()),
       (2, 'living_room', ());
