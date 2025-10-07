-- ========================================
-- CẬP NHẬT DAMAGE SKILL 9 LÊN 3000%
-- ========================================
-- Tác giả: AI Assistant
-- Ngày: 2025-10-06
-- Mô tả: Tăng damage skill 9 (cấp 10) từ 1000% lên 3000%
--        Giảm cooldown xuống còn 50s
-- ========================================

-- BACKUP TRƯỚC KHI CHỈNH (KHUYẾN NGHỊ)
CREATE TABLE IF NOT EXISTS skill_template_backup_20251006 AS SELECT * FROM skill_template WHERE id IN (24, 25, 26);

-- ========================================
-- SKILL 24: SUPER KAMEJOKO (TRÁI ĐẤT)
-- ========================================
UPDATE `skill_template`
SET `skills` = '[\"{\\\"power_require\\\":60000000000,\\\"damage\\\":700,\\\"dx\\\":190,\\\"dy\\\":25,\\\"price\\\":9999,\\\"max_fight\\\":1,\\\"mana_use\\\":80,\\\"cool_down\\\":150000,\\\"id\\\":156,\\\"point\\\":1,\\\"info\\\":\\\"Chưởng 1\\\"}\",\"{\\\"power_require\\\":60000000000,\\\"damage\\\":900,\\\"dx\\\":200,\\\"dy\\\":30,\\\"price\\\":9999,\\\"max_fight\\\":1,\\\"mana_use\\\":75,\\\"cool_down\\\":130000,\\\"id\\\":157,\\\"point\\\":2,\\\"info\\\":\\\"Chưởng 2\\\"}\",\"{\\\"power_require\\\":60000000000,\\\"damage\\\":1100,\\\"dx\\\":210,\\\"dy\\\":35,\\\"price\\\":9999,\\\"max_fight\\\":1,\\\"mana_use\\\":70,\\\"cool_down\\\":120000,\\\"id\\\":158,\\\"point\\\":3,\\\"info\\\":\\\"Chưởng 3\\\"}\",\"{\\\"power_require\\\":60000000000,\\\"damage\\\":1300,\\\"dx\\\":230,\\\"dy\\\":40,\\\"price\\\":9999,\\\"max_fight\\\":1,\\\"mana_use\\\":65,\\\"cool_down\\\":110000,\\\"id\\\":159,\\\"point\\\":4,\\\"info\\\":\\\"Chưởng 4\\\"}\",\"{\\\"power_require\\\":60000000000,\\\"damage\\\":1600,\\\"dx\\\":250,\\\"dy\\\":45,\\\"price\\\":9999,\\\"max_fight\\\":1,\\\"mana_use\\\":60,\\\"cool_down\\\":100000,\\\"id\\\":160,\\\"point\\\":5,\\\"info\\\":\\\"Chưởng 5\\\"}\",\"{\\\"power_require\\\":60000000000,\\\"damage\\\":1900,\\\"dx\\\":270,\\\"dy\\\":50,\\\"price\\\":9999,\\\"max_fight\\\":1,\\\"mana_use\\\":55,\\\"cool_down\\\":90000,\\\"id\\\":161,\\\"point\\\":6,\\\"info\\\":\\\"Chưởng 6\\\"}\",\"{\\\"power_require\\\":60000000000,\\\"damage\\\":2200,\\\"dx\\\":290,\\\"dy\\\":55,\\\"price\\\":9999,\\\"max_fight\\\":1,\\\"mana_use\\\":50,\\\"cool_down\\\":80000,\\\"id\\\":162,\\\"point\\\":7,\\\"info\\\":\\\"Chưởng 7\\\"}\",\"{\\\"power_require\\\":60000000000,\\\"damage\\\":2500,\\\"dx\\\":310,\\\"dy\\\":60,\\\"price\\\":9999,\\\"max_fight\\\":1,\\\"mana_use\\\":45,\\\"cool_down\\\":70000,\\\"id\\\":163,\\\"point\\\":8,\\\"info\\\":\\\"Chưởng 8\\\"}\",\"{\\\"power_require\\\":60000000000,\\\"damage\\\":2700,\\\"dx\\\":330,\\\"dy\\\":65,\\\"price\\\":9999,\\\"max_fight\\\":1,\\\"mana_use\\\":40,\\\"cool_down\\\":60000,\\\"id\\\":164,\\\"point\\\":9,\\\"info\\\":\\\"Chưởng 9\\\"}\",\"{\\\"power_require\\\":60000000000,\\\"damage\\\":3000,\\\"dx\\\":350,\\\"dy\\\":70,\\\"price\\\":9999,\\\"max_fight\\\":1,\\\"mana_use\\\":35,\\\"cool_down\\\":50000,\\\"id\\\":165,\\\"point\\\":10,\\\"info\\\":\\\"Chưởng 10\\\"}\"]'
WHERE nclass_id = 0 AND id = 24;

