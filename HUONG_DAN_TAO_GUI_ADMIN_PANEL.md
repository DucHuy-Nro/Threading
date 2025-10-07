# 🎨 HƯỚNG DẪN TẠO GUI ADMIN PANEL (CỬA SỔ RIÊNG)

## 🎯 **MỤC TIÊU:**

Tạo một **CỬA SỔ RIÊNG** (GUI) để điều khiển server, bao gồm:
- ✅ Nút bấm "Bảo trì 20s"
- ✅ Nút bấm "Kick All Players"
- ✅ Menu dropdown chọn EXP (x1, x2, x5, ... x50)
- ✅ Hiển thị thông tin server (players online, EXP hiện tại, uptime)
- ✅ Tự động cập nhật mỗi 2 giây

---

## 📂 **BƯỚC 1: TẠO FILE MỚI**

### **1a. Tạo file trong NetBeans:**

1. Mở **NetBeans**
2. Right-click folder: `nro.models.server`
3. **New** → **Java Class**
4. Class Name: `AdminPanelGUI`
5. Click **Finish**

### **1b. File sẽ được tạo tại:**

```
src/nro/models/server/AdminPanelGUI.java
```

---

## 📝 **BƯỚC 2: PASTE CODE VÀO FILE MỚI**

**MỞ FILE:** `CODE_GUI_ADMIN_PANEL.txt`

**COPY TOÀN BỘ CODE** và paste vào file `AdminPanelGUI.java`

(Xóa hết code cũ trong file, paste code mới vào)

---

## 🔧 **BƯỚC 3: SỬA FILE ServerManager.java**

### **Tìm method `main`** (khoảng dòng 80-95):

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

        activeCommandLine();  // ← TÌM DÒNG NÀY
    } catch (Exception e) {
        Logger.logException(ServerManager.class, e);
    }
}
```

### **THAY THẾ dòng `activeCommandLine();` bằng:**

```java
// Mở GUI Admin Panel
AdminPanelGUI.openAdminPanel();
```

### **Code sau khi sửa:**

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

---

## 💾 **BƯỚC 4: LƯU TẤT CẢ FILE**

- Lưu `AdminPanelGUI.java` (Ctrl+S)
- Lưu `ServerManager.java` (Ctrl+S)

---

## 🔨 **BƯỚC 5: BUILD**

```bash
cd "E:\Source NRO by me\Threading"
taskkill /F /IM java.exe
ant clean && ant jar
```

---

## ▶️ **BƯỚC 6: CHẠY SERVER**

```bash
run.bat
```

---

## 🖼️ **KẾT QUẢ:**

Khi chạy `run.bat`:

1. **CMD hiện logs server** (như bình thường)
2. **CỬA SỔ GUI MỚI MỞ RA** với giao diện:

```
╔═══════════════════════════════════════════════╗
║        🔧 NRO SERVER - ADMIN PANEL 🔧        ║
╠═══════════════════════════════════════════════╣
║  Server: Ngọc Rồng Online                     ║
║  Status: 🟢 Đang hoạt động                    ║
║  Players Online: 5 / 1000                     ║
║  EXP Rate: x1                                 ║
║  Uptime: 06/10/2025 15:30:22                  ║
╠═══════════════════════════════════════════════╣
║                                               ║
║  [ ⏰ BẢO TRÌ 20S ]   [ 👢 KICK ALL ]        ║
║                                               ║
║  [ ⚡ BẢO TRÌ NGAY ]   [ 🔄 LÀM MỚI ]        ║
║                                               ║
║  ⭐ Thay đổi EXP: [▼ x1  ]  [ ✅ ÁP DỤNG ]   ║
║                                               ║
╚═══════════════════════════════════════════════╝
```

---

## 🎮 **CÁCH SỬ DỤNG:**

### **Bảo trì 20s:**
- Click nút **"⏰ BẢO TRÌ 20S"**
- Popup hỏi xác nhận
- Click **"Yes"**
- Countdown tự động!

### **Kick All Players:**
- Click nút **"👢 KICK ALL"**
- Popup hỏi xác nhận
- Click **"Yes"**
- Server tắt ngay!

### **Đổi EXP:**
- Click dropdown **"x1 ▼"**
- Chọn EXP (x2, x5, x10, ... x50)
- Click nút **"✅ ÁP DỤNG"**
- EXP đã đổi!

### **Làm mới thông tin:**
- Click nút **"🔄 LÀM MỚI"**
- Thông tin cập nhật (hoặc tự động mỗi 2s)

---

## ✨ **ĐẶC ĐIỂM:**

- ✅ Cửa sổ riêng, không chiếm CMD
- ✅ Tự động cập nhật thông tin mỗi 2 giây
- ✅ Giao diện đẹp, dễ sử dụng
- ✅ Có nút bấm, không cần gõ lệnh
- ✅ Hỏi xác nhận trước khi thực hiện

---

## 📋 **CHECKLIST:**

- [ ] Tạo file `AdminPanelGUI.java`
- [ ] Paste code từ `CODE_GUI_ADMIN_PANEL.txt`
- [ ] Sửa `ServerManager.java` (thay `activeCommandLine()` bằng `AdminPanelGUI.openAdminPanel()`)
- [ ] Lưu tất cả file
- [ ] Build (ant clean && ant jar)
- [ ] Chạy (run.bat)
- [ ] Thấy cửa sổ GUI mở ra!

---

**TIẾP THEO: MỞ FILE `CODE_GUI_ADMIN_PANEL.txt` ĐỂ COPY CODE!** 🚀
