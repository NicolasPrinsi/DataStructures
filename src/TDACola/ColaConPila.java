package TDACola;

import java.util.Stack;

public class ColaConPila<E> implements Queue<E> {
	private int size;
	private Stack<E> squeue;
	
	public ColaConPila() {
		size=0;
		squeue= new  Stack<E>();
	}
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public E front() throws EmptyQueueException {
		if(this.isEmpty())
			throw new EmptyQueueException("Error, cola vacia");
		return squeue.peek();
	}

	@Override
	public void enqueue(E element) {
		Stack<E>  aux = new Stack<E>();
		while(!squeue.isEmpty()) {
			aux.add(squeue.pop());
		}
		squeue.add(element);
		while(!aux.isEmpty()) {
			squeue.add(aux.pop());
		}
		size++;
	}

	@Override
	public E dequeue() throws EmptyQueueException {
		if(this.isEmpty())
			throw new EmptyQueueException("Error, cola vacia");
		size--;
		return squeue.pop();
	}

}
