package tdd.study.password;

public class PasswordStrengthMeter {

	public PasswordStrength meter(String password) {
		
		if (password == null || password.isEmpty()) {
			return PasswordStrength.INVALID;
		}
		
		int metCount = 0;
		boolean enoughLength = password.length() >= 8;
		boolean containsNum = isContainsNumberCriteria(password);
		boolean containsUppercase = isContainsUppercaseCriteria(password);
		
		if (enoughLength) metCount++;
		if (containsNum) metCount++;
		if (containsUppercase) metCount++;
		
		if (metCount <= 1) return PasswordStrength.WEAK;
		if (metCount == 2) return PasswordStrength.NORMAL;
		
		return PasswordStrength.STRONG;
	}
	
	private boolean isContainsNumberCriteria(String password) {
		for (char ch : password.toCharArray()) {
			if (ch >= '0' && ch <= '9') {
				return true;
			}
		}
		return false;
	}
	
	private boolean isContainsUppercaseCriteria(String password) {
		for (char ch : password.toCharArray()) {
			if (Character.isUpperCase(ch)) {
				return true;
			}
		}
		return false;
	}
	
}
