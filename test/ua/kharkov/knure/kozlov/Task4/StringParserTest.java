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
public class StringParserTest {

	private String s;// вход
	private double expectedValue;// ожидаемое значение
	private Class<Throwable> expectedException;

	public StringParserTest(String s, double expectedValue,
			Class<Throwable> expectedException) {
		this.s = s;
		this.expectedValue = expectedValue;
		this.expectedException = expectedException;
	}

	@Parameters
	public static Collection<?> getParameters() {
		Collection<Object[]> data = new ArrayList<Object[]>();
		data.add(new Object[] { "qwerty34.34abcd", 34.34, null });
		data.add(new Object[] { "qwe-34.34.34abcd", -34.34, null });
		data.add(new Object[] { "+17.-9abc", 17.0, null });
		data.add(new Object[] { "abc-.34abcd", -0.34, null });
		data.add(new Object[] { "2.3.4abcd", 2.3, null });
		data.add(new Object[] { "-2.3.4abcd", -2.3, null });
		data.add(new Object[] { "34a0.2bcd", 34.0, null });
		data.add(new Object[] { "abc+.34abcd", 0.34, null });
		data.add(new Object[] { "abc+.34+43abcd", 0.34, null });
		data.add(new Object[] { "abc", 0, AlasException.class });
		data.add(new Object[] { "-.", 0, AlasException.class });
		data.add(new Object[] { "-.abc0", 0.0, null });
		return data;
	}

	@BeforeClass
	public static void setUpGlobal() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
	}

	@Test
	public void testParse() throws AlasException {
		System.out.println("\ttest\t-->\t[" + s + ", " + expectedValue + ", "
				+ expectedException + "]");

		if (expectedException != null) {
			// функциональность негативного теста
			try {
				// вызов метод должен привести к выбросу исключения
				//
				StringParser.parse(s);
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
			double actual = StringParser.parse(s);
			Assert.assertEquals(expectedValue, actual, 0.00000000001);
		}

	}

	@Test
	public void testParseRE() throws Exception {
		System.out.println("\ttest\t-->\t[" + s + ", " + expectedValue + ", "
				+ expectedException + "]");

		if (expectedException != null) {
			// функциональность негативного теста
			try {
				// вызов метод должен привести к выбросу исключения
				//
				StringParser.parseRE(s);
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
			double actual = StringParser.parseRE(s);
			Assert.assertEquals(expectedValue, actual, 0.0000000000001);
		}
	}		

}
