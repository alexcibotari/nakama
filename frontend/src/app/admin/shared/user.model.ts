export class User {
  login: string;
  enabled: boolean;
  email: string;
  authorities?: Array<string>;

  name: string;
  givenName: string;
  familyName: string;
  birthday: string;
  gender: string;

  jobTitle: string;
  telephone: string;
}
