package com.truemega.test;

import java.util.UUID;

public class UUIDDemo {
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
				+ " (CASE WHEN  SERVICE_TYPE LIKE 'Air' AND  (DEPARTURE_DATE is null OR ARRIVAL_DATE is null OR ROUTING is null OR INTER_DOM is null OR AIRLINE is null) \n"
				+ " THEN 0 \n"
				+ " ELSE 1 END) AIR_MANDATORY_VALID_N, \n"
				+ " (CASE WHEN  NOT (SERVICE_TYPE  LIKE 'Air' OR SERVICE_TYPE  LIKE 'Hotel')  AND  ( FROM_DATE is null OR TO_DATE is null ) \n"
				+ " THEN 0 \n"
				+ " ELSE 1 END) OTHER_MANDATORY_VALID_N, \n"
				+ " (CASE WHEN  NOT (INTER_DOM  LIKE 'INTERNATIONAL' OR INTER_DOM  LIKE 'DOMESTIC') \n"
				+ " THEN 0 \n"
				+ " ELSE 1 END) INTER_DOM_VALID_N, \n"
				+ " (CASE WHEN   (CHECK_OUT-CHECK_IN)+1 !=  NUMBER_OF_NIGHTS \n"
				+ " THEN 0 \n" + " ELSE 1 END) NUMBER_OF_NIGHTS_VALID_N, \n"
				+ " (CASE WHEN   NET_AMOUNT+OPERATION_FEES !=  TOTAL_AMOUNT \n"
				+ " THEN 0 \n" + " ELSE 1 END) TOTAL_AMOUNT_VALID_N \n"
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
				+ " SERVICE_TYPE LIKE 'Air' AND  (DEPARTURE_DATE is null OR ARRIVAL_DATE is null OR ROUTING is null OR INTER_DOM is null OR AIRLINE is null) \n"
				+ " ) \n"
				+ " OR \n"
				+ " ( \n"
				+ " NOT (SERVICE_TYPE  LIKE 'Air' OR SERVICE_TYPE  LIKE 'Hotel') \n"
				+ " AND  \n"
				+ " ( FROM_DATE is null OR TO_DATE is null ) \n"
				+ " ) \n"
				+ " OR \n"
				+ " (  \n"
				+ " NOT (INTER_DOM  LIKE 'INTERNATIONAL' OR INTER_DOM  LIKE 'DOMESTIC') \n"
				+ " ) \n"
				+ " OR \n"
				+ " ( \n"
				+ " (CHECK_OUT-CHECK_IN)+1 !=  NUMBER_OF_NIGHTS \n"
				+ " ) \n"
				+ " OR \n"
				+ " ( \n"
				+ " NET_AMOUNT+OPERATION_FEES !=  TOTAL_AMOUNT \n"
				+ " ) ) t2 \n"
				+ " on( t1.INVOICE_ORDER   = t2.INVOICE_ORDER AND t1.TRANSACTION_ID = t2.TRANSACTION_ID ) \n"
				+ " when matched then \n"
				+ " update set  \n"
				+ " t1.GENERAL_MANDATORY_VALID=t2.GENERAL_MANDATORY_VALID_N, \n"
				+ " t1.INVOICE_DATE_RANGE_VALID=t2.INVOICE_DATE_RANGE_VALID_N, \n"
				+ " t1.AIR_MANDATORY_VALID=t2.AIR_MANDATORY_VALID_N, \n"
				+ " t1.OTHER_MANDATORY_VALID=t2.OTHER_MANDATORY_VALID_N, \n"
				+ " t1.NUMBER_OF_NIGHTS_VALID=t2.NUMBER_OF_NIGHTS_VALID_N, \n"
				+ " t1.TOTAL_AMOUNT_VALID=t2.TOTAL_AMOUNT_VALID_N, \n"
				+ " t1.INTER_DOM_VALID=t2.INTER_DOM_VALID_N";
		return query;
	}

	public static void main(String[] args) {
		UUIDDemo uuidDemo = new UUIDDemo();
		System.out.println(uuidDemo.getMergeStatement(52));
		// creating UUID
		UUID uid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");

		// checking the value of random UUID
		for (int i = 0; i < 10; i++)
			;
		// System.out.println("Random UUID value: " + uid.randomUUID());

		UUID idOne = UUID.randomUUID();
		UUID idTwo = UUID.randomUUID();
		System.out.println("UUID One: " + idOne.toString());
		System.out.println("UUID Two: " + idTwo);
	}
}
