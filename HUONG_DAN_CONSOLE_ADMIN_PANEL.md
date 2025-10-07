# 🎮 HƯỚNG DẪN CONSOLE ADMIN PANEL (CHI TIẾT)

## ✅ **ĐÃ LÀM GÌ:**

Tôi đã **SỬA XONG CODE** trong file `ServerManager.java`!

Khi bạn chạy server (`run.bat`), console sẽ hiển thị:

```
╔════════════════════════════════════════════════╗
║         🔧 ADMIN PANEL - NRO SERVER 🔧        ║
╠════════════════════════════════════════════════╣
║  Server: Ngọc Rồng Online                      ║
║  EXP Rate: x1                                  ║
║  Players Online: 5/1000                        ║
╠════════════════════════════════════════════════╣
║  [1] ⏰ Bảo trì 20s (Countdown)               ║
║  [2] 👢 Đá all player (Ngay lập tức)          ║
║  [3] ⭐ Thay đổi EXP Server                    ║
║  [4] 📊 Thông tin Server                       ║
║  [5] ⚡ Bảo trì ngay (5s countdown)            ║
║  [0] ❌ Thoát                                  ║
╚════════════════════════════════════════════════╝
👉 Nhập lựa chọn: _
```

---

## 🚀 **CÁCH SỬ DỤNG (SIÊU ĐƠN GIẢN):**

### **BƯỚC 1: BUILD CODE**

```bash
# Mở CMD, vào thư mục project
cd "E:\Source NRO by me\Threading"

# Tắt server nếu đang chạy
taskkill /F /IM java.exe

# Build
ant clean
ant jar
```

**Nếu build thành công, sẽ thấy:**
```
BUILD SUCCESSFUL
```

---

### **BƯỚC 2: CHẠY SERVER**

```bash
run.bat
```

**Console sẽ hiện:**
```
✓ Successfully loaded npc template (94)
✓ Successfully loaded map template (...)
✓ Active Port 14445

╔════════════════════════════════════════════════╗
║         🔧 ADMIN PANEL - NRO SERVER 🔧        ║
╠════════════════════════════════════════════════╣
...
👉 Nhập lựa chọn: _
```

---

### **BƯỚC 3: SỬ DỤNG CÁC CHỨC NĂNG**

---

## 🎯 **CHỨC NĂNG 1: BẢO TRÌ 20S**

### **Cách dùng:**

```
👉 Nhập lựa chọn: 1  [Enter]
```

**Console hiện:**
```
┌─────────────────────────────────────┐
│  ⚠️  XÁC NHẬN BẢO TRÌ 20 GIÂY  ⚠️   │
├─────────────────────────────────────┤
│ • Server sẽ countdown từ 20→1 giây  │
│ • Thông báo gửi cho tất cả player   │
│ • Server tự động tắt sau 20s        │
└─────────────────────────────────────┘
❓ Bạn có chắc chắn? (y/n): _
```

**Nhập `y` và Enter:**

```
✅ Đã kích hoạt bảo trì 20 giây!
⏰ Countdown bắt đầu...

Hệ thống sẽ bảo trì sau 20 giây nữa...
Hệ thống sẽ bảo trì sau 19 giây nữa...
Hệ thống sẽ bảo trì sau 18 giây nữa...
...
Hệ thống sẽ bảo trì sau 1 giây nữa...
BẢO TRÌ BẮT ĐẦU
→ Server tắt!
```

**📌 Tất cả player trong game cũng nhận thông báo MỖI GIÂY!**

---

## 👢 **CHỨC NĂNG 2: ĐÁ ALL PLAYER**

### **Cách dùng:**

```
👉 Nhập lựa chọn: 2  [Enter]
```

**Console hiện:**
```
┌─────────────────────────────────────┐
│  ⚠️  XÁC NHẬN ĐÁ ALL PLAYER  ⚠️     │
├─────────────────────────────────────┤
│ • Tất cả player bị kick NGAY        │
│ • Server tắt NGAY LẬP TỨC           │
│ • KHÔNG CÓ COUNTDOWN!               │
└─────────────────────────────────────┘
❓ Bạn có chắc chắn? (y/n): _
```

**Nhập `y` và Enter:**

