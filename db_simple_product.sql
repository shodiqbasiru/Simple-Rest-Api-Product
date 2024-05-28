-- -------------------------------------------------------------
-- -------------------------------------------------------------
-- TablePlus 1.1.8
--
-- https://tableplus.com/
--
-- Database: postgres
-- Generation Time: 2024-05-29 03:01:19.237731
-- -------------------------------------------------------------

-- This script only contains the table creation statements and does not fully represent the table in database. It's still missing: indices, triggers. Do not use it as backup.

-- Table Definition
CREATE TABLE "public"."m_image" (
    "id" varchar NOT NULL,
    "content_type" varchar NOT NULL,
    "name" varchar NOT NULL,
    "path" varchar NOT NULL,
    "size" int8 NOT NULL,
    PRIMARY KEY ("id")
);

-- This script only contains the table creation statements and does not fully represent the table in database. It's still missing: indices, triggers. Do not use it as backup.

-- Table Definition
CREATE TABLE "public"."m_product" (
    "id" varchar NOT NULL,
    "created_at" timestamp,
    "price" int4 NOT NULL,
    "product_name" varchar NOT NULL,
    "qty" int4 NOT NULL,
    "sku_code" varchar NOT NULL,
    "image_id" varchar,
    PRIMARY KEY ("id")
);

-- This script only contains the table creation statements and does not fully represent the table in database. It's still missing: indices, triggers. Do not use it as backup.

-- Table Definition
CREATE TABLE "public"."m_user" (
    "id" varchar NOT NULL,
    "email" varchar NOT NULL,
    "is_enable" bool,
    "name" varchar NOT NULL,
    "password" varchar NOT NULL,
    "phone_number" varchar NOT NULL,
    PRIMARY KEY ("id")
);

