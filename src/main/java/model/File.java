package model;

public class File {
private String transactionReference;
private String accid;
private String statementNo;
private String date;
private String currency;
private float startingAmount;
private float closingAmount;
private String closingDate;
private java.sql.Timestamp time;
private String filename;
public String getTransactionReference() {
	return transactionReference;
}
public void setTransactionReference(String transactionReference) {
	this.transactionReference = transactionReference;
}
public String getAccid() {
	return accid;
}
public void setAccid(String accid) {
	this.accid = accid;
}
public String getStatementNo() {
	return statementNo;
}
public void setStatementNo(String statementNo) {
	this.statementNo = statementNo;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getCurrency() {
	return currency;
}
public void setCurrency(String currency) {
	this.currency = currency;
}
public float getStartingAmount() {
	return startingAmount;
}
public void setStartingAmount(float startingAmount) {
	this.startingAmount = startingAmount;
}
public float getClosingAmount() {
	return closingAmount;
}
public void setClosingAmount(float closingAmount) {
	this.closingAmount = closingAmount;
}
public String getClosingDate() {
	return closingDate;
}
public void setClosingDate(String closingDate) {
	this.closingDate = closingDate;
}
public java.sql.Timestamp getTime() {
	return time;
}
public void setTime(java.sql.Timestamp time) {
	this.time = time;
}
public String getFilename() {
	return filename;
}
public void setFilename(String filename) {
	this.filename = filename;
}

}
