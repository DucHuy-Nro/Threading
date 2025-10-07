# 🌟 HƯỚNG DẪN: SKILL 9 & NPC SGOHAN

## 📚 **VẤN ĐỀ BÍ KÍP TUYỆT KỸ**

### **Item ID 1229: Bí Kíp Tuyệt Kỹ**
```
Tên: Bí kíp tuyệt kỹ
Mô tả: Gặp Whis tại hành tinh Bill
Type: 27 (không phải sách học skill thông thường)
Icon: 11238
```

### **❌ TẠI SAO KHÔNG HỌC ĐƯỢC?**
- **Item type 7** = Sách học skill thông thường (có thể click học)
- **Item type 27** = Item đặc biệt (KHÔNG thể click học)
- **Item 1229** chỉ dùng để **ĐỔI Ở NPC WHIS** (9999 bí kíp + 10tr vàng + 99 ngọc)

---

## 🛒 **GIẢI PHÁP: NPC SGOHAN BÁN SKILL 9**

### **TÍNH NĂNG:**
✅ **Mua trực tiếp** từ cấp 1 → 10  
✅ **Tự động học** skill vào player  
✅ **Thanh toán bằng Đá Ngũ Sắc** (Item ID: 674)  
✅ **Học tuần tự** (phải học cấp 1 trước cấp 2)  

---

## 💎 **BẢNG GIÁ ĐÁ NGŨ SẮC**

| Cấp | Giá (Đá Ngũ Sắc) | Tăng dame |
|-----|------------------|-----------|
| **Cấp 1** | 1 đá | 550% |
| **Cấp 2** | 100 đá | 600% |
| **Cấp 3** | 200 đá | 650% |
| **Cấp 4** | 500 đá | 700% |
| **Cấp 5** | 1,000 đá | 750% |
| **Cấp 6** | 1,500 đá | 800% |
| **Cấp 7** | 2,000 đá | 850% |
| **Cấp 8** | 2,500 đá | 900% |
| **Cấp 9** | 3,000 đá | 950% |
| **Cấp 10** | 4,000 đá | 1000% |

**Tổng đá để max:** 15,301 đá ngũ sắc

---

## 🎮 **3 TUYỆT KỸ THEO HÀNH TINH**

### **🌍 Trái Đất (Gender 0)**
- **Skill ID:** 24 (SUPER_KAME)
- **Tên:** Super Kamejoko
- **Animation:** Frame 1

### **🟢 Namec (Gender 1)**
- **Skill ID:** 26 (MA_PHONG_BA)
- **Tên:** Ma Phong Ba
- **Animation:** Frame 3

### **🔴 Xayda (Gender 2)**
- **Skill ID:** 25 (LIEN_HOAN_CHUONG)
- **Tên:** Ca Đíc Liên Hoàn Chưởng
- **Animation:** Frame 2

---

## 📋 **LOGIC HOẠT ĐỘNG**

### **1. Mở Menu:**
```
- Hiển thị tên skill theo gender
- Hiển thị cấp hiện tại (0-10)
- Hiển thị số đá ngũ sắc có
```

### **2. Chọn Cấp Muốn Mua:**
```
Menu 1: Cấp 1-5
Menu 2: Cấp 6-10
```

### **3. Kiểm Tra Điều Kiện:**
```java
// Check 1: Đã học cấp này chưa?
if (currentLevel >= targetLevel) → Thông báo đã học

// Check 2: Học tuần tự?
if (targetLevel > currentLevel + 1) → Phải học cấp trước

// Check 3: Đủ đá ngũ sắc?
if (daNguSac < price) → Báo thiếu đá
```

### **4. Mua Skill:**
```java
1. Trừ đá ngũ sắc từ túi
2. Tạo skill mới với cấp đã mua: SkillUtil.createSkill(skillId, level)
3. Set skill vào player: SkillUtil.setSkill(player, newSkill)
4. Thông báo thành công
5. Refresh inventory
```

---

## 🔧 **CÀI ĐẶT**

### **BƯỚC 1: BUILD**
```bash
ant clean && ant jar
```

### **BƯỚC 2: TEST**
1. Vào game
2. Chuẩn bị đá ngũ sắc trong túi
3. Click NPC SGohan
4. Chọn "Cấp 1-5" hoặc "Cấp 6-10"
5. Chọn cấp muốn mua
6. Kiểm tra Bảng Kỹ Năng

---

