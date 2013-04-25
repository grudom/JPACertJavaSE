package de.jpa.cert.domain;

/**
 * Enum for slot names
 *
 * Created by: gruppd, 04.02.13 18:11
 */
public enum SlotNameEnum {
    /*
	 * ~~ values ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 */
    A("A",0),B("B",1),C("C",2),D("D",3),E("E",4),F("F",5);

    /*
	 * ~~ attributes ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 */
    final String slotNu;
    final int level;

    /*
	 * ~~ constructor ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 */
    private SlotNameEnum(String slotNu, int level) {
        this.slotNu = slotNu;
        this.level = level;
    }

    /*
     * ~~~~ Getters / Setters ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    public static SlotNameEnum textValueOf(final int level) {
        for (final SlotNameEnum checkItm : SlotNameEnum.values()) {
            if (checkItm.getLevel() == level) {
                return checkItm;
            }
        }
        return null;
    }

    public int getLevel() {
        return level;
    }

    public String getSlotNu() {
        return slotNu;
    }

}
