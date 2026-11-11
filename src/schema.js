// 2. SCHEMA (TypeDefs / "Daftar Menu")
// Menjelaskan bentuk data apa saja yang tersedia dan apa yang bisa diminta oleh client.

export const typeDefs = `#graphql
  # Tipe data untuk Penulis
  type Author {
    id: ID!
    name: String!
    books: [Book] # Relasi: seorang penulis punya banyak buku
  }

  # Tipe data untuk Buku
  type Book {
    id: ID!
    title: String!
    year: Int!
    author: Author # Relasi: setiap buku punya satu penulis
  }

  # Query = Operasi untuk MEMBACA data (Read)
  type Query {
    books(search: String): [Book]      # Ambil buku (bisa difilter dengan kata kunci)
    book(id: ID!): Book                # Ambil satu buku berdasarkan ID
    authors: [Author]                  # Ambil semua penulis
    author(id: ID!): Author            # Ambil satu penulis berdasarkan ID
  }

  # Mutation = Operasi untuk MENGUBAH / MENAMBAH data (Create, Update, Delete)
  type Mutation {
    addBook(title: String!, year: Int!, authorId: ID!): Book
  }
`;

