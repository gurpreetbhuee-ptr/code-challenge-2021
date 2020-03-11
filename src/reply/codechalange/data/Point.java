package reply.codechalange.data;

public class Point
{
	int x;
	int y;
	Point parent;

	public Point(final int x, final int y, final Point parent)
	{
		this.x = x;
		this.y = y;
		this.parent = parent;
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

	public Point getParent()
	{
		return parent;
	}

	public void setParent(final Point parent)
	{
		this.parent = parent;
	}
}
