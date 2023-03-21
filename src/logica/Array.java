package logica;

public class Array {

	private byte[] array;
	private int code;
	
	public Array(byte[] array) {
		this.array = array;
		this.code = -1;
	}
	
	@Override
	public int hashCode() {
		if(this.code != -1) return this.code;
		this.code = CombinacionTool.getHashCode(this.array);
		return this.code;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Array)) return false;
		Array a = (Array)o;
		if(a.getArray().length != this.getArray().length) return false;
		if(this.hashCode() == a.hashCode()) return true;
		else return false;
	}

	public byte[] getArray() {
		return array;
	} 
}
