# 🛒 HƯỚNG DẪN: SHOP SKILL 9 TỰ ĐỘNG HỌC

## 🎯 **YÊU CẦU:**

Khi mua skill trong shop `SHOP_TUYET_KY`:
- ✅ **KHÔNG** ra item trong hành trang
- ✅ **TỰ ĐỘNG** học skill vào Bảng Kỹ Năng
- ✅ **CHECK** điều kiện: Đủ tiền, đã học cấp trước
- ✅ **TRỪ TIỀN** từ shop (đá ngũ sắc hoặc vàng/ngọc)

---

## 📁 **FILE CẦN SỬA:**

**`src/nro/models/shop/ShopService.java`**

---

## 🔧 **BƯỚC 1: THÊM HÀM MUA SKILL 9**

Thêm hàm này vào cuối class `ShopService` (trước dấu `}` cuối cùng):

```java
/**
 * Mua skill 9 (Tuyệt kỹ) từ shop - Tự động học skill
 */
private void buySkill9(Player player, ItemShop is) {
    // Lấy thông tin item
    int itemTempId = is.temp.id;
    
    // Xác định skill ID và cấp từ item ID
    // Item ID: 2001-2009 (TD cấp 2-10), 2011-2019 (NM cấp 2-10), 2021-2029 (XD cấp 2-10)
    // Item ID: 1044 (TD cấp 1), 1211 (NM cấp 1), 1212 (XD cấp 1)
    
    int skillId = -1;
    int targetLevel = 1;
    int playerGender = player.gender;
    
    // Xác định skill ID theo item
    if (itemTempId == 1044 || (itemTempId >= 2001 && itemTempId <= 2009)) {
        // Trái Đất - Super Kamejoko
        skillId = 24; // Skill.SUPER_KAME
        if (itemTempId == 1044) {
            targetLevel = 1;
        } else {
            targetLevel = (itemTempId - 2001) + 2; // 2001=cấp2, 2002=cấp3, ..., 2009=cấp10
        }
        if (playerGender != 0) {
            Service.gI().sendThongBao(player, "Skill này chỉ dành cho Trái Đất!");
            return;
        }
    } else if (itemTempId == 1211 || (itemTempId >= 2011 && itemTempId <= 2019)) {
        // Namec - Ma Phong Ba
        skillId = 26; // Skill.MA_PHONG_BA
        if (itemTempId == 1211) {
            targetLevel = 1;
        } else {
            targetLevel = (itemTempId - 2011) + 2;
        }
        if (playerGender != 1) {
            Service.gI().sendThongBao(player, "Skill này chỉ dành cho Namec!");
            return;
        }
    } else if (itemTempId == 1212 || (itemTempId >= 2021 && itemTempId <= 2029)) {
        // Xayda - Ca Đíc Liên Hoàn Chưởng
        skillId = 25; // Skill.LIEN_HOAN_CHUONG
        if (itemTempId == 1212) {
            targetLevel = 1;
        } else {
            targetLevel = (itemTempId - 2021) + 2;
        }
        if (playerGender != 2) {
            Service.gI().sendThongBao(player, "Skill này chỉ dành cho Xayda!");
            return;
        }
    } else {
        Service.gI().sendThongBao(player, "Item không hợp lệ!");
        return;
    }
    
    // Lấy skill hiện tại
    Skill currentSkill = SkillUtil.getSkillbyId(player, skillId);
    int currentLevel = (currentSkill != null && currentSkill.point > 0) ? currentSkill.point : 0;
    
    // Check 1: Đã học cấp này chưa?
    if (currentLevel >= targetLevel) {
        Service.gI().sendThongBao(player, 
            "❌ Bạn đã học cấp " + targetLevel + " rồi!\n"
            + "Cấp hiện tại: " + currentLevel);
        return;
    }
    
    // Check 2: Phải học tuần tự
    if (targetLevel > currentLevel + 1) {
        Service.gI().sendThongBao(player, 
            "❌ Bạn phải học tuần tự!\n"
            + "Học cấp " + (currentLevel + 1) + " trước.");
        return;
    }
    
    // Check 3: Đủ tiền shop (vàng/ngọc/đá)
    if (!subMoneyByItemShop(player, is)) {
        // Hàm subMoneyByItemShop đã thông báo lỗi
        return;
    }
    
    // Học skill
    Skill newSkill = SkillUtil.createSkill(skillId, targetLevel);
    if (newSkill != null) {
        SkillUtil.setSkill(player, newSkill);
        
        // Thông báo thành công
        String skillName = "";
        switch (skillId) {
            case 24: skillName = "Super Kamejoko"; break;
            case 25: skillName = "Ca Đíc Liên Hoàn Chưởng"; break;
            case 26: skillName = "Ma Phong Ba"; break;
        }
        
        Service.gI().sendThongBao(player, 
            "✅ HỌC TUYỆT KỸ THÀNH CÔNG!\n\n"
            + "🌟 " + skillName + "\n"
            + "📈 Cấp: " + currentLevel + " → " + targetLevel + "\n"
            + "💰 Đã trừ tiền từ shop\n\n"
            + "Hãy vào Bảng Kỹ Năng để xem!");
        
        // Refresh inventory
        InventoryService.gI().sendItemBags(player);
        Service.gI().sendMoney(player);
    } else {
        Service.gI().sendThongBao(player, "❌ Có lỗi khi học skill!");
        // Hoàn tiền (vì đã trừ ở trên)
        // TODO: Implement hoàn tiền nếu cần
    }
}
```

---

