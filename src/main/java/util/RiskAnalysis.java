package util;

public class RiskAnalysis {
	private float requestedAmount;
	private float averageAmount;
	private float minimalAmount;
	private int noOfTransactionsToDifferentIbans;
	private int noOfTransactionWithNoDescription;
	private String response;
	public static final String RejectMessage = "The risk level is high. It is recommended to reject the loan request.";
	public static final String AproveMessage = "The risk level is low. It is recommended to accept the loan request";

	public RiskAnalysis() {
		// TODO Auto-generated constructor stub
	}

	public void estimateRisk() {
		this.response = AproveMessage;
		boolean isFraudalent = false;
		if (requestedAmount > averageAmount) {
			this.response = RejectMessage + "Average amount in bank account is not enough. ";
			isFraudalent = true;
		}
		if (requestedAmount > minimalAmount) {
			isFraudalent = true;
			if (this.response.contains(RejectMessage)) {
				this.response += "Minimal amount in bank account is not enough. ";
			} else {
				this.response = RejectMessage + "Minimal amount in bank account is not enough. ";
			}
		}
		if (noOfTransactionsToDifferentIbans > 0) {
			isFraudalent = true;
			if (this.response.contains(RejectMessage)) {
				this.response += "Transactions to the same company but with a different IBAN are present. ";
			} else {
				this.response = RejectMessage
						+ "Transactions to the same company but with a different IBAN are present. ";
			}
		}
		if (noOfTransactionWithNoDescription > 0) {
			isFraudalent = true;
			if (this.response.contains(RejectMessage)) {
				this.response += "Transactions with no description are present. ";
			} else {
				this.response = RejectMessage + "Transactions with no description are present. ";
			}
		}

	}

	public float getAverageAmount() {
		return averageAmount;
	}

	public void setAverageAmount(float averageAmount) {
		this.averageAmount = averageAmount;
	}

	public float getMinimalAmount() {
		return minimalAmount;
	}

	public void setMinimalAmount(float minimalAmount) {
		this.minimalAmount = minimalAmount;
	}

	public int getNoOfTransactionsToDifferentIbans() {
		return noOfTransactionsToDifferentIbans;
	}

	public void setNoOfTransactionsToDifferentIbans(int noOfTransactionsToDifferentIbans) {
		this.noOfTransactionsToDifferentIbans = noOfTransactionsToDifferentIbans;
	}

	public int getNoOfTransactionWithNoDescription() {
		return noOfTransactionWithNoDescription;
	}

	public void setNoOfTransactionWithNoDescription(int noOfTransactionWithNoDescription) {
		this.noOfTransactionWithNoDescription = noOfTransactionWithNoDescription;
	}

	public String getResponse() {
		return response;
	}

	public float getRequestedAmount() {
		return requestedAmount;
	}

	public void setRequestedAmount(float requestedAmount) {
		this.requestedAmount = requestedAmount;
	}

}
