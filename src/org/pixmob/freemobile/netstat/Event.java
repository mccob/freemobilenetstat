/*
 * Copyright (C) 2012 Pixmob (http://github.com/pixmob)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.pixmob.freemobile.netstat;

import org.pixmob.freemobile.netstat.content.NetstatContract.Events;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.format.DateFormat;

/**
 * Network event.
 * @author Pixmob
 */
public class Event {
    public long timestamp;
    public boolean screenOn;
    public boolean wifiConnected;
    public boolean mobileConnected;
    public String mobileOperator;
    public int batteryLevel;
    public boolean powerOn;
	public int lac;
	public int cellID;
	public int band;

    /**
     * Read an {@link Event} instance from a database {@link Cursor}. The cursor
     * should include every columns defined in {@link Events}.
     */
    public void read(Cursor c) {
        timestamp = c.getLong(c.getColumnIndexOrThrow(Events.TIMESTAMP));
        screenOn = c.getInt(c.getColumnIndexOrThrow(Events.SCREEN_ON)) == 1;
        wifiConnected = c.getInt(c.getColumnIndexOrThrow(Events.WIFI_CONNECTED)) == 1;
        mobileConnected = c.getInt(c.getColumnIndexOrThrow(Events.MOBILE_CONNECTED)) == 1;
        mobileOperator = c.getString(c.getColumnIndexOrThrow(Events.MOBILE_OPERATOR));
        if (mobileOperator != null) {
            mobileOperator = mobileOperator.intern();
        }
        batteryLevel = c.getInt(c.getColumnIndexOrThrow(Events.BATTERY_LEVEL));
		powerOn = c.getInt(c.getColumnIndexOrThrow(Events.POWER_ON)) == 1;
		
		lac = c.getInt(c.getColumnIndexOrThrow(Events.LAC));
		cellID = c.getInt(c.getColumnIndexOrThrow(Events.CELL_ID));
		band = c.getInt(c.getColumnIndexOrThrow(Events.BAND));
    }

    /**
     * Fill a {@link ContentValues} instance with values from this instance.
     */
    public void write(ContentValues values) {
        values.put(Events.TIMESTAMP, timestamp);
        values.put(Events.SCREEN_ON, screenOn ? 1 : 0);
        values.put(Events.WIFI_CONNECTED, wifiConnected ? 1 : 0);
        values.put(Events.MOBILE_CONNECTED, mobileConnected ? 1 : 0);
        values.put(Events.MOBILE_OPERATOR, mobileOperator);
        values.put(Events.BATTERY_LEVEL, batteryLevel);
		values.put(Events.POWER_ON, powerOn ? 1 : 0);
		values.put(Events.LAC, lac);
		values.put(Events.CELL_ID, cellID);
		values.put(Events.BAND, band);
    }

    @Override
    public String toString() {
        return "Event[timestamp=" + DateFormat.format("dd/MM/yyyy hh:mm:ss", timestamp) + "; screen="
                + screenOn + "; wifi=" + wifiConnected + "; mobile=" + mobileConnected + "; operator="
                + mobileOperator + "; battery=" + batteryLevel + "; powerOn=" + powerOn + "; lac=" + lac + "; cellID=" + cellID + "; band=" + band + "]";
    }
}
