CREATE DATABASE IF NOT EXISTS tamarithz;

ALTER DATABASE tamarithz
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

GRANT ALL PRIVILEGES ON tamarithz.* TO pc@localhost IDENTIFIED BY 'pc';

USE tamarithz;

/*TODO Crear tablas + hacer inserts de los activos/amenazas/salvaguardas*/
