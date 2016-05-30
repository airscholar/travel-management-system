package com.truemega.beans.administration;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.truemega.beans.TravelSingleBean;
import com.truemega.converter.ServiceConverter;
import com.truemega.dto.ProductTypeDTO;
import com.truemega.dto.ServiceTypeDTO;
import com.truemega.enums.Status;
import com.truemega.enums.UIOperation;
import com.truemega.interfaces.administration.RatesService;
import com.truemega.logger.LoggerService;
import com.truemega.sessionbeans.Entity;
import com.truemega.sessionbeans.Screen;
import com.truemega.utils.HttpJSFUtils;

@ManagedBean(name = "ratesClonerSingle")
@ViewScoped
public class RatesClonerSingleBean extends TravelSingleBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private RatesService ratesService;

	private LoggerService loggerService = new LoggerService();

	private String fromYear;
	private String toYear;
	private int numberOfRecords;

	@PostConstruct
	public void init() {

		Screen screen = (Screen) HttpJSFUtils.getSession().getAttribute(
				"screen");
		switch (screen.getScreenMode()) {
		case 0:
			screenMode = UIOperation.ADD;
			break;
		case 1:
			screenMode = UIOperation.UPDATE;
			load();
			break;
		case 2:
			screenMode = UIOperation.VIEW;
			load();
			break;
		}

	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		loggerService
				.logPortalInfo(" start save method of RatesClonerSingleBean ");
		boolean isValid = validate();
		if (isValid) {

			try {

				numberOfRecords = ratesService.cloneRates(toYear, fromYear,
						getUserName());
				screenMode = UIOperation.VIEW;
				operationStatus = Status.SUCCESS;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				operationStatus = Status.FAIL;
				operationMessage = "Invalid Data";
				loggerService.logPortalError("can't save clone Rate ", e);
			}

		} else {
			// Error Message
			screenMode = UIOperation.ADD;
			operationStatus = Status.FAIL;

		}
		loggerService
				.logPortalInfo(" end save method of RatesClonerSingleBean ");

	}

	@Override
	public void update() {
	}

	@Override
	public void load() {
	}

	@Override
	public boolean validate() {

		List<Object[]> list = ratesService.listCountRatesOfTwoYears(toYear,
				fromYear);
		Object[] result = list.get(0);
		int fromYearCount = new BigDecimal(result[0].toString()).intValue();
		int toYearCount = new BigDecimal(result[1].toString()).intValue();
		if (fromYearCount == 0) {
			operationMessage = "there are no rates in the from year selected";
			return false;
		}
		if (toYearCount > 0) {
			operationMessage = "there are  rates in the to year selected";
			return false;
		}
		return true;
	}

	public String getFromYear() {
		return fromYear;
	}

	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}

	public String getToYear() {
		return toYear;
	}

	public void setToYear(String toYear) {
		this.toYear = toYear;
	}

	public int getNumberOfRecords() {
		return numberOfRecords;
	}

	public void setNumberOfRecords(int numberOfRecords) {
		this.numberOfRecords = numberOfRecords;
	}

}
