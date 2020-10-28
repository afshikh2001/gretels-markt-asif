CREATE TABLE order
(
  id            BIGSERIAL        NOT NULL CONSTRAINT order_pkey  PRIMARY KEY,
  customer_id   BIGSERIAL        NOT NULL,
  price         DOUBLE PRECISION NOT NULL,
  price_unit    VARCHAR(32)      NOT NULL,
  created_at    BIGSERIAL        NOT NULL,
  updated_at    BIGSERIAL        NOT NULL,

  FOREIGN KEY (customer_id) REFERENCES order(id)
);

CREATE UNIQUE INDEX order_id_uindex ON order (id);