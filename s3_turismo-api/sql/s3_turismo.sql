
delete from ViajeEntity;
delete from SitioTuristicoEntity;
delete from BlogDeViajeroEntity;
delete from FacturaEntity;
delete from TarjetaDeCreditoEntity;
delete from PreferenciaEntity;
delete from CiudadEntity;
delete from PaisEntity_PlanTuristicoEntity;
delete from PaisEntity;
delete from PlanTuristicoEntity_ViajeroEntity;
delete from ValoracionEntity;
delete from PlanTuristicoEntity;
delete from ViajeroEntity;


insert into ViajeroEntity(id, nombreUsuario, codigoUnico, idioma, tipoDeUsuario, cantidadPlanes, informacionPersonal) values (13111, 'Felipe Aguirre', 121, 'Ingles', 'Preferencial', 1, 'soy venezolano');
insert into ViajeroEntity(id, nombreUsuario, codigoUnico, idioma, tipoDeUsuario, cantidadPlanes, informacionPersonal) values (13222, 'Mariana Rodriguez', 122, 'Japonés', 'Normal', 7, 'hola');
insert into ViajeroEntity(id, nombreUsuario, codigoUnico, idioma, tipoDeUsuario, cantidadPlanes, informacionPersonal) values (13333, 'Carlos Sanchez', 123, 'Italiano', 'Habitual', 5, 'adios');
insert into ViajeroEntity(id, nombreUsuario, codigoUnico, idioma, tipoDeUsuario, cantidadPlanes, informacionPersonal) values (13444, 'Luisa Cifuentes', 124, 'Aleman', 'Normal', 8, 'hasta la vista, baby');
insert into ViajeroEntity(id, nombreUsuario, codigoUnico, idioma, tipoDeUsuario, cantidadPlanes, informacionPersonal) values (13555, 'Daenerys Targaryen', 125, 'Dothraki', 'Habitual', 8, 'Fire and Blood');
insert into ViajeroEntity(id, nombreUsuario, codigoUnico, idioma, tipoDeUsuario, cantidadPlanes, informacionPersonal) values (13666, 'Annabeth Chase', 126, 'Griego', 'Preferencial', 2, 'Seaweed Brain');


insert into PlanTuristicoEntity(id, nombrePlan, tipoPlan, costoPorPersona, descripcion, ubicacion, guia, duracion, idioma) values (111, 'Cataratas de diversión', 'Todo incluido', 120000.0, 'Viaje a las cataratas del Niagara', 'Estados Unidos', 0, '2 días', 'Inglés');
insert into PlanTuristicoEntity(id, nombrePlan, tipoPlan, costoPorPersona, descripcion, ubicacion, guia, duracion, idioma) values (112, 'Gravity Falls', 'Familiar', 140000.0, 'Viaje a la tierra de Mavel y Dipper', 'Neverland', 1, '5 días', 'Japonés');
insert into PlanTuristicoEntity(id, nombrePlan, tipoPlan, costoPorPersona, descripcion, ubicacion, guia, duracion, idioma) values (113, 'Volcán del Fin del Mundo', 'Todo incluido', 150000.0, 'Viaje al cinturón de fuego del pacífico', 'Nicaragua', 0, '3 días', 'Español');
insert into PlanTuristicoEntity(id, nombrePlan, tipoPlan, costoPorPersona, descripcion, ubicacion, guia, duracion, idioma) values (114, 'Hora de Aventura', 'Familiar', 120000.0, 'Viaje al increíble de Finn y Jake', 'Woo', 1, '4 días', 'Japonés');
insert into PlanTuristicoEntity(id, nombrePlan, tipoPlan, costoPorPersona, descripcion, ubicacion, guia, duracion, idioma) values (115, 'Redentor', 'Familiar', 150000.0, 'Viaje a las mejores capillas', 'Roma', 0, '10 días', 'Italiano');
insert into PlanTuristicoEntity(id, nombrePlan, tipoPlan, costoPorPersona, descripcion, ubicacion, guia, duracion, idioma) values (116, 'Into the Abyss', 'Todo incluido', 130000.0, 'Viaje a las minas más asombrosas', 'Abbys', 0, '5 días', 'Japonés');

