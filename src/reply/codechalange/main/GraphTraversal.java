package reply.codechalange.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import reply.codechalange.data.Customer;
import reply.codechalange.data.Point;
import reply.codechalange.data.Route;
import reply.codechalange.data.RouteIterator;


public class GraphTraversal
{
	final int[][] DIRS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	public List<RouteIterator> traverseGraph(final int[][] grid, final List<Customer> customerList)
	{
		final List<RouteIterator> routeIteratorList = new ArrayList<>();
		for (int i = 0; i < grid.length; i++)
		{
			for (int j = 0; j < grid[0].length; j++)
			{
				final Point office = new Point(i, j, null);
				final RouteIterator routeIterator = new RouteIterator();
				routeIterator.setOffice(office);

				for (final Customer customer : customerList)
				{
					maximumPath(grid, customer, office, routeIterator);
				}

				if (routeIterator.getCustomerRouteMap() != null && !routeIterator.getCustomerRouteMap().isEmpty())
					routeIteratorList.add(routeIterator);
			}
		}
		return routeIteratorList;
	}


	private RouteIterator maximumPath(final int[][] grid, final Customer customer, final Point office,
			final RouteIterator routeIterator)
	{
		final int n = grid.length, m = grid[0].length;

		final Queue<Point> pq = new PriorityQueue<>((a, b) -> Integer.compare(grid[a.getX()][a.getY()], grid[b.getX()][b.getY()]));
		pq.add(office);

		final boolean[][] visited = new boolean[n][m];
		visited[office.getX()][office.getY()] = true; // visited

		while (!pq.isEmpty())
		{
			final Point top = pq.poll();
			final int i = top.getX(), j = top.getY();

			if (null != customer.getPoint() && i == customer.getPoint().getX() && j == customer.getPoint().getY())
			{
				Map<Customer, Route> map = null;
				if (routeIterator.getCustomerRouteMap() == null)
				{
					map = new HashMap<>();
				}
				else
				{
					map = routeIterator.getCustomerRouteMap();
				}
				map.put(customer, printPath(top, new StringBuilder(), 0, grid));

				routeIterator.setCustomerRouteMap(map);
				break;
			}
			for (final int[] d : DIRS)
			{
				final int newi = d[0] + i, newj = d[1] + j;
				if (newi >= 0 && newi < n && newj >= 0 && newj < m && !visited[newi][newj] && grid[newi][newj] != 0)
				{
					final Point point = new Point(newi, newj, top);
					pq.add(point);
					visited[newi][newj] = true;
				}
			}
		}
		return routeIterator;
	}



	private String getPath(final Point point)
	{
		final Point prevPoint = point.getParent();
		final int dirX = point.getX() - prevPoint.getX();
		final int dirY = point.getY() - prevPoint.getY();

		if (dirX == 1 && dirY == 0)
			return "D";
		else if (dirX == -1 && dirY == 0)
			return "U";
		else if (dirX == 0 && dirY == 1)
			return "R";
		else if (dirX == 0 && dirY == -1)
			return "L";
		return null;
	}


	// Utility function to print path from source to destination
	private Route printPath(final Point point, final StringBuilder path, int cost, final int[][] grid)
	{
		final Route route = new Route();
		if (point == null)
		{
			route.setPoint(point);
			route.setRoute(path.reverse().toString());
			route.setCost(cost);
			return route;
		}

		if(point.getParent() != null)
		{
			cost += grid[point.getX()][point.getY()];
			path.append(getPath(point));
		}

		return printPath(point.getParent(), path, cost, grid);
	}
}
