CREATE TABLE public."user"
(
    id integer DEFAULT nextval('user_user_id_seq'::regclass) PRIMARY KEY NOT NULL,
    full_name varchar(100),
    passport varchar(10),
    password varchar(200),
    type varchar(5) NOT NULL
);
CREATE UNIQUE INDEX user_user_id_uindex ON public."user" (id);
INSERT INTO public."user" (id, full_name, passport, password, type) VALUES (46, 'Мухаметзянов Азат Иванович', '8012674598', '$2a$04$O/Fh9ZNjkzUqTp1nCHA/reu41BN1Q9kMlsqBrktgkW7RNu84LT3oK', 'user');
INSERT INTO public."user" (id, full_name, passport, password, type) VALUES (47, 'Зайдуллин Самат Вильянисович', '8013567891', '$2a$04$RZ4h7Roaf2MpYSUTnI.lSu9S/piVe9xMi7ls3GMibe0EHpUvSNudO', 'user');
INSERT INTO public."user" (id, full_name, passport, password, type) VALUES (48, 'Мухаметьянов Айдар Фаритович', '8011676688', '$2a$04$BGGOtcvt8pNg4PjFzuLUc.iVD1a4B5WQ3jCQmhzyQ/0h31oOSNA1q', 'user');
INSERT INTO public."user" (id, full_name, passport, password, type) VALUES (49, 'Алешин Никита Сергеевич', '8010994533', '$2a$04$DE0MyFtkwNbNpwx2eCGzuuvZIGX2eJU8TqPlZwi1blCYQQvm5QMfC', 'user');
INSERT INTO public."user" (id, full_name, passport, password, type) VALUES (50, 'Карпова Ксения Вячеславовна', '8010888867', '$2a$04$lMjNNdVb2BlB99IoAgvoYOiMezmn7r6YklVmmzxYf5yHuL0e57oPO', 'user');
INSERT INTO public."user" (id, full_name, passport, password, type) VALUES (52, 'Иванов Иван Иванович', '8045858236', '$2a$04$aiM27zCFVaU06RJDyvw1S.N2blYehD70WQvb18vUEv3NOXohoG8KG', 'user');
INSERT INTO public."user" (id, full_name, passport, password, type) VALUES (17, 'Иванова Елена Петровна', '0000000000', '$2a$04$.5Ln5MIO6y0SKSrhJ8lK2e8VVvIlaANtOjjihpta9JCIHPSNe/TAq', 'admin');
INSERT INTO public."user" (id, full_name, passport, password, type) VALUES (51, 'Чернышева Ксения Сергеевна', '8013857433', '$2a$04$.5Ln5MIO6y0SKSrhJ8lK2e8VVvIlaANtOjjihpta9JCIHPSNe/TAq', 'user');
INSERT INTO public."user" (id, full_name, passport, password, type) VALUES (45, 'Калимуллин Эрнест Ришатович', '8013838731', '$2a$04$fhC9fyESCZ.3T6PJvO.nCOFz0niiKvH9Ki8FnwJusAYwNfxuwEdXm', 'user');