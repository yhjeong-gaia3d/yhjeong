package tdd.study.password;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

class PasswordStrengthMeterTest {
	
	private PasswordStrengthMeter meter = new PasswordStrengthMeter();
	
	private void assertStrength(String password, PasswordStrength expStr) {
		PasswordStrength result = meter.meter(password);
		assertEquals(expStr, result);
	}
	
	@Test
	@DisplayName("암호가 모든 조건을 충족하면 암호 강도는 강함이어야 함")
	void testMeetAllCriteriaThenStrong() {
		assertStrength("ab12!@AB", PasswordStrength.STRONG);
		assertStrength("abc1!Add", PasswordStrength.STRONG);
	}
	
	@Test
	@DisplayName("암호가 8글자 미만이고 나머지 조건을 중족하면 암호 강도는 보통이어야 함")
	void testMeetOtherCriteriaExceptForLengthThenNormal() {
		assertStrength("ab12!@A", PasswordStrength.NORMAL);
		assertStrength("Ab12!c", PasswordStrength.NORMAL);
	}
	
	@Test
	@DisplayName("숫자를 포함하지 않고 나머지 조건을 중족하면 암호 강도는 보통이어야 함")
	void testMeetOtherCriteriaExceptForNumberThenNormal() {
		assertStrength("ab!@Aasdf", PasswordStrength.NORMAL);
		assertStrength("Ab@#$oer!c", PasswordStrength.NORMAL);
	}
	
	@Test
	@DisplayName("대문자를 포함하지 않고 나머지 조건을 중족하면 암호 강도는 보통이어야 함")
	void testMeetOtherCriteriaExceptForUppercaseThenNormal() {
		assertStrength("ab!@1125asdf", PasswordStrength.NORMAL);
		assertStrength("123b@#$oer!c", PasswordStrength.NORMAL);
	}
	
	@Test
	@DisplayName("길이가 8글자 이상인 조건만 충총하는 경우 암호 강도는 약함이어야 함")
	void testMeetOnlyLengthCriteriaThenWeak() {
		assertStrength("abcdefghi", PasswordStrength.WEAK);
	}
	
	@Test
	@DisplayName("숫자 포함 조건만 충총하는 경우 암호 강도는 약함이어야 함")
	void testMeetOnlyNumberCriteriaThenWeak() {
		assertStrength("123456", PasswordStrength.WEAK);
	}
	
	@Test
	@DisplayName("대문자 포함 조건만 충총하는 경우 암호 강도는 약함이어야 함")
	void testMeetOnlyUppercaseCriteriaThenWeak() {
		assertStrength("ABCDEFG", PasswordStrength.WEAK);
	}
	
	@Test
	@DisplayName("아무 조건도 충족하지 않는 경우 암호 강도는 약함이어야 함")
	void testMeetNoCriteriaThenWeak() {
		assertStrength("abc", PasswordStrength.WEAK);
	}
	
	@Test
	@DisplayName("null을 입력한 경우 PasswordStrength.INVALID를 리턴")
	void testNullInputThenInvalid() {
		assertStrength(null, PasswordStrength.INVALID);
	}
	
	@Test
	@DisplayName("공백을 입력한 경우 PasswordStrength.INVALID를 리턴")
	void testEmptyInputThenInvalid() {
		assertStrength("", PasswordStrength.INVALID);
	}

}
