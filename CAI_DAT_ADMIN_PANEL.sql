-- ========================================================
-- CÀI ĐẶT ADMIN PANEL - CHẠY CÁC LỆNH SAU TRONG NAVICAT
-- ========================================================

-- ========================================================
-- BƯỚC 1: THÊM NPC TEMPLATE
-- ========================================================

-- Kiểm tra NPC ID 85 có tồn tại chưa
SELECT * FROM npc_template WHERE id = 85;

-- Nếu chưa có, thêm mới:
INSERT INTO `npc_template` VALUES (
    85,                     -- id (NPC ID)
    'Admin Panel',          -- NAME (Tên hiển thị)
    18,                     -- head (sprite đầu)
    19,                     -- body (sprite thân)
    20,                     -- leg (sprite chân)
    349                     -- avatar (icon)
);

-- Kiểm tra đã thêm thành công chưa:
SELECT * FROM npc_template WHERE id = 85;
-- Kết quả: Phải có 1 dòng với id=85

-- ========================================================
-- BƯỚC 2: THÊM NPC VÀO MAP
-- ========================================================

-- Option A: Đặt ở Map 5 (Đảo Kamê) - Tọa độ (500, 300)

-- Xem data hiện tại của map 5:
SELECT id, NAME, data FROM map_template WHERE id = 5;

-- Kết quả VD: 
-- data = [[39,984,408],[13,1068,408],[21,1205,408]]

-- ĐỂ THÊM NPC:
-- 1. Copy chuỗi JSON trên
-- 2. Thêm [85,500,300] vào cuối
-- 3. Kết quả: [[39,984,408],[13,1068,408],[21,1205,408],[85,500,300]]
-- 4. Paste vào ô 'data' của map 5 trong Navicat

-- (Không thể dùng UPDATE vì cấu trúc JSON phức tạp, phải sửa thủ công)

-- ========================================================
-- BƯỚC 3: SET ADMIN CHO ACCOUNT
-- ========================================================

-- Kiểm tra account hiện tại:
SELECT id, username, is_admin FROM account;

-- Set admin cho account của bạn:
UPDATE account 
SET is_admin = 1 
WHERE username = 'your_username_here';
-- ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
-- THAY 'your_username_here' bằng username thật của bạn!

-- VD:
-- UPDATE account SET is_admin = 1 WHERE username = 'admin';
-- UPDATE account SET is_admin = 1 WHERE username = 'blackgoku';

-- Kiểm tra đã set thành công:
SELECT username, is_admin FROM account WHERE username = 'your_username_here';
-- Kết quả: is_admin phải = 1

-- ========================================================
-- BƯỚC 4: KIỂM TRA DỮ LIỆU
-- ========================================================

-- Kiểm tra tổng hợp:
SELECT 
    'NPC Template' AS type,
    CAST(id AS CHAR) AS id,
    NAME AS name
FROM npc_template 
WHERE id = 85

UNION ALL

SELECT 
    'Admin Account' AS type,
    CAST(id AS CHAR) AS id,
    username AS name
FROM account 
WHERE is_admin = 1;

-- Kết quả phải có:
-- NPC Template | 85 | Admin Panel
-- Admin Account | X | your_username

-- ========================================================
-- THÊM NPC VÀO MAP KHÁC (OPTIONAL)
-- ========================================================

-- Nếu muốn đặt NPC ở map khác:

-- Map 48 (Nhà Kaio - Map admin thường dùng):
-- 1. Mở Navicat → map_template → id = 48
-- 2. Sửa cột 'data'
-- 3. Thêm: [85, 600, 400]

-- Map 0 (Làng Aru - Map đầu tiên):
-- Thêm: [85, 300, 200]

-- Map 42 (Thần điện):
-- Thêm: [85, 700, 300]

-- ========================================================
-- XÓA NPC (NẾU CẦN)
-- ========================================================

-- Xóa NPC khỏi database:
DELETE FROM npc_template WHERE id = 85;

-- Nhớ xóa khỏi map_template (sửa cột data thủ công)

-- ========================================================
-- LƯU Ý QUAN TRỌNG:
-- ========================================================

-- 1. SAU KHI CHẠY SQL → PHẢI RESTART SERVER!
-- 2. SAU KHI SET ADMIN → PHẢI LOGOUT & LOGIN LẠI!
-- 3. NPC ID 85 có thể đổi thành ID khác (86-94) nếu 85 đã dùng
-- 4. Kiểm tra is_admin = 1 trước khi test!

-- ========================================================
-- KIỂM TRA NHANH:
-- ========================================================

-- Tất cả trong 1 query:
SELECT 'Step 1: NPC Template' AS check_item, 
       CASE WHEN COUNT(*) > 0 THEN '✅ OK' ELSE '❌ CHƯA CÓ' END AS status
FROM npc_template WHERE id = 85

UNION ALL

SELECT 'Step 2: Admin Account' AS check_item,
       CASE WHEN COUNT(*) > 0 THEN '✅ OK' ELSE '❌ CHƯA CÓ' END AS status
FROM account WHERE is_admin = 1;

-- Kết quả phải cả 2 đều ✅ OK
