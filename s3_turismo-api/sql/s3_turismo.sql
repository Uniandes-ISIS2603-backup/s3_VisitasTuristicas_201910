
delete from ViajeEntity;
delete from SitioTuristicoEntity;
delete from BlogDeViajeroEntity;
delete from FacturaEntity;
delete from TarjetaDeCreditoEntity;
delete from PreferenciaEntity;
delete from CIUDADENTITY_PLANTURISTICOENTITY;
delete from CiudadEntity;
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


insert into PlanTuristicoEntity(id, nombrePlan, tipoPlan, costoPorPersona, descripcion, ubicacion, guia, duracion, idioma,imagen) values (111, 'Cataratas de diversión', 'Todo incluido', 120000.0, 'Viaje a las cataratas del Niagara', 'Estados Unidos', 0, '2 días', 'Inglés','http://www.avalturistica.com/php/images/servicios/planes-turisticos.jpg');
insert into PlanTuristicoEntity(id, nombrePlan, tipoPlan, costoPorPersona, descripcion, ubicacion, guia, duracion, idioma,imagen) values (112, 'Gravity Falls', 'Familiar', 140000.0, 'Viaje a la tierra de Mavel y Dipper', 'Neverland', 1, '5 días', 'Japonés','https://images.geads.com.co/2016/05/17/todo-incluido-planes-turisticos-pagan-1-viajan-2-v_dlq647m_3.jpg');
insert into PlanTuristicoEntity(id, nombrePlan, tipoPlan, costoPorPersona, descripcion, ubicacion, guia, duracion, idioma,imagen) values (113, 'Volcán del Fin del Mundo', 'Todo incluido', 150000.0, 'Viaje al cinturón de fuego del pacífico', 'Nicaragua', 0, '3 días', 'Español','http://planesacancun.com/wp-content/uploads/2016/12/planes-turisticos-a-cancun-desde-colombia-Promociones-Descuentos-22-1.jpg');
insert into PlanTuristicoEntity(id, nombrePlan, tipoPlan, costoPorPersona, descripcion, ubicacion, guia, duracion, idioma,imagen) values (114, 'Hora de Aventura', 'Familiar', 120000.0, 'Viaje al increíble de Finn y Jake', 'Woo', 1, '4 días', 'Japonés','http://www.planesypaquetesturisticos.com.co/images/amazonas/ticuna/decalodge_ticuna2.jpg');
insert into PlanTuristicoEntity(id, nombrePlan, tipoPlan, costoPorPersona, descripcion, ubicacion, guia, duracion, idioma,imagen) values (115, 'Redentor', 'Familiar', 150000.0, 'Viaje a las mejores capillas', 'Roma', 0, '10 días', 'Italiano','https://diariodeunturista.com/wp-content/uploads/2009/11/fontana-di-trevi.jpg');
insert into PlanTuristicoEntity(id, nombrePlan, tipoPlan, costoPorPersona, descripcion, ubicacion, guia, duracion, idioma,imagen) values (116, 'Into the Abyss', 'Todo incluido', 130000.0, 'Viaje a las minas más asombrosas', 'Abbys', 0, '5 días', 'Japonés','https://diariodeunturista.com/wp-content/uploads/2009/11/fontana-di-trevi.jpg');

insert into PlanTuristicoEntity_ViajeroEntity(planesturisticos_id,viajeros_id) values (111,13111);
insert into PlanTuristicoEntity_ViajeroEntity(planesturisticos_id,viajeros_id) values (111,13222);
insert into PlanTuristicoEntity_ViajeroEntity(planesturisticos_id,viajeros_id) values (111,13333);
insert into PlanTuristicoEntity_ViajeroEntity(planesturisticos_id,viajeros_id) values (111,13444);
insert into PlanTuristicoEntity_ViajeroEntity(planesturisticos_id,viajeros_id) values (112,13111);
insert into PlanTuristicoEntity_ViajeroEntity(planesturisticos_id,viajeros_id) values (112,13222);
insert into PlanTuristicoEntity_ViajeroEntity(planesturisticos_id,viajeros_id) values (113,13333);
insert into PlanTuristicoEntity_ViajeroEntity(planesturisticos_id,viajeros_id) values (113,13444);


