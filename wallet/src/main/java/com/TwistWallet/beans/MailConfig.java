package com.TwistWallet.beans;

/**
 * @author rohitc
 *
 */
public class MailConfig {
	
	private String ccMailId;
    private String bccMailId;
    private String fromMailId;
    private String ccMailList[];
    private String bccMailList[];
	public String getCcMailId() {
		return ccMailId;
	}
	public void setCcMailId(String ccMailId) {
		this.ccMailId = ccMailId;
	}
	public String getBccMailId() {
		return bccMailId;
	}
	public void setBccMailId(String bccMailId) {
		this.bccMailId = bccMailId;
	}
	public String getFromMailId() {
		return fromMailId;
	}
	public void setFromMailId(String fromMailId) {
		this.fromMailId = fromMailId;
	}
	public String[] getCcMailList() {
		return ccMailList;
	}
	public void setCcMailList(String[] ccMailList) {
		this.ccMailList = ccMailList;
	}
	public String[] getBccMailList() {
		return bccMailList;
	}
	public void setBccMailList(String[] bccMailList) {
		this.bccMailList = bccMailList;
	}
    
    
}
