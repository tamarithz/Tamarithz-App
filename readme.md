# Tamarith

Herramienta para el análisis de riesgos basado en Magerit V3

## Cómo se instala

-DESARROLLO : Importar como proyecto maven, ejecutar como proyecto de Spring
-PRODUCCIÓN : Instalar con Docker

### Prerequisitos

-DESARROLLO : Spring, se recomienda un editor para archivos .less (gestionados con wro)

```
Copiar el archivo .big dentro de la carpeta ./bang
```

### Instrucciones

-Crear un usuario (el primero al desplegar tendrá permisos de administrador)

```
Home/registrar
```
-Añadir nueva empresa en la seccion 'BUSCAR EMPRESA'

```
Añadir Empresa
```
-Generar un nuevo informe y obtener los datos necesarios de la empresa: 'ACTIVOS', 'AMENAZAS', 'SALVAGUARDAS'

```
Iniciar nuevo informe
```


Para más informacion : https:\\tamarithz.com

## Pruebas unitarias

Cómo se puede probar parte del software



## Herramientas usadas

* [Spring](https://spring.io/) - El framework de Java usado
* [Thymeleaf] (https://www.thymeleaf.org/) - Thymeleaf es un generador de plantillas moderno para aplicaciones web y standalone
* [HSQLDB] (http://hsqldb.org/) - Base de datos de desarrollo con soporte para MySQL


## Versiones

-[0.1] - Estructura de ficheros y base de datos. Generación de entidades por ingeniería inversa
-[0.2] - Creación de controladores y servicios para consumir las entidades.
-[0.3] - Inicio de la creación de la vista con Thymeleaf: plantilla "layout"
-[0.4] - Añadidas nuevas plantillas (input y select), relación de modelo con vista
-[0.5] - Pruebas unitarias con jUnit, remapeo de peticiones, revisión de integridad relacional

## Autores

* **Israel Sánchez Montoro** - *Descripción de las tareas* - [AudeSie7e](https://github.com/AudeSie7e)



## License

<a rel="license" href="http://creativecommons.org/licenses/by-sa/4.0/"><img alt="Licencia de Creative Commons" style="border-width:0" src="https://i.creativecommons.org/l/by-sa/4.0/88x31.png" /></a><br />Este obra está bajo una <a rel="license" href="http://creativecommons.org/licenses/by-sa/4.0/">licencia de Creative Commons Reconocimiento-CompartirIgual 4.0 Internacional</a>.

## Agradecimientos

* Salvador Zurita
* Empleo Digital Telefónica
* Tech Consulting
* Centro Criptológico Nacional