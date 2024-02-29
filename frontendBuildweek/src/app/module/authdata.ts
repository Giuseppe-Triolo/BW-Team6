export interface AuthData {
  accessToken: string;
  user: {
    id: number;
    username: string;
    email: string;
    name: string;
    surname: string;
  };
}
