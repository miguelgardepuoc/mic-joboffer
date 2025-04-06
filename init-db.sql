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

CREATE TABLE "candidate" (
    "id" uuid NOT NULL,
    "job_offer_id" uuid NOT NULL,
    "status" enum NOT NULL,
    "personal_email" bigint NOT NULL,
    "cv" bigint NOT NULL,
    "name" varchar,
    "surname" varchar,
    "phone_number" bigint,
    "created_by" varchar NOT NULL,
    "created_at" date NOT NULL,
    "last_modified_by" varchar,
    "last_modified_at" date,
    PRIMARY KEY ("id")
);

ALTER TABLE "job_offer"
ADD CONSTRAINT "fk_job_offer_id_candidate_job_offer_id" FOREIGN KEY("id") REFERENCES "candidate"("job_offer_id");
