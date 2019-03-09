delete from ViajeroEntity;
delete from PreferenciaEntity;

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