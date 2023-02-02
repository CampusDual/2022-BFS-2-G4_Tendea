--
-- PostgreSQL database dump
--

-- Dumped from database version 11.16 (Debian 11.16-0+deb10u1)
-- Dumped by pg_dump version 14.2

-- Started on 2023-02-02 13:18:24

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

DROP DATABASE "Fullstack_2022_2_G4";
--
-- TOC entry 3085 (class 1262 OID 202900)
-- Name: Fullstack_2022_2_G4; Type: DATABASE; Schema: -; Owner: -
--

CREATE DATABASE "Fullstack_2022_2_G4" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'en_US.UTF-8';


\connect "Fullstack_2022_2_G4"

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

--
-- TOC entry 3 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA public;


--
-- TOC entry 3086 (class 0 OID 0)
-- Dependencies: 3
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

--
-- TOC entry 204 (class 1259 OID 203282)
-- Name: users; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.users (
    id integer NOT NULL,
    login character varying(255),
    name character varying(255),
    password character varying(255),
    surname1 character varying(255),
    surname2 character varying(255),
    email character varying(255),
    nif character varying(255),
    active_status integer,
    created_at timestamp without time zone
);


--
-- TOC entry 203 (class 1259 OID 203280)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3087 (class 0 OID 0)
-- Dependencies: 203
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- TOC entry 206 (class 1259 OID 203954)
-- Name: addresses; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.addresses (
    id integer DEFAULT nextval('public.users_id_seq'::regclass) NOT NULL,
    cip character varying(255) NOT NULL,
    door character varying(255),
    floor character varying(255),
    number character varying(255),
    province character varying(255),
    street character varying(255),
    town character varying(255),
    favorite integer
);


--
-- TOC entry 219 (class 1259 OID 204980)
-- Name: cart_items; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.cart_items (
    id integer NOT NULL,
    quantity integer,
    product_id integer,
    catery_id integer
);


--
-- TOC entry 218 (class 1259 OID 204978)
-- Name: cart_items_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.cart_items_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3088 (class 0 OID 0)
-- Dependencies: 218
-- Name: cart_items_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.cart_items_id_seq OWNED BY public.cart_items.id;


--
-- TOC entry 221 (class 1259 OID 204993)
-- Name: carts; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.carts (
    id integer NOT NULL,
    comment character varying(255),
    create_at date,
    updated_at date,
    user_id integer,
    status integer
);


--
-- TOC entry 220 (class 1259 OID 204991)
-- Name: carts_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.carts_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3089 (class 0 OID 0)
-- Dependencies: 220
-- Name: carts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.carts_id_seq OWNED BY public.carts.id;


--
-- TOC entry 222 (class 1259 OID 205013)
-- Name: carts_items; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.carts_items (
    cart_id integer NOT NULL,
    items_id integer NOT NULL
);


--
-- TOC entry 210 (class 1259 OID 204667)
-- Name: categories; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.categories (
    id integer NOT NULL,
    name character varying(255) NOT NULL
);


--
-- TOC entry 209 (class 1259 OID 204665)
-- Name: categories_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.categories_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3090 (class 0 OID 0)
-- Dependencies: 209
-- Name: categories_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.categories_id_seq OWNED BY public.categories.id;


--
-- TOC entry 197 (class 1259 OID 203246)
-- Name: contacts; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.contacts (
    id integer NOT NULL,
    email character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    phone integer NOT NULL,
    surname1 character varying(255) NOT NULL,
    surname2 character varying(255) NOT NULL
);


--
-- TOC entry 196 (class 1259 OID 203244)
-- Name: contacts_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.contacts_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3091 (class 0 OID 0)
-- Dependencies: 196
-- Name: contacts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.contacts_id_seq OWNED BY public.contacts.id;


--
-- TOC entry 215 (class 1259 OID 204847)
-- Name: product_images; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.product_images (
    id integer NOT NULL,
    name character varying(255),
    url character varying(255),
    product_id integer
);


--
-- TOC entry 214 (class 1259 OID 204845)
-- Name: product_images_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.product_images_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3092 (class 0 OID 0)
-- Dependencies: 214
-- Name: product_images_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.product_images_id_seq OWNED BY public.product_images.id;


--
-- TOC entry 208 (class 1259 OID 204478)
-- Name: products; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.products (
    id integer NOT NULL,
    discount real,
    product_name character varying(255) NOT NULL,
    price real NOT NULL,
    create_at date,
    description character varying(255),
    updated_at date,
    bulk integer,
    category_id integer,
    shop_id integer
);


--
-- TOC entry 207 (class 1259 OID 204476)
-- Name: products_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.products_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3093 (class 0 OID 0)
-- Dependencies: 207
-- Name: products_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.products_id_seq OWNED BY public.products.id;


--
-- TOC entry 199 (class 1259 OID 203257)
-- Name: profiles; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.profiles (
    id integer NOT NULL,
    description character varying(255),
    name character varying(255)
);


--
-- TOC entry 198 (class 1259 OID 203255)
-- Name: profiles_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.profiles_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3094 (class 0 OID 0)
-- Dependencies: 198
-- Name: profiles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.profiles_id_seq OWNED BY public.profiles.id;


--
-- TOC entry 200 (class 1259 OID 203266)
-- Name: profiles_sections_map; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.profiles_sections_map (
    profile_id integer NOT NULL,
    section_id integer NOT NULL
);


--
-- TOC entry 202 (class 1259 OID 203271)
-- Name: sections; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.sections (
    id integer NOT NULL,
    alias character varying(255),
    description character varying(255),
    name character varying(255)
);


--
-- TOC entry 201 (class 1259 OID 203269)
-- Name: sections_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.sections_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3095 (class 0 OID 0)
-- Dependencies: 201
-- Name: sections_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.sections_id_seq OWNED BY public.sections.id;


--
-- TOC entry 217 (class 1259 OID 204928)
-- Name: shop_images; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.shop_images (
    id integer NOT NULL,
    name character varying(255),
    url character varying(255),
    shop_id integer
);


--
-- TOC entry 216 (class 1259 OID 204926)
-- Name: shop_images_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.shop_images_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3096 (class 0 OID 0)
-- Dependencies: 216
-- Name: shop_images_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.shop_images_id_seq OWNED BY public.shop_images.id;


--
-- TOC entry 212 (class 1259 OID 204773)
-- Name: shops; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.shops (
    id integer NOT NULL,
    active_status integer,
    address character varying(255),
    city character varying(255) NOT NULL,
    description character varying(255),
    email character varying(255) NOT NULL,
    name character varying(24) NOT NULL,
    phone character varying(255) NOT NULL,
    user_id integer,
    url_fb character varying(255),
    url_insta character varying(255)
);


--
-- TOC entry 213 (class 1259 OID 204782)
-- Name: shops_categories_map; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.shops_categories_map (
    shop_id integer NOT NULL,
    category_id integer NOT NULL
);


--
-- TOC entry 211 (class 1259 OID 204771)
-- Name: shops_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.shops_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3097 (class 0 OID 0)
-- Dependencies: 211
-- Name: shops_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.shops_id_seq OWNED BY public.shops.id;


--
-- TOC entry 205 (class 1259 OID 203291)
-- Name: users_profiles_map; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.users_profiles_map (
    user_id integer NOT NULL,
    profile_id integer NOT NULL
);


--
-- TOC entry 2874 (class 2604 OID 204983)
-- Name: cart_items id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.cart_items ALTER COLUMN id SET DEFAULT nextval('public.cart_items_id_seq'::regclass);


--
-- TOC entry 2875 (class 2604 OID 204996)
-- Name: carts id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.carts ALTER COLUMN id SET DEFAULT nextval('public.carts_id_seq'::regclass);


--
-- TOC entry 2870 (class 2604 OID 204670)
-- Name: categories id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.categories ALTER COLUMN id SET DEFAULT nextval('public.categories_id_seq'::regclass);


--
-- TOC entry 2864 (class 2604 OID 203249)
-- Name: contacts id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.contacts ALTER COLUMN id SET DEFAULT nextval('public.contacts_id_seq'::regclass);


--
-- TOC entry 2872 (class 2604 OID 204850)
-- Name: product_images id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product_images ALTER COLUMN id SET DEFAULT nextval('public.product_images_id_seq'::regclass);


--
-- TOC entry 2869 (class 2604 OID 204481)
-- Name: products id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.products ALTER COLUMN id SET DEFAULT nextval('public.products_id_seq'::regclass);


--
-- TOC entry 2865 (class 2604 OID 203260)
-- Name: profiles id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.profiles ALTER COLUMN id SET DEFAULT nextval('public.profiles_id_seq'::regclass);


--
-- TOC entry 2866 (class 2604 OID 203274)
-- Name: sections id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sections ALTER COLUMN id SET DEFAULT nextval('public.sections_id_seq'::regclass);


--
-- TOC entry 2873 (class 2604 OID 204931)
-- Name: shop_images id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.shop_images ALTER COLUMN id SET DEFAULT nextval('public.shop_images_id_seq'::regclass);


--
-- TOC entry 2871 (class 2604 OID 204776)
-- Name: shops id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.shops ALTER COLUMN id SET DEFAULT nextval('public.shops_id_seq'::regclass);


--
-- TOC entry 2867 (class 2604 OID 203285)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- TOC entry 3063 (class 0 OID 203954)
-- Dependencies: 206
-- Data for Name: addresses; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.addresses VALUES (23, '15895', 'Derecha', '2', '7', 'A Coruña', 'Camiño', 'O Pino', NULL);


--
-- TOC entry 3076 (class 0 OID 204980)
-- Dependencies: 219
-- Data for Name: cart_items; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.cart_items VALUES (25, 1, 106, NULL);
INSERT INTO public.cart_items VALUES (26, 1, 318, NULL);
INSERT INTO public.cart_items VALUES (27, 1, 308, NULL);
INSERT INTO public.cart_items VALUES (28, 1, 98, NULL);
INSERT INTO public.cart_items VALUES (29, 1, 106, NULL);
INSERT INTO public.cart_items VALUES (30, 1, 318, NULL);
INSERT INTO public.cart_items VALUES (31, 1, 308, NULL);
INSERT INTO public.cart_items VALUES (32, 1, 98, NULL);
INSERT INTO public.cart_items VALUES (33, 1, 106, NULL);
INSERT INTO public.cart_items VALUES (34, 1, 318, NULL);
INSERT INTO public.cart_items VALUES (35, 1, 308, NULL);
INSERT INTO public.cart_items VALUES (36, 1, 98, NULL);


--
-- TOC entry 3078 (class 0 OID 204993)
-- Dependencies: 221
-- Data for Name: carts; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.carts VALUES (7, 'Este es un comentario del carrito', '2022-11-21', NULL, 193, 1);
INSERT INTO public.carts VALUES (8, 'Este es un comentario del carrito', '2022-11-21', NULL, 193, 1);
INSERT INTO public.carts VALUES (9, 'Este es un comentario del carrito', '2022-11-21', NULL, 193, 1);


