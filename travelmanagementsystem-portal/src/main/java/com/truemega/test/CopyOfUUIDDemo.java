package com.truemega.test;

import java.util.UUID;

public class CopyOfUUIDDemo {
	private String getMergeStatement(int uploadedInvoiceFileId) {
		String query = " Merge into INVOICES_TEMP t1 using ( SELECT \n"
				+ " INVOICE_ORDER, \n"
				+ " TRANSACTION_ID, \n"
				+ " (CASE WHEN  INVOICE_NUMBER is null OR BOOKING_FILE_NUMBER is null OR EMPLOYEE_ID is null OR COST_CENTER is null \n"
				+ " OR EMPLOYEE_DEPARTMENT is null OR COST_CENTER_DEPARTMENT is null OR PASSENGER_NAME is null OR SERVICE_TYPE is null \n"
				+ " OR SERVICE_DESC is null OR SUPPLIER_NAME is null OR NET_AMOUNT is null OR OPERATION_FEES is null OR TOTAL_AMOUNT is null OR \n"
				+ " TICKET_NO is null OR TRAVEL_FORM_NUMBER is null OR INVOICE_DATE is null  \n"
				+ " THEN 0 \n"
				+ " ELSE 1 END) GENERAL_MANDATORY_VALID_N, \n"
				+ " (CASE WHEN  add_months(TO_DATE('26/'  || UPLOADED_INVOICE_FILE.INVOICES_MONTH, 'dd/mm/yyyy'), -1) > TRUNC(INVOICES_TEMP.INVOICE_DATE) \n"
				+ " OR TO_DATE('25/'  || UPLOADED_INVOICE_FILE.INVOICES_MONTH, 'dd/mm/yyyy') < TRUNC(INVOICES_TEMP.INVOICE_DATE) \n"
				+ " THEN 0 \n"
				+ " ELSE 1 END) INVOICE_DATE_RANGE_VALID_N, \n"
				+ " (CASE WHEN  LOWER(SERVICE_TYPE) LIKE 'air' AND  (DEPARTURE_DATE is null OR ARRIVAL_DATE is null OR ROUTING is null OR INTER_DOM is null OR AIRLINE is null) \n"
				+ " THEN 0 \n"
				+ " ELSE 1 END) AIR_MANDATORY_VALID_N, \n"
				+ " (CASE WHEN  LOWER(SERVICE_TYPE) LIKE 'hotel' AND  (CHECK_IN is null OR CHECK_OUT is null OR NUMBER_OF_NIGHTS is null OR NUMBER_OF_ROOMS is null OR ROOM_TYPE is null ) \n"
				+ " THEN 0  \n"
				+ " ELSE 1 END) HOTEL_MANDATORY_VALID_N, \n"
				+ " (CASE WHEN  NOT (LOWER(SERVICE_TYPE)  LIKE 'air' OR LOWER(SERVICE_TYPE)  LIKE 'hotel')  AND  ( FROM_DATE is null OR TO_DATE is null ) \n"
				+ " THEN 0 \n"
				+ " ELSE 1 END) OTHER_MANDATORY_VALID_N, \n"
				+ " (CASE WHEN  NOT (UPPER(INTER_DOM)  LIKE 'INTERNATIONAL' OR UPPER(INTER_DOM)  LIKE 'DOMESTIC') \n"
				+ " THEN 0 \n"
				+ " ELSE 1 END) INTER_DOM_VALID_N, \n"
				+ " (CASE WHEN   (CHECK_OUT-CHECK_IN)+1 !=  NUMBER_OF_NIGHTS \n"
				+ " THEN 0 \n"
				+ " ELSE 1 END) NUMBER_OF_NIGHTS_VALID_N, \n"
				+ " (CASE WHEN   NET_AMOUNT+OPERATION_FEES !=  TOTAL_AMOUNT \n"
				+ " THEN 0 \n"
				+ " ELSE 1 END) TOTAL_AMOUNT_VALID_N, \n"
				+ " ( CASE WHEN LOWER(SERVICE_TYPE) not in (select LOWER(name) from service_type) \n"
				+ "  THEN 0 \n"
				+ "  ELSE 1 \n"
				+ "  END)  SERVICE_TYPE_VALID_N1, \n"
				+ " ( CASE WHEN (LOWER(SERVICE_DESC),LOWER(SERVICE_TYPE)) not in ((SELECT LOWER(PRODUCT_TYPE.NAME),LOWER(st1.NAME) FROM PRODUCT_TYPE INNER JOIN SERVICE_TYPE st1 ON st1.ID = PRODUCT_TYPE.SERVICE_ID)) \n"
				+ "   THEN 0 \n"
				+ "  ELSE 1 \n"
				+ "  END) SERVICE_DESC_VALID_N1, \n"
				+ " ( CASE WHEN  LOWER(INVOICES_TEMP.SERVICE_TYPE) not like 'hotel' and  (LOWER(SUPPLIER_NAME),LOWER(SERVICE_DESC),LOWER(SERVICE_TYPE)) not in ((SELECT LOWER(s.NAME),LOWER(pt2.NAME),LOWER(st2.NAME) FROM PRODUCT_TYPE pt2 INNER JOIN SERVICE_TYPE st2 ON st2.ID = pt2.SERVICE_ID INNER JOIN SUPPLIER_PRODUCT sp ON pt2.ID = sp.PRODUCT_ID INNER JOIN SUPPLIER s ON s.ID = sp.SUPPLIER_ID)) \n"
				+ " THEN 0 \n"
				+ " ELSE 1 \n"
				+ " END) SUPPLIER_NAME_VALID_N1, \n"
				+ " ( CASE  WHEN LOWER(SERVICE_TYPE) LIKE 'air' AND  (LOWER(AIRLINE) not in (select LOWER(name) from AIRLINE)) \n"
				+ " THEN 0 \n"
				+ " ELSE 1 \n"
				+ " END) AIRLINE_VALID_N1, \n"
				+ "  ( CASE \n"
				+ "  WHEN INVOICE_NUMBER in (select DISTINCT INVOICE_NUMBER from INVOICES) \n"
				+ "  THEN 0 \n"
				+ "  ELSE 1 \n"
				+ "  END) INVOICE_NUMBER_VALID_N1, \n"
				+ " ( CASE  WHEN LOWER(SERVICE_TYPE) LIKE 'hotel' AND  (LOWER(ROOM_TYPE) not in (select LOWER(name) from ROOM_TYPE)) \n"
				+ " THEN 0 \n" + "   ELSE 1 \n" + " END) ROOM_TYPE_VALID_N1 \n"
				+ " FROM UPLOADED_INVOICE_FILE INNER JOIN INVOICES_TEMP \n"
				+ " ON UPLOADED_INVOICE_FILE.ID = "
				+ uploadedInvoiceFileId
				+ " and UPLOADED_INVOICE_FILE.ID = INVOICES_TEMP.UPLOADED_INVOICE_FILE_ID \n"
				+ " WHERE \n"
				+ " (  \n"
				+ " add_months(TO_DATE('26/'  || UPLOADED_INVOICE_FILE.INVOICES_MONTH, 'dd/mm/yyyy'), -1) > TRUNC(INVOICES_TEMP.INVOICE_DATE) \n"
				+ " OR \n"
				+ " TO_DATE('25/'  || UPLOADED_INVOICE_FILE.INVOICES_MONTH, 'dd/mm/yyyy') < TRUNC(INVOICES_TEMP.INVOICE_DATE) \n"
				+ " )  \n"
				+ " OR \n"
				+ " ( \n"
				+ " INVOICE_NUMBER is null OR BOOKING_FILE_NUMBER is null OR EMPLOYEE_ID is null OR COST_CENTER is null \n"
				+ " OR EMPLOYEE_DEPARTMENT is null OR COST_CENTER_DEPARTMENT is null OR PASSENGER_NAME is null OR SERVICE_TYPE is null \n"
				+ " OR SERVICE_DESC is null OR SUPPLIER_NAME is null OR NET_AMOUNT is null OR OPERATION_FEES is null OR TOTAL_AMOUNT is null OR \n"
				+ " TICKET_NO is null OR TRAVEL_FORM_NUMBER is null OR INVOICE_DATE is null  \n"
				+ " ) \n"
				+ " OR \n"
				+ " ( \n"
				+ " LOWER(SERVICE_TYPE) LIKE 'air' AND  (DEPARTURE_DATE is null OR ARRIVAL_DATE is null OR ROUTING is null OR INTER_DOM is null OR AIRLINE is null) \n"
				+ " ) \n"
				+ " OR  \n"
				+ " (  \n"
				+ " LOWER(SERVICE_TYPE) LIKE 'hotel' AND  (CHECK_IN is null OR CHECK_OUT is null OR NUMBER_OF_NIGHTS is null OR NUMBER_OF_ROOMS is null OR ROOM_TYPE is null) \n"
				+ " ) \n"
				+ " OR \n"
				+ " ( \n"
				+ " NOT (LOWER(SERVICE_TYPE)  LIKE 'air' OR LOWER(SERVICE_TYPE)  LIKE 'hotel') \n"
				+ " AND  \n"
				+ " ( FROM_DATE is null OR TO_DATE is null ) \n"
				+ " ) \n"
				+ " OR \n"
				+ " (  \n"
				+ " NOT (UPPER(INTER_DOM)  LIKE 'INTERNATIONAL' OR UPPER(INTER_DOM)  LIKE 'DOMESTIC') \n"
				+ " ) \n"
				+ " OR \n"
				+ " ( \n"
				+ " (CHECK_OUT-CHECK_IN)+1 !=  NUMBER_OF_NIGHTS \n"
				+ " ) \n"
				+ " OR \n"
				+ " ( \n"
				+ " NET_AMOUNT+OPERATION_FEES !=  TOTAL_AMOUNT \n"
				+ " ) \n"
				+ " OR \n"
				+ " LOWER(INVOICES_TEMP.SERVICE_TYPE) not in (select LOWER(name) from service_type) \n"
				+ "  OR \n"
				+ "  (LOWER(INVOICES_TEMP.SERVICE_DESC),LOWER(INVOICES_TEMP.SERVICE_TYPE)) not in ((SELECT LOWER(PRODUCT_TYPE.NAME),LOWER(st1.name) FROM  PRODUCT_TYPE INNER JOIN SERVICE_TYPE st1 ON st1.ID = PRODUCT_TYPE.SERVICE_ID )) \n"
				+ "  OR \n"
				+ "   LOWER(INVOICES_TEMP.SERVICE_TYPE) LIKE 'air' AND  (LOWER(INVOICES_TEMP.AIRLINE) not in (select LOWER(name) from AIRLINE)) \n"
				+ "  OR \n"
				+ "  LOWER(INVOICES_TEMP.SERVICE_TYPE) LIKE 'hotel' AND  (LOWER(INVOICES_TEMP.ROOM_TYPE) not in (select LOWER(name) from ROOM_TYPE)) \n"
				+ "  OR \n"
				+ " INVOICES_TEMP.INVOICE_NUMBER in (select DISTINCT INVOICE_NUMBER from INVOICES) \n"
				+ "  OR \n"
				+ " ( \n"
				+ " LOWER(INVOICES_TEMP.SERVICE_TYPE) not like 'hotel' and \n"
				+ " (LOWER(INVOICES_TEMP.SUPPLIER_NAME),LOWER(INVOICES_TEMP.SERVICE_DESC),LOWER(INVOICES_TEMP.SERVICE_TYPE)) not in ((SELECT LOWER(s.NAME),LOWER(pt2.NAME),LOWER(st2.NAME) FROM PRODUCT_TYPE pt2 INNER JOIN SERVICE_TYPE st2 ON st2.ID = pt2.SERVICE_ID INNER JOIN SUPPLIER_PRODUCT sp ON pt2.ID = sp.PRODUCT_ID INNER JOIN SUPPLIER s ON s.ID = sp.SUPPLIER_ID)) \n"
				+ " ) \n"
				+ " ) t2 \n"
				+ " on( t1.INVOICE_ORDER   = t2.INVOICE_ORDER AND t1.TRANSACTION_ID = t2.TRANSACTION_ID ) \n"
				+ " when matched then \n"
				+ " update set  \n"
				+ " t1.GENERAL_MANDATORY_VALID=t2.GENERAL_MANDATORY_VALID_N, \n"
				+ " t1.INVOICE_DATE_RANGE_VALID=t2.INVOICE_DATE_RANGE_VALID_N, \n"
				+ " t1.AIR_MANDATORY_VALID=t2.AIR_MANDATORY_VALID_N, \n"
				+ " t1.OTHER_MANDATORY_VALID=t2.OTHER_MANDATORY_VALID_N, \n"
				+ " t1.NUMBER_OF_NIGHTS_VALID=t2.NUMBER_OF_NIGHTS_VALID_N, \n"
				+ " t1.TOTAL_AMOUNT_VALID=t2.TOTAL_AMOUNT_VALID_N, \n"
				+ " t1.INTER_DOM_VALID=t2.INTER_DOM_VALID_N, \n"
				+ " t1.HOTEL_MANDATORY_VALID=t2.HOTEL_MANDATORY_VALID_N, "
				+ " t1.SERVICE_TYPE_VALID=t2.SERVICE_TYPE_VALID_N1, \n"
				+ " t1.SERVICE_DESC_VALID=t2.SERVICE_DESC_VALID_N1, \n"
				+ " t1.SUPPLIER_NAME_VALID=t2.SUPPLIER_NAME_VALID_N1, \n"
				+ " t1.AIRLINE_VALID=t2.AIRLINE_VALID_N1, \n"
				+ " t1.ROOM_TYPE_VALID=t2.ROOM_TYPE_VALID_N1, \n"
				+ " t1.INVOICE_NUMBER_VALID=t2.INVOICE_NUMBER_VALID_N1";
		return query;
	}

	private void getMergeStatement(Integer x) {
		x = x + 8;
		;
	}

	public static void main(String[] args) {
		Integer x = new Integer(3);

		CopyOfUUIDDemo uuidDemo = new CopyOfUUIDDemo();
		// uuidDemo.getMergeStatement(x);
		// System.out.println(x);
		System.out.println(uuidDemo.getMergeStatement(121));
		// creating UUID
		// UUID uid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
		//
		// // checking the value of random UUID
		// for (int i = 0; i < 10; i++)
		// ;
		// // System.out.println("Random UUID value: " + uid.randomUUID());
		//
		// UUID idOne = UUID.randomUUID();
		// UUID idTwo = UUID.randomUUID();
		// System.out.println("UUID One: " + idOne.toString());
		// System.out.println("UUID Two: " + idTwo);
	}
}
