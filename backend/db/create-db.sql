--
-- PostgreSQL database dump
--

-- Dumped from database version 10.13
-- Dumped by pg_dump version 10.13

-- Started on 2020-08-12 19:42:23

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
-- TOC entry 2859 (class 0 OID 0)
-- Dependencies: 2858
-- Name: DATABASE engineering_thesis; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE engineering_thesis IS 'engeneer tables stuff';


--
-- TOC entry 8 (class 2615 OID 16395)
-- Name: MedKit; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA "MedKit";


ALTER SCHEMA "MedKit" OWNER TO postgres;

--
-- TOC entry 1 (class 3079 OID 12924)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2861 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 199 (class 1259 OID 24610)
-- Name: Appointments; Type: TABLE; Schema: MedKit; Owner: postgres
--

CREATE TABLE "MedKit"."Appointments" (
    "Appointment_ID" numeric NOT NULL,
    "Appointment_Date" date,
    "Appointment_Cost" numeric(7,0),
    "Doctor_ID" numeric,
    "Patient_ID" numeric
);


ALTER TABLE "MedKit"."Appointments" OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 24594)
-- Name: Doctors; Type: TABLE; Schema: MedKit; Owner: postgres
--

CREATE TABLE "MedKit"."Doctors" (
    "Doctor_ID" numeric NOT NULL,
    "Doctor_Name" character varying(30),
    "Doctor_Surname" character varying(30),
    "Specialization" character varying(60)
);


ALTER TABLE "MedKit"."Doctors" OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 24660)
-- Name: Medical_Facility; Type: TABLE; Schema: MedKit; Owner: postgres
--

CREATE TABLE "MedKit"."Medical_Facility" (
    "Facility_ID" numeric NOT NULL,
    "Name" character varying,
    "Localization" character varying,
    "Doctors_Count" numeric,
    "Patients_Count" numeric,
    "Contact_Number" character varying
);


ALTER TABLE "MedKit"."Medical_Facility" OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 24630)
-- Name: Patient_Med_Data; Type: TABLE; Schema: MedKit; Owner: postgres
--

CREATE TABLE "MedKit"."Patient_Med_Data" (
    "Med_Data_ID" numeric NOT NULL,
    "Patient_ID" numeric,
    "Doctor_ID" numeric,
    "Date" date,
    "Medical_Procedure" character varying(100)
);


ALTER TABLE "MedKit"."Patient_Med_Data" OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 24586)
-- Name: Patients; Type: TABLE; Schema: MedKit; Owner: postgres
--

CREATE TABLE "MedKit"."Patients" (
    "Patient_ID" numeric NOT NULL,
    "Patient_Name" character varying(30),
    "Patient_Surname" character varying(30),
    "Is_Insured" boolean
);


ALTER TABLE "MedKit"."Patients" OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 24668)
-- Name: Price_List; Type: TABLE; Schema: MedKit; Owner: postgres
--

CREATE TABLE "MedKit"."Price_List" (
    "ID" numeric NOT NULL,
    "Name" character varying,
    "Price" numeric(7,0)
);


ALTER TABLE "MedKit"."Price_List" OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 24811)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 24813)
-- Name: patients; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.patients (
    patiend_id bigint NOT NULL,
    is_insured boolean NOT NULL,
    patient_name character varying(255),
    patient_surname character varying(255)
);


ALTER TABLE public.patients OWNER TO postgres;

--
-- TOC entry 2847 (class 0 OID 24610)
-- Dependencies: 199
-- Data for Name: Appointments; Type: TABLE DATA; Schema: MedKit; Owner: postgres
--

COPY "MedKit"."Appointments" ("Appointment_ID", "Appointment_Date", "Appointment_Cost", "Doctor_ID", "Patient_ID") FROM stdin;
1	2020-08-12	3000	2	1
\.


--
-- TOC entry 2846 (class 0 OID 24594)
-- Dependencies: 198
-- Data for Name: Doctors; Type: TABLE DATA; Schema: MedKit; Owner: postgres
--

