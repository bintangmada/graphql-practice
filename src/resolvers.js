// 3. RESOLVERS ("Koki / Dapur")
// Berisi fungsi-fungsi JavaScript yang bertugas mengambil data asli sesuai permintaan di Schema.

import { books, authors } from './data.js';

export const resolvers = {
  // Penyedia data untuk type Query
  Query: {
    books: (parent, args) => {
      // Jika client mengirim kata kunci pencarian, filter berdasarkan judul
      if (args.search) {
        return books.filter((b) =>
          b.title.toLowerCase().includes(args.search.toLowerCase())
        );
      }
      return books;
    },
    book: (parent, args) => books.find((b) => b.id === args.id),
    authors: () => authors,
    author: (parent, args) => authors.find((a) => a.id === args.id),
  },

  // Penyedia data untuk relasi di dalam type Book
  Book: {
    // Siapa penulis dari buku ini?
    author: (parent) => authors.find((a) => a.id === parent.authorId),
  },

  // Penyedia data untuk relasi di dalam type Author
  Author: {
    // Apa saja buku yang ditulis penulis ini?
    books: (parent) => books.filter((b) => b.authorId === parent.id),
  },

  // Penyedia aksi untuk type Mutation
  Mutation: {
    addBook: (parent, args) => {
      const newBook = {
        id: String(books.length + 1),
        title: args.title,
        year: args.year,
        authorId: args.authorId,
      };
      books.push(newBook);
      return newBook;
    },
  },
};

