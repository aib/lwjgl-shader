package net.aib42.lwjgl.shader;

/**
 * Custom exception used by the shader system if anything goes wrong
 *
 * @author aib
 * @date 2012-01-25
 */

public class ShaderException extends Exception
{
	protected String message;
	protected String details;

	public ShaderException(String message)
	{
		init(message, null, null);
	}

	public ShaderException(String message, Throwable cause)
	{
		init(message, cause.getMessage(), cause);
	}

	public ShaderException(String message, String details)
	{
		init(message, details, null);
	}

	protected void init(String message, String details, Throwable cause)
	{
		this.message = message;
		this.details = details;
		initCause(cause);
	}

	public String getDetailedMessage()
	{
		if (details == null) {
			return message + ".";
		} else {
			return message + ":\n" + details;
		}
	}

	@Override
	public String getMessage()
	{
		return getDetailedMessage();
	}
}
