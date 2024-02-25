
--
-- PostgreSQL database dump
--

-- Dumped from database version 16.1 (Debian 16.1-1.pgdg120+1)
-- Dumped by pg_dump version 16.1 (Debian 16.1-1.pgdg120+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;






SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: _user; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public._user  (
    birthdate date,
    user_id bigint NOT NULL,
    email character varying(255) UNIQUE,
    firstname character varying(255),
    lastname character varying(255),
    password character varying(255),
    role character varying(255),
    CONSTRAINT _user_role_check CHECK (((role)::text = ANY ((ARRAY['USER'::character varying, 'ADMIN'::character varying, 'MANAGER'::character varying])::text[])))
);



ALTER TABLE public._user OWNER TO "postgresMaster";

--
-- Name: _user_seq; Type: SEQUENCE; Schema: public; Owner: postgresMaster
--

CREATE SEQUENCE public._user_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public._user_seq OWNER TO "postgresMaster";

--
-- Name: actor; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public.actor (
    actor_id bigint NOT NULL,
    actor_name character varying(100),
    actor_img character varying(200)
);


ALTER TABLE public.actor OWNER TO "postgresMaster";

--
-- Name: actor_id_seq; Type: SEQUENCE; Schema: public; Owner: postgresMaster
--

CREATE SEQUENCE public.actor_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.actor_id_seq OWNER TO "postgresMaster";

--
-- Name: actor_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgresMaster
--

ALTER SEQUENCE public.actor_id_seq OWNED BY public.actor.actor_id;


--
-- Name: feedback; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public.feedback (
    feedback_id bigint NOT NULL,
    user_id bigint,
    film_id bigint,
    rating integer,
    comment text
);


ALTER TABLE public.feedback OWNER TO "postgresMaster";

--
-- Name: feedback_id_seq; Type: SEQUENCE; Schema: public; Owner: postgresMaster
--

CREATE SEQUENCE public.feedback_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.feedback_id_seq OWNER TO "postgresMaster";

--
-- Name: feedback_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgresMaster
--

ALTER SEQUENCE public.feedback_id_seq OWNED BY public.feedback.feedback_id;


--
-- Name: film; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public.film (
    film_id bigint NOT NULL,
    title character varying(255),
    description character varying(1000),
    director character varying(100),
    release_year integer,
    poster_img character varying(600),
    duration integer
);


ALTER TABLE public.film OWNER TO "postgresMaster";

--
-- Name: film_actor; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public.performance (
    performance_id bigint NOT NULL,
    film_id bigint NOT NULL,
    actor_id bigint NOT NULL,
    role_name character varying,
    description character varying
);


ALTER TABLE public.performance OWNER TO "postgresMaster";

CREATE SEQUENCE public.performance_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.performance_id_seq OWNER TO "postgresMaster";

--
-- Name: film_genre; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public.film_genre (
    film_id bigint NOT NULL,
    genre_id bigint NOT NULL
);


ALTER TABLE public.film_genre OWNER TO "postgresMaster";

--
-- Name: film_id_seq; Type: SEQUENCE; Schema: public; Owner: postgresMaster
--

CREATE SEQUENCE public.film_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.film_id_seq OWNER TO "postgresMaster";

--
-- Name: film_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgresMaster
--

ALTER SEQUENCE public.film_id_seq OWNED BY public.film.film_id;


--
-- Name: film_projection; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public.film_projection (
    projection_id bigint NOT NULL,
    film_id bigint,
    hall_id bigint,
    projection_date date,
    projection_time time,
    ticket_price bigint
);


ALTER TABLE public.film_projection OWNER TO "postgresMaster";

--
-- Name: film_projection_id_seq; Type: SEQUENCE; Schema: public; Owner: postgresMaster
--

CREATE SEQUENCE public.film_projection_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.film_projection_id_seq OWNER TO "postgresMaster";

--
-- Name: film_projection_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgresMaster
--

ALTER SEQUENCE public.film_projection_id_seq OWNED BY public.film_projection.projection_id;


--
-- Name: genre; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public.genre (
    genre_id bigint NOT NULL,
    genre_name character varying(50)
);


ALTER TABLE public.genre OWNER TO "postgresMaster";

--
-- Name: genre_id_seq; Type: SEQUENCE; Schema: public; Owner: postgresMaster
--

CREATE SEQUENCE public.genre_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.genre_id_seq OWNER TO "postgresMaster";

--
-- Name: genre_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgresMaster
--

ALTER SEQUENCE public.genre_id_seq OWNED BY public.genre.genre_id;


--
-- Name: hall; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public.hall (
    hall_id bigint NOT NULL,
    name character varying(100),
    row_seat integer,
    row_column integer

);


ALTER TABLE public.hall OWNER TO "postgresMaster";

--
-- Name: hall_id_seq; Type: SEQUENCE; Schema: public; Owner: postgresMaster
--

CREATE SEQUENCE public.hall_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.hall_id_seq OWNER TO "postgresMaster";

--
-- Name: hall_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgresMaster
--

ALTER SEQUENCE public.hall_id_seq OWNED BY public.hall.hall_id;


--
-- Name: reservation; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public.reservation (
    reservation_id bigint NOT NULL,
    user_id bigint,
    projection_id bigint,
    reserved_seat integer,
    is_on_discount boolean,
    date timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.reservation OWNER TO "postgresMaster";

--
-- Name: reservation_id_seq; Type: SEQUENCE; Schema: public; Owner: postgresMaster
--

CREATE SEQUENCE public.reservation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.reservation_id_seq OWNER TO "postgresMaster";

--
-- Name: reservation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgresMaster
--

ALTER SEQUENCE public.reservation_id_seq OWNED BY public.reservation.reservation_id;



--
-- Name: seat_id_seq; Type: SEQUENCE; Schema: public; Owner: postgresMaster
--


--
-- Name: token; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public.token (
    expired boolean NOT NULL,
    id integer NOT NULL,
    revoked boolean NOT NULL,
    user_id bigint,
    token character varying(255),
    token_type character varying(255),
    CONSTRAINT token_token_type_check CHECK (((token_type)::text = 'BEARER'::text))
);


ALTER TABLE public.token OWNER TO "postgresMaster";

--
-- Name: token_seq; Type: SEQUENCE; Schema: public; Owner: postgresMaster
--

CREATE SEQUENCE public.token_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.token_seq OWNER TO "postgresMaster";

--
-- Name: actor actor_id; Type: DEFAULT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.actor ALTER COLUMN actor_id SET DEFAULT nextval('public.actor_id_seq'::regclass);


--
-- Name: feedback feedback_id; Type: DEFAULT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.feedback ALTER COLUMN feedback_id SET DEFAULT nextval('public.feedback_id_seq'::regclass);

ALTER TABLE ONLY public.performance ALTER COLUMN performance_id SET DEFAULT nextval('public.performance_id_seq'::regclass);


--
-- Name: film film_id; Type: DEFAULT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.film ALTER COLUMN film_id SET DEFAULT nextval('public.film_id_seq'::regclass);


--
-- Name: film_projection projection_id; Type: DEFAULT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.film_projection ALTER COLUMN projection_id SET DEFAULT nextval('public.film_projection_id_seq'::regclass);


--
-- Name: genre genre_id; Type: DEFAULT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.genre ALTER COLUMN genre_id SET DEFAULT nextval('public.genre_id_seq'::regclass);


--
-- Name: hall hall_id; Type: DEFAULT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.hall ALTER COLUMN hall_id SET DEFAULT nextval('public.hall_id_seq'::regclass);


--
-- Name: reservation reservation_id; Type: DEFAULT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.reservation ALTER COLUMN reservation_id SET DEFAULT nextval('public.reservation_id_seq'::regclass);


--
-- Name: _user_seq; Type: SEQUENCE SET; Schema: public; Owner: postgresMaster
--

SELECT pg_catalog.setval('public._user_seq', 7, false);


--
-- Name: actor_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgresMaster
--

SELECT pg_catalog.setval('public.actor_id_seq',1, false);


--
-- Name: feedback_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgresMaster
--

SELECT pg_catalog.setval('public.feedback_id_seq', 1, false);

SELECT pg_catalog.setval('public.performance_id_seq', 1, false);


--
-- Name: film_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgresMaster
--

SELECT pg_catalog.setval('public.film_id_seq', 1, false);


--
-- Name: film_projection_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgresMaster
--

SELECT pg_catalog.setval('public.film_projection_id_seq', 1, false);


--
-- Name: genre_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgresMaster
--

SELECT pg_catalog.setval('public.genre_id_seq', 1, false);


--
-- Name: hall_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgresMaster
--

SELECT pg_catalog.setval('public.hall_id_seq', 1, false);


--
-- Name: reservation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgresMaster
--

SELECT pg_catalog.setval('public.reservation_id_seq', 1, false);



--
-- Name: token_seq; Type: SEQUENCE SET; Schema: public; Owner: postgresMaster
--

SELECT pg_catalog.setval('public.token_seq', 1, false);


--
-- Name: _user _user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public._user
    ADD CONSTRAINT _user_pkey PRIMARY KEY (user_id);



--
-- Name: actor actor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.actor
    ADD CONSTRAINT actor_pkey PRIMARY KEY (actor_id);


--
-- Name: feedback feedback_pkey; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.feedback
    ADD CONSTRAINT feedback_pkey PRIMARY KEY (feedback_id);


--
-- Name: film_actor film_actor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.performance
    ADD CONSTRAINT performance_pkey PRIMARY KEY (performance_id);


--
-- Name: film_genre film_genre_pkey; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.film_genre
    ADD CONSTRAINT film_genre_pkey PRIMARY KEY (film_id, genre_id);


--
-- Name: film film_pkey; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.film
    ADD CONSTRAINT film_pkey PRIMARY KEY (film_id);


--
-- Name: film_projection film_projection_pkey; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.film_projection
    ADD CONSTRAINT film_projection_pkey PRIMARY KEY (projection_id);


--
-- Name: genre genre_pkey; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.genre
    ADD CONSTRAINT genre_pkey PRIMARY KEY (genre_id);


--
-- Name: hall hall_pkey; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.hall
    ADD CONSTRAINT hall_pkey PRIMARY KEY (hall_id);


--
-- Name: reservation reservation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (reservation_id);





--
-- Name: token token_pkey; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.token
    ADD CONSTRAINT token_pkey PRIMARY KEY (id);


--
-- Name: token token_token_key; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.token
    ADD CONSTRAINT token_token_key UNIQUE (token);


--
-- Name: feedback feedback_film_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.feedback
    ADD CONSTRAINT feedback_film_id_fkey FOREIGN KEY (film_id) REFERENCES public.film(film_id)
    ON DELETE CASCADE;


--
-- Name: feedback feedback_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.feedback
    ADD CONSTRAINT feedback_user_id_fkey FOREIGN KEY (user_id) REFERENCES public._user(user_id)
    ON DELETE CASCADE;


--
-- Name: film_actor film_actor_actor_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.performance
    ADD CONSTRAINT performance_actor_id_fkey FOREIGN KEY (actor_id) REFERENCES public.actor(actor_id);


--
-- Name: film_actor film_actor_film_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.performance
    ADD CONSTRAINT performance_film_id_fkey FOREIGN KEY (film_id) REFERENCES public.film(film_id)
    ON DELETE CASCADE;


--
-- Name: film_genre film_genre_film_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.film_genre
    ADD CONSTRAINT film_genre_film_id_fkey FOREIGN KEY (film_id) REFERENCES public.film(film_id)
    ON DELETE CASCADE;


--
-- Name: film_genre film_genre_genre_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.film_genre
    ADD CONSTRAINT film_genre_genre_id_fkey FOREIGN KEY (genre_id) REFERENCES public.genre(genre_id);


--
-- Name: film_projection film_projection_film_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.film_projection
    ADD CONSTRAINT film_projection_film_id_fkey FOREIGN KEY (film_id) REFERENCES public.film(film_id)
    ON DELETE CASCADE;


--
-- Name: film_projection film_projection_hall_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.film_projection
    ADD CONSTRAINT film_projection_hall_id_fkey FOREIGN KEY (hall_id) REFERENCES public.hall(hall_id);


--
-- Name: token fkiblu4cjwvyntq3ugo31klp1c6; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.token
    ADD CONSTRAINT token_user_id_fkey FOREIGN KEY (user_id) REFERENCES public._user(user_id)
    ON DELETE CASCADE;


--
-- Name: reservation reservation_projection_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_projection_id_fkey FOREIGN KEY (projection_id) REFERENCES public.film_projection(projection_id);



--
-- Name: reservation reservation_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_user_id_fkey FOREIGN KEY (user_id) REFERENCES public._user(user_id)
    ON DELETE CASCADE;



--
-- PostgreSQL database dump complete
--


-- Inserimento di attori (actor)
INSERT INTO public.actor (actor_id, actor_name, actor_img)
VALUES
  (nextval('public.actor_id_seq'), 'Margot Robbie', 'https://image.tmdb.org/t/p/w500/euDPyqLnuwaWMHajcU3oZ9uZezR.jpg'),
  (nextval('public.actor_id_seq'), 'Ryan Gosling', 'https://cdn.britannica.com/93/215393-050-E428CADE/Canadian-actor-musician-Ryan-Gosling-2016.jpg?w=400&h=300&c=crop'),
  (nextval('public.actor_id_seq'), 'Cillian Murphy', 'https://resizing.flixster.com/-XZAfHZM39UwaGJIFWKAE8fS0ak=/v3/t/assets/236083_v9_bd.jpg'),
  (nextval('public.actor_id_seq'), 'Matt Damon', 'https://m.media-amazon.com/images/M/MV5BMTU0MTQ4OTMyMV5BMl5BanBnXkFtZTcwMTQxOTY1NA@@._V1_.jpg'),
  (nextval('public.actor_id_seq'), 'Robert Downey Jr.', 'https://upload.wikimedia.org/wikipedia/commons/thumb/9/94/Robert_Downey_Jr_2014_Comic_Con_%28cropped%29.jpg/640px-Robert_Downey_Jr_2014_Comic_Con_%28cropped%29.jpg'),
  (nextval('public.actor_id_seq'), 'Scarlett Johansson', 'https://media.gqitalia.it/photos/5f9bb303b16a44e7b421f225/master/pass/40-datos-curiosos-para-descubrir-a-scarlett-johansson.jpg'),
  (nextval('public.actor_id_seq'), 'Mark Ruffalo', 'https://m.media-amazon.com/images/M/MV5BNWIzZTI1ODUtZTUzMC00NTdiLWFlOTYtZTg4MGZkYmU4YzNlXkEyXkFqcGdeQXVyNTExOTk5Nzg@._V1_.jpg'),
  (nextval('public.actor_id_seq'), 'Gian', 'https://media.licdn.com/dms/image/D4E03AQEkkc5C5GfMig/profile-displayphoto-shrink_800_800/0/1688381929614?e=2147483647&v=beta&t=M_AqJX-O994UyeC7lp_ruOpMhzPZDwYFYGUxXmquUWA'),
  (nextval('public.actor_id_seq'), 'Riccardo', 'https://cdn.oreillystatic.com/authors/trim/prod/images/39372-200x200.jpg'),
  (nextval('public.actor_id_seq'), 'Bruce Campbell', 'https://i.guim.co.uk/img/static/sys-images/Film/Pix/pictures/2009/2/13/1234544417531/Bruce-Campbell-in-Army-of-001.jpg?width=465&dpr=1&s=none'),
  (nextval('public.actor_id_seq'), 'Hugh Grant', 'https://m.media-amazon.com/images/M/MV5BMTc4MTgxOTk2Ml5BMl5BanBnXkFtZTcwNzMwMjYwMw@@._V1_.jpg'),
  (nextval('public.actor_id_seq'), 'Paolo Villaggio', 'https://pad.mymovies.it/filmclub/attori/387.jpg'),
  (nextval('public.actor_id_seq'), 'Emma Stone', 'https://m.media-amazon.com/images/M/MV5BMjI4NjM1NDkyN15BMl5BanBnXkFtZTgwODgyNTY1MjE@._V1_.jpg'),
  (nextval('public.actor_id_seq'), 'Mario Reda', 'https://www.madmass.it/wp-content/uploads/2021/11/leonardo-dicaprio-migliori-interpretazioni-drammatiche-1.jpg'),
  (nextval('public.actor_id_seq'), 'Jim Carrey ', 'https://www.usatoday.com/gcdn/-mm-/d25c78a66612aa7d2186241811a4aae48d47a9a9/c=0-304-3648-5168/local/-/media/2017/06/01/USATODAY/USATODAY/636319345956925087-Jim-Carrey-beard-1.jpg');
-- Inserimento di film (film)
INSERT INTO public.film (title)
VALUES
  ( 'Barbie'),
  ( 'Oppenheimer'),
  ( 'The Holdovers'),
  ( 'Killers of The Flower Moon'),
  ('Past Lives'),
  ('The zone of interest'),
  ('Rustin'),
  ('Nyad'),
  ('The Color Purple'),
  ( 'Anatomy of a Fall'),
  ( 'May December'),
  ('American Fiction'),
  ('Maestro'),
  ('Elemental'),
  ( 'Poor Things');

-- Inserimento di generi (genre)
INSERT INTO public.genre (genre_id, genre_name) VALUES
  (nextval('public.genre_id_seq'), 'Drama'),
  (nextval('public.genre_id_seq'), 'Crime'),
  (nextval('public.genre_id_seq'), 'Action'),
  (nextval('public.genre_id_seq'), 'Fantasy'),
  (nextval('public.genre_id_seq'), 'Thriller'),
  (nextval('public.genre_id_seq'), 'Horror'),
  (nextval('public.genre_id_seq'), 'Romance'),
  (nextval('public.genre_id_seq'), 'Mystery'),
  (nextval('public.genre_id_seq'), 'Comedy'),
  (nextval('public.genre_id_seq'), 'Animation');

-- Inserimento di sale (hall)
INSERT INTO public.hall (hall_id, name, row_seat, row_column)
VALUES
  (1, 'Hall Spielberg', 5, 8),
  (2, 'Hall Hepburn', 6, 10),
  (3, 'Hall Hitchcock', 3, 8),
  (4, 'Hall Kubrick', 10, 14);



  -- Inserimento di film_actor (film, actor)
  INSERT INTO public.performance (film_id, actor_id, role_name)
  VALUES
      (1, 1, 'Ken'),
      (1, 2, 'Barbie'),
      (2, 5, 'J. Robert Oppenheimer'),
      (2, 3, 'General Leslie Groves'),
      (3, 2, 'Mr. Johnson'),
      (3, 12, 'Mrs. Smith'),
      (4, 14, 'se stesso'),
      (5, 8, 'Voldemort'),
      (5, 9, 'Albus Silente'),
      (6, 10, 'Winnie the Pooh'),
      (6, 3, 'Piglet'),
      (7, 2, 'Jennifer Corvino'),
      (7, 6, 'Frau Brückner'),
      (8, 10, 'Ash Williams'),
      (8, 14, 'Linda'),
      (9, 2, 'Genie'),
      (9, 13, 'Aladdin'),
      (10, 11, 'Willy Wonka'),
      (11, 3, 'Paul Bratter'),
      (11, 13, 'Corie Bratter'),
      (12, 14, 'se stesso'),
      (12, 12, 'Fracchia'),
      (13, 13, 'Victor Van Dort');


    -- Inserimento di film_genre (film, genre)
INSERT INTO public.film_genre (film_id, genre_id)
VALUES
  (1, 7),
  (1, 9),
  (1, 1),

  (2, 1),
  (2, 3),


  (3, 9),
  (3, 1),

  (4, 1),
  (4, 2),
  (4, 5),

  (5, 4),
  (5, 9),
  (5, 1),

  (6, 6),
  (6, 10),
  (6, 1),

  (7, 6),
  (7, 5),

  (8, 6),
  (8, 5),
  (8,9),

  (9, 10),
  (9, 1),

  (10, 4),
  (10, 9),

  (11, 9),
  (11, 1),

  (12, 9),
  (12, 1),

  (13, 1),
  (13, 10),
  (13, 6);

-- Inserimento di proiezioni (projection)


 -- Proiezioni per la sala 1
 -- Fracchia la Belva Umana
 INSERT INTO public.film_projection (projection_id, film_id, hall_id, projection_date, projection_time, ticket_price)
 VALUES
     (nextval('public.film_projection_id_seq'), 12, 1, '2024-02-24', '15:00', 10.00),
     (nextval('public.film_projection_id_seq'), 12, 1, '2024-02-25', '15:00', 10.00),
     (nextval('public.film_projection_id_seq'), 12, 1, '2024-02-26', '15:00', 10.00),
 -- Poor Things
     (nextval('public.film_projection_id_seq'), 13, 1, '2024-02-24', '19:00', 10.00),
     (nextval('public.film_projection_id_seq'), 13, 1, '2024-02-25', '19:00', 10.00),
     (nextval('public.film_projection_id_seq'), 13, 1, '2024-02-26', '19:00', 10.00),
 -- Wonka
     (nextval('public.film_projection_id_seq'), 10, 1, '2024-02-24', '21:00', 10.00),
     (nextval('public.film_projection_id_seq'), 10, 1, '2024-02-25', '21:00', 10.00),
     (nextval('public.film_projection_id_seq'), 10, 1, '2024-02-26', '21:00', 10.00),
     (nextval('public.film_projection_id_seq'), 14, 1, '2024-02-24', '15:00', 10.00),

       (nextval('public.film_projection_id_seq'), 14, 1, '2024-02-25', '15:00', 10.00),
       (nextval('public.film_projection_id_seq'), 14, 1, '2024-02-26', '15:00', 10.00),
       -- Another Film
       (nextval('public.film_projection_id_seq'), 15, 1, '2024-02-24', '19:00', 10.00),
       (nextval('public.film_projection_id_seq'), 15, 1, '2024-02-25', '19:00', 10.00),
       (nextval('public.film_projection_id_seq'), 15, 1, '2024-02-26', '19:00', 10.00),
       -- Yet Another Film
       (nextval('public.film_projection_id_seq'), 11, 1, '2024-02-24', '21:00', 10.00),
       (nextval('public.film_projection_id_seq'), 11, 1, '2024-02-25', '21:00', 10.00),
       (nextval('public.film_projection_id_seq'), 11, 1, '2024-02-26', '21:00', 10.00),
       -- And Another One
       (nextval('public.film_projection_id_seq'), 12, 1, '2024-02-24', '14:00', 10.00),
       (nextval('public.film_projection_id_seq'), 12, 1, '2024-02-25', '17:30', 10.00);
 -- Proiezioni per la sala 2
 -- Barbie
 INSERT INTO public.film_projection (projection_id, film_id, hall_id, projection_date, projection_time, ticket_price)
 VALUES
     (nextval('public.film_projection_id_seq'), 1, 2, '2024-02-24', '15:00', 10.00),
     (nextval('public.film_projection_id_seq'), 1, 2, '2024-02-25', '15:00', 10.00),
     (nextval('public.film_projection_id_seq'), 1, 2, '2024-02-26', '15:00', 10.00),
 -- Mario Reda
     (nextval('public.film_projection_id_seq'), 4, 2, '2024-02-24', '19:00', 10.00),
     (nextval('public.film_projection_id_seq'), 4, 2, '2024-02-25', '19:00', 10.00),
     (nextval('public.film_projection_id_seq'), 4, 2, '2024-02-26', '19:00', 10.00),
 -- THE HARRY POTTER SAGA
     (nextval('public.film_projection_id_seq'), 5, 2, '2024-02-24', '21:00', 10.00),
     (nextval('public.film_projection_id_seq'), 5, 2, '2024-02-25', '21:00', 10.00),
     (nextval('public.film_projection_id_seq'), 5, 2, '2024-02-26', '21:00', 10.00);
      INSERT INTO public.film_projection (projection_id, film_id, hall_id, projection_date, projection_time, ticket_price)
      VALUES
          (nextval('public.film_projection_id_seq'), 1, 2, '2024-02-24', '15:00', 10.00),
          (nextval('public.film_projection_id_seq'), 1, 2, '2024-02-25', '15:00', 10.00),
          (nextval('public.film_projection_id_seq'), 1, 2, '2024-02-26', '15:00', 10.00),
      -- Mario Reda
          (nextval('public.film_projection_id_seq'), 4, 2, '2024-02-24', '19:00', 10.00),
          (nextval('public.film_projection_id_seq'), 4, 2, '2024-02-25', '19:00', 10.00),
          (nextval('public.film_projection_id_seq'), 4, 2, '2024-02-26', '19:00', 10.00),
      -- THE HARRY POTTER SAGA
          (nextval('public.film_projection_id_seq'), 5, 2, '2024-02-24', '21:00', 10.00),
          (nextval('public.film_projection_id_seq'), 5, 2, '2024-02-25', '21:00', 10.00),
          (nextval('public.film_projection_id_seq'), 5, 2, '2024-02-26', '21:00', 10.00);

 -- Proiezioni per la sala 3
 -- Oppenheimer
 INSERT INTO public.film_projection (projection_id, film_id, hall_id, projection_date, projection_time, ticket_price)
 VALUES
     (nextval('public.film_projection_id_seq'), 2, 3, '2024-02-24', '15:00', 10.00),
     (nextval('public.film_projection_id_seq'), 2, 3, '2024-02-25', '15:00', 10.00),
     (nextval('public.film_projection_id_seq'), 2, 3, '2024-02-26', '15:00', 10.00),
 -- Winnie the Pooh - Blood and Honey 2
     (nextval('public.film_projection_id_seq'), 6, 3, '2024-02-24', '19:00', 10.00),
     (nextval('public.film_projection_id_seq'), 6, 3, '2024-02-25', '19:00', 10.00),
     (nextval('public.film_projection_id_seq'), 6, 3, '2024-02-26', '19:00', 10.00),
 -- Phenomena
     (nextval('public.film_projection_id_seq'), 7, 3, '2024-02-24', '21:00', 10.00),
     (nextval('public.film_projection_id_seq'), 7, 3, '2024-02-25', '21:00', 10.00),
     (nextval('public.film_projection_id_seq'), 7, 3, '2024-02-26', '21:00', 10.00);
INSERT INTO public.film_projection (projection_id, film_id, hall_id, projection_date, projection_time, ticket_price)
VALUES
  (nextval('public.film_projection_id_seq'), 8, 3, '2024-02-24', '15:00', 10.00),
  (nextval('public.film_projection_id_seq'), 8, 3, '2024-02-25', '15:00', 10.00),
  (nextval('public.film_projection_id_seq'), 8, 3, '2024-02-26', '15:00', 10.00),
  -- Another Film
  (nextval('public.film_projection_id_seq'), 9, 3, '2024-02-24', '19:00', 10.00),
  (nextval('public.film_projection_id_seq'), 9, 3, '2024-02-25', '19:00', 10.00),
  (nextval('public.film_projection_id_seq'), 9, 3, '2024-02-26', '19:00', 10.00),
  -- Yet Another Film
  (nextval('public.film_projection_id_seq'), 10, 3, '2024-02-24', '21:00', 10.00),
  (nextval('public.film_projection_id_seq'), 10, 3, '2024-02-25', '21:00', 10.00),
  (nextval('public.film_projection_id_seq'), 10, 3, '2024-02-26', '21:00', 10.00),
  -- And Another One
  (nextval('public.film_projection_id_seq'), 11, 3, '2024-02-24', '14:00', 10.00),
  (nextval('public.film_projection_id_seq'), 11, 3, '2024-02-25', '17:30', 10.00);


 -- Proiezioni per la sala 4
 -- The Holdovers
 INSERT INTO public.film_projection (projection_id, film_id, hall_id, projection_date, projection_time, ticket_price)
 VALUES
     (nextval('public.film_projection_id_seq'), 3, 4, '2024-02-24', '15:00', 10.00),
     (nextval('public.film_projection_id_seq'), 3, 4, '2024-02-25', '15:00', 10.00),
     (nextval('public.film_projection_id_seq'), 3, 4, '2024-02-26', '15:00', 10.00),
 -- L'Armata delle Tenebre
     (nextval('public.film_projection_id_seq'), 8, 4, '2024-02-24', '19:00', 10.00),
     (nextval('public.film_projection_id_seq'), 8, 4, '2024-02-25', '19:00', 10.00),
     (nextval('public.film_projection_id_seq'), 8, 4, '2024-02-26', '19:00', 10.00),
 -- Wish
     (nextval('public.film_projection_id_seq'), 9, 4, '2024-02-24', '21:00', 10.00),
     (nextval('public.film_projection_id_seq'), 9, 4, '2024-02-25', '21:00', 10.00),
     (nextval('public.film_projection_id_seq'), 9, 4, '2024-02-26', '21:00', 10.00);
     INSERT INTO public.film_projection (projection_id, film_id, hall_id, projection_date, projection_time, ticket_price)
     VALUES
       (nextval('public.film_projection_id_seq'), 13, 4, '2024-02-24', '15:00', 10.00),
       (nextval('public.film_projection_id_seq'), 13, 4, '2024-02-25', '15:00', 10.00),
       (nextval('public.film_projection_id_seq'), 13, 4, '2024-02-26', '15:00', 10.00),
       -- L'Armata delle Tenebre
       (nextval('public.film_projection_id_seq'), 14, 4, '2024-02-24', '19:00', 10.00),
       (nextval('public.film_projection_id_seq'), 14, 4, '2024-02-25', '19:00', 10.00),
       (nextval('public.film_projection_id_seq'), 14, 4, '2024-02-26', '19:00', 10.00),
       -- Wish
       (nextval('public.film_projection_id_seq'), 15, 4, '2024-02-24', '21:00', 10.00),
       (nextval('public.film_projection_id_seq'), 15, 4, '2024-02-25', '21:00', 10.00),
       (nextval('public.film_projection_id_seq'), 15, 4, '2024-02-26', '21:00', 10.00);







INSERT INTO public._user (user_id, firstname, lastname, birthdate, email, password, role)
VALUES
    (1,'Paolo', 'Lauria', '1900-12-10', 'jaita@gmail.com', '$2a$10$qych9P0oaZ9pvWGodxoo2etZru3UilDdLvQ2HDCQrySJpZffG/OWu', 'USER'),
    (2,'Tommaso', 'Mugnai', '1990-12-10', 'jaita1@gmail.com', '$2a$10$qych9P0oaZ9pvWGodxoo2etZru3UilDdLvQ2HDCQrySJpZffG/OWu', 'USER'),
    (3,'Alessandro', 'Esposito', '1990-12-10', 'jaita2@gmail.com', '$2a$10$qych9P0oaZ9pvWGodxoo2etZru3UilDdLvQ2HDCQrySJpZffG/OWu', 'USER'),
    (4,'Daniele', 'Schiavo', '1990-12-10', 'jaita3@gmail.com', '$2a$10$qych9P0oaZ9pvWGodxoo2etZru3UilDdLvQ2HDCQrySJpZffG/OWu', 'USER'),
    (5,'Ciro', 'Matarese', '1990-12-10', 'jaita4@gmail.com', '$2a$10$qych9P0oaZ9pvWGodxoo2etZru3UilDdLvQ2HDCQrySJpZffG/OWu', 'USER'),
    (6,'Francesca', 'Daniele', '1990-12-10', 'jaita5@gmail.com', '$2a$10$qych9P0oaZ9pvWGodxoo2etZru3UilDdLvQ2HDCQrySJpZffG/OWu', 'USER');

 -- Feedback Insertion
 -- Inserting reviews for each movie with user ID 1
 INSERT INTO public.feedback(feedback_id, user_id, film_id, rating, comment)
 VALUES
     (nextval('public.feedback_id_seq'), 2, 1, 8, 'Very interesting, highly recommend!'),  -- Barbie
     (nextval('public.feedback_id_seq'), 2, 2, 7, 'Good plot but disappointing execution.'),  -- Oppenheimer
     (nextval('public.feedback_id_seq'), 2, 3, 7, 'Liked it enough.'),  -- The Holdovers
     (nextval('public.feedback_id_seq'), 2, 4, 5, 'Didn''t meet my expectations.'),  -- Mario Reda
     (nextval('public.feedback_id_seq'), 2, 5, 9, 'Unforgettable!'),  -- THE HARRY POTTER SAGA
     (nextval('public.feedback_id_seq'), 2, 6, 10, 'Absolute masterpiece!'),  -- Winnie the Pooh - Blood and Honey 2
     (nextval('public.feedback_id_seq'), 2, 7, 8, 'Intriguing and well-made.'),  -- Phenomena
     (nextval('public.feedback_id_seq'), 2, 8, 6, 'Fun but a bit scary.'),  -- Army of Darkness
     (nextval('public.feedback_id_seq'), 2, 9, 9, 'Emotional story and extraordinary animation.'),  -- Wish
     (nextval('public.feedback_id_seq'), 2, 10, 7, 'Interesting reinterpretation of Willy Wonka''s world.'),  -- Wonka
     (nextval('public.feedback_id_seq'), 2, 11, 8, 'Romantic and funny.'),  -- Barefoot in the Park
     (nextval('public.feedback_id_seq'), 2, 12, 7, 'Classic Italian with comedic moments.'),  -- Fracchia the Human Beast
     (nextval('public.feedback_id_seq'), 2, 13, 9, 'Out of the ordinary and fun!');  -- Poor Things

 -- Inserting reviews for each movie with user ID 2
 INSERT INTO public.feedback(feedback_id, user_id, film_id, rating, comment)
 VALUES
     (nextval('public.feedback_id_seq'), 3, 1, 9, 'One of the best movies seen recently!'),  -- Barbie
     (nextval('public.feedback_id_seq'), 3, 2, 8, 'Intriguing and well-developed.'),  -- Oppenheimer
     (nextval('public.feedback_id_seq'), 3, 3, 7, 'Not entirely convincing.'),  -- The Holdovers
     (nextval('public.feedback_id_seq'), 3, 4, 7, 'Interesting but with some shortcomings.'),  -- Mario Reda
     (nextval('public.feedback_id_seq'), 3, 5, 10, 'Timeless masterpiece!'),  -- THE HARRY POTTER SAGA
     (nextval('public.feedback_id_seq'), 3, 6, 9, 'A must-watch!'),  -- Winnie the Pooh - Blood and Honey 2
     (nextval('public.feedback_id_seq'), 3, 7, 6, 'Atmosphere a bit too dark.'),  -- Phenomena
     (nextval('public.feedback_id_seq'), 3, 8, 8, 'Fun and slightly scary.'),  -- Army of Darkness
     (nextval('public.feedback_id_seq'), 3, 9, 8, 'Engaging story and high-quality animation.'),  -- Wish
     (nextval('public.feedback_id_seq'), 3, 10, 7, 'Interesting reinterpretation of Willy Wonka''s story.'),  -- Wonka
     (nextval('public.feedback_id_seq'), 3, 11, 7, 'Romantic and funny.'),  -- Barefoot in the Park
     (nextval('public.feedback_id_seq'), 3, 12, 6, 'Classic Italian comedy with hilarious moments.'),  -- Fracchia the Human Beast
     (nextval('public.feedback_id_seq'), 3, 13, 9, 'Offbeat and fun!');  -- Poor Things

 -- Inserting reviews for each movie with user ID 3
 INSERT INTO public.feedback(feedback_id, user_id, film_id, rating, comment)
 VALUES
     (nextval('public.feedback_id_seq'), 4, 1, 6, 'Not what I expected.'),  -- Barbie
     (nextval('public.feedback_id_seq'), 4, 2, 7, 'Engaging plot and well-acted.'),  -- Oppenheimer
     (nextval('public.feedback_id_seq'), 4, 3, 7, 'Good story but something was missing.'),  -- The Holdovers
     (nextval('public.feedback_id_seq'), 4, 4, 5, 'Didn''t live up to my expectations.'),  -- Mario Reda
     (nextval('public.feedback_id_seq'), 4, 5, 9, 'Timeless classic!'),  -- THE HARRY POTTER SAGA
     (nextval('public.feedback_id_seq'), 4, 6, 9, 'Fascinating and well-executed story.'),  -- Winnie the Pooh - Blood and Honey 2
     (nextval('public.feedback_id_seq'), 4, 7, 6, 'Atmosphere a bit dark but engaging.'),  -- Phenomena
     (nextval('public.feedback_id_seq'), 4, 8, 7, 'Fun and slightly scary.'),  -- Army of Darkness
     (nextval('public.feedback_id_seq'), 4, 9, 9, 'Exciting and well-animated.'),  -- Wish
     (nextval('public.feedback_id_seq'), 4, 10, 8, 'Interesting interpretation of Willy Wonka''s world.'),  -- Wonka
     (nextval('public.feedback_id_seq'), 4, 11, 8, 'Romantic and funny.'),  -- Barefoot in the Park
     (nextval('public.feedback_id_seq'), 4, 12, 7, 'Classic Italian comedy with hilarious moments.'),  -- Fracchia the Human Beast
     (nextval('public.feedback_id_seq'), 4, 13, 8, 'Unique and fun!');  -- Poor Things

 -- Inserting reviews for each movie with user ID 4
 INSERT INTO public.feedback(feedback_id, user_id, film_id, rating, comment)
 VALUES
     (nextval('public.feedback_id_seq'), 5, 1, 9, 'Very entertaining and suitable for all ages!'),  -- Barbie
     (nextval('public.feedback_id_seq'), 5, 2, 7, 'Good movie but perhaps a bit overrated.'),  -- Oppenheimer
     (nextval('public.feedback_id_seq'), 5, 3, 7, 'Nothing exceptional.'),  -- The Holdovers
     (nextval('public.feedback_id_seq'), 5, 4, 8, 'Intriguing and original.'),  -- Mario Reda
     (nextval('public.feedback_id_seq'), 5, 5, 10, 'Unforgettable!'),  -- THE HARRY POTTER SAGA
     (nextval('public.feedback_id_seq'), 5, 6, 9, 'Fascinating story and well-animated.'),  -- Winnie the Pooh - Blood and Honey 2
     (nextval('public.feedback_id_seq'), 5, 7, 8, 'Eerie atmosphere but engaging.'),  -- Phenomena
     (nextval('public.feedback_id_seq'), 5, 8, 7, 'Fun and slightly scary.'),  -- Army of Darkness
     (nextval('public.feedback_id_seq'), 5, 9, 9, 'Beautiful story and high-quality animation.'),  -- Wish
     (nextval('public.feedback_id_seq'), 5, 10, 8, 'A unique and creative take on Willy Wonka''s world.'),  -- Wonka
     (nextval('public.feedback_id_seq'), 5, 11, 8, 'Funny and romantic.'),  -- Barefoot in the Park
     (nextval('public.feedback_id_seq'), 5, 12, 7, 'Classic Italian comedy with funny moments.'),  -- Fracchia the Human Beast
     (nextval('public.feedback_id_seq'), 5, 13, 9, 'Quirky and fun!');  -- Poor Things

 -- Inserting reviews for each movie with user ID 5
 INSERT INTO public.feedback(feedback_id, user_id, film_id, rating, comment)
 VALUES
     (nextval('public.feedback_id_seq'), 6, 1, 7, 'Cute but perhaps more suitable for children.'),  -- Barbie
     (nextval('public.feedback_id_seq'), 6, 2, 7, 'Intriguing and well-acted.'),  -- Oppenheimer
     (nextval('public.feedback_id_seq'), 6, 3, 7, 'Not entirely satisfying.'),  -- The Holdovers
     (nextval('public.feedback_id_seq'), 6, 4, 6, 'Interesting but with some flaws.'),  -- Mario Reda
     (nextval('public.feedback_id_seq'), 6, 5, 10, 'A timeless classic!'),  -- THE HARRY POTTER SAGA
     (nextval('public.feedback_id_seq'), 6, 6, 8, 'Engaging and well-executed story.'),  -- Winnie the Pooh - Blood and Honey 2
     (nextval('public.feedback_id_seq'), 6, 7, 7, 'Eerie yet engaging atmosphere.'),  -- Phenomena
     (nextval('public.feedback_id_seq'), 6, 8, 8, 'Fun and scary.'),  -- Army of Darkness
     (nextval('public.feedback_id_seq'), 6, 9, 9, 'Beautiful story and high-quality animation.'),  -- Wish
     (nextval('public.feedback_id_seq'), 6, 10, 8, 'A unique and creative take on Willy Wonka''s world.'),  -- Wonka
     (nextval('public.feedback_id_seq'), 6, 11, 8, 'Funny and romantic.'),  -- Barefoot in the Park
     (nextval('public.feedback_id_seq'), 6, 12, 6, 'Classic Italian comedy.'),  -- Fracchia the Human Beast
     (nextval('public.feedback_id_seq'), 6, 13, 9, 'Out of the ordinary and fun!');  -- Poor Things



