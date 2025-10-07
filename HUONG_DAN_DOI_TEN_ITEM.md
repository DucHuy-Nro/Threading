# 📝 HƯỚNG DẪN: ĐỔI TÊN ITEM TRONG SQL

## ✅ **KẾT LUẬN: AN TOÀN ĐỔI TÊN!**

**Đổi tên item trong `item_template` KHÔNG CẦN đổi ở đâu khác!**  
Code Java chỉ dùng `item.template.id` (số), không dùng `name` (chữ).

---

## 🔍 **PHÂN TÍCH:**

### **1. Code Java dùng gì?**
```java
// ✅ TÌM ITEM BẰNG ID
Item item = InventoryService.gI().findItemBag(player, 674); // Đá ngũ sắc
Item item = InventoryService.gI().findItem(list, 1229);     // Bí kíp tuyệt kỹ

// ❌ KHÔNG TÌM BẰNG TÊN
// Không có hàm findItemByName() trong code!
```

### **2. `template.name` chỉ dùng để HIỂN THỊ:**
```java
// Hiển thị thông báo
Service.gI().sendThongBao(player, "Bạn đã nhận được " + item.template.name);
Service.gI().sendThongBao(player, "Bạn còn thiếu " + item.template.name);

// Hiển thị menu
msg.writer().writeUTF("Bạn chắc chắn muốn vứt " + item.template.name + "?");
```

**→ Chỉ dùng để IN RA cho người chơi đọc, KHÔNG dùng để so sánh/tìm kiếm!**

---

## ⚠️ **NGOẠI LỆ: SÁCH HỌC SKILL (Type 7)**

### **CÓ 1 CHỖ DUY NHẤT LẤY SỐ TỪ TÊN:**

**File:** `src/nro/models/services_func/UseItem.java` (dòng 1766-1767)

```java
// Học skill từ sách
String[] subName = item.template.name.split("");
byte level = Byte.parseByte(subName[subName.length - 1]);
// → Lấy KÝ TỰ CUỐI CÙNG của tên để xác định cấp skill!
```

**Ví dụ:**
```
"Sách tuyệt kỹ 1" → split("") → ["S","á","c","h",...,"1"] → level = 1
"Sách tuyệt kỹ 2" → split("") → ["S","á","c","h",...,"2"] → level = 2
"Sách tuyệt kỹ 7" → split("") → ["S","á","c","h",...,"7"] → level = 7
```

**⚠️ QUY TẮC CHO SÁCH SKILL:**
- **Tên PHẢI kết thúc bằng SỐ** (1, 2, 3, 4, 5, 6, 7)
- Số này = cấp skill
- Phần trước số có thể đổi tùy ý

---

## 📋 **QUY TẮC ĐỔI TÊN:**

### **✅ AN TOÀN (Đổi tùy ý):**

**99% item đều an toàn:**
```sql
-- ✅ ĐỔI TỰ DO
UPDATE item_template SET name = 'Đá Ngũ Sắc Siêu Cấp' WHERE id = 674;
UPDATE item_template SET name = 'Bí Kíp Bí Ẩn' WHERE id = 1229;
UPDATE item_template SET name = 'Rương Ngọc Rồng VIP' WHERE id = 570;
```

### **⚠️ CHÚ Ý (Giữ số cuối):**

**Chỉ SÁCH SKILL (type = 7) cần giữ số:**
```sql
-- ⚠️ PHẢI GIỮ SỐ CUỐI
-- Trước (Cấp 1):
name = 'Sách tuyệt kỹ 1'

-- ✅ ĐỔI ĐƯỢC (Giữ "1" ở cuối):
name = 'Sách tuyệt kỹ cao cấp 1'
name = 'Bí kíp tuyệt kỹ 1'
name = 'Cuốn sách kỹ năng 1'

-- ❌ SAI (Mất số "1"):
name = 'Sách tuyệt kỹ cấp một'      ← Lỗi! Không có số
name = 'Sách tuyệt kỹ 1 đặc biệt'   ← Lỗi! Số không ở cuối
name = 'Sách tuyệt kỹ'              ← Lỗi! Thiếu số
```

---

## 🔢 **DANH SÁCH SÁCH SKILL (Type 7):**

```sql
-- Cần kiểm tra các item có type = 7
SELECT id, type, name FROM item_template WHERE type = 7;

-- Kết quả (VD):
-- ID 1044: Sách tuyệt kỹ 1 (Trái Đất)
-- ID 1211: Sách tuyệt kỹ 1 (Namec)
-- ID 1212: Sách tuyệt kỹ 1 (Xayda)
-- ID 1278: Sách tuyệt kỹ 2
-- ID 1279: Sách tuyệt kỹ 2
-- ID 1280: Sách tuyệt kỹ 2
-- ... (cấp 3, 4, 5, 6, 7)
```

**→ CHỈ các item này cần giữ số cuối!**

---

## 📖 **VÍ DỤ ĐỔI TÊN AN TOÀN:**

