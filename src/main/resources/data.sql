-- Truncate all tables with CASCADE to remove FK dependencies safely
TRUNCATE TABLE
    USER_SUBJECTS,
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
    COUNTRIES,
    USERS
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
ALTER TABLE USER_SUBJECTS DISABLE TRIGGER ALL;
ALTER TABLE USERS DISABLE TRIGGER ALL;

-- Insert Users
INSERT INTO USERS (id, name, password) VALUES
(1, 'estudiante1', '12345'),
(2, 'estudiante2', '12345'),
(3, 'estudiante3', '12345'),
(4, 'estudiante4', '12345'),
(5, 'estudiante5', '12345');

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
(2, 'Ingeniería de Sistemas', 2),
(3, 'Ingeniería Industrial', 2),
(4, 'Ingeniería Civil', 2),
(5, 'Ingeniería Electrónica', 2),
(6, 'Ingeniería Mecánica', 2);

-- Insert Subjects
INSERT INTO SUBJECTS (code, name, program_code) VALUES
-- Psicología
('PSI-1001', 'Psicología General', 1),
('PSI-1002', 'Psicología del Desarrollo', 1),
('PSI-2001', 'Psicología Social', 1),
('PSI-2002', 'Psicología Clínica', 1),

-- Ingeniería de Sistemas
('ISIS-1001', 'Introducción a la Programación', 2),
('ISIS-1002', 'Fundamentos de Programación', 2),
('ISIS-2001', 'Estructuras de Datos', 2),
('ISIS-2002', 'Bases de Datos', 2),
('ISIS-3001', 'Desarrollo Web', 2),
('ISIS-3002', 'Desarrollo Móvil', 2),
('ISIS-4001', 'Inteligencia Artificial', 2),
('ISIS-4002', 'Machine Learning', 2),

-- Ingeniería Industrial
('IND-1001', 'Introducción a la Ingeniería Industrial', 3),
('IND-1002', 'Estadística Descriptiva', 3),
('IND-2001', 'Investigación de Operaciones', 3),
('IND-2002', 'Gestión de Calidad', 3),

-- Ingeniería Civil
('CIV-1001', 'Introducción a la Ingeniería Civil', 4),
('CIV-1002', 'Mecánica de Materiales', 4),
('CIV-2001', 'Estructuras', 4),
('CIV-2002', 'Hidráulica', 4),

-- Ingeniería Electrónica
('ELE-1001', 'Introducción a la Ingeniería Electrónica', 5),
('ELE-1002', 'Circuitos Eléctricos', 5),
('ELE-2001', 'Electrónica Digital', 5),
('ELE-2002', 'Microcontroladores', 5),

-- Ingeniería Mecánica
('MEC-1001', 'Introducción a la Ingeniería Mecánica', 6),
('MEC-1002', 'Termodinámica', 6),
('MEC-2001', 'Mecánica de Fluidos', 6),
('MEC-2002', 'Diseño Mecánico', 6);


-- Insert Groups
INSERT INTO GROUPS (number, semester, subject_code, professor_id) VALUES
(1, '2023-2', 'PSI-1001', '1001'),
(2, '2023-2', 'ISIS-1001', '1003'),
(3, '2023-2', 'ISIS-1002', '1004');

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
ALTER TABLE USER_SUBJECTS ENABLE TRIGGER ALL;
ALTER TABLE USERS ENABLE TRIGGER ALL;

