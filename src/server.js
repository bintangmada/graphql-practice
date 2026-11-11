// 4. SERVER ("Pelayan / Kasir")
// Menggabungkan Schema + Resolvers dan menyalakan server agar bisa diakses.

import { ApolloServer } from '@apollo/server';
import { startStandaloneServer } from '@apollo/server/standalone';
import { ApolloServerPluginLandingPageLocalDefault } from '@apollo/server/plugin/landingPage/default';
import { typeDefs } from './schema.js';
import { resolvers } from './resolvers.js';

// Buat instance Apollo Server
const server = new ApolloServer({
  typeDefs,
  resolvers,
  plugins: [
    // Menonaktifkan embed iframe agar browser tidak stuck loading
    ApolloServerPluginLandingPageLocalDefault({ embed: false }),
  ],
});

// Jalankan server di port 4000
const { url } = await startStandaloneServer(server, {
  listen: { port: 4000 },
});

console.log(`🚀 Server GraphQL siap di: ${url}`);

