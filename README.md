# Instalación:
## Base de datos 

* [Instalar wamp server](https://sourceforge.net/projects/wampserver/)
* Abrir PHPMyAdmin
* Importar el script de la base de datos

## Creación de entorno

* [Instalar Netbeans 8.2 con Apache Tomcat](https://netbeans.org/downloads/8.2/rc/)
* Clonar este repositorio:
```
git clone https://github.com/luisdanielruiz/SBB-backend
```
* Configurar proyecto en Netbeans

## Para instalación del librerias 
* Librerías de back end(En el project click derecho>propiedades>Librerías>Add Jar or folder>ruteo la carpeta>selecciono todas>listo)
* En el server.xml del tomcat agregar contexto (servers > click derecho en tomcat> edit server.xml) y agregar el contexto.

## Generar .war
* Click derecho en el proyecto, selecionar "Clean and Build"
