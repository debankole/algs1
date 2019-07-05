import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class BruteCollinearPoints {

	private Point[] points;
	private LineSegment[] segments;

	public BruteCollinearPoints(Point[] points) { // finds all line segments containing 4 points

		if (points == null) {
			throw new IllegalArgumentException();
		}

		this.points = points;

		findSegments();
	}

	private void findSegments() {
		this.segments = new LineSegment[0];
		LinkedList<LineSegment> s = new LinkedList<LineSegment>();

		for (int i1 = 0; i1 < this.points.length - 3; i1++) {
			Point p1 = this.points[i1];

			for (int i2 = i1 + 1; i2 < this.points.length - 2; i2++) {
				Point p2 = this.points[i2];

				for (int i3 = i2 + 1; i3 < this.points.length - 1; i3++) {
					Point p3 = this.points[i3];

					for (int i4 = i3 + 1; i4 < this.points.length; i4++) {
						Point p4 = this.points[i4];

						if (p1.slopeTo(p2) == p1.slopeTo(p3) && p1.slopeTo(p3) == p1.slopeTo(p4)) {
							Point[] a = new Point[] { p1, p2, p3, p4 };
							Arrays.sort(a);
							s.add(new LineSegment(a[0], a[3]));
						}
					}
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