## 📊 **VÍ DỤ MUA SKILL**

### **Trường hợp 1: Học lần đầu (Cấp 0 → 1)**
```
💎 Đá ngũ sắc: 1000
🎯 Chọn: Cấp 1

✅ Kết quả:
- Trừ: 1 đá
- Còn: 999 đá
- Skill: Cấp 0 → Cấp 1 (550% dame)
```

### **Trường hợp 2: Nâng cấp (Cấp 5 → 6)**
```
💎 Đá ngũ sắc: 2000
📈 Cấp hiện tại: 5
🎯 Chọn: Cấp 6

✅ Kết quả:
- Trừ: 1500 đá
- Còn: 500 đá
- Skill: Cấp 5 → Cấp 6 (800% dame)
```

### **Trường hợp 3: Nhảy cấp (Cấp 2 → 5) ❌**
```
📈 Cấp hiện tại: 2
🎯 Chọn: Cấp 5

❌ Lỗi:
"Bạn phải học tuần tự!
Học cấp 3 trước."
```

---

## 🎯 **SO SÁNH 2 CÁCH HỌC**

| | NPC Whis | NPC SGohan |
|---|----------|------------|
| **Vật phẩm** | 9999 Bí kíp tuyệt kỹ | Đá ngũ sắc |
| **Chi phí** | 10tr vàng + 99 ngọc | 1-4000 đá/cấp |
| **Tỷ lệ thành công** | 100% (cấp 1) | 100% |
| **Tỷ lệ nâng cấp** | 3.33% (1/30) | 100% |
| **Max cấp** | 9 | 10 |
| **Tự động học** | ❌ | ✅ |
| **Nâng tuần tự** | ✅ | ✅ |

---

## 💡 **LƯU Ý**

### **✅ ƯU ĐIỂM:**
- Mua trực tiếp, không cần sưu tầm bí kíp
- 100% thành công, không fail
- Lên cấp 10 (cao hơn Whis 1 cấp)
- Tự động học, không cần click item

### **⚠️ CHÚ Ý:**
- **Phải học tuần tự:** Cấp 1 → 2 → 3 → ... → 10
- **Không hoàn đá:** Đã mua không đổi lại
- **Đá ngũ sắc khó kiếm:** Cân nhắc kỹ trước khi mua
- **Chỉ 1 tuyệt kỹ:** Mỗi hành tinh 1 skill riêng

### **🔄 NẾU MUỐN RESET:**
- Không có cách reset skill 9
- Cân nhắc kỹ trước khi học

---

## 🛠️ **TROUBLESHOOTING**

### **Lỗi 1: "Bạn đã học cấp X rồi!"**
**Nguyên nhân:** Đã mua cấp này  
**Giải pháp:** Mua cấp tiếp theo

### **Lỗi 2: "Bạn phải học tuần tự!"**
**Nguyên nhân:** Nhảy cấp  
**Giải pháp:** Học từng cấp 1

### **Lỗi 3: "Không đủ Đá Ngũ Sắc!"**
**Nguyên nhân:** Thiếu đá  
**Giải pháp:** Farm thêm đá hoặc mua cấp thấp hơn

### **Lỗi 4: "Có lỗi khi học skill!"**
**Nguyên nhân:** Skill template không tồn tại  
**Giải pháp:** Kiểm tra database `skill_template`

---

## 📞 **HỖ TRỢ**

### **Kiểm tra skill hiện tại:**
```sql
-- Xem skill của player
SELECT id, name, skills FROM player WHERE id = [PLAYER_ID];
```

### **Thêm đá ngũ sắc test:**
```sql
-- Thêm 10000 đá ngũ sắc vào túi player
UPDATE player 
SET items_bag = JSON_ARRAY_APPEND(items_bag, '$', JSON_OBJECT(
    'temp_id', 674,
    'quantity', 10000
))
WHERE id = [PLAYER_ID];
```

### **Reset skill về cấp 0:**
```sql
-- Reset skill 9 về cấp 0 (nếu cần)
-- (Phức tạp, liên hệ dev)
```

---

## 🎉 **KẾT LUẬN**

✅ **NPC SGohan** giờ đây bán skill 9 trực tiếp!  
✅ **Không cần bí kíp tuyệt kỹ** nữa!  
✅ **100% thành công**, không fail!  
✅ **Max cấp 10**, mạnh hơn Whis!  

**BUILD & TEST NGAY! 🚀**
