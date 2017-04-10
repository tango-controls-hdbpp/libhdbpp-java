package org.tango.jhdb;

import org.tango.jhdb.data.HdbData;
import org.tango.jhdb.data.HdbDouble;
import org.tango.jhdb.data.HdbString;
import org.tango.jhdb.data.HdbStringArray;

import java.util.Date;

/**
 * Signal parameters structure
 */
public class HdbSigParam {

  public final static int QUERY_DATA     = 0;
  public final static int QUERY_CFG_ALL  = 1;
  public final static int QUERY_CFG_LABEL  = 2;
  public final static int QUERY_CFG_UNIT  = 3;
  public final static int QUERY_CFG_DISPLAY_UNIT  = 4;
  public final static int QUERY_CFG_STANDARD_UNIT  = 5;
  public final static int QUERY_CFG_FORMAT  = 6;
  public final static int QUERY_CFG_ARCH_REL_CHANGE  = 7;
  public final static int QUERY_CFG_ARCH_ABS_CHANGE  = 8;
  public final static int QUERY_CFG_ARCH_PERIOD  = 9;
  public final static int QUERY_CFG_DESCRIPTION  = 10;

  public final static String[] FIELDS = {
    "",
    " (Config)",
    " (Label)",
    " (Unit)",
    " (Display Unit)",
    " (Standard Unit)",
    " (Format)",
    " (Arch Rel Change)",
    " (Arch Abs Change)",
    " (Arch Period)",
    " (Description)"
  };

  public long   recvTime;
  public long   insertTime;
  public String label;
  public String unit;
  public double display_unit;
  public double standard_unit;
  public String format;
  public String archive_rel_change;
  public String archive_abs_change;
  public String archive_period;
  public String description;

  public String timeToStr(long time) {

    long ms = time/1000;
    Date d = new Date(ms);
    String dStr = Hdb.hdbDateFormat.format(d);
    String sStr = String.format("%06d",time%1000000);
    return dStr+"."+sStr;

  }

  /**
   * Return the corresponding type according to query mode
   * @param mode Query mode
   * @return HdbData corresponding to mode
   * @throws HdbFailed In case of failure
   */
  public static int getType(int mode) throws HdbFailed {

    switch (mode) {

      case QUERY_CFG_ALL:
        return HdbSigInfo.TYPE_ARRAY_STRING_RO;

      case QUERY_CFG_LABEL:
        return HdbSigInfo.TYPE_SCALAR_STRING_RO;

      case QUERY_CFG_UNIT:
        return HdbSigInfo.TYPE_SCALAR_STRING_RO;

      case QUERY_CFG_DISPLAY_UNIT:
        return HdbSigInfo.TYPE_SCALAR_DOUBLE_RO;

      case QUERY_CFG_STANDARD_UNIT:
        return HdbSigInfo.TYPE_SCALAR_DOUBLE_RO;

      case QUERY_CFG_FORMAT:
        return HdbSigInfo.TYPE_SCALAR_STRING_RO;

      case QUERY_CFG_ARCH_REL_CHANGE:
        return HdbSigInfo.TYPE_SCALAR_DOUBLE_RO;

      case QUERY_CFG_ARCH_ABS_CHANGE:
        return HdbSigInfo.TYPE_SCALAR_DOUBLE_RO;

      case QUERY_CFG_ARCH_PERIOD:
        return HdbSigInfo.TYPE_SCALAR_DOUBLE_RO;

      case QUERY_CFG_DESCRIPTION:
        return HdbSigInfo.TYPE_SCALAR_STRING_RO;

      default:
        throw new HdbFailed("HdbSigParam.convert() Invalid mode for conversion");

    }

  }