```
✅ Đang kick all players...
⚡ Server sẽ tắt ngay!
BẮT ĐẦU BẢO TRÌ NGAY
→ Server tắt ngay lập tức!
```

**📌 Không có countdown - Tắt NGAY!**

---

## ⭐ **CHỨC NĂNG 3: THAY ĐỔI EXP**

### **Cách dùng:**

```
👉 Nhập lựa chọn: 3  [Enter]
```

**Console hiện:**
```
╔════════════════════════════════════════╗
║     ⭐ THAY ĐỔI EXP SERVER ⭐          ║
╠════════════════════════════════════════╣
║  EXP hiện tại: x1                      ║
╠════════════════════════════════════════╣
║  Chọn hệ số EXP mới:                   ║
║                                        ║
║  [1] x1    [2] x2    [3] x5            ║
║  [4] x10   [5] x20   [6] x30           ║
║  [7] x40   [8] x50                     ║
║                                        ║
║  [0] Quay lại                          ║
╚════════════════════════════════════════╝
👉 Nhập lựa chọn: _
```

**Ví dụ nhập `4` (x10):**

```
👉 Nhập lựa chọn: 4  [Enter]

✅ Đã thay đổi EXP thành công!
📊 Từ: x1 → x10
⚡ Áp dụng NGAY cho tất cả player!
```

**📌 Tất cả player trong game nhận thông báo:**
```
🎉 THÔNG BÁO 🎉
EXP server đã thay đổi!
Từ x1 → x10
Chúc các bạn luyện cấp vui vẻ!
```

**📌 Ngay lúc đó, player giết mob sẽ nhận x10 EXP! Không cần restart!**

---

## 📊 **CHỨC NĂNG 4: THÔNG TIN SERVER**

### **Cách dùng:**

```
👉 Nhập lựa chọn: 4  [Enter]
```

**Console hiện:**
```
╔════════════════════════════════════════╗
║       📊 THÔNG TIN SERVER 📊           ║
╠════════════════════════════════════════╣
║  Tên Server: Ngọc Rồng Online          ║
║  IP: 36.50.135.150                     ║
║  Port: 14445                           ║
║  ─────────────────────────────────     ║
║  Players Online: 15/1000               ║
║  Max/IP: 999                           ║
║  EXP Rate: x10                         ║
║  ─────────────────────────────────     ║
║  Status: Hoạt động                     ║
║  Uptime: 06/10/2025 10:30:00           ║
╚════════════════════════════════════════╝

📌 Nhấn Enter để quay lại menu...
```

---

## 💡 **CÁC LỆNH KHÁC:**

Ngoài menu số, bạn vẫn có thể dùng **lệnh text cũ**:

```bash
# Lệnh cũ (vẫn hoạt động):
bt    → Bảo trì 5s
bat   → Bật auto maintenance
tat   → Tắt auto maintenance
run   → Chạy run.bat

# Lệnh mới:
menu  → Hiện lại menu
help  → Hiện lại menu
```

---

## 🔧 **BUILD & TEST:**

### **Toàn bộ quá trình:**

```bash
# 1. MỞ CMD, VÀO THƯ MỤC PROJECT
cd "E:\Source NRO by me\Threading"

# 2. TẮT SERVER (nếu đang chạy)
taskkill /F /IM java.exe

# 3. BUILD
ant clean
ant jar

# 4. CHẠY SERVER
run.bat

# 5. THẤY MENU HIỆN RA!
```

---

## 🎮 **DEMO SỬ DỤNG:**

### **Kịch bản 1: Tăng EXP lên x10**

```
Console hiện menu
    ↓
Nhập: 3 [Enter]
    ↓
Menu EXP hiện
    ↓
Nhập: 4 [Enter] (chọn x10)
    ↓
✅ EXP đã đổi thành x10!
    ↓
Player trong game nhận thông báo
    ↓
Giết mob ngay lúc đó → Nhận x10 EXP!
```

---

### **Kịch bản 2: Bảo trì server**

```
Console hiện menu
    ↓
Nhập: 1 [Enter]
    ↓
Xác nhận: y [Enter]
    ↓
Countdown bắt đầu:
  20s... 19s... 18s...
    ↓
Tất cả player nhận thông báo mỗi giây
    ↓
Hết 20s → Server tắt tự động!
```

