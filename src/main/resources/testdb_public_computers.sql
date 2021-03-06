INSERT INTO public.computers (id, computer_name, ip_addres, os_name, os_version, bit_os, cpu, rm, disk_space, freediskspace, filldiskspace, screenquality, video_card, cpu_id, ball) VALUES (4, 'R5', '192.168.0.4', 'Windows', 'Windows 10', 'win86', 'Intel core i  generation', 8, 1000, 530, 470, '1200 X 800', 'NVideo Geforce 1024', '124.001', 0);
INSERT INTO public.computers (id, computer_name, ip_addres, os_name, os_version, bit_os, cpu, rm, disk_space, freediskspace, filldiskspace, screenquality, video_card, cpu_id, ball) VALUES (7, 'R3', '192.168.0.7', 'Windows', 'Windows 7', 'win32', 'Intel core i  generation', 8, 1000, 530, 470, '1200 X 800', 'NVideo Geforce 1024', '124.001', 0);
INSERT INTO public.computers (id, computer_name, ip_addres, os_name, os_version, bit_os, cpu, rm, disk_space, freediskspace, filldiskspace, screenquality, video_card, cpu_id, ball) VALUES (1, 'R6', '192.168.0.1', 'Windows', 'Windows 8', 'win64', 'Intel core i  generation', 8, 1000, 530, 470, '1200 X 800', 'NVideo Geforce 1024', '124.001', 0);
INSERT INTO public.computers (id, computer_name, ip_addres, os_name, os_version, bit_os, cpu, rm, disk_space, freediskspace, filldiskspace, screenquality, video_card, cpu_id, ball) VALUES (3, 'R2', '192.168.0.3', 'Windows', 'Windows 8', 'win64', 'Intel core i  generation', 8, 1000, 530, 470, '1200 X 800', 'NVideo Standard 1024', '124.001', 0);
INSERT INTO public.computers (id, computer_name, ip_addres, os_name, os_version, bit_os, cpu, rm, disk_space, freediskspace, filldiskspace, screenquality, video_card, cpu_id, ball) VALUES (5, 'R4', '192.168.0.5', 'Ubuntu', 'Ubuntu 16', 'nill', 'Intel core i  generation', 8, 2000, 530, 470, '1200 X 800', 'NVideo Geforce 1024', '124.001', 0);
INSERT INTO public.computers (id, computer_name, ip_addres, os_name, os_version, bit_os, cpu, rm, disk_space, freediskspace, filldiskspace, screenquality, video_card, cpu_id, ball) VALUES (6, 'R7', '192.168.0.6', 'Ubuntu', 'Ubuntu 15', 'nill', 'Intel core i  generation', 8, 2000, 530, 470, '1200 X 800', 'NVideo 512', '124.001', 0);
INSERT INTO public.computers (id, computer_name, ip_addres, os_name, os_version, bit_os, cpu, rm, disk_space, freediskspace, filldiskspace, screenquality, video_card, cpu_id, ball) VALUES (2, 'R8', '192.168.0.2', 'Windows', 'Windows 10', 'win64', 'Intel core i  generation', 8, 2000, 530, 1470, '1200 X 800', 'NVideo Geforce 2048', '124.001', 0);


create table computers(
  id serial primary key,
  computer_name text,
  ip_addres text,
  os_name text,
  os_version text,
  bit_os text,
  cpu text,
  rm int,
  disk_space int,
  freeDiskSpace int,
  fillDiskSpace int,
  screenQuality text,
  video_card text,
  cpu_id text,
  ball int
);