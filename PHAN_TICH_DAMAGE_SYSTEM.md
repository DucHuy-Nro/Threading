# 🎯 PHÂN TÍCH HỆ THỐNG DAMAGE TRONG NRO SOURCE

## 📊 **KIỂU DỮ LIỆU DAMAGE:**

### **1. Trong Code Java:**

```java
// File: NPoint.java (Player stats)
public int dame, dameg;           // int (32-bit)
public int dameAdd;                // int (32-bit)
public int dameAfter;              // int (32-bit)

// File: Skill.java (Skill data)
public short damage;               // short (16-bit) ⚠️ QUAN TRỌNG!

// File: NPoint.java (Damage calculation)
int dameAttack = this.dame;        // int (32-bit)
long tempDameAttack;               // long (64-bit) cho tính toán
```

---

## 🔢 **GIỚI HẠN DAMAGE:**

### **A. GIỚI HẠN KIỂU DỮ LIỆU:**

| Kiểu | Bit | Min | Max | Ứng dụng |
|------|-----|-----|-----|----------|
| **byte** | 8 | -128 | 127 | Không dùng cho damage |
| **short** | 16 | -32,768 | **32,767** | ⚠️ **Skill damage%** |
| **int** | 32 | -2.1 tỷ | **2,147,483,647** | ✅ **Damage chính** |
| **long** | 64 | -9.2E18 | 9,223,372,036,854,775,807 | Tính toán trung gian |

### **B. GIỚI HẠN THỰC TẾ TRONG CODE:**

```java
// 1. GIỚI HẠN SKILL DAMAGE% (trong SQL)
public short damage;  // MAX = 32,767%

// 2. GIỚI HẠN DAMAGE CUỐI CÙNG
if (dameAttack > 2_147_483_647) {
    dameAttack = 2_147_483_647;  // MAX = 2.147 tỷ
}
return (int) dameAttack;
```

---

## ⚠️ **VẤN ĐỀ QUAN TRỌNG:**

### **🚨 LỖI NẾU SKILL DAMAGE > 32,767:**

```java
// Trong SQL, bạn set:
"damage": 35000  // VD: 35,000%

// Khi load vào Java:
skill.damage = Short.parseShort("35000");
// ❌ LỖI: NumberFormatException
// Short.MAX_VALUE = 32,767
```

### **📊 KẾT QUẢ:**

```
Nếu damage trong SQL > 32,767:
→ Server crash khi load skill
→ Hoặc damage bị overflow thành số âm
```

---

## 💡 **CÔNG THỨC TÍNH DAMAGE:**

### **1. DAMAGE GỐC (Dame):**

```java
long dame = this.dameg + this.dameAdd;

if (dame > 2_147_483_647) {
    dame = 2_147_483_647;
}

this.dame = (int) dame;
```

**Giải thích:**
- `dameg` = Damage từ cộng điểm TNSM
- `dameAdd` = Damage từ trang bị, buff, nội tại
- **Giới hạn:** 2,147,483,647 (2.1 tỷ)

### **2. DAMAGE KHI ĐÁNH (DameAttack):**

```java
// BƯỚC 1: Lấy dame gốc
int dameAttack = this.dame;

// BƯỚC 2: Nhân với skill damage%
percentDameSkill = skillSelect.damage;  // Từ SQL
dameAttack = dameAttack * percentDameSkill / 100;

// VD:
// dame = 1,000,000
// skill% = 3,000
// dameAttack = 1,000,000 × 3,000 / 100 = 30,000,000

// BƯỚC 3: Cộng buff nội tại
dameAttack += (dameAttack * percentDameIntrinsic / 100);

// BƯỚC 4: Cộng buff khác
dameAttack += (dameAttack * dameAfter / 100);

// BƯỚC 5: Cộng buff sexy, pet
if (effectSkill.isDameBuff) {
    dameAttack += (dameAttack * tiLeDameBuff / 100);
}

// BƯỚC 6: Cộng buff dame đánh mob
for (Integer tl : tlDameAttMob) {
    dameAttack += (dameAttack * tl / 100);
}

// BƯỚC 7: Nhân đôi nếu chí mạng
if (isCrit) {
    dameAttack *= 2;
    dameAttack += (dameAttack * tlSDCM / 100);
}

// BƯỚC 8: Cộng buff set đồ
dameAttack += ((long) dameAttack * percentXDame / 100);

// BƯỚC 9: Random ±5%
long tempDameAttack = (long) (dameAttack / 100L * 5L);
dameAttack += (long) (Util.getOne(-1, 1) * Util.nextInt((int) tempDameAttack) + 1);

// BƯỚC 10: Kiểm tra overflow
if (dameAttack > 2_147_483_647) {
    dameAttack = 2_147_483_647;  // CAP tại 2.1 tỷ
}

return (int) dameAttack;
```

