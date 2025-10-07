# 🎨 GUI ADMIN PANEL - NRO SERVER

## 📌 **GIỚI THIỆU**

Tạo một **CỬA SỔ GUI RIÊNG** để điều khiển server NRO với giao diện đẹp, dễ sử dụng!

**Khi chạy server:**
- ✅ **CMD:** Hiển thị server logs (như bình thường)
- ✅ **GUI Window:** Cửa sổ điều khiển (tự động mở) ← **MỚI!**

---

## 🎯 **CHỨC NĂNG**

### **1. Bảo trì 20s** ⏰
- Click nút → Countdown 20 giây
- Thông báo gửi mỗi giây cho tất cả player
- Server tự động tắt sau 20s

### **2. Kick All Players** 👢
- Click nút → Kick tất cả player
- Server tắt ngay lập tức
- Không có countdown

### **3. Thay đổi EXP** ⭐
- Dropdown chọn EXP (x1, x2, x5, x10, x20, x30, x40, x50)
- Click "Áp dụng"
- EXP thay đổi ngay
- Thông báo gửi tới tất cả player

### **4. Thông tin Server** 📊
- Hiển thị: Server name, status, players online, EXP rate, uptime
- Tự động cập nhật mỗi 2 giây
- Nút "Làm mới" để cập nhật thủ công

---

## 🚀 **HƯỚNG DẪN NHANH**

### **Bước 1: Tạo file mới**
```
File: src/nro/models/server/AdminPanelGUI.java
```

### **Bước 2: Paste code**
```
Mở file: CODE_GUI_ADMIN_PANEL.txt
Copy toàn bộ → Paste vào AdminPanelGUI.java
```

### **Bước 3: Sửa ServerManager.java**
```java
// TÌM dòng này (trong method main):
activeCommandLine();

// THAY BẰNG:
AdminPanelGUI.openAdminPanel();
```

### **Bước 4: Build & Run**
```bash
ant clean && ant jar
run.bat
```

**→ CỬA SỔ GUI SẼ TỰ ĐỘNG MỞ!** 🎉

---

## 📁 **CÁC FILE HƯỚNG DẪN**

| File | Mô tả |
|------|-------|
| **README.md** | File này - Tổng quan |
| **TOM_TAT_NHANH.md** | Tóm tắt 3 bước |
| **START_HERE_GUI.md** | Hướng dẫn chi tiết từng bước |
| **CODE_GUI_ADMIN_PANEL.txt** | Code để paste vào file mới |
| **HUONG_DAN_TAO_GUI_ADMIN_PANEL.md** | Hướng dẫn đầy đủ |
| **DEMO_GUI.txt** | Demo giao diện & cách dùng |

---

## 🎯 **BẮT ĐẦU TỪ ĐÂU?**

### **Nếu bạn muốn nhanh:**
1. Đọc: **`TOM_TAT_NHANH.md`**
2. Copy code từ: **`CODE_GUI_ADMIN_PANEL.txt`**
3. Làm theo 3 bước

### **Nếu bạn muốn hướng dẫn chi tiết:**
1. Đọc: **`START_HERE_GUI.md`**
2. Xem demo: **`DEMO_GUI.txt`**
3. Làm theo hướng dẫn từng bước

---

## 🖼️ **DEMO GUI**

```
┌───────────────────────────────────────────────┐
│  🔧 NRO SERVER - ADMIN PANEL 🔧              │
├───────────────────────────────────────────────┤
│  📊 THÔNG TIN SERVER                          │
│  ┌─────────────────────────────────────────┐  │
│  │ Server: Ngọc Rồng Online                │  │
│  │ Status: 🟢 Đang hoạt động               │  │
│  │ Players Online: 25 / 1000               │  │
│  │ EXP Rate: x1                            │  │
│  │ Uptime: 06/10/2025 15:30:22             │  │
│  └─────────────────────────────────────────┘  │
├───────────────────────────────────────────────┤
│  ⚙️ ĐIỀU KHIỂN                                │
│  ┌─────────────────────────────────────────┐  │
│  │  [⏰ BẢO TRÌ 20S]  [👢 KICK ALL]       │  │
│  │  [⚡ BẢO TRÌ NGAY]  [🔄 LÀM MỚI]       │  │
│  │  ⭐ Thay đổi EXP: [x1▼] [✅ ÁP DỤNG]   │  │
│  └─────────────────────────────────────────┘  │
└───────────────────────────────────────────────┘
```

---

## ✨ **ĐẶC ĐIỂM**

- ✅ **Cửa sổ riêng** - Không chiếm CMD
- ✅ **Giao diện đẹp** - Màu sắc, viền, icon
- ✅ **Dễ sử dụng** - Chỉ cần click nút
- ✅ **Tự động cập nhật** - Mỗi 2 giây
- ✅ **An toàn** - Xác nhận trước khi thực hiện
- ✅ **Popup thông báo** - Báo thành công/lỗi

---

## 🆘 **HỖ TRỢ**

### **Lỗi: Cannot find symbol AdminPanelGUI**
→ File chưa được tạo hoặc đặt sai vị trí  
→ Build lại: `ant clean && ant jar`

### **Lỗi: Cửa sổ không hiện**
→ Code paste sai hoặc build chưa thành công  
→ Kiểm tra lỗi trong NetBeans, build lại

### **Lỗi: Nút không hoạt động**
→ Paste chưa đủ code  
→ Đảm bảo paste TOÀN BỘ code từ file `CODE_GUI_ADMIN_PANEL.txt`

---

## 📝 **GHI CHÚ**

- Chỉ cần sửa **2 file**: Tạo mới `AdminPanelGUI.java` và sửa 1 dòng trong `ServerManager.java`
- Không cần hiểu code, chỉ cần **copy-paste** đúng
- GUI hoạt động độc lập với server logs
- Có thể đóng GUI mà không ảnh hưởng server

---

## 🎉 **BẮT ĐẦU NGAY!**

```
1. Mở file: TOM_TAT_NHANH.md
2. Làm theo 3 bước
3. Enjoy GUI đẹp! 🎨
```

---

**MỞ FILE `START_HERE_GUI.md` ĐỂ BẮT ĐẦU!** 🚀
