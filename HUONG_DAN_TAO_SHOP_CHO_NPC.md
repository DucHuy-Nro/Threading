# 🛒 HƯỚNG DẪN TẠO SHOP CHO NPC (SIÊU ĐỠN GIẢN)

## 🎯 **2 CÁCH TẠO SHOP:**

---

## ✅ **CÁCH 1: DÙNG SHOP CÓ SẴN (NHANH - 1 PHÚT)**

### **Bước 1: Chọn shop có sẵn**

Các shop có sẵn trong database:
- `BUNMA` - Shop Bulma (Trái Đất)
- `DENDE` - Shop Dende (Namek)
- `APPULE` - Shop Appule (Xayda)
- `KARIN` - Shop Karin
- `QUY_LAO` - Shop Quy Lão Kame
- `SANTA` - Shop Santa
- `BILL` - Shop Bill
- ... và nhiều shop khác

### **Bước 2: Sửa code NPC**

**Ví dụ:** NPC SGohan mở shop Bulma

```java
// File: src/nro/models/npc_list/SGohan.java

package nro.models.npc_list;

import nro.models.npc.Npc;
import nro.models.player.Player;
import nro.models.shop.ShopService; // ← Import này

public class SGohan extends Npc {

    public SGohan(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        super(mapId, status, cx, cy, tempId, avartar);
    }

    @Override
    public void openBaseMenu(Player player) {
        if (canOpenNpc(player)) {
            createOtherMenu(player, 0, 
                "Xin chào! Cần mua gì không?",
                "Mở\nShop",  // ← Menu option
                "Đóng");
        }
    }

    @Override
    public void confirmMenu(Player player, int select) {
        if (canOpenNpc(player)) {
            switch (select) {
                case 0: // Mở shop
                    ShopService.gI().opendShop(player, "BUNMA", true);
                    //                                  ↑↑↑↑↑↑
                    //                              Tên shop có sẵn
                    break;
            }
        }
    }
}
```

### **Bước 3: Build & Test**

```bash
# Build
ant clean && ant jar

# Run
run.bat

# Test in-game → Click NPC → Chọn "Mở Shop" → Shop hiện!
```

**✅ XONG! Đơn giản vậy thôi!** 🎉

---

## 🛠️ **CÁCH 2: TẠO SHOP MỚI (10 PHÚT)**

### **Bước 1: Thêm shop vào database**

Mở **Navicat** → Table `shop`:

```sql
-- Thêm shop mới
INSERT INTO `shop` VALUES (
    NULL,           -- id (auto increment)
    80,             -- npc_id (ID NPC của bạn, VD: 80)
    'SHOP_SGOHAN',  -- tag_name (tên gọi trong code)
    0               -- type_shop (0 = shop thường)
);
```

### **Bước 2: Thêm item vào shop**

Table `item_shop`:

```sql
-- Thêm item vào shop
INSERT INTO `item_shop` VALUES (
    NULL,                  -- id (auto increment)
    (SELECT id FROM shop WHERE tag_name = 'SHOP_SGOHAN'),  -- shop_id
    457,                   -- item_id (457 = Đậu thần)
    10000,                 -- gold (giá vàng, 0 nếu không bán bằng vàng)
    0,                     -- gem (giá ngọc, 0 nếu không bán bằng ngọc)
    1,                     -- is_new (1 = mới, 0 = cũ)
    0,                     -- tab (tab nào: 0, 1, 2...)
    -1,                    -- sale_from (sale từ ngày, -1 = không sale)
    -1                     -- sale_to (sale đến ngày)
);

-- Thêm nhiều item khác
INSERT INTO `item_shop` VALUES (NULL, (SELECT id FROM shop WHERE tag_name = 'SHOP_SGOHAN'), 193, 5000, 0, 1, 0, -1, -1);
-- 193 = Áo giáp

INSERT INTO `item_shop` VALUES (NULL, (SELECT id FROM shop WHERE tag_name = 'SHOP_SGOHAN'), 194, 5000, 0, 1, 0, -1, -1);
-- 194 = Quần giáp

-- ... thêm item khác
```

**Tham khảo ID item:** Xem table `item_template`

### **Bước 3: Sửa code NPC**

