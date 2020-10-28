CREATE TABLE orders
(
  id            BIGSERIAL        NOT NULL CONSTRAINT products_pkey  PRIMARY KEY,
  items         VARCHAR(128)     NOT NULL,
  customerId    BIGSERIAL        NOT NULL,
  price         DOUBLE PRECISION NOT NULL,
  price_unit    VARCHAR(32)      NOT NULL,
  created_at    BIGSERIAL        NOT NULL,
  updated_at    BIGSERIAL        NOT NULL,

  FOREIGN KEY (customerId) REFERENCES orders(id)
);

CREATE UNIQUE INDEX orders_id_uindex ON orders (id);