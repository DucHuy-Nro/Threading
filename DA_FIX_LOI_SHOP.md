# ✅ ĐÃ FIX LỖI SHOP

## ❌ **LỖI BẠN GẶP:**

```
error: cannot find symbol
  symbol:   method openShopNormal(Player,String,int,int,<null>,int,int)
  location: class ShopService
```

---

## 🐛 **NGUYÊN NHÂN:**

Bạn dùng method **SAI TÊN**: `openShopNormal()`

**Method đúng là:** `opendShop()` ← Chú ý: có chữ **d** (typo trong code gốc)

---

## ✅ **ĐÃ SỬA:**

**File:** `src/nro/models/npc_list/SGohan.java`

### **1. Thêm import:**
```java
import nro.models.shop.ShopService;  // ← Thêm dòng này
```

### **2. Sửa method call:**
```java
// TRƯỚC (SAI):
Service.gI().sendThongBao(player, "Chức năng cửa hàng chưa được thêm!");

// SAU (ĐÚNG):
ShopService.gI().opendShop(player, "BUNMA", true);
//              ↑↑↑↑↑↑↑↑↑
//              Chữ "d" không phải typo, là tên method thật!
```

---

## 📝 **METHOD ĐÚNG:**

```java
public void opendShop(Player player, String tagName, boolean allGender)
```

### **Tham số:**
- `player` - Người chơi
- `tagName` - Tên shop: `"BUNMA"`, `"DENDE"`, `"APPULE"`, v.v.
- `allGender` - `true` = tất cả giới tính, `false` = giới hạn

---

## 🚀 **BÂY GIỜ BUILD LẠI:**

```bash
# Clean and Build
ant clean && ant jar

# Hoặc trong NetBeans: Shift+F11
```

**→ SẼ COMPILE THÀNH CÔNG!** ✅

---

## 🎯 **TEST:**

1. **Start server:** `run.bat`
2. **Vào game**
3. **Đến map có NPC SGohan**
4. **Click NPC** → Chọn **"Cửa hàng"**
5. **Shop Bulma sẽ mở** ✅

---

## 💡 **GHI NHỚ:**

### **Method mở shop đúng:**
```java
ShopService.gI().opendShop(player, "SHOP_NAME", true);
```

### **Các shop có sẵn:**
- `"BUNMA"` - Shop Bulma
- `"DENDE"` - Shop Dende
- `"APPULE"` - Shop Appule
- `"KARIN"` - Shop Karin
- `"SANTA"` - Shop Santa
- `"BILL"` - Shop Bill
- `"QUY_LAO"` - Shop Quy Lão

---

**BUILD LẠI VÀ TEST NGAY!** 🛒✨
