const PROXY_CONFIG = [
  {
    context: [
      "/api",
      "/oauth",
      "/graphql",
      "/graphiql"
    ],
    target: "http://localhost:8080",
    secure: false
  }
]

module.exports = PROXY_CONFIG;
