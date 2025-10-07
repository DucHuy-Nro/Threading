# 🚀 NÂNG CẤP SERVER HỖ TRỢ LONG DAMAGE

## 📋 **CHECKLIST:**

Khi đã có client mod hỗ trợ long damage, cần sửa server:

### **1. Sửa kiểu dữ liệu dame**

```java
// File: src/nro/models/player/NPoint.java

// CŨ (line 69):
public int dame, dameg;

// MỚI:
public long dame, dameg;
```

### **2. Sửa kiểu dữ liệu dameAdd, dameAfter**

```java
// File: src/nro/models/player/NPoint.java

// CŨ (line 83):
public int hpAdd, mpAdd, dameAdd, defAdd, critAdd, hpHoiAdd, mpHoiAdd;

// MỚI:
public long hpAdd, mpAdd, dameAdd, defAdd, critAdd, hpHoiAdd, mpHoiAdd;

// CŨ (line 57):
public int dameAfter;

// MỚI:
public long dameAfter;
```

### **3. Sửa hàm getDameAttack**

```java
// File: src/nro/models/player/NPoint.java

// CŨ (line 1382):
public int getDameAttack(boolean isAttackMob) {
    int dameAttack = this.dame;
    // ... tính toán ...
    if (dameAttack > 2_147_483_647) {
        dameAttack = 2_147_483_647;  // ← XÓA DÒNG NÀY
    }
    return (int) dameAttack;
}

// MỚI:
public long getDameAttack(boolean isAttackMob) {
    long dameAttack = this.dame;  // Đổi int → long
    
    // ... giữ nguyên logic tính toán ...
    
    // XÓA check 2.147 tỷ:
    // if (dameAttack > 2_147_483_647) {
    //     dameAttack = 2_147_483_647;
    // }
    
    return dameAttack;  // Trả về long (không cần cast)
}
```

### **4. Sửa hàm tính dame gốc**

```java
// File: src/nro/models/player/NPoint.java
// Tìm hàm setDame (line ~1072)

// CŨ:
long dame = this.dameg + this.dameAdd;
// ...
if (dame > 2_147_483_647) {
    dame = 2_147_483_647;  // ← XÓA DÒNG NÀY
}
this.dame = (int) dame;

// MỚI:
long dame = this.dameg + this.dameAdd;
// ... tính toán tlDame ...
if (dame < 0) {
    dame = 0;
}
this.dame = dame;  // Không cần cast (int), vì dame đã là long
```

### **5. Sửa Skill damage%**

```java
// File: src/nro/models/skill/Skill.java

// CŨ (line 66):
public short damage;  // MAX 32,767

// MỚI:
public int damage;    // MAX 2.147 tỷ (đủ cho skill%)
```

### **6. Sửa load skill từ SQL**

```java
// File: src/nro/models/server/Manager.java
// Tìm hàm loadSkillTemplate (line ~431)

// CŨ:
skill.damage = Short.parseShort(String.valueOf(dts.get("damage")));

// MỚI:
skill.damage = Integer.parseInt(String.valueOf(dts.get("damage")));
```

### **7. Sửa các hàm injured (Mob, Player, Boss)**

**A. Mob.java:**

```java
// File: src/nro/models/mob/Mob.java
// Line ~132

// CŨ:
public void injured(Player plAtt, long damage, boolean dieWhenHpFull) {
    // ...
    if (damage > 2_147_483_647) {
        damage = 2_147_483_647;  // ← XÓA
    }
    // ...
}

// MỚI:
public void injured(Player plAtt, long damage, boolean dieWhenHpFull) {
    // ... giữ nguyên logic ...
    // XÓA check 2.147 tỷ
    
    this.point.hp -= damage;  // Vẫn dùng long
    // ...
}
```

**B. Player.java:**

```java
// File: src/nro/models/player/Player.java
// Line ~1195

// CŨ:
damage = Math.min(damage, 2_147_483_647);  // ← XÓA

// MỚI:
// Bỏ hẳn dòng này, để damage không bị giới hạn
```

**C. Các Boss:**

Tương tự, tìm các file Boss và xóa dòng giới hạn 2.147 tỷ:
- `Boss.java`
- `BossesData.java`
- Các boss event (Halloween, Noel, etc.)

### **8. Sửa Database Schema (Tùy chọn)**