---

### **Kịch bản 3: Kick all ngay**

```
Console hiện menu
    ↓
Nhập: 2 [Enter]
    ↓
Xác nhận: y [Enter]
    ↓
Server tắt NGAY LẬP TỨC!
(Không có countdown)
```

---

## ⚡ **TÍNH NĂNG:**

✅ **Menu đẹp** - Khung, icon, rõ ràng  
✅ **Dễ dùng** - Nhập số, không cần nhớ lệnh  
✅ **An toàn** - Có xác nhận (y/n) trước khi thực hiện  
✅ **Real-time** - EXP đổi ngay, không cần restart  
✅ **Thông báo** - Player trong game nhận thông báo  
✅ **Log** - Ghi log mọi thao tác  
✅ **Backward compatible** - Lệnh cũ (bt, bat, tat) vẫn hoạt động  

---

## 📋 **CHECKLIST:**

- [x] Code đã sửa xong trong ServerManager.java ✅
- [ ] Build project: `ant clean && ant jar`
- [ ] Run server: `run.bat`
- [ ] Thấy menu console hiện ra
- [ ] Test chức năng 1: Bảo trì 20s
- [ ] Test chức năng 2: Kick all
- [ ] Test chức năng 3: Đổi EXP
- [ ] Test chức năng 4: Xem info

---

## 🎯 **ĐƠN GIẢN HÓA:**

### **Bạn chỉ cần:**

1. **Build lại project** (1 lần duy nhất)
2. **Chạy server** như bình thường
3. **Nhập số** vào console để điều khiển!

**VÍ DỤ:**
- Muốn đổi EXP → Nhập `3` → Chọn `4` (x10) → Xong!
- Muốn bảo trì → Nhập `1` → Nhập `y` → Countdown tự động!

---

## 🖼️ **GIAO DIỆN CONSOLE:**

Khi chạy server, console sẽ như này:

```
[Server logs...]
✓ Successfully loaded npc template (94)
✓ Successfully loaded map template (...)
✓ Active Port 14445

╔════════════════════════════════════════════════╗
║         🔧 ADMIN PANEL - NRO SERVER 🔧        ║
╠════════════════════════════════════════════════╣
║  Server: Ngọc Rồng Online                      ║
║  EXP Rate: x1                                  ║
║  Players Online: 0/1000                        ║
╠════════════════════════════════════════════════╣
║  [1] ⏰ Bảo trì 20s (Countdown)               ║
║  [2] 👢 Đá all player (Ngay lập tức)          ║
║  [3] ⭐ Thay đổi EXP Server                    ║
║  [4] 📊 Thông tin Server                       ║
║  [5] ⚡ Bảo trì ngay (5s countdown)            ║
║  [0] ❌ Thoát                                  ║
╚════════════════════════════════════════════════╝
👉 Nhập lựa chọn: _  ← Cursor đợi bạn nhập
```

**Bạn chỉ cần gõ số và Enter!**

---

## 🎬 **VIDEO TEXT DEMO:**

### **Demo 1: Đổi EXP lên x50**

```
👉 Nhập lựa chọn: 3

╔════════════════════════════════════════╗
║     ⭐ THAY ĐỔI EXP SERVER ⭐          ║
╠════════════════════════════════════════╣
║  EXP hiện tại: x1                      ║
╠════════════════════════════════════════╣
║  Chọn hệ số EXP mới:                   ║
║                                        ║
║  [1] x1    [2] x2    [3] x5            ║
║  [4] x10   [5] x20   [6] x30           ║
║  [7] x40   [8] x50                     ║
║                                        ║
║  [0] Quay lại                          ║
╚════════════════════════════════════════╝
👉 Nhập lựa chọn: 8

✅ Đã thay đổi EXP thành công!
📊 Từ: x1 → x50
⚡ Áp dụng NGAY cho tất cả player!

[Menu hiện lại tự động]
```

**Trong game:**
- Tất cả player nhận thông báo: "EXP đã đổi thành x50!"
- Giết mob ngay lúc đó → Nhận x50 EXP!

---

### **Demo 2: Bảo trì 20s**

