package TDACola;

public class ArrayQueue<E> implements Queue<E> {

	private E[] arreglo;
	private int size;
	private int r;
	
	public ArrayQueue(int c) {
		arreglo = (E[]) new Object[c];
		size=0;
		r=0;
	}
	public ArrayQueue() {
		this(1000);
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
		if (this.isEmpty())
			throw new EmptyQueueException("Error, cola vacia");
		return arreglo[0];
	}

	@Override
	public void enqueue(E element) {
		if(r<=arreglo.length-1) {
			arreglo[r]=element;
			size++;
			r++;
		}
	}

	@Override
	public E dequeue() throws EmptyQueueException {
		if(this.isEmpty())
			throw new EmptyQueueException("Error, cola vacia");
		E toreturn=null;
		if(this.size()==1) {
			toreturn=arreglo[0];
			arreglo[0]=null;
			size=0;
			r=0;
		}else {
			toreturn = arreglo[0];
			for(int i=0; i<r-1;i++) {
				arreglo[i]=arreglo[i+1];
			}
			size--;
			r--;
		}
		return toreturn;
	}
	
	public String toString() {
		String toreturn="";
		for(int i=0;i<=r-1;i++) {
			toreturn=toreturn+arreglo[i]+" ";
		}
		return toreturn;
	}
}
