CREATE TABLE category
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_date  datetime              NULL,
    updated_date  datetime              NULL,
    name          VARCHAR(255)          NULL,
    `description` VARCHAR(255)          NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE product
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_date  datetime              NULL,
    updated_date  datetime              NULL,
    title         VARCHAR(255)          NULL,
    `description` VARCHAR(255)          NULL,
    price         DOUBLE                NULL,
    imageurl      VARCHAR(255)          NULL,
    category_id   BIGINT                NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);