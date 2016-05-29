package com.truemega.report;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import com.truemega.beans.TravelReportBean;
import com.truemega.converter.ProductConverter;
import com.truemega.converter.ServiceConverter;
import com.truemega.converter.SupplierProductConverter;
import com.truemega.dto.ProductTypeDTO;
import com.truemega.dto.ServiceTypeDTO;
import com.truemega.interfaces.administration.ProductService;
import com.truemega.interfaces.administration.ServiceService;
import com.truemega.logger.LoggerService;
import com.truemega.reportgenerator.DataType;
import com.truemega.reportgenerator.GenerateDynamicReport;

@ManagedBean(name = "productComparisonReport")
@ViewScoped
public class ProductComparisonReport extends TravelReportBean {

	private GenerateDynamicReport dynamicReport;

	private String year;

	private ServiceTypeDTO serviceTypeDTO;
	private ProductTypeDTO productTypeDTO;

	private LoggerService loggerService = new LoggerService();

	@EJB
	private ServiceService serviceService;
	private ServiceConverter serviceConverter;

	private List<ServiceTypeDTO> serviceTypeDTOs;

	@EJB
	private ProductService productService;

	private ProductConverter productConverter;

	private List<ProductTypeDTO> productTypeDTOs;

	@PostConstruct
	public void init() {

		serviceTypeDTOs = serviceService
				.getAllServices("productComparisonReport");
		serviceConverter = new ServiceConverter(serviceTypeDTOs);
	}

	@Override
	public void search() {
		// TODO Auto-generated method stub

		try {

			String queury = "SELECT \n"
					+ " RATES_VIEW1.SERVICE_NAME        	  AS SERVICE_NAME, \n"
					+ " RATES_VIEW1.PRODUCT_NAME             AS PRODUCT_NAME,  \n"
					+ " RATES_VIEW1.SUPPLIER_NAME            AS SUPPLIER_NAME,  \n"
					+ " RATES_VIEW1.AIRLINE_NAME             AS AIRLINE_NAME,  \n"
					+ " RATES_VIEW1.ROUTING                  AS ROUTING, \n"
					+ " RATES_VIEW1.RATE                     AS RATE, \n"
					+ " RATES_VIEW1.ROOM_TYPE_NAME           AS ROOM_TYPE_NAME, \n"
					+ " TO_CHAR(RATES_VIEW1.YEAR)                     AS YEAR \n"

					+ " FROM RATES_VIEW RATES_VIEW1 \n" + " WHERE 1=1 ";

			if (serviceTypeDTO != null)
				queury += " AND  RATES_VIEW1.SERVICE_ID =  "
						+ serviceTypeDTO.getId();

			if (productTypeDTO != null)
				queury += " AND  RATES_VIEW1.PRODUCT_ID =  "
						+ productTypeDTO.getId();

			if (year != null && year.length() > 0)
				queury += " AND  RATES_VIEW1.YEAR =  " + year;

			dynamicReport = new GenerateDynamicReport();
			dynamicReport.setReportName("ProductComparisonReport");
			dynamicReport.setReportTitle("Product Comparison Report");
			dynamicReport.setReportQuery(queury);

			dynamicReport.columnsNames.add("Service Name");
			dynamicReport.columnsNames.add("Product Name");
			dynamicReport.columnsNames.add("Supplier Name");
			dynamicReport.columnsNames.add("Airline Name");
			dynamicReport.columnsNames.add("Routing");
			dynamicReport.columnsNames.add("Rate");
			dynamicReport.columnsNames.add("Room Type");
			dynamicReport.columnsNames.add("Year");

			dynamicReport.fieldsNames.add("SERVICE_NAME");
			dynamicReport.fieldsNames.add("PRODUCT_NAME");
			dynamicReport.fieldsNames.add("SUPPLIER_NAME");
			dynamicReport.fieldsNames.add("AIRLINE_NAME");
			dynamicReport.fieldsNames.add("ROUTING");
			dynamicReport.fieldsNames.add("RATE");
			dynamicReport.fieldsNames.add("ROOM_TYPE_NAME");
			dynamicReport.fieldsNames.add("YEAR");

			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.DOUBLE.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());
			dynamicReport.dataTypes.add(DataType.STRING.toString());

			String reportPath = dynamicReport.exportDynamicReportToExcel();
			getDownloadableReportFile(reportPath);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void changeService(AjaxBehaviorEvent event) {

		loggerService.logPortalInfo(">> Start changeService()");
		ServiceTypeDTO serviceTypeDTO = (ServiceTypeDTO) event.getComponent()
				.getAttributes().get("serviceObj");
		if (serviceTypeDTO != null) {

			// call el service
			productTypeDTOs = productService
					.getProductsByService(serviceTypeDTO);
			productConverter = new ProductConverter(productTypeDTOs);
			productTypeDTO = null;

		} else {
			productTypeDTOs = null;
			productTypeDTO = null;
		}

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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public ServiceTypeDTO getServiceTypeDTO() {
		return serviceTypeDTO;
	}

	public void setServiceTypeDTO(ServiceTypeDTO serviceTypeDTO) {
		this.serviceTypeDTO = serviceTypeDTO;
	}

	public ProductTypeDTO getProductTypeDTO() {
		return productTypeDTO;
	}

	public void setProductTypeDTO(ProductTypeDTO productTypeDTO) {
		this.productTypeDTO = productTypeDTO;
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

	public ProductConverter getProductConverter() {
		return productConverter;
	}

	public void setProductConverter(ProductConverter productConverter) {
		this.productConverter = productConverter;
	}

	public List<ProductTypeDTO> getProductTypeDTOs() {
		return productTypeDTOs;
	}

	public void setProductTypeDTOs(List<ProductTypeDTO> productTypeDTOs) {
		this.productTypeDTOs = productTypeDTOs;
	}

}
