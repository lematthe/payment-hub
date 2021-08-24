CREATE TABLE processed_tx (id INTEGER, name VARCHAR(256), price FLOAT, variant INTEGER);
CREATE TABLE orphan_tx(id INTEGER, name VARCHAR(256));
INSERT INTO orphan_ack (id, name) VALUES ('1', 'blue'), ('2', 'green');