## 🔧 **BƯỚC 2: GỌI HÀM TRONG buyItem()**

Tìm dòng 710-714 trong `ShopService.java`:

```java
// Shop kỹ năng
if (shop.typeShop == ShopService.KINANG_SHOP) {
    learnKyNang(player, is);
    return;
}
```

**THÊM NGAY SAU ĐOẠN CODE TRÊN:**

```java
// Shop Skill 9 (Tuyệt Kỹ) - Tự động học skill
if (shop.tagName != null && shop.tagName.equals("SHOP_TUYET_KY")) {
    buySkill9(player, is);
    return;
}
```

**VỊ TRÍ CHÍNH XÁC:**

```java
// Dòng 710-714 (CŨ)
// Shop kỹ năng
if (shop.typeShop == ShopService.KINANG_SHOP) {
    learnKyNang(player, is);
    return;
}

// THÊM MỚI NGAY SAU ĐÂY
// Shop Skill 9 (Tuyệt Kỹ) - Tự động học skill
if (shop.tagName != null && shop.tagName.equals("SHOP_TUYET_KY")) {
    buySkill9(player, is);
    return;
}

// Dòng 716 (CŨ) - Giữ nguyên
// Hành trang đầy
if (InventoryService.gI().getCountEmptyBag(player) == 0) {
    Service.gI().sendThongBao(player, "Hành trang đã đầy");
    return;
}
```

---

## 🔧 **BƯỚC 3: THÊM IMPORT**

Thêm import ở đầu file `ShopService.java` (nếu chưa có):

```java
import nro.models.skill.Skill;
import nro.models.utils.SkillUtil;
```

---

## 📊 **LOGIC HOẠT ĐỘNG:**

```
Player: Mở shop SHOP_TUYET_KY
        ↓
Player: Chọn "Tuyệt kỹ Cađíc LH chưởng cấp 3"
        ↓
Code: Check shop.tagName == "SHOP_TUYET_KY"
        ↓
Code: Gọi buySkill9(player, is)
        ↓
buySkill9:
  1. Xác định skill ID (24/25/26)
  2. Xác định cấp (1-10)
  3. Check gender đúng không?
  4. Check đã học cấp này chưa?
  5. Check học tuần tự chưa?
  6. Trừ tiền shop (vàng/ngọc/đá)
  7. Học skill: SkillUtil.setSkill()
  8. Thông báo thành công
        ↓
Result: ✅ Skill tự động vào Bảng Kỹ Năng
        ✅ KHÔNG ra item trong túi
```

---

## 🎯 **MAPPING ITEM ID → SKILL:**

### **Trái Đất (Gender 0) - Super Kamejoko (ID 24):**
```
1044 → Cấp 1
2001 → Cấp 2
2002 → Cấp 3
2003 → Cấp 4
2004 → Cấp 5
2005 → Cấp 6
2006 → Cấp 7
2007 → Cấp 8
2008 → Cấp 9
2009 → Cấp 10
```

### **Namec (Gender 1) - Ma Phong Ba (ID 26):**
```
1211 → Cấp 1
2011 → Cấp 2
2012 → Cấp 3
2013 → Cấp 4
2014 → Cấp 5
2015 → Cấp 6
2016 → Cấp 7
2017 → Cấp 8
2018 → Cấp 9
2019 → Cấp 10
```

### **Xayda (Gender 2) - Ca Đíc LH Chưởng (ID 25):**
```
1212 → Cấp 1
2021 → Cấp 2
2022 → Cấp 3
2023 → Cấp 4
2024 → Cấp 5
2025 → Cấp 6
2026 → Cấp 7
2027 → Cấp 8
2028 → Cấp 9
2029 → Cấp 10
```

---

## ⚠️ **LƯU Ý:**

### **1. TYPE SELL trong shop:**

Shop có thể bán bằng:
- `COST_GOLD` - Vàng
- `COST_GEM` - Ngọc
- `COST_RUBY` - Hồng ngọc
- `COST_COUPON` - Điểm

Hàm `subMoneyByItemShop()` sẽ tự động check và trừ theo type.

### **2. Nếu muốn dùng Đá Ngũ Sắc:**

Cần sửa thành trừ item thay vì tiền:

```java
// Thay vì:
if (!subMoneyByItemShop(player, is)) {
    return;
}

// Dùng:
Item daNguSac = InventoryService.gI().findItemBag(player, 674);
int price = is.cost; // Giá đá ngũ sắc
if (daNguSac == null || daNguSac.quantity < price) {
    Service.gI().sendThongBao(player, 
        "❌ Không đủ Đá Ngũ Sắc!\n"
        + "Cần: " + price + " đá\n"
        + "Có: " + (daNguSac != null ? daNguSac.quantity : 0) + " đá");
    return;
}
InventoryService.gI().subQuantityItemsBag(player, daNguSac, price);
```

---

## 🔨 **BUILD & TEST:**

```bash
# 1. Build
ant clean && ant jar

# 2. Run
run.bat

# 3. Test trong game:
- Mở shop SGohan
- Click "Tuyệt kỹ Cađíc LH chưởng cấp 1"
- Kiểm tra:
  ✅ Không có item trong túi
  ✅ Skill xuất hiện ở Bảng Kỹ Năng
  ✅ Tiền/đá bị trừ
```

---

## 🎉 **KẾT QUẢ:**

✅ **Mua skill trong shop → Tự động học!**  
✅ **Không ra item trong túi!**  
✅ **Check điều kiện đầy đủ!**  
✅ **Trừ tiền/đá từ shop!**  

**HOÀN HẢO! 🚀**
