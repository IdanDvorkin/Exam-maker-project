package Model;

import java.io.Serializable;
import java.util.Arrays;

public class Set<T> implements Serializable,Cloneable {

	private T[] arr;
	private final int ENLARGE_FACTOR = 2;
	private int currentSize;

	public Set() {
		arr = (T[]) new Object[ENLARGE_FACTOR];
		currentSize = 0;
	}

	public void add(T newValue) throws Exception {
		boolean needToAdd = true;
		if (currentSize == arr.length)
			enlargeArray();

		for (int i = 0; i < currentSize; i++) {
			if (arr[i].equals(newValue))
				needToAdd = false;
		}

		if (needToAdd)
			arr[currentSize++] = newValue;
	}

	public T[] getArr() {
		return this.arr;
	}

	public T get(int index) {
		return arr[index];
	}

	public void set(int index, T value) {
		this.arr[index] = value;
	}

	public int getCurrentSize() {
		return this.currentSize;
	}

	public int capacity() {
		return this.arr.length;
	}

	public void decreaseCurrentSize() {
		this.currentSize--;
	}

	public Set<T> copy() throws Exception {
		Set<T> temp = new Set<T>();
		T tempVariable;
		for (int i = 0; i < this.currentSize; i++) {
			tempVariable=this.arr[i];
			temp.add(this.arr[i]);
		}
		return temp;
	}

	private void enlargeArray() {
		arr = Arrays.copyOf(arr, ENLARGE_FACTOR * currentSize);
	}

	public void clear() {
		for (int i = arr.length; i > 0; i--) {
			arr[i - 1] = null;
			this.decreaseCurrentSize();

		}
	}
	
	public Set<T> clone(){
		try {
			Set<T> temp = new Set<T>();
			temp=(Set<T>)super.clone();
			temp.arr=this.arr.clone();
	
			 return temp;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean equals(Set<T> otherSet) {
		if (this.arr.length != otherSet.arr.length)
			return false;
		for (int i = 0; i < arr.length; i++) {
			if (this.arr[i].equals(otherSet.arr[i]))
				return false;
		}
		return true;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer("");
		sb.append("The Answers are: \n");
		for (int i = 0; i < getCurrentSize(); i++) {
			sb.append("(" + (int) (i + 1) + ") :" + get(i) + " \n");

		}
		return sb.toString();

	}

}
