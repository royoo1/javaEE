package engine;

/**
 * ת�������쳣��
 * 
 * @author Jerome
 *
 */
public class ConvertorException extends Exception {

	/**
	 * code = 0, ��Դ���
	 */
	public int code;

	public ConvertorException(int code) {
		this.code = code;
	}

}