COPY "MedKit"."Doctors" ("Doctor_ID", "Doctor_Name", "Doctor_Surname", "Specialization") FROM stdin;
1	Mat	Patterson	Surgeon
2	Tom	Stanson	Oncologist
3	Jason	Blyn	Internist
\.


--
-- TOC entry 2849 (class 0 OID 24660)
-- Dependencies: 201
-- Data for Name: Medical_Facility; Type: TABLE DATA; Schema: MedKit; Owner: postgres
--

COPY "MedKit"."Medical_Facility" ("Facility_ID", "Name", "Localization", "Doctors_Count", "Patients_Count", "Contact_Number") FROM stdin;
1	California_Medical_Univeristy	LA some street and postcode	6	8	666-136-109
\.


--
-- TOC entry 2848 (class 0 OID 24630)
-- Dependencies: 200
-- Data for Name: Patient_Med_Data; Type: TABLE DATA; Schema: MedKit; Owner: postgres
--

COPY "MedKit"."Patient_Med_Data" ("Med_Data_ID", "Patient_ID", "Doctor_ID", "Date", "Medical_Procedure") FROM stdin;
1	1	1	2020-05-12	Tumor remove
\.


--
-- TOC entry 2845 (class 0 OID 24586)
-- Dependencies: 197
-- Data for Name: Patients; Type: TABLE DATA; Schema: MedKit; Owner: postgres
--

COPY "MedKit"."Patients" ("Patient_ID", "Patient_Name", "Patient_Surname", "Is_Insured") FROM stdin;
1	Tom	Ne	t
2	Mat	Jaw	t
3	Shaun	Trout	t
4	James	Clemp	t
5	Scott	Ignon	t
6	Pat	Rewning	t
7	Tom	Toms	t
8	Jacob	Patterson	t
\.


--
-- TOC entry 2850 (class 0 OID 24668)
-- Dependencies: 202
-- Data for Name: Price_List; Type: TABLE DATA; Schema: MedKit; Owner: postgres
--

COPY "MedKit"."Price_List" ("ID", "Name", "Price") FROM stdin;
1	general diagnostics	3000
2	tumor excision	1000
3	tissue study	200
4	teeth removal	1500
5	diabetes checking	130
6	transplantation	14600
7	transfusion	2350
8	anesthesia before surgery	7000
\.


--
-- TOC entry 2852 (class 0 OID 24813)
-- Dependencies: 204
-- Data for Name: patients; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.patients (patiend_id, is_insured, patient_name, patient_surname) FROM stdin;
\.


--
-- TOC entry 2862 (class 0 OID 0)
-- Dependencies: 203
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 1, false);


--
-- TOC entry 2707 (class 2606 OID 24617)
-- Name: Appointments Appointments_pkey; Type: CONSTRAINT; Schema: MedKit; Owner: postgres
--

ALTER TABLE ONLY "MedKit"."Appointments"
    ADD CONSTRAINT "Appointments_pkey" PRIMARY KEY ("Appointment_ID");


--
-- TOC entry 2705 (class 2606 OID 24601)
-- Name: Doctors Doctors_pkey; Type: CONSTRAINT; Schema: MedKit; Owner: postgres
--

ALTER TABLE ONLY "MedKit"."Doctors"
    ADD CONSTRAINT "Doctors_pkey" PRIMARY KEY ("Doctor_ID");


--
-- TOC entry 2715 (class 2606 OID 24667)
-- Name: Medical_Facility Medical_Facility_pkey; Type: CONSTRAINT; Schema: MedKit; Owner: postgres
--

ALTER TABLE ONLY "MedKit"."Medical_Facility"
    ADD CONSTRAINT "Medical_Facility_pkey" PRIMARY KEY ("Facility_ID");


--
-- TOC entry 2711 (class 2606 OID 24637)
-- Name: Patient_Med_Data Patient_Med_Data_pkey; Type: CONSTRAINT; Schema: MedKit; Owner: postgres
--

ALTER TABLE ONLY "MedKit"."Patient_Med_Data"
    ADD CONSTRAINT "Patient_Med_Data_pkey" PRIMARY KEY ("Med_Data_ID");


