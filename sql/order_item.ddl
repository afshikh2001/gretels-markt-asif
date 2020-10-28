CREATE TABLE order_item
(
  id               BIGSERIAL        NOT NULL CONSTRAINT order_item_pkey  PRIMARY KEY,
  name             VARCHAR(128)     NOT NULL,
  quantity         DOUBLE PRECISION NOT NULL,
  quantity_unit    VARCHAR(32)      NOT NULL,
  price            DOUBLE PRECISION NOT NULL,
  price_unit       VARCHAR(32)      NOT NULL,
  product_id       BIGSERIAL        NOT NULL,
  order_id         BIGSERIAL        NOT NULL,
  created_at       BIGSERIAL        NOT NULL,
  updated_at       BIGSERIAL        NOT NULL,

  FOREIGN KEY (product_id) REFERENCES order_item(id)
  FOREIGN KEY (order_id)   REFERENCES order_item(id)
);

CREATE UNIQUE INDEX order_item_id_uindex ON order_item (id);