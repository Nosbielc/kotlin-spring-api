CREATE TABLE users (
   id BIGINT AUTO_INCREMENT PRIMARY KEY,
   username VARCHAR(255) NOT NULL UNIQUE,
   password VARCHAR(255) NOT NULL,
   salt VARCHAR(255) NOT NULL,
   created_at timestamp DEFAULT CURRENT_TIMESTAMP,
   enabled BOOLEAN DEFAULT TRUE,
   expired BOOLEAN DEFAULT FALSE,
   locked BOOLEAN DEFAULT FALSE,
   credentials_expired BOOLEAN DEFAULT FALSE
);

CREATE TABLE roles (
   id BIGINT AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(255) NOT NULL UNIQUE
);

INSERT INTO roles (name) VALUES ('ADMIN');
INSERT INTO roles (name) VALUES ('USER');
INSERT INTO roles (name) VALUES ('GUEST');
INSERT INTO roles (name) VALUES ('MODERATOR');
INSERT INTO roles (name) VALUES ('EDITOR');

CREATE TABLE user_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id),
    PRIMARY KEY (user_id, role_id)
);

INSERT INTO users (username, password, salt, enabled) VALUES ('adminUser', '$2a$10$RmxBenb/YgIYPWgrrkr7Lu7.6n4qd34eVSTh4kR29uE3KtMmQlK0O', 'OI1kgS0sKcpZFIpxkmndbA==', TRUE); --adminPass
INSERT INTO users (username, password, salt, enabled) VALUES ('regularUser', '$2a$10$E1SOL3qM9JNp78CU1elgTejfqWRLWiowa/2lUdHs2imagF7cLph52', 'yIPLlVoyXhnRSmeMxOhabA==', TRUE); --userPass
INSERT INTO users (username, password, salt, enabled) VALUES ('guestUser', '$2a$10$zttp48LMLylgfZzzN.UoaupQCm2t0BN4XPdmS.qAzqKBhc2Myysre', 't7x+XzoQMW2pitxrM3yf2g==', TRUE); --guestPass
INSERT INTO users (username, password, salt, enabled) VALUES ('modUser', '$2a$10$K9iASh/yFHJe2qijrgp2uObVTx7raNli41Nn3TDCA0FNqemOkCaCC', 'fi6LwR/V42cEcPY5Xh4rLQ==', TRUE); --modPass
INSERT INTO users (username, password, salt, enabled) VALUES ('editorUser', '$2a$10$idtvUdMRweHK4qenid22suYmVXUh11qaG.rPPyrKb5C6MIg2V.1t.', 'VRs0JcpjVjWFSzv583iiVQ==', TRUE); --editorPass

INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);  -- adminUser -> ADMIN
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2);  -- regularUser -> USER
INSERT INTO user_roles (user_id, role_id) VALUES (3, 3);  -- guestUser -> GUEST
INSERT INTO user_roles (user_id, role_id) VALUES (4, 4);  -- modUser -> MODERATOR
INSERT INTO user_roles (user_id, role_id) VALUES (5, 5);  -- editorUser -> EDITOR

CREATE TABLE tokens (
      id BIGINT AUTO_INCREMENT PRIMARY KEY,
      token VARCHAR(255) UNIQUE,
      tokenType VARCHAR(255) DEFAULT 'BEARER',
      revoked BOOLEAN DEFAULT FALSE,
      expired BOOLEAN DEFAULT FALSE,
      user_id BIGINT,
      FOREIGN KEY (user_id) REFERENCES users(id)
);
