# ⚠️ VẤN ĐỀ: DAMAGE SKILL 9 KHÔNG THAY ĐỔI

## 🔍 **PHÂN TÍCH NGUYÊN NHÂN:**

### **1. DAMAGE ĐƯỢC LƯU Ở ĐÂU:**

```java
// File: Manager.java (line 431)
skill.damage = Short.parseShort(String.valueOf(dts.get("damage")));
```

**Kiểu dữ liệu:** `short`
- **Min:** -32,768
- **Max:** 32,767

### **2. CÔNG THỨC TÍNH DAMAGE:**

```java
// File: NPoint.java (line 1467-1475)
case Skill.SUPER_KAME:              // Super Kamejoko
    percentDameSkill = skillSelect.damage;
    break;
case Skill.LIEN_HOAN_CHUONG:        // Ca đíc LH chưởng
    percentDameSkill = skillSelect.damage;
    break;
case Skill.MA_PHONG_BA:             // Ma Phong Ba
    percentDameSkill = skillSelect.damage;
    break;

// Line 1535 - Công thức tính
dameAttack = dameAttack * percentDameSkill / 100;
```

### **CÔNG THỨC ĐẦY ĐỦ:**

```java
finalDamage = dame_player × (damage_skill% / 100)
```

**Ví dụ:**
```
dame_player = 10.000.000
damage_skill = 3000%

finalDamage = 10.000.000 × (3000 / 100)
            = 10.000.000 × 30
            = 300.000.000
```

---

## 🎯 **DAMAGE GỐC, DAMAGE TRUNG, DAMAGE ẢO LÀ GÌ?**

### **A. DAMAGE GỐC (Base Damage)**
```java
// NPoint.java
public int dame, dameg;
```
- `dame`: Damage hiện tại (sau khi cộng item)
- `dameg`: Damage gốc (từ chỉ số điểm)

**Nguồn gốc:**
- Từ điểm tiềm năng cộng vào
- Từ item options (option 0, 7, 14, 47)
- Từ set đồ

### **B. DAMAGE TRUNG BÌNH (Average Damage)**
```java
// NPoint.java (line 1563-1570)
long tempDameAttack = (long) (dameAttack / 100L * 5L);
dameAttack += (long) (Util.getOne(-1, 1) * Util.nextInt((int) tempDameAttack) + 1);
```

**Nghĩa là:** Damage dao động ±5%
```
Ví dụ: Damage = 100.000.000
Thực tế: 95.000.000 - 105.000.000
```

### **C. DAMAGE ẢO (Display Damage)**
- Là số damage hiển thị trên màn hình
- Có thể khác với damage thực tế
- Game client có thể format số theo kiểu riêng

---

## 🔧 **TẠI SAO ĐỔI DAMAGE KHÔNG THẤY THAY ĐỔI?**

### **NGUYÊN NHÂN 1: Chưa Restart Server**

**Vấn đề:**
```
1. Sửa SQL
2. Vào game luôn
3. Skill vẫn dùng data cũ trong RAM
```

**Giải pháp:**
```
1. Sửa SQL
2. RESTART SERVER (quan trọng!)
3. Vào game test
```

### **NGUYÊN NHÂN 2: Cache Skill Data**

Server load skill từ database **CHỈ 1 LẦN** khi khởi động:
```java
// Manager.java line 387
ps = ConnectionDatabase.prepareStatement("select * from skill_template ...");
```

**Nếu server đang chạy:**
- Sửa SQL → Không có tác dụng
- Phải restart để load lại

### **NGUYÊN NHÂN 3: Hiển Thị vs Thực Tế**

**Có thể bạn thấy:**
- Damage hiển thị: 50.000.000
- Damage thực tế: 300.000.000

**Nguyên nhân:**
- Game client format số
- Có giới hạn hiển thị
- Cần check combat log

---

## 🧪 **CÁCH TEST DAMAGE ĐÚNG:**

### **BƯỚC 1: CHECK SQL**

