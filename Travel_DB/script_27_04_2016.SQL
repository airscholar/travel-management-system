--------------------------------------------------------
--  File created - Wednesday-April-27-2016   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Sequence NOTIFICATION_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "TRAVEL"."NOTIFICATION_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 NOCACHE  ORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SEQ_AIRLINE
--------------------------------------------------------

   CREATE SEQUENCE  "TRAVEL"."SEQ_AIRLINE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SEQ_INVOICES
--------------------------------------------------------

   CREATE SEQUENCE  "TRAVEL"."SEQ_INVOICES"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SEQ_INVOICE_ATTACHMENT
--------------------------------------------------------

   CREATE SEQUENCE  "TRAVEL"."SEQ_INVOICE_ATTACHMENT"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SEQ_PRODUCT_TYPE
--------------------------------------------------------

   CREATE SEQUENCE  "TRAVEL"."SEQ_PRODUCT_TYPE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SEQ_RATES
--------------------------------------------------------

   CREATE SEQUENCE  "TRAVEL"."SEQ_RATES"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SEQ_ROOM_TYPE
--------------------------------------------------------

   CREATE SEQUENCE  "TRAVEL"."SEQ_ROOM_TYPE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SEQ_SERVICE_TYPE
--------------------------------------------------------

   CREATE SEQUENCE  "TRAVEL"."SEQ_SERVICE_TYPE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SEQ_SUPPLIER
--------------------------------------------------------

   CREATE SEQUENCE  "TRAVEL"."SEQ_SUPPLIER"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SEQ_SUPPLIER_PRODUCT
--------------------------------------------------------

   CREATE SEQUENCE  "TRAVEL"."SEQ_SUPPLIER_PRODUCT"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SEQ_UPLOADED_INVOICE_FILE
--------------------------------------------------------

   CREATE SEQUENCE  "TRAVEL"."SEQ_UPLOADED_INVOICE_FILE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence TRAVELUSER_SCREENS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "TRAVEL"."TRAVELUSER_SCREENS_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 NOCACHE  ORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence TRAVELUSER_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "TRAVEL"."TRAVELUSER_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 NOCACHE  ORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Table AIRLINE