-- ========================================
-- SKILL 25: CA ĐÍC LIÊN HOÀN CHƯỞNG (XAYDA)
-- ========================================
UPDATE `skill_template`
SET `skills` = '[\"{\\\"power_require\\\":60000000000,\\\"damage\\\":700,\\\"dx\\\":120,\\\"dy\\\":120,\\\"price\\\":9999,\\\"max_fight\\\":1,\\\"mana_use\\\":80,\\\"cool_down\\\":150000,\\\"id\\\":176,\\\"point\\\":1,\\\"info\\\":\\\"Chưởng 1\\\"}\",\"{\\\"power_require\\\":60000000000,\\\"damage\\\":900,\\\"dx\\\":130,\\\"dy\\\":130,\\\"price\\\":9999,\\\"max_fight\\\":1,\\\"mana_use\\\":75,\\\"cool_down\\\":130000,\\\"id\\\":177,\\\"point\\\":2,\\\"info\\\":\\\"Chưởng 2\\\"}\",\"{\\\"power_require\\\":60000000000,\\\"damage\\\":1100,\\\"dx\\\":140,\\\"dy\\\":140,\\\"price\\\":9999,\\\"max_fight\\\":1,\\\"mana_use\\\":70,\\\"cool_down\\\":120000,\\\"id\\\":178,\\\"point\\\":3,\\\"info\\\":\\\"Chưởng 3\\\"}\",\"{\\\"power_require\\\":60000000000,\\\"damage\\\":1300,\\\"dx\\\":150,\\\"dy\\\":150,\\\"price\\\":9999,\\\"max_fight\\\":1,\\\"mana_use\\\":65,\\\"cool_down\\\":110000,\\\"id\\\":179,\\\"point\\\":4,\\\"info\\\":\\\"Chưởng 4\\\"}\",\"{\\\"power_require\\\":60000000000,\\\"damage\\\":1600,\\\"dx\\\":160,\\\"dy\\\":160,\\\"price\\\":9999,\\\"max_fight\\\":1,\\\"mana_use\\\":60,\\\"cool_down\\\":100000,\\\"id\\\":180,\\\"point\\\":5,\\\"info\\\":\\\"Chưởng 5\\\"}\",\"{\\\"power_require\\\":60000000000,\\\"damage\\\":1900,\\\"dx\\\":170,\\\"dy\\\":170,\\\"price\\\":9999,\\\"max_fight\\\":1,\\\"mana_use\\\":55,\\\"cool_down\\\":90000,\\\"id\\\":181,\\\"point\\\":6,\\\"info\\\":\\\"Chưởng 6\\\"}\",\"{\\\"power_require\\\":60000000000,\\\"damage\\\":2200,\\\"dx\\\":180,\\\"dy\\\":180,\\\"price\\\":9999,\\\"max_fight\\\":1,\\\"mana_use\\\":50,\\\"cool_down\\\":80000,\\\"id\\\":182,\\\"point\\\":7,\\\"info\\\":\\\"Chưởng 7\\\"}\",\"{\\\"power_require\\\":60000000000,\\\"damage\\\":2500,\\\"dx\\\":190,\\\"dy\\\":190,\\\"price\\\":9999,\\\"max_fight\\\":1,\\\"mana_use\\\":45,\\\"cool_down\\\":70000,\\\"id\\\":183,\\\"point\\\":8,\\\"info\\\":\\\"Chưởng 8\\\"}\",\"{\\\"power_require\\\":60000000000,\\\"damage\\\":2700,\\\"dx\\\":200,\\\"dy\\\":200,\\\"price\\\":9999,\\\"max_fight\\\":1,\\\"mana_use\\\":40,\\\"cool_down\\\":60000,\\\"id\\\":184,\\\"point\\\":9,\\\"info\\\":\\\"Chưởng 9\\\"}\",\"{\\\"power_require\\\":60000000000,\\\"damage\\\":3000,\\\"dx\\\":210,\\\"dy\\\":210,\\\"price\\\":9999,\\\"max_fight\\\":1,\\\"mana_use\\\":35,\\\"cool_down\\\":50000,\\\"id\\\":185,\\\"point\\\":10,\\\"info\\\":\\\"Chưởng 10\\\"}\"]'
WHERE nclass_id = 2 AND id = 25;

