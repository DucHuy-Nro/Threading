# 🎯 HƯỚNG DẪN: CHỈNH CHỈ SỐ SKILL 9

## 📍 **VỊ TRÍ CHỈNH:**

**File:** `sql/ngocrong.sql`  
**Table:** `skill_template`  
**Skills cần tìm:**
- **Skill ID 24:** Super Kamejoko (Trái Đất)
- **Skill ID 25:** Ca đíc liên hoàn chưởng (Xayda)  
- **Skill ID 26:** Ma Phong Ba (Namec)

---

## 📊 **CẤU TRÚC SKILL_TEMPLATE:**

```sql
INSERT INTO `skill_template` VALUES (
    nclass_id,      -- 0 (TD), 1 (NM), 2 (XD)
    id,             -- 24, 25, 26
    NAME,           -- Tên skill
    max_point,      -- 9 (max cấp)
    mana_use_type,  -- 1
    TYPE,           -- 4
    icon_id,        -- Icon
    dam_info,       -- "Tăng sức đánh: #%"
    slot,           -- 8
    skills          -- JSON Array [cấp 1, cấp 2, ..., cấp 10]
);
```

---

## 🔍 **TÌM SKILL TRONG SQL:**

### **Cách 1: Search trong Navicat**
```
1. Mở Navicat
2. Click vào table "skill_template"
3. Ctrl+F → Search
4. Tìm: "Super Kamejoko" hoặc "Cađíc liên hoàn" hoặc "Ma Phong Ba"
```

