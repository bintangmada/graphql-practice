# 🚀 GraphQL Practice: From Zero to Real-World Architecture

Repositori ini adalah panduan praktis dan implementasi lengkap GraphQL untuk pemula hingga tingkat lanjut, mencakup perbandingan dengan REST API, implementasi backend ganda (**Java Spring Boot** & **Node.js Apollo Server**), serta antarmuka **Frontend Web Interaktif**.

---

## 📂 Struktur Direktori Proyek

```text
graphql-practice/
│
├── frontend/                          # SISI CLIENT (Aplikasi Web)
│   └── index.html                     # HTML + Vanilla JS Fetch (Query, Mutation, Search, Switcher)
│
├── backend/                           # SISI SERVER (Java Spring Boot 3)
│   ├── pom.xml                        # Maven configuration (spring-boot-starter-graphql)
│   └── src/main/
│       ├── resources/
│       │   ├── application.properties # Port 8080, aktifkan GraphiQL & CORS
│       │   └── graphql/
│       │       └── schema.graphqls    # Schema GraphQL universal
│       └── java/com/example/graphqlpractice/
│           ├── GraphqlPracticeApplication.java
│           ├── model/
│           │   ├── Book.java          # Java 17 Record
│           │   └── Author.java        # Java 17 Record
│           └── controller/
│               └── BookController.java # @QueryMapping, @MutationMapping, @SchemaMapping
│
├── src/                               # SISI SERVER ALTERNATIF (Node.js Apollo Server)
│   ├── data.js                        # Mock database
│   ├── schema.js                      # TypeDefs
│   ├── resolvers.js                   # Resolvers
│   └── server.js                      # Apollo Standalone Server (Port 4000)
│
└── package.json                       # Konfigurasi Node.js
```

---

## 💡 Konsep Inti & Analogi

### 🍔 REST API vs 🍱 GraphQL
* **REST API (Paket Fast Food):**
  * Membeli "Paket A" harus menerima semuanya (Burger + Kentang + Minuman).
  * Masalah: *Over-fetching* (data tidak terpakai tetap terkirim) & *Under-fetching* (harus pindah kasir/endpoint lain jika butuh data tambahan).
* **GraphQL (Prasmanan / Form Pesanan Kustom):**
  * Hanya ada **1 endpoint kasir** (`/graphql`).
  * Klien membawa secarik kertas checklist dan mencentang **hanya field yang dibutuhkan**. Server mengembalikan data persis sesuai pesanan.

---

## 🛠️ Cara Menjalankan Proyek

### 1. Menjalankan Backend Java (Spring Boot)
Pastikan Anda memiliki Java 17 dan Maven terpasang:
```bash
cd backend
mvn spring-boot:run
```
* **Endpoint API:** `http://localhost:8080/graphql`
* **GraphiQL Sandbox:** `http://localhost:8080/graphiql`

### 2. Menjalankan Backend Node.js (Apollo Server)
```bash
npm start
```
* **Endpoint API:** `http://localhost:4000/`

### 3. Menjalankan Frontend
* Buka file `frontend/index.html` langsung di browser Anda (klik kanan -> *Open in Default Browser*).
* Anda dapat memilih target backend di dropdown (Spring Boot atau Node.js).
* Anda dapat mencoba:
  * 🔍 **Pencarian Live:** Mengetik judul buku (mengirim Query dengan argument).
  * ➕ **Tambah Buku:** Mengisi form judul, tahun, dan penulis (mengirim Mutation).

---

## 📝 Contoh Operasi GraphQL

### 1. Query dengan Relasi (Ambil Buku + Penulisnya)
```graphql
query {
  books {
    title
    year
    author {
      name
    }
  }
}
```

### 2. Query dengan Filter Argument (Pencarian)
```graphql
query CariBuku($search: String) {
  books(search: $search) {
    id
    title
    author {
      name
    }
  }
}
```

### 3. Mutation (Menambah Buku Baru)
```graphql
mutation TambahBukuBaru($title: String!, $year: Int!, $authorId: ID!) {
  addBook(title: $title, year: $year, authorId: $authorId) {
    id
    title
    year
  }
}
```

---

## 🧠 Catatan Penting untuk Real-World Backend

1. **Apakah Backend selalu `SELECT *`?**
   * Di tabel umum: Backend mengambil data objek, lalu engine GraphQL otomatis membuang kolom yang tidak diminta sebelum dikirim ke internet.
   * Di relasi: Metode relasi (seperti `@SchemaMapping` di Spring Boot) **hanya dieksekusi jika client memintanya**. Jika client tidak minta `author`, database tabel author tidak akan pernah disentuh!
2. **N+1 Problem & DataLoader:**
   * Masalah: Mengambil 100 buku dan nama penulisnya bisa memicu $1 + 100$ query ke database.
   * Solusi: Menggunakan **DataLoader** untuk menggabungkan (*batching*) 100 query tadi menjadi 1 query `IN (...)`.
3. **Autentikasi (JWT):**
   * Karena endpoint hanya 1 (`/graphql`), token otentikasi diperiksa di lapisan **Context** pada setiap request sebelum diteruskan ke Controller / Resolvers.
