package com.truemega.report;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import com.truemega.beans.TravelReportBean;
import com.truemega.converter.AirLineConverter;
import com.truemega.converter.RoomConverter;
import com.truemega.converter.ServiceConverter;
import com.truemega.converter.SupplierProductConverter;
import com.truemega.dto.AirlineDTO;
import com.truemega.dto.RatesDTO;
import com.truemega.dto.RoomTypeDTO;
import com.truemega.dto.ServiceTypeDTO;
import com.truemega.dto.SupplierProductDTO;
import com.truemega.interfaces.administration.AirLineService;
import com.truemega.interfaces.administration.ProductSupplierService;
import com.truemega.interfaces.administration.RatesService;
import com.truemega.interfaces.administration.RoomTypeService;
import com.truemega.interfaces.administration.ServiceService;
import com.truemega.logger.LoggerService;
import com.truemega.reportgenerator.DataType;
import com.truemega.reportgenerator.GenerateDynamicReport;

@ManagedBean(name = "ratesComparisonReport")
@ViewScoped
public class RatesComparisonReport extends TravelReportBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GenerateDynamicReport dynamicReport;

	@EJB
	ServiceService serviceService;

	@EJB
	ProductSupplierService productSupplierService;

	@EJB
	RatesService ratesService;

	@EJB
	RoomTypeService roomTypeService;

	@EJB
	private AirLineService airLineService;

	private ServiceConverter serviceConverter;
	private SupplierProductConverter supplierProductConverter;
	private RoomConverter roomConverter;
	private AirLineConverter airLineConverter;

	private String year;

	private String toMonth;
	private String fromMonth;

	private LoggerService loggerService = new LoggerService();

	private RatesDTO ratesDTO = new RatesDTO();

	private ServiceTypeDTO serviceTypeDTO = new ServiceTypeDTO();
	private List<SupplierProductDTO> supplierProductDTOs = new ArrayList<SupplierProductDTO>();

	private List<RoomTypeDTO> roomTypeDTOs = new ArrayList<RoomTypeDTO>();
	private List<AirlineDTO> airlineDTOs = new ArrayList<AirlineDTO>();

	public ServiceTypeDTO getServiceTypeDTO() {
		return serviceTypeDTO;
	}

	public void setServiceTypeDTO(ServiceTypeDTO serviceTypeDTO) {
		this.serviceTypeDTO = serviceTypeDTO;
	}

	public RatesDTO getRatesDTO() {
		return ratesDTO;
	}

	public void setRatesDTO(RatesDTO ratesDTO) {
		this.ratesDTO = ratesDTO;
	}

	private List<ServiceTypeDTO> serviceTypeDTOs;

	private Integer rateId;

	public void changeService(AjaxBehaviorEvent event) {

		loggerService.logPortalInfo("Reports" + ">> Start changeService()");
		ServiceTypeDTO serviceTypeDTO = (ServiceTypeDTO) event.getComponent()
				.getAttributes().get("serviceObj");
		if (serviceTypeDTO != null) {
			supplierProductDTOs = productSupplierService
					.getAllSupplierProductsByServiceId(serviceTypeDTO.getId());
			supplierProductConverter = new SupplierProductConverter(
					supplierProductDTOs);

		} else {

		}

	}

	@PostConstruct
	public void init() {

		serviceTypeDTOs = serviceService.getAllServices("Reports");

		serviceConverter = new ServiceConverter(serviceTypeDTOs);
		supplierProductConverter = new SupplierProductConverter(
				supplierProductDTOs);

		roomTypeDTOs = roomTypeService.getAllRoomTypeActive("Reports");

		roomConverter = new RoomConverter(roomTypeDTOs);
		airlineDTOs = airLineService.getAllAirlineActive("Reports");
		airLineConverter = new AirLineConverter(airlineDTOs);

	}

	public ServiceConverter getServiceConverter() {
		return serviceConverter;
	}

	public void setServiceConverter(ServiceConverter serviceConverter) {
		this.serviceConverter = serviceConverter;
	}

	public List<ServiceTypeDTO> getServiceTypeDTOs() {
		return serviceTypeDTOs;
	}

	public void setServiceTypeDTOs(List<ServiceTypeDTO> serviceTypeDTOs) {
		this.serviceTypeDTOs = serviceTypeDTOs;
	}

	public List<SupplierProductDTO> getSupplierProductDTOs() {
		return supplierProductDTOs;
	}

	public void setSupplierProductDTOs(
			List<SupplierProductDTO> supplierProductDTOs) {
		this.supplierProductDTOs = supplierProductDTOs;
	}

	public SupplierProductConverter getSupplierProductConverter() {
		return supplierProductConverter;
	}

	public void setSupplierProductConverter(
			SupplierProductConverter supplierProductConverter) {
		this.supplierProductConverter = supplierProductConverter;
	}

	public Integer getRateId() {
		return rateId;
	}

	public void setRateId(Integer rateId) {
		this.rateId = rateId;
	}

	public List<RoomTypeDTO> getRoomTypeDTOs() {
		return roomTypeDTOs;
	}

	public void setRoomTypeDTOs(List<RoomTypeDTO> roomTypeDTOs) {
		this.roomTypeDTOs = roomTypeDTOs;
	}

	public List<AirlineDTO> getAirlineDTOs() {
		return airlineDTOs;
	}

	public void setAirlineDTOs(List<AirlineDTO> airlineDTOs) {
		this.airlineDTOs = airlineDTOs;
	}

	public RoomConverter getRoomConverter() {
		return roomConverter;
	}

	public void setRoomConverter(RoomConverter roomConverter) {
		this.roomConverter = roomConverter;
	}

	public AirLineConverter getAirLineConverter() {
		return airLineConverter;
	}

	public void setAirLineConverter(AirLineConverter airLineConverter) {
		this.airLineConverter = airLineConverter;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Override
	public void search() {
		// TODO Auto-generated method stub

		try {

			String queury = " SELECT INVOICES.INVOICE_ORDER, \n"
					+ " INVOICES.INVOICE_NUMBER, \n"
					+ " NVL(INVOICES.BOOKING_FILE_NUMBER, 'NA') AS BOOKING_FILE_NUMBER, \n"
					+ " INVOICES.SERVICE_TYPE, \n"
					+ " INVOICES.SERVICE_DESC, \n"
					+ " NVL(INVOICES.ROUTING, 'NA')   AS ROUTING, \n"
					+ " NVL(INVOICES.INTER_DOM, 'NA') AS INTER_DOM, \n"
					+ " NVL(INVOICES.AIRLINE, 'NA')   AS AIRLINE, \n"
					+ " NVL(INVOICES.ROOM_TYPE, 'NA') AS ROOM_TYPE, \n"
					+ " INVOICES.SUPPLIER_NAME, \n"
					+ " NVL(to_char(INVOICES.NET_AMOUNT/((INVOICES.CHECK_OUT-INVOICES.CHECK_IN)+1), '9999999999999.99'),'0')  AS HOTEL_RATE, \n"
					+ " NVL(to_char(INVOICES.NET_AMOUNT/((INVOICES.TO_DATE-INVOICES.FROM_DATE)+1), '9999999999999.99'),'0') AS INSURANCE_RATE, \n"
					+ " NVL(INVOICES.NET_AMOUNT,0) AS RATE, \n"
					+ " UPLOADED_INVOICE_FILE.INVOICES_MONTH AS  INVOICES_MONTH, \n"
					+ " RATES_VIEW.RATE       AS PRE_NEGOTIATED \n"
					+ " FROM INVOICES  INNER JOIN UPLOADED_INVOICE_FILE \n"
					+ " ON UPLOADED_INVOICE_FILE.ID=INVOICES.UPLOADED_INVOICE_FILE_ID";

			if (toMonth != null && toMonth.length() > 0) {
				Date toDate = convertStringToDate("01/" + toMonth);
				queury += " and to_date (UPLOADED_INVOICE_FILE.INVOICES_MONTH, 'MM/YYYY') <= TO_DATE ('"
						+ dateToString(toDate) + "', 'yyyy-MM-dd') ";
			}

			if (fromMonth != null && fromMonth.length() > 0) {
				Date fromDate = convertStringToDate("01/" + fromMonth);
				queury += " and to_date (UPLOADED_INVOICE_FILE.INVOICES_MONTH, 'MM/YYYY') >= TO_DATE ('"
						+ dateToString(fromDate) + "', 'yyyy-MM-dd') ";
			}

			queury += " LEFT JOIN RATES_VIEW \n"
					+ " ON INVOICES.SERVICE_TYPE   = RATES_VIEW.SERVICE_NAME \n"
					+ " AND INVOICES.SERVICE_DESC  = RATES_VIEW.PRODUCT_NAME \n"
					+ " AND INVOICES.SUPPLIER_NAME = RATES_VIEW.SUPPLIER_NAME \n"
					+ " AND SUBSTR(UPLOADED_INVOICE_FILE.INVOICES_MONTH,4) LIKE TO_CHAR(RATES_VIEW.YEAR) \n";

			dynamicReport = new GenerateDynamicReport();

			dynamicReport.fieldsNames.add("INVOICE_ORDER");
			dynamicReport.fieldsNames.add("INVOICE_NUMBER");
			dynamicReport.fieldsNames.add("BOOKING_FILE_NUMBER");
			dynamicReport.fieldsNames.add("SERVICE_TYPE");
			dynamicReport.fieldsNames.add("SERVICE_DESC");
			dynamicReport.fieldsNames.add("SUPPLIER_NAME");
			dynamicReport.fieldsNames.add("INVOICES_MONTH");
			dynamicReport.fieldsNames.add("PRE_NEGOTIATED");

			dynamicReport.columnsNames.add("Invoice Order");
			dynamicReport.columnsNames.add("Invoice Number");
			dynamicReport.columnsNames.add("Booking File Number");
			dynamicReport.columnsNames.add("Service Type");
			dynamicReport.columnsNames.add("Service Desc");
			dynamicReport.columnsNames.add("Supplier Name");
			dynamicReport.columnsNames.add("Invoices Month");
			dynamicReport.columnsNames.add("Pre-Negotiated Rates");

			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.DOUBLE.toString());

			String where = " where 1=1 ";

			if (serviceTypeDTO.getName().equalsIgnoreCase("hotel")) {
				// ratesDTO.supplierProductId
				where += " and lower (INVOICES.SERVICE_TYPE) like 'hotel'  "
						+ " and lower (INVOICES.SERVICE_DESC) like lower('"
						+ ratesDTO.getSupplierProductId().getProductId()
								.getName()
						+ "')"
						+ " and lower (INVOICES.SUPPLIER_NAME) like lower('"
						+ ratesDTO.getSupplierProductId().getSupplierId()
								.getName()
						+ "')"
						+ " and lower (INVOICES.ROOM_TYPE) like lower('"
						+ ratesDTO.getRoomTypeId().getName() + "')";

				queury += " AND lower(INVOICES.ROOM_TYPE)  like lower(RATES_VIEW.ROOM_TYPE_NAME) \n";

				dynamicReport.fieldsNames.add("ROOM_TYPE");
				dynamicReport.fieldsNames.add("HOTEL_RATE");

				dynamicReport.columnsNames.add("Room Type");
				dynamicReport.columnsNames.add("Hotel Rate");

				dynamicReport.dataTypes.add(DataType.STRING.toString());
				dynamicReport.dataTypes.add(DataType.STRING.toString());

			}

			else if (serviceTypeDTO.getName().equalsIgnoreCase("air")) {
				where += " and lower (INVOICES.SERVICE_TYPE) like 'air'  "
						+ " and lower (INVOICES.SERVICE_DESC) like lower('"
						+ ratesDTO.getSupplierProductId().getProductId()
								.getName()
						+ "')"
						+ " and lower (INVOICES.SUPPLIER_NAME) like lower('"
						+ ratesDTO.getSupplierProductId().getSupplierId()
								.getName() + "')"
						+ " and lower (INVOICES.AIRLINE) like lower('"
						+ ratesDTO.getAirlineId().getName() + "')"
						+ " and lower (INVOICES.ROUTING) like lower('"
						+ ratesDTO.getRouting() + "')";

				queury += " AND lower(INVOICES.AIRLINE)  like lower(RATES_VIEW.AIRLINE_NAME) \n";
				queury += " AND lower(INVOICES.ROUTING)  like lower(RATES_VIEW.ROUTING) \n";
				dynamicReport.fieldsNames.add("ROUTING");
				dynamicReport.fieldsNames.add("INTER_DOM");
				dynamicReport.fieldsNames.add("AIRLINE");

				dynamicReport.columnsNames.add("Routing");
				dynamicReport.columnsNames.add("Inter dom");
				dynamicReport.columnsNames.add("Airline");

				dynamicReport.dataTypes.add(DataType.STRING.toString());
				dynamicReport.dataTypes.add(DataType.STRING.toString());
				dynamicReport.dataTypes.add(DataType.STRING.toString());

				dynamicReport.fieldsNames.add("RATE");
				dynamicReport.columnsNames.add("Rate");
				dynamicReport.dataTypes.add(DataType.DOUBLE.toString());
			}

			else {

				if (serviceTypeDTO.getName().equalsIgnoreCase("Insurance")) {
					dynamicReport.fieldsNames.add("INSURANCE_RATE");
					dynamicReport.columnsNames.add("Insurance Rate");
					dynamicReport.dataTypes.add(DataType.STRING.toString());
				} else {
					dynamicReport.fieldsNames.add("RATE");
					dynamicReport.columnsNames.add("Rate");
					dynamicReport.dataTypes.add(DataType.DOUBLE.toString());

				}
				where += " and lower (INVOICES.SERVICE_TYPE) like lower('"
						+ serviceTypeDTO.getName()
						+ "')  "
						+ " and lower (INVOICES.SERVICE_DESC) like lower('"
						+ ratesDTO.getSupplierProductId().getProductId()
								.getName()
						+ "')"
						+ " and lower (INVOICES.SUPPLIER_NAME) like lower('"
						+ ratesDTO.getSupplierProductId().getSupplierId()
								.getName() + "')";
			}

			dynamicReport.setReportName("RatesComparisonReport");
			dynamicReport.setReportTitle("Rates Comparison Report");
			dynamicReport.setReportQuery(queury + where
					+ " order by to_date (INVOICES_MONTH, 'MM/YYYY') ");

			loggerService.logPortalInfo(queury + where
					+ " order by to_date (INVOICES_MONTH, 'MM/YYYY') ");
			String reportPath = dynamicReport.exportDynamicReportToExcel();
			getDownloadableReportFile(reportPath);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getToMonth() {
		return toMonth;
	}

	public void setToMonth(String toMonth) {
		this.toMonth = toMonth;
	}

	public String getFromMonth() {
		return fromMonth;
	}

	public void setFromMonth(String fromMonth) {
		this.fromMonth = fromMonth;
	}

	public String dateToString(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	public Date convertStringToDate(String s) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			if (s != null && !s.isEmpty()) {
				Date date = formatter.parse(s);
				return date;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
