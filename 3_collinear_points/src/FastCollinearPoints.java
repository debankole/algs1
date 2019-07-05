import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

import com.sun.istack.internal.Nullable;

public class FastCollinearPoints {
	private Point[] points;
	private LineSegment[] segments;

	public FastCollinearPoints(Point[] points) {
		if (points == null) {
			throw new IllegalArgumentException();
		}

		this.points = points;

		findSegments();
	}

	private void findSegments() {
		this.segments = new LineSegment[0];
		LinkedList<LineSegment> s = new LinkedList<LineSegment>();

		HashSet<String> processed = new HashSet<String>();

		for (int i1 = 0; i1 < this.points.length; i1++) {
			double lastSlope = 0;
			int sameSlopeCount = 1;
			Point pnt = this.points[i1];

			Point[] tempArray = Arrays.copyOf(this.points, this.points.length);
			Arrays.sort(tempArray, pnt.slopeOrder());

			for (int i2 = 0; i2 < tempArray.length; i2++) {

				double currentSlope = pnt.slopeTo(tempArray[i2]);

				if (currentSlope == lastSlope && i2 != tempArray.length - 1) {
					sameSlopeCount++;
				} else {
					lastSlope = currentSlope;

					if (sameSlopeCount >= 3) {
						Point[] a = new Point[sameSlopeCount + 1];
						a[0] = pnt;
						for (int i = 1; i <= sameSlopeCount; i++) {
							a[i] = tempArray[i2 - i];
						}

						Arrays.sort(a);
						LineSegment segment = new LineSegment(a[0], a[a.length - 1]);

						if (!processed.contains(segment.toString())) {
							s.add(segment);
							processed.add(segment.toString());
						}
					}

					sameSlopeCount = 1;
				}
			}
		}

		this.segments = s.toArray(this.segments);
	}

	public int numberOfSegments() { // the number of line segments
		return this.segments.length;
	}

	public LineSegment[] segments() { // the line segments
		return this.segments;
	}
}