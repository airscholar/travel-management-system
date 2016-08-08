package com.truemega.services.travelmanagementsystem;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.truemega.dao.GenericDAO;
import com.truemega.dto.InvoicesTempDTO;
import com.truemega.interfaces.travelmanagementsystem.InvoicesTempService;
import com.truemega.logger.LoggerService;
import com.truemega.mapping.MappingFactory;

@Stateless
public class InvoicesTempServiceImpl implements InvoicesTempService {

	@EJB
	private GenericDAO baseDao;

	@EJB
	private MappingFactory mapper;

	private LoggerService loggerService = new LoggerService();

	@Override
	public List<InvoicesTempDTO> listInvoicesTempsByFileID(Integer fileID,
			String userName) throws Exception {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		loggerService
				.logServiceInfo("Start  listInvoicesTempsByFileID Method with userName  "
						+ userName);

		String query = "select model1 from InvoicesTemp model1 where model1.uploadedInvoiceFileId.id = "
				+ fileID
				+ " and (model1.generalMandatoryValid = false or  model1.invoiceDateRangeValid = false "
				+ "  or  model1.airMandatoryValid = false or  model1.otherMandatoryValid = false "
				+ "  or  model1.interDomValid = false or  model1.numberOfNightsValid = false "
				+ "  or  model1.totalAmountValid = false or  model1.hotelMandatoryValid = false "
				+ "  or  model1.serviceTypeValid = false or  model1.serviceDescriptionValid = false"
				+ "  or  model1.supplierNameValid = false or  model1.airlineValid = false"
				+ " or  model1.roomTypeValid = false or  model1.invoiceNumberValid = false  "
				+ " or model1.ratesCombinationValid = false  ) "
				+ " ORDER BY model1.invoicesTempPK.invoiceOrder ";

		return mapper.mapAsList(baseDao.findListByQuery(query),
				InvoicesTempDTO.class);

	}

}
