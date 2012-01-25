package net.aib42.lwjgl.shader;

/**
 * A simple wrapper for an OpenGL shader object
 *
 * @author aib
 * @date 2012-01-25
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class Shader
{
	protected int shaderID;

	public Shader(int type) throws ShaderException
	{
		create(type);
	}

	public Shader(int type, CharSequence source) throws ShaderException
	{
		create(type);
		setSource(source);
	}

	public Shader(int type, Reader sourceReader) throws ShaderException
	{
		create(type);
		setSource(sourceReader);
	}

	protected void create(int type) throws ShaderException
	{
		shaderID = GL20.glCreateShader(type);

		if (shaderID == 0) {
			throw new ShaderException("Unable to create shader object");
		}
	}

	public int getShaderID()
	{
		return shaderID;
	}

	public Shader setSource(CharSequence source)
	{
		GL20.glShaderSource(shaderID, source);

		return this;
	}

	public Shader setSource(Reader sourceReader) throws ShaderException
	{
		BufferedReader buf = new BufferedReader(sourceReader);
		String source = "";

		try {
			String line;
			while ((line = buf.readLine()) != null) {
				source += line + "\n";
			}
		} catch (IOException ioe) {
			throw new ShaderException("Unable to read shader source", ioe);
		}

		setSource(source);

		return this;
	}

	public Shader compile() throws ShaderException
	{
		GL20.glCompileShader(shaderID);

		if (GL20.glGetShader(shaderID, GL20.GL_COMPILE_STATUS) != GL11.GL_TRUE) {
			throw new ShaderException("Unable to compile shader", getShaderInfoLog());
		}

		return this;
	}

	public String getShaderInfoLog()
	{
		return GL20.glGetShaderInfoLog(shaderID, GL20.glGetShader(shaderID, GL20.GL_INFO_LOG_LENGTH));
	}
}
