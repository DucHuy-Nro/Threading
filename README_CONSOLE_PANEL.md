# 🎮 CONSOLE ADMIN PANEL - NRO SERVER

## 🎯 **TỔNG QUAN:**

**Console Admin Panel** là bảng điều khiển hiện trên **CMD** khi chạy server NRO.

### **3 CHỨC NĂNG CHÍNH:**

| Chức năng | Mô tả | Cách dùng |
|-----------|-------|-----------|
| ⏰ **Bảo trì 20s** | Countdown từ 20→1 giây, thông báo mỗi giây | Nhập `1` |
| 👢 **Đá all player** | Kick tất cả ngay lập tức | Nhập `2` |
| ⭐ **Thay đổi EXP** | x1, x2, x5, x10, x20, x30, x40, x50 | Nhập `3` |

---

## ⚡ **QUICK START:**

```bash
# 1. Build
cd "E:\Source NRO by me\Threading"
ant clean && ant jar

# 2. Run
run.bat

# 3. Nhập số vào console để điều khiển!
```

---

## 🖼️ **GIAO DIỆN:**

Khi run server, console hiện:

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

## 📚 **HƯỚNG DẪN CHI TIẾT:**

Đọc file: **`HUONG_DAN_CONSOLE_ADMIN_PANEL.md`**

---

## 🔨 **BUILD:**

Đọc file: **`BUILD_VA_CHAY.md`**

---

## 💡 **VÍ DỤ SỬ DỤNG:**

### **Đổi EXP lên x50:**
```
Nhập: 3 → Nhập: 8 → Done!
```

### **Bảo trì sau 20s:**
```
Nhập: 1 → Nhập: y → Countdown tự động!
```

### **Kick all ngay:**
```
Nhập: 2 → Nhập: y → Server tắt ngay!
```

---

## ✅ **TÍNH NĂNG:**

- ✅ Menu đẹp với khung viền
- ✅ Dễ dùng (nhập số)
- ✅ An toàn (có xác nhận)
- ✅ Real-time (EXP đổi ngay, không restart)
- ✅ Thông báo player (countdown, EXP change...)
- ✅ Log actions (ghi lại mọi thao tác)

---

## 📁 **FILES:**

| File | Mô tả |
|------|-------|
| ⭐ **`README_CONSOLE_PANEL.md`** | File này - Tổng quan |
| ⭐ **`HUONG_DAN_CONSOLE_ADMIN_PANEL.md`** | Hướng dẫn chi tiết |
| ⭐ **`BUILD_VA_CHAY.md`** | Hướng dẫn build |

---

## 🎊 **ĐÃ SỬA CODE:**

**File:** `src/nro/models/server/ServerManager.java`

**Thêm:**
- Menu console đẹp
- 4 method mới (maintenance 20s, kick all, change EXP, server info)
- Xác nhận trước khi thực hiện
- Log tất cả actions

**→ BUILD VÀ CHẠY LÀ DÙNG ĐƯỢC NGAY!** ✅

---

**CHÚC BẠN THÀNH CÔNG!** 🚀