insert into PlanTuristicoEntity_ViajeroEntity(planesturisticos_id,viajeros_id) values (111,13111);
insert into PlanTuristicoEntity_ViajeroEntity(planesturisticos_id,viajeros_id) values (111,13222);
insert into PlanTuristicoEntity_ViajeroEntity(planesturisticos_id,viajeros_id) values (111,13333);
insert into PlanTuristicoEntity_ViajeroEntity(planesturisticos_id,viajeros_id) values (111,13444);
insert into PlanTuristicoEntity_ViajeroEntity(planesturisticos_id,viajeros_id) values (112,13111);
insert into PlanTuristicoEntity_ViajeroEntity(planesturisticos_id,viajeros_id) values (112,13222);
insert into PlanTuristicoEntity_ViajeroEntity(planesturisticos_id,viajeros_id) values (113,13333);
insert into PlanTuristicoEntity_ViajeroEntity(planesturisticos_id,viajeros_id) values (113,13444);


insert into PaisEntity(id, nombre) values (171,'Estados Unidos');
insert into PaisEntity(id, nombre) values (172,'Neverland');
insert into PaisEntity(id, nombre) values (173,'Nicaragua');
insert into PaisEntity(id, nombre) values (174,'Woo');
insert into PaisEntity(id, nombre) values (175,'Roma');
insert into PaisEntity(id, nombre) values (176,'Abbys');

insert into PaisEntity_PlanTuristicoEntity(paises_id,planes_id) values (171,111);
insert into PaisEntity_PlanTuristicoEntity(paises_id,planes_id) values (172,111);
insert into PaisEntity_PlanTuristicoEntity(paises_id,planes_id) values (173,111);
insert into PaisEntity_PlanTuristicoEntity(paises_id,planes_id) values (174,111);
insert into PaisEntity_PlanTuristicoEntity(paises_id,planes_id) values (171,112);
insert into PaisEntity_PlanTuristicoEntity(paises_id,planes_id) values (172,112);





insert into CiudadEntity(id, nombre, pais_id) values (191, 'Bogota', 171);
insert into CiudadEntity(id, nombre, pais_id) values (192, 'Otawa', 172);
insert into CiudadEntity(id, nombre, pais_id) values (193, 'Madrid', 173);
insert into CiudadEntity(id, nombre, pais_id) values (194, 'San Juan', 174);
insert into CiudadEntity(id, nombre, pais_id) values (195, 'Amsterdam', 175);
insert into CiudadEntity(id, nombre, pais_id) values (196, 'Okinawa', 176);


insert into PreferenciaEntity(id, nombrePreferencia, viajero_id) values (141, 'Iglesia', 13111);
insert into PreferenciaEntity(id, nombrePreferencia, viajero_id) values (142, 'Helado', 13222);
insert into PreferenciaEntity(id, nombrePreferencia, viajero_id) values (143, 'Queso', 13333);
insert into PreferenciaEntity(id, nombrePreferencia, viajero_id) values (144, 'Cabezas decapitadas', 13444);
insert into PreferenciaEntity(id, nombrePreferencia, viajero_id) values (145, 'Museos', 13555);
insert into PreferenciaEntity(id, nombrePreferencia, viajero_id) values (146, 'Pizza', 13666);


insert into TarjetaDeCreditoEntity(id, numero, banco, codigoSeguridad, viajero_id) values (151, '123456781', 'Colpatria', 781, 13111);
insert into TarjetaDeCreditoEntity(id, numero, banco, codigoSeguridad, viajero_id) values (152, '123456782', 'Colpatria', 782, 13222);
insert into TarjetaDeCreditoEntity(id, numero, banco, codigoSeguridad, viajero_id) values (153, '123456783', 'Colpatria', 783, 13333);
insert into TarjetaDeCreditoEntity(id, numero, banco, codigoSeguridad, viajero_id) values (154, '123456784', 'Colpatria', 784, 13444);
insert into TarjetaDeCreditoEntity(id, numero, banco, codigoSeguridad, viajero_id) values (155, '123456785', 'Colpatria', 785, 13555);
insert into TarjetaDeCreditoEntity(id, numero, banco, codigoSeguridad, viajero_id) values (156, '123456786', 'Colpatria', 786, 13666);


insert into FacturaEntity(id, descripcion, costo, viajero_id) values (161, 'Cataratas de diversión por 2 días todo incluido', 240000.0, 13111);
insert into FacturaEntity(id, descripcion, costo, viajero_id) values (162, 'Gravity Falls por 5 días familiar', 700000.0, 13222);
insert into FacturaEntity(id, descripcion, costo, viajero_id) values (163, 'Volcán del Fin del Mundo por 3 días todo incluido', 450000.0, 13333);
insert into FacturaEntity(id, descripcion, costo, viajero_id) values (164, 'Hora de Aventura por 4 días familiar', 480000.0, 13444);
insert into FacturaEntity(id, descripcion, costo, viajero_id) values (165, 'Redentor por 10 días familiar', 1500000.0, 13555);
insert into FacturaEntity(id, descripcion, costo, viajero_id) values (166, 'Into the Abyss por 5 días todo incluido', 650000.0, 13666);


