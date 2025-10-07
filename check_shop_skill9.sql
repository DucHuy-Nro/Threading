-- ============================================
-- KIỂM TRA SHOP SKILL 9
-- ============================================

-- 1. Kiểm tra shop SHOP_TUYET_KY có tồn tại không
SELECT id, tag_name, npc_id FROM shop WHERE tag_name = 'SHOP_TUYET_KY';

-- 2. Kiểm tra tab_shop của SHOP_TUYET_KY
SELECT ts.id, ts.tab_id, ts.shop_id, ts.tab_name 
FROM tab_shop ts
INNER JOIN shop s ON ts.shop_id = s.id
WHERE s.tag_name = 'SHOP_TUYET_KY';

-- 3. Kiểm tra items trong shop
SELECT 
    is2.id AS item_shop_id,
    is2.tab_id,
    is2.temp_id AS item_template_id,
    it.name AS item_name,
    it.type AS item_type,
    it.gender AS item_gender,
    is2.cost,
    is2.type_sell
FROM item_shop is2
INNER JOIN tab_shop ts ON is2.tab_id = ts.id
INNER JOIN shop s ON ts.shop_id = s.id
INNER JOIN item_template it ON is2.temp_id = it.id
WHERE s.tag_name = 'SHOP_TUYET_KY'
ORDER BY it.gender, is2.temp_id;

-- 4. Kiểm tra item_template ID 2001-2029 đã tồn tại chưa
SELECT COUNT(*) AS 'Số items mới (2001-2029)' 
FROM item_template 
WHERE id BETWEEN 2001 AND 2029;

-- 5. Xem chi tiết items nếu có
SELECT id, type, gender, name 
FROM item_template 
WHERE id IN (1044, 1211, 1212) OR id BETWEEN 2001 AND 2029
ORDER BY gender, id;
