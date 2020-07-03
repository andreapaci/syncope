-- Licensed to the Apache Software Foundation (ASF) under one
-- or more contributor license agreements.  See the NOTICE file
-- distributed with this work for additional information
-- regarding copyright ownership.  The ASF licenses this file
-- to you under the Apache License, Version 2.0 (the
-- "License"); you may not use this file except in compliance
-- with the License.  You may obtain a copy of the License at

--  http://www.apache.org/licenses/LICENSE-2.0

-- Unless required by applicable law or agreed to in writing,
-- software distributed under the License is distributed on an
-- "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
-- KIND, either express or implied.  See the License for the
-- specific language governing permissions and limitations
-- under the License.
ALTER USER sa SET PASSWORD '${testdb.password}';

DROP TABLE test IF EXISTS;
CREATE TABLE test (
id VARCHAR(50) PRIMARY KEY,
password VARCHAR(255) NOT NULL,
status VARCHAR(5));

INSERT INTO test VALUES ('testuser1', 'password', 'false');

DROP TABLE test2 IF EXISTS;
CREATE TABLE test2 (
id VARCHAR(50) PRIMARY KEY,
password VARCHAR(255) NOT NULL,
status VARCHAR(5));

INSERT INTO test2 VALUES ('testuser2', 'password321', 'false');
INSERT INTO test2 VALUES ('rossini', 'password321', 'true');
INSERT INTO test2 VALUES ('verdi', 'password321', 'true');

DROP TABLE testpull IF EXISTS;
CREATE TABLE testpull (
id CHAR(36) PRIMARY KEY,
username VARCHAR(80),
surname VARCHAR(80),
mustChangePassword BOOLEAN,
email VARCHAR(80),
lastModification TIMESTAMP);

DROP TABLE testPRINTER IF EXISTS;
CREATE TABLE testPRINTER (
id CHAR(36) PRIMARY KEY,
printername VARCHAR(80),
location VARCHAR(80),
deleted BOOLEAN DEFAULT FALSE,
lastModification TIMESTAMP);

CREATE TABLE user (
capsId INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1) PRIMARY KEY,
userId VARCHAR(30) NOT NULL,
password VARCHAR(255) NOT NULL,
type VARCHAR(17) NOT NULL,
residence VARCHAR(60),
telephone VARCHAR(20),
fax VARCHAR(20),
preference VARCHAR(120),
name VARCHAR(30),
surname VARCHAR(35),
fullname VARCHAR(35) NOT NULL,
birthdate VARCHAR(30),
gender VARCHAR(1),
taxNumber VARCHAR(30),
state VARCHAR(15),
studyTitle VARCHAR(30),
studyArea VARCHAR(30),
job VARCHAR(30),
companyType VARCHAR(30),
companyName VARCHAR(30),
vatNumber VARCHAR(30),
mandatoryDisclaimer BOOLEAN,
promoRCSDisclaimer BOOLEAN,
promoThirdPartyDisclaimer BOOLEAN,
UNIQUE(userId));

CREATE TABLE role (
roleId INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1) PRIMARY KEY,
roleName VARCHAR(120),
UNIQUE(roleName));
