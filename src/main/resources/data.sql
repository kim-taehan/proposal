insert into tb_customer(customer_id, customer_name, customer_type)
values ( nextval('tb_customer_seq'), '우리은행', 'BANK'),
      (  nextval('tb_customer_seq'),'신한은행', 'BANK'),
      (  nextval('tb_customer_seq'),'현대해상', 'INSURANCE');

insert into tb_team(team_id, team_name)
values ( nextval('tb_team_seq'), '금융 이노베이션1팀'),( nextval('tb_team_seq'), '금융 이노베이션2팀');

insert into tb_users(team_id, user_id, username, password, real_name, role)
values
 (1, nextval('tb_users_seq'), '08368', '$2a$12$jcKXsj4ZAIkGgZdnUQ6EcOduMlurEtX7Szjhr.kQp2iQXNucjZMI6', '김태한', 'ADMIN')
,(1, nextval('tb_users_seq'), '00001', '$2a$12$jcKXsj4ZAIkGgZdnUQ6EcOduMlurEtX7Szjhr.kQp2iQXNucjZMI6', '정일호', 'USER')
,(1, nextval('tb_users_seq'), '00002', '$2a$12$jcKXsj4ZAIkGgZdnUQ6EcOduMlurEtX7Szjhr.kQp2iQXNucjZMI6', '문병훈', 'USER')
,(1, nextval('tb_users_seq'), '00003', '$2a$12$jcKXsj4ZAIkGgZdnUQ6EcOduMlurEtX7Szjhr.kQp2iQXNucjZMI6', '이순자', 'USER')
,(1, nextval('tb_users_seq'), '00004', '$2a$12$jcKXsj4ZAIkGgZdnUQ6EcOduMlurEtX7Szjhr.kQp2iQXNucjZMI6', '정영준', 'USER')
,(1, nextval('tb_users_seq'), '00005', '$2a$12$jcKXsj4ZAIkGgZdnUQ6EcOduMlurEtX7Szjhr.kQp2iQXNucjZMI6', '권슬기', 'USER')
,(1, nextval('tb_users_seq'), '00006', '$2a$12$jcKXsj4ZAIkGgZdnUQ6EcOduMlurEtX7Szjhr.kQp2iQXNucjZMI6', '한세미', 'USER')
,(51, nextval('tb_users_seq'), '00007', '$2a$12$jcKXsj4ZAIkGgZdnUQ6EcOduMlurEtX7Szjhr.kQp2iQXNucjZMI6', '제니', 'USER')
,(51, nextval('tb_users_seq'), '00008', '$2a$12$jcKXsj4ZAIkGgZdnUQ6EcOduMlurEtX7Szjhr.kQp2iQXNucjZMI6', '리사', 'USER')
,(51, nextval('tb_users_seq'), '00009', '$2a$12$jcKXsj4ZAIkGgZdnUQ6EcOduMlurEtX7Szjhr.kQp2iQXNucjZMI6', '로제', 'USER')
,(51, nextval('tb_users_seq'), '00010', '$2a$12$jcKXsj4ZAIkGgZdnUQ6EcOduMlurEtX7Szjhr.kQp2iQXNucjZMI6', '지수', 'USER')