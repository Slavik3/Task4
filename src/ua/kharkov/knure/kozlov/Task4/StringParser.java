package ua.kharkov.knure.kozlov.Task4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс содержит статические методы для разбора строки и извлечения из нее
 * вещественного числа.
 * 
 * 
 * 
 */
public class StringParser {

	/**
	 * Извлекает из строки вещественное число<br/>
	 * (вариант без использования регулярных выражений).
	 * 
	 * @param s
	 *            входная строка.
	 * @return первое (слева) вещественное число, которое содержится в строке.
	 * @throws AlasException
	 *             если строка не содержит вещественное число.
	 */
	public static double parse(String s) throws AlasException {
		int i;
		for (i = 0; i < s.length(); i++) {// первая ли цифра
			char simvol = s.charAt(i);// charAt мет кот позв вернуть i-тый симв
										// в стр
			if (simvol >= '0' && simvol <= '9')
				break;
		}
		if (i == s.length())// если не сработал break
			throw new AlasException("не нашли цыфр");
		StringBuilder sb = new StringBuilder();// пустой набор символов
		if (i > 0 && s.charAt(i - 1) == '-') {// если перед числом -
			sb.append(s.charAt(i - 1));// забрам -
		}

		if (i > 0 && s.charAt(i - 1) == '.') {// если есть точка перед цифрами												
			if (s.charAt(i - 2) == '-') {
				sb.append(s.charAt(i - 2));// добавляем - append слияние строк				
			}
			sb.append(s.charAt(i - 1));// возврат i-го символа в строку и слияние										
		}

		for (int j = i; j < s.length(); j++) {// от позиции цифры кот нашли
			char simvol = s.charAt(j);
			if (simvol >= '0' && simvol <= '9')// если цифра
				sb.append(simvol);// в результат

			else if (simvol == '.' && sb.indexOf(".") == -1)// если точка и точку не добавляли в результат
				sb.append(simvol);
			else
				break;// если не . и не цифра
		}
		return Double.parseDouble(sb.toString());// конвертирует стр в число
	}

	/**
	 * Извлекает из строки вещественное число<br/>
	 * (вариант с регулярным выражением).
	 * 
	 * @param s
	 *            входная строка.
	 * @return первое (слева) вещественное число, которое содержится в строке.
	 * @throws AlasException
	 *             если строка не содержит вещественное число.
	 */
	public static double parseRE(String s) throws AlasException {
		Pattern p = Pattern.compile("[-]?[.]?[0-9]+([.]?[0-9]+)?");// созд об кот будут использовать для поиска													
		Matcher match = p.matcher(s);// Об кот будет искать, стр в кот искать
		boolean rez = match.find();// find найти
		if (rez == false)
			throw new AlasException("преобразование невозможно");
		else {
			String st = match.group();
			return Double.parseDouble(st);
		}
	}

	/**
	 * Преобразовывает строку в вещественное число.<br/>
	 * Замечание: реализовывать данный метод не обязательно, можно
	 * воспользоваться Double.parseDouble, см. замечание к условию задачи.
	 * 
	 * @param s
	 *            строковое представление числа (десятичная форма записи без
	 *            использования экспоненты)
	 * @return число, которое содержится во входной строке
	 * @throws NumberFormatException
	 *             если входная строка не содержит строковое представление
	 *             числа.
	 */
	public static double parseDouble(String s) {
		double sum = 0;
		int razradnost;
		boolean isNegative = false;
		int posOfPoint = s.indexOf(".");
		if (posOfPoint == -1)
			throw new NumberFormatException("нет точки");
		razradnost = posOfPoint - 1;
		if (posOfPoint != 0) {
			int i = 0;
			if (s.charAt(i) == '-') {
				isNegative = true;
				i++;
				razradnost--;
			}
			for (; i < posOfPoint; i++) {
				char simbol = s.charAt(i);// charAt возвр символ с указанной
											// позиции
				if ((simbol < '0' || simbol > '9'))
					throw new NumberFormatException("в строке присутствует символ");
				int tmp = simbol - '0';// преобразуем символ содержащий цифру в цифру										
				sum = sum + tmp * Math.pow(10, razradnost);
				razradnost--;
			}
		}

		for (int i = posOfPoint + 1, j = 0; i < s.length(); i++) {// для десятичн 
			char simbol = s.charAt(i);// charAt возвр символ с указанной позиции
			if ((simbol < '0' || simbol > '9'))// доп
				throw new NumberFormatException("в строке присутствует символ");
			int tmp = simbol - '0';// преобразуем символ содержащий цифру в цифру									
			sum = sum + tmp * Math.pow(10, --j);
		}		
		return isNegative ? -sum : sum;// if isNegative==true return -sum else										
	}

	/**
	 * Точка входа. Демонстрация работы класса.
	 * 
	 * @throws AlasException
	 */
	public static void main(String[] args) throws AlasException {
		StringParser sp = new StringParser();
		
		System.out.print(sp.parseRE("11.fhjg"));
	}
}

