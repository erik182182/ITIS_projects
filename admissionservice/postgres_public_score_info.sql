CREATE TABLE public.score_info
(
    id_dir integer,
    id_subj integer,
    min_score integer,
    CONSTRAINT score_info_direction_id_fk FOREIGN KEY (id_dir) REFERENCES public.direction (id),
    CONSTRAINT score_info_subject_id_fk FOREIGN KEY (id_subj) REFERENCES public.subject (id)
);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (20, 2, 30);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (20, 3, 27);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (20, 4, 35);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (11, 2, 35);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (11, 3, 30);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (11, 4, 32);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (13, 2, 30);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (13, 3, 35);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (13, 4, 25);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (13, 1, 30);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (15, 2, 30);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (15, 3, 25);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (15, 4, 40);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (16, 1, 50);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (16, 2, 50);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (16, 3, 50);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (16, 4, 50);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (16, 5, 50);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (16, 6, 50);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (16, 7, 50);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (16, 8, 50);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (16, 9, 50);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (14, 3, 30);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (14, 5, 24);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (14, 6, 37);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (21, 2, 25);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (21, 3, 26);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (21, 4, 27);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (19, 3, 30);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (19, 5, 35);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (19, 6, 27);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (17, 1, 30);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (17, 2, 25);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (12, 3, 30);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (12, 5, 26);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (12, 6, 23);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (9, 1, 20);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (9, 2, 20);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (9, 3, 20);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (10, 1, 40);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (10, 2, 40);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (10, 3, 40);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (17, 3, 29);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (18, 2, 27);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (18, 3, 28);
INSERT INTO public.score_info (id_dir, id_subj, min_score) VALUES (18, 4, 29);