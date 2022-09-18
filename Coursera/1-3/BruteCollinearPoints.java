import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
    private int numberOfSegments;

    private Point[] points;
    private LineSegment[] lineSegments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] staticPoints) {
        if (staticPoints == null) {
            throw new IllegalArgumentException();
        }
        int numPoints = staticPoints.length;
        points = new Point[numPoints];
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
        }
        Arrays.sort(points);
        List<LineSegment> lines = new ArrayList<>();
        for (int i = 3; i < numPoints; ++i) {
            for (int j = 2; j < i; ++j) {
                for (int k = 1; k < j; ++k) {
                    for (int l = 0; l < k; ++l) {
                        if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k])
                                && points[i].slopeTo(points[j]) == points[i].slopeTo(points[l])) {
                            lines.add(new LineSegment(points[l], points[i]));
                        }
                    }
                }
            }
        }
        numberOfSegments = lines.size();
        lineSegments = lines.toArray(new LineSegment[numberOfSegments]);
    }

    // the number of line segments
    public int numberOfSegments() {
        return numberOfSegments;
    }

    // the line segments
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}

