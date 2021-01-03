CREATE TABLE orders
(
  id            int           NOT NULL AUTO_INCREMENT,
  customer_id   BIGINT        NOT NULL,
  price         DOUBLE PRECISION  NOT NULL,
  price_unit    VARCHAR(255)      NOT NULL,
  created_at    BIGINT            NOT NULL,
  updated_at    BIGINT            NOT NULL,
primary key (id)
);