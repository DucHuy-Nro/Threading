# 🔧 FIX: SKILL 9 DAMAGE KHÔNG HOẠT ĐỘNG

## ❌ **VẤN ĐỀ:**
- Chỉnh cooldown trong SQL → Hoạt động ✅
- Chỉnh damage trong SQL → KHÔNG hoạt động ❌

---

## 🔍 **NGUYÊN NHÂN:**

File `src/nro/models/player/NPoint.java` thiếu code xử lý damage cho 3 skill:
- **Skill 24:** Super Kamejoko (Trái Đất)
- **Skill 25:** Ca đíc liên hoàn chưởng (Xayda)
- **Skill 26:** Ma Phong Ba (Namec)

**Cụ thể:**
```java
// Trong method getDameAttack()
switch (skillSelect.template.id) {
    case Skill.DRAGON:      // ✅ Có
    case Skill.KAMEJOKO:    // ✅ Có
    case Skill.LIEN_HOAN:   // ✅ Có
    // ...
    
    // ❌ THIẾU 3 SKILL NÀY:
    // case Skill.SUPER_KAME:
    // case Skill.LIEN_HOAN_CHUONG:
    // case Skill.MA_PHONG_BA:
}

// Vì KHÔNG có case
// → percentDameSkill = 0
// → Damage KHÔNG được tính từ SQL
```

---

## ✅ **ĐÃ SỬA:**

**File:** `src/nro/models/player/NPoint.java`  
**Line:** ~1465-1476

**Code đã thêm:**
```java
// ========== SKILL 9 (TUYỆT KỸ) ==========
case Skill.SUPER_KAME:              // Super Kamejoko (Trái Đất)
    percentDameSkill = skillSelect.damage;
    break;
case Skill.LIEN_HOAN_CHUONG:        // Ca đíc liên hoàn chưởng (Xayda)
    percentDameSkill = skillSelect.damage;
    break;
case Skill.MA_PHONG_BA:             // Ma Phong Ba (Namec)
    percentDameSkill = skillSelect.damage;
    break;
// ========================================
```

---

## 📋 **BÂY GIỜ LÀM GÌ:**

### **BƯỚC 1: BUILD**
```cmd
cd E:\Source NRO by me\Threading
ant clean jar
```

### **BƯỚC 2: RESTART SERVER**
```cmd
taskkill /F /IM java.exe
run.bat
```

### **BƯỚC 3: TEST**
```
1. Vào game
2. Dùng skill 9 (Ca đíc liên hoàn chưởng / Super Kame / Ma Phong Ba)
3. Kiểm tra damage
4. So sánh với damage cũ
```

---

## 🎯 **CÔNG THỨC DAMAGE SKILL 9:**

```java
// Công thức trong code
finalDamage = playerDame × skillDamage% / 100

// Ví dụ:
playerDame = 10.000.000
skillDamage = 3000% (như bạn đã chỉnh trong SQL)

finalDamage = 10.000.000 × 3000 / 100
            = 300.000.000 damage
```

---

## 💡 **VÍ DỤ SO SÁNH:**

### **Trước khi fix:**
```
Damage SQL: 3000%
Damage thực tế: 0 (vì percentDameSkill = 0)
```

### **Sau khi fix:**
```
Damage SQL: 3000%
Damage thực tế: playerDame × 30 = 300 triệu (nếu dame 10 triệu)
```

---

## 🔢 **KIỂM TRA DAMAGE TRONG SQL:**

### **Cách 1: Navicat**
```
1. Mở table skill_template
2. Tìm skill ID 24, 25, 26
3. Xem cột "skills" → point 10
4. Check giá trị "damage"
```

### **Cách 2: SQL Query**
```sql
-- Kiểm tra Super Kamejoko (ID 24)
SELECT name, skills 
FROM skill_template 
WHERE id = 24 AND nclass_id = 0;

-- Kiểm tra Ca đíc LH chưởng (ID 25)
SELECT name, skills 
FROM skill_template 
WHERE id = 25 AND nclass_id = 2;

-- Kiểm tra Ma Phong Ba (ID 26)
SELECT name, skills 
FROM skill_template 
WHERE id = 26 AND nclass_id = 1;
```

---

## ⚙️ **NẾU VẪN KHÔNG HOẠT ĐỘNG:**

### **Check 1: Đã build chưa?**
```cmd
ant clean jar
```

### **Check 2: Đã restart server chưa?**
```cmd
taskkill /F /IM java.exe
run.bat
```

### **Check 3: Xóa cache client**
```
Xóa folder cache trong client NRO
```

### **Check 4: Kiểm tra console log**
```
Xem console có báo lỗi gì không
```

---

## 🎁 **BONUS: BUFF SKILL 9**

### **Nếu muốn buff skill 9 mạnh hơn:**

**Option 1: Tăng damage + Giảm cooldown**
```json
// Cấp 10
{
  "damage": 3000,        // 3000% = x30 sức đánh
  "cool_down": 30000,    // 30 giây
  "mana_use": 20         // Ít mana
}
```

**Option 2: Thêm buff từ set đồ** (nếu muốn)
```java
// Trong NPoint.java, thêm vào case Skill.LIEN_HOAN_CHUONG:
if (this.player.setClothes.somang == 5) {  // Ví dụ set sô mảng
    percentXDame = 50;  // +50% damage
}
```

---

## 📊 **KẾT QUẢ SAU KHI FIX:**

| Trước Fix | Sau Fix |
|-----------|---------|
| Cooldown: ✅ Hoạt động | Cooldown: ✅ Hoạt động |
| Damage: ❌ KHÔNG hoạt động | Damage: ✅ HOẠT ĐỘNG |
| Range: ✅ Hoạt động | Range: ✅ Hoạt động |
| Mana: ✅ Hoạt động | Mana: ✅ Hoạt động |

---

## 🚀 **TÓM TẮT:**

✅ Đã thêm code xử lý damage cho skill 9  
✅ Giờ chỉnh damage trong SQL sẽ hoạt động  
✅ Công thức: `finalDamage = playerDame × damage% / 100`  
✅ Build → Restart → Test!  

**BẠN CÓ THỂ BUILD VÀ TEST NGAY BÂY GIỜ!** 🎮