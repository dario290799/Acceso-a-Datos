create table coldwar_partida(
     id int PRIMARY KEY,
     fecha varchar(10),
     hora varchar2(5),
     num_ronda int,
     puntero int
);

create table coldwar_planeta(
    id int PRIMARY KEY,
    vidas int,
    tipo varchar(50),
    nombre varchar(100),
    misiles_ataque int,
    misiles_defensa int,
    misiles_ronda int
);
