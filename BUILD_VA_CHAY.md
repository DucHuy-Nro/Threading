# 🚀 BUILD VÀ CHẠY SERVER (3 BƯỚC ĐƠN GIẢN)

## ⚡ **NHANH NHẤT (COPY-PASTE):**

Mở **CMD**, paste từng lệnh:

```bash
# Bước 1: Vào thư mục project
cd "E:\Source NRO by me\Threading"

# Bước 2: Tắt server cũ (nếu đang chạy)
taskkill /F /IM java.exe

# Bước 3: Build
ant clean && ant jar

# Bước 4: Chạy
run.bat
```

**→ Menu console sẽ hiện ngay!** ✅

---

## 📝 **CHI TIẾT TỪNG BƯỚC:**

### **BƯỚC 1: MỞ CMD**

1. Nhấn `Win + R`
2. Gõ `cmd`
3. Enter

---

### **BƯỚC 2: VÀO THƯ MỤC PROJECT**

```bash
# Thay đường dẫn thành đường dẫn của bạn:
cd "E:\Source NRO by me\Threading"
```

**Check đúng thư mục chưa:**
```bash
dir
```

Phải thấy:
```
build.xml
src
dist
...
```

---

### **BƯỚC 3: TẮT SERVER CŨ (nếu đang chạy)**

```bash
taskkill /F /IM java.exe
```

**Hoặc:**
- Vào **Task Manager** (Ctrl+Shift+Esc)
- Tìm `java.exe`
- **End Task**

---

### **BƯỚC 4: BUILD PROJECT**

```bash
# Clean trước
ant clean

# Build jar
ant jar
```

**Nếu thành công, sẽ thấy:**
```
Compiling 560 source files...
BUILD SUCCESSFUL
Total time: 10 seconds
```

**Nếu lỗi:**
- Xem phần [TROUBLESHOOTING] bên dưới
- Hoặc gửi lỗi cho tôi!

---

### **BƯỚC 5: CHẠY SERVER**

```bash
run.bat
```

**HOẶC:**

```bash
cd dist
java -jar NgocRongOnline.jar
```

---

## ✅ **KẾT QUẢ MONG ĐỢI:**

Console sẽ hiển thị:

```
[Server starting logs...]
✓ Successfully loaded npc template (94)
✓ Successfully loaded map template (162)
✓ Successfully loaded mob template (...)
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
👉 Nhập lựa chọn: _  ← Cursor chờ bạn nhập
```

**→ THÀNH CÔNG!** 🎉

---

## 🔧 **TROUBLESHOOTING:**

### **Lỗi: "ant not found" hoặc "ant không phải lệnh"**

**Nguyên nhân:** Apache Ant chưa cài hoặc chưa add vào PATH

**Fix 1: Dùng NetBeans (ĐƠN GIẢN NHẤT)**

1. Mở NetBeans
2. Open Project → Chọn folder "Threading"
3. Right-click project → **Clean and Build**
4. Chạy `run.bat`

**Fix 2: Cài Ant**

1. Download Apache Ant: https://ant.apache.org/bindownload.cgi
2. Giải nén vào `C:\ant`
3. Add `C:\ant\bin` vào PATH
4. Mở CMD mới, chạy `ant -version`

---

### **Lỗi: "BUILD FAILED"**

**Kiểm tra lỗi compile:**

Xem phần lỗi trong console:
```
error: cannot find symbol
  symbol: class Scanner
```

**→ Thiếu import hoặc code sai**

Gửi lỗi chi tiết cho tôi để fix!

---

### **Lỗi: "Unable to delete file dist/NgocRongOnline.jar"**

**Nguyên nhân:** Server đang chạy, file .jar đang được dùng

**Fix:**
```bash
# Kill java process
taskkill /F /IM java.exe
taskkill /F /IM javaw.exe

# Build lại
ant clean && ant jar
```

---

### **Menu không hiện**

**Nguyên nhân:** Build chưa thành công hoặc chạy file jar cũ

**Fix:**
1. Xóa folder `build` và `dist`
2. Build lại: `ant clean && ant jar`
3. Chắc chắn chạy: `run.bat` (hoặc file jar trong folder `dist`)

---

## 💡 **MẸO:**

### **1. Tạo script build nhanh:**

**File `quick_build.bat`:**

```bat
@echo off
echo ========================================
echo     BUILD NHANH NRO SERVER
echo ========================================
echo.

echo [1/3] Tắt server...
taskkill /F /IM java.exe 2>nul
timeout /t 2 /nobreak >nul

echo [2/3] Build...
call ant clean
call ant jar

echo [3/3] Chạy server...
call run.bat

pause
```

**Chạy:**
- Double-click `quick_build.bat`
- Tự động build và chạy!

---

### **2. Build trong NetBeans:**

```
1. Mở NetBeans
2. Open Project → Chọn folder Threading
3. Shift + F11 (Clean and Build)
4. Chạy run.bat
```

**→ Đơn giản hơn nhiều!**

---

### **3. Kiểm tra build thành công:**

```bash
# Kiểm tra file jar tồn tại
dir dist\NgocRongOnline.jar

# Xem kích thước (phải > 1MB)
```

---

## 📋 **CHECKLIST:**

### **Trước khi build:**
- [ ] Code đã sửa xong
- [ ] Server đã tắt hoàn toàn
- [ ] CMD đang ở đúng thư mục project

### **Sau khi build:**
- [ ] Thấy "BUILD SUCCESSFUL"
- [ ] File `dist/NgocRongOnline.jar` tồn tại
- [ ] Kích thước file jar > 1MB

### **Chạy server:**
- [ ] Chạy `run.bat`
- [ ] Thấy "Active Port 14445"
- [ ] **Menu console hiện ra** ✅
- [ ] Có thể nhập lệnh

---

## 🎯 **HOÀN THÀNH!**

Sau khi build thành công, bạn sẽ có:

✅ **Console Admin Panel** với menu đẹp  
✅ **3 chức năng chính** hoạt động  
✅ **Dễ sử dụng** - Chỉ cần nhập số  

**Chúc bạn thành công!** 🎉

---

## 📞 **NẾU GẶP LỖI:**

**Gửi cho tôi:**
1. Lỗi build (toàn bộ text lỗi)
2. Hoặc chụp màn hình CMD
3. Tôi sẽ giúp ngay!

---

**BẮT ĐẦU BUILD NGAY!** 🚀
