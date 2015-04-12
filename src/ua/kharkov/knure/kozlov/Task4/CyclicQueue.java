package ua.kharkov.knure.kozlov.Task4;

/**
 * Циклическая очередь.
 * 
 */
public class CyclicQueue {

	private Object[] array;// array - массив объектов

	private int tail;// индекс где находится хвост
	private int head;
	private int n;// размер очереди

	public CyclicQueue(int n) {// конструктор
		array = new Object[n];// инициализируем
		tail = 0;
		head = -1;//-1
		this.n = n;
	}

	/**
	 * Добавляет элемент в конец очереди.
	 * 
	 * @param element
	 *            элемент, который будет добавлен
	 * @throws AlasException
	 *             если невозможно добавить элемент (очередь заполнена).
	 */
	public void push(Object element) throws AlasException {
		if (tail == n) {// индекс где находится хвост за последним
			if (head == 0)// некуда вставлять, первый эл занят
				throw new AlasException("Очередь переполнена");
			else {// перв эл свободен
				tail = 0;
				array[tail] = element;
			}
		}

		else {// хвост внутри мас

			if (head == tail)// если хвост догнал голову

				throw new AlasException("хвост догнал голову");

			array[tail] = element;

			if (head == -1)// если первый раз
				head = 0;
		}
		tail++;// сдвиг хвост вперед
	}

	/**
	 * Извлекает элемент из конца очереди (удаляя его).
	 * 
	 * @return извлекаемый элемент.
	 * @throws AlasException
	 *             если невозможно получить элемент из очереди (очередь пуста).
	 */
	public Object pop() throws AlasException {
		if (head == -1)// если первый
			throw new AlasException("Очередь пуста");

		Object tmp = array[head];
		if (head+1 == tail) {//эл больше нет
			tail = 0;
			head = -1;
		}

		else if (head == n - 1 && tail !=n) {//голова в конце		
				head = 0;
		}
		else {
			head++;
		}
		return tmp;
	}

	/**
	 * Точка входа. Демонстрация работы класса.
	 * 
	 * @throws AlasException
	 */
	public static void main(String[] args) throws AlasException {
		CyclicQueue q = new CyclicQueue(1);
		q.push(1);
		q.pop();
		q.push(1);q.push(1);q.push(1);
	}
}
