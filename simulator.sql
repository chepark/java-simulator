-- create waiting_time table where the waiting time of each service is stored.
CREATE TABLE waiting_time (
    id INT AUTO_INCREMENT PRIMARY KEY,
    service_name VARCHAR(255),
    total_customers INT,
    starting_time DOUBLE,
    ending_time DOUBLE,
    avg_service_time DOUBLE
);

--test code
INSERT INTO waiting_time (service_name, total_customers, starting_time, ending_time, avg_service_time)
VALUES ('ServiceA', 100, 9.5, 17.3, 12.5);

-- create event table that contains service point and customers.
CREATE TABLE event (
    id INT AUTO_INCREMENT PRIMARY KEY,
    service_point VARCHAR(255),
    customers VARCHAR(255)
);

--test code
INSERT INTO event (service_point, customers)
VALUES ('ServicePoint 1', '1, 2, 3');