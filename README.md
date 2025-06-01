# 🚀 Proyecto Persistencia Polígolota - Sistema de Comentarios

## 👥 Integrantes

- Damy Villegas
- Samuel Dominguez
- Santiago Santacruz
- Alejandro Troya
- Juan Esteban Gomez

---

## 📋 Descripción

Sistema de **Persistencia Polígolota** que utiliza tanto **PostgreSQL** como **MongoDB** para gestionar datos universitarios y comentarios sobre planes de evaluación. Incluye una interfaz web moderna para la gestión de comentarios.

---

## 🛠️ Prerrequisitos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

- **Java 17** o superior
- **PostgreSQL** (cualquier versión reciente)
- **MongoDB** (cualquier versión reciente)
- **Maven** (incluido con el proyecto via wrapper)

---

## ⚙️ Configuración e Instalación

### 1. **Clonar y navegar al proyecto**
```bash
git clone <url-del-repositorio>
cd Final-Sid2
```

### 2. **Configurar PostgreSQL**
```bash
# Iniciar servicio PostgreSQL
sudo systemctl start postgresql

# O en Windows:
# net start postgresql

# Crear la base de datos
sudo -u postgres createdb proyectosid

# Configurar contraseña del usuario postgres
sudo -u postgres psql -c "ALTER USER postgres PASSWORD 'postgres';"
```

### 3. **Configurar MongoDB**
```bash
# Iniciar servicio MongoDB
sudo systemctl start mongod

# O en Windows:
# net start mongodb
```

### 4. **Verificar servicios**
```bash
# Verificar PostgreSQL
pg_isready

# Verificar MongoDB
systemctl status mongod
```

---

## 🚀 Ejecutar el Proyecto

### **Opción 1: Usando Maven Wrapper (Recomendado)**
```bash
# En Linux/Mac:
./mvnw spring-boot:run

# En Windows:
mvnw.cmd spring-boot:run
```

### **Opción 2: Usando Maven instalado**
```bash
mvn spring-boot:run
```

### **Opción 3: Compilar y ejecutar JAR**
```bash
# Compilar
./mvnw clean package

# Ejecutar
java -jar target/demo-0.0.1-SNAPSHOT.war
```

---

## 🌐 Acceso a la Aplicación

Una vez ejecutado el proyecto, puedes acceder a:

### **🎨 Interfaz Web (Frontend)**
- **URL Principal:** http://localhost:8080/
- **Funcionalidad:** Sistema completo de comentarios con interfaz moderna
- **Características:**
  - Ver planes de evaluación
  - Agregar comentarios a planes
  - Ver comentarios existentes
  - Interfaz responsive y moderna

### **🔌 API REST (Backend)**

#### **Endpoints MongoDB (Comentarios y Planes):**
- `GET http://localhost:8080/evalplans` - Todos los planes de evaluación
- `GET http://localhost:8080/comments` - Todos los comentarios
- `POST http://localhost:8080/comments/evalplan/{planId}` - Agregar comentario a un plan
- `GET http://localhost:8080/comments/evalplan/{planId}` - Comentarios de un plan específico

#### **Endpoints PostgreSQL (Datos Universitarios):**
- `GET http://localhost:8080/employees` - Empleados
- `GET http://localhost:8080/countries` - Países
- `GET http://localhost:8080/subjects` - Materias
- `GET http://localhost:8080/users` - Usuarios
- `GET http://localhost:8080/faculties` - Facultades

---

## 📊 Datos de Prueba

El proyecto carga automáticamente datos de ejemplo:

### **MongoDB (Comentarios):**
- 2 planes de evaluación: "Algebra Basics" y "Physics Intro"
- Comentarios ejemplo con usuarios y contenido
- Actividades asociadas a los planes

### **PostgreSQL (Universidad):**
- Países, departamentos y ciudades de Colombia
- Campus universitarios (Cali, Bogotá, Medellín, Barranquilla)
- Facultades y programas académicos
- Empleados y tipos de contrato

---

## 🧪 Probar la Funcionalidad

### **1. Interfaz Web:**
1. Abre http://localhost:8080/
2. Selecciona un plan de evaluación
3. Ve los comentarios existentes
4. Agrega un nuevo comentario
5. Observa la actualización en tiempo real

### **2. API con curl:**
```bash
# Ver planes de evaluación
curl http://localhost:8080/evalplans

# Ver empleados
curl http://localhost:8080/employees

# Agregar comentario
curl -X POST http://localhost:8080/comments/evalplan/plan1 \
  -H "Content-Type: application/json" \
  -d '{"user": "TestUser", "content": "Excelente plan!"}'
```

---

## 🛠️ Tecnologías Utilizadas

- **Backend:** Spring Boot 3.5.0, Java 17
- **Bases de Datos:** PostgreSQL + MongoDB
- **Frontend:** HTML5, CSS3, JavaScript ES6+
- **Persistencia:** JPA (PostgreSQL) + Spring Data MongoDB
- **Estilo:** Font Awesome, CSS Grid, Flexbox

---

## 🔧 Solución de Problemas

### **Error: Base de datos no encontrada**
```bash
# Recrear base de datos PostgreSQL
sudo -u postgres dropdb proyectosid
sudo -u postgres createdb proyectosid
```

### **Error: MongoDB no conecta**
```bash
# Reiniciar MongoDB
sudo systemctl restart mongod
```

### **Error: Puerto 8080 ocupado**
```bash
# Cambiar puerto en application.properties
server.port=8081
```

### **Error: Java version**
```bash
# Verificar versión de Java
java -version
# Debe ser Java 17 o superior
```

---

## 📝 Funcionalidades Principales

✅ **Persistencia Polígolota** - PostgreSQL + MongoDB  
✅ **API REST Completa** - CRUD para todas las entidades  
✅ **Sistema de Comentarios** - Agregar/Ver comentarios en planes  
✅ **Interfaz Web Moderna** - Frontend responsive e intuitivo  
✅ **Datos de Prueba** - Carga automática de datos ejemplo  
✅ **Relaciones Complejas** - Entre universidades, empleados y facultades  

---

## 🚪 Comandos Útiles

```bash
# Detener la aplicación
Ctrl + C

# Ver logs de la aplicación
./mvnw spring-boot:run --debug

# Limpiar y recompilar
./mvnw clean compile

# Ejecutar tests
./mvnw test
```

---

**¡El proyecto está listo para usar!** 🎉

Para cualquier problema, verifica que PostgreSQL y MongoDB estén ejecutándose correctamente.
