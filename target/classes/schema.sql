CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_code VARCHAR(255) NOT NULL UNIQUE,
    product_name VARCHAR(255) NOT NULL,
    product_description VARCHAR(500) NOT NULL,
    product_price DOUBLE NOT NULL,
    product_quantity INT NOT NULL,
    inventory_status VARCHAR(255) NOT NULL,
    product_category VARCHAR(255) NOT NULL,
    product_image VARCHAR(255),
    product_rating INT,
    internal_reference VARCHAR(255) NOT NULL UNIQUE,
    shell_id BIGINT,
    created_at BIGINT,
    updated_at BIGINT
);

