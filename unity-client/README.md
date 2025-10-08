# 📁 UNITY CLIENT PROJECT

## Hướng dẫn upload:

### Cách 1: Upload từng file
```
1. Vào thư mục Unity project của bạn
2. Tìm folder Assets/Scripts/
3. Copy tất cả file .cs vào đây: unity-client/Assets/Scripts/
4. Giữ nguyên cấu trúc thư mục con
```

### Cách 2: Upload tất cả (nếu project không quá nặng)
```
1. Copy toàn bộ folder Assets/ vào unity-client/Assets/
2. Bao gồm:
   - Scripts/ (C# scripts)
   - Scenes/ (Scene files)
   - Resources/ (Data, config)
   - Prefabs/ (UI prefabs)
```

## Các file quan trọng cần có:

### 1. Network (Kết nối server)
```
Scripts/Network/
├── NetworkManager.cs      (Quản lý kết nối)
├── Message.cs             (Đọc/ghi message)
├── Session.cs             (Socket connection)
└── MessageHandler.cs      (Xử lý message từ server)
```

### 2. Player
```
Scripts/Player/
├── Player.cs              (Player data)
├── PlayerController.cs    (Điều khiển player)
├── Skill.cs               (Skill system)
└── Inventory.cs           (Hành trang)
```

### 3. Item
```
Scripts/Item/
├── Item.cs                (Item data)
├── ItemTemplate.cs        (Item template)
└── ItemOption.cs          (Item option)
```

### 4. UI
```
Scripts/UI/
├── InventoryUI.cs         (UI hành trang)
├── ChatUI.cs              (UI chat)
├── SkillUI.cs             (UI skill)
└── ShopUI.cs              (UI shop)
```

## Sau khi upload:

Tôi sẽ:
✅ Đọc và phân tích code
✅ Hiểu protocol Unity client dùng
✅ Giúp tích hợp với NRO server
✅ Fix bugs nếu có
✅ Thêm tính năng mới

## Lưu ý:

- Chỉ cần upload file .cs (C# script)
- Không cần upload file .meta, .asset, .unity
- Không cần upload Library/, Temp/, obj/
- Config file (.json, .xml) nên upload
