package com.truemega.interfaces.notification;

import javax.ejb.Remote;

@Remote
public interface TestNotification {
	public void sendNotificationInvoiceUploaded();

	public void sendNotificationNotificationReservationUnknownHotel();

	public void sendNotificationMaxInsurancePeriod();

	public void sendNotificationMaxVisaPeriod();

	public void sendNotificationMaxAirLineTicket();

	public void sendNotificationMinAirLineTicket();

	public void sendNotificationHotelRateAbovePreNegotiatedRates();

	public void sendNotificationHotelRateBelowPreNegotiatedRates();

}
