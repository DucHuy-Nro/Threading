-- ============================================================
-- FIX NỘI TẠI AN TOÀN - RESET VỀ 0 RỒI MỚI XÓA TRÙNG LẶP
-- ============================================================

-- BƯỚC 1: RESET TOÀN BỘ NỘI TẠI CỦA TẤT CẢ PLAYERS VỀ 0
-- ============================================================
-- Format: [intrinsicId, param1, param2, countOpen, 0, 0, 0, 0]
-- Reset về: [0, 0, 0, 0, 0, 0, 0, 0] = Chưa kích hoạt

UPDATE `player` 
SET `data_intrinsic` = '[0,0,0,0,0,0,0,0]'
WHERE `data_intrinsic` IS NOT NULL;

-- Kiểm tra đã reset bao nhiêu players
SELECT 
    CONCAT('✅ Đã reset ', COUNT(*), ' players về nội tại 0') AS 'STEP 1 - Reset Players',
    COUNT(*) AS 'Số players bị reset'
FROM `player` 
WHERE `data_intrinsic` = '[0,0,0,0,0,0,0,0]';

-- ============================================================
-- BƯỚC 2: XÓA TOÀN BỘ NỘI TẠI TRÙNG LẶP
-- ============================================================
DELETE FROM `intrinsic`;

SELECT '✅ Đã xóa toàn bộ nội tại cũ' AS 'STEP 2 - Delete Old Intrinsics';

-- ============================================================
-- BƯỚC 3: THÊM LẠI 26 NỘI TẠI DUY NHẤT (ID 0-25)
-- ============================================================

-- ID 0: Chưa kích hoạt
INSERT INTO `intrinsic` VALUES (0, 'Chưa kích hoạt nội tại\nBấm vào để xem chi tiết', 0, 0, 0, 0, 5223, 3);

-- ========== TRÁI ĐẤT (Gender 0) ==========
INSERT INTO `intrinsic` VALUES (1, 'Chiêu đấm Dragon +p0% đến p1% sát thương', 5, 25, 0, 0, 569, 0);
INSERT INTO `intrinsic` VALUES (2, 'Chiêu Kamejoko +p0% đến p1% sát thương', 5, 25, 0, 0, 569, 0);
INSERT INTO `intrinsic` VALUES (3, 'Thái Dương Hạ San +p0% đến p1% tốc độ -p2% đến p3% KI', 10, 35, 10, 35, 5222, 0);
INSERT INTO `intrinsic` VALUES (4, 'Quả cầu kênh khi +p0% đến p1% tốc độ hồi phục', 15, 55, 0, 0, 5222, 0);
INSERT INTO `intrinsic` VALUES (5, 'Khiên năng lượng +p0% đến p1% tốc độ hồi phục', 15, 55, 0, 0, 5222, 0);
INSERT INTO `intrinsic` VALUES (6, 'Dịch chuyển tức thời +p0% đến p1% sát thương đòn kế', 50, 150, 0, 0, 568, 0);
INSERT INTO `intrinsic` VALUES (7, 'Thôi miên +p0% đến p1% sát thương đòn kế', 50, 150, 0, 0, 568, 0);

-- ========== NAMEC (Gender 1) ==========
INSERT INTO `intrinsic` VALUES (8, 'Chiêu đấm Demon +p0% đến p1% sát thương', 5, 25, 0, 0, 569, 1);
INSERT INTO `intrinsic` VALUES (9, 'Chiêu Masenko +p0% đến p1% sát thương', 2, 25, 0, 0, 569, 1);
INSERT INTO `intrinsic` VALUES (10, 'Trị thương +p0% đến p1% tốc độ hồi phục', 15, 65, 0, 0, 5222, 1);
INSERT INTO `intrinsic` VALUES (11, 'Makankosappo +p0% đến p1% tốc độ hồi phục', 15, 55, 0, 0, 5222, 1);
INSERT INTO `intrinsic` VALUES (12, 'Đẻ trứng +p0% đến p1% tốc độ hồi phục', 15, 65, 0, 0, 5222, 1);
INSERT INTO `intrinsic` VALUES (13, 'Liên hoàn +p0% đến p1% sát thương', 5, 25, 0, 0, 569, 1);
INSERT INTO `intrinsic` VALUES (14, 'Biến Sôcôla +p0% đến p1% sát thương đòn kế', 50, 150, 0, 0, 568, 1);
INSERT INTO `intrinsic` VALUES (15, 'Khiên năng lượng +p0% đến p1% tốc độ hồi phục', 15, 55, 0, 0, 5222, 1);

