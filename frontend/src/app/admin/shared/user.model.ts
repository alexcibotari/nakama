export class User {
  login: string;
  email: string;
  enabled: boolean;
  authorities?: Array<string>;
}