INSERT INTO "public"."m_image" ("id","content_type","name","path","size") VALUES 
('3534058a-469d-4844-a7e0-641f9de05683','image/jpeg','1716816968030_bembeng.jpg','/home/shodiqbasiru/Documents/ngoding/company_test/images/product/1716816968030_bembeng.jpg',149246),
('613f7807-37ae-45e0-a51f-6bece695c273','image/jpeg','1716817148009_kacang garuda.jpeg','/home/shodiqbasiru/Documents/ngoding/company_test/images/product/1716817148009_kacang garuda.jpeg',10452),
('5b0e9b83-ba22-4425-a0b2-23e15582e6bb','image/jpeg','1716817272379_kacang garuda.jpeg','/home/shodiqbasiru/Documents/ngoding/company_test/images/product/1716817272379_kacang garuda.jpeg',10452),
('c4baf5c1-ce36-4e4f-8a1a-31a039d6eafa','image/jpeg','1716817309404_kacang garuda.jpeg','/home/shodiqbasiru/Documents/ngoding/company_test/images/product/1716817309404_kacang garuda.jpeg',10452),
('ce680c5a-b1a8-4fa6-ba0c-bd23527bcc55','image/jpeg','1716817364655_bembeng.jpg','/home/shodiqbasiru/Documents/ngoding/company_test/images/product/1716817364655_bembeng.jpg',149246),
('70b9bd68-c209-46dc-8cd1-61211467c04f','image/jpeg','1716817456919_mountea.jpeg','/home/shodiqbasiru/Documents/ngoding/company_test/images/product/1716817456919_mountea.jpeg',16877),
('d1b74c6c-2610-4cd4-aaca-9e3410a41b63','image/jpeg','1716817649870_mountea.jpeg','/home/shodiqbasiru/Documents/ngoding/company_test/images/product/1716817649870_mountea.jpeg',16877),
('c991d637-1592-46c7-8723-688b6e77f2af','image/jpeg','1716828564791_ale ale.jpeg','/home/shodiqbasiru/Documents/ngoding/company_test/images/product/1716828564791_ale ale.jpeg',11964),
('1e64bff3-c715-4f80-ba85-be21233ba9be','image/jpeg','1716829185366_bibit ikan cupang.jpeg','/home/shodiqbasiru/Documents/ngoding/company_test/images/product/1716829185366_bibit ikan cupang.jpeg',14766),
('af9fec7c-0cd6-4fce-bb27-0a0c49bf2687','image/jpeg','1716829211794_bebek.jpg','/home/shodiqbasiru/Documents/ngoding/company_test/images/product/1716829211794_bebek.jpg',366567),
('c3560fb4-1023-4f19-8fcf-4be61b430d2d','image/jpeg','1716832112165_susu kurma.jpg','/home/shodiqbasiru/Documents/ngoding/company_test/images/product/1716832112165_susu kurma.jpg',701418),
('55f98eea-d428-4557-b7df-16afef377851','image/jpeg','1716833462005_susu kurma.jpg','/home/shodiqbasiru/Documents/ngoding/company_test/images/product/1716833462005_susu kurma.jpg',701418),
('90065a8c-bf6f-48f3-8469-bc7c6bc25b06','image/jpeg','1716836375772_ale ale.jpeg','/home/shodiqbasiru/Documents/ngoding/company_test/images/product/1716836375772_ale ale.jpeg',11964),
('b719aaec-9f10-4605-95df-9b13838bbbbc','image/jpeg','1716870875026_bembeng.jpg','/home/shodiqbasiru/Documents/ngoding/company_test/images/product/1716870875026_bembeng.jpg',149246),
('22d19431-b580-45cb-8b68-74e6ecb6883c','image/jpeg','1716871021328_kerbau-pampangan.jpg','/home/shodiqbasiru/Documents/ngoding/company_test/images/product/1716871021328_kerbau-pampangan.jpg',302285),
('5498a7e8-17b5-4201-970d-6425d12c0d83','image/jpeg','1716871286424_kacang garuda.jpeg','/home/shodiqbasiru/Documents/ngoding/company_test/images/product/1716871286424_kacang garuda.jpeg',10452),
('1e72ff70-91c4-4dfd-b485-f8a8cb9c14a8','image/jpeg','1716877745821_muffin.jpeg','/home/shodiqbasiru/Documents/ngoding/company_test/images/product/1716877745821_muffin.jpeg',10905),
('c6e129d2-2791-4d01-b8aa-3d32bca4b323','image/jpeg','1716877863603_bon cabe.jpeg','/home/shodiqbasiru/Documents/ngoding/company_test/images/product/1716877863603_bon cabe.jpeg',17277),
('3378509d-adcd-4190-99a3-5329e283013a','image/jpeg','1716877897791_mountea.jpeg','/home/shodiqbasiru/Documents/ngoding/company_test/images/product/1716877897791_mountea.jpeg',16877),
('ebe7caee-13d6-4784-93d5-e9f2b48e7aa8','image/jpeg','1716879137510_susu kurma.jpg','/home/shodiqbasiru/Documents/ngoding/company_test/images/product/1716879137510_susu kurma.jpg',701418),
('562352cc-d29d-4db2-afbc-770383364ef3','image/jpeg','1716879157926_sapi limosin.jpg','/home/shodiqbasiru/Documents/ngoding/company_test/images/product/1716879157926_sapi limosin.jpg',170551),
('dc8b774c-73e5-498c-8b93-7f2827c53961','image/jpeg','1716879700411_bembeng.jpg','/home/shodiqbasiru/Documents/ngoding/company_test/images/product/1716879700411_bembeng.jpg',149246),
('a10085d9-c3b3-488e-9f47-de264383d809','image/jpeg','1716892982276_bon cabe.jpeg','/home/shodiqbasiru/Documents/ngoding/company_test/images/product/1716892982276_bon cabe.jpeg',17277);

