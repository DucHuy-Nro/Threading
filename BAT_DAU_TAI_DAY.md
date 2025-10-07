# 🎯 BẮT ĐẦU TẠI ĐÂY - ADMIN PANEL

## 👋 **XIN CHÀO!**

Bạn vừa nhận được **ADMIN PANEL HOÀN CHỈNH** cho game NRO!

---

## ⚡ **BẮT ĐẦU NGAY (5 PHÚT):**

### **📖 ĐỌC FILE NÀY TRƯỚC:**
```
📁 HUONG_DAN_NHANH_ADMIN_PANEL.md  ⭐⭐⭐
```

**File này có:**
- ✅ Hướng dẫn từng bước (5 phút)
- ✅ Code cần copy-paste
- ✅ SQL commands
- ✅ Troubleshooting

---

## 📚 **CÁC FILE HƯỚNG DẪN:**

| File | Mục đích | Độ ưu tiên |
|------|----------|------------|
| **`HUONG_DAN_NHANH_ADMIN_PANEL.md`** | Cài đặt nhanh | ⭐⭐⭐ ĐỌC ĐẦU TIÊN! |
| `HUONG_DAN_CAI_DAT_ADMIN_PANEL.md` | Chi tiết đầy đủ | ⭐⭐ Nếu cần hiểu sâu |
| `CODE_SNIPPET_ADMIN_PANEL.md` | Code cần sửa | ⭐⭐ Tham khảo |
| `PHAN_TICH_ADMIN_PANEL.md` | Phân tích kỹ thuật | ⭐ Nếu muốn hiểu code |
| `CAI_DAT_ADMIN_PANEL.sql` | SQL commands | ⭐⭐ Cần chạy |

---

## 💻 **CODE ĐÃ TẠO:**

| File | Mô tả |
|------|-------|
| **`AdminPanel.java`** | ✅ NPC Admin Panel (HOÀN CHỈNH) |
| `SGohan.java` | NPC có shop mẫu |
| `NpcShopMau.java` | Template NPC shop |

---

## 🎯 **CHỨC NĂNG:**

### **1. ⏰ Bảo trì 20s**
- Countdown từ 20→1 giây
- Thông báo hiện mỗi giây
- Tất cả player nhận đồng thời

### **2. 👢 Đá all player**
- Kick tất cả ngay lập tức
- Không countdown
- Bảo trì khẩn cấp

### **3. ⭐ Thay đổi EXP**
- Chọn từ x1 đến x50
- Áp dụng ngay (không restart!)
- Thông báo toàn server

---

## 🚀 **CÁCH CÀI ĐẶT:**

```
1. Đọc: HUONG_DAN_NHANH_ADMIN_PANEL.md
2. Sửa code (3 dòng trong 2 files)
3. Chạy SQL (3 lệnh)
4. Build: ant clean && ant jar
5. Run: run.bat
6. Test: Vào game, click NPC
```

**→ Chỉ 5 phút!** ⏱️

---

## 💡 **LƯU Ý:**

### **Trước khi Build:**
- ✅ Tắt server (`taskkill /F /IM java.exe`)
- ✅ Clean trước (`ant clean`)

### **Sau khi Build:**
- ✅ Restart server hoàn toàn
- ✅ Logout/login game
- ✅ Kiểm tra account có `is_admin = 1`

---

## 📍 **VỊ TRÍ NPC:**

**Mặc định:** Map ID 5 (Đảo Kamê) tọa độ (500, 300)

**Muốn đổi?**
- Sửa SQL: `[85,x,y]` với x,y là tọa độ mới
- Hoặc thêm vào map khác

---

## ❓ **CẦN TRỢ GIÚP?**

### **Lỗi thường gặp:**

| Lỗi | File hướng dẫn |
|-----|----------------|
| Build failed | `HUONG_DAN_NHANH_ADMIN_PANEL.md` |
| NPC không hiện | `HUONG_DAN_CAI_DAT_ADMIN_PANEL.md` |
| "Không có quyền" | `CAI_DAT_ADMIN_PANEL.sql` (set admin) |
| EXP không đổi | `PHAN_TICH_ADMIN_PANEL.md` |

---

## 🎊 **KẾT QUẢ CUỐI CÙNG:**

Sau khi cài xong, bạn sẽ có:

✅ **Admin Panel chuyên nghiệp**
- Menu đẹp với emoji
- 3 chức năng đầy đủ
- Có xác nhận an toàn
- Log admin actions

✅ **Bảo trì linh hoạt**
- Countdown 20s
- Hoặc kick ngay

✅ **Quản lý EXP dễ dàng**
- Đổi x1-x50 tùy ý
- Real-time (không restart!)
- Thông báo players

✅ **Bảo mật tốt**
- Chỉ admin dùng được
- Có log tất cả hành động

---

## 🚀 **BẮT ĐẦU NGAY:**

```bash
# Mở file này:
HUONG_DAN_NHANH_ADMIN_PANEL.md

# Làm theo 5 bước
# Chỉ mất 5 phút!
```

---

**CHÚC BẠN THÀNH CÔNG!** 🎉🎮

Have fun with your Admin Panel! 😊
