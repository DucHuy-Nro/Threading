# 📖 GIẢI THÍCH `icon_spec` TRONG `item_shop`

## 🎯 **`icon_spec` LÀ GÌ?**

`icon_spec` = **Icon ID đặc biệt** để hiển thị icon khác với icon mặc định của item.

---

## 🔍 **CÁCH HOẠT ĐỘNG:**

### **1. Bình thường (icon_spec = 0):**

```sql
-- Item shop với icon mặc định
INSERT INTO item_shop VALUES (
    NULL,
    64,      -- tab_id
    1343,    -- temp_id (Item ID 1343)
    1, 1, 1, 10,
    0,       -- icon_spec = 0 (dùng icon mặc định của item 1343)
    NOW()
);
```

**→ Shop sẽ hiển thị icon mặc định từ `item_template` của item 1343**

---

### **2. Dùng icon đặc biệt (icon_spec > 0):**

```sql
-- Item shop với icon đặc biệt
INSERT INTO item_shop VALUES (
    NULL,
    26,      -- tab_id
    555,     -- temp_id (Item ID 555)
    1, 1, 0, 100,
    160,     -- icon_spec = 160 (dùng icon có iconID = 160)
    NOW()
);
```

**→ Shop sẽ tìm item nào có `iconID = 160` trong `item_template` và hiển thị icon đó!**

---

## 📊 **CÁCH TÌM `icon_spec`:**

### **Bước 1: Xem `item_template`**

```sql
-- Xem iconID của các item
SELECT id, name, iconID 
FROM item_template 
ORDER BY iconID;

-- Ví dụ kết quả:
-- id    name              iconID
-- 457   Đậu thần          457
-- 193   Áo giáp 1 sao     193
-- 555   Mũ đặc biệt       160
```

### **Bước 2: Chọn icon muốn dùng**

Nếu bạn muốn item **1343** (Sách tuyệt kỹ) hiển thị với icon của item **457** (Đậu thần):

```sql
INSERT INTO item_shop VALUES (
    NULL, 64, 1343, 1, 1, 1, 10,
    457,  -- icon_spec = 457 (iconID của Đậu thần)
    NOW()
);
```

**→ Item 1343 sẽ hiển thị với icon của Đậu thần!**

---

## 💡 **KHI NÀO DÙNG `icon_spec`?**

### **✅ Dùng `icon_spec > 0` khi:**
- Muốn item hiển thị icon khác
- Tạo item ảo (hiển thị một thứ, thực tế là thứ khác)
- Event đặc biệt (item bình thường nhưng icon đặc biệt)

### **✅ Dùng `icon_spec = 0` khi:**
- Hiển thị icon mặc định của item (99% trường hợp)
- Item bình thường

---

## 📝 **VÍ DỤ THỰC TẾ:**

### **Ví dụ 1: Icon mặc định (Thông thường)**

```sql
-- Đậu thần với icon mặc định
INSERT INTO item_shop VALUES (
    NULL, 64, 457, 1, 1, 0, 10000,
    0,     -- icon_spec = 0 (icon mặc định)
    NOW()
);
```
**→ Hiển thị icon của Đậu thần**

---

### **Ví dụ 2: Icon đặc biệt (Event)**

```sql
-- Đậu thần nhưng hiển thị icon Ngọc rồng 1 sao
INSERT INTO item_shop VALUES (
    NULL, 64, 457, 1, 1, 0, 10000,
    14,    -- icon_spec = 14 (iconID của Ngọc rồng 1 sao)
    NOW()
);
```
**→ Mua được Đậu thần nhưng trong shop hiển thị icon Ngọc rồng!**

---

## 🎓 **CÁCH TÌM iconID:**

### **Cách 1: Query trực tiếp**

```sql
-- Xem tất cả iconID
SELECT id, name, iconID 
FROM item_template 
WHERE name LIKE '%tên item%';

-- VD: Tìm icon của Đậu thần
SELECT id, name, iconID 
FROM item_template 
WHERE name LIKE '%đậu%';

-- Kết quả:
-- id: 457, name: Đậu thần, iconID: 457
```

### **Cách 2: Xem theo loại**

```sql
-- Ngọc rồng (iconID thường = 14-20)
SELECT * FROM item_template WHERE id BETWEEN 14 AND 20;

-- Item thông dụng
SELECT id, name, iconID 
FROM item_template 
WHERE id IN (457, 193, 194, 0, 6, 12);
```

---

## 📋 **BẢNG iconID THÔNG DỤNG:**

| Item | ID | iconID | Mô tả |
|------|-----|--------|-------|
| Ngọc rồng 1 sao | 14 | 14 | Icon ngôi sao |
| Đậu thần | 457 | 457 | Icon đậu xanh |
| Áo giáp | 193 | 193 | Icon áo |
| Quần giáp | 194 | 194 | Icon quần |
| Rada | 12 | 12 | Icon mắt |

---

## ⚡ **TÓM TẮT:**

### **Cho item của bạn (ID 1343):**

```sql
-- CÁCH 1: Dùng icon mặc định (KHUYẾN NGHỊ)
INSERT INTO item_shop VALUES (
    NULL, 64, 1343, 1, 1, 1, 10,
    0,      -- icon_spec = 0
    NOW()
);

-- CÁCH 2: Dùng icon đặc biệt (nếu cần)
INSERT INTO item_shop VALUES (
    NULL, 64, 1343, 1, 1, 1, 10,
    457,    -- icon_spec = 457 (icon của Đậu thần)
    NOW()
);
```

---

## 🎯 **KHUYẾN NGHỊ:**

**→ Dùng `icon_spec = 0` cho đơn giản!**

Chỉ dùng `icon_spec > 0` khi bạn thực sự muốn item hiển thị icon khác.

---

## 🔧 **CODE XỬ LÝ:**

Trong source code:

```java
// File: ItemService.java
public short getItemIdByIcon(short IconID) {
    for (int i = 0; i < Manager.ITEM_TEMPLATES.size(); i++) {
        if (Manager.ITEM_TEMPLATES.get(i).iconID == IconID) {
            return Manager.ITEM_TEMPLATES.get(i).id;
        }
    }
    return -1;
}
```

**→ Tìm item có iconID = icon_spec và dùng icon của item đó!**

---

**KẾT LUẬN: Dùng `icon_spec = 0` là an toàn nhất!** ✅
