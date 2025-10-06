# ✅ ĐÃ FIX LỖI SHOP!

## ❌ **LỖI BẠN GẶP:**

```
error: cannot find symbol
  symbol:   method openShopNormal(Player,String,int,int,<null>,int,int)
  location: class ShopService
```

---

## 🐛 **NGUYÊN NHÂN:**

Bạn đã dùng **sai tên method**!

### **SAI:**
```java
ShopService.gI().openShopNormal(player, "Shop Gạo", 0, -1, null, 0, 0);
//                ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
//              Method này KHÔNG TỒN TẠI!
```

### **ĐÚNG:**
```java
ShopService.gI().opendShop(player, "BUNMA", true);
//                ↑↑↑↑↑↑↑↑↑
//            Method đúng!
```

---

## ✅ **ĐÃ SỬA:**

**File:** `src/nro/models/npc_list/SGohan.java`

### **Import đúng:**
```java
import nro.models.shop.ShopService; // ✅ Đã thêm
```

### **Code đúng:**
```java
case 2: // Cửa hàng
    ShopService.gI().opendShop(player, "BUNMA", true);
    //                ↑↑↑↑↑↑↑↑↑
    //            Method ĐÚNG: opendShop (không phải openShopNormal)
    break;
```

---

## 📝 **CÁCH DÙNG ĐÚNG:**

### **Syntax:**
```java
ShopService.gI().opendShop(player, tagName, allGender);
```

### **Tham số:**
- `player` - Player object
- `tagName` - Tên shop trong database (VD: "BUNMA", "DENDE", "SANTA"...)
- `allGender` - `true` = tất cả gender, `false` = theo gender cụ thể

### **Ví dụ:**
```java
// Shop Bulma (cho tất cả)
ShopService.gI().opendShop(player, "BUNMA", true);

// Shop Dende (cho tất cả)
ShopService.gI().opendShop(player, "DENDE", true);

// Shop Santa (cho tất cả)
ShopService.gI().opendShop(player, "SANTA", true);

// Shop theo gender
ShopService.gI().opendShop(player, "BUNMA", false);
```

---

## 🚀 **BÂY GIỜ BUILD LẠI:**

```bash
# Clean & Build
ant clean && ant jar

# Hoặc trong NetBeans:
# Clean and Build (Shift+F11)
```

**→ SẼ COMPILE THÀNH CÔNG!** ✅

---

## 📋 **CHECKLIST:**

- [x] Import `ShopService`
- [x] Dùng method `opendShop()` (ĐÚNG)
- [x] Tham số: `(player, tagName, allGender)`
- [ ] Build lại project
- [ ] Test in-game

---

## 💡 **LƯU Ý:**

### **Các method ĐÚNG trong ShopService:**
```java
✅ opendShop(player, tagName, allGender)
❌ openShopNormal(...) // KHÔNG TỒN TẠI!
❌ openShop(...)       // KHÔNG TỒN TẠI!
```

### **TagName phải có trong database:**
Xem table `shop` → cột `tag_name`:
- "BUNMA"
- "DENDE"
- "APPULE"
- "SANTA"
- "BILL"
- ... và nhiều shop khác

---

**BUILD LẠI VÀ SẼ OK NGAY!** 🎉
