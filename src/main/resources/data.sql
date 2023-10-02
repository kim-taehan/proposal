insert into tb_customer(customer_id, customer_name, customer_type)
values ( nextval('tb_customer_seq'), '우리은행', 'BANK'),
      (  nextval('tb_customer_seq'),'신한은행', 'BANK'),
      (  nextval('tb_customer_seq'),'현대해상', 'INSURANCE');