  /**
   * Converts to HdbData according to QUERY mode
   * @param mode Query mode
   * @return HdbData corresponding to mode
   * @throws HdbFailed In case of failure
   */
   public HdbData convert(int mode) throws HdbFailed {

    switch (mode) {

      case QUERY_CFG_ALL:
      {
        String[] values = new String[] {
            "Label: " + label,
            "Unit: " + unit,
            "Display Unit:" + Double.toString(display_unit),
            "Standard Unit:" + Double.toString(standard_unit),
            "Format: " + format,
            "Archive Rel Change: " + archive_rel_change,
            "Archive Abs Change: " + archive_abs_change,
            "Archive Period: " + archive_period,
            "Description: " + description
        };
        HdbStringArray d = new HdbStringArray(HdbSigInfo.TYPE_ARRAY_STRING_RO,values);
        d.setDataTime(recvTime);
        d.setRecvTime(recvTime);
        return d;
      }

      case QUERY_CFG_LABEL:
      {
        HdbString s = new HdbString(HdbSigInfo.TYPE_SCALAR_STRING_RO,label);
        s.setDataTime(recvTime);
        s.setRecvTime(recvTime);
        return s;
      }

      case QUERY_CFG_UNIT:
      {
        HdbString s = new HdbString(HdbSigInfo.TYPE_SCALAR_STRING_RO,unit);
        s.setDataTime(recvTime);
        s.setRecvTime(recvTime);
        return s;
      }

      case QUERY_CFG_DISPLAY_UNIT:
      {
        HdbDouble d = new HdbDouble(HdbSigInfo.TYPE_SCALAR_DOUBLE_RO,display_unit);
        d.setDataTime(recvTime);
        d.setRecvTime(recvTime);
        return d;
      }

      case QUERY_CFG_STANDARD_UNIT:
      {
        HdbDouble d = new HdbDouble(HdbSigInfo.TYPE_SCALAR_DOUBLE_RO,standard_unit);
        d.setDataTime(recvTime);
        d.setRecvTime(recvTime);
        return d;
      }

      case QUERY_CFG_FORMAT:
      {
        HdbString s = new HdbString(HdbSigInfo.TYPE_SCALAR_STRING_RO,format);
        s.setDataTime(recvTime);
        s.setRecvTime(recvTime);
        return s;
      }

      case QUERY_CFG_ARCH_REL_CHANGE:
      {
        double v = Double.NaN;
        try {
          v = Double.parseDouble(archive_rel_change);
        } catch (NumberFormatException e) {}
        HdbDouble d = new HdbDouble(HdbSigInfo.TYPE_SCALAR_DOUBLE_RO,v);
        d.setDataTime(recvTime);
        d.setRecvTime(recvTime);
        return d;
      }

      case QUERY_CFG_ARCH_ABS_CHANGE:
      {
        double v = Double.NaN;
        try {
          v = Double.parseDouble(archive_abs_change);
        } catch (NumberFormatException e) {}
        HdbDouble d = new HdbDouble(HdbSigInfo.TYPE_SCALAR_DOUBLE_RO,v);
        d.setDataTime(recvTime);
        d.setRecvTime(recvTime);
        return d;
      }

      case QUERY_CFG_ARCH_PERIOD:
      {
        double v = Double.NaN;
        try {
          v = Double.parseDouble(archive_period);
        } catch (NumberFormatException e) {}
        HdbDouble d = new HdbDouble(HdbSigInfo.TYPE_SCALAR_DOUBLE_RO,v);
        d.setDataTime(recvTime);
        d.setRecvTime(recvTime);
        return d;
      }

      case QUERY_CFG_DESCRIPTION:
      {
        HdbString s = new HdbString(HdbSigInfo.TYPE_SCALAR_STRING_RO,description);
        s.setDataTime(recvTime);
        s.setRecvTime(recvTime);
        return s;
      }

      default:
        throw new HdbFailed("HdbSigParam.convert() Invalid mode for conversion");


    }

  }

  public String toString() {

    return  "insert_time: " + timeToStr(insertTime) + "\n" +
        "recv_time: " + timeToStr(recvTime) + "\n" +
        "label: " + label + "\n" +
        "unit: " + unit + "\n" +
        "display_unit: " + display_unit + "\n" +
        "standard_unit: " + standard_unit + "\n" +
        "format: " + format + "\n" +
        "archive_rel_change: " + archive_rel_change + "\n" +
        "archive_abs_change: " + archive_abs_change + "\n" +
        "archive_period: " + archive_period + "\n" +
        "description: " + description;

  }

}
