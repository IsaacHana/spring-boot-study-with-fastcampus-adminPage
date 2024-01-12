export interface User {
  id: number;
  account: string;
  password: string;
  status: string;
  email: string;
  phone_number: string;
  registered_at: string;
  unregistered_at: string;
  order_group_api_responses: [];
}

export interface Item {
  id: number;
  account: string;
  password: string;
  status: string;
  email: string;
  phone_number: string;
  registered_at: string;
  unregistered_at: string;
  order_group_api_responses: [];
}

export interface PaginationProps {
  total_pages: number;
  total_elements: number;
  current_page: number;
  current_elements: number;
  current_size: number;
}
