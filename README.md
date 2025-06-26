# 🔐 Encrypted File Organizer with Duplicate Detection & Cleanup

A **Core Java** console-based utility that helps users manage messy file systems by **organizing files**, **detecting duplicates**, and **encrypting/decrypting sensitive files** — all through an intuitive menu-driven interface.  
No GUI, no web — just pure Java, OOP principles, and utility-focused logic.

---

## 🚀 Features

- ✅ **Duplicate File Detection**
  - Uses **SHA-256** + file size to identify duplicates
  - Lets you safely delete redundant copies

- 🗂️ **Organize Files by Type**
  - Sorts files into folders like: `Images/`, `Documents/`, `Videos/`, etc.
  - Automatically creates folders based on file extensions

- 🔐 **AES-Based File Encryption & Decryption**
  - Encrypt any file with a password (AES-128)
  - Decrypt only with correct password
  - Useful for securing PDFs, images, reports, etc.

- 📝 **Action Logging**
  - Every operation is logged to a timestamped `organizer_log.txt` file

---

## 🧠 Concepts Used

- Java OOP: classes, interfaces, modular design
- File I/O (read, write, move, delete)
- SHA-256 hashing (for file fingerprinting)
- AES encryption using Java Cryptography API
- Exception handling
- CLI interaction using `Scanner`

---

## 📁 Project Structure

```
EncryptedFileOrganizer/
├── src/
│   └── com/
│       └── fileOrganizer/
│           ├── Main.java                # Main controller
│           ├── model/
│           │   └── FileInfo.java        # File metadata class
│           ├── service/
│           │   ├── DuplicateDetector.java
│           │   ├── Encryptor.java
│           │   ├── FileOrganizer.java
│           │   └── Logger.java
│           └── util/
│               └── FileUtils.java
├── README.md
└── .gitignore (optional)
```

---

## 📦 How to Run

### ⚙️ Requirements
- Java 8 or above (JDK installed)
- Any Java IDE (IntelliJ, Eclipse, VS Code)

### ▶️ Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/encrypted-file-organizer.git
   cd encrypted-file-organizer
   ```

2. **Open in IDE and compile**

3. **Run `Main.java`**

4. **Choose options from the menu:**
   - Detect duplicates
   - Organize files
   - Encrypt/Decrypt files
   - View logs

---

## 💡 Sample Console Output

```text
📁 Enter the path of the folder to manage:
C:/Users/Tushar/Desktop/Test

========== File Organizer Menu ==========
1. Detect & Clean Duplicate Files
2. Organise Files by Type
3. Encrypt a File
4. Decrypt a File
5. View Log File Path
6. Exit

Choose an option (1–6): 2
Moved resume.pdf → Documents/
Moved photo.jpg → Images/
✅ Files organized successfully.
```

---

## 🙋‍♂️ Author

**Tushar Ranjan Das**  
👨‍💻 CSE Graduate 2025 | Java | JavaScript | Web Dev | Python | Data Science  
📫 [LinkedIn](https://www.linkedin.com/in/tushar-ranjan-das/)
🌐 [GitHub](https://github.com/TusharRanjan02)

---

## ⭐ Show Your Support

If you liked this project:
- Give it a ⭐ on GitHub
- Share with others
- Suggest improvements or contribute!

---
