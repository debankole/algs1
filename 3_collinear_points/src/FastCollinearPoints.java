import java.util.Arrays;
import java.util.LinkedList;

public class FastCollinearPoints {
	private Point[] points;
	private LineSegment[] segments;

	public FastCollinearPoints(Point[] points) {
		if (points == null) {
			throw new IllegalArgumentException();
		}

		checkNull(points);
		Point[] cloned = points.clone();
		checkDuplicate(cloned);

		this.points = points;

		findSegments();
	}

	private boolean isSorted(Point[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			if (a[i].compareTo(a[i + 1]) > 0) {
				return false;
			}
		}
		return true;
	}

	private void findSegments() {
		this.segments = new LineSegment[0];
		LinkedList<LineSegment> s = new LinkedList<LineSegment>();

		for (int i1 = 0; i1 < this.points.length; i1++) {
			double lastSlope = 0;
			int sameSlopeCount = 1;
			Point pnt = this.points[i1];

			Point[] tempArray = Arrays.copyOf(this.points, this.points.length);
			Arrays.sort(tempArray, pnt.slopeOrder());

			for (int i2 = 0; i2 < tempArray.length; i2++) {

				double currentSlope = pnt.slopeTo(tempArray[i2]);

				if (currentSlope == lastSlope) {
					sameSlopeCount++;
				}

				if (currentSlope != lastSlope || i2 == tempArray.length - 1) {
					lastSlope = currentSlope;

					if (sameSlopeCount >= 3) {
						Point[] a = new Point[sameSlopeCount + 1];
						a[0] = pnt;

						boolean stop = false;
						for (int i = 1; i <= sameSlopeCount; i++) {
							a[i] = tempArray[i2 - i];
							if (pnt.compareTo(a[i]) > 0) {
								stop = true;
								break;
							}
						}

						if (stop) {
							sameSlopeCount = 1;
							continue;
						}

						Arrays.sort(a);
						LineSegment segment = new LineSegment(a[0], a[a.length - 1]);

						s.add(segment);

					}

					sameSlopeCount = 1;
				}
			}
		}

//		LinkedList<LineSegment> s1 = new LinkedList<LineSegment>();
//		LinkedList<String> processed = new LinkedList<String>();
//
//		for (LineSegment l : s) {
//			String t = l.toString();
//			if (!processed.contains(t)) {
//				s1.add(l);
//				processed.add(t);
//			}
//		}

		this.segments = s.toArray(this.segments);
	}

	private void checkNull(Point[] p) {
		for (Point pi : p) {
			if (pi == null) {
				throw new NullPointerException();
			}
		}
	}

	private void checkDuplicate(Point[] p) {

		Arrays.sort(p);
		for (int i = 0; i < p.length - 1; i++) {
			if (p[i].compareTo(p[i + 1]) == 0) {
				throw new IllegalArgumentException();
			}
		}
	}

	public int numberOfSegments() { // the number of line segments
		return this.segments.length;
	}

	public LineSegment[] segments() { // the line segments
		return Arrays.copyOf(this.segments, numberOfSegments());
	}
}