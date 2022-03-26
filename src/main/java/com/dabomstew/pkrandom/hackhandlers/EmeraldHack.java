package com.dabomstew.pkrandom.hackhandlers;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;
import com.dabomstew.pkrandom.RomFunctions;

public class EmeraldHack {
    public static int[][] getTutorIds() {
        int[] tutorIds1 = new int[] {11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 36,
                39, 142, 143, 146};
        int[] tutorIds2 = new int[] {26, 37, 38, 116, 28, 29, 35, 115, 77, 33, 34, 45, 46, 47, 53,
                102, 108, 148};
        int[] tutorIds3 = new int[] {27, 30, 31, 113, 43, 44, 112, 111, 110, 52, 81, 105, 118, 86,
                109, 55, 125, 149};
        int[] tutorIds4 = new int[] {40, 41, 42, 48, 49, 51, 107, 50, 103, 91, 56, 57, 58, 99, 114,
                106, 123, 145};
        int[] tutorIds5 =
                new int[] {54, 60, 78, 64, 65, 66, 82, 83, 74, 72, 61, 97, 98, 59, 32, 67, 89, 144};
        int[] tutorIds6 = new int[] {84, 75, 76, 73, 68, 71, 85, 62, 101, 117, 124, 0, 100, 119,
                120, 121, 122, 147};
        int[] tutorIds7 = new int[] {63, 95, 96, 104, 88, 90, 92, 93, 94, 79, 80, 87, 69, 70, 126};
        return new int[][] {tutorIds1, tutorIds2, tutorIds3, tutorIds4, tutorIds5, tutorIds6,
                tutorIds7};
    }

    public static byte[][] getTutorBytes() {
        byte[] tutorMoves1 = new byte[] {-46, 0, -51, 0, 69, 0, 87, 1, -102, 1, 95, 1, -62, 1, 58,
                1, -127, 0, 33, 1, 102, 0, -67, 0, 118, 0, -46, 1, -94, 0, -44, 1, -12, 0, 116, 0,
                -65, 0, 99, 2};
        byte[] tutorMoves2 = new byte[] {68, 0, 27, 1, -124, 1, 111, 0, -80, 1, 21, 1, -3, 0, -6, 1,
                124, 1, -21, 0, -36, 0, 67, 0, 78, 1, -119, 1, -49, 0, 42, 0, 85, 1, 97, 0};
        byte[] tutorMoves3 = new byte[] {68, 1, 100, 1, -110, 1, -85, 1, -60, 0, 15, 2, -90, 1, -88,
                1, -89, 1, 106, 1, 22, 2, 23, 1, 65, 2, 92, 1, 94, 1, -121, 0, 62, 0, 100, 0};
        byte[] tutorMoves4 = new byte[] {9, 0, 7, 0, 8, 0, 110, 1, -84, 1, 34, 0, 77, 1, 18, 2, -77,
                0, -107, 1, -125, 1, 16, 1, 17, 2, 43, 1, -72, 1, 55, 1, -109, 1, -107, 2};
        byte[] tutorMoves5 = new byte[] {-70, 1, -111, 1, -104, 1, 84, 1, -41, 0, 20, 1, -14, 0, 83,
                2, -71, 1, 48, 1, -113, 0, 23, 2, -12, 1, 15, 1, -33, 0, 14, 1, -122, 1, 118, 2};
        byte[] tutorMoves6 =
                new byte[] {-32, 0, -116, 1, 126, 2, -98, 1, 1, 1, -104, 2, -30, 0, -20, 1, 120, 2,
                        54, 2, 53, 1, -108, 2, -29, 0, 68, 2, 92, 2, 69, 2, -127, 2, 100, 2};
        byte[] tutorMoves7 = new byte[] {38, 0, 71, 2, -95, 1, 120, 0, 114, 1, -118, 1, -99, 1, -75,
                1, -74, 1, 30, 2, 56, 0, 93, 1, -56, 0, 26, 1, -29, 1};
        return new byte[][] {tutorMoves1, tutorMoves2, tutorMoves3, tutorMoves4, tutorMoves5,
                tutorMoves6, tutorMoves7};
    }