```sql
-- Chạy trong Navicat
SELECT 
    nclass_id,
    id,
    name,
    SUBSTRING_INDEX(SUBSTRING_INDEX(skills, '"damage":', -1), ',', 1) as damage_cap_10
FROM skill_template
WHERE id IN (24, 25, 26)
ORDER BY nclass_id;
```

**Kết quả mong đợi:**
```
nclass_id | id | name                         | damage_cap_10
0         | 24 | Super Kamejoko              | 3000
2         | 25 | Cađíc liên hoàn chưởng      | 3000
1         | 26 | Ma phong ba                 | 3000
```

### **BƯỚC 2: CHECK SERVER LOG**

**Khi khởi động server, xem:**
```
[INFO] Successfully loaded skill (3)
```

**Nếu thấy lỗi:**
```
[ERROR] Error loading skill template
```
→ JSON bị lỗi cú pháp

### **BƯỚC 3: TEST IN-GAME**

**Cách 1: Xem chỉ số**
```
1. Học skill 9 cấp 10
2. Vào menu kỹ năng
3. Click vào skill
4. Xem "Tăng sức đánh: X%"
   → Phải là 3000%
```

**Cách 2: Test damage thực tế**
```
1. Ghi lại dame_player của bạn
   (Menu → Thuộc tính → Sức đánh)
   
2. Đánh 1 con mob
   
3. Tính toán:
   finalDamage = dame_player × 30
   
4. So sánh với damage thực tế
```

---

## 🛠️ **GIẢI PHÁP:**

### **A. KIỂM TRA SQL ĐÃ SỬA ĐÚNG CHƯA**

**Mở file SQL, search:**
```
Cađíc liên hoàn chưởng
```

**Tìm dòng cấp 10 (point 10):**
```json
{"power_require":60000000000,"damage":3000,"dx":210,"dy":210,...,"point":10,...}
```

**CHECK:**
- ✅ `"damage":3000` → Đúng
- ❌ `"damage":1000` → Chưa sửa

### **B. RESTART SERVER ĐÚNG CÁCH**

```bash
# Cách 1: Dùng Admin Panel GUI
1. Click "Bảo trì ngay (5s)"
2. Đợi server tắt
3. Run lại run.bat

# Cách 2: Task Manager
1. Ctrl+Shift+Esc
2. Tìm "java.exe"
3. End Task
4. Run lại run.bat

# Cách 3: Command
taskkill /F /IM java.exe
run.bat
```

### **C. TEST LẠI**

```
1. Vào game
2. Học skill 9 (nếu chưa có)
3. Vào menu kỹ năng
4. Click vào skill → Xem %
5. Đánh thử 1 con mob
```

---

## 📊 **VÍ DỤ TÍNH DAMAGE:**

### **Giả sử:**
```
- Dame player: 10.000.000
- Skill damage%: 3000%
- Không crit
```

### **CÔNG THỨC:**

```java
// Bước 1: Tính damage skill
dameAttack = 10.000.000 * 3000 / 100
           = 300.000.000

// Bước 2: Cộng nội tại (giả sử +30%)
dameAttack = 300.000.000 + (300.000.000 * 30 / 100)
           = 390.000.000

// Bước 3: Random ±5%
tempDame = 390.000.000 * 5 / 100 = 19.500.000
dameAttack = 390.000.000 ± 19.500.000
           = 370.500.000 ~ 409.500.000

// Nếu crit (x2):
dameAttack = 390.000.000 * 2
           = 780.000.000
```

---

## ⚡ **GIỚI HẠN DAMAGE:**

### **1. GIỚI HẠN SHORT (Skill.damage)**

```java
skill.damage = Short.parseShort(...)
```

**Max: 32,767%**

→ Nếu bạn sửa `"damage":50000` sẽ bị lỗi!

### **2. GIỚI HẠN INT (finalDamage)**

