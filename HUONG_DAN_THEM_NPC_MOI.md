# 🚀 HƯỚNG DẪN THÊM NPC MỚI (NHANH - 5 PHÚT)

## 📋 **CÂU HỎI THƯỜNG GẶP:**

### ❓ **Tôi có cần tạo class trong source code không?**

**Trả lời:**

| Mục đích | Chỉ thêm DB | Thêm DB + Class |
|----------|-------------|-----------------|
| NPC trang trí (đứng im) | ✅ **ĐỦ RỒI** | ❌ Không cần |
| NPC có chức năng (shop, quest...) | ❌ Không đủ | ✅ **BẮT BUỘC** |

**Kết luận:**
- 🎨 **NPC trang trí** → Chỉ cần thêm vào database
- 🛠️ **NPC có chức năng** → Phải tạo class (3 bước đơn giản)

---

## 🎨 **CÁCH 1: THÊM NPC TRANG TRÍ (CHỈ CẦN DATABASE)**

### **Bước 1: Thêm vào `npc_template`**

```sql
-- Mở Navicat → Table npc_template → Insert
INSERT INTO `npc_template` VALUES (
    120,                    -- ID (dùng số chưa có)
    'Tên NPC',             -- Tên hiển thị
    100,                   -- head (sprite đầu)
    101,                   -- body (sprite thân)
    102,                   -- leg (sprite chân)
    1000                   -- avatar (icon)
);
```

### **Bước 2: Thêm vào map**

```sql
-- Mở Navicat → Table map_template → Tìm map muốn thêm
-- Sửa cột 'data' → Thêm vào mảng JSON:

VD: Thêm vào map ID 5
Tìm dòng: [[39,984,408],[13,1068,408],...]
Thêm: [120,500,300]  ← [npcId, x, y]

Kết quả: [[39,984,408],[13,1068,408],[120,500,300],...]
```

### **Bước 3: Restart server**

```bash
run.bat
```

**✅ XONG!** NPC sẽ hiển thị nhưng không làm gì khi click.

---

## 🛠️ **CÁCH 2: THÊM NPC CÓ CHỨC NĂNG (3 BƯỚC)**

### **Bước 1: Thêm vào database (như trên)**

```sql
INSERT INTO `npc_template` VALUES (121, 'Shop Gạo', 100, 101, 102, 1000);
```

### **Bước 2: Tạo class NPC**

Copy file `TEMPLATE_NPC_MOI.java` → Đổi tên thành `ShopGao.java`

```java
// File: src/nro/models/npc_list/ShopGao.java
package nro.models.npc_list;

import nro.models.npc.Npc;
import nro.models.player.Player;
import nro.models.services.Service;
import nro.models.shop.ShopService;

public class ShopGao extends Npc {

    public ShopGao(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        super(mapId, status, cx, cy, tempId, avartar);
    }

    @Override
    public void openBaseMenu(Player player) {
        if (canOpenNpc(player)) {
            createOtherMenu(player, 0, 
                "Xin chào! Tôi bán gạo",
                "Mua gạo",
                "Đóng");
        }
    }

    @Override
    public void confirmMenu(Player player, int select) {
        if (canOpenNpc(player)) {
            switch (select) {
                case 0: // Mua gạo
                    ShopService.gI().openShopNormal(player, 
                        "Shop Gạo", 0, -1, null, 0, 0);
                    break;
            }
        }
    }
}
```

### **Bước 3: Đăng ký NPC**

#### **3a. Thêm constant (`ConstNpc.java`):**

```java
// File: src/nro/models/consts/ConstNpc.java
// Tìm dòng cuối cùng của phần NPC IDs, thêm:

public static final byte SHOP_GAO = 121;
```

#### **3b. Import class (`NpcFactory.java`):**

```java
// File: src/nro/models/npc/NpcFactory.java
// Tìm phần imports, thêm:

import nro.models.npc_list.ShopGao;
```

#### **3c. Thêm case xử lý (`NpcFactory.java`):**

```java
// Tìm dòng "case ConstNpc.SGOHAN ->" (khoảng dòng 239)
// Thêm case mới TRƯỚC "default ->":

case ConstNpc.SHOP_GAO ->
    new ShopGao(mapId, status, cx, cy, tempId, avatar);
```

### **Bước 4: Build & Run**

```bash
# NetBeans: Clean and Build
ant clean && ant jar

# Restart server
run.bat
```

