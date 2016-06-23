--
-- Name: material; Type: TABLE; Schema: public;; Tablespace: 
--

CREATE TABLE material (
    id integer NOT NULL,
    descfisica character varying(100),
    entprinc character varying(500),
    imprenta character varying(500),
    titulo character varying(1000)
);

--
-- Name: material_assuntos; Type: TABLE; Schema: public;; Tablespace: 
--

CREATE TABLE material_assuntos (
    material_id integer NOT NULL,
    assuntos character varying(255)
);


--
-- Name: material_id_seq; Type: SEQUENCE; Schema: public;
--

CREATE SEQUENCE material_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: material_id_seq; Type: SEQUENCE OWNED BY; Schema: public;
--

ALTER SEQUENCE material_id_seq OWNED BY material.id;

--
-- Name: id; Type: DEFAULT; Schema: public;
--

ALTER TABLE ONLY material ALTER COLUMN id SET DEFAULT nextval('material_id_seq'::regclass);

--
-- Name: material_pkey; Type: CONSTRAINT; Schema: public;; Tablespace: 
--

ALTER TABLE ONLY material
    ADD CONSTRAINT material_pkey PRIMARY KEY (id);

--
-- Name: fk_4esmsyu0lk3vroauxig3x7grr; Type: FK CONSTRAINT; Schema: public;
--

ALTER TABLE ONLY material_assuntos
    ADD CONSTRAINT material_assuntos_mat_fk FOREIGN KEY (material_id) REFERENCES material(id);