    public static int getTMHMTableAdress(File romFile) {
        byte[] romData = initRomData(romFile);
        // String romString = Utilities.byteArrayAsHexStringClean(romData);
        String lookingFor = "08 01 51 01 60 01 5B 01"; // Beginning of tm/hm table...
        // MOVE_FOCUS_PUNCH, = 01 08
        // MOVE_DRAGON_CLAW, = 01 51
        // MOVE_WATER_PULSE, = 01 60
        // MOVE_CALM_MIND, = 01 5B
        // The above are then little endian'd and concatenated.
        // return romString.indexOf(lookingFor)/3;
        byte[] byteArray = new byte[lookingFor.length() / 3 + 1];
        String[] splitBytes = lookingFor.split(" ");
        for (int i = 0; i < splitBytes.length; i++) {
            byteArray[i] = (byte) Integer.parseInt(splitBytes[i], 16);
        }
        int firstOccurence = RomFunctions.search(romData, byteArray).get(0) + 20;
        byte[] newByteArray = new byte[(int) (Math.pow(2, 25) - 50)];
        System.arraycopy(romData, firstOccurence, newByteArray, 0, (int) (Math.pow(2, 25) - 50));
        int secondOcurrence = RomFunctions.search(newByteArray, byteArray).get(0);
        return secondOcurrence + firstOccurence;
    }

    public static int getTMHM2TableAdress(File romFile) {
        byte[] romData = initRomData(romFile);
        // String romString = Utilities.byteArrayAsHexStringClean(romData);
        String lookingFor = "63 01 9B 01 9C 01 D9 01"; // Beginning of tm/hm2 table...
        // 355 = Roost = 163 = 01 63 = 63 01
        // 411 = Focus blast = 19B = 01 9B = 9B 01
        // 412 = Energy ball = 19C = 01 9C = 9C 01
        // 473 = psyshock = 1D9 = 01 D9 = D9 01
        // The above are then little endian'd and concatenated.
        // return romString.indexOf(lookingFor)/3;
        byte[] byteArray = new byte[lookingFor.length() / 3 + 1];
        String[] splitBytes = lookingFor.split(" ");
        for (int i = 0; i < splitBytes.length; i++) {
            byteArray[i] = (byte) Integer.parseInt(splitBytes[i], 16);
        }
        return RomFunctions.search(romData, byteArray).get(0);
    }


    public static int getTutorLearnsetsAdress(File romFile) {
        byte[] romData = initRomData(romFile);
        // String romString = Utilities.byteArrayAsHexStringClean(romData);
        String lookingFor = "02 08 60 80 42 00 28 80"; // Beginning of tm/hm table...
        // 0x80600802,0x80280042 is the bulbasaur start, and then it's little endian'd
        // return romString.indexOf(lookingFor)/3;
        byte[] byteArray = new byte[lookingFor.length() / 3 + 1];
        String[] splitBytes = lookingFor.split(" ");
        for (int i = 0; i < splitBytes.length; i++) {
            byteArray[i] = (byte) Integer.parseInt(splitBytes[i], 16);
        }
        return RomFunctions.search(romData, byteArray).get(0);

    }

    public static void main(String... args) {
        File mapFile = new File("/home/bspector/java/universal-pokemon-randomizer/pokeemerald.map");
        File romFile = new File("/home/bspector/java/games/Pokemon Inclement Emerald.gba");
        generateConfig(mapFile, romFile);
    }

