-- ============================================
-- FIX NỘI TẠI: XÓA TRÙNG LẶP, CHỈ GIỮ 26 ENTRIES
-- ============================================

-- Xóa toàn bộ nội tại cũ
DELETE FROM `intrinsic`;

-- Thêm lại 26 nội tại duy nhất (ID 0-25)

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

-- Hoàn thành!
SELECT '✅ ĐÃ XÓA TRÙNG LẶP! CHỈ CÒN 26 NỘI TẠI (ID 0-25)' AS Status;
SELECT COUNT(*) AS 'Tổng số nội tại' FROM `intrinsic`;