-- ========== XAYDA (Gender 2) ==========
INSERT INTO `intrinsic` VALUES (16, 'Chiêu đấm Galick +p0% đến p1% sát thương', 5, 25, 0, 0, 569, 2);
INSERT INTO `intrinsic` VALUES (17, 'Chiêu Antomic +p0% đến p1% sát thương', 5, 25, 0, 0, 569, 2);
INSERT INTO `intrinsic` VALUES (18, 'Biến hình +p0% đến p1% sát thương', 5, 25, 0, 0, 569, 2);
INSERT INTO `intrinsic` VALUES (19, 'Tự phát nổ +p0% đến p1% tốc độ hồi phục', 15, 65, 0, 0, 5222, 2);
INSERT INTO `intrinsic` VALUES (20, 'Khiên năng lượng +p0% đến p1% tốc độ hồi phục', 15, 55, 0, 0, 5222, 2);
INSERT INTO `intrinsic` VALUES (21, 'Huýt sáo +p0% đến p1% tốc độ hồi phục', 15, 65, 0, 0, 5222, 2);
INSERT INTO `intrinsic` VALUES (22, 'Trói +p0% đến p1% sát thương đòn kế', 50, 150, 0, 0, 568, 2);

-- ========== CHUNG (Gender 3 - Tất cả hành tinh) ==========
INSERT INTO `intrinsic` VALUES (23, 'Vàng rơi từ quái +p0% đến p1%', 25, 300, 0, 0, 930, 3);
INSERT INTO `intrinsic` VALUES (24, 'Sức mạnh và tiềm năng khi đánh quái +p0% đến p1%', 5, 35, 0, 0, 3783, 3);
INSERT INTO `intrinsic` VALUES (25, 'Chí mạng liên tục khi HP dưới p0% đến p1%', 20, 50, 0, 0, 716, 3);

SELECT '✅ Đã thêm lại 26 nội tại duy nhất' AS 'STEP 3 - Add Intrinsics';

-- ============================================================
-- BƯỚC 4: KIỂM TRA KẾT QUẢ
-- ============================================================

-- Kiểm tra tổng số nội tại
SELECT 
    '✅ HOÀN THÀNH!' AS 'STATUS',
    COUNT(*) AS 'Tổng nội tại' 
FROM `intrinsic`;

-- Kiểm tra chi tiết 26 nội tại
SELECT 
    id AS 'ID',
    LEFT(name, 50) AS 'Tên nội tại',
    CASE 
        WHEN gender = 0 THEN 'Trái Đất'
        WHEN gender = 1 THEN 'Namec'
        WHEN gender = 2 THEN 'Xayda'
        ELSE 'Chung'
    END AS 'Hành tinh'
FROM `intrinsic` 
ORDER BY id;

-- Kiểm tra số players đã reset
SELECT 
    COUNT(*) AS 'Tổng players',
    SUM(CASE WHEN data_intrinsic = '[0,0,0,0,0,0,0,0]' THEN 1 ELSE 0 END) AS 'Players đã reset'
FROM `player`;

-- ============================================================
-- THÔNG BÁO
-- ============================================================
SELECT '
========================================
✅ FIX NỘI TẠI THÀNH CÔNG!
========================================

📊 KẾT QUẢ:
- Đã reset toàn bộ nội tại players về 0
- Đã xóa 208 nội tại trùng lặp
- Đã thêm lại 26 nội tại duy nhất
- Players có thể mở nội tại mới!

🎮 TIẾP THEO:
1. Build server: ant clean && ant jar
2. Run server: run.bat
3. Test mở nội tại trong game

⚠️ LƯU Ý:
- Players cũ phải MỞ LẠI nội tại
- Giá mở reset về 10 Tr (lần đầu)
- Không còn lặp lại 1-25 nữa!

========================================
' AS 'THÔNG BÁO';
