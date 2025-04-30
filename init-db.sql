CREATE TYPE status_enum AS ENUM ('APPLIED', 'INTERVIEWING', 'HIRED', 'REJECTED');

CREATE TABLE "job_offer" (
    "id" uuid NOT NULL,
    "job_title_id" uuid NOT NULL,
    "description" TEXT NOT NULL,
    "min_salary" numeric NOT NULL,
    "max_salary" numeric NOT NULL,
    "remote" smallint NOT NULL,
    "requirement" TEXT NOT NULL,
    "is_active" boolean NOT NULL,
    "created_by" varchar(255) NOT NULL,
    "created_at" timestamp NOT NULL,
    "last_modified_by" varchar(255),
    "last_modified_at" timestamp,
    PRIMARY KEY ("id")
);

CREATE TABLE "candidate" (
    "id" uuid NOT NULL,
    "job_offer_id" uuid NOT NULL,
    "status" status_enum NOT NULL,
    "personal_email" varchar(255) NOT NULL,
    "cv_filename" varchar(255) NOT NULL,
    "name" varchar(255),
    "surname" varchar(255),
    "phone_number" varchar(20),
    "created_by" varchar(255) NOT NULL,
    "created_at" timestamp NOT NULL,
    "last_modified_by" varchar(255),
    "last_modified_at" timestamp,
    PRIMARY KEY ("id"),
    FOREIGN KEY ("job_offer_id") REFERENCES "job_offer"("id")
);

ALTER TABLE "candidate"
ADD CONSTRAINT "fk_job_offer_id_candidate_job_offer_id" FOREIGN KEY("job_offer_id") REFERENCES "job_offer"("id");

