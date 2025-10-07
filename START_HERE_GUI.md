# ⭐ BẮT ĐẦU TẠI ĐÂY - TẠO CỬA SỔ ADMIN PANEL

## 🎯 **MỤC TIÊU:**

Tạo một **CỬA SỔ GUI RIÊNG** để điều khiển server (không phải menu text trong CMD!)

**Khi chạy server:**
- ✅ Cửa sổ 1: CMD với server logs (bình thường)
- ✅ Cửa sổ 2: GUI Admin Panel (cửa sổ mới tự mở) ← **CÁI NÀY BẠN MUỐN!**

---

## 🖼️ **HÌNH DUNG CỬA SỔ GUI:**

```
┌─────────────────────────────────────────────┐
│  🔧 NRO SERVER - ADMIN PANEL 🔧            │
├─────────────────────────────────────────────┤
│  📊 THÔNG TIN SERVER                        │
│  ┌───────────────────────────────────────┐  │
│  │ Server: Ngọc Rồng Online              │  │
│  │ Status: 🟢 Đang hoạt động             │  │
│  │ Players Online: 5 / 1000              │  │
│  │ EXP Rate: x1                          │  │
│  │ Uptime: 06/10/2025 15:30:22           │  │
│  └───────────────────────────────────────┘  │
├─────────────────────────────────────────────┤
│  ⚙️ ĐIỀU KHIỂN                              │
│  ┌───────────────────────────────────────┐  │
│  │  [⏰ BẢO TRÌ 20S]  [👢 KICK ALL]    │  │
│  │                                       │  │
│  │  [⚡ BẢO TRÌ NGAY]  [🔄 LÀM MỚI]    │  │
│  │                                       │  │
│  │  ⭐ Thay đổi EXP: [x1 ▼] [✅ ÁP DỤNG]│  │
│  │                                       │  │
│  │  💡 Panel tự động cập nhật mỗi 2 giây│  │
│  └───────────────────────────────────────┘  │
└─────────────────────────────────────────────┘
```

**→ Bạn chỉ cần CLICK NÚT để điều khiển!** 🖱️

---

## 🚀 **HƯỚNG DẪN (3 BƯỚC ĐƠN GIẢN):**

---

### **📂 BƯỚC 1: TẠO FILE MỚI** (30 giây)

#### **Trong NetBeans:**

1. Mở project **"Threading"**
2. Mở folder: **`Source Packages`** → **`nro.models.server`**
3. **Right-click** vào folder `nro.models.server`
4. Chọn: **New** → **Java Class...**
5. Class Name: **`AdminPanelGUI`**
6. Click **Finish**

**→ File `AdminPanelGUI.java` đã được tạo!**

---

#### **HOẶC tạo thủ công:**

1. Vào folder: `E:\Source NRO by me\Threading\src\nro\models\server\`
2. Tạo file mới tên: **`AdminPanelGUI.java`**

---

### **📝 BƯỚC 2: PASTE CODE VÀO FILE** (1 phút)

1. **MỞ FILE:** `CODE_GUI_ADMIN_PANEL.txt`
2. **COPY TOÀN BỘ CODE** trong file đó (Ctrl+A → Ctrl+C)
3. **MỞ FILE:** `AdminPanelGUI.java` trong NetBeans
4. **XÓA HẾT CODE CŨ** (nếu có)
5. **PASTE CODE MỚI** vào (Ctrl+V)
6. **LƯU FILE** (Ctrl+S)

---

### **🔧 BƯỚC 3: SỬA FILE ServerManager.java** (30 giây)

#### **3a. Tìm method `main`:**

Trong file `ServerManager.java`, tìm method `main` (khoảng **dòng 80-95**):

```java
public static void main(String[] args) {
    try {
        timeStart = TimeUtil.getTimeNow("dd/MM/yyyy HH:mm:ss");
        new Thread(() -> {
            try {
                ServerManager.gI().run();
            } catch (Exception e) {
                Logger.logException(ServerManager.class, e);
            }
        }, "ServerMain").start();

        activeCommandLine();  // ← TÌM DÒNG NÀY!
    } catch (Exception e) {
        Logger.logException(ServerManager.class, e);
    }
}
```

---

#### **3b. THAY THẾ dòng `activeCommandLine();`:**

**Dòng cũ:**
```java
activeCommandLine();
```

**Dòng mới:**
```java
// Mở GUI Admin Panel
AdminPanelGUI.openAdminPanel();
```

---

#### **3c. Code sau khi sửa:**

```java
public static void main(String[] args) {
    try {
        timeStart = TimeUtil.getTimeNow("dd/MM/yyyy HH:mm:ss");
        new Thread(() -> {
            try {
                ServerManager.gI().run();
            } catch (Exception e) {
                Logger.logException(ServerManager.class, e);
            }
        }, "ServerMain").start();

        // Mở GUI Admin Panel
        AdminPanelGUI.openAdminPanel();
    } catch (Exception e) {
        Logger.logException(ServerManager.class, e);
    }
}
```

**Lưu file (Ctrl+S)**

---

## 💾 **BƯỚC 4: LƯU TẤT CẢ VÀ BUILD** (1 phút)

```bash
# Mở CMD
cd "E:\Source NRO by me\Threading"

# Tắt server
taskkill /F /IM java.exe

