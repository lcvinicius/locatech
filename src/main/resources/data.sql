CREATE TABLE vehicle (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    brand VARCHAR(255),
    model VARCHAR(255),
    year_manufacture INT,
    plate VARCHAR(255),
    color VARCHAR(255),
    daily_value DECIMAL(10, 2)
);

CREATE TABLE people (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    cpf VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(255)  
);

CREATE TABLE rent (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    people_id BIGINT NOT NULL,
    vehicle_id BIGINT NOT NULL,
    start_date DATE,
    end_date DATE,
    total_value DECIMAL(10, 2),
    FOREIGN KEY (people_id) REFERENCES people(id),
    FOREIGN KEY (vehicle_id) REFERENCES vehicle(id)
);

INSERT INTO vehicle (brand, model, year_manufacture, plate, color, daily_value) VALUES
('Toyota', 'Corolla', 2020, 'ABC1234', 'Red', 100.00),
('Honda', 'Civic', 2019, 'XYZ5678', 'Blue', 120.00),
('Ford', 'Focus', 2021, 'LMN9101', 'Black', 110.00),
('Chevrolet', 'Cruze', 2018, 'QRS2345', 'White', 90.00),
('Nissan', 'Sentra', 2022, 'TUV6789', 'Green', 130.00);

INSERT INTO people (name, cpf, email, phone) VALUES
('Maria', '7164343456', 'maria@spring.com', '(11)42542913');

INSERT INTO rent (people_id, vehicle_id, start_date, end_date, total_value) VALUES
(1, 1, '2024-10-01', '2024-10-15', 1500.00);