--
-- TOC entry 3079 (class 0 OID 205013)
-- Dependencies: 222
-- Data for Name: carts_items; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.carts_items VALUES (8, 29);
INSERT INTO public.carts_items VALUES (8, 30);
INSERT INTO public.carts_items VALUES (8, 31);
INSERT INTO public.carts_items VALUES (8, 32);
INSERT INTO public.carts_items VALUES (9, 33);
INSERT INTO public.carts_items VALUES (9, 34);
INSERT INTO public.carts_items VALUES (9, 35);
INSERT INTO public.carts_items VALUES (9, 36);


--
-- TOC entry 3067 (class 0 OID 204667)
-- Dependencies: 210
-- Data for Name: categories; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.categories VALUES (1, 'Electronica');
INSERT INTO public.categories VALUES (2, 'Fruteria');
INSERT INTO public.categories VALUES (3, 'Panaderia');
INSERT INTO public.categories VALUES (4, 'Carniceria');
INSERT INTO public.categories VALUES (6, 'Lacteos');
INSERT INTO public.categories VALUES (7, 'Conservas');
INSERT INTO public.categories VALUES (8, 'Aperitivos');
INSERT INTO public.categories VALUES (9, 'Bebidas');
INSERT INTO public.categories VALUES (10, 'Huevos');
INSERT INTO public.categories VALUES (11, 'Congelados');
INSERT INTO public.categories VALUES (12, 'Limpieza');
INSERT INTO public.categories VALUES (13, 'Menaje');
INSERT INTO public.categories VALUES (14, 'Higiene personal');
INSERT INTO public.categories VALUES (15, 'Cuidado de la ropa');
INSERT INTO public.categories VALUES (17, 'Ropa');
INSERT INTO public.categories VALUES (18, 'Deportes');
INSERT INTO public.categories VALUES (19, 'Libros');
INSERT INTO public.categories VALUES (20, 'Papeleria');
INSERT INTO public.categories VALUES (21, 'Informatica');
INSERT INTO public.categories VALUES (22, 'Parafarmacia');
INSERT INTO public.categories VALUES (23, 'Charcuteria');
INSERT INTO public.categories VALUES (24, 'Precocinados');
INSERT INTO public.categories VALUES (25, 'Hogar');
INSERT INTO public.categories VALUES (26, 'Ferreteria');
INSERT INTO public.categories VALUES (27, 'Alimentacion');
INSERT INTO public.categories VALUES (28, 'Mascotas');


--
-- TOC entry 3054 (class 0 OID 203246)
-- Dependencies: 197
-- Data for Name: contacts; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.contacts VALUES (6, '6@6.es', 'a', 666666666, 'b', 'c');
INSERT INTO public.contacts VALUES (7, 'pep@e.com', 'pepe', 666555222, 'peep', 'pepe');