# Build
ant clean && ant jar
```

**Đợi thấy:**
```
BUILD SUCCESSFUL
Total time: 10 seconds
```

---

## ▶️ **BƯỚC 5: CHẠY SERVER** (5 giây)

```bash
run.bat
```

---

## 🎉 **KẾT QUẢ:**

### **Khi chạy `run.bat`:**

1. **CMD mở** → Server logs chạy bình thường
2. **CỬA SỔ GUI MỚI TỰ ĐỘNG MỞ!** → Admin Panel xuất hiện!

**→ BẠN THẤY 2 CỬA SỔ:**
- Cửa sổ 1: CMD (logs)
- Cửa sổ 2: GUI Admin Panel (điều khiển)

---

## 🎮 **CÁCH SỬ DỤNG:**

### **1. Bảo trì 20s:**
- Click nút **"⏰ BẢO TRÌ 20S"**
- Popup hỏi xác nhận
- Click **"Yes"**
- ✅ Countdown tự động!

---

### **2. Kick All Players:**
- Click nút **"👢 KICK ALL"**
- Popup hỏi xác nhận
- Click **"Yes"**
- ✅ Server tắt ngay!

---

### **3. Đổi EXP:**
- Click dropdown **"x1 ▼"**
- Chọn EXP (x2, x5, x10, x20, x30, x40, x50)
- Click nút **"✅ ÁP DỤNG"**
- Popup hỏi xác nhận
- Click **"Yes"**
- ✅ EXP đã đổi! Thông báo gửi tới tất cả player!

---

### **4. Làm mới thông tin:**
- Click nút **"🔄 LÀM MỚI"**
- ✅ Thông tin cập nhật ngay!
- (Hoặc đợi 2 giây, tự động cập nhật!)

---

## ✨ **ĐẶC ĐIỂM GUI:**

- ✅ **Cửa sổ riêng** - Không chiếm CMD
- ✅ **Giao diện đẹp** - Có màu sắc, icon
- ✅ **Nút bấm** - Dễ dùng, không cần gõ lệnh
- ✅ **Tự động cập nhật** - Thông tin refresh mỗi 2 giây
- ✅ **Xác nhận** - Hỏi trước khi thực hiện
- ✅ **Hover effect** - Nút sáng lên khi rê chuột
- ✅ **Popup thông báo** - Báo thành công/thất bại

---

## 📋 **CHECKLIST HOÀN CHỈNH:**

### **Tạo file:**
- [ ] Mở NetBeans
- [ ] Right-click folder `nro.models.server`
- [ ] New → Java Class
- [ ] Class Name: `AdminPanelGUI`
- [ ] Finish

### **Paste code:**
- [ ] Mở file `CODE_GUI_ADMIN_PANEL.txt`
- [ ] Copy toàn bộ code
- [ ] Mở file `AdminPanelGUI.java`
- [ ] Paste code vào
- [ ] Lưu file (Ctrl+S)

### **Sửa ServerManager:**
- [ ] Mở file `ServerManager.java`
- [ ] Tìm method `main`
- [ ] Tìm dòng `activeCommandLine();`
- [ ] Thay bằng `AdminPanelGUI.openAdminPanel();`
- [ ] Lưu file (Ctrl+S)

### **Build và chạy:**
- [ ] Tắt server
- [ ] Build (ant clean && ant jar)
- [ ] Thấy "BUILD SUCCESSFUL"
- [ ] Chạy (run.bat)
- [ ] Thấy cửa sổ GUI mở ra!

---

## 🆘 **NẾU GẶP LỖI:**

### **Lỗi: Cannot find symbol AdminPanelGUI**

**Nguyên nhân:** File chưa được tạo hoặc đặt sai vị trí

**Fix:**
- Đảm bảo file `AdminPanelGUI.java` nằm trong folder: `src/nro/models/server/`
- Build lại: `ant clean && ant jar`

---

### **Lỗi: Cửa sổ không hiện**

**Nguyên nhân:** Build chưa thành công hoặc code paste sai

**Fix:**
- Kiểm tra NetBeans có báo lỗi đỏ không
- Đảm bảo code paste ĐÚNG VÀO FILE `AdminPanelGUI.java`
- Build lại: `ant clean && ant jar`

---

### **Lỗi: Nút bấm không hoạt động**

**Nguyên nhân:** Code chưa đúng hoặc thiếu dependencies

**Fix:**
- Đảm bảo paste **TOÀN BỘ CODE** từ file `CODE_GUI_ADMIN_PANEL.txt`
- Build lại

---

## 📁 **CÁC FILE CẦN ĐỌC:**

1. ⭐ **`START_HERE_GUI.md`** ← Đang đọc file này!
2. ⭐ **`CODE_GUI_ADMIN_PANEL.txt`** ← Mở để copy code!
3. ⭐ **`HUONG_DAN_TAO_GUI_ADMIN_PANEL.md`** ← Hướng dẫn chi tiết

---

## 🎬 **VIDEO DEMO (Hình dung):**

```
Bước 1: Chạy run.bat
    ↓
CMD mở → Server logs chạy
    ↓
CỬA SỔ GUI TỰ MỞ (2 giây sau)
    ↓
Thấy Admin Panel với nút bấm
    ↓
Click nút "⭐ Thay đổi EXP"
    ↓
Chọn "x50" trong dropdown
    ↓
Click "✅ ÁP DỤNG"
    ↓
Popup xác nhận
    ↓
Click "Yes"
    ↓
✅ EXP đã đổi thành x50!
Popup thông báo thành công!
Tất cả player nhận thông báo!
```

---

## 🎉 **HOÀN THÀNH!**

Chỉ cần:
1. **Tạo file** `AdminPanelGUI.java`
2. **Paste code** từ `CODE_GUI_ADMIN_PANEL.txt`
3. **Sửa 1 dòng** trong `ServerManager.java`
4. **Build** và **chạy**
5. **Enjoy** cửa sổ GUI đẹp!

**→ KHÔNG CẦN HIỂU CODE, CHỈ CẦN COPY-PASTE!** 🚀

---

**MỞ FILE `CODE_GUI_ADMIN_PANEL.txt` NGAY ĐỂ BẮT ĐẦU!** 🎨
