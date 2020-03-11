package reply.codechalange.data;

public class Route
{
	Point point;

	String route;

	int cost;

	public Route(final Point point, final String route, final int cost)
	{
		this.point = point;
		this.route = route;
		this.cost = cost;
	}

	public Route()
	{

	}

	public Point getPoint()
	{
		return point;
	}

	public void setPoint(final Point point)
	{
		this.point = point;
	}

	public String getRoute()
	{
		return route;
	}

	public void setRoute(final String route)
	{
		this.route = route;
	}

	public int getCost()
	{
		return cost;
	}

	public void setCost(final int cost)
	{
		this.cost = cost;
	}
}
