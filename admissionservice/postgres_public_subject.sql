CREATE TABLE public.subject
(
    id integer DEFAULT nextval('subject_id_subj_seq'::regclass) PRIMARY KEY NOT NULL,
    name varchar(20)
);
CREATE UNIQUE INDEX subject_id_subj_uindex ON public.subject (id);
INSERT INTO public.subject (id, name) VALUES (1, 'Информатика');
INSERT INTO public.subject (id, name) VALUES (2, 'Математика');
INSERT INTO public.subject (id, name) VALUES (3, 'Русский язык');
INSERT INTO public.subject (id, name) VALUES (4, 'Физика');
INSERT INTO public.subject (id, name) VALUES (5, 'Биология');
INSERT INTO public.subject (id, name) VALUES (6, 'Химия');
INSERT INTO public.subject (id, name) VALUES (7, 'Обществознание');
INSERT INTO public.subject (id, name) VALUES (8, 'История');
INSERT INTO public.subject (id, name) VALUES (9, 'Литература');