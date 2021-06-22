package model;

public class Process {
private String valueDate;
private String entryDate;
private String creditDebit;
private float transactionAmount;
private String customerReference;
private String iban;
private String description;
private String partyname;
public String getValueDate() {
	return valueDate;
}
public void setValueDate(String valueDate) {
	this.valueDate = valueDate;
}
public String getEntryDate() {
	return entryDate;
}
public void setEntryDate(String entryDate) {
	this.entryDate = entryDate;
}
public String getCreditDebit() {
	return creditDebit;
}
public void setCreditDebit(String creditDebit) {
	this.creditDebit = creditDebit;
}
public float getTransactionAmount() {
	return transactionAmount;
}
public void setTransactionAmount(float transactionAmount) {
	this.transactionAmount = transactionAmount;
}
public String getCustomerReference() {
	return customerReference;
}
public void setCustomerReference(String customerReference) {
	this.customerReference = customerReference;
}
public String getIban() {
	return iban;
}
public void setIban(String iban) {
	this.iban = iban;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getPartyname() {
	return partyname;
}
public void setPartyname(String partyname) {
	this.partyname = partyname;
}


}
