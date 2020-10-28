CREATE TABLE user
(
  id                       BIGSERIAL        NOT NULL CONSTRAINT user_pkey PRIMARY KEY,
  first_name               VARCHAR(128)     NOT NULL,
  last_name                VARCHAR(128)     NOT NULL,
  email                    VARCHAR(128)     NOT NULL,
  password                 VARCHAR(128)     NOT NULL,
  plz                      INTEGER     NOT NULL,
  strasse                  VARCHAR(128)     NOT NULL,
  ort                      VARCHAR(128)     NOT NULL,
  country                  VARCHAR(128)     NOT NULL,
  additional_address       VARCHAR(128)     NOT NULL,
  created_at               BIGSERIAL        NOT NULL,
  updated_at               BIGSERIAL        NOT NULL
);

CREATE UNIQUE INDEX user_id_uindex ON user(id);

CREATE UNIQUE INDEX user_id_uindex ON user(email);