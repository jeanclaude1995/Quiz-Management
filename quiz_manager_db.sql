PGDMP     3                    z           Demo    12.10    12.10     X           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            Y           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            Z           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            [           1262    16569    Demo    DATABASE     �   CREATE DATABASE "Demo" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_India.1252' LC_CTYPE = 'English_India.1252';
    DROP DATABASE "Demo";
                postgres    false            �            1259    17671    mcq    TABLE     C  CREATE TABLE public.mcq (
    sno character varying(255) NOT NULL,
    topic character varying(255),
    question character varying(500),
    answer character varying(700),
    choice2 character varying(255),
    choice3 character varying(255),
    choice4 character varying(255),
    description character varying(255)
);
    DROP TABLE public.mcq;
       public         heap    postgres    false            �            1259    25863    open_questions    TABLE     �   CREATE TABLE public.open_questions (
    sno character varying(255) NOT NULL,
    topic character varying(255),
    question character varying(500),
    answer character varying(700),
    description character varying(255)
);
 "   DROP TABLE public.open_questions;
       public         heap    postgres    false            �            1259    17668    students    TABLE     `   CREATE TABLE public.students (
    id character varying(30),
    name character varying(255)
);
    DROP TABLE public.students;
       public         heap    postgres    false            T          0    17671    mcq 
   TABLE DATA           c   COPY public.mcq (sno, topic, question, answer, choice2, choice3, choice4, description) FROM stdin;
    public          postgres    false    218   �       U          0    25863    open_questions 
   TABLE DATA           S   COPY public.open_questions (sno, topic, question, answer, description) FROM stdin;
    public          postgres    false    227   �       S          0    17668    students 
   TABLE DATA           ,   COPY public.students (id, name) FROM stdin;
    public          postgres    false    217   ~       �
           2606    25870     open_questions open_questions_pk 
   CONSTRAINT     _   ALTER TABLE ONLY public.open_questions
    ADD CONSTRAINT open_questions_pk PRIMARY KEY (sno);
 J   ALTER TABLE ONLY public.open_questions DROP CONSTRAINT open_questions_pk;
       public            postgres    false    227            �
           2606    17678    mcq quiz_pk 
   CONSTRAINT     J   ALTER TABLE ONLY public.mcq
    ADD CONSTRAINT quiz_pk PRIMARY KEY (sno);
 5   ALTER TABLE ONLY public.mcq DROP CONSTRAINT quiz_pk;
       public            postgres    false    218            T   !  x�}T�;�\��>��J�A\7���y�U@7F7/e�}[�Mہ���tf��Ǔ����{�9���f&�v��� �\Kh����:'��w���.� #��u9l�RXI*�q��Xh9do����޼��4����f�����~<���y�Λ��TGN%W��i4������:�VR��(O��%�0aa��&��&��kp� �eN��� �Z�P�:a"LQ���ד�:`,�ʨc��b#ث'O�R�j�-Q��]�:�-�٭��h�%-v$�\��n�g3ա���N�C��7������4e�t�����y5?婞�;��Y5ݮD��JOm|�c/&��uC6��B� �R���#��.6S��q��z������O�F����$���Fk� ��A��zp�pb����=O�r�����6X��0��KN�o$'�Q�8�r)�G�Hƍr��16�0ۨ�����
LU���Ql��Y8)�9�6N~�B���d��%��|<ou;�,bWh*�����+�q����Z}��$��$F��ї;ܼ��8�8]�T.��K:�sz�)9e���%�8����msj'��k#��z�{�G�%�I7�¶�%�����!��`kV6g�\`h�噓K�$�(��Sf �=ɱ̿(Q� �R 鵶"0��r�O��9:�^�T�����*�C��.+�b#ɔ�t~��LD��O�tyr��W�kT�_ܶ��Y�aA��G>�����wu�P�?��q���Tz �=�-�O�4@�O�Cv3�S��3��`�4���|��Z��)��      U   �  x���[��@��;��~�v�?-���"�����6]3i�K�d���tA�%��:�U��ۛ�7�c��8{:pT���,J\��LUT����3�(d�I����ݛR�pvW�����((��]h֐�ĔJ�d��aJlRG�g9��<I�72�Q�jCIS�R�d9t�N�&��9r݂�"��A:����i,�T�ٸwoY����{�����}��Rj${VY��ɶ�^Y�N1d�'t*���d�Xy@S
Y6�xeT?��kwP��?
VIp�B�]M��6���0��Z�L�$��[�SIa(�d����p1�6_���AV0�û{���n�=�S�N�i7J�`c�=zB��C0@K��F��u׽���l�V�!���[�U�R�0ȥ�*g��C�$���	��f���{��ڈ����ٷ�뺟km�      S   G   x�K�I,MIuH-�,I�K+�L�
RKR��`.WVjb�Cznbf�^r~.'��U����X�P	�s��qqq  '     