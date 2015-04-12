package ua.kharkov.knure.kozlov.Task4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ����� �������� ����������� ������ ��� ������� ������ � ���������� �� ���
 * ������������� �����.
 * 
 * 
 * 
 */
public class StringParser {

	/**
	 * ��������� �� ������ ������������ �����<br/>
	 * (������� ��� ������������� ���������� ���������).
	 * 
	 * @param s
	 *            ������� ������.
	 * @return ������ (�����) ������������ �����, ������� ���������� � ������.
	 * @throws AlasException
	 *             ���� ������ �� �������� ������������ �����.
	 */
	public static double parse(String s) throws AlasException {
		int i;
		for (i = 0; i < s.length(); i++) {// ������ �� �����
			char simvol = s.charAt(i);// charAt ��� ��� ���� ������� i-��� ����
										// � ���
			if (simvol >= '0' && simvol <= '9')
				break;
		}
		if (i == s.length())// ���� �� �������� break
			throw new AlasException("�� ����� ����");
		StringBuilder sb = new StringBuilder();// ������ ����� ��������
		if (i > 0 && s.charAt(i - 1) == '-') {// ���� ����� ������ -
			sb.append(s.charAt(i - 1));// ������ -
		}

		if (i > 0 && s.charAt(i - 1) == '.') {// ���� ���� ����� ����� �������												
			if (s.charAt(i - 2) == '-') {
				sb.append(s.charAt(i - 2));// ��������� - append ������� �����				
			}
			sb.append(s.charAt(i - 1));// ������� i-�� ������� � ������ � �������										
		}

		for (int j = i; j < s.length(); j++) {// �� ������� ����� ��� �����
			char simvol = s.charAt(j);
			if (simvol >= '0' && simvol <= '9')// ���� �����
				sb.append(simvol);// � ���������

			else if (simvol == '.' && sb.indexOf(".") == -1)// ���� ����� � ����� �� ��������� � ���������
				sb.append(simvol);
			else
				break;// ���� �� . � �� �����
		}
		return Double.parseDouble(sb.toString());// ������������ ��� � �����
	}

	/**
	 * ��������� �� ������ ������������ �����<br/>
	 * (������� � ���������� ����������).
	 * 
	 * @param s
	 *            ������� ������.
	 * @return ������ (�����) ������������ �����, ������� ���������� � ������.
	 * @throws AlasException
	 *             ���� ������ �� �������� ������������ �����.
	 */
	public static double parseRE(String s) throws AlasException {
		Pattern p = Pattern.compile("[-]?[.]?[0-9]+([.]?[0-9]+)?");// ���� �� ��� ����� ������������ ��� ������													
		Matcher match = p.matcher(s);// �� ��� ����� ������, ��� � ��� ������
		boolean rez = match.find();// find �����
		if (rez == false)
			throw new AlasException("�������������� ����������");
		else {
			String st = match.group();
			return Double.parseDouble(st);
		}
	}

	/**
	 * ��������������� ������ � ������������ �����.<br/>
	 * ���������: ������������� ������ ����� �� �����������, �����
	 * ��������������� Double.parseDouble, ��. ��������� � ������� ������.
	 * 
	 * @param s
	 *            ��������� ������������� ����� (���������� ����� ������ ���
	 *            ������������� ����������)
	 * @return �����, ������� ���������� �� ������� ������
	 * @throws NumberFormatException
	 *             ���� ������� ������ �� �������� ��������� �������������
	 *             �����.
	 */
	public static double parseDouble(String s) {
		double sum = 0;
		int razradnost;
		boolean isNegative = false;
		int posOfPoint = s.indexOf(".");
		if (posOfPoint == -1)
			throw new NumberFormatException("��� �����");
		razradnost = posOfPoint - 1;
		if (posOfPoint != 0) {
			int i = 0;
			if (s.charAt(i) == '-') {
				isNegative = true;
				i++;
				razradnost--;
			}
			for (; i < posOfPoint; i++) {
				char simbol = s.charAt(i);// charAt ����� ������ � ���������
											// �������
				if ((simbol < '0' || simbol > '9'))
					throw new NumberFormatException("� ������ ������������ ������");
				int tmp = simbol - '0';// ����������� ������ ���������� ����� � �����										
				sum = sum + tmp * Math.pow(10, razradnost);
				razradnost--;
			}
		}

		for (int i = posOfPoint + 1, j = 0; i < s.length(); i++) {// ��� �������� 
			char simbol = s.charAt(i);// charAt ����� ������ � ��������� �������
			if ((simbol < '0' || simbol > '9'))// ���
				throw new NumberFormatException("� ������ ������������ ������");
			int tmp = simbol - '0';// ����������� ������ ���������� ����� � �����									
			sum = sum + tmp * Math.pow(10, --j);
		}		
		return isNegative ? -sum : sum;// if isNegative==true return -sum else										
	}

	/**
	 * ����� �����. ������������ ������ ������.
	 * 
	 * @throws AlasException
	 */
	public static void main(String[] args) throws AlasException {
		StringParser sp = new StringParser();
		
		System.out.print(sp.parseRE("11.fhjg"));
	}
}