-- ========================================
-- SKILL 26: MA PHONG BA (NAMEC)
-- ========================================
UPDATE `skill_template`
SET `skills` = '[\"{\\\"power_require\\\":60000000000,\\\"damage\\\":700,\\\"dx\\\":83,\\\"dy\\\":83,\\\"price\\\":9999,\\\"max_fight\\\":1,\\\"mana_use\\\":80,\\\"cool_down\\\":150000,\\\"id\\\":166,\\\"point\\\":1,\\\"info\\\":\\\"Chưởng 1\\\"}\",\"{\\\"power_require\\\":60000000000,\\\"damage\\\":900,\\\"dx\\\":95,\\\"dy\\\":95,\\\"price\\\":9999,\\\"max_fight\\\":1,\\\"mana_use\\\":75,\\\"cool_down\\\":130000,\\\"id\\\":167,\\\"point\\\":2,\\\"info\\\":\\\"Chưởng 2\\\"}\",\"{\\\"power_require\\\":60000000000,\\\"damage\\\":1100,\\\"dx\\\":107,\\\"dy\\\":107,\\\"price\\\":9999,\\\"max_fight\\\":1,\\\"mana_use\\\":70,\\\"cool_down\\\":120000,\\\"id\\\":168,\\\"point\\\":3,\\\"info\\\":\\\"Chưởng 3\\\"}\",\"{\\\"power_require\\\":60000000000,\\\"damage\\\":1300,\\\"dx\\\":119,\\\"dy\\\":119,\\\"price\\\":9999,\\\"max_fight\\\":1,\\\"mana_use\\\":65,\\\"cool_down\\\":110000,\\\"id\\\":169,\\\"point\\\":4,\\\"info\\\":\\\"Chưởng 4\\\"}\",\"{\\\"power_require\\\":60000000000,\\\"damage\\\":1600,\\\"dx\\\":130,\\\"dy\\\":130,\\\"price\\\":9999,\\\"max_fight\\\":1,\\\"mana_use\\\":60,\\\"cool_down\\\":100000,\\\"id\\\":170,\\\"point\\\":5,\\\"info\\\":\\\"Chưởng 5\\\"}\",\"{\\\"power_require\\\":60000000000,\\\"damage\\\":1900,\\\"dx\\\":142,\\\"dy\\\":142,\\\"price\\\":9999,\\\"max_fight\\\":1,\\\"mana_use\\\":55,\\\"cool_down\\\":90000,\\\"id\\\":171,\\\"point\\\":6,\\\"info\\\":\\\"Chưởng 6\\\"}\",\"{\\\"power_require\\\":60000000000,\\\"damage\\\":2200,\\\"dx\\\":154,\\\"dy\\\":154,\\\"price\\\":9999,\\\"max_fight\\\":1,\\\"mana_use\\\":50,\\\"cool_down\\\":80000,\\\"id\\\":172,\\\"point\\\":7,\\\"info\\\":\\\"Chưởng 7\\\"}\",\"{\\\"power_require\\\":60000000000,\\\"damage\\\":2500,\\\"dx\\\":165,\\\"dy\\\":165,\\\"price\\\":9999,\\\"max_fight\\\":1,\\\"mana_use\\\":45,\\\"cool_down\\\":70000,\\\"id\\\":173,\\\"point\\\":8,\\\"info\\\":\\\"Chưởng 8\\\"}\",\"{\\\"power_require\\\":60000000000,\\\"damage\\\":2700,\\\"dx\\\":177,\\\"dy\\\":177,\\\"price\\\":9999,\\\"max_fight\\\":1,\\\"mana_use\\\":40,\\\"cool_down\\\":60000,\\\"id\\\":174,\\\"point\\\":9,\\\"info\\\":\\\"Chưởng 9\\\"}\",\"{\\\"power_require\\\":60000000000,\\\"damage\\\":3000,\\\"dx\\\":188,\\\"dy\\\":188,\\\"price\\\":9999,\\\"max_fight\\\":1,\\\"mana_use\\\":35,\\\"cool_down\\\":50000,\\\"id\\\":175,\\\"point\\\":10,\\\"info\\\":\\\"Chưởng 10\\\"}\"]'
WHERE nclass_id = 1 AND id = 26;

-- ========================================
-- KIỂM TRA KẾT QUẢ
-- ========================================
SELECT 
    CONCAT('Skill ', id, ' - ', name) AS skill_name,
    nclass_id,
    CASE 
        WHEN nclass_id = 0 THEN 'Trái Đất'
        WHEN nclass_id = 1 THEN 'Namec'
        WHEN nclass_id = 2 THEN 'Xayda'
    END AS planet,
    SUBSTRING(skills, 1, 100) AS skills_preview
FROM skill_template
WHERE id IN (24, 25, 26)
ORDER BY id, nclass_id;

-- ========================================
-- HOÀN TẤT!
-- ========================================
-- Bây giờ:
-- 1. Restart server
-- 2. Vào game, BÁN skill 9 cũ
-- 3. MUA lại skill 9 mới (hoặc tạo char mới)
-- 4. Test damage
--
-- Nếu muốn REVERT (khôi phục):
-- DELETE FROM skill_template WHERE id IN (24,25,26);
-- INSERT INTO skill_template SELECT * FROM skill_template_backup_20251006;
-- ========================================
