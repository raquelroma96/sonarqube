CREATE TABLE "USER_ROLES"(
    "UUID" CHARACTER VARYING(40) NOT NULL,
    "ROLE" CHARACTER VARYING(64) NOT NULL,
    "ENTITY_UUID" CHARACTER VARYING(40),
    "USER_UUID" CHARACTER VARYING(255)
);
ALTER TABLE "USER_ROLES" ADD CONSTRAINT "PK_USER_ROLES" PRIMARY KEY("UUID");
CREATE INDEX "USER_ROLES_USER" ON "USER_ROLES"("USER_UUID" NULLS FIRST);