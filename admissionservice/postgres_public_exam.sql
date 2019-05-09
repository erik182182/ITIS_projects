CREATE TABLE public.exam
(
    id_user integer,
    id_subj integer,
    score integer,
    id integer DEFAULT nextval('exam_id_seq'::regclass) PRIMARY KEY NOT NULL,
    CONSTRAINT exam_user_user_id_fk FOREIGN KEY (id_user) REFERENCES public."user" (),
    CONSTRAINT exam_subject_id_subj_fk FOREIGN KEY (id_subj) REFERENCES public.subject ()
);
CREATE UNIQUE INDEX exam_id_uindex ON public.exam (id);
INSERT INTO public.exam (id_user, id_subj, score, id) VALUES (45, 1, 94, 103);
INSERT INTO public.exam (id_user, id_subj, score, id) VALUES (45, 2, 82, 104);
INSERT INTO public.exam (id_user, id_subj, score, id) VALUES (45, 3, 91, 105);
INSERT INTO public.exam (id_user, id_subj, score, id) VALUES (45, 4, 76, 106);
INSERT INTO public.exam (id_user, id_subj, score, id) VALUES (46, 1, 94, 108);
INSERT INTO public.exam (id_user, id_subj, score, id) VALUES (46, 2, 82, 110);
INSERT INTO public.exam (id_user, id_subj, score, id) VALUES (46, 3, 88, 111);
INSERT INTO public.exam (id_user, id_subj, score, id) VALUES (47, 1, 99, 112);
INSERT INTO public.exam (id_user, id_subj, score, id) VALUES (47, 2, 99, 113);
INSERT INTO public.exam (id_user, id_subj, score, id) VALUES (47, 3, 99, 114);
INSERT INTO public.exam (id_user, id_subj, score, id) VALUES (47, 4, 99, 115);
INSERT INTO public.exam (id_user, id_subj, score, id) VALUES (47, 5, 99, 116);
INSERT INTO public.exam (id_user, id_subj, score, id) VALUES (47, 6, 99, 117);
INSERT INTO public.exam (id_user, id_subj, score, id) VALUES (47, 7, 99, 118);
INSERT INTO public.exam (id_user, id_subj, score, id) VALUES (47, 8, 99, 119);
INSERT INTO public.exam (id_user, id_subj, score, id) VALUES (47, 9, 99, 120);
INSERT INTO public.exam (id_user, id_subj, score, id) VALUES (48, 2, 80, 121);
INSERT INTO public.exam (id_user, id_subj, score, id) VALUES (48, 3, 80, 122);
INSERT INTO public.exam (id_user, id_subj, score, id) VALUES (48, 4, 80, 123);
INSERT INTO public.exam (id_user, id_subj, score, id) VALUES (49, 1, 90, 124);
INSERT INTO public.exam (id_user, id_subj, score, id) VALUES (49, 2, 90, 125);
INSERT INTO public.exam (id_user, id_subj, score, id) VALUES (49, 3, 98, 126);
INSERT INTO public.exam (id_user, id_subj, score, id) VALUES (49, 4, 96, 127);
INSERT INTO public.exam (id_user, id_subj, score, id) VALUES (50, 1, 94, 128);
INSERT INTO public.exam (id_user, id_subj, score, id) VALUES (50, 5, 95, 129);
INSERT INTO public.exam (id_user, id_subj, score, id) VALUES (50, 6, 96, 130);
INSERT INTO public.exam (id_user, id_subj, score, id) VALUES (51, 1, 100, 131);
INSERT INTO public.exam (id_user, id_subj, score, id) VALUES (51, 2, 100, 132);
INSERT INTO public.exam (id_user, id_subj, score, id) VALUES (51, 3, 100, 133);
INSERT INTO public.exam (id_user, id_subj, score, id) VALUES (51, 4, 100, 134);
INSERT INTO public.exam (id_user, id_subj, score, id) VALUES (51, 5, 100, 135);
INSERT INTO public.exam (id_user, id_subj, score, id) VALUES (51, 6, 100, 136);
INSERT INTO public.exam (id_user, id_subj, score, id) VALUES (51, 7, 100, 137);
INSERT INTO public.exam (id_user, id_subj, score, id) VALUES (51, 8, 100, 138);
INSERT INTO public.exam (id_user, id_subj, score, id) VALUES (51, 9, 100, 139);
INSERT INTO public.exam (id_user, id_subj, score, id) VALUES (52, 2, 15, 140);
INSERT INTO public.exam (id_user, id_subj, score, id) VALUES (52, 3, 12, 141);
INSERT INTO public.exam (id_user, id_subj, score, id) VALUES (52, 4, 11, 142);