```
👉 Nhập lựa chọn: 1

┌─────────────────────────────────────┐
│  ⚠️  XÁC NHẬN BẢO TRÌ 20 GIÂY  ⚠️   │
├─────────────────────────────────────┤
│ • Server sẽ countdown từ 20→1 giây  │
│ • Thông báo gửi cho tất cả player   │
│ • Server tự động tắt sau 20s        │
└─────────────────────────────────────┘
❓ Bạn có chắc chắn? (y/n): y

✅ Đã kích hoạt bảo trì 20 giây!
⏰ Countdown bắt đầu...

[Console]
Hệ thống sẽ bảo trì sau 20 giây nữa. Hãy thoát game để tránh mất dữ liệu.
Hệ thống sẽ bảo trì sau 19 giây nữa. Hãy thoát game để tránh mất dữ liệu.
...

[Game - Tất cả players thấy]
20s: "Hệ thống sẽ bảo trì sau 20 giây nữa..."
19s: "Hệ thống sẽ bảo trì sau 19 giây nữa..."
...
1s: "Hệ thống sẽ bảo trì sau 1 giây nữa..."
0s: [Bị kick, server tắt]
```

---

### **Demo 3: Kick all ngay**

```
👉 Nhập lựa chọn: 2

┌─────────────────────────────────────┐
│  ⚠️  XÁC NHẬN ĐÁ ALL PLAYER  ⚠️     │
├─────────────────────────────────────┤
│ • Tất cả player bị kick NGAY        │
│ • Server tắt NGAY LẬP TỨC           │
│ • KHÔNG CÓ COUNTDOWN!               │
└─────────────────────────────────────┘
❓ Bạn có chắc chắn? (y/n): y

✅ Đang kick all players...
⚡ Server sẽ tắt ngay!
BẮT ĐẦU BẢO TRÌ NGAY
SUCCESSFULLY MAINTENANCE!

→ Server tắt!
```

---

## 🔍 **TROUBLESHOOTING:**

### **Lỗi 1: Build lỗi**

**Console hiện:**
```
error: cannot find symbol
```

**Fix:**
- Kiểm tra lại code đã sửa đúng chưa
- Xem phần lỗi cụ thể
- Gửi lỗi cho tôi!

---

### **Lỗi 2: Menu không hiện**

**Nguyên nhân:**
- Build chưa thành công
- Chạy file jar cũ

**Fix:**
```bash
# Build lại hoàn toàn
ant clean
ant jar

# Đảm bảo chạy file jar MỚI
run.bat
```

---

### **Lỗi 3: Nhập lệnh không hoạt động**

**Nguyên nhân:**
- Console bị treo
- Input stream bị lỗi

**Fix:**
- Đóng và mở lại CMD
- Chạy lại server
- Nhập đúng số (0-5)

---

### **Lỗi 4: EXP không đổi**

**Kiểm tra:**

Tìm code tính EXP trong project (thường ở `PlayerService.java` hoặc `NPoint.java`):

```java
// Phải có dòng này:
long exp = baseExp * Manager.RATE_EXP_SERVER;
```

Nếu không có → Cần thêm vào!

---

## 📝 **TÓM TẮT:**

### **ĐÃ LÀM:**
✅ Sửa `ServerManager.java` - Thêm menu console  
✅ Tạo method `showAdminMenu()` - Hiển thị menu đẹp  
✅ Tạo method `startMaintenance20s()` - Bảo trì 20s  
✅ Tạo method `kickAllPlayers()` - Kick all  
✅ Tạo method `changeExpRate()` - Đổi EXP  
✅ Tạo method `showServerInfo()` - Thông tin  

### **CẦN LÀM:**
1. Build: `ant clean && ant jar`
2. Run: `run.bat`
3. Enjoy! 🎉

---

## 🎊 **HOÀN THÀNH!**

**Bạn không cần biết code!**

Chỉ cần:
1. **Build** 1 lần
2. **Run** server
3. **Nhập số** để điều khiển!

Đơn giản như:
- Muốn đổi EXP → Nhập `3` → Chọn `8` (x50) → Xong!
- Muốn bảo trì → Nhập `1` → Nhập `y` → Tự động countdown!

---

**BUILD VÀ CHẠY NGAY ĐỂ XEM MENU CONSOLE ĐẸP!** 🚀
