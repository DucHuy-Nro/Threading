# ⭐ BẮT ĐẦU TẠI ĐÂY - HƯỚNG DẪN ĐƠN GIẢN NHẤT

## 🎯 **MỤC TIÊU:**

Tạo **Console Admin Panel** - Bảng điều khiển trên CMD khi chạy server.

**3 chức năng:**
1. ⏰ Bảo trì 20s (countdown mỗi giây)
2. 👢 Kick all players (ngay lập tức)
3. ⭐ Đổi EXP (x1, x2, x5, x10, x20, x30, x40, x50)

---

## 📝 **CHỈ CẦN SỬA 1 FILE DUY NHẤT!**

**File:** `src/nro/models/server/ServerManager.java`

---

## 🚀 **HƯỚNG DẪN (3 BƯỚC SIÊU ĐƠN GIẢN):**

---

### **BƯỚC 1: MỞ FILE** (10 giây)

**Trong NetBeans:**
1. Mở project "Threading"
2. Mở folder: `Source Packages` → `nro.models.server`
3. Double-click file **`ServerManager.java`**

**HOẶC dùng Notepad++:**
1. Vào folder: `E:\Source NRO by me\Threading\src\nro\models\server\`
2. Right-click file **`ServerManager.java`**
3. Open With → Notepad++

---

### **BƯỚC 2: SỬA CODE** (2 phút)

#### **2a. TÌM VÀ XÓA CODE CŨ:**

Nhấn **Ctrl + F** (tìm kiếm)

Tìm text: `private static void activeCommandLine()`

Bạn sẽ thấy đoạn code như này (khoảng **dòng 293-324**):

```java
private static void activeCommandLine() {
    Scanner sc = new Scanner(System.in);
    while (true) {
        String line = sc.nextLine();
        switch (line) {
            case "bt":
                Maintenance.gI().startSeconds(5);
                break;
            ...
        }
    }
}
```

**BÔI ĐEN** (chọn) toàn bộ method này từ dòng `private static void` đến dấu `}` cuối cùng.

**Nhấn Delete** để xóa!

---

#### **2b. PASTE CODE MỚI:**

**MỞ FILE:** `CODE_COPY_PASTE.txt`

**COPY** phần code từ "BƯỚC 1" trong file đó.

**PASTE** vào chỗ vừa xóa trong `ServerManager.java`

---

#### **2c. THÊM 4 METHOD MỚI:**

**Kéo xuống CUỐI FILE** `ServerManager.java`

Tìm dòng cuối cùng:

```java
}  ← Dấu ngoặc đóng cuối cùng của class
```

**TRƯỚC dấu `}` đó**, mở file `CODE_COPY_PASTE.txt`

**COPY** phần code từ "BƯỚC 3" và **PASTE** vào trước dấu `}` cuối.

---

#### **2d. LƯU FILE:**

Nhấn **Ctrl + S** để lưu!

---

### **BƯỚC 3: BUILD VÀ CHẠY** (1 phút)

```bash
# Mở CMD
cd "E:\Source NRO by me\Threading"

# Tắt server
taskkill /F /IM java.exe

# Build
ant clean && ant jar

# Chạy
run.bat
```

**→ Menu console sẽ hiện ngay!** 🎉

---

## 🖼️ **KẾT QUẢ:**

Khi chạy `run.bat`, console hiện:

```
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
👉 Nhập lựa chọn: _
```

**Chỉ cần nhập số để điều khiển!**

---

## 💡 **HÌNH DUNG QUÁ TRÌNH:**

### **File ServerManager.java TRƯỚC khi sửa:**

```
Dòng 1-292: Code khác...

Dòng 293: private static void activeCommandLine() {
Dòng 294:     Scanner sc = new Scanner(System.in);
...
Dòng 323:     }
Dòng 324: }  ← XÓA từ đây

Dòng 325:
Dòng 326: }  ← Dấu đóng class (giữ nguyên)
```

### **Sau khi sửa:**

```
Dòng 1-292: Code khác...

Dòng 293: private static void activeCommandLine() {  ← CODE MỚI
Dòng 294:     Scanner sc = new Scanner(System.in);
...
Dòng 350: }  ← Hết method activeCommandLine()

Dòng 351: 
Dòng 352: private static void showAdminMenu() {  ← 4 METHOD MỚI THÊM VÀO
...
Dòng 500: }  ← Hết method showServerInfo()

Dòng 501:
Dòng 502: }  ← Dấu đóng class (giữ nguyên)
```

---

## 📋 **TÓM TẮT:**

Bạn chỉ cần làm:

1. **Mở** file `ServerManager.java`
2. **Tìm** method `activeCommandLine()`
3. **Xóa** method cũ
4. **Paste** code mới từ file `CODE_COPY_PASTE.txt` (phần BƯỚC 1)
5. **Kéo xuống cuối** file
6. **Paste** thêm 4 method mới từ file `CODE_COPY_PASTE.txt` (phần BƯỚC 3)
7. **Lưu** file (Ctrl+S)
8. **Build** lại (ant clean && ant jar)
9. **Chạy** server (run.bat)
10. **Enjoy!** 🎉

---

## 📁 **CÁC FILE CẦN ĐỌC:**

1. ⭐ **`START_HERE.md`** ← Đang đọc file này!
2. ⭐ **`CODE_COPY_PASTE.txt`** ← Mở file này để copy code!
3. ⭐ **`HUONG_DAN_SUA_CODE_CHI_TIET.md`** ← Hướng dẫn chi tiết hơn

---

## ⚠️ **LƯU Ý:**

- ✅ Chỉ sửa **1 file duy nhất**: `ServerManager.java`
- ✅ **Copy-paste** code từ file `CODE_COPY_PASTE.txt`
- ✅ **Không cần hiểu** code, chỉ cần paste đúng vị trí
- ✅ **Lưu file** trước khi build

---

## 🎮 **SAU KHI XONG:**

Chạy server → Console hiện menu đẹp → Nhập số để điều khiển!

**Ví dụ:**
- Muốn đổi EXP x50? → Nhập `3` → Nhập `8` → Xong!
- Muốn bảo trì? → Nhập `1` → Nhập `y` → Countdown tự động!

---

**BẮT ĐẦU NGAY! MỞ FILE `CODE_COPY_PASTE.txt` ĐỂ COPY CODE!** 🚀