**✅ XONG!** NPC có đầy đủ chức năng!

---

## 📝 **CHECKLIST THÊM NPC MỚI:**

### **NPC TRANG TRÍ (Không cần code):**
- [ ] Thêm vào `npc_template` (Navicat)
- [ ] Thêm vào `map_template` (Navicat)
- [ ] Restart server
- [ ] Test in-game

### **NPC CÓ CHỨC NĂNG (Cần 3 bước code):**
- [ ] Thêm vào `npc_template` (Navicat)
- [ ] Thêm vào `map_template` (Navicat)
- [ ] Tạo class `TenNpc.java` trong `npc_list/`
- [ ] Thêm constant trong `ConstNpc.java`
- [ ] Import trong `NpcFactory.java`
- [ ] Thêm case trong `NpcFactory.java`
- [ ] Clean and Build
- [ ] Restart server
- [ ] Test in-game

---

## 🎯 **CÁC VÍ DỤ THỰC TẾ:**

### **1. NPC Bán Đồ:**
```java
@Override
public void openBaseMenu(Player player) {
    ShopService.gI().openShopNormal(player, 
        "Cửa hàng", 0, -1, null, 0, 0);
}
```

### **2. NPC Dịch Chuyển:**
```java
@Override
public void confirmMenu(Player player, int select) {
    if (select == 0) {
        ChangeMapService.gI().changeMap(player, 5, -1, 100, 200);
    }
}
```

### **3. NPC Đổi Vàng Lấy Ngọc:**
```java
case 0:
    if (player.inventory.gold >= 100000) {
        player.inventory.gold -= 100000;
        player.inventory.gem += 10;
        Service.gI().sendMoney(player);
        Service.gI().sendThongBao(player, "Đổi thành công!");
    } else {
        Service.gI().sendThongBao(player, "Không đủ vàng!");
    }
    break;
```

### **4. NPC Tặng Quà:**
```java
import nro.models.item.Item;
import nro.models.services.ItemService;
import nro.models.services.InventoryService;

case 0:
    Item item = ItemService.gI().createNewItem((short) 457);
    item.quantity = 99;
    InventoryService.gI().addItemBag(player, item);
    InventoryService.gI().sendItemBags(player);
    Service.gI().sendThongBao(player, "Nhận 99 đậu!");
    break;
```

---

## ⚡ **TÓM TẮT SIÊU NHANH:**

### **Chỉ muốn NPC đứng trang trí:**
```
1. Thêm vào npc_template (Navicat)
2. Thêm vào map_template (Navicat)
3. Restart → XONG!
```

### **Muốn NPC có chức năng:**
```
1. Thêm vào database
2. Copy TEMPLATE_NPC_MOI.java → Đổi tên
3. Sửa menu + chức năng
4. Thêm vào ConstNpc.java (1 dòng)
5. Thêm vào NpcFactory.java (2 dòng)
6. Build → Restart → XONG!
```

---

## 💡 **MẸO HAY:**

1. **Copy NPC có sẵn** làm mẫu:
   - Shop → Copy từ `Bulma.java`
   - Nhiệm vụ → Copy từ `OngGohan.java`
   - Dịch chuyển → Copy từ `ThanVuTru.java`

2. **Dùng template** đã tạo sẵn:
   - `TEMPLATE_NPC_MOI.java` có đầy đủ code mẫu

3. **Test nhanh:**
   - Thêm DB trước → Test hiển thị
   - Sau đó mới code chức năng

---

## ❓ **FAQ:**

**Q: Tôi thêm vào DB rồi nhưng NPC không hiện?**
- Kiểm tra ID có trùng không
- Kiểm tra tọa độ (x, y) trong map
- Restart server chưa?

**Q: NPC hiện nhưng click vào không có gì?**
- Bình thường! Vì bạn chưa tạo class
- Muốn có menu → Tạo class (3 bước)

**Q: Tôi có thể dùng ID bao nhiêu?**
- Bất kỳ ID nào chưa dùng (120, 200, 500, 999...)
- Nhờ đã đổi sang HashMap → Không giới hạn!

**Q: Tạo class có khó không?**
- Không! Chỉ copy template + sửa 5-10 dòng
- Tốn ~5 phút

---

**🎉 CHÚC BẠN THÀNH CÔNG!**

File `TEMPLATE_NPC_MOI.java` có đầy đủ code mẫu, bạn chỉ việc copy và sửa! 😊
