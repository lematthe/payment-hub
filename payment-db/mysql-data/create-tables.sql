use payments;
CREATE TABLE  processed(id INTEGER,txid VARCHAR(50), txType VARCHAR(50), requestedAmount DOUBLE (40,2), txState VARCHAR(50), countryCode VARCHAR(50), institutionID VARCHAR(50), ackid VARCHAR(50), ackNotes VARCHAR(50),  ackStatus VARCHAR(50));
CREATE TABLE orphan_tx(id INTEGER,txid VARCHAR(50), txType VARCHAR(50), requestedAmount DOUBLE (40,2), txState VARCHAR(50), countryCode VARCHAR(50), institutionID VARCHAR(50) );
CREATE TABLE orphan_ack ( id BIGINT, ackID VARCHAR(50), txID VARCHAR(5), confirmedAmount DOUBLE(40,2), ackNotes VARCHAR(50),  ackStatus VARCHAR(50));
