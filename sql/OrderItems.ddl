CREATE TABLE orderItems
(
  id               BIGSERIAL        NOT NULL CONSTRAINT products_pkey  PRIMARY KEY,
  name             VARCHAR(128)     NOT NULL,
  quantity         DOUBLE PRECISION NOT NULL,
  quantity_unit    VARCHAR(32)      NOT NULL,
  price            DOUBLE PRECISION NOT NULL,
  price_unit       VARCHAR(32)      NOT NULL,
  product_id       BIGSERIAL        NOT NULL,
  order_id         BIGSERIAL        NOT NULL,
  created_at       BIGSERIAL        NOT NULL,
  updated_at       BIGSERIAL        NOT NULL,

  FOREIGN KEY (product_id) REFERENCES products(id)
  FOREIGN KEY (order_id)   REFERENCES orders(id)
);

CREATE UNIQUE INDEX orders_id_uindex ON orders (id);