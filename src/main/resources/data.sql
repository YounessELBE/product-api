-- data.sql

-- Insert data for the Product table
INSERT INTO product (
    product_code,
    product_name,
    product_description,
    product_price,
    product_quantity,
    inventory_status,
    product_category,
    product_image,
    product_rating,
    internal_reference,
    shell_id,
    created_at,
    updated_at
) VALUES
      (
          'code-1',
          'Product 1',
          'Product 1 Description',
          100,
          10,
          'INSTOCK',
          'A Category',
          'product1.jpg',
          5,
          'product1ref_1',
          11111111,
          1731364892825,
          1731364892825
      ),
      (
          'code-2',
          'Product 2',
          'Product 2 Description',
          200,
          2,
          'LOWSTOCK',
          'B Category',
          'product2.jpg',
          4,
          'product2ref_2',
          22222222,
          1731364892825,
          1731364892825
      ),
      (
          'code-3',
          'Product 3',
          'Product 3 Description',
          300,
          0,
          'OUTOFSTOCK',
          'C Category',
          'product3.jpg',
          3,
          'product3ref_3',
          3333333333,
          1731364892825,
          1731364892825
      );
