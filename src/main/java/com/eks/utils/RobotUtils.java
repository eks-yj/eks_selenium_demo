package com.eks.utils;

import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.awt.event.InputEvent;

@Slf4j
public class RobotUtils {
    private static Robot ROBOT = null;
    static {
        try {
            ROBOT = new Robot();
        } catch (AWTException e) {
            log.info("AWTException:{}", e);
        }
    }
    public static void clickMouse(Integer beforeDelayInteger, Integer afterDelayInteger, Integer xInteger, Integer yInteger){
        ROBOT.mouseMove(xInteger, yInteger);
        ROBOT.delay(beforeDelayInteger);
        ROBOT.mousePress(InputEvent.BUTTON1_MASK);
        ROBOT.mouseRelease(InputEvent.BUTTON1_MASK);
        ROBOT.delay(afterDelayInteger);
    }
    public static void pressKey(Integer delayInteger, Boolean oneByoneBoolean, Integer... keyIntegerArray){
        if (oneByoneBoolean) {
            for (Integer keyInt : keyIntegerArray) {
                ROBOT.keyPress(keyInt);
                ROBOT.keyRelease(keyInt);
                ROBOT.delay(delayInteger);
            }
            return;
        }
        for (Integer keyInt : keyIntegerArray) {
            ROBOT.keyPress(keyInt);
        }
        ROBOT.delay(delayInteger);
        for (Integer keyInt : keyIntegerArray) {
            ROBOT.keyRelease(keyInt);
        }
    }
    public static Point getMousePoint(){
        return MouseInfo.getPointerInfo().getLocation();
    }
}
