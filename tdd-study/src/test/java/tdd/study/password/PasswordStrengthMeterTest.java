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
	@DisplayName("��ȣ�� ��� ������ �����ϸ� ��ȣ ������ �����̾�� ��")
	void testMeetAllCriteriaThenStrong() {
		assertStrength("ab12!@AB", PasswordStrength.STRONG);
		assertStrength("abc1!Add", PasswordStrength.STRONG);
	}
	
	@Test
	@DisplayName("��ȣ�� 8���� �̸��̰� ������ ������ �����ϸ� ��ȣ ������ �����̾�� ��")
	void testMeetOtherCriteriaExceptForLengthThenNormal() {
		assertStrength("ab12!@A", PasswordStrength.NORMAL);
		assertStrength("Ab12!c", PasswordStrength.NORMAL);
	}
	
	@Test
	@DisplayName("���ڸ� �������� �ʰ� ������ ������ �����ϸ� ��ȣ ������ �����̾�� ��")
	void testMeetOtherCriteriaExceptForNumberThenNormal() {
		assertStrength("ab!@Aasdf", PasswordStrength.NORMAL);
		assertStrength("Ab@#$oer!c", PasswordStrength.NORMAL);
	}
	
	@Test
	@DisplayName("�빮�ڸ� �������� �ʰ� ������ ������ �����ϸ� ��ȣ ������ �����̾�� ��")
	void testMeetOtherCriteriaExceptForUppercaseThenNormal() {
		assertStrength("ab!@1125asdf", PasswordStrength.NORMAL);
		assertStrength("123b@#$oer!c", PasswordStrength.NORMAL);
	}
	
	@Test
	@DisplayName("���̰� 8���� �̻��� ���Ǹ� �����ϴ� ��� ��ȣ ������ �����̾�� ��")
	void testMeetOnlyLengthCriteriaThenWeak() {
		assertStrength("abcdefghi", PasswordStrength.WEAK);
	}
	
	@Test
	@DisplayName("���� ���� ���Ǹ� �����ϴ� ��� ��ȣ ������ �����̾�� ��")
	void testMeetOnlyNumberCriteriaThenWeak() {
		assertStrength("123456", PasswordStrength.WEAK);
	}
	
	@Test
	@DisplayName("�빮�� ���� ���Ǹ� �����ϴ� ��� ��ȣ ������ �����̾�� ��")
	void testMeetOnlyUppercaseCriteriaThenWeak() {
		assertStrength("ABCDEFG", PasswordStrength.WEAK);
	}
	
	@Test
	@DisplayName("�ƹ� ���ǵ� �������� �ʴ� ��� ��ȣ ������ �����̾�� ��")
	void testMeetNoCriteriaThenWeak() {
		assertStrength("abc", PasswordStrength.WEAK);
	}
	
	@Test
	@DisplayName("null�� �Է��� ��� PasswordStrength.INVALID�� ����")
	void testNullInputThenInvalid() {
		assertStrength(null, PasswordStrength.INVALID);
	}
	
	@Test
	@DisplayName("������ �Է��� ��� PasswordStrength.INVALID�� ����")
	void testEmptyInputThenInvalid() {
		assertStrength("", PasswordStrength.INVALID);
	}

}
