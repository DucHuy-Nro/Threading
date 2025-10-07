-- ===================================================
-- 🚀 CHẠY FILE SQL NÀY ĐỂ CÀI ĐẶT HOÀN CHỈNH
-- ===================================================
-- Copy toàn bộ file này → Paste vào Navicat → Run!
-- ===================================================

USE ngocrong;

-- ===================================================
-- 1. THÊM NPC ADMIN PANEL (ID 85)
-- ===================================================

-- Kiểm tra xem đã có chưa
SELECT * FROM npc_template WHERE id = 85;

-- Nếu chưa có, chạy lệnh này:
INSERT INTO `npc_template` VALUES (85, 'Admin Panel', 18, 19, 20, 349)
ON DUPLICATE KEY UPDATE NAME = 'Admin Panel';

-- Kiểm tra đã thêm thành công
SELECT id, NAME FROM npc_template WHERE id = 85;
-- Kết quả mong đợi: 85 | Admin Panel

-- ===================================================
-- 2. THÊM NPC VÀO MAP 5 (Đảo Kamê)
-- ===================================================

-- ⚠️ PHẦN NÀY PHẢI SỬA THỦ CÔNG TRONG NAVICAT! ⚠️

-- Bước 1: Xem data hiện tại
SELECT id, NAME, data FROM map_template WHERE id = 5;

-- Bước 2: Copy cột 'data', ví dụ:
-- [[39,984,408],[13,1068,408],[21,1205,408],[81,240,288],[54,1292,408],[80,1418,456]]

-- Bước 3: Thêm [85,500,300] vào cuối:
-- [[39,984,408],[13,1068,408],[21,1205,408],[81,240,288],[54,1292,408],[80,1418,456],[85,500,300]]
--                                                                                      ↑↑↑↑↑↑↑↑↑↑↑↑↑
--                                                                                  NPC Admin Panel

-- Bước 4: Paste lại vào cột 'data' trong Navicat

-- ===================================================
-- 3. SET ACCOUNT LÀM ADMIN
-- ===================================================

-- Xem tất cả account
SELECT id, username, is_admin FROM account;

-- Set account của bạn làm admin (THAY 'admin' BẰNG USERNAME CỦA BẠN!)
UPDATE account 
SET is_admin = 1 
WHERE username = 'admin';  -- ← THAY TÊN CỦA BẠN VÀO ĐÂY!

-- Kiểm tra
SELECT username, is_admin FROM account WHERE is_admin = 1;
-- Kết quả mong đợi: username của bạn | 1

-- ===================================================
-- 4. KIỂM TRA SHOP SGOHAN (Đã có từ trước)
-- ===================================================

-- Kiểm tra shop
SELECT * FROM shop WHERE id = 37;
-- Kết quả: 37 | 80 | SHOP_TUYET_KY | 0 ✅

-- Kiểm tra tab shop
SELECT * FROM tab_shop WHERE id = 64;
-- Kết quả: 64 | 37 | Shop<>Tuyệt Kỹ ✅

-- Kiểm tra item trong shop
SELECT * FROM item_shop WHERE tab_id = 64;
-- Kết quả: 1005 | 64 | 1343 | ... ✅

-- ===================================================
-- 5. KẾT QUẢ MONG ĐỢI
-- ===================================================

-- Tổng hợp kiểm tra:
SELECT 
    'NPC SGohan' AS component,
    CASE WHEN EXISTS(SELECT 1 FROM npc_template WHERE id = 80) THEN '✅ OK' ELSE '❌ Missing' END AS status
UNION ALL
SELECT 
    'NPC Admin Panel',
    CASE WHEN EXISTS(SELECT 1 FROM npc_template WHERE id = 85) THEN '✅ OK' ELSE '❌ Missing' END
UNION ALL
SELECT 
    'Shop Tuyệt Kỹ',
    CASE WHEN EXISTS(SELECT 1 FROM shop WHERE tag_name = 'SHOP_TUYET_KY') THEN '✅ OK' ELSE '❌ Missing' END
UNION ALL
SELECT 
    'Account Admin',
    CASE WHEN EXISTS(SELECT 1 FROM account WHERE is_admin = 1) THEN '✅ OK' ELSE '❌ Missing' END;

-- Nếu tất cả đều "✅ OK" → Hoàn hảo!

-- ===================================================
-- 🎉 XONG! BÂY GIỜ:
-- ===================================================
-- 1. RESTART SERVER
-- 2. LOGIN VỚI ACCOUNT ADMIN
-- 3. ĐI MAP 5 (Đảo Kamê)
-- 4. TÌM 2 NPC:
--    - SGohan tại (1418, 456) → Shop Tuyệt Kỹ
--    - Admin Panel tại (500, 300) → Quản trị server
-- 5. ENJOY! 🎮
-- ===================================================
