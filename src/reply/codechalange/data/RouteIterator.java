package reply.codechalange.data;

import java.util.Map;


public class RouteIterator
{
	Point office;

	Map<Customer, Route> customerRouteMap;

	public RouteIterator()
	{
	}

	public RouteIterator(final Point office, final Map<Customer, Route> customerRouteMap)
	{
		this.office = office;
		this.customerRouteMap = customerRouteMap;
	}

	public Point getOffice()
	{
		return office;
	}

	public void setOffice(final Point office)
	{
		this.office = office;
	}

	public Map<Customer, Route> getCustomerRouteMap()
	{
		return customerRouteMap;
	}

	public void setCustomerRouteMap(final Map<Customer, Route> customerRouteMap)
	{
		this.customerRouteMap = customerRouteMap;
	}
}
