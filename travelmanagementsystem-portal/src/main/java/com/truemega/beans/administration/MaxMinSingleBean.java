package com.truemega.beans.administration;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.truemega.beans.TravelSingleBean;
import com.truemega.dto.MaxMinValuesDTO;
import com.truemega.enums.Status;
import com.truemega.enums.UIOperation;
import com.truemega.interfaces.administration.MaxMinService;
import com.truemega.interfaces.notification.UploadNotificationSender;
import com.truemega.logger.LoggerService;
import com.truemega.sessionbeans.Screen;
import com.truemega.utils.HttpJSFUtils;

@ManagedBean(name = "maxminsingle")
@ViewScoped
public class MaxMinSingleBean extends TravelSingleBean {

	private static final long serialVersionUID = 1L;

	private LoggerService loggerService = new LoggerService();

	@EJB
	private MaxMinService maxMinService;
	@EJB
	private UploadNotificationSender notification;

	private MaxMinValuesDTO visa = null;
	private MaxMinValuesDTO medicalInsurance = null;
	private MaxMinValuesDTO airplaneMax = null;
	private MaxMinValuesDTO airplanemin = null;

	private List<MaxMinValuesDTO> maxMinValuesDTOs = new ArrayList<MaxMinValuesDTO>();

	private Integer maxminId;

	@PostConstruct
	public void init() {

		Screen screen = (Screen) HttpJSFUtils.getSession().getAttribute(
				"screen");
		switch (screen.getScreenMode()) {
		case 0:
			screenMode = UIOperation.ADD;
			load();
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
				.logPortalInfo(" start update method of MaxMinSingleBean ");
		boolean isValid = validate();
		if (isValid) {

			try {

				maxMinService.updateMaxsMinsValues(maxMinValuesDTOs,
						getUserName());
				screenMode = UIOperation.VIEW;
				operationStatus = Status.SUCCESS;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				operationStatus = Status.FAIL;
				operationMessage = "Invalid Data";
				loggerService.logPortalError("can't update airlineDTO ", e);
			}

		} else {
			// Error Message
			screenMode = UIOperation.UPDATE;
			operationStatus = Status.FAIL;
			operationMessage = "This AirLine already exist. ";
		}

		loggerService.logPortalInfo(" end update method of MaxMinSingleBean ");

	}

	@Override
	public void update() {
	}

	public void uploadNotification() {

		// notification.sendNotificationInvoiceUploaded();
		System.out.println("Mail Sent");
	}

	public void maxinsurancePeriod() {
		// notification.sendNotificationMaxInsurancePeriod();
	}

	public void unknownHotls() {

		// notification.sendNotificationNotificationReservationUnknownHotel();
		System.out.println("PlZ Check Your Mail");

	}

	public void maxVisaPeriod() {

		// notification.sendNotificationMaxVisaPeriod();
		System.out.println("PlZ Check Your Mail");

	}

	public void maxAirLineTicket() {

		// notification.sendNotificationMaxAirLineTicket();
		System.out.println("PlZ Check Your Mail");

	}

	public void minAirLineTicket() {

		// notification.sendNotificationMinAirLineTicket();
		System.out.println("PlZ Check Your Mail");

	}

	public void hotelRateAbovePreNegotiatedRates() {

		// notification.sendNotificationHotelRateAbovePreNegotiatedRates();
		System.out.println("PlZ Check Your Mail");

	}

	public void hotelRateBelowPreNegotiatedRates() {

		// notification.sendNotificationHotelRateBelowPreNegotiatedRates();
		System.out.println("PlZ Check Your Mail");

	}

	@Override
	public void load() {

		loggerService.logPortalInfo(" start load method of MaxMinSingleBean ");
		maxMinValuesDTOs = maxMinService.getAllMaxMinValuess(getUserName());
		System.out.println("maxMinValuesDTOs  ==  " + maxMinValuesDTOs.size());

		try {

			visa = maxMinValuesDTOs.get(0);
			medicalInsurance = maxMinValuesDTOs.get(1);
			airplaneMax = maxMinValuesDTOs.get(2);
			airplanemin = maxMinValuesDTOs.get(3);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logPortalError("can't load  ", e);
		}
		loggerService.logPortalInfo(" end load method of MaxMinSingleBean ");

	}

	@Override
	public boolean validate() {

		return true;
	}

	public MaxMinValuesDTO getVisa() {
		return visa;
	}

	public void setVisa(MaxMinValuesDTO visa) {
		this.visa = visa;
	}

	public MaxMinValuesDTO getMedicalInsurance() {
		return medicalInsurance;
	}

	public void setMedicalInsurance(MaxMinValuesDTO medicalInsurance) {
		this.medicalInsurance = medicalInsurance;
	}

	public MaxMinValuesDTO getAirplaneMax() {
		return airplaneMax;
	}

	public void setAirplaneMax(MaxMinValuesDTO airplaneMax) {
		this.airplaneMax = airplaneMax;
	}

	public MaxMinValuesDTO getAirplanemin() {
		return airplanemin;
	}

	public void setAirplanemin(MaxMinValuesDTO airplanemin) {
		this.airplanemin = airplanemin;
	}

	public Integer getMaxminId() {
		return maxminId;
	}

	public void setMaxminId(Integer maxminId) {
		this.maxminId = maxminId;
	}

}