--
-- TOC entry 2703 (class 2606 OID 24593)
-- Name: Patients Patients_pkey; Type: CONSTRAINT; Schema: MedKit; Owner: postgres
--

ALTER TABLE ONLY "MedKit"."Patients"
    ADD CONSTRAINT "Patients_pkey" PRIMARY KEY ("Patient_ID");


--
-- TOC entry 2717 (class 2606 OID 24675)
-- Name: Price_List Price_List_pkey; Type: CONSTRAINT; Schema: MedKit; Owner: postgres
--

ALTER TABLE ONLY "MedKit"."Price_List"
    ADD CONSTRAINT "Price_List_pkey" PRIMARY KEY ("ID");


--
-- TOC entry 2719 (class 2606 OID 24820)
-- Name: patients patients_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.patients
    ADD CONSTRAINT patients_pkey PRIMARY KEY (patiend_id);


--
-- TOC entry 2708 (class 1259 OID 24623)
-- Name: fki_Doctor_ID; Type: INDEX; Schema: MedKit; Owner: postgres
--

CREATE INDEX "fki_Doctor_ID" ON "MedKit"."Appointments" USING btree ("Doctor_ID");


--
-- TOC entry 2712 (class 1259 OID 24659)
-- Name: fki_Doctor_Med_Data_ID; Type: INDEX; Schema: MedKit; Owner: postgres
--

CREATE INDEX "fki_Doctor_Med_Data_ID" ON "MedKit"."Patient_Med_Data" USING btree ("Doctor_ID");


--
-- TOC entry 2709 (class 1259 OID 24629)
-- Name: fki_Patient_ID; Type: INDEX; Schema: MedKit; Owner: postgres
--

CREATE INDEX "fki_Patient_ID" ON "MedKit"."Appointments" USING btree ("Patient_ID");


--
-- TOC entry 2713 (class 1259 OID 24648)
-- Name: fki_Patient_Med_Data_ID; Type: INDEX; Schema: MedKit; Owner: postgres
--

CREATE INDEX "fki_Patient_Med_Data_ID" ON "MedKit"."Patient_Med_Data" USING btree ("Patient_ID");


--
-- TOC entry 2720 (class 2606 OID 24618)
-- Name: Appointments Doctor_ID; Type: FK CONSTRAINT; Schema: MedKit; Owner: postgres
--

ALTER TABLE ONLY "MedKit"."Appointments"
    ADD CONSTRAINT "Doctor_ID" FOREIGN KEY ("Doctor_ID") REFERENCES "MedKit"."Doctors"("Doctor_ID") NOT VALID;


--
-- TOC entry 2722 (class 2606 OID 24654)
-- Name: Patient_Med_Data Doctor_Med_Data_ID; Type: FK CONSTRAINT; Schema: MedKit; Owner: postgres
--

ALTER TABLE ONLY "MedKit"."Patient_Med_Data"
    ADD CONSTRAINT "Doctor_Med_Data_ID" FOREIGN KEY ("Doctor_ID") REFERENCES "MedKit"."Doctors"("Doctor_ID") NOT VALID;


--
-- TOC entry 2721 (class 2606 OID 24624)
-- Name: Appointments Patient_ID; Type: FK CONSTRAINT; Schema: MedKit; Owner: postgres
--

ALTER TABLE ONLY "MedKit"."Appointments"
    ADD CONSTRAINT "Patient_ID" FOREIGN KEY ("Patient_ID") REFERENCES "MedKit"."Patients"("Patient_ID") NOT VALID;


--
-- TOC entry 2723 (class 2606 OID 24638)
-- Name: Patient_Med_Data Patient_Med_Data_ID; Type: FK CONSTRAINT; Schema: MedKit; Owner: postgres
--

ALTER TABLE ONLY "MedKit"."Patient_Med_Data"
    ADD CONSTRAINT "Patient_Med_Data_ID" FOREIGN KEY ("Patient_ID") REFERENCES "MedKit"."Patients"("Patient_ID") NOT VALID;


-- Completed on 2020-08-12 19:42:23

--
-- PostgreSQL database dump complete
--

