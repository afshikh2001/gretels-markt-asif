CREATE TABLE product
(
  id            BIGSERIAL        NOT NULL CONSTRAINT product_pkey  PRIMARY KEY,
  name          VARCHAR(128)     NOT NULL,
  type          VARCHAR(32)      NOT NULL,
  quantity      INTEGER          NOT NULL,
  quantity_unit VARCHAR(32)      NOT NULL,
  price         DOUBLE PRECISION NOT NULL,
  price_unit    VARCHAR(32)      NOT NULL,
  angebot       BOOLEAN,
  gesmeck       VARCHAR(32),
  media         BIGINT           NOT NULL,
  created_at    BIGSERIAL        NOT NULL,
  updated_at    BIGSERIAL        NOT NULL
);

CREATE UNIQUE INDEX product_id_uindex ON product(id);