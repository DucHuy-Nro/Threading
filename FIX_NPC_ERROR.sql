-- ============================================
-- FIX LỖI: Index 111 out of bounds for length 94
-- ============================================

-- BƯỚC 1: TÌM MAP ĐANG DÙNG NPC tempId = 111
-- Chạy câu lệnh này để tìm map nào có NPC với tempId = 111

SELECT id, NAME, data 
FROM map_template 
WHERE data LIKE '%[[111,%' 
   OR data LIKE '%,[111,%'
   OR data LIKE '%[111,%';

-- ============================================
-- BƯỚC 2: CHỌN MỘT TRONG CÁC CÁCH SỬA SAU
-- ============================================

-- --------------------------------------------
-- CÁCH 1: XÓA NPC KHỎI MAP 111 (nếu không cần)
-- --------------------------------------------

UPDATE map_template 
SET data = '[]' 
WHERE id = 111;

-- --------------------------------------------
-- CÁCH 2: THAY tempId = 111 → tempId hợp lệ
-- --------------------------------------------
-- Ví dụ: Thay tempId từ 111 → 7 (Bulma)
-- BẠN CẦN SỬA THỦ CÔNG TRONG NAVICAT
-- Vì cột 'data' là JSON phức tạp

-- Ví dụ cấu trúc:
-- SAI:  [[111,1,300,400]]
-- ĐÚNG: [[7,1,300,400]]

-- --------------------------------------------
-- CÁCH 3: THÊM NPC TEMPLATE MỚI ID = 111
-- --------------------------------------------

-- Kiểm tra xem ID 111 đã tồn tại chưa
SELECT * FROM npc_template WHERE id = 111;

-- Nếu chưa có, thêm mới:
INSERT INTO `npc_template` VALUES (
    111,                    -- id (NPC template ID)
    'NPC Tùy Chỉnh',       -- NAME (Thay tên NPC của bạn)
    18,                     -- head (ID sprite đầu - copy từ NPC khác)
    19,                     -- body (ID sprite thân - copy từ NPC khác)  
    20,                     -- leg (ID sprite chân - copy từ NPC khác)
    349                     -- avatar (ID icon - copy từ NPC khác)
);

-- HOẶC copy từ một NPC có sẵn (ví dụ Ông Gôhan - ID 0):
INSERT INTO `npc_template` 
SELECT 111, 'NPC Tùy Chỉnh', head, body, leg, avatar 
FROM npc_template 
WHERE id = 0;

-- ============================================
-- SAU KHI CHẠY SQL
-- ============================================
-- 1. Kiểm tra kết quả
-- 2. RESTART SERVER
-- 3. Kiểm tra log - không còn lỗi

-- ============================================
-- DANH SÁCH NPC TEMPLATE ID HỢP LỆ
-- ============================================

SELECT id, NAME, avatar 
FROM npc_template 
ORDER BY id;

-- ID hợp lệ: 0-84, 103-110
-- KHÔNG DÙNG: 85-102, 111+ (trừ khi bạn thêm mới)