--
-- TOC entry 3072 (class 0 OID 204847)
-- Dependencies: 215
-- Data for Name: product_images; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.product_images VALUES (196, 'd6923c8c-2927-48c2-9ca4-4ed4f059321a_solomillo.jpg', 'd6923c8c-2927-48c2-9ca4-4ed4f059321a_solomillo.jpg', 312);
INSERT INTO public.product_images VALUES (185, '19ca8b89-70aa-4fbc-b02d-8920c8beeac7_pipas.jpg', '19ca8b89-70aa-4fbc-b02d-8920c8beeac7_pipas.jpg', 303);
INSERT INTO public.product_images VALUES (4, '275066b6-1e8f-4845-b938-b1c780268f2e_840_560.jpg', '275066b6-1e8f-4845-b938-b1c780268f2e_840_560.jpg', 89);
INSERT INTO public.product_images VALUES (188, '8d3ecf53-4cde-4060-b15b-8d034c1f82ff_pan-de-jalá.jpg', '8d3ecf53-4cde-4060-b15b-8d034c1f82ff_pan-de-jalá.jpg', 304);
INSERT INTO public.product_images VALUES (199, 'f619e146-d33f-485e-bd41-a44845586ee6_balon-futbol-adidas-epp-2-888-d_nq_np_872445-mlc27439325608_052018-f.jpg', 'f619e146-d33f-485e-bd41-a44845586ee6_balon-futbol-adidas-epp-2-888-d_nq_np_872445-mlc27439325608_052018-f.jpg', 315);
INSERT INTO public.product_images VALUES (156, '8c3d488d-01b7-4e99-a089-f357c3d66919_lasagna.jpg', '8c3d488d-01b7-4e99-a089-f357c3d66919_lasagna.jpg', 277);
INSERT INTO public.product_images VALUES (9, '399a3d2d-2b54-4292-85c1-08f54a0a3a05_Auriculares.jpg', '399a3d2d-2b54-4292-85c1-08f54a0a3a05_Auriculares.jpg', 95);
INSERT INTO public.product_images VALUES (10, '3eada824-786e-43bd-a680-36679fd56fba_Pistachos.jpg', '3eada824-786e-43bd-a680-36679fd56fba_Pistachos.jpg', 96);
INSERT INTO public.product_images VALUES (11, 'c7e745a4-f87f-4210-89d1-6b05b1c06650_Huevos.jpg', 'c7e745a4-f87f-4210-89d1-6b05b1c06650_Huevos.jpg', 97);
INSERT INTO public.product_images VALUES (201, 'ae5262ad-6b33-4501-a085-848cc3ac08e6_gouda.png', 'ae5262ad-6b33-4501-a085-848cc3ac08e6_gouda.png', 317);
INSERT INTO public.product_images VALUES (13, '612491b0-3f00-4ed7-b598-abff0f1add40_CunaQueso.jpg', '612491b0-3f00-4ed7-b598-abff0f1add40_CunaQueso.jpg', 99);
INSERT INTO public.product_images VALUES (41, '8db1da10-92bb-4329-a439-45b873aa301c_Ensaladilla.jfif', '8db1da10-92bb-4329-a439-45b873aa301c_Ensaladilla.jfif', 121);
INSERT INTO public.product_images VALUES (15, '02d6fbe2-cc66-4d23-af95-9fc0a7660262_NikeJordan.jpg', '02d6fbe2-cc66-4d23-af95-9fc0a7660262_NikeJordan.jpg', 101);
INSERT INTO public.product_images VALUES (16, '6f7742d6-98dd-4465-a06e-85b75fb65fc8_Serrano.jpg', '6f7742d6-98dd-4465-a06e-85b75fb65fc8_Serrano.jpg', 102);
INSERT INTO public.product_images VALUES (17, '00217ef1-986c-468c-b4df-cb8d6d2bbc86_granadamollar.jpg', '00217ef1-986c-468c-b4df-cb8d6d2bbc86_granadamollar.jpg', 103);
INSERT INTO public.product_images VALUES (207, '389ce804-2d87-4ac2-b91b-f20560f1266a_imac.jpg', '389ce804-2d87-4ac2-b91b-f20560f1266a_imac.jpg', 323);
INSERT INTO public.product_images VALUES (27, 'ad20dc4c-124e-49e9-b895-b2c933a52ee8_NB.jfif', 'ad20dc4c-124e-49e9-b895-b2c933a52ee8_NB.jfif', 100);
INSERT INTO public.product_images VALUES (19, '2461092f-fcc6-4cd4-bf59-e981fd6bb907_coca-cola-zero-330-ml-24-ud.jpg', '2461092f-fcc6-4cd4-bf59-e981fd6bb907_coca-cola-zero-330-ml-24-ud.jpg', 105);
INSERT INTO public.product_images VALUES (20, 'd207e654-c75b-444a-8432-e1aabe2f1475_VinoTinto.jfif', 'd207e654-c75b-444a-8432-e1aabe2f1475_VinoTinto.jfif', 98);
INSERT INTO public.product_images VALUES (21, '7d5a7e01-691d-4bd9-9e23-696539ce1f44_AguaGas.jpg', '7d5a7e01-691d-4bd9-9e23-696539ce1f44_AguaGas.jpg', 106);
INSERT INTO public.product_images VALUES (22, 'bb22eb10-ff8f-4a55-832a-b6f21c20eaa8_Tortilla.jpg', 'bb22eb10-ff8f-4a55-832a-b6f21c20eaa8_Tortilla.jpg', 107);
INSERT INTO public.product_images VALUES (215, '4c4d5ca3-3869-43a9-9872-1b8d527c8e90_fuet.jpg', '4c4d5ca3-3869-43a9-9872-1b8d527c8e90_fuet.jpg', 331);
INSERT INTO public.product_images VALUES (25, '875d15bb-a69c-4448-b0ee-72c6fb238454_SambaBlancas.jfif', '875d15bb-a69c-4448-b0ee-72c6fb238454_SambaBlancas.jfif', 110);
INSERT INTO public.product_images VALUES (42, '5cb667ac-4c31-49fd-88f8-73201160724f_LomoCerdo.jfif', '5cb667ac-4c31-49fd-88f8-73201160724f_LomoCerdo.jfif', 122);
INSERT INTO public.product_images VALUES (134, '8fede677-cd7f-4140-8c6d-61ef61465e57_UvasBicolor.png', '8fede677-cd7f-4140-8c6d-61ef61465e57_UvasBicolor.png', 259);
INSERT INTO public.product_images VALUES (43, '8f8c054e-b290-4201-b17a-ceb38b3ad639_Pepinillos.jfif', '8f8c054e-b290-4201-b17a-ceb38b3ad639_Pepinillos.jfif', 123);
INSERT INTO public.product_images VALUES (32, '63c37aa0-ec42-47d6-a0ad-3e532d503b93_SUDADERA.jfif', '63c37aa0-ec42-47d6-a0ad-3e532d503b93_SUDADERA.jfif', 111);
INSERT INTO public.product_images VALUES (33, 'd64aaddf-d6c3-4dd1-a41d-985cf7d4e2d4_Radiador.jfif', 'd64aaddf-d6c3-4dd1-a41d-985cf7d4e2d4_Radiador.jfif', 112);
INSERT INTO public.product_images VALUES (36, '37654365-1589-4d49-b966-b5ec48ca3517_Boniato.jfif', '37654365-1589-4d49-b966-b5ec48ca3517_Boniato.jfif', 115);
INSERT INTO public.product_images VALUES (37, 'f1fe51a6-8593-485f-ad83-0a62ed1759e6_Kiwi.jfif', 'f1fe51a6-8593-485f-ad83-0a62ed1759e6_Kiwi.jfif', 118);
INSERT INTO public.product_images VALUES (40, '079712a1-eed0-4d22-832b-750865365574_PastaDentalOralB.jfif', '079712a1-eed0-4d22-832b-750865365574_PastaDentalOralB.jfif', 120);
INSERT INTO public.product_images VALUES (51, 'bcdd7f00-24d9-4e4a-86c2-3b794545225f_Pintalabios.jfif', 'bcdd7f00-24d9-4e4a-86c2-3b794545225f_Pintalabios.jfif', 130);
INSERT INTO public.product_images VALUES (45, '7cc32976-7474-4456-af64-9a1d8d76fb9c_Fairy.jfif', '7cc32976-7474-4456-af64-9a1d8d76fb9c_Fairy.jfif', 124);
INSERT INTO public.product_images VALUES (46, 'b0364490-8e0b-4cbf-8a2b-c79283e39bc4_Sarten.jfif', 'b0364490-8e0b-4cbf-8a2b-c79283e39bc4_Sarten.jfif', 125);
INSERT INTO public.product_images VALUES (48, '7d3a4c4c-7ae2-457f-9e06-19f1ea64be34_Libro.jfif', '7d3a4c4c-7ae2-457f-9e06-19f1ea64be34_Libro.jfif', 127);
INSERT INTO public.product_images VALUES (49, 'b5fdb243-cf7d-4193-a88a-c90eb91656d3_Libreta.jfif', 'b5fdb243-cf7d-4193-a88a-c90eb91656d3_Libreta.jfif', 128);
INSERT INTO public.product_images VALUES (50, 'dd387e45-b981-4830-8bdb-39595e82f489_laptop.jfif', 'dd387e45-b981-4830-8bdb-39595e82f489_laptop.jfif', 129);
INSERT INTO public.product_images VALUES (52, '4a43cad1-e16f-4b6b-88d4-e5807d7ddd5a_Lampara.jfif', '4a43cad1-e16f-4b6b-88d4-e5807d7ddd5a_Lampara.jfif', 131);
INSERT INTO public.product_images VALUES (54, '640520bf-197e-452a-87e9-03a932291190_Neutrogena.jfif', '640520bf-197e-452a-87e9-03a932291190_Neutrogena.jfif', 133);
INSERT INTO public.product_images VALUES (56, '25d92b06-d25c-4856-a374-49a191c28625_FiletePollo.jfif', '25d92b06-d25c-4856-a374-49a191c28625_FiletePollo.jfif', 135);
INSERT INTO public.product_images VALUES (157, '0b41150b-6a2a-47e9-9ed0-0d50ac46f090_pizza.jpg', '0b41150b-6a2a-47e9-9ed0-0d50ac46f090_pizza.jpg', 278);
INSERT INTO public.product_images VALUES (58, 'aae1cc4f-77d9-479b-9fcb-7c56ba2a4e36_LecheEvaporada.jfif', 'aae1cc4f-77d9-479b-9fcb-7c56ba2a4e36_LecheEvaporada.jfif', 136);
INSERT INTO public.product_images VALUES (59, '8a8a1c68-026e-48de-b377-05fc4f3bcc8a_GelDucha.jfif', '8a8a1c68-026e-48de-b377-05fc4f3bcc8a_GelDucha.jfif', 137);
INSERT INTO public.product_images VALUES (144, '445ff6b4-4ae4-4339-9154-2e3e4e65095f_curitas.jpg', '445ff6b4-4ae4-4339-9154-2e3e4e65095f_curitas.jpg', 264);
INSERT INTO public.product_images VALUES (146, '981db3f8-dd69-4553-ba07-4f19d2db6894_curitas.jpg', '981db3f8-dd69-4553-ba07-4f19d2db6894_curitas.jpg', 268);
INSERT INTO public.product_images VALUES (147, 'f6e67d6e-d235-418f-8d81-28d6826dad33_tv.jpg', 'f6e67d6e-d235-418f-8d81-28d6826dad33_tv.jpg', 269);
INSERT INTO public.product_images VALUES (148, '8a5c6b00-17ce-48ca-bb7e-a490f500a36f_escayola.jpg', '8a5c6b00-17ce-48ca-bb7e-a490f500a36f_escayola.jpg', 270);
INSERT INTO public.product_images VALUES (149, '45f19a99-b12b-42d1-8638-462df3be1fed_papel.jpg', '45f19a99-b12b-42d1-8638-462df3be1fed_papel.jpg', 271);
INSERT INTO public.product_images VALUES (150, '46629cd3-1b65-4250-802d-976bcfffb47b_tablet.jpeg', '46629cd3-1b65-4250-802d-976bcfffb47b_tablet.jpeg', 272);
INSERT INTO public.product_images VALUES (158, '29c81ac1-8cbf-41f5-a58a-4ff74ceff2bc_sandwich.jpg', '29c81ac1-8cbf-41f5-a58a-4ff74ceff2bc_sandwich.jpg', 279);
INSERT INTO public.product_images VALUES (159, 'f327385c-8fa1-43b3-9ed5-112a68da7973_ensaladilla.jpg', 'f327385c-8fa1-43b3-9ed5-112a68da7973_ensaladilla.jpg', 282);
INSERT INTO public.product_images VALUES (160, 'cd491d49-c571-48b5-9060-53aa7dece23d_crema.jpg', 'cd491d49-c571-48b5-9060-53aa7dece23d_crema.jpg', 283);
INSERT INTO public.product_images VALUES (230, '0a3fb01d-99f2-4c87-b7bf-c2332802b4d7_roscon.jpg', '0a3fb01d-99f2-4c87-b7bf-c2332802b4d7_roscon.jpg', 339);
INSERT INTO public.product_images VALUES (231, 'ae521e86-0980-4ba0-992d-f32e9882108e_magdalenas.jpg', 'ae521e86-0980-4ba0-992d-f32e9882108e_magdalenas.jpg', 340);
INSERT INTO public.product_images VALUES (193, '96610c8e-a9c2-4250-81f8-e026dcf69dc8_barradepan-600x600.jpg', '96610c8e-a9c2-4250-81f8-e026dcf69dc8_barradepan-600x600.jpg', 309);
INSERT INTO public.product_images VALUES (194, '030d10c2-9ec4-43f5-bc58-a65fea457cb4_empanada.jpg', '030d10c2-9ec4-43f5-bc58-a65fea457cb4_empanada.jpg', 310);
INSERT INTO public.product_images VALUES (232, 'c550d5c9-6988-475d-b8ec-85b902a67ec6_croissant-round.jpg', 'c550d5c9-6988-475d-b8ec-85b902a67ec6_croissant-round.jpg', 341);
INSERT INTO public.product_images VALUES (197, '7ee8e90d-a589-4114-8334-fcf4df40c9cf_carnepicada.png', '7ee8e90d-a589-4114-8334-fcf4df40c9cf_carnepicada.png', 313);
INSERT INTO public.product_images VALUES (200, '3a4065dd-0c36-4ad7-9468-8d3f209548f6_lecheentera.jpg', '3a4065dd-0c36-4ad7-9468-8d3f209548f6_lecheentera.jpg', 316);
INSERT INTO public.product_images VALUES (203, '85a26935-2b59-43e8-a92a-7d8d792ccf30_biblialatinoamericana3.jpg', '85a26935-2b59-43e8-a92a-7d8d792ccf30_biblialatinoamericana3.jpg', 319);
INSERT INTO public.product_images VALUES (204, 'a606cdcb-8bcd-43af-89ae-ce9f22d56f9e_torah.jpg', 'a606cdcb-8bcd-43af-89ae-ce9f22d56f9e_torah.jpg', 320);
INSERT INTO public.product_images VALUES (206, '0188f18a-1333-4609-b450-19bcd673c18a_juego.jpg', '0188f18a-1333-4609-b450-19bcd673c18a_juego.jpg', 322);
INSERT INTO public.product_images VALUES (208, 'a4ff61d8-e31e-4bef-a294-acdc3f428773_pc.jpg', 'a4ff61d8-e31e-4bef-a294-acdc3f428773_pc.jpg', 324);
INSERT INTO public.product_images VALUES (209, 'c87e9649-355f-406e-b25e-dcd35c63342c_monitor.jpg', 'c87e9649-355f-406e-b25e-dcd35c63342c_monitor.jpg', 325);
INSERT INTO public.product_images VALUES (210, '7e59b969-4cc6-4cd8-8fda-1c0b442c437f_router.jpg', '7e59b969-4cc6-4cd8-8fda-1c0b442c437f_router.jpg', 326);
INSERT INTO public.product_images VALUES (213, '1b4edadf-c66c-4a4b-8da6-e2ac42dc537d_chorizo.jpg', '1b4edadf-c66c-4a4b-8da6-e2ac42dc537d_chorizo.jpg', 329);
INSERT INTO public.product_images VALUES (216, 'cbced28a-513d-44d1-80bc-6acb37aec444_limpieza.jpg', 'cbced28a-513d-44d1-80bc-6acb37aec444_limpieza.jpg', 332);
INSERT INTO public.product_images VALUES (217, 'f4aef013-e6bb-43d8-8a83-b51639a855b8_limpieza2.jpg', 'f4aef013-e6bb-43d8-8a83-b51639a855b8_limpieza2.jpg', 333);
INSERT INTO public.product_images VALUES (244, '4b5f6b10-34ac-4638-b38f-37e2b0002478_uvasbicolor.png', '4b5f6b10-34ac-4638-b38f-37e2b0002478_uvasbicolor.png', 353);
INSERT INTO public.product_images VALUES (161, 'a8210e70-917f-4c08-9146-1146eec1d8f0_gambas.jpg', 'a8210e70-917f-4c08-9146-1146eec1d8f0_gambas.jpg', 284);
INSERT INTO public.product_images VALUES (162, '5926b22a-18e3-4943-b4b2-5ea616eb4c27_salmon.jpg', '5926b22a-18e3-4943-b4b2-5ea616eb4c27_salmon.jpg', 285);
INSERT INTO public.product_images VALUES (163, 'd68494ce-f55a-45d2-b3f7-847913550c10_nuggets.jpg', 'd68494ce-f55a-45d2-b3f7-847913550c10_nuggets.jpg', 286);
INSERT INTO public.product_images VALUES (164, 'a6300389-b9be-4415-8b6d-db08ce29938d_reloj.jpg', 'a6300389-b9be-4415-8b6d-db08ce29938d_reloj.jpg', 287);
INSERT INTO public.product_images VALUES (165, '6afcc6e5-202e-4362-aa99-2e829d5ffbd3_seda.jpg', '6afcc6e5-202e-4362-aa99-2e829d5ffbd3_seda.jpg', 289);
INSERT INTO public.product_images VALUES (166, 'ae716bd8-ea6d-4ec2-9b61-94f24641343e_seda.jpg', 'ae716bd8-ea6d-4ec2-9b61-94f24641343e_seda.jpg', 290);
INSERT INTO public.product_images VALUES (182, '01aa32d8-abe8-464f-996d-8ee459f58030_cacahuetes.jpg', '01aa32d8-abe8-464f-996d-8ee459f58030_cacahuetes.jpg', 300);
INSERT INTO public.product_images VALUES (183, '5a62ded0-9d52-43a3-8156-3ee5d64f9c52_patatillas.jpg', '5a62ded0-9d52-43a3-8156-3ee5d64f9c52_patatillas.jpg', 301);
INSERT INTO public.product_images VALUES (184, 'a744852d-4698-4058-a881-5d666fc16cc5_maiz.jpg', 'a744852d-4698-4058-a881-5d666fc16cc5_maiz.jpg', 302);
INSERT INTO public.product_images VALUES (239, '8d1bb1ac-4389-4d9d-8837-151d88d11766_microsoftteams-image(13).png', '8d1bb1ac-4389-4d9d-8837-151d88d11766_microsoftteams-image(13).png', 348);
INSERT INTO public.product_images VALUES (171, '661d6ac9-b755-4ab0-b1b9-4bd9f5b8ed53_cepillo.jpg', '661d6ac9-b755-4ab0-b1b9-4bd9f5b8ed53_cepillo.jpg', 294);
INSERT INTO public.product_images VALUES (173, '470d3fe0-8727-4187-b6aa-6d7952c4067b_detergente.jpg', '470d3fe0-8727-4187-b6aa-6d7952c4067b_detergente.jpg', 295);
INSERT INTO public.product_images VALUES (192, 'a7ab2380-e8d0-4cbd-a8dc-c8b4f807915b_barra-de-pan-comun.jpg', 'a7ab2380-e8d0-4cbd-a8dc-c8b4f807915b_barra-de-pan-comun.jpg', 308);
INSERT INTO public.product_images VALUES (175, '5536801e-df53-42a5-801b-4b9c961c8981_suavizante.jpg', '5536801e-df53-42a5-801b-4b9c961c8981_suavizante.jpg', 296);
INSERT INTO public.product_images VALUES (176, '6a67ee87-5558-42cf-80a4-8e3a4ae5e008_suavizante2.jpg', '6a67ee87-5558-42cf-80a4-8e3a4ae5e008_suavizante2.jpg', 297);
INSERT INTO public.product_images VALUES (177, '684dcd17-2b35-4f25-bf30-f5a6d7412a0d_quitamanchas.jpg', '684dcd17-2b35-4f25-bf30-f5a6d7412a0d_quitamanchas.jpg', 298);
INSERT INTO public.product_images VALUES (178, '26e5570a-f3c3-4c66-b105-2d4588ce5822_quitamanchas2.jpg', '26e5570a-f3c3-4c66-b105-2d4588ce5822_quitamanchas2.jpg', 299);
INSERT INTO public.product_images VALUES (240, 'b842e1ec-b711-4964-8018-b9b260819f30_microsoftteams-image(12).png', 'b842e1ec-b711-4964-8018-b9b260819f30_microsoftteams-image(12).png', 349);
INSERT INTO public.product_images VALUES (195, 'bf4828aa-b95a-44da-b3e8-db58d3d57402_tarta-queso-m.jpg', 'bf4828aa-b95a-44da-b3e8-db58d3d57402_tarta-queso-m.jpg', 311);
INSERT INTO public.product_images VALUES (241, 'dacc9a36-e203-47a2-b58c-8c909ce912e6_microsoftteams-image(11).png', 'dacc9a36-e203-47a2-b58c-8c909ce912e6_microsoftteams-image(11).png', 350);
INSERT INTO public.product_images VALUES (198, '96d7cba1-fe55-434d-ab6e-9ceb7cf5c5a4_chuletas.jpg', '96d7cba1-fe55-434d-ab6e-9ceb7cf5c5a4_chuletas.jpg', 314);
INSERT INTO public.product_images VALUES (202, '6a0b84c9-8814-4101-89af-baa4e65a6dc0_bodegon-cremoso_gran.jpg', '6a0b84c9-8814-4101-89af-baa4e65a6dc0_bodegon-cremoso_gran.jpg', 318);
INSERT INTO public.product_images VALUES (205, '16b08595-68ed-4579-a809-b1139bd79c7e_kamasutra.jpg', '16b08595-68ed-4579-a809-b1139bd79c7e_kamasutra.jpg', 321);
INSERT INTO public.product_images VALUES (211, 'c0aa9e5d-6fc7-4d9b-83f8-7b08050ea170_charcuteria1.jpg', 'c0aa9e5d-6fc7-4d9b-83f8-7b08050ea170_charcuteria1.jpg', 327);
INSERT INTO public.product_images VALUES (214, '58a04660-827d-45eb-9ee7-ba5b6280e9d6_panceta.jpg', '58a04660-827d-45eb-9ee7-ba5b6280e9d6_panceta.jpg', 330);