INSERT INTO "public"."m_product" ("id","created_at","price","product_name","qty","sku_code","image_id") VALUES 
('7de43e5d-c553-4b04-a0eb-f4cd8ea9543b','2024-05-28 11:41:26.433',1000,'Kacang Garuda',120,'sku 22324325ew','5498a7e8-17b5-4201-970d-6425d12c0d83'),
('6c261da8-052c-4a12-9e17-c23912f0b771','2024-05-28 13:27:22.76',5000,'Muffin',10,'sku 22324','1e72ff70-91c4-4dfd-b485-f8a8cb9c14a8'),
('e8b647d7-a2a1-4e26-b43f-25c0359724aa','2024-05-28 11:45:53.83',1000,'Mountea',50,'sku 223243421','3378509d-adcd-4190-99a3-5329e283013a'),
('16a27d17-20f6-4af3-92bb-a9f08996abe3','2024-05-28 13:52:17.52',12000,'susu kurma',21,'sku 22324','ebe7caee-13d6-4784-93d5-e9f2b48e7aa8'),
('2999b546-d6a3-4524-8663-3f7af8cfb5ab','2024-05-28 14:01:40.448',2500,'Bembeng',30,'SKU_g0a02hab82919023','dc8b774c-73e5-498c-8b93-7f2827c53961'),
('c19866d7-cfe6-4405-8428-4b59349e3ab7','2024-05-28 17:42:30.335',8000,'Bon Cabe Makaroni',50,'SKU_25a423ga80716203','a10085d9-c3b3-488e-9f47-de264383d809');

INSERT INTO "public"."m_user" ("id","email","is_enable","name","password","phone_number") VALUES 
('7095815c-5854-4c6d-8b32-a335b9ea4a37','fulan@gmail.com','TRUE','Fulan','$2a$10$0YU/C0.f9r2CN7ZX8ps.j.WnCfBgoje48OsWrtq75Ab8y3FmqzETW','081234456789'),
('66375e6b-10da-4a86-9ce9-cb37505cf864','gokil@gmail.com','TRUE','Muhammad gokil','$2a$10$ltzWpxzKeuyaHoBKzpVPiONWdXUVwsWVYu8nNnFDqp3GoUfXALgkS','08123456789'),
('b70825a2-76ce-4428-b30a-7119bdc6a2d3','calala@gmail.com','TRUE','calala','$2a$10$ZdgsOfqmZcFDvQsum2lFi.uPlMR.sIaGEcA8N2XXkwgTk.NZjnzm6','08123454321'),
('e2f870c0-40cf-4a71-a81c-40a0dc0df3bb','cayacaya@gmail.com','TRUE','caya caya','$2a$10$gjORWAn1G4hK4.ZNBhTzoOLrqMLI4YSHr3VhMrWdsohAfN71DDhlS','081234321223'),
('f445b92e-a248-4699-8fdd-fcc72b90c612','mayayo@gmail.com','TRUE','mayayo','$2a$10$M9Y.WOstnQH.OX9lMVIaruflDFCQm0SisZ8uWxYfEDvJUVZaOaE9u','08524567654'),
('616992b4-af00-49e9-8408-4f1ccdb8dd45','firmansyah@gmail.com','TRUE','firman','$2a$10$cOoqZxGDyNKpUYUSNdbBa.Iv143NJ0SwyqamVsBkIRHklsE3sBEoW','81394603245'),
('950465b5-e75e-48a4-af8a-450def5c4fbe','basiru@gmail.com','TRUE','Basiru','$2a$10$pMvEI3HWyq5oEFf8ClUUie1s2DM0307qmlN9PTugbQqqqYUkf0ohC','0833214423'),
('f4bd9f6c-bb9d-448e-b457-a2de92a32854','jaya@gmail.com','TRUE','jaya','$2a$10$aGEAssPucqkkNiemFJez4OPGYjkqyGzEZC/quG20mnzUXUiDuP2LS','08129876543');