### **Cách 2: Search trong file SQL**
```
1. Mở file sql/ngocrong.sql
2. Ctrl+F → Search
3. Tìm: "skill_template` VALUES (0, 24,"  (Super Kame)
4. Tìm: "skill_template` VALUES (2, 25,"  (Cađíc LH)
5. Tìm: "skill_template` VALUES (1, 26,"  (Ma Phong Ba)
```

### **Cách 3: Dùng command**
```bash
grep "skill_template.*Super Kamejoko" sql/ngocrong.sql
grep "skill_template.*Cađíc liên hoàn" sql/ngocrong.sql
grep "skill_template.*Ma Phong Ba" sql/ngocrong.sql
```

---

## 📋 **CÁC CHỈ SỐ CÓ THỂ CHỈNH:**

### **Mỗi cấp skill có:**

```json
{
  "power_require": 60000000000,  // Sức mạnh yêu cầu
  "damage": 550,                 // % Damage (550 = 550%)
  "dx": 120,                     // Phạm vi X (range)
  "dy": 120,                     // Phạm vi Y (range)
  "price": 9999,                 // Giá học (không dùng)
  "max_fight": 1,                // Số mục tiêu tối đa
  "mana_use": 80,                // Mana tiêu hao
  "cool_down": 170000,           // Thời gian hồi chiêu (ms)
  "id": 176,                     // Skill point ID
  "point": 1,                    // Cấp skill
  "info": "Chưởng 1"             // Mô tả
}
```

---

## 💥 **VÍ DỤ: CA ĐÍC LIÊN HOÀN CHƯỞNG (ID 25)**

### **CHỈ SỐ HIỆN TẠI:**

| Cấp | Damage % | Range | Mana | Cooldown |
|-----|----------|-------|------|----------|
| 1 | 550% | 120x120 | 80 | 170s |
| 2 | 600% | 130x130 | 75 | 160s |
| 3 | 650% | 140x140 | 70 | 150s |
| 4 | 700% | 150x150 | 65 | 140s |
| 5 | 750% | 160x160 | 60 | 130s |
| 6 | 800% | 170x170 | 55 | 120s |
| 7 | 850% | 180x180 | 50 | 110s |
| 8 | 900% | 190x190 | 45 | 100s |
| 9 | 950% | 200x200 | 40 | 90s |
| 10 | 1000% | 210x210 | 35 | 80s |

---

## 🔧 **CÁCH CHỈNH:**

### **VD: Tăng damage cấp 1 từ 550% → 700%**

**Tìm dòng trong SQL:**
```sql
-- Skill ID 25 (Ca đíc LH chưởng)
INSERT INTO `skill_template` VALUES (2, 25, 'Cađíc liên hoàn chưởng', 9, 1, 4, 11193, 'Tăng sức đánh: #%', 8, '[\"{\"power_require\":60000000000,\"damage\":550,...
```

**Đổi:**
```json
"damage":550  →  "damage":700
```

**Kết quả:**
```json
{..., "damage":700, "cool_down":170000, "id":176, "point":1, ...}
```

### **VD: Giảm cooldown cấp 10 từ 80s → 30s**

**Tìm:**
```json
{"..., "cool_down":80000, "id":185, "point":10, ...}
```

**Đổi:**
```json
"cool_down":80000  →  "cool_down":30000
```

---

## 📝 **HƯỚNG DẪN CHI TIẾT:**

### **BƯỚC 1: BACKUP**
```sql
-- Backup table
CREATE TABLE skill_template_backup AS SELECT * FROM skill_template;
```

### **BƯỚC 2: TÌM & CHỈNH**

**Cách 1 - Navicat (DỄ NHẤT):**
```
1. Mở Navicat
2. Vào table "skill_template"
3. Tìm dòng: nclass_id = 2, id = 25 (Xayda - Cađíc LH)
4. Click vào cột "skills"
5. Click "..." để mở editor
6. Sửa JSON (dễ nhìn hơn)
7. Save
```

**Cách 2 - SQL Update:**
```sql
-- Update toàn bộ JSON
UPDATE skill_template 
SET skills = '[{"damage":700, ...}, {"damage":750, ...}, ...]'
WHERE nclass_id = 2 AND id = 25;
```

**Cách 3 - Sửa file SQL trực tiếp:**
```
1. Mở file sql/ngocrong.sql
2. Search: "Cađíc liên hoàn chưởng"
3. Tìm dòng INSERT INTO skill_template
4. Sửa JSON trong dấu ' '
5. Save
6. Import lại SQL (hoặc chỉ chạy dòng UPDATE)
```

### **BƯỚC 3: TEST**
```bash
# Restart server
run.bat

# Test trong game
- Học skill cấp 1
- Kiểm tra damage, cooldown
```

---

## 🎯 **JSON FORMAT SKILL 9:**

### **Super Kamejoko (ID 24) - Trái Đất:**
```json
[
  {"power_require":60000000000,"damage":550,"dx":190,"dy":25,"mana_use":80,"cool_down":170000,"id":156,"point":1},
  {"power_require":60000000000,"damage":600,"dx":200,"dy":30,"mana_use":75,"cool_down":160000,"id":157,"point":2},
  {"power_require":60000000000,"damage":650,"dx":210,"dy":35,"mana_use":70,"cool_down":150000,"id":158,"point":3},
  {"power_require":60000000000,"damage":700,"dx":230,"dy":40,"mana_use":65,"cool_down":140000,"id":159,"point":4},
  {"power_require":60000000000,"damage":750,"dx":250,"dy":45,"mana_use":60,"cool_down":130000,"id":160,"point":5},
  {"power_require":60000000000,"damage":800,"dx":270,"dy":50,"mana_use":55,"cool_down":120000,"id":161,"point":6},
  {"power_require":60000000000,"damage":850,"dx":290,"dy":55,"mana_use":50,"cool_down":110000,"id":162,"point":7},
  {"power_require":60000000000,"damage":900,"dx":310,"dy":60,"mana_use":45,"cool_down":100000,"id":163,"point":8},
  {"power_require":60000000000,"damage":950,"dx":330,"dy":65,"mana_use":40,"cool_down":90000,"id":164,"point":9},
  {"power_require":60000000000,"damage":1000,"dx":350,"dy":70,"mana_use":35,"cool_down":80000,"id":165,"point":10}
]
```

### **Ca đíc liên hoàn chưởng (ID 25) - Xayda:**
```json
[
  {"damage":550,"dx":120,"dy":120,"mana_use":80,"cool_down":170000,"point":1},
  {"damage":600,"dx":130,"dy":130,"mana_use":75,"cool_down":160000,"point":2},
  {"damage":650,"dx":140,"dy":140,"mana_use":70,"cool_down":150000,"point":3},
  {"damage":700,"dx":150,"dy":150,"mana_use":65,"cool_down":140000,"point":4},
  {"damage":750,"dx":160,"dy":160,"mana_use":60,"cool_down":130000,"point":5},
  {"damage":800,"dx":170,"dy":170,"mana_use":55,"cool_down":120000,"point":6},
  {"damage":850,"dx":180,"dy":180,"mana_use":50,"cool_down":110000,"point":7},
  {"damage":900,"dx":190,"dy":190,"mana_use":45,"cool_down":100000,"point":8},
  {"damage":950,"dx":200,"dy":200,"mana_use":40,"cool_down":90000,"point":9},
  {"damage":1000,"dx":210,"dy":210,"mana_use":35,"cool_down":80000,"point":10}
]
```

### **Ma Phong Ba (ID 26) - Namec:**
```json
[
  // Tương tự Super Kamejoko
]
```

---

## 🔢 **GIẢI THÍCH CÁC CHỈ SỐ:**

| Thuộc tính | Ý nghĩa | Đơn vị | Ví dụ |
|------------|---------|--------|-------|
| **damage** | % Sát thương | % | 550 = 550% |
| **cool_down** | Thời gian hồi | ms | 170000 = 170s |
| **mana_use** | MP tiêu hao | điểm | 80 |
| **dx, dy** | Phạm vi skill | pixel | 120x120 |
| **power_require** | Sức mạnh yêu cầu | số | 60 tỷ |

---

## 💡 **VÍ DỤ CHỈNH:**

### **VD 1: Tăng damage tất cả cấp +100%**
```json
// Cấp 1: 550 → 650
"damage":550  →  "damage":650

// Cấp 2: 600 → 700
"damage":600  →  "damage":700

// ... tương tự cho cấp 3-10
```

### **VD 2: Giảm cooldown xuống còn 1/2**
```json
// Cấp 1: 170s → 85s
"cool_down":170000  →  "cool_down":85000

// Cấp 2: 160s → 80s
"cool_down":160000  →  "cool_down":80000

// ... tương tự
```

### **VD 3: Giảm mana sử dụng**
```json
// Cấp 1: 80 → 50
"mana_use":80  →  "mana_use":50

// Cấp 2: 75 → 45
"mana_use":75  →  "mana_use":45
```

### **VD 4: Tăng range skill**
```json
// Cấp 1: 120x120 → 200x200
"dx":120,"dy":120  →  "dx":200,"dy":200
```

---

## ⚠️ **LƯU Ý:**

### **✅ AN TOÀN:**
- Chỉnh `damage` (% sát thương)
- Chỉnh `cool_down` (thời gian hồi chiêu)
- Chỉnh `mana_use` (MP tiêu hao)
- Chỉnh `dx`, `dy` (phạm vi)

### **⚠️ CHÚ Ý:**
- **KHÔNG** đổi `id` (176-185)
- **KHÔNG** đổi `point` (1-10)
- **GIỮ NGUYÊN** format JSON
- **CHECK CÚ PHÁP** JSON (dấu ngoặc, dấu phẩy)

### **❌ KHÔNG NÊN:**
- Đổi `power_require` quá thấp (dễ quá)
- Cooldown < 10s (spam skill)
- Damage > 5000% (quá imba)
- Range > 500 (khỏng nhìn thấy)

---

## 🚀 **THỰC HIỆN:**

### **Option 1: Navicat (GỢI Ý)**
```
1. Mở Navicat → table skill_template
2. Tìm skill ID 24/25/26
3. Click cột "skills" → Click "..."
4. Sửa JSON (có highlight syntax)
5. Save
6. Restart server
```

### **Option 2: SQL Command**
```sql
-- Tìm skill
SELECT nclass_id, id, name, skills 
FROM skill_template 
WHERE id IN (24, 25, 26);

-- Copy JSON ra
-- Sửa trong notepad
-- Update lại

UPDATE skill_template 
SET skills = '[...]'  -- Paste JSON đã sửa
WHERE nclass_id = 2 AND id = 25;
```

### **Option 3: Export → Edit → Import**
```
1. Export skill_template ra file
2. Sửa file bằng text editor
3. Delete dữ liệu cũ
4. Import file đã sửa
```

---

## 📊 **VÍ DỤ CHỈNH SKILL 25 (Ca đíc LH chưởng):**

### **Trước (Cấp 1):**
```json
{
  "damage":550,
  "cool_down":170000,
  "mana_use":80
}
```

### **Sau (Buff mạnh hơn):**
```json
{
  "damage":700,         // +150% damage
  "cool_down":100000,   // 170s → 100s
  "mana_use":50         // 80 → 50 mana
}
```

---

## 🎯 **CÔNG THỨC TÍNH DAMAGE:**

```java
// Công thức game
finalDamage = playerDame × damage% / 100

// Ví dụ:
playerDame = 10.000.000
damage% = 550

finalDamage = 10.000.000 × 550 / 100
            = 55.000.000

// Nếu đổi damage 550 → 700:
finalDamage = 10.000.000 × 700 / 100
            = 70.000.000  (+15 triệu)
```

---

## ⏱️ **CÔNG THỨC COOLDOWN:**

```
cool_down (ms) / 1000 = giây

170000 ms = 170 giây = 2 phút 50 giây
80000 ms = 80 giây = 1 phút 20 giây
30000 ms = 30 giây
```

---

## 🎁 **GỢI Ý BUFF:**

### **Cân bằng game:**
```json
// Cấp 1-3: Học đầu
"damage": 600-700 (thay vì 550-650)
"cool_down": 120000 (thay vì 170000)

// Cấp 4-7: Giữa
"damage": 800-900
"cool_down": 90000

// Cấp 8-10: Max
"damage": 1000-1200
"cool_down": 60000 (1 phút)
```

### **Imba mode (quá mạnh):**
```json
"damage": 1500-2000  // x15-20 sức đánh
"cool_down": 30000   // 30s hồi chiêu
"mana_use": 10       // gần như free
```

---

## 📞 **NẾU CẦN GIÚP:**

Bạn muốn:
- **A)** Tôi tạo SQL script chỉnh sẵn (bạn chỉ việc chạy)
- **B)** Bạn tự sửa (tôi đã hướng dẫn trên)
- **C)** Bạn cho tôi số liệu mới (tôi tạo SQL)

**VD:** "Tôi muốn damage 700-1500%, cooldown 60-30s"

---

## 🔥 **TÓM TẮT:**

✅ **Vị trí:** `sql/ngocrong.sql` → table `skill_template`  
✅ **Skill:** ID 24, 25, 26  
✅ **Format:** JSON array  
✅ **Chỉnh:** damage, cool_down, mana_use, dx, dy  
✅ **Tool:** Navicat hoặc SQL command  

**BẠN MUỐN TÔI TẠO SQL SCRIPT CHỈNH SẴN KHÔNG?** 🛠️