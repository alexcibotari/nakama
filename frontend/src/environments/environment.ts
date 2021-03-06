// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

export const environment = {
  production: false,
  api: {
    url: '/api'
  },
  graphql: {
    uri: '/graphql'
  },
  oauth: {
    url: '/oauth',
    basic: 'd2ViYXBwOnRvcHNlY3JldA==',
    client: 'webapp',
    grant: 'password',
    key: 'oauth_token_key'
  }
};