--------------------------------------------------------

  CREATE TABLE "TRAVEL"."AIRLINE" 
   (	"ID" NUMBER(22,0), 
	"NAME" VARCHAR2(300 BYTE), 
	"MODIFICATION_DATE" DATE, 
	"SYSTEM_USER" VARCHAR2(300 BYTE), 
	"STATUS" NUMBER(1,0), 
	"DESCRIPTION" VARCHAR2(500 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Table CATEGORY
--------------------------------------------------------

  CREATE TABLE "TRAVEL"."CATEGORY" 
   (	"CAT_ID" NUMBER(38,0), 
	"CATEGORY_NAME" VARCHAR2(200 BYTE), 
	"CATEGORY_ORDER" NUMBER(38,0)
   ) ;
--------------------------------------------------------
--  DDL for Table CONFIG
--------------------------------------------------------

  CREATE TABLE "TRAVEL"."CONFIG" 
   (	"CONFIG_ID" NUMBER(38,0), 
	"CONFIG_NAME" VARCHAR2(100 BYTE), 
	"CONFIG_VALUE" VARCHAR2(100 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Table INVOICES
--------------------------------------------------------

  CREATE TABLE "TRAVEL"."INVOICES" 
   (	"ID" NUMBER(22,0), 
	"INVOICE_NUMBER" VARCHAR2(100 BYTE), 
	"BOOKING_FILE_NUMBER" VARCHAR2(100 BYTE), 
	"DEPARTURE_DATE" DATE, 
	"ARRIVAL_DATE" DATE, 
	"EMPLOYEE_ID" VARCHAR2(100 BYTE), 
	"COST_CENTER" VARCHAR2(100 BYTE), 
	"COST_CENTER_DEPARTMENT" VARCHAR2(100 BYTE), 
	"PASSENGER_NAME" VARCHAR2(300 BYTE), 
	"SERVICE_TYPE" VARCHAR2(300 BYTE), 
	"SERVICE_DESC" VARCHAR2(300 BYTE), 
	"ROUTING" VARCHAR2(300 BYTE), 
	"INTER_DOM" VARCHAR2(100 BYTE), 
	"CHECK_IN" DATE, 
	"CHECK_OUT" DATE, 
	"NUMBER_OF_NIGHTS" NUMBER(10,0), 
	"NUMBER_OF_ROOMS" NUMBER(10,0), 
	"AIRLINE" VARCHAR2(300 BYTE), 
	"ROOM_TYPE" VARCHAR2(300 BYTE), 
	"SUPPLIER_NAME" VARCHAR2(300 BYTE), 
	"NET_AMOUNT" NUMBER(22,2), 
	"OPERATION_FEES" NUMBER(22,2), 
	"TOTAL_AMOUNT" NUMBER(22,2), 
	"TICKET_NO" VARCHAR2(300 BYTE), 
	"TRAVEL_FORM_NUMBER" VARCHAR2(300 BYTE), 
	"DESCRIPTION" VARCHAR2(300 BYTE), 
	"FROM" DATE, 
	"TO" DATE, 
	"UPLOADED_INVOICE_FILE_ID" NUMBER(22,0)
   ) ;
--------------------------------------------------------
--  DDL for Table INVOICE_ATTACHMENT
--------------------------------------------------------

  CREATE TABLE "TRAVEL"."INVOICE_ATTACHMENT" 
   (	"ID" NUMBER(22,0), 
	"NAME" VARCHAR2(300 BYTE), 
	"CONTENT" BLOB, 
	"TYPE" VARCHAR2(300 BYTE), 
	"MODIFICATION_DATE" DATE, 
	"SYSTEM_USER" VARCHAR2(300 BYTE), 
	"UPLOADED_INVOICE_FILE_ID" NUMBER(22,0)
   ) ;
--------------------------------------------------------
--  DDL for Table NOTIFICATION
--------------------------------------------------------

  CREATE TABLE "TRAVEL"."NOTIFICATION" 
   (	"ID" NUMBER, 
	"ATTACH_FILE" BLOB, 
	"SUBJECT" VARCHAR2(200 BYTE), 
	"TEMPLATENAME" VARCHAR2(50 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Table PRODUCT_TYPE
--------------------------------------------------------

  CREATE TABLE "TRAVEL"."PRODUCT_TYPE" 
   (	"ID" NUMBER(22,0), 
	"NAME" VARCHAR2(300 BYTE), 
	"MODIFICATION_DATE" DATE, 
	"SYSTEM_USER" VARCHAR2(300 BYTE), 
	"STATUS" NUMBER(1,0), 
	"DESCRIPTION" VARCHAR2(500 BYTE), 
	"SERVICE_ID" NUMBER(22,0)
   ) ;
--------------------------------------------------------
--  DDL for Table RATES
--------------------------------------------------------

  CREATE TABLE "TRAVEL"."RATES" 
   (	"ID" NUMBER(22,0), 
	"SUPPLIER_PRODUCT_ID" NUMBER(22,0), 
	"RATE" NUMBER(22,2), 
	"ROOM_TYPE_ID" NUMBER(22,0), 
	"AIRLINE_ID" NUMBER(22,0), 
	"YEAR" NUMBER(5,0), 
	"MODIFICATION_DATE" DATE, 
	"SYSTEM_USER" VARCHAR2(300 BYTE), 
	"STATUS" NUMBER(1,0), 
	"DESCRIPTION" VARCHAR2(500 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Table ROOM_TYPE
--------------------------------------------------------

  CREATE TABLE "TRAVEL"."ROOM_TYPE" 
   (	"ID" NUMBER(22,0), 
	"NAME" VARCHAR2(300 BYTE), 
	"MODIFICATION_DATE" DATE, 
	"SYSTEM_USER" VARCHAR2(300 BYTE), 
	"STATUS" NUMBER(1,0), 
	"DESCRIPTION" VARCHAR2(500 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Table SERVICE_TYPE
--------------------------------------------------------

  CREATE TABLE "TRAVEL"."SERVICE_TYPE" 
   (	"ID" NUMBER(22,0), 
	"NAME" VARCHAR2(300 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Table SUPPLIER
--------------------------------------------------------

  CREATE TABLE "TRAVEL"."SUPPLIER" 
   (	"ID" NUMBER(22,0), 
	"NAME" VARCHAR2(300 BYTE), 
	"MODIFICATION_DATE" DATE, 
	"SYSTEM_USER" VARCHAR2(300 BYTE), 
	"STATUS" NUMBER(1,0), 
	"DESCRIPTION" VARCHAR2(500 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Table SUPPLIER_PRODUCT
--------------------------------------------------------

  CREATE TABLE "TRAVEL"."SUPPLIER_PRODUCT" 
   (	"ID" NUMBER(22,0), 
	"SUPPLIER_ID" NUMBER(22,0), 
	"PRODUCT_ID" NUMBER(22,0), 
	"MODIFICATION_DATE" DATE, 
	"SYSTEM_USER" VARCHAR2(300 BYTE), 
	"STATUS" NUMBER(1,0), 
	"DESCRIPTION" VARCHAR2(500 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Table SYSTEM_SCREENS
--------------------------------------------------------

  CREATE TABLE "TRAVEL"."SYSTEM_SCREENS" 
   (	"SCREEN_ID" NUMBER(22,0), 
	"SCREEN_NAME" VARCHAR2(100 BYTE), 
	"SCREEN_URL" VARCHAR2(256 BYTE), 
	"CATEGORY_ID" NUMBER(*,0), 
	"SCREEN_ORDER" NUMBER(32,0)
   ) ;
--------------------------------------------------------
--  DDL for Table TMS_USER
--------------------------------------------------------

  CREATE TABLE "TRAVEL"."TMS_USER" 
   (	"EMPLOYEE_ID" NUMBER(22,0), 
	"FULLNAME" VARCHAR2(200 BYTE), 
	"ACCOUNTNAME" VARCHAR2(200 BYTE), 
	"EMAIL" VARCHAR2(100 BYTE), 
	"PHONE" VARCHAR2(20 BYTE), 
	"DEPARTMENT" VARCHAR2(100 BYTE), 
	"COSTCENTER" VARCHAR2(50 BYTE), 
	"COMMENTS" VARCHAR2(300 BYTE), 
	"EMPLOYEE_TYPE" VARCHAR2(25 BYTE), 
	"STATUS" NUMBER(1,0), 
	"STAFFID" NUMBER(22,0)
   ) ;
--------------------------------------------------------
--  DDL for Table UPLOADED_INVOICE_FILE
--------------------------------------------------------

  CREATE TABLE "TRAVEL"."UPLOADED_INVOICE_FILE" 
   (	"ID" NUMBER(22,0), 
	"NAME" VARCHAR2(300 BYTE), 
	"CONTENT" BLOB, 
	"TYPE" VARCHAR2(300 BYTE), 
	"MODIFICATION_DATE" DATE, 
	"SYSTEM_USER" VARCHAR2(300 BYTE), 
	"APPROVED" NUMBER(1,0), 
	"REJECTION_REASON" VARCHAR2(300 BYTE), 
	"REJECTING_USER" VARCHAR2(300 BYTE), 
	"ACTION_TAKEN_DATE" DATE
   ) ;
--------------------------------------------------------
--  DDL for Table USERSCREENS
--------------------------------------------------------

  CREATE TABLE "TRAVEL"."USERSCREENS" 
   (	"EMPLOYEE_ID" NUMBER(22,0), 
	"SCREEN_ID" NUMBER, 
	"ADDMODE" NUMBER(1,0), 
	"EDITMODE" NUMBER(1,0), 
	"DELETEMODE" NUMBER(1,0), 
	"ID" NUMBER(*,0), 
	"VIEWMODE" NUMBER(1,0)
   ) ;
REM INSERTING into TRAVEL.AIRLINE
SET DEFINE OFF;
REM INSERTING into TRAVEL.CATEGORY
SET DEFINE OFF;
REM INSERTING into TRAVEL.CONFIG
SET DEFINE OFF;
Insert into TRAVEL.CONFIG (CONFIG_ID,CONFIG_NAME,CONFIG_VALUE) values (1,'LDAP_USERS_SB','OU=DPT,DC=vfdev,DC=com');
Insert into TRAVEL.CONFIG (CONFIG_ID,CONFIG_NAME,CONFIG_VALUE) values (2,'LDAP_URL','ldap://localhost:389');
Insert into TRAVEL.CONFIG (CONFIG_ID,CONFIG_NAME,CONFIG_VALUE) values (3,'LDAP_DOMAIN_NAME','vfdev.com');
Insert into TRAVEL.CONFIG (CONFIG_ID,CONFIG_NAME,CONFIG_VALUE) values (4,'MAIL_PORT','587');
Insert into TRAVEL.CONFIG (CONFIG_ID,CONFIG_NAME,CONFIG_VALUE) values (5,'MAIL_HOST','smtp.gmail.com');
Insert into TRAVEL.CONFIG (CONFIG_ID,CONFIG_NAME,CONFIG_VALUE) values (6,'MAIL_FROM','vodafoneincidentmanagement@gmail.com');
Insert into TRAVEL.CONFIG (CONFIG_ID,CONFIG_NAME,CONFIG_VALUE) values (7,'MAIL_AUTH','true');
Insert into TRAVEL.CONFIG (CONFIG_ID,CONFIG_NAME,CONFIG_VALUE) values (8,'MAIL_USERNAME','ahmedmis2014@gmail.com');
Insert into TRAVEL.CONFIG (CONFIG_ID,CONFIG_NAME,CONFIG_VALUE) values (9,'MAIL_PASSWORD','/pxYak0n00PwLIjPLy4n3g==');
Insert into TRAVEL.CONFIG (CONFIG_ID,CONFIG_NAME,CONFIG_VALUE) values (10,'MAIL_DEBUG','true');
Insert into TRAVEL.CONFIG (CONFIG_ID,CONFIG_NAME,CONFIG_VALUE) values (11,'SYSTEM_URL','http://localhost:7001/IncidentManagment/login.xhtml');
Insert into TRAVEL.CONFIG (CONFIG_ID,CONFIG_NAME,CONFIG_VALUE) values (12,'SYS_ADMIN','admin');
Insert into TRAVEL.CONFIG (CONFIG_ID,CONFIG_NAME,CONFIG_VALUE) values (13,'SYS_PASSWORD','123');
Insert into TRAVEL.CONFIG (CONFIG_ID,CONFIG_NAME,CONFIG_VALUE) values (14,'TravelTeam','vftestmails@gmail.com');
Insert into TRAVEL.CONFIG (CONFIG_ID,CONFIG_NAME,CONFIG_VALUE) values (15,'AgentTeam','vftestmails@gmail.com');
Insert into TRAVEL.CONFIG (CONFIG_ID,CONFIG_NAME,CONFIG_VALUE) values (16,'PROVIDER_URL','http://localhost:7007');
Insert into TRAVEL.CONFIG (CONFIG_ID,CONFIG_NAME,CONFIG_VALUE) values (17,'DBConnection','jdbc:oracle:thin:fleet/fleet@192.168.1.16:1521:orcl');
REM INSERTING into TRAVEL.INVOICES
SET DEFINE OFF;
REM INSERTING into TRAVEL.INVOICE_ATTACHMENT
SET DEFINE OFF;
REM INSERTING into TRAVEL.NOTIFICATION
SET DEFINE OFF;
REM INSERTING into TRAVEL.PRODUCT_TYPE
SET DEFINE OFF;
REM INSERTING into TRAVEL.RATES
SET DEFINE OFF;
REM INSERTING into TRAVEL.ROOM_TYPE
SET DEFINE OFF;
REM INSERTING into TRAVEL.SERVICE_TYPE
SET DEFINE OFF;
REM INSERTING into TRAVEL.SUPPLIER
SET DEFINE OFF;
REM INSERTING into TRAVEL.SUPPLIER_PRODUCT
SET DEFINE OFF;
REM INSERTING into TRAVEL.SYSTEM_SCREENS
SET DEFINE OFF;
REM INSERTING into TRAVEL.TMS_USER
SET DEFINE OFF;
Insert into TRAVEL.TMS_USER (EMPLOYEE_ID,FULLNAME,ACCOUNTNAME,EMAIL,PHONE,DEPARTMENT,COSTCENTER,COMMENTS,EMPLOYEE_TYPE,STATUS,STAFFID) values (43,'Sara Yehia','syehia1','vftestmails@gmail.com','1003013014','Technology','244',null,'RMSUSER',1,3144);
REM INSERTING into TRAVEL.UPLOADED_INVOICE_FILE
SET DEFINE OFF;
REM INSERTING into TRAVEL.USERSCREENS
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index SERVICE_PRODUCTNAME_UNIQUE
--------------------------------------------------------

  CREATE UNIQUE INDEX "TRAVEL"."SERVICE_PRODUCTNAME_UNIQUE" ON "TRAVEL"."PRODUCT_TYPE" ("SERVICE_ID", "NAME") 
  ;
--------------------------------------------------------
--  DDL for Index SYSTEM_SCREENS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "TRAVEL"."SYSTEM_SCREENS_PK" ON "TRAVEL"."SYSTEM_SCREENS" ("SCREEN_ID") 
  ;
--------------------------------------------------------
--  DDL for Index CONFIG_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "TRAVEL"."CONFIG_PK" ON "TRAVEL"."CONFIG" ("CONFIG_ID") 
  ;
--------------------------------------------------------
--  DDL for Index NOTIFICATION_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "TRAVEL"."NOTIFICATION_PK" ON "TRAVEL"."NOTIFICATION" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index USERSCREENS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "TRAVEL"."USERSCREENS_PK" ON "TRAVEL"."USERSCREENS" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index CATEGORY_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "TRAVEL"."CATEGORY_PK" ON "TRAVEL"."CATEGORY" ("CAT_ID") 
  ;
--------------------------------------------------------
--  DDL for Index TMS_USER_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "TRAVEL"."TMS_USER_PK" ON "TRAVEL"."TMS_USER" ("EMPLOYEE_ID") 
  ;
--------------------------------------------------------
--  DDL for Index PRODUCT_SUPPIER_UNIQUE
--------------------------------------------------------

  CREATE UNIQUE INDEX "TRAVEL"."PRODUCT_SUPPIER_UNIQUE" ON "TRAVEL"."SUPPLIER_PRODUCT" ("PRODUCT_ID", "SUPPLIER_ID") 
  ;
--------------------------------------------------------
--  DDL for Index SYS_C0014231
--------------------------------------------------------

  CREATE UNIQUE INDEX "TRAVEL"."SYS_C0014231" ON "TRAVEL"."TMS_USER" ("STAFFID") 
  ;
--------------------------------------------------------
--  Constraints for Table RATES
--------------------------------------------------------

  ALTER TABLE "TRAVEL"."RATES" ADD PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "TRAVEL"."RATES" MODIFY ("YEAR" NOT NULL ENABLE);
  ALTER TABLE "TRAVEL"."RATES" MODIFY ("RATE" NOT NULL ENABLE);
  ALTER TABLE "TRAVEL"."RATES" MODIFY ("SUPPLIER_PRODUCT_ID" NOT NULL ENABLE);
  ALTER TABLE "TRAVEL"."RATES" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table SUPPLIER_PRODUCT
--------------------------------------------------------

  ALTER TABLE "TRAVEL"."SUPPLIER_PRODUCT" ADD CONSTRAINT "PRODUCT_SUPPIER_UNIQUE" UNIQUE ("PRODUCT_ID", "SUPPLIER_ID") ENABLE;
  ALTER TABLE "TRAVEL"."SUPPLIER_PRODUCT" ADD PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "TRAVEL"."SUPPLIER_PRODUCT" MODIFY ("PRODUCT_ID" NOT NULL ENABLE);
  ALTER TABLE "TRAVEL"."SUPPLIER_PRODUCT" MODIFY ("SUPPLIER_ID" NOT NULL ENABLE);
  ALTER TABLE "TRAVEL"."SUPPLIER_PRODUCT" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table SYSTEM_SCREENS
--------------------------------------------------------

  ALTER TABLE "TRAVEL"."SYSTEM_SCREENS" ADD CONSTRAINT "SYSTEM_SCREENS_PK" PRIMARY KEY ("SCREEN_ID") ENABLE;
  ALTER TABLE "TRAVEL"."SYSTEM_SCREENS" MODIFY ("SCREEN_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table ROOM_TYPE
--------------------------------------------------------

  ALTER TABLE "TRAVEL"."ROOM_TYPE" ADD UNIQUE ("NAME") ENABLE;
  ALTER TABLE "TRAVEL"."ROOM_TYPE" ADD PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "TRAVEL"."ROOM_TYPE" MODIFY ("NAME" NOT NULL ENABLE);
  ALTER TABLE "TRAVEL"."ROOM_TYPE" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table CATEGORY
--------------------------------------------------------

  ALTER TABLE "TRAVEL"."CATEGORY" ADD CONSTRAINT "CATEGORY_PK" PRIMARY KEY ("CAT_ID") ENABLE;
  ALTER TABLE "TRAVEL"."CATEGORY" MODIFY ("CAT_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table SUPPLIER
--------------------------------------------------------

  ALTER TABLE "TRAVEL"."SUPPLIER" ADD UNIQUE ("NAME") ENABLE;
  ALTER TABLE "TRAVEL"."SUPPLIER" ADD PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "TRAVEL"."SUPPLIER" MODIFY ("NAME" NOT NULL ENABLE);
  ALTER TABLE "TRAVEL"."SUPPLIER" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table INVOICES
--------------------------------------------------------

  ALTER TABLE "TRAVEL"."INVOICES" ADD PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "TRAVEL"."INVOICES" MODIFY ("UPLOADED_INVOICE_FILE_ID" NOT NULL ENABLE);
  ALTER TABLE "TRAVEL"."INVOICES" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table SERVICE_TYPE
--------------------------------------------------------

  ALTER TABLE "TRAVEL"."SERVICE_TYPE" ADD UNIQUE ("NAME") ENABLE;
  ALTER TABLE "TRAVEL"."SERVICE_TYPE" ADD PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "TRAVEL"."SERVICE_TYPE" MODIFY ("NAME" NOT NULL ENABLE);
  ALTER TABLE "TRAVEL"."SERVICE_TYPE" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table CONFIG
--------------------------------------------------------

  ALTER TABLE "TRAVEL"."CONFIG" ADD CONSTRAINT "CONFIG_PK" PRIMARY KEY ("CONFIG_ID") ENABLE;
  ALTER TABLE "TRAVEL"."CONFIG" MODIFY ("CONFIG_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table NOTIFICATION
--------------------------------------------------------

  ALTER TABLE "TRAVEL"."NOTIFICATION" ADD CONSTRAINT "NOTIFICATION_PK" PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "TRAVEL"."NOTIFICATION" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table INVOICE_ATTACHMENT
--------------------------------------------------------

  ALTER TABLE "TRAVEL"."INVOICE_ATTACHMENT" ADD PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "TRAVEL"."INVOICE_ATTACHMENT" MODIFY ("UPLOADED_INVOICE_FILE_ID" NOT NULL ENABLE);
  ALTER TABLE "TRAVEL"."INVOICE_ATTACHMENT" MODIFY ("TYPE" NOT NULL ENABLE);
  ALTER TABLE "TRAVEL"."INVOICE_ATTACHMENT" MODIFY ("CONTENT" NOT NULL ENABLE);
  ALTER TABLE "TRAVEL"."INVOICE_ATTACHMENT" MODIFY ("NAME" NOT NULL ENABLE);
  ALTER TABLE "TRAVEL"."INVOICE_ATTACHMENT" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table USERSCREENS
--------------------------------------------------------

  ALTER TABLE "TRAVEL"."USERSCREENS" ADD CONSTRAINT "USERSCREENS_PK" PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "TRAVEL"."USERSCREENS" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "TRAVEL"."USERSCREENS" MODIFY ("EMPLOYEE_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table TMS_USER
--------------------------------------------------------

  ALTER TABLE "TRAVEL"."TMS_USER" ADD UNIQUE ("STAFFID") ENABLE;
  ALTER TABLE "TRAVEL"."TMS_USER" ADD CONSTRAINT "TMS_USER_PK" PRIMARY KEY ("EMPLOYEE_ID") ENABLE;
  ALTER TABLE "TRAVEL"."TMS_USER" MODIFY ("STAFFID" NOT NULL ENABLE);
  ALTER TABLE "TRAVEL"."TMS_USER" MODIFY ("EMPLOYEE_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table PRODUCT_TYPE
--------------------------------------------------------

  ALTER TABLE "TRAVEL"."PRODUCT_TYPE" ADD CONSTRAINT "SERVICE_PRODUCTNAME_UNIQUE" UNIQUE ("SERVICE_ID", "NAME") ENABLE;
  ALTER TABLE "TRAVEL"."PRODUCT_TYPE" ADD PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "TRAVEL"."PRODUCT_TYPE" MODIFY ("SERVICE_ID" NOT NULL ENABLE);
  ALTER TABLE "TRAVEL"."PRODUCT_TYPE" MODIFY ("NAME" NOT NULL ENABLE);
  ALTER TABLE "TRAVEL"."PRODUCT_TYPE" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table AIRLINE
--------------------------------------------------------

  ALTER TABLE "TRAVEL"."AIRLINE" ADD UNIQUE ("NAME") ENABLE;
  ALTER TABLE "TRAVEL"."AIRLINE" ADD PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "TRAVEL"."AIRLINE" MODIFY ("NAME" NOT NULL ENABLE);
  ALTER TABLE "TRAVEL"."AIRLINE" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table UPLOADED_INVOICE_FILE
--------------------------------------------------------

  ALTER TABLE "TRAVEL"."UPLOADED_INVOICE_FILE" ADD PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "TRAVEL"."UPLOADED_INVOICE_FILE" MODIFY ("TYPE" NOT NULL ENABLE);
  ALTER TABLE "TRAVEL"."UPLOADED_INVOICE_FILE" MODIFY ("CONTENT" NOT NULL ENABLE);
  ALTER TABLE "TRAVEL"."UPLOADED_INVOICE_FILE" MODIFY ("NAME" NOT NULL ENABLE);
  ALTER TABLE "TRAVEL"."UPLOADED_INVOICE_FILE" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table INVOICES
--------------------------------------------------------

  ALTER TABLE "TRAVEL"."INVOICES" ADD CONSTRAINT "UPLOAD_INVOICE_FILE_FK_INVOIC" FOREIGN KEY ("UPLOADED_INVOICE_FILE_ID")
	  REFERENCES "TRAVEL"."UPLOADED_INVOICE_FILE" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table INVOICE_ATTACHMENT
--------------------------------------------------------

  ALTER TABLE "TRAVEL"."INVOICE_ATTACHMENT" ADD CONSTRAINT "UPLOAD_INVOICE_FILE_FK_ATTACH" FOREIGN KEY ("UPLOADED_INVOICE_FILE_ID")
	  REFERENCES "TRAVEL"."UPLOADED_INVOICE_FILE" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table PRODUCT_TYPE
--------------------------------------------------------

  ALTER TABLE "TRAVEL"."PRODUCT_TYPE" ADD CONSTRAINT "PRODUCT_SERVICE" FOREIGN KEY ("SERVICE_ID")
	  REFERENCES "TRAVEL"."SERVICE_TYPE" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table RATES
--------------------------------------------------------

  ALTER TABLE "TRAVEL"."RATES" ADD CONSTRAINT "AIRLINE_FK_RATES" FOREIGN KEY ("AIRLINE_ID")
	  REFERENCES "TRAVEL"."AIRLINE" ("ID") ENABLE;
  ALTER TABLE "TRAVEL"."RATES" ADD CONSTRAINT "ROOM_TYPE_FK_RATES" FOREIGN KEY ("ROOM_TYPE_ID")
	  REFERENCES "TRAVEL"."ROOM_TYPE" ("ID") ENABLE;
  ALTER TABLE "TRAVEL"."RATES" ADD CONSTRAINT "SUPPLIER_PRODUCT_FK_RATES" FOREIGN KEY ("SUPPLIER_PRODUCT_ID")
	  REFERENCES "TRAVEL"."SUPPLIER_PRODUCT" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table SUPPLIER_PRODUCT
--------------------------------------------------------

  ALTER TABLE "TRAVEL"."SUPPLIER_PRODUCT" ADD CONSTRAINT "SUPPLIER_PRODUCT_FK_PRODUCT" FOREIGN KEY ("PRODUCT_ID")
	  REFERENCES "TRAVEL"."PRODUCT_TYPE" ("ID") ENABLE;
  ALTER TABLE "TRAVEL"."SUPPLIER_PRODUCT" ADD CONSTRAINT "SUPPLIER_PRODUCT_FK_SUPPLIER" FOREIGN KEY ("SUPPLIER_ID")
	  REFERENCES "TRAVEL"."SUPPLIER" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table SYSTEM_SCREENS
--------------------------------------------------------

  ALTER TABLE "TRAVEL"."SYSTEM_SCREENS" ADD CONSTRAINT "SYSTEM_SCREENS_FK1" FOREIGN KEY ("CATEGORY_ID")
	  REFERENCES "TRAVEL"."CATEGORY" ("CAT_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table USERSCREENS
--------------------------------------------------------

  ALTER TABLE "TRAVEL"."USERSCREENS" ADD CONSTRAINT "USERSCREENS_FK1" FOREIGN KEY ("SCREEN_ID")
	  REFERENCES "TRAVEL"."SYSTEM_SCREENS" ("SCREEN_ID") ENABLE;
  ALTER TABLE "TRAVEL"."USERSCREENS" ADD CONSTRAINT "USERSCREENS_FK_USER" FOREIGN KEY ("EMPLOYEE_ID")
	  REFERENCES "TRAVEL"."TMS_USER" ("EMPLOYEE_ID") ENABLE;
