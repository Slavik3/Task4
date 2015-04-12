package ua.kharkov.knure.kozlov.Task4;

import static org.junit.Assert.*;
import org.junit.Test;

public class CyclicQueueTest {
	
	@Test
	public void testPush2() throws AlasException {//хвост догнал голову
	CyclicQueue cq=new CyclicQueue(3);
	cq.push(1);
	cq.push(2);
	cq.push(3);
	cq.pop();
	cq.pop();
	cq.push(1);
	cq.push(3);
	}
	
	@Test
	public void testPushExeptionPerepolnOch() throws AlasException {//хвост догнал голову
	boolean isExeption=false;
		CyclicQueue cq=new CyclicQueue(3);
	cq.push(1);
	cq.push(2);
	cq.push(3);
	cq.pop();
	cq.pop();
	cq.push(1);
	cq.push(3);
	try{
	cq.push(3);
	}
	catch(AlasException e){
		isExeption=true;
	}
	assertTrue(isExeption);
	}
	
	@Test
	public void testPopLastElement() throws AlasException {//забр посл эл
		CyclicQueue cq=new CyclicQueue(2);
		cq.push(3);
		cq.pop();
	}
	
	@Test
	public void testPopHeadInEnd() throws AlasException {//
		CyclicQueue cq=new CyclicQueue(3);
		cq.push(3);
		cq.push(3);
		cq.push(3);
		cq.pop();
		cq.pop();		
	}
	
	@Test
	public void testPopHeadInEnd_() throws AlasException {//head = 0;
		CyclicQueue cq=new CyclicQueue(3);
		cq.push(3);
		cq.push(3);
		cq.push(3);
		cq.pop();
		cq.pop();		
		cq.push(3);
		cq.pop();
	}
	
	@Test
	public void testPushPerepoln() throws AlasException {//переполнение очереди
	boolean isExeption=false;
	CyclicQueue cq=new CyclicQueue(2);
	cq.push(1);
	cq.push(2);
	try{
	cq.push(3);
	}
	catch(AlasException e){
		isExeption=true;
	}
	assertTrue(isExeption);
	}
	
	@Test
	public void testPopPust() throws AlasException {//Очередь пуста
	boolean isExeption=false;
	CyclicQueue cq=new CyclicQueue(2);
	
	try{
		cq.pop();
	}
	catch(AlasException e){
		isExeption=true;
	}
	assertTrue(isExeption);
	}
	/*@Test(expected=AlasException.class)
	public void MethodTest() throws AlasException {
		CyclicQueue cq=new CyclicQueue(2);
		cq.pop();
	}*/

	
	
	
	@Test
	public void testMain() throws Exception {
			//System.out.println("\tmain");
			//CyclicQueue.main(new String[]{});
	}
	
}