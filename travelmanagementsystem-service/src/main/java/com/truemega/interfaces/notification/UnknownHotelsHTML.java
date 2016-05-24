package com.truemega.interfaces.notification;

import java.util.List;

public class UnknownHotelsHTML {
	
	public String buildHTMLTable( List<Object[]> resultUnknownHotels) {
		StringBuilder body = new StringBuilder();

		body.append(buildHTMLTableCSSAndStartTag());
		body.append(buildHTMLTableHeader());

		for (Object[] data : resultUnknownHotels) {
			body.append(buildHTMLTableRow(data));
		}
		body.append("</table>");
		return body.toString();
	}

	private String buildHTMLTableCSSAndStartTag() {
		StringBuilder body = new StringBuilder();
		body.append("<head><style> table, th, td { border: 1px solid black; border-collapse: collapse; } th, td {padding: 5px;text-align: left;}</style></head>");
		body.append("<table style=\'width:100%\'>");

		return body.toString();
	}

	private String buildHTMLTableHeader() {
		StringBuilder body = new StringBuilder();
		body.append("<tr>");
		body.append("<th>Invoice Order</th>");
		body.append("<th>Invoice Number</th>");
		body.append("<th>Supplier Name</th>");
		body.append("</tr>");

		return body.toString();
	}

	private String buildHTMLTableRow(Object[] data){
		StringBuilder body = new StringBuilder();
		body.append("<tr>");

		body.append(buildHTMLTableTd(data[0].toString()));
		body.append(buildHTMLTableTd(data[1].toString()));
		body.append(buildHTMLTableTd(data[2].toString()));
		body.append("</tr>");
		return body.toString();
	}

	private String buildHTMLTableTd(String tdValue) {

		return "<td>" + tdValue + "</td>";
	}

}
