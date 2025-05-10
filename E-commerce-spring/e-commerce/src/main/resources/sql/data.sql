INSERT INTO Category (name, description, logo_url, active, parent_id, created_at, modified_at, slug)
VALUES
    ('Electronics', 'All electronic items and gadgets', '/images/electronics.png', TRUE, NULL, '2024-03-12 10:00:00', NULL, 'electronics'),
    ('Smartphones', 'Latest smartphones from top brands', '/images/smartphones.png', TRUE, 1, '2024-03-12 10:05:00', NULL, 'smartphones'),
    ('Laptops', 'All types of laptops including gaming and business laptops', '/images/laptops.png', TRUE, 1, '2024-03-12 10:10:00', NULL, 'laptops'),
    ('Fashion', 'Clothing, shoes, and accessories', '/images/fashion.png', TRUE, NULL, '2024-03-12 10:15:00', NULL, 'fashion');


INSERT INTO Brand (name, description, logo_url, slug, active, created_at, modified_at)
VALUES
    ('Nike', 'Global brand for sportswear and athletic gear.', 'https://example.com/logos/nike.png', 'nike', true, NOW(), NOW()),
    ('Adidas', 'German multinational corporation that designs and manufactures shoes, clothing and accessories.', 'https://example.com/logos/adidas.png', 'adidas', true, NOW(), NOW()),
    ('Puma', 'Sportswear brand known for performance and style.', 'https://example.com/logos/puma.png', 'puma', true, NOW(), NOW()),
    ('Reebok', 'Athletic footwear and apparel company.', 'https://example.com/logos/reebok.png', 'reebok', false, NOW(), NULL),
    ('Under Armour', 'American sports equipment company.', 'https://example.com/logos/under-armour.png', 'under-armour', true, NOW(), NOW());