# **LiterAlura** 📚✨

**LiterAlura** es una aplicación basada en **Java** diseñada para los amantes de la lectura. Te permite explorar, filtrar y gestionar información sobre libros y autores con facilidad. Con un backend robusto construido en **Spring Boot** y la integración de una API externa, **LiterAlura** simplifica la búsqueda y organización de datos literarios.
![img.png](images%2Fimg.png)

## 🌟 **Características Principales**

1. **🔍 Búsqueda de libros por título**  
   Introduce el título completo o una parte, y obtendrás resultados precisos y relevantes.  
   ![img_1.png](images%2Fimg_1.png)

2. **📖 Listado de libros registrados**  
   Visualiza los libros previamente consultados para mantener un registro organizado.  
   ![img_2.png](images%2Fimg_2.png)

3. **✍️ Listado de autores registrados**  
   Accede a una lista de autores que han sido consultados con anterioridad.  
   ![img_3.png](images%2Fimg_3.png)

4. **📅 Autores vivos en un año específico**  
   Descubre los autores que vivieron en un año particular, según los datos disponibles.  
   ![img_4.png](images%2Fimg_4.png)

5. **🌐 Filtrar libros por idioma**  
   Muestra los libros previamente consultados según su idioma de publicación.  
   ![img_5.png](images%2Fimg_5.png)

## ⚙️ **Tecnologías Utilizadas**

- **Java**: Lenguaje principal de desarrollo.
- **Spring Boot**: Framework para el desarrollo ágil del backend.
- **Maven**: Gestión de dependencias y construcción del proyecto.
- **Hibernate**: Persistencia de datos y conexión con la base de datos.
- **PostgreSQL**: Base de datos relacional para almacenar información de libros y autores.
- **JSON**: Intercambio de datos con la API externa.

## 🚀 **Configuración e Instalación**

Sigue estos pasos para configurar y ejecutar **LiterAlura** en tu entorno local:

1. **Clona el repositorio:**
   ```bash
   git clone https://github.com/juanpfh5/literalura.git
   cd literalura
   ```  

2. **Crea la base de datos en PostgreSQL:**  
   Conéctate a tu servidor PostgreSQL y ejecuta:
   ```sql
   CREATE DATABASE literalura;
   ```  

3. **Configura las credenciales de la base de datos:**  
   Abre el archivo `application.properties` y ajusta los valores:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost/literalura
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   ```  

4. **Ejecuta la aplicación:**
   ```bash
   mvn spring-boot:run
   ```  

## 🌐 **Integración con API Externa**

**LiterAlura** se conecta con la API de **Gutendex** para obtener información actualizada sobre libros. Puedes explorar la API aquí: [Gutendex API](https://gutendex.com/).

---