```sql
-- Nếu lưu dame vào database
ALTER TABLE player 
MODIFY COLUMN dame BIGINT DEFAULT 0;

ALTER TABLE player 
MODIFY COLUMN dameg BIGINT DEFAULT 0;
```

---

## 🔍 **TÌM VÀ THAY THẾ:**

### **Dùng Search & Replace trong IDE:**

```
Tìm: public int dame
Thay: public long dame

Tìm: (int) dameAttack
Thay: dameAttack

Tìm: 2_147_483_647
Thay: (xóa dòng đó)

Tìm: Short.parseShort
Thay: Integer.parseInt (nếu là skill.damage)
```

---

## ⚠️ **LƯU Ý QUAN TRỌNG:**

### **1. Kiểm tra tất cả chỗ dùng getDameAttack:**

```bash
# Search trong project
grep -r "getDameAttack" src/

# Sửa tất cả:
int damage = getDameAttack();  // CŨ
↓
long damage = getDameAttack();  // MỚI
```

### **2. Kiểm tra các biến lưu damage:**

```java
// Tìm tất cả:
int dmg = player.nPoint.getDameAttack();
int finalDamage = ...;
int dameSkill = ...;

// Đổi thành:
long dmg = player.nPoint.getDameAttack();
long finalDamage = ...;
long dameSkill = ...;
```

### **3. Compile lại server:**

```bash
ant clean
ant build

# Nếu có lỗi:
# - Check tất cả chỗ dùng getDameAttack
# - Check tất cả biến dame, damage
# - Đổi int → long
```

---

## 🎯 **EXPECTED RESULT:**

### **Trước:**
```
Player dame: 100,000,000
Skill 9 (3000%): 100M × 3000% = 3,000,000,000
→ Server tính: 3 tỷ
→ Server CAP: 2,147,483,647 ❌
→ Client hiện: 2,147,483,647
```

### **Sau:**
```
Player dame: 100,000,000
Skill 9 (3000%): 100M × 3000% = 3,000,000,000
→ Server tính: 3 tỷ
→ Server KHÔNG CAP ✅
→ Client hiện: 3,000,000,000 🔥
```

### **Với dame lớn hơn:**
```
Player dame: 1,000,000,000 (1 tỷ)
Skill 9 (30,000%): 1B × 30,000% = 300,000,000,000
→ Server tính: 300 tỷ
→ Server KHÔNG CAP ✅
→ Client hiện: 300,000,000,000 💥💥💥
```

---

## 🚀 **BONUS: Tăng dame gốc lên cao**

Nếu muốn player đạt dame 1 tỷ (để skill 9 = 300 tỷ):

```java
// Tăng hệ số dame khi cộng điểm
// File: NPoint.java

// CŨ:
long damePerPoint = 100;  // Mỗi điểm TNSM = +100 dame

// MỚI:
long damePerPoint = 10000;  // Mỗi điểm TNSM = +10,000 dame
// → 100,000 điểm = 1 tỷ dame
```

Hoặc thêm buff VIP:

```java
// VIP 4 có thêm 500M dame
if (player.vipLevel == 4) {
    this.dameAdd += 500_000_000L;
}
```

---

## 📊 **TEST PLAN:**

### **1. Build server:**
```bash
ant clean build
run.bat
```

### **2. Test với client mod:**
```
1. Kết nối client mod vào server
2. Cộng điểm dame lên cao
3. Đánh mob
4. Xem damage hiện lên
   ✅ Nếu > 2.147 tỷ → THÀNH CÔNG!
   ❌ Nếu vẫn CAP 2.147 tỷ → Kiểm tra lại code
```

### **3. Test các skill:**
```
- Skill thường: Kiểm tra damage
- Skill 9: Kiểm tra damage lớn (60-70 tỷ)
- Chí mạng: Kiểm tra damage × 2
- Đánh boss: Kiểm tra không bị lỗi
```

---

## 🎯 **TÓM TẮT:**

```
1. Đổi int → long cho dame, dameg, dameAdd
2. Đổi short → int cho skill.damage
3. Xóa tất cả check > 2_147_483_647
4. Xóa tất cả Math.min(damage, 2_147_483_647)
5. Compile lại server
6. Test với client mod
7. Tận hưởng damage tỷ tỷ! 🔥
```

**DONE!** Player sẽ thích mê những con số to đùng! 💰💰💰