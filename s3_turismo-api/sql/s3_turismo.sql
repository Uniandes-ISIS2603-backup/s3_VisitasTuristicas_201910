delete from ViajeroEntity;
delete from PreferenciaEntity;
delete from PaisEntity;
delete from CiudadEntity;
delete from SitioTuristicoEntity;

insert into ViajeroEntity(idUsuario, nombreUsuario, idioma, cantidadPlanes, informacionPersonal) values (7392, 'Felipe Aguirre', 'Ingles', 1, 'soy venezolano');
insert into ViajeroEntity(idUsuario, nombreUsuario, idioma, cantidadPlanes, informacionPersonal) values (71312, 'Mariana Rodriguez', 'Japones', 7, 'hola');
insert into ViajeroEntity(idUsuario, nombreUsuario, idioma, cantidadPlanes, informacionPersonal) values (203040, 'Carlos Sanchez', 'Italiano', 5, 'adios');
insert into ViajeroEntity(idUsuario, nombreUsuario, idioma, cantidadPlanes, informacionPersonal) values (8788, 'Luisa Cifuentes', 'Aleman', 8, 'hasta la vista, baby');
insert into ViajeroEntity(idUsuario, nombreUsuario, idioma, cantidadPlanes, informacionPersonal) values (1984, 'Daenerys Targaryen', 'Dothraki', 8, 'Fire and Blood');
insert into ViajeroEntity(idUsuario, nombreUsuario, idioma, cantidadPlanes, informacionPersonal) values (122048, 'Annabeth Chase', 'Griego', 2, 'Seaweed Brain');

insert into PreferenciaEntity(nombrePreferencia) values ('Iglesias');
insert into PreferenciaEntity(nombrePreferencia) values ('Helado');
insert into PreferenciaEntity(nombrePreferencia) values ('Queso');
insert into PreferenciaEntity(nombrePreferencia) values ('Cabezas decapitadas');
insert into PreferenciaEntity(nombrePreferencia) values ('Museos');
insert into PreferenciaEntity(nombrePreferencia) values ('Pizza');



insert into PaisEntity(id,nombre) values (1,'Colombia');
insert into PaisEntity(id,nombre) values (2,'Canada');
insert into PaisEntity(id,nombre) values (3,'España');
insert into PaisEntity(id,nombre) values (4,'Costa Rica');
insert into PaisEntity(id,nombre) values (5,'Paises Bajos');

insert into CiudadEntity(id,nombre) values (1,'Bogota');
insert into CiudadEntity(id,nombre) values (2,'Otawa');
insert into CiudadEntity(id,nombre) values (3,'Madrid');
insert into CiudadEntity(id,nombre) values (4,'San Juan');
insert into CiudadEntity(id,nombre) values (5,'Amsterdam');


insert into SitioTuristicoEntity(id,tipo,nombre) values (1,'Iglesia','monserrate');
insert into SitioTuristicoEntity(id,tipo,nombre) values (2,'Parque','Parque Otawa');
insert into SitioTuristicoEntity(id,tipo,nombre) values (3,'Palacio','Palacio de oriente');
insert into SitioTuristicoEntity(id,tipo,nombre) values (4,'Castillo','Castillo san jose');
insert into SitioTuristicoEntity(id,tipo,nombre) values (5,'Calle','Calle rosa');




