package com.truemega.services.travelmanagementsystem;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.truemega.dao.GenericDAO;
import com.truemega.dto.InvoicesDTO;
import com.truemega.interfaces.travelmanagementsystem.InvoicesService;
import com.truemega.logger.LoggerService;
import com.truemega.mapping.MappingFactory;

@Stateless
public class InvoicesServiceImpl implements InvoicesService {

	@EJB
	private GenericDAO baseDao;

	@EJB
	private MappingFactory mapper;

	private LoggerService loggerService = new LoggerService();

	@Override
	public List<InvoicesDTO> listInvoicesByFileID(Integer fileID,
			String userName) throws Exception {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		loggerService
				.logServiceInfo("Start  listInvoicesByFileID Method with userName  "
						+ userName);

		String query = "select model1 from Invoices model1 where model1.uploadedInvoiceFileId.id = "
				+ fileID + " ORDER BY model1.invoiceOrder ";

		return mapper.mapAsList(baseDao.findListByQuery(query),
				InvoicesDTO.class);

	}

}
