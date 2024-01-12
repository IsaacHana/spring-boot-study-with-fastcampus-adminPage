export interface User {
  id: number;
  account: string;
  password: string;
  status: string;
  email: string;
  phone_number: string;
  registered_at: string;
  unregistered_at: string | null;
  order_group_api_responses: [];
}

export interface Item {
  id: number;
  status: string;
  name: string;
  title: string;
  content: string;
  price: number;
  brand_name: string;
  registered_at: string;
  unregistered_at: string | null;
  partner_id: number;
}

export interface Partner {
  id: number;
  name: string;
  address: string;
  call_number: string;
  partner_number: string;
  business_number: string;
  ceo_name: string;
  registered_at: string;
  unregistered_at: string;
  category_id: number;
}

export interface OrderGroup {
  id: number;
  status: string;
  order_type: string;
  rev_address: string;
  rev_name: string;
  payment_type: string;
  total_price: number;
  total_quantity: number;
  order_at: string;
  arrival_date: string;
}

export interface PaginationProps {
  total_pages: number;
  total_elements: number;
  current_page: number;
  current_elements: number;
  current_size: number;
}