    public static void generateConfig(File mapFile, File romFile) {
        if (!mapFile.exists()) {
            throw new RuntimeException(mapFile.getName() + " was not found: "
                    + "Error generating the custom config file");
        }
        if (!romFile.exists()) {
            throw new RuntimeException(romFile.getName() + " was not found: "
                    + "Error generating the custom config file");
        }
        byte[] romData = initRomData(romFile);
        String mapFileAsString = readLineByLineJava8(mapFile.getAbsolutePath());
        // MemoryExplorer.initMemoryAdressMapping(mapFile.getPath());
        String[] splitByLine = mapFileAsString.split("\n");
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, String> map2 = new HashMap<>();
        map.put("Base Stats", " gBaseStats");
        map.put("Pokemon Front", " gMonFrontPicTable");
        map.put("Pokemon Back", " gMonBackPicTable");
        map.put("Pokemon Shiny Palette", " gMonShinyPaletteTable");
        map.put("Pokemon Regular Palette", " gMonPaletteTable");
        map.put("Pokemon Evolution", " gEvolutionTable");
        map.put("Pokemon Battle Moves", " gBattleMoves");
        map.put("Hall Of Fame", " gText_WelcomeToHOF");
        map.put("Move Names", " gMoveNames");
        map.put("Pokemon Names", " gSpeciesNames");
        map.put("Tutor Table", " gTutorMoves");
        map.put("Level Up Table", " gLevelUpLearnsets");
        map.put("Tm/Hm Learn Sets", " gTMHMLearnsets");
        map.put("Wild Headers", " gWildMonHeaders");
        map.put("Trainers", " gTrainers");
        map.put("Ability Names", " gAbilityNames");
        map.put("Item Names", " gItems");
        String tutorAdress = null;
        String moveNamesAdress = null;
        ArrayList<String> toPrint = new ArrayList<>();
        loop1: for (String key : map.keySet()) {
            String toLookFor = map.get(key);
            for (String s : splitByLine) {
                if (s.contains(toLookFor)) {
                    String[] ss = s.trim().replaceAll(" +", " ").split(" ");
                    if (key.equalsIgnoreCase("Tutor Table")) {
                        tutorAdress = ss[0];
                    }
                    if (key.equalsIgnoreCase("Move Names")) {
                        moveNamesAdress = ss[0];
                    }
                    toPrint.add(key + " = " + ss[0]);
                    continue loop1;
                }
            }
        }
        toPrint.add(
                "TM/HM Table1 = 0x0000000008" + Integer.toHexString(getTMHMTableAdress(romFile)));
        toPrint.add(
                "TM/HM Table2 = 0x0000000008" + Integer.toHexString(getTMHM2TableAdress(romFile)));
        toPrint.add("Tutor Learn Sets = 0x0000000008"
                + Integer.toHexString(getTutorLearnsetsAdress(romFile)));

        byte[][] tutorBytes = getTutorBytes();
        for (int i = 0; i < tutorBytes.length; i++) {
            int location = RomFunctions.search(romData, tutorBytes[i]).get(0);
            toPrint.add("Tutor Gym" + (i + 1) + " = 0x0000000008" + Integer.toHexString(location));
        }

        int fromAdressT = Integer.parseInt(tutorAdress, 16);
        int fromAdressM = Integer.parseInt(moveNamesAdress, 16);
        int toAdress = (int) Math.pow(2, 25) - 50;// utils.convertFromHex(adressesSeperated[1]);
        byte[] tutorData = new byte[toAdress];
        System.arraycopy(romData, fromAdressT, tutorData, 0, toAdress);

        int tableUnitSize = 2;
        ArrayList<Integer> tutorMoveIndexList = new ArrayList<>();
        for (int i = 2; i < 18; i++) {
            int start = tableUnitSize * i;
            byte[] unitArray = new byte[tableUnitSize];
            System.arraycopy(tutorData, start, unitArray, 0, tableUnitSize);
            int moveId = ((unitArray[1] & 0xff) * 256) + (unitArray[0] & 0xff);
            tutorMoveIndexList.add(moveId);
        }

        byte[] moveNameData = Arrays.copyOfRange(romData, fromAdressM, toAdress);
        tableUnitSize = 13;
        ArrayList<String> moveNameList = new ArrayList<>();
        for (int i = 0; i < 914; i++) {
            int start = tableUnitSize * i;
            byte[] unitArray = new byte[tableUnitSize];
            System.arraycopy(moveNameData, start, unitArray, 0, tableUnitSize);
            StringBuilder stringBuilder = new StringBuilder();
            for (byte B : unitArray) {
                int ii = B & 0xff;
                stringBuilder.append((char) ii);
                // String hex = Integer.toHexString(ii);
                // stringBuilder.append(PokemonNameUtils.getLetter(hex));
            }
            String moveName = stringBuilder.toString().replace("^", " ").trim();
            moveNameList.add(moveName);
        }

        HashMap<String, String> shortTextToTrueText = new HashMap<>();
        shortTextToTrueText.put("gText_HiHorsepower", "gText_HighHorsepower");
        shortTextToTrueText.put("gText_StmpngTantrm", " gText_StompingTantrum");
        shortTextToTrueText.put("gText_ElctrcTrrain", " gText_ElectricTerrain");
        shortTextToTrueText.put("gText_PsychcTrrain", " gText_PsychicTerrain");
        shortTextToTrueText.put("gText_GrssyTerrain", " gText_GrassyTerrain");
        /*
         * gText_ScorchngSnds was not found! //Gen 8 gText_TripleAxel was not found! //Gen 8
         * gText_DualWingbeat was not found! //Gen 8 gText_ExpandngForc was not found! //Gen 8
         * gText_TerrainPulse was not found! //Gen 8 gText_RisngVoltage was not found! //Gen 8
         * gText_MeteorBeam was not found! //Gen 8 gText_FlipTurn was not found! //Gen 8
         * gText_SkitterSmack was not found! //Gen 8 gText_GrassyGlide was not found! //Gen 8
         * gText_ScaleShot was not found! //Gen 8 gText_Coaching was not found! //Gen 8
         * gText_Mistsplosion was not found! //Gen 8 gText_BodyPress was not found! //Gen 8
         * gText_Poltergeist was not found! //Gen 8 gText_SecretSword was not found! //Move Tutor
         * Not In Menu gText_DragonAscent was not found! //Move Tutor Not In Menu gText_RelicSong
         * was not found! //Move Tutor Not In Menu gText_DracoMeteor was not found! //Move Tutor Not
         * In Menu
         */
        for (int i = 0; i < 600; i++) {
            String programText = "gText_"
                    + moveNameList.get(tutorMoveIndexList.get(i)).replace("-", "").replace(" ", "");
            if (shortTextToTrueText.containsKey(programText)) {
                programText = shortTextToTrueText.get(programText);
            }
            programText = programText.trim();
            map2.put(moveNameList.get(tutorMoveIndexList.get(i)) + "["
            // + MemoryExplorer.memoryAdressNameToSize.get(programText) + "] {" + i + "}",
                    + "X] {" + i + "}", " " + programText);
        }

        ArrayList<String> forbidden = new ArrayList<>();

        forbidden.add(" gText_SeismicToss24BP");
        forbidden.add(" gText_Swift24BP");
        forbidden.add(" gText_MudSlap24BP");
        forbidden.add(" gText_PsychUp48BP");
        forbidden.add(" gText_Counter48BP");
        forbidden.add(" gText_DefenseCurl16BP");
        forbidden.add(" gText_IcyWind24BP");
        forbidden.add(" gText_FirePunch48BP");
        forbidden.add(" gText_IcePunch48BP");
        forbidden.add(" gText_ThunderPunch48BP");
        forbidden.add(" gText_BodySlam48BP");

        loop2: for (String key : map2.keySet()) {
            String toLookFor = map2.get(key);
            loop3: for (String s : splitByLine) {
                if (s.contains(toLookFor)) {
                    for (String sss : forbidden) {
                        if (s.contains(sss)) {
                            continue loop3;
                        }
                    }
                    String[] ss = s.trim().replaceAll(" +", " ").split(" ");
                    toPrint.add(key + " = " + ss[0]);
                    continue loop2;
                }
            }
            System.out.println(toLookFor + " was not found!");
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < toPrint.size(); i++) {
            stringBuilder.append(toPrint.get(i));
            if (i != toPrint.size() - 1) {
                stringBuilder.append("\n");
            }
        }

        try {
            Files.write(Paths.get(romFile.getName().replace(".gba", "_") + "config"),
                    stringBuilder.toString().getBytes());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        for (String s : toPrint) {
            System.out.println(s);
        }
    }

    public static String readLineByLineJava8(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }

    public static byte[] initRomData(File file) {
        System.out.println("[INFO] Init Rom Data");
        byte[] romData;
        try {
            romData = Files.readAllBytes(Paths.get(file.getPath()));
        } catch (Throwable t) {
            t.printStackTrace();
            return null;
        }
        return romData;
    }
}



// Pokemon Shiny Palette = 0x0000000008367344
// Hall Of Fame=0x00000000086e881c
// Pokemon Regular Palette=0x0000000008363864
// Tutor Table = 0x0000000008727070
// Pokemon Names = 0x000000000838c040
// Tm/Hm Learn Sets=0x00000000083a0fcc
// Base Stats = 0x00000000083a6880
// Move Names = 0x000000000838f46a
// Level Up Table=0x00000000083d9204
// Trainers=0x0000000008383aa8
// Wild Headers = 0x0000000008621250
// Pokemon Front = 0x0000000008374e3c
// Pokemon Battle Moves=0x000000000839b30c
// Ability Names = 0x0000000008396bd5
// Pokemon Back = 0x000000000835fd84
// Pokemon Evolution = 0x00000000083c16a4
// Item Names = 0x0000000008671bb4
// TM/HM Table1 = 0x000000000872d974
// TM/HM Table2 = 0x000000000872d9d8
