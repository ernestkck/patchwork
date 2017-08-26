package comp1140.ass2;

import java.util.Random;

public class TestUtility {
    static final int BASE_ITERATIONS = 100;
    static final int ROWS = 9;
    static final int COLS = 9;
    static final int TILES = 33;
    static final int ORIENTATIONS = 8;
    static final char BAD = Character.MAX_VALUE - 'Z';

    static char[] shuffleDeck(boolean red) {
        Random r = new Random();
        char[] rtn = new char[20];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 10; j++) {
                int idx;
                do {
                    idx = r.nextInt(20);
                } while (rtn[idx] != 0);
                rtn[idx] = (char) ((red ? 'A' : 'K') + j);
            }
        }
        return rtn;
    }

    static final String PATCH_CIRCLES[] = {
            "QJfXRIbTcdegKVZSUFWCYGAaBLDEOHPMN",
            "XgDEfCIYcaHdZbRJGAeWKBFLMNOPQUSTV",
            "cYbTeZVDLIPEagQHdWNfCAFBJRGKMOSUX",
            "OMPSQVALTNBXGCJWcUYaKIHZfRbdegDEF",
            "RcSGTZVJdUaWXKebfIgAHOPYLBCMNDQEF",
            "cSdDKfLTOHeBUJMVCgNEFPWXGQRYZAabI",
            "bRcECeQVOULZPYWBaTfSXAMDdgFHGIJKN",
            "RDZAaUeGFBVJEbSdHCMWXIcKTLNfOYgPQ",
            "aQPAVIHRJdNYbSKBLWMXCOZTUDEcefgFG",
            "GcQYRPCVEfSeTIgADUZWBFXHNdJMKaLOb",
            "GPMVIaeOgNBTRQFHYJKLSEWUXZfDbcdAC"
    };

    static final String PLACEMENTS[] = {
            "LHAAOAAAHADA.MAFANCAA..QFBCXBACIBHAhBBATDCAdGEC.eHFGhGBAKCCAVCFBSFHHhGHAFDADCECAADIBaFEABEEDECBFPDEAfFGARAAAgADHhADAUGAAYFHBhCFAJBFG.cGDA.",
            "eAFABAAAMACAOBHFUAGA..TCFA..VBBAXFGAhEHAECAB.fHGBhFHACBEB.IGEAhIGA.cDECdDGAbEACGDCAWDHHLDEDhAAANIBBPBCCSGAAhAGAgGAAYHFGHDAD",
            "FAGARAAAGADAMCIB.UAGA..XGGA..bCACeBGBVDFAhAAAIFGA..hIHAEEAAgDEAhAAAdFCAfFEACHFDJGCGSCDATGFADFIBaGABWHBGhIAAAAFBBHBDOBCA.YEAChHAA.",
            "LHAATAAA.XGDBJACA..UAFAaHFC.KAHBHEBAZCAARDDFeFHDhHGA.EEFBODFAhAAASEHBACCAGEAAcFAChEDA..fEFAgCAAFEHDQCEDhDIANHCBCHGA.IBAAhDDADICA.VAGB",
            "OHACLAAA.CAEADAGAQFAD.EDAFFBFDRECB.SBHBhABAGBDAVDFBUCAAaCFAKCCAhACAeBBB..fDGAhAEAgGFGPECCYADC.NEIAcABAhGHAJEHBdFAAXGBDhCEA.bGDAIAHAACIB",
            ".aAAA.....IADA.dAFA.KAHB...TBAAhAHA.HCCA.UDGAhBAA.JDAF.MECBhBFA.CEDA....gFDA.NIABFGFDhCEA.....hIIH....",
            "MAAADAAAdADAFHGAGAFA.KAHBRACAEGEA.eCCAQBAA.hIGAODABLEFAZDCB.hAFAPAFABDEAaDGDTCFAhEHASEHHXECAAECBHFABIGCAJFEB.hAHAcCABCGGD.YHAAhGIAWHFA.gEAF",
            "aAGAeAAAGADBBADAEAFAdCGBHDGD..MAIBXDEDKBGBLCAAhAAAOFHD..YABEhAAAPCEBRDFF.ACBAUEAAhABAVCCBSGGD.WCBFTGCAhAFANBAAgFAAQGEChCFAFGDAJEHBIHCAfHAB",
            "VGEARAAAdADA......NAFA.bFCDBAGAMFIB..OAHDUFABEEGBhIHAeCAB..gDBChEAAFCCHQDGChAAAICAAHDDB.SEADWAACCBBEZBDATGBAhABAfEEAPFHDYBHBhCHAXGEA",
            "ZGFABAAAHEDHJACA.aFGELAFBGCBA..QDFARBAA.VBDEhDDAfAHA.hGDAeCCAIHCFACIBUEAAhAAAWBHHFDGDdEDAOACG.cBABPFFBSGADhDBADEIBMHAAKGHDhAHACAACEAGB...",
            ".CAAAGABAMFIB..IHGEaADA.eDHFOAFD.BAHATBGAhBIAQCDDHFEDYAFEhFGALCHBWHBAhIAAZDAFDEAB.dEEDVBCARFCFJGEEECAEfGHAhAAAbDAGcFABABDA...gDEChFDAKHAA."
    };

    static final int SCORES[] = {
            22, 7, 24, -19, -11, 4, 20, -1, 22, -10, -101, 7, 2, 0, 23, 9, -2, -2, 19, -5, 15, 9
    };

    static final String INVALID_PLACEMENTS[] = {
            "LHAAOAAAHADA.MAFANCAA..QFBCXBACIBHAhBBATDCAdGEC.MHFGhGBAKCCAVCFBSFHFhGHAFDADWDHFGBEAAECAaECEECBFJFEBfAAEhFEARGAA.cABAgGGGhCFACFGCYAFC.",
            "eAFABAAAMACAOBHHUAGA..TCFA..VBBAXFGAhEHAECAB.fHGBhFHACBEB.IGEAhIGA.hDECdDGAbEACGDCAWDHFLDEDhAAANIBBPBCCSGAAhAGAgGAAYHFGHDAD",
            "FAGA.RAAAGADAMCIB.UAGA..XGGA..bCACeBGBVDFAhAAAIFGAEEAA.gDEAhIHAdFCAfFEAhABACHFDJGCGSCDATGFADFIBaGABWHBGBAFDhADAcEBD.YACChCEA.PCBBNIBB",
            "LHAATAAA.XGDBJACA..UAFAaHFC.KAHBHEBAZCAARDDFeFHDhHGA.EEFBODFAhAAASEHBACCAGEAALFAChEDA..fEFAgCAAFEHDQCEDhDIANHCBCHGA.IBAAhDDADICA.VAGBYACGMAAA",
            "OHACLAAA.CAEADAGAQFAD.EDAFFBFDRECB.SBHBhABANEIAGBDAVDFBUCAAaCFAKCCAhACAeBBB..fDGAhAEAgGFGPECCYADC.NEIAcABAhGHAJEHBdFAAXGBDhCEA.bGDAIAHAACIB",
            ".aAAA.....IADA.dAFA.KAHB...TBAAhAHA.HCCA.UDGAhBAA.JDAF.MECBhBFA.CEDA....gFDA.NIABFGFDhCEA.....hIIHhADA....",
            "MAAADAAAdADAFHGAGAFA.KAHBRACAEGEA.eCCAQBAA.hIGAODABLEFAZDCB.hAFAPAFABDEAaDGDTCFAhEHASEHFYECAAECBHFABIGCAJFEB.hAHAcCABCGGD.XHAAhGIAWHFA.gEAF",
            "hAGAeAAAGADBBADAEAFAdCGBHDGD..MAIBXDEDKBGBLCAAhAAAOFHD..YABEhAAAPCEBRDFF.ACBAUEAAhABAVCCBSGGD.WCBHTGCAhAFANBAAgFAAQGEChCFAFGDAJEHBIHCAfHAB",
            "VGEARAAAdADA......NAFA.bFCDBAGAMFIB..OAHDUFABEEGBhIHAeCAB..gDBChEAAFCCHIDGChAAAQCAAHDDB.SEADWAACXAEBhAHAZFFEcFCCaCHDPDEBhDHAJHAAYHEGCHAAfCFH",
            "ZGFABAAAHEDHJACA.aFGCLAFBGCBA..QDFARBAA.VBDEhDDAfAHA.hGDAeCCAIHCFACIBUEAAhAAAWBHHFDGDdEDAOACG.cBABPFFBSGADhDBADEIBMHAAKGHDhAHACAACEAGB...",
            ".CAAAGABAMFIB..IHGEaADA.eDHFOAFD.BAHATBGAhBIAQCDDHFEDYAFEhFGALCHBWHBAhSAAZDAFDEAB.dEEDVBCARFCFJGEEECAEfGHAhAAAbDAGcFABABDA...gDEChFDAKHAA."
    };

    static String badlyFormedTilePlacement(Random r) {
        char a = (char) ('A' + r.nextInt(ROWS));
        char bada = (char) ('A' + ROWS + r.nextInt(r.nextInt(BAD)));
        char b = (char) ('A' + r.nextInt(COLS));
        char badb = (char) ('A' + COLS + r.nextInt(BAD));
        char c = (char) ('A' + r.nextInt(TILES));
        if (c > 'Z') c += ('a'-'Z');
        char badc = (char) ('A' + TILES + r.nextInt(BAD));
        char d = (char) ('A' + ORIENTATIONS);
        char badd = (char) ('A' + ORIENTATIONS + r.nextInt(BAD));
        String test = "";
        switch (r.nextInt(5)) {
            case 0:
                test += "" + bada + b + c + d;
                break;
            case 1:
                test += "" + a + badb + c + d;
                break;
            case 2:
                test += "" + a + b + badc + d;
                break;
            case 3:
                test += "" + a + b + c + badd;
                break;
            default:
                test += "" + bada + b + badc + badd;
        }
        return test;

    }
}
