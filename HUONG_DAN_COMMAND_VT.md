# 📍 COMMAND XEM TỌA ĐỘ "VT"

## ✅ **ĐÃ THÊM COMMAND!**

---

## 🎮 **CÁCH SỬ DỤNG:**

### **CỰC KỲ ĐƠN GIẢN:**

1. **Đứng ở vị trí bất kỳ**
2. **Mở chat**
3. **Gõ:** `vt`
4. **Enter**
5. **Thấy thông báo:**
   ```
   📍 VỊ TRÍ HIỆN TẠI
   ━━━━━━━━━━━━━━━━
   🗺️ Map: 0 - Làng Aru
   🔢 Zone: 0
   📐 X: 432
   📐 Y: 336
   ```

---

## 💡 **LỆNH HỖ TRỢ:**

### **2 lệnh giống nhau:**

1. `vt` ← Ngắn gọn, dễ nhớ
2. `toado` ← Dài hơn nhưng rõ nghĩa

**Cả 2 đều OK!**

---

## 🎯 **ÁP DỤNG:**

### **1. Thêm NPC:**

```
Bước 1: Đi đến vị trí muốn đặt NPC
Bước 2: Chat "vt"
Bước 3: Ghi lại: X=500, Y=432
Bước 4: Thêm vào SQL: [49,500,432,4544]
```

### **2. Tạo waypoint:**

```
Bước 1: Đứng tại vị trí muốn làm cửa
Bước 2: Chat "vt"
Bước 3: Ghi lại tọa độ
Bước 4: Thêm vào waypoints
```

---

## 📊 **THÔNG TIN HIỂN THỊ:**

| Thông tin | Ý nghĩa |
|-----------|---------|
| **Map** | ID và tên map hiện tại |
| **Zone** | Zone ID (khu vực trong map) |
| **X** | Tọa độ ngang (0 → rộng map) |
| **Y** | Tọa độ dọc (0 → cao map) |

---

## ✨ **ĐẶC ĐIỂM:**

- ✅ **Không cần admin** - Ai cũng dùng được
- ✅ **Nhanh chóng** - Chỉ 2 ký tự
- ✅ **Chính xác** - Hiển thị cả tên map
- ✅ **Tiện lợi** - Không cần click NPC
- ✅ **Real-time** - Tọa độ cập nhật ngay

---

## 🔨 **BUILD & TEST:**

```bash
# Build
ant clean && ant jar

# Chạy
run.bat
```

---

## 🎬 **DEMO:**

### **Ví dụ 1: Tìm tọa độ đặt NPC**

```
Player: (Đứng ở giữa làng)
Player: vt
Server: 📍 VỊ TRÍ HIỆN TẠI
        🗺️ Map: 0 - Làng Aru
        📐 X: 432
        📐 Y: 336

→ Ghi lại: X=432, Y=336
→ Thêm NPC vào SQL: [49,432,336,4544]
```

---

### **Ví dụ 2: Di chuyển và kiểm tra**

```
Player: (Đi sang phải)
Player: vt
Server: X: 600, Y: 432

Player: (Đi sang trái)
Player: vt
Server: X: 200, Y: 432

→ Tọa độ thay đổi theo vị trí!
```

---

## 💡 **MẸO HAY:**

### **Tìm tọa độ chính giữa map:**

```
1. Vào map
2. Đi về giữa (nhìn kỹ)
3. Chat "vt"
4. Đây là tọa độ giữa map!
```

### **Tìm tọa độ cửa map:**

```
1. Đứng trước cửa đi sang map khác
2. Chat "vt"
3. Đây là tọa độ để tạo waypoint!
```

---

## 📝 **SO SÁNH VỚI COMMAND CŨ:**

### **Command Admin (chỉ admin dùng được):**

```
Admin chat: toado
→ Hiển thị: x: 432 - y: 336
```

### **Command Mới (ai cũng dùng được):**

```
Anyone chat: vt
→ Hiển thị: 
📍 VỊ TRÍ HIỆN TẠI
🗺️ Map: 0 - Làng Aru
🔢 Zone: 0
📐 X: 432
📐 Y: 336
```

**→ Command mới ĐẸP HƠN và CHI TIẾT HƠN!**

---

## 🎉 **HOÀN THÀNH!**

Giờ bạn có thể:
- ✅ Chat "vt" bất kỳ lúc nào
- ✅ Xem tọa độ ngay lập tức
- ✅ Không cần quyền admin
- ✅ Thiết kế map dễ dàng

---

## 🚀 **BUILD NGAY!**

```bash
ant clean && ant jar
run.bat
```

**Vào game → Chat "vt" → Xem tọa độ!** 🎮

---

**CỰC KỲ ĐƠN GIẢN VÀ TIỆN LỢI!** ⚡