insert into BlogDeViajeroEntity  (id, comentarios,  sugerencias, likes, planturistico_id) values (100,' El viaje fue divertido  ', ' El viaje fue emocionante  ',7,  111);
insert into BlogDeViajeroEntity  (id, comentarios,  sugerencias, likes, planturistico_id) values (200,'El viaje fue increible ' , 'El viaje fue divertido ', 8, 112);
insert into BlogDeViajeroEntity  (id, comentarios,  sugerencias, likes, planturistico_id) values (300,'El viaje fue increible ',' El viaje fue genial  ', 4 , 113);
insert into BlogDeViajeroEntity  (id, comentarios,  sugerencias, likes, planturistico_id) values (400,' El viaje fue divertido ', 'El viaje fue emocionante',5, 114);
insert into BlogDeViajeroEntity  (id, comentarios,  sugerencias, likes, planturistico_id) values (500,'El viaje fue increible ',' El viaje fue genial  ', 4 , 115);
insert into BlogDeViajeroEntity  (id, comentarios,  sugerencias, likes, planturistico_id) values (600,' El viaje fue divertido ', 'El viaje fue emocionante',5, 116);


insert into ValoracionEntity  (id, idUsuario,  valoracion, comentario, planturistico_id) values (100, 200, 4, 'El viaje fue divertido',111);
insert into ValoracionEntity  (id, idUsuario,  valoracion, comentario, planturistico_id) values (200, 300, 3, 'El viaje fue emocionante', 112);
insert into ValoracionEntity  (id, idUsuario,  valoracion, comentario, planturistico_id) values (300, 400, 2, 'El viaje fue increible', 113);
insert into ValoracionEntity  (id, idUsuario,  valoracion, comentario, planturistico_id) values (400, 500, 5, 'El viaje fue genial', 114);
insert into ValoracionEntity  (id, idUsuario,  valoracion, comentario, planturistico_id) values (500, 400, 2, 'El viaje fue increible', 115);
insert into ValoracionEntity  (id, idUsuario,  valoracion, comentario, planturistico_id) values (600, 500, 5, 'El viaje fue genial', 116);


insert into SitioTuristicoEntity(id, tipo, nombre, ciudad_id) values (211,'Iglesia','Monserrate', 191);
insert into SitioTuristicoEntity(id, tipo, nombre, ciudad_id) values (212,'Parque','Parque Otawa', 191);
insert into SitioTuristicoEntity(id, tipo, nombre, ciudad_id) values (213,'Palacio','Palacio de oriente', 192);
insert into SitioTuristicoEntity(id, tipo, nombre, ciudad_id) values (214,'Castillo','Castillo san jose', 193);
insert into SitioTuristicoEntity(id, tipo, nombre, ciudad_id) values (215,'Calle','Calle rosa', 192);
insert into SitioTuristicoEntity(id, tipo, nombre, ciudad_id) values (216,'Monumento','Monumento a los caidos', 191);


insert into ViajeEntity(fechaInicio, fechaFin, viajero_id, planturistico_id) values ('2018-10-04 08:00:00', '2018-11-05 08:00:00', 13111, 111);
insert into ViajeEntity(fechaInicio, fechaFin, viajero_id, planturistico_id) values ('2018-10-04 08:00:00', '2018-12-06 08:00:00' ,13222, 112);
insert into ViajeEntity(fechaInicio, fechaFin, viajero_id, planturistico_id) values ('2018-10-04 08:00:00', '2018-11-07 08:00:00', 13333, 113);
insert into ViajeEntity(fechaInicio, fechaFin, viajero_id, planturistico_id) values ('2018-10-04 08:00:00', '2018-12-08 08:00:00', 13444, 114);
insert into ViajeEntity(fechaInicio, fechaFin, viajero_id, planturistico_id) values ('2018-10-04 08:00:00', '2018-11-09 08:00:00', 13555, 115);
insert into ViajeEntity(fechaInicio, fechaFin, viajero_id, planturistico_id) values ('2018-10-04 08:00:00', '2018-12-04 08:00:00', 13666, 116);
