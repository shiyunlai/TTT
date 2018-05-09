/**
 * 
 */
package org.tis.tools.core.utils.marshaller;

import java.io.*;

/**
 * @author megapro
 *
 */
abstract class AbstractDataMarshaller implements IDataMarshaller {

	public void marshal(Object data, OutputStream out, Object additional) throws Exception {
		if (out == null) {
			throw new IllegalArgumentException("OutputStream is null!");
		}
		DataWrapper dataWrapper = new DataWrapper();
		dataWrapper.setData(data);
		doMarshal(dataWrapper, out, additional);
		out.flush();
	}

	@SuppressWarnings("unchecked")
	public <T> T unmarshal(InputStream in, Object additional) throws Exception {
		if (in == null) {
			throw new IllegalArgumentException("InputStream is null!");
		}
		Object obj = doUnmarshal(in, additional);
		if (obj instanceof DataWrapper) {
			return (T) ((DataWrapper) obj).getData();
		} else {
			return (T) obj;
		}
	}

	protected abstract void doMarshal(Object data, OutputStream out, Object additional) throws Exception;

	protected abstract <T> T doUnmarshal(InputStream in, Object additional) throws Exception;

	/**
	 * 序列化
	 * 
	 * @param data
	 *            数据对象，不可以为空
	 */
	public byte[] marshal(Object data) throws Exception {
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		marshal(data, byteOut, null);
		return byteOut.toByteArray();
	}

	/**
	 * 反序列化
	 * 
	 * @param in
	 *            输入，不可以为空
	 * @param encoding
	 *            编码格式，可以为空
	 * @return 数据对象
	 */
	@SuppressWarnings("unchecked")
	public <T> T unmarshal(byte[] bytes) throws Exception {
		if (bytes == null) {
			throw new IllegalArgumentException("bytes is null!");
		}
		ByteArrayInputStream byteIn = new ByteArrayInputStream(bytes);
		return (T) unmarshal(byteIn, null);
	}

	/**
	 * 序列化
	 * 
	 * @param data
	 *            数据对象，不可以为空
	 * @param encoding
	 *            编码格式，可以为空
	 */
	public String marshal(Object data, String encoding) throws Exception {
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		marshal(data, byteOut, null);
		if (encoding == null || encoding.trim().length() == 0) {
			// TODO 使用默认utf-8编码，可能对其他实现有影响
			return new String(byteOut.toByteArray(), "UTF-8");
		} else {
			return new String(byteOut.toByteArray(), encoding.trim());
		}
	}

	/**
	 * 反序列化
	 * 
	 * @param in
	 *            输入，不可以为空
	 * @param encoding
	 *            编码格式，可以为空
	 * @return 数据对象
	 */
	@SuppressWarnings("unchecked")
	public <T> T unmarshal(String in, String encoding) throws Exception {
		if (in == null) {
			throw new IllegalArgumentException("InString is null!");
		}
		ByteArrayInputStream byteIn = null;
		if (encoding == null || encoding.trim().length() == 0) {
			byteIn = new ByteArrayInputStream(in.getBytes("UTF-8"));
		} else {
			byteIn = new ByteArrayInputStream(in.getBytes(encoding.trim()));
		}
		return (T) unmarshal(byteIn, null);
	}

	/**
	 * 序列化
	 * 
	 * @param data
	 *            数据对象，不可以为空
	 * @param outFile
	 *            序列化目标文件，不可以为空
	 * @param encoding
	 *            编码格式，可以为空
	 */
	public void marshal(Object data, File outFile, String encoding) throws Exception {
		if (outFile == null) {
			throw new IllegalArgumentException("outFile is null!");
		}
		FileOutputStream output = null;
		try {
			if (!outFile.exists()) {
				outFile.createNewFile();
			}
			if (outFile.isDirectory()) {
				throw new IllegalArgumentException("Not dir:" + outFile.getAbsolutePath());
			}
			output = new FileOutputStream(outFile);
			marshal(data, output, null);
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (Exception ignore) {

				}
			}
		}
	}

	/**
	 * 反序列化
	 * 
	 * @param inFile
	 *            输入文件，不可以为空
	 * @param encoding
	 *            编码格式，可以为空
	 * @return 数据对象
	 */
	@SuppressWarnings("unchecked")
	public <T> T unmarshal(File inFile, String encoding) throws Exception {
		if (inFile == null) {
			throw new IllegalArgumentException("inFile is null!");
		}
		FileInputStream intput = null;
		try {
			if (!inFile.exists()) {
				throw new IllegalArgumentException("Not existed:" + inFile.getAbsolutePath());
			}
			if (inFile.isDirectory()) {
				throw new IllegalArgumentException("Not dir:" + inFile.getAbsolutePath());
			}
			intput = new FileInputStream(inFile);
			return (T) unmarshal(intput, null);
		} finally {
			if (intput != null) {
				try {
					intput.close();
				} catch (Exception ignore) {

				}
			}
		}
	}
}
