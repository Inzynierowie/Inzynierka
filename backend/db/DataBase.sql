PGDMP         $                x           engineering_thesis    10.13    10.13 $               0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false                       1262    16394    engineering_thesis    DATABASE     �   CREATE DATABASE engineering_thesis WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Polish_Poland.1250' LC_CTYPE = 'Polish_Poland.1250';
 "   DROP DATABASE engineering_thesis;
             postgres    false                        0    0    DATABASE engineering_thesis    COMMENT     C   COMMENT ON DATABASE engineering_thesis IS 'engeneer tables stuff';
                  postgres    false    2847                        2615    16395    MedKit    SCHEMA        CREATE SCHEMA "MedKit";
    DROP SCHEMA "MedKit";
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            !           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    3                        3079    12924    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            "           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            �            1259    24610    Appointments    TABLE     �   CREATE TABLE "MedKit"."Appointments" (
    "Appointment_ID" numeric NOT NULL,
    "Appointment_Date" date,
    "Appointment_Cost" numeric(7,0),
    "Doctor_ID" numeric,
    "Patient_ID" numeric
);
 $   DROP TABLE "MedKit"."Appointments";
       MedKit         postgres    false    8            �            1259    24594    Doctors    TABLE     �   CREATE TABLE "MedKit"."Doctors" (
    "Doctor_ID" numeric NOT NULL,
    "Doctor_Name" character varying(30),
    "Doctor_Surname" character varying(30),
    "Specialization" character varying(60)
);
    DROP TABLE "MedKit"."Doctors";
       MedKit         postgres    false    8            �            1259    24660    Medical_Facility    TABLE     �   CREATE TABLE "MedKit"."Medical_Facility" (
    "Facility_ID" numeric NOT NULL,
    "Name" character varying,
    "Localization" character varying,
    "Doctors_Count" numeric,
    "Patients_Count" numeric,
    "Contact_Number" character varying
);
 (   DROP TABLE "MedKit"."Medical_Facility";
       MedKit         postgres    false    8            �            1259    24630    Patient_Med_Data    TABLE     �   CREATE TABLE "MedKit"."Patient_Med_Data" (
    "Med_Data_ID" numeric NOT NULL,
    "Patient_ID" numeric,
    "Doctor_ID" numeric,
    "Date" date,
    "Medical_Procedure" character varying(100)
);
 (   DROP TABLE "MedKit"."Patient_Med_Data";
       MedKit         postgres    false    8            �            1259    24586    Patients    TABLE     �   CREATE TABLE "MedKit"."Patients" (
    "Patient_ID" numeric NOT NULL,
    "Patient_Name" character varying(30),
    "Patient_Surname" character varying(30),
    "Is_Insured" boolean
);
     DROP TABLE "MedKit"."Patients";
       MedKit         postgres    false    8            �            1259    24668 
   Price_List    TABLE     z   CREATE TABLE "MedKit"."Price_List" (
    "ID" numeric NOT NULL,
    "Name" character varying,
    "Price" numeric(7,0)
);
 "   DROP TABLE "MedKit"."Price_List";
       MedKit         postgres    false    8                      0    24610    Appointments 
   TABLE DATA                  COPY "MedKit"."Appointments" ("Appointment_ID", "Appointment_Date", "Appointment_Cost", "Doctor_ID", "Patient_ID") FROM stdin;
    MedKit       postgres    false    199   �(                 0    24594    Doctors 
   TABLE DATA               e   COPY "MedKit"."Doctors" ("Doctor_ID", "Doctor_Name", "Doctor_Surname", "Specialization") FROM stdin;
    MedKit       postgres    false    198   �(                 0    24660    Medical_Facility 
   TABLE DATA               �   COPY "MedKit"."Medical_Facility" ("Facility_ID", "Name", "Localization", "Doctors_Count", "Patients_Count", "Contact_Number") FROM stdin;
    MedKit       postgres    false    201   P)                 0    24630    Patient_Med_Data 
   TABLE DATA               u   COPY "MedKit"."Patient_Med_Data" ("Med_Data_ID", "Patient_ID", "Doctor_ID", "Date", "Medical_Procedure") FROM stdin;
    MedKit       postgres    false    200   �)                 0    24586    Patients 
   TABLE DATA               e   COPY "MedKit"."Patients" ("Patient_ID", "Patient_Name", "Patient_Surname", "Is_Insured") FROM stdin;
    MedKit       postgres    false    197   �)                 0    24668 
   Price_List 
   TABLE DATA               ?   COPY "MedKit"."Price_List" ("ID", "Name", "Price") FROM stdin;
    MedKit       postgres    false    202   p*       �
           2606    24617    Appointments Appointments_pkey 
   CONSTRAINT     p   ALTER TABLE ONLY "MedKit"."Appointments"
    ADD CONSTRAINT "Appointments_pkey" PRIMARY KEY ("Appointment_ID");
 N   ALTER TABLE ONLY "MedKit"."Appointments" DROP CONSTRAINT "Appointments_pkey";
       MedKit         postgres    false    199            �
           2606    24601    Doctors Doctors_pkey 
   CONSTRAINT     a   ALTER TABLE ONLY "MedKit"."Doctors"
    ADD CONSTRAINT "Doctors_pkey" PRIMARY KEY ("Doctor_ID");
 D   ALTER TABLE ONLY "MedKit"."Doctors" DROP CONSTRAINT "Doctors_pkey";
       MedKit         postgres    false    198            �
           2606    24667 &   Medical_Facility Medical_Facility_pkey 
   CONSTRAINT     u   ALTER TABLE ONLY "MedKit"."Medical_Facility"
    ADD CONSTRAINT "Medical_Facility_pkey" PRIMARY KEY ("Facility_ID");
 V   ALTER TABLE ONLY "MedKit"."Medical_Facility" DROP CONSTRAINT "Medical_Facility_pkey";
       MedKit         postgres    false    201            �
           2606    24637 &   Patient_Med_Data Patient_Med_Data_pkey 
   CONSTRAINT     u   ALTER TABLE ONLY "MedKit"."Patient_Med_Data"
    ADD CONSTRAINT "Patient_Med_Data_pkey" PRIMARY KEY ("Med_Data_ID");
 V   ALTER TABLE ONLY "MedKit"."Patient_Med_Data" DROP CONSTRAINT "Patient_Med_Data_pkey";
       MedKit         postgres    false    200            �
           2606    24593    Patients Patients_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY "MedKit"."Patients"
    ADD CONSTRAINT "Patients_pkey" PRIMARY KEY ("Patient_ID");
 F   ALTER TABLE ONLY "MedKit"."Patients" DROP CONSTRAINT "Patients_pkey";
       MedKit         postgres    false    197            �
           2606    24675    Price_List Price_List_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY "MedKit"."Price_List"
    ADD CONSTRAINT "Price_List_pkey" PRIMARY KEY ("ID");
 J   ALTER TABLE ONLY "MedKit"."Price_List" DROP CONSTRAINT "Price_List_pkey";
       MedKit         postgres    false    202            �
           1259    24623    fki_Doctor_ID    INDEX     S   CREATE INDEX "fki_Doctor_ID" ON "MedKit"."Appointments" USING btree ("Doctor_ID");
 %   DROP INDEX "MedKit"."fki_Doctor_ID";
       MedKit         postgres    false    199            �
           1259    24659    fki_Doctor_Med_Data_ID    INDEX     `   CREATE INDEX "fki_Doctor_Med_Data_ID" ON "MedKit"."Patient_Med_Data" USING btree ("Doctor_ID");
 .   DROP INDEX "MedKit"."fki_Doctor_Med_Data_ID";
       MedKit         postgres    false    200            �
           1259    24629    fki_Patient_ID    INDEX     U   CREATE INDEX "fki_Patient_ID" ON "MedKit"."Appointments" USING btree ("Patient_ID");
 &   DROP INDEX "MedKit"."fki_Patient_ID";
       MedKit         postgres    false    199            �
           1259    24648    fki_Patient_Med_Data_ID    INDEX     b   CREATE INDEX "fki_Patient_Med_Data_ID" ON "MedKit"."Patient_Med_Data" USING btree ("Patient_ID");
 /   DROP INDEX "MedKit"."fki_Patient_Med_Data_ID";
       MedKit         postgres    false    200            �
           2606    24618    Appointments Doctor_ID    FK CONSTRAINT     �   ALTER TABLE ONLY "MedKit"."Appointments"
    ADD CONSTRAINT "Doctor_ID" FOREIGN KEY ("Doctor_ID") REFERENCES "MedKit"."Doctors"("Doctor_ID") NOT VALID;
 F   ALTER TABLE ONLY "MedKit"."Appointments" DROP CONSTRAINT "Doctor_ID";
       MedKit       postgres    false    199    2698    198            �
           2606    24654 #   Patient_Med_Data Doctor_Med_Data_ID    FK CONSTRAINT     �   ALTER TABLE ONLY "MedKit"."Patient_Med_Data"
    ADD CONSTRAINT "Doctor_Med_Data_ID" FOREIGN KEY ("Doctor_ID") REFERENCES "MedKit"."Doctors"("Doctor_ID") NOT VALID;
 S   ALTER TABLE ONLY "MedKit"."Patient_Med_Data" DROP CONSTRAINT "Doctor_Med_Data_ID";
       MedKit       postgres    false    198    2698    200            �
           2606    24624    Appointments Patient_ID    FK CONSTRAINT     �   ALTER TABLE ONLY "MedKit"."Appointments"
    ADD CONSTRAINT "Patient_ID" FOREIGN KEY ("Patient_ID") REFERENCES "MedKit"."Patients"("Patient_ID") NOT VALID;
 G   ALTER TABLE ONLY "MedKit"."Appointments" DROP CONSTRAINT "Patient_ID";
       MedKit       postgres    false    197    2696    199            �
           2606    24638 $   Patient_Med_Data Patient_Med_Data_ID    FK CONSTRAINT     �   ALTER TABLE ONLY "MedKit"."Patient_Med_Data"
    ADD CONSTRAINT "Patient_Med_Data_ID" FOREIGN KEY ("Patient_ID") REFERENCES "MedKit"."Patients"("Patient_ID") NOT VALID;
 T   ALTER TABLE ONLY "MedKit"."Patient_Med_Data" DROP CONSTRAINT "Patient_Med_Data_ID";
       MedKit       postgres    false    200    2696    197               #   x�3�4202�5��54�4600�4�4����� >         M   x�3��M,�H,)I-*���.-JO���2����.I����%���g�psz%���r*�8=���@�1z\\\ �^�         W   x�3�tN��L�/��L��MM�LN̉��,K-�,.���qT(��MU(.)JM-QH�KQ(�/.I�OI�4��433�54bK�=... ��B         )   x�3�4B##]S]C#ΐ���"������T�=... ��         p   x��=�@D�#$1�U��i.����oſ���¼a��YB-�p�3:qc>���[�\�O�Q͒��3���qԔ|AC�%�N:���[^&�(���O\���"���[#�         �   x�%�K� ������OӍC��B�M�ܾ$ݎ�f��@�
�z�Rf���cTR_�h���1'�'u ���f��]C=�l��+p;48@�$��o�1�Ψ�`���IP.m?����k�R���&bو#��\Z��@����)�~T�<B     