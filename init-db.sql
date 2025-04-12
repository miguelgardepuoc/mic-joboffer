CREATE TABLE "job_offer" (
    "id" uuid NOT NULL,
    "job_title_id" uuid NOT NULL,
    "description" varchar NOT NULL,
    "min_salary" numeric NOT NULL,
    "max_salary" numeric NOT NULL,
    "remote" smallint NOT NULL,
    "requirement" varchar NOT NULL,
    "photo" bytea NOT NULL,
    "is_active" boolean NOT NULL,
    "created_by" varchar NOT NULL,
    "created_at" date NOT NULL,
    "last_modified_by" varchar,
    "last_modified_at" date,
    PRIMARY KEY ("id")
);

--CREATE TABLE "candidate" (
--    "id" uuid NOT NULL,
--    "job_offer_id" uuid NOT NULL,
--    "status" enum NOT NULL,
--    "personal_email" bigint NOT NULL,
--    "cv" bigint NOT NULL,
--    "name" varchar,
--    "surname" varchar,
--    "phone_number" bigint,
--    "created_by" varchar NOT NULL,
--    "created_at" date NOT NULL,
--    "last_modified_by" varchar,
--    "last_modified_at" date,
--    PRIMARY KEY ("id")
--);

--ALTER TABLE "job_offer"
--ADD CONSTRAINT "fk_job_offer_id_candidate_job_offer_id" FOREIGN KEY("id") REFERENCES "candidate"("job_offer_id");

INSERT INTO "job_offer" (
    "id",
    "job_title_id",
    "description",
    "min_salary",
    "max_salary",
    "remote",
    "requirement",
    "photo",
    "is_active",
    "created_by",
    "created_at",
    "last_modified_by",
    "last_modified_at"
)
VALUES (
    '3f3a01b3-d6d7-41b3-b6fc-0a87d8d431b7', -- id (UUID)
    'd8f90d3f-b6a9-4c45-9f4f-951f1d1b9571', -- job_title_id (UUID)
    'Software Engineer with focus on backend development', -- description (varchar)
    70000, -- min_salary (numeric)
    80000, -- max_salary (numeric)
    50, -- remote (smallint) - Representing 50% remote
    'Experience in Java, Spring Boot, REST APIs, and cloud services required', -- requirement (varchar)
   decode('89504e470d0a1a0a0000000d4948445200000010000000100806000000c8d43fa40000000467414d410000b18f0f970000000967414d410000b18f0f970000000a49444154789c8dd0cb0e8c3800c4c5efbefffe', 'hex'), -- photo (bytea) (Binary data, replace with actual content)
    true, -- is_active (boolean)
    'admin', -- created_by (varchar)
    '2025-04-06', -- created_at (date)
    'admin', -- last_modified_by (varchar)
    '2025-04-06' -- last_modified_at (date)
),(
    '3f3a01b3-d6d7-41b3-b6fc-0a87d8d431b8', -- id (UUID)
    'bf3e2b6b-d2e9-409f-97b3-b36ec0b1a90e', -- job_title_id (UUID)
    'Software Engineer with focus on backend development', -- description (varchar)
    30000, -- min_salary (numeric)
    35000, -- max_salary (numeric)
    50, -- remote (smallint) - Representing 50% remote
    'Experience in Java, Spring Boot, REST APIs, and cloud services required', -- requirement (varchar)
   decode('89504e470d0a1a0a0000000d4948445200000010000000100806000000c8d43fa40000000467414d410000b18f0f970000000967414d410000b18f0f970000000a49444154789c8dd0cb0e8c3800c4c5efbefffe', 'hex'), -- photo (bytea) (Binary data, replace with actual content)
    true, -- is_active (boolean)
    'admin', -- created_by (varchar)
    '2025-04-06', -- created_at (date)
    'admin', -- last_modified_by (varchar)
    '2025-04-06' -- last_modified_at (date)
),(
    '3f3a01b3-d6d7-41b3-b6fc-0a87d8d431b9', -- id (UUID)
    'c7924409-7eaf-4022-b0b3-9b8a78a0bb7d', -- job_title_id (UUID)
    'Software Engineer with focus on backend development', -- description (varchar)
    45000, -- min_salary (numeric)
    55000, -- max_salary (numeric)
    50, -- remote (smallint) - Representing 50% remote
    'Experience in Java, Spring Boot, REST APIs, and cloud services required', -- requirement (varchar)
   decode('89504e470d0a1a0a0000000d4948445200000010000000100806000000c8d43fa40000000467414d410000b18f0f970000000967414d410000b18f0f970000000a49444154789c8dd0cb0e8c3800c4c5efbefffe', 'hex'), -- photo (bytea) (Binary data, replace with actual content)
    true, -- is_active (boolean)
    'admin', -- created_by (varchar)
    '2025-04-06', -- created_at (date)
    'admin', -- last_modified_by (varchar)
    '2025-04-06' -- last_modified_at (date)
),(
    '3f3a01b3-d6d7-41b3-b6fc-0a87d8d431b2', -- id (UUID)
    '098bd9b4-8b57-4a18-bbde-9e6d1d678cfa', -- job_title_id (UUID)
    'Software Engineer with focus on backend development', -- description (varchar)
    30000, -- min_salary (numeric)
    35000, -- max_salary (numeric)
    50, -- remote (smallint) - Representing 50% remote
    'Experience in Java, Spring Boot, REST APIs, and cloud services required', -- requirement (varchar)
   decode('89504e470d0a1a0a0000000d4948445200000010000000100806000000c8d43fa40000000467414d410000b18f0f970000000967414d410000b18f0f970000000a49444154789c8dd0cb0e8c3800c4c5efbefffe', 'hex'), -- photo (bytea) (Binary data, replace with actual content)
    true, -- is_active (boolean)
    'admin', -- created_by (varchar)
    '2025-04-06', -- created_at (date)
    'admin', -- last_modified_by (varchar)
    '2025-04-06' -- last_modified_at (date)
),(
    '3f3a01b3-d6d7-41b3-b6fc-0a87d8d431b4', -- id (UUID)
    'dbf1a11c-f7f5-4b9f-98d4-e83c72ab1c57', -- job_title_id (UUID)
    'Software Engineer with focus on backend development', -- description (varchar)
    50000, -- min_salary (numeric)
    55000, -- max_salary (numeric)
    50, -- remote (smallint) - Representing 50% remote
    'Experience in Java, Spring Boot, REST APIs, and cloud services required', -- requirement (varchar)
   decode('89504e470d0a1a0a0000000d4948445200000010000000100806000000c8d43fa40000000467414d410000b18f0f970000000967414d410000b18f0f970000000a49444154789c8dd0cb0e8c3800c4c5efbefffe', 'hex'), -- photo (bytea) (Binary data, replace with actual content)
    true, -- is_active (boolean)
    'admin', -- created_by (varchar)
    '2025-04-06', -- created_at (date)
    'admin', -- last_modified_by (varchar)
    '2025-04-06' -- last_modified_at (date)
);
