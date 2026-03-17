export type Book = {
  id: string;
  title: string;
  author: string;
  description: string;
  price: number;
  imageUrl: string;
};

export type User = {
  id: string;
  name: string;
  email: string;
  password: string;
};

export type Order = {
  id: string;
  userId: string;
  bookIds: string[];
  totalAmount: number;
  orderDate: string;
};