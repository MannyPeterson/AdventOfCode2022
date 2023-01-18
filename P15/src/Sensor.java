
public class Sensor {
	public Point location;
	public Point nearestBeacon;
	public int distance;

	public Sensor(Point location, Point nearestBeacon) {
		this.location = location;
		this.nearestBeacon = nearestBeacon;
		this.distance = this.location.getDistanceTo(this.nearestBeacon);
	}

	public boolean isInRange(Point point) {

		/*
		 * if ((this.location.getDistanceTo(point) <= this.distance) &&
		 * (this.location.getDistanceTo(point) > 0)
		 * 
		 * && (this.nearestBeacon.getDistanceTo(point) > 0)) { ret = true; }
		 */
		if ((this.location.getDistanceTo(point) <= this.distance)) {
			return true;
		}
		return false;
	}
}
