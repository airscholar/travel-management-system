package com.truemega.interfaces.notification;

import javax.ejb.Remote;

@Remote
public interface UploadNotificationSender {
	public void sendNotificationInvoiceUploaded(String month, String to);

	public void sendNotificationNotificationReservationUnknownHotel(String month, String to,int fileID );

	public void sendNotificationMaxInsurancePeriod(String month, String to,int fileId);

	public void sendNotificationMaxVisaPeriod(String month, String to,int fileId);

	public void sendNotificationMaxAirLineTicket(String month, String to,int fileId);

	public void sendNotificationMinAirLineTicket(String month, String to,int fileId);

	public void sendNotificationHotelRateAbovePreNegotiatedRates(String month, String to,int fileId,String year);

	public void sendNotificationHotelRateBelowPreNegotiatedRates(String month, String to,int fileId,String year);

}
