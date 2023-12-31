CREATE TABLE "GROUP_ROLES"(
    "UUID" CHARACTER VARYING(40) NOT NULL,
    "ROLE" CHARACTER VARYING(64) NOT NULL,
    "ENTITY_UUID" CHARACTER VARYING(40),
    "GROUP_UUID" CHARACTER VARYING(40)
);
ALTER TABLE "GROUP_ROLES" ADD CONSTRAINT "PK_GROUP_ROLES" PRIMARY KEY("UUID");
CREATE UNIQUE INDEX "UNIQ_GROUP_ROLES" ON "GROUP_ROLES"("GROUP_UUID" NULLS FIRST, "ENTITY_UUID" NULLS FIRST, "ROLE" NULLS FIRST);