```java
// NPoint.java line 1579-1582
if (dameAttack > 2_147_483_647) {
    dameAttack = 2_147_483_647;
}
return (int) dameAttack;
```

**Max: 2.147.483.647** (2.1 tỷ)

→ Dù tính được bao nhiêu, max vẫn là 2.1 tỷ

---

## 🎯 **KẾT LUẬN:**

### **DAMAGE KHÔNG THAY ĐỔI VÌ:**

1. ❌ **Chưa restart server**
   → Giải pháp: Restart!
   
2. ❌ **Sửa SQL sai cú pháp**
   → Giải pháp: Check JSON syntax
   
3. ❌ **Sửa nhầm cấp skill**
   → Giải pháp: Sửa đúng `"point":10`
   
4. ❌ **Nhầm giữa hiển thị vs thực tế**
   → Giải pháp: Test damage thực tế

### **DAMAGE CÓ THAY ĐỔI NHƯNG:**

- ✅ Cooldown thay đổi → Server đã load SQL mới
- ❓ Damage không đổi → Có thể do:
  - Damage player quá thấp (1000 × 30 = chỉ 30.000)
  - Mob có giáp cao
  - Client hiển thị sai

---

## 🔥 **HÀNH ĐỘNG NGAY:**

### **BẠN CẦN LÀM:**

```
1. KIỂM TRA SQL:
   - Mở Navicat
   - Table: skill_template
   - Tìm id = 25 (Cađíc LH)
   - Click cột "skills"
   - Click "..."
   - Tìm cấp 10: "point":10
   - Xem "damage": phải là 3000

2. RESTART SERVER:
   - Tắt hết java.exe
   - Run lại run.bat
   - Đợi thấy "Successfully loaded skill (3)"

3. TEST:
   - Vào game
   - Menu kỹ năng → Click skill 9
   - Phải thấy "Tăng sức đánh: 3000%"
   - Đánh thử mob
   
4. BÁO CÁO:
   - Dame player của bạn: ???
   - Damage skill hiển thị: ???
   - Damage thực tế đánh mob: ???
```

---

## 💡 **GỢI Ý:**

### **NẾU VẪN KHÔNG ĐỔI:**

Tôi sẽ tạo SQL script tự động:
```sql
-- Sửa toàn bộ 3 skill 9, cấp 10, damage 3000%
UPDATE skill_template 
SET skills = REPLACE(skills, 
    '"point":10,"info":"Chưởng 10"}"]', 
    '"damage":3000,"point":10,"info":"Chưởng 10"}"]'
)
WHERE id IN (24, 25, 26);
```

**Bạn muốn tôi tạo script không?**

---

## 📞 **TRẢ LỜI CÂU HỎI:**

### **Q: Damage gốc, damage trung, damage ảo là gì?**

**A:**
- **Damage gốc:** Dame player (from stats + items)
- **Damage trung:** Damage sau công thức (với skill %)
- **Damage ảo:** Damage hiển thị (có thể format)

### **Q: Tại sao cooldown đổi được mà damage không?**

**A:** 
- Cả 2 đều load từ SQL
- Nếu cooldown đổi → Server đã restart
- Damage không đổi → Có thể:
  - Sửa nhầm cấp skill khác
  - Check nhầm skill khác
  - Mob có giáp cao, damage bị giảm

### **Q: Làm sao biết damage có tăng thật không?**

**A:**
```
Công thức:
finalDamage = dame_player × (skill_damage% / 100)

Test:
1. Dame player: 10.000.000
2. Skill cũ 1000% → Damage: 100.000.000
3. Skill mới 3000% → Damage: 300.000.000

Nếu không thấy x3 damage → Chưa restart hoặc sửa sai
```

---

**BẠN CẦN TÔI:**
- A) Tạo SQL script sửa damage tự động
- B) Tạo tool check damage in-game
- C) Hướng dẫn debug chi tiết hơn

**BÁO CÁO CHO TÔI:** Bạn đã restart server chưa? Và skill hiển thị % bao nhiêu?