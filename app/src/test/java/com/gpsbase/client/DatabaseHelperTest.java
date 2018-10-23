
package com.gpsbase.client;

import android.location.Location;

import com.gpsbase.client.gps.models.Position;
import com.gpsbase.client.gps.utils.DatabaseHelper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.Date;

import static com.gpsbase.client.gps.utils.DatabaseHelper.POSITIONS_TEMP_TABLE;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class DatabaseHelperTest {

    @Test
    public void test() throws Exception {

        DatabaseHelper databaseHelper = new DatabaseHelper(RuntimeEnvironment.application);

        Position position = new Position("123456789012345", new Location("gps"), 0);
        position.setTime(new Date(0));

        assertNull(databaseHelper.selectFirstPosition());

        databaseHelper.insertPosition(POSITIONS_TEMP_TABLE, position, 444);

        position = databaseHelper.selectFirstPosition(POSITIONS_TEMP_TABLE);

        assertNotNull(position);

        databaseHelper.deletePosition(position.getId());

        assertNull(databaseHelper.selectFirstPosition(POSITIONS_TEMP_TABLE));

    }

}