---

## 🎯 **TRẢ LỜI CÂU HỎI:**

### **Q: Source này dùng damage loại gì?**
**A:** Source dùng **INT (32-bit)** cho damage chính

### **Q: Giới hạn damage tối đa là bao nhiêu?**
**A:** **2,147,483,647** (2.1 tỷ)

### **Q: Nếu đánh vượt quá giới hạn thì sao?**
**A:** 
- Code đã có check: `if (dameAttack > 2_147_483_647)`
- Damage sẽ bị **CAP** (giới hạn) ở 2.147 tỷ
- **KHÔNG BỊ LỖI**, chỉ damage không tăng nữa

### **Q: Skill damage% giới hạn bao nhiêu?**
**A:**
- Skill damage dùng **SHORT** → MAX = **32,767%**
- Nếu set > 32,767 trong SQL → **SERVER CRASH!**

---

## 📋 **BẢNG TÍNH DAMAGE VỚI CÁC MỨC DAME GỐC:**

### **Giả sử: Skill damage = 3,000%**

| Dame Gốc | Skill% | Damage (Không crit) | Damage (Crit x2) | Kết quả |
|----------|--------|---------------------|------------------|---------|
| 1,000,000 | 3,000 | 30,000,000 | 60,000,000 | ✅ OK |
| 10,000,000 | 3,000 | 300,000,000 | 600,000,000 | ✅ OK |
| 50,000,000 | 3,000 | 1,500,000,000 | 3,000,000,000 | ⚠️ CAP 2.1 tỷ |
| 100,000,000 | 3,000 | 3,000,000,000 | 6,000,000,000 | ⚠️ CAP 2.1 tỷ |

### **Giả sử: Skill damage = 10,000%**

| Dame Gốc | Skill% | Damage (Không crit) | Damage (Crit x2) | Kết quả |
|----------|--------|---------------------|------------------|---------|
| 1,000,000 | 10,000 | 100,000,000 | 200,000,000 | ✅ OK |
| 10,000,000 | 10,000 | 1,000,000,000 | 2,000,000,000 | ✅ OK |
| 30,000,000 | 10,000 | 3,000,000,000 | 6,000,000,000 | ⚠️ CAP 2.1 tỷ |

### **Giả sử: Skill damage = 32,767% (MAX)**

| Dame Gốc | Skill% | Damage (Không crit) | Damage (Crit x2) | Kết quả |
|----------|--------|---------------------|------------------|---------|
| 100,000 | 32,767 | 32,767,000 | 65,534,000 | ✅ OK |
| 1,000,000 | 32,767 | 327,670,000 | 655,340,000 | ✅ OK |
| 10,000,000 | 32,767 | 3,276,700,000 | 6,553,400,000 | ⚠️ CAP 2.1 tỷ |

---

## 🚨 **CẢNH BÁO:**

### **❌ KHÔNG NÊN:**

1. **Set skill damage > 32,767 trong SQL**
   ```sql
   "damage": 50000  -- ❌ Server crash!
   ```
   → Lý do: `short` max = 32,767

2. **Mong đợi damage > 2.147 tỷ**
   ```
   Dame gốc: 100,000,000
   Skill%: 30,000
   Kỳ vọng: 30 tỷ
   Thực tế: 2.147 tỷ (bị CAP)
   ```

3. **Sửa code dùng long cho damage**
   ```java
   // ❌ KHÔNG LÀM NÀY (nếu không biết rõ)
   public long dame;  // Client chỉ đọc int!
   ```
   → Lý do: Client game chỉ hỗ trợ int (32-bit)

### **✅ NÊN:**

1. **Set skill damage hợp lý**
   ```sql
   "damage": 3000   -- ✅ OK (3,000%)
   "damage": 10000  -- ✅ OK (10,000%)
   "damage": 30000  -- ✅ OK (30,000%)
   "damage": 32767  -- ✅ MAX
   ```

2. **Test damage với dame gốc khác nhau**
   ```
   - Dame 1 triệu → Test
   - Dame 10 triệu → Test
   - Dame 100 triệu → Test (sẽ thấy CAP)
   ```

3. **Chấp nhận giới hạn 2.1 tỷ**
   ```
   Đây là giới hạn của game engine
   Không thể vượt qua trừ khi:
   - Sửa client
   - Sửa protocol
   - Rất phức tạp!
   ```

---

## 📊 **VÍ DỤ THỰC TẾ:**

### **Scenario 1: Skill 9 với 3,000%**

