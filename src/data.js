// 1. DATA (Mock Database)
// Di dunia nyata, data ini biasanya diambil dari database (PostgreSQL, MongoDB, MySQL, dsb).
// Untuk latihan, kita pakai array JavaScript biasa di memori.

export let books = [
  { id: '1', title: 'Laskar Pelangi', year: 2005, authorId: '101' },
  { id: '2', title: 'Bumi Manusia', year: 1980, authorId: '102' },
  { id: '3', title: 'Anak Semua Bangsa', year: 1981, authorId: '102' },
];

export let authors = [
  { id: '101', name: 'Andrea Hirata' },
  { id: '102', name: 'Pramoedya Ananta Toer' },
];

