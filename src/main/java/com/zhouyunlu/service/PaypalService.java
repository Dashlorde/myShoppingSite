package com.zhouyunlu.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.jboss.logging.Logger;
import org.xml.sax.SAXException;

import com.paypal.exception.ClientActionRequiredException;
import com.paypal.exception.HttpErrorException;
import com.paypal.exception.InvalidCredentialException;
import com.paypal.exception.InvalidResponseDataException;
import com.paypal.exception.MissingCredentialException;
import com.paypal.exception.SSLConfigurationException;
import com.paypal.sdk.exceptions.OAuthException;
import com.paypal.svcs.services.AdaptiveAccountsService;
import com.paypal.svcs.types.common.RequestEnvelope;
import com.zhouyunlu.pojo.CartProduct;
import com.zhouyunlu.pojo.Order;
import com.zhouyunlu.pojo.User;

import urn.ebay.api.PayPalAPI.PayPalAPIInterfaceServiceService;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutReq;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutRequestType;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutResponseType;
import urn.ebay.apis.CoreComponentTypes.BasicAmountType;
import urn.ebay.apis.eBLBaseComponents.CurrencyCodeType;
import urn.ebay.apis.eBLBaseComponents.PaymentActionCodeType;
import urn.ebay.apis.eBLBaseComponents.PaymentDetailsItemType;
import urn.ebay.apis.eBLBaseComponents.PaymentDetailsType;
import urn.ebay.apis.eBLBaseComponents.SetExpressCheckoutRequestDetailsType;

public class PaypalService {
	private static Logger log = Logger.getLogger(PaypalService.class);

	public void main(String arg[], User user, float amount, Set<CartProduct> cartProductList) {
		PaypalService pps = new PaypalService();

		try {
			String token = pps.paypalCheckOut(user, amount, cartProductList);
			log.info(
					"Url to redirect to: https://www.sandbox.paypal.com/webscr?cmd=_express-checkout&useraction=commit&token="
							+ token);

		} catch (SSLConfigurationException | InvalidCredentialException | HttpErrorException
				| InvalidResponseDataException | ClientActionRequiredException | MissingCredentialException
				| OAuthException | IOException | InterruptedException | ParserConfigurationException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String paypalCheckOut(User user, float amount, Set<CartProduct> cartProductList)
			throws SSLConfigurationException, InvalidCredentialException, HttpErrorException,
			InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, OAuthException,
			IOException, InterruptedException, ParserConfigurationException, SAXException {
		PaymentDetailsType paymentDetails = new PaymentDetailsType();
		String orderPrice = Float.toString(amount);

		String returnURL = "http://www.zhouyunlu.com/checkOutSuccess.jsp";
		String cancelURL = "http://www.zhouyunlu.com/index.jsp";
		//String returnURL = "http://localhost:8080/myShoppingSite/checkOutSuccess.jsp";
		//String cancelURL = "http://localhost:8080/myShoppingSite/index.jsp";
		
		paymentDetails.setPaymentAction(PaymentActionCodeType.fromValue("Sale"));
		CurrencyCodeType currencyCode = CurrencyCodeType.USD;

		List<PaymentDetailsItemType> items = new ArrayList<PaymentDetailsItemType>();
		for (CartProduct cp : cartProductList) {
			PaymentDetailsItemType item = new PaymentDetailsItemType();
			BasicAmountType amt = new BasicAmountType();
			amt.setCurrencyID(currencyCode);
			amt.setValue(Float.toString(cp.getProduct().getProductPrice()));

			item.setQuantity(cp.getQuantity());
			item.setName(cp.getProduct().getProductName());
			item.setAmount(amt);
			items.add(item);
		}

		paymentDetails.setPaymentDetailsItem(items);
		BasicAmountType orderTotal = new BasicAmountType();
		orderTotal.setCurrencyID(CurrencyCodeType.fromValue("USD"));
		orderTotal.setValue(orderPrice);
		paymentDetails.setOrderTotal(orderTotal);
		List<PaymentDetailsType> paymentDetailsList = new ArrayList<PaymentDetailsType>();
		paymentDetailsList.add(paymentDetails);

		SetExpressCheckoutRequestDetailsType setExpressCheckoutRequestDetails = new SetExpressCheckoutRequestDetailsType();
		setExpressCheckoutRequestDetails.setReturnURL(returnURL);
		setExpressCheckoutRequestDetails.setCancelURL(cancelURL);
		setExpressCheckoutRequestDetails.setCustom(Long.toString(user.getId()));

		setExpressCheckoutRequestDetails.setPaymentDetails(paymentDetailsList);

		SetExpressCheckoutRequestType setExpressCheckoutRequest = new SetExpressCheckoutRequestType(
				setExpressCheckoutRequestDetails);
		setExpressCheckoutRequest.setVersion("104.0");

		SetExpressCheckoutReq setExpressCheckoutReq = new SetExpressCheckoutReq();
		setExpressCheckoutReq.setSetExpressCheckoutRequest(setExpressCheckoutRequest);

		Map<String, String> sdkConfig = new HashMap<String, String>();
		sdkConfig.put("mode", "sandbox");
		sdkConfig.put("acct1.UserName", "zhouyunlu0216-facilitator_api1.gmail.com");
		sdkConfig.put("acct1.Password", "TDVLL7M2MVEQLNPZ");
		sdkConfig.put("acct1.Signature", "AFcWxV21C7fd0v3bYYYRCpSSRl31AWGtrq9X841jJfXEvxtz0WDLXyKX");
		// sdkConfig.put("acct1.UserName", seller.getPaypalUsername());
		// sdkConfig.put("acct1.Password", seller.getPaypalPassword());
		// sdkConfig.put("acct1.Signature", seller.getSignature());
		PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(sdkConfig);
		SetExpressCheckoutResponseType setExpressCheckoutResponse = service.setExpressCheckout(setExpressCheckoutReq);

		return "redirect:https://www.sandbox.paypal.com/webscr?cmd=_express-checkout&useraction=commit&token="
				+ setExpressCheckoutResponse.getToken();
	}

}
