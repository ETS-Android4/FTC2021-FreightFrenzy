package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.opencv.aruco.DetectorParameters;
import org.opencv.aruco.Dictionary;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

import org.opencv.aruco.Aruco;

public class VisionPipeline extends OpenCvPipeline {

    public enum MarkerPlacement {
        LEFT,
        CENTER,
        RIGHT,
        UNKNOWN
    }

    private MarkerPlacement markerPlacement = MarkerPlacement.UNKNOWN;

    public MarkerPlacement getMarkerPlacement() {
        return markerPlacement;
    }

    private DigitalChannel redLED;
    private DigitalChannel greenLED;
    private RevBlinkinLedDriver leds;

    /*
     * Cache
     */
    private Mat output = new Mat();

    public VisionPipeline (HardwareMap hardwareMap){

        redLED = hardwareMap.get(DigitalChannel.class, "red");
        greenLED = hardwareMap.get(DigitalChannel.class, "green");

        // change LED mode from input to output
        redLED.setMode(DigitalChannel.Mode.OUTPUT);
        greenLED.setMode(DigitalChannel.Mode.OUTPUT);

        leds = hardwareMap.get(RevBlinkinLedDriver.class, "Blingkin");

    }

    @Override
    public Mat processFrame(Mat input) {
//        System.out.println(input.dump());
        // I/System.out: Mat [ 240*320*CV_8UC4, isCont=true, isSubmat=false, nativeObj=0xe870b880, dataAddr=0xce783000 ]
        //System.out.println(input.toString());

        Imgproc.cvtColor(input, input, Imgproc.COLOR_RGB2BGR);

        List<Mat> markerCorners = new ArrayList<>();
        MatOfInt markerIDs = new MatOfInt();

        //
        // Detect Aruco markers
        //
        Dictionary dict = Aruco.getPredefinedDictionary(Aruco.DICT_6X6_250);
        DetectorParameters parameters = DetectorParameters.create();
        Aruco.detectMarkers(input, dict, markerCorners, markerIDs, parameters);

        //
        // Determine which team marker spot it is on (If markers are present)
        //

        Rect leftArea = new Rect(
                new Point(0,0),
                new Point(input.width()/3.0, input.height())
        );
        Rect centerArea = new Rect(
                new Point(input.width()/3.0,0),
                new Point(input.width()*2.0/3.0,input.height())
        );
        Rect rightArea = new Rect(
                new Point(input.width()*2.0/3.0,0),
                new Point(input.width(), input.height())
        );

        markerPlacement = MarkerPlacement.UNKNOWN;

        int[] markerIDArray = new int[0];
        if (!markerCorners.isEmpty()) {
            markerIDArray = markerIDs.toArray();
        }
        for (int i = 0; i < markerCorners.size(); i++) {
            int markerID = markerIDArray[i];
            Mat corners = markerCorners.get(i);

            // https://docs.opencv.org/3.4/dd/d49/tutorial_py_contour_features.html
            Moments m = Imgproc.moments(corners);

            double cx = m.m10/m.m00;
            double cy = m.m01/m.m00;
            Point centerOfMass = new Point(cx, cy);

            // We only care about marker with id 23
            if (markerID != 23) {
                System.out.println("Ignoring marker: id="+markerID + " cx="+cx + " cy="+cy);
                continue;
            }

            // Figure out which region of the image the marker is in.
            if(leftArea.contains(centerOfMass)) {
                markerPlacement = MarkerPlacement.LEFT;
            } else if(centerArea.contains(centerOfMass)) {
                markerPlacement = MarkerPlacement.CENTER;
            } else if(rightArea.contains(centerOfMass)) {
                markerPlacement = MarkerPlacement.RIGHT;
            }

            System.out.println("Found marker: id="+markerID + " placement=" + markerPlacement+ " cx="+cx + " cy="+cy);
            break;
        }


        //
        // Annotate the source image
        //
        input.copyTo(output);

        double leftAlpha = 0.1;
        double centerAlpha = 0.1;
        double rightAlpha = 0.1;

        if (markerPlacement == MarkerPlacement.LEFT) {
            leftAlpha = 0.75;
            // blue/amber
            redLED.setState(false);
            greenLED.setState(false);
            leds.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLUE);
        } else if (markerPlacement == MarkerPlacement.CENTER) {
            // green
            centerAlpha = 0.75;
            redLED.setState(false);
            greenLED.setState(true);
            leds.setPattern(RevBlinkinLedDriver.BlinkinPattern.GREEN);
        } else if (markerPlacement == MarkerPlacement.RIGHT) {
            // red
            rightAlpha = 0.75;
            greenLED.setState(false);
            redLED.setState(true);
            leds.setPattern(RevBlinkinLedDriver.BlinkinPattern.RED);
        } else {
            greenLED.setState(true);
            redLED.setState(true);
            leds.setPattern(RevBlinkinLedDriver.BlinkinPattern.HEARTBEAT_WHITE);
        }

        drawTransparentRect(leftArea ,new Scalar(0,0,255), leftAlpha, output);
        drawTransparentRect(centerArea ,new Scalar(0,255,0), centerAlpha, output);
        drawTransparentRect(rightArea ,new Scalar(255,0,0),  rightAlpha, output);

        Aruco.drawDetectedMarkers(output, markerCorners, markerIDs);


        return output;
    }

    private void drawTransparentRect(Rect rect, Scalar color, double alpha, Mat dest) {
        Mat overlay = dest.clone();
        Imgproc.rectangle(overlay, rect, color, -1);

        Core.addWeighted(overlay, alpha, dest, 1, 1-alpha, dest);
        overlay.release();
    }

}
