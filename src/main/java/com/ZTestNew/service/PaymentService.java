package com.ZTestNew.service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.ZTestNew.model.Payment;
import com.google.gson.*;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Payments")
public class PaymentService {
	Payment itemObj = new Payment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayemnts() {
		return itemObj.readPayments();
	}

	@GET
	@Path("/{user}/{id}")
	@Produces(MediaType.TEXT_HTML)
	public String readPaymentsUser(@PathParam("id") String id, @PathParam("user") String user) {
		return itemObj.readPaymentsUser(id, user);
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(String paymentData) {

		JsonObject itemObject = new JsonParser().parse(paymentData).getAsJsonObject();

		int doctorID = itemObject.get("doctorID").getAsInt();
		int hospitalID = itemObject.get("hospitalID").getAsInt();
		int patientID = itemObject.get("patientID").getAsInt();
		String total = itemObject.get("total").getAsString();

		String output = itemObj.insertPayment(patientID, hospitalID, doctorID, total);
		return output;
	}

	@POST
	@Path("/paymentDetails/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPaymentDetails(String paymentDetailsData) {

		JsonObject itemObject = new JsonParser().parse(paymentDetailsData).getAsJsonObject();

		String cardNo = itemObject.get("cardNo").getAsString();
		String cvv = itemObject.get("cvv").getAsString();
		String patientID = itemObject.get("patientID").getAsString();

		String output = itemObj.insertPaymentDetails(cardNo, cvv, patientID);
		return output;
	}

	@PUT
	@Path("/paymentDetails/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePaymentDetails(String paymentDetailsData) {

		JsonObject itemObject = new JsonParser().parse(paymentDetailsData).getAsJsonObject();

		String cardNo = itemObject.get("cardNo").getAsString();
		String cvv = itemObject.get("cvv").getAsString();
		String patientID = itemObject.get("patientID").getAsString();
		int ppymentDetailsId = itemObject.get("ppymentDetailsId").getAsInt();

		String output = itemObj.updatePaymentDetails(cardNo, cvv, patientID, ppymentDetailsId);
		return output;

	}

	@GET
	@Path("/paymentDetails/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String readPaymentsDetailsUser(@PathParam("id") String id) {
		return itemObj.readPaymentsDetailsUser(id);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public String deletePayment(@PathParam("id") String id) {

		String output = itemObj.deleteItem(id);
		return output;
	}
}
