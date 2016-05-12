package com.truemega.services.travelmanagementsystem;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.truemega.dao.GenericDAO;
import com.truemega.dto.UploadedInvoiceFileDTO;
import com.truemega.interfaces.travelmanagementsystem.UploadedInvoicesFileService;
import com.truemega.logger.LoggerService;
import com.truemega.mapping.MappingFactory;

@Stateless
public class UploadedInvoicesFileServiceImpl implements
		UploadedInvoicesFileService {

	@EJB
	private GenericDAO baseDao;

	@EJB
	private MappingFactory mapper;

	private LoggerService loggerService = new LoggerService();

	@Override
	public List<UploadedInvoiceFileDTO> listUploadedInvoiceFiles(String userName) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		loggerService
				.logServiceInfo("Start  listUploadedInvoiceFiles Method with userName  "
						+ userName);
		// String query = "select model from UploadedInvoiceFile model ";

		String query = "select model1 from UploadedInvoiceFile model1 where model1.id in "
				+ " (select DISTINCT model.uploadedInvoiceFileId.id from InvoicesTemp model )";
		return mapper.mapAsList(baseDao.findListByQuery(query),
				UploadedInvoiceFileDTO.class);

	}
}
