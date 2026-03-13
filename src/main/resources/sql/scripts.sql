CREATE TABLE ordersdb.users (
                                username VARCHAR(50) NOT NULL PRIMARY KEY,
                                password VARCHAR(500) NOT NULL,
                                enabled TINYINT(1) NOT NULL
);
CREATE TABLE authorities (
                             username VARCHAR(50) NOT NULL,
                             authority VARCHAR(50) NOT NULL,
                             CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users(username)
);
create unique index ix_auth_username on authorities (username,authority);
