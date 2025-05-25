-- Truncate all tables with CASCADE to remove FK dependencies safely
TRUNCATE TABLE
    GROUPS,
    SUBJECTS,
    PROGRAMS,
    AREAS,
    CONTRACT_TYPES,
    FACULTIES,
    EMPLOYEES,
    EMPLOYEE_TYPES,
    CAMPUSES,
    CITIES,
    DEPARTMENTS,
    COUNTRIES
CASCADE;

-- Disable triggers (foreign key constraints)
ALTER TABLE AREAS DISABLE TRIGGER ALL;
ALTER TABLE SUBJECTS DISABLE TRIGGER ALL;
ALTER TABLE CITIES DISABLE TRIGGER ALL;
ALTER TABLE DEPARTMENTS DISABLE TRIGGER ALL;
ALTER TABLE EMPLOYEES DISABLE TRIGGER ALL;
ALTER TABLE FACULTIES DISABLE TRIGGER ALL;
ALTER TABLE GROUPS DISABLE TRIGGER ALL;
ALTER TABLE PROGRAMS DISABLE TRIGGER ALL;
ALTER TABLE CAMPUSES DISABLE TRIGGER ALL;



-- Insert Countries
INSERT INTO COUNTRIES (code, name) VALUES
(1, 'Colombia');


-- Insert Departments
INSERT INTO DEPARTMENTS (code, name, country_code) VALUES
(1, 'Valle del Cauca', 1),
(2, 'Cundinamarca', 1),
(5, 'Antioquia', 1),
(8, 'Atlántico', 1),
(11, 'Bogotá D.C.', 1);


-- Insert Cities
INSERT INTO CITIES (code, name, dept_code) VALUES
(101, 'Cali', 1),
(102, 'Bogotá', 11),
(103, 'Medellín', 5),
(104, 'Barranquilla', 8),
(105, 'Barranquilla', 8);

-- Insert Campuses
INSERT INTO CAMPUSES (code, name, city_code) VALUES
(1, 'Campus Cali', 101),
(2, 'Campus Bogotá', 102),
(3, 'Campus Medellín', 103),
(4, 'Campus Barranquilla', 104);

-- Insert Contract Types
INSERT INTO CONTRACT_TYPES (name) VALUES
('Planta'),
('Cátedra');


-- Insert Employee Types
INSERT INTO EMPLOYEE_TYPES (name) VALUES
('Docente'),
('Administrativo');

-- Insert Employees
INSERT INTO EMPLOYEES (id, first_name, last_name, email, contract_type, employee_type, faculty_code, campus_code, birth_place_code) VALUES
('1001', 'Juan', 'Pérez', 'juan.perez@univcali.edu.co', 'Planta', 'Docente', 1, 1, 101),
('1002', 'María', 'Gómez', 'maria.gomez@univcali.edu.co', 'Planta', 'Administrativo', 1, 2, 102),
('1003', 'Carlos', 'López', 'carlos.lopez@univcali.edu.co', 'Cátedra', 'Docente', 2, 1, 103),
('1004', 'Carlos', 'Mejía', 'carlos.mejia@univcali.edu.co', 'Planta', 'Docente', 1, 3, 103),
('1005', 'Sandra', 'Ortiz', 'sandra.ortiz@univcali.edu.co', 'Cátedra', 'Docente', 2, 4, 104),
('1006', 'Julián', 'Reyes', 'julian.reyes@univcali.edu.co', 'Planta', 'Administrativo', 2, 1, 105);

-- Insert Faculties
INSERT INTO FACULTIES (code, name, location, phone_number, dean_id) VALUES
(1, 'Facultad de Ciencias Sociales', 'Cali', '555-1234', '1001'),
(2, 'Facultad de Ingeniería', 'Cali', '555-5678', '1002');


-- Insert Areas
INSERT INTO AREAS (code, name, faculty_code, coordinator_id) VALUES
(1, 'Área de Ciencias Sociales', 1, '1001'),
(2, 'Área de Ingeniería', 2, '1003');

-- Insert Programs
INSERT INTO PROGRAMS (code, name, area_code) VALUES
(1, 'Psicología', 1),
(2, 'Ingeniería de Sistemas', 2);

-- Insert Subjects
INSERT INTO SUBJECTS (code, name, program_code) VALUES
('S101', 'Psicología General', 1),
('S102', 'Cálculo I', 2),
('S103', 'Programación', 2),
('S104', 'Estructuras de Datos', 2),
('S105', 'Bases de Datos', 2),
('S106', 'Redes de Computadores', 2),
('S107', 'Sistemas Operativos', 2),
('S108', 'Algoritmos Avanzados', 2);


-- Insert Groups
INSERT INTO GROUPS (number, semester, subject_code, professor_id) VALUES
(1, '2023-2', 'S101', '1001'),
(2, '2023-2', 'S102', '1003'),
(3, '2023-2', 'S103', '1004');

-- Re-enable triggers (foreign key constraints)
ALTER TABLE AREAS ENABLE TRIGGER ALL;
ALTER TABLE SUBJECTS ENABLE TRIGGER ALL;
ALTER TABLE CITIES ENABLE TRIGGER ALL;
ALTER TABLE DEPARTMENTS ENABLE TRIGGER ALL;
ALTER TABLE EMPLOYEES ENABLE TRIGGER ALL;
ALTER TABLE FACULTIES ENABLE TRIGGER ALL;
ALTER TABLE GROUPS ENABLE TRIGGER ALL;
ALTER TABLE PROGRAMS ENABLE TRIGGER ALL;
ALTER TABLE CAMPUSES ENABLE TRIGGER ALL;
