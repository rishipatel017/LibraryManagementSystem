# Author
Name: RIshi Patel
College: A.D. Patel Institute of Technology (ADIT)
Course: Programming With Java (PWJ)
Project Type: Mini Project
Semester: 4th Semester
Enrollment Number: 12402080603014
Submission Date: 12-04-2025

# 📚 Library Management System

A simple console-based Java application for managing a library system. It allows you to manage users (students and faculty), books, and borrowing/returning operations with persistent data storage using serialization.


## 🚀 Features

- Add, list, and manage **students** and **faculty**.
- Add, remove, update, and search **books**.
- Borrow and return books with automatic inventory updates.
- Save and load data using `.dat` files.
- Organized and extensible object-oriented structure.


## 🧩 Project Structure
bash 
```
├── 📁 library/
|    LibrarySystem/
|    │
|    ├── 📁 bin/                           # Compiled classes
|    │
|    ├── 📁 lib/                           # External libraries (if any)
|    │
|    ├── 📁 res/                           # Reserved for assets (currently unused)
|    │
|    ├── 📁 src/
│    └── 📁 in/
│         └── 📁 ac/
│              └── 📁 adit/
│                   └── 📁 pwj/
│                        └── 📁 miniproject/
│                             └── 📁 library/
│                                  ├── Main.java
│                                  ├── Book.java
│                                  ├── Library.java
│                                  ├── User.java
│                                  ├── Student.java
│                                  ├── Faculty.java
│                                  ├── UserDataManager.java
│                                  └── DataManager.java
│
| 
├── books.dat                    # Serialized book data
├── users.dat                    # Serialized user data
└── README.md                    # Project documentation
```


## 🧰 Features

- 📘 Add, remove, and manage books
- 👨‍🎓 Manage users (Students & Faculty)
- 📥 Borrow and 📤 return books
- 🔍 Search books by ID, title, or author
- 💾 Persistent data using serialization (`.dat` files)



## 💻 How to Set Up & Run

### 📦 From `library.zip`

1. **Extract the ZIP File**  
   Unzip `library.zip` to a preferred location on your computer.

2. **Open in IDE (Optional)**  
   You can import the project into an IDE like Eclipse or IntelliJ IDEA.  
   Just point to the `LibrarySystem` folder inside `library/`.

3. **Compile from Terminal (If using CLI)**  
   Navigate to the source folder:
   cd library/LibrarySystem/src


## 💾 Data Persistence

- **Books** are saved in: `books.dat`
- **Users** are saved in: `users.dat`
- Serialization is handled using `ObjectOutputStream` / `ObjectInputStream`.


## 📦 Requirements

- Java 8 or above
- No external libraries required

## 🛠️ How to Run

1. **Compile the project:**
    javac -cp in/ac/adit/pwj/miniproject/library/*.java
2. **Run the project:**
    cd ../bin
    java in.ac.adit.pwj.miniproject.library.Main


**Sample Menu**
====== Library Management System ======
1. Add User
2. List All Users
-------------------------------
3. Add Book
4. Remove Book
5. Update Book Quantity
6. List All Books
7. Search Book
-------------------------------
8. Borrow Book
9. Return Book
-------------------------------
10. Save & Exit

📈 Future Enhancements (Ideas)
- Book borrow limits for users
- Due dates and fine system
- Book reservation feature
- GUI interface (JavaFX or Swing)
- Login system with roles
- Unit tests using JUnit
