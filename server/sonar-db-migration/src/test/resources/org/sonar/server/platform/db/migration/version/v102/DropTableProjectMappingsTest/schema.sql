CREATE TABLE "PROJECT_MAPPINGS"(
    "UUID" CHARACTER VARYING(40) NOT NULL,
    "KEY_TYPE" CHARACTER VARYING(200) NOT NULL,
    "KEE" CHARACTER VARYING(4000) NOT NULL,
    "PROJECT_UUID" CHARACTER VARYING(40) NOT NULL,
    "CREATED_AT" BIGINT NOT NULL
);
ALTER TABLE "PROJECT_MAPPINGS" ADD CONSTRAINT "PK_PROJECT_MAPPINGS" PRIMARY KEY("UUID");
CREATE UNIQUE INDEX "KEY_TYPE_KEE" ON "PROJECT_MAPPINGS"("KEY_TYPE" NULLS FIRST, "KEE" NULLS FIRST);
CREATE INDEX "PROJECT_UUID" ON "PROJECT_MAPPINGS"("PROJECT_UUID" NULLS FIRST);
