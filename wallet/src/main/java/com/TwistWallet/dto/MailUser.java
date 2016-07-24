package com.TwistWallet.dto;

public class MailUser {
	
	    private String userName;
	    private String emailAddress;
	    private String templateName;
	    private String subject;
	    private String ccMailList[];
	    private String bccMailList[];
	    private String ccMailId;
	    private String bccMailId;
	    private String emailAddressList[];
	    private String userActivationCode;
	    private String activationUrl;
	    private String adminName;
	    private String firstName;
	    private String tempPassword;
	    
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getEmailAddress() {
			return emailAddress;
		}
		public void setEmailAddress(String emailAddress) {
			this.emailAddress = emailAddress;
		}
		public String getTemplateName() {
			return templateName;
		}
		public void setTemplateName(String templateName) {
			this.templateName = templateName;
		}
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
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
		public String[] getEmailAddressList() {
			return emailAddressList;
		}
		public void setEmailAddressList(String[] emailAddressList) {
			this.emailAddressList = emailAddressList;
		}
		public String getUserActivationCode() {
			return userActivationCode;
		}
		public void setUserActivationCode(String userActivationCode) {
			this.userActivationCode = userActivationCode;
		}
		public String getActivationUrl() {
			return activationUrl;
		}
		public void setActivationUrl(String activationUrl) {
			this.activationUrl = activationUrl;
		}
		public String getAdminName() {
			return adminName;
		}
		public void setAdminName(String adminName) {
			this.adminName = adminName;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getTempPassword() {
			return tempPassword;
		}
		public void setTempPassword(String tempPassword) {
			this.tempPassword = tempPassword;
		}
	    
	    
}
