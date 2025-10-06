-- ========================================
-- FIX: Thêm NPC Template ID = 111
-- ========================================

-- Bước 1: Kiểm tra NPC template ID 111 có tồn tại chưa
SELECT * FROM npc_template WHERE id = 111;

-- Bước 2: Nếu chưa có, thêm NPC template mới
-- Thay đổi các giá trị head, body, leg, avatar theo NPC bạn muốn
INSERT INTO `npc_template` VALUES (111, 'Tên NPC Của Bạn', 18, 19, 20, 349);

-- HOẶC nếu bạn muốn copy từ một NPC có sẵn, ví dụ Ông Gôhan (id=0):
INSERT INTO `npc_template` 
SELECT 111, 'Tên NPC Mới', head, body, leg, avatar 
FROM npc_template 
WHERE id = 0;

-- ========================================
-- LƯU Ý: Bạn cần điền đúng thông tin:
-- ========================================
-- - NAME: Tên NPC hiển thị
-- - head, body, leg: ID của sprite parts
-- - avatar: ID của avatar icon

-- ========================================
-- SAU KHI THÊM, RESTART SERVER!
-- ========================================