insert into CiudadEntity(id, nombre,imagen,descripcion) values (191, 'Bogota','https://upload.wikimedia.org/wikipedia/commons/4/4c/Centro_internacional.JPG','Is the capital and largest city of Colombia, administered as the Capital District, although often erroneously thought of as part of Cundinamarca.');
insert into CiudadEntity(id, nombre,imagen,descripcion) values (192, 'Otawa','https://free4kwallpapers.com/no-watermarks/originals/2015/10/03/ottawa-wallpaper.jpg',' It stands on the south bank of the Ottawa River in the eastern portion of southern Ontario. Ottawa borders Gatineau, Quebec;');
insert into CiudadEntity(id, nombre,imagen,descripcion) values (193, 'Madrid','https://images2.alphacoders.com/853/thumb-1920-853900.jpg','Is the capital of Spain and the largest municipality in both the Community of Madrid and Spain as a whole. The city has almost 3.3 million');
insert into CiudadEntity(id, nombre,imagen,descripcion) values (194, 'San Juan','http://wallpapersdsc.net/wp-content/uploads/2016/09/San-Juan-HD.jpg','located in the western part of the country. Neighbouring provinces are, moving clockwise from the north, La Rioja, San Luis and Mendoza. It borders with Chile at the west.');
insert into CiudadEntity(id, nombre,imagen,descripcion) values (195, 'Amsterdam','https://images4.alphacoders.com/708/thumb-1920-708178.jpg',' is the capital city and most populous municipality of the Netherlands. Its status as the capital is mandated by the Constitution of the Netherlands');
insert into CiudadEntity(id, nombre,imagen,descripcion) values (196, 'Okinawa','https://wallpapertag.com/wallpaper/full/6/5/a/858602-okinawa-wallpaper-1920x1200-for-macbook.jpg',' It encompasses two thirds of the Ryukyu Islands in a chain over 1,000 kilometres (620 mi) long.');


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


insert into SitioTuristicoEntity(id, tipo,imagen, nombre, ciudad_id) values (211,'Iglesia','https://directoriofacil.com/wp-content/uploads/2018/05/monserrate-67.jpg','Monserrate', 191);
insert into SitioTuristicoEntity(id, tipo,imagen, nombre, ciudad_id) values (212,'Parque','http://s1.1zoom.me/b5649/886/Canada_Parks_Ottawa_Ontario_Design_541059_2560x1440.jpg','Parque Otawa', 191);
insert into SitioTuristicoEntity(id, tipo,imagen, nombre, ciudad_id) values (213,'Palacio','https://media2.trover.com/T/55d4bab634fbe4295601312e/fixedw.jpg','Palacio de oriente', 192);
insert into SitioTuristicoEntity(id, tipo,imagen, nombre, ciudad_id) values (214,'Castillo','https://es.wikipedia.org/wiki/Castillo_de_San_Jos%C3%A9#/media/File:005-Castillo_de_San_Jos%C3%A9-Arrecife-Lanzarote_(RI-51-0008260).jpg','Castillo san jose', 193);
insert into SitioTuristicoEntity(id, tipo,imagen, nombre, ciudad_id) values (215,'Calle','http://www.plataformaurbana.cl/archive/2013/12/08/intervencion-urbana-calle-rosa-en-lisboa/calle-rosa-1/','Calle rosa', 192);
insert into SitioTuristicoEntity(id, tipo,imagen, nombre, ciudad_id) values (216,'Monumento','https://es.wikipedia.org/wiki/Plaza_y_Monumento_de_los_Ca%C3%ADdos#/media/File:Momunento_a_los_Ca%C3%ADdos.JPG','Monumento a los caidos', 191);



insert into CIUDADENTITY_PLANTURISTICOENTITY(ciudades_id,planes_id) values (191,111);
insert into CIUDADENTITY_PLANTURISTICOENTITY(ciudades_id,planes_id) values (191,112);
insert into CIUDADENTITY_PLANTURISTICOENTITY(ciudades_id,planes_id) values (191,113);
insert into CIUDADENTITY_PLANTURISTICOENTITY(ciudades_id,planes_id) values (191,114);
insert into CIUDADENTITY_PLANTURISTICOENTITY(ciudades_id,planes_id) values (191,115);
insert into CIUDADENTITY_PLANTURISTICOENTITY(ciudades_id,planes_id) values (192,111);
insert into CIUDADENTITY_PLANTURISTICOENTITY(ciudades_id,planes_id) values (192,112);
insert into CIUDADENTITY_PLANTURISTICOENTITY(ciudades_id,planes_id) values (192,113);
insert into CIUDADENTITY_PLANTURISTICOENTITY(ciudades_id,planes_id) values (192,114);
insert into CIUDADENTITY_PLANTURISTICOENTITY(ciudades_id,planes_id) values (192,115);
insert into CIUDADENTITY_PLANTURISTICOENTITY(ciudades_id,planes_id) values (192,116);








/*
insert into ViajeEntity(fechaInicio, fechaFin, viajero_id, planturistico_id) values ('2018-10-04 08:00:00', '2018-11-05 08:00:00', 13111, 111);
insert into ViajeEntity(fechaInicio, fechaFin, viajero_id, planturistico_id) values ('2018-10-04 08:00:00', '2018-12-06 08:00:00' ,13222, 112);
insert into ViajeEntity(fechaInicio, fechaFin, viajero_id, planturistico_id) values ('2018-10-04 08:00:00', '2018-11-07 08:00:00', 13333, 113);
insert into ViajeEntity(fechaInicio, fechaFin, viajero_id, planturistico_id) values ('2018-10-04 08:00:00', '2018-12-08 08:00:00', 13444, 114);
insert into ViajeEntity(fechaInicio, fechaFin, viajero_id, planturistico_id) values ('2018-10-04 08:00:00', '2018-11-09 08:00:00', 13555, 115);
insert into ViajeEntity(fechaInicio, fechaFin, viajero_id, planturistico_id) values ('2018-10-04 08:00:00', '2018-12-04 08:00:00', 13666, 116);
*/