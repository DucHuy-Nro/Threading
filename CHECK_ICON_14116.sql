-- ================================================
-- KIỂM TRA icon_spec = 14116
-- ================================================

-- Bước 1: Tìm item nào có icon_id = 14116
SELECT id, name, type, icon_id 
FROM item_template 
WHERE icon_id = 14116;

-- Nếu không có kết quả, tìm gần đó:
SELECT id, name, type, icon_id 
FROM item_template 
WHERE icon_id BETWEEN 14100 AND 14120
ORDER BY icon_id;

-- Bước 2: Xem các item đang dùng icon_spec = 14116
SELECT 
    s.tag_name AS shop_name,
    t.name AS tab_name,
    it.name AS item_name,
    it.id AS item_id,
    it.icon_id AS item_icon,
    sh.icon_spec
FROM item_shop sh
JOIN tab_shop t ON sh.tab_id = t.id
JOIN shop s ON t.shop_id = s.id
JOIN item_template it ON sh.temp_id = it.id
WHERE sh.icon_spec = 14116
LIMIT 10;

-- Bước 3: Kiểm tra item 1343 (Sách tuyệt kỹ) của bạn
SELECT id, name, type, icon_id 
FROM item_template 
WHERE id = 1343;