### **VD 1: Đổi tên item thông thường**
```sql
-- ĐÁ NGŨ SẮC (ID 674)
-- Trước: "Đá ngũ sắc"
-- Sau:   "Đá Ngũ Sắc Siêu Hiếm"
UPDATE item_template SET name = 'Đá Ngũ Sắc Siêu Hiếm' WHERE id = 674;
-- ✅ AN TOÀN 100%
```

### **VD 2: Đổi tên bí kíp tuyệt kỹ**
```sql
-- BÍ KÍP TUYỆT KỸ (ID 1229, type 27)
-- Trước: "Bí kíp tuyệt kỹ"
-- Sau:   "Bí Kíp Tuyệt Kỹ Huyền Thoại"
UPDATE item_template SET name = 'Bí Kíp Tuyệt Kỹ Huyền Thoại' WHERE id = 1229;
-- ✅ AN TOÀN (type 27, không phải 7)
```

### **VD 3: Đổi tên sách skill (type 7)**
```sql
-- SÁCH TUYỆT KỸ CẤP 1 (type 7)
-- Trước: "Sách tuyệt kỹ 1"

-- ✅ ĐÚNG:
UPDATE item_template SET name = 'Bí Kíp Kỹ Năng 1' WHERE id = 1044;
UPDATE item_template SET name = 'Sách Tuyệt Kỹ Cao Cấp 1' WHERE id = 1044;

-- ❌ SAI:
UPDATE item_template SET name = 'Bí Kíp Kỹ Năng Cấp Một' WHERE id = 1044;
UPDATE item_template SET name = 'Sách Tuyệt Kỹ' WHERE id = 1044;
```

---

## 🎯 **TÓM TẮT:**

| Item Type | Đổi Tên | Quy Tắc |
|-----------|---------|---------|
| **99% items** | ✅ Tự do | Không giới hạn |
| **Sách skill (type 7)** | ⚠️ Giữ số cuối | Tên phải kết thúc bằng số 1-7 |

---

## 🔍 **CÁCH KIỂM TRA:**

### **1. Kiểm tra item có phải sách skill không:**
```sql
-- Xem type của item
SELECT id, name, type FROM item_template WHERE id = [ITEM_ID];

-- Nếu type = 7 → Cần giữ số cuối
-- Nếu type != 7 → Đổi tùy ý
```

### **2. Tìm tất cả sách skill:**
```sql
-- Tìm toàn bộ sách skill (type 7)
SELECT id, name, type FROM item_template 
WHERE type = 7 
ORDER BY id;
```

---

## ⚠️ **CHÚ Ý KHI ĐỔI TÊN:**

### **✅ LUÔN AN TOÀN:**
- Đổi tên item vũ khí, trang bị, đá quý
- Đổi tên item nhiệm vụ, sự kiện
- Đổi tên nguyên liệu, thuốc
- Đổi tên pet, cài trang
- **→ 99% item đều an toàn!**

### **⚠️ CẦN CHÚ Ý:**
- **Chỉ sách skill (type 7)** cần giữ số cuối
- Kiểm tra type trước khi đổi
- Test kỹ sau khi đổi

### **❌ KHÔNG CẦN:**
- Không cần đổi code Java
- Không cần đổi file khác
- Không cần restart server (chỉ cần reload item)

---

## 🚀 **CÁC BƯỚC ĐỔI TÊN:**

### **1. BACKUP:**
```sql
-- Backup table item_template
CREATE TABLE item_template_backup AS SELECT * FROM item_template;
```

### **2. KIỂM TRA TYPE:**
```sql
-- Xem type của item muốn đổi
SELECT id, name, type, description FROM item_template WHERE id = [ITEM_ID];
```

### **3. ĐỔI TÊN:**
```sql
-- Nếu type != 7: Đổi tùy ý
UPDATE item_template SET name = 'Tên Mới' WHERE id = [ITEM_ID];

-- Nếu type = 7: Giữ số cuối (1-7)
UPDATE item_template SET name = 'Tên Mới [SỐ]' WHERE id = [ITEM_ID];
```

### **4. TEST:**
```sql
-- Kiểm tra đã đổi chưa
SELECT id, name, type FROM item_template WHERE id = [ITEM_ID];
```

### **5. RELOAD TRONG GAME:**
- Restart server HOẶC
- Dùng lệnh admin reload item (nếu có)

---

## 📞 **TROUBLESHOOTING:**

### **Lỗi: "Không học được skill"**
**Nguyên nhân:** Tên sách skill không có số cuối  
**Giải pháp:** Sửa lại tên kết thúc bằng số 1-7

### **Lỗi: "Skill lên cấp sai"**
**Nguyên nhân:** Số cuối không đúng với cấp skill  
**Giải pháp:** Đổi số cuối cho đúng (VD: Cấp 3 thì phải là "...3")

### **Item không hiển thị tên mới**
**Nguyên nhân:** Chưa reload server  
**Giải pháp:** Restart server hoặc logout/login lại

---

## 🎉 **KẾT LUẬN:**

✅ **Đổi tên item HOÀN TOÀN AN TOÀN!**  
✅ **Không cần sửa code Java!**  
✅ **Chỉ cần chú ý sách skill (type 7)!**  
✅ **99% item đổi tùy ý!**  

**ĐỔI NGAY KHÔNG SỢ LỖI! 🚀**