--
-- TOC entry 3065 (class 0 OID 204478)
-- Dependencies: 208
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.products VALUES (112, NULL, 'Radiador de aceite', 37.9900017, '2022-10-27', '2000W Radiador de Aceite – 8 Elementos, 3 Configuraciones de Calor, Temporizador y Termostato', NULL, 0, 1, 30);
INSERT INTO public.products VALUES (312, NULL, 'Solomillo de ternera', 23, '2022-11-17', 'Solimillo de ternera gallega. Pieza entera. ', NULL, 0, 4, 42);
INSERT INTO public.products VALUES (106, NULL, 'Agua mineral con gas', 1.25, '2022-10-27', 'Agua mineral con gas 1,25 l.', NULL, 0, 9, 30);
INSERT INTO public.products VALUES (271, 5, 'Papel A4', 65, '2022-11-16', '5 Paquetes De Papel A4 500 Hojas 75 Grs Calidad Premium Temp', NULL, 0, 20, 45);
INSERT INTO public.products VALUES (278, NULL, 'Pizza de barbacoa', 4.69999981, '2022-11-16', 'Denominación del alimento: Pizza Fresca Barbacoa
Nombre del operador: CASA TARRADELLAS. S.A.', NULL, 0, 27, 36);
INSERT INTO public.products VALUES (105, NULL, 'Coca Cola zero x 24 ', 18.2399998, '2022-10-27', 'El mismo sabor de siempre con zero calorias. Pack de 24 latas de 330 ml.', NULL, 0, 9, 30);
INSERT INTO public.products VALUES (97, NULL, 'Huevos tipo A  clase L', 2.54999995, '2022-10-27', NULL, NULL, 0, 10, 36);
INSERT INTO public.products VALUES (102, 25, 'Jamon serrano 150 gr.', 4.0999999, '2022-10-27', NULL, NULL, 0, 23, 42);
INSERT INTO public.products VALUES (303, NULL, 'Pipas', 1.14999998, '2022-11-17', 'Pipas girasol gigantes tostadas aguasal. ¶Bolsa 200 gr', NULL, 0, 8, 41);
INSERT INTO public.products VALUES (110, 0, 'Zapatillas Adidas', 60, '2022-10-27', 'Zapatillas Adidas Samba. Todas las tallas. ', NULL, 0, 18, 46);
INSERT INTO public.products VALUES (295, NULL, 'Detergente liquido', 15.3999996, '2022-11-17', '
Detergente líquido Colon 50 lavados. Colon. Extra luminosidad. ', NULL, 0, 15, 35);
INSERT INTO public.products VALUES (282, NULL, 'Ensaladilla rusa', 4.69999981, '2022-11-16', 'Ensaladilla con atun, huevo, zanahoria, guisantes, patatas y mayonesa. 450 gr. ', NULL, 0, 27, 36);
INSERT INTO public.products VALUES (136, 0, 'Leche Evaporada', 4.30000019, '2022-10-27', 'Leche Evaporada Asturiana 500 ml', NULL, 1, 6, 28);
INSERT INTO public.products VALUES (283, NULL, 'Crema de calabacín', 2.29999995, '2022-11-16', 'Calabacín 100% organico, libre de gluten y grasas trans. 485 ml.', NULL, 0, 27, 36);
INSERT INTO public.products VALUES (131, 10, 'Lampara de mesa IKEA', 90, '2022-10-27', NULL, NULL, 1, 25, 43);
INSERT INTO public.products VALUES (98, 22, 'Vino tinto La Mancha', 2.19000006, '2022-10-27', NULL, NULL, 0, 9, 30);
INSERT INTO public.products VALUES (284, NULL, 'Gambas congeladas', 5.30000019, '2022-11-16', 'Gamba congelada cruda pelada mediana. Paquete de 360 gr. ', NULL, 0, 11, 36);
INSERT INTO public.products VALUES (133, 0, 'Neutrogena Hydro', 87, '2022-10-27', 'La Crema en Gel de Hydro Boost® es una hidratante con una acción nutritiva intensa de larga duración que hidrata la piel durante todo el día. ¡Pruébala!', NULL, 1, 22, 35);
INSERT INTO public.products VALUES (107, 23, 'Tortilla de patata', 2.99000001, '2022-10-27', 'Tortilla de patata con cebolla 450 gr. Lista para servir. ', NULL, 0, 24, 36);
INSERT INTO public.products VALUES (122, 0, 'Lomo de cerdo 1Kg', 4.30000019, '2022-10-27', NULL, NULL, 1, 4, 42);
INSERT INTO public.products VALUES (96, 21, 'Pistachos 200 gr.', 3.19000006, '2022-10-27', NULL, NULL, 0, 8, 41);
INSERT INTO public.products VALUES (99, NULL, 'Cuña de queso RIBETO', 3, '2022-10-27', NULL, NULL, 0, 6, 28);
INSERT INTO public.products VALUES (120, NULL, 'Pasta dental Oral B', 2.4000001, '2022-10-27', NULL, NULL, 0, 14, 35);
INSERT INTO public.products VALUES (115, NULL, 'Boniato 500 gr.', 1.5, '2022-10-27', NULL, NULL, 1, 2, 44);
INSERT INTO public.products VALUES (124, 0, 'Lava vajillas Fairy', 3.79999995, '2022-10-27', NULL, NULL, 0, 12, 41);
INSERT INTO public.products VALUES (297, NULL, 'Suavizante de ropa', 37.7999992, '2022-11-17', '
Suavizante concentrado azul Flor 156 lavados. Formato de ahorro. ', NULL, 0, 15, 35);
INSERT INTO public.products VALUES (319, NULL, 'La Biblia', 54, '2022-11-18', 'La Biblia Latinoamérica (Letra Grande), surtido: colores aleatorios (rojo, verde, azul) Tapa dura – 13 diciembre 2010', NULL, 0, 19, 45);
INSERT INTO public.products VALUES (316, NULL, 'Leche entera', 1.35000002, '2022-11-17', '
Leche UHT entera brik 1 l · ASTURIANA', NULL, 0, 6, 28);
INSERT INTO public.products VALUES (298, NULL, 'Quitamanchas SANYTOL', 5.23000002, '2022-11-17', 'Quitamanchas en polvo. Desinfectante de Tejidos elimina el 99.9% de los gérmenes y bacterias. Es uno de los productos que componen la innovadora gama de desinfectantes sin lejía de Sanytol. ', NULL, 0, 15, 35);
INSERT INTO public.products VALUES (103, 27, 'Granada Mollar 1kg.', 3.29999995, '2022-10-27', NULL, NULL, 1, 2, 44);
INSERT INTO public.products VALUES (118, 10, 'Kiwi 1kg.', 4, '2022-11-04', NULL, NULL, 1, 2, 44);
INSERT INTO public.products VALUES (299, 10, 'Quitamanchas', 4.94999981, '2022-11-17', 'Quitamanchas desengrasante Oxi Action spray 750 ml. Ideal para remover manchas de aceite, salsa y grasas. ', NULL, 0, 15, 35);
INSERT INTO public.products VALUES (130, 0, 'Pintalabios YSL', 135, '2022-10-27', 'Pintalabios YSL Rouge intense', NULL, 1, 22, 35);
INSERT INTO public.products VALUES (308, NULL, 'Barra comun', 1, '2022-11-17', 'Barra de pan comun, hecha de trigo 100%', NULL, 0, 3, 37);
INSERT INTO public.products VALUES (323, NULL, 'iMac', 257, '2022-11-18', 'Apple iMac 21,5 Pgs. i5 2,5 GHz HDD 500 GB RAM 8 GB - Sin Teclado Sin Raton (Reacondicionado)', NULL, 0, 21, 40);
INSERT INTO public.products VALUES (270, 5, 'Escayola Paris', 50, '2022-11-16', 'Proclinic informa:

Color blanco. Tipo 2.

Datos técnicos:

Proporción de mezcla: 50ml-100gr
Tiempo de trabajo: 3'' /5''-7''
Expansión de fraguado: 0,29%', NULL, 0, 26, 43);
INSERT INTO public.products VALUES (135, 10, 'Filete de pollo 1Kg.', 7.3499999, '2022-10-27', NULL, NULL, 0, 4, 42);
INSERT INTO public.products VALUES (272, NULL, 'Tablet 32GB', 250, '2022-11-16', 'Presentamos la tableta RCA 11 Galileo Pro 2 en 1 con teclado desmontable. El 11 Galileo Pro funciona con Android Marshmallow y muestra el nuevo diseño de material del sistema operativo.', NULL, 0, 1, 30);
INSERT INTO public.products VALUES (287, NULL, 'Smartwatch', 650, '2022-11-17', 'Xiaomi Band Activity Tracker Xiaomi Smartwatch Mujeres Hombres con Pantalla a Color AMOLED 1.4 '''' 5ATM Tipo magnético Carga 50m Podómetro Impermeable Pulsera de Fitness Monitor de frecuencia cardíaca', NULL, 0, 1, 30);
INSERT INTO public.products VALUES (128, 0, 'Libreta 80 hojas', 4, '2022-10-27', 'Libreta de papel reciclado. Tapa semidura. Blanco. ', NULL, 1, 20, 45);
INSERT INTO public.products VALUES (89, 0, 'Manzanas', 1.75, '2022-10-27', NULL, NULL, 1, 2, 44);
INSERT INTO public.products VALUES (127, 20, 'La chica de nieve', 45, '2022-10-27', 'JAVIER CASTILLO VUELVE A LO GRANDE.', NULL, 1, 19, 45);
INSERT INTO public.products VALUES (129, 15, 'Ordenador Microsoft', 785, '2022-10-27', 'Microsoft Surface Laptop 4 Negro Intel Core i5-1145G7/8GB/512 GB SSD/13.5" Táctil', NULL, 1, 21, 40);
INSERT INTO public.products VALUES (302, NULL, 'Maiz frito', 0.949999988, '2022-11-17', 'Maíz pequeño frito sabor barbacoa. Bolsa 140 gr', NULL, 0, 8, 41);
INSERT INTO public.products VALUES (314, NULL, 'Chuletas de cordero', 27, '2022-11-17', 'Chuletas de cordero 1 kg.', NULL, 0, 4, 42);
INSERT INTO public.products VALUES (268, NULL, 'Curitas', 3.5, '2022-11-16', 'Marca	Hansaplast
Tamaño	10 Unidades (Paquete de 1)
Peso del producto	33 Gramos
Forma del producto	Tiras
Acerca de este producto
Apósito universal ', NULL, 0, 22, 35);
INSERT INTO public.products VALUES (137, 10, 'Gel de ducha DOVE ', 5.5, '2022-10-27', 'Gel de ducha 400 ml. El mejor gel de ducha para hombre debería hidratar i cuidar la piel. Visita la página de geles de ducha Dove Men+Care para encontrar el tuyo.', NULL, 1, 14, 35);
INSERT INTO public.products VALUES (123, 0, 'Pepinillos Rioverde', 4.5, '2022-10-27', NULL, NULL, 0, 7, 36);
INSERT INTO public.products VALUES (121, 15, 'Ensaladilla congelada', 3.25, '2022-10-27', 'Ensaladilla congelada 1kg. sin judias. ', NULL, 0, 11, 36);
INSERT INTO public.products VALUES (100, 10, 'Zapatillas NB', 90, '2022-10-27', NULL, NULL, 0, 18, 46);
INSERT INTO public.products VALUES (111, 15, 'Sudadera Nike Air', 60, '2022-10-27', NULL, NULL, 0, 18, 46);
INSERT INTO public.products VALUES (101, NULL, 'Zapatillas Nike Jordan', 120, '2022-10-27', NULL, NULL, 0, 18, 46);
INSERT INTO public.products VALUES (95, NULL, 'Auriculares Parasonic', 9.98999977, '2022-10-27', NULL, NULL, 0, 1, 30);
INSERT INTO public.products VALUES (333, NULL, 'Limpiacristales', 30.9500008, '2022-11-18', 'Limpiacristales magnético Ventanas Doble Cristal Climalit Limpiador Ventanas con imán Uso Profesional Limpia Cristales magnetico (Climalit 15-24mm Espesor)', NULL, 0, 12, 41);
INSERT INTO public.products VALUES (340, NULL, 'Magdalenas', 0.949999988, '2022-11-21', 'Magdalenas de naranja, vainilla o chocolate. Horneadas diariamente. ', NULL, 0, 3, 37);
INSERT INTO public.products VALUES (277, NULL, 'Lasaña vegetal 300gr.', 4.5, '2022-11-16', 'Unimarc receta lasaña. receta tarantela (pudín con manzana). loli domínguez. recetas de cocina. Lo bueno de los días fríos son las comidas calientitas nacho román nos prepara un rico estofado lasaña receta unimarc.', NULL, 0, 27, 36);
INSERT INTO public.products VALUES (279, 10, 'Sandwich', 3, '2022-11-16', 'Sandwich de jamon, chorizo, queso, lechuga y tomate. 250 gr. ', NULL, 0, 27, 36);
INSERT INTO public.products VALUES (285, NULL, 'Salmon congelado', 7.5, '2022-11-16', 'Salmón Pink Blue Snow 500 g
Productos de mar de primera calidad que aman los chefs.

La carne de los salmones es típicamente rosa, pero su color puede ir desde el rojo hasta el naranja.', NULL, 0, 11, 36);
INSERT INTO public.products VALUES (286, NULL, 'Nuggets de pollo', 3.6500001, '2022-11-16', 'Nuggets de Pollo Congelados Granja. 500 gr. ', NULL, 0, 11, 36);
INSERT INTO public.products VALUES (296, NULL, 'Suavizante de ropa', 13.5, '2022-11-17', 'Suavizante concentrado para la ropa con hasta 200 días de suavidad y frescor con solo medio tapón
Aroma Floral, disfruta del placer de una fragancia fresca moderna
Evita la electricidad estática en tus prendas', NULL, 0, 15, 35);
INSERT INTO public.products VALUES (304, NULL, 'Pan Challah', 7, '2022-11-17', 'Pan Challah para Shabbat. Disponible jueves y viernes. ', NULL, 0, 3, 37);
INSERT INTO public.products VALUES (309, NULL, 'Barra artesanal', 1.20000005, '2022-11-17', 'Barra de pan artesanal hecha con harinas de trigo y centeno. ', NULL, 0, 3, 37);
INSERT INTO public.products VALUES (311, NULL, 'Tarta de queso', 27, '2022-11-17', 'Tarta de queso al horno, estilo gallego. 1.5 kg aprox. ', NULL, 0, 3, 37);
INSERT INTO public.products VALUES (310, NULL, 'Empanada', 15, '2022-11-17', 'Empanada de atun, pollo, bacalao o verduras al estilo gallego. 1.25 kg.', NULL, 0, 3, 37);
INSERT INTO public.products VALUES (317, NULL, 'Queso gouda', 3.75, '2022-11-17', 'Queso tipo gouda joven. Importado. 150 gr. ', NULL, 0, 6, 28);
INSERT INTO public.products VALUES (318, NULL, 'Queso de cabra', 6.30000019, '2022-11-17', 'Queso creomoso de cabra. 260 gr. ', NULL, 0, 6, 28);
INSERT INTO public.products VALUES (324, 15, 'Ordenador HP', 279, '2022-11-18', 'HP Elite 8300 - Ordenador de sobremesa Completo + TFT 22pulg (Intel Core I5-3470, 8GB RAM,Disco HDD 500GB, WiFi, Windows 10 Profesional 64 bits) (Reacondicionado)', NULL, 0, 21, 40);
INSERT INTO public.products VALUES (325, 30, 'Monitor', 94.9899979, '2022-11-18', 'Philips Monitors - Monitor 243V7QDSB/00- 24", FHD, 75Hz, IPS, Flicker Free, (1920x1080, 250cd/m² VESA, DSUB, HDMI), Color Negro', NULL, 0, 21, 40);
INSERT INTO public.products VALUES (326, NULL, 'Router', 43.9900017, '2022-11-18', 'Tenda Router WiFi 4G03 4G LTE 300 Mbps, banda inalámbrica de 2.4 GHz, control parental, monitoreo del tráfico de datos, puerto LAN/WAN, con ranura para tarjeta SIM', NULL, 0, 21, 40);
INSERT INTO public.products VALUES (331, NULL, 'Fuet', 22.5, '2022-11-18', 'Espuña Minuets Fuet - Cañitas de Fuet / Longaniza. Caja de 18 envases individuales de 50 grs ideal para llevar.', NULL, 0, 23, 42);
INSERT INTO public.products VALUES (327, NULL, 'Paleta iberica', 145, '2022-11-18', 'andreu - Paletilla de Bellota Ibérica entera deshuesada y lista para consumir - Jamón de bellota envasado al vacío en 10 paquetes. Pack UNICS Total 1,35 kg', NULL, 0, 23, 42);
INSERT INTO public.products VALUES (329, NULL, 'Chorizo', 14.25, '2022-11-18', 'CHORIZO CULAR IBÉRICO DE BELLOTA CERTIFICADO ELABORACIÓN TRADICIONAL Y CURACIÓN EN BODEGA NATURAL. PIEZA DE 750-800 gr ENVASADA AL VACIO', NULL, 0, 23, 42);
INSERT INTO public.products VALUES (289, NULL, 'Seda dental', 2.70000005, '2022-11-17', 'Seda Dental Satin Floss 25m contiene cera que favorece el movimiento del hilo en las zonas interdentales', NULL, 0, 14, 35);
INSERT INTO public.products VALUES (330, NULL, 'Panceta ', 89, '2022-11-18', 'Panceta de cerdo con pimienta negra,1.3 kg', NULL, 0, 23, 42);
INSERT INTO public.products VALUES (294, 10, 'Cepillo dental electrico', 250, '2022-11-17', '
Oral-B Vitality 80312499 cepillo eléctrico para dientes Adulto Cepillo dental oscilante Negro', NULL, 0, 14, 35);
INSERT INTO public.products VALUES (322, NULL, 'Juego de tronos', 25, '2022-11-18', 'Juego De Tronos / A Game of Thrones: Cancion De Hielo Y Fuego I (Canción de Hielo y Fuego) Tapa blanda – 21 junio 2022
de George R. R. Martin  (Autor)', NULL, 0, 19, 45);
INSERT INTO public.products VALUES (332, NULL, 'Cepillo electrico', 20.9899998, '2022-11-18', 'Cepillo de Limpieza eléctrico, fregador Giratorio eléctrico con 3 Cabezales de Cepillo, Kit de Limpieza automático para bañera de baño, Cocina, azulejo, Ventana, bañera, Plato, Fregadero', NULL, 0, 12, 41);
INSERT INTO public.products VALUES (269, NULL, 'Televisor Samsung J6200 50"', 2500, '2022-11-16', 'Disfrute de la conectividad a Internet y el intercambio de contenido inalámbrico con el Samsung J6200 50" Class Full HD Smart LED TV. Este modelo tiene una pantalla de 50" y cuenta con una resolución nativa de 1920 x 1080', NULL, 0, 1, 30);
INSERT INTO public.products VALUES (290, NULL, 'Seda dental', 3.75, '2022-11-17', 'Seda Dental Satin Floss 50m contiene cera que favorece el movimiento del hilo en las zonas interdentales', NULL, 0, 14, 35);
INSERT INTO public.products VALUES (259, NULL, 'Uva bicolor (500g)', 2, '2022-11-15', NULL, NULL, 0, 2, 44);
INSERT INTO public.products VALUES (320, NULL, 'Tora', 120, '2022-11-18', 'Tora (Libros de Moises) con Haftarot Tapa dura – 1 enero 2006
de Moises Katznelson - Editorial Sinai (Autor), Editorial Sinai (Ilustrador)', NULL, 0, 19, 45);
INSERT INTO public.products VALUES (321, NULL, 'Kamasutra', 45, '2022-11-18', 'Kamasutra original Ilustrado: El clásico de la literatura erótica (Eros) Tapa blanda – Ilustrado, 1 julio 2007
de Sebastian Aguilar (Autor)', NULL, 0, 19, 45);
INSERT INTO public.products VALUES (300, NULL, 'Cacahuetes', 3.5, '2022-11-17', 'Cacahuetes frescos a granel. ', NULL, 1, 8, 41);
INSERT INTO public.products VALUES (301, NULL, 'Patatas fritas', 3.25, '2022-11-17', 'Patatas fritas al sarten, saladas. 2 kg.', NULL, 0, 8, 41);
INSERT INTO public.products VALUES (313, NULL, 'Carne picada', 3.75, '2022-11-17', 'Carne picada de ternera gallega. 500 gr. ', NULL, 0, 4, 42);
INSERT INTO public.products VALUES (264, NULL, 'Curitas', 5.5, '2022-11-16', 'Resistentes al agua para evitar bacterias y suciedad. 20 Unidades', NULL, 0, 22, 35);
INSERT INTO public.products VALUES (125, 0, 'Sarten 24 cm', 16.5, '2022-10-27', NULL, NULL, 1, 13, 36);
INSERT INTO public.products VALUES (315, NULL, 'Balón Fútbol', 75, '2022-11-17', '
Balón Fútbol adidas Epp 2-888', NULL, 0, 18, 46);
INSERT INTO public.products VALUES (339, NULL, 'Roscon de Reyes', 25, '2022-11-21', 'Roscon de reyes, distintos rellenos: nata, chocolate y crema inglesa. 1 kg.', NULL, 0, 3, 37);
INSERT INTO public.products VALUES (341, NULL, 'Croissant', 1, '2022-11-22', 'Croissant 350 gr.', NULL, 0, 3, 37);
INSERT INTO public.products VALUES (348, 10, 'Plátanos kg', 3, '2022-11-28', 'Plátano de Canarias. Con motitas', NULL, 0, 2, 44);
INSERT INTO public.products VALUES (349, NULL, 'Patata, kg', 1.20000005, '2022-11-28', 'Patata de Xinzo. Ideal para cocer', NULL, 0, 2, 44);
INSERT INTO public.products VALUES (353, NULL, 'Uvas (1kg)', 2, '2022-11-29', NULL, NULL, 0, 2, 55);
INSERT INTO public.products VALUES (350, NULL, 'Fresón de Palos, kg', 3, '2022-11-28', 'Fresón de Palos, bandeja de 1 kg.', NULL, 0, 2, 44);


--
-- TOC entry 3056 (class 0 OID 203257)
-- Dependencies: 199
-- Data for Name: profiles; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.profiles VALUES (1, 'Acceso general', 'Administrador');
INSERT INTO public.profiles VALUES (2, 'Acceso Clientes', 'Cliente');
INSERT INTO public.profiles VALUES (3, 'Acceso tienda', 'Tienda');
INSERT INTO public.profiles VALUES (4, 'Acceso servicios', 'Servicios');
INSERT INTO public.profiles VALUES (5, 'Acceso transportista', 'Transportista');


--
-- TOC entry 3057 (class 0 OID 203266)
-- Dependencies: 200
-- Data for Name: profiles_sections_map; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.profiles_sections_map VALUES (1, 1);
INSERT INTO public.profiles_sections_map VALUES (1, 2);
INSERT INTO public.profiles_sections_map VALUES (1, 3);
INSERT INTO public.profiles_sections_map VALUES (2, 2);
INSERT INTO public.profiles_sections_map VALUES (3, 2);
INSERT INTO public.profiles_sections_map VALUES (3, 3);


--
-- TOC entry 3059 (class 0 OID 203271)
-- Dependencies: 202
-- Data for Name: sections; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.sections VALUES (2, 'CLIENTS', 'Area Clientes', 'Clientes');
INSERT INTO public.sections VALUES (3, 'SHOPS', 'Area tiendas', 'Tiendas');
INSERT INTO public.sections VALUES (1, 'ADMIN', 'Perfiles y secciones a los que puede acceder cada perfil.', 'Contactos');


--
-- TOC entry 3074 (class 0 OID 204928)
-- Dependencies: 217
-- Data for Name: shop_images; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.shop_images VALUES (88, '581e7f8a-8d19-44d0-94b1-0d0c1fe7b41b_tabishopfrutas.png', '581e7f8a-8d19-44d0-94b1-0d0c1fe7b41b_tabishopfrutas.png', 28);
INSERT INTO public.shop_images VALUES (49, '201514a7-a1dc-4b8b-8976-c68878fcf4fa_imageshop4.png', '201514a7-a1dc-4b8b-8976-c68878fcf4fa_imageshop4.png', 40);
INSERT INTO public.shop_images VALUES (51, '3e17c231-9fee-4d82-9336-59938435264e_farmacia.jpg', '3e17c231-9fee-4d82-9336-59938435264e_farmacia.jpg', 35);
INSERT INTO public.shop_images VALUES (52, 'fe0e732b-3e94-4c9f-8238-b18f860b90e6_imageshop.jpg', 'fe0e732b-3e94-4c9f-8238-b18f860b90e6_imageshop.jpg', 36);
INSERT INTO public.shop_images VALUES (54, '99220388-c02b-4fcd-901e-e94763fbaf20_imageshop3.jpg', '99220388-c02b-4fcd-901e-e94763fbaf20_imageshop3.jpg', 41);
INSERT INTO public.shop_images VALUES (96, '74ae324b-84dc-4a0a-b88b-ffd5e9fa9784_tabishopfruteria.png', '74ae324b-84dc-4a0a-b88b-ffd5e9fa9784_tabishopfruteria.png', 44);
INSERT INTO public.shop_images VALUES (99, '6bb4273e-0b79-49f5-8974-b00fb9ca21af_tabishopfrutas.png', '6bb4273e-0b79-49f5-8974-b00fb9ca21af_tabishopfrutas.png', 55);


--
-- TOC entry 3069 (class 0 OID 204773)
-- Dependencies: 212
-- Data for Name: shops; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.shops VALUES (42, NULL, 'Rua Retrama 14, 36940', 'Cangas', 'Las mejores carnes de Galicia', 'carniceria@galicia.com', 'Carniceria Galicia', '65789234', 318, NULL, NULL);
INSERT INTO public.shops VALUES (10, 1, 'calle corre platano', 'a coruña', 'descripcion', 'nuevoe3@mailcom', 'Frutas Paco', '92792531', 75, 'https://es-es.facebook.com', 'https://instagram.com');
INSERT INTO public.shops VALUES (41, NULL, 'Camino Zoe, 5', 'A Coruña', 'Mercaderia variada', 'luna@mejia.com', 'Todo Market', '654390176', 317, NULL, NULL);
INSERT INTO public.shops VALUES (35, NULL, 'Rua Conde de Torrecedeira 37, 36202', 'Vigo', 'Todo en cosmetica y aseo', 'mipueblo@farmacia.com', 'Cosmetica de mi Pueblo', '986555777', 286, NULL, NULL);
INSERT INTO public.shops VALUES (46, NULL, 'Rua Persico, 6, 15900', 'Padron', NULL, 'jocelyn@cuervo.com', 'Deportes Jocelyn', '678903466', 322, NULL, NULL);
INSERT INTO public.shops VALUES (55, NULL, 'R\ Rosalia de Castro 15', 'A Coruña', 'Frutas del barrio, de calidad y excelente sabor', 'flacereza@gmail.com', 'Frutas La Cereza', '661541541', 333, NULL, 'https://www.instagram.com/');
INSERT INTO public.shops VALUES (28, 1, 'Calle San Bernardo, 29, Santiago', 'Santiago', 'Tu fruteria de confianza', 'tami@shop.com', 'TamiShop', '688958855', 285, NULL, 'https://instagram.com');
INSERT INTO public.shops VALUES (30, 1, 'Ua direccion', 'Barcelona', 'descripcion', 'emunah@shop.com', 'Tienda De Adolfo', '777777777', 193, NULL, NULL);
INSERT INTO public.shops VALUES (9, 1, 'calle piruleta', 'a coruña', 'descripcion', 'nuevoe3@mailcom', 'Cortocircuito', '92798531', 78, NULL, NULL);
INSERT INTO public.shops VALUES (36, NULL, 'Ronda Otero, 7', 'Santiago', NULL, 'pako@tienda.com', 'Tienda Pako', '988222333', 313, NULL, NULL);
INSERT INTO public.shops VALUES (40, NULL, 'Rúa Covarrubias, 184, Bajo 3º', 'Santiago', NULL, 'pepe@garcia.com', 'Tienda de Pepe', '666666666', 316, NULL, NULL);
INSERT INTO public.shops VALUES (37, NULL, 'Calle Celia, 81', 'Pontevedra', 'La panaderia de toda la vida!', 'shimon@shepard.com', 'Panaderia Challah', '987123654', 314, NULL, NULL);
INSERT INTO public.shops VALUES (45, NULL, 'Av. do Regato, 3, 32005', 'Ourense', 'Papeleria y libreria para ti', 'sandra@ozuna.com', 'Papeleria Ourense', '989646525', 321, NULL, NULL);
INSERT INTO public.shops VALUES (43, NULL, 'Rua Brea 27, 36416', 'Mos', 'Ferreteria completa', 'ferreteria@manolo.com', 'Ferreteria Manolo', '65748395', 319, NULL, NULL);
INSERT INTO public.shops VALUES (44, NULL, 'Rua Oliveiras 79, 15895', 'O Milladoiro', 'Frutas y verduras frescas del campo', 'froitas@levas.com', 'Froitas Levas', '987123506', 320, NULL, NULL);
INSERT INTO public.shops VALUES (11, 1, 'calle Migas', 'a coruña', 'descripcion', 'nuevoe4@mailcom', 'Panaderia Mollete', '92732531', 198, NULL, NULL);


--
-- TOC entry 3070 (class 0 OID 204782)
-- Dependencies: 213
-- Data for Name: shops_categories_map; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.shops_categories_map VALUES (9, 1);
INSERT INTO public.shops_categories_map VALUES (10, 2);
INSERT INTO public.shops_categories_map VALUES (11, 3);
INSERT INTO public.shops_categories_map VALUES (28, 28);
INSERT INTO public.shops_categories_map VALUES (30, 19);
INSERT INTO public.shops_categories_map VALUES (35, 22);
INSERT INTO public.shops_categories_map VALUES (36, 27);
INSERT INTO public.shops_categories_map VALUES (37, 3);
INSERT INTO public.shops_categories_map VALUES (40, 19);
INSERT INTO public.shops_categories_map VALUES (41, 25);
INSERT INTO public.shops_categories_map VALUES (42, 4);
INSERT INTO public.shops_categories_map VALUES (43, 26);
INSERT INTO public.shops_categories_map VALUES (44, 2);
INSERT INTO public.shops_categories_map VALUES (45, 20);
INSERT INTO public.shops_categories_map VALUES (46, 17);
INSERT INTO public.shops_categories_map VALUES (55, 2);


--
-- TOC entry 3061 (class 0 OID 203282)
-- Dependencies: 204
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.users VALUES (75, 'Pablo123456', 'Pablo', 'PFL1234', 'Fuentes', 'Lopez', 'pablo@fuentes.es', NULL, 1, '2022-10-11 09:09:02.322');
INSERT INTO public.users VALUES (76, 'Pablo1234567', 'Pablo', 'PFL1234', 'Fuentes', 'Lopez', 'pablo1@fuentes.es', NULL, 1, '2022-10-11 10:22:58.131');
INSERT INTO public.users VALUES (78, 'Pablo12345678', 'Pablo', '2x01nKjimUUGMSrxFTqNQQ==', 'Fuentes', 'Lopez', 'pablo2@fuentes.es', NULL, 1, '2022-10-11 10:25:09.841');
INSERT INTO public.users VALUES (79, 'PabloA', 'Pablo', '46VySzLEmtSrIQ/BqZNINQ==', 'Fuentes', 'Lopez', 'pablo3@fuentes.es', NULL, 1, '2022-10-11 10:38:57.432');
INSERT INTO public.users VALUES (81, 'javaliente', 'Pablo', 'iahBrp7zqAbL0K0RHgPscw==', 'Fuentes', 'Lopez', 'javaliente@fuentes.es', NULL, 1, '2022-10-11 11:26:03.622');
INSERT INTO public.users VALUES (313, 'Pako', 'Pako', 'tZCXKCWG76B0V8A/ii78eQ==', 'Pako', 'Pako', 'pako@tienda.com', NULL, NULL, '2022-11-16 16:34:22.451');
INSERT INTO public.users VALUES (314, 'Shimon', 'Shimon', '6/d1V5xAQD+WvahjmS9a+g==', 'Kalmanovitz', 'Shepard', 'shimon@shepard.com', NULL, NULL, '2022-11-17 11:49:33.236');
INSERT INTO public.users VALUES (315, 'Embora', 'Embora', 'UZUAx23YzWoqJE0CjrI1IQ==', 'Maria', 'Beagle', 'embora@beagle.guau', NULL, NULL, '2022-11-18 10:43:24.759');
INSERT INTO public.users VALUES (316, 'ShopPepe', 'Pepe', 'PUmYBoJH57Pb8ABPc1H9Sg==', 'Garcia', 'Garcia', 'pepe@garcia.com', NULL, NULL, '2022-11-18 10:48:49.506');
INSERT INTO public.users VALUES (317, 'California', 'Luna', 'b3d/wB04ESTREmP1bWOLzg==', 'Mejia', 'Largo', 'luna@mejia.com', NULL, NULL, '2022-11-18 12:48:39.509');
INSERT INTO public.users VALUES (318, 'Tendea1', 'Luis', 'DRoz9Of9DdIq1t2rrxWNEQ==', 'Hurtado', 'Mejia', 'tendea1@tendea.com', NULL, NULL, '2022-11-21 09:57:31.184');
INSERT INTO public.users VALUES (319, 'Tendea2', 'Tendea', 'WF5V4rvCEPQyXfPvXYDQqQ==', 'Tienda', 'tienda', 'tienda@tendea.com', NULL, NULL, '2022-11-21 10:19:12.586');
INSERT INTO public.users VALUES (320, 'Tendea3', 'Froitas', 'bckBx0SzoSon8azu9XXe+Q==', 'Levas', 'Agora', 'froitas@levas.com', NULL, NULL, '2022-11-21 10:37:07.399');
INSERT INTO public.users VALUES (321, 'Tendea4', 'Sandra', 'ahzrcRL5fva9WCFEiVbZHA==', 'Ibarguen', 'Ozuna', 'sandra@ozuna.com', NULL, NULL, '2022-11-21 10:40:26.224');
INSERT INTO public.users VALUES (322, 'Tendea5', 'Jocelyn', 'MzhaSTLxUGsR6Kit9X0pDQ==', 'Cuervo', 'Palacios', 'jocelyn@cuervo.com', NULL, NULL, '2022-11-21 11:58:34.105');
INSERT INTO public.users VALUES (324, 'Tienda16', 'Manuel', 'Iwg9esgQ7PPypUprVnv0/Q==', 'Martinez', 'Martinez', 'mmm@gmail.com', NULL, NULL, '2022-11-23 13:29:00.722');
INSERT INTO public.users VALUES (325, 'PabloFL', 'Pablo', 'hNVfr/iqVHB1KH89JU+NiQ==', 'Fuentes', 'Lopez', 'pfuentesl@gmail.com', NULL, NULL, '2022-11-24 12:42:21.987');
INSERT INTO public.users VALUES (205, 'Manuel88', 'Qwer', 'ARibDZoSCbYIi6pTpCCGIQ==', 'ty', 'ui', 'Qwer@ty.es', NULL, NULL, '2022-10-14 19:13:58.664');
INSERT INTO public.users VALUES (308, 'admin1', 'Adolfo', 'WphKNlFzRveaKBNZTSusoA==', 'Blanco', 'Salazar', 'ajblanco156@gmail.com', NULL, NULL, '2022-11-16 14:28:20.049');
INSERT INTO public.users VALUES (309, 'admin2', 'Adolfo José', 'OF+CnyWF72yvGc9LmFmjOw==', 'Blanco', 'Salazar', 'adolfo.blanco@campusdual.com', NULL, NULL, '2022-11-16 14:34:11.756');
INSERT INTO public.users VALUES (312, 'admin3', 'Adolfo José', 'feN+NMcdkZbFTXCQRNvjFg==', 'Blanco', 'Salazar', 'ajblanco156@icloud.com', NULL, NULL, '2022-11-16 14:35:14.373');
INSERT INTO public.users VALUES (193, 'demoadmin', 'Adolfo', 'nEPLPaUm2CmNqJVAxBFetQ==', 'Blanco', 'Salazar', 'demo@mail.com', NULL, 1, '2022-10-14 09:19:23.204');
INSERT INTO public.users VALUES (198, 'demoLH2', 'Adolfo', 'MZbNglM9EDG+jvz7/eVKTw==', 'Blanco', 'Salazar', 'demoLH2@mail.com', NULL, NULL, '2022-10-14 10:14:51.994');
INSERT INTO public.users VALUES (199, 'PabloFPL1', 'Pablo', 'nsRBJEUMyIjWU/zENnHekA==', 'Fuentes', 'Lopez', 'pablopfl12@gmail.com', NULL, NULL, '2022-10-14 10:29:24.413');
INSERT INTO public.users VALUES (200, 'Javaliente1', 'Pablo', 'Lnl/4E+nNdgq65roetcneA==', 'Fuentes', 'Lopez', 'javaliente1@gmail.com', NULL, NULL, '2022-10-14 10:30:46.795');
INSERT INTO public.users VALUES (333, 'PabloPFL', 'Pablo', 'sos/L1v58ps88sbEYBRIhQ==', 'Fuentes', 'López', 'pablopfl@gmail.com', NULL, NULL, '2022-11-29 11:44:32.247');
INSERT INTO public.users VALUES (207, 'kululuslabo1', 'asdfsadf', 'r2ph3r0sekslpuBX2h1IXw==', 'sdfgdsf', 'gdsgfdsgf', 'sdfgdfg@gmail.com', NULL, NULL, '2022-10-18 09:41:36.795');
INSERT INTO public.users VALUES (208, 'Javalientes12', 'asdfas', 'JCriUKSbsnZaVrH+wfG7XA==', 'dfasdfasdf', 'sadfsadf', 'sdgdsfhntdnty@gmail.com', NULL, NULL, '2022-10-18 11:42:42.418');
INSERT INTO public.users VALUES (283, 'Tendero1', 'Pablo', 'Cacahuete16', 'Fuentes', 'López', 'tendea101@tendea.com', NULL, NULL, NULL);
INSERT INTO public.users VALUES (284, 'Tendero2', 'Roi', '6rt9nWVIgSR14yxNmMRNTw==', 'Gandara', 'Loe', 'tendea102@tendea.com', NULL, NULL, '2022-11-08 13:59:07.93');
INSERT INTO public.users VALUES (285, 'Tabi', 'Maria', 'yMcwSrInGZ/i3r1CpTckOA==', 'Tabi', 'Tabi', 'tabi@tabito.com', NULL, NULL, '2022-11-10 16:41:08.412');
INSERT INTO public.users VALUES (286, 'Shalom', 'Luis', 'Mv8kVV7BKMvEqciJRcqiCA==', 'Hurtado', 'Mejia', 'shalom@tiendas.com', NULL, NULL, '2022-11-16 12:54:50.184');


--
-- TOC entry 3062 (class 0 OID 203291)
-- Dependencies: 205
-- Data for Name: users_profiles_map; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.users_profiles_map VALUES (333, 3);
INSERT INTO public.users_profiles_map VALUES (205, 2);
INSERT INTO public.users_profiles_map VALUES (207, 2);
INSERT INTO public.users_profiles_map VALUES (208, 2);
INSERT INTO public.users_profiles_map VALUES (283, 3);
INSERT INTO public.users_profiles_map VALUES (284, 3);
INSERT INTO public.users_profiles_map VALUES (285, 3);
INSERT INTO public.users_profiles_map VALUES (198, 3);
INSERT INTO public.users_profiles_map VALUES (286, 3);
INSERT INTO public.users_profiles_map VALUES (308, 2);
INSERT INTO public.users_profiles_map VALUES (309, 2);
INSERT INTO public.users_profiles_map VALUES (312, 2);
INSERT INTO public.users_profiles_map VALUES (313, 3);
INSERT INTO public.users_profiles_map VALUES (193, 2);
INSERT INTO public.users_profiles_map VALUES (314, 3);
INSERT INTO public.users_profiles_map VALUES (200, 1);
INSERT INTO public.users_profiles_map VALUES (315, 2);
INSERT INTO public.users_profiles_map VALUES (316, 3);
INSERT INTO public.users_profiles_map VALUES (317, 3);
INSERT INTO public.users_profiles_map VALUES (318, 3);
INSERT INTO public.users_profiles_map VALUES (319, 3);
INSERT INTO public.users_profiles_map VALUES (320, 3);
INSERT INTO public.users_profiles_map VALUES (321, 3);
INSERT INTO public.users_profiles_map VALUES (322, 3);
INSERT INTO public.users_profiles_map VALUES (324, 2);
INSERT INTO public.users_profiles_map VALUES (199, 3);


--
-- TOC entry 3098 (class 0 OID 0)
-- Dependencies: 218
-- Name: cart_items_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.cart_items_id_seq', 36, true);


--
-- TOC entry 3099 (class 0 OID 0)
-- Dependencies: 220
-- Name: carts_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.carts_id_seq', 9, true);


--
-- TOC entry 3100 (class 0 OID 0)
-- Dependencies: 209
-- Name: categories_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.categories_id_seq', 1, false);


--
-- TOC entry 3101 (class 0 OID 0)
-- Dependencies: 196
-- Name: contacts_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.contacts_id_seq', 7, true);


--
-- TOC entry 3102 (class 0 OID 0)
-- Dependencies: 214
-- Name: product_images_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.product_images_id_seq', 244, true);


--
-- TOC entry 3103 (class 0 OID 0)
-- Dependencies: 207
-- Name: products_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.products_id_seq', 353, true);


--
-- TOC entry 3104 (class 0 OID 0)
-- Dependencies: 198
-- Name: profiles_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.profiles_id_seq', 2, true);


--
-- TOC entry 3105 (class 0 OID 0)
-- Dependencies: 201
-- Name: sections_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.sections_id_seq', 2, true);


--
-- TOC entry 3106 (class 0 OID 0)
-- Dependencies: 216
-- Name: shop_images_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.shop_images_id_seq', 99, true);


--
-- TOC entry 3107 (class 0 OID 0)
-- Dependencies: 211
-- Name: shops_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.shops_id_seq', 55, true);


--
-- TOC entry 3108 (class 0 OID 0)
-- Dependencies: 203
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.users_id_seq', 333, true);


--
-- TOC entry 2911 (class 2606 OID 204985)
-- Name: cart_items cart_items_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.cart_items
    ADD CONSTRAINT cart_items_pkey PRIMARY KEY (id);


--
-- TOC entry 2913 (class 2606 OID 204998)
-- Name: carts carts_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.carts
    ADD CONSTRAINT carts_pkey PRIMARY KEY (id);


--
-- TOC entry 2899 (class 2606 OID 204672)
-- Name: categories categories_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (id);


--
-- TOC entry 2877 (class 2606 OID 203254)
-- Name: contacts contacts_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.contacts
    ADD CONSTRAINT contacts_pkey PRIMARY KEY (id);


--
-- TOC entry 2907 (class 2606 OID 204855)
-- Name: product_images product_images_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product_images
    ADD CONSTRAINT product_images_pkey PRIMARY KEY (id);


--
-- TOC entry 2897 (class 2606 OID 204483)
-- Name: products products_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);


--
-- TOC entry 2881 (class 2606 OID 203265)
-- Name: profiles profiles_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.profiles
    ADD CONSTRAINT profiles_pkey PRIMARY KEY (id);


--
-- TOC entry 2883 (class 2606 OID 203279)
-- Name: sections sections_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sections
    ADD CONSTRAINT sections_pkey PRIMARY KEY (id);


--
-- TOC entry 2909 (class 2606 OID 204936)
-- Name: shop_images shop_images_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.shop_images
    ADD CONSTRAINT shop_images_pkey PRIMARY KEY (id);


--
-- TOC entry 2905 (class 2606 OID 204786)
-- Name: shops_categories_map shops_categories_map_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.shops_categories_map
    ADD CONSTRAINT shops_categories_map_pkey PRIMARY KEY (shop_id, category_id);


--
-- TOC entry 2901 (class 2606 OID 204781)
-- Name: shops shops_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.shops
    ADD CONSTRAINT shops_pkey PRIMARY KEY (id);


--
-- TOC entry 2885 (class 2606 OID 203299)
-- Name: sections uk_3hhqmvx0pt70xjvmjdo5a76go; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sections
    ADD CONSTRAINT uk_3hhqmvx0pt70xjvmjdo5a76go UNIQUE (alias);


--
-- TOC entry 2879 (class 2606 OID 203297)
-- Name: contacts uk_9mg1wlguu09et002069e9qeum; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.contacts
    ADD CONSTRAINT uk_9mg1wlguu09et002069e9qeum UNIQUE (phone);


--
-- TOC entry 2903 (class 2606 OID 204790)
-- Name: shops uk_9o6u1cksbcn3h77ir3jf9l24b; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.shops
    ADD CONSTRAINT uk_9o6u1cksbcn3h77ir3jf9l24b UNIQUE (phone);


--
-- TOC entry 2887 (class 2606 OID 203301)
-- Name: users uk_ow0gan20590jrb00upg3va2fn; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk_ow0gan20590jrb00upg3va2fn UNIQUE (login);


--
-- TOC entry 2915 (class 2606 OID 205017)
-- Name: carts_items uk_qh6eu5td19tdxi31j9u6axlaf; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.carts_items
    ADD CONSTRAINT uk_qh6eu5td19tdxi31j9u6axlaf UNIQUE (items_id);


--
-- TOC entry 2889 (class 2606 OID 203990)
-- Name: users unique_email; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT unique_email UNIQUE (email);


--
-- TOC entry 2891 (class 2606 OID 203290)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 2895 (class 2606 OID 203295)
-- Name: users_profiles_map users_profiles_map_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users_profiles_map
    ADD CONSTRAINT users_profiles_map_pkey PRIMARY KEY (user_id, profile_id);


--
-- TOC entry 2893 (class 2606 OID 203988)
-- Name: users users_un; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_un UNIQUE (login, email);


--
-- TOC entry 2927 (class 2606 OID 204986)
-- Name: cart_items fk1re40cjegsfvw58xrkdp6bac6; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.cart_items
    ADD CONSTRAINT fk1re40cjegsfvw58xrkdp6bac6 FOREIGN KEY (product_id) REFERENCES public.products(id);


--
-- TOC entry 2922 (class 2606 OID 204796)
-- Name: shops fk34po7mmli7wotimo70r6640ap; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.shops
    ADD CONSTRAINT fk34po7mmli7wotimo70r6640ap FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 2930 (class 2606 OID 205018)
-- Name: carts_items fk69xi9wmv7eowgjemoxlogkcy1; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.carts_items
    ADD CONSTRAINT fk69xi9wmv7eowgjemoxlogkcy1 FOREIGN KEY (items_id) REFERENCES public.cart_items(id);


--
-- TOC entry 2923 (class 2606 OID 204801)
-- Name: shops_categories_map fk747nd6qq570e0td79kx3m6xaf; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.shops_categories_map
    ADD CONSTRAINT fk747nd6qq570e0td79kx3m6xaf FOREIGN KEY (category_id) REFERENCES public.categories(id);


--
-- TOC entry 2921 (class 2606 OID 204791)
-- Name: products fk7kp8sbhxboponhx3lxqtmkcoj; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT fk7kp8sbhxboponhx3lxqtmkcoj FOREIGN KEY (shop_id) REFERENCES public.shops(id);


--
-- TOC entry 2929 (class 2606 OID 205004)
-- Name: carts fkb5o626f86h46m4s7ms6ginnop; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.carts
    ADD CONSTRAINT fkb5o626f86h46m4s7ms6ginnop FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 2919 (class 2606 OID 203317)
-- Name: users_profiles_map fkgu8qveimyui706fn78n4xbenf; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users_profiles_map
    ADD CONSTRAINT fkgu8qveimyui706fn78n4xbenf FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 2926 (class 2606 OID 204944)
-- Name: shop_images fkhklkimv3eu30fw1v56wp65kjx; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.shop_images
    ADD CONSTRAINT fkhklkimv3eu30fw1v56wp65kjx FOREIGN KEY (shop_id) REFERENCES public.shops(id);


--
-- TOC entry 2916 (class 2606 OID 203302)
-- Name: profiles_sections_map fkkqferkfgrrnho62b21rya9ej9; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.profiles_sections_map
    ADD CONSTRAINT fkkqferkfgrrnho62b21rya9ej9 FOREIGN KEY (section_id) REFERENCES public.sections(id);


--
-- TOC entry 2928 (class 2606 OID 205029)
-- Name: cart_items fkn8gs5gdm2flx8ehe4u00rhkqp; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.cart_items
    ADD CONSTRAINT fkn8gs5gdm2flx8ehe4u00rhkqp FOREIGN KEY (catery_id) REFERENCES public.carts(id);


--
-- TOC entry 2917 (class 2606 OID 203307)
-- Name: profiles_sections_map fknbjkd2pgwcyijblewp1d33rox; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.profiles_sections_map
    ADD CONSTRAINT fknbjkd2pgwcyijblewp1d33rox FOREIGN KEY (profile_id) REFERENCES public.profiles(id);


--
-- TOC entry 2924 (class 2606 OID 204806)
-- Name: shops_categories_map fknjqmeqcyfl5xmtlcfu23supau; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.shops_categories_map
    ADD CONSTRAINT fknjqmeqcyfl5xmtlcfu23supau FOREIGN KEY (shop_id) REFERENCES public.shops(id);


--
-- TOC entry 2920 (class 2606 OID 204733)
-- Name: products fkog2rp4qthbtt2lfyhfo32lsw9; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT fkog2rp4qthbtt2lfyhfo32lsw9 FOREIGN KEY (category_id) REFERENCES public.categories(id);


--
-- TOC entry 2931 (class 2606 OID 205023)
-- Name: carts_items fkpulqfptcuw8ttvds9pihgai16; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.carts_items
    ADD CONSTRAINT fkpulqfptcuw8ttvds9pihgai16 FOREIGN KEY (cart_id) REFERENCES public.carts(id);


--
-- TOC entry 2925 (class 2606 OID 204856)
-- Name: product_images fkqnq71xsohugpqwf3c9gxmsuy; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product_images
    ADD CONSTRAINT fkqnq71xsohugpqwf3c9gxmsuy FOREIGN KEY (product_id) REFERENCES public.products(id);


--
-- TOC entry 2918 (class 2606 OID 203312)
-- Name: users_profiles_map fksv94wyv9yb3b2hmvr5f48o281; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users_profiles_map
    ADD CONSTRAINT fksv94wyv9yb3b2hmvr5f48o281 FOREIGN KEY (profile_id) REFERENCES public.profiles(id);


-- Completed on 2023-02-02 13:18:29

--
-- PostgreSQL database dump complete
--

