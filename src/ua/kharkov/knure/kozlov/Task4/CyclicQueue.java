package ua.kharkov.knure.kozlov.Task4;

/**
 * ����������� �������.
 * 
 */
public class CyclicQueue {

	private Object[] array;// array - ������ ��������

	private int tail;// ������ ��� ��������� �����
	private int head;
	private int n;// ������ �������

	public CyclicQueue(int n) {// �����������
		array = new Object[n];// ��������������
		tail = 0;
		head = -1;//-1
		this.n = n;
	}

	/**
	 * ��������� ������� � ����� �������.
	 * 
	 * @param element
	 *            �������, ������� ����� ��������
	 * @throws AlasException
	 *             ���� ���������� �������� ������� (������� ���������).
	 */
	public void push(Object element) throws AlasException {
		if (tail == n) {// ������ ��� ��������� ����� �� ���������
			if (head == 0)// ������ ���������, ������ �� �����
				throw new AlasException("������� �����������");
			else {// ���� �� ��������
				tail = 0;
				array[tail] = element;
			}
		}

		else {// ����� ������ ���

			if (head == tail)// ���� ����� ������ ������

				throw new AlasException("����� ������ ������");

			array[tail] = element;

			if (head == -1)// ���� ������ ���
				head = 0;
		}
		tail++;// ����� ����� ������
	}

	/**
	 * ��������� ������� �� ����� ������� (������ ���).
	 * 
	 * @return ����������� �������.
	 * @throws AlasException
	 *             ���� ���������� �������� ������� �� ������� (������� �����).
	 */
	public Object pop() throws AlasException {
		if (head == -1)// ���� ������
			throw new AlasException("������� �����");

		Object tmp = array[head];
		if (head+1 == tail) {//�� ������ ���
			tail = 0;
			head = -1;
		}

		else if (head == n - 1 && tail !=n) {//������ � �����		
				head = 0;
		}
		else {
			head++;
		}
		return tmp;
	}

	/**
	 * ����� �����. ������������ ������ ������.
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
