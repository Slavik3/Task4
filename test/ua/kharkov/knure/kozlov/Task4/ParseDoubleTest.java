package ua.kharkov.knure.kozlov.Task4;

import ua.kharkov.knure.kozlov.Task4.AlasException;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
@RunWith(Parameterized.class)
public class ParseDoubleTest {

	private String s;// вход
	private double expectedValue;// ожидаемое значение
	private Class<Throwable> expectedException;

	public ParseDoubleTest(String s, double expectedValue,
			Class<Throwable> expectedException) {
		this.s = s;
		this.expectedValue = expectedValue;
		this.expectedException = expectedException;
	}

	@Parameters
	public static Collection<?> getParameters() {
		Collection<Object[]> data = new ArrayList<Object[]>();
		data.add(new Object[] { "22.1", 22.1, null });
		data.add(new Object[] { "-22.1", -22.1, null });
		data.add(new Object[] { "abc", 0, NumberFormatException.class });
		data.add(new Object[] { "11", 0, NumberFormatException.class });
		data.add(new Object[] { "a22.1", 0, NumberFormatException.class });
		data.add(new Object[] { "22.1a", 0, NumberFormatException.class });
		return data;
	}

	@BeforeClass
	public static void setUpGlobal() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
	}

	@Test
	public void testParseDouble() throws AlasException {
		System.out.println("\ttest\t-->\t[" + s + ", " + expectedValue + ", "
				+ expectedException + "]");

		if (expectedException != null) {
			// функциональность негативного теста
			try {
				// вызов метод должен привести к выбросу исключения
				//
				StringParser.parseDouble(s);
				// если метод отработал и выброса исключения не произошло
				// то тест не пройден ("фейлим" тест)
				Assert.fail("Expected exception: " + expectedException);
			} catch (Throwable t) {
				// если исключение произошло проверяем его тип
				// и в случае, если было выброшено исключение
				// но его тип не совпал с ожидаемым, то тест не пройден
				// ("фейлим" тест)
				if (t.getClass() != expectedException)
					Assert.fail("Expected exception: " + expectedException);
			}
		} else {
			// функциональность позитивных тестов
			double actual = StringParser.parseDouble(s);
			Assert.assertEquals(expectedValue, actual, 0.00000000001);
		}

	}
}
