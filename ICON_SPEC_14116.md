# 🔍 KIỂM TRA icon_spec = 14116

## ❌ **KẾT QUẢ:**

**`icon_spec = 14116` KHÔNG TỒN TẠI trong database!**

---

## 📊 **CHỨNG CỨ:**

Tìm kiếm trong `item_template`:

```
icon_id gần 14116:
- 14115 → Item 1700: 'CT Hằng Nga'
- 14117 → Item 1704: 'Bí Ngô Cánh Dơi'
- 14149 → Item 1705: 'Cải Trang Chi Chi Võ Đài'

→ KHÔNG CÓ icon_id = 14116! ❌
```

---

## 💡 **Ý NGHĨA:**

Khi bạn dùng `icon_spec = 14116`:

```sql
INSERT INTO item_shop VALUES (
    NULL, 64, 1343, 1, 1, 1, 10,
    14116,  -- ← Icon này KHÔNG TỒN TẠI!
    NOW()
);
```

**→ Game sẽ không tìm thấy icon → Có thể:**
- Không hiển thị icon (icon trống)
- Hiển thị icon lỗi/default
- Hoặc gây crash

---

## ✅ **GIẢI PHÁP:**

### **Cách 1: Dùng icon mặc định (KHUYẾN NGHỊ)**

```sql
UPDATE item_shop 
SET icon_spec = 0 
WHERE id = 1005;
```

**→ Sẽ dùng icon mặc định của item 1343**

---

### **Cách 2: Dùng icon có thật**

Chọn một icon_id **CÓ TRONG DATABASE:**

```sql
-- Xem danh sách icon_id có thể dùng
SELECT id, name, icon_id 
FROM item_template 
WHERE icon_id BETWEEN 14000 AND 15000
ORDER BY icon_id;
```

**Một số icon đẹp có thể dùng:**

| icon_id | Item | Tên |
|---------|------|-----|
| 14115 | 1700 | CT Hằng Nga |
| 14117 | 1704 | Bí Ngô Cánh Dơi |
| 14149 | 1705 | Cải Trang Chi Chi |
| 14186 | 1708 | CT Android 21 |
| 14203 | 1711 | Cân Đẩu Vân Thơ Mộng |
| 14242 | 1713 | Khủng Long Thơ Mộng |
| 14258 | 1712 | Pet Rồng xương |

**Ví dụ dùng icon Pet Rồng xương:**

```sql
UPDATE item_shop 
SET icon_spec = 14258 
WHERE id = 1005;
```

---

### **Cách 3: Tìm icon của Sách Tuyệt Kỹ**

```sql
-- Xem icon mặc định của item 1343
SELECT id, name, icon_id 
FROM item_template 
WHERE id = 1343;
```

Nếu item 1343 có `icon_id = 1234`, bạn có thể dùng:

```sql
UPDATE item_shop 
SET icon_spec = 1234 
WHERE id = 1005;

-- HOẶC đơn giản hơn, dùng 0:
UPDATE item_shop 
SET icon_spec = 0 
WHERE id = 1005;
```

---

## 🎯 **CÁCH TÌM icon_id ĐÚNG:**

### **Bước 1: Vào Navicat → Chạy query này:**

```sql
-- Xem tất cả icon có thể dùng
SELECT id, name, type, icon_id 
FROM item_template 
WHERE type = 33  -- Type 33 thường là sách/skill
ORDER BY icon_id;

-- HOẶC tìm theo tên
SELECT id, name, type, icon_id 
FROM item_template 
WHERE name LIKE '%sách%' 
   OR name LIKE '%skill%'
   OR name LIKE '%tuyệt%';
```

### **Bước 2: Chọn icon_id thích hợp**

Ví dụ tìm được:
- Item 1300: 'Sách X', icon_id = 9500
- Item 1301: 'Sách Y', icon_id = 9501

→ Dùng `icon_spec = 9500` hoặc `9501`

---

## ⚡ **FIX NHANH:**

```sql
-- Mở Navicat → Table item_shop → Tìm dòng id = 1005
-- Sửa icon_spec từ 14116 → 0

UPDATE item_shop 
SET icon_spec = 0 
WHERE id = 1005;

-- Restart server
-- → Shop sẽ hiển thị icon mặc định của item 1343 ✅
```

---

## 📋 **CHECKLIST TÌM ICON:**

1. **Mở Navicat**
2. **Query:**
   ```sql
   SELECT id, name, icon_id 
   FROM item_template 
   WHERE id = 1343;  -- ID item của bạn
   ```
3. **Xem kết quả:** VD: `icon_id = 1234`
4. **2 LỰA CHỌN:**
   - Dùng `icon_spec = 0` (icon mặc định) ✅
   - Dùng `icon_spec = 1234` (giống item)
5. **Update:**
   ```sql
   UPDATE item_shop SET icon_spec = 0 WHERE id = 1005;
   ```
6. **Restart server**

---

## 💡 **KẾT LUẬN:**

**icon_spec = 14116 là LỖI vì không tồn tại!**

**→ Sửa thành `icon_spec = 0` là an toàn nhất!** ✅

---

## 🎓 **QUY TẮC:**

✅ **icon_spec = 0** → Luôn OK (dùng icon mặc định)  
✅ **icon_spec = icon_id có trong item_template** → OK  
❌ **icon_spec = số ngẫu nhiên không có** → LỖI!

---

**Chạy lệnh SQL này để fix:**

```sql
UPDATE item_shop SET icon_spec = 0 WHERE id = 1005;
```

**→ XONG!** ✅
