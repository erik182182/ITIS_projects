CREATE TABLE public.declaration
(
    id_user integer,
    id_dir integer,
    sum_score integer,
    id integer DEFAULT nextval('application_id_seq'::regclass) PRIMARY KEY NOT NULL,
    CONSTRAINT declaration_user_id_fk FOREIGN KEY (id_user) REFERENCES public."user" (id),
    CONSTRAINT declaration_direction_id_fk FOREIGN KEY (id_dir) REFERENCES public.direction (id)
);
CREATE UNIQUE INDEX application_id_uindex ON public.declaration (id);
INSERT INTO public.declaration (id_user, id_dir, sum_score, id) VALUES (46, 10, 264, 67);
INSERT INTO public.declaration (id_user, id_dir, sum_score, id) VALUES (51, 16, 900, 68);
INSERT INTO public.declaration (id_user, id_dir, sum_score, id) VALUES (51, 10, 300, 69);
INSERT INTO public.declaration (id_user, id_dir, sum_score, id) VALUES (51, 20, 300, 70);
INSERT INTO public.declaration (id_user, id_dir, sum_score, id) VALUES (47, 10, 297, 71);
INSERT INTO public.declaration (id_user, id_dir, sum_score, id) VALUES (47, 16, 891, 72);
INSERT INTO public.declaration (id_user, id_dir, sum_score, id) VALUES (46, 17, 264, 73);
INSERT INTO public.declaration (id_user, id_dir, sum_score, id) VALUES (48, 15, 240, 82);
INSERT INTO public.declaration (id_user, id_dir, sum_score, id) VALUES (48, 18, 240, 83);
INSERT INTO public.declaration (id_user, id_dir, sum_score, id) VALUES (48, 11, 240, 84);
INSERT INTO public.declaration (id_user, id_dir, sum_score, id) VALUES (49, 18, 284, 85);
INSERT INTO public.declaration (id_user, id_dir, sum_score, id) VALUES (49, 20, 284, 86);
INSERT INTO public.declaration (id_user, id_dir, sum_score, id) VALUES (45, 10, 267, 90);
INSERT INTO public.declaration (id_user, id_dir, sum_score, id) VALUES (45, 21, 249, 92);
INSERT INTO public.declaration (id_user, id_dir, sum_score, id) VALUES (45, 11, 249, 96);