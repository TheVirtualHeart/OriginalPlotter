


//**********************************************************
//Assure to copy the EndianUtils in the directory:
//"org/apache/commons/io"
//inside the same folder of the .java file
//**********************************************************

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.EndianUtils;

/**
 * Extracted from OpenAnalyser: DatFormatReader.
 * License: GPL, Version 3
 * Alexander Schlemmer, 2010 at MPIDS Göttingen
 * @author salexan
 */

 
public class cascade_import {


	

    public boolean littleEndian = true;
    public double framerate;
    public String date = "";
    public String version;
    private int skipAfterEachFrame;
    public int sizex, sizey, nframes;

    /**
     * Example read-method, that reads a full video file into a 3D-double-array.
	 ****
	 !!!!!!!!!!!!modified in int!!!!!!!!!!!!
	 ****
     * @param filename
     * @return
     * @throws IOException
     */
	 
	 
    public short[][][] readVideoFile (String filename) throws IOException {
		//filename = "2010_feb16_temp_endo_010.dat";
        DataInputStream is = new DataInputStream(new BufferedInputStream(new FileInputStream(filename)));
        readHeader(is);
        short[][][] data = readData(is,0,nframes);
        is.close();
        return data;
    }

	
	//Test to return matrix dimension
	public int readNumbFrame () throws IOException {
		return nframes;
	}
	public int readXDim () throws IOException {
		return sizex;
	}
	public int readYDim () throws IOException {
		return sizey;
	}
	
	

    public void readHeader(DataInputStream is) throws IOException {

        byte[] b = new byte[1];
        is.read(b);
        int sum = 1;
        version = new String(b);
        skipAfterEachFrame = 0;

        if (version.equals("d")) {
            b = new byte[17];
            is.read(b);
            sum += 17;
            String datetime = new String(b);
            if (!datetime.substring(datetime.length() - 3, datetime.length() - 2).equals(":")) {
                b = new byte[7];
                is.read(b);
                sum += 7;
                datetime += new String(b);
            }

            nframes = EndianUtils.readSwappedInteger(is);
            sizey = EndianUtils.readSwappedInteger(is);
            sizex = EndianUtils.readSwappedInteger(is);
            is.skip(10);
            sum += 22;
            is.skip(1024 - sum);
        } else if (version.equals("f") || version.equals("e")) {
            int byteOrder = is.readInt();
            if (byteOrder == 439041101) {
                littleEndian = false;
                // Big Endian:
                nframes = is.readInt();
                sizex = is.readInt();
                sizey = is.readInt();
                skip(is, 8);
                framerate = is.readInt() / 1000.0;
            } else {
                littleEndian = true;
                // Little Endian:
                nframes = EndianUtils.readSwappedInteger(is);
                sizex = EndianUtils.readSwappedInteger(is);
                sizey = EndianUtils.readSwappedInteger(is);
                skip(is, 8);
                framerate = EndianUtils.readSwappedInteger(is) / 1000.0;
            }
            b = new byte[24];
            is.read(b);
            date = new String(b);
            is.skip(971);
            skipAfterEachFrame = 8;
        }
    }

    public short[][][] readData(DataInputStream is, int frameFrom, int frameTo) throws IOException {
        short[][][] data = new short[frameTo - frameFrom][sizex][sizey];
        skip(is, sizex * sizey * frameFrom * 2 + skipAfterEachFrame * frameFrom);
        for (int t = 0; t < frameTo - frameFrom; t++) {
            for (int y = 0; y < sizey; y++) {
                for (int x = 0; x < sizex; x++) {
                    if (!littleEndian) {
                        data[t][x][y] = (short) is.readChar();
                    } else {
                        data[t][x][y] = (short) EndianUtils.readSwappedUnsignedShort(is);
                    }
                }
            }
            skip(is, skipAfterEachFrame);
        }
        return data;
    }

    public void skip(InputStream is, long skipFramesLength) throws IOException {
        long n = is.skip(skipFramesLength);
        int count = 0;
        while (n != skipFramesLength) {
            n += is.skip(skipFramesLength - n);
            if (count == Integer.MAX_VALUE) {
                break;
            }
        }
    }
}

