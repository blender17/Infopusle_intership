package pkg;

import pkg.constraints.IP;
import pkg.constraints.Zero;
import pkg.constraints.PhoneNumber;


public class POJO {

	@PhoneNumber
	private String phoneNumber;

	@IP
	private String ipAddress;

	@Zero
	private double num;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public double getNum() {
		return num;
	}

	public void setNum(double num) {
		this.num = num;
	}
}
