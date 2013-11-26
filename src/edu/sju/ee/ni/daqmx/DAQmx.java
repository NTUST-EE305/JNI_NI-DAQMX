package edu.sju.ee.ni.daqmx;

import edu.sju.ee.jni.NativeUtils;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Leo
 */
public class DAQmx {

    static {
        try {
            NativeUtils.loadLibraryFromJar("libDAQ");
        } catch (IOException ex) {
            Logger.getLogger(DAQmx.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //**************************************************************************
    //Java Object
    private long nativeStruct;

    public DAQmx() {
        construct();
    }

    @Override
    protected void finalize() throws Throwable {
        this.destruct();
        super.finalize();
    }

    //**************************************************************************
    //Native Object
    private native void construct();

    private native void destruct();

    public native boolean isAlive();

    //**************************************************************************
    //1092*** Values for the Data Layout parameter of DAQmxWriteAnalogF64, DAQmxWriteBinaryI16, DAQmxWriteDigitalU8, DAQmxWriteDigitalU32, DAQmxWriteDigitalLines ***
    public static final boolean Val_GroupByChannel = false;
    public static final boolean Val_GroupByScanNumber = true;
    //1156*** Value for the Terminal Config parameter of DAQmxCreateAIVoltageChan, DAQmxCreateAICurrentChan and DAQmxCreateAIVoltageChanWithExcit ***
    public static final int Val_Cfg_Default = -1;
    public static final int Val_Volts = 10348;
    //1281*** Value set AcquisitionType ***
    public static final int Val_FiniteSamps = 10178;
    public static final int Val_ContSamps = 10123;
    public static final int Val_HWTimedSinglePoint = 12522;
    //1544*** Value set Edge1 ***
    public static final int Val_Rising = 10280;
    public static final int Val_Falling = 10171;

    //**************************************************************************
    //Channel Configuration/Creation
    public native void createAIVoltageChan(String physicalChannel, String nameToAssignToChannel, int terminalConfig, double minVal, double maxVal, int units, String customScaleName) throws Exception;

    //**************************************************************************
    //Timing
    public native void cfgSampClkTiming(String source, double rate, int activeEdge, int sampleMode, long sampsPerChan) throws Exception;

    //**************************************************************************
    //2211 Task Configuration/Control
    public native void createTask(String taskName) throws Exception;

    public native void startTask() throws Exception;

    public native void stopTask() throws Exception;

    public native void clearTask() throws Exception;

    //**************************************************************************
    //2402 Read Data
    public native int readAnalogF64(int numSampsPerChan, double timeout, boolean fillMode, double readArray[], int arraySizeInSamps, Boolean reserved) throws Exception;

    //**************************************************************************
    //Test----------------------------------------------------------------------
    public native void test();

}