```java
// Player stats
dame = 50,000,000  // 50 triệu dame gốc

// Skill 9 cấp 10
skill.damage = 3000  // 3,000%

// Tính damage
dameAttack = 50,000,000 × 3000 / 100
           = 1,500,000,000  // 1.5 tỷ

// Nếu chí mạng
dameAttack = 1,500,000,000 × 2
           = 3,000,000,000  // 3 tỷ

// Kiểm tra overflow
if (3,000,000,000 > 2,147,483,647) {
    dameAttack = 2,147,483,647  // CAP tại 2.147 tỷ
}

// Kết quả cuối
Damage hiện lên: 2,147,483,647
```

### **Scenario 2: Skill 9 với 32,767% (MAX)**

```java
// Player stats
dame = 10,000,000  // 10 triệu dame gốc

// Skill 9 cấp 10
skill.damage = 32767  // MAX short

// Tính damage
dameAttack = 10,000,000 × 32767 / 100
           = 3,276,700,000  // 3.27 tỷ

// Kiểm tra overflow
if (3,276,700,000 > 2,147,483,647) {
    dameAttack = 2,147,483,647  // CAP tại 2.147 tỷ
}

// Kết quả cuối
Damage hiện lên: 2,147,483,647
```

---

## 🎯 **KẾT LUẬN:**

### **1. Source này dùng INT (32-bit)**
- Giới hạn: **2,147,483,647** (2.1 tỷ)
- Đủ mạnh cho hầu hết game play

### **2. Skill damage% dùng SHORT (16-bit)**
- Giới hạn: **32,767%**
- KHÔNG được vượt quá trong SQL

### **3. Damage có CAP (giới hạn trên)**
- Code đã check overflow
- Damage tối đa hiện lên: 2.147 tỷ
- **KHÔNG BỊ LỖI** nếu vượt quá

### **4. Các loại damage:**

```
┌─────────────────────────────────────┐
│ DAMAGE GỐC (Base Damage)            │
│ = dameg + dameAdd                   │
│ (từ cộng điểm + trang bị)           │
│ MAX: 2.147 tỷ (int)                 │
└─────────────────────────────────────┘
              ↓
┌─────────────────────────────────────┐
│ SKILL DAMAGE %                      │
│ = skill.damage từ SQL               │
│ MAX: 32,767% (short)                │
└─────────────────────────────────────┘
              ↓
┌─────────────────────────────────────┐
│ DAMAGE TRUNG GIAN                   │
│ = dame × skill% / 100               │
│ Dùng LONG (64-bit) để tính          │
└─────────────────────────────────────┘
              ↓
┌─────────────────────────────────────┐
│ DAMAGE CUỐI CÙNG                    │
│ = + buff nội tại                    │
│ = + buff set đồ                     │
│ = × 2 nếu crit                      │
│ = CAP tại 2.147 tỷ (int)           │
└─────────────────────────────────────┘
```

---

## 💡 **KHUYẾN NGHỊ:**

### **Cho PVE (đánh quái):**
```
Skill damage: 3,000 - 10,000%
Dame gốc: 10 triệu - 50 triệu
Kết quả: 300 triệu - 5 tỷ damage
```

### **Cho PVP (đánh người):**
```
Skill damage: 1,000 - 5,000%
Dame gốc: 10 triệu - 100 triệu
Kết quả: 100 triệu - 2.1 tỷ damage
```

### **Cho Boss/Event:**
```
Skill damage: 5,000 - 20,000%
Dame gốc: 50 triệu - 100 triệu
Kết quả: Luôn CAP 2.1 tỷ
```

---

## 🔧 **NẾU MUỐN TĂNG GIỚI HẠN:**

### **Option 1: Sửa code (Khó)**
```java
// Đổi từ int → long
public long dame;
public long getDameAttack() {
    // ... tính toán ...
    return dameAttack;  // long
}

// ⚠️ Cần sửa:
// - Client code
// - Network protocol
// - Database schema
// → RẤT PHỨC TẠP!
```

### **Option 2: Chấp nhận giới hạn (Dễ)**
```
- Giữ nguyên 2.1 tỷ
- Cân bằng game quanh con số này
- Đủ mạnh cho gameplay
```

### **Option 3: Dùng multiplier**
```java
// Damage hiện 2.1 tỷ
// Nhưng tính toán nội bộ × 10
// VD: 2.1 tỷ × 10 = 21 tỷ (dùng long)
// Chỉ hiện 2.1 tỷ cho player
```

---

**TÓM LẠI:**
- **INT = 2.147 tỷ MAX** ✅
- **SHORT = 32,767 MAX** ⚠️
- **LONG = 9 tỷ tỷ MAX** (tính toán)
- **Vượt quá → BỊ CAP, KHÔNG LỖI** 🛡️