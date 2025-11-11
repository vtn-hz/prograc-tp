CREATE DATABASE IF NOT EXISTS Grupo_6
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE Grupo_6;

DROP TABLE IF EXISTS asociado;
CREATE TABLE asociado (
  dni        VARCHAR(20)  NOT NULL,
  nya        VARCHAR(120) NOT NULL,
  telefono   VARCHAR(40)  NOT NULL,
  ciudad     VARCHAR(80)  NOT NULL,
  direccion  VARCHAR(160) NOT NULL,
  PRIMARY KEY (dni),
  KEY idx_asociado_nya (nya)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Datos de prueba: 25 asociados
INSERT INTO asociado (dni, nya, telefono, ciudad, direccion) VALUES
('20000001', 'Ana García', '223-4000001', 'Mar del Plata', 'Av. Colón 100'),
('20000002', 'Diego López', '223-4000002', 'Mar del Plata', 'Belgrano 202'),
('20000003', 'María Fernández', '223-4000003', 'Mar del Plata', 'Mitre 45'),
('20000004', 'Juan Pérez', '223-4000004', 'Mar del Plata', 'San Martín 78'),
('20000005', 'Lucía Gómez', '223-4000005', 'Mar del Plata', 'Rivadavia 321'),
('20000006', 'Sofía Martínez', '223-4000006', 'Miramar', 'Calle 15 500'),
('20000007', 'Carlos Torres', '223-4000007', 'Mar del Plata', 'Hipólito Yrigoyen 60'),
('20000008', 'Martín Álvarez', '223-4000008', 'Mar del Plata', 'España 210'),
('20000009', 'Carolina Ruiz', '223-4000009', 'Mar del Plata', 'Belgrano 450'),
('20000010', 'Pablo Díaz', '223-4000010', 'Mar del Plata', 'Uruguay 99'),
('20000011', 'Laura Herrera', '223-4000011', 'Mar del Plata', 'Perú 12'),
('20000012', 'Federico Castro', '223-4000012', 'Mar del Plata', 'San Juan 88'),
('20000013', 'Marta Bravo', '223-4000013', 'Mar del Plata', 'Chacabuco 23'),
('20000014', 'Ricardo Silva', '223-4000014', 'Mar del Plata', 'Roca 405'),
('20000015', 'Verónica Peña', '223-4000015', 'Mar del Plata', 'Alsina 77'),
('20000016', 'Diego Moreira', '223-4000016', 'Mar del Plata', 'Sarmiento 234'),
('20000017', 'Mariana Ortega', '223-4000017', 'Mar del Plata', 'Dorrego 18'),
('20000018', 'Héctor Blanco', '223-4000018', 'Mar del Plata', 'La Rioja 3'),
('20000019', 'Esteban Molina', '223-4000019', 'Mar del Plata', 'Castelli 56'),
('20000020', 'Claudia Vega', '223-4000020', 'Mar del Plata', 'Alem 190'),
('20000021', 'Andrés Núñez', '223-4000021', 'Mar del Plata', 'Italia 305'),
('20000022', 'Paula Rojas', '223-4000022', 'Mar del Plata', 'Pueyrredón 22'),
('20000023', 'Gonzalo Prado', '223-4000023', 'Mar del Plata', 'Belgrano 12'),
('20000024', 'Silvia Costa', '223-4000024', 'Mar del Plata', 'Chubut 400'),
('20000025', 'Nicolás Herrera', '223-4000025', 'Mar del Plata', 'Paz 5')

ON DUPLICATE KEY UPDATE
  nya = VALUES(nya),
  telefono = VALUES(telefono),
  ciudad = VALUES(ciudad),
  direccion = VALUES(direccion);