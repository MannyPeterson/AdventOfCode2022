
public class Main {
	private static final int[][] input = { { 98246, 1908027, 1076513, 2000000 }, { 1339369, 2083853, 1076513, 2000000 },
			{ 679177, 3007305, 1076513, 2000000 }, { 20262, 3978297, 13166, 4136840 },
			{ 3260165, 2268955, 4044141, 2290104 }, { 2577675, 3062584, 2141091, 2828176 },
			{ 3683313, 2729137, 4044141, 2290104 }, { 1056412, 370641, 1076513, 2000000 },
			{ 2827280, 1827095, 2757345, 1800840 }, { 1640458, 3954524, 2141091, 2828176 },
			{ 2139884, 1162189, 2757345, 1800840 }, { 3777450, 3714504, 3355953, 3271922 },
			{ 1108884, 2426713, 1076513, 2000000 }, { 2364307, 20668, 2972273, -494417 },
			{ 3226902, 2838842, 3355953, 3271922 }, { 22804, 3803886, 13166, 4136840 },
			{ 2216477, 2547945, 2141091, 2828176 }, { 1690953, 2203555, 1076513, 2000000 },
			{ 3055156, 3386812, 3355953, 3271922 }, { 3538996, 719130, 2972273, -494417 },
			{ 2108918, 2669413, 2141091, 2828176 }, { 3999776, 2044283, 4044141, 2290104 },
			{ 2184714, 2763072, 2141091, 2828176 }, { 2615462, 2273553, 2757345, 1800840 } };

	private static final int[][] input2 = { { 2, 18, -2, 15 }, { 9, 16, 10, 16 }, { 13, 2, 15, 3 }, { 12, 14, 10, 16 },
			{ 10, 20, 10, 16 }, { 14, 17, 10, 16 }, { 8, 7, 2, 10 }, { 2, 0, 2, 10 }, { 0, 11, 2, 10 },
			{ 20, 14, 25, 17 }, { 17, 20, 21, 22 }, { 16, 7, 15, 3 }, { 14, 3, 15, 3 }, { 20, 1, 15, 3 } };

	public static void main(String[] args) {

		Sensor[] sensors = new Sensor[input.length];

		for (int i = 0; i < input.length; i++) {
			sensors[i] = new Sensor(new Point(input[i][1], input[i][0]), new Point(input[i][3], input[i][2]));
		}

		boolean inrange = false;
		int newcol = 0;
		Point position = new Point();
		for (position.row = 0; position.row <= 4000000; position.row++) {
			for (position.col = 0; position.col <= 4000000; position.col++) {
				inrange = false;
				for (Sensor sensor : sensors) {
					if (sensor.isInRange(position)) {
						position.col = sensor.location.col
								+ (sensor.distance - Math.abs(sensor.location.row - position.row));
						inrange = true;
						break;
					}
				}
				if (!inrange) {
					System.out.println(
							position.row + " " + position.col + " " + (long) ((long) ((long) position.col * 4000000) + (long) position.row));
					System.exit(0);
				}

			}
		}

		/*
		 * boolean inrange = false; Point position = new Point(); for (position.row = 0;
		 * position.row <= 4000000; position.row++) { for (position.col = 0;
		 * position.col <= 4000000; position.col++) { inrange = false; for (Sensor
		 * sensor : sensors) { if (sensor.isInRange(position)) { inrange = true; break;
		 * } } if (!inrange) { System.out.println( position.row + " " + position.col +
		 * " " + ((position.col * 4000000) + position.row)); System.exit(0); }
		 * 
		 * } }
		 */
		System.out.println("NOPE :(");
		System.exit(0);
	}

}
