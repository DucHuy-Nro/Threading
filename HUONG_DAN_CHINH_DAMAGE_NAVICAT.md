# 🎯 HƯỚNG DẪN CHỈNH DAMAGE SKILL 9 BẰNG NAVICAT

## 📋 **BƯỚC 1: MỞ TABLE**

```
1. Mở Navicat
2. Kết nối database "ngocrong"
3. Double click table "skill_template"
```

---

## 📋 **BƯỚC 2: TÌM SKILL**

```
1. Nhấn Ctrl+F (hoặc nút Search)
2. Tìm: "Cađíc liên hoàn chưởng"
3. Hoặc tìm theo ID:
   - nclass_id = 0, id = 24 (Super Kame - Trái Đất)
   - nclass_id = 1, id = 26 (Ma Phong Ba - Namec)
   - nclass_id = 2, id = 25 (Ca đíc - Xayda)
```

---

## 📋 **BƯỚC 3: MỞ JSON EDITOR**

```
1. Click vào dòng skill (ví dụ: Ca đíc LH chưởng)
2. Nhìn sang cột "skills" (cột cuối cùng)
3. Click vào ô "skills"
4. Click nút "..." (3 chấm) bên phải ô
5. Một cửa sổ mới hiện ra với JSON
```

---

## 📋 **BƯỚC 4: SỬA JSON**

### **Hiện tại (Cấp 10):**
```json
{
  "power_require":60000000000,
  "damage":1000,
  "dx":210,
  "dy":210,
  "price":9999,
  "max_fight":1,
  "mana_use":35,
  "cool_down":80000,
  "id":185,
  "point":10,
  "info":"Chưởng 10"
}
```

### **Đổi thành (VD: damage 3000%):**
```json
{
  "power_require":60000000000,
  "damage":3000,        ← ĐỔI CHỖ NÀY
  "dx":210,
  "dy":210,
  "price":9999,
  "max_fight":1,
  "mana_use":35,
  "cool_down":80000,
  "id":185,
  "point":10,
  "info":"Chưởng 10"
}
```

### **⚠️ LƯU Ý:**
- Chỉ đổi số `1000` → `3000`
- KHÔNG đổi dấu ngoặc, dấu phẩy
- KHÔNG xóa dấu `"`
- Có thể dùng Ctrl+F trong cửa sổ JSON để tìm `"damage":1000`

---

## 📋 **BƯỚC 5: SAVE**

```
1. Click nút "OK" ở cửa sổ JSON
2. Click nút "Apply" (hoặc dấu tích xanh ở toolbar)
3. Hoặc nhấn Ctrl+S
4. Navicat sẽ hỏi "Are you sure?" → Click "Yes"
```

---

## 📋 **BƯỚC 6: KIỂM TRA**

```
1. Click lại vào ô "skills"
2. Click "..." để mở lại JSON
3. Kéo xuống cấp 10
4. Xem "damage" đã đổi thành 3000 chưa?
```

---

## 📋 **BƯỚC 7: RESTART SERVER**

```
1. Tắt server (Ctrl+C hoặc taskkill)
2. Chạy lại: run.bat
3. Đợi server khởi động xong
```

---

## 📋 **BƯỚC 8: TEST IN-GAME**

### **⚠️ QUAN TRỌNG: PHẢI HỌC LẠI SKILL!**

**Skill data đã được cache trong player data!**

```
Cách 1: Bán skill cũ, mua lại
1. Vào shop SGohan
2. Mua lại skill 9 cấp 10
3. Skill mới sẽ có damage 3000%

Cách 2: Reset skill qua SQL (Nguy hiểm!)
UPDATE player 
SET skills = '[]' 
WHERE id = YOUR_PLAYER_ID;

Cách 3: Tạo nhân vật mới để test
```

---

## 🎯 **VÍ DỤ CHỈNH CẢ 3 SKILL:**

### **Skill 24 - Super Kamejoko (Trái Đất)**
```
1. Tìm: nclass_id = 0, id = 24
2. Mở JSON, tìm "point":10
3. Đổi "damage":1000 → "damage":3000
4. Click OK → Apply
```

### **Skill 25 - Ca đíc LH chưởng (Xayda)**
```
1. Tìm: nclass_id = 2, id = 25
2. Mở JSON, tìm "point":10
3. Đổi "damage":1000 → "damage":3000
4. Click OK → Apply
```

### **Skill 26 - Ma Phong Ba (Namec)**
```
1. Tìm: nclass_id = 1, id = 26
2. Mở JSON, tìm "point":10
3. Đổi "damage":1000 → "damage":3000
4. Click OK → Apply
```

---

## 🎯 **CHỈNH TẤT CẢ CẤP (1-10):**

Nếu muốn chỉnh cả 10 cấp:

### **Cấp 1:**
```json
{"damage":550, "cool_down":170000, "point":1}
→
{"damage":1000, "cool_down":100000, "point":1}
```

### **Cấp 2:**
```json
{"damage":600, "cool_down":160000, "point":2}
→
{"damage":1200, "cool_down":90000, "point":2}
```

### **... (tương tự cho cấp 3-9)**

### **Cấp 10:**
```json
{"damage":1000, "cool_down":80000, "point":10}
→
{"damage":3000, "cool_down":50000, "point":10}
```

---

## ⚠️ **SAI LẦM THƯỜNG GẶP:**

### **❌ SAI 1: Chỉnh nhưng không Apply**
```
Triệu chứng: Chỉnh xong, click OK, đóng Navicat
Fix: Phải click "Apply" hoặc Ctrl+S
```

### **❌ SAI 2: Chỉnh sai JSON (thiếu dấu phẩy, ngoặc)**
```
Triệu chứng: Navicat báo lỗi "Invalid JSON"
Fix: Kiểm tra lại dấu ngoặc, dấu phẩy
```

### **❌ SAI 3: Chỉnh xong không restart server**
```
Triệu chứng: Vào game vẫn như cũ
Fix: Phải restart server
```

### **❌ SAI 4: Restart server nhưng không học lại skill**
```
Triệu chứng: Damage vẫn thế
Fix: Phải BÁN skill cũ, MUA lại skill mới
Hoặc: Tạo nhân vật mới để test
```

---

## 🔍 **CÁCH KIỂM TRA DAMAGE TRONG GAME:**

### **1. Xem info skill:**
```
- Mở bảng kỹ năng (K)
- Click vào skill 9
- Xem "Tăng sức đánh: X%"
```

### **2. Test damage thực tế:**
```
- Vào đánh mob
- Xem số damage hiện lên
- So sánh với công thức:
  Damage = DameGốc × SkillDamage% / 100
  
VD: 
- Dame gốc: 1,000,000
- Skill%: 3000
- Damage: 1,000,000 × 3000 / 100 = 30,000,000
```

---

## 🎯 **TÓM TẮT NHANH:**

```
1. Navicat → table skill_template
2. Tìm skill (Ctrl+F)
3. Click ô "skills" → Click "..."
4. Sửa "damage":1000 → 3000
5. OK → Apply (Ctrl+S)
6. Restart server
7. Học lại skill hoặc tạo char mới
8. Test damage
```

---

## 💡 **NẾU VẪN KHÔNG ĐƯỢC:**

Cho tôi biết:
1. Bạn đã Apply chưa? (Ctrl+S)
2. Bạn đã restart server chưa?
3. Bạn đã học lại skill chưa?
4. Damage hiện tại trong Navicat là bao nhiêu?

**HOẶC:** Tôi tạo SQL script cho bạn chạy luôn, không cần chỉnh tay!