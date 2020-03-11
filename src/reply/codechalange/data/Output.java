package reply.codechalange.data;

import java.io.Serializable;


public class Output  implements Serializable
{
	private static final long serialVersionUID = 1L;
	int x;
	int y;
	String path;

	public Output(final int x, final int y, final String path)
	{
		this.x = x;
		this.y = y;
		this.path = path;
	}

	public int getX()
	{
		return x;
	}

	public void setX(final int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(final int y)
	{
		this.y = y;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(final String path)
	{
		this.path = path;
	}

	@Override
	public String toString()
	{
		return "Output{" +
				"x=" + x +
				", y=" + y +
				", path='" + path + '\'' +
				'}';
	}
}
