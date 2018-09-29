CREATE TABLE users
(
  id            BIGSERIAL        NOT NULL
    CONSTRAINT users_pkey
    PRIMARY KEY,
  vorName               VARCHAR(128)     NOT NULL,
  nachName              VARCHAR(128)     NOT NULL,
  email                 VARCHAR(128)     NOT NULL,
  password              VARCHAR(128)     NOT NULL,
  plz                   INTEGER     NOT NULL,
  straße                VARCHAR(128)     NOT NULL,
  ort                   VARCHAR(128)     NOT NULL,
  country               VARCHAR(128)     NOT NULL,
  additionalAddress     VARCHAR(128)     NOT NULL,
  updated_at            BIGSERIAL        NOT NULL
);

CREATE UNIQUE INDEX users_id_uindex
  ON users (id);

CREATE UNIQUE INDEX users_id_uindex
  ON users (email);