import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {

    private final Point[] ePoints;

    private Point[] points;
    private final int numberOfSegments;

    private final LineSegment[] lineSegments;

    public FastCollinearPoints(Point[] staticPoints) {
        if (staticPoints == null) {
            throw new IllegalArgumentException();
        }
        int numPoints = staticPoints.length;
        points = new Point[numPoints];
        ePoints = new Point[numPoints];
        for (int i = 0; i < numPoints; ++i) {
            if (staticPoints[i] == null) {
                throw new IllegalArgumentException();
            }
            for (int j = 0; j < i; ++j) {
                if (staticPoints[j].compareTo(staticPoints[i]) == 0) {
                    throw new IllegalArgumentException();
                }
            }
            points[i] = staticPoints[i];
            ePoints[i] = points[i];
        }
        Arrays.sort(points);
        List<LineSegment> lines = new ArrayList<>();
        for (int i = 0; i < numPoints; ++i) {
            Arrays.sort(ePoints, points[i].slopeOrder());
            for (int l = 0, r = 0; l < numPoints; l = r) {
                boolean lowest = true;
                while (r < numPoints
                        && points[i].slopeTo(ePoints[r]) == points[i].slopeTo(ePoints[l])) {
                    if (ePoints[r].compareTo(points[i]) < 0) {
                        lowest = false;
                    }
                    r++;
                }
                if (lowest && r - l > 2) {
                    Point highest = points[i];
                    for (int j = l; j < r; ++j) {
                        if (ePoints[j].compareTo(highest) > 0) {
                            highest = ePoints[j];
                        }
                    }
                    lines.add(new LineSegment(points[i], highest));
                }
            }
        }
        numberOfSegments = lines.size();
        lineSegments = lines.toArray(new LineSegment[numberOfSegments]);
    }

    public int numberOfSegments() {
        return numberOfSegments;
    }


    public LineSegment[] segments() {
        return Arrays.copyOf(lineSegments, numberOfSegments);
    }


    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