INSERT INTO "job_offer" (
    "id",
    "job_title_id",
    "description",
    "min_salary",
    "max_salary",
    "remote",
    "requirement",
    "is_active",
    "created_by",
    "created_at",
    "last_modified_by",
    "last_modified_at"
)
VALUES (
    '3f3a01b3-d6d7-41b3-b6fc-0a87d8d431b7',
    'd8f90d3f-b6a9-4c45-9f4f-951f1d1b9571',
    'Estamos buscando un Principal Software Engineer para liderar equipos de desarrollo en proyectos innovadores. Este puesto es clave para la arquitectura de nuestras soluciones tecnológicas, asegurando que se cumplan los más altos estándares de calidad. Como líder técnico, colaborarás estrechamente con otros departamentos, influirás en la dirección técnica de la empresa y mentorizarás a ingenieros junior y senior. Es fundamental tener experiencia en proyectos grandes y en liderar equipos de desarrollo.',
    70000,
    80000,
    0,
    'Experiencia significativa como ingeniero de software; dominio de arquitecturas de microservicios; conocimientos profundos en lenguajes como Java, C# o Python; habilidades en liderazgo de equipos y toma de decisiones técnicas; experiencia en la implementación de soluciones en la nube (AWS, Azure, GCP); excelente capacidad para comunicar ideas técnicas complejas',
    true,
    'admin',
    '2025-04-06',
    null,
    null
),(
    '3f3a01b3-d6d7-41b3-b6fc-0a87d8d431b8',
    'bf3e2b6b-d2e9-409f-97b3-b36ec0b1a90e',
    'Buscamos un Junior Backend Developer para incorporarse a nuestro equipo de desarrollo en una empresa tecnológica en pleno crecimiento. Participarás en el diseño, implementación y mantenimiento de servicios backend, colaborando con equipos multidisciplinares para crear soluciones escalables y eficientes. Trabajarás bajo la supervisión de desarrolladores senior, adquiriendo experiencia en entornos ágiles y utilizando herramientas y tecnologías modernas. Si te apasiona la programación y quieres crecer profesionalmente, esta es tu oportunidad.',
    30000,
    35000,
    75,
    'Conocimientos en Node.js, Python o Java; comprensión básica de bases de datos relacionales (PostgreSQL, MySQL); nociones de diseño y consumo de APIs REST; experiencia con Git y control de versiones; interés en metodologías ágiles (Scrum, Kanban); capacidad para resolver problemas y aprender rápidamente; buen nivel de comunicación y trabajo en equipo',
    true,
    'admin',
    '2025-04-06',
    null,
    null
),(
    '3f3a01b3-d6d7-41b3-b6fc-0a87d8d431b9',
    'c7924409-7eaf-4022-b0b3-9b8a78a0bb7d',
    'Buscamos un Data Engineer para unirse a nuestro equipo de datos. Serás responsable de diseñar y mantener las arquitecturas de datos, creando soluciones escalables y de alta disponibilidad. Estarás a cargo de la integración de datos desde múltiples fuentes, optimizando flujos de trabajo y trabajando estrechamente con científicos de datos para mejorar la calidad de los datos y la toma de decisiones.',
    45000,
    55000,
    50,
    'Experiencia en bases de datos relacionales y no relacionales; dominio de herramientas como Apache Hadoop, Spark o Kafka; conocimientos en ETL; programación en Python o Scala; experiencia con herramientas de orquestación de datos (Airflow, Prefect); capacidad para optimizar procesos y trabajo en equipo',
    true,
    'admin',
    '2025-04-06',
    null,
    null
),(
    '3f3a01b3-d6d7-41b3-b6fc-0a87d8d431b2',
    '098bd9b4-8b57-4a18-bbde-9e6d1d678cfa',
    '¿Te apasiona el desarrollo frontend? Únete a nuestro equipo como Junior Frontend Developer y ayuda a construir aplicaciones web modernas y dinámicas. Estarás trabajando con tecnologías como HTML, CSS y JavaScript, y aprenderás a utilizar frameworks como React o Vue.js. Este es un rol ideal para alguien con ganas de crecer profesionalmente y trabajar en proyectos de alto impacto.',
    30000,
    35000,
    50,
    'Conocimientos básicos en HTML, CSS y JavaScript; experiencia con algún framework de frontend (React, Vue.js, Angular); familiaridad con herramientas de control de versiones (Git); capacidad para trabajar en equipo; ganas de aprender y desarrollarse profesionalmente',
    true,
    'admin',
    '2025-04-06',
    null,
    null
),(
    '3f3a01b3-d6d7-41b3-b6fc-0a87d8d431b4',
    'dbf1a11c-f7f5-4b9f-98d4-e83c72ab1c57',
    'Estamos buscando un Product Manager experimentado para liderar la estrategia de nuestros productos. Trabajarás estrechamente con equipos de desarrollo, marketing y ventas para asegurar que las funcionalidades del producto cumplan con las expectativas del mercado. Tendrás la oportunidad de definir la visión del producto, realizar investigaciones de mercado y gestionar el ciclo de vida del producto.',
    50000,
    55000,
    50,
    'Experiencia en gestión de productos tecnológicos; habilidades en la creación de hojas de ruta y análisis de mercado; dominio de metodologías ágiles (Scrum, Kanban); excelente capacidad de comunicación y trabajo en equipo; experiencia en la colaboración con equipos técnicos y no técnicos',
    true,
    'admin',
    '2025-04-06',
    null,
    null
),(
     '3f3a01b3-d6d8-41b3-b6fc-0a87d8d431b5',
     'f6180285-9f66-493b-b629-0892118c6d75',
     'Buscamos un Database Software Engineer para diseñar y optimizar sistemas de bases de datos de alto rendimiento. Trabajarás en la creación de soluciones escalables para almacenar y acceder a grandes volúmenes de datos. Este puesto requiere experiencia con bases de datos relacionales y no relacionales, así como conocimientos en optimización de consultas y arquitecturas distribuidas.',
     40000,
     50000,
     50,
     'Experiencia en bases de datos SQL y NoSQL; conocimientos en diseño de esquemas de bases de datos; optimización de rendimiento; familiaridad con tecnologías de almacenamiento distribuido (Cassandra, MongoDB); experiencia con herramientas de automatización de bases de datos y buenas prácticas de seguridad',
     true,
     'admin',
     '2025-04-06',
     null,
     null
 ),
 (
     '3f3a01b3-d6d7-41b3-b6fc-0a87d8d431d1',
     '3c01ef99-9c73-4bac-a27f-24dc5089df16',
     'Estamos buscando un Platform Engineer para desarrollar y mantener la infraestructura y herramientas que soportan nuestros productos y servicios. Trabajarás en la creación de plataformas escalables y seguras, así como en la automatización de procesos. Este rol requiere experiencia trabajando con entornos en la nube y una mentalidad orientada a la mejora continua.',
     70000,
     90000,
     100,
     'Experiencia con plataformas en la nube (AWS, GCP, Azure); conocimientos de contenedores y orquestación (Docker, Kubernetes); habilidades de scripting (Python, Bash); experiencia en la automatización de infraestructura (Terraform, Ansible); comprensión de la seguridad en la infraestructura',
     true,
     'admin',
     '2025-04-06',
     null,
     null
 ),
 (
     '3f3a01b3-d6d7-41b3-b6fc-0a87d8d431c4',
     '23f8785e-efce-48fc-9dcd-0141ea777fed',
     'Buscamos un Senior Machine Learning Engineer con experiencia en la implementación de modelos predictivos y en el diseño de arquitecturas de aprendizaje automático. Este rol será clave en la creación de soluciones basadas en IA que impacten directamente en los productos de la empresa. Necesitamos a alguien con conocimientos avanzados en deep learning, NLP y visión computacional.',
     100000,
     130000,
     75,
     'Experiencia en machine learning y deep learning; dominio de frameworks como TensorFlow, PyTorch, o Keras; experiencia en el manejo de grandes volúmenes de datos; conocimientos avanzados en Python y R; capacidad para implementar y optimizar modelos de ML en producción',
     true,
     'admin',
     '2025-04-06',
     null,
     null
 ),
 (
     '3f3a01b3-d6d7-41b2-b6fc-0a87d8d431c5',
     'e513050a-a90a-4d3c-b820-113b9e098e52',
     'Estamos buscando un Tech Lead con sólida experiencia técnica y habilidades de liderazgo. Serás responsable de dirigir un equipo de desarrollo, coordinar el diseño y la implementación de soluciones de software, y asegurar la calidad del código. Este puesto requiere un enfoque en la entrega de valor mediante la colaboración entre equipos y la mejora continua de procesos.',
     80000,
     110000,
     100,
     'Experiencia como líder técnico o senior en equipos de desarrollo; conocimientos en arquitecturas de microservicios; dominio de lenguajes como Java, Python o C#; experiencia con metodologías ágiles; capacidad para mentorizar y guiar equipos técnicos; habilidades excepcionales de comunicación',
     true,
     'admin',
     '2025-04-06',
     null,
     null
 );
