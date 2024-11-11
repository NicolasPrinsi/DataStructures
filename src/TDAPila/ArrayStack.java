package TDAPila;

public class ArrayStack<E> implements Stack<E>{
	private int size;
	private E[]  stack;
	
	public ArrayStack(int cap) {
		size=0;
		stack= (E[]) new Object[cap];
	}
	public ArrayStack() {
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
	public E top() throws EmptyStackException {
		if(this.isEmpty())
			throw new EmptyStackException("Error, pila vacia");
		return stack[size-1];
	}

	@Override
	public void push(E element) {
		if(size<stack.length) {
			stack[size]=element;
			size++;
		}else {
			E[] temp = (E[]) new Object[stack.length*2];
			for (int i=0; i<stack.length;i++) {
				temp[i]=stack[i];
			}
			temp[size]=element;
			size++;
			stack=temp;
		}
	}

	@Override
	public E pop() throws EmptyStackException {
		if(this.isEmpty())
			throw new EmptyStackException("Error, pila vacia");
		E toreturn = stack[size-1];
		stack[size-1]=null;
		size--;
		return toreturn;
	}
	
	public String toString() {
		String toreturn="";
		for (int i=0;i<size;i++) {
			toreturn=toreturn+stack[i]+" ";
		}
		return toreturn;
	}
}