```java
// File: SGohan.java

@Override
public void confirmMenu(Player player, int select) {
    if (canOpenNpc(player)) {
        switch (select) {
            case 0: // Mở shop mới
                ShopService.gI().opendShop(player, "SHOP_SGOHAN", true);
                //                                  ↑↑↑↑↑↑↑↑↑↑↑
                //                              Tên shop mới tạo
                break;
        }
    }
}
```

### **Bước 4: Build & Test**

```bash
ant clean && ant jar
run.bat
```

**✅ XONG! Shop riêng của bạn!** 🎉

---

## 📋 **CÁC LOẠI SHOP:**

### **type_shop trong database:**

| type_shop | Loại | Mô tả |
|-----------|------|-------|
| 0 | Shop thường | Mua bằng vàng/ngọc |
| 1 | Shop đặc biệt | (Có logic riêng) |
| 2 | Shop sự kiện | (Giới hạn thời gian) |
| 3 | Shop đổi điểm | Đổi bằng điểm sự kiện |

---

## 🎨 **CÁC VÍ DỤ CODE MẪU:**

### **1. NPC có nhiều shop (tab):**

```java
@Override
public void openBaseMenu(Player player) {
    createOtherMenu(player, 0, 
        "Chọn shop nào?",
        "Shop\nvũ khí",
        "Shop\nphụ kiện", 
        "Đóng");
}

@Override
public void confirmMenu(Player player, int select) {
    switch (select) {
        case 0:
            ShopService.gI().opendShop(player, "BUNMA", true);
            break;
        case 1:
            ShopService.gI().opendShop(player, "SANTA", true);
            break;
    }
}
```

### **2. Shop có điều kiện:**

```java
case 0:
    if (player.nPoint.power >= 10000) {
        ShopService.gI().opendShop(player, "SHOP_VIP", true);
    } else {
        Service.gI().sendThongBao(player, 
            "Cần sức mạnh 10,000 để vào shop!");
    }
    break;
```

### **3. Shop theo gender:**

```java
case 0:
    if (player.gender == ConstPlayer.TRAI_DAT) {
        ShopService.gI().opendShop(player, "BUNMA", true);
    } else if (player.gender == ConstPlayer.NAMEC) {
        ShopService.gI().opendShop(player, "DENDE", true);
    } else {
        ShopService.gI().opendShop(player, "APPULE", true);
    }
    break;
```

---

## 🔍 **TÌM ITEM ID ĐỂ THÊM VÀO SHOP:**

### **Cách 1: Xem trong database**

```sql
-- Xem tất cả item
SELECT id, name, type FROM item_template ORDER BY id;

-- Tìm item theo tên
SELECT id, name, type FROM item_template WHERE name LIKE '%đậu%';

-- Item thường dùng:
-- 457 = Đậu thần
-- 193 = Áo giáp 1 sao
-- 194 = Quần giáp 1 sao
-- 0-6 = Ngọc rồng 1-7 sao
```

### **Cách 2: Xem shop khác có gì**

```sql
-- Xem shop Bulma bán gì
SELECT i.id, i.name, s.gold, s.gem 
FROM item_shop s 
JOIN item_template i ON s.item_id = i.id
WHERE s.shop_id = (SELECT id FROM shop WHERE tag_name = 'BUNMA');
```

---

## ⚡ **TÓM TẮT NHANH:**

### **Muốn nhanh → CÁCH 1:**
1. Import `ShopService`
2. Gọi `ShopService.gI().opendShop(player, "BUNMA", true);`
3. Build & Run
4. **XONG!** ✅

### **Muốn tùy chỉnh → CÁCH 2:**
1. Thêm vào table `shop` (Navicat)
2. Thêm item vào `item_shop` (Navicat)
3. Sửa code NPC gọi shop mới
4. Build & Run
5. **XONG!** ✅

---

## 📝 **CHECKLIST:**

- [ ] Import `ShopService` vào class NPC
- [ ] Thêm menu "Mở shop"
- [ ] Gọi `ShopService.gI().opendShop(...)`
- [ ] (Nếu shop mới) Thêm vào database
- [ ] Build lại project
- [ ] Test in-game

---

**CHÚC BẠN TẠO SHOP THÀNH CÔNG!** 🛒🎉

Bắt đầu với CÁCH 1 (dùng shop có sẵn) sẽ dễ nhất! 😊
