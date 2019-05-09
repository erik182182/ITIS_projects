CREATE TABLE public.city
(
    id integer DEFAULT nextval('city_id_city_seq'::regclass) PRIMARY KEY NOT NULL,
    name varchar(30)
);
INSERT INTO public.city (id, name) VALUES (4, 'Казань');
INSERT INTO public.city (id, name) VALUES (5, 'Москва');
INSERT INTO public.city (id, name) VALUES (6, 'Уфа');
INSERT INTO public.city (id, name) VALUES (7, 'Санкт-Петербург');
INSERT INTO public.city (id, name) VALUES (8, 'Екатеринбург');