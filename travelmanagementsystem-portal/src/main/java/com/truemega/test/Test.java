package com.truemega.test;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getQuery("2015", "2016"));
		System.out.println(getInsertIntoActualTableQuery(201));
	}
	
	public static String getInsertIntoActualTableQuery(int fileID) {
		String query = "insert into INVOICES \n"
				+ " (ID,INVOICE_ORDER,TRANSACTION_ID,INVOICE_NUMBER,BOOKING_FILE_NUMBER,DEPARTURE_DATE,ARRIVAL_DATE, \n"
				+ " EMPLOYEE_ID,COST_CENTER,COST_CENTER_DEPARTMENT,PASSENGER_NAME,SERVICE_TYPE,SERVICE_DESC,ROUTING, \n"
				+ " INTER_DOM,CHECK_IN,CHECK_OUT,NUMBER_OF_NIGHTS,NUMBER_OF_ROOMS,AIRLINE,ROOM_TYPE,SUPPLIER_NAME, \n"
				+ " NET_AMOUNT,OPERATION_FEES,TOTAL_AMOUNT,TICKET_NO,TRAVEL_FORM_NUMBER,DESCRIPTION,FROM_DATE,TO_DATE, \n"
				+ " UPLOADED_INVOICE_FILE_ID,EMPLOYEE_DEPARTMENT,INVOICE_DATE,DESTINATION, CITY, COUNTRY, TRIP_PURPOSE ) \n"
				+ " SELECT SEQ_INVOICES.nextval,INVOICE_ORDER,TRANSACTION_ID,INVOICE_NUMBER,BOOKING_FILE_NUMBER,DEPARTURE_DATE, \n"
				+ " ARRIVAL_DATE,EMPLOYEE_ID,COST_CENTER,COST_CENTER_DEPARTMENT,PASSENGER_NAME,SERVICE_TYPE,SERVICE_DESC, \n"
				+ " ROUTING,INTER_DOM,CHECK_IN,CHECK_OUT,NUMBER_OF_NIGHTS,NUMBER_OF_ROOMS,AIRLINE,ROOM_TYPE,SUPPLIER_NAME, \n"
				+ " NET_AMOUNT,OPERATION_FEES,TOTAL_AMOUNT,TICKET_NO,TRAVEL_FORM_NUMBER,DESCRIPTION,FROM_DATE,TO_DATE, \n"
				+ " UPLOADED_INVOICE_FILE_ID,EMPLOYEE_DEPARTMENT,INVOICE_DATE,DESTINATION, CITY, COUNTRY, TRIP_PURPOSE \n"
				+ " from INVOICES_TEMP where UPLOADED_INVOICE_FILE_ID="
				+ fileID + " \n";
		return query;
	}


	public static String getQuery(String firstYear, String secondYear) {

		String fromYear = firstYear;
		String toYear = secondYear;

		String q = "SELECT RATES_VIEW1.SERVICE_NAME        AS SERVICE_NAME1, \n"
				+ " RATES_VIEW1.PRODUCT_NAME             AS PRODUCT_NAME1, \n"
				+ " RATES_VIEW1.SUPPLIER_NAME            AS SUPPLIER_NAME1, \n"
				+ " RATES_VIEW1.AIRLINE_NAME             AS AIRLINE_NAME1, \n"
				+ " RATES_VIEW1.ROUTING                  AS ROUTING1, \n"
				+ " RATES_VIEW1.RATE                     AS RATE1, \n"
				+ " RATES_VIEW1.ROOM_TYPE_NAME           AS ROOM_TYPE_NAME1, \n"
				+ " RATES_VIEW1.YEAR                     AS YEAR1, \n"
				+ " RATES_VIEW2.SERVICE_NAME             AS SERVICE_NAME2, \n"
				+ " RATES_VIEW2.PRODUCT_NAME             AS PRODUCT_NAME2, \n"
				+ " RATES_VIEW2.SUPPLIER_NAME            AS SUPPLIER_NAME2, \n"
				+ " RATES_VIEW2.AIRLINE_NAME             AS AIRLINE_NAME2, \n"
				+ " RATES_VIEW2.ROUTING                  AS ROUTING2, \n"
				+ " RATES_VIEW2.RATE                     AS RATE2, \n"
				+ " RATES_VIEW2.ROOM_TYPE_NAME           AS ROOM_TYPE_NAME2, \n"
				+ " RATES_VIEW2.YEAR                     AS YEAR2 \n"
				+ " FROM RATES_VIEW RATES_VIEW1 \n"
				+ " FULL OUTER JOIN RATES_VIEW RATES_VIEW2 \n"
				+ " ON RATES_VIEW1.SUPPLIER_PRODUCT_ID = RATES_VIEW2.SUPPLIER_PRODUCT_ID \n"
				+ " AND RATES_VIEW1.AIRLINE_ID         = RATES_VIEW2.AIRLINE_ID \n"
				+ " AND RATES_VIEW1.ROOM_TYPE_ID       = RATES_VIEW2.ROOM_TYPE_ID \n"
				+ " AND LOWER(RATES_VIEW1.ROUTING)     = LOWER(RATES_VIEW1.ROUTING) \n"
				+ " WHERE RATES_VIEW1.YEAR             ="
				+ firstYear
				+ " \n"
				+ " AND RATES_VIEW2.YEAR               =" + secondYear + " \n";

		String systemUser = "user";
		q = "INSERT INTO TRAVEL.RATES (ID, SUPPLIER_PRODUCT_ID, RATE, ROOM_TYPE_ID,AIRLINE_ID, YEAR, MODIFICATION_DATE, SYSTEM_USER, STATUS,DESCRIPTION,ROUTING) \n"
				+ " SELECT \n"
				+ " SEQ_RATES.nextval, \n"
				+ " SUPPLIER_PRODUCT_ID, \n"
				+ " RATE, \n"
				+ " ROOM_TYPE_ID, \n"
				+ " AIRLINE_ID, \n"
				+ " "
				+ toYear
				+ " AS YEAR, \n"
				+ " sysDate AS MODIFICATION_DATE, \n"
				+ " '"
				+ systemUser
				+ "' AS SYSTEM_USER, \n"
				+ " STATUS, \n"
				+ " DESCRIPTION, \n"
				+ " ROUTING  From RATES  where  YEAR = "
				+ fromYear;

		return q;

	}
}
